package com.hackafe.nikola.sunshine;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import static com.hackafe.nikola.sunshine.R.id;
import static com.hackafe.nikola.sunshine.R.layout;
import static com.hackafe.nikola.sunshine.R.mipmap;

/*
public class MainActivity_backup extends ActionBarActivity {
    private Menu menu;
    String sort = "?ascending=true&order=posts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);


        View splash = this.findViewById(id.splash);

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

    public void load(){

            Bundle bndl = new Bundle();
            bndl.putString("sort", sort);
            ForumFragment ff = new ForumFragment();
            ff.setArguments(bndl);
            getSupportFragmentManager().beginTransaction().add(id.container, ff).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Bundle bndl = new Bundle();
        ForumFragment ff = new ForumFragment();
        String sortString;
        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                return true;


            case R.id.btn_sortAsc:
                if ((sort).equals("?ascending=true&order=posts")) {
                    menu.getItem(4).setIcon(getResources().getDrawable(mipmap.ic_sort_za));
                    Toast.makeText(this, "Sort Selected...ASC", Toast.LENGTH_SHORT).show();
                    sort = "?descending=true&order=posts";
                    bndl.putString("sort", sort);
                    ff.setArguments(bndl);
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, ff).commit();
                    break;

                } else {
                    menu.getItem(4).setIcon(getResources().getDrawable(mipmap.ic_sort_az));
                    Toast.makeText(this, "Sort Selected...DESC", Toast.LENGTH_SHORT).show();
                    sort = "?ascending=true&order=posts";
                    bndl.putString("sort", sort);
                    ff.setArguments(bndl);
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, ff).commit();
                    break;
                }
            case R.id.sortLatest:
                sort = "?ascending=true&order=activity";
                Toast.makeText(this, "?ascending=true&order=activity " + sort, Toast.LENGTH_SHORT).show();
                bndl.putString("sort", sort);
                ff.setArguments(bndl);
                getSupportFragmentManager().beginTransaction().replace(R.id.container, ff).commit();
                break;
            case R.id.sortReplies:
                sort = "?ascending=true&order=posts";
                Toast.makeText(this, "?ascending=true&order=posts " + sort, Toast.LENGTH_SHORT).show();
                bndl.putString("sort", sort);
                ff.setArguments(bndl);
                getSupportFragmentManager().beginTransaction().replace(R.id.container, ff).commit();
                break;
            case R.id.sortViews:
                sort = "?ascending=true&order=views";
                Toast.makeText(this, "?ascending=true&order=views " + sort, Toast.LENGTH_SHORT).show();
                bndl.putString("sort", sort);
                ff.setArguments(bndl);
                getSupportFragmentManager().beginTransaction().replace(R.id.container, ff).commit();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}

*/