package com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_items

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_types.Advantage

/**
 * List of common advantages and disadvantages a character may acquire.
 *
 * @param charInstance object holding the character's stats
 */
class CommonAdvantages(private val charInstance: BaseCharacter) {
    val characteristicPoint = Advantage(
        "characteristicPoint",
        R.string.addCharPoint,
        R.string.charPointDesc,
        R.string.charPointEffect,
        R.string.charPointRestriction,
        R.string.advantageSpecial,
        R.array.primaryCharArray,
        0,
        null,
        listOf(1),
        0,
        { input, _ ->
            charInstance.primaryList.allPrimaries[input!!].setBonus(1)
        }
    ) { input, _ ->
        charInstance.primaryList.allPrimaries[input!!].setBonus(-1)
    }

    val acuteSenses = Advantage(
        "acuteSenses",
        R.string.acuteSenses,
        R.string.acuteDesc,
        R.string.acuteEffect,
        null,
        null,
        null,
        null,
        null,
        listOf(1),
        0,
        {_, _ ->
            charInstance.secondaryList.notice.setSpecial(30)
            charInstance.secondaryList.search.setSpecial(30)
        }
    ) { _, _ ->
        charInstance.secondaryList.notice.setSpecial(-30)
        charInstance.secondaryList.search.setSpecial(-30)
    }

    val artifact = Advantage(
        "artifact",
        R.string.artifact,
        R.string.artifactDesc,
        R.string.artifactEffect,
        null,
        null,
        null,
        null,
        null,
        listOf(1, 2, 3),
        0,
        null,
        null
    )

    val ambidextrous = Advantage(
        "ambidextrous",
        R.string.ambidextrous,
        R.string.ambiDesc,
        R.string.ambiEffect,
        null,
        null,
        null,
        null,
        null,
        listOf(1),
        0,
        null,
        null
    )

    val characteristicToNine = Advantage(
        "characteristicToNine",
        R.string.charToNine,
        R.string.charTo9Desc,
        R.string.charTo9Effect,
        null,
        R.string.advantageSpecial,
        R.array.primaryCharArray,
        0,
        null,
        listOf(2),
        0,
        {input, _ ->
            charInstance.primaryList.allPrimaries[input!!].setInput(9)
        },
        null
    )

    val psyDisciplineAccess = Advantage(
        "psyDisciplineAccess",
        R.string.psyDiscAccess,
        R.string.disciplineAccDesc,
        R.string.disciplineAccEff,
        null,
        null,
        R.array.disciplineNames,
        0,
        null,
        listOf(1),
        0,
        {input, _ ->
            charInstance.psychic.legalDisciplines.add(charInstance.psychic.allDisciplines[input!!])
            charInstance.psychic.legalDisciplines.add(charInstance.psychic.matrixPowers)
        }
    ) { input, _ ->
        charInstance.psychic.removeLegalDisciplineFromInt(input!!)
    }

    val charm = Advantage(
        "charm",
        R.string.charm,
        R.string.charmDesc,
        R.string.charmEffect,
        null,
        null,
        null,
        null,
        null,
        listOf(1),
        0,
        null,
        null
    )

    val disquieting = Advantage(
        "disquieting",
        R.string.disquieting,
        R.string.disquietDesc,
        R.string.disquietEffect,
        null,
        null,
        null,
        null,
        null,
        listOf(1),
        0,
        null,
        null
    )

    val animalAffinity = Advantage(
        "animalAffinity",
        R.string.animalAffinity,
        R.string.affinityDesc,
        R.string.affinityEffect,
        null,
        null,
        null,
        null,
        null,
        listOf(1),
        0,
        null,
        null
    )

    val dangerSense = Advantage(
        "dangerSense",
        R.string.dangerSense,
        R.string.dangerSenseDesc,
        R.string.dangerSenseEff,
        null,
        null,
        null,
        null,
        null,
        listOf(2),
        0,
        null,
        null
    )

    val beenAround = Advantage(
        "beenAround",
        R.string.beenAround,
        R.string.beenAroundDesc,
        R.string.beenAroundEff,
        null,
        null,
        null,
        null,
        null,
        listOf(1, 2, 3),
        0,
        null,
        null
    )

