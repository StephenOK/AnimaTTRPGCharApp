package com.example.animabuilder.CharacterCreation.Attributes.Class

import java.io.Serializable

class CharClass(var heldClass: ClassName) : Serializable {
    var classIndex = 0
    var lifePointMultiple = 0
    var lifePointsPerLevel = 0
    var initiativePerLevel = 0
    var mkPerLevel = 0
    var psyPerTurn = 0
    fun getpsyPerTurn(): Int {
        return psyPerTurn
    }

    //primary ability block
    //combat block
    var combatMax = 0.0
    var atkGrowth = 0
    var blockGrowth = 0
    var dodgeGrowth = 0
    var armorGrowth = 0
    var kiGrowth = 0
    var kiAccumMult = 0

    //supernatural block
    var magMax = 0.0
    var zeonGrowth = 0
    var maGrowth = 0
    var maProjGrowth = 0
    var summonGrowth = 0
    var controlGrowth = 0
    var bindGrowth = 0
    var banishGrowth = 0

    //psychic block
    var psyMax = 0.0
    var psyPointGrowth = 0
    var psyProjGrowth = 0

    //secondary ability block
    var athGrowth = 0
    var socGrowth = 0
    var percGrowth = 0
    var intellGrowth = 0
    var vigGrowth = 0
    var subterGrowth = 0
    var creatGrowth = 0

    //reduced cost block
    var strengthFeatReduction = 0
    var standPainReduction = 0
    var composeReduction = 0
    var trapReduction = 0
    var herbalReduction = 0
    var animalReduction = 0
    var medReduction = 0
    var appraiseReduction = 0
    var stealthReduction = 0
    var memorizeReduction = 0
    var magAppraiseReduction = 0
    var sleightReduction = 0
    var persuadeReduction = 0
    var occultReduction = 0

    //innate bonus block
    //primaries
    var atkPerLevel = 0
    var blockPerLevel = 0
    var dodgePerLevel = 0
    var armorPerLevel = 0
    var zeonPerLevel = 0
    var summonPerLevel = 0
    var controlPerLevel = 0
    var bindPerLevel = 0
    var banishPerLevel = 0

    //secondaries
    var acrobatPerLevel = 0
    var athletPerLevel = 0
    var climbPerLevel = 0
    var jumpPerLevel = 0
    var ridePerLevel = 0
    var swimPerLevel = 0
    var artPerLevel = 0
    var dancePerLevel = 0
    var forgePerLevel = 0
    var musicPerLevel = 0
    var sleightPerLevel = 0
    var noticePerLevel = 0
    var searchPerLevel = 0
    var trackPerLevel = 0
    var intimidatePerLevel = 0
    var leaderPerLevel = 0
    var persuadePerLevel = 0
    var stylePerLevel = 0
    var disguisePerLevel = 0
    var hidePerLevel = 0
    var lockpickPerLevel = 0
    var poisonPerLevel = 0
    var theftPerLevel = 0
    var stealthPerLevel = 0
    var trapPerLevel = 0
    var animalPerLevel = 0
    var appraisePerLevel = 0
    var herbPerLevel = 0
    var histPerLevel = 0
    var memorizePerLevel = 0
    var magAppraisePerLevel = 0
    var medicPerLevel = 0
    var navPerLevel = 0
    var occultPerLevel = 0
    var sciencePerLevel = 0
    var composePerLevel = 0
    var strengthFeatPerLevel = 0
    var standPainPerLevel = 0

    private fun setClassBase(statList: IntArray) {
        lifePointMultiple = statList[0]
        lifePointsPerLevel = statList[1]
        initiativePerLevel = statList[2]
        mkPerLevel = statList[3]
        psyPerTurn = statList[4]
        combatMax = statList[5] / 100.0
        atkGrowth = statList[6]
        blockGrowth = statList[7]
        dodgeGrowth = statList[8]
        armorGrowth = statList[9]
        kiGrowth = statList[10]
        kiAccumMult = statList[11]
        magMax = statList[12] / 100.0
        zeonGrowth = statList[13]
        maGrowth = statList[14]
        maProjGrowth = statList[15]
        summonGrowth = statList[16]
        controlGrowth = statList[17]
        bindGrowth = statList[18]
        banishGrowth = statList[19]
        psyMax = statList[20] / 100.0
        psyPointGrowth = statList[21]
        psyProjGrowth = statList[22]
        athGrowth = statList[23]
        socGrowth = statList[24]
        percGrowth = statList[25]
        intellGrowth = statList[26]
        vigGrowth = statList[27]
        subterGrowth = statList[28]
        creatGrowth = statList[29]
    }

