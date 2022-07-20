package com.example.animabuilder;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.example.animabuilder.CharacterCreation.BaseCharacter;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class CharacteristicInput implements TextWatcher {

    EditText userInput;
    TextView modOutput;
    Consumer<Integer> valSetter;
    Supplier<Integer> valGetter;
    Context pageOf;

    public CharacteristicInput(EditText userInput, TextView modOutput, Consumer<Integer> valSetter,
                               Supplier<Integer> valGetter, Context pageOf){
        this.userInput = userInput;
        this.modOutput = modOutput;
        this.valSetter = valSetter;
        this.valGetter = valGetter;
        this.pageOf = pageOf;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        applyModVal();
    }

    /**
     * Sets the character's indicated value to the input value and alters the indicated mod display
     * to the corresponding value
     */
    private void applyModVal(){

        //if integer value given
        if(!userInput.getText().toString().equals("")){
            //chanage the indicated value in the character page
            valSetter.accept(Integer.parseInt(userInput.getText().toString()));

            //get the indicated value from the character page
            modOutput.setText(pageOf.getString(R.string.intItem, valGetter.get()));
        }
        //clear mod if text is empty
        else
            modOutput.setText("");
    }
}
