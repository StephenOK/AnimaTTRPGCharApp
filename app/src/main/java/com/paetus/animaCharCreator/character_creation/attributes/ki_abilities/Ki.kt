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
 * @param charInstance object that holds all of the character's data.
 */
class Ki(private val charInstance: BaseCharacter){
    //data table of technique effects
    val techniqueDatabase = TechniqueTableDataRecord()

    //initialize martial knowledge values
    val martialKnowledgeMax = mutableIntStateOf(0)
    val martialKnowledgeSpec = mutableIntStateOf(0)
    val martialKnowledgeRemaining = mutableIntStateOf(0)

    //initialize stat ki points and accumulation
    val strKi = KiStat(this@Ki)
    val dexKi = KiStat(this@Ki)
    val agiKi = KiStat(this@Ki)
    val conKi = KiStat(this@Ki)
    val powKi = KiStat(this@Ki)
    val wpKi = KiStat(this@Ki)

    //gather all ki stat items
    val allKiStats = listOf(strKi, dexKi, agiKi, conKi, powKi, wpKi)

    //initialize value for total ki points bought
    val totalPointBuy = mutableIntStateOf(0)

    //initialize total bought accumulation value
    val totalAccBuy = mutableIntStateOf(0)

    //initialize total ki points
    val totalKi = mutableIntStateOf(0)

    //initialize total accumulation value
    val totalAcc = mutableIntStateOf(0)

    //get data of ki techniques
    val kiRecord = KiRecord()

    //initialize list of taken ki abilities
    val takenAbilities = mutableListOf<KiAbility>()

    //get all prebuilt techniques
    val allPrebuilts = TechniquePrebuilts().allTechniques.associateBy({it}, {mutableStateOf(false)})

    //initialize character's custom techniques
    val customTechniques = mutableMapOf<CustomTechnique, MutableState<Boolean>>()

    /**
     * Sets martial knowledge to the appropriate amount for each taken item.
     */
    fun updateMkSpent(){
        //reset martial knowledge remaining to its maximum value
        martialKnowledgeRemaining.intValue = martialKnowledgeMax.intValue

        //removes martial knowledge for each ki ability taken
        takenAbilities.forEach{
            martialKnowledgeRemaining.intValue -= it.mkCost
        }

        //removes martial knowledge for each dominion technique taken
        getTakenTechs().forEach{
            martialKnowledgeRemaining.intValue -= it.mkCost()
        }
    }

    /**
     * Changes the martial knowledge bonus by the inputted amount.
     *
     * @param input value to change the martial knowledge bonus by
     */
    fun updateMKSpec(input: Int){
        martialKnowledgeSpec.intValue += input
        updateMK()
    }

    /**
     * Recalculates the character's maximum martial knowledge
     */
    fun updateMK(){
        //determine MK gained from class levels
        val classMK =
            if(charInstance.lvl.value != 0) charInstance.ownClass.value.mkPerLevel * charInstance.lvl.value
            //give half from one level if level 0 character
            else charInstance.ownClass.value.mkPerLevel/2

        martialKnowledgeMax.intValue = classMK + charInstance.weaponProficiencies.mkFromArts() + martialKnowledgeSpec.intValue
        updateMkSpent()
    }

    /**
     * Updates the total ki points bought for each KiStat item.
     */
    fun updateBoughtPoints(){
        totalPointBuy.intValue = 0
        allKiStats.forEach{totalPointBuy.intValue += it.boughtKiPoints.value}
    }

    /**
     * Updates the total ki accumulation bought for each KiStat item.
     */
    fun updateBoughtAcc(){
        totalAccBuy.intValue = 0
        allKiStats.forEach{totalAccBuy.intValue += it.boughtAccumulation.value}
    }

    /**
     * Recalculates the total ki points acquired by the character.
     */
    fun updateTotalPoints(){
        totalKi.intValue = 0
        allKiStats.forEach{totalKi.intValue += it.totalKiPoints.value}
    }

    /**
     * Recalculates the total ki accumulation acquired by the character.
     */
    fun updateTotalAcc(){
        totalAcc.intValue = 0
        allKiStats.forEach{totalAcc.intValue += it.totalAccumulation.value}
    }

