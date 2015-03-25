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
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
/*

public class ForumFragment_backup extends Fragment {
    final String TAG = "SSUserFragment";
    List<Topic> topics = null;
    LayoutInflater minflater = null;
    ViewGroup mcontainer = null;
    public String msort = "DESC";
    String data = null;
    String load_posts_result = null;
    boolean waith = true;
    Context mcontext;
    ProgressDialog mpDialog;

    public ForumFragment_backup() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceStatet) {
        this.minflater = inflater;

        this.mcontainer = container;
        Bundle bndl = getArguments();
        this.msort = bndl.getString("sort");

        View rootView = minflater.inflate(R.layout.fragment_main, mcontainer, false);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);



        waith = true;
        new LoadPosts().execute();
        int slp = 0;
        while (waith) {
            slp++;
            try {
                Thread.sleep(100);
                //     Log.e(TAG, "Sleep " + slp);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        switch (load_posts_result) {
            case "fetch_error":
                Toast.makeText(container.getContext(), "Грешка при извличане на данни!",
                        Toast.LENGTH_LONG).show();
                return rootView;
            case "pars_error":
                Toast.makeText(container.getContext(), "Грешка при обработка на данните!",
                        Toast.LENGTH_LONG).show();
                return rootView;
            case "":
                Toast.makeText(container.getContext(), "Непозната грешка!",
                        Toast.LENGTH_LONG).show();
                return rootView;
        }



        AdapterView collection = (AdapterView) rootView.findViewById(R.id.container);
        final ForumAdapter adapter = new ForumAdapter(inflater, topics);


        collection.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Integer pos = (Integer) collection.getItemAtPosition(position);
                LinearLayout ll = (LinearLayout) collection.getChildAt(pos);
                TextView tvFbook = (TextView) ll.findViewById(R.id.listItemFB);
                String fUser = tvFbook.getText().toString();
                if (!fUser.equals(null)) {
                    String fbook = "http://facebook.com/" + fUser;
                    Toast.makeText(collection.getContext(), "F: " + fbook, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(fbook)));
                } else {
                    Toast.makeText(collection.getContext(), "Facebook profile missing!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });


        collection.setAdapter(adapter);
        return rootView;
    }

    ///////////////////////////////////////////
    private class LoadPosts extends AsyncTask<String, Void, String> {
        PowerManager.WakeLock mWakeLock;

        @Override
        protected String doInBackground(String... params) {
            data = getTopics(msort);
            if (data == "ERROR") {
                load_posts_result = "fetch_error";
                // return "fetch_error";
            } else {
                // Log.e(TAG, "START Parse Async");
                topics = parseTopics(data);
                //   Log.e(TAG, "FINISH Parse Async "+topics.size());
                if (topics.size() == 0) {
                    load_posts_result = "pars_error";
                    return "pars_error";
                }
            }
            load_posts_result = "OK";
            //  Log.e(TAG, "RETURN Async ");
            waith = false;
            return "OK";

        }

        @Override
        protected void onPostExecute(String result) {
            mWakeLock.release();
            if (mpDialog != null && mpDialog.isShowing()) {
                mpDialog.dismiss();
            }
            super.onPostExecute("OK");
        }

        @Override
        protected void onPreExecute() {
            PowerManager pm = (PowerManager) mcontainer.getContext().getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, TAG);
            mWakeLock.acquire(10000);


            //mpDialog = new ProgressDialog(MyApplication.getAppContext());
            mpDialog = new ProgressDialog(mcontainer.getContext());
            mpDialog.setMessage("Loading data...");
            mpDialog.setCancelable(false);
            mpDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mpDialog.show();

            super.onPreExecute();

        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
    //////////////////////////////////////

    private String getTopics(String sort) {
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
        } catch (Throwable r) {
            Log.e(TAG, r.getMessage(), r);
            return "ERROR";
        }
    }

    static String convertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    private List<Topic> parseTopics(String data) {
        int percent;
        mpDialog.setMessage("Parsing Data ...");
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
                mpDialog.setProgress(percent);
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
        } catch (Throwable r) {
            Log.e(TAG, r.getMessage(), r);
        }
        return null;
    }

}
*/