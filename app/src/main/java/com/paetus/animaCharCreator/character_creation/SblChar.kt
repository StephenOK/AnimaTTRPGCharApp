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
import com.paetus.animaCharCreator.character_creation.attributes.magic.SblMagic
import com.paetus.animaCharCreator.character_creation.attributes.modules.SblProficiencies
import com.paetus.animaCharCreator.character_creation.attributes.primary_abilities.SblPrimaryChar
import com.paetus.animaCharCreator.character_creation.attributes.primary_abilities.SblPrimaryList
import com.paetus.animaCharCreator.character_creation.attributes.psychic.SblPsychic
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
     * Changes the level record to the inputted list.
     *
     * @param newRef list of character records to check now
     */
    fun updateReference(newRef: List<BaseCharacter?>){
        //for each new record item, overwrite the previous record item
        newRef.forEach{character ->
            charRefs[newRef.indexOf(character)] = character
        }
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
    }

    fun nonZeroReset(){
        //get the class at this level
        val prevClass = charRefs[lvl.intValue]!!.classes.ownClass.intValue

        //get any natural bonus applied at this level
        val secondaryIndex = getAddedSecondary()

        //run level reset
        resetLevel()

        //reapply previously held class
        charRefs[lvl.intValue]!!.classes.setOwnClass(prevClass)

        //check for changed class and remove, if necessary
        if(charRefs[lvl.intValue +1]!!.classes.ownClass.intValue != prevClass)
            classes.changeClasses(
                startLevel = lvl.intValue + 1,
                classIndex = prevClass
            )

        //remove any applied secondary natural bonus
        if(secondaryIndex != null)
            secondaryList.getAllSecondaries()[secondaryIndex].setNatBonus(false)
    }

    /**
     * Resets the current level record to an empty state.
     */
    fun resetLevel(){
        //replace current record with an empty record
        charRefs[lvl.intValue] =
            BaseCharacter(
                newHost = this,
                prevIndex = lvl.intValue - 1,
                isAdded = lvl.intValue != 0
            )

        //update all level based items
        setLvl(lvl.intValue)
    }

    /**
     * Determine which secondary bonus was added this level.
     *
     * @return the index of the added bonus, if one is present
     */
    fun getAddedSecondary(): Int?{
        //for each secondary item
        secondaryList.getAllSecondaries().forEach{
            it as SblSecondaryCharacteristic

            //return index if bonus at this level and not on a previous one
            if(getCharAtLevel().secondaryList.getAllSecondaries()[it.secondaryIndex].bonusApplied.value &&
                !charRefs[lvl.intValue - 1]!!.secondaryList.getAllSecondaries()[it.secondaryIndex].bonusApplied.value)
                return it.secondaryIndex
        }

        //return no bonus taken flag
        return null
    }

    /**
     * Determine that the character is allowed to change its level.
     *
     * @return list of error strings that would prevent a change in level
     */
    fun levelChangeLegal(): List<@Composable () -> String>{
        //initialize final result
        val output = mutableListOf<@Composable () -> String>()

        //determine if DP spent appropriately
        if(spentTotal.intValue != devPT.intValue)
            output.add{
                //add either indicator of overspent DP
                if (spentTotal.intValue > devPT.intValue) {
                    stringResource(R.string.overDpFailure)
                }
                //or indicator of underspent DP
                else {
                    stringResource(R.string.underDpFailure)
                }
            }

        //determine if combat max maintained
        if(ptInCombat.intValue > maxCombatDP.intValue)
            output.add{
                stringResource(
                    R.string.sectionCapBreach,
                    stringResource(R.string.combatLabel)
                )
            }

        //determine if magic max maintained
        if(ptInMag.intValue > maxMagDP.intValue)
            output.add{
                stringResource(
                    R.string.sectionCapBreach,
                    stringResource(R.string.magicLabel)
                )
            }

        //determine if psychic max maintained
        if(ptInPsy.intValue > maxPsyDP.intValue)
            output.add{
                stringResource(
                    R.string.sectionCapBreach,
                    stringResource(R.string.psychicLabel)
                )
            }

        //check each primary characteristic
        primaryList.allPrimaries().forEach{
            if(!(it as SblPrimaryChar).validGrowth())
                //if growth is not logical, notify of error in this stat
                output.add {
                    stringResource(
                        R.string.primaryBonusPointReduction,
                        stringArrayResource(id = R.array.primaryCharArray)[it.charIndex]
                    )
                }
        }

        //notify of too many primary bonus points added
        if(primaryList.getPrimaryBonusTotal() > lvl.intValue/2)
            output.add{stringResource(R.string.invalidPrimaryBonus)}

        //notify of illegal life multiple growth
        if(!combat.validLifeGrowth())
            output.add{stringResource(R.string.lifeMultReduction)}

        //notify of bad combat ability point distribution
        if(!combat.validAttackDodgeBlock())
            output.add{stringResource(id = R.string.combatPointMisuse)}

        //determine if points removed from combat items
        combat.allAbilities().forEach{
            if(!(it as SblCombatItem).validGrowth())
                output.add{
                    stringResource(
                        R.string.combatInputPointReduction,
                        stringResource(it.itemLabel)
                    )
                }
        }

        //determine that no points have been removed from secondary items
        secondaryList.getAllSecondaries().forEach{
            if(!(it as SblSecondaryCharacteristic).validGrowth())
                output.add{
                    stringResource(
                        R.string.secondaryInputPointReduction,
                        stringArrayResource(id = R.array.secondaryCharacteristics)[it.secondaryIndex]
                    )
                }

            //determine that the secondary item has a legal minimum input
            if(it.pointsApplied.intValue in 1..4)
                output.add{
                    stringResource(
                        R.string.secondaryInputTooFewPoints,
                        stringArrayResource(id = R.array.secondaryCharacteristics)[it.secondaryIndex]
                    )
                }
        }

        //determine that all natural bonuses have been distributed
        if(secondaryList.countNatBonuses() < lvl.intValue)
            output.add{
                stringResource(R.string.natBonusNotDistributed)
            }

        //look through each ki stat
        ki.allKiStats().forEach{kiStat ->
            //check for valid point growth
            if(!(kiStat as SblKiStat).validPointGrowth())
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
            if(!kiStat.validAccGrowth())
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
        if(!magic.validPointGrowth())
            output.add{stringResource(R.string.zeonPointReduction)}

        //catch invalid zeon accumulation growth
        if(!magic.validAccGrowth())
            output.add{stringResource(R.string.zeonAccReduction)}

        //catch invalid magic projection growth
        if(!magic.validProjGrowth())
            output.add{stringResource(R.string.zeonProjReduction)}

        //catch invalid book level growth
        magic.retrieveBooks().forEach{
            if(!it.validBookGrowth())
                output.add{
                    stringResource(
                        R.string.bookLevelReduction,
                        stringArrayResource(R.array.elementList)[magic.retrieveBooks().indexOf(it)]
                    )
                }
        }

        //catch book levels spent maximum exceeded
        if(!magic.legalMagLevels())
            output.add{stringResource(R.string.bookLevelsExceeded)}

        //catch empty free spell slots
        if(!magic.validFreeSpells())
            output.add{stringResource(R.string.emptyFreeSpell)}

        //catch invalid psychic potential growth
        if(!psychic.validPsyPotentialGrowth())
            output.add{stringResource(R.string.psychicPotentialReduction)}

        //catch invalid psychic point growth
        if(!psychic.validPsyPointGrowth())
            output.add{stringResource(R.string.psychicPointReduction)}

        //catch invalid psychic projection growth
        if(!psychic.validPsyProjGrowth())
            output.add{stringResource(R.string.psychicProjectionReduction)}

        //catch invalid psychic innate slot growth
        if(!psychic.validInnateSlots())
            output.add{stringResource(R.string.psyInnateSlotReduction)}

        //catch invalid psychic points spent
        if(psychic.getFreePsyPoints() < 0)
            output.add{stringResource(R.string.overPsyPointFailure)}

        //catch all invalid psychic power enhancement growth
        psychic.findIllegalEnhancement().forEach{power ->
            output.add{
                stringResource(
                    R.string.psyPowerEnhancementReduction,
                    stringArrayResource(R.array.powerNames)[power.name]
                )
            }
        }

        //catch all invalid summoning ability growth
        summoning.allSummoning().forEach{ability ->
            if(!(ability as SblSummonAbility).legalGrowth()){
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
        //look through each file in the directory
        sourceDIR.listFiles()?.forEach{file ->
            //create a character based on that file data
            val levelChar = BaseCharacter(
                charFile = file,
                secondaryFile = secondaryFile,
                techFile = techFile,
                objectDB = objectDB
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

    /**
     * Constructor for validation SblChar
     *
     * @param startLevel level to set this character at
     * @param reference character record to apply to the character
     */
    constructor(
        startLevel: Int,
        reference: List<BaseCharacter?>
    ): this(){
        //apply character record
        updateReference(newRef = reference)

        //initialize the character
        charStartup()

        //set the character to the indicated level
        setLvl(levNum = startLevel)
    }

    fun charStartup(){
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

        //set magic projection imbalance
        magic.setProjImbalance(charRefs[0]!!.magic.magProjImbalance.intValue)
        magic.imbalanceIsAttack.value = charRefs[0]!!.magic.imbalanceIsAttack.value

        //set advantages
        val advantageCopy = mutableListOf<Advantage>()
        advantageCopy.addAll(charRefs[0]!!.advantageRecord.takenAdvantages)

        advantageCopy.forEach{
            advantageRecord.acquireAdvantage(it, it.picked, it.pickedCost, it.multPicked)
        }

        //set inventory items
        charRefs[0]!!.inventory.boughtGoods.forEach{(item, amount) ->
            inventory.boughtGoods.plus(Pair(item, amount))
        }
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
            if(!levelChangeLegal().isEmpty()) return index
        }

        //return final level option
        return 20
    }
}