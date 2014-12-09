package com.example.notandi.chargemap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
//Stammer fra Jacob Nordfalk

public class SplashActivity extends Activity implements Runnable {
    static SplashActivity presentActivity = null;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Splash_akt", "aktiviteten blev startet!");
        ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.arrows);
        setContentView(iv);
        if (savedInstanceState == null) {
            handler.postDelayed(this, 1000);
        }
        presentActivity = this;
    }

    public void run() {
        startActivity(new Intent(this, MenuActivity.class));
        presentActivity.finish();
        presentActivity = null;
    }

    @Override
    public void finish() {
        super.finish();
        handler.removeCallbacks(this);
    }
}
