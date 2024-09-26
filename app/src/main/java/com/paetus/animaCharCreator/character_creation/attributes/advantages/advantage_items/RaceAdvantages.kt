package com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_items

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_types.Advantage
import com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_types.RacialAdvantage

/**
 * Lists of advantages for every race available to a character.
 */
class RaceAdvantages{
    private val exceptionalResistancesSylvain = RacialAdvantage(
        name = R.string.sylvainResistances,
        description = R.string.sylvResDesc,
        onTake = {character, _, _ ->
            //set special resistance values for this race
            character.combat.physicalRes.setSpecial(specChange = 5)
            character.combat.diseaseRes.setSpecial(specChange = 20)
            character.combat.venomRes.setSpecial(specChange = 5)
            character.combat.magicRes.setSpecial(specChange = 10)
            character.combat.psychicRes.setSpecial(specChange = 10)

            //remove any restricted disadvantages for Sylvain
            character.advantageRecord.removeAdvantage(advantage = character.objectDB.commonAdvantages.sickly)
            character.advantageRecord.removeAdvantage(advantage = character.objectDB.commonAdvantages.seriousIllness)
            character.advantageRecord.removeAdvantage(advantage = character.objectDB.commonAdvantages.magicSusceptibility)
        },
        onRemove = {character, _, _ ->
            //remove resistance bonuses from this race
            character.combat.physicalRes.setSpecial(specChange = -5)
            character.combat.diseaseRes.setSpecial(specChange = -20)
            character.combat.venomRes.setSpecial(specChange = -5)
            character.combat.magicRes.setSpecial(specChange = -10)
            character.combat.psychicRes.setSpecial(specChange = -10)
        }
    )

    private val inclinationLight = RacialAdvantage(
        name = R.string.lightInclination,
        description = R.string.lightInclineDesc,
        onTake = {character, _, _ ->
            //obtain base version of the elemental compatibility advantage
            val reference = character.objectDB.magicAdvantages.elementalCompatibility

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
            character.advantageRecord.removeAdvantage(advantage = dummyAdvantage)
        },
        onRemove = null
    )

