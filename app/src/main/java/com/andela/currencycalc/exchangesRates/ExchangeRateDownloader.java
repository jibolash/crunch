package com.andela.currencycalc.exchangesRates;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by JIBOLA on 28-Sep-15.
 */
public class ExchangeRateDownloader extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... url) {
        return getRatesJson(url[0]);
    }

    private String getRatesJson(String address) {
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader reader = null;

        try{
            URL url = new URL(address);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";

            while ((line  = reader.readLine()) != null){
                stringBuffer.append(line);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(reader != null){
                try{
                    reader.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return stringBuffer.toString();
    }
}