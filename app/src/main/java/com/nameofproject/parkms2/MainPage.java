package com.nameofproject.parkms2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
    }


    public void sendToOtherActivity1(View view) {
        Intent mainIntent = new Intent(this,MainActivity.class);
        startActivity(mainIntent);
    }
}