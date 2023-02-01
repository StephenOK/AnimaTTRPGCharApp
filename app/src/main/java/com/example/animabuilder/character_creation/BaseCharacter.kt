package com.example.animabuilder.character_creation

import androidx.compose.runtime.MutableState
import com.example.animabuilder.character_creation.attributes.advantages.advantage_items.AdvantageRecord
import com.example.animabuilder.character_creation.attributes.ki_abilities.Ki
import com.example.animabuilder.serializables.SerialOutputStream
import com.example.animabuilder.character_creation.attributes.secondary_abilities.SecondaryList
import com.example.animabuilder.character_creation.attributes.class_objects.CharClass
import com.example.animabuilder.character_creation.attributes.race_objects.CharRace
import com.example.animabuilder.character_creation.attributes.class_objects.ClassName
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
    val secondaryList = SecondaryList(this@BaseCharacter)
    val weaponProficiencies = WeaponProficiencies(this@BaseCharacter)
    val ki = Ki(this@BaseCharacter)
    val magic = Magic(this@BaseCharacter)
    val summoning = Summoning(this@BaseCharacter)
    val psychic = Psychic(this@BaseCharacter)
    val advantageRecord = AdvantageRecord(this@BaseCharacter)

    lateinit var ownClass: CharClass

    //initialize character's class and race
    var ownRace = CharRace(RaceName.human, this@BaseCharacter)

    //character's level
    var lvl = 0
    var presence: Int = 20

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

    var specInitiative = 0
    var totalInitiative = 0

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

    var fatigue = 0
    var specFatigue = 0

    var baseRegen = 0
    var specRegen = 0
    var totalRegen = 0

    var sizeSpecial = 0
    var sizeCategory = 0

    var appearance = 5

    var equippedPiece: Armor? = null
    var equippedWeapon: Weapon? = null











    fun setGender(input: Boolean){
        if(ownRace.heldRace == RaceName.dukzarist){
            if(isMale) rphysSpec -= 5
            else rmSpec -= 5
        }

        isMale = input

        if(ownRace.heldRace == RaceName.dukzarist){
            if(isMale) rphysSpec += 5
            else rmSpec += 5
        }
    }

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
        ownRace!!.raceAdvantages.forEach{
            if(it.onTake != null)
                it.onTake!!(it.picked!!, it.cost[it.pickedCost])
        }
    }

    fun removeRaceAdvantages(){
        ownRace!!.raceAdvantages.forEach{
            if(it.onRemove != null)
                it.onRemove!!(it.picked!!, it.cost[it.pickedCost])
        }
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

    private fun updatePsychicSpent(){
        ptInPsy = psychic.calculateSpent()
    }

    //setters for each primary characteristic
    var setSTR = { strVal: Int ->
        str = strVal
        updateStrValues()
    }

    @JvmName("setStrBonus1")
    fun setStrBonus(bonus: Int){
        strBonus += bonus
        updateStrValues()
    }

    fun updateStrValues(){
        totalSTR =
            if(advantageRecord.contains("Increase One Characteristic to Nine", 0, 0)) 9
            else str + strBonus
        modSTR = getModVal(totalSTR)
        updateWear()
        updateSize()

        secondaryList.updateSTR(modSTR)
        ki.updateKiStats()
    }

    var setDEX = { dexVal: Int ->
        dex = dexVal
        updateDexValues()
    }

    @JvmName("setDexBonus1")
    fun setDexBonus(bonus: Int){
        dexBonus += bonus
        updateDexValues()
    }

    fun updateDexValues(){
        totalDEX =
            if(advantageRecord.contains("Increase One Characteristic to Nine", 1, 0)) 9
            else dex + dexBonus
        modDEX = getModVal(totalDEX)

        secondaryList.updateDEX(modDEX)
        updateAttack()
        updateBlock()
        ki.updateKiStats()
        magic.calcMagProj()
        psychic.updatePsyProjection()
    }

    var setAGI = { agiVal: Int ->
        agi = agiVal
        updateAgiValues()
    }

    @JvmName("setAgiBonus1")
    fun setAgiBonus(bonus: Int){
        agiBonus += bonus
        updateAgiValues()
    }

    fun updateAgiValues(){
        totalAGI =
            if(advantageRecord.contains("Increase One Characteristic to Nine", 2, 0)) 9
            else agi + agiBonus
        modAGI = getModVal(totalAGI)

        secondaryList.updateAGI(modAGI)
        updateDodge()
        ki.updateKiStats()
    }

    var setCON = { conVal: Int ->
        con = conVal
        updateConValues()
    }

    @JvmName("setConBonus1")
    fun setConBonus(bonus: Int){
        conBonus += bonus
        updateConValues()
    }

    fun updateConValues(){
        totalCON =
            if(advantageRecord.contains("Increase One Characteristic to Nine", 3, 0)) 9
            else con + conBonus
        modCON = getModVal(totalCON)
        updateFatigue()
        getBaseRegen()
        updateSize()

        updateLifeBase()
        updateLifePoints()
        updateResistances()
        ki.updateKiStats()
    }

    var setINT = { intVal: Int ->
        int = intVal
        updateIntValues()
    }

    @JvmName("setIntBonus1")
    fun setIntBonus(bonus: Int){
        intBonus += bonus
        updateIntValues()
    }

    fun updateIntValues(){
        totalINT =
            if(advantageRecord.contains("Increase One Characteristic to Nine", 4, 0)) 9
            else int + intBonus
        modINT = getModVal(totalINT)

        secondaryList.updateINT(modINT)
        magic.setMagicLevelMax()
    }

    var setPOW = { powVal: Int ->
        pow = powVal
        updatePowValues()
    }

    @JvmName("setPowBonus1")
    fun setPowBonus(bonus: Int){
        powBonus += bonus
        updatePowValues()
    }

    fun updatePowValues(){
        totalPOW =
            if(advantageRecord.contains("Increase One Characteristic to Nine", 5, 0)) 9
            else pow + powBonus
        modPOW = getModVal(totalPOW)

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
        updateWpValues()
    }

    @JvmName("setWpBonus1")
    fun setWpBonus(bonus: Int){
        wpBonus += bonus
        updateWpValues()
    }

    fun updateWpValues(){
        totalWP =
            if(advantageRecord.contains("Increase One Characteristic to Nine", 6, 0)) 9
            else wp + wpBonus
        modWP = getModVal(totalWP)

        secondaryList.updateWP(modWP)
        updateResistances()
        ki.updateKiStats()
        summoning.updateControl()
        psychic.setBasePotential()
    }

    var setPER = { perVal: Int ->
        per += perVal
        updatePerValues()
    }

    @JvmName("setPerBonus1")
    fun setPerBonus(bonus: Int){
        perBonus = bonus
        updatePerValues()
    }

    fun updatePerValues(){
        totalPER =
            if(advantageRecord.contains("Increase One Characteristic to Nine", 7, 0)) 9
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

    //update the base number of life points based on the character's constitution
    fun updateLifeBase(){
        lifeBase = if(totalCON == 1)
            5
        else
            20 + (totalCON * 10) + modCON
    }

    //updates the number of life multiples the user is taking for their character
    fun takeLifeMult(multTake: Int){
        lifeMultsTaken = multTake
        updateTotalSpent()
        updateLifePoints()
    }

    //updates the character's total life points
    fun updateLifePoints(){
        lifeMax = lifeBase + (lifeMultsTaken * totalCON) + (ownClass.lifePointsPerLevel * lvl)
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

    fun changeSpecInitiative(changeBy: Int){
        specInitiative += changeBy
        updateInitiative()
    }

    fun updateInitiative(){
        totalInitiative = (ownClass.initiativePerLevel * lvl) + specInitiative
    }

    //updates the character's resistances
    fun updateResistances(){
        resistPhys = ((presence + modCON + rphysSpec) * rphysMult).toInt()
        resistDisease = ((presence + modCON + rdSpec) * rdMult).toInt()
        resistVen = ((presence + modCON + rvSpec) * rvMult).toInt()
        resistMag = ((presence + modPOW + rmSpec) * rmMult).toInt()
        resistPsy = ((presence + modWP + rpsySpec) * rpsyMult).toInt()
    }

    fun updateFatigue(){
        fatigue = totalCON + specFatigue
    }

    fun getBaseRegen(){
        baseRegen = when(totalCON){
            in 3..7 -> 1
            in 8..9 -> 2
            in 10..18 -> totalCON - 7
            in 19..20 -> 12
            else -> 0
        }

        updateRegeneration()
    }

    fun updateRegeneration(){
        totalRegen = baseRegen + specRegen
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
        if(advantageRecord.takenAdvantages.contains(advantageRecord.commonAdvantages.unattractive))
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

            addNewData(ownClass.heldClass.name)
            addNewData(ownRace!!.heldRace.name)
            addNewData(lvl)

            addNewData(totalSTR)
            addNewData(totalDEX)
            addNewData(totalAGI)
            addNewData(totalCON)
            addNewData(totalINT)
            addNewData(totalPOW)
            addNewData(totalWP)
            addNewData(totalPER)

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