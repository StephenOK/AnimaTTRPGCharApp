package com.example.animabuilder.character_creation.attributes.modules

import com.example.animabuilder.R
import java.io.Serializable

class StyleInstances: Serializable {
    val battoJutsu = StyleModule(
        "Batto Jutsu/Iai Jutsu",
        "This skill permits a character to unsheathe his weapon with perfect ease. " +
                "The character can unsheathe his weapon without applying the -25 penalty to " +
                "the Attack or Block abilities. It has no effect for two-handed weapons.",
        30
    )

    val areaAttack = StyleModule(
        "Area Attack",
        "The character specializes in broad maneuvers that can take out various " +
                "enemies with greater ease. This reduces the penalty for an Area Attack " +
                "maneuver by half. Therefore a character applies a -25 to his attack ability " +
                "when using this attack.",
        40
    )

    val precisionAttack = StyleModule(
        "Precision Attack",
        "The character has a marked ability to put his adversary in a Menace " +
                "Position. This reduces the penalty for a Put at Weapon's Point maneuver by " +
                "half. Therefore a character applies -50 to his attack ability when using this attack.",
        50
    )

    val disarmingAttack = StyleModule(
        "Disarming Attack",
        "A character with this ability has specialized in disarming his opponents. " +
                "This reduces the penalty for a Disarm maneuver to -20.",
        40
    )

    val allStyles = listOf(battoJutsu, areaAttack, precisionAttack, disarmingAttack)

    fun getStyle(input: String): StyleModule?{
        allStyles.forEach{
            if(it.name == input)
                return it
        }

        return null
    }
}