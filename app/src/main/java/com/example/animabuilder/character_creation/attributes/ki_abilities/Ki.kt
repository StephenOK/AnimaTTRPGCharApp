package com.example.animabuilder.character_creation.attributes.ki_abilities

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

    val strKi = KiStat(this@Ki)
    val dexKi = KiStat(this@Ki)
    val agiKi = KiStat(this@Ki)
    val conKi = KiStat(this@Ki)
    val powKi = KiStat(this@Ki)
    val wpKi = KiStat(this@Ki)

    val allKiStats = listOf(strKi, dexKi, agiKi, conKi, powKi, wpKi)

    //initialize total ki points
    var totalKi = 0

    //initialize total accumulation value
    var totalAcc = 0

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

    //initialize value for total ki points bought
    var totalPointBuy = 0

    //initialize total bought accumulation value
    var totalAccBuy = 0

    fun updateBoughtPoints(){
        totalPointBuy = 0
        allKiStats.forEach{totalPointBuy += it.boughtKiPoints}
    }
    fun updateTotalPoints(){
        totalKi = 0
        allKiStats.forEach{totalKi += it.totalKiPoints}
    }
    fun updateBoughtAcc(){
        totalAccBuy = 0
        allKiStats.forEach{totalAccBuy += it.boughtAccumulation}
    }
    fun updateTotalAcc(){
        totalAcc = 0
        allKiStats.forEach{totalAcc += it.totalAccumulation}
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
        allKiStats.forEach{
            it.setBoughtKiPoints(fileReader.readLine().toInt())
            it.setBoughtAccumulation(fileReader.readLine().toInt())
        }

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
        allKiStats.forEach{
            charInstance.addNewData(it.boughtKiPoints)
            charInstance.addNewData(it.boughtAccumulation)
        }

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