package com.example.animabuilder.character_creation

import androidx.compose.runtime.MutableState
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
import com.example.animabuilder.character_creation.equipment.Armor
import com.example.animabuilder.character_creation.equipment.weapons.weapon_classes.Weapon
import com.example.animabuilder.character_creation.equipment.weapons.WeaponProficiencies
import java.io.*
import java.nio.charset.StandardCharsets
import kotlin.Throws
import kotlin.math.ceil

/**
 * Character being built by the user
 * Holder class of all other character creation objects
 */

class BaseCharacter: Serializable {
    //character's name
    var charName = ""

    var isMale = true

    //list of secondary abilities
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

    var str = 0
    var dex = 0
    var agi = 0
    var con = 0
    var int = 0
    var pow = 0
    var wp = 0
    var per = 0

    var strBonus = 0
    var dexBonus = 0
    var agiBonus = 0
    var conBonus = 0
    var intBonus = 0
    var powBonus = 0
    var wpBonus = 0
    var perBonus = 0

    //initialize character's primary characteristics
    var totalSTR = 0
    var totalDEX = 0
    var totalAGI = 0
    var totalCON = 0
    var totalINT = 0
    var totalPOW = 0
    var totalWP = 0
    var totalPER = 0

    //initialize stat modifiers
    var modSTR = 0
    var modDEX = 0
    var modAGI = 0
    var modCON = 0
    var modINT = 0
    var modPOW = 0
    var modWP = 0
    var modPER = 0

    var sizeSpecial = 0
    var sizeCategory = 0

    var appearance = 5

    var equippedPiece: Armor? = null
    var equippedWeapon: Weapon? = null











