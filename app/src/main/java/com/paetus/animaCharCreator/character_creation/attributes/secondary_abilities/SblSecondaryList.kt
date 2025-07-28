package com.paetus.animaCharCreator.character_creation.attributes.secondary_abilities

import com.paetus.animaCharCreator.character_creation.SblChar
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

/**
 * Subclass of SecondaryList for use in a SBL character.
 *
 * @param sblChar object that manages all of the character's stats
 */
class SblSecondaryList(
    val sblChar: SblChar
): SecondaryList(sblChar){
    override val acrobatics = SblSecondaryCharacteristic(parent = this, secondaryIndex = 0)
    override val athletics = SblSecondaryCharacteristic(parent = this, secondaryIndex = 1)
    override val climb = SblSecondaryCharacteristic(parent = this, secondaryIndex = 2)
    override val jump = SblSecondaryCharacteristic(parent = this, secondaryIndex = 3)
    override val ride = SblSecondaryCharacteristic(parent = this, secondaryIndex = 4)
    override val swim = SblSecondaryCharacteristic(parent = this, secondaryIndex = 5)

    override val intimidate = SblSecondaryCharacteristic(parent = this, secondaryIndex = 6)
    override val leadership = SblSecondaryCharacteristic(parent = this, secondaryIndex = 7)
    override val persuasion = SblSecondaryCharacteristic(parent = this, secondaryIndex = 8)
    override val style = SblSecondaryCharacteristic(parent = this, secondaryIndex = 9)

    override val notice = SblSecondaryCharacteristic(parent = this, secondaryIndex = 10)
    override val search = SblSecondaryCharacteristic(parent = this, secondaryIndex = 11)
    override val track = SblSecondaryCharacteristic(parent = this, secondaryIndex = 12)

    override val animals = SblSecondaryCharacteristic(parent = this, secondaryIndex = 13)
    override val appraise = SblSecondaryCharacteristic(parent = this, secondaryIndex = 14)
    override val herbalLore = SblSecondaryCharacteristic(parent = this, secondaryIndex = 15)
    override val history = SblSecondaryCharacteristic(parent = this, secondaryIndex = 16)
    override val memorize = SblSecondaryCharacteristic(parent = this, secondaryIndex = 17)
    override val magicAppraise = SblSecondaryCharacteristic(parent = this, secondaryIndex = 18)
    override val medic = SblSecondaryCharacteristic(parent = this, secondaryIndex = 19)
    override val navigate = SblSecondaryCharacteristic(parent = this, secondaryIndex = 20)
    override val occult = SblSecondaryCharacteristic(parent = this, secondaryIndex = 21)
    override val sciences = SblSecondaryCharacteristic(parent = this, secondaryIndex = 22)

    override val composure = SblSecondaryCharacteristic(parent = this, secondaryIndex = 23)
    override val strengthFeat = SblSecondaryCharacteristic(parent = this, secondaryIndex = 24)
    override val resistPain = SblSecondaryCharacteristic(parent = this, secondaryIndex = 25)

    override val disguise = SblSecondaryCharacteristic(parent = this, secondaryIndex = 26)
    override val hide = SblSecondaryCharacteristic(parent = this, secondaryIndex = 27)
    override val lockPick = SblSecondaryCharacteristic(parent = this, secondaryIndex = 28)
    override val poisons = SblSecondaryCharacteristic(parent = this, secondaryIndex = 29)
    override val theft = SblSecondaryCharacteristic(parent = this, secondaryIndex = 30)
    override val stealth = SblSecondaryCharacteristic(parent = this, secondaryIndex = 31)
    override val trapLore = SblSecondaryCharacteristic(parent = this, secondaryIndex = 32)

    override val art = SblSecondaryCharacteristic(parent = this, secondaryIndex = 33)
    override val dance = SblSecondaryCharacteristic(parent = this, secondaryIndex = 34)
    override val forging = SblSecondaryCharacteristic(parent = this, secondaryIndex = 35)
    override val music = SblSecondaryCharacteristic(parent = this, secondaryIndex = 36)
    override val sleightHand = SblSecondaryCharacteristic(parent = this, secondaryIndex = 37)

    //initialize custom characteristic field lists
    private val customSblAthletics = mutableListOf<SblCustomCharacteristic>()
    private val customSblSocials = mutableListOf<SblCustomCharacteristic>()
    private val customSblPercs = mutableListOf<SblCustomCharacteristic>()
    private val customSblIntells = mutableListOf<SblCustomCharacteristic>()
    private val customSblVigors = mutableListOf<SblCustomCharacteristic>()
    private val customSblSubs = mutableListOf<SblCustomCharacteristic>()
    private val customSblCreates = mutableListOf<SblCustomCharacteristic>()

    //initialize custom characteristic mod lists
    private val customSblSTR = mutableListOf<SblCustomCharacteristic>()
    private val customSblDEX = mutableListOf<SblCustomCharacteristic>()
    private val customSblAGI = mutableListOf<SblCustomCharacteristic>()
    private val customSblCON = mutableListOf<SblCustomCharacteristic>()
    private val customSblINT = mutableListOf<SblCustomCharacteristic>()
    private val customSblPOW = mutableListOf<SblCustomCharacteristic>()
    private val customSblWP = mutableListOf<SblCustomCharacteristic>()
    private val customSblPER = mutableListOf<SblCustomCharacteristic>()

    /**
     * Retrieves all custom characteristics available to this character.
     *
     * @return list of all custom characteristics
     */
    fun getAllSblCustoms(): List<SblCustomCharacteristic> {
        return customSblAthletics + customSblSocials + customSblPercs + customSblIntells + customSblVigors + customSblSubs + customSblCreates
    }

    /**
     * Retrieves all secondary characteristics this character has access to.
     *
     * @return all secondaries available to the character
     */
    override fun getAllSecondaries(): List<SecondaryCharacteristic> {
        return fullList() + getAllSblCustoms()
    }

    /**
     * Retrieves a secondary field based on the numerical input.
     *
     * @param fieldInteger number to convert to a secondary field
     * @return list of characteristics associated with the desired field
     */
    override fun intToField(fieldInteger: Int): List<SecondaryCharacteristic> {
        return when(fieldInteger){
            0 -> listOf(acrobatics, athletics, climb, jump, ride, swim) + customSblAthletics
            1 -> listOf(intimidate, leadership, persuasion, style) + customSblSocials
            2 -> listOf(notice, search, track) + customSblPercs
            3 -> listOf(animals, appraise, herbalLore, history, memorize, magicAppraise, medic,
                navigate, occult, sciences) + customSblIntells
            4 -> listOf(composure, strengthFeat, resistPain) + customSblVigors
            5 -> listOf(disguise, hide, lockPick, poisons, theft, stealth, trapLore) + customSblSubs
            6 -> listOf(art, dance, forging, music, sleightHand) + customSblCreates
            else -> listOf()
        }
    }

    /**
     * Attempts to toggle the natural bonus of the inputted characteristic.
     *
     * @param characteristic secondary characteristic to toggle the bonus of
     */
    override fun toggleNatBonus(characteristic: SecondaryCharacteristic) {
        //if natural bonus is currently off
        if(!characteristic.bonusApplied.value){
            //make true if characteristic is invested in and there are bonuses available
            if(countNatBonuses() < charInstance.lvl.intValue && characteristic.pointsApplied.intValue > 0)
                characteristic.setNatBonus(natBonus = true)
        }

        //if natural bonus is currently on
        else{
            //toggle off if not applied in a previous level
            if(sblChar.getCharAtLevel().secondaryList.getAllSecondaries()[(characteristic as SblSecondaryCharacteristic).secondaryIndex].bonusApplied.value)
                characteristic.setNatBonus(natBonus = false)
        }
    }

    /**
     * Applies custom secondaries from the inputted file to the character.
     *
     * @param input directory for custom secondary characteristics
     * @param filename name of the character file that may be associated with private custom characteristics
     */
    override fun applySecondaryChars(
        input: File,
        filename: String
    ){
        //for each file in this directory
        input.listFiles()?.forEach{file ->
            //initialize this file's reader
            val customInput = FileInputStream(file)
            val readCustom = InputStreamReader(customInput, StandardCharsets.UTF_8)
            val fileReader = BufferedReader(readCustom)

            //retrieve data about the custom secondary characteristic
            val valid = fileReader.readLine().toBoolean()
            val fileCheck = fileReader.readLine()

            //if characteristic is either public or private to this character
            if(valid || fileCheck == filename){
                //create custom characteristic item
                val newTech = SblCustomCharacteristic(
                    parent = this,
                    secondaryIndex = getAllSecondaries().size,
                    name = fileReader.readLine(),
                    filename = filename,
                    isPublic = valid,
                    field = fileReader.readLine().toInt(),
                    primary = fileReader.readLine().toInt()
                )

                //add characteristic to character
                addSblCustom(newSecondary = newTech)
            }

            //close reader
            customInput.close()
        }

        //apply characteristic to level records
        sblChar.levelLoop{character ->
            character.secondaryList.applySecondaryChars(input = input, filename = filename)
        }
    }

    /**
     * Adds a custom secondary characteristic to this character.
     *
     * @param newSecondary characteristic to be added
     */
    fun addSblCustom(newSecondary: SblCustomCharacteristic){
        //add the custom characteristic to the indicated field
        when(newSecondary.fieldIndex.intValue){
            //adding to athletics
            0 -> {
                addSblCustomToField(
                    newSecondary = newSecondary,
                    fieldList = customSblAthletics,
                    newGrowth = charInstance.classes.getClass().athGrowth
                )
            }

            //adding to socials
            1 -> {
                addSblCustomToField(
                    newSecondary = newSecondary,
                    fieldList = customSblSocials,
                    newGrowth = charInstance.classes.getClass().socGrowth
                )
            }

            //adding to perceptions
            2 -> {
                addSblCustomToField(
                    newSecondary = newSecondary,
                    fieldList = customSblPercs,
                    newGrowth = charInstance.classes.getClass().percGrowth
                )
            }

            //adding to intelligences
            3 -> {
                addSblCustomToField(
                    newSecondary = newSecondary,
                    fieldList = customSblIntells,
                    newGrowth = charInstance.classes.getClass().intellGrowth
                )
            }

            //adding to vigors
            4 -> {
                addSblCustomToField(
                    newSecondary = newSecondary,
                    fieldList = customSblVigors,
                    newGrowth = charInstance.classes.getClass().vigGrowth
                )
            }

            //adding to subterfuges
            5 -> {
                addSblCustomToField(
                    newSecondary = newSecondary,
                    fieldList = customSblSubs,
                    newGrowth = charInstance.classes.getClass().subterGrowth
                )
            }

            //adding to creatives
            6 -> {
                addSblCustomToField(
                    newSecondary = newSecondary,
                    fieldList = customSblCreates,
                    newGrowth = charInstance.classes.getClass().createGrowth
                )
            }

            else -> {}
        }

        //add the characteristic to their indicated primary group
        when(newSecondary.primaryCharIndex.intValue){
            //adding to strength
            0 -> {
                customSblSTR.add(element = newSecondary)
                updateSTR()
            }

            //adding to dexterity
            1 -> {
                customSblDEX.add(element = newSecondary)
                updateDEX()
            }

            //adding to agility
            2 -> {
                customSblAGI.add(element = newSecondary)
                updateAGI()
            }

            //adding to constitution
            3 -> {
                customSblCON.add(element = newSecondary)
                updateCON()
            }

            //adding to intelligence
            4 -> {
                customSblINT.add(element = newSecondary)
                updateINT()
            }

            //adding to power
            5 -> {
                customSblPOW.add(element = newSecondary)
                updatePOW()
            }

            //adding to willpower
            6 -> {
                customSblWP.add(element = newSecondary)
                updateWP()
            }

            //adding to perception
            7 -> {
                customSblPER.add(element = newSecondary)
                updatePER()
            }

            else -> {}
        }
    }

    /**
     * Adds a custom characteristic to the indicated field.
     *
     * @param newSecondary custom characteristic to add ot the field
     * @param fieldList list of custom characteristics for the indicated field
     * @param newGrowth character's class growth in that field
     */
    private fun addSblCustomToField(
        newSecondary: SblCustomCharacteristic,
        fieldList: MutableList<SblCustomCharacteristic>,
        newGrowth: Int
    ){
        //add characteristic to field and apply the appropriate development points
        fieldList.add(element = newSecondary)
        newSecondary.setDevCost(dpCost = newGrowth)

        //determine if the character has an advantage bonus in this field
        val fieldAdvantage = charInstance.advantageRecord.getAdvantage(advantageString = "fieldAptitude")

        //apply that bonus, if available
        if(fieldAdvantage != null && fieldAdvantage.picked == newSecondary.fieldIndex.intValue)
            newSecondary.setDevelopmentDeduction(dpDeduction = 1)
    }

    /**
     * Updates the relevant values of secondary characteristics when the character changes level.
     */
    fun levelUpdate() {
        getAllSecondaries() .forEach{secondary ->
            (secondary as SblSecondaryCharacteristic).bonusApplied.value = secondary.natTaken()
            secondary.pointsAppliedUpdate()
            secondary.classTotalRefresh()
        }
    }
}