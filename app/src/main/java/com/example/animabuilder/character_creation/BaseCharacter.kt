package com.example.animabuilder.character_creation

import androidx.compose.runtime.MutableState
import com.example.animabuilder.character_creation.attributes.ki_abilities.Ki
import com.example.animabuilder.character_creation.attributes.advantages.AdvantageRecord
import com.example.animabuilder.serializables.SerialOutputStream
import com.example.animabuilder.character_creation.attributes.secondary_abilities.SecondaryList
import com.example.animabuilder.character_creation.attributes.class_objects.CharClass
import com.example.animabuilder.character_creation.attributes.race_objects.CharRace
import com.example.animabuilder.character_creation.attributes.class_objects.ClassName
import com.example.animabuilder.character_creation.attributes.race_objects.RaceName
import com.example.animabuilder.character_creation.attributes.magic.Magic
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
    var charName: String = ""

    //list of secondary abilities
    val secondaryList = SecondaryList()
    val advantageRecord = AdvantageRecord(this@BaseCharacter)
    val ki = Ki(this@BaseCharacter)
    val magic = Magic(this@BaseCharacter)
    val summoning = Summoning(this@BaseCharacter)

    lateinit var ownClass: CharClass

    //initialize character's class and race
    var ownRace: CharRace? = null

    //character's level
    var lvl = 0
    var presence: Int = 20

    //character creation and development points
    var createPT = 0
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

    //initialize character's primary characteristics
    var str = 0
    var dex = 0
    var agi = 0
    var con = 0
    var int = 0
    var pow = 0
    var wp = 0
    var per = 0

    //initialize stat modifiers
    var modSTR = 0
    var modDEX = 0
    var modAGI = 0
    var modCON = 0
    var modINT = 0
    var modPOW = 0
    var modWP = 0
    var modPER = 0

    //character's maximum hp
    var lifeMax = 0

    var lifeBase = 0
    var lifeMultsTaken = 0

    //character's attack stat
    var attack = 0
    var pointInAttack = 0

    //character's block stat
    var block = 0
    var pointInBlock = 0

    //character's dodge stat
    var dodge = 0
    var pointInDodge = 0

    //character's wear armor stat
    var wearArmor = 0
    var pointInWear = 0

    //maximum fatigue value
    var maxFatigue = 0

    var initiative = 0

    //character resistance stats
    var resistPhys = 0
    var resistDisease = 0
    var resistVen = 0
    var resistMag = 0
    var resistPsy = 0

    var rphysSpec = 0
    var rdSpec = 0
    var rvSpec = 0
    var rmSpec = 0
    var rpsySpec = 0

    var rphysMult = 1.0
    var rdMult = 1.0
    var rvMult = 1.0
    var rmMult = 1.0
    var rpsyMult = 1.0

    val weaponProficiencies = WeaponProficiencies(this@BaseCharacter)

    var equippedPiece: Armor? = null
    var equippedWeapon: Weapon? = null











    //setter for class with ClassName input
    fun setOwnClass(classIn: ClassName?) {
        ownClass = CharClass(classIn!!)
        updateClassInputs()
    }

    //setter for class with String input
    fun setOwnClass(className: String?) {
        ownClass = CharClass(ClassName.fromString(className))
        updateClassInputs()
    }

    //setter for class with Integer input
    fun setOwnClass(classInt: Int?){
        ownClass = CharClass(ClassName.fromInt(classInt))
        updateClassInputs()
    }

    //updates class values when the character's class changes
    fun updateClassInputs(){
        updateLifePoints()

        updateAttack()
        updateBlock()
        updateDodge()
        updateWear()

        adjustMaxValues()
        secondaryList.classUpdate(ownClass)
        updateMK()

        magic.calcMaxZeon()

        summoning.updateSummon()
        summoning.updateControl()
        summoning.updateBind()
        summoning.updateBanish()

        updateTotalSpent()
    }

    //setter for race with RaceName input
    fun setOwnRace(raceIn: RaceName) {
        ownRace = CharRace(raceIn, advantageRecord)
    }

    //setter for race with String input
    fun setOwnRace(raceName: String?) {
        ownRace = CharRace(RaceName.fromString(raceName), advantageRecord)
    }

    //setter for race with Integer input
    fun setOwnRace(raceNum: Int?){
        ownRace = CharRace(RaceName.fromInt(raceNum), advantageRecord)
    }

    //update level and associated values
    @JvmName("setLvl1")
    fun setLvl(levNum: Int) {
        //set new level number
        lvl = levNum

        //determine development point count
        devPT = 500 + lvl * 100

        presence =
            if(lvl == 0)
                20
            else
                25 + (5 * lvl)
        updateResistances()

        //recalculate maximum DP allotments
        dpAllotmentCalc()

        updateLifePoints()
        updateAttack()
        updateBlock()
        updateDodge()
        updateWear()

        updateMK()

        secondaryList.levelUpdate(lvl, ownClass)
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
        spentTotal = secondaryList.calculateSpent() + ptInCombat + ptInMag + ptInPsy
    }

    //updates the total development points spent in combat abilities
    private fun updateCombatSpent(){
        ptInCombat =
            lifeMultsTaken * ownClass.lifePointMultiple +
            pointInAttack * ownClass.atkGrowth +
            pointInBlock * ownClass.blockGrowth +
            pointInDodge * ownClass.dodgeGrowth +
            pointInWear * ownClass.armorGrowth +
            weaponProficiencies.calculateSpent() +
            ki.calculateSpent()
    }

    private fun updateMagicSpent(){
        ptInMag = magic.calculateSpent() + summoning.calculateSpent()
    }

    //setters for each primary characteristic
    var setSTR = { strVal: Int ->
        str = strVal
        modSTR = getModVal(str)

        updateWear()
        secondaryList.updateSTR(modSTR)
        ki.updateKiStats()
    }
    var setDEX = { dexVal: Int ->
        dex = dexVal
        modDEX = getModVal(dex)

        secondaryList.updateDEX(modDEX)
        updateAttack()
        updateBlock()
        ki.updateKiStats()
        magic.calcMagProj()
    }
    var setAGI = { agiVal: Int ->
        agi = agiVal
        modAGI = getModVal(agi)

        secondaryList.updateAGI(modAGI)
        updateDodge()
        ki.updateKiStats()
    }
    var setCON = { conVal: Int ->
        con = conVal
        modCON = getModVal(con)

        updateLifeBase()
        updateLifePoints()
        updateResistances()
        ki.updateKiStats()
    }
    var setINT = { intVal: Int ->
        int = intVal
        modINT = getModVal(int)
        secondaryList.updateINT(modINT)
        magic.setMagicLevelMax()
    }
    var setPOW = { powVal: Int ->
        pow = powVal
        modPOW = getModVal(pow)
        secondaryList.updatePOW(modPOW)
        updateResistances()
        ki.updateKiStats()
        magic.setBaseZeon()
        magic.setBaseZeonAcc()
        summoning.updateSummon()
        summoning.updateBind()
        summoning.updateBanish()
    }
    var setWP = { wpVal: Int ->
        wp = wpVal
        modWP = getModVal(wp)
        secondaryList.updateWP(modWP)
        updateResistances()
        ki.updateKiStats()
        summoning.updateControl()
    }
    var setPER = { perVal: Int ->
        per = perVal
        modPER = getModVal(per)
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

    //update the base number of life points based on the character's constitution
    fun updateLifeBase(){
        lifeBase = if(con == 1)
            5
        else
            20 + (con * 10) + modCON
    }

    //updates the number of life multiples the user is taking for their character
    fun takeLifeMult(multTake: Int){
        lifeMultsTaken = multTake
        updateTotalSpent()
        updateLifePoints()
    }

    //updates the character's total life points
    fun updateLifePoints(){
        lifeMax = lifeBase + (lifeMultsTaken * con) + (ownClass.lifePointsPerLevel * lvl)
    }

    //updates attack points and updates a string item if one given
    val applyAttackPoint = {score: Int, textChange: MutableState<String>? ->
        pointInAttack = score
        updateTotalSpent()
        updateAttack()

        if(textChange != null)
            textChange.value = attack.toString()

        validAttackDodgeBlock()
    }

    //updates the character's attack value
    fun updateAttack(){
        attack = pointInAttack + modDEX + attackClassVal()
        weaponProficiencies.updateMartialMax()
    }

    //gets the character's class values for attack
    fun attackClassVal(): Int{
        var total = ownClass.atkPerLevel * lvl
        if(weaponProficiencies.takenMartialList.contains(weaponProficiencies.martials.capoeira))
            total += 10

        return if(total > 50)
            50
        else
            total
    }

    //updates block points and updates a string item if one given
    val applyBlockPoint = {score: Int, textChange: MutableState<String>? ->
        pointInBlock = score
        updateTotalSpent()
        updateBlock()

        if(textChange != null)
            textChange.value = block.toString()

        validAttackDodgeBlock()
    }

    //updates the character's block value
    fun updateBlock(){
        block = pointInBlock + modDEX + (ownClass.blockPerLevel * lvl)
        weaponProficiencies.updateMartialMax()
    }

    //updates dodge points and updates a string item if one given
    val applyDodgePoint = {score: Int, textChange: MutableState<String>? ->
        pointInDodge = score
        updateTotalSpent()
        updateDodge()

        if(textChange != null)
            textChange.value = dodge.toString()

        validAttackDodgeBlock()
    }

    //updates the character's dodge value
    fun updateDodge(){
        dodge = pointInDodge + modAGI + (ownClass.dodgePerLevel * lvl)
        weaponProficiencies.updateMartialMax()
    }

    //determines if the attack, block, and dodge the user has taken is valid
    fun validAttackDodgeBlock(): Boolean{
        //if only one stat developed, cannot exceed 25% of overall devPT
        return ((pointInBlock == 0 && pointInDodge == 0 && pointInAttack * ownClass.atkGrowth <= devPT/4) ||
                (pointInAttack == 0 && pointInDodge == 0 && pointInBlock * ownClass.blockGrowth <= devPT/4) ||
                (pointInAttack == 0 && pointInBlock == 0 && pointInDodge * ownClass.dodgeGrowth <= devPT/4)) ||

                //attack, dodge, and block cannot equate to over 50% of overall devPT
                (((pointInAttack * ownClass.atkGrowth) + (pointInBlock * ownClass.blockGrowth) + (pointInDodge * ownClass.dodgeGrowth) <= devPT/2) &&

                //attack can not be more than 50 of either one of block or dodge
                (attack - block <= 50 || attack - dodge <= 50) &&

                //neither block nor dodge can be 50 more than attack
                (block - attack <= 50 && dodge - attack <= 50))
    }

    //updates wear armor points and updates a string item if one given
    val applyWearPoint = {score: Int, textChange: MutableState<String>? ->
        pointInWear = score
        updateTotalSpent()
        updateWear()

        if(textChange != null)
            textChange.value = wearArmor.toString()

        true
    }

    //updates the character's wear armor value
    fun updateWear(){
        wearArmor = pointInWear + modSTR + (ownClass.armorPerLevel * lvl)
    }

    //updates the character's resistances
    fun updateResistances(){
        resistPhys = ((presence + modCON + rphysSpec) * rphysMult).toInt()
        resistDisease = ((presence + modCON + rdSpec) * rdMult).toInt()
        resistVen = ((presence + modCON + rvSpec) * rvMult).toInt()
        resistMag = ((presence + modPOW + rmSpec) * rmMult).toInt()
        resistPsy = ((presence + modWP + rpsySpec) * rpsyMult).toInt()
    }

    //updates the character's martial knowledge
    fun updateMK(){
        ki.martialKnowledgeMax = (ownClass.mkPerLevel * lvl) + weaponProficiencies.mkFromArts()
        ki.updateMkSpent()
    }













    //constructor for new character
    constructor() {
        charName = ""

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

        takeLifeMult(fileReader.readLine().toInt())
        applyAttackPoint(fileReader.readLine().toInt(), null)
        applyBlockPoint(fileReader.readLine().toInt(), null)
        applyDodgePoint(fileReader.readLine().toInt(), null)
        applyWearPoint(fileReader.readLine().toInt(), null)

        secondaryList.loadList(fileReader)
        weaponProficiencies.loadProficiencies(fileReader)
        ki.loadKiAttributes(fileReader)
        magic.loadMagic(fileReader)
        summoning.loadSummoning(fileReader)

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

            addNewData(ownClass.heldClass.name)
            addNewData(ownRace!!.heldRace.name)
            addNewData(lvl)

            addNewData(str)
            addNewData(dex)
            addNewData(agi)
            addNewData(con)
            addNewData(int)
            addNewData(pow)
            addNewData(wp)
            addNewData(per)

            addNewData(lifeMultsTaken)
            addNewData(pointInAttack)
            addNewData(pointInBlock)
            addNewData(pointInDodge)
            addNewData(pointInWear)

            secondaryList.writeList(this@BaseCharacter)
            weaponProficiencies.writeProficiencies(this@BaseCharacter)
            ki.writeKiAttributes()
            magic.writeMagic()
            summoning.writeSummoning()

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