package com.example.daniel.RomanCalculator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Expression extends Observable implements Observer, Serializable {
    private ArrayList<Numeral> values;
    private ArrayList<Character> operators;
    private String currentInt;
    private String currentRoman;
    private String expression;
    private Numeral result;
// Display observes Expression, Expression observes Keypad

    public Expression() {
        this.values = new ArrayList<>();
        this.operators = new ArrayList<>();
        this.currentInt = "";
        this.currentRoman = "";
        this.expression = "";
        this.result = null;
    }

    public ArrayList<Numeral> getValues() {
        return values;
    }

    public ArrayList<Character> getOperators() {
        return operators;
    }

    public String getCurrentInt() {
        return currentInt;
    }

    public String getCurrentRoman() {
        return currentRoman;
    }

    public String getExpression() {
        return expression;
    }

    public Numeral getResult() {
        return result;
    }

    public void inputInteger(String s) {
        if (currentRoman.isEmpty() && Integer.parseInt(currentInt + s) <= Numeral.MAX_VALUE) {
            if (!currentInt.isEmpty() || !s.equals("0")) {
                currentInt += s;
//                result = null;
                setChanged();
                notifyObservers();  // notify IntegerDisplay of new digit
            }
        }
    }

    public void inputRoman(String s) {
        if (currentInt.isEmpty()) {
            Numeral test = new Numeral(currentRoman + s);
            if (test.checkValid()) {
                currentRoman += s;
//                result = null;
                setChanged();
                notifyObservers();  // notify RomanDisplay of new char
            }
        }
    }

    public void inputOperator(String op) {
        if (!currentInt.isEmpty() && currentRoman.isEmpty()) {
            values.add(new Numeral(Integer.valueOf(currentInt)));
            expression += currentInt + " " + op + " ";
            currentInt = "";
//            result = null;
            if (op.equals("=")) {
                evaluate();
            }
            else {
                operators.add(op.toCharArray()[0]);
                setChanged();
                notifyObservers();  // notify ExpressionDisplay to append and IntegerDisplay to clear
            }
        }
        else if (currentInt.isEmpty() && !currentRoman.isEmpty()) {
            values.add(new Numeral(currentRoman));
            expression += currentRoman + " " + op + " ";
            currentRoman = "";
//            result = null;
            if (op.equals("=")) {
                evaluate();
            }
            else {
                operators.add(op.toCharArray()[0]);
                setChanged();
                notifyObservers();  // notify ExpressionDisplay to append and RomanDisplay to clear
            }
        }
    }

    public void inputDelete() {
        if (result == null) {
            if (!currentInt.isEmpty() && currentRoman.isEmpty()) {
                currentInt = currentInt.substring(0, currentInt.length() - 1);
                setChanged();
                notifyObservers();  // notify IntegerDisplay to delete digit
            }
            else if (currentInt.isEmpty() && !currentRoman.isEmpty()) {
                currentRoman = currentRoman.substring(0, currentRoman.length() - 1);
                setChanged();
                notifyObservers();  // notify RomanDisplay to delete character
            }
        }
    }

    public void inputClear() {
        values.clear();
        operators.clear();
        currentInt = "";
        currentRoman = "";
        expression = "";
        result = null;
        setChanged();
        notifyObservers();  // notify all Displays to clear
    }

    public void resultClear() {
        expression = "";
        result = null;
    }

    public void evaluate() {
        if (values.size() != operators.size() + 1) {
            setChanged();
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
                    setChanged();
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

        String saved_exp = expression;

        inputClear();

        if (!ans.checkValid()) {
            setChanged();
            notifyObservers("Out of Bounds");  // TODO: notify Display if answer out of bounds
        }
        else {
            this.expression = saved_exp;
            this.result = ans;
            currentRoman = ans.getRoman();

            setChanged();
            notifyObservers();  // TODO: notify Display of answer

            resultClear();
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
