package com.example.fakenews;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class NewsPage extends AsyncTask<String, Void, String> {
    String result;
    @Override
    protected String doInBackground(String... urls) {
        result= "";
        URL link;
        HttpURLConnection myConnection= null;

        try{
            link= new URL (urls[0]);
            myConnection= (HttpURLConnection)link.openConnection();
            InputStream in= myConnection.getInputStream();
            InputStreamReader myStreamReader= new InputStreamReader(in);
            int data= myStreamReader.read();
            while (data!= -1){
                char current= (char)data;
                result+= current;
                data= myStreamReader.read();
            }
            return result;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try{
            JSONObject myObject= new JSONObject(result);
            JSONArray articles = myObject.getJSONArray("articles");
            JSONObject zeroInd = articles.getJSONObject(0);
            JSONObject source = zeroInd.getJSONObject("source");


            String title1 = source.getString("id");
            NewsActivity.content1.setText("Title: " + title1);
            NewsActivity.publishedon1.setText("Help");

        } catch (JSONException e){
            e.printStackTrace();
        }
    }
}
