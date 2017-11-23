package com.example.hoangtu.ailatrieuphu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class OpenPlayActivity extends Activity {
    private boolean isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sflash_screen);
        initView();
    }

    private void initView() {
        isRunning = true;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(OpenPlayActivity.this, MenuGameActivity.class);
                startActivity(intent);
                isRunning = false;
                finish();
            }
        },3000);
    }
}
