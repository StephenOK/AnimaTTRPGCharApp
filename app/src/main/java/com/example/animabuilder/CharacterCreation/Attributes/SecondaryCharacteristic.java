package com.example.animabuilder.CharacterCreation.Attributes;

import java.io.Serializable;

public class SecondaryCharacteristic implements Serializable {
    int modVal;
    int pointsApplied;
    int pointsFromClass;
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

    public void setPointsApplied(int points, int lvl){
        pointsApplied = points * lvl;
        refreshTotal();
    }

    public int getPointsFromClass() {
        return pointsFromClass;
    }

    public void setPointsFromClass(int points){
        pointsFromClass = points;
        refreshTotal();
    }

    public SecondaryCharacteristic(int modVal, int pointsApplied, int pointsPerLevel, int lvl){
        this.modVal = modVal;
        this.pointsApplied = pointsApplied;
        pointsFromClass = pointsPerLevel * lvl;

        refreshTotal();
    }

    private void refreshTotal(){
        total = modVal + pointsApplied + pointsFromClass;
    }
}
