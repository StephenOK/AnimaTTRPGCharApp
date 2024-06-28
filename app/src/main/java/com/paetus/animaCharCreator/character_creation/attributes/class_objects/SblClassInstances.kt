package com.paetus.animaCharCreator.character_creation.attributes.class_objects

import com.paetus.animaCharCreator.character_creation.SblChar

/**
 * Class object for a save by level character.
 */
class SblClassInstances(
    override val charInstance: SblChar,
): ClassInstances(charInstance = charInstance) {
    /**
     * Setter for class with class input.
     *
     * @param classIndex new class to set for this character
     */
    override fun setOwnClass(classIndex: Int) {
        //get starting level for class change
        var levelCounter =
            if(charInstance.lvl.intValue == 0) 0
            else charInstance.lvl.intValue + 1

        //get changed class
        val initClass =
            if(charInstance.lvl.intValue == 0)
                charInstance.charRefs[0]!!.classes.ownClass.intValue
            else
                charInstance.charRefs[charInstance.lvl.intValue + 1]!!.classes.ownClass.intValue

        //change all recorded level classes up to any change
        while(charInstance.charRefs[levelCounter] != null &&
              charInstance.charRefs[levelCounter]!!.classes.ownClass.intValue == initClass)
            charInstance.charRefs[levelCounter++]!!.classes.setOwnClass(classIndex)

        //update combat items
        charInstance.combat.updateClassLife()
        charInstance.combat.updateInitiative()
        charInstance.combat.allAbilities().forEach{it.updateClassTotal()}

        //update total martial knowledge
        charInstance.ki.updateMK()

        //update secondary items' class totals
        charInstance.secondaryList.getAllSecondaries().forEach{it.classTotalRefresh()}

        //update magic item totals
        charInstance.magic.updateZeonFromClass()
        charInstance.summoning.allSummoning().forEach{it.updateLevelTotal()}

        charInstance.dpAllotmentCalc()
        charInstance.updateTotalSpent()
    }

    override fun setSelection(
        selectionIndex: Int,
        secondarySelection: Int
    ): Int {
        //record current selected value
        val prevIndex = freelancerSelection[selectionIndex] - 1

        //if user is clearing selection
        if(secondarySelection == 0){
            //remove previous bonus if one taken
            if(prevIndex >= 0)
                charInstance.levelLoop(endLevel = 20){
                    if(it.classes.ownClass.intValue == 0) {
                        it.secondaryList.getAllSecondaries()[prevIndex].setClassPointsPerLevel(0)
                        it.classes.freelancerSelection[selectionIndex] = 0
                    }
                }

            //set new input
            freelancerSelection[selectionIndex] = 0
        }

        //user is making a selection
        else{
            //determine that this input is not taken in another record index
            freelancerSelection.forEach{secondary->
                //return current value if match found
                if(secondary == secondarySelection)
                    return freelancerSelection[selectionIndex]
            }

            //remove previous bonus if one taken
            if(prevIndex >= 0)
                charInstance.levelLoop(endLevel = 20){
                    it.secondaryList.getAllSecondaries()[prevIndex].setClassPointsPerLevel(0)
                }

            //set new input
            freelancerSelection[selectionIndex] = secondarySelection

            //add new bonus
            charInstance.levelLoop(endLevel = 20){
                it.classes.freelancerSelection[selectionIndex] = secondarySelection
                it.secondaryList.getAllSecondaries()[secondarySelection - 1].setClassPointsPerLevel(classBonus = 10)
            }
        }

        //update class totals
        charInstance.secondaryList.getAllSecondaries().forEach{it.classTotalRefresh()}

        //return changed value
        return freelancerSelection[selectionIndex]
    }

    /**
     * Gets the DP spent in this section.
     */
    fun calculateSpent(): Int{
        //initialize point output
        var output = 0

        //get character's initial class
        var prevClassIndex = charInstance.charRefs[0]!!.classes.ownClass.intValue

        //look through the level record
        charInstance.levelLoop(endLevel = charInstance.lvl.intValue + 1){ character ->
            //run action if a change in class found
            if(prevClassIndex != character.classes.ownClass.intValue){
                //changing to or from Freelancer will only ever spend 20 DP
                if(prevClassIndex == 0 || character.classes.ownClass.intValue == 0)
                    output += 20

                //if freelancer not involved
                else {
                    val prevClass = charInstance.classRecord.allClasses[prevClassIndex]
                    val newClass = charInstance.classRecord.allClasses[character.classes.ownClass.intValue]

                    //initialize all archetypes match flag to true
                    var allMatch = true

                    //initialize any match found flag to false
                    var partMatch = false

                    //get pair of archetypes in the proper ordering
                    val checkPair =
                        if(newClass.archetype.size > prevClass.archetype.size)
                            Pair(newClass.archetype, prevClass.archetype)
                        else
                            Pair(prevClass.archetype, newClass.archetype)

                    //check each of the longer list's items
                    checkPair.first.forEach {archetype ->
                        //notify of partial match if there is one
                        if (checkPair.second.contains(archetype))
                            partMatch = true
                        //notify of not all matching if no match present
                        else
                            allMatch = false
                    }

                    //add the appropriate amount of DP for
                    output +=
                        //all archetypes matching
                        if (allMatch) 20
                        //some matching archetypes
                        else if (partMatch) 40
                        //or no matching archetypes
                        else 60
                }

                //update the checked class with this level
                prevClassIndex = character.classes.ownClass.intValue
            }
        }

        //return points spent
        return output
    }
}