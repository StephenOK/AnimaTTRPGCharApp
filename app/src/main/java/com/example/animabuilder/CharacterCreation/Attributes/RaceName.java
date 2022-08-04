package com.example.animabuilder.CharacterCreation.Attributes;

import java.io.Serializable;

public enum RaceName implements Serializable {
    human,
    sylvain,
    jayan,
    danjayni,
    ebudan,
    daimah,
    dukzarist;

    public static RaceName fromString(String input){
        switch(input){
            case "sylvain":
            case "Sylvain": return sylvain;

            case "jayan":
            case "Jayan": return jayan;

            case "danjayni":
            case "D\'Anjayni": return danjayni;

            case "ebudan":
            case "Ebudan": return ebudan;

            case "daimah":
            case "Daimah": return daimah;

            case "dukzarist":
            case "Duk\'zarist": return dukzarist;

            case "human":
            case "Human":
            default: return human;
        }
    }
}
