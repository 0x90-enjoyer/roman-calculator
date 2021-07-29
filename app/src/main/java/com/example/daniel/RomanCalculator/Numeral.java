package com.example.daniel.RomanCalculator;

/*
© 2018 Daniel Duong
@author danielduong8@gmail.com
 _____     ______     __   __     __     ______     __            _____     __  __     ______     __   __     ______
/\  __-.  /\  __ \   /\ "-.\ \   /\ \   /\  ___\   /\ \          /\  __-.  /\ \/\ \   /\  __ \   /\ "-.\ \   /\  ___\
\ \ \/\ \ \ \  __ \  \ \ \-.  \  \ \ \  \ \  __\   \ \ \____     \ \ \/\ \ \ \ \_\ \  \ \ \/\ \  \ \ \-.  \  \ \ \__ \
 \ \____-  \ \_\ \_\  \ \_\\"\_\  \ \_\  \ \_____\  \ \_____\     \ \____-  \ \_____\  \ \_____\  \ \_\\"\_\  \ \_____\
  \/____/   \/_/\/_/   \/_/ \/_/   \/_/   \/_____/   \/_____/      \/____/   \/_____/   \/_____/   \/_/ \/_/   \/_____/

*/

public class Numeral {
    private static final int MAX_VALUE = 3999;
    private static final int MIN_VALUE = 1;
    private static final String VALID_ROMAN = "IVXLCDM";
    private static final int[] ROMAN_VALUES = {1, 5, 10, 50, 100, 500, 1000};
    private String roman;
    private int value;

    /**
     * Constructs a Numeral object given a string in the form of roman roman.
     * Checks if the string given string is valid and raises an error if otherwise.
     *
     * @param s the string to be converted into a Numeral object
     * @throws RuntimeException - if the given roman are invalid
     */
    public Numeral(String s) {
        set(s);
    }

    /**
     * Constructs a Numeral object given an integer. Checks if the given integer is
     * valid and raises an error if otherwise.
     *
     * @param i any integer between and including MIN_VALUE-MAX_VALUE
     * @throws RuntimeException - if the integer is negative or greater than MAX_VALUE
     */
    public Numeral(int i) {
        set(i);
    }

    /**
     * Returns the integer value of the Numeral object.
     *
     * @return the integer value
     */
    public int toInt() {
        return this.value;
    }

    /**
     * Returns the roman of the Numeral object as a string.
     *
     * @return the string of roman roman
     */
    public String toString() {
        return this.roman;
    }

    /**
     * Returns a boolean value depending on whether the given string violates any of
     * a various number of conditions and sets the object variables accordingly.
     *
     * @param s the string of roman roman
     * @return false - if the characters of the string are not part of the roman
     * numeral subset, if the integer value of the numeral exceeds MAX_VALUE, and
     * if the characters in the string do not follow proper roman numeral
     * numbering convention true - if otherwise
     */
    public boolean set(String s) {
        String characters = "IVXLCDM";
        for (int i = 0; i < s.length(); i++) {
            if (!characters.contains("" + s.charAt(i))) {
                return false;
            }
        }
        int value = romanToInt(s);
        if (value > MAX_VALUE || !intToRoman(value).equals(s)) {
            return false;
        }
        this.value = value;
        this.roman = s;
        return true;
    }

    /**
     * Checks if the given integer is less than MIN_VALUE or greater than MAX_VALUE and sets the
     * object variables if none of the conditions are violated.
     *
     * @param i an integer value
     * @return false - if the given integer is below MIN_VALUE or exceeds MAX_VALUE, true - if
     * otherwise
     */
    public boolean set(int i) {
        if (i < MIN_VALUE || i > MAX_VALUE) {
            return false;
        }
        this.value = i;
        this.roman = intToRoman(i);
        return true;
    }

    /**
     * Adds the value of the given object to the value of the Numeral object and
     * creates new roman accordingly if all conditions are met and returns a
     * boolean value.
     *
     * @param r a Numeral object
     * @return false - if the new value exceeds MAX_VALUE, true - if otherwise
     */
    public boolean add(Numeral r) {
        if (this.value + r.value > MAX_VALUE) {
            return false;
        }
        this.value += r.value;
        this.roman = intToRoman(this.value);
        return true;
    }

    /**
     * Subtracts the value of the given object from the value of the Numeral object
     * and creates new roman accordingly if all conditions are met and returns a
     * boolean value.
     *
     * @param r a Numeral object
     * @return false - if the new value is below MIN_VALUE, true - if otherwise
     */
    public boolean subtract(Numeral r) {
        if (this.value - r.value < MIN_VALUE) {
            return false;
        }
        this.value -= r.value;
        this.roman = intToRoman(this.value);
        return true;
    }

    /**
     * Multiplies the value of the given object to the value of the Numeral object and
     * creates new roman accordingly if all conditions are met and returns a
     * boolean value.
     *
     * @param r a Numeral object
     * @return false - if the new value exceeds MAX_VALUE, true - if otherwise
     */
    public boolean multiply(Numeral r) {
        if (this.value * r.value > MAX_VALUE) {
            return false;
        }
        this.value *= r.value;
        this.roman = intToRoman(this.value);
        return true;
    }

    /**
     * Divides the value of the given object from the value of the Numeral object
     * and creates new roman accordingly if all conditions are met and returns a
     * boolean value.
     *
     * @param r a Numeral object
     * @return false - if the new value is below MIN_VALUE, true - if otherwise
     */
    public boolean divide(Numeral r) {
        if (this.value / r.value < MIN_VALUE) {
            return false;
        }
        this.value /= r.value;
        this.roman = intToRoman(this.value);
        return true;
    }

    /**
     * Converts an integer to a String of roman roman and returns the String.
     * This is done through the manipulation of place value.
     *
     * @param num the integer to be converted to a String
     * @return the resulting String
     */
    public static String intToRoman(int num) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (num != 0) {
            int remainder = num % 10;
            if (remainder == 4) {
                result.insert(0, VALID_ROMAN.charAt(i + 1));
                result.insert(0, VALID_ROMAN.charAt(i));
            } else if (remainder == 9) {
                result.insert(0, VALID_ROMAN.charAt(i + 2));
                result.insert(0, VALID_ROMAN.charAt(i));
            } else {
                for (int j = 0; j < remainder % 5; j++) {
                    result.insert(0, VALID_ROMAN.charAt(i));
                }
                if (remainder >= 5) {
                    result.insert(0, VALID_ROMAN.charAt(i + 1));
                }
            }
            i += 2;
            num /= 10;
        }
        return result.toString();
    }

    /**
     * Converts a String of roman roman to its integer value and returns the
     * integer.
     *
     * @param s the given string of roman roman
     * @return the resulting integer
     */
    public static int romanToInt(String s) {
        int total = 0;

        for (int i = 0; i < s.length() - 1; i++) {
            int letter = ROMAN_VALUES[VALID_ROMAN.indexOf(s.charAt(i))];
            int adjLetter = ROMAN_VALUES[VALID_ROMAN.indexOf(s.charAt(i + 1))];
            if (letter >= adjLetter) {
                total += letter;
            } else {
                total -= letter;
            }
        }
        total += ROMAN_VALUES[VALID_ROMAN.indexOf(s.charAt(s.length() - 1))];
        return total;
    }
}