    /**
     * Determines the development points spent in ki point and accumulation purchases.
     */
    fun calculateSpent(): Int{
        var total = 0

        //add bought ki points and accumulation values
        total += totalPointBuy.intValue * charInstance.ownClass.value.kiGrowth
        total += totalAccBuy.intValue * charInstance.ownClass.value.kiAccumMult

        return total
    }

    /**
     * Attempt to add a Ki Ability to the character.
     *
     * @param newIn Ki Ability to attempt to add
     * @return true if ability has been successfully added
     */
    fun attemptAbilityAdd(newIn: KiAbility): Boolean{
        //check if character has the necessary martial knowledge for the ability
        if(martialKnowledgeRemaining.intValue - newIn.mkCost >= 0) {
            takenAbilities += newIn
            updateMkSpent()
            return true
        }

        //indicate failed addition
        return false
    }

    /**
     * Removes the Ki Ability indicated by the user
     *
     * @param item Ki Ability to remove
     */
    fun removeAbility(item: KiAbility){
        //remove the item from the list
        takenAbilities -= item

        //make sure any other ability is not disqualified by this one's removal
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            takenAbilities.removeIf{!isQualified(it)}
        }
        else{
            val removeList = mutableListOf<KiAbility>()

            takenAbilities.forEach{
                if(!isQualified(it)) removeList += it
            }

            takenAbilities.removeAll(removeList)
        }

        //remove techniques if Ki Control removed
        allPrebuilts.forEach{it.value.value = false}
        customTechniques.forEach{it.value.value = false}

