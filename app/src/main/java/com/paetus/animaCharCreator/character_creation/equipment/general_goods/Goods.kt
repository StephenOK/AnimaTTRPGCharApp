package com.paetus.animaCharCreator.character_creation.equipment.general_goods

import com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances.Armors
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances.Art
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances.Clothing
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances.Dwellings
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances.FoodAndDrink
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances.Gems
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances.Lodging
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances.Miscellaneous
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances.Painting
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances.Poisons
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances.Services
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances.Transport
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances.Travel
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances.Weapons

/**
 * Data set for purchasable items for the character.
 */
class Goods {
    private val clothing = Clothing()
    private val travel = Travel()
    private val transport = Transport()
    private val foodAndDrink = FoodAndDrink()
    private val lodging = Lodging()
    private val dwellings = Dwellings()
    private val services = Services()
    private val art = Art()
    private val gems = Gems()
    private val painting = Painting()
    private val poisons = Poisons()
    private val miscellaneous = Miscellaneous()
    private val weapons = Weapons()
    private val armors = Armors()

    val allCategories = listOf(
        clothing,
        travel,
        transport,
        foodAndDrink,
        lodging,
        dwellings,
        services,
        art,
        gems,
        painting,
        poisons,
        miscellaneous,
        weapons,
        armors
    )
}