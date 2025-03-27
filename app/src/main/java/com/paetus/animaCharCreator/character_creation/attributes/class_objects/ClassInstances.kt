package com.paetus.animaCharCreator.character_creation.attributes.class_objects

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.writeDataTo
import java.io.BufferedReader
import java.io.ByteArrayOutputStream

/**
 * Record of classes available to the character.
 *
 * @param charInstance object that holds the character's data
 */
open class ClassInstances(
    open val charInstance: BaseCharacter
){
    fun getClassRecord(): ClassRecord{return charInstance.objectDB.classRecord}

    //initialize freelancer bonus selections
    val freelancerSelection = mutableListOf(0, 0, 0, 0, 0)

    //initialize pointer to class item
    val ownClass = mutableIntStateOf(value = 0)

    //initialize paladin's magic selection
    val magPaladin = mutableStateOf(value = true)

    /**
     * Setter for class with class input.
     *
     * @param classIndex new class to set for this character
     */
    open fun setOwnClass(classIndex: Int){
        //undo current class buffs
        getClass().onRemove(charInstance)

        //change class and apply new buffs
        ownClass.intValue = classIndex
        getClass().onTake(charInstance)

        //update class life points
        charInstance.combat.updateClassLife()

        //update initiative bonus
        charInstance.combat.updateInitiative()

        //update character's maximum point values
        charInstance.percCombatDP.doubleValue = getClass().combatMax
        charInstance.percMagDP.doubleValue = getClass().magMax
        charInstance.percPsyDP.doubleValue = getClass().psyMax
        charInstance.dpAllotmentCalc()

        //update secondary bonuses
        charInstance.secondaryList.classUpdate(newClass = getClass())

        //update character's martial knowledge
        charInstance.ki.updateMK()

        //update innate psychic points
        charInstance.psychic.setInnatePsy()

        //update all spent value totals
        charInstance.updateTotalSpent()
    }

    /**
     * Setter for class with a string input.
     *
     * @param classString string to be converted into a class the character will take
     */
    fun setOwnClass(classString: String){
        //search through all class objects
        getClassRecord().allClasses.forEach{charClass ->
            //apply class if match found
            if(charClass.saveName == classString){
                setOwnClass(getClassRecord().allClasses.indexOf(charClass))
                return@forEach
            }
        }
    }

    /**
     * Retrieves the class associated with the current pointer state.
     */
    fun getClass(): CharClass{return getClassRecord().allClasses[ownClass.intValue]}

    /**
     * Attempts to change the selection in the indicated record index.
     *
     * @param selectionIndex record location to set the selection to
     * @param secondarySelection value to set the selection to
     * @return value recorded after operation
     */
    open fun setSelection(
        selectionIndex: Int,
        secondarySelection: Int
    ): Int{
        //record current selected value
        val prevIndex = freelancerSelection[selectionIndex] - 1

        //if user is clearing selection
        if(secondarySelection == 0) {
            //remove previous bonus if one taken
            if(prevIndex >= 0)
                charInstance.secondaryList.getAllSecondaries()[prevIndex].setClassPointsPerLevel(0)

            //set new input
            freelancerSelection[selectionIndex] = 0
        }

        //user is making a selection
        else{
            //determine that this input is not taken in another record index
            freelancerSelection.forEach{secondary ->
                //return current value if match found
                if(secondary == secondarySelection)
                    return freelancerSelection[selectionIndex]
            }

            //remove previous bonus if one taken
            if(prevIndex >= 0)
                charInstance.secondaryList.getAllSecondaries()[prevIndex].setClassPointsPerLevel(0)

            //set new input
            freelancerSelection[selectionIndex] = secondarySelection

            //add new bonus
            charInstance.secondaryList.getAllSecondaries()[secondarySelection - 1].setClassPointsPerLevel(classBonus = 10)
        }

        //return changed value
        return freelancerSelection[selectionIndex]
    }

    /**
     * Toggles the user's selection for their paladin boon.
     */
    open fun toggleMagPaladin(){
        //remove paladin bonus
        paladinDeactivate()

        //swap paladin magic selection
        magPaladin.value = !magPaladin.value

        //add paladin bonus
        paladinActivate()
    }

    /**
     * Function to run when the character takes a paladin level.
     */
    fun paladinActivate(){
        //if character is taking magic abilities
        if(magPaladin.value){
            //add 20 zeon points no matter the specific class
            charInstance.magic.setZeonPerLevel(lvlBonus = 20)

            //add banish for paladins
            if(ownClass.intValue == 3)
                charInstance.summoning.banish.setPointsPerLevel(lvlBonus = 10)
            //add control for dark paladins
            else if (ownClass.intValue == 4)
                charInstance.summoning.control.setPointsPerLevel(lvlBonus = 10)
        }
        //add composure for paladins
        else if(ownClass.intValue == 3)
            charInstance.secondaryList.composure.setClassPointsPerLevel(classBonus = 10)
        //add withstand pain for dark paladins
        else if (ownClass.intValue == 4)
            charInstance.secondaryList.resistPain.setClassPointsPerLevel(classBonus = 10)
    }

    /**
     * Function to run when the character removes a paladin level.
     */
    fun paladinDeactivate(){
        //if the character had magic abilities
        if(magPaladin.value){
            //remove zeon points from class
            charInstance.magic.setZeonPerLevel(lvlBonus = 0)

            //remove banish points from paladins
            if(ownClass.intValue == 3)
                charInstance.summoning.banish.setPointsPerLevel(lvlBonus = 0)
            //remove control points from dark paladins
            else if (ownClass.intValue == 4)
                charInstance.summoning.control.setPointsPerLevel(lvlBonus = 0)
        }
        //remove composure points from paladins
        else if(ownClass.intValue == 3)
            charInstance.secondaryList.composure.setClassPointsPerLevel(classBonus = 0)
        //remove withstand points from dark paladins
        else if (ownClass.intValue == 4)
            charInstance.secondaryList.resistPain.setClassPointsPerLevel(classBonus = 0)
    }

    /**
     * Loads character class data from file.
     *
     * @param fileReader object that reads the file
     * @param writeVersion app version the read file was written in
     */
    fun loadClassData(
        fileReader: BufferedReader,
        writeVersion: Int
    ){
        //get paladin's selection
        if(!fileReader.readLine().toBoolean())
            toggleMagPaladin()

        //get the correct number of loops for the app version
        val loopNum =
            if(writeVersion <= 27) 3
            else 5

        //get each freelancer selected characteristic
        for(index in 0 until loopNum)
            setSelection(selectionIndex = index, secondarySelection = fileReader.readLine().toInt())
    }

    /**
     * Writes the data in this section to file.
     */
    fun writeClassData(
        byteArray: ByteArrayOutputStream
    ){
        writeDataTo(writer = byteArray, input = magPaladin.value)

        freelancerSelection.forEach{
            writeDataTo(writer = byteArray, input = it)
        }
    }
}