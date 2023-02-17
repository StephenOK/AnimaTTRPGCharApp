package com.example.animabuilder.character_creation.attributes.ki_abilities

import androidx.compose.runtime.MutableState
import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.Element
import com.example.animabuilder.character_creation.attributes.ki_abilities.abilities.KiAbility
import com.example.animabuilder.character_creation.attributes.ki_abilities.abilities.KiRecord
import com.example.animabuilder.character_creation.attributes.ki_abilities.techniques.Technique
import com.example.animabuilder.character_creation.attributes.ki_abilities.techniques.TechniqueEffect
import com.example.animabuilder.character_creation.attributes.ki_abilities.techniques.TechniquePrebuilts
import java.io.BufferedReader
import java.io.Serializable

/**
 * Component that holds a character's ki abilities and dominion techniques
 * Also holds the data for default ki abilities and techniques
 */

class Ki(private val charInstance: BaseCharacter) : Serializable {
    val kiRecord = KiRecord()

    //initialize list of taken ki abilities
    val takenAbilities = mutableListOf<KiAbility>()

    /**
     * Attempt to add the Ki Ability the user desires
     *
     * newIn: Ki Ability to attempt to add
     */
    fun attemptAbilityAdd(newIn: KiAbility): Boolean{
        //check if character has the necessary martial knowledge for the ability
        if(martialKnowledgeRemaining - newIn.mkCost >= 0) {
            takenAbilities += newIn
            updateMkSpent()
            return true
        }

        //indicate failed addition
        return false
    }

    /**
     * Removes the Ki Ability as indicated by the user
     *
     * item: Ki Ability to remove
     */
    fun removeAbility(item: KiAbility){
        //remove the item from the list
        takenAbilities -= item

        //make sure any other ability is not disqualified by this one's removal
        takenAbilities.removeIf{
            it.prerequisites != null && !takenAbilities.contains(it.prerequisites)
        }

        //update martial knowledge expenditure
        updateMkSpent()
    }

    val allTechniques = TechniquePrebuilts().allTechniques

    //initialize character's techniques of each level
    val takenFirstTechniques = mutableListOf<Technique>()
    val takenSecondTechniques = mutableListOf<Technique>()
    val takenThirdTechniques = mutableListOf<Technique>()

    //initialize character's custom techniques
    val customTechniques = mutableListOf<Technique>()

    //compile all of the character's techniques
    val takenTechniques = (takenFirstTechniques + takenSecondTechniques + takenThirdTechniques).toMutableList()

    /**
     * Attempts to add a technique to the character
     *
     * input: the technique to attempt to add
     */
    fun addTechnique(input: Technique): Boolean{
        when(input.level){
            //inputting a level one technique only requires enough MK
            1 ->
                if(martialKnowledgeRemaining - input.mkCost() >= 0){
                    takenFirstTechniques += input
                    if(customCheck(input))
                        customTechniques += input
                    updateFullList()
                    return true
                }

            //inputting a level two technique requires two level one techniques
            2 ->
                if(martialKnowledgeRemaining - input.mkCost() >= 0 && takenFirstTechniques.size >= 2){
                    takenSecondTechniques += input
                    if(customCheck(input))
                        customTechniques += input
                    updateFullList()
                    return true
                }

            //inputting a level three technique requires two level two techniques
            3 ->
                if(martialKnowledgeRemaining - input.mkCost() >= 0 && takenSecondTechniques.size >= 2){
                    takenThirdTechniques += input
                    if(customCheck(input))
                        customTechniques += input
                    updateFullList()
                    return true
                }
            else -> {}
        }

        //notify of failed addition
        return false
    }

    /**
     * Determines if the technique is custom or prebuilt
     *
     * input: the technique to check
     */
    fun customCheck(input: Technique): Boolean{
        //look through all default techniques
        allTechniques.forEach{
            //return false if match found
            if(it.equivalentTo(input))
                return false
        }

        //return that the technique is custom
        return true
    }

