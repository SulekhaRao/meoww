package com.example.sulekhasurbhi.swasthya.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.sulekhasurbhi.swasthya.Fragment.BlogPostFragment;
import com.example.sulekhasurbhi.swasthya.Fragment.ChatFragment;
import com.example.sulekhasurbhi.swasthya.Fragment.FollowFragment;

public class PagerAdapterForBlogPostActivity extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapterForBlogPostActivity(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                BlogPostFragment tab1 = new BlogPostFragment();
                return tab1;
            case 1:
                ChatFragment chatTab = new ChatFragment();
                return chatTab;
            case 2:
                FollowFragment followTab = new FollowFragment();
                return followTab;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}