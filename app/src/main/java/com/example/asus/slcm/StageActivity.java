package com.example.asus.slcm;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class StageActivity extends AppCompatActivity {

    CardView cardview;
    LinearLayout.LayoutParams layoutparams;
    TextView textview;
    RelativeLayout relativeLayout;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage);

        context = getApplicationContext();
        relativeLayout = (RelativeLayout)findViewById(R.id.relativelayout1);

        Intent i = getIntent();
        User ourBoi = (User) i.getSerializableExtra("current_user");
        Log.d("BOI", ourBoi.getmRawPassword());
        if (ourBoi.getAreSubjectsLoaded()) {

        }
    }
}
