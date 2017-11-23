package com.example.hoangtu.ailatrieuphu;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MyDialogExitGame extends Dialog implements View.OnClickListener {

    private Button btThoat, btKhong;
    public static final int DUNG_CUOC_CHOI = 1;
    public static final int KHONG_DUNG_CUOC_CHOI = -1;
    private int isExit = 0;

    public MyDialogExitGame(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.exit_dialog);
        initViews();
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    private void initViews() {
        btKhong=(Button)findViewById(R.id.bt_khong);
        btThoat=(Button)findViewById(R.id.bt_thoat);
        btThoat.setOnClickListener(this);
        btKhong.setOnClickListener(this);

        //this.getWindow().getAttributes().windowAnimations = R.style.DialogAnim;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_thoat:
                getOwnerActivity().finish();
                isExit = DUNG_CUOC_CHOI;
                break;
            case R.id.bt_khong:
                isExit = KHONG_DUNG_CUOC_CHOI;
                dismiss();
                break;
            default:
                break;
        }
    }
    public int getIsExit(){
        return isExit;
    }
    public void setIsExit(int exit){
        this.isExit = exit;
    }
}
