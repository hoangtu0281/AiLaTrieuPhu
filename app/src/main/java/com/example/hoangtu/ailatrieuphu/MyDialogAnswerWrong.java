package com.example.hoangtu.ailatrieuphu;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.Button;


public class MyDialogAnswerWrong extends Dialog implements View.OnClickListener {
    private int isOK = 0;
    public static final int OK = 1;
    public static final int NO = -1;
    private Button mBtnChoiLai, mBtnKhongChoiLai;

    public MyDialogAnswerWrong(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_answer_wrong);
        initView();
        setCanceledOnTouchOutside(true);
        setCancelable(true);
    }

    private void initView() {
        //this.getWindow().getAttributes().windowAnimations = R.style.DialogAnim;
        mBtnChoiLai = (Button) findViewById(R.id.bt_choilai);
        mBtnKhongChoiLai = (Button) findViewById(R.id.bt_khong_choilai);

        mBtnChoiLai.setOnClickListener(this);
        mBtnKhongChoiLai.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_choilai:
                isOK = OK;
                Intent intent = new Intent(getOwnerActivity(), MenuGameActivity.class);
                getOwnerActivity().startActivity(intent);
                break;
            case R.id.bt_khong_choilai:
                dismiss();
            default:
                break;
        }
    }

    public int getIsOK() {
        return isOK;
    }

    public void setIsOK(int isOK) {
        this.isOK = isOK;
    }
}
