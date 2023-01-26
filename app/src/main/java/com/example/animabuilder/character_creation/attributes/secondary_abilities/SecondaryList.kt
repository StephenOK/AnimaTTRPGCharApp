package com.example.animabuilder.character_creation.attributes.secondary_abilities

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

class SecondaryList(private val charInstance: BaseCharacter) : Serializable {
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
    var acrobatics = SecondaryCharacteristic(this)
    var athletics = SecondaryCharacteristic(this)
    var climb = SecondaryCharacteristic(this)
    var jump = SecondaryCharacteristic(this)
    var ride = SecondaryCharacteristic(this)
    var swim = SecondaryCharacteristic(this)

    //creative
    var art = SecondaryCharacteristic(this)
    var dance = SecondaryCharacteristic(this)
    var forging = SecondaryCharacteristic(this)
    var music = SecondaryCharacteristic(this)
    var sleightHand = SecondaryCharacteristic(this)

    //perceptive
    var notice = SecondaryCharacteristic(this)
    var search = SecondaryCharacteristic(this)
    var track = SecondaryCharacteristic(this)

    //social
    var intimidate = SecondaryCharacteristic(this)
    var leadership = SecondaryCharacteristic(this)
    var persuasion = SecondaryCharacteristic(this)
    var style = SecondaryCharacteristic(this)

    //subterfuge
    var disguise = SecondaryCharacteristic(this)
    var hide = SecondaryCharacteristic(this)
    var lockPick = SecondaryCharacteristic(this)
    var poisons = SecondaryCharacteristic(this)
    var theft = SecondaryCharacteristic(this)
    var stealth = SecondaryCharacteristic(this)
    var trapLore = SecondaryCharacteristic(this)

    //intellectual
    var animals = SecondaryCharacteristic(this)
    var appraise = SecondaryCharacteristic(this)
    var herbalLore = SecondaryCharacteristic(this)
    var history = SecondaryCharacteristic(this)
    var memorize = SecondaryCharacteristic(this)
    var magicAppraise = SecondaryCharacteristic(this)
    var medic = SecondaryCharacteristic(this)
    var navigate = SecondaryCharacteristic(this)
    var occult = SecondaryCharacteristic(this)
    var sciences = SecondaryCharacteristic(this)

    //vigor
    var composure = SecondaryCharacteristic(this)
    var strengthFeat = SecondaryCharacteristic(this)
    var resistPain = SecondaryCharacteristic(this)

    val fullList = listOf(acrobatics, athletics, climb, jump, ride, swim, art, dance, forging, music,
    sleightHand, notice, search, track, intimidate, leadership, persuasion, style, disguise, hide,
    lockPick, poisons, theft, stealth, trapLore, animals, appraise, herbalLore, history, memorize,
    magicAppraise, medic, navigate, occult, sciences, composure, strengthFeat, resistPain)

