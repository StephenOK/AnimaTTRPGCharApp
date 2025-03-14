package com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_items

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_types.Advantage

/**
 * List of common advantages and disadvantages a character may acquire.
 */
class CommonAdvantages{
    val characteristicPoint = Advantage(
        saveTag = "characteristicPoint",
        name = R.string.addCharPoint,
        description = R.string.charPointDesc,
        effect = R.string.charPointEffect,
        restriction = R.string.charPointRestriction,
        special = R.string.advantageSpecial,
        options = R.array.primaryCharArray,
        picked = 0,
        multPicked = null,
        cost = listOf(1),
        pickedCost = 0,
        onTake = {character, input, _ ->
            //increase characteristic by one point
            character.primaryList.allPrimaries()[input!!].setBonus(bonusInput = 1)
        },
        onRemove = {character, input, _ ->
            //remove characteristic bonus point
            character.primaryList.allPrimaries()[input!!].setBonus(bonusInput = -1)
        }
    )

    private val acuteSenses = Advantage(
        saveTag = "acuteSenses",
        name = R.string.acuteSenses,
        description = R.string.acuteDesc,
        effect = R.string.acuteEffect,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(1),
        pickedCost = 0,
        onTake = {character, _, _ ->
            //apply bonuses to perception and search
            character.secondaryList.notice.setSpecial(specBonus = 30)
            character.secondaryList.search.setSpecial(specBonus = 30)
        },
        onRemove = {character, _, _ ->
            //remove perception and search bonuses
            character.secondaryList.notice.setSpecial(specBonus = -30)
            character.secondaryList.search.setSpecial(specBonus = -30)
        }
    )

