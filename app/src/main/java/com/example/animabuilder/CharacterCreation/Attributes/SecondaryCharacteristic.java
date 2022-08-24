package com.example.animabuilder.CharacterCreation.Attributes;

import com.example.animabuilder.CharacterCreation.SerialOutputStream;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

public class SecondaryCharacteristic implements Serializable {
    ByteArrayOutputStream byteArray;

    int modVal;
    int pointsApplied;
    int devPerPoint;
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

    public int getDevPerPoint(){
        return devPerPoint;
    }

    public void setDevPerPoint(int perPoint){
        devPerPoint = perPoint;
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

    public int getTotal(){
        return total;
    }

    public void load(BufferedReader fileReader, SecondaryList caller) throws IOException {
        setPointsApplied(Integer.parseInt(fileReader.readLine()));

        String loadBoolean = fileReader.readLine();
        if(loadBoolean.equals("true")) {
            setBonusApplied(true);
            caller.incrementNat(true);
        }
        else
            setBonusApplied(false);
    }

    public void write(SerialOutputStream byteArray){
        byteArray.write((pointsApplied + "\n").getBytes(StandardCharsets.UTF_8), 0, (pointsApplied + "\n").getBytes(StandardCharsets.UTF_8).length);

        String booleanIn;
        if(bonusApplied)
            booleanIn = "true";
        else
            booleanIn = "false";

        byteArray.write((booleanIn + "\n").getBytes(StandardCharsets.UTF_8), 0, (booleanIn + "\n").getBytes(StandardCharsets.UTF_8).length);
    }
}
