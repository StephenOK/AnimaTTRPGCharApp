package com.paetus.animaCharCreator.character_creation.attributes.primary_abilities

import com.paetus.animaCharCreator.character_creation.SblChar

/**
 * Subclass of PrimaryList for use in a SBL character.
 *
 * @param charInstance object that contains all of the character's stats
 */
class SblPrimaryList(
    private val charInstance: SblChar
): PrimaryList(charInstance) {
    override val str = SblPrimaryChar(
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

    override val dex = SblPrimaryChar(
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

    override val agi = SblPrimaryChar(
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

    override val con = SblPrimaryChar(
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

    override val int = SblPrimaryChar(
        charInstance = charInstance,
        advantageCap = 13,
        charIndex = 4,
        setUpdate = {_, _ ->
            charInstance.secondaryList.updateINT()
            charInstance.magic.setMagicLevelMax()
        }
    )

    override val pow = SblPrimaryChar(
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

    override val wp = SblPrimaryChar(
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

    override val per = SblPrimaryChar(
        charInstance = charInstance,
        advantageCap = 13,
        charIndex = 7,
        setUpdate = {_, _ ->
            charInstance.secondaryList.updatePER()
        }
    )

    /**
     * Determines if the total level bonuses applied are valid.
     *
     * @return true if bonus does not exceed half of the character's level
     */
    override fun validLevelBonuses(): Boolean {
        //notify of bad characteristic bonus growth if one found
        allPrimaries().forEach{primary ->
            if(!(primary as SblPrimaryChar).validGrowth()) return false
        }

        //run the normal check
        return super.validLevelBonuses()
    }
}