package com.example.animabuilder.character_creation.attributes.psychic.disciplines

import com.example.animabuilder.character_creation.attributes.psychic.Discipline
import com.example.animabuilder.character_creation.attributes.psychic.PsychicPower
import java.io.Serializable

/**
 * Record of all of the available physically increasing powers.
 */
class PhysicalIncrease: Discipline(), Serializable {
    val increaseJump = PsychicPower(
        "Increase Jump Ability",
        1,
        true,
        true,
        "The psychic can jump extraordinarily high. The psychic adds a variable bonus to " +
                "his Jump Ability (in some cases reaching to Inhuman or Zen levels), depending on " +
                "his success in activating this Power.",
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
        "Increase Ability",
        1,
        true,
        true,
        "This Power enhances a psychic's Dexterity or Agility. If the psychic chooses " +
                "Agility, only the Characteristic score increases; Movement Value does not " +
                "increase. Progression is cut in half once the increased Characteristic reaches " +
                "10. In other words, two added points are necessary to increase one point past " +
                "that number.",
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
        "Increase Acrobatics",
        1,
        true,
        true,
        "The psychic can perform stunning acrobatic tricks and somersaults of almost " +
                "Supernatural proportions. To this effect, a certain bonus is added to the " +
                "Acrobatics Ability. In some cases, a psychic's Acrobatics Ability can reach to " +
                "Inhuman or even Zen levels.",
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
        "Increase Strength",
        1,
        true,
        true,
        "This Power increases the psychic's Strength. Progression is cut in half once " +
                "the increased Characteristic has gone beyond 10. In other words, two added points " +
                "are necessary to increase 1 point past that number.",
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
        "Inhumanity",
        1,
        true,
        true,
        "With this Power, psychics can reach Inhuman Difficulty levels when performing " +
                "physical actions. In addition, Inhumanity improves all Athletics Secondary " +
                "Abilities by applying bonuses to the corresponding rolls.",
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
        "Increase Motion",
        1,
        true,
        true,
        "Psychics can move at much higher speeds than usual, increasing their Movement " +
                "Value. Progression will be cut in half once their Movement Value reaches 10.",
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
        "Increase Reaction",
        2,
        true,
        true,
        "By increasing their reaction speed and enhancing their senses, this ability " +
                "allows psychics to jump ahead of normal people when responding to events. " +
                "Reaction Increase provides a special Initiative bonus for the next turn.",
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
        "Perception Increase",
        2,
        true,
        true,
        "This Power enhances a character's perceptive capabilities. In gaming terms, it " +
                "adds points to a psychic's Perception. Progression will be cut in half if the " +
                "Characteristic exceeds 10.",
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
        "Increase Endurance",
        2,
        false,
        true,
        "The psychic is able to strengthen his body's resistance by controlling his own " +
                "cells. In this way, he can absorb damage and receive impacts without suffering " +
                "the consequences. This Power increases a character's PhR as determined by the " +
                "success achieved in activating it (see the Effects Table).",
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
        "Regeneration",
        2,
        true,
        true,
        "By controlling their bodies on a very primary level, psychics can increase " +
                "their healing rate. This Power increases a character's Regeneration level - " +
                "although it cannot increase it past 18.",
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
        "Fatigue Elimination",
        3,
        true,
        false,
        "By using energy from their Psychic Matrix, characters can unload physical " +
                "exhaustion, restoring some of their lost Fatigue Points. Fatigue Points lost due " +
                "to failing in the use of Psychic Powers cannot be restored with this power.",
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
        "Total Increase",
        3,
        true,
        true,
        "Characters can increase all of their Physical Characteristics at once. This " +
                "Power provides a bonus to Strength, Dexterity, Agility, Constitution, and also " +
                "Perception. The effects of this Power will add up with those of any other " +
                "increase kept active by the character. Progression will be cut in half once " +
                "their characteristics reach 10.",
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
        "Imbue",
        3,
        true,
        true,
        "This Power allows the psychic to use abilities from this Discipline on other " +
                "characters. Imbued Powers cannot have an activation Difficulty higher than the " +
                "one indicated on the Effects Table below.",
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

    override var allPowers = listOf(increaseJump, increaseAbility, increaseAcrobatics, increaseStrength,
        inhumanity, increaseMotion, increaseReaction, perceptionIncrease, increaseEndurance,
        regeneration, fatigueElimination, totalIncrease, imbue)
}