package com.hackafe.nikola.sunshine;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.PowerManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import static com.hackafe.nikola.sunshine.R.*;


public class MainActivity extends ActionBarActivity {
    private Menu menu;
    final String TAG = "SSMainAct";
    String sort = "?ascending=true&order=posts";
    List<Topic> topics = null;
    String data = null;
    String load_posts_result = null;
    boolean waith = true;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(layout.activity_main);
        setContentView(layout.fragment_main);


        View splash = this.findViewById(id.splash);
        context = splash.getContext();
        splash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean load() {
        //  View rootView = getMenuInflater().inflate(R.layout.fragment_main, menu);
        new LoadPosts().execute();
        return false;
    }

    public boolean afterLoad() {
        LayoutInflater inflater = getLayoutInflater();
        switch (load_posts_result) {
            case "fetch_error":
                Toast.makeText(this, "Грешка при извличане на данни!",
                        Toast.LENGTH_LONG).show();
                Log.e(TAG, "load_posts_result: " + load_posts_result);
                return false;
            case "pars_error":
                Toast.makeText(this, "Грешка при обработка на данните!",
                        Toast.LENGTH_LONG).show();
                return false;
            case "":
                Toast.makeText(this, "Непозната грешка!",
                        Toast.LENGTH_LONG).show();
                return false;
        }


        AdapterView collection = (AdapterView) this.findViewById(R.id.container);
        ForumAdapter adapter = new ForumAdapter(inflater, topics);
        try {
            collection.setAdapter(adapter);
        } catch (Error e) {
            Log.e(TAG, "Error on load: " + e.getMessage());
        }
        View splash = this.findViewById(id.splash);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0);
        lp.weight = (float) 0;
        splash.setLayoutParams(lp);
        lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0);
        lp.weight = (float) 1;
        collection.setLayoutParams(lp);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //  Bundle bndl = new Bundle();
        //  ForumFragment ff = new ForumFragment();
        String sortString;
        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                return true;


            case R.id.btn_sortAsc:
                if ((sort).equals("?ascending=true&order=posts")) {
                    menu.getItem(4).setIcon(getResources().getDrawable(R.mipmap.ic_sort_za));
                    Toast.makeText(this, "Sort Selected...ASC", Toast.LENGTH_SHORT).show();
                    sort = "?descending=true&order=posts";
                    load();
                    break;

                } else {
                    menu.getItem(4).setIcon(getResources().getDrawable(R.mipmap.ic_sort_az));
                    Toast.makeText(this, "Sort Selected...DESC", Toast.LENGTH_SHORT).show();
                    sort = "?ascending=true&order=posts";
                    load();
                    break;
                }
            case R.id.sortLatest:
                sort = "?ascending=true&order=activity";
                Toast.makeText(this, "?ascending=true&order=activity " + sort, Toast.LENGTH_SHORT).show();
                load();
                break;
            case R.id.sortReplies:
                sort = "?ascending=true&order=posts";
                Toast.makeText(this, "?ascending=true&order=posts " + sort, Toast.LENGTH_SHORT).show();
                load();
                break;
            case R.id.sortViews:
                sort = "?ascending=true&order=views";
                Toast.makeText(this, "?ascending=true&order=views " + sort, Toast.LENGTH_SHORT).show();
                load();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    ///////////////////////////////////////////
    public class LoadPosts extends AsyncTask<String, Integer, String> {
        PowerManager.WakeLock mWakeLock;
        ProgressDialog pDialog = new ProgressDialog(context);
        getForumData getForumData = new getForumData();

        @Override
        protected String doInBackground(String... params) {


            data = getForumData.getTopics(sort);
            if (data == "ERROR") {
                load_posts_result = "fetch_error";

            } else {
                // Log.e(TAG, "START Parse Async");
                topics = getForumData.parseTopics(data, this);
                publishProgress();
                //   Log.e(TAG, "FINISH Parse Async "+topics.size());
                if (topics.size() == 0) {
                    load_posts_result = "pars_error";
                    // return "pars_error";
                }
            }
            load_posts_result = "OK";
            //  Log.e(TAG, "RETURN Async ");
            return "OK";
        }

        public void setProgress(int p) {
            publishProgress(p, 100);
        }

        @Override
        protected void onPostExecute(String result) {
            mWakeLock.release();
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }
            waith = false;
            super.onPostExecute("OK");
            afterLoad();
        }

        @Override
        protected void onPreExecute() {
            PowerManager pm = (PowerManager) MainActivity.this.getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, TAG);
            mWakeLock.acquire(10000);

            Log.e(TAG, "BEFORE DIALOG SHOW");
            pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pDialog.setMessage("Loading data...");
            pDialog.setIndeterminate(false);
            pDialog.setMax(100);
            pDialog.setCancelable(false);
            pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pDialog.show();

            super.onPreExecute();

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            if (values.length > 0)
                pDialog.setProgress(values[0]);
        }
    }
    //////////////////////////////////////

}

