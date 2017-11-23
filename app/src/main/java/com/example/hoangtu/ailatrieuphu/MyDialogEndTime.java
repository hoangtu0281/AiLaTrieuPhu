package com.example.hoangtu.ailatrieuphu;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;


public class MyDialogEndTime extends Dialog implements View.OnClickListener {
    private Button mBtnOk;

    public MyDialogEndTime(Context context) {

        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_end_time);
        initView();
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    private void initView() {
        mBtnOk = (Button) findViewById(R.id.bt_ok_end_time);
        mBtnOk.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_ok_end_time:
                dismiss();
                break;
        }
    }
}
