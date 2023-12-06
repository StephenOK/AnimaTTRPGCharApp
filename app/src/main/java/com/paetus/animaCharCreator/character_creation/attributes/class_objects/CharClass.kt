package com.paetus.animaCharCreator.character_creation.attributes.class_objects

import com.paetus.animaCharCreator.enumerations.Archetype

/**
 * Object class that holds values related to a character's class
 *
 * @param saveName name to write to file
 * @param archetype enumeration list of archetypes related to this class
 *
 * @param lifePointMultiple cost of life point multiples for this class
 * @param lifePointsPerLevel life points gained per level for this class
 * @param initiativePerLevel initiative gained from this class per level
 * @param mkPerLevel martial knowledge gained from this class per level
 * @param psyPerTurn innate psychic points for this class
 *
 * @param combatMax max percentage of investment in the combat section
 * @param atkGrowth points spent per point in attack
 * @param blockGrowth points spent per point in block
 * @param dodgeGrowth points spent per point in dodge
 * @param armorGrowth points spent per point in wear armor
 * @param kiGrowth points spent per ki point bought
 * @param kiAccumMult paints spent per ki accumulation point bought
 *
 * @param magMax max percentage of investment in the magic section
 * @param zeonGrowth points spent per five zeon points gained
 * @param maGrowth points spent per magic accumulation multiple gained
 * @param maProjGrowth points spent per magic projection gained
 * @param summonGrowth points spent per summon point gained
 * @param controlGrowth points spent per control point gained
 * @param bindGrowth points spent per bind point gained
 * @param banishGrowth points spent per banish point gained
 *
 * @param psyMax max percentage of investment in the psychic section
 * @param psyPointGrowth points spent per psychic point bought
 * @param psyProjGrowth points spent per psychic projection point bought
 *
 * @param athGrowth default points spent on athletics secondaries
 * @param socGrowth default points spent on social secondaries
 * @param percGrowth default points spent on perception secondaries
 * @param intellGrowth default points spent on intelligence secondaries
 * @param vigGrowth default points spent on vigor secondaries
 * @param subterGrowth default points spent on subterfuge secondaries
 * @param createGrowth default points spent on creative secondaries
 *
 * @param onTake function to run on class acquisition
 * @param onRemove function to run on class removal
 */
class CharClass(
    val saveName: String,
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
    val createGrowth: Int,

    val onTake: () -> Unit,
    val onRemove: () -> Unit
)