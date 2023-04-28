package com.example.animabuilder.character_creation.attributes.ki_abilities

import androidx.compose.runtime.mutableStateOf
import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.Element
import com.example.animabuilder.character_creation.attributes.ki_abilities.abilities.KiAbility
import com.example.animabuilder.character_creation.attributes.ki_abilities.abilities.KiRecord
import com.example.animabuilder.character_creation.attributes.ki_abilities.techniques.Technique
import com.example.animabuilder.character_creation.attributes.ki_abilities.techniques.TechniqueEffect
import com.example.animabuilder.character_creation.attributes.ki_abilities.techniques.TechniquePrebuilts
import java.io.BufferedReader

/**
 * Component that manages a character's ki points and accumulation.
 * Also manages the character's ki abilities and dominion techniques.
 *
 * @param charInstance object that holds all of the character's data.
 */
class Ki(private val charInstance: BaseCharacter){
    //get data of ki techniques
    val kiRecord = KiRecord()

    //initialize list of taken ki abilities
    val takenAbilities = mutableListOf<KiAbility>()

    /**
     * Attempt to add a Ki Ability to the character.
     *
     * @param newIn Ki Ability to attempt to add
     * @return true if ability has been successfully added
     */
    fun attemptAbilityAdd(newIn: KiAbility): Boolean{
        //check if character has the necessary martial knowledge for the ability
        if(martialKnowledgeRemaining.value - newIn.mkCost >= 0) {
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
        takenAbilities.removeIf{
            it.prerequisites != null && !takenAbilities.contains(it.prerequisites)
        }

        //update martial knowledge expenditure
        updateMkSpent()
    }

    //get all prebuilt techniques
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
     * Attempts to add a technique to the character.
     *
     * @param input the technique to attempt to add
     * @return state of successful addition
     */
    fun addTechnique(input: Technique): Boolean{
        when(input.level){
            //inputting a level one technique only requires enough MK
            1 ->
                if(martialKnowledgeRemaining.value - input.mkCost() >= 0){
                    takenFirstTechniques += input
                    if(customCheck(input))
                        customTechniques += input
                    updateFullList()
                    return true
                }

            //inputting a level two technique requires two level one techniques
            2 ->
                if(martialKnowledgeRemaining.value - input.mkCost() >= 0 && takenFirstTechniques.size >= 2){
                    takenSecondTechniques += input
                    if(customCheck(input))
                        customTechniques += input
                    updateFullList()
                    return true
                }

            //inputting a level three technique requires two level two techniques
            3 ->
                if(martialKnowledgeRemaining.value - input.mkCost() >= 0 && takenSecondTechniques.size >= 2){
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
     * Determines if the technique is custom or prebuilt.
     *
     * @param input the technique to check
     * @return true if technique is custom
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
     * Removes a technique from the character.
     *
     * @param input the technique to remove from the character
     */
    fun removeTechnique(input: Technique){
        //remove the technique from the appropriate level bundle
        when(input.level){
            1 -> takenFirstTechniques -= input
            2 -> takenSecondTechniques -= input
            3 -> takenThirdTechniques -= input
            else -> {}
        }

        //remove from custom technique list if it is there
        customTechniques -= input

        //remove any potential invalid techniques after this one's removal
        removeExtra()
        updateFullList()
    }

    /**
     * Checks if second and third level techniques are still valid for the character to take.
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
     * Sets martial knowledge to the appropriate amount for each taken item.
     */
    fun updateMkSpent(){
        //reset martial knowledge remaining to its maximum value
        martialKnowledgeRemaining.value = martialKnowledgeMax.value

        //removes martial knowledge for each ki ability taken
        takenAbilities.forEach{
            martialKnowledgeRemaining.value -= it.mkCost
        }

        //removes martial knowledge for each dominion technique taken
        takenTechniques.forEach{
            martialKnowledgeRemaining.value -= it.mkCost()
        }
    }

    //initialize martial knowledge values
    val martialKnowledgeMax = mutableStateOf(0)
    val martialKnowledgeSpec = mutableStateOf(0)
    val martialKnowledgeRemaining = mutableStateOf(0)

    //initialize stat ki points and accumulation
    val strKi = KiStat(this@Ki)
    val dexKi = KiStat(this@Ki)
    val agiKi = KiStat(this@Ki)
    val conKi = KiStat(this@Ki)
    val powKi = KiStat(this@Ki)
    val wpKi = KiStat(this@Ki)

    //gather all ki stat items
    val allKiStats = listOf(strKi, dexKi, agiKi, conKi, powKi, wpKi)

    //initialize total ki points
    val totalKi = mutableStateOf(0)

    //initialize total accumulation value
    val totalAcc = mutableStateOf(0)

    /**
     * Changes the martial knowledge bonus by the inputted amount.
     *
     * @param input value to change the martial knowledge bonus by
     */
    fun updateMKSpec(input: Int){
        martialKnowledgeSpec.value += input
        updateMK()
    }

    /**
     * Recalculates the character's maximum martial knowledge
     */
    fun updateMK(){
        val classMK =
            if(charInstance.lvl.value != 0) charInstance.ownClass.value.mkPerLevel * charInstance.lvl.value
            else charInstance.ownClass.value.mkPerLevel/2

        martialKnowledgeMax.value = classMK + charInstance.weaponProficiencies.mkFromArts() + martialKnowledgeSpec.value
        updateMkSpent()
    }

    //initialize value for total ki points bought
    val totalPointBuy = mutableStateOf(0)

    //initialize total bought accumulation value
    val totalAccBuy = mutableStateOf(0)

    /**
     * Updates the total ki points bought for each KiStat item.
     */
    fun updateBoughtPoints(){
        totalPointBuy.value = 0
        allKiStats.forEach{totalPointBuy.value += it.boughtKiPoints.value}
    }

    /**
     * Recalculates the total ki points acquired by the character.
     */
    fun updateTotalPoints(){
        totalKi.value = 0
        allKiStats.forEach{totalKi.value += it.totalKiPoints.value}
    }

    /**
     * Updates the total ki accumulation bought for each KiStat item.
     */
    fun updateBoughtAcc(){
        totalAccBuy.value = 0
        allKiStats.forEach{totalAccBuy.value += it.boughtAccumulation.value}
    }

    /**
     * Recalculates the total ki accumulation acquired by the character.
     */
    fun updateTotalAcc(){
        totalAcc.value = 0
        allKiStats.forEach{totalAcc.value += it.totalAccumulation.value}
    }

    /**
     * Determines the development points spent in ki point and accumulation purchases.
     */
    fun calculateSpent(): Int{
        var total = 0

        //add bought ki points and accumulation values
        total += totalPointBuy.value * charInstance.ownClass.value.kiGrowth
        total += totalAccBuy.value * charInstance.ownClass.value.kiAccumMult

        return total
    }


    /**
     * Loads data in regards to this section from saved file data.
     *
     * @param fileReader file to read the data from
     */
    fun loadKiAttributes(fileReader: BufferedReader){
        //set bought ki points and accumulation for each KiStat item
        allKiStats.forEach{
            it.setBoughtKiPoints(fileReader.readLine().toInt())
            it.setBoughtAccumulation(fileReader.readLine().toInt())
        }

        //acquire saved data for ki abilities
        for(index in 0 until fileReader.readLine().toInt()){
            takenAbilities += listOf(getAbility(fileReader.readLine())!!)
        }

        //acquire saved data for dominion techniques
        for(index in 0 until fileReader.readLine().toInt()){
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

            for(techLoops in 0 until fileReader.readLine().toInt()){
                //get effect name
                val teName = fileReader.readLine()

                //get effect amount
                val teEffect = fileReader.readLine()

                //get effect cost
                val teCost = fileReader.readLine().toInt()

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
                for(effectLoops in 0 until fileReader.readLine().toInt()){
                    teElements += Element.fromString(fileReader.readLine())
                }

                //add effect to  technique
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
            }

            //create full technique to add
            val newTech = Technique(techName, techDesc, techLvl, techMaint, techEffects)

            //add prebuilt technique if this item matches one
            if(techEquivalent(newTech) != null)
                addTechnique(techEquivalent(newTech)!!)
            else
                addTechnique(newTech)
        }

        updateMkSpent()
    }

    /**
     * Writes data to file for ki abilities, techniques, and purchases for ki points and accumulation
     */
    fun writeKiAttributes() {
        //write data from KiStats
        allKiStats.forEach{
            charInstance.addNewData(it.boughtKiPoints.value)
            charInstance.addNewData(it.boughtAccumulation.value)
        }

        //write number of ki abilities taken and specific abilities taken
        charInstance.addNewData(takenAbilities.size)
        takenAbilities.forEach{
            charInstance.addNewData(it.name)
        }

        //write number of techniques taken and data on each technique
        charInstance.addNewData(takenTechniques.size)
        takenTechniques.forEach{
            it.write(charInstance)
        }
    }

    /**
     * Finds a ki ability based on its name.
     *
     * @param toFind name of the ki ability to find
     * @return ki ability of the search if available
     */
    fun getAbility(toFind: String): KiAbility?{
        kiRecord.allKiAbilities.forEach{
            if(it.name == toFind)
                return it
        }

        return null
    }

    /**
     * Determines if a technique from file is equivalent to a prebuilt technique.
     *
     * @param compareTo technique to check against prebuilt techniques
     * @return prebuilt technique if one matches
     */
    fun techEquivalent(compareTo: Technique): Technique?{
        allTechniques.forEach{
            if(it.equivalentTo(compareTo))
                return it
        }

        return null
    }
}