package com.usertesting.tuan;

/**
 * Created by tuan on 3/20/2016.
 */
public class Utils {

    /**
     * Capitalize the first letter of the given string
     * @param input the string to be capitalized
     * @return the capitalized string
     */
    public static String capFirstLetter(String input) {
        if(input == null || input.length() == 0){
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
}