    fun setGender(input: Boolean){
        if(ownRace.heldRace == RaceName.dukzarist){
            if(isMale) combat.rphysSpec -= 5
            else combat.rmSpec -= 5
        }

        isMale = input

        if(ownRace.heldRace == RaceName.dukzarist){
            if(isMale) combat.rphysSpec += 5
            else combat.rmSpec += 5
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
        combat.updateLifePoints()

        combat.updateAttack()
        combat.updateBlock()
        combat.updateDodge()
        combat.updateWear()
        combat.updateInitiative()

        adjustMaxValues()
        secondaryList.classUpdate(ownClass)
        ki.updateMK()

        magic.calcMaxZeon()

        summoning.updateSummon()
        summoning.updateControl()
        summoning.updateBind()
        summoning.updateBanish()

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
        ownRace.raceAdvantages.forEach{
            if(it.onTake != null)
                it.onTake!!(it.picked, it.cost[it.pickedCost])
        }
    }

    fun removeRaceAdvantages(){
        ownRace.raceAdvantages.forEach{
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
        combat.updateResistances()

        //recalculate maximum DP allotments
        dpAllotmentCalc()

        combat.updateLifePoints()
        combat.updateAttack()
        combat.updateBlock()
        combat.updateDodge()
        combat.updateWear()
        combat.updateInitiative()

        ki.updateMK()

        psychic.setInnatePsy()

        secondaryList.classUpdate(ownClass)
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

    //setters for each primary characteristic
    var setSTR = { strVal: Int ->
        str = strVal

        while(str + strBonus > 11 && advantageRecord.getAdvantage("Add One Point to a Characteristic", 0, 0) != null){
            advantageRecord.removeAdvantage(advantageRecord.getAdvantage("Add One Point to a Characteristic", 0, 0)!!)
        }

        updateStrValues()
    }

    @JvmName("setStrBonus1")
    fun setStrBonus(bonus: Int){
        strBonus += bonus
        updateStrValues()
    }

    fun updateStrValues(){
        totalSTR =
            if(advantageRecord.getAdvantage("Increase One Characteristic to Nine", 0, 0) != null) 9
            else str + strBonus
        modSTR = getModVal(totalSTR)
        combat.updateWear()
        updateSize()

        secondaryList.updateSTR(modSTR)
        ki.updateKiStats()
    }

    var setDEX = { dexVal: Int ->
        dex = dexVal

        while(dex + dexBonus > 11 && advantageRecord.getAdvantage("Add One Point to a Characteristic", 1, 0) != null){
            advantageRecord.removeAdvantage(advantageRecord.getAdvantage("Add One Point to a Characteristic", 1, 0)!!)
        }

        updateDexValues()
    }

    @JvmName("setDexBonus1")
    fun setDexBonus(bonus: Int){
        dexBonus += bonus
        updateDexValues()
    }

    fun updateDexValues(){
        totalDEX =
            if(advantageRecord.getAdvantage("Increase One Characteristic to Nine", 1, 0) != null) 9
            else dex + dexBonus
        modDEX = getModVal(totalDEX)

        secondaryList.updateDEX(modDEX)
        combat.updateAttack()
        combat.updateBlock()
        combat.updateInitiative()
        ki.updateKiStats()
        magic.calcMagProj()
        psychic.updatePsyProjection()
    }

    var setAGI = { agiVal: Int ->
        agi = agiVal

        while(agi + agiBonus > 11 && advantageRecord.getAdvantage("Add One Point to a Characteristic", 2, 0) != null){
            advantageRecord.removeAdvantage(advantageRecord.getAdvantage("Add One Point to a Characteristic", 2, 0)!!)
        }

        updateAgiValues()
    }

    @JvmName("setAgiBonus1")
    fun setAgiBonus(bonus: Int){
        agiBonus += bonus
        updateAgiValues()
    }

    fun updateAgiValues(){
        totalAGI =
            if(advantageRecord.getAdvantage("Increase One Characteristic to Nine", 2, 0) != null) 9
            else agi + agiBonus
        modAGI = getModVal(totalAGI)

        secondaryList.updateAGI(modAGI)
        combat.updateDodge()
        combat.updateInitiative()
        ki.updateKiStats()
    }

    var setCON = { conVal: Int ->
        con = conVal

        while(con + conBonus > 11 && advantageRecord.getAdvantage("Add One Point to a Characteristic", 3, 0) != null){
            advantageRecord.removeAdvantage(advantageRecord.getAdvantage("Add One Point to a Characteristic", 3, 0)!!)
        }

        updateConValues()
    }

    @JvmName("setConBonus1")
    fun setConBonus(bonus: Int){
        conBonus += bonus
        updateConValues()
    }

    fun updateConValues(){
        totalCON =
            if(advantageRecord.getAdvantage("Increase One Characteristic to Nine", 3, 0) != null) 9
            else con + conBonus
        modCON = getModVal(totalCON)
        combat.updateFatigue()
        combat.getBaseRegen()
        updateSize()

        combat.updateLifeBase()
        combat.updateLifePoints()
        combat.updateResistances()
        ki.updateKiStats()
    }

    var setINT = { intVal: Int ->
        int = intVal

        while(int + intBonus > 13 && advantageRecord.getAdvantage("Add One Point to a Characteristic", 4, 0) != null){
            advantageRecord.removeAdvantage(advantageRecord.getAdvantage("Add One Point to a Characteristic", 4, 0)!!)
        }

        updateIntValues()
    }

    @JvmName("setIntBonus1")
    fun setIntBonus(bonus: Int){
        intBonus += bonus
        updateIntValues()
    }

    fun updateIntValues(){
        totalINT =
            if(advantageRecord.getAdvantage("Increase One Characteristic to Nine", 4, 0) != null) 9
            else int + intBonus
        modINT = getModVal(totalINT)

        secondaryList.updateINT(modINT)
        magic.setMagicLevelMax()
    }

    var setPOW = { powVal: Int ->
        pow = powVal

        while(pow + powBonus > 13 && advantageRecord.getAdvantage("Add One Point to a Characteristic", 5, 0) != null){
            advantageRecord.removeAdvantage(advantageRecord.getAdvantage("Add One Point to a Characteristic", 5, 0)!!)
        }

        updatePowValues()
    }

    @JvmName("setPowBonus1")
    fun setPowBonus(bonus: Int){
        powBonus += bonus
        updatePowValues()
    }

    fun updatePowValues(){
        totalPOW =
            if(advantageRecord.getAdvantage("Increase One Characteristic to Nine", 5, 0) != null) 9
            else pow + powBonus
        modPOW = getModVal(totalPOW)

        secondaryList.updatePOW(modPOW)
        combat.updateResistances()
        ki.updateKiStats()
        magic.setBaseZeon()
        magic.setBaseZeonAcc()
        summoning.updateSummon()
        summoning.updateBind()
        summoning.updateBanish()
    }

    var setWP = { wpVal: Int ->
        wp = wpVal

        while(wp + wpBonus > 13 && advantageRecord.getAdvantage("Add One Point to a Characteristic", 6, 0) != null){
            advantageRecord.removeAdvantage(advantageRecord.getAdvantage("Add One Point to a Characteristic", 6, 0)!!)
        }

        updateWpValues()
    }

    @JvmName("setWpBonus1")
    fun setWpBonus(bonus: Int){
        wpBonus += bonus
        updateWpValues()
    }

    fun updateWpValues(){
        totalWP =
            if(advantageRecord.getAdvantage("Increase One Characteristic to Nine", 6, 0) != null) 9
            else wp + wpBonus
        modWP = getModVal(totalWP)

        secondaryList.updateWP(modWP)
        combat.updateResistances()
        ki.updateKiStats()
        summoning.updateControl()
        psychic.setBasePotential()
    }

    var setPER = { perVal: Int ->
        per = perVal

        while(per + perBonus > 13 && advantageRecord.getAdvantage("Add One Point to a Characteristic", 7, 0) != null){
            advantageRecord.removeAdvantage(advantageRecord.getAdvantage("Add One Point to a Characteristic", 7, 0)!!)
        }

        updatePerValues()
    }

    @JvmName("setPerBonus1")
    fun setPerBonus(bonus: Int){
        perBonus += bonus
        updatePerValues()
    }

    fun updatePerValues(){
        totalPER =
            if(advantageRecord.getAdvantage("Increase One Characteristic to Nine", 7, 0) != null) 9
            else per + perBonus
        modPER = getModVal(totalPER)

        secondaryList.updatePER(modPER)
    }

    //calculate the corresponding mod value for the given characteristic value
    private fun getModVal(statVal: Int): Int {
        val output: Int = if (statVal < 5) {
            when (statVal) {
                2 -> -20
                3 -> -10
                4 -> -5
                else -> -30
            }
        } else {
            (15 * (statVal / 5 - 1) + 5 * ceil(statVal % 5 / 2.0)).toInt()
        }
        return output
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
        sizeCategory = totalSTR + totalCON + sizeSpecial
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

        setSTR(5)
        setDEX(5)
        setAGI(5)
        setCON(5)
        setINT(5)
        setPOW(5)
        setWP(5)
        setPER(5)
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

        setSTR(fileReader.readLine().toInt())
        setDEX(fileReader.readLine().toInt())
        setAGI(fileReader.readLine().toInt())
        setCON(fileReader.readLine().toInt())
        setINT(fileReader.readLine().toInt())
        setPOW(fileReader.readLine().toInt())
        setWP(fileReader.readLine().toInt())
        setPER(fileReader.readLine().toInt())

        combat.loadCombat(fileReader)

        setAppearance(fileReader.readLine().toInt())

        secondaryList.loadList(fileReader)
        weaponProficiencies.loadProficiencies(fileReader)
        ki.loadKiAttributes(fileReader)
        magic.loadMagic(fileReader)
        summoning.loadSummoning(fileReader)
        psychic.loadPsychic(fileReader)
        advantageRecord.loadAdvantages(fileReader)

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

            addNewData(totalSTR)
            addNewData(totalDEX)
            addNewData(totalAGI)
            addNewData(totalCON)
            addNewData(totalINT)
            addNewData(totalPOW)
            addNewData(totalWP)
            addNewData(totalPER)

            combat.writeCombat()

            addNewData(appearance)

            secondaryList.writeList()
            weaponProficiencies.writeProficiencies()
            ki.writeKiAttributes()
            magic.writeMagic()
            summoning.writeSummoning()
            psychic.writePsychic()
            advantageRecord.writeAdvantages()

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