package com.paetus.animaCharCreator.character_creation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.advantages.SblAdvantages
import com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_types.Advantage
import com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_types.RacialAdvantage
import com.paetus.animaCharCreator.character_creation.attributes.class_objects.SblClassInstances
import com.paetus.animaCharCreator.character_creation.attributes.combat.SblCombatAbilities
import com.paetus.animaCharCreator.character_creation.attributes.combat.SblCombatItem
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.SblKi
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.SblKiStat
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.abilities.KiAbility
import com.paetus.animaCharCreator.character_creation.attributes.magic.SblMagic
import com.paetus.animaCharCreator.character_creation.attributes.modules.SblProficiencies
import com.paetus.animaCharCreator.character_creation.attributes.primary_abilities.SblPrimaryChar
import com.paetus.animaCharCreator.character_creation.attributes.primary_abilities.SblPrimaryList
import com.paetus.animaCharCreator.character_creation.attributes.psychic.SblPsychic
import com.paetus.animaCharCreator.character_creation.attributes.secondary_abilities.SblCustomCharacteristic
import com.paetus.animaCharCreator.character_creation.attributes.secondary_abilities.SblSecondaryCharacteristic
import com.paetus.animaCharCreator.character_creation.attributes.secondary_abilities.SblSecondaryList
import com.paetus.animaCharCreator.character_creation.attributes.summoning.SblSummonAbility
import com.paetus.animaCharCreator.character_creation.attributes.summoning.SblSummoning
import com.paetus.animaCharCreator.character_creation.equipment.SblInventory
import java.io.File

/**
 * Subclass of BaseCharacter which works on characters that are saved in individual levels.
 * Each level is saved as its own BaseCharacter and recompiled in this item.
 */
class SblChar(): BaseCharacter() {
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

    /**
     * Sets the character's name value.
     *
     * @param newName string to set as the character's name
     */
    override fun setName(newName: String) {
        //set name as normal
        super.setName(newName = newName)

        //record name in level 0 record
        charRefs[0]!!.setName(newName = charName.value)
    }

    /**
     * Sets the character's experience point value.
     *
     * @param newExp value to set as the character's experience points
     */
    override fun setExp(newExp: Int) {
        //get experience points gained in previous levels
        val prevPoints = getRecordSum(endLevel = lvl.intValue - 1){character ->
            character.experiencePoints.intValue
        }

        //apply additional experience points to the current level record
        charRefs[lvl.intValue]!!.experiencePoints.intValue = newExp - prevPoints

        //apply exp points to the main record
        updateExperiencePoints()
    }

    /**
     * Get the number of experience points held at the given level.
     *
     * @param level character level checked
     */
    fun expPointsAtLevel(level: Int): Int{
        return getRecordSum(endLevel = level){character ->
            character.experiencePoints.intValue
        }
    }

    /**
     * Refresh the current experience points held by the character.
     */
    fun updateExperiencePoints(){
        //reset the held points
        experiencePoints.intValue = 0

        //add each record's experience points held
        levelLoop{character ->
            experiencePoints.intValue += character.experiencePoints.intValue
        }
    }

    /**
     * Determines if the character has enough experience points to pass the indicated level.
     *
     * @param level character level checked
     * @return true if character can go to a higher level
     */
    fun expValReachedAtLevel(level: Int): Boolean{
        return when(level){
            //level 0 characters may always level up from exp
            0 -> true
            in 1..14 -> {
                //initialize the needed point counter
                var needed = 0

                //add an increasing amount for each level, starting with 100 exp
                for(loopNum in 0 ..< level){
                    needed += 100 + 25 * loopNum
                }

                //check that current points exceeds the calculated requirement
                expPointsAtLevel(level = level) >= needed
            }
            //set a fixed increase value for levels higher than 14
            else -> expPointsAtLevel(level = level) >= 3675 + 450 * (level - 14)
        }
    }

