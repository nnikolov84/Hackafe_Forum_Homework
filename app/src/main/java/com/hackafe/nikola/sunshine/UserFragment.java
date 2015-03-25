package com.hackafe.nikola.sunshine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.Inflater;

/**
 * A placeholder fragment containing a simple view.
 */
/*
public class UserFragment extends Fragment {
    final String TAG = "SSUserFragment";
    List<List<String>> users = null;
    public LayoutInflater minflater = null;

    public UserFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.minflater = inflater;
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String data = getUsers();

        if (data == "ERROR") {
            Toast.makeText(container.getContext(), "Грешка при извличане на данни!",
                    Toast.LENGTH_LONG).show();
            return rootView;
        }
        //Log.d(TAG, data);


        users = parseUsers(data);


        String userData = getUserData(data);
        final TextView tvUsers = (TextView) rootView.findViewById(R.id.users);
        tvUsers.setText(userData);
        final UsersAdapter adapter = new UsersAdapter(inflater, users);
        //   final ListView collection = (ListView) rootView.findViewById(R.id.container);

        final AdapterView collection = (AdapterView) rootView.findViewById(R.id.container);

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


        Button addMoreBtn = (Button) rootView.findViewById(R.id.btn_refresh);

        addMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // adapter.addOneItem(Integer.parseInt(tvUsers.getText().toString()));
                String data = getUsers();
                tvUsers.setText(getUserData(data));
                users = parseUsers(data);
                UsersAdapter adapter = new UsersAdapter(minflater, users);
                collection.setAdapter(adapter);
                Toast.makeText(collection.getContext(), "Refresh...", Toast.LENGTH_SHORT).show();

            }
        });

        collection.setAdapter(adapter);
        return rootView;
    }

    private String getUserData(String data) {
        try {
            JSONObject obj = new JSONObject(data);
            JSONObject users = obj.getJSONObject("data");
            Number usrCount = users.getInt("count");
            Number gstCount = users.getInt("guests");
            return "All Users: " + usrCount + " Guests: " + gstCount;
        } catch (Throwable r) {
            Log.e(TAG, r.getMessage(), r);
        }
        return "N/A";
    }

    private List<List<String>> parseUsers(String data) {
        try {
            List<List<String>> Alist = new ArrayList<List<String>>();
            //List<String> Auser = new ArrayList<String>();

            JSONObject obj = new JSONObject(data);
            JSONObject users = obj.getJSONObject("data");
            JSONArray list = users.getJSONArray("users");
            for (int i = 0; i < list.length(); i++) {
                JSONObject userInfo = list.getJSONObject(i);
                String name1 = userInfo.getString("name1");
                String name2 = userInfo.getString("name2");
                String facebook = new String();
                facebook = userInfo.getString("facebook");
                List<String> Auser = new ArrayList<String>();
                Date dt = new Date();
                Auser.add(0, name1 + " " + name2 + " " + dt);
                Auser.add(1, facebook);
                Alist.add(i, Auser);
                //String forecastStr = dateStr + " - " + description  + " - " + dayTemp;
                Log.d(TAG, "i: " + i + " name1 = " + name1 + " name2 = " + name2 + " facebook = " + facebook);
            }
            return Alist;
        } catch (Throwable r) {
            Log.e(TAG, r.getMessage(), r);
        }
        return null;
    }


    private String getUsers() {
        try {
            URL url = new URL("http://78.130.204.197/?format=json");
            InputStream inputStream = url.openStream();
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder total = new StringBuilder();
            String line;
            //  Log.d(TAG, "Read line");
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            String data = total.toString();

            r.close();
            //  Log.d(TAG, "Return Data");
            // return "{\"data\":{\"count\":5,\"guests\":4,\"users\":[{\"name1\": \"Rangel\",\"name2\": \"Ivanov\",\"twitter\": \"\",\"facebook\": \"rangel.ivanov.92\",\"googlePlus\": \"\",tel\": \"\",\"email\": \"\",\"website\": \"\"},{\"name1\":\"Geno\",\"name2\":\"Roupsky\",\"twitter\":\"groupsky\",\"facebook\":\"groupsky\",\"googlePlus\":\"102993162472282037495\",\"tel\":\"+359897038871\",\"email\":\"geno@masconsult.eu\",\"website\":\"http:\\/\\/github.com\\/groupsky\"}]}}";
            return data;
        } catch (Throwable r) {
            Log.e(TAG, r.getMessage(), r);
            return "ERROR";
        }
    }
}
*/