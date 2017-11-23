package com.example.hoangtu.ailatrieuphu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class LoadGameActivity extends Activity {
    private Animation animationRotateLightLogo, animationRotateLoading;
    private ImageView mIVLoadGame, mIVLightLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_game_screen);
        initView();
    }

    private void initView() {
        mIVLoadGame = (ImageView) findViewById(R.id.iv_load_game);
        mIVLightLogo = (ImageView) findViewById(R.id.iv_light_logo);

        animationRotateLoading = AnimationUtils.loadAnimation(this, R.anim.rotate_anim_img_load_game);
        //animationRotateLightLogo = AnimationUtils.loadAnimation(this, R.anim.rotate_anim_light_logo);

        mIVLoadGame.startAnimation(animationRotateLoading);
       // mIVLightLogo.startAnimation(animationRotateLightLogo);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoadGameActivity.this, MainGameActivity.class);
                startActivity(intent);
            }
        }, 4000);
    }
}
