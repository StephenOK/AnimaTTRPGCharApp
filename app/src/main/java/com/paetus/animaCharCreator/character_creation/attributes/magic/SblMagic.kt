package com.paetus.animaCharCreator.character_creation.attributes.magic

import com.paetus.animaCharCreator.character_creation.SblChar
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.NecromancyBook
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.SblMagBook
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.SblNecromancy

/**
 * Component that holds a SBL Character's magical abilities.
 *
 * @param sblChar object that holds all of the character's data
 */
class SblMagic(val sblChar: SblChar): Magic(sblChar) {
    override val lightBook = SblMagBook(sblChar, this, getMagLibrary().lightSpells, 1, 0)
    override val darkBook = SblMagBook(sblChar, this, getMagLibrary().darkSpells, 0, 1)
    override val creationBook = SblMagBook(sblChar, this, getMagLibrary().creationSpells, 3, 2)
    override val destructionBook = SblMagBook(sblChar, this, getMagLibrary().destructionSpells, 2, 3)
    override val airBook = SblMagBook(sblChar, this, getMagLibrary().airSpells, 5, 4)
    override val earthBook = SblMagBook(sblChar, this, getMagLibrary().earthSpells, 4, 5)
    override val waterBook = SblMagBook(sblChar, this, getMagLibrary().waterSpells, 7, 6)
    override val fireBook = SblMagBook(sblChar, this, getMagLibrary().fireSpells, 6, 7)
    override val essenceBook = SblMagBook(sblChar, this, getMagLibrary().essenceSpells, 9, 8)
    override val illusionBook = SblMagBook(sblChar, this, getMagLibrary().illusionSpells, 8, 9)
    override val necromancyBook = SblNecromancy(sblChar, getMagLibrary().necromancySpells, this)

    /**
     * Gets the class's zeon point DP cost.
     */
    override fun getZeonPointCost(): Int {
        return sblChar.getCharAtLevel().magic.getZeonPointCost()
    }

    /**
     * Gets the class's zeon accumulation DP cost.
     */
    override fun getZeonAccCost(): Int {
        return sblChar.getCharAtLevel().magic.getZeonAccCost()
    }

    /**
     * Gets the class's magic projection DP cost.
     */
    override fun getMagProjCost(): Int {
        return sblChar.getCharAtLevel().magic.getMagProjCost()
    }

    /**
     * Set the number of Zeon points the character has bought.
     *
     * @param zeonBuy the number of Zeon points bought for the character
     */
    override fun buyZeon(zeonBuy: Int) {
        //determine points from previous levels
        var preInputVal = 0
        sblChar.levelLoop(endLevel = sblChar.lvl.intValue - 1){ character ->
            preInputVal += character.magic.boughtZeon.intValue
        }

        //apply new points
        sblChar.getCharAtLevel().magic.buyZeon(zeonBuy = zeonBuy - preInputVal)

        //update zeon point total
        updateBoughtZeon()
    }

    /**
     * The user attempts to buy the given amount of accumulation.
     *
     * @param accBuy the accumulation amount bought for the character
     */
    override fun buyZeonAcc(accBuy: Int) {
        //determine points from previous levels
        var preInputVal = 0
        sblChar.levelLoop(endLevel = sblChar.lvl.intValue - 1){ character ->
            preInputVal += character.magic.zeonAccMult.intValue - 1
        }

        //apply new points
        sblChar.getCharAtLevel().magic.buyZeonAcc(accBuy = accBuy - preInputVal)

        //update zeon accumulation
        updateZeonAcc()
    }

    /**
     * Set the amount of magic projection bought by the user.
     *
     * @param projBuy the projection amount bought for the character
     */
    override fun buyMagProj(projBuy: Int) {
        //determine points from previous levels
        var preInputVal = 0
        sblChar.levelLoop(endLevel = sblChar.lvl.intValue - 1){ character ->
            preInputVal += character.magic.boughtMagProjection.intValue
        }

        //apply new points
        sblChar.getCharAtLevel().magic.buyMagProj(projBuy = projBuy - preInputVal)

        //update magic projection
        updateMagProj()
    }

