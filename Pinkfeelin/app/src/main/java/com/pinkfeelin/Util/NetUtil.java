package com.pinkfeelin.Util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Marco on 14/11/2016.
 */

public class NetUtil {
    public static String post(String url, String body) {
        HttpURLConnection urlConnection = null;
        String result = null;
        try {
            URL url1 = new URL("http://www.android.com/");
            urlConnection = (HttpURLConnection) url1.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            result = readStream(in);
        } catch (Exception e) {

        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return result;
    }

    private static String readStream(InputStream in) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String read;
        try {
            while ((read = br.readLine()) != null) {
                //System.out.println(read);
                sb.append(read);

            }
            br.close();
        } catch (Exception e) {

        } finally {

        }
        return sb.toString();

    }
}
