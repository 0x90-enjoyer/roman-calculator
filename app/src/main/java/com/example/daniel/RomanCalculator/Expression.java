package com.example.daniel.RomanCalculator;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Expression extends Observable implements Observer {
    private final ArrayList<Numeral> values;
    private final ArrayList<Character> operators;
    // Display observes Expression, Expression observes Keypad

    public Expression() {
        this.values = new ArrayList<>();
        this.operators = new ArrayList<>();
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

        notifyObservers();  // TODO: notify Display of answer
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO: if digit / roman keypress observed:

        // TODO: if '=' keypress observed:
        evaluate();
    }
}
