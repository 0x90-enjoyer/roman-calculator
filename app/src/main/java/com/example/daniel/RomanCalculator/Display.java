package com.example.daniel.RomanCalculator;

import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

public abstract class Display implements Observer {
    protected TextView view;
    private String text;

    public Display(TextView view) {
        this.view = view;
        this.text = "";
    }

    // Display observes Expression
    public abstract void update(Observable o, Object arg);
}