    val subjectAptitude = Advantage(
        "subjectAptitude",
        R.string.subjectAptitude,
        R.string.subjAptDesc,
        R.string.subjAptEff,
        R.string.subjAptRestriction,
        null,
        R.array.secondaryCharacteristics,
        0,
        null,
        listOf(1, 2),
        0,
        {input, cost ->
            charInstance.secondaryList.fullList[input!!].setDevelopmentDeduction(cost)
            charInstance.updateTotalSpent()
        }
    ) { input, cost ->
        charInstance.secondaryList.fullList[input!!].setDevelopmentDeduction(cost * -1)
        charInstance.updateTotalSpent()
    }

    val naturalPsychicPower = Advantage(
        "naturalPsychicPower",
        R.string.psyPowerAccess,
        R.string.natPsyPowDesc,
        R.string.natPsyPowEff,
        null,
        null,
        R.array.powerNames,
        0,
        null,
        listOf(1, 2, 3),
        0,
        null,
        null
    )

    val fieldAptitude = Advantage(
        "fieldAptitude",
        R.string.fieldAptitude,
        R.string.fieldAptDesc,
        R.string.fieldAptEff,
        R.string.fieldAptRestriction,
        null,
        R.array.secondaryFields,
        0,
        null,
        listOf(2),
        0,
        {input, _ ->
            val field = charInstance.secondaryList.intToField(input!!)
            field.forEach{it.setDevelopmentDeduction(1)}
            charInstance.updateTotalSpent()
        }
    ) { input, _ ->
        val field = charInstance.secondaryList.intToField(input!!)
        field.forEach { it.setDevelopmentDeduction(-1) }
        charInstance.updateTotalSpent()
    }

    val characteristicReroll = Advantage(
        "characteristicReroll",
        R.string.repeatCharRoll,
        R.string.charRerollDesc,
        R.string.charRerollEff,
        R.string.charRerollRestriction,
        R.string.advantageSpecial,
        R.array.primaryCharArray,
        0,
        null,
        listOf(1),
        0,
        null,
        null
    )

    val martialMastery = Advantage(
        "martialMastery",
        R.string.martialMastery,
        R.string.mmDesc,
        R.string.mmEff,
        null,
        null,
        null,
        null,
        null,
        listOf(1, 2, 3),
        0,
        {_, cost ->
            when(cost){
                1 -> charInstance.ki.updateMKSpec(40)
                2 -> charInstance.ki.updateMKSpec(80)
                3 -> charInstance.ki.updateMKSpec(120)
            }
        }
    ) { _, cost ->
        when (cost) {
            1 -> charInstance.ki.updateMKSpec(-40)
            2 -> charInstance.ki.updateMKSpec(-80)
            3 -> charInstance.ki.updateMKSpec(-120)
        }
    }

    val goodLuck = Advantage(
        "goodLuck",
        R.string.goodLuck,
        R.string.goodLuckDesc,
        R.string.goodLuckEff,
        null,
        null,
        null,
        null,
        null,
        listOf(1),
        0,
        null,
        null
    )

    val kiRecovery = Advantage(
        "kiRecovery",
        R.string.kiRecovery,
        R.string.kiRecoverDesc,
        R.string.kiRecoverEff,
        null,
        null,
        null,
        null,
        null,
        listOf(1, 2, 3),
        0,
        null,
        null
    )

    val jackOfAllTrades = Advantage(
        "jackOfAllTrades",
        R.string.jackTrades,
        R.string.jackDesc,
        R.string.jackEff,
        null,
        null,
        null,
        null,
        null,
        listOf(2),
        0,
        {_, _ ->
            charInstance.secondaryList.allTradesTaken.value = true
            charInstance.secondaryList.fullList.forEach{it.refreshTotal()}
        }
    ) { _, _ ->
        charInstance.secondaryList.allTradesTaken.value = false
        charInstance.secondaryList.fullList.forEach { it.refreshTotal() }
    }

    val naturalArmor = Advantage(
        "naturalArmor",
        R.string.naturalArmor,
        R.string.natArmorDesc,
        R.string.natArmorEff,
        null,
        null,
        null,
        null,
        null,
        listOf(1),
        0,
        null,
        null
    )

    val mysticalArmor = Advantage(
        "mysticalArmor",
        R.string.mysticArmor,
        R.string.mysArmorDesc,
        R.string.mysArmorEff,
        null,
        null,
        null,
        null,
        null,
        listOf(1),
        0,
        null,
        null
    )

