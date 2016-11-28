package com.pinkfeelin.Data.Data.Repository;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.pinkfeelin.Data.Data.Buy;
import com.pinkfeelin.Data.Data.User;
import com.pinkfeelin.Util.NetUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Marco on 15/11/2016.
 */

public class BuysRepository {
    public static void getBuys(final int id, final BuyCallback callback){
        AsyncTask<Void,Void,List<Buy>> task= new AsyncTask<Void, Void, List<Buy>>() {
            @Override
            protected List<Buy> doInBackground(Void... params) {
                String res;
                HashMap<String, String> body= new HashMap<String, String>();
                body.put("id",Integer.toString(id));
                res= NetUtil.post("http://192.168.100.22/mobile/historial",body);
                if(res==null){
                    Log.d("result","vacío");
                    return null;
                }else if(res.contains("Error:")){
                    Log.d("result", res);
                    return null;
                }
                Type listTye= new TypeToken<ArrayList<Buy>>(){}.getType();
                List<Buy> listOfBuys = new Gson().fromJson(res,listTye);

                return listOfBuys;

            }

            @Override
            protected void onPostExecute(List<Buy> list) {
                super.onPostExecute(list);
                if(list==null){
                    callback.error("No hay lista");
                }else {
                    callback.success(true, list);
                }
            }
        };
        task.execute();

    }
    public interface BuyCallback{
        void error(String msg);
        void success(boolean success,List<Buy> list);
    }
}
