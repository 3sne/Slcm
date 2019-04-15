package com.example.asus.slcm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText userName,password;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName=findViewById(R.id.userName);    //get the username and password
        password=findViewById(R.id.password);
        loginButton=findViewById(R.id.loginButton);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 String user=userName.getText().toString().trim();
                 String pass=password.getText().toString().trim();

                if ( !(TextUtils.isEmpty(user)) && !(TextUtils.isEmpty(pass)) ){

                    /*write the code for login that is your http request*/

                    Toast.makeText(LoginActivity.this,"Henlo ",Toast.LENGTH_LONG).show();

                }
                else
                    Toast.makeText(LoginActivity.this,"Please enter username or password",Toast.LENGTH_LONG).show();

            }
        });





    }
}
