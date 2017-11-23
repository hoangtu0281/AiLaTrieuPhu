package com.example.hoangtu.ailatrieuphu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;


public class FinishActivity extends Activity {
    private static final int CHOI_LAI = 101;
    private TextView mTxtNumberQuestion, mTxtMoney;
    private MyDialogAnswerWrong myDialogAnswerWrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_game);
        initView();
    }

    private void initView() {
        myDialogAnswerWrong = new MyDialogAnswerWrong(this);
        //mTxtMoney = (TextView) findViewById(R.id.tv_finish_money_vnd);
        //mTxtNumberQuestion = (TextView) findViewById(R.id.tv_finish_tai_cau_hoi);

        Intent intentResult = getIntent();
       // mTxtNumberQuestion.setText("BẠN ĐÃ DỪNG CUỘC CHƠI TẠI CÂU HỎI SỐ " );
        //mTxtMoney.setText(intentResult.getStringExtra(MainGameActivity.KEY_MONEY));
        myDialogAnswerWrong.setOwnerActivity(this);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                myDialogAnswerWrong.show();
            }
        }, 5000);
    }
}
