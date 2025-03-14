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
        name = R.string.sylvainResistances,
        description = R.string.sylvResDesc,
        onTake = {_, _ ->
            //set special resistance values for this race
            charInstance.combat.physicalRes.setSpecial(specChange = 5)
            charInstance.combat.diseaseRes.setSpecial(specChange = 20)
            charInstance.combat.venomRes.setSpecial(specChange = 5)
            charInstance.combat.magicRes.setSpecial(specChange = 10)
            charInstance.combat.psychicRes.setSpecial(specChange = 10)

            //remove any restricted disadvantages for Sylvain
            charInstance.advantageRecord.removeAdvantage(advantage = charInstance.advantageRecord.commonAdvantages.sickly)
            charInstance.advantageRecord.removeAdvantage(advantage = charInstance.advantageRecord.commonAdvantages.seriousIllness)
            charInstance.advantageRecord.removeAdvantage(advantage = charInstance.advantageRecord.commonAdvantages.magicSusceptibility)
        },
        onRemove = {_, _ ->
            //remove resistance bonuses from this race
            charInstance.combat.physicalRes.setSpecial(specChange = -5)
            charInstance.combat.diseaseRes.setSpecial(specChange = -20)
            charInstance.combat.venomRes.setSpecial(specChange = -5)
            charInstance.combat.magicRes.setSpecial(specChange = -10)
            charInstance.combat.psychicRes.setSpecial(specChange = -10)
        }
    )

    private val inclinationLight = RacialAdvantage(
        name = R.string.lightInclination,
        description = R.string.lightInclineDesc,
        onTake = {_, _ ->
            //obtain base version of the elemental compatibility advantage
            val reference = charInstance.advantageRecord.magicAdvantages.elementalCompatibility

            //create advantage for compatibility with Dark
            val dummyAdvantage = Advantage(
                saveTag = reference.saveTag,
                name = reference.name,
                description = reference.description,
                effect = reference.effect,
                restriction = reference.restriction,
                special = reference.special,
                options = null,
                picked = 1,
                multPicked = null,
                cost = reference.cost,
                pickedCost = reference.pickedCost,
                onTake = reference.onTake,
                onRemove = reference.onRemove
            )

            //remove any dark compatibility from the character
            charInstance.advantageRecord.removeAdvantage(advantage = dummyAdvantage)
        },
        onRemove = null
    )

    private val quickHealingSylvain = RacialAdvantage(
        name = R.string.sylvainHealing,
        description = R.string.sylvainHealingDesc,
        onTake = {_, _ ->
            //apply sylvain's regeneration bonus
            charInstance.combat.specRegen.intValue += 1
            charInstance.combat.updateRegeneration()
        },
        onRemove = {_, _ ->
            //remove sylvain's regeneration bonus
            charInstance.combat.specRegen.intValue -= 1
            charInstance.combat.updateRegeneration()
        }
    )

    private val senseLightDarkSylvain = RacialAdvantage(
        name = R.string.sylvainSense,
        description = R.string.lightSenseDesc,
        onTake = null,
        onRemove = null
    )

    private val immortalSoulSylvain = RacialAdvantage(
        name = R.string.sylvainSoul,
        description = R.string.sylvainSoulDesc,
        onTake = null,
        onRemove = null
    )

    private val giant = RacialAdvantage(
        name = R.string.giant,
        description = R.string.giantDesc,
        onTake = {_, _ ->
            //check for uncommon size advantage
            val uncommonSizeHeld = charInstance.advantageRecord.getAdvantage(advantageString = "uncommonSize")

            //remove advantage if it reduces their size
            if(uncommonSizeHeld != null && uncommonSizeHeld.picked!! < 5)
                charInstance.advantageRecord.removeAdvantage(advantage = uncommonSizeHeld)

            //increase size of character
            charInstance.changeSize(sizeInput = 6)
        },
        onRemove = {_, _ ->
            //restore character's size from their race
            charInstance.changeSize(sizeInput = 3)
        }
    )

    private val withstandFatigue = RacialAdvantage(
        name = R.string.jayanFatigue,
        description = R.string.standFatigueDesc,
        onTake = {_, _ ->
            //grant racial fatigue bonus
            charInstance.combat.specFatigue.intValue += 1
            charInstance.combat.updateFatigue()
        },
        onRemove = {_, _ ->
            //remove racial fatigue bonus
            charInstance.combat.specFatigue.intValue -= 1
            charInstance.combat.updateFatigue()
        }
    )

    private val damageResistance = RacialAdvantage(
        name = R.string.resDamage,
        description = R.string.damageResDesc,
        onTake = {_, _ ->
            //grant physical resistance bonus
            charInstance.combat.physicalRes.setSpecial(specChange = 15)
        },
        onRemove = {_, _ ->
            //remove physical resistance bonus
            charInstance.combat.physicalRes.setSpecial(specChange = -15)
        }
    )

    private val uncommonStrength = RacialAdvantage(
        name = R.string.uncommonStr,
        description = R.string.uncommonStrDesc,
        onTake = {_, _ ->
            //set new racial bonus value
            charInstance.primaryList.str.setBonus(bonusInput = 1)

            //check for reduction in strength stat
            val reference = charInstance.advantageRecord.commonAdvantages.deductCharacteristic
            val dummyAdvantage = Advantage(
                saveTag = reference.saveTag,
                name = reference.name,
                description = reference.description,
                effect = reference.effect,
                restriction = reference.restriction,
                special = reference.special,
                options = null,
                picked = 0,
                multPicked = null,
                cost = reference.cost,
                pickedCost = reference.pickedCost,
                onTake = reference.onTake,
                onRemove = reference.onRemove
            )

            //remove if present
            charInstance.advantageRecord.removeAdvantage(advantage = dummyAdvantage)
        },
        onRemove = {_, _ ->
            //remove strength bonus
            charInstance.primaryList.str.setBonus(bonusInput = -1)
        }
    )

    private val spiritualVision = RacialAdvantage(
        name = R.string.spiritualVision,
        description = R.string.spiritVisionDesc,
        onTake = null,
        onRemove = null
    )

    private val magicSusceptibility = RacialAdvantage(
        name = R.string.jayanMagSusceptibility,
        description = R.string.jayanMagSusDesc,
        onTake = {_, _ ->
            //reduce the Jayan's magic resistance
            charInstance.combat.magicRes.setSpecial(specChange = -10)
        },
        onRemove = {_, _ ->
            //remove magic resistance penalty
            charInstance.combat.magicRes.setSpecial(specChange = 10)
        }
    )

    private val immortalSoulJayan = RacialAdvantage(
        name = R.string.jayanSoul,
        description = R.string.jayanSoulDesc,
        onTake = null,
        onRemove = null
    )

    private val withoutTrace = RacialAdvantage(
        name = R.string.withoutTrace,
        description = R.string.withoutTraceDesc,
        onTake = null,
        onRemove = null
    )

    private val forgetfulness = RacialAdvantage(
        name = R.string.forgetfulness,
        description = R.string.forgetDesc,
        onTake = null,
        onRemove = null
    )

    private val commonAppearance = RacialAdvantage(
        name = R.string.commonAppearance,
        description = R.string.commonAppearDesc,
        onTake = { _, _ ->
            //set D'Anjayni's appearance value if current appearance is illegal
            if(charInstance.appearance.intValue !in 3..7)
                charInstance.setAppearance(newAppearance = 5)
        },
        onRemove = null
    )

    private val undetectability = RacialAdvantage(
        name = R.string.undetectability,
        description = R.string.undetectDesc,
        onTake = null,
        onRemove = null
    )

    private val silentWhisper = RacialAdvantage(
        name = R.string.silentWhisper,
        description = R.string.silentWhisperDesc,
        onTake = null,
        onRemove = null
    )

    private val immortalSoulDanjayni = RacialAdvantage(
        name = R.string.danjayniSoul,
        description = R.string.danjayniSoulDesc,
        onTake = null,
        onRemove = null
    )

    private val orinie = RacialAdvantage(
        name = R.string.orinie,
        description = R.string.orinieDesc,
        onTake = null,
        onRemove = null
    )

    private val celestialEssence = RacialAdvantage(
        name = R.string.celestialEssence,
        description = R.string.celestEssenceDesc,
        onTake = null,
        onRemove = null
    )

    private val seraphimWings = RacialAdvantage(
        name = R.string.seraphimWings,
        description = R.string.seraphWingDesc,
        onTake = null,
        onRemove = null
    )

    private val immortalSoulEbudan = RacialAdvantage(
        name = R.string.ebudanSoul,
        description = R.string.ebudanSoulDesc,
        onTake = null,
        onRemove = null
    )

    private val seeEssence = RacialAdvantage(
        name = R.string.seeEssence,
        description = R.string.seeEssenceDesc,
        onTake = null,
        onRemove = null
    )

    private val forestSense = RacialAdvantage(
        name = R.string.forestSense,
        description = R.string.forestSenseDesc,
        onTake = null,
        onRemove = null
    )

    private val natureCure = RacialAdvantage(
        name = R.string.natureCure,
        description = R.string.natureCureDesc,
        onTake = null,
        onRemove = null
    )

    private val forestMovement = RacialAdvantage(
        name = R.string.forestMovement,
        description = R.string.forestMoveDesc,
        onTake = null,
        onRemove = null
    )

    private val smallSize = RacialAdvantage(
        name = R.string.smallSize,
        description = R.string.smallSizeDesc,
        onTake = {_, _ ->
            //reduce the Daimah's size
            charInstance.sizeSpecial.intValue -= 1
            charInstance.updateSize()
        },
        onRemove = {_, _ ->
            //remove the Daimah's size reduction
            charInstance.sizeSpecial.intValue += 1
            charInstance.updateSize()
        }
    )

    private val immortalSoulDaimah = RacialAdvantage(
        name = R.string.daimahSoul,
        description = R.string.daimahSoulDesc,
        onTake = null,
        onRemove = null
    )

    private val exceptionalResistancesDukzarist = RacialAdvantage(
        name = R.string.dukzaristResistances,
        description = R.string.dukResDesc,
        onTake = {_, _ ->
            //increase all of the character's resistances
            charInstance.combat.allResistances.forEach{resistance ->
                resistance.setSpecial(specChange = 15)
            }

            //apply the gender specific bonus
            if(charInstance.isMale.value) charInstance.combat.physicalRes.setSpecial(specChange = 5)
            else charInstance.combat.magicRes.setSpecial(specChange = 5)
        },
        onRemove = {_, _ ->
            //remove bonuses from resistances
            charInstance.combat.allResistances.forEach{resistance ->
                resistance.setSpecial(specChange = -15)
            }

            //remove the gender specific bonus
            if(charInstance.isMale.value) charInstance.combat.physicalRes.setSpecial(specChange = -5)
            else charInstance.combat.magicRes.setSpecial(specChange = -5)
        }
    )

    private val inclinationDark = RacialAdvantage(
        name = R.string.inclinationDark,
        description = R.string.darkInclineDesc,
        onTake = {_, _ ->
            //construct elemental compatibility with light advantage
            val reference = charInstance.advantageRecord.magicAdvantages.elementalCompatibility
            val dummyAdvantage = Advantage(
                saveTag = reference.saveTag,
                name = reference.name,
                description = reference.description,
                effect = reference.effect,
                restriction = reference.restriction,
                special = reference.special,
                options = null,
                picked = 0,
                multPicked = null,
                cost = reference.cost,
                pickedCost = reference.pickedCost,
                onTake = reference.onTake,
                onRemove = reference.onRemove
            )

            //remove any elemental compatibility with light advantage held
            charInstance.advantageRecord.removeAdvantage(advantage = dummyAdvantage)
        },
        onRemove = null
    )

    private val withstandDeath = RacialAdvantage(
        name = R.string.withstandDeath,
        description = R.string.deathWithstandDesc,
        onTake = null,
        onRemove = null
    )

    private val quickHealingDukzarist = RacialAdvantage(
        name = R.string.dukzaristHealing,
        description = R.string.dukRegenDesc,
        onTake = {_, _ ->
            //increase the character's regeneration
            charInstance.combat.specRegen.intValue += 1
            charInstance.combat.updateRegeneration()
        },
        onRemove = {_, _ ->
            //remove the character's regeneration bonus
            charInstance.combat.specRegen.intValue -= 1
            charInstance.combat.updateRegeneration()
        }
    )

    private val limitedNeeds = RacialAdvantage(
        name = R.string.limitedNeeds,
        description = R.string.limitedNeedsDesc,
        onTake = null,
        onRemove = null
    )

    private val senseLightDarkDukzarist = RacialAdvantage(
        name = R.string.dukzaristSense,
        description = R.string.darkSenseDesc,
        onTake = null,
        onRemove = null
    )

    private val nightVision = RacialAdvantage(
        name = R.string.nightVision,
        description = R.string.dukNightVisDesc,
        onTake = null,
        onRemove = null
    )

    private val fireDevotion = RacialAdvantage(
        name = R.string.fireDevotion,
        description = R.string.devoteFireDesc,
        onTake = {_, _ ->
            //if the character has any psychic points to work with
            if(charInstance.psychic.totalPsychicPoints.intValue > 0) {
                //if character currently has no free psychic points
                if (charInstance.psychic.getFreePsyPoints() == 0) {
                    //remove the most recently acquired power, if available
                    if(charInstance.psychic.masteredPowers.isNotEmpty()) {
                        val power = charInstance.psychic.masteredPowers.keys.last()
                        charInstance.psychic.masterPower(
                            power = power,
                            discipline = charInstance.psychic.getPowerDiscipline(power = power)!!,
                            isMastering = false
                        )
                    }
                    else{
                        //remove most recently added discipline, if available
                        if(charInstance.psychic.disciplineInvestment.isNotEmpty())
                            charInstance.psychic.updateInvestment(
                                discipline = charInstance.psychic.disciplineInvestment.last(),
                                isTaken = false
                            )
                    }
                }

                //attempt to add pyrokinesis to the character's accessible disciplines
                charInstance.psychic.updateInvestment(charInstance.psychic.pyrokinesis, true)
            }
        },
        onRemove = null
    )

    private val perfectBodies = RacialAdvantage(
        name = R.string.perfectBodies,
        description = R.string.perfectBodDesc,
        onTake = {_, _ ->
            //remove any disadvantage forbidden by this race
            charInstance.advantageRecord.removeAdvantage(advantage = charInstance.advantageRecord.commonAdvantages.atrophiedLimb)
            charInstance.advantageRecord.removeAdvantage(advantage = charInstance.advantageRecord.commonAdvantages.blind)
            charInstance.advantageRecord.removeAdvantage(advantage = charInstance.advantageRecord.commonAdvantages.deafness)
            charInstance.advantageRecord.removeAdvantage(advantage = charInstance.advantageRecord.commonAdvantages.mute)
            charInstance.advantageRecord.removeAdvantage(advantage = charInstance.advantageRecord.commonAdvantages.nearsighted)
            charInstance.advantageRecord.removeAdvantage(advantage = charInstance.advantageRecord.commonAdvantages.physicalWeakness)
            charInstance.advantageRecord.removeAdvantage(advantage = charInstance.advantageRecord.commonAdvantages.seriousIllness)
            charInstance.advantageRecord.removeAdvantage(advantage = charInstance.advantageRecord.commonAdvantages.sickly)
            charInstance.advantageRecord.removeAdvantage(advantage = charInstance.advantageRecord.commonAdvantages.poisonSusceptibility)
        },
        onRemove = null
    )

    private val metalAllergy = RacialAdvantage(
        name = R.string.metalAllergy,
        description = R.string.metalAllergyDesc,
        onTake = null,
        onRemove = null
    )

    private val immortalSoulDukzarist = RacialAdvantage(
        name = R.string.dukzaristSoul,
        description = R.string.dukzaristSoulDesc,
        onTake = null,
        onRemove = null
    )

    /**
     * Retrieves a racial advantage list based on a string input.
     *
     * @param raceName name of the race that is queried for
     * @return list of the corresponding advantages
     */
    fun getFromString(
        raceName: String
    ): List<RacialAdvantage>{
        return when(raceName){
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
     * @param raceAdvantages list of advantages associated with a race
     * @return string name associated with the list
     */
    fun getNameOfList(
        raceAdvantages: List<RacialAdvantage>
    ): String{
        return when(raceAdvantages){
            sylvainAdvantages -> "Sylvain"
            jayanAdvantages -> "Jayan"
            danjayniAdvantages -> "D\'Anjayni"
            ebudanAdvantages -> "Ebudan"
            daimahAdvantages -> "Daimah"
            dukzaristAdvantages -> "Duk\'zarist"
            else -> "Human"
        }
    }

    //compile advantages for Sylvain
    val sylvainAdvantages = listOf(exceptionalResistancesSylvain, inclinationLight, quickHealingSylvain,
        senseLightDarkSylvain, immortalSoulSylvain)

    //compile advantages for Jayan
    val jayanAdvantages = listOf(giant, withstandFatigue, damageResistance, uncommonStrength,
        spiritualVision, magicSusceptibility, immortalSoulJayan)

    //compile advantages for D'Anjayni
    val danjayniAdvantages = listOf(withoutTrace, forgetfulness, commonAppearance, undetectability,
        silentWhisper, immortalSoulDanjayni)

    //compile advantages for Ebudan
    private val ebudanAdvantages = listOf(orinie, celestialEssence, seraphimWings, immortalSoulEbudan)

    //compile advantages for Daimah
    private val daimahAdvantages = listOf(seeEssence, forestSense, natureCure, forestMovement, smallSize,
        immortalSoulDaimah)

    //compile advantages for Duk'zarist
    val dukzaristAdvantages = listOf(exceptionalResistancesDukzarist, inclinationDark, withstandDeath,
        quickHealingDukzarist, limitedNeeds, senseLightDarkDukzarist, nightVision, fireDevotion,
        perfectBodies, metalAllergy, immortalSoulDukzarist)

    //make full list of racial advantages, including an empty one for humans
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