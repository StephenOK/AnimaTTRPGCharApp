package com.paetus.animaCharCreator.character_creation.attributes.ki_abilities

import android.os.Build
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.enumerations.Element
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.abilities.KiAbility
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.abilities.KiRecord
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.base.PrebuiltTech
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.base.CustomTechnique
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.base.TechniqueBase
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.effect.TechniqueEffect
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.TechniquePrebuilts
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.effect.TechniqueTableData
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.effect.TechniqueTableDataRecord
import com.paetus.animaCharCreator.writeDataTo
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

/**
 * Component that manages a character's ki points and accumulation.
 * Also manages the character's ki abilities and dominion techniques.
 *
 * @param charInstance object that holds all of the character's data
 */
open class Ki(private val charInstance: BaseCharacter){
    //data table of technique effects
    val techniqueDatabase = TechniqueTableDataRecord()

    //initialize martial knowledge values
    val martialKnowledgeMax = mutableIntStateOf(value = 10)
    val martialKnowledgeSpec = mutableIntStateOf(value = 0)
    val martialKnowledgeRemaining = mutableIntStateOf(value = 10)

    //initialize stat ki points and accumulation
    val strKi = KiStat(parent = this@Ki)
    val dexKi = KiStat(parent = this@Ki)
    val agiKi = KiStat(parent = this@Ki)
    val conKi = KiStat(parent = this@Ki)
    val powKi = KiStat(parent = this@Ki)
    val wpKi = KiStat(parent = this@Ki)

    //gather all ki stat items
    private val allKiStats = listOf(strKi, dexKi, agiKi, conKi, powKi, wpKi)

    //initialize value for total ki points bought
    private val totalPointBuy = mutableIntStateOf(value = 0)

    //initialize total bought accumulation value
    private val totalAccBuy = mutableIntStateOf(value = 0)

    //initialize total ki points
    val totalKi = mutableIntStateOf(value = 30)

    //initialize total accumulation value
    val totalAcc = mutableIntStateOf(value = 6)

    //get data of ki techniques
    val kiRecord = KiRecord()

    //initialize list of taken ki abilities
    val takenAbilities = mutableListOf<KiAbility>()

    //create of map of prebuilt techniques and their taken states
    val allPrebuilts =
        TechniquePrebuilts(techniqueDataRecord = techniqueDatabase)
            .allTechniques.associateBy(
                keySelector = {it},
                valueTransform = {mutableStateOf(false)}
            )

    //initialize character's custom techniques
    val customTechniques = mutableMapOf<CustomTechnique, MutableState<Boolean>>()

    /**
     * Gets the class's ki accumulation DP cost.
     */
    fun getKiAccumulationCost(): Int{return charInstance.classes.getClass().kiAccumMult}

    /**
     * Gets the class's ki point DP cost.
     */
    fun getKiPointCost(): Int{return charInstance.classes.getClass().kiGrowth}

    /**
     * Sets martial knowledge to the appropriate amount for each taken item.
     */
    fun updateMkSpent(){
        //reset martial knowledge remaining to its maximum value
        martialKnowledgeRemaining.intValue = martialKnowledgeMax.intValue

        //removes martial knowledge for each ki ability taken
        takenAbilities.forEach{kiAbility ->
            martialKnowledgeRemaining.intValue -= kiAbility.mkCost
        }

        //removes martial knowledge for each dominion technique taken
        getTakenTechs().forEach{techniques ->
            martialKnowledgeRemaining.intValue -= techniques.mkCost()
        }
    }

    /**
     * Changes the martial knowledge bonus by the inputted amount.
     *
     * @param mkBonus value to change the martial knowledge bonus by
     */
    fun updateMKSpec(mkBonus: Int){
        martialKnowledgeSpec.intValue += mkBonus
        updateMK()
    }