    /**
     * Removes a technique from the character
     *
     * input: the technique to remove from the character
     */
    fun removeTechnique(input: Technique){
        when(input.level){
            1 -> takenFirstTechniques -= input
            2 -> takenSecondTechniques -= input
            3 -> takenThirdTechniques -= input
            else -> {}
        }
        customTechniques -= input
        removeExtra()
        updateFullList()
    }

    /**
     * Checks if second and third level techniques are still valid for the character to take
     */
    fun removeExtra(){
        //remove second level techniques if not enough first level techniques
        if(takenFirstTechniques.size < 2)
            takenSecondTechniques.clear()
        //remove third level techniques if not enough second level techniques
        if(takenSecondTechniques.size < 2)
            takenThirdTechniques.clear()
    }

    /**
     * Recompiles the full technique list after a change in one of them
     */
    fun updateFullList(){
        takenTechniques.clear()
        takenTechniques += takenFirstTechniques + takenSecondTechniques + takenThirdTechniques
        updateMkSpent()
    }

    /**
     * Sets martial knowledge to the appropriate amount for each taken item
     */
    fun updateMkSpent(){
        //reset martial knowledge remaining to its maximum value
        martialKnowledgeRemaining = martialKnowledgeMax

        //removes martial knowledge for each ki ability taken
        takenAbilities.forEach{
            martialKnowledgeRemaining -= it.mkCost
        }

        //removes martial knowledge for each dominion technique taken
        takenTechniques.forEach{
            martialKnowledgeRemaining -= it.mkCost()
        }
    }

    //initialize martial knowledge values
    var martialKnowledgeMax = 0
    var martialKnowledgeSpec = 0
    var martialKnowledgeRemaining = martialKnowledgeMax

    //initialize ki points for each pertinent characteristic
    var kiSTR = 0
    var kiDEX = 0
    var kiAGI = 0
    var kiCON = 0
    var kiPOW = 0
    var kiWP = 0

    //initialize total ki points
    var totalKi = 0

    //initialize ki accumulation for each pertinent characteristic
    var accSTR = 0
    var accDEX = 0
    var accAGI = 0
    var accCON = 0
    var accPOW = 0
    var accWP = 0

    //initialize total accumulation value
    var totalAcc = 0

    //initialize ki points bought for each characteristic
    var boughtStrPoint = 0
    var boughtDexPoint = 0
    var boughtAgiPoint = 0
    var boughtConPoint = 0
    var boughtPowPoint = 0
    var boughtWpPoint = 0

    fun updateMKSpec(input: Int){
        martialKnowledgeSpec += input
        updateMK()
    }

    //updates the character's martial knowledge
    fun updateMK(){
        martialKnowledgeMax = (charInstance.ownClass.mkPerLevel * charInstance.lvl) +
                charInstance.weaponProficiencies.mkFromArts() + martialKnowledgeSpec
        updateMkSpent()
    }

    //make a function for a change in each characteristic's bought point value
    val setBoughtStr = {
            input: Int,
            statFinal: MutableState<String>,
            changeDisplay: (String) -> Unit ->
        boughtStrPoint = input
        updateStr()
        updateTotals()
        statFinal.value = kiSTR.toString()
        changeDisplay(totalKi.toString())
    }

    val setBoughtDex = {
            input: Int,
            statFinal: MutableState<String>,
            changeDisplay: (String) -> Unit ->
        boughtDexPoint = input
        updateDex()
        updateTotals()
        statFinal.value = kiDEX.toString()
        changeDisplay(totalKi.toString())
    }

    val setBoughtAgi = {
            input: Int,
            statFinal: MutableState<String>,
            changeDisplay: (String) -> Unit ->
        boughtAgiPoint = input
        updateAgi()
        updateTotals()
        statFinal.value = kiAGI.toString()
        changeDisplay(totalKi.toString())
    }

