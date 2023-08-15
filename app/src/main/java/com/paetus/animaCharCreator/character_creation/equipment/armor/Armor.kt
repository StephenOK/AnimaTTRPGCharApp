package com.paetus.animaCharCreator.character_creation.equipment.armor

import com.paetus.animaCharCreator.enumerations.ArmorLocation

/**
 * Object that defines a piece of armor a character may acquire.
 *
 * @param name displayed string for the item's name
 *
 * @param wearRequired character's wear armor needed to effectively use this armor
 * @param natPenalty initiative and secondary characteristic penalty for those unable to
 * effectively use this armor
 * @param moveRestriction movement value reduction when wearing this armor
 * @param fortitude armor's resistance to breaking
 * @param presence size of the armor
 * @param location part of the body protected by the armor
 * @param isHard true if the armor is in the hard armor class
 *
 * @param cutRes armor's cut resistance value
 * @param impactRes armor's impact resistance value
 * @param thrustRes armor's thrust resistance value
 * @param heatRes armor's heat resistance value
 * @param elecRes armor's electricity resistance value
 * @param coldRes armor's cold resistance value
 * @param energyRes armor's energy resistance value
 *
 * @param description details on the armor's properties
 */
class Armor(
    val name: String,

    val wearRequired: Int,
    val natPenalty: Int,
    val moveRestriction: Int,
    val fortitude: Int,
    val presence: Int,
    val location: ArmorLocation,
    val isHard: Boolean,

    val cutRes: Int,
    val impactRes: Int,
    val thrustRes: Int,
    val heatRes: Int,
    val elecRes: Int,
    val coldRes: Int,
    val energyRes: Int,

    val description: String
)