package com.example.animabuilder.CharacterCreation;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.lang.Math;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.example.animabuilder.Armor;
import com.example.animabuilder.CharacterCreation.Attributes.CharClass;
import com.example.animabuilder.CharacterCreation.Attributes.CharRace;
import com.example.animabuilder.CharacterCreation.Attributes.ClassName;
import com.example.animabuilder.CharacterCreation.Attributes.RaceName;
import com.example.animabuilder.Weapon;

public class BaseCharacter implements Serializable {
    String charName;

    public void setCharName(String input){
        charName = input;
    }

    public String getCharName(){
        return charName;
    }



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

    public Consumer<Integer> setSTR = strVal -> {
        STR = strVal;

        //change strength modifier number
        modSTR = getModVal(STR);

        //change wear armor ability
        setWearArmor(modSTR);

        //change appropriate secondary abilities
    };

    public Consumer<Integer> setDEX = dexVal ->{
        DEX = dexVal;

        modDEX = getModVal(DEX);
    };

    public Consumer<Integer> setAGI = agiVal -> {
        AGI = agiVal;

        modAGI = getModVal(AGI);
    };

    public Consumer<Integer> setCON = conVal -> {
        CON = conVal;

        modCON = getModVal(CON);
    };

    public Consumer<Integer> setINT = intVal -> {
        INT = intVal;

        modINT = getModVal(INT);
    };

    public Consumer<Integer> setPOW = powVal -> {
        POW = powVal;

        modPOW = getModVal(POW);
    };

    public Consumer<Integer> setWP = wpVal -> {
        WP = wpVal;

        modWP = getModVal(WP);
    };

    public Consumer<Integer> setPER = perVal -> {
        PER = perVal;
        modPER = getModVal(PER);
    };

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

    public Supplier<Integer> getModSTR = () -> modSTR;
    public Supplier<Integer> getModDEX = () -> modDEX;
    public Supplier<Integer> getModAGI = () -> modAGI;
    public Supplier<Integer> getModCON = () -> modCON;
    public Supplier<Integer> getModINT = () -> modINT;
    public Supplier<Integer> getModPOW = () -> modPOW;
    public Supplier<Integer> getModWP = () -> modWP;
    public Supplier<Integer> getModPER = () -> modPER;

    CharClass ownClass;
    CharRace ownRace;

    public CharClass getOwnClass(){
        return ownClass;
    }

    public void setOwnClass(ClassName classIn){
        ownClass = new CharClass(classIn);
        adjustMaxValues();
    }
    public void setOwnClass(String className){
        ownClass = new CharClass(ClassName.fromString(className));
        adjustMaxValues();
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






    //list of secondary abilities

    //athletics
    int acrobatics;
    int athletics;
    int climb;
    int jump;
    int ride;
    int swim;

    //creative
    int art;
    int dance;
    int forging;
    int music;
    int sleightHand;

    //perceptive
    int notice;
    int search;
    int track;

    //social
    int intimidate;
    int leadership;
    int persuasion;
    int style;

    //subterfuge
    int disguise;
    int hide;
    int lockPick;
    int poisons;
    int theft;
    int stealth;
    int trapLore;

    //intellectual
    int animals;
    int appraise;
    int herbalLore;
    int history;
    int memorize;
    int magicAppraise;
    int medic;
    int navigate;
    int occult;
    int sciences;

    //vigor
    int composure;
    int strengthFeat;
    int resistPain;




    Armor equippedPiece;
    Weapon equippedWeapon;






    public BaseCharacter(){
        setOwnClass(ClassName.freelancer);
        setOwnRace(RaceName.human);

        setCharName("");
        setLvl(0);
    }

    public byte[] convertCharacter(){
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();

        return byteArray.toByteArray();
    }
}