    /**
     * Recalculates the character's maximum martial knowledge
     */
    open fun updateMK(){
        //determine MK gained from class levels
        val classMK =
            if(charInstance.lvl.intValue != 0) charInstance.classes.getClass().mkPerLevel * charInstance.lvl.intValue
            //give half from one level if level 0 character
            else charInstance.classes.getClass().mkPerLevel/2

        martialKnowledgeMax.intValue = classMK + charInstance.weaponProficiencies.mkFromArts() + martialKnowledgeSpec.intValue
        updateMkSpent()
    }

    /**
     * Updates the total ki points bought for each KiStat item.
     */
    fun updateBoughtPoints(){
        totalPointBuy.intValue = 0
        allKiStats.forEach{kiStat -> totalPointBuy.intValue += kiStat.boughtKiPoints.intValue}
    }

    /**
     * Updates the total ki accumulation bought for each KiStat item.
     */
    fun updateBoughtAcc(){
        totalAccBuy.intValue = 0
        allKiStats.forEach{kiStat -> totalAccBuy.intValue += kiStat.boughtAccumulation.intValue}
    }

    /**
     * Recalculates the total ki points acquired by the character.
     */
    fun updateTotalPoints(){
        totalKi.intValue = 0
        allKiStats.forEach{kiStat -> totalKi.intValue += kiStat.totalKiPoints.intValue}
    }

    /**
     * Recalculates the total ki accumulation acquired by the character.
     */
    fun updateTotalAcc(){
        totalAcc.intValue = 0
        allKiStats.forEach{kiStat -> totalAcc.intValue += kiStat.totalAccumulation.intValue}
    }

    /**
     * Determines the development points spent in ki point and accumulation purchases.
     */
    fun calculateSpent(): Int{
        var total = 0

        //add bought ki points and accumulation values
        total += totalPointBuy.intValue * charInstance.classes.getClass().kiGrowth
        total += totalAccBuy.intValue * charInstance.classes.getClass().kiAccumMult

        return total
    }

    /**
     * Attempt to add a Ki Ability to the character.
     *
     * @param newAbility Ki Ability to attempt to add
     * @return true if ability has been successfully added
     */
    fun attemptAbilityAdd(
        newAbility: KiAbility
    ): Boolean{
        //check if character has the necessary martial knowledge for the ability
        if(martialKnowledgeRemaining.intValue - newAbility.mkCost >= 0) {
            takenAbilities += newAbility
            updateMkSpent()
            return true
        }

        //indicate failed addition
        return false
    }

    /**
     * Removes the Ki Ability indicated by the user
     *
     * @param ability Ki Ability to remove
     */
    fun removeAbility(ability: KiAbility){
        //remove the item from the list
        takenAbilities -= ability

        //make sure any other ability is not disqualified by this one's removal
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            takenAbilities.removeIf{!isQualified(ability = it)}
        }
        else{
            val removeList = mutableListOf<KiAbility>()

            takenAbilities.forEach{kiAbility ->
                if(!isQualified(ability = kiAbility)) removeList += kiAbility
            }

            takenAbilities.removeAll(elements = removeList)
        }

        //remove techniques if Ki Control removed
        allPrebuilts.forEach{prebuilts -> prebuilts.value.value = false}
        customTechniques.forEach{customs -> customs.value.value = false}

