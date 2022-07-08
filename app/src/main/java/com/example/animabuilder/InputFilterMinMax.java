package com.example.animabuilder;

import android.text.InputFilter;
import android.text.Spanned;

public class InputFilterMinMax implements InputFilter {
    private int min;
    private int max;

    public InputFilterMinMax(int minIn, int maxIn){
        min = minIn;
        max = maxIn;
    }

    public InputFilterMinMax(String minIn, String maxIn){
        min = Integer.parseInt(minIn);
        max = Integer.parseInt(maxIn);
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dStart, int dEnd){
        try{
            String newVal = dest.toString().substring(0, dStart)
                    + dest.toString().substring(dEnd, dest.toString().length());

            newVal = newVal.substring(0, dStart) + source.toString() + newVal.substring(dStart, newVal.length());

            int input = Integer.parseInt(newVal);

            if(isInRange(min, max, input))
                return null;
        }
        catch (NumberFormatException nfe){

        }

        return "";
    }

    private boolean isInRange(int min, int max, int input){
        return input >= min && input <= max;
    }
}
