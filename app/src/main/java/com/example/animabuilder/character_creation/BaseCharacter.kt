package com.example.animabuilder.character_creation

import com.example.animabuilder.character_creation.attributes.advantages.AdvantageRecord
import com.example.animabuilder.character_creation.attributes.ki_abilities.Ki
import com.example.animabuilder.serializables.SerialOutputStream
import com.example.animabuilder.character_creation.attributes.secondary_abilities.SecondaryList
import com.example.animabuilder.character_creation.attributes.class_objects.ClassInstances
import com.example.animabuilder.character_creation.attributes.race_objects.CharRace
import com.example.animabuilder.character_creation.attributes.class_objects.ClassName
import com.example.animabuilder.character_creation.attributes.combat.CombatAbilities
import com.example.animabuilder.character_creation.attributes.race_objects.RaceName
import com.example.animabuilder.character_creation.attributes.magic.Magic
import com.example.animabuilder.character_creation.attributes.psychic.Psychic
import com.example.animabuilder.character_creation.attributes.summoning.Summoning
import com.example.animabuilder.character_creation.attributes.modules.WeaponProficiencies
import com.example.animabuilder.character_creation.attributes.primary_abilities.PrimaryList
import java.io.*
import java.nio.charset.StandardCharsets
import kotlin.Throws

/**
 * Character being built by the user
 * Holder class of all other character creation objects
 */

class BaseCharacter: Serializable {
    //character's name
    var charName = ""

    var isMale = true

    //list of secondary abilities
    val primaryList = PrimaryList(this@BaseCharacter)
    val combat = CombatAbilities(this@BaseCharacter)
    val secondaryList = SecondaryList(this@BaseCharacter)
    val weaponProficiencies = WeaponProficiencies(this@BaseCharacter)
    val ki = Ki(this@BaseCharacter)
    val magic = Magic(this@BaseCharacter)
    val summoning = Summoning(this@BaseCharacter)
    val psychic = Psychic(this@BaseCharacter)
    val advantageRecord = AdvantageRecord(this@BaseCharacter)

    val classes = ClassInstances(this@BaseCharacter)
    var ownClass = classes.mentalist

    //initialize character's class and race
    var ownRace = CharRace(RaceName.human, this@BaseCharacter)

    //character's level
    var lvl = 0

    //character development points
    var devPT = 0
    var spentTotal = 0

    //maximum point allotments to combat, magic, and psychic abilities
    var maxCombatDP = 0
    var percCombatDP = 0.0
    var ptInCombat = 0

    var maxMagDP = 0
    var percMagDP = 0.0
    var ptInMag = 0

    var maxPsyDP = 0
    var percPsyDP = 0.0
    var ptInPsy = 0

    var sizeSpecial = 0
    var sizeCategory = 0

    var appearance = 5











    fun setGender(input: Boolean){
        if(ownRace.heldRace == RaceName.dukzarist){
            if(isMale) combat.physicalRes.setSpecial(-5)
            else combat.magicRes.setSpecial(-5)
        }

        isMale = input

        if(ownRace.heldRace == RaceName.dukzarist){
            if(isMale) combat.physicalRes.setSpecial(5)
            else combat.magicRes.setSpecial(5)
        }
    }

    //setter for class with ClassName input
    fun setOwnClass(classIn: ClassName) {
        ownClass.onRemove()
        ownClass = classes.findClass(classIn)!!
        ownClass.onTake()
        updateClassInputs()
    }

    //setter for class with String input
    fun setOwnClass(className: String?) {
        ownClass.onRemove()
        ownClass = classes.findClass(ClassName.fromString(className))!!
        ownClass.onTake()
        updateClassInputs()
    }

    //setter for class with Integer input
    fun setOwnClass(classInt: Int?){
        ownClass.onRemove()
        ownClass = classes.findClass(ClassName.fromInt(classInt))!!
        ownClass.onTake()
        updateClassInputs()
    }

    //updates class values when the character's class changes
    fun updateClassInputs(){
        combat.setLifePerLevel(ownClass.lifePointsPerLevel)
        combat.updateInitiative()

        adjustMaxValues()

        secondaryList.classUpdate(ownClass)

        ki.updateMK()

        magic.calcMaxZeon()

        psychic.setInnatePsy()

        updateTotalSpent()
    }

    //setter for race with RaceName input
    fun setOwnRace(raceIn: RaceName) {
        removeRaceAdvantages()
        ownRace = CharRace(raceIn, this@BaseCharacter)
        applyRaceAdvantages()
    }

    //setter for race with String input
    fun setOwnRace(raceName: String?) {
        removeRaceAdvantages()
        ownRace = CharRace(RaceName.fromString(raceName), this@BaseCharacter)
        applyRaceAdvantages()
    }

    //setter for race with Integer input
    fun setOwnRace(raceNum: Int?){
        removeRaceAdvantages()
        ownRace = CharRace(RaceName.fromInt(raceNum), this@BaseCharacter)
        applyRaceAdvantages()
    }

    fun applyRaceAdvantages(){
        advantageRecord.raceAdvantages.forEach{
            if(it.onTake != null)
                it.onTake!!(it.picked, it.cost[it.pickedCost])
        }
    }

    fun removeRaceAdvantages(){
        advantageRecord.raceAdvantages.forEach{
            if(it.onRemove != null)
                it.onRemove!!(it.picked, it.cost[it.pickedCost])
        }
    }

