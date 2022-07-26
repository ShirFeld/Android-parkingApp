package com.nameofproject.parkms2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {
    private Animation logoAnim;
    private ImageView logoImage;
    private ImageView logoImage2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        logoImage = findViewById(R.id.imageView);
        logoAnim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.splash_anim);
        logoImage.startAnimation(logoAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainPage.class);
                startActivity(intent);
                finish();
            }
        },2000);



    }
}