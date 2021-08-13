package com.example.daniel.RomanCalculator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private Expression exp;
    int mNoOfTabs;

    /**
     * PagerAdapter constructor calls upon the parent class to initialize the FragmentManager as
     * well as assigns the number of tabs in the TabLayout to a class variable.
     *
     * @param fm FragmentManager - an interface used to interact with fragment objects inside of an
     *           activity
     * @param NumberOfTabs the number of tabs present in the TabLayout
     */
    public PagerAdapter(FragmentManager fm, int NumberOfTabs, Expression exp) {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
        this.exp = exp;
    }

    /**
     * Switches between fragments depending on which position is selected (in TabLayout).
     *
     * @param position the currently selected tab
     * @return the new fragment to be displayed onto the screen
     */
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return IntegerFragment.newInstance(exp);
            case 1:
                return RomanFragment.newInstance(exp);
            default:
                return null;
        }
    }

    /**
     * Returns the class variable containing the total number of tabs in the TabLayout.
     * @return the total number of tabs
     */
    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