    /**
     * Updates the number of bought zeon points.
     */
    private fun updateBoughtZeon(){
        //reset bought value
        boughtZeon.intValue = 0

        //add points from each level
        sblChar.levelLoop{ character ->
            boughtZeon.intValue += character.magic.boughtZeon.intValue
        }

        //reevaluate total zeon points
        calcMaxZeon()
        sblChar.updateTotalSpent()
    }

    /**
     * Updates the number of zeon accumulation points bought.
     */
    private fun updateZeonAcc(){
        //reset bought value
        zeonAccMult.intValue = 1

        //add points from each level
        sblChar.levelLoop{ character ->
            zeonAccMult.intValue += character.magic.zeonAccMult.intValue - 1
        }

        //reevaluate total accumulation
        calcZeonAcc()
        sblChar.updateTotalSpent()
    }

    /**
     * Updates the number of magic projection points bought.
     */
    private fun updateMagProj(){
        //reset bought value
        boughtMagProjection.intValue = 0

        //add points from each level
        sblChar.levelLoop{ character ->
            boughtMagProjection.intValue += character.magic.boughtMagProjection.intValue
        }

        //reevaluate total projection
        calcMagProj()
        sblChar.updateTotalSpent()
    }

    /**
     * Update the total number of Zeon points gained from levels.
     */
    override fun updateZeonFromClass() {
        //get total from classes
        zeonFromClass.intValue =
            //sum each level's class bonus
            if(sblChar.lvl.intValue != 0){
                var output = 0

                sblChar.levelLoop(startLevel = 1){
                    output += it.magic.zeonPerLevel.intValue
                }

                output
            }
            //get half of first level's class bonus
            else sblChar.charRefs[0]!!.magic.zeonPerLevel.intValue/2

        calcMaxZeon()
    }

    /**
     * Recalculate the character's maximum Zeon.
     */
    override fun calcMaxZeon() {
        //reset points to base and class points
        zeonMax.intValue = baseZeon.intValue + zeonFromClass.intValue

        //add zeon points from each level
        sblChar.levelLoop{ character ->
            zeonMax.intValue += (character.magic.boughtZeon.intValue * 5)
        }
    }

    /**
     * Updates all primary statuses of all book investment sections.
     */
    fun updateBookPrimaries(){
        retrieveBooks().forEach{it.updatePrimary()}
    }

    /**
     * Function to run on confirmed removal of The Gift advantage.
     */
    override fun loseMagic() {
        //remove magic abilities from each level record
        sblChar.levelLoop(
            endLevel = 20
        ){character ->
            character.magic.loseMagic()
        }

        levelUpdate()
    }

    /**
     * Updates the character's values relevant to level changes.
     */
    fun levelUpdate(){
        //update all magic ability values
        updateBoughtZeon()
        updateZeonAcc()
        updateMagProj()

        //update all character primary states
        updateBookPrimaries()

        //update all magic level investments for element books
        retrieveBooks().forEach{
            if(it is NecromancyBook)
                (it as SblNecromancy).levelUpdate()
            else
                (it as SblMagBook).levelUpdate()
        }
    }

    /**
     * Determines if the spent points in zeon points are valid.
     *
     * @return true if no loss in points
     */
    fun validPointGrowth(): Boolean{return sblChar.getCharAtLevel().magic.boughtZeon.intValue >= 0}

    /**
     * Determines if the spent points in zeon accumulation are valid.
     *
     * @return true if no loss in points
     */
    fun validAccGrowth(): Boolean{return sblChar.getCharAtLevel().magic.zeonAccMult.intValue >= 1}

    /**
     * Determine if the spent points in magic projection are valid.
     *
     * @return true if no loss in points
     */
    fun validProjGrowth(): Boolean{return sblChar.getCharAtLevel().magic.boughtMagProjection.intValue >= 0}
}