    val setBoughtCon = {
            input: Int,
            statFinal: MutableState<String>,
            changeDisplay: (String) -> Unit ->
        boughtConPoint = input
        updateCon()
        updateTotals()
        statFinal.value = kiCON.toString()
        changeDisplay(totalKi.toString())
    }

    val setBoughtPow = {
            input: Int,
            statFinal: MutableState<String>,
            changeDisplay: (String) -> Unit ->
        boughtPowPoint = input
        updatePow()
        updateTotals()
        statFinal.value = kiPOW.toString()
        changeDisplay(totalKi.toString())
    }

    val setBoughtWp = {
            input: Int,
            statFinal: MutableState<String>,
            changeDisplay: (String) -> Unit ->
        boughtWpPoint = input
        updateWp()
        updateTotals()
        statFinal.value = kiWP.toString()
        changeDisplay(totalKi.toString())
    }

    //initialize value for total ki points bought
    var totalPointBuy = 0

    //initialize ki accumulation bought for each characteristic
    var boughtStrAcc = 0
    var boughtDexAcc = 0
    var boughtAgiAcc = 0
    var boughtConAcc = 0
    var boughtPowAcc = 0
    var boughtWpAcc = 0

    //make a function for a change in each characteristic's bought accumulation value
    val setStrAcc = {
            input: Int,
            statFinal: MutableState<String>,
            changeDisplay: (String) -> Unit ->
        boughtStrAcc = input
        updateStr()
        updateTotals()
        statFinal.value = accSTR.toString()
        changeDisplay(totalAcc.toString())
    }

    val setDexAcc = {
            input: Int,
            statFinal: MutableState<String>,
            changeDisplay: (String) -> Unit ->
        boughtDexAcc = input
        updateDex()
        updateTotals()
        statFinal.value = accDEX.toString()
        changeDisplay(totalAcc.toString())
    }

    val setAgiAcc = {
            input: Int,
            statFinal: MutableState<String>,
            changeDisplay: (String) -> Unit ->
        boughtAgiAcc = input
        updateAgi()
        updateTotals()
        statFinal.value = accAGI.toString()
        changeDisplay(totalAcc.toString())
    }

    val setConAcc = {
            input: Int,
            statFinal: MutableState<String>,
            changeDisplay: (String) -> Unit ->
        boughtConAcc = input
        updateCon()
        updateTotals()
        statFinal.value = accCON.toString()
        changeDisplay(totalAcc.toString())
    }

    val setPowAcc = {
            input: Int,
            statFinal: MutableState<String>,
            changeDisplay: (String) -> Unit ->
        boughtPowAcc = input
        updatePow()
        updateTotals()
        statFinal.value = accPOW.toString()
        changeDisplay(totalAcc.toString())
    }

    val setWpAcc = {
            input: Int,
            statFinal: MutableState<String>,
            changeDisplay: (String) -> Unit ->
        boughtWpAcc = input
        updateWp()
        updateTotals()
        statFinal.value = accWP.toString()
        changeDisplay(totalAcc.toString())
    }

    //initialize total bought accumulation value
    var totalAccBuy = 0

    /**
     * Updates the ki values for stats when a primary characteristic changes
     */
    fun updateKiStats() {
        updateStr()
        updateDex()
        updateAgi()
        updateCon()
        updatePow()
        updateWp()

        updateTotals()
    }

    /**
     * Updates ki for the strength characteristic
     */
    fun updateStr() {
        kiSTR = getStatKi(charInstance.primaryList.str.total) + boughtStrPoint
        accSTR = getStatKiAcc(charInstance.primaryList.str.total) + boughtStrAcc
    }

    /**
     * Updates ki for the dexterity characteristic
     */
    fun updateDex() {
        kiDEX = getStatKi(charInstance.primaryList.dex.total) + boughtDexPoint
        accDEX = getStatKiAcc(charInstance.primaryList.dex.total) + boughtDexAcc
    }

