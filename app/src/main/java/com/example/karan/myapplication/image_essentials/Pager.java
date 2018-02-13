package com.example.karan.myapplication.image_essentials;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.karan.myapplication.fragments.Description;
import com.example.karan.myapplication.fragments.FamousPlaces;

//Extending FragmentStatePagerAdapter
public class Pager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    private final Bundle fragmentBundle;

    //Constructor to the class
    public Pager(FragmentManager fm, int tabCount, Bundle bundle) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
        this.fragmentBundle = bundle;
    }

    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
        fragmentBundle = null;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                Description description = new Description();
                description.setArguments(this.fragmentBundle);
                return description;
            case 1:
                FamousPlaces famousPlaces = new FamousPlaces();
                famousPlaces.setArguments(this.fragmentBundle);
                return famousPlaces;
            default:
                return null;
        }
    }
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "CITY DETAILS";
            case 1:
                return "FAMOUS PLACES";
            default:
                return "";
        }
    }

    //Overridden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}