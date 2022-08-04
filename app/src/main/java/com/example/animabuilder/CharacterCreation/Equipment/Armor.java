package com.example.animabuilder.CharacterCreation.Equipment;

import java.io.Serializable;

public class Armor implements Serializable {
    int strReq;
    int checkPenalty;

    public int getStrReq(){
        return strReq;
    }
}
