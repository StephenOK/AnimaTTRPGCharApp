package com.paetus.animaCharCreator.character_creation.attributes.modules

/**
 * Record of style modules a character may acquire.
 */
class StyleInstances{
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

    val magAsAttack = StyleModule(
        "Magic Projection as an Attack",
        "This represents a character's joining of his combat knowledge with the ability " +
                "to direct spells at an opponent. For this character, spells act like real " +
                "weapons that he can project like a true martial ability. This allows the " +
                "character to use his attack ability when undertaking an offensive Magic " +
                "Projection. Only the base ability score is used, not the points for innate " +
                "improvement that come from combat classes. This module cannot be used to launch " +
                "passive or defensive spells.",
        75
    )

    val magAsDefense = StyleModule(
        "Magic Projection as a Defense",
        "The character uses his defensive knowledge to project his passive and shielding " +
                "spells as though they were weapons or armor that intercept the attacks of his " +
                "enemies. This permits a character's Defense ability to be used as a defensive " +
                "Magic Projection. Only the base ability is used, not the points for innate " +
                "improvement that come from particular classes. This module cannot be used to " +
                "direct offensive spells.",
        75
    )

    val psyProjection = StyleModule(
        "Psychic Projection Module",
        "The character uses his Combat Abilities to project his mental powers as if " +
                "they were actual weapons. This gives the character the ability to use Combat " +
                "Abilities as a Psychic Projection. The offensive is used to attack, and the " +
                "defensive to raise shields. Only the base ability is used, not the points for " +
                "innate improvement that come from particular classes.",
        100
    )

    val allStyles = listOf(battoJutsu, areaAttack, precisionAttack, disarmingAttack, magAsAttack,
        magAsDefense, psyProjection)

    val exceptions = listOf(magAsAttack, magAsDefense, psyProjection)

    /**
     * Retrieves the desired style module.
     *
     * @param input name of the style to find
     */
    fun getStyle(input: String): StyleModule?{
        //for all styles on record
        allStyles.forEach{
            //return any positive match
            if(it.name == input)
                return it
        }

        //notify of failed find
        return null
    }
}