    val untiring = Advantage(
        "untiring",
        R.string.untiring,
        R.string.untiringDesc,
        R.string.untiringEff,
        null,
        null,
        null,
        null,
        null,
        listOf(1, 2, 3),
        0,
        {_, cost ->
            when(cost){
                1 -> charInstance.combat.specFatigue.value += 3
                2 -> charInstance.combat.specFatigue.value += 6
                3 -> charInstance.combat.specFatigue.value += 9
            }

            charInstance.combat.updateFatigue()
        }
    ) { _, cost ->
        when (cost) {
            1 -> charInstance.combat.specFatigue.value -= 3
            2 -> charInstance.combat.specFatigue.value -= 6
            3 -> charInstance.combat.specFatigue.value -= 9
        }

        charInstance.combat.updateFatigue()
    }

    val uncommonSize = Advantage(
        "uncommonSize",
        R.string.uncommonSize,
        R.string.uncommonSizeDesc,
        R.string.uncommonSizeEff,
        null,
        null,
        R.array.sizeList,
        0,
        null,
        listOf(1),
        0,
        {input, _ ->
            charInstance.changeSize(input!!)
        }
    ) { input, _ ->
        charInstance.changeSize(9 - input!!)
    }

    val startingWealth = Advantage(
        "startingWealth",
        R.string.startingWealth,
        R.string.startWealthDesc,
        R.string.startWealthEff,
        R.string.startWealthRestriction,
        null,
        null,
        null,
        null,
        listOf(1, 2, 3),
        0,
        {_, cost ->
            when(cost){
                1 -> charInstance.inventory.setWealthBonus(2000)
                2 -> charInstance.inventory.setWealthBonus(5000)
                3 -> charInstance.inventory.setWealthBonus(10000)
            }
        }
    ) { _, _ -> charInstance.inventory.setWealthBonus(0) }

    val increasedRegeneration = Advantage(
        "increasedRegeneration",
        R.string.increaseRegen,
        R.string.regenDesc,
        R.string.regenEff,
        null,
        null,
        null,
        null,
        null,
        listOf(1, 2, 3),
        0,
        {_, cost ->
            when(cost){
                1 -> charInstance.combat.specRegen.value += 2
                2 -> charInstance.combat.specRegen.value += 4
                3 -> charInstance.combat.specRegen.value += 6
            }

            charInstance.combat.updateRegeneration()
        }
    ) { _, cost ->
        when (cost) {
            1 -> charInstance.combat.specRegen.value -= 2
            2 -> charInstance.combat.specRegen.value -= 4
            3 -> charInstance.combat.specRegen.value -= 6
        }

        charInstance.combat.updateRegeneration()
    }

    val elan = Advantage(
        "elan",
        R.string.elan,
        R.string.elanDesc,
        R.string.elanEff,
        null,
        null,
        null,
        null,
        null,
        listOf(1, 2, 3),
        0,
        null,
        null
    )

    val painImmunity = Advantage(
        "painImmunity",
        R.string.painImmunity,
        R.string.painImmuneDesc,
        R.string.painImmuneEff,
        null,
        null,
        null,
        null,
        null,
        listOf(1),
        0,
        null,
        null
    )

    val gift = Advantage(
        "gift",
        R.string.gift,
        R.string.giftDesc,
        R.string.giftEff,
        null,
        null,
        null,
        null,
        null,
        listOf(2),
        0,
        null
    ) { _, _ ->
        charInstance.magic.loseMagic()
    }

    val seeSupernatural = Advantage(
        "seeSupernatural",
        R.string.seeSupernatural,
        R.string.seeSuperDesc,
        R.string.seeSuperEff,
        null,
        null,
        null,
        null,
        null,
        listOf(1),
        0,
        null,
        null
    )

    val nightVision = Advantage(
        "nightVision",
        R.string.nightVision,
        R.string.nightVisDesc,
        R.string.nightVisEff,
        null,
        null,
        null,
        null,
        null,
        listOf(1),
        0,
        null,
        null
    )

    val fortunate = Advantage(
        "fortunate",
        R.string.fortunate,
        R.string.fortuneDesc,
        R.string.fortuneEff,
        null,
        null,
        null,
        null,
        null,
        listOf(1),
        0,
        null,
        null
    )

    val freePsychicDiscipline = Advantage(
        "allPsyDisciplines",
        R.string.allPsyDisciplines,
        R.string.allDiscDesc,
        R.string.allDiscEff,
        null,
        null,
        null,
        null,
        null,
        listOf(2),
        0,
        {_, _ ->
            val removable = charInstance.advantageRecord.getAdvantage("psyDisciplineAccess")
            if(removable != null)
                charInstance.advantageRecord.removeAdvantage(removable)

            charInstance.psychic.legalDisciplines.addAll(charInstance.psychic.allDisciplines)
        }
    ) { _, _ ->
        charInstance.psychic.allDisciplines.forEach {
            charInstance.psychic.removeLegalDiscipline(it)
        }
    }

