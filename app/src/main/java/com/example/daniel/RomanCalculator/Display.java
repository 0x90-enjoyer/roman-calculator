package com.example.daniel.RomanCalculator;

import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

public abstract class Display implements Observer {
    protected TextView view;

    public Display(TextView view) {
        this.view = view;
        this.view.setMovementMethod(new ScrollingMovementMethod());
    }

    // Display observes Expression
    public abstract void update(Observable o, Object arg);
}
