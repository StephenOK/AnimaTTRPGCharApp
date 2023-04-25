package com.example.animabuilder.character_creation.attributes.combat

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
    var inputVal = 0

    //initialize modifier points for this stat
    var modPoints = 0

    //initialize points per level for this stat
    var pointPerLevel = 0

    //initialize additional points for this stat
    var classBonus = 0

    //initialize class total for this stat
    var classTotal = 0

    //initialize total for this stat
    var total = 0

    /**
     * Sets the user's input for this stat
     *
     * @param score value of user applied points
     */
    @JvmName("setInputVal1")
    fun setInputVal(score: Int){
        inputVal = score
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
        modPoints = input
        updateTotal()
    }

    /**
     * Sets the points per level for this stat.
     *
     * @param input points per level to set
     */
    @JvmName("setPointPerLevel1")
    fun setPointPerLevel(input: Int){
        pointPerLevel = input
        updateClassTotal()
    }

    /**
     * Sets additional bonuses that apply to class maximum cap.
     *
     * @param input amount to increment the bonus by
     */
    @JvmName("setClassBonus1")
    fun setClassBonus(input: Int){
        classBonus += input
        updateClassTotal()
    }

    /**
     * Update the class total for the individual stat.
     */
    fun updateClassTotal(){
        //determine actual total
        classTotal =
            if(charInstance.lvl != 0) (pointPerLevel * charInstance.lvl) + classBonus
            else (pointPerLevel/2) + classBonus

        //set class cap if it is exceeded
        if(classTotal > 50)
            classTotal = 50

        //update overall total
        updateTotal()
    }

    /**
     * Updates the total value for each contributing item.
     */
    fun updateTotal(){
        //determine total
        total = inputVal + modPoints + classTotal

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
        charInstance.addNewData(inputVal)
    }
}