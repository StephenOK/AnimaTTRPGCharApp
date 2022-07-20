package com.example.animabuilder.CharacterCreation.Attributes;

public class CharRace {
    RaceName heldRace;
    public RaceName getHeldRace(){
        return heldRace;
    }


    int raceIndex;

    public int getRaceIndex() {
        return raceIndex;
    }

    public CharRace(RaceName raceType){
        heldRace = raceType;

        switch(raceType){
            case sylvain:
                raceIndex = 1;
                break;

            case jayan:
                raceIndex = 2;
                break;

            case danjayni:
                raceIndex = 3;
                break;

            case ebudan:
                raceIndex = 4;
                break;

            case daimah:
                raceIndex = 5;
                break;

            case dukzarist:
                raceIndex = 6;
                break;

            default:
                raceIndex = 0;
        }
    }
}
