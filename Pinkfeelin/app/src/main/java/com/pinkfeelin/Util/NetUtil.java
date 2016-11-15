package com.pinkfeelin.Util;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static android.R.attr.x;

/**
 * Created by Marco on 14/11/2016.
 */

public class NetUtil {
    public static String post(String url, HashMap<String,String> body) {
        HttpURLConnection urlConnection = null;
        String result = null;
        try {
            URL url1 = new URL(url);
            urlConnection = (HttpURLConnection) url1.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            OutputStream out=new BufferedOutputStream(urlConnection.getOutputStream());
            writeStream(out, getPostDataString(body));

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            result = readStream(in);
        } catch (Exception e) {
            Log.e("error",e.getMessage(),e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return result;
    }

    private static void writeStream(OutputStream out, String body) {
        try {
            BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
            writer.write(body);
            writer.flush();
            writer.close();
            out.close();
        } catch (IOException e) {
            Log.e("error",e.getMessage(),e);
        }
    }

    private static String readStream(InputStream in) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String read;
        try {
            while ((read = br.readLine()) != null) {
                System.out.println(read);
                sb.append(read);

            }
            br.close();
        } catch (Exception e) {
            Log.e("error",e.getMessage(),e);
        } finally {

        }
        return sb.toString();

    }
    private static String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}
