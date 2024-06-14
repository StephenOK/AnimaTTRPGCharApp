package com.paetus.animaCharCreator.character_creation

import com.paetus.animaCharCreator.character_creation.attributes.class_objects.SblClassInstances
import com.paetus.animaCharCreator.character_creation.attributes.combat.SblCombatAbilities
import com.paetus.animaCharCreator.character_creation.attributes.combat.SblCombatItem
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.SblKi
import com.paetus.animaCharCreator.character_creation.attributes.magic.SblMagic
import com.paetus.animaCharCreator.character_creation.attributes.primary_abilities.SblPrimaryChar
import com.paetus.animaCharCreator.character_creation.attributes.primary_abilities.SblPrimaryList
import com.paetus.animaCharCreator.character_creation.attributes.psychic.SblPsychic
import com.paetus.animaCharCreator.character_creation.attributes.secondary_abilities.SblSecondaryList
import com.paetus.animaCharCreator.character_creation.attributes.summoning.SblSummoning
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
    val charRefs = mutableListOf(
        BaseCharacter(newHost = this),
        BaseCharacter(newHost = this),
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
    override val combat = SblCombatAbilities(charInstance = this)
    override val secondaryList = SblSecondaryList(sblChar = this)

    override val ki = SblKi(charInstance = this)
    override val magic = SblMagic(charInstance = this)

    override val psychic = SblPsychic(charInstance = this)
    override val summoning = SblSummoning(charInstance = this)
    override val classes = SblClassInstances(charInstance = this)

    /**
     * Updates the character's level and any associated values.
     *
     * @param levNum value to set the character's level to
     */
    override fun setLvl(levNum: Int) {
        //initialize character if no record at that level
        if(charRefs[levNum + 1] == null) {
            charRefs[levNum + 1] = BaseCharacter(newHost = this)

            charRefs[levNum + 1]!!.setLvl(levNum = 1)
            charRefs[levNum + 1]!!.classes.setOwnClass(charRefs[levNum]!!.classes.ownClass.intValue)
        }

        //update secondary items
        secondaryList.levelUpdate(newLevel = levNum)

        super.setLvl(levNum)

        //update primary bonus amounts
        primaryList.allPrimaries().forEach{
            (it as SblPrimaryChar).refreshBonusTotal()
        }

        //update combat item input values
        combat.allAbilities().forEach{
            (it as SblCombatItem).updateInput()
        }

        //update dev points spent
        updateTotalSpent()
    }

    /**
     * Calculates percentage allotments for each category.
     */
    override fun dpAllotmentCalc() {
        //reinitialize DP caps for each category
        maxCombatDP.intValue = 0
        maxMagDP.intValue= 0
        maxPsyDP.intValue = 0

        //get each character's dp amount for each category
        levelLoop{checkChar ->
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
                checkChar.combat.lifeMultsTaken.intValue * classRecord.allClasses[checkChar.classes.ownClass.intValue].lifePointMultiple +
                        checkChar.secondaryList.calculateSpent()
        }

        spentTotal.intValue += classes.calculateSpent() + ptInCombat.intValue + ptInMag.intValue + ptInPsy.intValue
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
     * @param startLevel level to start
     * @param endLevel level to loop to (defaults to current level)
     * @param runFunc what function to run for each character level
     */
    fun levelLoop(
        startLevel: Int = 0,
        endLevel: Int = lvl.intValue,
        runFunc: (BaseCharacter) -> Unit
    ){
        //catch potential negative level input
        if(endLevel >= 0) {
            for (index in startLevel..endLevel) {
                if (charRefs[index] != null)
                    runFunc(charRefs[index]!!)
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
                if(character != charRefs[0])
                    character.setLvl(levNum = 1)

                //apply primary bonus values
                character.primaryList.allPrimaries().forEach{primeChar ->
                    primaryList.allPrimaries()[primeChar.charIndex].setLevelBonus(primeChar.levelBonus.intValue)
                }
            }
        }
    }
}