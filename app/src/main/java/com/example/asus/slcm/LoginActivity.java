package com.example.asus.slcm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class LoginActivity extends AppCompatActivity {

    EditText userName,password;
    Button loginButton;
    private RequestQueue mQueue;
    private String mUsername;
    private String mPassword;
    public User ourBoi = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName=findViewById(R.id.userName);    //get the username and password
        password=findViewById(R.id.password);
        loginButton=findViewById(R.id.loginButton);
        mQueue = Volley.newRequestQueue(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 String user=userName.getText().toString().trim();
                 String pass=password.getText().toString().trim();


                if ( !(TextUtils.isEmpty(user)) && !(TextUtils.isEmpty(pass)) ){

                    /*write the code for login that is your http request*/

                    mUsername = user;
                    mPassword = pass;
                    ourBoi.setmRegistrationNumber(mUsername);
                    ourBoi.setmRawPassword(mPassword);
                    Toast.makeText(LoginActivity.this,"YAY",Toast.LENGTH_LONG).show();
                    jsonParse();
                    Intent i = new Intent(view.getContext(), StageActivity.class);
                    i.putExtra("current_user", ourBoi);
                    startActivity(i);

                } else {
                    Toast.makeText(LoginActivity.this, "Please enter username or password", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void jsonParse() {
        String url = "http://13.234.66.100/go?username=" + mUsername + "&password=" + mPassword;

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getString("code").equals("666")) { //Successful login
                                // parse subject data
                                JSONArray subjectArray = response.getJSONArray("data");
                                for (int i = 0; i < subjectArray.length(); i++) {
                                    JSONObject subjectObject = subjectArray.getJSONObject(i);
                                    String a = subjectObject.getString("Attendance(%)");
                                    String b = subjectObject.getString("Days Absent");
                                    String c = subjectObject.getString("Academic Year");
                                    String d = subjectObject.getString("Days Present");
                                    String e = subjectObject.getString("Total Class");
                                    String f = subjectObject.getString("Semester");
                                    String g = subjectObject.getString("Subject ");
                                    String h = subjectObject.getString("Subject Code");
                                    Subject sub = new Subject(a, b, c, d, e, f, g, h);
                                    ourBoi.addSubject(sub);
                                }
                                ourBoi.printUserInfoToConsole();
                                Toast.makeText(LoginActivity.this,"SUCCESS!\nCODE: 666",Toast.LENGTH_LONG).show();
                            } else {
                                //erroneous login
                                Toast.makeText(LoginActivity.this,"ERROR :(\nCODE: " + response.getString("code"),Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            Log.e("JSON ERROR", e.toString());
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

}
