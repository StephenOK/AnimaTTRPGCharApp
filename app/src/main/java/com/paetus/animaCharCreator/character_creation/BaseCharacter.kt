package com.paetus.animaCharCreator.character_creation

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
import java.io.*
import java.nio.charset.StandardCharsets
import kotlin.Throws

/**
 * Character being built by the user
 * Holder class of all other character creation objects
 */
class BaseCharacter {
    //character's name
    val charName = mutableStateOf("")

    //character's current experience point amount
    val experiencePoints = mutableStateOf(0)

    //character's gender (default male)
    val isMale = mutableStateOf(true)

    //character's rule settings
    val rules = RuleRecord(this@BaseCharacter)

    //list of secondary abilities
    val primaryList = PrimaryList(this@BaseCharacter)
    val combat = CombatAbilities(this@BaseCharacter)
    val secondaryList = SecondaryList(this@BaseCharacter, primaryList)
    val weaponProficiencies = WeaponProficiencies(this@BaseCharacter)
    val ki = Ki(this@BaseCharacter)
    val magic = Magic(this@BaseCharacter)
    val summoning = Summoning(this@BaseCharacter)
    val psychic = Psychic(this@BaseCharacter)
    val advantageRecord = AdvantageRecord(this@BaseCharacter)
    val inventory = Inventory(this@BaseCharacter)

    //list of all classes available
    val classes = ClassInstances(this@BaseCharacter)

    //list of all race advantages
    val races = RaceAdvantages(this@BaseCharacter)

    //set default class to one with empty onTake and onRemove functions
    val ownClass = mutableStateOf(classes.mentalist)

    //initialize character's race
    val ownRace = mutableStateOf<List<RacialAdvantage>>(listOf())

    //character's level
    val lvl = mutableStateOf(0)

    //character development points
    val devPT = mutableStateOf(0)
    val spentTotal = mutableStateOf(0)

    //maximum point allotments to combat, magic, and psychic abilities
    val maxCombatDP = mutableStateOf(0)
    private val percCombatDP = mutableStateOf(0.0)
    val ptInCombat = mutableStateOf(0)

    val maxMagDP = mutableStateOf(0)
    private val percMagDP = mutableStateOf(0.0)
    val ptInMag = mutableStateOf(0)

    val maxPsyDP = mutableStateOf(0)
    private val percPsyDP = mutableStateOf(0.0)
    val ptInPsy = mutableStateOf(0)

    //character size items
    val sizeSpecial = mutableStateOf(0)
    val sizeCategory = mutableStateOf(0)

    //character's appearance
    val appearance = mutableStateOf(5)

    //character's movement value
    val movement = mutableStateOf(1)

    //character's weight index
    val weightIndex = mutableStateOf(1)

    //character's gnosis value
    val gnosis = mutableStateOf(0)

    /**
     * Changes the character's gender depending on the input
     *
     * @param input true if male, false if female
     */
    fun setGender(input: Boolean){
        //remove previous buff if character is a duk'zarist
        if(ownRace.value == races.dukzaristAdvantages){
            if(isMale.value) combat.physicalRes.setSpecial(-5)
            else combat.magicRes.setSpecial(-5)
        }

        //set desired input
        isMale.value = input

        //apply gendered buff if character is a duk'zarist
        if(ownRace.value == races.dukzaristAdvantages){
            if(isMale.value) combat.physicalRes.setSpecial(5)
            else combat.magicRes.setSpecial(5)
        }
    }

    /**
     * Setter for class with class input.
     */
    @JvmName("setOwnClass1")
    fun setOwnClass(input: CharClass){
        //undo current class buffs
        ownClass.value.onRemove()

        //change class and apply new buffs
        ownClass.value = input
        ownClass.value.onTake()

        updateClassInputs()
    }

    fun setOwnClass(input: String){
        //undo current class buffs
        ownClass.value.onRemove()

        //find new class and apply new buffs
        classes.allClasses.forEach{
            if(it.saveName == input){
                ownClass.value = it
                ownClass.value.onTake()
                return@forEach
            }
        }

        updateClassInputs()
    }