    /**
     * Updates ki for the agility characteristic
     */
    fun updateAgi() {
        kiAGI = getStatKi(charInstance.primaryList.agi.total) + boughtAgiPoint
        accAGI = getStatKiAcc(charInstance.primaryList.agi.total) + boughtAgiAcc
    }

    /**
     * Updates ki for the constitution characteristic
     */
    fun updateCon() {
        kiCON = getStatKi(charInstance.primaryList.con.total) + boughtConPoint
        accCON =  getStatKiAcc(charInstance.primaryList.con.total) + boughtConAcc
    }

    /**
     * Updates ki for the power characteristic
     */
    fun updatePow() {
        kiPOW = getStatKi(charInstance.primaryList.pow.total) + boughtPowPoint
        accPOW = getStatKiAcc(charInstance.primaryList.pow.total) + boughtPowAcc
    }

    /**
     * Updates ki for the willpower characteristic
     */
    fun updateWp() {
        kiWP = getStatKi(charInstance.primaryList.wp.total) + boughtWpPoint
        accWP = getStatKiAcc(charInstance.primaryList.wp.total) + boughtWpAcc
    }

    /**
     * Updates all totals in relation to ki points and accumulation
     */
    fun updateTotals() {
        totalKi = kiSTR + kiDEX + kiAGI + kiCON + kiPOW + kiWP
        totalAcc = accSTR + accDEX + accAGI + accCON + accPOW + accWP

        totalPointBuy = boughtStrPoint + boughtDexPoint + boughtAgiPoint + boughtConPoint + boughtPowPoint + boughtWpPoint
        totalAccBuy = boughtStrAcc + boughtDexAcc + boughtAgiAcc + boughtConAcc + boughtPowAcc + boughtWpAcc
        charInstance.updateTotalSpent()
    }

    /**
     * Gets the ki point value from the inputted stat value
     *
     * input: value of the primary characteristic
     */
    fun getStatKi(input: Int): Int {
        return if (input <= 10)
                input
            else
                10 + ((input - 10) * 2)
    }

    /**
     * Gets the ki accumulation value from the inputted stat value
     *
     * input: value of the primary characteristic
     */
    fun getStatKiAcc(input: Int): Int{
        return if(input <= 9)
                1
            else if (input in 10 .. 12)
                2
            else if(input in 13 .. 15)
                3
            else 4
    }

    /**
     * Determines the development points spent in ki point and accumulation purchases
     */
    fun calculateSpent(): Int{
        var total = 0

        total += totalPointBuy * charInstance.ownClass.kiGrowth
        total += totalAccBuy * charInstance.ownClass.kiAccumMult

        return total
    }


