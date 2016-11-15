package com.pinkfeelin.Login;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import com.pinkfeelin.Data.Data.Repository.UserRepository;
import com.pinkfeelin.Data.Data.User;
import com.pinkfeelin.R;
import com.pinkfeelin.Util.NetUtil;

import java.util.HashMap;

public class Login extends AppCompatActivity {
    EditText email,password;
    Button loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Gson gson= new Gson();
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
                UserRepository.login(em, pass, new UserRepository.UserCallback() {
                    @Override
                    public void error(String msg) {
                        Toast.makeText(Login.this, msg, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void succes(boolean succes, User user) {
                        Toast.makeText(Login.this, "Hola "+user.getName(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

    }


}
