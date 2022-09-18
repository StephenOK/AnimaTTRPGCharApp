package com.example.animabuilder.character_creation.attributes.class_objects

import java.io.Serializable

/**
 * Names of classes given in the system
 */

enum class ClassName : Serializable {
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

    companion object {
        /**
         * Converts string objects into their corresponding class
         */
        @JvmStatic
        fun fromString(input: String?): ClassName {
            return when (input) {
                "warrior", "Warrior" -> warrior
                "acroWarrior", "Acrobatic Warrior" -> acroWarrior
                "paladin", "Paladin" -> paladin
                "darkPaladin", "Dark Paladin" -> darkPaladin
                "weaponMaster", "Weaponsmaster" -> weaponMaster
                "technician", "Technician" -> technician
                "tao", "Tao" -> tao
                "ranger", "Ranger" -> ranger
                "shadow", "Shadow" -> shadow
                "thief", "Thief" -> thief
                "assassin", "Assassin" -> assassin
                "wizard", "Wizard" -> wizard
                "warlock", "Warlock" -> warlock
                "illusionist", "Illusionist" -> illusionist
                "wizMentalist", "Wizard Mentalist" -> wizMentalist
                "summoner", "Summoner" -> summoner
                "warSummoner", "Warrior Summoner" -> warSummoner
                "mentalist", "Mentalist" -> mentalist
                "warMentalist", "Warrior Mentalist" -> warMentalist
                "freelancer", "Freelancer" -> freelancer
                else -> freelancer
            }
        }

        /**
         * Converts integer objects into their corresponding class
         */
        fun fromInt(classInt: Int?): ClassName {
            return when (classInt){
                1 -> warrior
                2 -> acroWarrior
                3 -> paladin
                4 -> darkPaladin
                5 -> weaponMaster
                6 -> technician
                7 -> tao
                8 -> ranger
                9 -> shadow
                10 -> thief
                11 -> assassin
                12 -> wizard
                13 -> warlock
                14 -> illusionist
                15 -> wizMentalist
                16 -> summoner
                17 -> warSummoner
                18 -> mentalist
                19 -> warMentalist
                0 -> freelancer
                else -> freelancer
            }
        }
    }
}