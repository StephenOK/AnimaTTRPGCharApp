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

class SecondaryList : Serializable {
    //initialize level indicator
    private var lvl = 0

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
            return if (currentNatTaken < lvl) {
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
    var acrobatics = SecondaryCharacteristic()
    var athletics = SecondaryCharacteristic()
    var climb = SecondaryCharacteristic()
    var jump = SecondaryCharacteristic()
    var ride = SecondaryCharacteristic()
    var swim = SecondaryCharacteristic()

    //creative
    var art = SecondaryCharacteristic()
    var dance = SecondaryCharacteristic()
    var forging = SecondaryCharacteristic()
    var music = SecondaryCharacteristic()
    var sleightHand = SecondaryCharacteristic()

    //perceptive
    var notice = SecondaryCharacteristic()
    var search = SecondaryCharacteristic()
    var track = SecondaryCharacteristic()

    //social
    var intimidate = SecondaryCharacteristic()
    var leadership = SecondaryCharacteristic()
    var persuasion = SecondaryCharacteristic()
    var style = SecondaryCharacteristic()

    //subterfuge
    var disguise = SecondaryCharacteristic()
    var hide = SecondaryCharacteristic()
    var lockPick = SecondaryCharacteristic()
    var poisons = SecondaryCharacteristic()
    var theft = SecondaryCharacteristic()
    var stealth = SecondaryCharacteristic()
    var trapLore = SecondaryCharacteristic()

    //intellectual
    var animals = SecondaryCharacteristic()
    var appraise = SecondaryCharacteristic()
    var herbalLore = SecondaryCharacteristic()
    var history = SecondaryCharacteristic()
    var memorize = SecondaryCharacteristic()
    var magicAppraise = SecondaryCharacteristic()
    var medic = SecondaryCharacteristic()
    var navigate = SecondaryCharacteristic()
    var occult = SecondaryCharacteristic()
    var sciences = SecondaryCharacteristic()

    //vigor
    var composure = SecondaryCharacteristic()
    var strengthFeat = SecondaryCharacteristic()
    var resistPain = SecondaryCharacteristic()

    val fullList = listOf(acrobatics, athletics, climb, jump, ride, swim, art, dance, forging, music,
    sleightHand, notice, search, track, intimidate, leadership, persuasion, style, disguise, hide,
    lockPick, poisons, theft, stealth, trapLore, animals, appraise, herbalLore, history, memorize,
    magicAppraise, medic, navigate, occult, sciences, composure, strengthFeat, resistPain)

    //update values based on given level change
    fun levelUpdate(lvl: Int, heldClass: CharClass) {
        this.lvl = lvl
        classUpdate(heldClass)
    }

    //update values based on given class change
    fun classUpdate(newClass: CharClass) {
        acrobatics.setPointsFromClass(newClass.acrobatPerLevel * lvl)
        acrobatics.setDevPerPoint(newClass.athGrowth)

        athletics.setPointsFromClass(newClass.athletPerLevel * lvl)
        athletics.setDevPerPoint(newClass.athGrowth)

        climb.setPointsFromClass(newClass.climbPerLevel * lvl)
        climb.setDevPerPoint(newClass.athGrowth)

        jump.setPointsFromClass(newClass.jumpPerLevel * lvl)
        jump.setDevPerPoint(newClass.athGrowth)

        ride.setPointsFromClass(newClass.ridePerLevel * lvl)
        ride.setDevPerPoint(newClass.athGrowth)

        swim.setPointsFromClass(newClass.swimPerLevel * lvl)
        swim.setDevPerPoint(newClass.athGrowth)

        art.setPointsFromClass(newClass.artPerLevel * lvl)
        art.setDevPerPoint(newClass.creatGrowth)

        dance.setPointsFromClass(newClass.dancePerLevel * lvl)
        dance.setDevPerPoint(newClass.creatGrowth)

        forging.setPointsFromClass(newClass.forgePerLevel * lvl)
        forging.setDevPerPoint(newClass.creatGrowth)

        music.setPointsFromClass(newClass.musicPerLevel * lvl)
        music.setDevPerPoint(newClass.creatGrowth)

        sleightHand.setPointsFromClass(newClass.sleightPerLevel * lvl)
        sleightHand.setDevPerPoint(newClass.creatGrowth)

        notice.setPointsFromClass(newClass.noticePerLevel * lvl)
        notice.setDevPerPoint(newClass.percGrowth)

        search.setPointsFromClass(newClass.searchPerLevel * lvl)
        search.setDevPerPoint(newClass.percGrowth)

        track.setPointsFromClass(newClass.trackPerLevel * lvl)
        track.setDevPerPoint(newClass.percGrowth)

        intimidate.setPointsFromClass(newClass.intimidatePerLevel * lvl)
        intimidate.setDevPerPoint(newClass.socGrowth)

        leadership.setPointsFromClass(newClass.leaderPerLevel * lvl)
        leadership.setDevPerPoint(newClass.socGrowth)

        persuasion.setPointsFromClass(newClass.persuadePerLevel * lvl)
        persuasion.setDevPerPoint(newClass.socGrowth)

        style.setPointsFromClass(newClass.stylePerLevel * lvl)
        style.setDevPerPoint(newClass.socGrowth)

        disguise.setPointsFromClass(newClass.disguisePerLevel * lvl)
        disguise.setDevPerPoint(newClass.subterGrowth)

        hide.setPointsFromClass(newClass.hidePerLevel * lvl)
        hide.setDevPerPoint(newClass.subterGrowth)

        lockPick.setPointsFromClass(newClass.lockpickPerLevel * lvl)
        lockPick.setDevPerPoint(newClass.subterGrowth)

        poisons.setPointsFromClass(newClass.poisonPerLevel * lvl)
        poisons.setDevPerPoint(newClass.subterGrowth)

        theft.setPointsFromClass(newClass.theftPerLevel * lvl)
        theft.setDevPerPoint(newClass.subterGrowth)

        stealth.setPointsFromClass(newClass.stealthPerLevel * lvl)
        stealth.setDevPerPoint(newClass.subterGrowth)

        trapLore.setPointsFromClass(newClass.trapPerLevel * lvl)
        trapLore.setDevPerPoint(newClass.subterGrowth)

        animals.setPointsFromClass(newClass.animalPerLevel * lvl)
        animals.setDevPerPoint(newClass.intellGrowth)

        appraise.setPointsFromClass(newClass.appraisePerLevel * lvl)
        appraise.setDevPerPoint(newClass.intellGrowth)

        herbalLore.setPointsFromClass(newClass.herbPerLevel * lvl)
        herbalLore.setDevPerPoint(newClass.intellGrowth)

        history.setPointsFromClass(newClass.histPerLevel * lvl)
        history.setDevPerPoint(newClass.intellGrowth)

        memorize.setPointsFromClass(newClass.memorizePerLevel * lvl)
        memorize.setDevPerPoint(newClass.intellGrowth)

        magicAppraise.setPointsFromClass(newClass.magAppraisePerLevel * lvl)
        magicAppraise.setDevPerPoint(newClass.intellGrowth)

        medic.setPointsFromClass(newClass.medicPerLevel * lvl)
        medic.setDevPerPoint(newClass.intellGrowth)

        navigate.setPointsFromClass(newClass.navPerLevel * lvl)
        navigate.setDevPerPoint(newClass.intellGrowth)

        occult.setPointsFromClass(newClass.occultPerLevel * lvl)
        occult.setDevPerPoint(newClass.intellGrowth)

        sciences.setPointsFromClass(newClass.sciencePerLevel * lvl)
        sciences.setDevPerPoint(newClass.intellGrowth)

        composure.setPointsFromClass(newClass.composePerLevel * lvl)
        composure.setDevPerPoint(newClass.vigGrowth)

        strengthFeat.setPointsFromClass(newClass.strengthFeatPerLevel * lvl)
        strengthFeat.setDevPerPoint(newClass.vigGrowth)

        resistPain.setPointsFromClass(newClass.standPainPerLevel * lvl)
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
}