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
        R.string.increaseJump,
        1,
        true,
        true,
        R.string.increaseJumpDesc,
        listOf(
            "Fatigue 2",
            "+10 to Jump",
            "+20 to Jump",
            "+40 to Jump",
            "+80 to Jump",
            "+120 to Jump/Inhumanity",
            "+180 to Jump/Inhumanity",
            "+220 to Jump/Inhumanity",
            "+280 to Jump/Zen",
            "+280 to Jump/Zen",
            "+320 to Jump/Zen"
        )
    )

    val increaseAbility = PsychicPower(
        "increaseAbility",
        R.string.increaseAbility,
        1,
        true,
        true,
        R.string.increaseAbilityDesc,
        listOf(
            "Fatigue 4",
            "Fatigue 2",
            "Dex or Agi +1",
            "Dex or Agi +2",
            "Dex or Agi +3",
            "Dex or Agi +4",
            "Dex or Agi +5",
            "Dex or Agi +6",
            "Dex or Agi +8",
            "Dex or Agi +10"
        )
    )

    val increaseAcrobatics = PsychicPower(
        "increaseAcro",
        R.string.increaseAcrobatics,
        1,
        true,
        true,
        R.string.increaseAcrobaticsDesc,
        listOf(
            "Fatigue 2",
            "+10 Acrobatics",
            "+20 Acrobatics",
            "+40 Acrobatics",
            "+80 Acrobatics",
            "+120 Acrobatics/Inhumanity",
            "+180 Acrobatics/Inhumanity",
            "+220 Acrobatics/Inhumanity",
            "+280 Acrobatics/Zen",
            "+320 Acrobatics/Zen"
        )
    )

    val increaseStrength = PsychicPower(
        "increaseStr",
        R.string.increaseStrength,
        1,
        true,
        true,
        R.string.increaseStrengthDesc,
        listOf(
            "Fatigue 4",
            "Fatigue 2",
            "Strength +1",
            "Strength +2",
            "Strength +3",
            "Strength +4",
            "Strength +5",
            "Strength +6",
            "Strength +8",
            "Strength +10"
        )
    )

    val inhumanity = PsychicPower(
        "inhumanity",
        R.string.inhumanityPower,
        1,
        true,
        true,
        R.string.inhumanityPowerDesc,
        listOf(
            "Fatigue 4",
            "Fatigue 2",
            "Inhumanity",
            "Inhumanity/+5 to Athletics Abilities",
            "Inhumanity/+10 to Athletics Abilities",
            "Inhumanity/+20 to Athletics Abilities",
            "Zen/+30 to Athletics Abilities",
            "Zen/+40 to Athletics Abilities",
            "Zen/+60 to Athletics Abilities",
            "Zen/+80 to Athletics Abilities"
        )
    )

    val increaseMotion = PsychicPower(
        "increaseMotion",
        R.string.increaseMotion,
        1,
        true,
        true,
        R.string.increaseMotionDesc,
        listOf(
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "Movement Value +1",
            "Movement Value +2",
            "Movement Value +3",
            "Movement Value +4",
            "Movement Value +5",
            "Movement Value +6",
            "Movement Value +8"
        )
    )

    val increaseReaction = PsychicPower(
        "increaseReaction",
        R.string.increaseReactionPower,
        2,
        true,
        true,
        R.string.increaseReactionPowerDesc,
        listOf(
            "Fatigue 8",
            "Fatigue 4",
            "Fatigue 2",
            "+20 to Initiative",
            "+40 to Initiative",
            "+60 to Initiative",
            "+80 to Initiative",
            "+120 to Initiative",
            "+160 to Initiative",
            "+200 to Initiative"
        )
    )

    val perceptionIncrease = PsychicPower(
        "percIncrease",
        R.string.percIncrease,
        2,
        true,
        true,
        R.string.percIncreaseDesc,
        listOf(
            "Fatigue 8",
            "Fatigue 4",
            "Fatigue 2",
            "Perception +1",
            "Perception +2",
            "Perception +3",
            "Perception +4",
            "Perception +5",
            "Perception +6",
            "Perception +8"
        )
    )

    val increaseEndurance = PsychicPower(
        "increaseEndurance",
        R.string.increaseEndurance,
        2,
        false,
        true,
        R.string.increaseEnduranceDesc,
        listOf(
            "Fatigue 8",
            "Fatigue 4",
            "Fatigue 2",
            "+10 to PhR",
            "+20 to PhR",
            "+40 to PhR",
            "+80 to PhR",
            "+120 to PhR",
            "+160 to PhR",
            "+200 to PhR"
        )
    )

    val regeneration = PsychicPower(
        "regeneration",
        R.string.regenPower,
        2,
        true,
        true,
        R.string.regenPowerDesc,
        listOf(
            "+1 to Regeneration Level",
            "+2 to Regeneration Level",
            "+4 to Regeneration Level",
            "+6 to Regeneration Level",
            "+8 to Regeneration Level",
            "+10 to Regeneration Level",
            "+12 to Regeneration Level"
        )
    )

    val fatigueElimination = PsychicPower(
        "fatigueEliminate",
        R.string.fatigueEliminate,
        3,
        true,
        false,
        R.string.fatigueEliminateDesc,
        listOf(
            "Fatigue 16",
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "Recovery of 2 Fatigue Points",
            "Recovery of 4 Fatigue Points",
            "Recovery of 6 Fatigue Points",
            "Recovery of 10 Fatigue Points",
            "Complete Recovery"
        )
    )

    val totalIncrease = PsychicPower(
        "totalIncrease",
        R.string.totalIncrease,
        3,
        true,
        true,
        R.string.totalIncreaseDesc,
        listOf(
            "Fatigue 16",
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "Physical Characteristics +1",
            "Physical Characteristics +2",
            "Physical Characteristics +4",
            "Physical Characteristics +6",
            "Physical Characteristics +8"
        )
    )

    val imbue = PsychicPower(
        "imbue",
        R.string.imbue,
        3,
        true,
        true,
        R.string.imbueDesc,
        listOf(
            "Fatigue 16",
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "Very Difficult-level Powers",
            "Absurd-level Powers",
            "Almost Impossible-level Powers",
            "Impossible-level Powers",
            "Inhuman-level Powers"
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