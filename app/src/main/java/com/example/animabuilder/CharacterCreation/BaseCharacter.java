package com.example.animabuilder.CharacterCreation;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.Math;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.example.animabuilder.CharacterCreation.Attributes.*;
import com.example.animabuilder.CharacterCreation.Equipment.*;

public class BaseCharacter implements Serializable {
    String charName;

    ByteArrayOutputStream byteArray;

    public void setCharName(String input){
        charName = input;
    }

    public String getCharName(){
        return charName;
    }

    //list of secondary abilities
    SecondaryList secondaryList;


    //character's level
    int lvl;

    //character creation and development points
    int createPT;
    int devPT;
    int spentTotal;

    public int getDevPT() {return devPT;}
    public int getSpentTotal() {return spentTotal;}

    //maximum point allotments to combat, magic, and psychic abilities
    int maxCombatDP;
    double percCombatDP;
    int ptInCombat;

    public int getMaxCombatDP(){return maxCombatDP;}
    public int getPtInCombat(){return ptInCombat;}

    int maxMagDP;
    double percMagDP;
    int ptInMag;

    public int getMaxMagDP(){return maxMagDP;}
    public int getPtInMag(){return ptInMag;}

    int maxPsyDP;
    double percPsyDP;
    int ptInPsy;

    public int getMaxPsyDP(){return maxPsyDP;}
    public int getPtInPsy(){return ptInPsy;}

    private void dpAllotmentCalc(){
        maxCombatDP = (int)(devPT * percCombatDP);
        maxMagDP = (int)(devPT * percMagDP);
        maxPsyDP = (int)(devPT * percPsyDP);
    }

    public void setLvl(int levNum){
        //set new level number
        lvl = levNum;

        //determine development point count
        devPT = 500 + (lvl * 100);

        //recalculate maximum DP allotments
        dpAllotmentCalc();

        secondaryList.levelUpdate(lvl, ownClass);
    }

    public int getLvl(){
        return lvl;
    }






    //initialize stats for the character
    //manipulable by user
    int STR;
    int DEX;
    int AGI;
    int CON;
    int INT;
    int POW;
    int WP;
    int PER;

    //initialize stat modifiers
    int modSTR;
    int modDEX;
    int modAGI;
    int modCON;
    int modINT;
    int modPOW;
    int modWP;
    int modPER;

    public SerialConsumer<Integer> setSTR = strVal -> {
        STR = strVal;

        //change strength modifier number
        modSTR = getModVal(STR);

        //change wear armor ability
        setWearArmor(modSTR);

        secondaryList.updateSTR(modSTR);
    };

    public SerialConsumer<Integer> setDEX = dexVal ->{
        DEX = dexVal;

        modDEX = getModVal(DEX);

        secondaryList.updateDEX(modDEX);
    };

    public SerialConsumer<Integer> setAGI = agiVal -> {
        AGI = agiVal;

        modAGI = getModVal(AGI);

        secondaryList.updateAGI(modAGI);
    };

    public SerialConsumer<Integer> setCON = conVal -> {
        CON = conVal;

        modCON = getModVal(CON);
    };

    public SerialConsumer<Integer> setINT = intVal -> {
        INT = intVal;

        modINT = getModVal(INT);

        secondaryList.updateINT(modINT);
    };

    public SerialConsumer<Integer> setPOW = powVal -> {
        POW = powVal;

        modPOW = getModVal(POW);

        secondaryList.updatePOW(modPOW);
    };

    public SerialConsumer<Integer> setWP = wpVal -> {
        WP = wpVal;

        modWP = getModVal(WP);

        secondaryList.updateWP(modWP);
    };

    public SerialConsumer<Integer> setPER = perVal -> {
        PER = perVal;
        modPER = getModVal(PER);

        secondaryList.updatePER(modPER);
    };

    public int getSTR() {
        return STR;
    }

    public int getDEX(){
        return DEX;
    }

    public int getAGI(){
        return AGI;
    }

    public int getCON() {
        return CON;
    }

    public int getINT() {
        return INT;
    }

    public int getPOW() {
        return POW;
    }

    public int getWP() {
        return WP;
    }

    public int getPER() {
        return PER;
    }

    private int getModVal(int statVal){
        int output;

        if(statVal <= 5){
            switch(statVal){
                case 1:
                    output = -30;
                    break;
                case 2:
                    output = -20;
                    break;
                case 3:
                    output = -10;
                    break;
                case 4:
                    output = -5;
                    break;
                default:
                    output = 0;
            }
        }
        else{
            output = (int)((15 * ((statVal/5) - 1)) + (5 * Math.ceil((statVal % 5) / 2.0)));
        }

        return output;
    }

