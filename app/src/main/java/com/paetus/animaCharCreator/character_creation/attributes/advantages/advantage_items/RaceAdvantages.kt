package com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_items

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_types.Advantage
import com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_types.RacialAdvantage

/**
 * Lists of advantages for every race available to a character.
 *
 * @param charInstance object holding a character's stats
 */
class RaceAdvantages(private val charInstance: BaseCharacter){
    private val exceptionalResistancesSylvain = RacialAdvantage(
        R.string.sylvainResistances,
        R.string.sylvResDesc,
        {_, _ ->
            charInstance.combat.physicalRes.setSpecial(5)
            charInstance.combat.diseaseRes.setSpecial(20)
            charInstance.combat.venomRes.setSpecial(5)
            charInstance.combat.magicRes.setSpecial(10)
            charInstance.combat.psychicRes.setSpecial(10)

            charInstance.advantageRecord.removeAdvantage(charInstance.advantageRecord.commonAdvantages.sickly)
            charInstance.advantageRecord.removeAdvantage(charInstance.advantageRecord.commonAdvantages.seriousIllness)
            charInstance.advantageRecord.removeAdvantage(charInstance.advantageRecord.commonAdvantages.magicSusceptibility)
        },
        {_, _ ->
            charInstance.combat.physicalRes.setSpecial(-5)
            charInstance.combat.diseaseRes.setSpecial(-20)
            charInstance.combat.venomRes.setSpecial(-5)
            charInstance.combat.magicRes.setSpecial(-10)
            charInstance.combat.psychicRes.setSpecial(-10)
        }
    )

    private val inclinationLight = RacialAdvantage(
        R.string.lightInclination,
        R.string.lightInclineDesc,
        {_, _ ->
            val reference = charInstance.advantageRecord.magicAdvantages.elementalCompatibility

            val dummyAdvantage = Advantage(
                reference.saveTag,
                reference.name,
                reference.description,
                reference.effect,
                reference.restriction,
                reference.special,
                null,
                1,
                reference.cost,
                reference.pickedCost,
                reference.onTake,
                reference.onRemove
            )

            charInstance.advantageRecord.removeAdvantage(dummyAdvantage)
        },
        null
    )

    private val quickHealingSylvain = RacialAdvantage(
        R.string.sylvainHealing,
        R.string.sylvainHealingDesc,
        {_, _ ->
            charInstance.combat.specRegen.value += 1
            charInstance.combat.updateRegeneration()
        },
        {_, _ ->
            charInstance.combat.specRegen.value -= 1
            charInstance.combat.updateRegeneration()
        }
    )

    private val senseLightDarkSylvain = RacialAdvantage(
        R.string.sylvainSense,
        R.string.lightSenseDesc,
        null,
        null
    )

    private val immortalSoulSylvain = RacialAdvantage(
        R.string.sylvainSoul,
        R.string.sylvainSoulDesc,
        null,
        null
    )

    private val giant = RacialAdvantage(
        R.string.giant,
        R.string.giantDesc,
        {_, _ ->
            val uncommonSizeHeld = charInstance.advantageRecord.getAdvantage("uncommonSize")
            if(uncommonSizeHeld != null && uncommonSizeHeld.picked!! < 5)
                charInstance.advantageRecord.removeAdvantage(uncommonSizeHeld)

            charInstance.changeSize(6)
        },
        {_, _ ->
            charInstance.changeSize(3)
        }
    )

    private val withstandFatigue = RacialAdvantage(
        R.string.jayanFatigue,
        R.string.standFatigueDesc,
        {_, _ ->
            charInstance.combat.specFatigue.value += 1
            charInstance.combat.updateFatigue()
        },
        {_, _ ->
            charInstance.combat.specFatigue.value -= 1
            charInstance.combat.updateFatigue()
        }
    )

