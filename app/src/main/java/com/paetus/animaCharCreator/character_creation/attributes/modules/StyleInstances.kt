package com.paetus.animaCharCreator.character_creation.attributes.modules

import com.paetus.animaCharCreator.R

/**
 * Record of style modules a character may acquire.
 */
class StyleInstances{
    private val battoJutsu = StyleModule(
        saveName = "battoJutsu",
        name = R.string.battoJutsu,
        description = R.string.battoJutsuDesc,
        cost = 30
    )

    private val areaAttack = StyleModule(
        saveName = "areaAttack",
        name = R.string.areaAttack,
        description = R.string.areaAttackDesc,
        cost = 40
    )

    private val precisionAttack = StyleModule(
        saveName = "preciseAttack",
        name = R.string.preciseAttack,
        description = R.string.preciseAttackDesc,
        cost = 50
    )

    private val disarmingAttack = StyleModule(
        saveName = "disarm",
        name = R.string.disarm,
        description = R.string.disarmDesc,
        cost = 40
    )

    val magAsAttack = StyleModule(
        saveName = "magAsAttack",
        name = R.string.magAsAttack,
        description = R.string.magAsAttackDesc,
        cost = 75
    )

    val magAsDefense = StyleModule(
        saveName = "magAsDefense",
        name = R.string.magAsDefense,
        description = R.string.magAsDefenseDesc,
        cost = 75
    )

    val psyProjection = StyleModule(
        saveName = "psyProj",
        name = R.string.psyProj,
        description = R.string.psyProjDesc,
        cost = 100
    )

    val allStyles = listOf(battoJutsu, areaAttack, precisionAttack, disarmingAttack, magAsAttack,
        magAsDefense, psyProjection)

    val exceptions = listOf(magAsAttack, magAsDefense, psyProjection)

    /**
     * Retrieves the desired style module.
     *
     * @param styleName name of the style to find
     * @return the style found by the search, if available
     */
    fun getStyle(styleName: String): StyleModule?{
        //for all styles on record
        allStyles.forEach{style ->
            //return any positive match
            if(style.saveName == styleName)
                return style
        }

        //notify of failed find
        return null
    }
}