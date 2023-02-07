package com.example.animabuilder.character_creation.attributes.class_objects

import java.io.Serializable

/**
 * Object class that holds values related to a character's class
 */
class CharClass(
    val heldClass: ClassName,
    val archetype: List<Archetype>,

    val lifePointMultiple: Int,
    val lifePointsPerLevel: Int,
    val initiativePerLevel: Int,
    val mkPerLevel: Int,
    val psyPerTurn: Int,

    val combatMax: Double,
    val atkGrowth: Int,
    val blockGrowth: Int,
    val dodgeGrowth: Int,
    val armorGrowth: Int,
    val kiGrowth: Int,
    val kiAccumMult: Int,

    val magMax: Double,
    val zeonGrowth: Int,
    val maGrowth: Int,
    val maProjGrowth: Int,
    val summonGrowth: Int,
    val controlGrowth: Int,
    val bindGrowth: Int,
    val banishGrowth: Int,

    val psyMax: Double,
    val psyPointGrowth: Int,
    val psyProjGrowth: Int,

    val athGrowth: Int,
    val socGrowth: Int,
    val percGrowth: Int,
    val intellGrowth: Int,
    val vigGrowth: Int,
    val subterGrowth: Int,
    val creatGrowth: Int,

    val onTake: () -> Unit,
    val onRemove: () -> Unit
) : Serializable