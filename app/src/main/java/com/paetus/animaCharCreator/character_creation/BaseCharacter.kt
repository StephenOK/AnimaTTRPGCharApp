package com.paetus.animaCharCreator.character_creation

import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.paetus.animaCharCreator.BuildConfig
import com.paetus.animaCharCreator.character_creation.attributes.advantages.AdvantageRecord
import com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_items.RaceAdvantages
import com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_types.RacialAdvantage
import com.paetus.animaCharCreator.character_creation.attributes.class_objects.CharClass
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.Ki
import com.paetus.animaCharCreator.character_creation.attributes.secondary_abilities.SecondaryList
import com.paetus.animaCharCreator.character_creation.attributes.class_objects.ClassInstances
import com.paetus.animaCharCreator.character_creation.attributes.combat.CombatAbilities
import com.paetus.animaCharCreator.character_creation.attributes.magic.Magic
import com.paetus.animaCharCreator.character_creation.attributes.psychic.Psychic
import com.paetus.animaCharCreator.character_creation.attributes.summoning.Summoning
import com.paetus.animaCharCreator.character_creation.attributes.modules.WeaponProficiencies
import com.paetus.animaCharCreator.character_creation.attributes.primary_abilities.PrimaryList
import com.paetus.animaCharCreator.character_creation.equipment.Inventory
import com.paetus.animaCharCreator.writeDataTo
import java.io.*
import java.nio.charset.StandardCharsets

/**
 * Character being built by the user
 * Holder class of all other character creation objects
 */
class BaseCharacter {
    //character's name
    val charName = mutableStateOf(value = "")

    //character's current experience point amount
    val experiencePoints = mutableIntStateOf(value = 0)

    //character's gender (default male)
    val isMale = mutableStateOf(value = true)

    //character's rule settings
    val rules = RuleRecord()

    //list of secondary abilities
    val primaryList = PrimaryList(charInstance = this)
    val combat = CombatAbilities(charInstance = this)
    val secondaryList = SecondaryList(charInstance = this, primaryList = primaryList)
    val weaponProficiencies = WeaponProficiencies(charInstance = this)
    val ki = Ki(charInstance = this)
    val magic = Magic(charInstance = this)
    val summoning = Summoning(charInstance = this)
    val psychic = Psychic(charInstance = this)
    val advantageRecord = AdvantageRecord(charInstance = this)
    val inventory = Inventory(charInstance = this)

    //list of all classes available
    val classes = ClassInstances(charInstance = this)

    //list of all race advantages
    val races = RaceAdvantages(charInstance = this)

    //set default class to one with empty onTake and onRemove functions
    val ownClass = mutableStateOf(value = classes.mentalist)

    //initialize character's race
    val ownRace = mutableStateOf<List<RacialAdvantage>>(value = listOf())

    //character's level
    val lvl = mutableIntStateOf(value = 0)

    //character development points
    val devPT = mutableIntStateOf(value = 0)
    val spentTotal = mutableIntStateOf(value = 0)

    //maximum point allotments to combat, magic, and psychic abilities
    val maxCombatDP = mutableIntStateOf(value = 0)
    private val percCombatDP = mutableDoubleStateOf(value = 0.0)
    val ptInCombat = mutableIntStateOf(value = 0)

    val maxMagDP = mutableIntStateOf(value = 0)
    private val percMagDP = mutableDoubleStateOf(value = 0.0)
    val ptInMag = mutableIntStateOf(value = 0)

    val maxPsyDP = mutableIntStateOf(value = 0)
    private val percPsyDP = mutableDoubleStateOf(value = 0.0)
    val ptInPsy = mutableIntStateOf(value = 0)

    //character size items
    val sizeSpecial = mutableIntStateOf(value = 0)
    val sizeCategory = mutableIntStateOf(value = 0)

    //character's appearance
    val appearance = mutableIntStateOf(value = 5)

    //character's movement value
    val movement = mutableIntStateOf(value = 1)

    //character's weight index
    val weightIndex = mutableIntStateOf(value = 1)

    //character's gnosis value
    val gnosis = mutableIntStateOf(value = 0)

    /**
     * Changes the character's gender depending on the input
     *
     * @param gender true if male, false if female
     */
    fun setGender(gender: Boolean){
        //remove previous buff if character is a duk'zarist
        if(ownRace.value == races.dukzaristAdvantages){
            if(isMale.value) combat.physicalRes.setSpecial(specChange = -5)
            else combat.magicRes.setSpecial(specChange = -5)
        }

        //set desired input
        isMale.value = gender

        //apply gendered buff if character is a duk'zarist
        if(ownRace.value == races.dukzaristAdvantages){
            if(isMale.value) combat.physicalRes.setSpecial(specChange = 5)
            else combat.magicRes.setSpecial(specChange = 5)
        }
    }

