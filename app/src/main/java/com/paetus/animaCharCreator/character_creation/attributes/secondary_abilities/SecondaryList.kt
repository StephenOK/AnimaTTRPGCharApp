package com.paetus.animaCharCreator.character_creation.attributes.secondary_abilities

import androidx.compose.runtime.mutableStateOf
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.character_creation.attributes.class_objects.CharClass
import com.paetus.animaCharCreator.character_creation.attributes.primary_abilities.PrimaryList
import kotlin.Throws
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.IOException

/**
 * Object that holds all of a character's secondary characteristics
 * Keeps track of natural bonuses taken by the player
 *
 * @param charInstance object that manages all of the character's stats
 * @param primaryList primary characteristics of the character
 */
class SecondaryList(val charInstance: BaseCharacter, val primaryList: PrimaryList){
    //initialize held state of jack of all trades advantage
    val allTradesTaken = mutableStateOf(false)

    //Initialize all secondary characteristics in list
    //athletics
    val acrobatics = SecondaryCharacteristic(this)
    val athletics = SecondaryCharacteristic(this)
    val climb = SecondaryCharacteristic(this)
    val jump = SecondaryCharacteristic(this)
    val ride = SecondaryCharacteristic(this)
    val swim = SecondaryCharacteristic(this)

    //creative
    val art = SecondaryCharacteristic(this)
    val dance = SecondaryCharacteristic(this)
    val forging = SecondaryCharacteristic(this)
    val music = SecondaryCharacteristic(this)
    val sleightHand = SecondaryCharacteristic(this)

    //perceptive
    val notice = SecondaryCharacteristic(this)
    val search = SecondaryCharacteristic(this)
    val track = SecondaryCharacteristic(this)

    //social
    val intimidate = SecondaryCharacteristic(this)
    val leadership = SecondaryCharacteristic(this)
    val persuasion = SecondaryCharacteristic(this)
    val style = SecondaryCharacteristic(this)

    //subterfuge
    val disguise = SecondaryCharacteristic(this)
    val hide = SecondaryCharacteristic(this)
    val lockPick = SecondaryCharacteristic(this)
    val poisons = SecondaryCharacteristic(this)
    val theft = SecondaryCharacteristic(this)
    val stealth = SecondaryCharacteristic(this)
    val trapLore = SecondaryCharacteristic(this)

    //intellectual
    val animals = SecondaryCharacteristic(this)
    val appraise = SecondaryCharacteristic(this)
    val herbalLore = SecondaryCharacteristic(this)
    val history = SecondaryCharacteristic(this)
    val memorize = SecondaryCharacteristic(this)
    val magicAppraise = SecondaryCharacteristic(this)
    val medic = SecondaryCharacteristic(this)
    val navigate = SecondaryCharacteristic(this)
    val occult = SecondaryCharacteristic(this)
    val sciences = SecondaryCharacteristic(this)

    //vigor
    val composure = SecondaryCharacteristic(this)
    val strengthFeat = SecondaryCharacteristic(this)
    val resistPain = SecondaryCharacteristic(this)

    //get all secondary characteristics
    val fullList = listOf(acrobatics, athletics, climb, jump, ride, swim, intimidate, leadership,
        persuasion, style, notice, search, track, animals, appraise, herbalLore, history, memorize,
        magicAppraise, medic, navigate, occult, sciences, composure, strengthFeat, resistPain,
        disguise, hide, lockPick, poisons, theft, stealth, trapLore, art, dance, forging, music,
        sleightHand)

    /**
     * Retrieves a secondary field based on the numerical input.
     *
     * @param input number to convert to a secondary field
     * @return list of characteristics associated with the desired field
     */
    fun intToField(input: Int): List<SecondaryCharacteristic>{
        return when(input){
            0 -> listOf(acrobatics, athletics, climb, jump, ride, swim)
            1 -> listOf(art, dance, forging, music, sleightHand)
            2 -> listOf(notice, search, track)
            3 -> listOf(intimidate, leadership, persuasion, style)
            4 -> listOf(disguise, hide, lockPick, poisons, theft, stealth, trapLore)
            5 -> listOf(animals, appraise, herbalLore, history, memorize, magicAppraise, medic,
                navigate, occult, sciences)
            6 -> listOf(composure, strengthFeat, resistPain)
            else -> listOf()
        }
    }

    /**
     * Attempts to toggle the natural bonus of the inputted characteristic.
     *
     * @param target secondary characteristic to toggle the bonus of
     */
    fun toggleNatBonus(target: SecondaryCharacteristic){
        //if natural bonus is currently off
        if(!target.bonusApplied.value){
            //if characteristic is invested in and there are bonuses available
            if(countNatBonuses() < charInstance.lvl.value && target.pointsApplied.value > 0)
                target.setBonusApplied(true)
        }

        //remove bonus if it is currently on
        else
            target.setBonusApplied(false)
    }