    init {
        val statBlock: IntArray
        when (heldClass) {
            ClassName.warrior -> {
                classIndex = 1
                statBlock = intArrayOf(
                    15, 15, 5, 25, 3,
                    60, 2, 2, 2, 2, 2, 20,
                    50, 3, 70, 3, 3, 3, 3, 3,
                    50, 20, 3,
                    2, 2, 2, 3, 2, 2, 2
                )
                strengthFeatReduction = 1
                atkPerLevel = 5
                blockPerLevel = 5
                armorPerLevel = 5
                strengthFeatPerLevel = 5
            }
            ClassName.acroWarrior -> {
                classIndex = 2
                statBlock = intArrayOf(
                    20, 10, 10, 25, 3,
                    60, 2, 3, 2, 2, 2, 20,
                    50, 3, 70, 3, 3, 3, 3, 3,
                    50, 20, 3,
                    2, 2, 2, 3, 2, 2, 2
                )
                atkPerLevel = 5
                dodgePerLevel = 5
                acrobatPerLevel = 10
                jumpPerLevel = 10
                athletPerLevel = 10
                sleightPerLevel = 10
                stylePerLevel = 10
            }
            ClassName.paladin -> {
                classIndex = 3
                statBlock = intArrayOf(
                    15, 15, 5, 20, 3,
                    60, 2, 2, 2, 2, 2, 20,
                    50, 2, 60, 3, 3, 3, 3, 1,
                    50, 20, 3,
                    2, 1, 2, 2, 2, 3, 2
                )
                standPainReduction = 1
                blockPerLevel = 5
                armorPerLevel = 10
                banishPerLevel = 10
                zeonPerLevel = 20
                leaderPerLevel = 10
                standPainPerLevel = 10
                stylePerLevel = 5
            }
            ClassName.darkPaladin -> {
                classIndex = 4
                statBlock = intArrayOf(
                    15, 15, 5, 20, 3,
                    60, 2, 2, 2, 2, 2, 20,
                    50, 2, 60, 3, 3, 1, 3, 3,
                    50, 20, 3,
                    2, 1, 2, 2, 2, 2, 2
                )
                composeReduction = 1
                atkPerLevel = 5
                armorPerLevel = 5
                controlPerLevel = 10
                zeonPerLevel = 20
                intimidatePerLevel = 10
                composePerLevel = 10
                stylePerLevel = 5
                persuadePerLevel = 5
            }
            ClassName.weaponMaster -> {
                classIndex = 5
                statBlock = intArrayOf(
                    10, 20, 5, 10, 3,
                    60, 2, 2, 2, 1, 3, 30,
                    50, 3, 70, 3, 3, 3, 3, 3,
                    50, 20, 3,
                    2, 2, 2, 3, 1, 3, 2
                )
                atkPerLevel = 5
                blockPerLevel = 5
                armorPerLevel = 10
                strengthFeatPerLevel = 5
            }
            ClassName.technician -> {
                classIndex = 6
                statBlock = intArrayOf(
                    20, 5, 5, 50, 3,
                    60, 2, 2, 2, 2, 1, 10,
                    50, 3, 70, 3, 3, 3, 3, 3,
                    50, 20, 3,
                    2, 2, 2, 3, 2, 2, 2
                )
                atkPerLevel = 5
            }
            ClassName.tao -> {
                classIndex = 7
                statBlock = intArrayOf(
                    20, 10, 5, 30, 3,
                    60, 2, 2, 2, 2, 2, 15,
                    50, 3, 70, 3, 3, 3, 3, 3,
                    50, 20, 3,
                    2, 2, 2, 3, 2, 2, 2
                )
                stylePerLevel = 5
            }
            ClassName.ranger -> {
                classIndex = 8
                statBlock = intArrayOf(
                    20, 10, 5, 20, 3,
                    60, 2, 2, 2, 2, 2, 25,
                    50, 3, 70, 3, 3, 3, 3, 3,
                    50, 20, 3,
                    2, 2, 1, 3, 3, 2, 2
                )
                trapReduction = 1
                herbalReduction = 2
                animalReduction = 1
                medReduction = 2
                atkPerLevel = 5
                noticePerLevel = 10
                searchPerLevel = 10
                trackPerLevel = 10
                trapPerLevel = 5
                animalPerLevel = 5
                herbPerLevel = 5
            }
            ClassName.shadow -> {
                classIndex = 9
                statBlock = intArrayOf(
                    20, 5, 10, 25, 3,
                    60, 2, 3, 2, 2, 2, 20,
                    50, 3, 70, 3, 3, 3, 3, 3,
                    50, 20, 3,
                    2, 2, 2, 3, 2, 2, 2
                )
                atkPerLevel = 5
                dodgePerLevel = 5
                noticePerLevel = 10
                searchPerLevel = 10
                hidePerLevel = 10
                stealthPerLevel = 10
            }
            ClassName.thief -> {
                classIndex = 10
                statBlock = intArrayOf(
                    20, 5, 10, 20, 3,
                    50, 2, 3, 2, 3, 2, 25,
                    50, 3, 70, 3, 3, 3, 3, 3,
                    50, 20, 3,
                    1, 2, 2, 3, 3, 1, 2
                )
                appraiseReduction = 1
                dodgePerLevel = 5
                noticePerLevel = 5
                searchPerLevel = 5
                hidePerLevel = 5
                stealthPerLevel = 5
                trapPerLevel = 5
                sleightPerLevel = 5
                theftPerLevel = 10
            }
            ClassName.assassin -> {
                classIndex = 11
                statBlock = intArrayOf(
                    20, 5, 10, 20, 3,
                    50, 2, 3, 2, 3, 2, 25,
                    50, 3, 70, 3, 3, 3, 3, 3,
                    50, 20, 3,
                    2, 2, 1, 3, 3, 2, 2
                )
                stealthReduction = 1
                composeReduction = 2
                memorizeReduction = 2
                atkPerLevel = 5
                noticePerLevel = 10
                searchPerLevel = 10
                hidePerLevel = 10
                stealthPerLevel = 10
                poisonPerLevel = 10
                composePerLevel = 10
                trapPerLevel = 10
            }
            ClassName.wizard -> {
                classIndex = 12
                statBlock = intArrayOf(
                    20, 5, 5, 10, 3,
                    50, 3, 3, 2, 3, 3, 30,
                    60, 1, 50, 2, 2, 2, 2, 2,
                    50, 20, 3,
                    2, 2, 2, 2, 3, 2, 2
                )
                magAppraiseReduction = 1
                zeonPerLevel = 100
                magAppraisePerLevel = 10
                occultPerLevel = 5
            }
            ClassName.warlock -> {
                classIndex = 13
                statBlock = intArrayOf(
                    20, 10, 5, 20, 3,
                    50, 2, 2, 2, 2, 2, 25,
                    50, 1, 50, 2, 2, 2, 2, 2,
                    50, 20, 3,
                    2, 2, 2, 2, 2, 2, 2
                )
                atkPerLevel = 5
                blockPerLevel = 5
                dodgePerLevel = 5
                zeonPerLevel = 20
                magAppraisePerLevel = 5
            }
            ClassName.illusionist -> {
                classIndex = 14
                statBlock = intArrayOf(
                    20, 5, 5, 20, 3,
                    50, 3, 3, 2, 3, 2, 25,
                    60, 1, 60, 2, 3, 3, 3, 3,
                    50, 20, 3,
                    2, 2, 2, 2, 3, 2, 2
                )
                sleightReduction = 1
                persuadeReduction = 1
                zeonPerLevel = 75
                magAppraisePerLevel = 5
                stealthPerLevel = 10
                hidePerLevel = 10
                sleightPerLevel = 10
                disguisePerLevel = 5
                theftPerLevel = 5
                persuadePerLevel = 5
            }
            ClassName.wizMentalist -> {
                classIndex = 15
                statBlock = intArrayOf(
                    20, 5, 5, 10, 1,
                    50, 3, 3, 2, 3, 3, 30,
                    50, 1, 50, 2, 2, 2, 2, 2,
                    50, 10, 2,
                    2, 2, 2, 2, 3, 2, 2
                )
                zeonPerLevel = 100
                magAppraisePerLevel = 10
                occultPerLevel = 5
            }
            ClassName.summoner -> {
                classIndex = 16
                statBlock = intArrayOf(
                    20, 5, 5, 10, 3,
                    50, 3, 3, 2, 3, 3, 30,
                    60, 1, 60, 3, 1, 1, 1, 1,
                    50, 20, 3,
                    2, 2, 2, 2, 3, 2, 2
                )
                occultReduction = 1
                zeonPerLevel = 50
                summonPerLevel = 10
                controlPerLevel = 10
                bindPerLevel = 10
                banishPerLevel = 10
                magAppraisePerLevel = 5
                occultPerLevel = 10
            }
            ClassName.warSummoner -> {
                classIndex = 17
                statBlock = intArrayOf(
                    20, 10, 5, 20, 3,
                    50, 2, 2, 2, 2, 2,
                    50, 1, 60, 3, 1, 1, 1, 1,
                    50, 20, 3,
                    2, 2, 2, 2, 2, 2, 2
                )
                atkPerLevel = 5
                blockPerLevel = 5
                dodgePerLevel = 5
                zeonPerLevel = 20
                summonPerLevel = 5
                controlPerLevel = 5
                bindPerLevel = 5
                banishPerLevel = 5
                occultPerLevel = 5
            }
            ClassName.mentalist -> {
                classIndex = 18
                statBlock = intArrayOf(
                    20, 5, 5, 10, 1,
                    50, 3, 3, 2, 3, 3, 30,
                    50, 3, 70, 3, 3, 3, 3, 3,
                    60, 10, 2,
                    2, 2, 2, 2, 3, 2, 2
                )
            }
            ClassName.warMentalist -> {
                classIndex = 19
                statBlock = intArrayOf(
                    20, 10, 5, 20, 1,
                    50, 2, 2, 2, 2, 2, 25,
                    50, 3, 70, 3, 3, 3, 3, 3,
                    50, 15, 2,
                    2, 2, 2, 3, 2, 2, 2
                )
                atkPerLevel = 5
                blockPerLevel = 5
                dodgePerLevel = 5
            }
            else -> {
                classIndex = 0
                statBlock = intArrayOf(
                    20, 5, 5, 20, 2,
                    60, 2, 2, 2, 2, 2, 20,
                    60, 2, 60, 2, 2, 2, 2, 2,
                    60, 20, 2,
                    2, 2, 2, 2, 2, 2, 2
                )
                zeonPerLevel = 10
            }
        }
        setClassBase(statBlock)
    }
}