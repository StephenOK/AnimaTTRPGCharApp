package com.paetus.animaCharCreator.character_creation.equipment

import android.os.Build
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.instances.*
import com.paetus.animaCharCreator.enumerations.CoinType
import com.paetus.animaCharCreator.writeDataTo
import java.io.BufferedReader
import java.io.ByteArrayOutputStream

/**
 * Class that manages the character's purchased items and maximum coin value.
 *
 * @param charInstance object that holds all of the character's data
 */
class Inventory(val charInstance: BaseCharacter) {
    //instantiate all equipment category objects
    val clothing = Clothing()
    val travel = Travel()
    val transport = Transport()
    val foodAndDrink = FoodAndDrink()
    val lodging = Lodging()
    val dwellings = Dwellings()
    val services = Services()
    val art = Art()
    val gems = Gems()
    val painting = Painting()
    val poisons = Poisons()
    val miscellaneous = Miscellaneous()
    val weapons = Weapons()
    val armors = Armors()

    //gather all categories
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

    //initialize maximum coin expenditures
    val maxGold = mutableIntStateOf(value = 0)
    val maxSilver = mutableIntStateOf(value = 0)
    val maxCopper = mutableIntStateOf(value = 0)

    //initialize starting wealth bonus
    val wealthBonus = mutableIntStateOf(value = 0)

    //initialize bought items
    val boughtGoods = mutableMapOf<GeneralEquipment, Int>()

    //initialize spent coin values
    val goldSpent = mutableDoubleStateOf(value = 0.0)
    val silverSpent = mutableDoubleStateOf(value = 0.0)
    val copperSpent = mutableDoubleStateOf(value = 0.0)

    /**
     * Setter for the character's maximum gold limit.
     *
     * @param maxVal value to set the maximum to
     */
    fun setMaxGold(maxVal: Int){maxGold.intValue = maxVal}

    /**
     * Setter for the character's maximum silver limit.
     *
     * @param maxVal value to set the maximum to
     */
    fun setMaxSilver(maxVal: Int){maxSilver.intValue = maxVal}

    /**
     * Setter for the character's maximum copper limit.
     *
     * @param maxVal value to set the maximum to
     */
    fun setMaxCopper(maxVal: Int){maxCopper.intValue = maxVal}

    /**
     * Setter for the character's bonus wealth from the advantage Starting Wealth.
     *
     * @param bonusWealth value to set the bonus to
     */
    fun setWealthBonus(bonusWealth: Int){wealthBonus.intValue = bonusWealth}

    /**
     * Function to run when the user purchases an amount of items for their character.
     *
     * @param equipment piece of equipment to acquire
     * @param quantity amount of the given item to purchase
     */
    fun buyItem(
        equipment: GeneralEquipment,
        quantity: Int
    ){
        //initialize found item flag
        var foundItem = false

        //determine if the character already has a copy of this item
        boughtGoods.forEach{(heldItem, heldAmount) ->
            //match found based on name and quality
            if(equipment.saveName == heldItem.saveName && equipment.currentQuality == heldItem.currentQuality){
                //replace input with the new value
                val newVal = heldAmount + quantity
                boughtGoods[heldItem] = newVal


                //set flag to true
                foundItem = true

                //end search
                return@forEach
            }
        }

        //add new item if no previous instance found
        if(!foundItem)
            boughtGoods += Pair(equipment, quantity)

        //recalculate character's spent money
        countSpent()
    }

    /**
     * Removes a single instance of the given piece of equipment.
     *
     * @param equipment piece of equipment to remove from the character
     */
    fun removeItem(equipment: GeneralEquipment){
        //initialize equipment removed
        var checkedItem: GeneralEquipment? = null

        //find the equipment item in the character's inventory
        boughtGoods.forEach{(heldItem, heldAmount) ->
            //find the item based on its name and quality
            if(equipment.saveName == heldItem.saveName && equipment.currentQuality == heldItem.currentQuality){
                //remove one of the indicated items
                val newVal = heldAmount - 1

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    boughtGoods.replace(heldItem, newVal)
                }
                else{
                    boughtGoods[heldItem] = newVal
                }

                //set removed item key
                checkedItem = heldItem

                //terminate loop
                return@forEach
            }
        }

        //remove item listing if no more copies of the item are in inventory
        if(checkedItem != null && boughtGoods[checkedItem] == 0)
            boughtGoods.remove(checkedItem)