    /**
     * Setter for class with class input.
     *
     * @param newClass new class to set for this character
     */
    private fun setOwnClass(newClass: CharClass){
        //undo current class buffs
        ownClass.value.onRemove()

        //change class and apply new buffs
        ownClass.value = newClass
        ownClass.value.onTake()

        //update class life points
        combat.updateClassLife()

        //update initiative bonus
        combat.updateInitiative()

        //update character's maximum point values
        percCombatDP.doubleValue = ownClass.value.combatMax
        percMagDP.doubleValue = ownClass.value.magMax
        percPsyDP.doubleValue = ownClass.value.psyMax
        dpAllotmentCalc()

        //update secondary bonuses
        secondaryList.classUpdate(newClass = ownClass.value)

        //update character's martial knowledge
        ki.updateMK()

        //update innate psychic points
        psychic.setInnatePsy()

        //update all spent value totals
        updateTotalSpent()
    }

    /**
     * Setter for class with a string input.
     *
     * @param classString string to be converted into a class the character will take
     */
    private fun setOwnClass(classString: String){
        //search through all class objects
        classes.allClasses.forEach{charClass ->
            //apply class if match found
            if(charClass.saveName == classString){
                setOwnClass(charClass)
                return@forEach
            }
        }
    }

    /**
     * Setter for class with Integer input.
     *
     * @param classInt number that references the class the character will take
     */
    fun setOwnClass(classInt: Int){
        //apply the class indicated by the number
        setOwnClass(classes.allClasses[classInt])
    }

    /**
     * Sets the character's race to the inputted item.
     *
     * @param raceIn new race to set the character to
     */
    private fun setOwnRace(raceIn: List<RacialAdvantage>) {
        //remove previous race buffs
        ownRace.value.forEach{advantage ->
            //if the advantage has an onRemove effect, run it
            if(advantage.onRemove != null)
                advantage.onRemove!!(advantage.picked, advantage.cost[advantage.pickedCost])
        }

        //apply new race and buffs
        ownRace.value = raceIn

        ownRace.value.forEach{advantage ->
            //if the advantage has an onTake effect, run it
            if(advantage.onTake != null)
                advantage.onTake!!(advantage.picked, advantage.cost[advantage.pickedCost])
        }
    }

    /**
     * Sets the character's race to the one indicated by the inputted string.
     *
     * @param raceName string to convert to the applied race
     */
    private fun setOwnRace(raceName: String) {
        //set the character's race to the indicated item
        setOwnRace(races.getFromString(raceName))
    }

    /**
     * Sets the character's race to the value indicated by the inputted integer.
     *
     * @param raceNum index of the desired race to apply
     */
    fun setOwnRace(raceNum: Int){
        //apply the indicated race to the character
        setOwnRace(races.allAdvantageLists[raceNum])
    }

    /**
     * Updates the character's level and any associated values.
     *
     * @param levNum value to set the character's level to
     */
    fun setLvl(levNum: Int) {
        //set new level number
        lvl.intValue = levNum

        //determine development point count
        devPT.intValue =
            if(lvl.intValue == 0) 400
            else 500 + lvl.intValue * 100

        //refactor the character's presence
        combat.updatePresence()

        //recalculate maximum DP allotments
        dpAllotmentCalc()

        //recalculate life points
        combat.updateClassLife()

        //recalculate the other combat abilities
        combat.allAbilities.forEach{combatItem ->
            combatItem.updateClassTotal()
        }

        //recalculate initiative
        combat.updateInitiative()

        //recalculate martial knowledge
        ki.updateMK()

        //recalculate maximum zeon
        magic.updateZeonFromClass()

        //recalculate summoning abilities
        summoning.allSummoning.forEach{summonItem ->
            summonItem.updateLevelTotal()
        }

        //recalculate psychic points available
        psychic.setInnatePsy()

        //recalculate character's secondary ability values
        secondaryList.getAllSecondaries().forEach{secondaryItem ->
            secondaryItem.classTotalRefresh()
        }
    }

    /**
     * Calculates percentage allotments for each category.
     */
    private fun dpAllotmentCalc() {
        maxCombatDP.intValue = (devPT.intValue * percCombatDP.doubleValue).toInt()
        maxMagDP.intValue = (devPT.intValue * percMagDP.doubleValue).toInt()
        maxPsyDP.intValue = (devPT.intValue * percPsyDP.doubleValue).toInt()
    }

