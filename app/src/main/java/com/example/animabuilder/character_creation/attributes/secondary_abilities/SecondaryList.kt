package com.example.animabuilder.character_creation.attributes.secondary_abilities

import com.example.animabuilder.character_creation.attributes.class_objects.CharClass
import kotlin.Throws
import com.example.animabuilder.serializables.SerialOutputStream
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

    //update values based on given level change
    fun levelUpdate(lvl: Int, heldClass: CharClass) {
        this.lvl = lvl
        classUpdate(heldClass)
    }

    //update values based on given class change
    fun classUpdate(newClass: CharClass) {
        acrobatics.setPointsFromClass(newClass.acrobatPerLevel * lvl)
        acrobatics.devPerPoint = newClass.athGrowth

        athletics.setPointsFromClass(newClass.athletPerLevel * lvl)
        athletics.devPerPoint = newClass.athGrowth

        climb.setPointsFromClass(newClass.climbPerLevel * lvl)
        climb.devPerPoint = newClass.athGrowth

        jump.setPointsFromClass(newClass.jumpPerLevel * lvl)
        jump.devPerPoint = newClass.athGrowth

        ride.setPointsFromClass(newClass.ridePerLevel * lvl)
        ride.devPerPoint = newClass.athGrowth

        swim.setPointsFromClass(newClass.swimPerLevel * lvl)
        swim.devPerPoint = newClass.athGrowth

        art.setPointsFromClass(newClass.artPerLevel * lvl)
        art.devPerPoint = newClass.creatGrowth

        dance.setPointsFromClass(newClass.dancePerLevel * lvl)
        dance.devPerPoint = newClass.creatGrowth

        forging.setPointsFromClass(newClass.forgePerLevel * lvl)
        forging.devPerPoint = newClass.creatGrowth

        music.setPointsFromClass(newClass.musicPerLevel * lvl)
        music.devPerPoint = newClass.creatGrowth

        sleightHand.setPointsFromClass(newClass.sleightPerLevel * lvl)
        sleightHand.devPerPoint = newClass.creatGrowth

        notice.setPointsFromClass(newClass.noticePerLevel * lvl)
        notice.devPerPoint = newClass.percGrowth

        search.setPointsFromClass(newClass.searchPerLevel * lvl)
        search.devPerPoint = newClass.percGrowth

        track.setPointsFromClass(newClass.trackPerLevel * lvl)
        track.devPerPoint = newClass.percGrowth

        intimidate.setPointsFromClass(newClass.intimidatePerLevel * lvl)
        intimidate.devPerPoint = newClass.socGrowth

        leadership.setPointsFromClass(newClass.leaderPerLevel * lvl)
        leadership.devPerPoint = newClass.socGrowth

        persuasion.setPointsFromClass(newClass.persuadePerLevel * lvl)
        persuasion.devPerPoint = newClass.socGrowth

        style.setPointsFromClass(newClass.stylePerLevel * lvl)
        style.devPerPoint = newClass.socGrowth

        disguise.setPointsFromClass(newClass.disguisePerLevel * lvl)
        disguise.devPerPoint = newClass.subterGrowth

        hide.setPointsFromClass(newClass.hidePerLevel * lvl)
        hide.devPerPoint = newClass.subterGrowth

        lockPick.setPointsFromClass(newClass.lockpickPerLevel * lvl)
        lockPick.devPerPoint = newClass.subterGrowth

        poisons.setPointsFromClass(newClass.poisonPerLevel * lvl)
        poisons.devPerPoint = newClass.subterGrowth

        theft.setPointsFromClass(newClass.theftPerLevel * lvl)
        theft.devPerPoint = newClass.subterGrowth

        stealth.setPointsFromClass(newClass.stealthPerLevel * lvl)
        stealth.devPerPoint = newClass.subterGrowth

        trapLore.setPointsFromClass(newClass.trapPerLevel * lvl)
        trapLore.devPerPoint = newClass.subterGrowth

        animals.setPointsFromClass(newClass.animalPerLevel * lvl)
        animals.devPerPoint = newClass.intellGrowth

        appraise.setPointsFromClass(newClass.appraisePerLevel * lvl)
        appraise.devPerPoint = newClass.intellGrowth

        herbalLore.setPointsFromClass(newClass.herbPerLevel * lvl)
        herbalLore.devPerPoint = newClass.intellGrowth

        history.setPointsFromClass(newClass.histPerLevel * lvl)
        history.devPerPoint = newClass.intellGrowth

        memorize.setPointsFromClass(newClass.memorizePerLevel * lvl)
        memorize.devPerPoint = newClass.intellGrowth

        magicAppraise.setPointsFromClass(newClass.magAppraisePerLevel * lvl)
        magicAppraise.devPerPoint = newClass.intellGrowth

        medic.setPointsFromClass(newClass.medicPerLevel * lvl)
        medic.devPerPoint = newClass.intellGrowth

        navigate.setPointsFromClass(newClass.navPerLevel * lvl)
        navigate.devPerPoint = newClass.intellGrowth

        occult.setPointsFromClass(newClass.occultPerLevel * lvl)
        occult.devPerPoint = newClass.intellGrowth

        sciences.setPointsFromClass(newClass.sciencePerLevel * lvl)
        sciences.devPerPoint = newClass.intellGrowth

        composure.setPointsFromClass(newClass.composePerLevel * lvl)
        composure.devPerPoint = newClass.vigGrowth

        strengthFeat.setPointsFromClass(newClass.strengthFeatPerLevel * lvl)
        strengthFeat.devPerPoint = newClass.vigGrowth

        resistPain.setPointsFromClass(newClass.standPainPerLevel * lvl)
        resistPain.devPerPoint = newClass.vigGrowth
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
        acrobatics.load(fileReader!!, this)
        athletics.load(fileReader, this)
        climb.load(fileReader, this)
        jump.load(fileReader, this)
        ride.load(fileReader, this)
        swim.load(fileReader, this)
        art.load(fileReader, this)
        dance.load(fileReader, this)
        forging.load(fileReader, this)
        music.load(fileReader, this)
        sleightHand.load(fileReader, this)
        notice.load(fileReader, this)
        search.load(fileReader, this)
        track.load(fileReader, this)
        intimidate.load(fileReader, this)
        leadership.load(fileReader, this)
        persuasion.load(fileReader, this)
        style.load(fileReader, this)
        disguise.load(fileReader, this)
        hide.load(fileReader, this)
        lockPick.load(fileReader, this)
        poisons.load(fileReader, this)
        theft.load(fileReader, this)
        stealth.load(fileReader, this)
        trapLore.load(fileReader, this)
        animals.load(fileReader, this)
        appraise.load(fileReader, this)
        herbalLore.load(fileReader, this)
        history.load(fileReader, this)
        memorize.load(fileReader, this)
        magicAppraise.load(fileReader, this)
        medic.load(fileReader, this)
        navigate.load(fileReader, this)
        occult.load(fileReader, this)
        sciences.load(fileReader, this)
        composure.load(fileReader, this)
        strengthFeat.load(fileReader, this)
        resistPain.load(fileReader, this)
    }

    //save characteristic values to file
    fun writeList(byteArray: SerialOutputStream?) {
        acrobatics.write(byteArray!!)
        athletics.write(byteArray)
        climb.write(byteArray)
        jump.write(byteArray)
        ride.write(byteArray)
        swim.write(byteArray)
        art.write(byteArray)
        dance.write(byteArray)
        forging.write(byteArray)
        music.write(byteArray)
        sleightHand.write(byteArray)
        notice.write(byteArray)
        search.write(byteArray)
        track.write(byteArray)
        intimidate.write(byteArray)
        leadership.write(byteArray)
        persuasion.write(byteArray)
        style.write(byteArray)
        disguise.write(byteArray)
        hide.write(byteArray)
        lockPick.write(byteArray)
        poisons.write(byteArray)
        theft.write(byteArray)
        stealth.write(byteArray)
        trapLore.write(byteArray)
        animals.write(byteArray)
        appraise.write(byteArray)
        herbalLore.write(byteArray)
        history.write(byteArray)
        memorize.write(byteArray)
        magicAppraise.write(byteArray)
        medic.write(byteArray)
        navigate.write(byteArray)
        occult.write(byteArray)
        sciences.write(byteArray)
        composure.write(byteArray)
        strengthFeat.write(byteArray)
        resistPain.write(byteArray)
    }
}