    private val quickHealingSylvain = RacialAdvantage(
        name = R.string.sylvainHealing,
        description = R.string.sylvainHealingDesc,
        onTake = {character, _, _ ->
            //apply sylvain's regeneration bonus
            character.combat.specRegen.intValue += 1
            character.combat.updateRegeneration()
        },
        onRemove = {character, _, _ ->
            //remove sylvain's regeneration bonus
            character.combat.specRegen.intValue -= 1
            character.combat.updateRegeneration()
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
        onTake = {character, _, _ ->
            //check for uncommon size advantage
            val uncommonSizeHeld = character.advantageRecord.getAdvantage(advantageString = "uncommonSize")

            //remove advantage if it reduces their size
            if(uncommonSizeHeld != null && uncommonSizeHeld.picked!! < 5)
                character.advantageRecord.removeAdvantage(advantage = uncommonSizeHeld)

            //increase size of character
            character.changeSize(sizeInput = 6)
        },
        onRemove = {character, _, _ ->
            //restore character's size from their race
            character.changeSize(sizeInput = 3)
        }
    )

    private val withstandFatigue = RacialAdvantage(
        name = R.string.jayanFatigue,
        description = R.string.standFatigueDesc,
        onTake = {character, _, _ ->
            //grant racial fatigue bonus
            character.combat.specFatigue.intValue += 1
            character.combat.updateFatigue()
        },
        onRemove = {character, _, _ ->
            //remove racial fatigue bonus
            character.combat.specFatigue.intValue -= 1
            character.combat.updateFatigue()
        }
    )

    private val damageResistance = RacialAdvantage(
        name = R.string.resDamage,
        description = R.string.damageResDesc,
        onTake = {character, _, _ ->
            //grant physical resistance bonus
            character.combat.physicalRes.setSpecial(specChange = 15)
        },
        onRemove = {character, _, _ ->
            //remove physical resistance bonus
            character.combat.physicalRes.setSpecial(specChange = -15)
        }
    )

    private val uncommonStrength = RacialAdvantage(
        name = R.string.uncommonStr,
        description = R.string.uncommonStrDesc,
        onTake = {character, _, _ ->
            //set new racial bonus value
            character.primaryList.str.setBonus(bonusInput = 1)

            //check for reduction in strength stat
            val reference = character.objectDB.commonAdvantages.deductCharacteristic
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
            character.advantageRecord.removeAdvantage(advantage = dummyAdvantage)
        },
        onRemove = {character, _, _ ->
            //remove strength bonus
            character.primaryList.str.setBonus(bonusInput = -1)
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
        onTake = {character, _, _ ->
            //reduce the Jayan's magic resistance
            character.combat.magicRes.setSpecial(specChange = -10)
        },
        onRemove = {character, _, _ ->
            //remove magic resistance penalty
            character.combat.magicRes.setSpecial(specChange = 10)
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
        onTake = {character, _, _ ->
            //set D'Anjayni's appearance value if current appearance is illegal
            if(character.appearance.intValue !in 3..7)
                character.setAppearance(newAppearance = 5)
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
        onTake = {character, _, _ ->
            //reduce the Daimah's size
            character.sizeSpecial.intValue -= 1
            character.updateSize()
        },
        onRemove = {character, _, _ ->
            //remove the Daimah's size reduction
            character.sizeSpecial.intValue += 1
            character.updateSize()
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
        onTake = {character, _, _ ->
            //increase all of the character's resistances
            character.combat.allResistances.forEach{resistance ->
                resistance.setSpecial(specChange = 15)
            }

            //apply the gender specific bonus
            if(character.isMale.value) character.combat.physicalRes.setSpecial(specChange = 5)
            else character.combat.magicRes.setSpecial(specChange = 5)
        },
        onRemove = {character, _, _ ->
            //remove bonuses from resistances
            character.combat.allResistances.forEach{resistance ->
                resistance.setSpecial(specChange = -15)
            }

            //remove the gender specific bonus
            if(character.isMale.value) character.combat.physicalRes.setSpecial(specChange = -5)
            else character.combat.magicRes.setSpecial(specChange = -5)
        }
    )

    private val inclinationDark = RacialAdvantage(
        name = R.string.inclinationDark,
        description = R.string.darkInclineDesc,
        onTake = {character, _, _ ->
            //construct elemental compatibility with light advantage
            val reference = character.objectDB.magicAdvantages.elementalCompatibility
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
            character.advantageRecord.removeAdvantage(advantage = dummyAdvantage)
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
        onTake = {character, _, _ ->
            //increase the character's regeneration
            character.combat.specRegen.intValue += 1
            character.combat.updateRegeneration()
        },
        onRemove = {character, _, _ ->
            //remove the character's regeneration bonus
            character.combat.specRegen.intValue -= 1
            character.combat.updateRegeneration()
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
        onTake = {character, _, _ ->
            //if the character has any psychic points to work with
            if(character.psychic.totalPsychicPoints.intValue > 0) {
                //if character currently has no free psychic points
                if (character.psychic.getFreePsyPoints() == 0) {
                    //remove the most recently acquired power, if available
                    if(character.psychic.masteredPowers.isNotEmpty()) {
                        val power = character.psychic.masteredPowers.keys.last()
                        character.psychic.masterPower(
                            power = power,
                            discipline = character.psychic.getPowerDiscipline(power = power)!!,
                            isMastering = false
                        )
                    }
                    else{
                        //remove most recently added discipline, if available
                        if(character.psychic.disciplineInvestment.size > 0)
                            character.psychic.updateInvestment(
                                discipline = character.psychic.disciplineInvestment.last(),
                                isTaken = false
                            )
                    }
                }

                //attempt to add pyrokinesis to the character's accessible disciplines
                character.psychic.updateInvestment(character.psychic.pyrokinesis, true)
            }
        },
        onRemove = null
    )

    private val perfectBodies = RacialAdvantage(
        name = R.string.perfectBodies,
        description = R.string.perfectBodDesc,
        onTake = {character, _, _ ->
            //remove any disadvantage forbidden by this race
            character.advantageRecord.removeAdvantage(advantage = character.objectDB.commonAdvantages.atrophiedLimb)
            character.advantageRecord.removeAdvantage(advantage = character.objectDB.commonAdvantages.blind)
            character.advantageRecord.removeAdvantage(advantage = character.objectDB.commonAdvantages.deafness)
            character.advantageRecord.removeAdvantage(advantage = character.objectDB.commonAdvantages.mute)
            character.advantageRecord.removeAdvantage(advantage = character.objectDB.commonAdvantages.nearsighted)
            character.advantageRecord.removeAdvantage(advantage = character.objectDB.commonAdvantages.physicalWeakness)
            character.advantageRecord.removeAdvantage(advantage = character.objectDB.commonAdvantages.seriousIllness)
            character.advantageRecord.removeAdvantage(advantage = character.objectDB.commonAdvantages.sickly)
            character.advantageRecord.removeAdvantage(advantage = character.objectDB.commonAdvantages.poisonSusceptibility)
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