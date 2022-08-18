package com.example.animabuilder.CharacterCreation;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.animabuilder.CharacterCreation.Attributes.SecondaryCharacteristic;
import com.example.animabuilder.R;

public class SecondaryInput implements TextWatcher {
    TableRow workingRow;
    Context location;
    SecondaryCharacteristic workingStat;

    public SecondaryInput(TableRow workingRow, Context location, SecondaryCharacteristic workingStat){
        this.workingRow = workingRow;
        this.location = location;
        this.workingStat = workingStat;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if(!(((EditText)workingRow.getChildAt(1)).getText().toString().equals(""))) {
            workingStat.setPointsApplied(Integer.parseInt(((EditText) workingRow.getChildAt(1)).getText().toString()));
            ((TextView) workingRow.getChildAt(5)).setText(location.getString(R.string.intItem, workingStat.getTotal()));
        }
        else{
            workingStat.setPointsApplied(0);
            ((TextView) workingRow.getChildAt(5)).setText(location.getString(R.string.intItem, workingStat.getTotal()));
        }
    }
}
