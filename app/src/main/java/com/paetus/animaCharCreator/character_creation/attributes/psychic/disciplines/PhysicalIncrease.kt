package com.paetus.animaCharCreator.character_creation.attributes.psychic.disciplines

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.psychic.Discipline
import com.paetus.animaCharCreator.character_creation.attributes.psychic.PsychicPower

/**
 * Record of all of the available physically increasing powers.
 */
class PhysicalIncrease: Discipline(saveName = "physIncrease"){
    private val increaseJump = PsychicPower(
        saveName = "increaseJump",
        name = 52,
        level = 1,
        isActive = true,
        maintained = true,
        description = R.string.increaseJumpDesc,
        stringBaseList = listOf(R.string.jumpIncrease, R.string.jumpIncreaseInhuman, R.string.jumpIncreaseZen),
        stringBaseCount = listOf(1, 5, 8, 10),
        stringInput = listOf(
            2,
            10,
            20,
            40,
            80,
            120,
            180,
            220,
            280,
            320
        )
    )

    private val increaseAbility = PsychicPower(
        saveName = "increaseAbility",
        name = 53,
        level = 1,
        isActive = true,
        maintained = true,
        description = R.string.increaseAbilityDesc,
        stringBaseList = listOf(R.string.dexOrAgiIncrease),
        stringBaseCount = listOf(2, 10),
        stringInput = listOf(
            4, 2,
            1,
            2,
            3,
            4,
            5,
            6,
            8,
            10
        )
    )

    private val increaseAcrobatics = PsychicPower(
        saveName = "increaseAcro",
        name = 54,
        level = 1,
        isActive = true,
        maintained = true,
        description = R.string.increaseAcrobaticsDesc,
        stringBaseList = listOf(R.string.acroIncrease, R.string.acroIncreaseInhuman, R.string.acroIncreaseZen),
        stringBaseCount = listOf(1, 5, 8, 10),
        stringInput = listOf(
            2,
            10,
            20,
            40,
            80,
            120,
            180,
            220,
            280,
            320
        )
    )

    private val increaseStrength = PsychicPower(
        saveName = "increaseStr",
        name = 55,
        level = 1,
        isActive = true,
        maintained = true,
        description = R.string.increaseStrengthDesc,
        stringBaseList = listOf(R.string.strengthIncrease),
        stringBaseCount = listOf(2, 10),
        stringInput = listOf(
            4, 2,
            1,
            2,
            3,
            4,
            5,
            6,
            8,
            10
        )
    )

    private val inhumanity = PsychicPower(
        saveName = "inhumanity",
        name = 56,
        level = 1,
        isActive = true,
        maintained = true,
        description = R.string.inhumanityPowerDesc,
        stringBaseList = listOf(R.string.inhumanityPower, R.string.athleticsIncrease, R.string.athleticsIncreaseZen),
        stringBaseCount = listOf(2, 3, 6, 10),
        stringInput = listOf(
            4, 2,
            null,
            5,
            10,
            20,
            30,
            40,
            60,
            80
        )
    )

    private val increaseMotion = PsychicPower(
        saveName = "increaseMotion",
        name = 57,
        level = 1,
        isActive = true,
        maintained = true,
        description = R.string.increaseMotionDesc,
        stringBaseList = listOf(R.string.movementValIncrease),
        stringBaseCount = listOf(3, 10),
        stringInput = listOf(
            6, 4, 2,
            1,
            2,
            3,
            4,
            5,
            6,
            8
        )
    )

    private val increaseReaction = PsychicPower(
        saveName = "increaseReaction",
        name = 58,
        level = 2,
        isActive = true,
        maintained = true,
        description = R.string.increaseReactionPowerDesc,
        stringBaseList = listOf(R.string.initiativeIncrease),
        stringBaseCount = listOf(3, 10),
        stringInput = listOf(
            8, 4, 2,
            20,
            40,
            60,
            80,
            120,
            160,
            200
        )
    )

    private val perceptionIncrease = PsychicPower(
        saveName = "percIncrease",
        name = 59,
        level = 2,
        isActive = true,
        maintained = true,
        description = R.string.percIncreaseDesc,
        stringBaseList = listOf(R.string.perceptionIncrease),
        stringBaseCount = listOf(3, 10),
        stringInput = listOf(
            8, 4, 2,
            1,
            2,
            3,
            4,
            5,
            6,
            8
        )
    )

    private val increaseEndurance = PsychicPower(
        saveName = "increaseEndurance",
        name = 60,
        level = 2,
        isActive = false,
        maintained = true,
        description = R.string.increaseEnduranceDesc,
        stringBaseList = listOf(R.string.addPhysRes),
        stringBaseCount = listOf(3, 10),
        stringInput = listOf(
            8, 4, 2,
            10,
            20,
            40,
            80,
            120,
            160,
            200
        )
    )

    val regeneration = PsychicPower(
        saveName = "regeneration",
        name = 61,
        level = 2,
        isActive = true,
        maintained = true,
        description = R.string.regenPowerDesc,
        stringBaseList = listOf(R.string.regenerationIncrease),
        stringBaseCount = listOf(3, 10),
        stringInput = listOf(
            8, 6, 4,
            1,
            2,
            4,
            6,
            8,
            10,
            12
        )
    )

    private val fatigueElimination = PsychicPower(
        saveName = "fatigueEliminate",
        name = 62,
        level = 3,
        isActive = true,
        maintained = false,
        description = R.string.fatigueEliminateDesc,
        stringBaseList = listOf(R.string.fatigueRecovery, R.string.completeRecovery),
        stringBaseCount = listOf(5, 9, 10),
        stringInput = listOf(
            16, 12, 8, 6, 4,
            2,
            4,
            6,
            10,
            null
        )
    )

    private val totalIncrease = PsychicPower(
        saveName = "totalIncrease",
        name = 63,
        level = 3,
        isActive = true,
        maintained = true,
        description = R.string.totalIncreaseDesc,
        stringBaseList = listOf(R.string.physCharIncreaseInput),
        stringBaseCount = listOf(5, 10),
        stringInput = listOf(
            16, 12, 8, 6, 4,
            1,
            2,
            4,
            6,
            8
        )
    )

    private val imbue = PsychicPower(
        saveName = "imbue",
        name = 64,
        level = 3,
        isActive = true,
        maintained = true,
        description = R.string.imbueDesc,
        stringBaseList = listOf(R.string.powerLevel),
        stringBaseCount = listOf(5, 10),
        stringInput = listOf(
            16, 12, 8, 6, 4,
            {R.string.veryDifficult},
            {R.string.absurd},
            {R.string.almostImpossible},
            {R.string.impossible},
            {R.string.inhuman}
        )
    )

    init {
        allPowers.addAll(
            elements = listOf(
                increaseJump,
                increaseAbility,
                increaseAcrobatics,
                increaseStrength,
                inhumanity,
                increaseMotion,
                increaseReaction,
                perceptionIncrease,
                increaseEndurance,
                regeneration,
                fatigueElimination,
                totalIncrease,
                imbue
            )
        )
    }
}