    private val damageResistance = RacialAdvantage(
        R.string.resDamage,
        R.string.damageResDesc,
        {_, _ -> charInstance.combat.physicalRes.setSpecial(15)},
        {_, _ -> charInstance.combat.physicalRes.setSpecial(-15)}
    )

    private val uncommonStrength = RacialAdvantage(
        R.string.uncommonStr,
        R.string.uncommonStrDesc,
        {_, _ ->
            charInstance.primaryList.str.setBonus(1)
            val reference = charInstance.advantageRecord.commonAdvantages.deductCharacteristic

            val dummyAdvantage = Advantage(
                reference.saveTag,
                reference.name,
                reference.description,
                reference.effect,
                reference.restriction,
                reference.special,
                null,
                0,
                reference.cost,
                reference.pickedCost,
                reference.onTake,
                reference.onRemove
            )

            charInstance.advantageRecord.removeAdvantage(dummyAdvantage)
        },
        {_, _ -> charInstance.primaryList.str.setBonus(-1)}
    )

    private val spiritualVision = RacialAdvantage(
        R.string.spiritualVision,
        R.string.spiritVisionDesc,
        null,
        null
    )

    private val magicSusceptibility = RacialAdvantage(
        R.string.jayanMagSusceptibility,
        R.string.jayanMagSusDesc,
        {_, _ -> charInstance.combat.magicRes.setSpecial(-10)},
        {_, _ -> charInstance.combat.magicRes.setSpecial(10)}
    )

    private val immortalSoulJayan = RacialAdvantage(
        R.string.jayanSoul,
        R.string.jayanSoulDesc,
        null,
        null
    )

    private val withoutTrace = RacialAdvantage(
        R.string.withoutTrace,
        R.string.withoutTraceDesc,
        null,
        null
    )

    private val forgetfulness = RacialAdvantage(
        R.string.forgetfulness,
        R.string.forgetDesc,
        null,
        null
    )

    private val commonAppearance = RacialAdvantage(
        R.string.commonAppearance,
        R.string.commonAppearDesc,
        {_, _ -> if(charInstance.appearance.value !in 3..7) charInstance.setAppearance(5)},
        null
    )

    private val undetectability = RacialAdvantage(
        R.string.undetectability,
        R.string.undetectDesc,
        null,
        null
    )

    private val silentWhisper = RacialAdvantage(
        R.string.silentWhisper,
        R.string.silentWhisperDesc,
        null,
        null
    )

    private val immortalSoulDanjayni = RacialAdvantage(
        R.string.danjayniSoul,
        R.string.danjayniSoulDesc,
        null,
        null
    )

    private val orinie = RacialAdvantage(
        R.string.orinie,
        R.string.orinieDesc,
        null,
        null
    )

    private val celestialEssence = RacialAdvantage(
        R.string.celestialEssence,
        R.string.celestEssenceDesc,
        null,
        null
    )

    private val seraphimWings = RacialAdvantage(
        R.string.seraphimWings,
        R.string.seraphWingDesc,
        null,
        null
    )

    private val immortalSoulEbudan = RacialAdvantage(
        R.string.ebudanSoul,
        R.string.ebudanSoulDesc,
        null,
        null
    )

    private val seeEssence = RacialAdvantage(
        R.string.seeEssence,
        R.string.seeEssenceDesc,
        null,
        null
    )

    private val forestSense = RacialAdvantage(
        R.string.forestSense,
        R.string.forestSenseDesc,
        null,
        null
    )

    private val natureCure = RacialAdvantage(
        R.string.natureCure,
        R.string.natureCureDesc,
        null,
        null
    )

    private val forestMovement = RacialAdvantage(
        R.string.forestMovement,
        R.string.forestMoveDesc,
        null,
        null
    )

    private val smallSize = RacialAdvantage(
        R.string.smallSize,
        R.string.smallSizeDesc,
        {_, _ ->
            charInstance.sizeSpecial.value -= 1
            charInstance.updateSize()
        },
        {_, _ ->
            charInstance.sizeSpecial.value += 1
            charInstance.updateSize()
        }
    )

