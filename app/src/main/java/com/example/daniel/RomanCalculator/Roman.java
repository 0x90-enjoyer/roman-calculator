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

public class Roman {
    private String numerals;
    private int value;

    public Roman() {
    }

    /**
     * Constructs a Roman object given a string in the form of roman numerals.
     * Checks if the string given string is valid and raises an error if otherwise.
     *
     * @param s the string to be converted into a Roman object
     * @throws RuntimeException - if the given numerals are invalid
     */
    public Roman(String s) {
        set(s);
    }

    /**
     * Constructs a Roman object given an integer. Checks if the given integer is
     * valid and raises an error if otherwise.
     *
     * @param i any integer between and including 1-4999
     * @throws RuntimeException - if the integer is negative or greater than 4999
     */
    public Roman(int i) {
        set(i);
    }

    /**
     * Returns the integer value of the Roman object.
     *
     * @return the integer value
     */
    public int toInt() {
        return this.value;
    }

    /**
     * Returns the numerals of the Roman object as a string.
     *
     * @return the string of roman numerals
     */
    public String toString() {
        return this.numerals;
    }

    /**
     * Returns a boolean value depending on whether the given string violates any of
     * a various number of conditions and sets the object variables accordingly.
     *
     * @param s the string of roman numerals
     * @return false - if the characters of the string are not part of the roman
     * numeral subset, if the integer value of the numeral exceeds 4999, and
     * if the characters in the string do not follow proper roman numeral
     * numbering convention true - if otherwise
     */
    public boolean set(String s) {
        String characters = "IVXLCDM";
        for (int i = 0; i < s.length(); i++) {
            if (characters.indexOf(s.charAt(i)) == -1) {
                return false;
            }
        }
        int value = convertToInt(s);
        if (value >= 5000 || !convertToString(value).equals(s)) {
            return false;
        }
        this.value = value;
        this.numerals = s;
        return true;
    }

    /**
     * Checks if the given integer is less than 1 or greater than 4999 and sets the
     * object variables if none of the conditions are violated.
     *
     * @param i an integer value
     * @return false - if the given integer is below 1 or exceeds 4999, true - if
     * otherwise
     */
    public boolean set(int i) {
        if (i < 1 || i >= 5000) {
            return false;
        }
        this.value = i;
        this.numerals = convertToString(i);
        return true;
    }

    /**
     * Adds the given integer to the value of the Roman object and creates new
     * numerals accordingly if all conditions are met and returns a boolean value.
     *
     * @param i any integer
     * @return false - if the new value exceeds 4999, true - if otherwise
     */
    public boolean add(int i) {
        if (this.value + i >= 5000) {
            return false;
        }
        this.value += i;
        this.numerals = convertToString(this.value);
        return true;
    }

    /**
     * Adds the value of the given object to the value of the Roman object and
     * creates new numerals accordingly if all conditions are met and returns a
     * boolean value.
     *
     * @param r a Roman object
     * @return false - if the new value exceeds 4999, true - if otherwise
     */
    public boolean add(Roman r) {
        if (this.value + r.value >= 5000) {
            return false;
        }
        this.value += r.value;
        this.numerals = convertToString(this.value);
        return true;
    }

    /**
     * Subtracts the value of the given object from the value of the Roman object
     * and creates new numerals accordingly if all conditions are met and returns a
     * boolean value.
     *
     * @param i any integer
     * @return false - if the new value is below 1, true - if otherwise
     */
    public boolean subtract(int i) {
        if (this.value - i < 1) {
            return false;
        }
        this.value -= i;
        this.numerals = convertToString(this.value);
        return true;
    }

    /**
     * Subtracts the value of the given object from the value of the Roman object
     * and creates new numerals accordingly if all conditions are met and returns a
     * boolean value.
     *
     * @param r a Roman object
     * @return false - if the new value is below 1, true - if otherwise
     */
    public boolean subtract(Roman r) {
        if (this.value - r.value < 1) {
            return false;
        }
        this.value -= r.value;
        this.numerals = convertToString(this.value);
        return true;
    }

    /**
     * Multiplies the given integer to the value of the Roman object and creates new
     * numerals accordingly if all conditions are met and returns a boolean value.
     *
     * @param i any integer
     * @return false - if the new value exceeds 4999, true - if otherwise
     */
    public boolean multiply(int i) {
        if (this.value * i >= 5000) {
            return false;
        }
        this.value *= i;
        this.numerals = convertToString(this.value);
        return true;
    }

    /**
     * Multiplies the value of the given object to the value of the Roman object and
     * creates new numerals accordingly if all conditions are met and returns a
     * boolean value.
     *
     * @param r a Roman object
     * @return false - if the new value exceeds 4999, true - if otherwise
     */
    public boolean multiply(Roman r) {
        if (this.value * r.value >= 5000) {
            return false;
        }
        this.value *= r.value;
        this.numerals = convertToString(this.value);
        return true;
    }

    /**
     * Divides the value of the given object from the value of the Roman object
     * and creates new numerals accordingly if all conditions are met and returns a
     * boolean value.
     *
     * @param i any integer
     * @return false - if the new value is below 1, true - if otherwise
     */
    public boolean divide(int i) {
        if (this.value / i < 1) {
            return false;
        }
        this.value /= i;
        this.numerals = convertToString(this.value);
        return true;
    }

    /**
     * Divides the value of the given object from the value of the Roman object
     * and creates new numerals accordingly if all conditions are met and returns a
     * boolean value.
     *
     * @param r a Roman object
     * @return false - if the new value is below 1, true - if otherwise
     */
    public boolean divide(Roman r) {
        if (this.value / r.value < 1) {
            return false;
        }
        this.value /= r.value;
        this.numerals = convertToString(this.value);
        return true;
    }

    /**
     * Converts an integer to a String of roman numerals and returns the String.
     * This is done through the manipulation of place value.
     *
     * @param num the integer to be converted to a String
     * @return the resulting String
     */
    public static String convertToString(int num) {
        String numerals = "IVXLCDM";
        String full = "";
        for (int k = 0; ; num /= 10, k += 2) {
            int j = num % 10;
            if (k == numerals.length() - 1) {
                full = new String(new char[num]).replace("\0", "" + numerals.charAt(k)) + full;
                break;
            }
            String temp = new String(new char[j]).replace("\0", "" + numerals.charAt(k));

            temp = temp.replaceAll(new String(new char[9]).replace("\0", "" + numerals.charAt(k)),
                    "" + numerals.charAt(k) + numerals.charAt(k + 2));
            temp = temp.replaceAll(new String(new char[5]).replace("\0", "" + numerals.charAt(k)),
                    "" + numerals.charAt(k + 1));
            temp = temp.replaceAll(new String(new char[4]).replace("\0", "" + numerals.charAt(k)),
                    "" + numerals.charAt(k) + numerals.charAt(k + 1));
            full = temp + full;
        }
        return full;
    }

    /**
     * Converts a String of roman numerals to its integer value and returns the
     * integer.
     *
     * @param str the given string of roman numerals
     * @return the resulting integer
     */
    public static int convertToInt(String str) {
        int[] values = {1, 5, 10, 50, 100, 500, 1000};
        String numerals = "IVXLCDM";
        int total = 0;

        for (int i = 0; i < str.length() - 1; i++) {
            int letter = values[numerals.indexOf(str.charAt(i))];
            int adjletter = values[numerals.indexOf(str.charAt(i + 1))];
            if (letter >= adjletter) {
                total += letter;
            } else {
                total -= letter;
            }
        }
        total += values[numerals.indexOf(str.charAt(str.length() - 1))];
        return total;
    }
}
