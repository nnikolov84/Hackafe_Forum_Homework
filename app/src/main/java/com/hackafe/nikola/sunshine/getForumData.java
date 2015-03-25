package com.hackafe.nikola.sunshine;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */

public class getForumData {
    final String TAG = "SSgetForumData";
    ProgressDialog lvpdialog;

    public String getTopics(String sort) {
        try {

            String baseUrl = "http://frm.hackafe.org/latest.json";
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet;
            URL url;
            HttpURLConnection conn;

            //  Log.e(TAG, "baseUrl + sort: " + baseUrl + sort);
            httpGet = new HttpGet(baseUrl + sort);
            InputStream stream = httpClient.execute(httpGet).getEntity().getContent();
            String data = convertStreamToString(stream);
            stream.close();


            return data;
        } catch (Exception r) {
            Log.e(TAG,  "Error on fetch: " + r.getMessage(), r);
            return "ERROR";
        }
    }

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public List<Topic> parseTopics(String data, ProgressDialog pdialog) {
        int percent;

        this.lvpdialog = pdialog;
        lvpdialog.setMessage("Parsing Data ...");
        //  Log.e(TAG, "START PARSING");
        try {
            List<Topic> titleList = new ArrayList<Topic>();
            //List<String> Auser = new ArrayList<String>();
            Bitmap avatar = null;
            String title = null;
            JSONObject obj = new JSONObject(data);
            JSONObject topic_list = obj.getJSONObject("topic_list");
            JSONArray topics = topic_list.getJSONArray("topics");
            for (int i = 0; i < topics.length(); i++) {
                // percent = (int) (topics.length() * 100 / i+1);
                percent = 1;
                lvpdialog.setProgress(percent);
                JSONObject jtopic = topics.getJSONObject(i);
                title = jtopic.getString("title");
                //       Log.e(TAG, "title 2 : "+ title);
                JSONArray jposters = jtopic.getJSONArray("posters");
                Integer originalPosterId = 0;
                Integer x = 0;
                while (x < jposters.length()) {
                    if (jposters.getJSONObject(x).getString("description").contains("Original Poster")) {
                        originalPosterId = jposters.getJSONObject(x).getInt("user_id");
                        x = jposters.length();
                    }
                    x++;
                }
                JSONArray users = obj.getJSONArray("users");
                for (int y = 0; y < users.length(); y++) {
                    // String username = users.getJSONObject(y).getString("username");
                    Integer userId = users.getJSONObject(y).getInt("id");
                    //if ((last_poster_username).equals(username)) {
                    if (originalPosterId == userId) {
                        //   Log.v(TAG, "usernameIN: "+username);
                        String avatar_url = "http://frm.hackafe.org/" + obj.getJSONArray("users").getJSONObject(y).getString("avatar_template").replace("{size}", "30");
                        //    Log.v(TAG, "avatar_url " + avatar_url);
                        URL url = new URL(avatar_url);
                        InputStream inputStream = url.openStream();
                        avatar = BitmapFactory.decodeStream(inputStream);
                        inputStream.close();
                        y = users.length();
                    }
                }
                Topic newTopic = new Topic();
                newTopic.avatar = avatar;
                newTopic.title = title;
                titleList.add(i, newTopic);

            }
            return titleList;
        } catch (Exception r) {
            Log.e(TAG, "Error on pars: " + r.getMessage(), r);
        }
        return null;
    }

}