    //update level and associated values
    @JvmName("setLvl1")
    fun setLvl(levNum: Int) {
        //set new level number
        lvl = levNum

        //determine development point count
        devPT = 500 + lvl * 100

        combat.updatePresence()

        //recalculate maximum DP allotments
        dpAllotmentCalc()

        combat.updateClassLife()
        combat.allAbilities.forEach{
            it.updateClassTotal()
        }
        combat.updateInitiative()

        ki.updateMK()

        magic.updateZeonFromClass()

        summoning.allSummoning.forEach{it.updateLevelTotal()}

        psychic.setInnatePsy()

        secondaryList.fullList.forEach{it.classTotalRefresh()}
    }

    //get new dp maximums based on class change
    private fun adjustMaxValues() {
        percCombatDP = ownClass.combatMax
        percMagDP = ownClass.magMax
        percPsyDP = ownClass.psyMax
        dpAllotmentCalc()
    }

    //calculates percentage allotments for each category
    private fun dpAllotmentCalc() {
        maxCombatDP = (devPT * percCombatDP).toInt()
        maxMagDP = (devPT * percMagDP).toInt()
        maxPsyDP = (devPT * percPsyDP).toInt()
    }

    //updates the total development points spent
    fun updateTotalSpent(){
        weaponProficiencies.doubleCheck()
        updateCombatSpent()
        updateMagicSpent()
        updatePsychicSpent()
        spentTotal = combat.lifeMultsTaken * ownClass.lifePointMultiple +
                secondaryList.calculateSpent() + ptInCombat + ptInMag + ptInPsy
    }

    //updates the total development points spent in combat abilities
    private fun updateCombatSpent(){
        ptInCombat =
            combat.calculateSpent() +
            weaponProficiencies.calculateSpent() +
            ki.calculateSpent()
    }

    private fun updateMagicSpent(){
        ptInMag = magic.calculateSpent() + summoning.calculateSpent()
    }

    private fun updatePsychicSpent(){
        ptInPsy = psychic.calculateSpent()
    }

    fun changeSize(input: Int){
        when(input){
            0 -> sizeSpecial -= 5
            1 -> sizeSpecial -= 4
            2 -> sizeSpecial -= 3
            3 -> sizeSpecial -= 2
            4 -> sizeSpecial -= 1
            5 -> sizeSpecial += 1
            6 -> sizeSpecial += 2
            7 -> sizeSpecial += 3
            8 -> sizeSpecial += 4
            9 -> sizeSpecial += 5
        }

        updateSize()
    }

    fun updateSize(){
        sizeCategory = primaryList.str.total + primaryList.con.total + sizeSpecial
    }

    @JvmName("setAppearance1")
    fun setAppearance(input: Int){
        if(advantageRecord.getAdvantage("Unattractive") != null)
            appearance = 2
        else if(ownRace.heldRace != RaceName.danjayni || input in 3..7)
            appearance = input
    }














    //constructor for new character
    constructor() {
        setOwnClass(ClassName.freelancer)
        setOwnRace(RaceName.human)
        setLvl(0)

        primaryList.str.setInput(5)
        primaryList.dex.setInput(5)
        primaryList.agi.setInput(5)
        primaryList.con.setInput(5)
        primaryList.int.setInput(5)
        primaryList.pow.setInput(5)
        primaryList.wp.setInput(5)
        primaryList.per.setInput(5)
    }

    //constructor for character with file association
    constructor(fileInput: File?) {
        val restoreChar = FileInputStream(fileInput)
        val readChar = InputStreamReader(restoreChar, StandardCharsets.UTF_8)
        val fileReader = BufferedReader(readChar)

        charName = fileReader.readLine()

        setGender(fileReader.readLine().toBoolean())

        setOwnClass(fileReader.readLine())
        setOwnRace(fileReader.readLine())
        setLvl(fileReader.readLine().toInt())

        primaryList.loadPrimaries(fileReader)

        combat.loadCombat(fileReader)

        setAppearance(fileReader.readLine().toInt())

        secondaryList.loadList(fileReader)
        weaponProficiencies.loadProficiencies(fileReader)
        ki.loadKiAttributes(fileReader)
        magic.loadMagic(fileReader)
        summoning.loadSummoning(fileReader)
        advantageRecord.loadAdvantages(fileReader)
        psychic.loadPsychic(fileReader)

        restoreChar.close()

        updateTotalSpent()
    }

    private lateinit var byteArray: SerialOutputStream

    //retrieve byte information for the character
    @get:Throws(IOException::class)
    val bytes: ByteArray
        get() {
            byteArray = SerialOutputStream()

            addNewData(charName)

            addNewData(isMale.toString())

            addNewData(ownClass.heldClass.name)
            addNewData(ownRace.heldRace.name)
            addNewData(lvl)

            primaryList.writePrimaries()

            combat.writeCombat()

            addNewData(appearance)

            secondaryList.writeList()
            weaponProficiencies.writeProficiencies()
            ki.writeKiAttributes()
            magic.writeMagic()
            summoning.writeSummoning()
            advantageRecord.writeAdvantages()
            psychic.writePsychic()

            byteArray.close()

            return byteArray.toByteArray()
        }

    //adds new String data to the ByteOutputStream
    fun addNewData(toAdd: String?) {
        byteArray.write(
            """$toAdd""".toByteArray(StandardCharsets.UTF_8),
            0,
            """$toAdd""".toByteArray(StandardCharsets.UTF_8).size
        )

        writeEndLine()
    }

    //adds new Int data to the ByteOutputStream
    fun addNewData(toAdd: Int?) {
        byteArray.write(
            """$toAdd""".toByteArray(StandardCharsets.UTF_8),
            0,
            """$toAdd""".toByteArray(StandardCharsets.UTF_8).size
        )

        writeEndLine()
    }

    fun writeEndLine(){
        byteArray.write(
            "\n".toByteArray(StandardCharsets.UTF_8),
            0,
            "\n".toByteArray(StandardCharsets.UTF_8).size)
    }
}