    private val immortalSoulDaimah = RacialAdvantage(
        R.string.daimahSoul,
        R.string.daimahSoulDesc,
        null,
        null
    )

    private val exceptionalResistancesDukzarist = RacialAdvantage(
        R.string.dukzaristResistances,
        R.string.dukResDesc,
        {_, _ ->
            charInstance.combat.allResistances.forEach{it.setSpecial(15)}

            if(charInstance.isMale.value) charInstance.combat.physicalRes.setSpecial(5)
            else charInstance.combat.magicRes.setSpecial(5)
        },
        {_, _ ->
            charInstance.combat.allResistances.forEach{it.setSpecial(-15)}

            if(charInstance.isMale.value) charInstance.combat.physicalRes.setSpecial(-5)
            else charInstance.combat.magicRes.setSpecial(-5)
        }
    )

    private val inclinationDark = RacialAdvantage(
        R.string.inclinationDark,
        R.string.darkInclineDesc,
        {_, _ ->
            val reference = charInstance.advantageRecord.magicAdvantages.elementalCompatibility

            val dummyAdvantage = Advantage(
                reference.saveTag,
                reference.name,
                reference.description,
                reference.effect,
                reference.restriction,
                reference.special,
                null,
                0,
                reference.cost,
                reference.pickedCost,
                reference.onTake,
                reference.onRemove
            )

            charInstance.advantageRecord.removeAdvantage(dummyAdvantage)
        },
        null
    )

    private val withstandDeath = RacialAdvantage(
        R.string.withstandDeath,
        R.string.deathWithstandDesc,
        null,
        null
    )

    private val quickHealingDukzarist = RacialAdvantage(
        R.string.dukzaristHealing,
        R.string.dukRegenDesc,
        {_, _ ->
            charInstance.combat.specRegen.value += 1
            charInstance.combat.updateRegeneration()
        },
        {_, _ ->
            charInstance.combat.specRegen.value -= 1
            charInstance.combat.updateRegeneration()
        }
    )

    private val limitedNeeds = RacialAdvantage(
        R.string.limitedNeeds,
        R.string.limitedNeedsDesc,
        null,
        null
    )

    private val senseLightDarkDukzarist = RacialAdvantage(
        R.string.dukzaristSense,
        R.string.darkSenseDesc,
        null,
        null
    )

    private val nightVision = RacialAdvantage(
        R.string.nightVision,
        R.string.dukNightVisDesc,
        null,
        null
    )

    private val fireDevotion = RacialAdvantage(
        R.string.fireDevotion,
        R.string.devoteFireDesc,
        {_, _ ->
            if(charInstance.psychic.totalPsychicPoints.value > 0) {
                if (charInstance.psychic.getFreePsyPoints() == 0) {
                    val powerRemoved =
                        if (charInstance.psychic.masteredPowers.isNotEmpty())
                            charInstance.psychic.masteredPowers.keys.last()
                        else null
                    if(powerRemoved != null)
                        charInstance.psychic.masterPower(
                            powerRemoved,
                            charInstance.psychic.getPowerDiscipline(powerRemoved)!!,
                            false
                        )
                    else{
                        val disciplineRemoved =
                            if (charInstance.psychic.disciplineInvestment.size > 0) charInstance.psychic.disciplineInvestment.last()
                            else null
                        if(disciplineRemoved != null)
                            charInstance.psychic.updateInvestment(disciplineRemoved, false)
                    }
                }

                charInstance.psychic.updateInvestment(charInstance.psychic.pyrokinesis, true)
            }
        },
        null
    )

