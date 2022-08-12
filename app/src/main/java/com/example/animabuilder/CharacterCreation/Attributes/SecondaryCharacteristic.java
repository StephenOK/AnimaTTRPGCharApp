package com.example.animabuilder.CharacterCreation.Attributes;

import java.io.Serializable;

public class SecondaryCharacteristic implements Serializable {
    int modVal;
    int pointsApplied;
    int pointsFromClass;
    boolean bonusApplied;
    int total;

    public int getModVal() {
        return modVal;
    }

    public void setModVal(int value){
        modVal = value;
        refreshTotal();
    }

    public int getPointsApplied() {
        return pointsApplied;
    }

    public void setPointsApplied(int points){
        pointsApplied = points;
        refreshTotal();
    }

    public int getPointsFromClass() {
        return pointsFromClass;
    }

    public void setPointsFromClass(int points){
        pointsFromClass = points;
        refreshTotal();
    }

    public boolean isBonusApplied() {
        return bonusApplied;
    }

    public void setBonusApplied(boolean bonus){
        bonusApplied = bonus;
        refreshTotal();
    }

    public SecondaryCharacteristic(){
        modVal = 0;
        pointsApplied = 0;
        pointsFromClass = 0;
        bonusApplied = false;

        refreshTotal();
    }

    private void refreshTotal(){
        total = modVal + pointsApplied + pointsFromClass;
        if(pointsApplied == 0)
            total -= 30;
        else if(bonusApplied)
            total += 5;
    }
}
