package com.example.animabuilder.CharacterCreation.Attributes;

public enum ClassName {
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
            case "Warrior": return ClassName.warrior;
            case "Acrobatic Warrior": return ClassName.acroWarrior;
            case "Paladin": return ClassName.paladin;
            case "Dark Paladin": return ClassName.darkPaladin;
            case "Weaponsmaster": return ClassName.weaponMaster;
            case "Technician": return ClassName.technician;
            case "Tao": return ClassName.tao;
            case "Ranger": return ClassName.ranger;
            case "Shadow": return ClassName.shadow;
            case "Thief": return ClassName.thief;
            case "Assassin": return ClassName.assassin;
            case "Wizard": return ClassName.wizard;
            case "Warlock": return ClassName.warlock;
            case "Illusionist": return ClassName.illusionist;
            case "Wizard Mentalist": return ClassName.wizMentalist;
            case "Summoner": return ClassName.summoner;
            case "Warrior Summoner": return ClassName.warSummoner;
            case "Mentalist": return ClassName.mentalist;
            case "Warrior Mentalist": return ClassName.warMentalist;
            case "Freelancer":
            default: return ClassName.freelancer;
        }
    }
}
