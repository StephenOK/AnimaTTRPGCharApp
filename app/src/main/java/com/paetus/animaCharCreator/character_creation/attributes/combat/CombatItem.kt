package com.paetus.animaCharCreator.character_creation.attributes.combat

import androidx.compose.runtime.mutableIntStateOf
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.writeDataTo
import java.io.BufferedReader
import java.io.ByteArrayOutputStream

/**
 * Item that holds data in regards to a character's combat ability.
 * Utilized for the character's attack, block, dodge, and wear armor.
 *
 * @param charInstance object that holds all of a character's data
 * @param itemLabel reference to the string to label this object
 */
open class CombatItem(
    private val charInstance: BaseCharacter,
    val itemLabel: Int
){
    //initialize user input value for this stat
    val inputVal = mutableIntStateOf(value = 0)

    //initialize modifier points for this stat
    val modPoints = mutableIntStateOf(value = 0)

    //initialize points per level for this stat
    val pointPerLevel = mutableIntStateOf(value = 0)

    //initialize additional points for this stat
    val classBonus = mutableIntStateOf(value = 0)

    //initialize class total for this stat
    val classTotal = mutableIntStateOf(value = 0)

    //initialize total for this stat
    val total = mutableIntStateOf(value = 0)

    /**
     * Sets the user's input for this stat.
     *
     * @param purchase value of user applied points
     */
    open fun setInputVal(purchase: Int){
        inputVal.intValue = purchase
        charInstance.updateTotalSpent()
        updateTotal()
    }

    /**
     * Sets the modifier points for this stat.
     *
     * @param modVal number of points to set for the mod
     */
    fun setModPoints(modVal: Int){
        modPoints.intValue = modVal
        updateTotal()
    }

    /**
     * Sets the points per level for this stat.
     *
     * @param lvlBonus points per level to set
     */
    fun setPointPerLevel(lvlBonus: Int){
        pointPerLevel.intValue = lvlBonus
        updateClassTotal()
    }

    /**
     * Sets additional bonuses that apply to class maximum cap.
     *
     * @param classBonus amount to increment the bonus by
     */
    fun setClassBonus(classBonus: Int){
        this.classBonus.intValue += classBonus
        updateClassTotal()
    }

    /**
     * Update the class total for the individual stat.
     */
    open fun updateClassTotal(){
        //determine actual total
        classTotal.intValue =
            if(charInstance.lvl.intValue != 0) (pointPerLevel.intValue * charInstance.lvl.intValue) + classBonus.intValue
            else (pointPerLevel.intValue/2) + classBonus.intValue

        //set class cap if it is exceeded
        if(classTotal.intValue > 50)
            classTotal.intValue = 50

        //update overall total
        updateTotal()
    }

    /**
     * Updates the total value for each contributing item.
     */
    fun updateTotal(){
        //determine total
        total.intValue = inputVal.intValue + modPoints.intValue + classTotal.intValue

        //update total martial arts the character can take
        charInstance.weaponProficiencies.updateMartialMax()
    }

    /**
     * Loads the information for this item.
     *
     * @param fileReader file to take the item's data from
     */
    fun loadItem(fileReader: BufferedReader){
        setInputVal(purchase = fileReader.readLine().toInt())
    }

    /**
     * Writes the user's inputted value to a file.
     *
     * @param byteArray output stream to write to
     */
    fun writeItem(byteArray: ByteArrayOutputStream) {
        writeDataTo(writer = byteArray, input = inputVal.intValue)
    }
}