    /**
     * Updates the total development points spent.
     */
    fun updateTotalSpent(){
        //make sure martial arts taken are still legal
        weaponProficiencies.doubleCheck()

        //update the points spent in the combat section
        ptInCombat.intValue =
            combat.calculateSpent() +
                    weaponProficiencies.calculateSpent() +
                    ki.calculateSpent()

        //update the points spent in the magic section
        ptInMag.intValue =
            magic.calculateSpent() +
                    summoning.calculateSpent() +
                    weaponProficiencies.calcPointsInMag()

        //update the points spent in the psychic section
        ptInPsy.intValue =
            psychic.calculateSpent() +
                    weaponProficiencies.calcPointsInPsy()

        //update the total expenditure
        spentTotal.intValue = combat.lifeMultsTaken.intValue * ownClass.value.lifePointMultiple +
                secondaryList.calculateSpent() + ptInCombat.intValue + ptInMag.intValue + ptInPsy.intValue
    }

    /**
     * Changes the character's size category special based on the inputted value.
     *
     * @param sizeInput index of the change to run
     */
    fun changeSize(sizeInput: Int){
        //run the indicated change to the size special value
        when(sizeInput){
            0 -> sizeSpecial.intValue -= 5
            1 -> sizeSpecial.intValue -= 4
            2 -> sizeSpecial.intValue -= 3
            3 -> sizeSpecial.intValue -= 2
            4 -> sizeSpecial.intValue -= 1
            5 -> sizeSpecial.intValue += 1
            6 -> sizeSpecial.intValue += 2
            7 -> sizeSpecial.intValue += 3
            8 -> sizeSpecial.intValue += 4
            9 -> sizeSpecial.intValue += 5
        }

        //update the character's total size
        updateSize()
    }

    /**
     * Updates the character's total size category.
     */
    fun updateSize(){
        sizeCategory.intValue = primaryList.str.total.intValue + primaryList.con.total.intValue + sizeSpecial.intValue
    }

    /**
     * Sets the character's appearance to the inputted item, if able.
     *
     * @param newAppearance value to attempt to set the character's appearance to
     */
    fun setAppearance(newAppearance: Int){
        //set appearance to 2 if character has unattractive disadvantage
        if(advantageRecord.getAdvantage(advantageString = "unattractive") != null)
            appearance.intValue = 2

        //only apply appearance if character is either not a dan'jayni or if it is a legal value for that race
        else if(ownRace.value != races.danjayniAdvantages || newAppearance in 3..7)
            appearance.intValue = newAppearance
    }

    /**
     * Sets the character's movement distance depending on the inputted value.
     *
     * @param moveValue total agility score the character possesses
     */
    fun setMovement(moveValue: Int){
        movement.intValue = when(moveValue){
            1 -> 1
            2 -> 4
            3 -> 8
            4 -> 15
            5 -> 20
            6 -> 22
            7 -> 25
            8 -> 28
            9 -> 32
            10 -> 35
            11 -> 40
            12 -> 50
            13 -> 80
            14 -> 150
            15 -> 250
            16 -> 500
            17 -> 1000
            18 -> 5000
            19 -> 25000
            else -> 0
        }
    }

    /**
     * Sets the weight index for the character.
     *
     * @param weightValue total strength score the character possesses
     */
    fun setWeightIndex(weightValue: Int){
        weightIndex.intValue = when(weightValue){
            1 -> 1
            2 -> 10
            3 -> 20
            4 -> 40
            5 -> 60
            6 -> 120
            7 -> 180
            8 -> 260
            9 -> 350
            10 -> 420
            11 -> 600
            12 -> 1
            13 -> 3
            14 -> 25
            15 -> 100
            16 -> 500
            17 -> 2500
            18 -> 10000
            19 -> 150000
            else -> 0
        }
    }

    constructor(){
        createDefaultChar()
    }

    /**
     * Default constructor for new character.
     *
     * @param filename file associated with this character
     * @param secondaryFile directory for custom secondary characteristics
     * @param techFile directory for custom dominion techniques
     */
    constructor(
        filename: String,
        secondaryFile: File,
        techFile: File
    ) {
        //retrieve custom secondaries for this character
        secondaryList.applySecondaryChars(input = secondaryFile, filename = filename)

        //retrieve custom techniques for this character
        ki.applyCustomTechs(customTechDir = techFile, filename = filename)

        //construct the BaseCharacter object
        createDefaultChar()
    }

    private fun createDefaultChar(){
        //set default values for class, race, and level
        setOwnClass(newClass = classes.freelancer)
        setOwnRace(raceIn = listOf())
        setLvl(levNum = 0)

        //set default values for primary characteristics
        primaryList.str.setInput(baseIn = 5)
        primaryList.dex.setInput(baseIn = 5)
        primaryList.agi.setInput(baseIn = 5)
        primaryList.con.setInput(baseIn = 5)
        primaryList.int.setInput(baseIn = 5)
        primaryList.pow.setInput(baseIn = 5)
        primaryList.wp.setInput(baseIn = 5)
        primaryList.per.setInput(baseIn = 5)
    }

