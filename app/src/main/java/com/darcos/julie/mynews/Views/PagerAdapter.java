package com.darcos.julie.mynews.Views;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.darcos.julie.mynews.Fragments.BlankFragment;
import com.darcos.julie.mynews.Fragments.TopStoriesFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    private final String tabTitles[] =new String[]{"Top Stories","Most popular","Sport"};
    private final int PAGE_COUNT = 3;

     public PagerAdapter(FragmentManager fm){
         super(fm);
     }

     @Override
    public int getCount(){
         return PAGE_COUNT;
     }

     @Override
    public Fragment getItem(int position){

         return new TopStoriesFragment();
     }


     public CharSequence getPageTitle(int position){
         return tabTitles[position];
     }
}
