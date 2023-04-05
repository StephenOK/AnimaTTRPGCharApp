package com.example.animabuilder.character_creation.equipment

import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.equipment.general_goods.GeneralEquipment
import com.example.animabuilder.character_creation.equipment.general_goods.instances.*
import java.io.BufferedReader
import java.io.Serializable

class Inventory(val charInstance: BaseCharacter) : Serializable {
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
    var maxSilver = 0
    var maxCopper = 0

    var goldSpent = 0
    var silverSpent = 0
    var copperSpent = 0

    val boughtGoods = mutableListOf<GeneralEquipment>()

    @JvmName("setMaxGold1")
    fun setMaxGold(input: Int){maxGold = input}

    @JvmName("setMaxSilver1")
    fun setMaxSilver(input: Int){maxSilver = input}

    @JvmName("setMaxCopper1")
    fun setMaxCopper(input: Int){maxCopper = input}

    fun buyItem(item: GeneralEquipment){
        boughtGoods += item

        when(item.coinType){
            CoinType.Gold -> goldSpent += item.baseCost
            CoinType.Silver -> silverSpent += item.baseCost
            CoinType.Copper -> copperSpent += item.baseCost
        }

        coinExchange()
    }

    fun removeItem(item: GeneralEquipment){
        boughtGoods -= item

        when(item.coinType){
            CoinType.Gold -> goldSpent -= item.baseCost
            CoinType.Silver -> silverSpent -= item.baseCost
            CoinType.Copper -> copperSpent -= item.baseCost
        }

        coinFix()
    }

    fun coinExchange(){
        while(copperSpent >= 10){
            copperSpent -= 10
            silverSpent += 1
        }

        while(silverSpent >= 100){
            silverSpent -= 100
            goldSpent += 1
        }
    }

    fun coinFix(){
        while(copperSpent < 0){
            copperSpent += 10
            silverSpent -= 1
        }

        while(silverSpent < 0){
            silverSpent += 100
            goldSpent -= 1
        }
    }

    fun findItem(name: String, cost: Int): GeneralEquipment?{
        allCategories.forEach{
            val item = it.findEquipment(name, cost)

            if(item != null) return item
        }

        return null
    }

    fun loadInventory(fileReader: BufferedReader){
        setMaxGold(fileReader.readLine().toInt())
        setMaxSilver(fileReader.readLine().toInt())
        setMaxCopper(fileReader.readLine().toInt())

        for(loop in 0 until fileReader.readLine().toInt()){
            buyItem(findItem(fileReader.readLine(), fileReader.readLine().toInt())!!)
        }
    }

    fun writeInventory(){
        charInstance.addNewData(maxGold)
        charInstance.addNewData(maxSilver)
        charInstance.addNewData(maxCopper)

        charInstance.addNewData(boughtGoods.size)

        boughtGoods.forEach{
            charInstance.addNewData(it.name)
            charInstance.addNewData(it.baseCost)
        }
    }
}