        //recalculate spent money
        countSpent()
    }

    /**
     * Determines the amount of money spent by the character on equipment.
     */
    private fun countSpent(){
        //reset spent values
        copperSpent.doubleValue = 0.0
        silverSpent.doubleValue = 0.0
        goldSpent.doubleValue = 0.0

        //add all item costs to the appropriate amounts spent
        boughtGoods.forEach{(equipment, quantity) ->
            when(equipment.coinType){
                CoinType.Copper -> copperSpent.doubleValue += equipment.baseCost * quantity
                CoinType.Silver -> silverSpent.doubleValue += equipment.baseCost * quantity
                CoinType.Gold -> goldSpent.doubleValue += equipment.baseCost * quantity
            }
        }

        //correct values
        goldFix()
    }

    /**
     * Fixes the gold counter to display a valid value.
     */
    private fun goldFix(){
        //convert decimal gold to silver
        if(goldSpent.doubleValue % 1.0 != 0.0){
            silverSpent.doubleValue += (goldSpent.doubleValue % 1.0) * 100
            goldSpent.doubleValue -= goldSpent.doubleValue % 1.0
        }

        //fix the silver amount
        silverFix()
    }

    /**
     * Fixes the silver counter to display a valid value.
     */
    private fun silverFix(){
        //correct any negative silver value by converting gold to silver
        while(silverSpent.doubleValue < 0){
            silverSpent.doubleValue += 100
            goldSpent.doubleValue -= 1
        }

        //convert sufficient silver amounts to gold
        while(silverSpent.doubleValue >= 100){
            silverSpent.doubleValue -= 100
            goldSpent.doubleValue += 1
        }

        //convert decimal silver to copper
        if(silverSpent.doubleValue % 1.0 != 0.0){
            copperSpent.doubleValue += (silverSpent.doubleValue % 1.0) * 10
            silverSpent.doubleValue -= silverSpent.doubleValue % 1.0
        }

        //fix the copper amount
        copperFix()
    }

    /**
     * Fixes the copper counter to display a valid value
     */
    private fun copperFix(){
        //correct any negative copper value by converting silver to copper
        while(copperSpent.doubleValue < 0){
            copperSpent.doubleValue += 10
            silverSpent.doubleValue -= 1
            silverFix()
        }

        //convert sufficient copper amounts to silver
        while(copperSpent.doubleValue >= 10){
            copperSpent.doubleValue -= 10
            silverSpent.doubleValue += 1
        }

        //fix silver if changes here caused needed changes
        if(silverSpent.doubleValue > 100)
            silverFix()
    }

    /**
     * Searches through each available category to find the desired equipment.
     *
     * @param name name of the desired equipment
     * @param quality quality of the equipment, if available
     * @return found piece of equipment or null flag
     */
    private fun findItem(
        name: String,
        quality: Int?
    ): GeneralEquipment?{
        //search through each category
        allCategories.forEach{category ->
            //search category for equipment
            val item = category.findEquipment(equipName = name, quality = quality)

            //return found item
            if(item != null) return item
        }

        //return null if item not found
        return null
    }

    /**
     * Loads data for the character's inventory.
     *
     * @param fileReader file data to input to this object
     */
    fun loadInventory(fileReader: BufferedReader){
        //retrieve maximum coin values
        setMaxGold(maxVal = fileReader.readLine().toInt())
        setMaxSilver(maxVal = fileReader.readLine().toInt())
        setMaxCopper(maxVal = fileReader.readLine().toInt())

        //restore previous equipment
        for(loop in 0 until fileReader.readLine().toInt()){
            buyItem(
                equipment = findItem(
                    name = fileReader.readLine(),
                    quality = fileReader.readLine().toIntOrNull()
                )!!,
                quantity = fileReader.readLine().toInt()
            )
        }
    }

    /**
     * Writes data from this character to file.
     *
     * @param byteArray output stream for this section's data
     */
    fun writeInventory(byteArray: ByteArrayOutputStream) {
        //write data on maximum coin values
        writeDataTo(writer = byteArray, input = maxGold.intValue)
        writeDataTo(writer = byteArray, input = maxSilver.intValue)
        writeDataTo(writer = byteArray, input = maxCopper.intValue)

        //add size of equipment data
        writeDataTo(writer = byteArray, input = boughtGoods.size)

        //write each item's name, quality, and quantity
        boughtGoods.forEach{(equipment, quantity) ->
            writeDataTo(writer = byteArray, input = equipment.saveName)
            writeDataTo(writer = byteArray, input = equipment.currentQuality)
            writeDataTo(writer = byteArray, input = quantity)
        }
    }
}