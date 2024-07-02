package com.paetus.animaCharCreator.character_creation.attributes.primary_abilities

import com.paetus.animaCharCreator.character_creation.BaseCharacter
import java.io.BufferedReader
import java.io.ByteArrayOutputStream

/**
 * Record of all of a character's primary characteristic.
 *
 * @param charInstance object that contains all of the character's stats
 */
open class PrimaryList(private val charInstance: BaseCharacter){
    open val str = PrimaryCharacteristic(
        charInstance = charInstance,
        advantageCap = 11,
        charIndex = 0,
        setUpdate = {mod, total ->
            charInstance.setWeightIndex(weightValue = total)
            charInstance.combat.wearArmor.setModPoints(modVal = mod)
            charInstance.updateSize()

            charInstance.secondaryList.updateSTR()
            charInstance.ki.strKi.primaryUpdate(primeBase = total)
        }
    )

    open val dex = PrimaryCharacteristic(
        charInstance = charInstance,
        advantageCap = 11,
        charIndex = 1,
        setUpdate = {mod, total ->
            charInstance.secondaryList.updateDEX()
            charInstance.combat.attack.setModPoints(modVal = mod)
            charInstance.combat.block.setModPoints(modVal = mod)
            charInstance.combat.updateInitiative()
            charInstance.ki.dexKi.primaryUpdate(primeBase = total)
            charInstance.magic.calcMagProj()
            charInstance.psychic.updatePsyProjection()
        }
    )

    open val agi = PrimaryCharacteristic(
        charInstance = charInstance,
        advantageCap = 11,
        charIndex = 2,
        setUpdate = {mod, total ->
            charInstance.setMovement(moveValue = total)
            charInstance.secondaryList.updateAGI()
            charInstance.combat.dodge.setModPoints(modVal = mod)
            charInstance.combat.updateInitiative()
            charInstance.ki.agiKi.primaryUpdate(primeBase = total)
        }
    )

    open val con = PrimaryCharacteristic(
        charInstance = charInstance,
        advantageCap = 11,
        charIndex = 3,
        setUpdate = {mod, total ->
            charInstance.combat.updateFatigue()
            charInstance.combat.getBaseRegen()
            charInstance.updateSize()

            charInstance.combat.updateLifeBase()
            charInstance.combat.updateLifePoints()
            charInstance.combat.diseaseRes.setMod(newMod = mod)
            charInstance.combat.venomRes.setMod(newMod = mod)
            charInstance.combat.physicalRes.setMod(newMod = mod)
            charInstance.secondaryList.updateCON()
            charInstance.ki.conKi.primaryUpdate(primeBase = total)
        }
    )

    open val int = PrimaryCharacteristic(
        charInstance = charInstance,
        advantageCap = 13,
        charIndex = 4,
        setUpdate = {_, _ ->
            charInstance.secondaryList.updateINT()
            charInstance.magic.setMagicLevelMax()
        }
    )

    open val pow = PrimaryCharacteristic(
        charInstance = charInstance,
        advantageCap = 13,
        charIndex = 5,
        setUpdate = {mod, total ->
            charInstance.secondaryList.updatePOW()
            charInstance.combat.magicRes.setMod(newMod = mod)
            charInstance.ki.powKi.primaryUpdate(primeBase = total)
            charInstance.magic.setBaseZeon()
            charInstance.magic.setBaseZeonAcc()
            charInstance.summoning.summon.setModVal(modValue = mod)
            charInstance.summoning.bind.setModVal(modValue = mod)
            charInstance.summoning.banish.setModVal(modValue = mod)
        }
    )

    open val wp = PrimaryCharacteristic(
        charInstance = charInstance,
        advantageCap = 13,
        charIndex = 6,
        setUpdate = {mod, total ->
            charInstance.secondaryList.updateWP()
            charInstance.combat.psychicRes.setMod(newMod = mod)
            charInstance.ki.wpKi.primaryUpdate(primeBase = total)
            charInstance.summoning.control.setModVal(modValue = mod)
            charInstance.psychic.setBasePotential()
        }
    )

    open val per = PrimaryCharacteristic(
        charInstance = charInstance,
        advantageCap = 13,
        charIndex = 7,
        setUpdate = {_, _ ->
            charInstance.secondaryList.updatePER()
        }
    )

    /**
     * Retrieves all primary characteristic items.
     *
     * @return list of primary characteristics
     */
    fun allPrimaries(): List<PrimaryCharacteristic>{
        return listOf(str, dex, agi, con, int, pow, wp, per)
    }

    /**
     * Determines if the total level bonuses applied are valid.
     *
     * @return true if bonus does not exceed half of the character's level
     */
    open fun validLevelBonuses(): Boolean{
        return getPrimaryBonusTotal() <= charInstance.lvl.intValue/2
    }

    /**
     * Gets the total amount of primary bonus points applied to the character.
     *
     * @return number of primary bonus points spent
     */
    fun getPrimaryBonusTotal(): Int{
        //initialize counter
        var total = 0

        //add points from each primary characteristic
        allPrimaries().forEach{primary -> total += primary.levelBonus.intValue}

        //give final total
        return total
    }

    /**
     * Get data for each of the primary characteristics.
     *
     * @param fileReader file to get the data from
     */
    fun loadPrimaries(fileReader: BufferedReader){
        allPrimaries().forEach{primary -> primary.load(fileReader = fileReader)}
    }

    /**
     * Save all primary characteristic data to file.
     *
     * @param byteArray output stream for the data
     */
    fun writePrimaries(byteArray: ByteArrayOutputStream) {
        allPrimaries().forEach{primary -> primary.write(byteArray = byteArray) }
    }
}