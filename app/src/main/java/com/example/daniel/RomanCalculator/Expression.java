package com.example.daniel.RomanCalculator;

import java.util.Observable;
import java.util.Observer;

public class Expression extends Observable implements Observer {
    // Display observes Expression, Expression observes Keypad
    @Override
    public void update(Observable o, Object arg) {

    }
}