    /**
     * Setter for class with Integer input.
     */
    fun setOwnClass(classInt: Int){
        //undo current class buffs
        ownClass.value.onRemove()

        //changes class and apply new buffs
        ownClass.value = classes.allClasses[classInt]
        ownClass.value.onTake()

        updateClassInputs()
    }

    /**
     * Updates class values when the character's class changes.
     */
    fun updateClassInputs(){
        //update class life points
        combat.updateClassLife()

        //update initiative bonus
        combat.updateInitiative()

        //update character's maximum point values
        percCombatDP.value = ownClass.value.combatMax
        percMagDP.value = ownClass.value.magMax
        percPsyDP.value = ownClass.value.psyMax
        dpAllotmentCalc()

        //update secondary bonuses
        secondaryList.classUpdate(ownClass.value)

        //update character's martial knowledge
        ki.updateMK()

        //update innate psychic points
        psychic.setInnatePsy()

        //update all spent value totals
        updateTotalSpent()
    }

    /**
     * Setter for race with RaceName input.
     */
    @JvmName("setOwnRace1")
    fun setOwnRace(raceIn: List<RacialAdvantage>) {
        //remove previous race buffs
        removeRaceAdvantages()

        //apply new race and buffs
        ownRace.value = raceIn
        applyRaceAdvantages()
    }

    /**
     * Setter for race with String input.
     */
    fun setOwnRace(raceName: String) {
        //remove previous race buffs
        removeRaceAdvantages()

        //apply new race and buffs
        ownRace.value = races.getFromString(raceName)
        applyRaceAdvantages()
    }

    /**
     * Setter for race with Integer input.
     */
    fun setOwnRace(raceNum: Int){
        //remove previous race buffs
        removeRaceAdvantages()

        //apply new race and buffs
        ownRace.value = races.allAdvantageLists[raceNum]
        applyRaceAdvantages()
    }

    /**
     * Apply the advantages of the character's current race.
     */
    fun applyRaceAdvantages(){
        ownRace.value.forEach{
            //if the advantage has an onTake effect, run it
            if(it.onTake != null)
                it.onTake!!(it.picked, it.cost[it.pickedCost])
        }
    }

    /**
     * Remove the advantages of the character's current race.
     */
    fun removeRaceAdvantages(){
        ownRace.value.forEach{
            //if the advantage has an onRemove effect, run it
            if(it.onRemove != null)
                it.onRemove!!(it.picked, it.cost[it.pickedCost])
        }
    }

    /**
     * Updates the character's level and any associated values.
     */
    @JvmName("setLvl1")
    fun setLvl(levNum: Int) {
        //set new level number
        lvl.value = levNum

        //determine development point count
        devPT.value =
            if(lvl.value == 0) 400
            else 500 + lvl.value * 100

        //refactor the character's presence
        combat.updatePresence()

        //recalculate maximum DP allotments
        dpAllotmentCalc()

        //recalculate life points
        combat.updateClassLife()

        //recalculate the other combat abilities
        combat.allAbilities.forEach{
            it.updateClassTotal()
        }

        //recalculate initiative
        combat.updateInitiative()

        //recalculate martial knowledge
        ki.updateMK()

        //recalculate maximum zeon
        magic.updateZeonFromClass()

        //recalculate summoning abilities
        summoning.allSummoning.forEach{it.updateLevelTotal()}

        //recalculate psychic points available
        psychic.setInnatePsy()

        //recalculate character's secondary ability values
        secondaryList.fullList.forEach{it.classTotalRefresh()}
    }

    /**
     * Calculates percentage allotments for each category.
     */
    private fun dpAllotmentCalc() {
        maxCombatDP.value = (devPT.value * percCombatDP.value).toInt()
        maxMagDP.value = (devPT.value * percMagDP.value).toInt()
        maxPsyDP.value = (devPT.value * percPsyDP.value).toInt()
    }

    /**
     * Updates the total development points spent.
     */
    fun updateTotalSpent(){
        //make sure martial arts taken are still legal
        weaponProficiencies.doubleCheck()

        //update the individual expenditure values
        updateCombatSpent()
        updateMagicSpent()
        updatePsychicSpent()

        //update the total expenditure
        spentTotal.value = combat.lifeMultsTaken.value * ownClass.value.lifePointMultiple +
                secondaryList.calculateSpent() + ptInCombat.value + ptInMag.value + ptInPsy.value
    }

