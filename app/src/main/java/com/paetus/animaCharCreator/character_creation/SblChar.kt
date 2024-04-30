package com.paetus.animaCharCreator.character_creation

import com.paetus.animaCharCreator.character_creation.attributes.primary_abilities.SblPrimaryList
import com.paetus.animaCharCreator.character_creation.attributes.secondary_abilities.SblSecondaryList
import java.io.File

/**
 * Subclass of BaseCharacter which works on characters that are saved in individual levels.
 * Each level is saved as its own BaseCharacter and recompiled in this item.
 *
 * @param sourceDIR home directory of the character's level files
 * @param secondaryFile location of custom secondary characteristic items
 * @param techFile location of custom dominion technique items
 */
class SblChar(
    sourceDIR: File,
    secondaryFile: File,
    techFile: File
): BaseCharacter() {
    //initialize level data for this character
    val charRefs = mutableListOf<BaseCharacter?>(
        BaseCharacter(),
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null
    )

    //initialize character's other item data
    override val primaryList = SblPrimaryList(charInstance = this)

    override val secondaryList = SblSecondaryList(sblChar = this)

    /**
     * Updates the character's level and any associated values.
     *
     * @param levNum value to set the character's level to
     */
    override fun setLvl(levNum: Int) {
        //initialize character if no record at that level
        if(charRefs[levNum] == null) {
            charRefs[levNum] = BaseCharacter()

            charRefs[levNum]!!.classes.setOwnClass(charRefs[levNum - 1]!!.classes.ownClass.value)
        }

        secondaryList.levelUpdate(newLevel = levNum)

        super.setLvl(levNum)

        updateTotalSpent()
    }

    /**
     * Calculates percentage allotments for each category.
     */
    override fun dpAllotmentCalc() {
        //reinitialize DP caps for each categoryy
        maxCombatDP.intValue = 0
        maxMagDP.intValue= 0
        maxPsyDP.intValue = 0

        //get each character's dp amount for each category
        levelLoop(lvl.intValue){checkChar ->
            //determine the level's development points
            val dpSection =
                when(checkChar) {
                    charRefs.first() -> 400
                    charRefs[1] -> 200
                    else -> 100
                }

            //split as needed for each section
            maxCombatDP.intValue += (dpSection * checkChar.percCombatDP.doubleValue).toInt()
            maxMagDP.intValue += (dpSection * checkChar.percMagDP.doubleValue).toInt()
            maxPsyDP.intValue += (dpSection * checkChar.percPsyDP.doubleValue).toInt()
        }
    }

    /**
     * Updates the total development points spent.
     */
    override fun updateTotalSpent(){
        //reset spent totals
        ptInCombat.intValue = 0
        ptInMag.intValue = 0
        ptInPsy.intValue = 0
        spentTotal.intValue = 0

        //make sure martial arts taken are still legal
        weaponProficiencies.doubleCheck()

        //add DP spent in each level
        levelLoop{checkChar ->
            //add level's combat expenditures
            ptInCombat.intValue +=
                checkChar.combat.calculateSpent() +
                        checkChar.weaponProficiencies.calculateSpent() +
                        checkChar.ki.calculateSpent()

            //add level's magic expenditures
            ptInMag.intValue +=
                checkChar.magic.calculateSpent() +
                        checkChar.summoning.calculateSpent() +
                        checkChar.weaponProficiencies.calcPointsInMag()

            //add level's psychic expenditures
            ptInPsy.intValue +=
                checkChar.psychic.calculateSpent() +
                        checkChar.weaponProficiencies.calcPointsInPsy()

            //add level's other items
            spentTotal.intValue +=
                checkChar.combat.lifeMultsTaken.intValue * checkChar.classes.ownClass.value.lifePointMultiple +
                        checkChar.secondaryList.calculateSpent()
        }

        spentTotal.intValue += ptInCombat.intValue + ptInMag.intValue + ptInPsy.intValue
    }

    /**
     * Get the character record for the current level.
     *
     * @return held character for the level
     */
    fun getCharAtLevel(): BaseCharacter{
        return charRefs[lvl.intValue]!!
    }

    /**
     * Loop through each character record up to the indicated level.
     *
     * @param charLevel level to loop to (defaults to current level)
     * @param runFunc what function to run for each character level
     */
    fun levelLoop(
        charLevel: Int = lvl.intValue,
        runFunc: (BaseCharacter) -> Unit
    ){
        //catch potential negative level input
        if(charLevel >= 0) {
            for (index in 0..charLevel) {
                if (charRefs[index] != null)
                    runFunc(charRefs[index]!!)
                //else
                //    runFunc(BaseCharacter())
            }
        }
    }

    /**
     * Initialize the SBL character.
     */
    init{
        //look through each file in the directory
        sourceDIR.listFiles()?.forEach{file ->
            //create a character based on that file data
            val levelChar = BaseCharacter(
                charFile = file,
                secondaryFile = secondaryFile,
                techFile = techFile
            )

            //set character at the indicated index
            charRefs[levelChar.lvl.intValue] = levelChar
        }

        //for each character level record
        charRefs.forEach{character ->
            if(character != null){
                //apply primary bonus values
                character.primaryList.allPrimaries().forEach{primeChar ->
                    primaryList.allPrimaries()[primeChar.charIndex].setLevelBonus(primeChar.levelBonus.intValue)
                }
            }
        }
    }
}