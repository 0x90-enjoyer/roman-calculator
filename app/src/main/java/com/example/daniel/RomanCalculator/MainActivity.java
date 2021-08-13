package com.example.daniel.RomanCalculator;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements IntegerFragment.OnFragmentInteractionListener, RomanFragment.OnFragmentInteractionListener {
    /**
     * Inflate the main activity layout's UI as well as initializes various item/event listeners required
     * for interaction with the user. Enables the functionality of TabLayout and ViewPager used for
     * fragments.
     *
     * @param savedInstanceState if the activity is re-initialized, contains the data saved from the
     *                           previous instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Button> buttons = new ArrayList<>();

        buttons.add(findViewById(R.id.button_del));
        buttons.add(findViewById(R.id.button_clear));
        buttons.add(findViewById(R.id.button_add));
        buttons.add(findViewById(R.id.button_subtract));
        buttons.add(findViewById(R.id.button_multiply));
        buttons.add(findViewById(R.id.button_divide));
        buttons.add(findViewById(R.id.button_equals));

        Expression exp = new Expression();

        Keypad kp = new Keypad(buttons);
        kp.addObserver(exp);

        TabLayout tabLayout = findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Integer"));
        tabLayout.addTab(tabLayout.newTab().setText("Roman"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), exp);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            /**
             * Called when a tab is selected. Switches between fragments depending on
             * the currently selected tab in the TabLayout.
             *
             * @param tab a tab in this layout
             */
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            /**
             * Called when a tab is no longer selected.
             * @param tab a tab in this layout
             */
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            /**
             * Called when the user re-selects a tab that is currently selected.
             *
             * @param tab a tab in this layout
             */
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /**
     * Handles communication between fragments.
     *
     * @param uri
     */
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}