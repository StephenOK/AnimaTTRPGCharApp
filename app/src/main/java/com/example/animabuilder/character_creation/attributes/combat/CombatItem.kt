package com.example.animabuilder.character_creation.attributes.combat

import androidx.compose.runtime.mutableStateOf
import com.example.animabuilder.character_creation.BaseCharacter
import java.io.BufferedReader

/**
 * Item that holds data in regards to a character's combat ability.
 * Utilized for the character's attack, block, dodge, and wear armor.
 *
 * @param charInstance object that holds all of a character's data
 */
class CombatItem(
    private val charInstance: BaseCharacter
){
    //initialize user input value for this stat
    val inputVal = mutableStateOf(0)

    //initialize modifier points for this stat
    val modPoints = mutableStateOf(0)

    //initialize points per level for this stat
    val pointPerLevel = mutableStateOf(0)

    //initialize additional points for this stat
    val classBonus = mutableStateOf(0)

    //initialize class total for this stat
    val classTotal = mutableStateOf(0)

    //initialize total for this stat
    val total = mutableStateOf(0)

    /**
     * Sets the user's input for this stat
     *
     * @param score value of user applied points
     */
    @JvmName("setInputVal1")
    fun setInputVal(score: Int){
        inputVal.value = score
        charInstance.updateTotalSpent()
        updateTotal()
    }

    /**
     * Sets the modifier points for this stat.
     *
     * @param input number of points to set for the mod
     */
    @JvmName("setModPoints1")
    fun setModPoints(input: Int){
        modPoints.value = input
        updateTotal()
    }

    /**
     * Sets the points per level for this stat.
     *
     * @param input points per level to set
     */
    @JvmName("setPointPerLevel1")
    fun setPointPerLevel(input: Int){
        pointPerLevel.value = input
        updateClassTotal()
    }

    /**
     * Sets additional bonuses that apply to class maximum cap.
     *
     * @param input amount to increment the bonus by
     */
    @JvmName("setClassBonus1")
    fun setClassBonus(input: Int){
        classBonus.value += input
        updateClassTotal()
    }

    /**
     * Update the class total for the individual stat.
     */
    fun updateClassTotal(){
        //determine actual total
        classTotal.value =
            if(charInstance.lvl.value != 0) (pointPerLevel.value * charInstance.lvl.value) + classBonus.value
            else (pointPerLevel.value/2) + classBonus.value

        //set class cap if it is exceeded
        if(classTotal.value > 50)
            classTotal.value = 50

        //update overall total
        updateTotal()
    }

    /**
     * Updates the total value for each contributing item.
     */
    fun updateTotal(){
        //determine total
        total.value = inputVal.value + modPoints.value + classTotal.value

        //update total martial arts the character can take
        charInstance.weaponProficiencies.updateMartialMax()
    }

    /**
     * Loads the information for this item.
     *
     * @param fileReader file to take the item's data from
     */
    fun loadItem(fileReader: BufferedReader){
        setInputVal(fileReader.readLine().toInt())
    }

    /**
     * Writes the user's inputted value to a file.
     */
    fun writeItem(){
        charInstance.addNewData(inputVal.value)
    }
}