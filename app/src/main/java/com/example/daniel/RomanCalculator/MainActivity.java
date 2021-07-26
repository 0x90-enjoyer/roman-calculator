package com.example.daniel.RomanCalculator;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements IntegerFragment.OnFragmentInteractionListener, RomanFragment.OnFragmentInteractionListener {
    ArrayList<Roman> items = new ArrayList<>();
    ArrayList<Character> operations = new ArrayList<>();
    String currentNum = "";
    boolean answer = false;
    boolean error = false;

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

        TabLayout tabLayout = findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Integer"));
        tabLayout.addTab(tabLayout.newTab().setText("Roman"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
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

    /**
     * onClick method that activates upon the interaction of a roman button. Appends the roman
     * numeral to the first display after performing various error checks. Prevents the combining of
     * integers and roman numerals. Also prevents input if an numerical error or the answer is present.
     * Keeps track of the current integer or numeral on the display. Keeps numerals in the range of
     * 1-4999 and prevents the entering of invalid roman numerals.
     *
     * @param view any UI that can respond to user input
     */
    public void inputRoman(View view) {
        if (!error && !answer && (currentNum.length() == 0 || Character.isLetter(currentNum.charAt(0)))) { // prevents combining of numbers and letters, disables input after answer
            TextView display_1 = findViewById(R.id.display_1);
            String display_1s = display_1.getText().toString();

            String character = ((TextView) findViewById(view.getId())).getText().toString();

            currentNum = "";
            for (int i = display_1s.length() - 1; i >= 0; i--) { // find the last inputted numeral
                if (Character.isLetter(display_1s.charAt(i))) {
                    currentNum = display_1s.charAt(i) + currentNum;
                } else {
                    break;
                }
            }

            Roman r = new Roman();

            if (r.set(currentNum + character)) { // prevents numbers from going out of bounds
                display_1.append(character);
                currentNum = currentNum + character;
            }
        }
    }

    /**
     * onClick method that activates upon the interaction of an integer button. Appends the integer
     * to the first display after performing various error checks. Prevents the combining of
     * integers and roman numerals. Prevents input if an numerical error or answer is present.
     * Keeps track of the current integer or numeral on the display. Keeps integers in the range of
     * 1-4999.
     *
     * @param view any UI that can respond to user input
     */
    public void inputInteger(View view) {
        if (!error && !answer && (currentNum.length() == 0 || Character.isDigit(currentNum.charAt(0)))) {
            TextView display_1 = findViewById(R.id.display_1);
            String display_1s = display_1.getText().toString();

            String character = ((TextView) findViewById(view.getId())).getText().toString();

            currentNum = "";
            for (int i = display_1s.length() - 1; i >= 0; i--) {
                if (Character.isDigit(display_1s.charAt(i))) {
                    currentNum = display_1s.charAt(i) + currentNum;
                } else {
                    break;
                }
            }

            Roman r = new Roman();
            if (r.set(Integer.parseInt(currentNum + character))) {
                display_1.append(character);
                currentNum = currentNum + character;
            }
        }
    }

    /**
     * onClick method that activates upon the interaction of the delete button (D). Removes the last
     * index from the display after performing various error checks. Prevents the delete action if
     * an error is present on the screen or if the display is empty. Keeps track of the current
     * integer or numeral on the display.
     *
     * @param view any UI that can respond to user input
     */
    public void delete(View view) {
        if (!error) {
            TextView display_1 = findViewById(R.id.display_1);

            ((TextView) findViewById(R.id.display_2)).setText("");
            ((TextView) findViewById(R.id.display_3)).setText("");

            if (display_1.getText().length() > 0) { // if display_1 contains numbers deletes them
                display_1.setText(display_1.getText().subSequence(0, display_1.getText().length() - 1));
                answer = false;
                String display_1s = display_1.getText().toString();
                currentNum = "";
                for (int i = display_1s.length() - 1; i >= 0; i--) {
                    if (Character.isDigit(display_1s.charAt(i))) {
                        currentNum = display_1s.charAt(i) + currentNum;
                    } else if (Character.isLetter(display_1s.charAt(i))) {
                        currentNum = display_1s.charAt(i) + currentNum;
                    } else {
                        break;
                    }
                }
            }
        }
    }

    /**
     * onClick method that activates upon the interaction of the clear button (C). Resets the
     * calculator to its factory state by clearing all displays and resetting all class variables.
     *
     * @param view any UI that can respond to user input
     */
    public void clear(View view) {
        ((TextView) findViewById(R.id.display_1)).setText("");
        ((TextView) findViewById(R.id.display_2)).setText("");
        ((TextView) findViewById(R.id.display_3)).setText("");

        answer = false;
        error = false;
        currentNum = "";
    }

    /**
     * onClick method that activates upon the interaction of any operation button. Appends an
     * operation sign to the display after performing various error checks. Prevents the appending
     * if an error is present or replaces an operation if the last index already contains an
     * operation sign. Prevents operations from being added to an empty display. If an answer is
     * present, the answer is added to the display before adding the operation.
     *
     * @param view any UI that can respond to user input
     */
    public void operation(View view) {
        if (!error) {
            TextView display_1 = findViewById(R.id.display_1);
            TextView display_2 = findViewById(R.id.display_2);

            if (answer) { // allows answer to be used in next equation
                display_1.setText(display_2.getText());
                display_2.setText("");
                ((TextView) findViewById(R.id.display_3)).setText("");
                answer = false;
            }

            String display_1s = display_1.getText().toString();
            String character = ((TextView) findViewById(view.getId())).getText().toString();

            if (display_1s.equals("")) { // prevents operations on empty display

            } else if (!Character.isDigit(display_1s.charAt(display_1s.length() - 1)) &&
                    !Character.isLetter(display_1s.charAt(display_1s.length() - 1))) { // if two operations are inputted consecutively, most recent replaces the other
                String s = display_1s.subSequence(0, display_1.length() - 1) + character;
                display_1.setText(s);
            } else {
                display_1.append(character);
                currentNum = "";
            }
        }
    }

    /**
     * onClick method that activates upon the interaction of the equal button. Separates the display
     * elements into a list containing all integers / numerals (as Roman objects) and a list
     * containing all operations. Calls upon the calculate(ArrayList<Character> operations,
     * ArrayList<Roman> items) method to calculate the result of all integers/numerals and
     * operations. If the Roman object produced has the default integer value, the result is
     * outputted to the display. If otherwise, an error message is displayed onto the displays.
     *
     * @param view any UI that can respond to user input
     */
    public void equal(View view) {
        if (!error) {
            TextView display_1 = findViewById(R.id.display_1);
            TextView display_2 = findViewById(R.id.display_2);
            TextView display_3 = findViewById(R.id.display_3);

            // disables equal button in various scenarios
            if (!answer && !display_1.getText().toString().equals("") && (Character.isDigit(display_1.getText().charAt(display_1.getText().length() - 1)) || Character.isLetter(display_1.getText().charAt(display_1.getText().length() - 1)))) {
                String displayText = display_1.getText().toString();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < display_1.getText().length(); i++) {
                    if (!Character.isDigit(displayText.charAt(i)) && !Character.isLetter(displayText.charAt(i))) {
                        if (Character.isDigit(sb.toString().charAt(0))) {
                            items.add(new Roman(Integer.parseInt(sb.toString())));
                        } else if (Character.isLetter(sb.toString().charAt(0))) {
                            items.add(new Roman(sb.toString()));
                        }
                        operations.add(displayText.charAt(i));
                        sb.delete(0, sb.length());
                    } else {
                        sb.append(displayText.charAt(i));
                    }
                }
                if (Character.isDigit(sb.toString().charAt(0))) {
                    items.add(new Roman(Integer.parseInt(sb.toString())));
                } else if (Character.isLetter(sb.toString().charAt(0))) {
                    items.add(new Roman(sb.toString()));
                }
                Roman r = calculate(operations, items);
                items.clear();
                operations.clear();

                if (r.toInt() != 0) {
                    String i = r.toInt() + "";
                    display_1.append("=");
                    display_2.setText(i);
                    display_3.setText(r.toString());
                    answer = true;
                    currentNum = "";
                } else { // if resulting total is out of range, displays an error
                    display_1.setText(getString(R.string.error_1));
                    display_2.setText(getString(R.string.error_2));
                    display_3.setText(getString(R.string.error_3));
                    error = true;
                    currentNum = "";
                }
            }
        }
    }

    /**
     * A recursive calculate method that determines the result of all Roman objects and operations
     * associated with it while accounting for the order of operations. Scans the list of operations
     * for an index containing either a multiplication or division operator and takes the same index
     * of the Roman list and performs the operation on the adjacent Roman. If no multiplication or
     * division operators are present, the same process is repeated for addition and subtraction. If
     * any operation performed results in a Roman object with a value that is out of range, a
     * "blank" roman object is returned. The function calls itself with the new list of Roman
     * objects and operations and once the list of Roman objects is reduced to a single object, the
     * Roman object is returned.
     *
     * @param operations the Character ArrayList containing the operators
     * @param items the Roman ArrayList containing the Roman objects
     * @return if list is valid - a Roman object containing the total values of all
     * integers/numerals and operations, if otherwise, returns a "blank" Roman object with no
     * parameters
     */
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