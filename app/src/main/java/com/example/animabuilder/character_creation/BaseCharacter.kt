package com.example.animabuilder.character_creation

import androidx.compose.runtime.MutableState
import com.example.animabuilder.character_creation.attributes.advantages.AdvantageRecord
import com.example.animabuilder.serializables.SerialOutputStream
import com.example.animabuilder.character_creation.attributes.secondary_abilities.SecondaryList
import com.example.animabuilder.character_creation.attributes.class_objects.CharClass
import com.example.animabuilder.character_creation.attributes.race_objects.CharRace
import com.example.animabuilder.character_creation.attributes.class_objects.ClassName
import com.example.animabuilder.character_creation.attributes.race_objects.RaceName
import com.example.animabuilder.character_creation.equipment.Armor
import com.example.animabuilder.character_creation.equipment.weapons.Weapon
import com.example.animabuilder.character_creation.equipment.weapons.WeaponOptions
import com.example.animabuilder.character_creation.equipment.weapons.WeaponType
import java.io.*
import java.nio.charset.StandardCharsets
import kotlin.Throws
import kotlin.math.ceil

/**
 * Character being built by the player
 * Holder class of all other character creation objects
 */

class BaseCharacter: Serializable {
    //character's name
    var charName: String = ""

    //list of secondary abilities
    var secondaryList = SecondaryList()
    var advantageRecord = AdvantageRecord(this@BaseCharacter)

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
    var lifeAt = 0

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

    var allActionPenalty = 0

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

    val weaponOptions = WeaponOptions()

    var primaryWeapon: Weapon = weaponOptions.unarmed

    var secondaryWeapon: List<Weapon> = mutableListOf()

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

    fun setOwnClass(classInt: Int?){
        ownClass = CharClass(ClassName.fromInt(classInt))

        updateClassInputs()
    }

