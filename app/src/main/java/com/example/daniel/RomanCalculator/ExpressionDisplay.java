package com.example.daniel.RomanCalculator;

import android.widget.TextView;

import java.util.Observable;

public class ExpressionDisplay extends Display {
    public ExpressionDisplay(TextView view) {
        super(view);
    }

    @Override
    public void update(Observable o, Object arg) {
        Expression exp = (Expression) o;
        if (arg != null) {
            view.setText((String) arg);
        }
        else {
            view.setText(exp.getExpression());
        }
    }
}
