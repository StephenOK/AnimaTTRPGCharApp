package com.paetus.animaCharCreator.character_creation.attributes.ki_abilities


/**
 * Object that holds data in regards to a primary characteristics associated ki points and accumulation.
 *
 * @param parent ki object that manages this item
 */
class SblKiStat(
    val kiParent: SblKi,
    val kiIndex: Int
): KiStat(kiParent){
    /**
     * Set the bought ki points to the indicated value.
     *
     * @param kiPurchase amount of points to buy in this stat
     */
    override fun setBoughtKiPoints(kiPurchase: Int) {
        //get previous points in this stat
        var preInputVal = 0
        kiParent.charInstance.levelLoop(endLevel = kiParent.charInstance.lvl.intValue - 1){character ->
            preInputVal += character.ki.allKiStats()[kiIndex].boughtKiPoints.intValue
        }

        //apply new points to this level reference
        kiParent.charInstance.getCharAtLevel().ki.allKiStats()[kiIndex].setBoughtKiPoints(kiPurchase - preInputVal)

        //update point totals
        updateInput()
    }

    /**
     * Update the applied points and point totals for this ki item.
     */
    fun updateInput(){
        //reset bought point total
        boughtKiPoints.intValue = 0

        //add all points from each level
        kiParent.charInstance.levelLoop{
            boughtKiPoints.intValue += it.ki.allKiStats()[kiIndex].boughtKiPoints.intValue
        }

        //update ki item and full point total
        updateTotalPoints()
    }

    /**
     * Set the bought accumulation to the indicated value.
     *
     * @param accPurchase amount of accumulation to buy in this stat
     */
    override fun setBoughtAccumulation(accPurchase: Int) {
        //get accumulation acquired in previous levels
        var preInputVal = 0
        kiParent.charInstance.levelLoop(endLevel = kiParent.charInstance.lvl.intValue - 1){character ->
            preInputVal += character.ki.allKiStats()[kiIndex].boughtAccumulation.intValue
        }

        //apply new points to this level record
        kiParent.charInstance.getCharAtLevel().ki.allKiStats()[kiIndex].setBoughtAccumulation(accPurchase - preInputVal)

        //update accumulation items
        accUpdate()
    }

    /**
     * Update the applied accumulation and total accumulation for this item.
     */
    fun accUpdate(){
        //reset input value
        boughtAccumulation.intValue = 0

        //sum up accumulations added to each level
        kiParent.charInstance.levelLoop {
            boughtAccumulation.intValue += it.ki.allKiStats()[kiIndex].boughtAccumulation.intValue
        }

        //update ki item and full accumulation total
        updateAccumulation()
    }

    /**
     * Determine the validity of the point acquisition for this item.
     *
     * @return true if no error found
     */
    fun validPointGrowth(): Boolean{
        return kiParent.charInstance.getCharAtLevel().ki.allKiStats()[kiIndex].boughtKiPoints.intValue >= 0
    }

    /**
     * Determine the validity of the accumulation acquisition for this item.
     *
     * @return true if no error found
     */
    fun validAccGrowth(): Boolean{
        return kiParent.charInstance.getCharAtLevel().ki.allKiStats()[kiIndex].boughtAccumulation.intValue >= 0
    }
}