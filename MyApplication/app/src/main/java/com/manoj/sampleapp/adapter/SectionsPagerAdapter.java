package com.manoj.sampleapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.manoj.sampleapp.fragment.FirstViewPagerFragment;
import com.manoj.sampleapp.fragment.FourthViewPagerFragment;
import com.manoj.sampleapp.fragment.SecondViewPagerFragment;
import com.manoj.sampleapp.fragment.ThirdViewPagerFragment;

/**
 * Created by manoj on 1/11/17.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return FirstViewPagerFragment.getNewInstance(position + 1);
        if (position == 1)
            return SecondViewPagerFragment.getNewInstance(position + 1);
        if (position == 2)
            return ThirdViewPagerFragment.getNewInstance(position + 1);
        if (position == 3)
            return FourthViewPagerFragment.getNewInstance(position + 1);
        return null;
    }

    @Override
    public int getCount() {
        // Show 4 total pages.
        return 4;
    }


}