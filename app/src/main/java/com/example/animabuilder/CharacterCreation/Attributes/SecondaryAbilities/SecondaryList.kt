package com.example.animabuilder.CharacterCreation.Attributes.SecondaryAbilities

import com.example.animabuilder.CharacterCreation.Attributes.Class.CharClass
import kotlin.Throws
import com.example.animabuilder.Serializables.SerialOutputStream
import java.io.BufferedReader
import java.io.IOException
import java.io.Serializable

class SecondaryList : Serializable {
    var lvl = 0
    var currentNatTaken = 0
    fun incrementNat(direction: Boolean): Boolean {
        if (direction) {
            return if (currentNatTaken < lvl) {
                currentNatTaken++
                true
            } else false
        } else currentNatTaken--
        return true
    }

    //athletics
    var acrobatics: SecondaryCharacteristic
    var athletics: SecondaryCharacteristic
    var climb: SecondaryCharacteristic
    var jump: SecondaryCharacteristic
    var ride: SecondaryCharacteristic
    var swim: SecondaryCharacteristic

    //creative
    var art: SecondaryCharacteristic
    var dance: SecondaryCharacteristic
    var forging: SecondaryCharacteristic
    var music: SecondaryCharacteristic
    var sleightHand: SecondaryCharacteristic

    //perceptive
    var notice: SecondaryCharacteristic
    var search: SecondaryCharacteristic
    var track: SecondaryCharacteristic

    //social
    var intimidate: SecondaryCharacteristic
    var leadership: SecondaryCharacteristic
    var persuasion: SecondaryCharacteristic
    var style: SecondaryCharacteristic

    //subterfuge
    var disguise: SecondaryCharacteristic
    var hide: SecondaryCharacteristic
    var lockPick: SecondaryCharacteristic
    var poisons: SecondaryCharacteristic
    var theft: SecondaryCharacteristic
    var stealth: SecondaryCharacteristic
    var trapLore: SecondaryCharacteristic

    //intellectual
    var animals: SecondaryCharacteristic
    var appraise: SecondaryCharacteristic
    var herbalLore: SecondaryCharacteristic
    var history: SecondaryCharacteristic
    var memorize: SecondaryCharacteristic
    var magicAppraise: SecondaryCharacteristic
    var medic: SecondaryCharacteristic
    var navigate: SecondaryCharacteristic
    var occult: SecondaryCharacteristic
    var sciences: SecondaryCharacteristic

    //vigor
    var composure: SecondaryCharacteristic
    var strengthFeat: SecondaryCharacteristic
    var resistPain: SecondaryCharacteristic
    fun levelUpdate(lvl: Int, heldClass: CharClass) {
        this.lvl = lvl
        classUpdate(heldClass)
    }

    fun classUpdate(newClass: CharClass) {
        acrobatics.pointsFromClass = newClass.acrobatPerLevel * lvl
        acrobatics.devPerPoint = newClass.athGrowth
        athletics.pointsFromClass = newClass.athletPerLevel * lvl
        athletics.devPerPoint = newClass.athGrowth
        climb.pointsFromClass = newClass.climbPerLevel * lvl
        climb.devPerPoint = newClass.athGrowth
        jump.pointsFromClass = newClass.jumpPerLevel * lvl
        jump.devPerPoint = newClass.athGrowth
        ride.pointsFromClass = newClass.ridePerLevel * lvl
        ride.devPerPoint = newClass.athGrowth
        swim.pointsFromClass = newClass.swimPerLevel * lvl
        swim.devPerPoint = newClass.athGrowth
        art.pointsFromClass = newClass.artPerLevel * lvl
        art.devPerPoint = newClass.creatGrowth
        dance.pointsFromClass = newClass.dancePerLevel * lvl
        dance.devPerPoint = newClass.creatGrowth
        forging.pointsFromClass = newClass.forgePerLevel * lvl
        forging.devPerPoint = newClass.creatGrowth
        music.pointsFromClass = newClass.musicPerLevel * lvl
        music.devPerPoint = newClass.creatGrowth
        sleightHand.pointsFromClass = newClass.sleightPerLevel * lvl
        sleightHand.devPerPoint = newClass.creatGrowth
        notice.pointsFromClass = newClass.noticePerLevel * lvl
        notice.devPerPoint = newClass.percGrowth
        search.pointsFromClass = newClass.searchPerLevel * lvl
        search.devPerPoint = newClass.percGrowth
        track.pointsFromClass = newClass.trackPerLevel * lvl
        track.devPerPoint = newClass.percGrowth
        intimidate.pointsFromClass = newClass.intimidatePerLevel * lvl
        intimidate.devPerPoint = newClass.socGrowth
        leadership.pointsFromClass = newClass.leaderPerLevel * lvl
        leadership.devPerPoint = newClass.socGrowth
        persuasion.pointsFromClass = newClass.persuadePerLevel * lvl
        persuasion.devPerPoint = newClass.socGrowth
        style.pointsFromClass = newClass.stylePerLevel * lvl
        style.devPerPoint = newClass.socGrowth
        disguise.pointsFromClass = newClass.disguisePerLevel * lvl
        disguise.devPerPoint = newClass.subterGrowth
        hide.pointsFromClass = newClass.hidePerLevel * lvl
        hide.devPerPoint = newClass.subterGrowth
        lockPick.pointsFromClass = newClass.lockpickPerLevel * lvl
        lockPick.devPerPoint = newClass.subterGrowth
        poisons.pointsFromClass = newClass.poisonPerLevel * lvl
        poisons.devPerPoint = newClass.subterGrowth
        theft.pointsFromClass = newClass.theftPerLevel * lvl
        theft.devPerPoint = newClass.subterGrowth
        stealth.pointsFromClass = newClass.stealthPerLevel * lvl
        stealth.devPerPoint = newClass.subterGrowth
        trapLore.pointsFromClass = newClass.trapPerLevel * lvl
        trapLore.devPerPoint = newClass.subterGrowth
        animals.pointsFromClass = newClass.animalPerLevel * lvl
        animals.devPerPoint = newClass.intellGrowth
        appraise.pointsFromClass = newClass.appraisePerLevel * lvl
        appraise.devPerPoint = newClass.intellGrowth
        herbalLore.pointsFromClass = newClass.herbPerLevel * lvl
        herbalLore.devPerPoint = newClass.intellGrowth
        history.pointsFromClass = newClass.histPerLevel * lvl
        history.devPerPoint = newClass.intellGrowth
        memorize.pointsFromClass = newClass.memorizePerLevel * lvl
        memorize.devPerPoint = newClass.intellGrowth
        magicAppraise.pointsFromClass = newClass.magAppraisePerLevel * lvl
        magicAppraise.devPerPoint = newClass.intellGrowth
        medic.pointsFromClass = newClass.medicPerLevel * lvl
        medic.devPerPoint = newClass.intellGrowth
        navigate.pointsFromClass = newClass.navPerLevel * lvl
        navigate.devPerPoint = newClass.intellGrowth
        occult.pointsFromClass = newClass.occultPerLevel * lvl
        occult.devPerPoint = newClass.intellGrowth
        sciences.pointsFromClass = newClass.sciencePerLevel * lvl
        sciences.devPerPoint = newClass.intellGrowth
        composure.pointsFromClass = newClass.composePerLevel * lvl
        composure.devPerPoint = newClass.vigGrowth
        strengthFeat.pointsFromClass = newClass.strengthFeatPerLevel * lvl
        strengthFeat.devPerPoint = newClass.vigGrowth
        resistPain.pointsFromClass = newClass.standPainPerLevel * lvl
        resistPain.devPerPoint = newClass.vigGrowth
    }

