package com.example.hoangtu.ailatrieuphu;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;


public class MyDialogChangeQuestion extends Dialog implements View.OnClickListener {
    private Button btnYes, btnNo;
    public static final int YES = 1;
    public static final int NO = -1;
    private int isChange = 0;

    public MyDialogChangeQuestion(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.change_question_dialog);
        initViews();
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    private void initViews() {
        btnNo = (Button) findViewById(R.id.btn_no_change);
        btnYes = (Button) findViewById(R.id.btn_yes_change);
        btnNo.setOnClickListener(this);
        btnYes.setOnClickListener(this);

        //this.getWindow().getAttributes().windowAnimations = R.style.DialogAnim;
    }

    public int getIsChange() {
        return isChange;
    }

    public void setIsChange(int change) {
        this.isChange = change;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes_change:
                isChange = YES;
                dismiss();
                break;
            case R.id.btn_no_change:
                isChange = NO;
                dismiss();
                break;
        }
    }
}
