package com.hackafe.nikola.sunshine;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* Created by Nikola on 11.3.2015 г..
*/
/*
public class UsersAdapter extends BaseAdapter {
    final String TAG = "SSUserAdapter";
    LayoutInflater inflater;
    List<List<String>> users;
    int rowCount = 1;


    public UsersAdapter(LayoutInflater inflater, List<List<String>> users) {
        this.inflater = inflater;
        this.users = users;
    }
    @Override

    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout root;
        TextView listItemName;
        TextView listItemFB;

        if (convertView != null) {
            root = (LinearLayout) convertView;
        } else {
            root = (LinearLayout) inflater.inflate(R.layout.list_item_users, parent, false);

        }


        listItemName = (TextView) root.findViewById(R.id.listItemName);
        listItemFB = (TextView) root.findViewById(R.id.listItemFB);

        if (getItemViewType(position) == 0) {
            root.setBackgroundColor(R.color.background_material_dark);
        } else {
            root.setBackgroundColor(Color.rgb(255, 255, 255));
        }
        List<String> userDet = (List<String>) users.get(position);
        String names = userDet.get(0);
        Log.d(TAG, "names: "+names + " position: "+position);
        String fbook = userDet.get(1);
        listItemName.setText(names);
        listItemFB.setText(fbook);
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
        //return rowCount;
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}
*/