    /**
     * Updates the total development points spent in combat abilities.
     * Get by calculating the values spent in the combat, module, and ki sections.
     */
    private fun updateCombatSpent(){
        ptInCombat.value =
            combat.calculateSpent() +
            weaponProficiencies.calculateSpent() +
            ki.calculateSpent()
    }

    /**
     * Updates the total development points spent in magic abilities.
     * Get by calculating the values spent in the magic and summoning sections.
     */
    private fun updateMagicSpent(){
        ptInMag.value = magic.calculateSpent() + summoning.calculateSpent() + weaponProficiencies.calcPointsInMag()
    }

    /**
     * Updates the total development points spent in psychic abilities.
     */
    private fun updatePsychicSpent(){
        ptInPsy.value = psychic.calculateSpent() + weaponProficiencies.calcPointsInPsy()
    }

    /**
     * Changes the character's size category special based on the inputted value.
     *
     * @param input index of the change to run
     */
    fun changeSize(input: Int){
        //run the indicated change to the size special value
        when(input){
            0 -> sizeSpecial.value -= 5
            1 -> sizeSpecial.value -= 4
            2 -> sizeSpecial.value -= 3
            3 -> sizeSpecial.value -= 2
            4 -> sizeSpecial.value -= 1
            5 -> sizeSpecial.value += 1
            6 -> sizeSpecial.value += 2
            7 -> sizeSpecial.value += 3
            8 -> sizeSpecial.value += 4
            9 -> sizeSpecial.value += 5
        }

        //update the character's total size
        updateSize()
    }

    /**
     * Updates the character's total size category.
     */
    fun updateSize(){
        sizeCategory.value = primaryList.str.total.value + primaryList.con.total.value + sizeSpecial.value
    }

    /**
     * Sets the character's appearance to the inputted item, if able.
     *
     * @param input appearance value to potentially apply
     */
    @JvmName("setAppearance1")
    fun setAppearance(input: Int){
        //set appearance to 2 if character has unattractive disadvantage
        if(advantageRecord.getAdvantage("unattractive") != null)
            appearance.value = 2

        //only apply appearance if character is either not a dan'jayni or if it is a legal value for that race
        else if(ownRace.value != races.danjayniAdvantages || input in 3..7)
            appearance.value = input
    }