        //update martial knowledge expenditure
        updateMkSpent()
    }

    /**
     * Determines if the inputted Ki Ability is valid for the character to have.
     * 
     * @param ability Ki Ability to check
     * @return true if ability is a valid addition
     */
    private fun isQualified(ability: KiAbility): Boolean{
        //return true for no prerequisites or all prerequisites met
        return ability.prerequisite == null ||
                (takenAbilities.contains(element = ability.prerequisite) &&
                        isQualified(ability = ability.prerequisite))
    }

    /**
     * Finds a ki ability based on its name.
     *
     * @param findAbility name of the ki ability to find
     * @return ki ability of the search if available
     */
    private fun getAbility(findAbility: String): KiAbility?{
        kiRecord.allKiAbilities.forEach{ability ->
            if(ability.saveTag == findAbility)
                return ability
        }

        return null
    }

    /**
     * Attempts to add a technique to the character.
     *
     * @param technique the technique to attempt to add
     * @return state of successful addition
     */
    fun attemptTechAddition(technique: TechniqueBase){
        //check if technique is purchasable
        if(martialKnowledgeRemaining.intValue - technique.mkCost() >= 0)
            //check if character is qualified to have this technique
            if(technique.level.intValue == 1 || getLevelCount(level = technique.level.intValue - 1) >= 2)
                addTechnique(technique = technique)
    }

    /**
     * Adds the inputted technique to the character.
     *
     * @param technique technique to add to the character
     */
    private fun addTechnique(technique: TechniqueBase){
        //if prebuilt technique, toggle the corresponding checkbox
        if(technique is PrebuiltTech) allPrebuilts[technique]!!.value = true

        //if custom technique
        else {
            //search for existing copy of this technique
            var found = false
            customTechniques.keys.forEach{customTech ->
                //toggle the associated checkbox if found
                if(customTech.equivalentTo(compareTo = technique)){
                    customTechniques[customTech]!!.value = true
                    found = true
                    return@forEach
                }
            }

            //add custom technique if never found
            if(!found)
                customTechniques += Pair(technique as CustomTechnique, mutableStateOf(value = true))
        }

        //update the spent martial knowledge
        updateMkSpent()
    }

    /**
     * Retrieves the number of techniques of the indicated level.
     *
     * @param level technique level to retrieve the number of
     * @return the number of techniques the character has with the indicated level
     */
    fun getLevelCount(
        level: Int
    ): Int{
        //initialize the counter
        var output = 0

        //count each technique with the desired level
        getTakenTechs().forEach{heldTech ->
            if(heldTech.level.intValue == level) output++
        }

        //return the final count
        return output
    }

    /**
     * Removes a technique from the character.
     *
     * @param technique the technique to remove from the character
     */
    fun removeTechnique(technique: TechniqueBase){
        //remove technique from the appropriate list
        if(technique is PrebuiltTech) allPrebuilts[technique]!!.value = false
        else customTechniques[technique as CustomTechnique]!!.value = false

        //remove any potential invalidated techniques after this one's removal
        removeExtra()
        updateMkSpent()
    }

    /**
     * Checks if second and third level techniques are still valid for the character to take.
     */
    private fun removeExtra(){
        //remove second level techniques if not enough first level techniques
        if(getLevelCount(1) < 2) {
            allPrebuilts.forEach{(heldPrebuilt, isHeld) ->
                if(heldPrebuilt.level.intValue == 2 && isHeld.value) isHeld.value = false
            }
            customTechniques.forEach{(heldCustom, isHeld) ->
                if(heldCustom.level.intValue == 2 && isHeld.value) isHeld.value = false
            }
        }

        //remove third level techniques if not enough second level techniques
        if(getLevelCount(2) < 2){
            allPrebuilts.forEach{(heldCustom, isHeld) ->
                if(heldCustom.level.intValue == 3 && isHeld.value) isHeld.value = false
            }
            customTechniques.forEach{(heldCustom, isHeld) ->
                if(heldCustom.level.intValue == 3 && isHeld.value) isHeld.value = false
            }
        }
    }

    /**
     * Determines if a technique from file is equivalent to a prebuilt technique.
     *
     * @param compareTo technique to check against prebuilt techniques
     * @return prebuilt technique if one matches
     */
    private fun techEquivalent(
        compareTo: TechniqueBase
    ): TechniqueBase?{
        //look through prebuilts for a match
        allPrebuilts.keys.forEach{prebuilts ->
            //return technique if match found
            if(prebuilts.equivalentTo(compareTo = compareTo))
                return prebuilts
        }

        //notify of no match found
        return null
    }

    /**
     * Finds a prebuilt technique based on the inputted string.
     *
     * @param input name key for the prebuilt technique
     * @return prebuilt technique if matching key found
     */
    private fun findPrebuilt(
        input: String
    ): PrebuiltTech?{
        //check each technique's save tag for a match
        allPrebuilts.keys.forEach{prebuilt ->
            if(prebuilt.saveName == input) return prebuilt
        }

        //notify of no match found
        return null
    }

    /**
     * Retrieves all techniques this character has access to.
     *
     * @return all techniques possessed by this character
     */
    private fun getTakenTechs(): List<TechniqueBase>{
        //initialize output
        val output = mutableListOf<TechniqueBase>()

        //get all taken prebuilt techniques
        allPrebuilts.forEach{(prebuilt, isHeld) ->
            if(isHeld.value)
                output += prebuilt
        }

        //get all taken custom techniques
        customTechniques.forEach{(custom, isHeld) ->
            if(isHeld.value)
                output += custom
        }

        //return all techniques as a list
        return output.toList()
    }

    /**
     * Loads data in regards to this section from saved file data.
     *
     * @param fileReader file to read the data from
     * @param writeVersion version of the file being read
     * @param filename name of the file being read
     */
    fun loadKiAttributes(
        fileReader: BufferedReader,
        writeVersion: Int,
        filename: String
    ){
        //set bought ki points and accumulation for each KiStat item
        allKiStats.forEach{kiStat ->
            kiStat.setBoughtKiPoints(kiPurchase = fileReader.readLine().toInt())
            kiStat.setBoughtAccumulation(accPurchase = fileReader.readLine().toInt())
        }

        //acquire saved data for ki abilities
        for(index in 0 until fileReader.readLine().toInt()){
            takenAbilities += listOf(getAbility(findAbility = fileReader.readLine())!!)
        }

        when(writeVersion){
            //version before prebuilts applied
            in 0 .. 9 ->
                loadCustomTech(
                    writeVersion = writeVersion,
                    filename = filename,
                    fileReader = fileReader
                )

            //version before custom techniques were in their own file
            in 10 .. 24 -> {
                //retrieve each applied prebuilt
                for(index in 0 until fileReader.readLine().toInt())
                    allPrebuilts[findPrebuilt(input = fileReader.readLine())!!]!!.value = true

                //load all custom techniques
                loadCustomTech(
                    writeVersion = writeVersion,
                    filename = filename,
                    fileReader = fileReader
                )
            }

            else -> {
                //retrieve each applied prebuilt
                for(index in 0 until fileReader.readLine().toInt())
                    allPrebuilts[findPrebuilt(input = fileReader.readLine())!!]!!.value = true

                //for each indicated custom technique held
                for(index in 0 until fileReader.readLine().toInt()){
                    //get the custom technique's name and held state
                    val techName = fileReader.readLine()
                    val hasFile = fileReader.readLine().toBoolean()

                    //apply held state to the indicated custom technique, if available
                    customTechniques.forEach{(custom, isHeld) ->
                        if(custom.name.value == techName)
                            isHeld.value = hasFile
                    }
                }
            }
        }

        //update spent martial knowledge
        updateMkSpent()
    }

    /**
     * Load data for a custom technique this character has.
     *
     * @param writeVersion version the read file is written in
     * @param filename name of the file being read
     * @param fileReader item reading the file
     */
    private fun loadCustomTech(
        writeVersion: Int,
        filename: String,
        fileReader: BufferedReader
    ){
        //acquire saved data for dominion techniques
        for (index in 0 until fileReader.readLine().toInt()) {
            //get technique's name
            val techName = fileReader.readLine()

            //get technique's description
            val techDesc = fileReader.readLine()

            //get technique's level
            val techLvl = fileReader.readLine().toInt()

            //get array of technique's maintenance
            val techMaint = mutableListOf(
                fileReader.readLine().toInt(),
                fileReader.readLine().toInt(),
                fileReader.readLine().toInt(),
                fileReader.readLine().toInt(),
                fileReader.readLine().toInt(),
                fileReader.readLine().toInt()
            )

            //initialize list of technique's effects
            val techEffects: MutableList<TechniqueEffect> = mutableListOf()

            for (techLoops in 0 until fileReader.readLine().toInt()) {
                techEffects +=
                    if(writeVersion <= 10) loadEffectOld(fileReader = fileReader)
                    else loadEffect(fileReader = fileReader)
            }

            //create full technique to add
            val newTech = CustomTechnique(
                name = techName,
                isPublic = true,
                fileOrigin = "",
                description = techDesc,
                level = techLvl,
                maintArray = techMaint,
                givenAbilities = techEffects
            )

            //determine if equal to prebuilt technique
            val equivalent = techEquivalent(compareTo = newTech)

            //add prebuilt technique if this item matches one
            if (equivalent != null)
                attemptTechAddition(technique = equivalent)
            //otherwise add custom technique if available to the character
            else if(newTech.isPublic.value || newTech.fileOrigin.value == filename)
                attemptTechAddition(technique = newTech)
        }
    }

    /**
     * Loads data for a technique's effect.
     *
     * @param fileReader input reader for the effect data
     * @return the technique effect from the data being read
     */
    private fun loadEffect(
        fileReader: BufferedReader
    ): TechniqueEffect{
        //get the effect's name reference
        val name = fileReader.readLine().toInt()

        //retrieve the appropriate technique table data
        val tableData =
            //retrieve data based on the saved values
            if(name != 14) techniqueDatabase.findData(
                name = name,
                primary = fileReader.readLine().toInt(),
                secondary = fileReader.readLine().toInt(),
                mkCost = fileReader.readLine().toInt()
            )
            //retrieve data for a saved state effect
            else techniqueDatabase.stateEffect(
                stateRef = fileReader.readLine().toInt(),
                costRemain = fileReader.readLine().toInt(),
                primary = fileReader.readLine().toInt()
            )!!

        //get effect build array
        val teBuild = mutableListOf(
            fileReader.readLine().toInt(),
            fileReader.readLine().toInt(),
            fileReader.readLine().toInt(),
            fileReader.readLine().toInt(),
            fileReader.readLine().toInt(),
            fileReader.readLine().toInt()
        )

        //get effect ki additions
        val teAdditions = mutableListOf(
            fileReader.readLine().toIntOrNull(),
            fileReader.readLine().toIntOrNull(),
            fileReader.readLine().toIntOrNull(),
            fileReader.readLine().toIntOrNull(),
            fileReader.readLine().toIntOrNull(),
            fileReader.readLine().toIntOrNull()
        )

        //initialize effect elements
        val teElements = mutableListOf<Element>()

        //get all saved elements
        for (effectLoops in 0 until fileReader.readLine().toInt()) {
            teElements += Element.fromString(fileReader.readLine())
        }

        //return the loaded effect
        return TechniqueEffect(
            data = tableData,
            kiBuild = teBuild,
            buildAdditions = teAdditions,
            elements = teElements
        )
    }

    /**
     * Loads data for a technique's effect for data saved before app version 10.
     *
     * @param fileReader input reader for the effect data
     * @return the technique effect from the data being read
     */
    private fun loadEffectOld(
        fileReader: BufferedReader
    ): TechniqueEffect{
        //get effect name
        var teName = nameToReference(effectName = fileReader.readLine())

        //get effect amount
        var teEffect = stringToEffectRefs(effectString = fileReader.readLine())

        //get effect cost
        var teCost = fileReader.readLine().toInt()

        //get effect maintenance
        val teMaint = fileReader.readLine().toInt()

        //get effect build costs
        val tePair = Pair(fileReader.readLine().toInt(), fileReader.readLine().toInt())

        //get effect build array
        val teBuild = mutableListOf(
            fileReader.readLine().toInt(),
            fileReader.readLine().toInt(),
            fileReader.readLine().toInt(),
            fileReader.readLine().toInt(),
            fileReader.readLine().toInt(),
            fileReader.readLine().toInt()
        )

        //get effect ki additions
        val teAdditions = mutableListOf(
            fileReader.readLine().toIntOrNull(),
            fileReader.readLine().toIntOrNull(),
            fileReader.readLine().toIntOrNull(),
            fileReader.readLine().toIntOrNull(),
            fileReader.readLine().toIntOrNull(),
            fileReader.readLine().toIntOrNull()
        )

        //initialize effect elements
        val teElements = mutableListOf<Element>()

        //get effect elements
        for (effectLoops in 0 until fileReader.readLine().toInt()) {
            teElements += Element.fromString(elementName = fileReader.readLine())
        }

        var teLevel = fileReader.readLine().toInt()

        //correct mistake in determined condition and predetermination costs
        if(teName == 38){
            teName = 37
            teEffect = Pair(R.string.determinedCondition, null)
        }
        else if (teName == 37 && teEffect.first == R.string.determinedCondition){
            teName = 38
            teEffect = Pair(R.string.predetermination, null)
            teCost = -20
            teLevel = 2
        }

        //add effect to  technique
        return TechniqueEffect(
            data = TechniqueTableData(
                name = teName,
                effectRef = teEffect.first,
                effectVal = teEffect.second,
                primaryCost = tePair.first,
                secondaryCost = tePair.second,
                mkCost = teCost,
                maintCost = teMaint,
                level = teLevel
            ),
            kiBuild = teBuild,
            buildAdditions = teAdditions,
            elements = teElements
        )
    }

    /**
     * Convert a string to its numerical reference.
     *
     * @param effectName name of the effect to convert
     * @return number representation of the effect name
     */
    private fun nameToReference(
        effectName: String
    ): Int{
        return when(effectName){
            "Attack Ability" -> 1
            "Counterattack Ability" -> 2
            "Block Ability" -> 3
            "Limited Block Ability" -> 4
            "Dodge Ability" -> 5
            "Limited Dodge Ability" -> 6
            "Damage Multiplier" -> 7
            "Damage Augmentation" -> 8
            "Additional Attack" -> 9
            "Limited Additional Attack" -> 10
            "Additional Defense" -> 11
            "Additional Action" -> 12
            "Initiative Augmentation" -> 13
            "States" -> 14
            "Combat Maneuvers and Aiming" -> 15
            "Armor Increase" -> 16
            "Armor Destruction" -> 17
            "Breakage Augmentation" -> 18
            "Fortitude Augmentation" -> 19
            "Long-Distance Attack" -> 20
            "Area Attack" -> 21
            "Automatic Transportation" -> 22
            "Critical Enhancement" -> 23
            "Physical Ki Weapon" -> 24
            "Trapping" -> 25
            "Projection" -> 26
            "Energy Shield" -> 27
            "Intangibility" -> 28
            "Mirage" -> 29
            "Attack Mirroring" -> 30
            "Energy Damaging Attack" -> 31
            "Elemental Attack" -> 32
            "Supernatural Attack" -> 33
            "Damage Resistance" -> 34
            "Elemental Binding" -> 35
            "Reduced Damage" -> 36
            "Special Requirements" -> 37
            "Predetermination" -> 38
            else -> 0
        }
    }

    /**
     * Convert an effect string to a pair for string resource use.
     *
     * @param effectString string that describes the effect
     * @return pair of integers for use in stringResource functions
     */
    private fun stringToEffectRefs(
        effectString: String
    ): Pair<Int, Int?>{
        try {
            //attempt to convert entirely to digit
            effectString.first().digitToInt()
            return Pair(R.string.justNum, effectString.toInt())
        }
        //if more values present in input
        catch(e: Exception) {
            return when (effectString.first()) {
                //with addition input
                '+' -> {
                    //attempt putting in integer with no other alterations
                    try{Pair(R.string.addNumber, effectString.drop(1).toInt())}
                    catch(e: Exception){
                        //attempt to remove only last character
                        try {
                            Pair(R.string.distanceLabelM, effectString.dropLast(1).toInt())
                        }
                        //remove last two characters
                        catch (e: Exception) {
                            Pair(R.string.distanceLabelKM, effectString.dropLast(2).toInt())
                        }
                    }
                }

                //with multiplication input, only remove first character
                'x' -> Pair(R.string.multNumber, effectString.drop(1).toInt())

                //with subtraction input
                '-' -> {
                    //attempt to remove ending character
                    try {
                        Pair(R.string.subtractNumber, effectString.drop(1).toInt())
                    }
                    //remove the last three characters
                    catch (e: Exception) {
                        Pair(R.string.subtractAT, (effectString.drop(1)).dropLast(3).toInt())
                    }
                }

                //if not easily discernible by the first character
                else ->{
                    //get string for a state effect
                    try{
                        //remove but remember potential integer notification
                        val copyRef = effectString.dropLast(4)
                        val copyVal = effectString.dropWhile{it != '('}.drop(1).toInt()

                        //get the name for the state applied
                        when(copyRef){
                            "PhR Reduction" -> Pair(R.string.physReduction, copyVal)
                            "Blindness" -> Pair(R.string.blindnessData, copyVal)
                            "Characteristic Reduction" -> Pair(R.string.charReduction, copyVal)
                            "Partial Paralysis" -> Pair(R.string.partParalyze, copyVal)
                            "Damage" -> Pair(R.string.damage, copyVal)
                            "Unconscious" -> Pair(R.string.unconscious, copyVal)
                            "Coma" -> Pair(R.string.coma, copyVal)
                            "Total Paralysis" -> Pair(R.string.totalParalyze, copyVal)
                            "Life Drain" -> Pair(R.string.lifeDrain, copyVal)
                            "Control" -> Pair(R.string.controlData, copyVal)
                            "Death" -> Pair(R.string.death, copyVal)
                            else -> Pair(0, null)
                        }
                    }

                    //item has been determined to not be a state effect
                    catch(e: Exception) {
                        //attempt to return string with no addition
                        when (effectString) {
                            "Vital Sacrifice" -> Pair(R.string.vitalSacData, null)
                            "Double Vital Sacrifice" -> Pair(R.string.doubleVitalSac, null)
                            "Health Sacrifice" -> Pair(R.string.healthSac, null)
                            "Characteristic Sacrifice" -> Pair(R.string.charSac, null)

                            "Continuous Attack" -> Pair(R.string.continuousAttack, null)

                            "Unlimited" -> Pair(R.string.unlimited, null)

                            "Added Fatigue Bonus" -> Pair(R.string.addFatigueBonus, null)

                            "Target Choice" -> Pair(R.string.targetChoice, null)
                            "Automatic Critical" -> Pair(R.string.autoCrit, null)
                            "Projectile Weapon" -> Pair(R.string.projectileWeapon, null)
                            "Intangibility" -> Pair(R.string.intangibility, null)
                            "Moderate" -> Pair(R.string.moderate, null)
                            "Difficult" -> Pair(R.string.difficult, null)
                            "Very Difficult" -> Pair(R.string.veryDifficult, null)
                            "Absurd" -> Pair(R.string.absurd, null)
                            "Almost Impossible" -> Pair(R.string.almostImpossible, null)
                            "Impossible" -> Pair(R.string.impossible, null)
                            "Inhuman" -> Pair(R.string.inhuman, null)
                            "Zen" -> Pair(R.string.zenData, null)
                            "Attack Mirroring" -> Pair(R.string.attackMirror, null)
                            "Mirror Esoteric Abilities" -> Pair(R.string.mirrorEsoteric, null)
                            "Energy" -> Pair(R.string.energyData, null)
                            "Elemental" -> Pair(R.string.elementalData, null)
                            "Single Element" -> Pair(R.string.singleElement, null)
                            "Two Elements" -> Pair(R.string.twoElement, null)
                            "No Damage" -> Pair(R.string.noDamage, null)
                            "Half Damage" -> Pair(R.string.halfDamage, null)
                            "Single Intensity" -> Pair(R.string.singleIntensity, null)
                            "Major Intensity" -> Pair(R.string.majorIntensity, null)
                            "Determined Condition" -> Pair(R.string.determinedCondition, null)
                            "Predetermination" -> Pair(R.string.predetermination, null)

                            //other input found
                            else -> {
                                //get input for distance in meters
                                try {
                                    Pair(R.string.distanceLabelM, effectString.dropLast(1).toInt())
                                }
                                //get input for distance in kilometers
                                catch (e: Exception) {
                                    Pair(R.string.distanceLabelKM, effectString.dropLast(2).toInt())
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Writes data to file for ki abilities, techniques, and purchases for ki points and accumulation.
     *
     * @param byteArray output stream to write the data to
     */
    fun writeKiAttributes(byteArray: ByteArrayOutputStream) {
        //write data from KiStats
        allKiStats.forEach{kiStat ->
            writeDataTo(writer = byteArray, input = kiStat.boughtKiPoints.intValue)
            writeDataTo(writer = byteArray, input = kiStat.boughtAccumulation.intValue)
        }

        //write number of ki abilities taken and specific abilities taken
        writeDataTo(writer = byteArray, input = takenAbilities.size)
        takenAbilities.forEach{kiAbility ->
            writeDataTo(writer = byteArray, input = kiAbility.saveTag)
        }

        //gather held prebuilt techniques
        val writePrebuilts = mutableListOf<PrebuiltTech>()
        allPrebuilts.forEach{(prebuilt, isHeld) ->
            if(isHeld.value) writePrebuilts += prebuilt
        }

        //write held prebuilts and prebuilt data
        writeDataTo(writer = byteArray, input = writePrebuilts.size)
        writePrebuilts.forEach{heldPrebuilt ->
            heldPrebuilt.write(byteArray = byteArray)
        }

        //write data on held custom techniques
        writeDataTo(writer = byteArray, input = customTechniques.size)
        customTechniques.forEach{(custom, isHeld) ->
            writeDataTo(writer = byteArray, input = custom.name.value)
            writeDataTo(writer = byteArray, input = isHeld.value)
        }
    }

    /**
     * Take data about all created custom techniques and apply them to the character if they can
     * use them.
     *
     * @param customTechDir directory that holds the custom technique data
     * @param filename name of the file associated with this character
     */
    fun applyCustomTechs(
        customTechDir: File,
        filename: String
    ){
        //for each file in the directory
        customTechDir.listFiles()?.forEach{customTechFile ->
            //open file reader
            val customInput = FileInputStream(customTechFile)
            val readCustom = InputStreamReader(customInput, StandardCharsets.UTF_8)
            val fileReader = BufferedReader(readCustom)

            //get the name of the technique
            val name = fileReader.readLine()

            //get the public state of the technique
            val public = fileReader.readLine().toBoolean()

            //get the file name of the character who created this technique
            val fileOrigin = fileReader.readLine()

            //get the description of the technique
            val description = fileReader.readLine()

            //get the level of the technique
            val level = fileReader.readLine().toInt()

            //get the maintenance array of the technique
            val maintArray = mutableListOf(
                fileReader.readLine().toInt(),
                fileReader.readLine().toInt(),
                fileReader.readLine().toInt(),
                fileReader.readLine().toInt(),
                fileReader.readLine().toInt(),
                fileReader.readLine().toInt()
            )

            //get data on the technique's effects
            val givenAbilities = mutableListOf<TechniqueEffect>()
            for(techLoops in 0 until fileReader.readLine().toInt()){
                givenAbilities += loadEffect(fileReader = fileReader)
            }

            //close file reader
            customInput.close()

            //if character can access the technique
            if(public || filename == fileOrigin){
                //add to the character's available list
                customTechniques +=
                    Pair(
                        CustomTechnique(
                            name = name,
                            isPublic = public,
                            fileOrigin = fileOrigin,
                            description = description,
                            level = level,
                            maintArray = maintArray,
                            givenAbilities = givenAbilities
                        ),
                        mutableStateOf(value = false)
                    )
            }
        }
    }

    /**
     * Saves the held status about this character's available custom techniques.
     *
     * @param directory where the data will be saved
     */
    fun saveOutCustoms(
        directory: File
    ){
        customTechniques.keys.forEach{customTech ->
            //open the file and byte writer
            val fileWriter = File(directory, customTech.name.value).outputStream()
            val byteArray = ByteArrayOutputStream()

            //write the byte data
            customTech.write(byteArray = byteArray)

            //close the byte reader
            byteArray.close()

            //output to the file writer
            fileWriter.write(byteArray.toByteArray())
            fileWriter.close()
        }
    }
}