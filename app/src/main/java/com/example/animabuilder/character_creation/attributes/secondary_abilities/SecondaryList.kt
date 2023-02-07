package com.example.animabuilder.character_creation.attributes.secondary_abilities

import com.example.animabuilder.R
import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.attributes.class_objects.CharClass
import kotlin.Throws
import java.io.BufferedReader
import java.io.IOException
import java.io.Serializable

/**
 * Object that holds all of a character's secondary characteristics
 * Keeps track of natural bonuses taken by the player
 */

class SecondaryList(val charInstance: BaseCharacter) : Serializable {
    var allTradesTaken = false

    //initialize current natural bonuses taken
    var currentNatTaken = 0

    /**
     * Increments the number of natural bonuses the player has taken
     * Direction indicates whether the player is adding or removing a bonus
     */
    fun incrementNat(direction: Boolean): Boolean {
        //if user attempts to add a bonus
        if (direction) {
            //check if any bonuses available and grant if able
            return if (currentNatTaken < charInstance.lvl) {
                currentNatTaken++
                true
            } else false
        }
        //if user attempts to remove a bonus
        else currentNatTaken--

        return true
    }

    //Initialize all secondary characteristics in list
    //athletics
    val acrobatics = SecondaryCharacteristic(R.string.acrobaticsLabel, this)
    val athletics = SecondaryCharacteristic(R.string.athleticsLabel, this)
    val climb = SecondaryCharacteristic(R.string.climbLabel, this)
    val jump = SecondaryCharacteristic(R.string.jumpLabel, this)
    val ride = SecondaryCharacteristic(R.string.rideLabel, this)
    val swim = SecondaryCharacteristic(R.string.swimLabel, this)

    //creative
    val art = SecondaryCharacteristic(R.string.artLabel, this)
    val dance = SecondaryCharacteristic(R.string.danceLabel, this)
    val forging = SecondaryCharacteristic(R.string.forgeLabel, this)
    val music = SecondaryCharacteristic(R.string.musicLabel, this)
    val sleightHand = SecondaryCharacteristic(R.string.sleightLabel, this)

    //perceptive
    val notice = SecondaryCharacteristic(R.string.noticeLabel, this)
    val search = SecondaryCharacteristic(R.string.searchLabel, this)
    val track = SecondaryCharacteristic(R.string.trackLabel, this)

    //social
    val intimidate = SecondaryCharacteristic(R.string.intimidateLabel, this)
    val leadership = SecondaryCharacteristic(R.string.leadershipLabel, this)
    val persuasion = SecondaryCharacteristic(R.string.persuasionLabel, this)
    val style = SecondaryCharacteristic(R.string.styleLabel, this)

    //subterfuge
    val disguise = SecondaryCharacteristic(R.string.disguiseLabel, this)
    val hide = SecondaryCharacteristic(R.string.hideLabel, this)
    val lockPick = SecondaryCharacteristic(R.string.lockpickLabel, this)
    val poisons = SecondaryCharacteristic(R.string.poisonLabel, this)
    val theft = SecondaryCharacteristic(R.string.theftLabel, this)
    val stealth = SecondaryCharacteristic(R.string.stealthLabel, this)
    val trapLore = SecondaryCharacteristic(R.string.trapLabel, this)

    //intellectual
    val animals = SecondaryCharacteristic(R.string.animalLabel, this)
    val appraise = SecondaryCharacteristic(R.string.appraiseLabel, this)
    val herbalLore = SecondaryCharacteristic(R.string.herbalLabel, this)
    val history = SecondaryCharacteristic(R.string.histLabel, this)
    val memorize = SecondaryCharacteristic(R.string.memLabel, this)
    val magicAppraise = SecondaryCharacteristic(R.string.mAppraiseLabel, this)
    val medic = SecondaryCharacteristic(R.string.medLabel, this)
    val navigate = SecondaryCharacteristic(R.string.navLabel, this)
    val occult = SecondaryCharacteristic(R.string.occultLabel, this)
    val sciences = SecondaryCharacteristic(R.string.scienceLabel, this)

