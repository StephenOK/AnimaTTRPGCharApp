package com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.abilities

import com.paetus.animaCharCreator.R

/**
 * Record of Ki Abilities the character can take.
 */
class KiRecord{
    val useOfKi = KiAbility(
        saveTag = "useOfKi",
        name = R.string.kiUse,
        prerequisite = null,
        mkCost = 40,
        description = R.string.kiUseDesc
    )

    val kiControl = KiAbility(
        saveTag = "kiControl",
        name = R.string.kiControl,
        prerequisite = useOfKi,
        mkCost = 30,
        description = R.string.kiControlDesc
    )

    private val kiDetection = KiAbility(
        saveTag = "kiDetection",
        name = R.string.kiDetection,
        prerequisite = kiControl,
        mkCost = 20,
        description = R.string.kiDetectionDesc
    )

    private val erudition = KiAbility(
        saveTag = "erudition",
        name = R.string.erudition,
        prerequisite = kiDetection,
        mkCost = 10,
        description = R.string.eruditionDesc
    )

    private val weightElimination = KiAbility(
        saveTag = "weightElimination",
        name = R.string.weightElim,
        prerequisite = useOfKi,
        mkCost = 10,
        description = R.string.weightElimDesc
    )

    private val levitation = KiAbility(
        saveTag = "levitation",
        name = R.string.levitation,
        prerequisite = weightElimination,
        mkCost = 20,
        description = R.string.leviDesc
    )

    private val objectMotion = KiAbility(
        saveTag = "objectMotion",
        name = R.string.objectMotion,
        prerequisite = levitation,
        mkCost = 10,
        description = R.string.objectMotionDesc
    )

    val flight = KiAbility(
        saveTag = "flight",
        name = R.string.flight,
        prerequisite = levitation,
        mkCost = 20,
        description = R.string.flightDesc
    )

    val presenceExtrusion = KiAbility(
        saveTag = "presenceExtrusion",
        name = R.string.presExtrude,
        prerequisite = useOfKi,
        mkCost = 10,
        description = R.string.presExtrudeDesc
    )

    private val energyArmor = KiAbility(
        saveTag = "energyArmor",
        name = R.string.energyArmor,
        prerequisite = presenceExtrusion,
        mkCost = 10,
        description = R.string.energyArmorDesc
    )

    private val auraExtension = KiAbility(
        saveTag = "auraExtension",
        name = R.string.auraExt,
        prerequisite = presenceExtrusion,
        mkCost = 10,
        description = R.string.auraExtDesc
    )

    private val destructionByKi = KiAbility(
        saveTag = "kiDestruction",
        name = R.string.kiDestruction,
        prerequisite = presenceExtrusion,
        mkCost = 20,
        description = R.string.kiDestructionDesc
    )

    private val kiTransmission = KiAbility(
        saveTag = "kiTransmit",
        name = R.string.kiTransmit,
        prerequisite = useOfKi,
        mkCost = 10,
        description = R.string.kiTransmitDesc
    )

    private val kiHealing = KiAbility(
        saveTag = "kiHeal",
        name = R.string.kiHeal,
        prerequisite = kiTransmission,
        mkCost = 10,
        description = R.string.kiHealDesc
    )

    val useOfNecessaryEnergy = KiAbility(
        saveTag = "useOfNecEnergy",
        name = R.string.useNecEnergy,
        prerequisite = useOfKi,
        mkCost = 10,
        description = R.string.useNecEnergyDesc
    )

    private val kiConcealment = KiAbility(
        saveTag = "kiConceal",
        name = R.string.kiConceal,
        prerequisite = useOfNecessaryEnergy,
        mkCost = 10,
        description = R.string.kiConcealDesc
    )

    private val falseDeath = KiAbility(
        saveTag = "falseDeath",
        name = R.string.falseDeath,
        prerequisite = kiConcealment,
        mkCost = 10,
        description = R.string.falseDeathDesc
    )

    private val eliminateNecessities = KiAbility(
        saveTag = "eliminateNecessities",
        name = R.string.elimNecess,
        prerequisite = useOfNecessaryEnergy,
        mkCost = 10,
        description = R.string.elimNecessDesc
    )

    private val penaltyReduction = KiAbility(
        saveTag = "penaltyReduce",
        name = R.string.penaltyReduce,
        prerequisite = useOfNecessaryEnergy,
        mkCost = 20,
        description = R.string.penaltyReduceDesc
    )

    val recovery = KiAbility(
        saveTag = "recovery",
        name = R.string.recovery,
        prerequisite = penaltyReduction,
        mkCost = 20,
        description = R.string.recoveryDesc
    )

    private val charAugmentation = KiAbility(
        saveTag = "charAugmentation",
        name = R.string.charAugment,
        prerequisite = useOfNecessaryEnergy,
        mkCost = 20,
        description = R.string.charAugmentDesc
    )

    val inhumanity = KiAbility(
        saveTag = "inhumanity",
        name = R.string.inhumanity,
        prerequisite = useOfKi,
        mkCost = 30,
        description = R.string.inhumanityDesc
    )

    private val zen = KiAbility(
        saveTag = "zen",
        name = R.string.zen,
        prerequisite = inhumanity,
        mkCost = 50,
        description = R.string.zenDesc
    )

    //compile all ki abilities together
    val allKiAbilities = listOf(useOfKi, kiControl, kiDetection, erudition, weightElimination,
        levitation, objectMotion, flight, presenceExtrusion, energyArmor, auraExtension,
        destructionByKi, kiTransmission, kiHealing, useOfNecessaryEnergy, kiConcealment, falseDeath,
        eliminateNecessities, penaltyReduction, recovery, charAugmentation, inhumanity, zen)
}