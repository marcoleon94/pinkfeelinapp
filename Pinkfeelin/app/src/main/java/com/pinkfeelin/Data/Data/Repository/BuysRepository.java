package com.pinkfeelin.Data.Data.Repository;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.pinkfeelin.Data.Data.Buy;
import com.pinkfeelin.Data.Data.User;
import com.pinkfeelin.Util.NetUtil;

import java.util.HashMap;

/**
 * Created by Marco on 15/11/2016.
 */

public class BuysRepository {
    public static void getBuys(final User user,BuyCallback callback){
        AsyncTask<Void,Void,Void> task= new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                String res;
                HashMap<String, String> body= new HashMap<String, String>();
                body.put("id",Integer.toString(user.getId()));
                res= NetUtil.post("http://192.168.0.30:8000/mobile/historial",body);
                if(res==null){
                    Log.d("result","vacío");
                    return null;
                }else if(res.contains("Error:")){
                    Log.d("result", res);
                    return null;
                }
                Buy[] buys=new Buy[res.length()];

                    Gson gs= new Gson();



                User u=gs.fromJson(res,User.class);
                return u;

            }
        }

    }
    public interface BuyCallback{
        void error(String msg);
        void success(boolean success,Buy[] buy);
    }
}