        //update martial knowledge expenditure
        updateMkSpent()
    }

    /**
     * Determines if the inputted Ki Ability is valid for the character to have.
     * 
     * @param input Ki Ability to check
     * @return true if ability is a valid addition
     */
    fun isQualified(input: KiAbility): Boolean{
        //return true for no prerequisites or all prerequisites met
        return input.prerequisites == null ||
                (takenAbilities.contains(input.prerequisites) && isQualified(input.prerequisites))
    }

    /**
     * Finds a ki ability based on its name.
     *
     * @param toFind name of the ki ability to find
     * @return ki ability of the search if available
     */
    fun getAbility(toFind: String): KiAbility?{
        kiRecord.allKiAbilities.forEach{
            if(it.saveTag == toFind)
                return it
        }

        return null
    }

    /**
     * Attempts to add a technique to the character.
     *
     * @param input the technique to attempt to add
     * @return state of successful addition
     */
    fun attemptTechAddition(input: TechniqueBase){
        if(martialKnowledgeRemaining.intValue - input.mkCost() >= 0)
            if(input.level.intValue == 1 || getLevelCount(input.level.intValue - 1) >= 2)
                addTechnique(input)
    }

    fun addTechnique(input: TechniqueBase){
        if(input is PrebuiltTech) allPrebuilts[input]!!.value = true
        else {
            var found = false
            customTechniques.forEach{
                if(it.key.equivalentTo(input)){
                    customTechniques[it.key]!!.value = true
                    found = true
                    return@forEach
                }
            }

            if(!found)
                customTechniques += Pair(input as CustomTechnique, mutableStateOf(true))
        }

        updateMkSpent()
    }

    fun getLevelCount(level: Int): Int{
        var output = 0
        getTakenTechs().forEach{
            if(it.level.intValue == level) output++
        }

        return output
    }

    /**
     * Removes a technique from the character.
     *
     * @param input the technique to remove from the character
     */
    fun removeTechnique(input: TechniqueBase){
        //remove technique from the appropriate list
        if(input is PrebuiltTech) allPrebuilts[input]!!.value = false
        else customTechniques[input as CustomTechnique]!!.value = false

        //remove any potential invalid techniques after this one's removal
        removeExtra()
        updateMkSpent()
    }

    /**
     * Checks if second and third level techniques are still valid for the character to take.
     */
    fun removeExtra(){
        //remove second level techniques if not enough first level techniques
        if(getLevelCount(1) < 2) {
            allPrebuilts.forEach{
                if(it.key.level.intValue == 2 && it.value.value) it.value.value = false
            }
            customTechniques.forEach{
                if(it.key.level.intValue == 2 && it.value.value) it.value.value = false
            }
        }

        //remove third level techniques if not enough second level techniques
        if(getLevelCount(2) < 2){
            allPrebuilts.forEach{
                if(it.key.level.intValue == 3 && it.value.value) it.value.value = false
            }
            customTechniques.forEach{
                if(it.key.level.intValue == 3 && it.value.value) it.value.value = false
            }
        }
    }

    /**
     * Determines if a technique from file is equivalent to a prebuilt technique.
     *
     * @param compareTo technique to check against prebuilt techniques
     * @return prebuilt technique if one matches
     */
    fun techEquivalent(compareTo: TechniqueBase): TechniqueBase?{
        allPrebuilts.forEach{
            if(it.key.equivalentTo(compareTo))
                return it.key
        }

        return null
    }

    fun findPrebuilt(input: String): PrebuiltTech?{
        allPrebuilts.forEach{
            if(it.key.saveName == input) return it.key
        }

        return null
    }

    fun getTakenTechs(): List<TechniqueBase>{
        val output = mutableListOf<TechniqueBase>()

        allPrebuilts.forEach{
            if(it.value.value)
                output += it.key
        }

        customTechniques.forEach{
            if(it.value.value)
                output += it.key
        }

        return output.toList()
    }

    /**
     * Loads data in regards to this section from saved file data.
     *
     * @param fileReader file to read the data from
     */
    fun loadKiAttributes(
        fileReader: BufferedReader,
        writeVersion: Int,
        filename: String
    ){
        //set bought ki points and accumulation for each KiStat item
        allKiStats.forEach{
            it.setBoughtKiPoints(fileReader.readLine().toInt())
            it.setBoughtAccumulation(fileReader.readLine().toInt())
        }

        //acquire saved data for ki abilities
        for(index in 0 until fileReader.readLine().toInt()){
            takenAbilities += listOf(getAbility(fileReader.readLine())!!)
        }

        when(writeVersion){
            in 0 .. 9 -> loadCustomTech(writeVersion, filename, fileReader)
            in 10 .. 24 -> {
                for(index in 0 until fileReader.readLine().toInt())
                    allPrebuilts[findPrebuilt(fileReader.readLine())!!]!!.value = true

                loadCustomTech(writeVersion, filename, fileReader)
            }
            else -> {
                for(index in 0 until fileReader.readLine().toInt())
                    allPrebuilts[findPrebuilt(fileReader.readLine())!!]!!.value = true

                for(index in 0 until fileReader.readLine().toInt()){
                    val techName = fileReader.readLine()
                    val hasFile = fileReader.readLine().toBoolean()

                    customTechniques.forEach{
                        if(it.key.name.value == techName)
                            it.value.value = hasFile
                    }
                }

                updateMkSpent()
            }
        }

        updateMkSpent()
    }

    fun loadCustomTech(
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
                    if(writeVersion <= 10) loadEffectOld(fileReader)
                    else loadEffect(fileReader)
            }

            //create full technique to add
            val newTech = CustomTechnique(
                techName,
                true,
                "",
                techDesc,
                techLvl,
                techMaint,
                techEffects
            )

            val equivalent = techEquivalent(newTech)

            //add prebuilt technique if this item matches one
            if (equivalent != null)
                attemptTechAddition(equivalent)
            else if(newTech.isPublic.value || newTech.fileOrigin.value == filename)
                attemptTechAddition(newTech)
        }
    }

    fun loadEffect(fileReader: BufferedReader): TechniqueEffect{
        val name = fileReader.readLine().toInt()

        val tableData =
            if(name != 14) techniqueDatabase.findData(name, fileReader.readLine().toInt(), fileReader.readLine().toInt(), fileReader.readLine().toInt())
            else techniqueDatabase.stateEffect(fileReader.readLine().toInt(), fileReader.readLine().toInt(), fileReader.readLine().toInt())!!

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

        for (effectLoops in 0 until fileReader.readLine().toInt()) {
            teElements += Element.fromString(fileReader.readLine())
        }

        return TechniqueEffect(
            tableData,
            teBuild,
            teAdditions,
            teElements
        )
    }

    fun loadEffectOld(fileReader: BufferedReader): TechniqueEffect{
        //get effect name
        var teName = nameToReference(fileReader.readLine())

        //get effect amount
        var teEffect = stringToEffectRefs(fileReader.readLine())

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
            teElements += Element.fromString(fileReader.readLine())
        }

        var teLevel = fileReader.readLine().toInt()

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
                TechniqueTableData(
                    teName,
                    teEffect.first,
                    teEffect.second,
                    tePair.first,
                    tePair.second,
                    teCost,
                    teMaint,
                    teLevel
                ),
                teBuild,
                teAdditions,
                teElements
            )
    }

    fun nameToReference(input: String): Int{
        return when(input){
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

    fun stringToEffectRefs(input: String): Pair<Int, Int?>{
        try {
            input.first().digitToInt()
            return Pair(R.string.justNum, input.toInt())
        }
        catch(e: Exception) {
            return when (input.first()) {
                '+' -> {
                    try{Pair(R.string.addNumber, input.drop(1).toInt())}
                    catch(e: Exception){
                        try {
                            Pair(R.string.distanceLabelM, input.dropLast(1).toInt())
                        } catch (e: Exception) {
                            Pair(R.string.distanceLabelKM, input.dropLast(2).toInt())
                        }
                    }
                }
                'x' -> Pair(R.string.multNumber, input.drop(1).toInt())
                '-' -> {
                    try {
                        Pair(R.string.subtractNumber, input.drop(1).toInt())
                    } catch (e: Exception) {
                        Pair(R.string.subtractAT, (input.drop(1)).dropLast(3).toInt())
                    }
                }
                else ->{
                    try{
                        val copyRef = input.dropLast(4)
                        val copyVal = input.dropWhile{it != '('}.drop(1).toInt()
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
                    }catch(e: Exception) {
                        when (input) {
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
                            else -> {
                                try {
                                    Pair(R.string.distanceLabelM, input.dropLast(1).toInt())
                                } catch (e: Exception) {
                                    Pair(R.string.distanceLabelKM, input.dropLast(2).toInt())
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Writes data to file for ki abilities, techniques, and purchases for ki points and accumulation
     */
    fun writeKiAttributes(byteArray: ByteArrayOutputStream) {
        //write data from KiStats
        allKiStats.forEach{
            writeDataTo(byteArray, it.boughtKiPoints.value)
            writeDataTo(byteArray, it.boughtAccumulation.value)
        }

        //write number of ki abilities taken and specific abilities taken
        writeDataTo(byteArray, takenAbilities.size)
        takenAbilities.forEach{
            writeDataTo(byteArray, it.saveTag)
        }

        val writePrebuilts = mutableListOf<PrebuiltTech>()
        allPrebuilts.forEach{
            if(it.value.value) writePrebuilts += it.key
        }

        writeDataTo(byteArray, writePrebuilts.size)
        writePrebuilts.forEach{
            it.write(byteArray)
        }

        writeDataTo(byteArray, customTechniques.size)
        customTechniques.forEach{
            writeDataTo(byteArray, it.key.name.value)
            writeDataTo(byteArray, it.value.value)
        }
    }

    fun applyCustomTechs(
        input: File,
        filename: String
    ){
        input.walk().forEach{
            if(it != input){
                val customInput = FileInputStream(it)
                val readCustom = InputStreamReader(customInput, StandardCharsets.UTF_8)
                val fileReader = BufferedReader(readCustom)

                val name = fileReader.readLine()
                val public = fileReader.readLine().toBoolean()
                val fileOrigin = fileReader.readLine()
                val description = fileReader.readLine()
                val level = fileReader.readLine().toInt()

                val maintArray = mutableListOf(
                    fileReader.readLine().toInt(),
                    fileReader.readLine().toInt(),
                    fileReader.readLine().toInt(),
                    fileReader.readLine().toInt(),
                    fileReader.readLine().toInt(),
                    fileReader.readLine().toInt()
                )

                val givenAbilities = mutableListOf<TechniqueEffect>()
                for(techLoops in 0 until fileReader.readLine().toInt()){
                    givenAbilities += loadEffect(fileReader)
                }

                customInput.close()

                if(public || filename == fileOrigin){
                    customTechniques +=
                        Pair(
                            CustomTechnique(
                                name,
                                public,
                                fileOrigin,
                                description,
                                level,
                                maintArray,
                                givenAbilities
                            ),
                            mutableStateOf(false)
                        )
                }
            }
        }
    }

    fun saveOutCustoms(
        directory: File
    ){
        customTechniques.keys.forEach{
            val fileWriter = File(directory, it.name.value).outputStream()
            val byteArray = ByteArrayOutputStream()
            it.write(byteArray)
            byteArray.close()

            fileWriter.write(byteArray.toByteArray())
            fileWriter.close()
        }
    }
}