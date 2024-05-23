package com.paetus.animaCharCreator.character_creation.attributes.class_objects

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.enumerations.Archetype

/**
 * Object that holds class data to be used by the character.
 */
class ClassRecord(
    val charInstance: BaseCharacter
) {
    private val warrior =
        CharClass(
            saveName = "warrior",
            archetype = listOf(Archetype.Fighter),
            lifePointMultiple = 15,
            lifePointsPerLevel = 15,
            initiativePerLevel = 5,
            mkPerLevel = 25,
            psyPerTurn = 3,
            combatMax = 0.6,
            atkGrowth = 2,
            blockGrowth = 2,
            dodgeGrowth = 2,
            armorGrowth = 2,
            kiGrowth = 2,
            kiAccumMult = 20,
            magMax = 0.5,
            zeonGrowth = 3,
            maGrowth = 70,
            maProjGrowth = 3,
            summonGrowth = 3,
            controlGrowth = 3,
            bindGrowth = 3,
            banishGrowth = 3,
            psyMax = 0.5,
            psyPointGrowth = 20,
            psyProjGrowth = 3,
            athGrowth = 2,
            socGrowth = 2,
            percGrowth = 2,
            intellGrowth = 3,
            vigGrowth = 2,
            subterGrowth = 2,
            createGrowth = 2,
            reducedCosts = mapOf(Pair(24, 1)),
            primaryBonus = mapOf(
                Pair(R.string.attackLabel, 5),
                Pair(R.string.blockLabel, 5),
                Pair(R.string.wearLabel, 5)
            ),
            secondaryBonus = mapOf(Pair(24, 5)),
            specialText = null,
            onTake = {
                //apply attack, block, and wear armor class bonuses
                charInstance.combat.attack.setPointPerLevel(lvlBonus = 5)
                charInstance.combat.block.setPointPerLevel(lvlBonus = 5)
                charInstance.combat.wearArmor.setPointPerLevel(lvlBonus = 5)

                //give bonus for feats of strength
                charInstance.secondaryList.strengthFeat.setClassPointsPerLevel(classBonus = 5)

                //set individual growth value for feats of strength
                charInstance.secondaryList.strengthFeat.setDevelopmentDeduction(dpDeduction = 1)
            }
        ) {
            //remove class combat bonuses
            charInstance.combat.attack.setPointPerLevel(lvlBonus = 0)
            charInstance.combat.block.setPointPerLevel(lvlBonus = 0)
            charInstance.combat.wearArmor.setPointPerLevel(lvlBonus = 0)

            //remove feats of strength class bonus
            charInstance.secondaryList.strengthFeat.setClassPointsPerLevel(classBonus = 0)

            //reset growth value from this class
            charInstance.secondaryList.strengthFeat.setDevelopmentDeduction(dpDeduction = -1)
        }

    private val acroWarrior = CharClass(
        saveName = "acroWarrior",
        archetype = listOf(Archetype.Fighter),
        lifePointMultiple = 20,
        lifePointsPerLevel = 10,
        initiativePerLevel = 10,
        mkPerLevel = 25,
        psyPerTurn = 3,
        combatMax = 0.6,
        atkGrowth = 2,
        blockGrowth = 3,
        dodgeGrowth = 2,
        armorGrowth = 2,
        kiGrowth = 2,
        kiAccumMult = 20,
        magMax = 0.5,
        zeonGrowth = 3,
        maGrowth = 70,
        maProjGrowth = 3,
        summonGrowth = 3,
        controlGrowth = 3,
        bindGrowth = 3,
        banishGrowth = 3,
        psyMax = 0.5,
        psyPointGrowth = 20,
        psyProjGrowth = 3,
        athGrowth = 2,
        socGrowth = 2,
        percGrowth = 2,
        intellGrowth = 3,
        vigGrowth = 2,
        subterGrowth = 2,
        createGrowth = 2,
        reducedCosts = mapOf(),
        primaryBonus = mapOf(
            Pair(R.string.attackLabel, 5),
            Pair(R.string.dodgeLabel, 5)
        ),
        secondaryBonus = mapOf(
            Pair(0, 10),
            Pair(3, 10),
            Pair(1, 10),
            Pair(37, 10),
            Pair(9, 10)
        ),
        specialText = null,
        onTake = {
            //apply combat class bonuses
            charInstance.combat.attack.setPointPerLevel(lvlBonus = 5)
            charInstance.combat.dodge.setPointPerLevel(lvlBonus = 5)

            //apply secondary characteristic bonuses
            charInstance.secondaryList.acrobatics.setClassPointsPerLevel(classBonus = 10)
            charInstance.secondaryList.jump.setClassPointsPerLevel(classBonus = 10)
            charInstance.secondaryList.athletics.setClassPointsPerLevel(classBonus = 10)
            charInstance.secondaryList.sleightHand.setClassPointsPerLevel(classBonus = 10)
            charInstance.secondaryList.style.setClassPointsPerLevel(classBonus = 10)
        }
    ) {
        //remove combat class bonuses
        charInstance.combat.attack.setPointPerLevel(lvlBonus = 0)
        charInstance.combat.dodge.setPointPerLevel(lvlBonus = 0)

        //remove secondary characteristic bonuses
        charInstance.secondaryList.acrobatics.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.jump.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.athletics.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.sleightHand.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.style.setClassPointsPerLevel(classBonus = 0)
    }

    private val paladin = CharClass(
        saveName = "paladin",
        archetype = listOf(Archetype.Fighter),
        lifePointMultiple = 15,
        lifePointsPerLevel = 15,
        initiativePerLevel = 5,
        mkPerLevel = 20,
        psyPerTurn = 3,
        combatMax = 0.60,
        atkGrowth = 2,
        blockGrowth = 2,
        dodgeGrowth = 2,
        armorGrowth = 2,
        kiGrowth = 2,
        kiAccumMult = 20,
        magMax = 0.50,
        zeonGrowth = 2,
        maGrowth = 60,
        maProjGrowth = 3,
        summonGrowth = 3,
        controlGrowth = 3,
        bindGrowth = 3,
        banishGrowth = 1,
        psyMax = 0.50,
        psyPointGrowth = 20,
        psyProjGrowth = 3,
        athGrowth = 2,
        socGrowth = 1,
        percGrowth = 2,
        intellGrowth = 2,
        vigGrowth = 2,
        subterGrowth = 3,
        createGrowth = 2,
        reducedCosts = mapOf(Pair(25, 1)),
        primaryBonus = mapOf(
            Pair(R.string.blockLabel, 5),
            Pair(R.string.wearLabel, 10),
            Pair(R.string.banishTitle, 10),
            Pair(R.string.zeonLabel, 20)
        ),
        secondaryBonus = mapOf(
            Pair(7, 10),
            Pair(25, 10),
            Pair(9, 5)
        ),
        specialText = R.string.paladinSpecial,
        onTake = {
            //apply combat class bonuses
            charInstance.combat.block.setPointPerLevel(lvlBonus = 5)
            charInstance.combat.wearArmor.setPointPerLevel(lvlBonus = 10)

            //apply secondary class bonuses
            charInstance.secondaryList.leadership.setClassPointsPerLevel(classBonus = 10)
            charInstance.secondaryList.resistPain.setClassPointsPerLevel(classBonus = 10)
            charInstance.secondaryList.style.setClassPointsPerLevel(classBonus = 5)

            //apply cost reduction to withstand pain
            charInstance.secondaryList.resistPain.setDevelopmentDeduction(1)

            //apply magic abilities if chosen
            if(charInstance.classes.magPaladin.value){
                charInstance.magic.setZeonPerLevel(lvlBonus = 20)
                charInstance.summoning.banish.setPointsPerLevel(lvlBonus = 10)
            }
            //otherwise add composure class bonuses
            else charInstance.secondaryList.composure.setClassPointsPerLevel(classBonus = 10)
        }
    ) {
        //remove combat class bonus
        charInstance.combat.block.setPointPerLevel(lvlBonus = 0)
        charInstance.combat.wearArmor.setPointPerLevel(lvlBonus = 0)

        //remove secondary class bonuses
        charInstance.secondaryList.leadership.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.resistPain.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.style.setClassPointsPerLevel(classBonus = 0)

        //remove cost reduction to withstand pain
        charInstance.secondaryList.resistPain.setDevelopmentDeduction(dpDeduction = -1)

        //remove any applied magic ability
        if (charInstance.classes.magPaladin.value) {
            charInstance.magic.setZeonPerLevel(lvlBonus = 0)
            charInstance.summoning.banish.setPointsPerLevel(lvlBonus = 0)
        }
        //if no magic abilities, remove composure class bonus
        else charInstance.secondaryList.composure.setClassPointsPerLevel(classBonus = 0)
    }

    private val darkPaladin = CharClass(
        saveName = "darkPaladin",
        archetype = listOf(Archetype.Fighter),
        lifePointMultiple = 15,
        lifePointsPerLevel = 15,
        initiativePerLevel = 5,
        mkPerLevel = 20,
        psyPerTurn = 3,
        combatMax = 0.60,
        atkGrowth = 2,
        blockGrowth = 2,
        dodgeGrowth = 2,
        armorGrowth = 2,
        kiGrowth = 2,
        kiAccumMult = 20,
        magMax = 0.50,
        zeonGrowth = 2,
        maGrowth = 60,
        maProjGrowth = 3,
        summonGrowth = 3,
        controlGrowth = 1,
        bindGrowth = 3,
        banishGrowth = 3,
        psyMax = 0.50,
        psyPointGrowth = 20,
        psyProjGrowth = 3,
        athGrowth = 2,
        socGrowth = 1,
        percGrowth = 2,
        intellGrowth = 2,
        vigGrowth = 2,
        subterGrowth = 2,
        createGrowth = 2,
        reducedCosts = mapOf(Pair(23, 1)),
        primaryBonus = mapOf(
            Pair(R.string.attackLabel, 5),
            Pair(R.string.wearLabel, 5),
            Pair(R.string.controlTitle, 10),
            Pair(R.string.zeonLabel, 20)
        ),
        secondaryBonus = mapOf(
            Pair(6, 10),
            Pair(23, 10),
            Pair(9, 5),
            Pair(8, 5)
        ),
        specialText = R.string.darkPaladinSpecial,
        onTake = {
            //apply combat class bonuses
            charInstance.combat.attack.setPointPerLevel(lvlBonus = 5)
            charInstance.combat.wearArmor.setPointPerLevel(lvlBonus = 5)

            //apply secondary class bonuses
            charInstance.secondaryList.intimidate.setClassPointsPerLevel(classBonus = 10)
            charInstance.secondaryList.composure.setClassPointsPerLevel(classBonus = 10)
            charInstance.secondaryList.style.setClassPointsPerLevel(classBonus = 5)
            charInstance.secondaryList.persuasion.setClassPointsPerLevel(classBonus = 5)

            //reduce composure's cost
            charInstance.secondaryList.composure.setDevelopmentDeduction(dpDeduction = 1)

            //apply class magic abilities, if chosen
            if(charInstance.classes.magPaladin.value){
                charInstance.magic.setZeonPerLevel(lvlBonus = 20)
                charInstance.summoning.control.setPointsPerLevel(lvlBonus = 10)
            }
            //if not, apply withstand pain class bonus
            else
                charInstance.secondaryList.resistPain.setClassPointsPerLevel(classBonus = 10)
        }
    ) {
        //remove combat class bonuses
        charInstance.combat.attack.setPointPerLevel(lvlBonus = 0)
        charInstance.combat.wearArmor.setPointPerLevel(lvlBonus = 0)

        //remove secondary class bonuses
        charInstance.secondaryList.intimidate.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.composure.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.style.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.persuasion.setClassPointsPerLevel(classBonus = 0)

        //remove composure cost reduction
        charInstance.secondaryList.composure.setDevelopmentDeduction(dpDeduction = -1)

        //remove any applied magic abilities
        if (charInstance.classes.magPaladin.value) {
            charInstance.magic.setZeonPerLevel(lvlBonus = 0)
            charInstance.summoning.control.setPointsPerLevel(lvlBonus = 0)
        }
        //otherwise, remove withstand pain class bonus
        else
            charInstance.secondaryList.resistPain.setClassPointsPerLevel(classBonus = 0)
    }

    private val weaponMaster = CharClass(
        saveName = "weaponMaster",
        archetype = listOf(Archetype.Fighter),
        lifePointMultiple = 10,
        lifePointsPerLevel = 20,
        initiativePerLevel = 5,
        mkPerLevel = 10,
        psyPerTurn = 3,
        combatMax = 0.60,
        atkGrowth = 2,
        blockGrowth = 2,
        dodgeGrowth = 2,
        armorGrowth = 1,
        kiGrowth = 3,
        kiAccumMult = 30,
        magMax = 0.50,
        zeonGrowth = 3,
        maGrowth = 70,
        maProjGrowth = 3,
        summonGrowth = 3,
        controlGrowth = 3,
        bindGrowth = 3,
        banishGrowth = 3,
        psyMax = 0.50,
        psyPointGrowth = 20,
        psyProjGrowth = 3,
        athGrowth = 2,
        socGrowth = 2,
        percGrowth = 2,
        intellGrowth = 3,
        vigGrowth = 1,
        subterGrowth = 3,
        createGrowth = 2,
        reducedCosts = mapOf(),
        primaryBonus = mapOf(
            Pair(R.string.attackLabel, 5),
            Pair(R.string.blockLabel, 5),
            Pair(R.string.wearLabel, 10)
        ),
        secondaryBonus = mapOf(Pair(24, 5)),
        specialText = R.string.weaponmasterSpecial,
        onTake = {
            //apply combat class bonuses
            charInstance.combat.attack.setPointPerLevel(lvlBonus = 5)
            charInstance.combat.block.setPointPerLevel(lvlBonus = 5)
            charInstance.combat.wearArmor.setPointPerLevel(lvlBonus = 10)

            //apply feats of strength class bonus
            charInstance.secondaryList.strengthFeat.setClassPointsPerLevel(classBonus = 5)
        }
    ) {
        //remove combat class bonuses
        charInstance.combat.attack.setPointPerLevel(lvlBonus = 0)
        charInstance.combat.block.setPointPerLevel(lvlBonus = 0)
        charInstance.combat.wearArmor.setPointPerLevel(lvlBonus = 0)

        //remove feats of strength class bonus
        charInstance.secondaryList.strengthFeat.setClassPointsPerLevel(classBonus = 0)
    }

    private val technician = CharClass(
        saveName = "technician",
        archetype = listOf(Archetype.Domine),
        lifePointMultiple = 20,
        lifePointsPerLevel = 5,
        initiativePerLevel = 5,
        mkPerLevel = 50,
        psyPerTurn = 3,
        combatMax = 0.60,
        atkGrowth = 2,
        blockGrowth = 2,
        dodgeGrowth = 2,
        armorGrowth = 2,
        kiGrowth = 1,
        kiAccumMult = 10,
        magMax = 0.50,
        zeonGrowth = 3,
        maGrowth = 70,
        maProjGrowth = 3,
        summonGrowth = 3,
        controlGrowth = 3,
        bindGrowth = 3,
        banishGrowth = 3,
        psyMax = 0.50,
        psyPointGrowth = 20,
        psyProjGrowth = 3,
        athGrowth = 2,
        socGrowth = 2,
        percGrowth = 2,
        intellGrowth = 3,
        vigGrowth = 2,
        subterGrowth = 2,
        createGrowth = 2,
        reducedCosts = mapOf(),
        primaryBonus = mapOf(Pair(R.string.attackLabel, 5)),
        secondaryBonus = mapOf(),
        specialText = null,
        onTake = {
            //set attack class bonus
            charInstance.combat.attack.setPointPerLevel(lvlBonus = 5)}
    ) {
        //remove attack class bonus
        charInstance.combat.attack.setPointPerLevel(lvlBonus = 0)
    }

    private val tao = CharClass(
        saveName = "tao",
        archetype = listOf(Archetype.Fighter, Archetype.Domine),
        lifePointMultiple = 20,
        lifePointsPerLevel = 10,
        initiativePerLevel = 5,
        mkPerLevel = 30,
        psyPerTurn = 3,
        combatMax = 0.60,
        atkGrowth = 2,
        blockGrowth = 2,
        dodgeGrowth = 2,
        armorGrowth = 2,
        kiGrowth = 2,
        kiAccumMult = 15,
        magMax = 0.50,
        zeonGrowth = 3,
        maGrowth = 70,
        maProjGrowth = 3,
        summonGrowth = 3,
        controlGrowth = 3,
        bindGrowth = 3,
        banishGrowth = 3,
        psyMax = 0.50,
        psyPointGrowth = 20,
        psyProjGrowth = 3,
        athGrowth = 2,
        socGrowth = 2,
        percGrowth = 2,
        intellGrowth = 3,
        vigGrowth = 2,
        subterGrowth = 2,
        createGrowth = 2,
        reducedCosts = mapOf(),
        primaryBonus = mapOf(),
        secondaryBonus = mapOf(Pair(9, 5)),
        specialText = R.string.taoSpecial,
        onTake = {
            //set style class bonus
            charInstance.secondaryList.style.setClassPointsPerLevel(classBonus = 5)
        }
    ) {
        //remove style class bonus
        charInstance.secondaryList.style.setClassPointsPerLevel(classBonus = 0)
    }

    private val ranger = CharClass(
        saveName = "ranger",
        archetype = listOf(Archetype.Fighter, Archetype.Prowler),
        lifePointMultiple = 20,
        lifePointsPerLevel = 10,
        initiativePerLevel = 5,
        mkPerLevel = 20,
        psyPerTurn = 3,
        combatMax = 0.60,
        atkGrowth = 2,
        blockGrowth = 2,
        dodgeGrowth = 2,
        armorGrowth = 2,
        kiGrowth = 2,
        kiAccumMult = 25,
        magMax = 0.50,
        zeonGrowth = 3,
        maGrowth = 70,
        maProjGrowth = 3,
        summonGrowth = 3,
        controlGrowth = 3,
        bindGrowth = 3,
        banishGrowth = 3,
        psyMax = 0.50,
        psyPointGrowth = 20,
        psyProjGrowth = 3,
        athGrowth = 2,
        socGrowth = 2,
        percGrowth = 1,
        intellGrowth = 3,
        vigGrowth = 3,
        subterGrowth = 2,
        createGrowth = 2,
        reducedCosts = mapOf(
            Pair(32, 1),
            Pair(15, 2),
            Pair(13, 1),
            Pair(19, 2)
        ),
        primaryBonus = mapOf(Pair(R.string.attackLabel, 5)),
        secondaryBonus = mapOf(
            Pair(10, 10),
            Pair(11, 10),
            Pair(12, 10),
            Pair(32, 5),
            Pair(13, 5),
            Pair(15, 5)
        ),
        specialText = R.string.rangerSpecial,
        onTake = {
            //apply attack class bonus
            charInstance.combat.attack.setPointPerLevel(lvlBonus = 5)

            //apply secondary class bonuses
            charInstance.secondaryList.notice.setClassPointsPerLevel(classBonus = 10)
            charInstance.secondaryList.search.setClassPointsPerLevel(classBonus = 10)
            charInstance.secondaryList.track.setClassPointsPerLevel(classBonus = 10)
            charInstance.secondaryList.trapLore.setClassPointsPerLevel(classBonus = 5)
            charInstance.secondaryList.animals.setClassPointsPerLevel(classBonus = 5)
            charInstance.secondaryList.herbalLore.setClassPointsPerLevel(classBonus = 5)

            //apply secondary dp cost reductions
            charInstance.secondaryList.trapLore.setDevelopmentDeduction(dpDeduction = 1)
            charInstance.secondaryList.herbalLore.setDevelopmentDeduction(dpDeduction = 1)
            charInstance.secondaryList.animals.setDevelopmentDeduction(dpDeduction = 2)
            charInstance.secondaryList.medic.setDevelopmentDeduction(dpDeduction = 1)
        }
    ) {
        //remove attack class bonus
        charInstance.combat.attack.setPointPerLevel(lvlBonus = 5)

        //remove secondary class bonuses
        charInstance.secondaryList.notice.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.search.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.track.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.trapLore.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.animals.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.herbalLore.setClassPointsPerLevel(classBonus = 0)

        //remove secondary dp cost reductions
        charInstance.secondaryList.trapLore.setDevelopmentDeduction(dpDeduction = -1)
        charInstance.secondaryList.herbalLore.setDevelopmentDeduction(dpDeduction = -1)
        charInstance.secondaryList.animals.setDevelopmentDeduction(dpDeduction = -2)
        charInstance.secondaryList.medic.setDevelopmentDeduction(dpDeduction = -1)
    }

    private val shadow = CharClass(
        saveName = "shadow",
        archetype = listOf(Archetype.Fighter, Archetype.Prowler),
        lifePointMultiple = 20,
        lifePointsPerLevel = 5,
        initiativePerLevel = 10,
        mkPerLevel = 25,
        psyPerTurn = 3,
        combatMax = 0.60,
        atkGrowth = 2,
        blockGrowth = 3,
        dodgeGrowth = 2,
        armorGrowth = 2,
        kiGrowth = 2,
        kiAccumMult = 20,
        magMax = 0.50,
        zeonGrowth = 3,
        maGrowth = 70,
        maProjGrowth = 3,
        summonGrowth = 3,
        controlGrowth = 3,
        bindGrowth = 3,
        banishGrowth = 3,
        psyMax = 0.50,
        psyPointGrowth = 20,
        psyProjGrowth = 3,
        athGrowth = 2,
        socGrowth = 2,
        percGrowth = 2,
        intellGrowth = 3,
        vigGrowth = 2,
        subterGrowth = 2,
        createGrowth = 2,
        reducedCosts = mapOf(),
        primaryBonus = mapOf(
            Pair(R.string.attackLabel, 5),
            Pair(R.string.dodgeLabel, 5)
        ),
        secondaryBonus = mapOf(
            Pair(10, 10),
            Pair(11, 10),
            Pair(27, 10),
            Pair(31, 10)
        ),
        specialText = R.string.kiConcealSpecial,
        onTake = {
            //apply combat class bonuses
            charInstance.combat.attack.setPointPerLevel(lvlBonus = 5)
            charInstance.combat.dodge.setPointPerLevel(lvlBonus = 5)

            //apply secondary class bonuses
            charInstance.secondaryList.notice.setClassPointsPerLevel(classBonus = 10)
            charInstance.secondaryList.search.setClassPointsPerLevel(classBonus = 10)
            charInstance.secondaryList.hide.setClassPointsPerLevel(classBonus = 10)
            charInstance.secondaryList.stealth.setClassPointsPerLevel(classBonus = 10)
        }
    ) {
        //remove combat class bonuses
        charInstance.combat.attack.setPointPerLevel(lvlBonus = 0)
        charInstance.combat.dodge.setPointPerLevel(lvlBonus = 0)

        //remove secondary class bonuses
        charInstance.secondaryList.notice.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.search.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.hide.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.stealth.setClassPointsPerLevel(classBonus = 0)
    }

    private val thief = CharClass(
        saveName = "thief",
        archetype = listOf(Archetype.Prowler),
        lifePointMultiple = 20,
        lifePointsPerLevel = 5,
        initiativePerLevel = 10,
        mkPerLevel = 20,
        psyPerTurn = 3,
        combatMax = 0.50,
        atkGrowth = 2,
        blockGrowth = 3,
        dodgeGrowth = 2,
        armorGrowth = 3,
        kiGrowth = 2,
        kiAccumMult = 25,
        magMax = 0.50,
        zeonGrowth = 3,
        maGrowth = 70,
        maProjGrowth = 3,
        summonGrowth = 3,
        controlGrowth = 3,
        bindGrowth = 3,
        banishGrowth = 3,
        psyMax = 0.50,
        psyPointGrowth = 20,
        psyProjGrowth = 3,
        athGrowth = 1,
        socGrowth = 2,
        percGrowth = 2,
        intellGrowth = 3,
        vigGrowth = 3,
        subterGrowth = 1,
        createGrowth = 2,
        reducedCosts = mapOf(Pair(14, 1)),
        primaryBonus = mapOf(Pair(R.string.dodgeLabel, 5)),
        secondaryBonus = mapOf(
            Pair(10, 5),
            Pair(11, 5),
            Pair(27, 5),
            Pair(31, 5),
            Pair(32, 5),
            Pair(30, 10)
        ),
        specialText = R.string.kiConcealSpecial,
        onTake = {
            //apply dodge class bonus
            charInstance.combat.dodge.setPointPerLevel(lvlBonus = 5)

            //apply secondary class bonuses
            charInstance.secondaryList.notice.setClassPointsPerLevel(classBonus = 5)
            charInstance.secondaryList.search.setClassPointsPerLevel(classBonus = 5)
            charInstance.secondaryList.hide.setClassPointsPerLevel(classBonus = 5)
            charInstance.secondaryList.stealth.setClassPointsPerLevel(classBonus = 5)
            charInstance.secondaryList.trapLore.setClassPointsPerLevel(classBonus = 5)
            charInstance.secondaryList.sleightHand.setClassPointsPerLevel(classBonus = 5)
            charInstance.secondaryList.theft.setClassPointsPerLevel(classBonus = 10)

            //apply appraisal cost reduction
            charInstance.secondaryList.appraise.setDevelopmentDeduction(dpDeduction = 2)
        }
    ) {
        //remove dodge class bonus
        charInstance.combat.dodge.setPointPerLevel(lvlBonus = 0)

        //remove secondary class bonuses
        charInstance.secondaryList.notice.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.search.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.hide.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.stealth.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.trapLore.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.sleightHand.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.theft.setClassPointsPerLevel(classBonus = 0)

        //remove appraisal cost reduction
        charInstance.secondaryList.appraise.setDevelopmentDeduction(dpDeduction = -2)
    }

    private val assassin = CharClass(
        saveName = "assassin",
        archetype = listOf(Archetype.Prowler),
        lifePointMultiple = 20,
        lifePointsPerLevel = 5,
        initiativePerLevel = 10,
        mkPerLevel = 20,
        psyPerTurn = 3,
        combatMax = 0.50,
        atkGrowth = 2,
        blockGrowth = 3,
        dodgeGrowth = 2,
        armorGrowth = 3,
        kiGrowth = 2,
        kiAccumMult = 25,
        magMax = 0.50,
        zeonGrowth = 3,
        maGrowth = 70,
        maProjGrowth = 3,
        summonGrowth = 3,
        controlGrowth = 3,
        bindGrowth = 3,
        banishGrowth = 3,
        psyMax = 0.50,
        psyPointGrowth = 20,
        psyProjGrowth = 3,
        athGrowth = 2,
        socGrowth = 2,
        percGrowth = 1,
        intellGrowth = 3,
        vigGrowth = 3,
        subterGrowth = 2,
        createGrowth = 2,
        reducedCosts = mapOf(
            Pair(31, 1),
            Pair(23, 2),
            Pair(17, 2)
        ),
        primaryBonus = mapOf(Pair(R.string.attackLabel, 5)),
        secondaryBonus = mapOf(
            Pair(10, 10),
            Pair(11, 10),
            Pair(27, 10),
            Pair(31, 10),
            Pair(29, 10),
            Pair(23, 10),
            Pair(32, 10)
        ),
        specialText = null,
        onTake = {
            //apply attack class bonus
            charInstance.combat.attack.setPointPerLevel(lvlBonus = 5)

            //apply secondary class bonuses
            charInstance.secondaryList.notice.setClassPointsPerLevel(classBonus = 10)
            charInstance.secondaryList.search.setClassPointsPerLevel(classBonus = 10)
            charInstance.secondaryList.hide.setClassPointsPerLevel(classBonus = 10)
            charInstance.secondaryList.stealth.setClassPointsPerLevel(classBonus = 10)
            charInstance.secondaryList.poisons.setClassPointsPerLevel(classBonus = 10)
            charInstance.secondaryList.composure.setClassPointsPerLevel(classBonus = 10)
            charInstance.secondaryList.trapLore.setClassPointsPerLevel(classBonus = 10)

            //apply secondary cost reductions
            charInstance.secondaryList.stealth.setDevelopmentDeduction(dpDeduction = 1)
            charInstance.secondaryList.composure.setDevelopmentDeduction(dpDeduction = 1)
            charInstance.secondaryList.memorize.setDevelopmentDeduction(dpDeduction = 1)
        }
    ) {
        //remove attack class bonus
        charInstance.combat.attack.setPointPerLevel(lvlBonus = 0)

        //remove secondary class bonuses
        charInstance.secondaryList.notice.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.search.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.hide.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.stealth.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.poisons.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.composure.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.trapLore.setClassPointsPerLevel(classBonus = 0)

        //remove secondary cost reductions
        charInstance.secondaryList.stealth.setDevelopmentDeduction(dpDeduction = -1)
        charInstance.secondaryList.composure.setDevelopmentDeduction(dpDeduction = -1)
        charInstance.secondaryList.memorize.setDevelopmentDeduction(dpDeduction = -1)
    }

    private val wizard = CharClass(
        saveName = "wizard",
        archetype = listOf(Archetype.Mystic),
        lifePointMultiple = 20,
        lifePointsPerLevel = 5,
        initiativePerLevel = 5,
        mkPerLevel = 10,
        psyPerTurn = 3,
        combatMax = 0.50,
        atkGrowth = 3,
        blockGrowth = 3,
        dodgeGrowth = 2,
        armorGrowth = 3,
        kiGrowth = 3,
        kiAccumMult = 30,
        magMax = 0.60,
        zeonGrowth = 1,
        maGrowth = 50,
        maProjGrowth = 2,
        summonGrowth = 2,
        controlGrowth = 2,
        bindGrowth = 2,
        banishGrowth = 2,
        psyMax = 0.50,
        psyPointGrowth = 20,
        psyProjGrowth = 3,
        athGrowth = 2,
        socGrowth = 2,
        percGrowth = 2,
        intellGrowth = 2,
        vigGrowth = 3,
        subterGrowth = 2,
        createGrowth = 2,
        reducedCosts = mapOf(Pair(18, 1)),
        primaryBonus = mapOf(Pair(R.string.zeonLabel, 100)),
        secondaryBonus = mapOf(
            Pair(18, 10),
            Pair(21, 5)
        ),
        specialText = null,
        onTake = {
            //apply secondary class bonuses
            charInstance.secondaryList.magicAppraise.setClassPointsPerLevel(classBonus = 10)
            charInstance.secondaryList.occult.setClassPointsPerLevel(classBonus = 5)

            //apply reduced magic appraisal cost reduction
            charInstance.secondaryList.magicAppraise.setDevelopmentDeduction(dpDeduction = 1)

            //apply max zeon gained per level
            charInstance.magic.setZeonPerLevel(lvlBonus = 100)
        }
    ) {
        //remove secondary class bonuses
        charInstance.secondaryList.magicAppraise.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.occult.setClassPointsPerLevel(classBonus = 0)

        //remove magic appraisal cost reduction
        charInstance.secondaryList.magicAppraise.setDevelopmentDeduction(dpDeduction = -1)

        //remove max zeon gained per level
        charInstance.magic.setZeonPerLevel(lvlBonus = 0)
    }

    private val warlock = CharClass(
        saveName = "warlock",
        archetype = listOf(Archetype.Fighter, Archetype.Mystic),
        lifePointMultiple = 20,
        lifePointsPerLevel = 10,
        initiativePerLevel = 5,
        mkPerLevel = 20,
        psyPerTurn = 3,
        combatMax = 0.50,
        atkGrowth = 2,
        blockGrowth = 2,
        dodgeGrowth = 2,
        armorGrowth = 2,
        kiGrowth = 2,
        kiAccumMult = 25,
        magMax = 0.50,
        zeonGrowth = 1,
        maGrowth = 50,
        maProjGrowth = 2,
        summonGrowth = 2,
        controlGrowth = 2,
        bindGrowth = 2,
        banishGrowth = 2,
        psyMax = 0.50,
        psyPointGrowth = 20,
        psyProjGrowth = 3,
        athGrowth = 2,
        socGrowth = 2,
        percGrowth = 2,
        intellGrowth = 2,
        vigGrowth = 2,
        subterGrowth = 2,
        createGrowth = 2,
        reducedCosts = mapOf(),
        primaryBonus = mapOf(
            Pair(R.string.attackLabel, 5),
            Pair(R.string.blockLabel, 5),
            Pair(R.string.dodgeLabel, 5),
            Pair(R.string.zeonLabel, 20)
        ),
        secondaryBonus = mapOf(Pair(18, 5)),
        specialText = null,
        onTake = {
            //apply combat class bonuses
            charInstance.combat.attack.setPointPerLevel(lvlBonus = 5)
            charInstance.combat.block.setPointPerLevel(lvlBonus = 5)
            charInstance.combat.dodge.setPointPerLevel(lvlBonus = 5)

            //apply magic appraisal class bonus
            charInstance.secondaryList.magicAppraise.setClassPointsPerLevel(classBonus = 5)

            //apply zeon points per level
            charInstance.magic.setZeonPerLevel(lvlBonus = 20)
        }
    ) {
        //remove combat class bonuses
        charInstance.combat.attack.setPointPerLevel(lvlBonus = 0)
        charInstance.combat.block.setPointPerLevel(lvlBonus = 0)
        charInstance.combat.dodge.setPointPerLevel(lvlBonus = 0)

        //remove magic appraisal class bonus
        charInstance.secondaryList.magicAppraise.setClassPointsPerLevel(classBonus = 0)

        //remove zeon points per level
        charInstance.magic.setZeonPerLevel(lvlBonus = 0)
    }

    private val illusionist = CharClass(
        saveName = "illusionist",
        archetype = listOf(Archetype.Mystic, Archetype.Prowler),
        lifePointMultiple = 20,
        lifePointsPerLevel = 5,
        initiativePerLevel = 5,
        mkPerLevel = 20,
        psyPerTurn = 3,
        combatMax = 0.50,
        atkGrowth = 3,
        blockGrowth = 3,
        dodgeGrowth = 2,
        armorGrowth = 3,
        kiGrowth = 2,
        kiAccumMult = 25,
        magMax = 0.60,
        zeonGrowth = 1,
        maGrowth = 60,
        maProjGrowth = 2,
        summonGrowth = 3,
        controlGrowth = 3,
        bindGrowth = 3,
        banishGrowth = 3,
        psyMax = 0.50,
        psyPointGrowth = 20,
        psyProjGrowth = 3,
        athGrowth = 2,
        socGrowth = 2,
        percGrowth = 2,
        intellGrowth = 2,
        vigGrowth = 3,
        subterGrowth = 2,
        createGrowth = 2,
        reducedCosts = mapOf(
            Pair(37, 1),
            Pair(8, 1)
        ),
        primaryBonus = mapOf(Pair(R.string.zeonLabel, 75)),
        secondaryBonus = mapOf(
            Pair(18, 5),
            Pair(31, 10),
            Pair(27, 10),
            Pair(37, 10),
            Pair(26, 5),
            Pair(30, 5),
            Pair(8, 5)
        ),
        specialText = null,
        onTake = {
            //apply secondary class bonuses
            charInstance.secondaryList.magicAppraise.setClassPointsPerLevel(classBonus = 5)
            charInstance.secondaryList.stealth.setClassPointsPerLevel(classBonus = 10)
            charInstance.secondaryList.hide.setClassPointsPerLevel(classBonus = 10)
            charInstance.secondaryList.sleightHand.setClassPointsPerLevel(classBonus = 10)
            charInstance.secondaryList.disguise.setClassPointsPerLevel(classBonus = 5)
            charInstance.secondaryList.theft.setClassPointsPerLevel(classBonus = 5)
            charInstance.secondaryList.persuasion.setClassPointsPerLevel(classBonus = 5)

            //apply secondary cost reductions
            charInstance.secondaryList.sleightHand.setDevelopmentDeduction(dpDeduction = 1)
            charInstance.secondaryList.persuasion.setDevelopmentDeduction(dpDeduction = 1)

            //apply zeon points gained per level
            charInstance.magic.setZeonPerLevel(lvlBonus = 75)
        }
    ) {
        //remove secondary class bonuses
        charInstance.secondaryList.magicAppraise.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.stealth.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.hide.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.sleightHand.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.disguise.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.theft.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.persuasion.setClassPointsPerLevel(classBonus = 0)

        //remove secondary cost reductions
        charInstance.secondaryList.sleightHand.setDevelopmentDeduction(dpDeduction = -1)
        charInstance.secondaryList.persuasion.setDevelopmentDeduction(dpDeduction = -1)

        //remove zeon points gained per level
        charInstance.magic.setZeonPerLevel(lvlBonus = 0)
    }

    private val wizMentalist = CharClass(
        saveName = "wizMentalist",
        archetype = listOf(Archetype.Mystic, Archetype.Psychic),
        lifePointMultiple = 20,
        lifePointsPerLevel = 5,
        initiativePerLevel = 5,
        mkPerLevel = 10,
        psyPerTurn = 1,
        combatMax = 0.50,
        atkGrowth = 3,
        blockGrowth = 3,
        dodgeGrowth = 2,
        armorGrowth = 3,
        kiGrowth = 3,
        kiAccumMult = 30,
        magMax = 0.50,
        zeonGrowth = 1,
        maGrowth = 50,
        maProjGrowth = 2,
        summonGrowth = 2,
        controlGrowth = 2,
        bindGrowth = 2,
        banishGrowth = 2,
        psyMax = 0.50,
        psyPointGrowth = 10,
        psyProjGrowth = 2,
        athGrowth = 2,
        socGrowth = 2,
        percGrowth = 2,
        intellGrowth = 2,
        vigGrowth = 3,
        subterGrowth = 2,
        createGrowth = 2,
        reducedCosts = mapOf(),
        primaryBonus = mapOf(Pair(R.string.zeonLabel, 100)),
        secondaryBonus = mapOf(
            Pair(18, 10),
            Pair(21, 5)
        ),
        specialText = null,
        onTake = {
            //apply secondary class bonuses
            charInstance.secondaryList.magicAppraise.setClassPointsPerLevel(classBonus = 10)
            charInstance.secondaryList.occult.setClassPointsPerLevel(classBonus = 5)

            //apply max zeon gained per level
            charInstance.magic.setZeonPerLevel(lvlBonus = 100)
        }
    ) {
        //remove secondary class bonuses
        charInstance.secondaryList.magicAppraise.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.occult.setClassPointsPerLevel(classBonus = 0)

        //remove max zeon gained per level
        charInstance.magic.setZeonPerLevel(lvlBonus = 0)
    }

    private val summoner = CharClass(
        saveName = "summoner",
        archetype = listOf(Archetype.Mystic),
        lifePointMultiple = 20,
        lifePointsPerLevel = 5,
        initiativePerLevel = 5,
        mkPerLevel = 10,
        psyPerTurn = 3,
        combatMax = 0.50,
        atkGrowth = 3,
        blockGrowth = 3,
        dodgeGrowth = 2,
        armorGrowth = 3,
        kiGrowth = 3,
        kiAccumMult = 30,
        magMax = 0.60,
        zeonGrowth = 1,
        maGrowth = 60,
        maProjGrowth = 3,
        summonGrowth = 1,
        controlGrowth = 1,
        bindGrowth = 1,
        banishGrowth = 1,
        psyMax = 0.50,
        psyPointGrowth = 20,
        psyProjGrowth = 3,
        athGrowth = 2,
        socGrowth = 2,
        percGrowth = 2,
        intellGrowth = 2,
        vigGrowth = 3,
        subterGrowth = 2,
        createGrowth = 2,
        reducedCosts = mapOf(Pair(21, 1)),
        primaryBonus = mapOf(
            Pair(R.string.zeonLabel, 50),
            Pair(R.string.summonLabel, 10),
            Pair(R.string.controlTitle, 10),
            Pair(R.string.bindTitle, 10),
            Pair(R.string.banishTitle, 10)
        ),
        secondaryBonus = mapOf(
            Pair(18, 5),
            Pair(21, 10)
        ),
        specialText = null,
        onTake = {
            //apply secondary class bonuses
            charInstance.secondaryList.magicAppraise.setClassPointsPerLevel(classBonus = 5)
            charInstance.secondaryList.occult.setClassPointsPerLevel(classBonus = 10)

            //apply reduced cost to occult
            charInstance.secondaryList.occult.setDevelopmentDeduction(dpDeduction = 1)

            //apply max zeon gained per level
            charInstance.magic.setZeonPerLevel(lvlBonus = 50)

            //apply summoning ability bonuses
            charInstance.summoning.summon.setPointsPerLevel(lvlBonus = 10)
            charInstance.summoning.control.setPointsPerLevel(lvlBonus = 10)
            charInstance.summoning.bind.setPointsPerLevel(lvlBonus = 10)
            charInstance.summoning.banish.setPointsPerLevel(lvlBonus = 10)
        }
    ) {
        //remove secondary class bonuses
        charInstance.secondaryList.magicAppraise.setClassPointsPerLevel(classBonus = 0)
        charInstance.secondaryList.occult.setClassPointsPerLevel(classBonus = 0)

        //remove occult cost reduction
        charInstance.secondaryList.occult.setDevelopmentDeduction(dpDeduction = -1)

        //remove max zeon gained per level
        charInstance.magic.setZeonPerLevel(lvlBonus = 0)

        //remove summoning ability bonuses
        charInstance.summoning.summon.setPointsPerLevel(lvlBonus = 0)
        charInstance.summoning.control.setPointsPerLevel(lvlBonus = 0)
        charInstance.summoning.bind.setPointsPerLevel(lvlBonus = 0)
        charInstance.summoning.banish.setPointsPerLevel(lvlBonus = 0)
    }

    private val warSummoner = CharClass(
        saveName = "warSummoner",
        archetype = listOf(Archetype.Fighter, Archetype.Mystic),
        lifePointMultiple = 20,
        lifePointsPerLevel = 10,
        initiativePerLevel = 5,
        mkPerLevel = 20,
        psyPerTurn = 3,
        combatMax = 0.50,
        atkGrowth = 2,
        blockGrowth = 2,
        dodgeGrowth = 2,
        armorGrowth = 2,
        kiGrowth = 2,
        kiAccumMult = 20,
        magMax = 0.50,
        zeonGrowth = 1,
        maGrowth = 60,
        maProjGrowth = 3,
        summonGrowth = 1,
        controlGrowth = 1,
        bindGrowth = 1,
        banishGrowth = 1,
        psyMax = 0.50,
        psyPointGrowth = 20,
        psyProjGrowth = 3,
        athGrowth = 2,
        socGrowth = 2,
        percGrowth = 2,
        intellGrowth = 2,
        vigGrowth = 2,
        subterGrowth = 2,
        createGrowth = 2,
        reducedCosts = mapOf(),
        primaryBonus = mapOf(
            Pair(R.string.attackLabel, 5),
            Pair(R.string.blockLabel, 5),
            Pair(R.string.dodgeLabel, 5),
            Pair(R.string.zeonLabel, 20),
            Pair(R.string.summonLabel, 5),
            Pair(R.string.controlTitle, 5),
            Pair(R.string.bindTitle, 5)
        ),
        secondaryBonus = mapOf(Pair(21, 5)),
        specialText = null,
        onTake = {
            //apply combat class bonuses
            charInstance.combat.attack.setPointPerLevel(lvlBonus = 5)
            charInstance.combat.block.setPointPerLevel(lvlBonus = 5)
            charInstance.combat.dodge.setPointPerLevel(lvlBonus = 5)

            //apply occult class bonus
            charInstance.secondaryList.occult.setClassPointsPerLevel(classBonus = 5)

            //apply max zeon gained per level
            charInstance.magic.setZeonPerLevel(lvlBonus = 20)

            //apply summoning class bonuses
            charInstance.summoning.summon.setPointsPerLevel(lvlBonus = 5)
            charInstance.summoning.control.setPointsPerLevel(lvlBonus = 5)
            charInstance.summoning.bind.setPointsPerLevel(lvlBonus = 5)
            charInstance.summoning.banish.setPointsPerLevel(lvlBonus = 5)
        }
    ) {
        //remove combat class bonuses
        charInstance.combat.attack.setPointPerLevel(lvlBonus = 0)
        charInstance.combat.block.setPointPerLevel(lvlBonus = 0)
        charInstance.combat.dodge.setPointPerLevel(lvlBonus = 0)

        //remove occult class bonus
        charInstance.secondaryList.occult.setClassPointsPerLevel(classBonus = 0)

        //remove max zeon gained per level
        charInstance.magic.setZeonPerLevel(lvlBonus = 0)

        //remove summoning class bonuses
        charInstance.summoning.summon.setPointsPerLevel(lvlBonus = 0)
        charInstance.summoning.control.setPointsPerLevel(lvlBonus = 0)
        charInstance.summoning.bind.setPointsPerLevel(lvlBonus = 0)
        charInstance.summoning.banish.setPointsPerLevel(lvlBonus = 0)
    }

    private val mentalist = CharClass(
        saveName = "mentalist",
        archetype = listOf(Archetype.Psychic),
        lifePointMultiple = 20,
        lifePointsPerLevel = 5,
        initiativePerLevel = 5,
        mkPerLevel = 10,
        psyPerTurn = 1,
        combatMax = 0.50,
        atkGrowth = 3,
        blockGrowth = 3,
        dodgeGrowth = 2,
        armorGrowth = 3,
        kiGrowth = 3,
        kiAccumMult = 30,
        magMax = 0.50,
        zeonGrowth = 3,
        maGrowth = 70,
        maProjGrowth = 3,
        summonGrowth = 3,
        controlGrowth = 3,
        bindGrowth = 3,
        banishGrowth = 3,
        psyMax = 0.60,
        psyPointGrowth = 10,
        psyProjGrowth = 2,
        athGrowth = 2,
        socGrowth = 2,
        percGrowth = 2,
        intellGrowth = 2,
        vigGrowth = 3,
        subterGrowth = 2,
        createGrowth = 2,
        reducedCosts = mapOf(),
        primaryBonus = mapOf(),
        secondaryBonus = mapOf(),
        specialText = null,
        onTake = {}
    ) {}

    private val warMentalist = CharClass(
        saveName = "warMentalist",
        archetype = listOf(Archetype.Fighter, Archetype.Psychic),
        lifePointMultiple = 20,
        lifePointsPerLevel = 10,
        initiativePerLevel = 5,
        mkPerLevel = 20,
        psyPerTurn = 1,
        combatMax = 0.50,
        atkGrowth = 2,
        blockGrowth = 2,
        dodgeGrowth = 2,
        armorGrowth = 2,
        kiGrowth = 2,
        kiAccumMult = 25,
        magMax = 0.50,
        zeonGrowth = 3,
        maGrowth = 70,
        maProjGrowth = 3,
        summonGrowth = 3,
        controlGrowth = 3,
        bindGrowth = 3,
        banishGrowth = 3,
        psyMax = 0.50,
        psyPointGrowth = 15,
        psyProjGrowth = 2,
        athGrowth = 2,
        socGrowth = 2,
        percGrowth = 2,
        intellGrowth = 3,
        vigGrowth = 2,
        subterGrowth = 2,
        createGrowth = 2,
        reducedCosts = mapOf(),
        primaryBonus = mapOf(
            Pair(R.string.attackLabel, 5),
            Pair(R.string.blockLabel, 5),
            Pair(R.string.dodgeLabel, 5)
        ),
        secondaryBonus = mapOf(),
        specialText = null,
        onTake = {
            charInstance.combat.attack.setPointPerLevel(5)
            charInstance.combat.block.setPointPerLevel(5)
            charInstance.combat.dodge.setPointPerLevel(5)
        }
    ) {
        charInstance.combat.attack.setPointPerLevel(0)
        charInstance.combat.block.setPointPerLevel(0)
        charInstance.combat.dodge.setPointPerLevel(0)
    }

    private val freelancer = CharClass(
        saveName = "freelancer",
        archetype = listOf(Archetype.Novel),
        lifePointMultiple = 20,
        lifePointsPerLevel = 5,
        initiativePerLevel = 5,
        mkPerLevel = 20,
        psyPerTurn = 2,
        combatMax = 0.60,
        atkGrowth = 2,
        blockGrowth = 2,
        dodgeGrowth = 2,
        armorGrowth = 2,
        kiGrowth = 2,
        kiAccumMult = 20,
        magMax = 0.60,
        zeonGrowth = 2,
        maGrowth = 60,
        maProjGrowth = 2,
        summonGrowth = 2,
        controlGrowth = 2,
        bindGrowth = 2,
        banishGrowth = 2,
        psyMax = 0.60,
        psyPointGrowth = 20,
        psyProjGrowth = 2,
        athGrowth = 2,
        socGrowth = 2,
        percGrowth = 2,
        intellGrowth = 2,
        vigGrowth = 2,
        subterGrowth = 2,
        createGrowth = 2,
        reducedCosts = mapOf(),
        primaryBonus = mapOf(Pair(R.string.zeonLabel, 10)),
        secondaryBonus = mapOf(),
        specialText = null,
        onTake = {
            charInstance.classes.freelancerSelection.forEach{secondarySelected ->
                //update characteristic's class value if a selection was made
                if(secondarySelected != 0)
                    charInstance.secondaryList.getAllSecondaries()[secondarySelected - 1].setClassPointsPerLevel(classBonus = 10)
            }

            //apply max zeon gained per level
            charInstance.magic.setZeonPerLevel(10)
        }
    ) {
        //update characteristic's class value if a selection was made
        charInstance.classes.freelancerSelection.forEach { secondarySelected ->
            if (secondarySelected != 0)
                charInstance.secondaryList.getAllSecondaries()[secondarySelected - 1].setClassPointsPerLevel(
                    classBonus = 0
                )
        }

        //remove max zeon gained per level
        charInstance.magic.setZeonPerLevel(0)
    }

    //collect all available class items
    val allClasses = listOf(freelancer, warrior, acroWarrior, paladin, darkPaladin, weaponMaster, technician, tao,
        ranger, shadow, thief, assassin, wizard, warlock, illusionist, wizMentalist, summoner, warSummoner,
        mentalist, warMentalist)
}