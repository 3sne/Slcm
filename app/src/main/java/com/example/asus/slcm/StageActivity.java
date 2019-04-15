package com.example.asus.slcm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class StageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage);

        Intent i = getIntent();
        User ourBoi = (User) i.getSerializableExtra("current_user");
        Log.d("BOI", ourBoi.getmRawPassword());

        
    }
}