    private val artifact = Advantage(
        saveTag = "artifact",
        name = R.string.artifact,
        description = R.string.artifactDesc,
        effect = R.string.artifactEffect,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(1, 2, 3),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    private val ambidextrous = Advantage(
        saveTag = "ambidextrous",
        name = R.string.ambidextrous,
        description = R.string.ambiDesc,
        effect = R.string.ambiEffect,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(1),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    private val characteristicToNine = Advantage(
        saveTag = "characteristicToNine",
        name = R.string.charToNine,
        description = R.string.charTo9Desc,
        effect = R.string.charTo9Effect,
        restriction = null,
        special = R.string.advantageSpecial,
        options = R.array.primaryCharArray,
        picked = 0,
        multPicked = null,
        cost = listOf(2),
        pickedCost = 0,
        onTake = {character, input, _ ->
            //set the determined primary stat to 9
            character.primaryList.allPrimaries()[input!!].setInput(baseIn = 9)
        },
        onRemove = null
    )

    val psyDisciplineAccess = Advantage(
        saveTag = "psyDisciplineAccess",
        name = R.string.psyDiscAccess,
        description = R.string.disciplineAccDesc,
        effect = R.string.disciplineAccEff,
        restriction = null,
        special = null,
        options = R.array.disciplineNames,
        picked = 0,
        multPicked = null,
        cost = listOf(1),
        pickedCost = 0,
        onTake = {character, input, _ ->
            //add access to the indicated psychic discipline and matrix powers
            character.psychic.legalDisciplines.add(element = character.objectDB.psyLibrary.allDisciplines[input!!])
            character.psychic.legalDisciplines.add(element = character.psychic.matrixPowers)
        },
        onRemove = {character, input, _ ->
            //remove the indicated psychic discipline from accessibility
            character.psychic.removeLegalDisciplineFromInt(discIndex = input!!)
        }
    )

    private val charm = Advantage(
        saveTag = "charm",
        name = R.string.charm,
        description = R.string.charmDesc,
        effect = R.string.charmEffect,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(1),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    private val disquieting = Advantage(
        saveTag = "disquieting",
        name = R.string.disquieting,
        description = R.string.disquietDesc,
        effect = R.string.disquietEffect,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(1),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    private val animalAffinity = Advantage(
        saveTag = "animalAffinity",
        name = R.string.animalAffinity,
        description = R.string.affinityDesc,
        effect = R.string.affinityEffect,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(1),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    private val dangerSense = Advantage(
        saveTag = "dangerSense",
        name = R.string.dangerSense,
        description = R.string.dangerSenseDesc,
        effect = R.string.dangerSenseEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(2),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    private val beenAround = Advantage(
        saveTag = "beenAround",
        name = R.string.beenAround,
        description = R.string.beenAroundDesc,
        effect = R.string.beenAroundEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(1, 2, 3),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    val subjectAptitude = Advantage(
        saveTag = "subjectAptitude",
        name = R.string.subjectAptitude,
        description = R.string.subjAptDesc,
        effect = R.string.subjAptEff,
        restriction = R.string.subjAptRestriction,
        special = null,
        options = R.array.secondaryCharacteristics,
        picked = 0,
        multPicked = null,
        cost = listOf(1, 2),
        pickedCost = 0,
        onTake = {character, input, cost ->
            //reduce the cost of the indicated secondary characteristic
            if(input!! < 38)
                character.secondaryList.fullList()[input].setDevelopmentDeduction(dpDeduction = cost)
            else
                character.secondaryList.getAllCustoms()[input - 38].setDevelopmentDeduction(dpDeduction = cost)

            character.updateTotalSpent()
        },
        onRemove = {character, input, cost ->
            //restore the previous cost for this characteristic
            if(input!! < 38)
                character.secondaryList.fullList()[input].setDevelopmentDeduction(dpDeduction = cost * -1)
            else
                character.secondaryList.getAllCustoms()[input - 38].setDevelopmentDeduction(dpDeduction = cost * -1)

            character.updateTotalSpent()
        }
    )

    val naturalPsychicPower = Advantage(
        saveTag = "naturalPsychicPower",
        name = R.string.psyPowerAccess,
        description = R.string.natPsyPowDesc,
        effect = R.string.natPsyPowEff,
        restriction = null,
        special = null,
        options = R.array.powerNames,
        picked = 0,
        multPicked = null,
        cost = listOf(1, 2, 3),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    val fieldAptitude = Advantage(
        saveTag = "fieldAptitude",
        name = R.string.fieldAptitude,
        description = R.string.fieldAptDesc,
        effect = R.string.fieldAptEff,
        restriction = R.string.fieldAptRestriction,
        special = null,
        options = R.array.secondaryFields,
        picked = 0,
        multPicked = null,
        cost = listOf(2),
        pickedCost = 0,
        onTake = {character, input, _ ->
            //reduce the cost of each item in the indicated field
            val field = character.secondaryList.intToField(fieldInteger = input!!)
            field.forEach{secondaryChar ->
                secondaryChar.setDevelopmentDeduction(dpDeduction = 1)
            }
            character.updateTotalSpent()
        },
        onRemove = {character, input, _ ->
            //remove the bonus to the indicated field's cost
            val field = character.secondaryList.intToField(fieldInteger = input!!)
            field.forEach{secondaryChar ->
                secondaryChar.setDevelopmentDeduction(dpDeduction = -1)
            }
            character.updateTotalSpent()
        }
    )

    private val characteristicReroll = Advantage(
        saveTag = "characteristicReroll",
        name = R.string.repeatCharRoll,
        description = R.string.charRerollDesc,
        effect = R.string.charRerollEff,
        restriction = R.string.charRerollRestriction,
        special = R.string.advantageSpecial,
        options = R.array.primaryCharArray,
        picked = 0,
        multPicked = null,
        cost = listOf(1),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    private val martialMastery = Advantage(
        saveTag = "martialMastery",
        name = R.string.martialMastery,
        description = R.string.mmDesc,
        effect = R.string.mmEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(1, 2, 3),
        pickedCost = 0,
        onTake = {character, _, cost ->
            //add the desired amount of martial knowledge
            when(cost){
                1 -> character.ki.updateMKSpec(40)
                2 -> character.ki.updateMKSpec(80)
                3 -> character.ki.updateMKSpec(120)
            }
        },
        onRemove = {character, _, cost ->
            //remove the granted martial knowledge bonus
            when (cost) {
                1 -> character.ki.updateMKSpec(-40)
                2 -> character.ki.updateMKSpec(-80)
                3 -> character.ki.updateMKSpec(-120)
            }
        }
    )

    private val goodLuck = Advantage(
        saveTag = "goodLuck",
        name = R.string.goodLuck,
        description = R.string.goodLuckDesc,
        effect = R.string.goodLuckEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(1),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    private val kiRecovery = Advantage(
        saveTag = "kiRecovery",
        name = R.string.kiRecovery,
        description = R.string.kiRecoverDesc,
        effect = R.string.kiRecoverEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(1, 2, 3),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    private val jackOfAllTrades = Advantage(
        saveTag = "jackOfAllTrades",
        name = R.string.jackTrades,
        description = R.string.jackDesc,
        effect = R.string.jackEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(2),
        pickedCost = 0,
        onTake = {character, _, _ ->
            //let secondary list know of the change and update characteristics accordingly
            character.secondaryList.allTradesTaken.value = true
            character.secondaryList.getAllSecondaries().forEach{secondary ->
                secondary.refreshTotal()
            }
        },
        onRemove = {character, _, _ ->
            //let secondary list know of the change and restore characteristics to normal
            character.secondaryList.allTradesTaken.value = false
            character.secondaryList.getAllSecondaries().forEach{secondary ->
                secondary.refreshTotal()
            }
        }
    )

    private val naturalArmor = Advantage(
        saveTag = "naturalArmor",
        name = R.string.naturalArmor,
        description = R.string.natArmorDesc,
        effect = R.string.natArmorEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(1),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    private val mysticalArmor = Advantage(
        saveTag = "mysticalArmor",
        name = R.string.mysticArmor,
        description = R.string.mysArmorDesc,
        effect = R.string.mysArmorEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(1),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    private val untiring = Advantage(
        saveTag = "untiring",
        name = R.string.untiring,
        description = R.string.untiringDesc,
        effect = R.string.untiringEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(1, 2, 3),
        pickedCost = 0,
        onTake = {character, _, cost ->
            //update fatigue points as desired
            when(cost){
                1 -> character.combat.specFatigue.intValue += 3
                2 -> character.combat.specFatigue.intValue += 6
                3 -> character.combat.specFatigue.intValue += 9
            }

            character.combat.updateFatigue()
        },
        onRemove = {character, _, cost ->
            //remove bonus to fatigue points
            when (cost) {
                1 -> character.combat.specFatigue.intValue -= 3
                2 -> character.combat.specFatigue.intValue -= 6
                3 -> character.combat.specFatigue.intValue -= 9
            }

            character.combat.updateFatigue()
        }
    )

    val uncommonSize = Advantage(
        saveTag = "uncommonSize",
        name = R.string.uncommonSize,
        description = R.string.uncommonSizeDesc,
        effect = R.string.uncommonSizeEff,
        restriction = null,
        special = null,
        options = R.array.sizeList,
        picked = 0,
        multPicked = null,
        cost = listOf(1),
        pickedCost = 0,
        onTake = {character, input, _ ->
            character.changeSize(sizeInput = input!!)
        },
        onRemove = {character, input, _ ->
            character.changeSize(sizeInput = 9 - input!!)
        }
    )

    private val startingWealth = Advantage(
        saveTag = "startingWealth",
        name = R.string.startingWealth,
        description = R.string.startWealthDesc,
        effect = R.string.startWealthEff,
        restriction = R.string.startWealthRestriction,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(1, 2, 3),
        pickedCost = 0,
        onTake = {character, _, cost ->
            //set bonus wealth to the indicated value
            when(cost){
                1 -> character.inventory.setWealthBonus(bonusWealth = 2000)
                2 -> character.inventory.setWealthBonus(bonusWealth = 5000)
                3 -> character.inventory.setWealthBonus(bonusWealth = 10000)
            }
        },
        onRemove = {character, _, _ ->
            //remove all bonus wealth
            character.inventory.setWealthBonus(bonusWealth = 0)
        }
    )

    private val increasedRegeneration = Advantage(
        saveTag = "increasedRegeneration",
        name = R.string.increaseRegen,
        description = R.string.regenDesc,
        effect = R.string.regenEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(1, 2, 3),
        pickedCost = 0,
        onTake = {character, _, cost ->
            //set regeneration bonus value
            when(cost){
                1 -> character.combat.specRegen.intValue += 2
                2 -> character.combat.specRegen.intValue += 4
                3 -> character.combat.specRegen.intValue += 6
            }

            character.combat.updateRegeneration()
        },
        onRemove = {character, _, cost ->
            //remove regeneration bonus
            when (cost) {
                1 -> character.combat.specRegen.intValue -= 2
                2 -> character.combat.specRegen.intValue -= 4
                3 -> character.combat.specRegen.intValue -= 6
            }

            character.combat.updateRegeneration()
        }
    )

    private val elan = Advantage(
        saveTag = "elan",
        name = R.string.elan,
        description = R.string.elanDesc,
        effect = R.string.elanEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(1, 2, 3),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    private val painImmunity = Advantage(
        saveTag = "painImmunity",
        name = R.string.painImmunity,
        description = R.string.painImmuneDesc,
        effect = R.string.painImmuneEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(1),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    val gift = Advantage(
        saveTag = "gift",
        name = R.string.gift,
        description = R.string.giftDesc,
        effect = R.string.giftEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(2),
        pickedCost = 0,
        onTake = null,
        onRemove = {character, _, _ ->
            character.magic.loseMagic()
        }
    )

    private val seeSupernatural = Advantage(
        saveTag = "seeSupernatural",
        name = R.string.seeSupernatural,
        description = R.string.seeSuperDesc,
        effect = R.string.seeSuperEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(1),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    private val nightVision = Advantage(
        saveTag = "nightVision",
        name = R.string.nightVision,
        description = R.string.nightVisDesc,
        effect = R.string.nightVisEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(1),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    private val fortunate = Advantage(
        saveTag = "fortunate",
        name = R.string.fortunate,
        description = R.string.fortuneDesc,
        effect = R.string.fortuneEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(1),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    private val freePsychicDiscipline = Advantage(
        saveTag = "allPsyDisciplines",
        name = R.string.allPsyDisciplines,
        description = R.string.allDiscDesc,
        effect = R.string.allDiscEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(2),
        pickedCost = 0,
        onTake = {character, _, _ ->
            //remove any individual discipline access acquired
            val removable = character.advantageRecord.getAdvantage(advantageString = "psyDisciplineAccess")
            if(removable != null)
                character.advantageRecord.removeAdvantage(advantage = removable)

            //add all disciplines to character's access
            character.psychic.legalDisciplines.addAll(elements = character.objectDB.psyLibrary.allDisciplines)
        },
        onRemove = {character, _, _ ->
            //remove all disciplines from character's access
            character.objectDB.psyLibrary.allDisciplines.forEach {discipline ->
                character.psychic.removeLegalDiscipline(discipline = discipline)
            }
        }
    )

    private val quickReflexes = Advantage(
        saveTag = "quickReflexes",
        name = R.string.quickReflexes,
        description = R.string.reflexDesc,
        effect = R.string.reflexEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(1, 2, 3),
        pickedCost = 0,
        onTake = {character, _, cost ->
            //apply desired initiative bonus to the character
            when(cost){
                1 -> character.combat.changeSpecInitiative(initSpecial = 25)
                2 -> character.combat.changeSpecInitiative(initSpecial = 45)
                3 -> character.combat.changeSpecInitiative(initSpecial = 60)
            }
        },
        onRemove = {character, _, cost ->
            //remove bonus from the character's initiative
            when (cost) {
                1 -> character.combat.changeSpecInitiative(initSpecial = -25)
                2 -> character.combat.changeSpecInitiative(initSpecial = -45)
                3 -> character.combat.changeSpecInitiative(initSpecial = -60)
            }
        }
    )

    private val learning = Advantage(
        saveTag = "learning",
        name = R.string.learning,
        description = R.string.learningDesc,
        effect = R.string.learningEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(1, 2, 3),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    private val naturalLearner = Advantage(
        saveTag = "naturalLearner",
        name = R.string.naturalLearner,
        description = R.string.natLearnDesc,
        effect = R.string.natLearnEff,
        restriction = null,
        special = null,
        options = R.array.secondaryCharacteristics,
        picked = 0,
        multPicked = null,
        cost = listOf(1, 2, 3),
        pickedCost = 0,
        onTake = {character, input, cost ->
            //apply the desired bonus to the indicated characteristic
            val characteristic =
                if(input!! < 38) character.secondaryList.fullList()[input]
                else character.secondaryList.getAllCustoms()[input - 38]

            when(cost){
                1 -> characteristic.setSpecialPerLevel(lvlBonus = 10)
                2 -> characteristic.setSpecialPerLevel(lvlBonus = 20)
                3 -> characteristic.setSpecialPerLevel(lvlBonus = 30)
            }
        },
        onRemove = {character, input, cost ->
            //remove the bonus from the characteristic
            val characteristic =
                if(input!! < 38) character.secondaryList.fullList()[input]
                else character.secondaryList.getAllCustoms()[input - 38]

            when (cost) {
                1 -> characteristic.setSpecialPerLevel(lvlBonus = -10)
                2 -> characteristic.setSpecialPerLevel(lvlBonus = -20)
                3 -> characteristic.setSpecialPerLevel(lvlBonus = -30)
            }
        }
    )

    private val fieldLearner = Advantage(
        saveTag = "fieldLearner",
        name = R.string.fieldLearner,
        description = R.string.fieldLearnDesc,
        effect = R.string.fieldLearnEff,
        restriction = null,
        special = null,
        options = R.array.secondaryFields,
        picked = 0,
        multPicked = null,
        cost = listOf(2, 3),
        pickedCost = 0,
        onTake = {character, input, cost ->
            //add level bonuses to the indicated field characteristics
            val charList = character.secondaryList.intToField(fieldInteger = input!!)
            charList.forEach{secondary ->
                when(cost){
                    2 -> secondary.setSpecialPerLevel(lvlBonus = 5)
                    3 -> secondary.setSpecialPerLevel(lvlBonus = 10)
                }
            }
        },
        onRemove = {character, input, cost ->
            //remove level bonuses to the indicated field characteristics
            val charList = character.secondaryList.intToField(fieldInteger = input!!)
            charList.forEach {secondary ->
                when (cost) {
                    2 -> secondary.setSpecialPerLevel(lvlBonus = -5)
                    3 -> secondary.setSpecialPerLevel(lvlBonus = -10)
                }
            }
        }
    )

    private val exceptionalMagicResistance = Advantage(
        saveTag = "exceptionalMagRes",
        name = R.string.exceptionalMagRes,
        description = R.string.exMagResDesc,
        effect = R.string.exMagResEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(1, 2),
        pickedCost = 0,
        onTake = {character, _, cost ->
            //add desired bonus to magic resistance
            when(cost){
                1 -> character.combat.magicRes.setSpecial(specChange = 25)
                2 -> character.combat.magicRes.setSpecial(specChange = 50)
            }
        },
        onRemove = {character, _, cost ->
            //remove previous bonus to magic resistance
            when (cost) {
                1 -> character.combat.magicRes.setSpecial(specChange = -25)
                2 -> character.combat.magicRes.setSpecial(specChange = -50)
            }
        }
    )

    private val exceptionalPhysicalResistance = Advantage(
        saveTag = "exceptionalPhysRes",
        name = R.string.exceptionalPhysRes,
        description = R.string.exPhysResDesc,
        effect = R.string.exPhysResEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(1, 2),
        pickedCost = 0,
        onTake = {character, _, cost ->
            when(cost){
                //set bonuses for the appropriate resistances
                1 -> {
                    character.combat.physicalRes.setSpecial(specChange = 25)
                    character.combat.venomRes.setSpecial(specChange = 25)
                    character.combat.diseaseRes.setSpecial(specChange = 25)
                }

                2 -> {
                    character.combat.physicalRes.setSpecial(specChange = 50)
                    character.combat.venomRes.setSpecial(specChange = 50)
                    character.combat.diseaseRes.setSpecial(specChange = 50)
                }
            }
        },
        onRemove = {character, _, cost ->
            when (cost) {
                //remove bonuses from the appropriate resistances
                1 -> {
                    character.combat.physicalRes.setSpecial(specChange = -25)
                    character.combat.venomRes.setSpecial(specChange = -25)
                    character.combat.diseaseRes.setSpecial(specChange = -25)
                }

                2 -> {
                    character.combat.physicalRes.setSpecial(specChange = -50)
                    character.combat.venomRes.setSpecial(specChange = -50)
                    character.combat.diseaseRes.setSpecial(specChange = -50)
                }
            }
        }
    )

    private val exceptionalPsychicResistance = Advantage(
        saveTag = "exceptionalPsyRes",
        name = R.string.exceptionalPsyRes,
        description = R.string.exPsyResDesc,
        effect = R.string.exPsyResEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(1, 2),
        pickedCost = 0,
        onTake = {character, _, cost ->
            //give bonus points to psychic resistance
            when(cost){
                1 -> character.combat.psychicRes.setSpecial(specChange = 25)
                2 -> character.combat.psychicRes.setSpecial(specChange = 50)
            }
        },
        onRemove = {character, _, cost ->
            //remove bonus points to psychic resistance
            when (cost) {
                1 -> character.combat.psychicRes.setSpecial(specChange = -25)
                2 -> character.combat.psychicRes.setSpecial(specChange = -50)
            }
        }
    )

    private val lightSleeper = Advantage(
        saveTag = "lightSleeper",
        name = R.string.lightSleeper,
        description = R.string.lightSleepDesc,
        effect = R.string.lightSleepEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(1),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    val advantages = listOf(
        gift, psyDisciplineAccess, freePsychicDiscipline, naturalPsychicPower, characteristicReroll,
        characteristicPoint, characteristicToNine, exceptionalPhysicalResistance, exceptionalMagicResistance,
        exceptionalPsychicResistance, subjectAptitude, fieldAptitude, naturalLearner, fieldLearner,
        jackOfAllTrades, ambidextrous, increasedRegeneration, quickReflexes, naturalArmor, mysticalArmor,
        painImmunity, untiring, uncommonSize, martialMastery, kiRecovery, goodLuck, fortunate,
        startingWealth, artifact, animalAffinity, charm, disquieting, acuteSenses, nightVision,
        dangerSense, seeSupernatural, lightSleeper, beenAround, learning, elan
    )





    private val badLuck = Advantage(
        saveTag = "badLuck",
        name = R.string.badLuck,
        description = R.string.badLuckDesc,
        effect = R.string.badLuckEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(-1),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    val blind = Advantage(
        saveTag = "blind",
        name = R.string.blind,
        description = R.string.blindDesc,
        effect = R.string.blindEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(-2),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    val deafness = Advantage(
        saveTag = "deafness",
        name = R.string.deafness,
        description = R.string.deafDesc,
        effect = R.string.deafEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(-1),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    val mute = Advantage(
        saveTag = "mute",
        name = R.string.mute,
        description = R.string.muteDesc,
        effect = R.string.muteEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(-1),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    val nearsighted = Advantage(
        saveTag = "nearsighted",
        name = R.string.nearsighted,
        description = R.string.nearsightDesc,
        effect = R.string.nearsightEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(-1),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    val exclusiveWeapon = Advantage(
        saveTag = "exclusiveWeapon",
        name = R.string.exclusiveWeapon,
        description = R.string.exclusiveDesc,
        effect = R.string.exclusiveEff,
        restriction = R.string.exclusiveRestriction,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(-1),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    private val severeAllergy = Advantage(
        saveTag = "severeAllergy",
        name = R.string.severeAllergy,
        description = R.string.allergyDesc,
        effect = R.string.allergyEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(-1),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    private val seriousVice = Advantage(
        saveTag = "seriousVice",
        name = R.string.seriousVice,
        description = R.string.viceDesc,
        effect = R.string.viceEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(-1),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    val atrophiedLimb = Advantage(
        saveTag = "atrophiedLimb",
        name = R.string.atrophiedLimb,
        description = R.string.atrophyDesc,
        effect = R.string.atrophyEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(-1),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    val seriousIllness = Advantage(
        saveTag = "seriousIllness",
        name = R.string.seriousIllness,
        description = R.string.illnessDesc,
        effect = R.string.illnessEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(-2),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    val physicalWeakness = Advantage(
        saveTag = "physWeakness",
        name = R.string.physWeakness,
        description = R.string.physWeakDesc,
        effect = R.string.physWeakEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(-1),
        pickedCost = 0,
        onTake = {character, _, _ ->
            //apply penalty to physical resistance
            character.combat.physicalRes.setMultiplier(multVal = 0.5)
        },
        onRemove = {character, _, _ ->
            //remove penalty to physical resistance
            character.combat.physicalRes.setMultiplier(multVal = 1.0)
        }
    )

    private val deepSleeper = Advantage(
        saveTag = "deepSleeper",
        name = R.string.deepSleeper,
        description = R.string.deepSleepDesc,
        effect = R.string.deepSleepEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(-1),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    val deductCharacteristic = Advantage(
        saveTag = "charDeduction",
        name = R.string.deductChar,
        description = R.string.charDeductDesc,
        effect = R.string.charDeductEff,
        restriction = R.string.charDeductRestriction,
        special = null,
        options = R.array.primaryCharArray,
        picked = 0,
        multPicked = null,
        cost = listOf(-1),
        pickedCost = 0,
        onTake = {character, input, _ ->
            //apply penalty to stat
            character.primaryList.allPrimaries()[input!!].setBonus(bonusInput = -2)
        },
        onRemove = {character, input, _ ->
            //remove penalty from stat
            character.primaryList.allPrimaries()[input!!].setBonus(bonusInput = 2)
        }
    )

    private val unfortunate = Advantage(
        saveTag = "unfortunate",
        name = R.string.unfortunate,
        description = R.string.unfortuneDesc,
        effect = R.string.unfortuneEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(-1),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    private val easilyPossessed = Advantage(
        saveTag = "easilyPossessed",
        name = R.string.easilyPossessed,
        description = R.string.possessedDesc,
        effect = R.string.possessedEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(-1),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    private val exhausted = Advantage(
        saveTag = "exhausted",
        name = R.string.exhausted,
        description = R.string.exhaustDesc,
        effect = R.string.exhaustEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(-1),
        pickedCost = 0,
        onTake = {character, _, _ ->
            //apply fatigue penalty
            character.combat.specFatigue.intValue -= 1
            character.combat.updateFatigue()
        },
        onRemove = {character, _, _ ->
            //remove fatigue penalty
            character.combat.specFatigue.intValue += 1
            character.combat.updateFatigue()
        }
    )

    private val severePhobia = Advantage(
        saveTag = "phobia",
        name = R.string.severePhobia,
        description = R.string.phobiaDesc,
        effect = R.string.phobiaEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(-1),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    private val painVulnerability = Advantage(
        saveTag = "painVulnerability",
        name = R.string.painVulnerability,
        description = R.string.painVulnDesc,
        effect = R.string.painVulnEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(-1),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    val sickly = Advantage(
        saveTag = "sickly",
        name = R.string.sickly,
        description = R.string.sicklyDesc,
        effect = R.string.sicklyEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(-1),
        pickedCost = 0,
        onTake = {character, _, _ ->
            //apply penalty to disease resistance
            character.combat.diseaseRes.setMultiplier(multVal = 0.5)
        },
        onRemove = {character, _, _ ->
            //remove penalty from disease resistance
            character.combat.diseaseRes.setMultiplier(multVal = 1.0)
        }
    )

    private val slowHealer = Advantage(
        saveTag = "slowHeal",
        name = R.string.slowHealer,
        description = R.string.slowHealDesc,
        effect = R.string.slowHealEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(-1),
        pickedCost = 0,
        onTake = {character, _, _ ->
            //apply regeneration penalty
            character.combat.specRegen.intValue -= 1
            character.combat.updateRegeneration()
        },
        onRemove = {character, _, _ ->
            //remove regeneration penalty
            character.combat.specRegen.intValue += 1
            character.combat.updateRegeneration()
        }
    )

    private val slowLearner = Advantage(
        saveTag = "slowLearner",
        name = R.string.slowLearner,
        description = R.string.slowLearnDesc,
        effect = R.string.slowLearnEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(-1, -2),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    private val slowReactions = Advantage(
        saveTag = "slowReactions",
        name = R.string.slowReactions,
        description = R.string.slowReactDesc,
        effect = R.string.slowReactEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(-1, -2),
        pickedCost = 0,
        onTake = {character, _, cost ->
            //apply desired initiative penalty
            when(cost){
                -1 -> character.combat.specInitiative.intValue -= 30
                -2 -> character.combat.specInitiative.intValue -= 60
            }

            character.combat.updateInitiative()
        },
        onRemove = {character, _, cost ->
            //remove desired initiative penalty
            when (cost) {
                -1 -> character.combat.specInitiative.intValue += 30
                -2 -> character.combat.specInitiative.intValue += 60
            }

            character.combat.updateInitiative()
        }
    )

    val magicSusceptibility = Advantage(
        saveTag = "magSusceptibility",
        name = R.string.magSusceptibility,
        description = R.string.magSusceptDesc,
        effect = R.string.magSusceptEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(-1),
        pickedCost = 0,
        onTake = {character, _, _ ->
            //apply magic resistance penalty
            character.combat.magicRes.setMultiplier(multVal = 0.5)
        },
        onRemove = {character, _, _ ->
            //remove magic resistance penalty
            character.combat.magicRes.setMultiplier(multVal = 1.0)
        }
    )

    val poisonSusceptibility = Advantage(
        saveTag = "venSusceptibility",
        name = R.string.poisonSusceptibility,
        description = R.string.venSusceptDesc,
        effect = R.string.venSusceptEff,
        restriction = null,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(-1),
        pickedCost = 0,
        onTake = {character, _, _ ->
            //apply poison resistance penalty
            character.combat.venomRes.setMultiplier(multVal = 0.5)
        },
        onRemove = {character, _, _ ->
            //remove poison resistance penalty
            character.combat.venomRes.setMultiplier(multVal = 1.0)
        }
    )

    val unattractive = Advantage(
        saveTag = "unattractive",
        name = R.string.unattractive,
        description = R.string.unattractiveDesc,
        effect = R.string.unattractiveEff,
        restriction = R.string.unattractiveRestriction,
        special = null,
        options = null,
        picked = null,
        multPicked = null,
        cost = listOf(-1),
        pickedCost = 0,
        onTake = {character, _, _ ->
            character.setAppearance(newAppearance = 2)
        },
        onRemove = null
    )

    private val temperatureVulnerability = Advantage(
        saveTag = "tempVulnerability",
        name = R.string.tempVulnerability,
        description = R.string.tempVulnDesc,
        effect = R.string.tempVulnEff,
        restriction = null,
        special = null,
        options = R.array.tempVulnerableOptions,
        picked = 0,
        multPicked = null,
        cost = listOf(-1),
        pickedCost = 0,
        onTake = null,
        onRemove = null
    )

    val disadvantages = listOf(
        deductCharacteristic, physicalWeakness, poisonSusceptibility, sickly, magicSusceptibility,
        painVulnerability, easilyPossessed, temperatureVulnerability, exclusiveWeapon, slowHealer,
        slowReactions, exhausted, badLuck, unfortunate, unattractive, nearsighted, blind, deafness,
        mute, atrophiedLimb, severePhobia, severeAllergy, seriousVice, seriousIllness, deepSleeper,
        slowLearner
    )
}