    /**
     * Toggles the experience point level restriction flag.
     */
    fun toggleExpLock(){
        //toggle the main flag
        expLock.value = !expLock.value

        //toggle the flags in each record
        levelLoop{character -> character.expLock.value = expLock.value}
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
        if(levNum + 1 <= 20 && charRefs[levNum + 1] == null)
            charRefs[levNum + 1] = BaseCharacter(newHost = this, prevIndex = levNum, isAdded = true)

        //update free spells held before saving
        magic.retrieveBooks().forEach{book -> book.validateFreeSpells()}

        super.setLvl(levNum)

        //update the experience points held
        updateExperiencePoints()

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

        //update ki abilities
        ki.levelUpdate()

        //update weapon proficiencies
        weaponProficiencies.levelUpdate()

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

        //initialize first martial art taken tracker
        var firstArt = false

        //add DP spent in each level
        levelLoop{checkChar ->
            //add level's combat expenditures
            ptInCombat.intValue +=
                checkChar.combat.calculateSpent() +
                        checkChar.weaponProficiencies.calculateSpent() +
                        checkChar.ki.calculateSpent()

            //add points missed in calculating points spent in martial arts
            if(firstArt && checkChar.weaponProficiencies.takenMartialList.isNotEmpty()) {
                ptInCombat.intValue +=
                    if (checkChar.classes.ownClass.intValue == 7) 10
                    else if(weaponProficiencies.primaryWeapon.intValue == 0) 25
                    else 0
            }

            //notify of martial art list no longer being empty
            if(checkChar.weaponProficiencies.takenMartialList.isNotEmpty())
                firstArt = true

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

        spentTotal.intValue += classes.calculateSpentAtLevel(lvl.intValue) + ptInCombat.intValue + ptInMag.intValue + ptInPsy.intValue
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
     * Get the sum of the indicated item from the character record.
     *
     * @param startLevel record index to start the sum at
     * @param endLevel record index to finish the sum at
     * @param sumFunc method to run on the character record to get the added number
     * @return total sum gained from each record
     */
    fun getRecordSum(
        startLevel: Int = 0,
        endLevel: Int = lvl.intValue,
        sumFunc: (BaseCharacter) -> Int
    ): Int{
        //initialize the result
        var output = 0

        //add each record's data as indicated
        levelLoop(
            startLevel = startLevel,
            endLevel = endLevel
        ){character ->
            output += sumFunc(character)
        }

        //give final outcome
        return output
    }

    /**
     * Get a list of items as indicated from each character level record.
     *
     * @param startLevel record index to start the collection at
     * @param endLevel record index to finish the collection at
     * @param listFunc method to run on the character record to get the listed items
     * @return final list of each record's items
     */
    fun getRecordList(
        startLevel: Int = 0,
        endLevel: Int = lvl.intValue,
        listFunc: (BaseCharacter) -> List<Any>
    ): List<Any>{
        //initialize the final list
        val output = mutableListOf<Any>()

        levelLoop(
            startLevel = startLevel,
            endLevel = endLevel
        ){character ->
            output.addAll(listFunc(character))
        }

        //give final outcome
        return output.toList()
    }

    fun zeroReset(){
        //run level reset
        resetLevel()

        //apply race
        setOwnRace(charRefs[0]!!.ownRace.value)

        //reset initial class to freelancer
        classes.changeClasses(
            startLevel = 1,
            classIndex = 0
        )

        //apply empty freelancer selection list
        for(index in 0..4)
            classes.setSelection(
                selectionIndex = index,
                secondarySelection = -1
            )

        weaponProficiencies.setPrimaryWeapon(charRefs[0]!!.weaponProficiencies.primaryWeapon.intValue)

        magic.setProjImbalance(imbalance = charRefs[0]!!.magic.magProjImbalance.intValue)
        magic.imbalanceIsAttack.value = charRefs[0]!!.magic.imbalanceIsAttack.value

        //remove advantages
        while(!advantageRecord.takenAdvantages.isEmpty())
            advantageRecord.removeAdvantage(advantageRecord.takenAdvantages.first())

        super.setName(newName = charRefs[0]!!.charName.value)
        super.setExp(newExp = charRefs[0]!!.experiencePoints.intValue)
        super.setAppearance(newAppearance = charRefs[0]!!.appearance.intValue)
        super.setGnosis(newGnosis = charRefs[0]!!.gnosis.intValue)

        //prevent any potential change to the exp restriction flag
        charRefs[0]!!.expLock.value = expLock.value

        //reset primary data
        primaryList.allPrimaries().forEach{primary ->
            primary.setInput(5)
        }

        //reset available coin maximums
        inventory.maxGold.intValue = 0
        inventory.maxSilver.intValue = 0
        inventory.maxCopper.intValue = 0

        //remove all purchased items
        inventory.boughtGoods.clear()

        //update spent coin values
        inventory.countSpent()
    }

    fun nonZeroReset(){
        //get the class at this level
        val prevClass = charRefs[lvl.intValue]!!.classes.ownClass.intValue

        //run level reset
        resetLevel()

        //reapply previously held class
        charRefs[lvl.intValue]!!.classes.setOwnClass(prevClass)

        //reapply freelancer's selected bonuses
        if(charRefs[lvl.intValue]!!.classes.ownClass.intValue == 0)
            classes.freelancerSelection.forEach{selection ->
                if(selection != -1) {
                    charRefs[lvl.intValue]!!.secondaryList.getAllSecondaries()[selection].setClassPointsPerLevel(
                        classBonus = 10
                    )
                    secondaryList.getAllSecondaries()[selection].classTotalRefresh()
                }
            }

        //if the character is a paladin and chose not to take magical abilities
        if(firstPaladin() >= 0 && !charRefs[firstPaladin()]!!.classes.magPaladin.value) {
            //toggle the character record's decision and apply the appropriate bonuses
            if(charRefs[lvl.intValue]!!.classes.ownClass.intValue == 3 || charRefs[lvl.intValue]!!.classes.ownClass.intValue == 4) {
                charRefs[lvl.intValue]!!.classes.toggleMagPaladin()

                secondaryList.getAllSecondaries().forEach{it.classTotalRefresh()}
                magic.updateZeonFromClass()
                summoning.allSummoning().forEach{it.updateLevelTotal()}
            }

            //only toggle the record if this level is not a paladin level
            else charRefs[lvl.intValue]!!.classes.magPaladin.value = false
        }

        //check for changed class and remove, if necessary
        if(charRefs[lvl.intValue + 1]!!.classes.ownClass.intValue != prevClass)
            classes.changeClasses(
                startLevel = lvl.intValue + 1,
                classIndex = prevClass
            )

        magic.updateZeonFromClass()
        updateTotalSpent()
    }

    /**
     * Resets the current level record to an empty state.
     */
    fun resetLevel(){
        //check for reset of freelancer bonus selection
        if(firstFreelancer() == lvl.intValue)
            for(index in 0..4)
                classes.setSelection(
                    selectionIndex = index,
                    secondarySelection = -1
                )

        //replace current record with an empty record
        charRefs[lvl.intValue] =
            BaseCharacter(
                newHost = this,
                prevIndex = lvl.intValue - 1,
                isAdded = lvl.intValue != 0
            )

        //update all level based items
        setLvl(lvl.intValue)

        levelLoop(
            startLevel = lvl.intValue + 1,
            endLevel = 20
        ){character ->
            //get the record's corresponding level
            val charLevel = charRefs.indexOf(character)

            //check validity of taken natural bonuses in future levels
            character.secondaryList.getAllSecondaries().forEach{secondary ->
                if(secondary.bonusApplied.value){
                    if((secondaryList.getAllSecondaries()[character.secondaryList.getAllSecondaries().indexOf(secondary)] as SblSecondaryCharacteristic).getPointsInAtLevel(level = charLevel) == 0)
                        secondary.bonusApplied.value = false

                    return@forEach
                }
            }

            //initialize list of ki abilities to remove
            val removeList = mutableListOf<KiAbility>()

            //check validity of taken ki abilities in future levels
            character.ki.takenAbilities.forEach{kiAbility ->
                val levelAbilities = ki.kiAbilitiesAtLevel(level = charLevel)

                //add ability to removal list if either there are insufficient MK points or if it's no longer qualified
                if(ki.getMKAtLevel(level = charLevel) - ki.getSpentMKAtLevel(level = charLevel - 1) < kiAbility.mkCost ||
                    (kiAbility.prerequisite != null &&
                            (!levelAbilities.contains(kiAbility.prerequisite) ||
                            removeList.contains(kiAbility.prerequisite))))
                    removeList.add(kiAbility)
            }

            //remove future techniques if ki control is removed
            if(!ki.kiAbilitiesAtLevel(charRefs.indexOf(character)).contains(objectDB.kiRecord.kiControl))
                character.ki.heldTechniques.clear()

            //remove the indicated ki abilities
            character.ki.takenAbilities.removeAll(removeList)

            //TODO: Check that it should be just psychic and not character.psychic
            //check psychic power investment in future levels
            psychic.legalDisciplines.forEach{discipline -> psychic.removeIllegal(discipline)}
        }

        //validate techniques in future levels
        ki.removeExtra()
    }

    /**
     * Determines the first level the character has the freelancer class at.
     *
     * @return the level the character first has the freelancer class at.
     */
    fun firstFreelancer(): Int{
        //initialize output at unfound indicator
        var output = -1

        //search each level for the freelancer class
        levelLoop(endLevel = 20){character ->
            if(output == -1 && character.classes.ownClass.intValue == 0)
                output = charRefs.indexOf(character)
        }

        return output
    }

    /**
     * Determines the first level the character has a paladin class at.
     *
     * @return the level the character first has a paladin class at.
     */
    fun firstPaladin(): Int{
        //initialize output at unfound indicator
        var output = -1

        //search each level for a paladin class
        levelLoop(endLevel = 20){character ->
            if(output == -1 && character.classes.ownClass.intValue in 3..4)
                output = charRefs.indexOf(character)
        }

        return output
    }

    /**
     * Determine the development points spent at the indicated level.
     *
     * @param level character level to get the data for
     * @return points spent at this level
     */
    fun getDPSpentAtLevel(level: Int): Int{
        //give the total as the sum of this level's combat abilities,
        return getCombatSpentAtLevel(level = level) +
                //magic abilities,
                getMagicSpentAtLevel(level = level) +
                //psychic abilities,
                getPsychicSpentAtLevel(level = level) +
                //life mults,
                combat.getLifeMultsCostAtLevel(level = level) +
                //secondary items,
                secondaryList.getSecondaryPointsSpentAtLevel(level = level) +
                //and class changes
                classes.calculateSpentAtLevel(level = level)
    }

    /**
     * Gets the number of points spent in combat items at this level.
     *
     * @param level character level to check the points spent at
     * @return total points spent in combat abilities at the indicated level
     */
    fun getCombatSpentAtLevel(level: Int): Int{
        //initialize first martial art taken flag
        var firstArt = false

        //start output at sum of each record's combat, module, and ki items
        var output = getRecordSum(endLevel = level){character ->
            character.combat.calculateSpent() +
                    character.weaponProficiencies.calculateSpent() +
                    character.ki.calculateSpent()
        }

        //catch missed points from martial arts taken
        levelLoop(endLevel = level){character ->
            //if record has martial arts taken and isn't the first record with arts
            output += if (firstArt && character.weaponProficiencies.takenMartialList.isNotEmpty()) {
                //add 10 points if character is a Tao
                if (character.classes.ownClass.intValue == 7) 10
                //add 25 points if primary weapon is unarmed
                else if (weaponProficiencies.primaryWeapon.intValue == 0) 25
                //add no points if neither apply
                else 0
            } else 0

            //indicate first record with martial arts taken
            if(character.weaponProficiencies.takenMartialList.isNotEmpty())
                firstArt = true
        }

        //give final result
        return output
    }

    /**
     * Gets number of points spent in magic items at this level.
     *
     * @param level character level to check the points spent at
     * @return total points spent in magic abilities at the indicated level
     */
    fun getMagicSpentAtLevel(level: Int): Int{
        //give the total as a sum of the character's
        return getRecordSum(endLevel = level){character ->
            //magic abilities,
            character.magic.calculateSpent() +
                    //summoning abilities,
                    character.summoning.calculateSpent() +
                    //and any magic related style modules
                    character.weaponProficiencies.calcPointsInMag()
        }
    }

    /**
     * Gets the number of points spent in psychic items at this level.
     *
     * @param level character level to check the points spent at
     * @return total points spent in psychic abilities at the indicated level
     */
    fun getPsychicSpentAtLevel(level: Int): Int{
        //give the total as a sum of the character's
        return getRecordSum(endLevel = level){character ->
            //psychic abilities
            character.psychic.calculateSpent() +
                    //and any psychic related style modules
                    character.weaponProficiencies.calcPointsInPsy()
        }
    }

    /**
     * Get available DP at the indicated level.
     *
     * @param level character level to get the total for
     * @return DP the character may spend at this level
     */
    fun getMaxDPAtLevel(level: Int): Int{
        //give 400 points to level 0 character
        return if(level == 0) 400
        //calculate the appropriate value based on the level
        else 500 + level * 100
    }

    /**
     * Determines the category maximum at the indicated level.
     *
     * @param level character level to get the data for
     * @param multFunc method to run to get the DP data
     * @return DP available to the category at this level
     */
    fun levelMaxCalc(
        level: Int,
        multFunc: (BaseCharacter) -> Double
    ): Int{
        //get percentage of 400 for level 0 characters
        return if(level == 0)
            (400 * multFunc(charRefs[0]!!)).toInt()
        else
            //start calculation with percentage of 600 DP
            (600 * multFunc(charRefs[1]!!)).toInt() +
                    //get sum of each record after the first level
                    getRecordSum(
                        startLevel = 2,
                        endLevel = level
                    ){character ->
                        (100 * multFunc(character)).toInt()
                    }
    }

    /**
     * Gets the DP available for combat abilities at the indicated level.
     *
     * @param level character level to get the amount for
     * @return combat DP the character may spend
     */
    fun getCombatMaxAtLevel(level: Int): Int{
        //determine points based on each level's class combat maximums
        return levelMaxCalc(level = level){character ->
            character.classes.getClass().combatMax
        }
    }

    /**
     * Gets the DP available for magic abilities at the indicated level.
     *
     * @param level character level to get the amount for
     * @return magic DP the character may spend
     */
    fun getMagicMaxAtLevel(level: Int): Int{
        //determine points based on each level's class magic maximums
        return levelMaxCalc(level = level){character ->
            character.classes.getClass().magMax
        }
    }

    /**
     * Gets the DP available for psychic abilities at the indicated level.
     *
     * @param level character level to get the amount for
     * @return psychic DP the character may spend
     */
    fun getPsyMaxAtLevel(level: Int): Int{
        //determine points based on each level's class psychic maximums
        return levelMaxCalc(level = level){character ->
            character.classes.getClass().psyMax
        }
    }

    /**
     * Determine that the character is allowed to change its level.
     *
     * @return list of error strings that would prevent a change in level
     */
    fun levelChangeLegal(atLevel: Int): List<@Composable () -> String>{
        //initialize final result
        val output = mutableListOf<@Composable () -> String>()

        //get DP total spent and total available at this level
        val dpSpent = getDPSpentAtLevel(level = atLevel)
        val levelMax = getMaxDPAtLevel(level = atLevel)

        //determine if DP spent appropriately
        if(dpSpent != levelMax)
            output.add{
                //add either indicator of overspent DP
                if (dpSpent > levelMax) {
                    stringResource(R.string.overDpFailure)
                }
                //or indicator of underspent DP
                else {
                    stringResource(R.string.underDpFailure)
                }
            }

        //determine if combat max maintained
        if(getCombatSpentAtLevel(level = atLevel) > getCombatMaxAtLevel(level = atLevel))
            output.add{
                stringResource(
                    R.string.sectionCapBreach,
                    stringResource(R.string.combatLabel)
                )
            }

        //determine if magic max maintained
        if(getMagicSpentAtLevel(level = atLevel) > getMagicMaxAtLevel(level = atLevel))
            output.add{
                stringResource(
                    R.string.sectionCapBreach,
                    stringResource(R.string.magicLabel)
                )
            }

        //determine if psychic max maintained
        if(getPsychicSpentAtLevel(level = atLevel) > getPsyMaxAtLevel(level = atLevel))
            output.add{
                stringResource(
                    R.string.sectionCapBreach,
                    stringResource(R.string.psychicLabel)
                )
            }

        //check exp points if restriction is enabled
        if(expLock.value){
            //check that the character has enough points to level up
            if (!expValReachedAtLevel(level = atLevel))
                output.add{stringResource(R.string.expLevelTooLow)}
            //check that the level does not have negative experience points
            if(charRefs[atLevel]!!.experiencePoints.intValue < 0)
                output.add{stringResource(R.string.invalidExpGrowth)}
        }

        //check each primary characteristic
        primaryList.allPrimaries().forEach{
            if(!(it as SblPrimaryChar).validGrowthAtLevel(level = atLevel))
                //if growth is not logical, notify of error in this stat
                output.add {
                    stringResource(
                        R.string.primaryBonusPointReduction,
                        stringArrayResource(id = R.array.primaryCharArray)[it.charIndex]
                    )
                }
        }

        //notify of too many primary bonus points added
        if(primaryList.getPrimaryBonusesAtLevel(level = atLevel) > atLevel/2)
            output.add{stringResource(R.string.invalidPrimaryBonus)}

        //notify of illegal life multiple growth
        if(!combat.validLifeGrowthAtLevel(level = atLevel))
            output.add{stringResource(R.string.lifeMultReduction)}

        //notify of bad combat ability point distribution
        if(!combat.validAttackDodgeBlockAtLevel(level = atLevel))
            output.add{stringResource(id = R.string.combatPointMisuse)}

        //determine if points removed from combat items
        combat.allAbilities().forEach{
            if(!(it as SblCombatItem).validGrowthAtLevel(level = atLevel))
                output.add{
                    stringResource(
                        R.string.combatInputPointReduction,
                        stringResource(it.itemLabel)
                    )
                }
        }

        //determine that all freelancer selections have been made at the first instance of the freelancer class
        if(atLevel == firstFreelancer() && charRefs[atLevel]!!.classes.freelancerSelection.contains(-1))
            output.add{stringResource(id = R.string.freelancerSelectionNeeded)}

        //determine that no points have been removed from secondary items
        secondaryList.getAllSecondaries().forEach{
            if(!(it as SblSecondaryCharacteristic).validGrowthAtLevel(level = atLevel)) {
                output.add{
                    stringResource(
                        R.string.secondaryInputPointReduction,
                        if (it.getIndex() < 38)
                            stringArrayResource(id = R.array.secondaryCharacteristics)[it.getIndex()]
                        else
                            (it as SblCustomCharacteristic).name.value
                    )
                }
            }

            //determine that the secondary item has a legal minimum input
            if(it.pointsApplied.intValue in 1..4)
                output.add{
                    stringResource(
                        R.string.secondaryInputTooFewPoints,
                        stringArrayResource(id = R.array.secondaryCharacteristics)[it.getIndex()]
                    )
                }
        }

        //determine that all natural bonuses have been distributed
        if(!secondaryList.natBonusAtLevel(level = atLevel))
            output.add{
                stringResource(R.string.natBonusNotDistributed)
            }

        //look through each ki stat
        ki.allKiStats().forEach{kiStat ->
            //check for valid point growth
            if(!(kiStat as SblKiStat).validPointGrowthAtLevel(level = atLevel))
                output.add{
                    stringResource(
                        R.string.kiPointReduction,
                        stringArrayResource(id = R.array.primaryCharArray)[
                            //get exact index number if not POW or WP
                            if(kiStat.kiIndex <  4)
                                kiStat.kiIndex
                            //correct index to get proper name
                            else
                                kiStat.kiIndex + 1
                        ]
                    )
                }

            //check for valid accumulation growth
            if(!kiStat.validAccGrowthAtLevel(level = atLevel))
                output.add{
                    stringResource(
                        R.string.kiAccReduction,
                        stringArrayResource(id = R.array.primaryCharArray)[
                            //get exact index number if not POW or WP
                            if(kiStat.kiIndex <  4)
                                kiStat.kiIndex
                            //correct index to get proper name
                            else
                                kiStat.kiIndex + 1
                        ]
                    )
                }
        }

        //catch invalid zeon point growth
        if(!magic.validPointGrowthAtLevel(level = atLevel))
            output.add{stringResource(R.string.zeonPointReduction)}

        //catch invalid zeon accumulation growth
        if(!magic.validAccGrowthAtLevel(level = atLevel))
            output.add{stringResource(R.string.zeonAccReduction)}

        //catch invalid magic projection growth
        if(!magic.validProjGrowthAtLevel(level = atLevel))
            output.add{stringResource(R.string.magicProjReduction)}

        //catch invalid magic projection value
        if(!magic.getValidProjectionAtLevel(level = atLevel))
            output.add{stringResource(R.string.magicProjMisuse)}

        //catch invalid book level growth
        magic.retrieveBooks().forEach{
            if(!it.validBookGrowthAtLevel(level = atLevel))
                output.add{
                    stringResource(
                        R.string.bookLevelReduction,
                        stringArrayResource(R.array.elementList)[magic.retrieveBooks().indexOf(it)]
                    )
                }
        }

        //catch book levels spent maximum exceeded
        if(!magic.legalMagLevelsSpentAtLevel(level = atLevel))
            output.add{stringResource(R.string.bookLevelsExceeded)}

        //catch empty free spell slots
        if(!magic.validFreeSpellsAtLevel(level = atLevel))
            output.add{stringResource(R.string.emptyFreeSpell)}

        //catch invalid psychic potential growth
        if(!psychic.validPsyPotentialGrowthAtLevel(level = atLevel))
            output.add{stringResource(R.string.psychicPotentialReduction)}

        //catch invalid psychic point growth
        if(!psychic.validPsyPointGrowthAtLevel(level = atLevel))
            output.add{stringResource(R.string.psychicPointReduction)}

        //catch invalid psychic projection growth
        if(!psychic.validPsyProjGrowthAtLevel(level = atLevel))
            output.add{stringResource(R.string.psychicProjectionReduction)}

        //catch invalid psychic projection value
        if(!psychic.getValidProjectionAtLevel(level = atLevel))
            output.add{stringResource(R.string.psychicProjectionMisuse)}

        //catch invalid psychic innate slot growth
        if(!psychic.validInnateSlotsAtLevel(level = atLevel))
            output.add{stringResource(R.string.psyInnateSlotReduction)}

        //catch invalid psychic points spent
        if(psychic.getFreePsyPointsAtLevel(level = atLevel) < 0)
            output.add{stringResource(R.string.overPsyPointFailure)}

        //catch all invalid psychic power enhancement growth
        psychic.findIllegalEnhancementAtLevel(level = atLevel).forEach{ power ->
            output.add{
                stringResource(
                    R.string.psyPowerEnhancementReduction,
                    stringArrayResource(R.array.powerNames)[power.name]
                )
            }
        }

        //catch all invalid summoning ability growth
        summoning.allSummoning().forEach{ability ->
            if(!(ability as SblSummonAbility).legalGrowthAtLevel(level = atLevel)){
                output.add{
                    stringResource(
                        R.string.summoningAbilityReduction,
                        stringResource(ability.stringRef)
                    )
                }
            }
        }

        return output
    }

    /**
     * Initialize the SBL character.
     *
     * @param sourceDIR home directory of the character's level files
     * @param secondaryFile location of custom secondary characteristic items
     * @param techFile location of custom dominion technique items
     */
    constructor(
        sourceDIR: File,
        secondaryFile: File,
        techFile: File
    ): this() {
        //apply custom secondaries
        secondaryList.applySecondaryChars(
            input = secondaryFile,
            filename = sourceDIR.name
        )

        //apply custom techniques
        ki.applyCustomTechs(
            customTechDir = techFile,
            filename = sourceDIR.name
        )

        //look through each file in the directory
        sourceDIR.listFiles()?.forEach{file ->
            //create a character based on that file data
            val levelChar = BaseCharacter(
                charFile = file,
                secondaryFile = secondaryFile,
                techFile = techFile,
                objectDB = objectDB,
                host = true
            )

            //set character at the indicated index
            charRefs[file.nameWithoutExtension.toInt()] = levelChar
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

        //run initializing steps
        charStartup()

        //set the level the character is starting at
        setLvl(levNum = startLevel())
    }

    fun charStartup(){
        //set name, exp, race, gender, appearance, and gnosis
        super.setName(charRefs[0]!!.charName.value)
        super.setOwnRace(charRefs[0]!!.ownRace.value)
        super.setGender(charRefs[0]!!.isMale.value)
        super.setAppearance(charRefs[0]!!.appearance.intValue)
        super.setGnosis(charRefs[0]!!.gnosis.intValue)

        //set initial class
        classes.setOwnClass(charRefs[0]!!.classes.ownClass.intValue)

        //set freelancer selection options
        if(firstFreelancer() >= 0) {
            for (index in 0..4) {
                classes.setSelection(
                    selectionIndex = index,
                    secondarySelection = charRefs[firstFreelancer()]!!.classes.freelancerSelection[index]
                )
            }
        }

        //set experience point lock state
        expLock.value = charRefs[0]!!.expLock.value

        //set primary items
        charRefs[0]!!.primaryList.allPrimaries().forEach{primary ->
            primaryList.allPrimaries()[primary.charIndex].setInput(primary.inputValue.intValue)
        }

        //set primary weapon
        weaponProficiencies.setPrimaryWeapon(charRefs[0]!!.weaponProficiencies.primaryWeapon.intValue)

        //set magic projection imbalance
        magic.setProjImbalance(charRefs[0]!!.magic.magProjImbalance.intValue)
        magic.imbalanceIsAttack.value = charRefs[0]!!.magic.imbalanceIsAttack.value

        //set advantages
        val advantageCopy = mutableListOf<Advantage>()
        advantageCopy.addAll(charRefs[0]!!.advantageRecord.takenAdvantages)

        advantageCopy.forEach{
            advantageRecord.acquireAdvantage(it, it.picked, it.pickedCost, it.multPicked)
        }

        //set currency maximums
        inventory.maxGold.intValue = charRefs[0]!!.inventory.maxGold.intValue
        inventory.maxSilver.intValue = charRefs[0]!!.inventory.maxSilver.intValue
        inventory.maxCopper.intValue = charRefs[0]!!.inventory.maxCopper.intValue

        //set inventory items
        charRefs[0]!!.inventory.boughtGoods.forEach{(item, amount) ->
            inventory.boughtGoods += Pair(item, amount)
        }

        //update spent coin values
        inventory.countSpent()
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

            //set to the indicated level for validation
            setLvl(index)

            //return index if all points in this level are not spent
            if(!levelChangeLegal(atLevel = index).isEmpty()) return index
        }

        //return final level option
        return 20
    }
}