    /**
     * Retrieves the number of currently held natural bonuses.
     */
    fun countNatBonuses(): Int{
        //initialize counter
        var total = 0

        //check each characteristic for a held bonus
        fullList.forEach{
            if(it.bonusApplied.value) total++
        }

        //return final count
        return total
    }

    /**
     * Update values based on given class change.
     *
     * @param newClass class the character now possesses
     */
    fun classUpdate(newClass: CharClass) {
        //update field growth rates
        updateGrowth(0, newClass.athGrowth)
        updateGrowth(1, newClass.creatGrowth)
        updateGrowth(2, newClass.percGrowth)
        updateGrowth(3, newClass.socGrowth)
        updateGrowth(4, newClass.subterGrowth)
        updateGrowth(5, newClass.intellGrowth)
        updateGrowth(6, newClass.vigGrowth)

        //update all characteristic totals and expenditures
        fullList.forEach{
            it.updateDevSpent()
            it.refreshTotal()
        }
    }

    /**
     * Updates the development point cost of the given field.
     *
     * @param itemNum number indicating which field to change
     * @param newNum number to set the development point cost to
     */
    fun updateGrowth(itemNum: Int, newNum: Int){
        intToField(itemNum).forEach{it.setDevPerPoint(newNum)}
    }

    /**
     * Update needed values based on new strength modifier.
     */
    fun updateSTR() {
        jump.setModVal(primaryList.str.outputMod.value)
        strengthFeat.setModVal(primaryList.str.outputMod.value)
    }

    /**
     * Update needed values based on new dexterity modifier.
     */
    fun updateDEX() {
        forging.setModVal(primaryList.dex.outputMod.value)
        sleightHand.setModVal(primaryList.dex.outputMod.value)
        disguise.setModVal(primaryList.dex.outputMod.value)
        lockPick.setModVal(primaryList.dex.outputMod.value)
        theft.setModVal(primaryList.dex.outputMod.value)
        trapLore.setModVal(primaryList.dex.outputMod.value)
    }

    /**
     * Update needed values based on new agility modifier.
     */
    fun updateAGI() {
        acrobatics.setModVal(primaryList.agi.outputMod.value)
        athletics.setModVal(primaryList.agi.outputMod.value)
        climb.setModVal(primaryList.agi.outputMod.value)
        ride.setModVal(primaryList.agi.outputMod.value)
        swim.setModVal(primaryList.agi.outputMod.value)
        dance.setModVal(primaryList.agi.outputMod.value)
        stealth.setModVal(primaryList.agi.outputMod.value)
    }

    /**
     * Update needed values based on new intelligence modifier.
     */
    fun updateINT() {
        persuasion.setModVal(primaryList.int.outputMod.value)
        poisons.setModVal(primaryList.int.outputMod.value)
        animals.setModVal(primaryList.int.outputMod.value)
        appraise.setModVal(primaryList.int.outputMod.value)
        herbalLore.setModVal(primaryList.int.outputMod.value)
        history.setModVal(primaryList.int.outputMod.value)
        memorize.setModVal(primaryList.int.outputMod.value)
        medic.setModVal(primaryList.int.outputMod.value)
        navigate.setModVal(primaryList.int.outputMod.value)
        occult.setModVal(primaryList.int.outputMod.value)
        sciences.setModVal(primaryList.int.outputMod.value)
    }

    /**
     * Update needed values based on new power modifier.
     */
    fun updatePOW() {
        art.setModVal(primaryList.pow.outputMod.value)
        music.setModVal(primaryList.pow.outputMod.value)
        leadership.setModVal(primaryList.pow.outputMod.value)
        style.setModVal(primaryList.pow.outputMod.value)
        magicAppraise.setModVal(primaryList.pow.outputMod.value)
    }

    /**
     * Update needed values based on new willpower modifier.
     */
    fun updateWP() {
        intimidate.setModVal(primaryList.wp.outputMod.value)
        composure.setModVal(primaryList.wp.outputMod.value)
        resistPain.setModVal(primaryList.wp.outputMod.value)
    }

    /**
     * Update needed values based on new perception modifier
     */
    fun updatePER() {
        notice.setModVal(primaryList.per.outputMod.value)
        search.setModVal(primaryList.per.outputMod.value)
        track.setModVal(primaryList.per.outputMod.value)
        hide.setModVal(primaryList.per.outputMod.value)
    }

    /**
     * Load characteristic values from a given file reader.
     *
     * @param fileReader file to retrieve data from
     */
    @Throws(IOException::class)
    fun loadList(fileReader: BufferedReader?) {
        fullList.forEach{it.load(fileReader!!)}
    }

    /**
     * Save characteristic values to file.
     */
    fun writeList(byteArray: ByteArrayOutputStream) {
        fullList.forEach{it.write(byteArray) }
    }

    /**
     * Determine the total number of development points spent in this section.
     *
     * @return total development points spent
     */
    fun calculateSpent(): Int{
        var total = 0

        fullList.forEach{total += it.pointsIn.value}

        return total
    }
}