    fun updateSTR(strMod: Int) {
        jump.modVal = strMod
        strengthFeat.modVal = strMod
    }

    fun updateDEX(dexMod: Int) {
        forging.modVal = dexMod
        sleightHand.modVal = dexMod
        disguise.modVal = dexMod
        lockPick.modVal = dexMod
        theft.modVal = dexMod
        trapLore.modVal = dexMod
    }

    fun updateAGI(agiMod: Int) {
        acrobatics.modVal = agiMod
        athletics.modVal = agiMod
        climb.modVal = agiMod
        ride.modVal = agiMod
        swim.modVal = agiMod
        dance.modVal = agiMod
        stealth.modVal = agiMod
    }

    fun updateINT(intMod: Int) {
        persuasion.modVal = intMod
        poisons.modVal = intMod
        animals.modVal = intMod
        appraise.modVal = intMod
        herbalLore.modVal = intMod
        history.modVal = intMod
        memorize.modVal = intMod
        medic.modVal = intMod
        navigate.modVal = intMod
        occult.modVal = intMod
        sciences.modVal = intMod
    }

    fun updatePOW(powMod: Int) {
        art.modVal = powMod
        music.modVal = powMod
        leadership.modVal = powMod
        style.modVal = powMod
        magicAppraise.modVal = powMod
    }

    fun updateWP(wpMod: Int) {
        intimidate.modVal = wpMod
        composure.modVal = wpMod
        resistPain.modVal = wpMod
    }

    fun updatePER(perMod: Int) {
        notice.modVal = perMod
        search.modVal = perMod
        track.modVal = perMod
        hide.modVal = perMod
    }

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

    init {
        acrobatics = SecondaryCharacteristic()
        athletics = SecondaryCharacteristic()
        climb = SecondaryCharacteristic()
        jump = SecondaryCharacteristic()
        ride = SecondaryCharacteristic()
        swim = SecondaryCharacteristic()
        art = SecondaryCharacteristic()
        dance = SecondaryCharacteristic()
        forging = SecondaryCharacteristic()
        music = SecondaryCharacteristic()
        sleightHand = SecondaryCharacteristic()
        notice = SecondaryCharacteristic()
        search = SecondaryCharacteristic()
        track = SecondaryCharacteristic()
        intimidate = SecondaryCharacteristic()
        leadership = SecondaryCharacteristic()
        persuasion = SecondaryCharacteristic()
        style = SecondaryCharacteristic()
        disguise = SecondaryCharacteristic()
        hide = SecondaryCharacteristic()
        lockPick = SecondaryCharacteristic()
        poisons = SecondaryCharacteristic()
        theft = SecondaryCharacteristic()
        stealth = SecondaryCharacteristic()
        trapLore = SecondaryCharacteristic()
        animals = SecondaryCharacteristic()
        appraise = SecondaryCharacteristic()
        herbalLore = SecondaryCharacteristic()
        history = SecondaryCharacteristic()
        memorize = SecondaryCharacteristic()
        magicAppraise = SecondaryCharacteristic()
        medic = SecondaryCharacteristic()
        navigate = SecondaryCharacteristic()
        occult = SecondaryCharacteristic()
        sciences = SecondaryCharacteristic()
        composure = SecondaryCharacteristic()
        strengthFeat = SecondaryCharacteristic()
        resistPain = SecondaryCharacteristic()
    }
}