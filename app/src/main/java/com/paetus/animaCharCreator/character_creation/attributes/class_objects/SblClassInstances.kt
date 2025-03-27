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

    /**
     * Toggles the user's selection for their paladin boon.
     */
    override fun toggleMagPaladin() {
        charInstance.levelLoop(
            endLevel = 20
        ){character ->
            //apply bonus to valid classes
            if(character.classes.ownClass.intValue in 3..4)
                character.classes.toggleMagPaladin()
            //simply change the flag
            else
                character.classes.magPaladin.value = !magPaladin.value
        }

        //change main flag
        magPaladin.value = !magPaladin.value

        //update class items
        charInstance.secondaryList.getAllSecondaries().forEach{it.classTotalRefresh()}
        charInstance.magic.updateZeonFromClass()
        charInstance.summoning.allSummoning().forEach{it.updateLevelTotal()}
    }

    /**
     * Attempts to change the selection in the indicated record index.
     *
     * @param selectionIndex record location to set the selection to
     * @param secondarySelection value to set the selection to
     * @return value recorded after operation
     */
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
                charInstance.levelLoop(startLevel = 0, endLevel = 20){
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
                charInstance.levelLoop(startLevel = 0, endLevel = 20){
                    if(it.classes.ownClass.intValue == 0)
                        it.secondaryList.getAllSecondaries()[prevIndex].setClassPointsPerLevel(0)
                }

            //set new input
            freelancerSelection[selectionIndex] = secondarySelection

            //add new bonus
            charInstance.levelLoop(startLevel= 0, endLevel = 20){
                if(it.classes.ownClass.intValue == 0) {
                    it.classes.freelancerSelection[selectionIndex] = secondarySelection
                    it.secondaryList.getAllSecondaries()[secondarySelection - 1].setClassPointsPerLevel(
                        classBonus = 10
                    )
                }
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
        
        //look through the level record
        charInstance.levelLoop(
            startLevel = 1
        ){character ->
            //add class difference points for each level
            output += getClassPointsByLevel(baseLevel = charInstance.charRefs.indexOf(character))
        }

        //return points spent
        return output
    }

    /**
     * Get development points from a class change from the indicated level.
     *
     * @param baseLevel level to check for class change
     * @return value of points spent to make the change
     */
    fun getClassPointsByLevel(baseLevel: Int): Int{
        //get this level's class if available
        val levelClass = charInstance.charRefs[baseLevel]?.classes?.ownClass?.intValue

        //get the next level's class if available
        val nextClass =
            if(baseLevel < 20)
                charInstance.charRefs[baseLevel + 1]?.classes?.ownClass?.intValue
            else null

        //run action if a change in class found
        if(levelClass != null && nextClass != null && levelClass != nextClass){
            //changing to or from Freelancer will only ever spend 20 DP
            if(levelClass == 0 || nextClass == 0)
                return 20

            //if freelancer not involved
            else {
                val prevClass = getClassRecord().allClasses[levelClass]
                val newClass = getClassRecord().allClasses[nextClass]

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

                //all archetypes matching
                return if (allMatch) 20
                //some matching archetypes
                else if (partMatch) 40
                //or no matching archetypes
                else 60
            }
        }

        return 0
    }
}