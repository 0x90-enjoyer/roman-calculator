package com.example.daniel.RomanCalculator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Expression extends Observable implements Observer, Serializable {
    private ArrayList<Numeral> values;
    private ArrayList<Character> operators;
    private String currentInt;
    private String currentString;
    // Display observes Expression, Expression observes Keypad

    public Expression() {
        this.values = new ArrayList<>();
        this.operators = new ArrayList<>();
        this.currentInt = "";
        this.currentString = "";
    }

    public void inputInteger(String s) {
        if (currentString.isEmpty() && Integer.parseInt(currentInt + s) <= Numeral.MAX_VALUE) {
            if (!currentInt.isEmpty() || !s.equals("0")) {
                currentInt += s;
                notifyObservers();  // notify IntegerDisplay of new digit
            }
        }
    }

    public void inputRoman(String s) {
        if (currentInt.isEmpty()) {
            currentString += s;
            notifyObservers();  // notify RomanDisplay of new char
        }
    }

    public void inputOperator(String op) {
        if (!currentInt.isEmpty() && currentString.isEmpty()) {
            values.add(new Numeral(Integer.valueOf(currentInt)));
            currentInt = "";
            if (op.equals("=")) {
                evaluate();
            }
            else {
                operators.add(op.toCharArray()[0]);
                notifyObservers();  // notify ExpressionDisplay to append and IntegerDisplay to clear
            }
        }
        else if (currentInt.isEmpty() && !currentString.isEmpty()) {
            values.add(new Numeral(currentString));
            currentString = "";
            if (op.equals("=")) {
                evaluate();
            }
            else {
                operators.add(op.toCharArray()[0]);
                notifyObservers();  // notify ExpressionDisplay to append and RomanDisplay to clear
            }
        }
    }

    public void inputDelete() {
        if (!currentInt.isEmpty() && currentString.isEmpty()) {
            currentInt = currentInt.substring(0, currentInt.length() - 1);
            notifyObservers();  // notify IntegerDisplay to delete digit
        }
        else if (currentInt.isEmpty() && !currentString.isEmpty()) {
            currentString = currentString.substring(0, currentString.length() - 1);
            notifyObservers();  // notify RomanDisplay to delete character
        }
    }

    public void inputClear() {
        values.clear();
        operators.clear();
        currentInt = "";
        currentString = "";
        notifyObservers();  // notify all Displays to clear
    }

    public void add(Numeral value) {
        values.add(value);
    }

    public void add(char operator) {
        operators.add(operator);
    }

    public void evaluate() {
        if (values.size() != operators.size() + 1) {
            notifyObservers();  // TODO: notify Display of error (incorrect syntax)
            return;
        }

        int result = 0;
        int num = values.get(0).getValue();
        int sign = 1;
        int i = 1;

        for (char op: operators) {
            int value = values.get(i).getValue();
            if (op == '×') {
                num *= value;
            }
            else if (op == '/') {
                if (value == 0) {
                    notifyObservers();  // TODO: notify Display of error (division by zero)
                    return;
                }
                num /= value;
            }
            else if (op == '+') {
                result += num * sign;
                sign = 1;
                num = value;
            }
            else if (op == '-') {
                result += num * sign;
                sign = -1;
                num = value;
            }
            i++;
        }
        result += num * sign;

        Numeral ans = new Numeral(result);

        if (!ans.checkValid()) {
            notifyObservers();  // TODO: notify Display if answer out of bounds
        }
        else {
            notifyObservers();  // TODO: notify Display of answer

            inputClear();
            currentString = ans.getRoman();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        String key = (String) arg;
        if (key.matches("[0-9]")) {
            inputInteger(key);
        }
        else if (key.matches("[A-Za-z]")) {
            inputRoman(key);
        }
        else if (key.matches("\\W")) {
            inputOperator(key);
        }
        else if (key.equals("delete")) {
            inputDelete();
        }
        else if (key.equals("clear")) {
            inputClear();
        }
    }
}
