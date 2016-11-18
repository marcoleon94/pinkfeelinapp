package com.pinkfeelin.Data.Data.Repository;

import android.media.browse.MediaBrowser;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pinkfeelin.Data.Data.Buy;
import com.pinkfeelin.Data.Data.Item;
import com.pinkfeelin.Util.NetUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Marco on 17/11/2016.
 */

public class ItemsRepository {
    public static void getDetails(final int id_compra, final DetailsCallback callback){
        AsyncTask <Void,Void,List<Item>> task= new AsyncTask<Void, Void, List<Item>>() {
            @Override
            protected List<Item> doInBackground(Void... params) {
                String res;
                HashMap<String, String> body= new HashMap<String, String>();
                body.put("id_compra",Integer.toString(id_compra));
                res= NetUtil.post("http://192.168.0.30:8000/mobile/detalle",body);
                if(res==null){
                    Log.d("result","vacío");
                    return null;
                }else if(res.contains("Error:")){
                    Log.d("result", res);
                    return null;
                }
                Type listTye= new TypeToken<ArrayList<Item>>(){}.getType();
                List<Item> listOfItems = new Gson().fromJson(res,listTye);

                return listOfItems;
            }

            @Override
            protected void onPostExecute(List<Item> items) {
                super.onPostExecute(items);
                if(items==null){
                    callback.error("No hay lista");
                }else {
                    callback.success(true, items);
                }
            }
        };
        task.execute();

    }

    public interface DetailsCallback{
        void error(String msg);
        void success(boolean success, List<Item> list);
    }
}
