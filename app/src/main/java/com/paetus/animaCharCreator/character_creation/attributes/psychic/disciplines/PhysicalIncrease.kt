package com.paetus.animaCharCreator.character_creation.attributes.psychic.disciplines

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.psychic.Discipline
import com.paetus.animaCharCreator.character_creation.attributes.psychic.PsychicPower

/**
 * Record of all of the available physically increasing powers.
 */
class PhysicalIncrease: Discipline("physIncrease"){
    val increaseJump = PsychicPower(
        "increaseJump",
        52,
        1,
        true,
        true,
        R.string.increaseJumpDesc,
        listOf(R.string.jumpIncrease, R.string.jumpIncreaseInhuman, R.string.jumpIncreaseZen),
        listOf(1, 5, 8, 10),
        listOf(
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

    val increaseAbility = PsychicPower(
        "increaseAbility",
        53,
        1,
        true,
        true,
        R.string.increaseAbilityDesc,
        listOf(R.string.dexOrAgiIncrease),
        listOf(2, 10),
        listOf(
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

    val increaseAcrobatics = PsychicPower(
        "increaseAcro",
        54,
        1,
        true,
        true,
        R.string.increaseAcrobaticsDesc,
        listOf(R.string.acroIncrease, R.string.acroIncreaseInhuman, R.string.acroIncreaseZen),
        listOf(1, 5, 8, 10),
        listOf(
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

    val increaseStrength = PsychicPower(
        "increaseStr",
        55,
        1,
        true,
        true,
        R.string.increaseStrengthDesc,
        listOf(R.string.strengthIncrease),
        listOf(2, 10),
        listOf(
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

    val inhumanity = PsychicPower(
        "inhumanity",
        56,
        1,
        true,
        true,
        R.string.inhumanityPowerDesc,
        listOf(R.string.inhumanityPower, R.string.athleticsIncrease, R.string.athleticsIncreaseZen),
        listOf(2, 3, 6, 10),
        listOf(
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

    val increaseMotion = PsychicPower(
        "increaseMotion",
        57,
        1,
        true,
        true,
        R.string.increaseMotionDesc,
        listOf(R.string.movementValIncrease),
        listOf(3, 10),
        listOf(
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

    val increaseReaction = PsychicPower(
        "increaseReaction",
        58,
        2,
        true,
        true,
        R.string.increaseReactionPowerDesc,
        listOf(R.string.initiativeIncrease),
        listOf(3, 10),
        listOf(
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

    val perceptionIncrease = PsychicPower(
        "percIncrease",
        59,
        2,
        true,
        true,
        R.string.percIncreaseDesc,
        listOf(R.string.perceptionIncrease),
        listOf(3, 10),
        listOf(
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

    val increaseEndurance = PsychicPower(
        "increaseEndurance",
        60,
        2,
        false,
        true,
        R.string.increaseEnduranceDesc,
        listOf(R.string.addPhysRes),
        listOf(3, 10),
        listOf(
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
        "regeneration",
        61,
        2,
        true,
        true,
        R.string.regenPowerDesc,
        listOf(R.string.regenerationIncrease),
        listOf(3, 10),
        listOf(
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

    val fatigueElimination = PsychicPower(
        "fatigueEliminate",
        62,
        3,
        true,
        false,
        R.string.fatigueEliminateDesc,
        listOf(R.string.fatigueRecovery, R.string.completeRecovery),
        listOf(5, 9, 10),
        listOf(
            16, 12, 8, 6, 4,
            2,
            4,
            6,
            10,
            null
        )
    )

    val totalIncrease = PsychicPower(
        "totalIncrease",
        63,
        3,
        true,
        true,
        R.string.totalIncreaseDesc,
        listOf(R.string.physCharIncreaseInput),
        listOf(5, 10),
        listOf(
            16, 12, 8, 6, 4,
            1,
            2,
            4,
            6,
            8
        )
    )

    val imbue = PsychicPower(
        "imbue",
        64,
        3,
        true,
        true,
        R.string.imbueDesc,
        listOf(R.string.powerLevel),
        listOf(5, 10),
        listOf(
            16, 12, 8, 6, 4,
            {R.string.veryDifficult},
            {R.string.absurd},
            {R.string.almostImpossible},
            {R.string.impossible},
            {R.string.inhuman}
        )
    )

    init {
        allPowers.addAll(listOf(
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
        ))
    }
}