package com.hackafe.nikola.sunshine;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
* Created by Nikola on 11.3.2015 г..
*/
public class ForumAdapter extends BaseAdapter {
    final String TAG = "SSUserAdapter";
    LayoutInflater inflater;
    List<Topic> lvtopics;
    int rowCount = 1;

    public ForumAdapter(LayoutInflater inflater, List<Topic> topics) {
        this.inflater = inflater;
        this.lvtopics = topics;
    }
    @Override

    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout root;
        TextView listItemTopic;
        ImageView avatar;
        TextView listItemFB;

        if (convertView != null) {
            root = (LinearLayout) convertView;
        } else {
            root = (LinearLayout) inflater.inflate(R.layout.list_item_topic, parent, false);

        }


        listItemTopic = (TextView) root.findViewById(R.id.topicTitle);
        avatar = (ImageView) root.findViewById(R.id.avatar);
   //     listItemFB = (TextView) root.findViewById(R.id.listItemFB);

        if (getItemViewType(position) == 0) {
            root.setBackgroundColor(Color.rgb(202, 250, 241));
        } else {
            root.setBackgroundColor(Color.rgb(255, 255, 255));
        }
        Topic topic = (Topic) lvtopics.get(position);
        String title = topic.title;
       avatar.setImageBitmap(topic.avatar);
      //  Log.d(TAG, "title: "+title);
        listItemTopic.setText(title);
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
        return lvtopics.size();

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
