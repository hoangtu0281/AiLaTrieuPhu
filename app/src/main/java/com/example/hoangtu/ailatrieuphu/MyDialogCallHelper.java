package com.example.hoangtu.ailatrieuphu;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyDialogCallHelper extends Dialog implements View.OnClickListener {
    private ImageView mIvBacSi, mIvKiSu, mIvGiaoVien, mIvPhongVien;
    private TextView mTxtBacSi, mTxtKiSu, mTxtGiaoVien, mTxtPhongvien;
    private Button mBtnDong;

    private String cauTraLoi;

    public MyDialogCallHelper(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.ask_professional_layout);
        //getWindow().getAttributes().windowAnimations = R.style.Dialog_Call_Help;
        initViews();
        setCanceledOnTouchOutside(true);
        setCancelable(true);
    }

    private void initViews() {
        cauTraLoi = "phuong an : D";
        mIvBacSi = (ImageView) findViewById(R.id.iv_bacsi);
        mIvGiaoVien = (ImageView) findViewById(R.id.iv_giaovien);
        mIvKiSu = (ImageView) findViewById(R.id.iv_kisu);
        mIvPhongVien = (ImageView) findViewById(R.id.iv_phongvien);

        mTxtBacSi = (TextView) findViewById(R.id.tv_bacsi);
        mTxtGiaoVien = (TextView) findViewById(R.id.tv_giaovien);
        mTxtKiSu = (TextView) findViewById(R.id.tv_kisu);
        mTxtPhongvien = (TextView) findViewById(R.id.tv_phongvien);
        mBtnDong = (Button) findViewById(R.id.btn_call_helper_dong);
        mBtnDong.setOnClickListener(this);

        mIvBacSi.setOnClickListener(this);
        mIvPhongVien.setOnClickListener(this);
        mIvKiSu.setOnClickListener(this);
        mIvGiaoVien.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_call_helper_dong:
                dismiss();
                break;
            case R.id.iv_bacsi:
                mTxtBacSi.setText(cauTraLoi);
                mTxtBacSi.setTextSize(30);
                mTxtBacSi.setTextColor(Color.YELLOW);
                mIvGiaoVien.setVisibility(View.INVISIBLE);
                mIvPhongVien.setVisibility(View.INVISIBLE);
                mIvKiSu.setVisibility(View.INVISIBLE);

                mTxtGiaoVien.setVisibility(View.INVISIBLE);
                mTxtPhongvien.setVisibility(View.INVISIBLE);
                mTxtKiSu.setVisibility(View.INVISIBLE);
                break;
            case R.id.iv_giaovien:
                mTxtGiaoVien.setText(cauTraLoi);
                mTxtGiaoVien.setTextSize(30);
                mTxtGiaoVien.setTextColor(Color.YELLOW);
                mIvBacSi.setVisibility(View.INVISIBLE);
                mIvPhongVien.setVisibility(View.INVISIBLE);
                mIvKiSu.setVisibility(View.INVISIBLE);

                mTxtBacSi.setVisibility(View.INVISIBLE);
                mTxtPhongvien.setVisibility(View.INVISIBLE);
                mTxtKiSu.setVisibility(View.INVISIBLE);
                break;
            case R.id.iv_kisu:
                mTxtKiSu.setText(cauTraLoi);
                mTxtKiSu.setTextSize(30);
                mTxtKiSu.setTextColor(Color.YELLOW);
                mIvGiaoVien.setVisibility(View.INVISIBLE);
                mIvPhongVien.setVisibility(View.INVISIBLE);
                mIvBacSi.setVisibility(View.INVISIBLE);

                mTxtGiaoVien.setVisibility(View.INVISIBLE);
                mTxtPhongvien.setVisibility(View.INVISIBLE);
                mTxtBacSi.setVisibility(View.INVISIBLE);
                break;
            case R.id.iv_phongvien:
                mTxtPhongvien.setText(cauTraLoi);
                mTxtPhongvien.setTextSize(30);
                mTxtPhongvien.setTextColor(Color.YELLOW);
                mIvGiaoVien.setVisibility(View.INVISIBLE);
                mIvBacSi.setVisibility(View.INVISIBLE);
                mIvKiSu.setVisibility(View.INVISIBLE);

                mTxtGiaoVien.setVisibility(View.INVISIBLE);
                mTxtBacSi.setVisibility(View.INVISIBLE);
                mTxtKiSu.setVisibility(View.INVISIBLE);
                break;
            default:
                break;
        }
    }

    public String getCauTraLoi() {
        return cauTraLoi;
    }

    public void setCauTraLoi(String cauTraLoi) {
        this.cauTraLoi = cauTraLoi;
    }
}
