package com.pinkfeelin.Login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pinkfeelin.R;

public class Login extends AppCompatActivity {
    EditText email,password;
    Button loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Gson gson= new Gson();
        email=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        loginbtn=(Button) findViewById(R.id.loginbtn);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em, pass;
                em = email.getText().toString();
                pass = password.getText().toString();
                Toast.makeText(Login.this,"Hola "+em+" "+pass,Toast.LENGTH_SHORT).show();

            }
        });

    }


}