    private val perfectBodies = RacialAdvantage(
        R.string.perfectBodies,
        R.string.perfectBodDesc,
        {_, _ ->
            charInstance.advantageRecord.removeAdvantage(charInstance.advantageRecord.commonAdvantages.atrophiedLimb)
            charInstance.advantageRecord.removeAdvantage(charInstance.advantageRecord.commonAdvantages.blind)
            charInstance.advantageRecord.removeAdvantage(charInstance.advantageRecord.commonAdvantages.deafness)
            charInstance.advantageRecord.removeAdvantage(charInstance.advantageRecord.commonAdvantages.mute)
            charInstance.advantageRecord.removeAdvantage(charInstance.advantageRecord.commonAdvantages.nearsighted)
            charInstance.advantageRecord.removeAdvantage(charInstance.advantageRecord.commonAdvantages.physicalWeakness)
            charInstance.advantageRecord.removeAdvantage(charInstance.advantageRecord.commonAdvantages.seriousIllness)
            charInstance.advantageRecord.removeAdvantage(charInstance.advantageRecord.commonAdvantages.sickly)
            charInstance.advantageRecord.removeAdvantage(charInstance.advantageRecord.commonAdvantages.poisonSusceptibility)
        },
        null
    )

    private val metalAllergy = RacialAdvantage(
        R.string.metalAllergy,
        R.string.metalAllergyDesc,
        null,
        null
    )

    private val immortalSoulDukzarist = RacialAdvantage(
        R.string.dukzaristSoul,
        R.string.dukzaristSoulDesc,
        null,
        null
    )

    /**
     * Retrieves a racial advantage list based on a string input.
     *
     * @param input name of the race that is input
     * @return list of the corresponding advantages
     */
    fun getFromString(input: String): List<RacialAdvantage>{
        return when(input){
            "Sylvain" -> sylvainAdvantages
            "Jayan" -> jayanAdvantages
            "D\'Anjayni" -> danjayniAdvantages
            "Ebudan" -> ebudanAdvantages
            "Daimah" -> daimahAdvantages
            "Duk\'zarist" -> dukzaristAdvantages
            else -> listOf()
        }
    }

    /**
     * Get string that describes the inputted racial advantage list.
     *
     * @param input to get the name of
     * @return string name associated with the list
     */
    fun getNameOfList(input: List<RacialAdvantage>): String{
        return when(input){
            sylvainAdvantages -> "Sylvain"
            jayanAdvantages -> "Jayan"
            danjayniAdvantages -> "D\'Anjayni"
            ebudanAdvantages -> "Ebudan"
            daimahAdvantages -> "Daimah"
            dukzaristAdvantages -> "Duk\'zarist"
            else -> "Human"
        }
    }

    val sylvainAdvantages = listOf(exceptionalResistancesSylvain, inclinationLight, quickHealingSylvain,
        senseLightDarkSylvain, immortalSoulSylvain)

    val jayanAdvantages = listOf(giant, withstandFatigue, damageResistance, uncommonStrength,
        spiritualVision, magicSusceptibility, immortalSoulJayan)

    val danjayniAdvantages = listOf(withoutTrace, forgetfulness, commonAppearance, undetectability,
        silentWhisper, immortalSoulDanjayni)

    val ebudanAdvantages = listOf(orinie, celestialEssence, seraphimWings, immortalSoulEbudan)

    val daimahAdvantages = listOf(seeEssence, forestSense, natureCure, forestMovement, smallSize,
        immortalSoulDaimah)

    val dukzaristAdvantages = listOf(exceptionalResistancesDukzarist, inclinationDark, withstandDeath,
        quickHealingDukzarist, limitedNeeds, senseLightDarkDukzarist, nightVision, fireDevotion,
        perfectBodies, metalAllergy, immortalSoulDukzarist)

    val allAdvantageLists = listOf(
        listOf(),
        sylvainAdvantages,
        jayanAdvantages,
        danjayniAdvantages,
        ebudanAdvantages,
        daimahAdvantages,
        dukzaristAdvantages
    )
}