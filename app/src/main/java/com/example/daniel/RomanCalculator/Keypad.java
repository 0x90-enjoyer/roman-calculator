package com.example.daniel.RomanCalculator;

import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Observable;

public class Keypad extends Observable {
    private View.OnClickListener listener;
    private ArrayList<Button> keys;

    // Expression observes Keypad
    public Keypad(ArrayList<Button> keys) {
        this.keys = keys;
        this.listener = Keypad.this::onClick;
        setListeners();
    }

    private void setListeners() {
        for (Button key : keys) {
            key.setOnClickListener(listener);
        }
    }

    public void onClick(View v) {
        setChanged();
        notifyObservers(v.getTag());
    }
}
