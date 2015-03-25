package com.hackafe.nikola.sunshine;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
* Created by Nikola on 11.3.2015 г..

public class ForecastAdapter extends BaseAdapter {

    LayoutInflater inflater;
    int rowCount = 3;


    public ForecastAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout root;
        TextView text;
        TextView textB;

        if (convertView != null) {
            root = (LinearLayout) convertView;
        } else {
            root = (LinearLayout) inflater.inflate(R.layout.list_item_forcast, parent, false);

        }


        text = (TextView) root.findViewById(R.id.list_item_forecast_listview);
        textB = (TextView) root.findViewById(R.id.list_item_forecast_listview2);

        if (getItemViewType(position) == 0) {
            root.setBackgroundColor(R.color.background_material_dark);
        } else {
            root.setBackgroundColor(Color.rgb(255, 255, 255));
        }

        text.setText(Integer.toString(position));
        textB.setText("TEST");
        return root;
    }

    @Override
    public int getItemViewType(int position) {
        if ((position & 1) == 0) {
            return 0;
        } else {
            return 1;
        }
    }


    @Override
    public int getCount() {
        return rowCount;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addOneItem(int addCount) {
        rowCount = rowCount + addCount;
        notifyDataSetChanged();


    }


}
 */