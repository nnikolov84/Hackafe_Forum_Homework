package com.hackafe.nikola.sunshine;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.

public class ForecastFragment extends Fragment
{

    public ForecastFragment() {
    }

   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        String data = getForecast();

        List<String> forecast = parseForecast(data);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        final ForecastAdapter adapter = new ForecastAdapter(inflater);
        final ListView collection = (ListView) rootView.findViewById(R.id.container);
        Button addMoreBtn = (Button) rootView.findViewById(R.id.btn_add_more_items);
        final TextView addCountTV = (TextView) rootView.findViewById(R.id.addCount);
        addMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addOneItem(Integer.parseInt(addCountTV.getText().toString()));
                collection.setSelection(adapter.getCount() - 1);
            }
        });

        collection.setAdapter(adapter);
        return rootView;
    }

    private List<String> parseForecast(String data) {
        try {
            JSONObject obj = new JSONObject(data);
            JSONArray list = obj.getJSONArray("list");

            for (int i = 0; i < list.length(); i++) {
                JSONObject forecast = list.getJSONObject(i);
                JSONObject temp = forecast.getJSONObject("temp");
                double dayTemp = temp.getDouble("day");
                JSONArray weather = forecast.getJSONArray("weather");
                JSONObject weather1 = weather.getJSONObject(0);
                String description = weather1.getString("description");
                long dt = forecast.getLong("dt");
                String dateStr = SimpleDateFormat.getDateInstance().format(new Date(dt*1000));
                String forecastStr = String.format("%s - %s %.1f C", dateStr, description, dayTemp);
                //String forecastStr = dateStr + " - " + description  + " - " + dayTemp;
                Log.d("Sunshine", "forecast = "+forecastStr);
            }

        } catch (Throwable r) {
            Log.e("Sunshine", r.getMessage(), r);
        }
        return null;
    }


    private String getForecast() {
        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=Plovdiv&mode=json&units=metric&cnt=7");
            InputStream inputStream = url.openStream();
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            String data = total.toString();

            r.close();
            return data;
        } catch (Throwable r) {
            Log.e("Sunshine", r.getMessage(), r);
            return "ERROR";
        }
    }

}
 */