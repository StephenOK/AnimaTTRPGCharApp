package com.example.animabuilder.character_creation.attributes.summoning

import com.example.animabuilder.character_creation.BaseCharacter
import java.io.BufferedReader
import java.io.Serializable

/**
 * Section containing stats involving a character's summoning ability
 * Holds values for summon, control, bind, and banish abilities
 */
class Summoning(private val charInstance: BaseCharacter): Serializable {
    //instantiate summoning action bought amounts and totals
    var summonBought = 0
    var summonPerLevel = 0
    var summonTotal = 0

    var controlBought = 0
    var controlPerLevel = 0
    var controlTotal = 0

    var bindBought = 0
    var bindPerLevel = 0
    var bindTotal = 0

    var banishBought = 0
    var banishPerLevel = 0
    var banishTotal = 0


    /**
     * Buys the inputted amount of summon points for the character
     *
     * amount: number of summon points to apply
     */
    fun buySummon(amount: Int) {
        summonBought = amount
        updateSummon()
    }

    @JvmName("setSummonPerLevel1")
    fun setSummonPerLevel(amount: Int){
        summonPerLevel = amount
        updateSummon()
    }

    /**
     * Updates the character's total summon value
     */
    fun updateSummon(){
        summonTotal = summonBought + charInstance.modPOW + (summonPerLevel * charInstance.lvl)
    }

    /**
     * Buys the inputted amount of control points for the character
     *
     * amount: number of control points to apply
     */
    fun buyControl(amount: Int){
        controlBought = amount
        updateControl()
    }

    @JvmName("setControlPerLevel1")
    fun setControlPerLevel(amount: Int){
        controlPerLevel = amount
        updateControl()
    }

    /**
     * Updates the character's total control value
     */
    fun updateControl(){
        controlTotal = controlBought + charInstance.modWP + (controlPerLevel * charInstance.lvl)
    }

    /**
     * Buys the inputted amount of bind points for the character
     *
     * amount: number of bind points to apply
     */
    fun buyBind(amount: Int){
        bindBought = amount
        updateBind()
    }

    @JvmName("setBindPerLevel1")
    fun setBindPerLevel(amount: Int){
        bindPerLevel = amount
        updateBind()
    }

    /**
     * Updates the character's total bind value
     */
    fun updateBind(){
        bindTotal = bindBought + charInstance.modPOW + (bindPerLevel * charInstance.lvl)
    }

    /**
     * Buys the inputted amount of banish points for the character
     *
     * amount: number of banish points to apply
     */
    fun buyBanish(amount: Int){
        banishBought = amount
        updateBanish()
    }

    @JvmName("setBanishPerLevel1")
    fun setBanishPerLevel(amount: Int){
        banishPerLevel = amount
        updateBanish()
    }

    /**
     * Updates the character's total banish value
     */
    fun updateBanish(){
        banishTotal = banishBought + charInstance.modPOW + (banishPerLevel * charInstance.lvl)
    }

    /**
     * Determines the development points spent in this section
     */
    fun calculateSpent(): Int{
        return (summonBought * charInstance.ownClass.summonGrowth) +
                (controlBought * charInstance.ownClass.controlGrowth) +
                (bindBought * charInstance.ownClass.bindGrowth) +
                (banishBought * charInstance.ownClass.banishGrowth)
    }

    /**
     * Loads data from file to this page's section
     */
    fun loadSummoning(fileReader: BufferedReader){
        buySummon(fileReader.readLine().toInt())
        buyControl(fileReader.readLine().toInt())
        buyBind(fileReader.readLine().toInt())
        buyBanish(fileReader.readLine().toInt())
    }

    /**
     * Writes pertinent data from this section to file
     */
    fun writeSummoning(){
        charInstance.addNewData(summonBought)
        charInstance.addNewData(controlBought)
        charInstance.addNewData(bindBought)
        charInstance.addNewData(banishBought)
    }
}