package com.example.asus.slcm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    EditText userName,password;
    Button loginButton;
    private RequestQueue mQueue;
    private String mUsername;
    private String mPassword;
    public User ourBoi = new User();
    private Context mContext;
    LinearLayout linlaHeaderProgress;
    CardView cv;
    TextView appTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName=findViewById(R.id.userName);    //get the username and password
        password=findViewById(R.id.password);
        loginButton=findViewById(R.id.loginButton);
        mQueue = Volley.newRequestQueue(this);
        mContext = getApplicationContext();
        linlaHeaderProgress = (LinearLayout) findViewById(R.id.linlaHeaderProgress);
        cv = (CardView) findViewById(R.id.card_view_login);
        appTitle = (TextView) findViewById(R.id.app_title);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String un = sharedPref.getString("tentative_username", "bobo");
        String ps = sharedPref.getString("tentative_password", "vogo");

        if (!un.equals("bobo") || !ps.equals("vogo")) {
            mUsername = un;
            mPassword = ps;
            ourBoi.setmRegistrationNumber(mUsername);
            ourBoi.setmRawPassword(mPassword);
            if (isNetworkAvailable()) {
                jsonParse();
            } else {
                Log.i("offline", "Not connect to internet");
                Toast.makeText(LoginActivity.this, "Check your internet connection :)", Toast.LENGTH_LONG).show();
            }
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName.clearFocus();
                password.clearFocus();
                loginButton.requestFocus();
                 String user=userName.getText().toString().trim();
                 String pass=password.getText().toString().trim();
                 if (!(TextUtils.isEmpty(user)) && !(TextUtils.isEmpty(pass))) {
                     if (verifyUserName(user)) {
                         Log.i("verify", "SUCCC");
                         mUsername = user;
                         mPassword = pass;
                         ourBoi.setmRegistrationNumber(mUsername);
                         ourBoi.setmRawPassword(mPassword);
                         if (isNetworkAvailable()) {
                             jsonParse();
                         } else {
                             Log.i("offline", "Not connect to internet");
                             Toast.makeText(LoginActivity.this, "Check your internet connection :)", Toast.LENGTH_LONG).show();
                         }
                     } else {
                         Log.i("verify", "UN ____  SUCCC");
                         Toast.makeText(LoginActivity.this, "Invalid Registration Number", Toast.LENGTH_LONG).show();
                     }
                 } else {
                     Toast.makeText(LoginActivity.this, "Please enter username or password", Toast.LENGTH_LONG).show();
                 }
            }
        });
    }

    private void jsonParse() {
        String url = "http://13.234.66.100/go?username=" + mUsername + "&password=" + mPassword;

        progressBarStart();
        hideKeyboard(LoginActivity.this);

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("res", response.toString());
                            if (response.getString("code").equals("666")) { //Successful login
                                // parse subject data
                                JSONArray subjectArray = response.getJSONArray("data");
                                if (subjectArray.length() > 0) {
                                    for (int i = 0; i < subjectArray.length(); i++) {
                                        JSONObject subjectObject = subjectArray.getJSONObject(i);
                                        String a = subjectObject.getString("Academic Year");
                                        String b = subjectObject.getString("Attendance(%)");
                                        String c = subjectObject.getString("Days Absent");
                                        String d = subjectObject.getString("Days Present");
                                        String e = subjectObject.getString("Total Class");
                                        String f = subjectObject.getString("Semester");
                                        String g = subjectObject.getString("Subject ");
                                        String h = subjectObject.getString("Subject Code");
                                        Subject sub = new Subject(a, b, c, d, e, f, g, h);
                                        ourBoi.addSubject(sub);
                                    }
                                    if (ourBoi.getAreSubjectsLoaded()) {
                                        SharedPreferences sharedPref = LoginActivity.this.getPreferences(Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPref.edit();
                                        editor.putString("tentative_username", userName.getText().toString().trim());
                                        editor.putString("tentative_password", password.getText().toString().trim());
                                        editor.apply();
                                        Intent i = new Intent(mContext, StageActivity.class);
                                        i.putExtra("current_user", ourBoi);
                                        startActivity(i);
                                    }
                                } else {
                                    progressBarEnd();
                                    Toast.makeText(LoginActivity.this,"Incorrect Credentials :(\nCODE: ", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                //erroneous login
                                progressBarEnd();
                                Toast.makeText(LoginActivity.this,"ERROR :(\nCODE: " + response.getString("code"),Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            Log.e("JSON ERROR", e.toString());
                            Toast.makeText(LoginActivity.this,"Timed Out, please try again ^.^",Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("SOMEERROR", "Well this is a bust." + error.toString());
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

    private void progressBarStart() {
        cv.setVisibility(View.GONE);
        appTitle.setVisibility(View.GONE);
        linlaHeaderProgress.setVisibility(View.VISIBLE);
    }

    private void progressBarEnd() {
        cv.setVisibility(View.VISIBLE);
        appTitle.setVisibility(View.VISIBLE);
        linlaHeaderProgress.setVisibility(View.GONE);
    }

    public boolean verifyUserName(String user) {
        return user.matches("[12][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]");
    }

    public void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    protected void onStop() {
        super.onStop();
        progressBarEnd();
    }
}
