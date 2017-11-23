package com.example.hoangtu.ailatrieuphu;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.view.Window;
import android.widget.TableRow;


public class MyDialogMoneyShow extends Dialog {
    private static final int QUESTION1 = 1;
    private static final int QUESTION2 = 2;
    private static final int QUESTION3 = 3;
    private static final int QUESTION4 = 4;
    private static final int QUESTION5 = 5;
    private static final int QUESTION6 = 6;
    private static final int QUESTION7 = 7;
    private static final int QUESTION8 = 8;
    private static final int QUESTION9 = 9;
    private static final int QUESTION10 = 10;
    private static final int QUESTION11 = 11;
    private static final int QUESTION12 = 12;
    private static final int QUESTION13 = 13;
    private static final int QUESTION14 = 14;
    private static final int QUESTION15 = 15;
    private TableRow mTable1, mTable2, mTable3, mTable4, mTable5, mTable6, mTable7,
            mTable8, mTable9, mTable10, mTable11, mTable12, mTable13, mTable14, mTable15;

    public MyDialogMoneyShow(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_money);

        initView();
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    private void initView() {
        mTable1 = (TableRow) findViewById(R.id.tbRow_1);
        mTable2 = (TableRow) findViewById(R.id.tbRow_2);
        mTable3 = (TableRow) findViewById(R.id.tbRow_3);
        mTable4 = (TableRow) findViewById(R.id.tbRow_4);
        mTable5 = (TableRow) findViewById(R.id.tbRow_5);
        mTable6 = (TableRow) findViewById(R.id.tbRow_6);
        mTable7 = (TableRow) findViewById(R.id.tbRow_7);
        mTable8 = (TableRow) findViewById(R.id.tbRow_8);
        mTable9 = (TableRow) findViewById(R.id.tbRow_9);
        mTable10 = (TableRow) findViewById(R.id.tbRow_10);
        mTable11 = (TableRow) findViewById(R.id.tbRow_11);
        mTable12 = (TableRow) findViewById(R.id.tbRow_12);
        mTable13 = (TableRow) findViewById(R.id.tbRow_13);
        mTable14 = (TableRow) findViewById(R.id.tbRow_14);
        mTable15 = (TableRow) findViewById(R.id.tbRow_15);


    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void setBackgroundTableRow(int index) {
        switch (index) {
            case QUESTION1:
                mTable1.setBackground(null);
                mTable2.setBackground(null);
                mTable3.setBackground(null);
                mTable4.setBackground(null);
                mTable5.setBackground(null);
                mTable6.setBackground(null);
                mTable7.setBackground(null);
                mTable8.setBackground(null);
                mTable9.setBackground(null);
                mTable10.setBackground(null);
                mTable11.setBackground(null);
                mTable12.setBackground(null);
                mTable13.setBackground(null);
                mTable14.setBackground(null);
                mTable15.setBackground(null);

                break;
            case QUESTION2:
                mTable1.setBackground(null);
                mTable2.setBackground(null);
                mTable3.setBackground(null);
                mTable4.setBackground(null);
                mTable5.setBackground(null);
                mTable6.setBackground(null);
                mTable7.setBackground(null);
                mTable8.setBackground(null);
                mTable9.setBackground(null);
                mTable10.setBackground(null);
                mTable11.setBackground(null);
                mTable12.setBackground(null);
                mTable13.setBackground(null);
                mTable14.setBackground(null);
                mTable15.setBackground(null);
                break;
            case QUESTION3:
                mTable1.setBackground(null);
                mTable2.setBackground(null);
                mTable3.setBackground(null);
                mTable4.setBackground(null);
                mTable5.setBackground(null);
                mTable6.setBackground(null);
                mTable7.setBackground(null);
                mTable8.setBackground(null);
                mTable9.setBackground(null);
                mTable10.setBackground(null);
                mTable11.setBackground(null);
                mTable12.setBackground(null);
                mTable13.setBackground(null);
                mTable14.setBackground(null);
                mTable15.setBackground(null);
                break;
            case QUESTION4:
                mTable1.setBackground(null);
                mTable2.setBackground(null);
                mTable4.setBackground(null);
                mTable3.setBackground(null);
                mTable5.setBackground(null);
                mTable6.setBackground(null);
                mTable7.setBackground(null);
                mTable8.setBackground(null);
                mTable9.setBackground(null);
                mTable10.setBackground(null);
                mTable11.setBackground(null);
                mTable12.setBackground(null);
                mTable13.setBackground(null);
                mTable14.setBackground(null);
                mTable15.setBackground(null);
                break;
            case QUESTION5:
                mTable1.setBackground(null);
                mTable2.setBackground(null);
                mTable5.setBackground(null);
                mTable4.setBackground(null);
                mTable3.setBackground(null);
                mTable6.setBackground(null);
                mTable7.setBackground(null);
                mTable8.setBackground(null);
                mTable9.setBackground(null);
                mTable10.setBackground(null);
                mTable11.setBackground(null);
                mTable12.setBackground(null);
                mTable13.setBackground(null);
                mTable14.setBackground(null);
                mTable15.setBackground(null);
                break;
            case QUESTION6:
                mTable1.setBackground(null);
                mTable2.setBackground(null);
                mTable6.setBackground(null);
                mTable4.setBackground(null);
                mTable5.setBackground(null);
                mTable3.setBackground(null);
                mTable7.setBackground(null);
                mTable8.setBackground(null);
                mTable9.setBackground(null);
                mTable10.setBackground(null);
                mTable11.setBackground(null);
                mTable12.setBackground(null);
                mTable13.setBackground(null);
                mTable14.setBackground(null);
                mTable15.setBackground(null);
                break;
            case QUESTION7:
                mTable1.setBackground(null);
                mTable2.setBackground(null);
                mTable7.setBackground(null);
                mTable4.setBackground(null);
                mTable5.setBackground(null);
                mTable6.setBackground(null);
                mTable3.setBackground(null);
                mTable8.setBackground(null);
                mTable9.setBackground(null);
                mTable10.setBackground(null);
                mTable11.setBackground(null);
                mTable12.setBackground(null);
                mTable13.setBackground(null);
                mTable14.setBackground(null);
                mTable15.setBackground(null);
                break;
            case QUESTION8:
                mTable1.setBackground(null);
                mTable2.setBackground(null);
                mTable8.setBackground(null);
                mTable4.setBackground(null);
                mTable5.setBackground(null);
                mTable6.setBackground(null);
                mTable7.setBackground(null);
                mTable3.setBackground(null);
                mTable9.setBackground(null);
                mTable10.setBackground(null);
                mTable11.setBackground(null);
                mTable12.setBackground(null);
                mTable13.setBackground(null);
                mTable14.setBackground(null);
                mTable15.setBackground(null);
                break;
            case QUESTION9:
                mTable1.setBackground(null);
                mTable2.setBackground(null);
                mTable9.setBackground(null);
                mTable4.setBackground(null);
                mTable5.setBackground(null);
                mTable6.setBackground(null);
                mTable7.setBackground(null);
                mTable8.setBackground(null);
                mTable3.setBackground(null);
                mTable10.setBackground(null);
                mTable11.setBackground(null);
                mTable12.setBackground(null);
                mTable13.setBackground(null);
                mTable14.setBackground(null);
                mTable15.setBackground(null);
                break;
            case QUESTION10:
                mTable1.setBackground(null);
                mTable2.setBackground(null);
                mTable10.setBackground(null);
                mTable4.setBackground(null);
                mTable5.setBackground(null);
                mTable6.setBackground(null);
                mTable7.setBackground(null);
                mTable8.setBackground(null);
                mTable9.setBackground(null);
                mTable3.setBackground(null);
                mTable11.setBackground(null);
                mTable12.setBackground(null);
                mTable13.setBackground(null);
                mTable14.setBackground(null);
                mTable15.setBackground(null);
                break;
            case QUESTION11:
                mTable1.setBackground(null);
                mTable2.setBackground(null);
                mTable11.setBackground(null);
                mTable4.setBackground(null);
                mTable5.setBackground(null);
                mTable6.setBackground(null);
                mTable7.setBackground(null);
                mTable8.setBackground(null);
                mTable9.setBackground(null);
                mTable10.setBackground(null);
                mTable3.setBackground(null);
                mTable12.setBackground(null);
                mTable13.setBackground(null);
                mTable14.setBackground(null);
                mTable15.setBackground(null);
                break;
            case QUESTION12:
                mTable1.setBackground(null);
                mTable2.setBackground(null);
                mTable12.setBackground(null);
                mTable4.setBackground(null);
                mTable5.setBackground(null);
                mTable6.setBackground(null);
                mTable7.setBackground(null);
                mTable8.setBackground(null);
                mTable9.setBackground(null);
                mTable10.setBackground(null);
                mTable11.setBackground(null);
                mTable3.setBackground(null);
                mTable13.setBackground(null);
                mTable14.setBackground(null);
                mTable15.setBackground(null);
                break;
            case QUESTION13:
                mTable1.setBackground(null);
                mTable2.setBackground(null);
                mTable13.setBackground(null);
                mTable4.setBackground(null);
                mTable5.setBackground(null);
                mTable6.setBackground(null);
                mTable7.setBackground(null);
                mTable8.setBackground(null);
                mTable9.setBackground(null);
                mTable10.setBackground(null);
                mTable11.setBackground(null);
                mTable12.setBackground(null);
                mTable3.setBackground(null);
                mTable14.setBackground(null);
                mTable15.setBackground(null);
                break;
            case QUESTION14:
                mTable1.setBackground(null);
                mTable2.setBackground(null);
                mTable14.setBackground(null);
                mTable4.setBackground(null);
                mTable5.setBackground(null);
                mTable6.setBackground(null);
                mTable7.setBackground(null);
                mTable8.setBackground(null);
                mTable9.setBackground(null);
                mTable10.setBackground(null);
                mTable11.setBackground(null);
                mTable12.setBackground(null);
                mTable13.setBackground(null);
                mTable3.setBackground(null);
                mTable15.setBackground(null);
                break;
            case QUESTION15:
                mTable1.setBackground(null);
                mTable2.setBackground(null);
                mTable15.setBackground(null);
                mTable4.setBackground(null);
                mTable5.setBackground(null);
                mTable6.setBackground(null);
                mTable7.setBackground(null);
                mTable8.setBackground(null);
                mTable9.setBackground(null);
                mTable10.setBackground(null);
                mTable11.setBackground(null);
                mTable12.setBackground(null);
                mTable13.setBackground(null);
                mTable14.setBackground(null);
                mTable3.setBackground(null);
                break;
            default:
                break;
        }
    }

}
