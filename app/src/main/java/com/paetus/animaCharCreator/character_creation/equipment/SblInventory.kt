package com.paetus.animaCharCreator.character_creation.equipment

import com.paetus.animaCharCreator.character_creation.SblChar
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment

/**
 * Class that manages the purchased items and maximum coin values for an SBL Character.
 *
 * @param sblChar object that holds all of the character's data
 */
class SblInventory(
    val sblChar: SblChar
): Inventory(sblChar){
    /**
     * Setter for the character's maximum gold limit.
     *
     * @param maxVal value to set the maximum to
     */
    override fun setMaxGold(maxVal: Int) {
        super.setMaxGold(maxVal)

        //save value to the level 0 record
        sblChar.charRefs[0]!!.inventory.setMaxGold(maxVal)
    }

    /**
     * Setter for the character's maximum silver limit.
     *
     * @param maxVal value to set the maximum to
     */
    override fun setMaxSilver(maxVal: Int) {
        super.setMaxSilver(maxVal)

        //save value to the level 0 record
        sblChar.charRefs[0]!!.inventory.setMaxSilver(maxVal)
    }

    /**
     * Setter for the character's maximum copper limit.
     *
     * @param maxVal value to set the maximum to
     */
    override fun setMaxCopper(maxVal: Int) {
        super.setMaxCopper(maxVal)

        //save value to the level 0 record
        sblChar.charRefs[0]!!.inventory.setMaxCopper(maxVal)
    }

    /**
     * Setter for the character's bonus wealth from the advantage Starting Wealth.
     *
     * @param bonusWealth value to set the bonus to
     */
    override fun setWealthBonus(bonusWealth: Int) {
        super.setWealthBonus(bonusWealth)

        //save value to the level 0 record
        sblChar.charRefs[0]!!.inventory.setWealthBonus(bonusWealth)
    }

    /**
     * Function to run when the user purchases an amount of items for their character.
     *
     * @param equipment piece of equipment to acquire
     * @param quantity amount of the given item to purchase
     */
    override fun buyItem(equipment: GeneralEquipment, quantity: Int) {
        super.buyItem(equipment, quantity)

        //save value to the level 0 record
        sblChar.charRefs[0]!!.inventory.buyItem(equipment, quantity)
    }

    /**
     * Removes a single instance of the given piece of equipment.
     *
     * @param equipment piece of equipment to remove from the character
     */
    override fun removeItem(equipment: GeneralEquipment) {
        super.removeItem(equipment)

        //save value to the level 0 record
        sblChar.charRefs[0]!!.inventory.removeItem(equipment)
    }
}