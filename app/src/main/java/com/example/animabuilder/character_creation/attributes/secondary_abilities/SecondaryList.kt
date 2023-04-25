package com.example.animabuilder.character_creation.attributes.secondary_abilities

import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.attributes.class_objects.CharClass
import kotlin.Throws
import java.io.BufferedReader
import java.io.IOException

/**
 * Object that holds all of a character's secondary characteristics
 * Keeps track of natural bonuses taken by the player
 *
 * @param charInstance object that manages all of the character's stats
 */
class SecondaryList(val charInstance: BaseCharacter){
    //initialize held state of jack of all trades advantage
    var allTradesTaken = false

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
        if(!target.bonusApplied){
            //if characteristic is invested in and there are bonuses available
            if(countNatBonuses() < charInstance.lvl && target.pointsApplied > 0)
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
            if(it.bonusApplied) total++
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
        jump.setModVal(charInstance.primaryList.str.outputMod)
        strengthFeat.setModVal(charInstance.primaryList.str.outputMod)
    }

    /**
     * Update needed values based on new dexterity modifier.
     */
    fun updateDEX() {
        forging.setModVal(charInstance.primaryList.dex.outputMod)
        sleightHand.setModVal(charInstance.primaryList.dex.outputMod)
        disguise.setModVal(charInstance.primaryList.dex.outputMod)
        lockPick.setModVal(charInstance.primaryList.dex.outputMod)
        theft.setModVal(charInstance.primaryList.dex.outputMod)
        trapLore.setModVal(charInstance.primaryList.dex.outputMod)
    }

    /**
     * Update needed values based on new agility modifier.
     */
    fun updateAGI() {
        acrobatics.setModVal(charInstance.primaryList.agi.outputMod)
        athletics.setModVal(charInstance.primaryList.agi.outputMod)
        climb.setModVal(charInstance.primaryList.agi.outputMod)
        ride.setModVal(charInstance.primaryList.agi.outputMod)
        swim.setModVal(charInstance.primaryList.agi.outputMod)
        dance.setModVal(charInstance.primaryList.agi.outputMod)
        stealth.setModVal(charInstance.primaryList.agi.outputMod)
    }

    /**
     * Update needed values based on new intelligence modifier.
     */
    fun updateINT() {
        persuasion.setModVal(charInstance.primaryList.int.outputMod)
        poisons.setModVal(charInstance.primaryList.int.outputMod)
        animals.setModVal(charInstance.primaryList.int.outputMod)
        appraise.setModVal(charInstance.primaryList.int.outputMod)
        herbalLore.setModVal(charInstance.primaryList.int.outputMod)
        history.setModVal(charInstance.primaryList.int.outputMod)
        memorize.setModVal(charInstance.primaryList.int.outputMod)
        medic.setModVal(charInstance.primaryList.int.outputMod)
        navigate.setModVal(charInstance.primaryList.int.outputMod)
        occult.setModVal(charInstance.primaryList.int.outputMod)
        sciences.setModVal(charInstance.primaryList.int.outputMod)
    }

    /**
     * Update needed values based on new power modifier.
     */
    fun updatePOW() {
        art.setModVal(charInstance.primaryList.pow.outputMod)
        music.setModVal(charInstance.primaryList.pow.outputMod)
        leadership.setModVal(charInstance.primaryList.pow.outputMod)
        style.setModVal(charInstance.primaryList.pow.outputMod)
        magicAppraise.setModVal(charInstance.primaryList.pow.outputMod)
    }

    /**
     * Update needed values based on new willpower modifier.
     */
    fun updateWP() {
        intimidate.setModVal(charInstance.primaryList.wp.outputMod)
        composure.setModVal(charInstance.primaryList.wp.outputMod)
        resistPain.setModVal(charInstance.primaryList.wp.outputMod)
    }

    /**
     * Update needed values based on new perception modifier
     */
    fun updatePER() {
        notice.setModVal(charInstance.primaryList.per.outputMod)
        search.setModVal(charInstance.primaryList.per.outputMod)
        track.setModVal(charInstance.primaryList.per.outputMod)
        hide.setModVal(charInstance.primaryList.per.outputMod)
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
    fun writeList() {
        fullList.forEach{it.write() }
    }

    /**
     * Determine the total number of development points spent in this section.
     *
     * @return total development points spent
     */
    fun calculateSpent(): Int{
        var total = 0

        fullList.forEach{total += it.pointsIn}

        return total
    }
}