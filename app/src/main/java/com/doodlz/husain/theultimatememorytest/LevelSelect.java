package com.doodlz.husain.theultimatememorytest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LevelSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_select);
    }

    public void easyBtnClicked(View view) {
        Intent intent =new Intent(LevelSelect.this,MainActivity.class);
        intent.putExtra("level",5);
        startActivity(intent);
    }

    public void mediumBtnClicked(View view) {
        Intent intent =new Intent(LevelSelect.this,MainActivity.class);
        intent.putExtra("level",7);
        startActivity(intent);
    }

    public void hardBtnClicked(View view) {
        Intent intent =new Intent(LevelSelect.this,MainActivity.class);
        intent.putExtra("level",9);
        startActivity(intent);
    }

    public void insaneBtnClicked(View view) {
        Intent intent =new Intent(LevelSelect.this,MainActivity.class);
        intent.putExtra("level",11);
        startActivity(intent);
    }
}