    /**
     * Loads data in regards to this section from saved file data
     */
    fun loadKiAttributes(fileReader: BufferedReader){
        boughtStrPoint = fileReader.readLine().toInt()
        boughtStrAcc = fileReader.readLine().toInt()
        boughtDexPoint = fileReader.readLine().toInt()
        boughtDexAcc = fileReader.readLine().toInt()
        boughtAgiPoint = fileReader.readLine().toInt()
        boughtAgiAcc = fileReader.readLine().toInt()
        boughtConPoint = fileReader.readLine().toInt()
        boughtConAcc = fileReader.readLine().toInt()
        boughtPowPoint = fileReader.readLine().toInt()
        boughtPowAcc = fileReader.readLine().toInt()
        boughtWpPoint = fileReader.readLine().toInt()
        boughtWpAcc = fileReader.readLine().toInt()

        var loops = fileReader.readLine().toInt()

        while(loops > 0){
            takenAbilities += listOf(getAbility(fileReader.readLine())!!)
            loops--
        }

        loops = fileReader.readLine().toInt()

        while(loops > 0){
            val techName = fileReader.readLine()
            val techDesc = fileReader.readLine()
            val techLvl = fileReader.readLine().toInt()
            val techMaint = mutableListOf(
                fileReader.readLine().toInt(),
                fileReader.readLine().toInt(),
                fileReader.readLine().toInt(),
                fileReader.readLine().toInt(),
                fileReader.readLine().toInt(),
                fileReader.readLine().toInt()
            )
            val techEffects: MutableList<TechniqueEffect> = mutableListOf()

            var techLoops = fileReader.readLine().toInt()

            while(techLoops > 0){
                val teName = fileReader.readLine()
                val teEffect = fileReader.readLine()
                val teCost = fileReader.readLine().toInt()
                val teMaint = fileReader.readLine().toInt()

                val tePair = Pair(fileReader.readLine().toInt(), fileReader.readLine().toInt())

                val teBuild = mutableListOf(
                    fileReader.readLine().toInt(),
                    fileReader.readLine().toInt(),
                    fileReader.readLine().toInt(),
                    fileReader.readLine().toInt(),
                    fileReader.readLine().toInt(),
                    fileReader.readLine().toInt()
                )

                val teAdditions = mutableListOf(
                    fileReader.readLine().toIntOrNull(),
                    fileReader.readLine().toIntOrNull(),
                    fileReader.readLine().toIntOrNull(),
                    fileReader.readLine().toIntOrNull(),
                    fileReader.readLine().toIntOrNull(),
                    fileReader.readLine().toIntOrNull()
                )

                val teElements = mutableListOf<Element>()

                var effectLoops = fileReader.readLine().toInt()
                while(effectLoops > 0){
                    teElements += Element.fromString(fileReader.readLine())
                    effectLoops--
                }

                techEffects +=
                        TechniqueEffect(
                            teName,
                            teEffect,
                            teCost,
                            teMaint,
                            tePair,
                            teBuild,
                            teAdditions,
                            teElements,
                            fileReader.readLine().toInt()
                        )

                techLoops--
            }

            val newTech = Technique(techName, techDesc, techLvl, techMaint, techEffects)

            if(techEquivalent(newTech).first)
                addTechnique(techEquivalent(newTech).second!!)
            else
                addTechnique(newTech)

            loops--
        }

        updateMkSpent()
    }

    /**
     * Writes data to file for ki abilities, techniques, and purchases for ki points and accumulation
     */
    fun writeKiAttributes() {
        charInstance.addNewData(boughtStrPoint)
        charInstance.addNewData(boughtStrAcc)
        charInstance.addNewData(boughtDexPoint)
        charInstance.addNewData(boughtDexAcc)
        charInstance.addNewData(boughtAgiPoint)
        charInstance.addNewData(boughtAgiAcc)
        charInstance.addNewData(boughtConPoint)
        charInstance.addNewData(boughtConAcc)
        charInstance.addNewData(boughtPowPoint)
        charInstance.addNewData(boughtPowAcc)
        charInstance.addNewData(boughtWpPoint)
        charInstance.addNewData(boughtWpAcc)

        charInstance.addNewData(takenAbilities.size)
        takenAbilities.forEach{
            charInstance.addNewData(it.name)
        }

        charInstance.addNewData(takenTechniques.size)
        takenTechniques.forEach{
            it.write(charInstance)
        }
    }

    /**
     * Finds a ki ability based on its name
     *
     * toFind: name of the ki ability to find
     */
    fun getAbility(toFind: String): KiAbility?{
        kiRecord.allKiAbilities.forEach{
            if(it.name == toFind)
                return it
        }

        return null
    }

    /**
     * Determines if a technique from file is equivalent to a prebuilt technique
     *
     * compareTo: technique to check against prebuilt techniques
     */
    fun techEquivalent(compareTo: Technique): Pair<Boolean, Technique?>{
        allTechniques.forEach{
            if(it.equivalentTo(compareTo))
                return Pair(true, it)
        }

        return Pair(false, null)
    }
}