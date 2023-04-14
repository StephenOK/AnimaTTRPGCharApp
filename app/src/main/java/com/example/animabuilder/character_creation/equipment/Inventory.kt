package com.example.animabuilder.character_creation.equipment

import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.equipment.general_goods.GeneralEquipment
import com.example.animabuilder.character_creation.equipment.general_goods.instances.*
import java.io.BufferedReader

class Inventory(val charInstance: BaseCharacter) {
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

    var maxGold = 0

    var goldSpent = 0.0
    var silverSpent = 0.0
    var copperSpent = 0.0

    val boughtGoods = mutableMapOf<GeneralEquipment, Int>()

    @JvmName("setMaxGold1")
    fun setMaxGold(input: Int){maxGold = input}

    fun buyItem(item: GeneralEquipment, quantity: Int){
        var foundItem = false

        boughtGoods.forEach{
            if(item.name == it.key.name && item.currentQuality == it.key.currentQuality){
                boughtGoods.replace(it.key, it.value + quantity)
                foundItem = true
                return@forEach
            }
        }

        if(!foundItem)
            boughtGoods += Pair(item, quantity)

        when(item.coinType){
            CoinType.Gold -> goldSpent += item.baseCost * quantity
            CoinType.Silver -> silverSpent += item.baseCost * quantity
            CoinType.Copper -> copperSpent += item.baseCost * quantity
        }

        goldFix()
    }

    fun removeItem(item: GeneralEquipment){
        var checkedItem: GeneralEquipment? = null

        boughtGoods.forEach{
            if(item.name == it.key.name && item.currentQuality == it.key.currentQuality){
                val newVal = it.value - 1

                boughtGoods.replace(it.key, newVal)
                checkedItem = it.key

                return@forEach
            }
        }

        if(checkedItem != null && boughtGoods[checkedItem] == 0)
            boughtGoods.remove(checkedItem)

        when(item.coinType){
            CoinType.Gold -> goldSpent -= item.baseCost
            CoinType.Silver -> silverSpent -= item.baseCost
            CoinType.Copper -> copperSpent -= item.baseCost
        }

        goldFix()
    }

    fun copperFix(){
        while(copperSpent < 0){
            copperSpent += 10
            silverSpent -= 1
        }
        while(copperSpent >= 10){
            copperSpent -= 10
            silverSpent += 1
        }
    }

    fun silverFix(){
        while(silverSpent < 0){
            silverSpent += 100
            goldSpent -= 1
        }
        while(silverSpent >= 100){
            silverSpent -= 100
            goldSpent += 1
        }

        if(silverSpent % 1.0 != 0.0){
            copperSpent += (silverSpent % 1.0) * 10
            silverSpent -= silverSpent % 1.0
        }

        copperFix()
    }

    fun goldFix(){
        if(goldSpent % 1.0 != 0.0){
            silverSpent += (goldSpent % 1.0) * 100
            goldSpent -= goldSpent % 1.0
        }

        silverFix()
    }

    fun findItem(name: String, cost: Double, quality: Int?): GeneralEquipment?{
        allCategories.forEach{
            val item = it.findEquipment(name, cost, quality)

            if(item != null) return item
        }

        return null
    }

    fun loadInventory(fileReader: BufferedReader){
        setMaxGold(fileReader.readLine().toInt())

        for(loop in 0 until fileReader.readLine().toInt()){
            buyItem(
                findItem(
                    fileReader.readLine(),
                    fileReader.readLine().toDouble(),
                    fileReader.readLine().toIntOrNull()
                )!!,
                fileReader.readLine().toInt()
            )
        }
    }

    fun writeInventory(){
        charInstance.addNewData(maxGold)

        charInstance.addNewData(boughtGoods.size)

        boughtGoods.forEach{
            charInstance.addNewData(it.key.name)
            charInstance.addNewData(it.key.baseCost)
            charInstance.addNewData(it.key.currentQuality)
            charInstance.addNewData(it.value)
        }
    }
}