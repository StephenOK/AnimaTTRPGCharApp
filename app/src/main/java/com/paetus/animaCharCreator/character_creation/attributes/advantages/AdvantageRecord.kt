package com.paetus.animaCharCreator.character_creation.attributes.advantages

import androidx.compose.runtime.mutableIntStateOf
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_items.CommonAdvantages
import com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_items.MagicAdvantages
import com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_items.PsychicAdvantages
import com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_types.Advantage
import com.paetus.animaCharCreator.enumerations.Archetype
import com.paetus.animaCharCreator.writeDataTo
import java.io.BufferedReader
import java.io.ByteArrayOutputStream

/**
 * Section that holds data on the advantages and disadvantages chosen for the character.
 * Holds information on how many creation points have been spent.
 * Holds information on all base versions of a character's advantages and disadvantages.
 * Adds and removes advantages from the character.
 * Returns error messages to the caller for display.
 *
 * @param charInstance head character object that holds this section
 */
open class AdvantageRecord(
    private val charInstance: BaseCharacter
){
    fun commonAdvantages(): CommonAdvantages{return charInstance.objectDB.commonAdvantages}
    fun magicAdvantages(): MagicAdvantages{return charInstance.objectDB.magicAdvantages}
    fun psyAdvantages(): PsychicAdvantages{return charInstance.objectDB.psychicAdvantages}

    //initialize list of taken advantages
    val takenAdvantages = mutableListOf<Advantage>()

    //initialize spent creation points
    val creationPointSpent = mutableIntStateOf(value = 0)

    /**
     * Finds the base advantage of the inputted name.
     *
     * @param findAdvantage name flag for the queried advantage
     * @return the sought for advantage, if there is one present
     */
    private fun findAdvantage(
        findAdvantage: String
    ): Advantage?{
        //search through each base list to find a match
        commonAdvantages().advantages.forEach{advantage ->
            //return if a common advantage matches
            if(advantage.saveTag == findAdvantage) return advantage
        }
        magicAdvantages().advantages.forEach{advantage ->
            //return if a magic advantage matches
            if(advantage.saveTag == findAdvantage) return advantage
        }
        psyAdvantages().advantages.forEach{advantage ->
            //return if a psychic advantage matches
            if(advantage.saveTag == findAdvantage) return advantage
        }
        commonAdvantages().disadvantages.forEach{advantage ->
            //return if a common disadvantage matches
            if(advantage.saveTag == findAdvantage) return advantage
        }
        magicAdvantages().disadvantages.forEach{advantage ->
            //return if a magic disadvantage matches
            if(advantage.saveTag == findAdvantage) return advantage
        }
        psyAdvantages().disadvantages.forEach{advantage ->
            //return if a psychic disadvantage matches
            if(advantage.saveTag == findAdvantage) return advantage
        }

        //return null for no match found
        return null
    }

    /**
     * Method used to add an advantage or disadvantage to the character
     *
     * @param advantageBase base version of the advantage to add
     * @param taken index of the chosen item if applicable
     * @param takenCost index of the chosen cost of the advantage
     * @param multTaken list of taken options if applicable to the advantage
     * @return either an error message for a failed addition or a null item for a successful addition
     */
    open fun acquireAdvantage(
        advantageBase: Advantage,
        taken: Int?,
        takenCost: Int,
        multTaken: List<Int>?
    ): Int?{
        //halt addition of fourth disadvantage
        if(advantageBase.cost[takenCost] < 0 && countDisadvantages() >= 3)
            return R.string.excessDisadvantages

        //implement racial restrictions on advantages
        when(charInstance.ownRace.value){
            //advantage restrictions for sylvain
            charInstance.objectDB.races.sylvainAdvantages -> {
                when(advantageBase){
                    //forbid sickly, serious illness, and magic susceptibility for sylvain
                    commonAdvantages().sickly,
                    commonAdvantages().seriousIllness,
                    commonAdvantages().magicSusceptibility -> return R.string.sylvainRestriction

                    //forbid Dark element option for elemental compatibility
                    charInstance.objectDB.magicAdvantages.elementalCompatibility -> if(taken == 1)
                        return R.string.sylvainDarkRestriction
                }
            }

            //advantage restrictions for jayan
            charInstance.objectDB.races.jayanAdvantages -> {
                when(advantageBase){
                    //forbid size reduction in Jayan
                    commonAdvantages().uncommonSize -> if(taken!! < 5)
                        return R.string.jayanSizeRestriction
                    //forbid Strength reduction for Jayan
                    commonAdvantages().deductCharacteristic -> if(taken == 0)
                        return R.string.jayanStrengthRestriction
                }
            }

            //advantage restrictions for duk'zarist
            charInstance.objectDB.races.dukzaristAdvantages -> {
                when(advantageBase){
                    //forbid these disadvantages for dukzarist
                    commonAdvantages().atrophiedLimb,
                    commonAdvantages().blind,
                    commonAdvantages().deafness,
                    commonAdvantages().mute,
                    commonAdvantages().nearsighted,
                    commonAdvantages().physicalWeakness,
                    commonAdvantages().seriousIllness,
                    commonAdvantages().sickly,
                    commonAdvantages().poisonSusceptibility -> return R.string.dukzaristRestriction

                    //forbid Light element option for this advantage
                    magicAdvantages().elementalCompatibility -> if(taken == 0)
                        return R.string.dukzaristLightRestriction

                    //Dukzarist must develop pyrokinesis first
                    commonAdvantages().psyDisciplineAccess ->{
                        if(taken != 2 && !charInstance.psychic.legalDisciplines.contains(charInstance.psychic.pyrokinesis))
                            return R.string.dukzaristPyroRestriction
                    }
                }
            }
            else -> {}
        }

        //implement advantage restrictions
        when(advantageBase){
            commonAdvantages().characteristicPoint -> {
                //apply amount cap to each stat
                when(taken) {
                    0 -> if(charInstance.primaryList.str.total.intValue + 1 > 11) return R.string.strengthIncreaseRestriction
                    1 -> if(charInstance.primaryList.dex.total.intValue + 1 > 11) return R.string.dexterityIncreaseRestriction
                    2 -> if(charInstance.primaryList.agi.total.intValue + 1 > 11) return R.string.agilityIncreaseRestriction
                    3 -> if(charInstance.primaryList.con.total.intValue + 1 > 11) return R.string.constitutionIncreaseRestriction
                    4 -> if(charInstance.primaryList.int.total.intValue + 1 > 13) return R.string.intelligenceIncreaseRestriction
                    5 -> if(charInstance.primaryList.pow.total.intValue + 1 > 13) return R.string.powerIncreaseRestriction
                    6 -> if(charInstance.primaryList.wp.total.intValue + 1 > 13) return R.string.willpowerIncreaseRestriction
                    7 -> if(charInstance.primaryList.per.total.intValue + 1 > 13) return R.string.perceptionIncreaseRestriction
                    else -> return R.string.failedInput
                }
            }

            //forbid reduction of growth stat to below zero
            commonAdvantages().subjectAptitude -> {
                //get the indicated characteristic, be it default or custom
                val secondary =
                    if(taken!! < 38) charInstance.secondaryList.fullList()[taken]
                    else charInstance.secondaryList.getAllCustoms()[taken - 38]

                //check that stat does not fall to or below zero
                if(secondary.devPerPoint.intValue - advantageBase.cost[takenCost] <= 0)
                    return R.string.costReductionRestriction
            }

            commonAdvantages().fieldAptitude -> {
                //get current growth value that will be changed by this advantage
                val prevGrowth =
                    when(taken){
                        0 -> charInstance.classes.getClass().athGrowth
                        1 -> charInstance.classes.getClass().createGrowth
                        2 -> charInstance.classes.getClass().percGrowth
                        3 -> charInstance.classes.getClass().socGrowth
                        4 -> charInstance.classes.getClass().subterGrowth
                        5 -> charInstance.classes.getClass().intellGrowth
                        6 -> charInstance.classes.getClass().vigGrowth
                        else -> 0
                    }

                //stop acquisition if growth goes to zero
                if(prevGrowth - 1 <= 0)
                    return R.string.fieldReductionRestriction
            }

            //no need to acquire more disciplines if all are already taken
            commonAdvantages().psyDisciplineAccess -> {
                if(this.getAdvantage("allPsyDisciplines") != null)
                    return R.string.redundantPsychicDiscipline
            }

            //character's class archetype must be one of the indicated types
            commonAdvantages().exclusiveWeapon -> {
                if(!charInstance.classes.getClass().archetype.contains(Archetype.Fighter) &&
                        !charInstance.classes.getClass().archetype.contains(Archetype.Domine) &&
                        !charInstance.classes.getClass().archetype.contains(Archetype.Prowler) &&
                        !charInstance.classes.getClass().archetype.contains(Archetype.Novel))
                    return R.string.classRestriction
            }

            //check minimum appearance before taking this disadvantage
            commonAdvantages().unattractive ->
                if(charInstance.appearance.intValue < 7) return R.string.appearanceRestriction

            //assure all elements picked for half attuned to tree advantage
            magicAdvantages().halfTreeAttuned ->
                if (multTaken!!.size != 5) return R.string.incompleteHalfTree

            magicAdvantages().superiorMagicRecovery -> {
                if(this.getAdvantage("slowMagRecover") != null)
                    return R.string.magicRecoveryRestriction
                else if(this.getAdvantage("magicBlockage") != null)
                    return R.string.magicBlockageRestriction
            }

            //prevent either of these disadvantages from being taken with the other one
            charInstance.objectDB.magicAdvantages.slowMagicRecovery -> {
                if (this.getAdvantage("magicBlockage") != null)
                    return R.string.magicBlockageRestriction
                else if(this.getAdvantage("superiorMagRecovery") != null)
                    return R.string.superiorMagRecoveryRestriction
            }
            charInstance.objectDB.magicAdvantages.magicBlockage -> {
                if (this.getAdvantage("slowMagRecover") != null)
                    return R.string.magicRecoveryRestriction
                else if(this.getAdvantage("superiorMagRecovery") != null)
                    return R.string.superiorMagRecoveryRestriction
            }
        }

        //check if able to take multiple times
        if(advantageBase.special == null && this.getAdvantage(advantageBase.saveTag) != null)
            return R.string.duplicateRestriction
        else if(taken != null && getAdvantage("Natural Knowledge of a Path", taken, takenCost) != null)
            return R.string.duplicatePathRestriction

        //create advantage to take
        val copyAdvantage = Advantage(
            saveTag = advantageBase.saveTag,
            name = advantageBase.name,
            description = advantageBase.description,
            effect = advantageBase.effect,
            restriction = advantageBase.restriction,
            special = advantageBase.special,
            options = advantageBase.options,
            picked = taken,
            multPicked = multTaken,
            cost = advantageBase.cost,
            pickedCost = takenCost,
            onTake = advantageBase.onTake,
            onRemove = advantageBase.onRemove
        )

        //check that character has sufficient creation points to buy this advantage
        if(creationPointSpent.intValue + copyAdvantage.cost[copyAdvantage.pickedCost] <= 3){
            takenAdvantages += copyAdvantage

            //apply effect
            if(copyAdvantage.onTake != null)
                copyAdvantage.onTake(
                    charInstance,
                    taken,
                    copyAdvantage.cost[takenCost]
                )

            refreshSpent()
            return null
        }

        //notify of insufficient creation points
        return R.string.failedPurchase
    }

    /**
     * Removes an advantage from the character.
     *
     * @param advantage item to be removed
     */
    open fun removeAdvantage(
        advantage: Advantage
    ){
        //search for copy of inputted advantage
        takenAdvantages.forEach{heldCopy ->
            if(heldCopy.isEquivalent(advantage)){
                //remove the found advantage
                takenAdvantages -= heldCopy

                //undo its effect
                if(heldCopy.onRemove != null)
                    heldCopy.onRemove(
                        charInstance,
                        heldCopy.picked,
                        heldCopy.cost[heldCopy.pickedCost]
                    )

                //terminate process
                refreshSpent()
                return
            }
        }
    }

    /**
     * Finds a taken advantage of the inputted name.
     *
     * @param advantageString name of the advantage to find
     * @return the sought for advantage, if there is one present
     */
    fun getAdvantage(
        advantageString: String
    ): Advantage?{
        //search through all acquired advantages
        takenAdvantages.forEach{advantage ->
            //return if match is found
            if(advantage.saveTag == advantageString) return advantage
        }

        //return null if no match
        return null
    }

    /**
     * Finds a taken advantage of the inputted name, type, and cost.
     *
     * @param name name of the advantage to find
     * @param taken type of the advantage to find
     * @param cost cost of the item to find
     * @return the sought for advantage, if there is one present
     */
    fun getAdvantage(
        name: String,
        taken: Int,
        cost: Int
    ): Advantage?{
        //search through all acquired advantages
        takenAdvantages.forEach{advantage ->
            //return if identical advantage found
            if(advantage.saveTag == name && advantage.picked == taken && advantage.pickedCost == cost)
                return advantage
        }

        //return null if no match
        return null
    }

    /**
     * Recalculate the number of creation points spent by the character.
     */
    private fun refreshSpent(){
        //reset spent value
        creationPointSpent.intValue = 0

        //add cost of each taken advantage
        takenAdvantages.forEach{advantage ->
            creationPointSpent.intValue += advantage.cost[advantage.pickedCost]
        }
    }

    /**
     * Determines the number of disadvantages the character has taken.
     *
     * @return the disadvantage count
     */
    private fun countDisadvantages(): Int{
        //initialize counter
        var total = 0

        //search through all acquired advantages
        takenAdvantages.forEach{advantage ->
            //increment if disadvantage found
            if(advantage.cost[advantage.pickedCost] < 0)
                total++
        }

        //return counter
        return total
    }


    /**
     * Retrieves advantage data from file.
     *
     * @param fileReader file reader to gather data from
     * @param version app version that the file was saved as
     */
    fun loadAdvantages(
        fileReader: BufferedReader,
        version: Int
    ){
        //retrieve number of taken advantages
        (0 until fileReader.readLine().toInt()).forEach{
            if(version <= 13) {
                //apply recorded advantage data
                acquireAdvantage(
                    advantageBase = findAdvantage(findAdvantage = fileReader.readLine())!!,
                    taken = fileReader.readLine().toIntOrNull(),
                    takenCost = fileReader.readLine().toInt(),
                    multTaken = null
                )
            }
            else{
                //retrieve advantage name, option, and cost
                val advName = findAdvantage(fileReader.readLine())!!
                val advTaken = fileReader.readLine().toIntOrNull()
                val advCost = fileReader.readLine().toInt()

                //get multiple options taken if applicable
                val advMultTaken =
                    if(fileReader.readLine().toIntOrNull() == null) null
                    else{
                        listOf(
                            fileReader.readLine().toInt(),
                            fileReader.readLine().toInt(),
                            fileReader.readLine().toInt(),
                            fileReader.readLine().toInt(),
                            fileReader.readLine().toInt()
                        )
                    }

                //add data as an advantage
                acquireAdvantage(advName, advTaken, advCost, advMultTaken)
            }
        }
    }

    /**
     * Write advantage data to file.
     */
    fun writeAdvantages(byteArray: ByteArrayOutputStream) {
        //record number of taken advantages
        writeDataTo(byteArray, takenAdvantages.size)

        takenAdvantages.forEach{advantage ->
            //write advantage name, option, and cost
            writeDataTo(byteArray, advantage.saveTag)
            writeDataTo(byteArray, advantage.picked)
            writeDataTo(byteArray, advantage.pickedCost)

            //write if multiple options picked
            if(advantage.multPicked == null) writeDataTo(byteArray, null)
            else{
                //record size of list and options
                writeDataTo(byteArray, advantage.multPicked.size)
                advantage.multPicked.forEach{multItem ->
                    writeDataTo(byteArray, multItem)
                }
            }
        }
    }
}