    /**
     * Sets the character's movement distance depending on the inputted value.
     *
     * @param input total agility score the character possesses
     */
    @JvmName("setMovement1")
    fun setMovement(input: Int){
        movement.value = when(input){
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

    @JvmName("setWeightIndex1")
    fun setWeightIndex(input: Int){
        weightIndex.value = when(input){
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


    /**
     * Default constructor for new character.
     */
    constructor() {
        //set default values for class, race, and level
        setOwnClass(classes.freelancer)
        setOwnRace(listOf())
        setLvl(0)

        //set default values for primary characteristics
        primaryList.str.setInput(5)
        primaryList.dex.setInput(5)
        primaryList.agi.setInput(5)
        primaryList.con.setInput(5)
        primaryList.int.setInput(5)
        primaryList.pow.setInput(5)
        primaryList.wp.setInput(5)
        primaryList.per.setInput(5)
    }

    /**
     * Constructor for character with file association.
     *
     * @param fileInput file to load the character data from
     */
    constructor(fileInput: File?) {
        //initialize file input reader
        val restoreChar = FileInputStream(fileInput)
        val readChar = InputStreamReader(restoreChar, StandardCharsets.UTF_8)
        val fileReader = BufferedReader(readChar)

        val version = fileReader.readLine().toInt()

        rules.loadRules(fileReader)

        //get the character's name
        charName.value = fileReader.readLine()

        //get the character's experience point amount
        experiencePoints.value = fileReader.readLine().toInt()

        //get the character's gender
        setGender(fileReader.readLine().toBoolean())

        //get the character's class, race, and level
        setOwnClass(fileReader.readLine())
        setOwnRace(fileReader.readLine())
        setLvl(fileReader.readLine().toInt())

        //load paladin's chosen level boon
        classes.loadClassData(fileReader)

        //load character's primary abilities
        primaryList.loadPrimaries(fileReader)

        //load character's combat abilities
        combat.loadCombat(fileReader)

        //load character's appearance
        setAppearance(fileReader.readLine().toInt())

        //load character's secondary abilities
        secondaryList.loadList(fileReader)

        //load character's modules
        weaponProficiencies.loadProficiencies(fileReader)

        //load character's ki abilities
        ki.loadKiAttributes(fileReader, version)

        //load character's magic abilities
        magic.loadMagic(fileReader)

        //load character's summoning abilities
        summoning.loadSummoning(fileReader)

        //load character's advantage record
        advantageRecord.loadAdvantages(fileReader)

        //load character's psychic abilities
        psychic.loadPsychic(fileReader)

        //load character's inventory
        inventory.loadInventory(fileReader)

        //end file reading
        restoreChar.close()

        //update character's development point expenditure
        updateTotalSpent()
    }

    private lateinit var byteArray: ByteArrayOutputStream

    /**
     * Retrieve byte information for the character.
     */
    @get:Throws(IOException::class)
    val bytes: ByteArray
        get() {
            //initialize byte stream
            byteArray = ByteArrayOutputStream()

            addNewData(BuildConfig.VERSION_CODE)

            rules.writeRules()

            //add name data
            addNewData(charName.value)

            //add experience point data
            addNewData(experiencePoints.value)

            //add gender data
            addNewData(isMale.value)

            //add class, race, and level data
            addNewData(ownClass.value.saveName)
            addNewData(races.getNameOfList(ownRace.value))
            addNewData(lvl.value)

            //write paladin's chosen level boon
            classes.writeClassData()

            //write primary characteristic data
            primaryList.writePrimaries()

            //write combat item data
            combat.writeCombat()

            //write appearance data
            addNewData(appearance.value)

            //write secondary characteristic data
            secondaryList.writeList()

            //write module data
            weaponProficiencies.writeProficiencies()

            //write ki ability data
            ki.writeKiAttributes()

            //write magic data
            magic.writeMagic()

            //write summoning ability data
            summoning.writeSummoning()

            //write advantage data
            advantageRecord.writeAdvantages()

            //write psychic data
            psychic.writePsychic()

            //write inventory data
            inventory.writeInventory()

            //end writing data
            byteArray.close()

            //convert stream data to byte array
            return byteArray.toByteArray()
        }

    /**
     * Adds new String data to the ByteOutputStream.
     *
     * @param toAdd string data to add to the byte array
     */
    fun addNewData(toAdd: String?) {
        byteArray.write(
            """$toAdd""".toByteArray(StandardCharsets.UTF_8),
            0,
            """$toAdd""".toByteArray(StandardCharsets.UTF_8).size
        )

        writeEndLine()
    }

    /**
     * Adds new Int data to the output stream.
     *
     * @param toAdd integer data to add to the byte array
     */
    fun addNewData(toAdd: Int?) {
        byteArray.write(
            """$toAdd""".toByteArray(StandardCharsets.UTF_8),
            0,
            """$toAdd""".toByteArray(StandardCharsets.UTF_8).size
        )

        writeEndLine()
    }

    /**
     * Adds new Double data to the output stream.
     *
     * @param toAdd double data to add to the byte array
     */
    fun addNewData(toAdd: Double?){
        byteArray.write(
            """$toAdd""".toByteArray(StandardCharsets.UTF_8),
            0,
            """$toAdd""".toByteArray(StandardCharsets.UTF_8).size
        )

        writeEndLine()
    }

    /**
     * Adds new Boolean data to the output stream.
     *
     * @param toAdd boolean data to add to the stream
     */
    fun addNewData(toAdd: Boolean?){
        byteArray.write(
            """$toAdd""".toByteArray(StandardCharsets.UTF_8),
            0,
            """$toAdd""".toByteArray(StandardCharsets.UTF_8).size
        )

        writeEndLine()
    }

    /**
     * Writes a new line character to separate data from other data.
     */
    fun writeEndLine(){
        byteArray.write(
            "\n".toByteArray(StandardCharsets.UTF_8),
            0,
            "\n".toByteArray(StandardCharsets.UTF_8).size)
    }
}