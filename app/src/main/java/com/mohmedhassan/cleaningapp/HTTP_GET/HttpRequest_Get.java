package com.mohmedhassan.cleaningapp.HTTP_GET;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest_Get extends AsyncTask<HttpCall_Get, String, StringBuilder> {
    private static final String TAG = HttpRequest_Get.class.getSimpleName();

    private static final String UTF_8 = "UTF-8";

    public boolean connectionTimeOut = false;

    @Override
    protected StringBuilder doInBackground(HttpCall_Get... params) {
        HttpURLConnection urlConnection = null;
        HttpCall_Get httpCall = params[0];
        StringBuilder response = new StringBuilder();

        try{
            String dataParams = getDataString(httpCall.getParams(), httpCall.getMethodtype());
            URL url = new URL(httpCall.getMethodtype() == HttpCall_Get.GET ? httpCall.getUrl() + dataParams : httpCall.getUrl());
            Log.d(TAG,"APIUrl :: "+String.valueOf(url));
            Log.d(TAG,"params :: "+String.valueOf(dataParams));
            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod(httpCall.getMethodtype() == HttpCall_Get.GET ? "GET":"POST");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            if(httpCall.getParams() != null && httpCall.getMethodtype() == HttpCall_Get.POST){

                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, UTF_8));
                writer.append(dataParams);
                writer.flush();
                writer.close();
                os.close();
            }

            int responseCode = urlConnection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                String line ;
                BufferedReader br = new BufferedReader( new InputStreamReader(urlConnection.getInputStream()));
                while ((line = br.readLine()) != null || line == ""){
                    response.append(line);
                }


            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            connectionTimeOut = true;
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return response;
    }

    @Override
    protected void onPostExecute(StringBuilder s) {
        super.onPostExecute(s);
        onResponse(s);
    }

    public void onResponse(StringBuilder response){

    }

    private String getDataString(HashMap<String,String> params, int methodType) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean isFirst = true;
        for(Map.Entry<String,String> entry : params.entrySet()){
            if (isFirst){
                isFirst = false;
                if(methodType == HttpCall_Get.GET){
                    result.append("?");
                }
            }else{
                result.append("&");
            }
            result.append(URLEncoder.encode(entry.getKey(), UTF_8));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), UTF_8));
        }
        return result.toString();
    }
}
