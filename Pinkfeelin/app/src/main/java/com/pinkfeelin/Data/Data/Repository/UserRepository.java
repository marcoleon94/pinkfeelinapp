package com.pinkfeelin.Data.Data.Repository;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.pinkfeelin.Data.Data.User;
import com.pinkfeelin.Util.NetUtil;

import java.util.HashMap;

/**
 * Created by Marco on 15/11/2016.
 */

public class UserRepository {
    public static void login (final String email,final String password,final UserCallback callback){
        AsyncTask<Void,Void,User> task= new AsyncTask<Void, Void, User>() {
            @Override
            protected User doInBackground(Void... params) {
                String res =null;
                HashMap<String,String> body=new HashMap<String, String>();
                body.put("email",email);
                body.put("password",password);
                res= NetUtil.post("http://192.168.0.30:8000/mobile/login",body);
                if(res==null){
                    Log.d("result","vacío");
                    return null;
                }else if(res.contains("Error:")){
                    Log.d("result", res);
                    return null;
                }
                Gson gs= new Gson();
                User u=gs.fromJson(res,User.class);
                return u;
            }

            @Override
            protected void onPostExecute(User user) {
                super.onPostExecute(user);
                if(user!=null) {
                    callback.succes(true, user);
                }else{
                    callback.error("No se generó el usuario");
                }
            }
        };
        task.execute();
    }

    public interface UserCallback{
        void error(String msg);
        void succes(boolean succes, User user);
    }
}
