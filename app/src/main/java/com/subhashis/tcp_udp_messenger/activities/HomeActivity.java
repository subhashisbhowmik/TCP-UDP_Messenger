package com.subhashis.tcp_udp_messenger.activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.subhashis.tcp_udp_messenger.R;
import com.subhashis.tcp_udp_messenger.fragments.HolderFragment;
import com.subhashis.tcp_udp_messenger.slider.SlidingTabLayout;

public class HomeActivity extends AppCompatActivity implements HolderFragment.OnFragmentInteractionListener {
    final Context activityContext =this;
    ViewPager pager;
    SlidingTabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        pager = (ViewPager) findViewById(R.id.viewpager);
        pager.setAdapter(new CustomPagerAdapter(getSupportFragmentManager()));
        tabs.setDistributeEvenly(true);
        tabs.setViewPager(pager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    class CustomPagerAdapter extends FragmentPagerAdapter {
        String[] tabNames=new String [3];

        public CustomPagerAdapter(FragmentManager fm) {
            super(fm);
            tabNames[0] = "Connections"; //getResources().getString(R.string.tab1);
            tabNames[1] = "Create"; //getResources().getString(R.string.tab2);
            tabNames[2] = "Listen"; //getResources().getString(R.string.tab3);

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabNames[position];
        }

        @Override
        public Fragment getItem(int position) {
            return HolderFragment.newInstance(activityContext,String.valueOf(position), "");
        }

        @Override
        public int getCount() {
            return 3;
        }
    }


}