    val quickReflexes = Advantage(
        "quickReflexes",
        R.string.quickReflexes,
        R.string.reflexDesc,
        R.string.reflexEff,
        null,
        null,
        null,
        null,
        null,
        listOf(1, 2, 3),
        0,
        {_, cost ->
            when(cost){
                1 -> charInstance.combat.changeSpecInitiative(25)
                2 -> charInstance.combat.changeSpecInitiative(45)
                3 -> charInstance.combat.changeSpecInitiative(60)
            }
        }
    ) { _, cost ->
        when (cost) {
            1 -> charInstance.combat.changeSpecInitiative(-25)
            2 -> charInstance.combat.changeSpecInitiative(-45)
            3 -> charInstance.combat.changeSpecInitiative(-60)
        }
    }

    val learning = Advantage(
        "learning",
        R.string.learning,
        R.string.learningDesc,
        R.string.learningEff,
        null,
        null,
        null,
        null,
        null,
        listOf(1, 2, 3),
        0,
        null,
        null
    )

    val naturalLearner = Advantage(
        "naturalLearner",
        R.string.naturalLearner,
        R.string.natLearnDesc,
        R.string.natLearnEff,
        null,
        null,
        R.array.secondaryCharacteristics,
        0,
        null,
        listOf(1, 2, 3),
        0,
        {input, cost ->
            val characteristic = charInstance.secondaryList.fullList[input!!]

            when(cost){
                1 -> characteristic.setSpecialPerLevel(10)
                2 -> characteristic.setSpecialPerLevel(20)
                3 -> characteristic.setSpecialPerLevel(30)
            }
        }
    ) { input, cost ->
        val characteristic = charInstance.secondaryList.fullList[input!!]

        when (cost) {
            1 -> characteristic.setSpecialPerLevel(-10)
            2 -> characteristic.setSpecialPerLevel(-20)
            3 -> characteristic.setSpecialPerLevel(-30)
        }
    }

    val fieldLearner = Advantage(
        "fieldLearner",
        R.string.fieldLearner,
        R.string.fieldLearnDesc,
        R.string.fieldLearnEff,
        null,
        null,
        R.array.secondaryFields,
        0,
        null,
        listOf(2, 3),
        0,
        {input, cost ->
            val charList = charInstance.secondaryList.intToField(input!!)
            charList.forEach{
                when(cost){
                    2 -> it.setSpecialPerLevel(5)
                    3 -> it.setSpecialPerLevel(10)
                }
            }
        }
    ) { input, cost ->
        val charList = charInstance.secondaryList.intToField(input!!)
        charList.forEach {
            when (cost) {
                2 -> it.setSpecialPerLevel(-5)
                3 -> it.setSpecialPerLevel(-10)
            }
        }
    }

    val exceptionalMagicResistance = Advantage(
        "exceptionalMagRes",
        R.string.exceptionalMagRes,
        R.string.exMagResDesc,
        R.string.exMagResEff,
        null,
        null,
        null,
        null,
        null,
        listOf(1, 2),
        0,
        {_, cost ->
            when(cost){
                1 -> charInstance.combat.magicRes.setSpecial(25)
                2 -> charInstance.combat.magicRes.setSpecial(50)
            }
        }
    ) { _, cost ->
        when (cost) {
            1 -> charInstance.combat.magicRes.setSpecial(-25)
            2 -> charInstance.combat.magicRes.setSpecial(-50)
        }
    }

    val exceptionalPhysicalResistance = Advantage(
        "exceptionalPhysRes",
        R.string.exceptionalPhysRes,
        R.string.exPhysResDesc,
        R.string.exPhysResEff,
        null,
        null,
        null,
        null,
        null,
        listOf(1, 2),
        0,
        {_, cost ->
            when(cost){
                1 -> {
                    charInstance.combat.physicalRes.setSpecial(25)
                    charInstance.combat.venomRes.setSpecial(25)
                    charInstance.combat.diseaseRes.setSpecial(25)
                }
                2 -> {
                    charInstance.combat.physicalRes.setSpecial(50)
                    charInstance.combat.venomRes.setSpecial(50)
                    charInstance.combat.diseaseRes.setSpecial(50)
                }
            }
        }
    ) { _, cost ->
        when (cost) {
            1 -> {
                charInstance.combat.physicalRes.setSpecial(-25)
                charInstance.combat.venomRes.setSpecial(-25)
                charInstance.combat.diseaseRes.setSpecial(-25)
            }

            2 -> {
                charInstance.combat.physicalRes.setSpecial(-50)
                charInstance.combat.venomRes.setSpecial(-50)
                charInstance.combat.diseaseRes.setSpecial(-50)
            }
        }
    }

