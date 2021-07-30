package com.example.daniel.RomanCalculator;

import java.util.Observable;
import java.util.Observer;

public abstract class Display implements Observer {
    // Display observes Expression
    @Override
    public abstract void update(Observable o, Object arg);
}
