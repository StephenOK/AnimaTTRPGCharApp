package com.example.animabuilder.CharacterCreation

import com.example.animabuilder.Serializables.SerialOutputStream
import com.example.animabuilder.CharacterCreation.Attributes.SecondaryAbilities.SecondaryList
import com.example.animabuilder.CharacterCreation.Attributes.Class.CharClass
import com.example.animabuilder.CharacterCreation.Attributes.Race.CharRace
import com.example.animabuilder.CharacterCreation.Attributes.Class.ClassName
import com.example.animabuilder.CharacterCreation.Attributes.Race.RaceName
import com.example.animabuilder.CharacterCreation.Equipment.Armor
import com.example.animabuilder.CharacterCreation.Equipment.Weapon
import java.io.*
import java.nio.charset.StandardCharsets
import kotlin.Throws

class BaseCharacter : Serializable {
    var charName: String? = null
    var byteArray: SerialOutputStream? = null

    //list of secondary abilities
    var secondaryList: SecondaryList = SecondaryList()

    //character's level
    var lvl = 0

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

    private fun dpAllotmentCalc() {
        maxCombatDP = (devPT * percCombatDP).toInt()
        maxMagDP = (devPT * percMagDP).toInt()
        maxPsyDP = (devPT * percPsyDP).toInt()
    }

    @JvmName("setLvl1")
    fun setLvl(levNum: Int) {
        //set new level number
        lvl = levNum

        //determine development point count
        devPT = 500 + lvl * 100

        //recalculate maximum DP allotments
        dpAllotmentCalc()
        secondaryList.levelUpdate(lvl, ownClass!!)
    }

    //initialize stats for the character
    //manipulable by user
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

    var setSTR = { strVal: Int ->
        str = strVal

        //change strength modifier number
        modSTR = getModVal(str)

        //change wear armor ability
        setWearArmor(modSTR)
        secondaryList.updateSTR(modSTR)
    }
    var setDEX = { dexVal: Int ->
        dex = dexVal
        modDEX = getModVal(dex)
        secondaryList.updateDEX(modDEX)
    }
    var setAGI = { agiVal: Int ->
        agi = agiVal
        modAGI = getModVal(agi)
        secondaryList.updateAGI(modAGI)
    }
    var setCON = { conVal: Int ->
        con = conVal
        modCON = getModVal(con)
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
    }
    var setWP = { wpVal: Int ->
        wp = wpVal
        modWP = getModVal(wp)
        secondaryList.updateWP(modWP)
    }
    var setPER = { perVal: Int ->
        per = perVal
        modPER = getModVal(per)
        secondaryList.updatePER(modPER)
    }

    private fun getModVal(statVal: Int): Int {
        val output: Int
        output = if (statVal <= 5) {
            when (statVal) {
                1 -> -30
                2 -> -20
                3 -> -10
                4 -> -5
                else -> 0
            }
        } else {
            (15 * (statVal / 5 - 1) + 5 * Math.ceil(statVal % 5 / 2.0)).toInt()
        }
        return output
    }

    var getModSTR = { modSTR }
    var getModDEX = { modDEX }
    var getModAGI = { modAGI }
    var getModCON = { modCON }
    var getModINT = { modINT }
    var getModPOW = { modPOW }
    var getModWP = { modWP }
    var getModPER = { modPER }

    var ownClass: CharClass? = null
    var ownRace: CharRace? = null

    fun setOwnClass(classIn: ClassName?) {
        ownClass = CharClass(classIn!!)
        adjustMaxValues()
        secondaryList.classUpdate(ownClass!!)
    }

    fun setOwnClass(className: String?) {
        ownClass = CharClass(ClassName.fromString(className))
        adjustMaxValues()
        secondaryList.classUpdate(ownClass!!)
    }

    private fun adjustMaxValues() {
        percCombatDP = ownClass!!.combatMax
        percMagDP = ownClass!!.magMax
        percPsyDP = ownClass!!.psyMax
        dpAllotmentCalc()
    }

    fun setOwnRace(raceIn: RaceName?) {
        ownRace = CharRace(raceIn!!)
    }

    fun setOwnRace(raceName: String?) {
        ownRace = CharRace(RaceName.fromString(raceName))
    }

    private fun updateClassValues() {}

    //maximum fatigue value
    var maxFatigue = 0

    //character resistance stats
    var resistPhys = 0
    var resistDisease = 0
    var resistVen = 0
    var resistMag = 0
    var resistPsy = 0

    //character's maximum hp
    var lifeMax = 0

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

    @JvmName("setWearArmor1")
    private fun setWearArmor(waValue: Int) {
        wearArmor = waValue
        checkArmor(wearArmor)
    }

    private fun checkArmor(strCheck: Int): Boolean {
        return equippedPiece == null || equippedPiece!!.strReq < strCheck
    }

    var initiative = 0
    var maxKi = 0
    var maxPsi = 0
    var maxZeon = 0
    var equippedPiece: Armor? = null
    var equippedWeapon: Weapon? = null

    constructor() {
        secondaryList = SecondaryList()
        setOwnClass(ClassName.freelancer)
        setOwnRace(RaceName.human)
        charName = ""
        setLvl(0)
        spentTotal = 0
        setSTR(5)
        setDEX(5)
        setAGI(5)
        setCON(5)
        setINT(5)
        setPOW(5)
        setWP(5)
        setPER(5)
    }

    constructor(fileInput: File?) {
        val restoreChar = FileInputStream(fileInput)
        val readChar = InputStreamReader(restoreChar, StandardCharsets.UTF_8)
        val fileReader = BufferedReader(readChar)
        secondaryList = SecondaryList()
        charName = fileReader.readLine()
        setOwnClass(fileReader.readLine())
        setOwnRace(fileReader.readLine())
        setLvl(fileReader.readLine().toInt())
        spentTotal = fileReader.readLine().toInt()
        setSTR(fileReader.readLine().toInt())
        setDEX(fileReader.readLine().toInt())
        setAGI(fileReader.readLine().toInt())
        setCON(fileReader.readLine().toInt())
        setINT(fileReader.readLine().toInt())
        setPOW(fileReader.readLine().toInt())
        setWP(fileReader.readLine().toInt())
        setPER(fileReader.readLine().toInt())
        secondaryList.loadList(fileReader)
        restoreChar.close()
    }

    @get:Throws(IOException::class)
    val bytes: ByteArray
        get() {
            byteArray = SerialOutputStream()
            addNewData(charName)
            addNewData(ownClass!!.heldClass.name)
            addNewData(ownRace!!.heldRace.name)
            addNewData(lvl)
            addNewData(spentTotal)
            addNewData(str)
            addNewData(dex)
            addNewData(agi)
            addNewData(con)
            addNewData(int)
            addNewData(pow)
            addNewData(wp)
            addNewData(per)
            secondaryList.writeList(byteArray)
            byteArray!!.close()
            return byteArray!!.toByteArray()
        }

    private fun addNewData(toAdd: String?) {
        byteArray!!.write(
            """$toAdd
""".toByteArray(StandardCharsets.UTF_8), 0, """$toAdd
""".toByteArray(StandardCharsets.UTF_8).size
        )
    }

    private fun addNewData(toAdd: Int) {
        byteArray!!.write(
            """$toAdd
""".toByteArray(StandardCharsets.UTF_8), 0, """$toAdd
""".toByteArray(StandardCharsets.UTF_8).size
        )
    }
}