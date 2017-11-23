package com.example.hoangtu.ailatrieuphu;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class DBManager {
    private static final String PATH_DB = Environment.getDataDirectory().getPath() + "/data/ailatrieuphu.trungns.com.ailatrieuphu/databases";
    private static final String DB_NAME = "Question";
    private static final String TAG = DBManager.class.getName();
    private static final String SQL_GET_15_QUESTION = "SELECT * FROM (SELECT * FROM Question ORDER BY RANDOM()) GROUP BY level ORDER BY level LIMIT 15";
    private SQLiteDatabase mSqliteDB;
    private List<Question> arrQ = new ArrayList<>();

    public DBManager(Context mContext) {
        copyDB(mContext);
    }

    public List<Question> getArrQ() {
        return arrQ;
    }

    public SQLiteDatabase getmSqliteDB() {
        return mSqliteDB;
    }

    public void get15Questions() {
        openDB();
        Cursor cursor = mSqliteDB.rawQuery(SQL_GET_15_QUESTION, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int quesionIndex = cursor.getColumnIndex("question");
            int levelIndex = cursor.getColumnIndex("level");
            int caseAIndex = cursor.getColumnIndex("casea");
            int caseBIndex = cursor.getColumnIndex("caseb");
            int caseCIndex = cursor.getColumnIndex("casec");
            int caseDIndex = cursor.getColumnIndex("cased");
            int trueCaseIndex = cursor.getColumnIndex("truecase");
            while (cursor.isAfterLast() == false) {
                String question, caseA, caseB, caseC, caseD;
                int level, trueCase;
                question = cursor.getString(quesionIndex);
                caseA = cursor.getString(caseAIndex);
                caseB = cursor.getString(caseBIndex);
                caseC = cursor.getString(caseCIndex);
                caseD = cursor.getString(caseDIndex);
                level = Integer.parseInt(cursor.getString(levelIndex));
                trueCase = Integer.parseInt(cursor.getString(trueCaseIndex));
                Question qItem = new Question(question, level,
                        caseA, caseB, caseC,
                        caseD, trueCase);
                arrQ.add(qItem);
                cursor.moveToNext();
            }
        }
        for (int i = 0; i < arrQ.size(); i++) {
            Log.i(TAG, arrQ.get(i).toString());
        }
    }


    public void openDB() {
        if (mSqliteDB == null || !mSqliteDB.isOpen()) {
            mSqliteDB = SQLiteDatabase.openDatabase(PATH_DB + "/" + DB_NAME, null, SQLiteDatabase.OPEN_READWRITE);
        }
    }

    public void closeDB() {
        if (mSqliteDB != null && mSqliteDB.isOpen()) {
            mSqliteDB.close();
        }
    }

    private void copyDB(Context mContext) {
        new File(PATH_DB).mkdir();
        File dbFile = new File(PATH_DB + "/" + DB_NAME);
        if (dbFile.exists()) {
            return;
        }
        AssetManager assetMgr = mContext.getAssets();
        try {
            InputStream input = assetMgr.open(DB_NAME);
            FileOutputStream output = new FileOutputStream(dbFile);
            int len;
            byte buff[] = new byte[1024];

            len = input.read(buff);
            while (len > 0) {
                output.write(buff, 0, len);
                len = input.read(buff);
            }
            input.close();
            output.close();
            Log.i(TAG, "DB is copied in data/data... ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
