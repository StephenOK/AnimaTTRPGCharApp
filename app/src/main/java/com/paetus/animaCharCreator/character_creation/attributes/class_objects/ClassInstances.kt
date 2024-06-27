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
        charInstance.classRecord.allClasses.forEach{charClass ->
            //apply class if match found
            if(charClass.saveName == classString){
                setOwnClass(charInstance.classRecord.allClasses.indexOf(charClass))
                return@forEach
            }
        }
    }

    /**
     * Retrieves the class associated with the current pointer state.
     */
    fun getClass(): CharClass{return charInstance.classRecord.allClasses[ownClass.intValue]}

    /**
     * Attempts to change the selection in the indicated record index.
     *
     * @param selectionIndex record location to set the selection to
     * @param secondarySelection value to set the selection to
     * @return value recorded after operation
     */
    fun setSelection(
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
    fun toggleMagPaladin(){
        //swap character's selection
        magPaladin.value = !magPaladin.value

        //boon items for paladins
        if(ownClass.intValue == 3){
            //grant magic options
            if(magPaladin.value){
                //remove composure bonus
                charInstance.secondaryList.composure.setClassPointsPerLevel(classBonus = 0)

                //grant zeon and banish points
                charInstance.magic.setZeonPerLevel(lvlBonus = 20)
                charInstance.summoning.banish.setPointsPerLevel(lvlBonus = 10)
            }

            //grant secondary boon
            else {
                //remove zeon and banish points
                charInstance.magic.setZeonPerLevel(lvlBonus = 0)
                charInstance.summoning.banish.setPointsPerLevel(lvlBonus = 0)

                //grant composure bonus
                charInstance.secondaryList.composure.setClassPointsPerLevel(classBonus = 10)
            }
        }

        //boon items for dark paladins
        else{
            //grant magic options
            if(magPaladin.value){
                //remove withstand pain bonus
                charInstance.secondaryList.resistPain.setClassPointsPerLevel(classBonus = 0)

                //grant zeon and control points
                charInstance.magic.setZeonPerLevel(lvlBonus = 20)
                charInstance.summoning.control.setPointsPerLevel(lvlBonus = 10)
            }

            //grant secondary boon
            else {
                //remove zeon and control points
                charInstance.magic.setZeonPerLevel(lvlBonus = 0)
                charInstance.summoning.control.setPointsPerLevel(lvlBonus = 0)

                //grant withstand pain bonus
                charInstance.secondaryList.resistPain.setClassPointsPerLevel(classBonus = 10)
            }
        }
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
        magPaladin.value = fileReader.readLine().toBoolean()

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