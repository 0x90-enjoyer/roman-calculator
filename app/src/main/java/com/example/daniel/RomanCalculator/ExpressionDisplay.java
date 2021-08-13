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
//            StringBuilder sb = new StringBuilder();
//            for (int i = 0; i < exp.getValues().size(); i++) {
//                Numeral value = exp.getValues().get(i);
//                char operator = exp.getOperators().get(i);
//                if (exp.getValueTypes().get(i).equals("int")) {
//                    sb.append(String.valueOf(value.getValue()));
//                }
//                else if (exp.getValueTypes().get(i).equals("String")) {
//                    sb.append(value.getRoman());
//                }
//                sb.append(" ");
//                sb.append(operator);
//                sb.append(" ");
//            }
//            text = sb.toString();
//            view.setText(text);
        }
    }
}