    public SerialSupplier<Integer> getModSTR = () -> modSTR;
    public SerialSupplier<Integer> getModDEX = () -> modDEX;
    public SerialSupplier<Integer> getModAGI = () -> modAGI;
    public SerialSupplier<Integer> getModCON = () -> modCON;
    public SerialSupplier<Integer> getModINT = () -> modINT;
    public SerialSupplier<Integer> getModPOW = () -> modPOW;
    public SerialSupplier<Integer> getModWP = () -> modWP;
    public SerialSupplier<Integer> getModPER = () -> modPER;

    CharClass ownClass;
    CharRace ownRace;

    public CharClass getOwnClass(){
        return ownClass;
    }

    public void setOwnClass(ClassName classIn){
        ownClass = new CharClass(classIn);
        adjustMaxValues();
        secondaryList.classUpdate(ownClass);
    }
    public void setOwnClass(String className){
        ownClass = new CharClass(ClassName.fromString(className));
        adjustMaxValues();
        secondaryList.classUpdate(ownClass);
    }

    private void adjustMaxValues(){
        percCombatDP = ownClass.getCombatMax();
        percMagDP = ownClass.getMagMax();
        percPsyDP = ownClass.getPsyMax();

        dpAllotmentCalc();
    }

    public CharRace getOwnRace() { return ownRace; }

    public void setOwnRace(RaceName raceIn){
        ownRace = new CharRace(raceIn);
    }
    public void setOwnRace(String raceName){
        ownRace = new CharRace(RaceName.fromString(raceName));
    }

    private void updateClassValues(){

    }






    //maximum fatigue value
    int maxFatigue;

    //character resistance stats
    int resistPhys;
    int resistDisease;
    int resistVen;
    int resistMag;
    int resistPsy;

    //character's maximum hp
    int lifeMax;

    //character's attack stat
    int attack;
    int pointInAttack;

    //character's block stat
    int block;
    int pointInBlock;

    //character's dodge stat
    int dodge;
    int pointInDodge;

    //character's wear armor stat
    int wearArmor;
    int pointInWear;

    private void setWearArmor(int waValue){
        wearArmor = waValue;

        checkArmor(wearArmor);
    }

    private boolean checkArmor(int strCheck){
        return equippedPiece == null || equippedPiece.getStrReq() < strCheck;
    }

    int initiative;

    int maxKi;
    int maxPsi;
    int maxZeon;



    Armor equippedPiece;
    Weapon equippedWeapon;






    public BaseCharacter(){
        secondaryList = new SecondaryList();

        setOwnClass(ClassName.freelancer);
        setOwnRace(RaceName.human);

        setCharName("");
        setLvl(0);

        setSTR.accept(5);
        setDEX.accept(5);
        setAGI.accept(5);
        setCON.accept(5);
        setINT.accept(5);
        setPOW.accept(5);
        setWP.accept(5);
        setPER.accept(5);
    }

    public BaseCharacter(File fileInput) throws IOException {
        FileInputStream restoreChar = new FileInputStream(fileInput);
        InputStreamReader readChar = new InputStreamReader(restoreChar, StandardCharsets.UTF_8);
        BufferedReader fileReader = new BufferedReader(readChar);

        secondaryList = new SecondaryList();

        setCharName(fileReader.readLine());
        setOwnClass(fileReader.readLine());
        setOwnRace(fileReader.readLine());

        setLvl(Integer.parseInt(fileReader.readLine()));

        setSTR.accept(Integer.parseInt(fileReader.readLine()));
        setDEX.accept(Integer.parseInt(fileReader.readLine()));
        setAGI.accept(Integer.parseInt(fileReader.readLine()));
        setCON.accept(Integer.parseInt(fileReader.readLine()));
        setINT.accept(Integer.parseInt(fileReader.readLine()));
        setPOW.accept(Integer.parseInt(fileReader.readLine()));
        setWP.accept(Integer.parseInt(fileReader.readLine()));
        setPER.accept(Integer.parseInt(fileReader.readLine()));

        restoreChar.close();
    }

    public byte[] getBytes(){
        byteArray = new ByteArrayOutputStream();

        addNewData(charName);
        addNewData(ownClass.getHeldClass().name());
        addNewData(ownRace.getHeldRace().name());

        addNewData(lvl);

        addNewData(STR);
        addNewData(DEX);
        addNewData(AGI);
        addNewData(CON);
        addNewData(INT);
        addNewData(POW);
        addNewData(WP);
        addNewData(PER);

        return byteArray.toByteArray();
    }

    private void addNewData (String toAdd){
        byteArray.write((toAdd + "\n").getBytes(StandardCharsets.UTF_8), 0, (toAdd + "\n").getBytes(StandardCharsets.UTF_8).length);
    }

    private void addNewData(int toAdd){
        byteArray.write((toAdd + "\n").getBytes(StandardCharsets.UTF_8), 0, (toAdd + "\n").getBytes(StandardCharsets.UTF_8).length);
    }
}
