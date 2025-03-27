package com.paetus.animaCharCreator.character_creation

import com.paetus.animaCharCreator.character_creation.attributes.advantages.SblAdvantages
import com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_types.RacialAdvantage
import com.paetus.animaCharCreator.character_creation.attributes.class_objects.SblClassInstances
import com.paetus.animaCharCreator.character_creation.attributes.combat.SblCombatAbilities
import com.paetus.animaCharCreator.character_creation.attributes.combat.SblCombatItem
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.SblKi
import com.paetus.animaCharCreator.character_creation.attributes.magic.SblMagic
import com.paetus.animaCharCreator.character_creation.attributes.modules.SblProficiencies
import com.paetus.animaCharCreator.character_creation.attributes.primary_abilities.SblPrimaryChar
import com.paetus.animaCharCreator.character_creation.attributes.primary_abilities.SblPrimaryList
import com.paetus.animaCharCreator.character_creation.attributes.psychic.SblPsychic
import com.paetus.animaCharCreator.character_creation.attributes.secondary_abilities.SblSecondaryList
import com.paetus.animaCharCreator.character_creation.attributes.summoning.SblSummonAbility
import com.paetus.animaCharCreator.character_creation.attributes.summoning.SblSummoning
import com.paetus.animaCharCreator.character_creation.equipment.SblInventory
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
        BaseCharacter(newHost = this, prevIndex = -1, isAdded = false),
        BaseCharacter(newHost = this, prevIndex = 0, isAdded = false),
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
    override val weaponProficiencies = SblProficiencies(charInstance = this)
    override val ki = SblKi(charInstance = this)
    override val magic = SblMagic(sblChar = this)
    override val summoning = SblSummoning(charInstance = this)
    override val psychic = SblPsychic(charInstance = this)
    override val advantageRecord = SblAdvantages(sblChar = this)
    override val inventory = SblInventory(sblChar = this)

    override val classes = SblClassInstances(charInstance = this)

    /**
     * Sets the character's name value.
     *
     * @param newName string to set as the character's name
     */
    override fun setName(newName: String) {
        //set name as normal
        super.setName(newName)

        //record name in level 0 record
        charRefs[0]!!.setName(charName.value)
    }

    /**
     * Sets the character's experience point value.
     *
     * @param newExp value to set as the character's experience points
     */
    override fun setExp(newExp: Int) {
        //set exp as normal
        super.setExp(newExp)

        //record exp in level 0 record
        charRefs[0]!!.setExp(experiencePoints.intValue)
    }

    /**
     * Changes the character's gender depending on the input
     *
     * @param gender true if male, false if female
     */
    override fun setGender(gender: Boolean) {
        //set gender as normal
        super.setGender(gender)

        //record gender in level 0 record
        charRefs[0]!!.setGender(isMale.value)
    }

    /**
     * Sets the character's race to the inputted item.
     *
     * @param raceIn new race to set the character to
     */
    override fun setOwnRace(raceIn: List<RacialAdvantage>) {
        //set race as normal
        super.setOwnRace(raceIn)

        //record race in level 0 record
        charRefs[0]!!.setOwnRace(ownRace.value)
    }

    /**
     * Updates the character's level and any associated values.
     *
     * @param levNum value to set the character's level to
     */
    override fun setLvl(levNum: Int) {
        //initialize character if no record at that level
        if(charRefs[levNum + 1] == null)
            charRefs[levNum + 1] = BaseCharacter(newHost = this, prevIndex = levNum, isAdded = true)

        super.setLvl(levNum)

        //update primary bonus amounts
        primaryList.allPrimaries().forEach{
            (it as SblPrimaryChar).refreshBonusTotal()
        }

        //update combat item input values
        combat.updateLifeMults()
        combat.allAbilities().forEach{
            (it as SblCombatItem).updateInput()
        }

        //update secondary items
        secondaryList.levelUpdate()

        //update weapon proficiencies
        weaponProficiencies.levelUpdate()

        //update ki abilities
        ki.levelUpdate()

        //update magic abilities
        magic.levelUpdate()

        //update psychic abilities
        psychic.levelUpdate()

        //update summoning abilities
        summoning.allSummoning().forEach{(it as SblSummonAbility).levelUpdate()}

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
                checkChar.combat.lifeMultsTaken.intValue * objectDB.classRecord.allClasses[checkChar.classes.ownClass.intValue].lifePointMultiple +
                        checkChar.secondaryList.calculateSpent()
        }

        spentTotal.intValue += classes.calculateSpent() + ptInCombat.intValue + ptInMag.intValue + ptInPsy.intValue
    }

    /**
     * Sets the character's appearance to the inputted item, if able.
     *
     * @param newAppearance value to attempt to set the character's appearance to
     */
    override fun setAppearance(newAppearance: Int) {
        //set appearance as normal
        super.setAppearance(newAppearance)

        //record appearance in level 0 record
        charRefs[0]!!.setAppearance(appearance.intValue)
    }

    /**
     * Set the character's gnosis value.
     *
     * @param newGnosis value to set to the character's gnosis
     */
    override fun setGnosis(newGnosis: Int) {
        //set gnosis as normal
        super.setGnosis(newGnosis)

        //record gnosis in level 0 record
        charRefs[0]!!.setGnosis(gnosis.intValue)
    }

    /**
     * Calculates the DP spent in the indicated level.
     *
     * @param level record index to check
     * @return the DP spent in that level
     */
    fun getLevelPoints(
        level: Int
    ): Int{
        //retrieve the record to check
        val record = charRefs[level]

        //if there is an existing record
        return if(record != null)
            //determine all points spent in this level
            record.combat.calculateSpent() + record.weaponProficiencies.calculateSpent() +
                    record.ki.calculateSpent() + record.magic.calculateSpent() +
                    record.summoning.calculateSpent() + record.weaponProficiencies.calcPointsInMag() +
                    record.psychic.calculateSpent() + record.weaponProficiencies.calcPointsInPsy() +
                    (record.combat.lifeMultsTaken.intValue * objectDB.classRecord.allClasses[record.classes.ownClass.intValue].lifePointMultiple) +
                    record.secondaryList.calculateSpent() + classes.getClassPointsByLevel(baseLevel = level)
        //otherwise return 0
        else 0
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
            val level = file.nameWithoutExtension.toInt()

            //create a character based on that file data
            val levelChar = BaseCharacter(
                charFile = file,
                secondaryFile = secondaryFile,
                techFile = techFile,
                objectDB = objectDB
            )

            //set character at the indicated index
            charRefs[level] = levelChar
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

        //set name, exp, race, gender, appearance, and gnosis
        super.setName(charRefs[0]!!.charName.value)
        super.setExp(charRefs[0]!!.experiencePoints.intValue)
        super.setOwnRace(charRefs[0]!!.ownRace.value)
        super.setGender(charRefs[0]!!.isMale.value)
        super.setAppearance(charRefs[0]!!.appearance.intValue)
        super.setGnosis(charRefs[0]!!.gnosis.intValue)

        //set initial class
        classes.setOwnClass(charRefs[0]!!.classes.ownClass.intValue)

        //set freelancer selection options
        for(index in 0..4){
            classes.setSelection(
                selectionIndex = index,
                secondarySelection = charRefs[0]!!.classes.freelancerSelection[index]
            )
        }

        //set primary items
        charRefs[0]!!.primaryList.allPrimaries().forEach{primary ->
            primaryList.allPrimaries()[primary.charIndex].setInput(primary.inputValue.intValue)
        }

        //set primary weapon
        weaponProficiencies.setPrimaryWeapon(charRefs[0]!!.weaponProficiencies.primaryWeapon.intValue)

        //set advantages
        charRefs[0]!!.advantageRecord.takenAdvantages.forEach{
            advantageRecord.takenAdvantages.add(it)
        }

        //set inventory items
        charRefs[0]!!.inventory.boughtGoods.forEach{(item, amount) ->
            inventory.boughtGoods.plus(Pair(item, amount))
        }

        //set the level the character is starting at
        setLvl(startLevel())
    }

    /**
     * Determine the starting level for a loaded character.
     *
     * @return level to open the character at
     */
    private fun startLevel(): Int{
        //run through characters up to 18
        for(index in 0..19){
            //return index if no level record two levels higher
            if(charRefs[index + 1] == null)
                return index

            //return index if all points in this level are not spent
            when(index){
                0 -> {if(getLevelPoints(0) != 400) return 0}
                1 -> {if(getLevelPoints(1) != 200) return 1}
                else -> {if(getLevelPoints(index) != 100) return index}
            }
        }

        //return final level option
        return 20
    }
}