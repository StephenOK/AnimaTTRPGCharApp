package com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.abilities

import com.paetus.animaCharCreator.R

/**
 * Record of Ki Abilities the character can take.
 */
class KiRecord{
    val useOfKi = KiAbility(
        "useOfKi",
        R.string.kiUse,
        null,
        40,
        R.string.kiUseDesc
    )

    val kiControl = KiAbility(
        "kiControl",
        R.string.kiControl,
        useOfKi,
        30,
        R.string.kiControlDesc
    )

    val kiDetection = KiAbility(
        "kiDetection",
        R.string.kiDetection,
        kiControl,
        20,
        R.string.kiDetectionDesc
    )

    val erudition = KiAbility(
        "erudition",
        R.string.erudition,
        kiDetection,
        10,
        R.string.eruditionDesc
    )

    val weightElimination = KiAbility(
        "weightElimination",
        R.string.weightElim,
        useOfKi,
        10,
        R.string.weightElimDesc
    )

    val levitation = KiAbility(
        "levitation",
        R.string.levitation,
        weightElimination,
        20,
        R.string.leviDesc
    )

    val objectMotion = KiAbility(
        "objectMotion",
        R.string.objectMotion,
        levitation,
        10,
        R.string.objectMotionDesc
    )

    val flight = KiAbility(
        "flight",
        R.string.flight,
        levitation,
        20,
        R.string.flightDesc
    )

    val presenceExtrusion = KiAbility(
        "presenceExtrusion",
        R.string.presExtrude,
        useOfKi,
        10,
        R.string.presExtrudeDesc
    )

    val energyArmor = KiAbility(
        "energyArmor",
        R.string.energyArmor,
        presenceExtrusion,
        10,
        R.string.energyArmorDesc
    )

    val auraExtension = KiAbility(
        "auraExtension",
        R.string.auraExt,
        presenceExtrusion,
        10,
        R.string.auraExtDesc
    )

    val destructionByKi = KiAbility(
        "kiDestruction",
        R.string.kiDestruction,
        presenceExtrusion,
        20,
        R.string.kiDestructionDesc
    )

    val kiTransmission = KiAbility(
        "kiTransmit",
        R.string.kiTransmit,
        useOfKi,
        10,
        R.string.kiTransmitDesc
    )

    val kiHealing = KiAbility(
        "kiHeal",
        R.string.kiHeal,
        kiTransmission,
        10,
        R.string.kiHealDesc
    )

    val useOfNecessaryEnergy = KiAbility(
        "useOfNecEnergy",
        R.string.useNecEnergy,
        useOfKi,
        10,
        R.string.useNecEnergyDesc
    )

    val kiConcealment = KiAbility(
        "kiConceal",
        R.string.kiConceal,
        useOfNecessaryEnergy,
        10,
        R.string.kiConcealDesc
    )

    val falseDeath = KiAbility(
        "falseDeath",
        R.string.falseDeath,
        kiConcealment,
        10,
        R.string.falseDeathDesc
    )

    val eliminateNecessities = KiAbility(
        "eliminateNecessities",
        R.string.elimNecess,
        useOfNecessaryEnergy,
        10,
        R.string.elimNecessDesc
    )

    val penaltyReduction = KiAbility(
        "penaltyReduce",
        R.string.penaltyReduce,
        useOfNecessaryEnergy,
        20,
        R.string.penaltyReduceDesc
    )

    val recovery = KiAbility(
        "recovery",
        R.string.recovery,
        penaltyReduction,
        20,
        R.string.recoveryDesc
    )

    val charAugmentation = KiAbility(
        "charAugmentation",
        R.string.charAugment,
        useOfNecessaryEnergy,
        20,
        R.string.charAugmentDesc
    )

    val inhumanity = KiAbility(
        "inhumanity",
        R.string.inhumanity,
        useOfKi,
        30,
        R.string.inhumanityDesc
    )

    val zen = KiAbility(
        "zen",
        R.string.zen,
        inhumanity,
        50,
        R.string.zenDesc
    )

    //compile all ki abilities together
    val allKiAbilities = listOf(useOfKi, kiControl, kiDetection, erudition, weightElimination,
        levitation, objectMotion, flight, presenceExtrusion, energyArmor, auraExtension,
        destructionByKi, kiTransmission, kiHealing, useOfNecessaryEnergy, kiConcealment, falseDeath,
        eliminateNecessities, penaltyReduction, recovery, charAugmentation, inhumanity, zen)
}