package com.example.animabuilder.CharacterCreation.Attributes;

import java.io.Serializable;

public enum ClassName implements Serializable {
    warrior,
    acroWarrior,
    paladin,
    darkPaladin,
    weaponMaster,
    technician,
    tao,
    ranger,
    shadow,
    thief,
    assassin,
    wizard,
    warlock,
    illusionist,
    wizMentalist,
    summoner,
    warSummoner,
    mentalist,
    warMentalist,
    freelancer;

    public static ClassName fromString(String input){
        switch(input){
            case "warrior":
            case "Warrior": return ClassName.warrior;

            case "acroWarrior":
            case "Acrobatic Warrior": return ClassName.acroWarrior;

            case "paladin":
            case "Paladin": return ClassName.paladin;

            case "darkPaladin":
            case "Dark Paladin": return ClassName.darkPaladin;

            case "weaponMaster":
            case "Weaponsmaster": return ClassName.weaponMaster;

            case "technician":
            case "Technician": return ClassName.technician;

            case "tao":
            case "Tao": return ClassName.tao;

            case "ranger":
            case "Ranger": return ClassName.ranger;

            case "shadow":
            case "Shadow": return ClassName.shadow;

            case "thief":
            case "Thief": return ClassName.thief;

            case "assassin":
            case "Assassin": return ClassName.assassin;

            case "wizard":
            case "Wizard": return ClassName.wizard;

            case "warlock":
            case "Warlock": return ClassName.warlock;

            case "illusionist":
            case "Illusionist": return ClassName.illusionist;

            case "wizMentalist":
            case "Wizard Mentalist": return ClassName.wizMentalist;

            case "summoner":
            case "Summoner": return ClassName.summoner;

            case "warSummoner":
            case "Warrior Summoner": return ClassName.warSummoner;

            case "mentalist":
            case "Mentalist": return ClassName.mentalist;

            case "warMentalist":
            case "Warrior Mentalist": return ClassName.warMentalist;

            case "freelancer":
            case "Freelancer":
            default: return ClassName.freelancer;
        }
    }
}