    //vigor
    val composure = SecondaryCharacteristic(R.string.composureLabel, this)
    val strengthFeat = SecondaryCharacteristic(R.string.strFeatLabel, this)
    val resistPain = SecondaryCharacteristic(R.string.resistPainLabel, this)

    val fullList = listOf(acrobatics, athletics, climb, jump, ride, swim, art, dance, forging, music,
    sleightHand, notice, search, track, intimidate, leadership, persuasion, style, disguise, hide,
    lockPick, poisons, theft, stealth, trapLore, animals, appraise, herbalLore, history, memorize,
    magicAppraise, medic, navigate, occult, sciences, composure, strengthFeat, resistPain)

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

    //update values based on given class change
    fun classUpdate(newClass: CharClass) {
        updateGrowth(intToField(0), newClass.athGrowth)
        updateGrowth(intToField(1), newClass.creatGrowth)
        updateGrowth(intToField(2), newClass.percGrowth)
        updateGrowth(intToField(3), newClass.socGrowth)
        updateGrowth(intToField(4), newClass.subterGrowth)
        updateGrowth(intToField(5), newClass.intellGrowth)
        updateGrowth(intToField(6), newClass.vigGrowth)

        fullList.forEach{it.refreshTotal()}
    }

    fun updateGrowth(items: List<SecondaryCharacteristic>, newNum: Int){
        items.forEach{it.setDevPerPoint(newNum)}
    }

    //update needed values based on new strength modifier
    fun updateSTR(strMod: Int) {
        jump.setModVal(strMod)
        strengthFeat.setModVal(strMod)
    }

    //update needed values based on new dexterity modifier
    fun updateDEX(dexMod: Int) {
        forging.setModVal(dexMod)
        sleightHand.setModVal(dexMod)
        disguise.setModVal(dexMod)
        lockPick.setModVal(dexMod)
        theft.setModVal(dexMod)
        trapLore.setModVal(dexMod)
    }

    //update needed values based on new agility modifier
    fun updateAGI(agiMod: Int) {
        acrobatics.setModVal(agiMod)
        athletics.setModVal(agiMod)
        climb.setModVal(agiMod)
        ride.setModVal(agiMod)
        swim.setModVal(agiMod)
        dance.setModVal(agiMod)
        stealth.setModVal(agiMod)
    }

    //update needed values based on new intelligence modifier
    fun updateINT(intMod: Int) {
        persuasion.setModVal(intMod)
        poisons.setModVal(intMod)
        animals.setModVal(intMod)
        appraise.setModVal(intMod)
        herbalLore.setModVal(intMod)
        history.setModVal(intMod)
        memorize.setModVal(intMod)
        medic.setModVal(intMod)
        navigate.setModVal(intMod)
        occult.setModVal(intMod)
        sciences.setModVal(intMod)
    }

    //update needed values based on new power modifier
    fun updatePOW(powMod: Int) {
        art.setModVal(powMod)
        music.setModVal(powMod)
        leadership.setModVal(powMod)
        style.setModVal(powMod)
        magicAppraise.setModVal(powMod)
    }

    //update needed values based on new willpower modifier
    fun updateWP(wpMod: Int) {
        intimidate.setModVal(wpMod)
        composure.setModVal(wpMod)
        resistPain.setModVal(wpMod)
    }

    //update needed values based on new perception modifier
    fun updatePER(perMod: Int) {
        notice.setModVal(perMod)
        search.setModVal(perMod)
        track.setModVal(perMod)
        hide.setModVal(perMod)
    }

    //load characteristic values from a given file reader
    @Throws(IOException::class)
    fun loadList(fileReader: BufferedReader?) {
        fullList.forEach{it.load(fileReader!!, this)}
    }

    //save characteristic values to file
    fun writeList(charInstance: BaseCharacter) {
        fullList.forEach{it.write(charInstance)}
    }

    fun calculateSpent(): Int{
        var total = 0

        fullList.forEach{total += it.pointsIn}

        return total
    }
}