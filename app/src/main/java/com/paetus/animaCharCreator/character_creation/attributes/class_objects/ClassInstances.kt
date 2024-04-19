package com.paetus.animaCharCreator.character_creation.attributes.class_objects

import androidx.compose.runtime.mutableStateOf
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.enumerations.Archetype
import com.paetus.animaCharCreator.writeDataTo
import java.io.BufferedReader
import java.io.ByteArrayOutputStream

/**
 * Record of classes available to the character.
 *
 * @param charInstance object that holds the character's data
 */
class ClassInstances(private val charInstance: BaseCharacter){
    //initialize freelancer bonus selections
    val freelancerSelection = mutableListOf(0, 0, 0, 0, 0)

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

    val paladin = CharClass(
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
            if(magPaladin.value){
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
        if (magPaladin.value) {
            charInstance.magic.setZeonPerLevel(lvlBonus = 0)
            charInstance.summoning.banish.setPointsPerLevel(lvlBonus = 0)
        }
        //if no magic abilities, remove composure class bonus
        else charInstance.secondaryList.composure.setClassPointsPerLevel(classBonus = 0)
    }

    val darkPaladin = CharClass(
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
            if(magPaladin.value){
                charInstance.magic.setZeonPerLevel(lvlBonus = 20)
                charInstance.summoning.control.setPointsPerLevel(lvlBonus = 10)
            }
            //if not, apply withstand pain class bonus
            else charInstance.secondaryList.resistPain.setClassPointsPerLevel(classBonus = 10)
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
        if (magPaladin.value) {
            charInstance.magic.setZeonPerLevel(lvlBonus = 0)
            charInstance.summoning.control.setPointsPerLevel(lvlBonus = 0)
        }
        //otherwise, remove withstand pain class bonus
        else charInstance.secondaryList.resistPain.setClassPointsPerLevel(classBonus = 0)
    }

    val weaponMaster = CharClass(
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

    val tao = CharClass(
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

    val mentalist = CharClass(
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

    val freelancer = CharClass(
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
            freelancerSelection.forEach{secondarySelected ->
                //update characteristic's class value if a selection was made
                if(secondarySelected != 0)
                    charInstance.secondaryList.getAllSecondaries()[secondarySelected - 1].setClassPointsPerLevel(classBonus = 10)
            }

            //apply max zeon gained per level
            charInstance.magic.setZeonPerLevel(10)
        }
    ) {
        //update characteristic's class value if a selection was made
        freelancerSelection.forEach { secondarySelected ->
            if (secondarySelected != 0)
                charInstance.secondaryList.getAllSecondaries()[secondarySelected - 1].setClassPointsPerLevel(
                    classBonus = 0
                )
        }

        //remove max zeon gained per level
        charInstance.magic.setZeonPerLevel(0)
    }

    val ownClass = mutableStateOf(value = freelancer)

    //collect all available class items
    val allClasses = listOf(freelancer, warrior, acroWarrior, paladin, darkPaladin, weaponMaster, technician, tao,
    ranger, shadow, thief, assassin, wizard, warlock, illusionist, wizMentalist, summoner, warSummoner,
    mentalist, warMentalist)

    //initialize paladin's magic selection
    val magPaladin = mutableStateOf(value = true)

    /**
     * Setter for class with class input.
     *
     * @param newClass new class to set for this character
     */
    fun setOwnClass(newClass: CharClass){
        //undo current class buffs
        ownClass.value.onRemove()

        //change class and apply new buffs
        ownClass.value = newClass
        ownClass.value.onTake()

        //update class life points
        charInstance.combat.updateClassLife()

        //update initiative bonus
        charInstance.combat.updateInitiative()

        //update character's maximum point values
        charInstance.percCombatDP.doubleValue = ownClass.value.combatMax
        charInstance.percMagDP.doubleValue = ownClass.value.magMax
        charInstance.percPsyDP.doubleValue = ownClass.value.psyMax
        charInstance.dpAllotmentCalc()

        //update secondary bonuses
        charInstance.secondaryList.classUpdate(newClass = ownClass.value)

        //update character's martial knowledge
        charInstance.ki.updateMK()

        //update innate psychic points
        charInstance.psychic.setInnatePsy()

        //update all spent value totals
        charInstance.updateTotalSpent()
    }

    /**
     * Setter for class with a string input.
     *
     * @param classString string to be converted into a class the character will take
     */
    fun setOwnClass(classString: String){
        //search through all class objects
        allClasses.forEach{charClass ->
            //apply class if match found
            if(charClass.saveName == classString){
                setOwnClass(charClass)
                return@forEach
            }
        }
    }

    /**
     * Setter for class with Integer input.
     *
     * @param classInt number that references the class the character will take
     */
    fun setOwnClass(classInt: Int){
        //apply the class indicated by the number
        setOwnClass(allClasses[classInt])
    }

    /**
     * Attempts to change the selection in the indicated record index.
     *
     * @param selectionIndex record location to set the selection to
     * @param secondarySelection value to set the selection to
     * @return value recorded after operation
     */
    fun setSelection(
        selectionIndex: Int,
        secondarySelection: Int
    ): Int{
        //record current selected value
        val prevIndex = freelancerSelection[selectionIndex] - 1

        //if user is clearing selection
        if(secondarySelection == 0) {
            //remove previous bonus if one taken
            if(prevIndex >= 0)
                charInstance.secondaryList.getAllSecondaries()[prevIndex].setClassPointsPerLevel(0)

            //set new input
            freelancerSelection[selectionIndex] = 0
        }

        //user is making a selection
        else{
            //determine that this input is not taken in another record index
            freelancerSelection.forEach{secondary ->
                //return current value if match found
                if(secondary == secondarySelection)
                    return freelancerSelection[selectionIndex]
            }

            //remove previous bonus if one taken
            if(prevIndex >= 0)
                charInstance.secondaryList.getAllSecondaries()[prevIndex].setClassPointsPerLevel(0)

            //set new input
            freelancerSelection[selectionIndex] = secondarySelection

            //add new bonus
            charInstance.secondaryList.getAllSecondaries()[secondarySelection - 1].setClassPointsPerLevel(classBonus = 10)
        }

        //return changed value
        return freelancerSelection[selectionIndex]
    }

    /**
     * Toggles the user's selection for their paladin boon.
     */
    fun toggleMagPaladin(){
        //swap character's selection
        magPaladin.value = !magPaladin.value

        //boon items for paladins
        if(ownClass.value == paladin){
            //grant magic options
            if(magPaladin.value){
                //remove composure bonus
                charInstance.secondaryList.composure.setClassPointsPerLevel(classBonus = 0)

                //grant zeon and banish points
                charInstance.magic.setZeonPerLevel(lvlBonus = 20)
                charInstance.summoning.banish.setPointsPerLevel(lvlBonus = 10)
            }

            //grant secondary boon
            else {
                //remove zeon and banish points
                charInstance.magic.setZeonPerLevel(lvlBonus = 0)
                charInstance.summoning.banish.setPointsPerLevel(lvlBonus = 0)

                //grant composure bonus
                charInstance.secondaryList.composure.setClassPointsPerLevel(classBonus = 10)
            }
        }

        //boon items for dark paladins
        else{
            //grant magic options
            if(magPaladin.value){
                //remove withstand pain bonus
                charInstance.secondaryList.resistPain.setClassPointsPerLevel(classBonus = 0)

                //grant zeon and control points
                charInstance.magic.setZeonPerLevel(lvlBonus = 20)
                charInstance.summoning.control.setPointsPerLevel(lvlBonus = 10)
            }

            //grant secondary boon
            else {
                //remove zeon and control points
                charInstance.magic.setZeonPerLevel(lvlBonus = 0)
                charInstance.summoning.control.setPointsPerLevel(lvlBonus = 0)

                //grant withstand pain bonus
                charInstance.secondaryList.resistPain.setClassPointsPerLevel(classBonus = 10)
            }
        }
    }

    /**
     * Loads character class data from file.
     *
     * @param fileReader object that reads the file
     * @param writeVersion app version the read file was written in
     */
    fun loadClassData(
        fileReader: BufferedReader,
        writeVersion: Int
    ){
        //get paladin's selection
        magPaladin.value = fileReader.readLine().toBoolean()

        //get the correct number of loops for the app version
        val loopNum =
            if(writeVersion <= 27) 3
            else 5

        //get each freelancer selected characteristic
        for(index in 0 until loopNum)
            setSelection(selectionIndex = index, secondarySelection = fileReader.readLine().toInt())
    }

    /**
     * Writes the data in this section to file.
     */
    fun writeClassData(
        byteArray: ByteArrayOutputStream
    ){
        writeDataTo(writer = byteArray, input = magPaladin.value)

        freelancerSelection.forEach{
            writeDataTo(writer = byteArray, input = it)
        }
    }
}