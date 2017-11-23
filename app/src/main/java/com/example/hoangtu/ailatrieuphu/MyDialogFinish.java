package com.example.hoangtu.ailatrieuphu;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.TextView;


public class MyDialogFinish extends Dialog {
    private TextView mTxtNumberQuestion, mTxtMoney;

    public MyDialogFinish(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_finish_game);
        initView();
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    private void initView() {
        //mTxtMoney = (TextView) findViewById(R.id.tv_finish_money_vnd);
        //mTxtNumberQuestion = (TextView) findViewById(R.id.tv_finish_tai_cau_hoi);


        //getWindow().getAttributes().windowAnimations = R.style.DialogAnim;
    }

    public TextView getmTxtNumberQuestion() {
        return mTxtNumberQuestion;
    }

    public TextView getmTxtMoney() {
        return mTxtMoney;
    }
}