    //update values based on given class change
    fun classUpdate(newClass: CharClass) {
        acrobatics.setPointsFromClass(newClass.acrobatPerLevel * charInstance.lvl)
        acrobatics.setDevPerPoint(newClass.athGrowth)

        athletics.setPointsFromClass(newClass.athletPerLevel * charInstance.lvl)
        athletics.setDevPerPoint(newClass.athGrowth)

        climb.setPointsFromClass(newClass.climbPerLevel * charInstance.lvl)
        climb.setDevPerPoint(newClass.athGrowth)

        jump.setPointsFromClass(newClass.jumpPerLevel * charInstance.lvl)
        jump.setDevPerPoint(newClass.athGrowth)

        ride.setPointsFromClass(newClass.ridePerLevel * charInstance.lvl)
        ride.setDevPerPoint(newClass.athGrowth)

        swim.setPointsFromClass(newClass.swimPerLevel * charInstance.lvl)
        swim.setDevPerPoint(newClass.athGrowth)

        art.setPointsFromClass(newClass.artPerLevel * charInstance.lvl)
        art.setDevPerPoint(newClass.creatGrowth)

        dance.setPointsFromClass(newClass.dancePerLevel * charInstance.lvl)
        dance.setDevPerPoint(newClass.creatGrowth)

        forging.setPointsFromClass(newClass.forgePerLevel * charInstance.lvl)
        forging.setDevPerPoint(newClass.creatGrowth)

        music.setPointsFromClass(newClass.musicPerLevel * charInstance.lvl)
        music.setDevPerPoint(newClass.creatGrowth)

        sleightHand.setPointsFromClass(newClass.sleightPerLevel * charInstance.lvl)
        sleightHand.setDevPerPoint(newClass.creatGrowth)

        notice.setPointsFromClass(newClass.noticePerLevel * charInstance.lvl)
        notice.setDevPerPoint(newClass.percGrowth)

        search.setPointsFromClass(newClass.searchPerLevel * charInstance.lvl)
        search.setDevPerPoint(newClass.percGrowth)

        track.setPointsFromClass(newClass.trackPerLevel * charInstance.lvl)
        track.setDevPerPoint(newClass.percGrowth)

        intimidate.setPointsFromClass(newClass.intimidatePerLevel * charInstance.lvl)
        intimidate.setDevPerPoint(newClass.socGrowth)

        leadership.setPointsFromClass(newClass.leaderPerLevel * charInstance.lvl)
        leadership.setDevPerPoint(newClass.socGrowth)

        persuasion.setPointsFromClass(newClass.persuadePerLevel * charInstance.lvl)
        persuasion.setDevPerPoint(newClass.socGrowth)

        style.setPointsFromClass(newClass.stylePerLevel * charInstance.lvl)
        style.setDevPerPoint(newClass.socGrowth)

        disguise.setPointsFromClass(newClass.disguisePerLevel * charInstance.lvl)
        disguise.setDevPerPoint(newClass.subterGrowth)

        hide.setPointsFromClass(newClass.hidePerLevel * charInstance.lvl)
        hide.setDevPerPoint(newClass.subterGrowth)

        lockPick.setPointsFromClass(newClass.lockpickPerLevel * charInstance.lvl)
        lockPick.setDevPerPoint(newClass.subterGrowth)

        poisons.setPointsFromClass(newClass.poisonPerLevel * charInstance.lvl)
        poisons.setDevPerPoint(newClass.subterGrowth)

        theft.setPointsFromClass(newClass.theftPerLevel * charInstance.lvl)
        theft.setDevPerPoint(newClass.subterGrowth)

        stealth.setPointsFromClass(newClass.stealthPerLevel * charInstance.lvl)
        stealth.setDevPerPoint(newClass.subterGrowth)

        trapLore.setPointsFromClass(newClass.trapPerLevel * charInstance.lvl)
        trapLore.setDevPerPoint(newClass.subterGrowth)

        animals.setPointsFromClass(newClass.animalPerLevel * charInstance.lvl)
        animals.setDevPerPoint(newClass.intellGrowth)

        appraise.setPointsFromClass(newClass.appraisePerLevel * charInstance.lvl)
        appraise.setDevPerPoint(newClass.intellGrowth)

        herbalLore.setPointsFromClass(newClass.herbPerLevel * charInstance.lvl)
        herbalLore.setDevPerPoint(newClass.intellGrowth)

        history.setPointsFromClass(newClass.histPerLevel * charInstance.lvl)
        history.setDevPerPoint(newClass.intellGrowth)

        memorize.setPointsFromClass(newClass.memorizePerLevel * charInstance.lvl)
        memorize.setDevPerPoint(newClass.intellGrowth)

        magicAppraise.setPointsFromClass(newClass.magAppraisePerLevel * charInstance.lvl)
        magicAppraise.setDevPerPoint(newClass.intellGrowth)

        medic.setPointsFromClass(newClass.medicPerLevel * charInstance.lvl)
        medic.setDevPerPoint(newClass.intellGrowth)

        navigate.setPointsFromClass(newClass.navPerLevel * charInstance.lvl)
        navigate.setDevPerPoint(newClass.intellGrowth)

        occult.setPointsFromClass(newClass.occultPerLevel * charInstance.lvl)
        occult.setDevPerPoint(newClass.intellGrowth)

        sciences.setPointsFromClass(newClass.sciencePerLevel * charInstance.lvl)
        sciences.setDevPerPoint(newClass.intellGrowth)

        composure.setPointsFromClass(newClass.composePerLevel * charInstance.lvl)
        composure.setDevPerPoint(newClass.vigGrowth)

        strengthFeat.setPointsFromClass(newClass.strengthFeatPerLevel * charInstance.lvl)
        strengthFeat.setDevPerPoint(newClass.vigGrowth)

        resistPain.setPointsFromClass(newClass.standPainPerLevel * charInstance.lvl)
        resistPain.setDevPerPoint(newClass.vigGrowth)
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

    fun intToCharacteristic(input: Int): SecondaryCharacteristic?{
        return when(input){
            0 -> charInstance.secondaryList.acrobatics
            1 -> charInstance.secondaryList.athletics
            2 -> charInstance.secondaryList.climb
            3 -> charInstance.secondaryList.jump
            4 -> charInstance.secondaryList.ride
            5 -> charInstance.secondaryList.swim

            6 -> charInstance.secondaryList.art
            7 -> charInstance.secondaryList.dance
            8 -> charInstance.secondaryList.forging
            9 -> charInstance.secondaryList.music
            10 -> charInstance.secondaryList.sleightHand

            11 -> charInstance.secondaryList.notice
            12 -> charInstance.secondaryList.search
            13 -> charInstance.secondaryList.track

            14 -> charInstance.secondaryList.intimidate
            15 -> charInstance.secondaryList.leadership
            16 -> charInstance.secondaryList.persuasion
            17 -> charInstance.secondaryList.style

            18 -> charInstance.secondaryList.disguise
            19 -> charInstance.secondaryList.hide
            20 -> charInstance.secondaryList.lockPick
            21 -> charInstance.secondaryList.poisons
            22 -> charInstance.secondaryList.theft
            23 -> charInstance.secondaryList.stealth
            24 -> charInstance.secondaryList.trapLore

            25 -> charInstance.secondaryList.animals
            26 -> charInstance.secondaryList.appraise
            27 -> charInstance.secondaryList.herbalLore
            28 -> charInstance.secondaryList.history
            29 -> charInstance.secondaryList.memorize
            30 -> charInstance.secondaryList.magicAppraise
            31 -> charInstance.secondaryList.medic
            32 -> charInstance.secondaryList.navigate
            33 -> charInstance.secondaryList.occult
            34 -> charInstance.secondaryList.sciences

            35 -> charInstance.secondaryList.composure
            36 -> charInstance.secondaryList.strengthFeat
            37 -> charInstance.secondaryList.resistPain
            else -> null
        }
    }

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
}