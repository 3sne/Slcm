package com.example.asus.slcm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class StageActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SubjectRecyclerAdapter srAdapter;
    private List<Subject> subjectList;
    private TextView registrationNumberTextView;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage);

        // Receive User from LoginActivity
        Intent i = getIntent();
        final User ourBoi = (User) i.getSerializableExtra("current_user");

        // Load into Recycler View
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        srAdapter = new SubjectRecyclerAdapter(this, (List<Subject>) ourBoi.subjectList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(srAdapter);

        registrationNumberTextView = (TextView) findViewById(R.id.regNo);
        registrationNumberTextView.setText("Hi, " + ourBoi.getmRegistrationNumber());
        logoutButton = (Button) findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //remove saved login data, and go to LoginActivity screen
                SharedPreferences sharedPref = StageActivity.this.getSharedPreferences(getString(R.string.sharedPreferenceLabel) , Context.MODE_PRIVATE);
                SharedPreferences.Editor spEditor = sharedPref.edit();
                spEditor.remove(getString(R.string.sharedPreferenceUsername));
                spEditor.remove(getString(R.string.sharedPreferencePassword));
                spEditor.apply();
                StageActivity.this.finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        srAdapter.notifyDataSetChanged();
    }
}