    fun updateClassInputs(){
        updateLifePoints()

        updateAttack()
        updateBlock()
        updateDodge()
        updateWear()

        adjustMaxValues()
        secondaryList.classUpdate(ownClass)
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

    fun updateTotalSpent(){
        updateCombatSpent()
        spentTotal = secondaryList.calculateSpent() + ptInCombat + ptInMag + ptInPsy
    }

    fun updateCombatSpent(){
        ptInCombat =
            lifeMultsTaken * ownClass.lifePointMultiple +
            pointInAttack * ownClass.atkGrowth +
            pointInBlock * ownClass.blockGrowth +
            pointInDodge * ownClass.dodgeGrowth +
            pointInWear * ownClass.armorGrowth +
            calculateModulePoints()
    }

    //setters for each primary characteristic
    var setSTR = { strVal: Int ->
        str = strVal
        modSTR = getModVal(str)

        updateWear()
        secondaryList.updateSTR(modSTR)
    }
    var setDEX = { dexVal: Int ->
        dex = dexVal
        modDEX = getModVal(dex)

        secondaryList.updateDEX(modDEX)
        updateAttack()
        updateBlock()
    }
    var setAGI = { agiVal: Int ->
        agi = agiVal
        modAGI = getModVal(agi)

        secondaryList.updateAGI(modAGI)
        updateDodge()
    }
    var setCON = { conVal: Int ->
        con = conVal
        modCON = getModVal(con)

        updateLifeBase()
        updateLifePoints()
        updateResistances()
    }
    var setINT = { intVal: Int ->
        int = intVal
        modINT = getModVal(int)
        secondaryList.updateINT(modINT)
    }
    var setPOW = { powVal: Int ->
        pow = powVal
        modPOW = getModVal(pow)
        secondaryList.updatePOW(modPOW)
        updateResistances()
    }
    var setWP = { wpVal: Int ->
        wp = wpVal
        modWP = getModVal(wp)
        secondaryList.updateWP(modWP)
        updateResistances()
    }
    var setPER = { perVal: Int ->
        per = perVal
        modPER = getModVal(per)
        secondaryList.updatePER(modPER)
    }

    //calculate the corresponding mod value for the given characteristic value
    private fun getModVal(statVal: Int): Int {
        val output: Int = if (statVal <= 5) {
            when (statVal) {
                1 -> -30
                2 -> -20
                3 -> -10
                4 -> -5
                else -> 0
            }
        } else {
            (15 * (statVal / 5 - 1) + 5 * ceil(statVal % 5 / 2.0)).toInt()
        }
        return output
    }

    fun updateLifeBase(){
        lifeBase = when(con){
            1 -> 5
            2 -> 20
            3 -> 40
            4 -> 55
            5 -> 70
            6 -> 85
            7 -> 95
            8 -> 110
            9 -> 120
            10 -> 135
            11 -> 150
            12 -> 160
            13 -> 175
            14 -> 185
            15 -> 200
            16 -> 215
            17 -> 225
            18 -> 240
            19 -> 250
            20 -> 265
            else -> 0
        }
    }

    fun takeLifeMult(multTake: Int){
        lifeMultsTaken = multTake
        updateTotalSpent()
        updateLifePoints()
    }

    fun updateLifePoints(){
        lifeMax = lifeBase + (lifeMultsTaken * con) + (ownClass.lifePointsPerLevel * lvl)
    }

    val applyAttackPoint = {score: Int, textChange: MutableState<String>? ->
        pointInAttack = score
        updateTotalSpent()
        updateAttack()

        if(textChange != null)
            textChange.value = attack.toString()
    }

    fun updateAttack(){
        attack = pointInAttack + modDEX + (ownClass.atkPerLevel * lvl)
    }

    val applyBlockPoint = {score: Int, textChange: MutableState<String>? ->
        pointInBlock = score
        updateTotalSpent()
        updateBlock()

        if(textChange != null)
            textChange.value = block.toString()
    }

    fun updateBlock(){
        block = pointInBlock + modDEX + (ownClass.blockPerLevel * lvl)
    }

    val applyDodgePoint = {score: Int, textChange: MutableState<String>? ->
        pointInDodge = score
        updateTotalSpent()
        updateDodge()

        if(textChange != null)
            textChange.value = dodge.toString()
    }

    fun updateDodge(){
        dodge = pointInDodge + modAGI + (ownClass.dodgePerLevel * lvl)
    }

    val applyWearPoint = {score: Int, textChange: MutableState<String>? ->
        pointInWear = score
        updateTotalSpent()
        updateWear()

        if(textChange != null)
            textChange.value = wearArmor.toString()
    }

    fun updateWear(){
        wearArmor = pointInWear + modSTR + (ownClass.armorPerLevel * lvl)
    }

    fun updateResistances(){
        resistPhys = ((presence + modCON + rphysSpec) * rphysMult).toInt()
        resistDisease = ((presence + modCON + rdSpec) * rdMult).toInt()
        resistVen = ((presence + modCON + rvSpec) * rvMult).toInt()
        resistMag = ((presence + modPOW + rmSpec) * rmMult).toInt()
        resistPsy = ((presence + modWP + rpsySpec) * rpsyMult).toInt()
    }

    @JvmName("setPrimaryWeapon1")
    fun setPrimaryWeapon(toSet: Weapon){
        primaryWeapon = toSet
        updateTotalSpent()
    }

    @JvmName("setPrimaryWeapon2")
    fun setPrimaryWeapon(toSet: String){
        primaryWeapon = weaponOptions.findWeapon(toSet)
        updateTotalSpent()
    }

    fun addSecondaryWeapon(toAdd: Weapon){
        secondaryWeapon = secondaryWeapon + toAdd
        updateTotalSpent()
    }

    fun addSecondaryWeapon(toAdd: String){
        secondaryWeapon = secondaryWeapon + weaponOptions.findWeapon(toAdd)
        updateTotalSpent()
    }

    fun removeSecondaryWeapon(toRemove: Weapon){
        secondaryWeapon = secondaryWeapon - toRemove
        updateTotalSpent()
    }

    fun calculateModulePoints(): Int{
        var total = 0

        secondaryWeapon.forEach{
            //if primary weapon is mixed
            if(primaryWeapon.type == WeaponType.Mixed){
                //apply same type for exactly matching weapons
                if(it.type == WeaponType.Mixed) {
                    if ((primaryWeapon.mixedType!! - it.mixedType).isEmpty())
                        total += 10

                    //apply mixed type for one matching type
                    else if (primaryWeapon.mixedType!!.contains(it.mixedType!![0]) ||
                        primaryWeapon.mixedType!!.contains(it.mixedType!![1])
                    )
                        total += 15
                }

                //apply mixed type for it belonging to one mixed type
                else if(primaryWeapon.mixedType!!.contains(it.type))
                    total += 15

                else
                    total += 20
            }
            else if(it.type == WeaponType.Mixed && it.mixedType!!.contains(primaryWeapon.type))
                    total += 15
            else if(it.type == primaryWeapon.type)
                total += 10
            else
                total += 20
        }

        return total
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

        secondaryList.loadList(fileReader)

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

        setPrimaryWeapon(fileReader.readLine())

        var loops = fileReader.readLine().toInt()

        while(loops > 0){
            addSecondaryWeapon(fileReader.readLine())
            loops--
        }

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

            secondaryList.writeList(byteArray)

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

            addNewData(primaryWeapon.name)

            addNewData(secondaryWeapon.size)

            secondaryWeapon.forEach{
                addNewData(it.name)
            }

            byteArray.close()

            return byteArray.toByteArray()
        }

    //adds new String data to the ByteOutputStream
    private fun addNewData(toAdd: String?) {
        byteArray.write(
            """$toAdd""".toByteArray(StandardCharsets.UTF_8),
            0,
            """$toAdd""".toByteArray(StandardCharsets.UTF_8).size
        )

        writeEndLine()
    }

    //adds new Int data to the ByteOutputStream
    private fun addNewData(toAdd: Int) {
        byteArray.write(
            """$toAdd""".toByteArray(StandardCharsets.UTF_8),
            0,
            """$toAdd""".toByteArray(StandardCharsets.UTF_8).size
        )

        writeEndLine()
    }

    private fun writeEndLine(){
        byteArray.write(
            "\n".toByteArray(StandardCharsets.UTF_8),
            0,
            "\n".toByteArray(StandardCharsets.UTF_8).size)
    }
}