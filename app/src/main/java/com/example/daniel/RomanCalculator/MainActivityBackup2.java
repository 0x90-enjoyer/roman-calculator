package com.example.daniel.RomanCalculator;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivityBackup2 extends AppCompatActivity implements IntegerFragment.OnFragmentInteractionListener, RomanFragment.OnFragmentInteractionListener {
    ArrayList<Roman> items = new ArrayList<>();
    ArrayList<Character> operations = new ArrayList<>();
    TextView focus;
    boolean answer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        focus = findViewById(R.id.display_2);

        TabLayout tabLayout = findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Integer"));
        tabLayout.addTab(tabLayout.newTab().setText("Roman"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void numberInput(View view) {
        if (answer) {
            clear(view);
            answer = false;
        }

        final int TEMP_ID = getResources().getIdentifier("button_0", "id", getPackageName());
        TextView display_2 = findViewById(R.id.display_2);
        String digit = "" + (view.getId() - TEMP_ID);

        if (display_2.getText().toString().equals("") && focus == display_2) {
            display_2.setText("");
        }
        focus.append(digit);
    }

    // TODO: need to edit objects, try to find an alternative to store values and calculate (megastring)
    public void delete(View view) {
        TextView display_1 = findViewById(R.id.display_1);
        TextView display_2 = findViewById(R.id.display_2);
        TextView display_3 = findViewById(R.id.display_3);

        display_3.setText("");

        if (display_2.getText().length() > 0) { // if display_2 contains numbers, deletes them
            display_2.setText(display_2.getText().subSequence(0, display_2.getText().length() - 1));
        } else if (display_1.getText().length() > 0) { // if display_1 contains numbers deletes them
            display_1.setText(display_1.getText().subSequence(0, display_1.getText().length() - 1));
            focus = display_1;
            answer = false;
        }
    }

    public void clear(View view) {
        TextView display_1 = findViewById(R.id.display_1);
        TextView display_2 = findViewById(R.id.display_2);
        TextView display_3 = findViewById(R.id.display_3);

        display_1.setText("");
        display_2.setText("");
        display_3.setText("");

        focus = display_2;
    }

    public void add(View view) {
        TextView display_1 = findViewById(R.id.display_1);
        TextView display_2 = findViewById(R.id.display_2);
        TextView display_3 = findViewById(R.id.display_3);

        if (answer) {
            display_1.setText("");
            display_3.setText("");
            answer = false;
        }

        if (display_1.getText().toString().equals("") && display_2.getText().toString().equals("")) {

        } else if (!display_2.getText().toString().equals("")) {
            display_1.append(display_2.getText().toString());
            display_1.append("+");
            display_2.setText("");
        } else {
            String s = display_1.getText().subSequence(0, display_1.length() - 1) + "+";
            display_1.setText(s);
        }

        focus = display_2;
    }

    public void subtract(View view) {
        TextView display_1 = findViewById(R.id.display_1);
        TextView display_2 = findViewById(R.id.display_2);
        TextView display_3 = findViewById(R.id.display_3);

        if (answer) {
            display_1.setText("");
            display_3.setText("");
            answer = false;
        }

        if (display_1.getText().toString().equals("") && display_2.getText().toString().equals("")) {

        } else if (!display_2.getText().toString().equals("")) {
            display_1.append(display_2.getText().toString());
            display_1.append("-");
            display_2.setText("");
        } else {
            String s = display_1.getText().subSequence(0, display_1.length() - 1) + "-";
            display_1.setText(s);
        }

        focus = display_2;
    }

    public void multiply(View view) {
        TextView display_1 = findViewById(R.id.display_1);
        TextView display_2 = findViewById(R.id.display_2);
        TextView display_3 = findViewById(R.id.display_3);

        if (answer) {
            display_1.setText("");
            display_3.setText("");
            answer = false;
        }

        if (display_1.getText().toString().equals("") && display_2.getText().toString().equals("")) {

        } else if (!display_2.getText().toString().equals("")) {
            display_1.append(display_2.getText().toString());
            display_1.append("×");
            display_2.setText("");
        } else {
            String s = display_1.getText().subSequence(0, display_1.length() - 1) + "×";
            display_1.setText(s);
        }

        focus = display_2;
    }

    public void divide(View view) {
        TextView display_1 = findViewById(R.id.display_1);
        TextView display_2 = findViewById(R.id.display_2);
        TextView display_3 = findViewById(R.id.display_3);

        if (answer) {
            display_1.setText("");
            display_3.setText("");
            answer = false;
        }

        if (display_1.getText().toString().equals("") && display_2.getText().toString().equals("")) {

        } else if (!display_2.getText().toString().equals("")) {
            display_1.append(display_2.getText().toString());
            display_1.append("÷");
            display_2.setText("");
        } else {
            String s = display_1.getText().subSequence(0, display_1.length() - 1) + "÷";
            display_1.setText(s);
        }

        focus = display_2;
    }

    public void equal(View view) {
        TextView display_1 = findViewById(R.id.display_1);
        TextView display_2 = findViewById(R.id.display_2);
        TextView display_3 = findViewById(R.id.display_3);
        Log.d("test", !display_1.getText().toString().equals("") + "");

        if (!answer && (!display_1.getText().toString().equals("") || (display_1.getText().toString().equals("") && !display_2.getText().toString().equals("")))) {
            display_1.append(display_2.getText().toString());
            display_1.append("=");

            String displayText = display_1.getText().toString();
            String s = "";
            for (int i = 0; i < display_1.getText().length(); i++) {
                if (displayText.charAt(i) == '+' || displayText.charAt(i) == '-' || displayText.charAt(i) == '×' || displayText.charAt(i) == '÷') {
                    items.add(new Roman(Integer.parseInt(s)));
                    operations.add(displayText.charAt(i));
                    s = "";
                } else {
                    s += displayText.charAt(i);
                }
            }
            items.add(new Roman(Integer.parseInt(s.substring(0, s.length() - 1))));
            Roman r = calculate(operations, items);
            items.clear();
            operations.clear();

            display_2.setText(r.toInt() + "");
            display_3.setText(r.toString());

            answer = true;
        }
    }

    public Roman calculate(ArrayList<Character> operations, ArrayList<Roman> items) {
        if (operations.size() == 0) {
            return items.get(0);
        }
        for (int i = 0; i < operations.size(); i++) {
            if (operations.get(i) == '×') {
                items.get(i).multiply(items.get(i + 1));
                items.remove(i + 1);
                operations.remove(i);
                return calculate(operations, items);
            } else if (operations.get(i) == '÷') {
                items.get(i).divide(items.get(i + 1));
                items.remove(i + 1);
                operations.remove(i);
                return calculate(operations, items);
            }
        }
        for (int i = 0; i < operations.size(); i++) {
            if (operations.get(i) == '+') {
                items.get(i).add(items.get(i + 1));
                items.remove(i + 1);
                operations.remove(i);
                return calculate(operations, items);
            } else if (operations.get(i) == '-') {
                items.get(i).subtract(items.get(i + 1));
                items.remove(i + 1);
                operations.remove(i);
                return calculate(operations, items);
            }
        }
        return new Roman();
    }

    public void displayError() {

    }
}

// TODO: if only one number is inputted and equal button is pressed, display returns the number
// TODO: deleting up until the equal sign and pressing equal should produce an answer
// TODO: pressing an operation button while editing the display deletes the entire display