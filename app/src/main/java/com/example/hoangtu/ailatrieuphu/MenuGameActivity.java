package com.example.hoangtu.ailatrieuphu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MenuGameActivity extends Activity implements View.OnClickListener {
    public static final int SHOW_DIALOG = 1;
    public static final int NO_SHOW_DIALOG = -1;
    private Animation rotateLightLogo;
    private ImageView mIVLightLogo;
    private Animation animationLayout;
    private LinearLayout mLinerMenu;
    private Button mBtnPlayTrial, mBtnSignInZalo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_game);
        initView();
        initAnimationAndDialog();
    }

    private void initAnimationAndDialog() {
        //rotateLightLogo = AnimationUtils.loadAnimation(this, R.anim.rotate_anim_light_logo);
//        mIVLightLogo.startAnimation(rotateLightLogo);

        animationLayout = AnimationUtils.loadAnimation(this, R.anim.setanim_menu);


    }

    private void initView() {
        mLinerMenu = (LinearLayout) findViewById(R.id.liner_menu);
        mIVLightLogo = (ImageView) findViewById(R.id.iv_light_logo);
        mBtnPlayTrial = (Button) findViewById(R.id.btn_play_trial);
        mBtnSignInZalo = (Button) findViewById(R.id.btn_signin_zalo);

        mBtnPlayTrial.setOnClickListener(this);
        mBtnSignInZalo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play_trial:
                Intent intent = new Intent(MenuGameActivity.this, LoadGameActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_signin_zalo:
                Toast.makeText(this, "CANNOT LOG IN ZALO!", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

}
