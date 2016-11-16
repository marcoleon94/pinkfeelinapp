package com.pinkfeelin.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pinkfeelin.Data.Data.Repository.UserRepository;
import com.pinkfeelin.Data.Data.User;
import com.pinkfeelin.Historial.HistoryActivity;
import com.pinkfeelin.R;
import com.pinkfeelin.Util.AppUtil;

public class Login extends AppCompatActivity {
    EditText email,password;
    Button loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
                    public void success(boolean succes, User user) {
                        Toast.makeText(Login.this, "Hola "+user.getName(), Toast.LENGTH_SHORT).show();
                        openHistorial(user);

                    }
                });

            }
        });
        if(AppUtil.isDebug()){
            email.setText("paramore_malj@outlook.com");
            password.setText("paramore");
        }

    }

    private void openHistorial(User user) {
        Intent intent= new Intent(this, HistoryActivity.class);
        Bundle b= new Bundle();
        b.putInt("id",user.getId());
        intent.putExtra("login",b);
        startActivity(intent);
    }


}
