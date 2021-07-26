package com.example.daniel.RomanCalculator;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivityBackup3 extends AppCompatActivity implements IntegerFragment.OnFragmentInteractionListener, RomanFragment.OnFragmentInteractionListener {
    ArrayList<Roman> items = new ArrayList<>();
    ArrayList<Character> operations = new ArrayList<>();
    String currentNum = "";
    char currentOperation = ' ';
    boolean answer = false;
    boolean error = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        if (!error) {
            TextView display_1 = findViewById(R.id.display_1);
            TextView display_2 = findViewById(R.id.display_2);
            TextView display_3 = findViewById(R.id.display_3);

            String display_1s = display_1.getText().toString();

            if (answer) {
                display_1.setText("");
                display_2.setText("");
                display_3.setText("");
                answer = false;
            }

            final int TEMP_ID = getResources().getIdentifier("button_0", "id",
                    getPackageName());
            String digit = "" + (view.getId() - TEMP_ID);

            if (!(currentOperation == ' ')) {
                currentNum = display_1s.substring(display_1s.indexOf(currentOperation), display_1s.length());
            } else {
                currentNum = display_1s;
            }

            if (Integer.parseInt(currentNum + digit) < 5000) {
                display_1.append(digit);
            }
        }
    }

    public void delete(View view) {
        if (!error) {
            TextView display_1 = findViewById(R.id.display_1);
            TextView display_2 = findViewById(R.id.display_2);
            TextView display_3 = findViewById(R.id.display_3);

            String display_1s = display_1.getText().toString();

            display_2.setText("");
            display_3.setText("");

            if (display_1.getText().length() > 0) { // if display_1 contains numbers deletes them
                display_1.setText(display_1.getText().subSequence(0, display_1.getText().length() - 1));
                answer = false;
                if (!(currentOperation == ' ')) {
                    currentNum = display_1s.substring(display_1s.indexOf(currentOperation), display_1s.length());
                } else {
                    currentNum = display_1s;
                }
            }
        }
    }

    public void clear(View view) {
        TextView display_1 = findViewById(R.id.display_1);
        TextView display_2 = findViewById(R.id.display_2);
        TextView display_3 = findViewById(R.id.display_3);

        display_1.setText("");
        display_2.setText("");
        display_3.setText("");

        answer = false;
        error = false;
    }

    public void add(View view) {
        if (!error) {
            TextView display_1 = findViewById(R.id.display_1);
            TextView display_2 = findViewById(R.id.display_2);
            TextView display_3 = findViewById(R.id.display_3);

            if (answer) {
                display_1.setText(display_2.getText());
                display_2.setText("");
                display_3.setText("");
                answer = false;
            }

            if (display_1.getText().toString().equals("")) {

            } else if (!Character.isDigit(display_1.getText().charAt(display_1.getText().length() - 1))) {
                String s = display_1.getText().subSequence(0, display_1.length() - 1) + "+";
                display_1.setText(s);
            } else {
                display_1.append("+");
            }
        }
    }

    public void subtract(View view) {
        if (!error) {
            TextView display_1 = findViewById(R.id.display_1);
            TextView display_2 = findViewById(R.id.display_2);
            TextView display_3 = findViewById(R.id.display_3);

            if (answer) {
                display_1.setText(display_2.getText());
                display_2.setText("");
                display_3.setText("");
                answer = false;
            }

            if (display_1.getText().toString().equals("")) {

            } else if (!Character.isDigit(display_1.getText().charAt(display_1.getText().length() - 1))) {
                String s = display_1.getText().subSequence(0, display_1.length() - 1) + "-";
                display_1.setText(s);
            } else {
                display_1.append("-");
            }
        }
    }

    public void multiply(View view) {
        if (!error) {
            TextView display_1 = findViewById(R.id.display_1);
            TextView display_2 = findViewById(R.id.display_2);
            TextView display_3 = findViewById(R.id.display_3);

            if (answer) {
                display_1.setText(display_2.getText());
                display_2.setText("");
                display_3.setText("");
                answer = false;
            }

            if (display_1.getText().toString().equals("")) {

            } else if (!Character.isDigit(display_1.getText().charAt(display_1.getText().length() - 1))) {
                String s = display_1.getText().subSequence(0, display_1.length() - 1) + "×";
                display_1.setText(s);
            } else {
                display_1.append("×");
            }
        }
    }

    public void divide(View view) {
        if (!error) {
            TextView display_1 = findViewById(R.id.display_1);
            TextView display_2 = findViewById(R.id.display_2);
            TextView display_3 = findViewById(R.id.display_3);

            if (answer) {
                display_1.setText(display_2.getText());
                display_2.setText("");
                display_3.setText("");
                answer = false;
            }

            if (display_1.getText().toString().equals("")) {

            } else if (!Character.isDigit(display_1.getText().charAt(display_1.getText().length() - 1))) {
                String s = display_1.getText().subSequence(0, display_1.length() - 1) + "÷";
                display_1.setText(s);
            } else {
                display_1.append("÷");
            }
        }
    }

    public void equal(View view) {
        if (!error) {
            TextView display_1 = findViewById(R.id.display_1);
            TextView display_2 = findViewById(R.id.display_2);
            TextView display_3 = findViewById(R.id.display_3);

            if (!answer && !display_1.getText().toString().equals("") && Character.isDigit(display_1.getText().charAt(display_1.getText().length() - 1))) {
                String displayText = display_1.getText().toString();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < display_1.getText().length(); i++) {
                    if (displayText.charAt(i) == '+' || displayText.charAt(i) == '-' || displayText.charAt(i) == '×' || displayText.charAt(i) == '÷') {
                        items.add(new Roman(Integer.parseInt(sb.toString())));
                        operations.add(displayText.charAt(i));
                        sb.delete(0, sb.length());
                    } else {
                        sb.append(displayText.charAt(i));
                    }
                }
                items.add(new Roman(Integer.parseInt(sb.toString())));
                Roman r = calculate(operations, items);
                items.clear();
                operations.clear();

                if (r.toInt() != 0) {
                    String i = r.toInt() + "";
                    display_1.append("=");
                    display_2.setText(i);
                    display_3.setText(r.toString());
                    answer = true;
                } else {
                    display_1.setText(getString(R.string.error_1));
                    display_2.setText(getString(R.string.error_2));
                    display_3.setText(getString(R.string.error_3));
                    error = true;
                }
            }
        }
    }

    public Roman calculate(ArrayList<Character> operations, ArrayList<Roman> items) {
        if (operations.size() == 0) {
            return items.get(0);
        }
        for (int i = 0; i < operations.size(); i++) {
            if (operations.get(i) == '×') {
                if (!items.get(i).multiply(items.get(i + 1))) {
                    return new Roman();
                }
                items.remove(i + 1);
                operations.remove(i);
                return calculate(operations, items);
            } else if (operations.get(i) == '÷') {
                if (!items.get(i).divide(items.get(i + 1))) {
                    return new Roman();
                }
                items.remove(i + 1);
                operations.remove(i);
                return calculate(operations, items);
            }
        }
        for (int i = 0; i < operations.size(); i++) {
            if (operations.get(i) == '+') {
                if (!items.get(i).add(items.get(i + 1))) {
                    return new Roman();
                }
                items.remove(i + 1);
                operations.remove(i);
                return calculate(operations, items);
            } else if (operations.get(i) == '-') {
                if (!items.get(i).subtract(items.get(i + 1))) {
                    return new Roman();
                }
                items.remove(i + 1);
                operations.remove(i);
                return calculate(operations, items);
            }
        }
        return new Roman();
    }
}

// TODO: if only one number is inputted and equal button is pressed, display returns the number
// TODO: deleting up until the equal sign and pressing equal should produce an answer
// TODO: pressing an operation button while editing the display deletes the entire display