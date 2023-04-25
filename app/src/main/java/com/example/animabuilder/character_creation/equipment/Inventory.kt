package com.example.animabuilder.character_creation.equipment

import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.equipment.general_goods.GeneralEquipment
import com.example.animabuilder.character_creation.equipment.general_goods.instances.*
import java.io.BufferedReader

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
    var maxGold = 0
    var maxSilver = 0
    var maxCopper = 0

    //initialize starting wealth bonus
    var wealthBonus = 0

    //initialize spent coin values
    var goldSpent = 0.0
    var silverSpent = 0.0
    var copperSpent = 0.0

    //initialize bought items
    val boughtGoods = mutableMapOf<GeneralEquipment, Int>()

    /**
     * Setter for the character's maximum gold limit.
     *
     * @param input value to set the maximum to
     */
    @JvmName("setMaxGold1")
    fun setMaxGold(input: Int){maxGold = input}

    /**
     * Setter for the character's maximum silver limit.
     *
     * @param input value to set the maximum to
     */
    @JvmName("setMaxSilver1")
    fun setMaxSilver(input: Int){maxSilver = input}

    /**
     * Setter for the character's maximum copper limit.
     *
     * @param input value to set the maximum to
     */
    @JvmName("setMaxCopper1")
    fun setMaxCopper(input: Int){maxCopper = input}

    /**
     * Setter for the character's bonus wealth from the advantage Starting Wealth.
     *
     * @param input value to set the bonus to
     */
    @JvmName("setWealthBonus1")
    fun setWealthBonus(input: Int){wealthBonus = input}

    /**
     * Function to run when the user purchases an amount of items for their character.
     *
     * @param item piece of equipment to acquire
     * @param quantity amount of the given item to purchase
     */
    fun buyItem(item: GeneralEquipment, quantity: Int){
        //initialize found item flag
        var foundItem = false

        //determine if the character already has a copy of this item
        boughtGoods.forEach{
            //match found based on name and quality
            if(item.name == it.key.name && item.currentQuality == it.key.currentQuality){
                //replace input with the new value
                boughtGoods.replace(it.key, it.value + quantity)

                //set flag to true
                foundItem = true

                //end search
                return@forEach
            }
        }

        //add new item if no previous instance found
        if(!foundItem)
            boughtGoods += Pair(item, quantity)

        //recalculate character's spent money
        countSpent()
    }

    /**
     * Removes a single instance of the given piece of equipment.
     *
     * @param item piece of equipment to remove from the character
     */
    fun removeItem(item: GeneralEquipment){
        //initialize equipment removed
        var checkedItem: GeneralEquipment? = null

        //find the equipment item in the character's inventory
        boughtGoods.forEach{
            //find the item based on its name and quality
            if(item.name == it.key.name && item.currentQuality == it.key.currentQuality){
                //remove one of the indicated items
                val newVal = it.value - 1
                boughtGoods.replace(it.key, newVal)

                //set removed item key
                checkedItem = it.key

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
    fun countSpent(){
        //reset spent values
        copperSpent = 0.0
        silverSpent = 0.0
        goldSpent = 0.0

        //add all item costs to the appropriate amounts spent
        boughtGoods.forEach{
            when(it.key.coinType){
                CoinType.Copper -> copperSpent += it.key.baseCost * it.value
                CoinType.Silver -> silverSpent += it.key.baseCost * it.value
                CoinType.Gold -> goldSpent += it.key.baseCost * it.value
            }
        }

        //correct values
        goldFix()
    }

    /**
     * Fixes the copper counter to display a valid value
     */
    fun copperFix(){
        //correct any negative copper value by converting silver to copper
        while(copperSpent < 0){
            copperSpent += 10
            silverSpent -= 1
            silverFix()
        }

        //convert sufficient copper amounts to silver
        while(copperSpent >= 10){
            copperSpent -= 10
            silverSpent += 1
        }

        //fix silver if changes here caused needed changes
        if(silverSpent > 100)
            silverFix()
    }

    /**
     * Fixes the silver counter to display a valid value.
     */
    fun silverFix(){
        //correct any negative silver value by converting gold to silver
        while(silverSpent < 0){
            silverSpent += 100
            goldSpent -= 1
        }

        //convert sufficient silver amounts to gold
        while(silverSpent >= 100){
            silverSpent -= 100
            goldSpent += 1
        }

        //convert decimal silver to copper
        if(silverSpent % 1.0 != 0.0){
            copperSpent += (silverSpent % 1.0) * 10
            silverSpent -= silverSpent % 1.0
        }

        //fix the copper amount
        copperFix()
    }

    /**
     * Fixes the gold counter to display a valid value.
     */
    fun goldFix(){
        //convert decimal gold to silver
        if(goldSpent % 1.0 != 0.0){
            silverSpent += (goldSpent % 1.0) * 100
            goldSpent -= goldSpent % 1.0
        }

        //fix the silver amount
        silverFix()
    }

    /**
     * Searches through each available category to find the desired equipment.
     *
     * @param name name of the desired equipment
     * @param quality quality of the equipment, if available
     * @return found piece of equipment or null flag
     */
    fun findItem(name: String, quality: Int?): GeneralEquipment?{
        //search through each category
        allCategories.forEach{
            //search category for equipment
            val item = it.findEquipment(name, quality)

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
        //retrieve maximum gold values
        setMaxGold(fileReader.readLine().toInt())
        setMaxSilver(fileReader.readLine().toInt())
        setMaxCopper(fileReader.readLine().toInt())

        //restore previous equipment
        for(loop in 0 until fileReader.readLine().toInt()){
            buyItem(
                findItem(
                    fileReader.readLine(),
                    fileReader.readLine().toIntOrNull()
                )!!,
                fileReader.readLine().toInt()
            )
        }
    }

    /**
     * Writes data from this character to file.
     */
    fun writeInventory(){
        //write data on maximum coin values
        charInstance.addNewData(maxGold)
        charInstance.addNewData(maxSilver)
        charInstance.addNewData(maxCopper)

        //add size of equipment data
        charInstance.addNewData(boughtGoods.size)

        //write each item's name, quality, and quantity
        boughtGoods.forEach{
            charInstance.addNewData(it.key.name)
            charInstance.addNewData(it.key.currentQuality)
            charInstance.addNewData(it.value)
        }
    }
}