package com.example.daniel.RomanCalculator;

import android.widget.TextView;

import java.util.Observable;

public class IntegerDisplay extends Display {
    public IntegerDisplay(TextView view) {
        super(view);
    }

    @Override
    public void update(Observable o, Object arg) {
        Expression exp = (Expression) o;
        if (exp.getResult() != null) {
            view.setText(String.valueOf(exp.getResult().getValue()));
        }
        else {
            view.setText(exp.getCurrentInt());
        }
    }
}
