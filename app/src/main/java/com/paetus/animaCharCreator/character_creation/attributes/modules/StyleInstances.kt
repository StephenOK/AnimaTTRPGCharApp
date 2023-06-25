package com.paetus.animaCharCreator.character_creation.attributes.modules

import com.paetus.animaCharCreator.R

/**
 * Record of style modules a character may acquire.
 */
class StyleInstances{
    val battoJutsu = StyleModule(
        "battoJutsu",
        R.string.battoJutsu,
        R.string.battoJutsuDesc,
        30
    )

    val areaAttack = StyleModule(
        "areaAttack",
        R.string.areaAttack,
        R.string.areaAttackDesc,
        40
    )

    val precisionAttack = StyleModule(
        "preciseAttack",
        R.string.preciseAttack,
        R.string.preciseAttackDesc,
        50
    )

    val disarmingAttack = StyleModule(
        "disarm",
        R.string.disarm,
        R.string.disarmDesc,
        40
    )

    val magAsAttack = StyleModule(
        "magAsAttack",
        R.string.magAsAttack,
        R.string.magAsAttackDesc,
        75
    )

    val magAsDefense = StyleModule(
        "magAsDefense",
        R.string.magAsDefense,
        R.string.magAsDefenseDesc,
        75
    )

    val psyProjection = StyleModule(
        "psyProj",
        R.string.psyProj,
        R.string.psyProjDesc,
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
            if(it.saveName == input)
                return it
        }

        //notify of failed find
        return null
    }
}