    /**
     * Constructor for character with file association.
     *
     * @param charFile file to load the character data from
     * @param secondaryFile directory for the custom secondary items
     * @param techFile directory for the custom technique items
     */
    constructor(
        charFile: File,
        secondaryFile: File,
        techFile: File
    ) {

        secondaryList.applySecondaryChars(input = secondaryFile, filename = charFile.name)
        ki.applyCustomTechs(customTechDir = techFile, filename = charFile.name)

        //initialize file input reader
        val restoreChar = FileInputStream(charFile)
        val readChar = InputStreamReader(restoreChar, StandardCharsets.UTF_8)
        val fileReader = BufferedReader(readChar)

        //get the version of the app for this save file
        val version = fileReader.readLine().toInt()

        //get the rules applied to this character
        rules.loadRules(fileReader = fileReader)

        //get the character's name
        charName.value = fileReader.readLine()

        //get the character's experience point amount
        experiencePoints.intValue = fileReader.readLine().toInt()

        //get the character's gender
        setGender(gender = fileReader.readLine().toBoolean())

        //get the character's class, race, and level
        setOwnClass(classString = fileReader.readLine())
        setOwnRace(raceName = fileReader.readLine())
        setLvl(levNum = fileReader.readLine().toInt())

        //load paladin's chosen level boon
        classes.loadClassData(fileReader = fileReader)

        //load character's primary abilities
        primaryList.loadPrimaries(fileReader = fileReader)

        //load character's combat abilities
        combat.loadCombat(fileReader = fileReader)

        //load character's appearance
        setAppearance(newAppearance = fileReader.readLine().toInt())

        //load character's secondary abilities
        secondaryList.loadList(fileReader = fileReader, writeVersion = version)

        //load character's combat modules
        weaponProficiencies.loadProficiencies(fileReader = fileReader)

        //load character's ki abilities
        ki.loadKiAttributes(
            fileReader = fileReader,
            writeVersion = version,
            filename = charFile.name
        )

        //load character's magic abilities
        magic.loadMagic(fileReader = fileReader)

        //load character's summoning abilities
        summoning.loadSummoning(fileReader = fileReader)

        //load character's advantage record
        advantageRecord.loadAdvantages(fileReader = fileReader, version = version)

        //load character's psychic abilities
        psychic.loadPsychic(fileReader = fileReader)

        //load character's inventory
        inventory.loadInventory(fileReader = fileReader)

        //end file reading
        restoreChar.close()

        //update character's development point expenditure
        updateTotalSpent()
    }

    /**
     * Retrieve byte information for the character.
     */
    val bytes: ByteArray
        get() {
            //initialize byte stream
            val byteArray = ByteArrayOutputStream()

            //add the app version
            writeDataTo(writer = byteArray, input = BuildConfig.VERSION_CODE)

            //add the rules settings
            rules.writeRules(byteArray = byteArray)

            //add name data
            writeDataTo(writer = byteArray, input = charName.value)

            //add experience point data
            writeDataTo(writer = byteArray, input = experiencePoints.intValue)

            //add gender data
            writeDataTo(writer = byteArray, input = isMale.value)

            //add class, race, and level data
            writeDataTo(writer = byteArray, input = ownClass.value.saveName)
            writeDataTo(writer = byteArray, input = races.getNameOfList(ownRace.value))
            writeDataTo(writer = byteArray, input = lvl.intValue)

            //write paladin's chosen level boon
            classes.writeClassData(byteArray = byteArray)

            //write primary characteristic data
            primaryList.writePrimaries(byteArray = byteArray)

            //write combat item data
            combat.writeCombat(byteArray = byteArray)

            //write appearance data
            writeDataTo(writer = byteArray, input = appearance.intValue)

            //write secondary characteristic data
            secondaryList.writeList(byteArray = byteArray)

            //write module data
            weaponProficiencies.writeProficiencies(byteArray = byteArray)

            //write ki ability data
            ki.writeKiAttributes(byteArray = byteArray)

            //write magic data
            magic.writeMagic(byteArray = byteArray)

            //write summoning ability data
            summoning.writeSummoning(byteArray = byteArray)

            //write advantage data
            advantageRecord.writeAdvantages(byteArray = byteArray)

            //write psychic data
            psychic.writePsychic(byteArray = byteArray)

            //write inventory data
            inventory.writeInventory(byteArray = byteArray)

            //end writing data
            byteArray.close()

            //convert stream data to byte array
            return byteArray.toByteArray()
        }
}