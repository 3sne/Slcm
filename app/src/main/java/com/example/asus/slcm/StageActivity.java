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

        Intent i = getIntent();
        final User ourBoi = (User) i.getSerializableExtra("current_user");
        Log.d("BOI", "THIS = " + ourBoi.subjectList.size());
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
            public void onClick(View v) {
                Log.d("BOI", "THAT = " + ourBoi.subjectList.size());
                SharedPreferences sharedPref = StageActivity.this.getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor spEditor = sharedPref.edit();
                spEditor.remove("tentative_username");
                spEditor.remove("tentative_password");
//                int size = ourBoi.subjectList.size();
//                ourBoi.subjectList.clear();
//                srAdapter.notifyItemRangeRemoved(0, size);
//                srAdapter.notifyDataSetChanged();
                StageActivity.this.finish();
            }
        });

    }
}