    val exceptionalPsychicResistance = Advantage(
        "exceptionalPsyRes",
        R.string.exceptionalPsyRes,
        R.string.exPsyResDesc,
        R.string.exPsyResEff,
        null,
        null,
        null,
        null,
        null,
        listOf(1, 2),
        0,
        {_, cost ->
            when(cost){
                1 -> charInstance.combat.psychicRes.setSpecial(25)
                2 -> charInstance.combat.psychicRes.setSpecial(50)
            }
        }
    ) { _, cost ->
        when (cost) {
            1 -> charInstance.combat.psychicRes.setSpecial(-25)
            2 -> charInstance.combat.psychicRes.setSpecial(-50)
        }
    }

    val lightSleeper = Advantage(
        "lightSleeper",
        R.string.lightSleeper,
        R.string.lightSleepDesc,
        R.string.lightSleepEff,
        null,
        null,
        null,
        null,
        null,
        listOf(1),
        0,
        null,
        null
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





    val badLuck = Advantage(
        "badLuck",
        R.string.badLuck,
        R.string.badLuckDesc,
        R.string.badLuckEff,
        null,
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val blind = Advantage(
        "blind",
        R.string.blind,
        R.string.blindDesc,
        R.string.blindEff,
        null,
        null,
        null,
        null,
        null,
        listOf(-2),
        0,
        null,
        null
    )

    val deafness = Advantage(
        "deafness",
        R.string.deafness,
        R.string.deafDesc,
        R.string.deafEff,
        null,
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val mute = Advantage(
        "mute",
        R.string.mute,
        R.string.muteDesc,
        R.string.muteEff,
        null,
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val nearsighted = Advantage(
        "nearsighted",
        R.string.nearsighted,
        R.string.nearsightDesc,
        R.string.nearsightEff,
        null,
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val exclusiveWeapon = Advantage(
        "exclusiveWeapon",
        R.string.exclusiveWeapon,
        R.string.exclusiveDesc,
        R.string.exclusiveEff,
        R.string.exclusiveRestriction,
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val severeAllergy = Advantage(
        "severeAllergy",
        R.string.severeAllergy,
        R.string.allergyDesc,
        R.string.allergyEff,
        null,
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val seriousVice = Advantage(
        "seriousVice",
        R.string.seriousVice,
        R.string.viceDesc,
        R.string.viceEff,
        null,
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val atrophiedLimb = Advantage(
        "atrophiedLimb",
        R.string.atrophiedLimb,
        R.string.atrophyDesc,
        R.string.atrophyEff,
        null,
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val seriousIllness = Advantage(
        "seriousIllness",
        R.string.seriousIllness,
        R.string.illnessDesc,
        R.string.illnessEff,
        null,
        null,
        null,
        null,
        null,
        listOf(-2),
        0,
        null,
        null
    )

    val physicalWeakness = Advantage(
        "physWeakness",
        R.string.physWeakness,
        R.string.physWeakDesc,
        R.string.physWeakEff,
        null,
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        {_, _ -> charInstance.combat.physicalRes.setMultiplier(0.5)}
    ) { _, _ -> charInstance.combat.physicalRes.setMultiplier(1.0) }

    val deepSleeper = Advantage(
        "deepSleeper",
        R.string.deepSleeper,
        R.string.deepSleepDesc,
        R.string.deepSleepEff,
        null,
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val deductCharacteristic = Advantage(
        "charDeduction",
        R.string.deductChar,
        R.string.charDeductDesc,
        R.string.charDeductEff,
        R.string.charDeductRestriction,
        null,
        R.array.primaryCharArray,
        0,
        null,
        listOf(-1),
        0,
        {input, _ ->
            charInstance.primaryList.allPrimaries[input!!].setBonus(-2)
        }
    ) { input, _ ->
        charInstance.primaryList.allPrimaries[input!!].setBonus(2)
    }

    val unfortunate = Advantage(
        "unfortunate",
        R.string.unfortunate,
        R.string.unfortuneDesc,
        R.string.unfortuneEff,
        null,
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val easilyPossessed = Advantage(
        "easilyPossessed",
        R.string.easilyPossessed,
        R.string.possessedDesc,
        R.string.possessedEff,
        null,
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val exhausted = Advantage(
        "exhausted",
        R.string.exhausted,
        R.string.exhaustDesc,
        R.string.exhaustEff,
        null,
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        {_, _ ->
            charInstance.combat.specFatigue.value -= 1
            charInstance.combat.updateFatigue()
        }
    ) { _, _ ->
        charInstance.combat.specFatigue.value += 1
        charInstance.combat.updateFatigue()
    }

    val severePhobia = Advantage(
        "phobia",
        R.string.severePhobia,
        R.string.phobiaDesc,
        R.string.phobiaEff,
        null,
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val painVulnerability = Advantage(
        "painVulnerability",
        R.string.painVulnerability,
        R.string.painVulnDesc,
        R.string.painVulnEff,
        null,
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val sickly = Advantage(
        "sickly",
        R.string.sickly,
        R.string.sicklyDesc,
        R.string.sicklyEff,
        null,
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        {_, _ -> charInstance.combat.diseaseRes.setMultiplier(0.5)}
    ) { _, _ -> charInstance.combat.diseaseRes.setMultiplier(1.0) }

    val slowHealer = Advantage(
        "slowHeal",
        R.string.slowHealer,
        R.string.slowHealDesc,
        R.string.slowHealEff,
        null,
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        {_, _ ->
            charInstance.combat.specRegen.value -= 1
            charInstance.combat.updateRegeneration()
        }
    ) { _, _ ->
        charInstance.combat.specRegen.value += 1
        charInstance.combat.updateRegeneration()
    }

    val slowLearner = Advantage(
        "slowLearner",
        R.string.slowLearner,
        R.string.slowLearnDesc,
        R.string.slowLearnEff,
        null,
        null,
        null,
        null,
        null,
        listOf(-1, -2),
        0,
        null,
        null
    )

    val slowReactions = Advantage(
        "slowReactions",
        R.string.slowReactions,
        R.string.slowReactDesc,
        R.string.slowReactEff,
        null,
        null,
        null,
        null,
        null,
        listOf(-1, -2),
        0,
        {_, cost ->
            when(cost){
                -1 -> charInstance.combat.specInitiative.value -= 30
                -2 -> charInstance.combat.specInitiative.value -= 60
            }

            charInstance.combat.updateInitiative()
        }
    ) { _, cost ->
        when (cost) {
            -1 -> charInstance.combat.specInitiative.value += 30
            -2 -> charInstance.combat.specInitiative.value += 60
        }

        charInstance.combat.updateInitiative()
    }

    val magicSusceptibility = Advantage(
        "magSusceptibility",
        R.string.magSusceptibility,
        R.string.magSusceptDesc,
        R.string.magSusceptEff,
        null,
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        {_, _ -> charInstance.combat.magicRes.setMultiplier(0.5) }
    ) { _, _ -> charInstance.combat.magicRes.setMultiplier(1.0) }

    val poisonSusceptibility = Advantage(
        "venSusceptibility",
        R.string.poisonSusceptibility,
        R.string.venSusceptDesc,
        R.string.venSusceptEff,
        null,
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        {_, _ -> charInstance.combat.venomRes.setMultiplier(0.5)}
    ) { _, _ -> charInstance.combat.venomRes.setMultiplier(1.0) }

    val unattractive = Advantage(
        "unattractive",
        R.string.unattractive,
        R.string.unattractiveDesc,
        R.string.unattractiveEff,
        R.string.unattractiveRestriction,
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        {_, _ ->
            charInstance.setAppearance(2)
        },
        null
    )

    val temperatureVulnerability = Advantage(
        "tempVulnerability",
        R.string.tempVulnerability,
        R.string.tempVulnDesc,
        R.string.tempVulnEff,
        null,
        null,
        R.array.tempVulnerableOptions,
        0,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val disadvantages = listOf(
        deductCharacteristic, physicalWeakness, poisonSusceptibility, sickly, magicSusceptibility,
        painVulnerability, easilyPossessed, temperatureVulnerability, exclusiveWeapon, slowHealer,
        slowReactions, exhausted, badLuck, unfortunate, unattractive, nearsighted, blind, deafness,
        mute, atrophiedLimb, severePhobia, severeAllergy, seriousVice, seriousIllness, deepSleeper,
        slowLearner
    )
}