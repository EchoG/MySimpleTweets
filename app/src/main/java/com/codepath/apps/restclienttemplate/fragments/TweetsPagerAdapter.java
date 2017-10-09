package com.codepath.apps.restclienttemplate.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by chenrangong on 10/7/17.
 */

public class TweetsPagerAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[] {"Home", "Mentions"};
    private Context context;
    HomeTimelineFragment homeTimelineFragment;

    public TweetsPagerAdapter(FragmentManager fm, Context context, HomeTimelineFragment homeTimelineFragment){
        super(fm);
        this.context = context;
        this.homeTimelineFragment = homeTimelineFragment;
    }

    // return the total number of fragment

    @Override
    public int getCount() {
        return 2;
    }

    //return the fragment to use depending on the position

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return homeTimelineFragment;
        } else if(position == 1){
            return new MentionsTimelineFragment();
        } else{
            return null;
        }
    }

    //return title

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
