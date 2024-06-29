package com.paetus.animaCharCreator.character_creation.attributes.secondary_abilities

import androidx.compose.runtime.mutableStateOf
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.character_creation.attributes.class_objects.CharClass
import com.paetus.animaCharCreator.writeDataTo
import kotlin.Throws
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

/**
 * Object that holds all of a character's secondary characteristics.
 * Keeps track of natural bonuses taken by the player.
 *
 * @param charInstance object that manages all of the character's stats
 */
open class SecondaryList(
    val charInstance: BaseCharacter
){
    //initialize held state of jack of all trades advantage
    val allTradesTaken = mutableStateOf(value = false)

    //Initialize all secondary characteristics in list
    //athletics
    open val acrobatics = SecondaryCharacteristic(parent = this)
    open val athletics = SecondaryCharacteristic(parent = this)
    open val climb = SecondaryCharacteristic(parent = this)
    open  val jump = SecondaryCharacteristic(parent = this)
    open val ride = SecondaryCharacteristic(parent = this)
    open val swim = SecondaryCharacteristic(parent = this)

    //creative
    open val art = SecondaryCharacteristic(parent = this)
    open val dance = SecondaryCharacteristic(parent = this)
    open val forging = SecondaryCharacteristic(parent = this)
    open val music = SecondaryCharacteristic(parent = this)
    open val sleightHand = SecondaryCharacteristic(parent = this)

    //perceptive
    open val notice = SecondaryCharacteristic(parent = this)
    open val search = SecondaryCharacteristic(parent = this)
    open val track = SecondaryCharacteristic(parent = this)

    //social
    open val intimidate = SecondaryCharacteristic(parent = this)
    open val leadership = SecondaryCharacteristic(parent = this)
    open val persuasion = SecondaryCharacteristic(parent = this)
    open val style = SecondaryCharacteristic(parent = this)

    //subterfuge
    open val disguise = SecondaryCharacteristic(parent = this)
    open val hide = SecondaryCharacteristic(parent = this)
    open val lockPick = SecondaryCharacteristic(parent = this)
    open val poisons = SecondaryCharacteristic(parent = this)
    open val theft = SecondaryCharacteristic(parent = this)
    open val stealth = SecondaryCharacteristic(parent = this)
    open val trapLore = SecondaryCharacteristic(parent = this)

    //intellectual
    open val animals = SecondaryCharacteristic(parent = this)
    open val appraise = SecondaryCharacteristic(parent = this)
    open val herbalLore = SecondaryCharacteristic(parent = this)
    open val history = SecondaryCharacteristic(parent = this)
    open val memorize = SecondaryCharacteristic(parent = this)
    open val magicAppraise = SecondaryCharacteristic(parent = this)
    open val medic = SecondaryCharacteristic(parent = this)
    open val navigate = SecondaryCharacteristic(parent = this)
    open val occult = SecondaryCharacteristic(parent = this)
    open val sciences = SecondaryCharacteristic(parent = this)

    //vigor
    open val composure = SecondaryCharacteristic(parent = this)
    open val strengthFeat = SecondaryCharacteristic(parent = this)
    open val resistPain = SecondaryCharacteristic(parent = this)

    private val customAthletics = mutableListOf<CustomCharacteristic>()
    private val customSocials = mutableListOf<CustomCharacteristic>()
    private val customPercs = mutableListOf<CustomCharacteristic>()
    private val customIntells = mutableListOf<CustomCharacteristic>()
    private val customVigors = mutableListOf<CustomCharacteristic>()
    private val customSubs = mutableListOf<CustomCharacteristic>()
    private val customCreates = mutableListOf<CustomCharacteristic>()

    private val customSTR = mutableListOf<CustomCharacteristic>()
    private val customDEX = mutableListOf<CustomCharacteristic>()
    private val customAGI = mutableListOf<CustomCharacteristic>()
    private val customCON = mutableListOf<CustomCharacteristic>()
    private val customINT = mutableListOf<CustomCharacteristic>()
    private val customPOW = mutableListOf<CustomCharacteristic>()
    private val customWP = mutableListOf<CustomCharacteristic>()
    private val customPER = mutableListOf<CustomCharacteristic>()

    /**
     * Retrives all secondary characteristic items in this list.
     *
     * @return list of default secondary characteristics
     */
    fun fullList(): List<SecondaryCharacteristic>{
        return listOf(acrobatics, athletics, climb, jump, ride, swim, intimidate, leadership,
            persuasion, style, notice, search, track, animals, appraise, herbalLore, history, memorize,
            magicAppraise, medic, navigate, occult, sciences, composure, strengthFeat, resistPain,
            disguise, hide, lockPick, poisons, theft, stealth, trapLore, art, dance, forging, music,
            sleightHand)
    }

    /**
     * Retrieves all custom characteristics available to this character.
     */
    fun getAllCustoms(): List<CustomCharacteristic>{
        return customAthletics + customSocials + customPercs + customIntells + customVigors + customSubs + customCreates
    }

    /**
     * Retrieves the desired custom characteristic from the name input.
     *
     * @param charName name of the custom characteristic to find
     * @return the desired characteristic, if found
     */
    private fun getCustomCharacteristic(
        charName: String
    ): CustomCharacteristic?{
        getAllCustoms().forEach{customChar ->
            if(customChar.name.value == charName) return customChar
        }

        return null
    }

    /**
     * Get the index number of the indicated custom characteristic.
     *
     * @param charName name of the custom characteristic to find
     */
    fun getCustomIndex(
        charName: String
    ): Int{
        return getAllCustoms().indexOf(getCustomCharacteristic(charName = charName))
    }

    /**
     * Retrieves all secondary characteristics this character has access to.
     */
    fun getAllSecondaries(): List<SecondaryCharacteristic>{
        return fullList() + getAllCustoms()
    }

    /**
     * Retrieves a secondary field based on the numerical input.
     *
     * @param fieldInteger number to convert to a secondary field
     * @return list of characteristics associated with the desired field
     */
    fun intToField(
        fieldInteger: Int
    ): List<SecondaryCharacteristic>{
        return when(fieldInteger){
            0 -> listOf(acrobatics, athletics, climb, jump, ride, swim) + customAthletics
            1 -> listOf(intimidate, leadership, persuasion, style) + customSocials
            2 -> listOf(notice, search, track) + customPercs
            3 -> listOf(animals, appraise, herbalLore, history, memorize, magicAppraise, medic,
                navigate, occult, sciences) + customIntells
            4 -> listOf(composure, strengthFeat, resistPain) + customVigors
            5 -> listOf(disguise, hide, lockPick, poisons, theft, stealth, trapLore) + customSubs
            6 -> listOf(art, dance, forging, music, sleightHand) + customCreates
            else -> listOf()
        }
    }

    /**
     * Attempts to toggle the natural bonus of the inputted characteristic.
     *
     * @param characteristic secondary characteristic to toggle the bonus of
     */
    open fun toggleNatBonus(characteristic: SecondaryCharacteristic){
        //if natural bonus is currently off
        if(!characteristic.bonusApplied.value){
            //make true if characteristic is invested in and there are bonuses available
            if(countNatBonuses() < charInstance.lvl.intValue && characteristic.pointsApplied.intValue > 0)
                characteristic.setNatBonus(natBonus = true)
        }

        //remove bonus if it is currently on
        else
            characteristic.setNatBonus(natBonus = false)
    }

    /**
     * Retrieves the number of currently held natural bonuses.
     *
     * @return number of natural bonuses held
     */
    fun countNatBonuses(): Int{
        //initialize counter
        var total = 0

        //check each characteristic for a held bonus
        getAllSecondaries().forEach{secondary ->
            if(secondary.bonusApplied.value) total++
        }

        //return final count
        return total
    }

    /**
     * Update values based on given class change.
     *
     * @param newClass class the character now possesses
     */
    fun classUpdate(
        newClass: CharClass
    ){
        //update field growth rates
        updateGrowth(field = 0, newCost = newClass.athGrowth)
        updateGrowth(field = 1, newCost = newClass.socGrowth)
        updateGrowth(field = 2, newCost = newClass.percGrowth)
        updateGrowth(field = 3, newCost = newClass.intellGrowth)
        updateGrowth(field = 4, newCost = newClass.vigGrowth)
        updateGrowth(field = 5, newCost = newClass.subterGrowth)
        updateGrowth(field = 6, newCost = newClass.createGrowth)

        //update all characteristic totals and expenditures
        getAllSecondaries().forEach{secondary ->
            secondary.updateDevSpent()
            secondary.refreshTotal()
        }
    }

    /**
     * Updates the development point cost of the given field.
     *
     * @param field number indicating which field to change
     * @param newCost number to set the development point cost to
     */
    private fun updateGrowth(
        field: Int,
        newCost: Int
    ){
        intToField(fieldInteger = field).forEach{secondary ->
            secondary.setDevCost(dpCost = newCost)
        }
    }

    /**
     * Update needed values based on new strength modifier.
     */
    fun updateSTR() {
        jump.setModVal(modValue = charInstance.primaryList.str.outputMod.intValue)
        strengthFeat.setModVal(modValue = charInstance.primaryList.str.outputMod.intValue)

        customSTR.forEach{customChar -> customChar.setModVal(charInstance.primaryList.str.outputMod.intValue)}
    }

    /**
     * Update needed values based on new dexterity modifier.
     */
    fun updateDEX() {
        forging.setModVal(modValue = charInstance.primaryList.dex.outputMod.intValue)
        sleightHand.setModVal(modValue = charInstance.primaryList.dex.outputMod.intValue)
        disguise.setModVal(modValue = charInstance.primaryList.dex.outputMod.intValue)
        lockPick.setModVal(modValue = charInstance.primaryList.dex.outputMod.intValue)
        theft.setModVal(modValue = charInstance.primaryList.dex.outputMod.intValue)
        trapLore.setModVal(modValue = charInstance.primaryList.dex.outputMod.intValue)

        customDEX.forEach{customChar -> customChar.setModVal(charInstance.primaryList.dex.outputMod.intValue)}
    }

    /**
     * Update needed values based on new agility modifier.
     */
    fun updateAGI() {
        acrobatics.setModVal(modValue = charInstance.primaryList.agi.outputMod.intValue)
        athletics.setModVal(modValue = charInstance.primaryList.agi.outputMod.intValue)
        climb.setModVal(modValue = charInstance.primaryList.agi.outputMod.intValue)
        ride.setModVal(modValue = charInstance.primaryList.agi.outputMod.intValue)
        swim.setModVal(modValue = charInstance.primaryList.agi.outputMod.intValue)
        dance.setModVal(modValue = charInstance.primaryList.agi.outputMod.intValue)
        stealth.setModVal(modValue = charInstance.primaryList.agi.outputMod.intValue)

        customAGI.forEach{customChar -> customChar.setModVal(charInstance.primaryList.agi.outputMod.intValue)}
    }

    /**
     * Updates needed values based on new constitution modifier.
     */
    fun updateCON(){
        customCON.forEach{customChar -> customChar.setModVal(charInstance.primaryList.con.outputMod.intValue)}
    }

    /**
     * Update needed values based on new intelligence modifier.
     */
    fun updateINT() {
        persuasion.setModVal(modValue = charInstance.primaryList.int.outputMod.intValue)
        poisons.setModVal(modValue = charInstance.primaryList.int.outputMod.intValue)
        animals.setModVal(modValue = charInstance.primaryList.int.outputMod.intValue)
        appraise.setModVal(modValue = charInstance.primaryList.int.outputMod.intValue)
        herbalLore.setModVal(modValue = charInstance.primaryList.int.outputMod.intValue)
        history.setModVal(modValue = charInstance.primaryList.int.outputMod.intValue)
        memorize.setModVal(modValue = charInstance.primaryList.int.outputMod.intValue)
        medic.setModVal(modValue = charInstance.primaryList.int.outputMod.intValue)
        navigate.setModVal(modValue = charInstance.primaryList.int.outputMod.intValue)
        occult.setModVal(modValue = charInstance.primaryList.int.outputMod.intValue)
        sciences.setModVal(modValue = charInstance.primaryList.int.outputMod.intValue)

        customINT.forEach{customChar -> customChar.setModVal(charInstance.primaryList.int.outputMod.intValue)}
    }

    /**
     * Update needed values based on new power modifier.
     */
    fun updatePOW() {
        art.setModVal(modValue = charInstance.primaryList.pow.outputMod.intValue)
        music.setModVal(modValue = charInstance.primaryList.pow.outputMod.intValue)
        leadership.setModVal(modValue = charInstance.primaryList.pow.outputMod.intValue)
        style.setModVal(modValue = charInstance.primaryList.pow.outputMod.intValue)
        magicAppraise.setModVal(modValue = charInstance.primaryList.pow.outputMod.intValue)

        customPOW.forEach{customChar -> customChar.setModVal(charInstance.primaryList.pow.outputMod.intValue)}
    }

    /**
     * Update needed values based on new willpower modifier.
     */
    fun updateWP() {
        intimidate.setModVal(modValue = charInstance.primaryList.wp.outputMod.intValue)
        composure.setModVal(modValue = charInstance.primaryList.wp.outputMod.intValue)
        resistPain.setModVal(modValue = charInstance.primaryList.wp.outputMod.intValue)

        customWP.forEach{customChar -> customChar.setModVal(charInstance.primaryList.wp.outputMod.intValue)}
    }

    /**
     * Update needed values based on new perception modifier
     */
    fun updatePER() {
        notice.setModVal(modValue = charInstance.primaryList.per.outputMod.intValue)
        search.setModVal(modValue = charInstance.primaryList.per.outputMod.intValue)
        track.setModVal(modValue = charInstance.primaryList.per.outputMod.intValue)
        hide.setModVal(modValue = charInstance.primaryList.per.outputMod.intValue)

        customPER.forEach{customChar -> customChar.setModVal(charInstance.primaryList.per.outputMod.intValue)}
    }

    /**
     * Load characteristic values from a given file reader.
     *
     * @param fileReader file to retrieve data from
     * @param writeVersion app version of the loaded file
     */
    @Throws(IOException::class)
    fun loadList(
        fileReader: BufferedReader?,
        writeVersion: Int
    ) {
        //load values for each default secondary characteristic
        fullList().forEach{characteristic -> characteristic.load(fileReader!!)}

        //for files created after custom secondaries were implemented
        if(writeVersion >= 22) {
            for (count in 0 until fileReader!!.readLine().toInt()) {

                //get the custom characteristic's name
                val customName = fileReader.readLine()

                //initialize found state of characteristic
                var found = false

                //determine if file's characteristic is accessible to this character
                getAllCustoms().forEach {customChar ->
                    //apply stats if it is
                    if (customChar.name.value == customName) {
                        customChar.load(fileReader)
                        found = true
                        return@forEach
                    }
                }

                //move past junk data if it is not
                if(!found){
                    fileReader.readLine()
                    fileReader.readLine()
                }
            }
        }
    }

    /**
     * Save characteristic values to file.
     *
     * @param byteArray output stream for the acquired data
     */
    fun writeList(byteArray: ByteArrayOutputStream) {
        //write each default characteristic's data
        fullList().forEach{secondary -> secondary.write(byteArray = byteArray)}

        //write the number of custom characteristics available
        writeDataTo(writer = byteArray, input = getAllCustoms().size)

        //write all custom characteristic data
        getAllCustoms().forEach{customChar -> customChar.write(byteArray = byteArray)}
    }

    /**
     * Determine the total number of development points spent in this section.
     *
     * @return total development points spent
     */
    fun calculateSpent(): Int{
        //initialize total points spent
        var total = 0

        //count points spent in each secondary characteristic
        getAllSecondaries().forEach{secondary -> total += secondary.pointsIn.intValue}

        //return final total
        return total
    }

    /**
     * Applies custom secondaries from the inputted file to the character.
     *
     * @param input directory for custom secondary characteristics
     * @param filename name of the character file that may be associated with private custom characteristics
     */
    fun applySecondaryChars(
        input: File,
        filename: String
    ){
        //for each file in the directory
        input.listFiles()?.forEach{file ->
            //initialize this file's reader
            val customInput = FileInputStream(file)
            val readCustom = InputStreamReader(customInput, StandardCharsets.UTF_8)
            val fileReader = BufferedReader(readCustom)

            //retrieve data about the custom secondary characteristic
            val valid = fileReader.readLine().toBoolean()
            val fileCheck = fileReader.readLine()
            val name = fileReader.readLine()
            val field = fileReader.readLine().toInt()
            val primary = fileReader.readLine().toInt()

            //if characteristic is either public or private to this character
            if (valid || fileCheck == filename) {
                //create custom characteristic item
                val newTech = CustomCharacteristic(
                    parent = this,
                    name = name,
                    filename = filename,
                    isPublic = valid,
                    field = field,
                    primary = primary
                )

                //add characteristic to character
                addCustomSecondary(newSecondary = newTech)
            }

            //close reader
            customInput.close()
        }
    }

    /**
     * Adds a custom secondary characteristic to this character.
     *
     * @param newSecondary characteristic to be added
     */
    fun addCustomSecondary(
        newSecondary: CustomCharacteristic,
    ){
        //add the custom characteristic to the indicated field
        when(newSecondary.fieldIndex.intValue){
            //adding to athletics
            0 -> {
                addCustomToField(
                    newSecondary = newSecondary,
                    fieldList = customAthletics,
                    newGrowth = charInstance.classes.getClass().athGrowth
                )
            }

            //adding to socials
            1 -> {
                addCustomToField(
                    newSecondary = newSecondary,
                    fieldList = customSocials,
                    newGrowth = charInstance.classes.getClass().socGrowth
                )
            }

            //adding to perceptions
            2 -> {
                addCustomToField(
                    newSecondary = newSecondary,
                    fieldList = customPercs,
                    newGrowth = charInstance.classes.getClass().percGrowth
                )
            }

            //adding to intelligences
            3 -> {
                addCustomToField(
                    newSecondary = newSecondary,
                    fieldList = customIntells,
                    newGrowth = charInstance.classes.getClass().intellGrowth
                )
            }

            //adding to vigors
            4 -> {
                addCustomToField(
                    newSecondary = newSecondary,
                    fieldList = customVigors,
                    newGrowth = charInstance.classes.getClass().vigGrowth
                )
            }

            //adding to subterfuges
            5 -> {
                addCustomToField(
                    newSecondary = newSecondary,
                    fieldList = customSubs,
                    newGrowth = charInstance.classes.getClass().subterGrowth
                )
            }

            //adding to creatives
            6 -> {
                addCustomToField(
                    newSecondary = newSecondary,
                    fieldList = customCreates,
                    newGrowth = charInstance.classes.getClass().createGrowth
                )
            }

            else -> {}
        }

        //add the characteristic to their indicated primary group
        when(newSecondary.primaryCharIndex.intValue){
            //adding to strength
            0 -> {
                customSTR.add(element = newSecondary)
                updateSTR()
            }

            //adding to dexterity
            1 -> {
                customDEX.add(element = newSecondary)
                updateDEX()
            }

            //adding to agility
            2 -> {
                customAGI.add(element = newSecondary)
                updateAGI()
            }

            //adding to constitution
            3 -> {
                customCON.add(element = newSecondary)
                updateCON()
            }

            //adding to intelligence
            4 -> {
                customINT.add(element = newSecondary)
                updateINT()
            }

            //adding to power
            5 -> {
                customPOW.add(element = newSecondary)
                updatePOW()
            }

            //adding to willpower
            6 -> {
                customWP.add(element = newSecondary)
                updateWP()
            }

            //adding to perception
            7 -> {
                customPER.add(element = newSecondary)
                updatePER()
            }
        }
    }

    /**
     * Adds a custom characteristic to the indicated field.
     *
     * @param newSecondary custom characteristic to add to the field
     * @param fieldList list of custom characteristics for the indicated field
     * @param newGrowth character's class growth in that field
     */
    private fun addCustomToField(
        newSecondary: CustomCharacteristic,
        fieldList: MutableList<CustomCharacteristic>,
        newGrowth: Int
    ){
        //add characteristic to field and apply the appropriate development points
        fieldList.add(element = newSecondary)
        newSecondary.setDevCost(dpCost = newGrowth)

        //determine if the character has a advantage bonus in this field
        val fieldAdvantage = charInstance.advantageRecord.getAdvantage(advantageString = "fieldAptitude")

        //apply that bonus, if available
        if(fieldAdvantage != null && fieldAdvantage.picked == newSecondary.fieldIndex.intValue){
            newSecondary.setDevelopmentDeduction(dpDeduction = 1)
        }
    }
}