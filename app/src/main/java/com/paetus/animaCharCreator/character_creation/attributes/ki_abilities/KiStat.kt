package com.paetus.animaCharCreator.character_creation.attributes.ki_abilities

import androidx.compose.runtime.mutableIntStateOf

/**
 * Object that holds data in regards to a primary characteristics associated ki points and accumulation.
 *
 * @param parent ki object that manages this item
 */
open class KiStat(
    val parent: Ki
){
    //initialize base ki points in this stat
    val baseKiPoints = mutableIntStateOf(value = 5)

    //initialise ki points bought for this stat
    val boughtKiPoints = mutableIntStateOf(value = 0)

    //initialize total ki points in this stat
    val totalKiPoints = mutableIntStateOf(value = 5)

    //initialize base accumulation in this stat
    val baseAccumulation = mutableIntStateOf(value = 1)

    //initialize accumulation bought for this stat
    val boughtAccumulation = mutableIntStateOf(value = 0)

    //initialize total accumulation in this stat
    val totalAccumulation = mutableIntStateOf(value = 1)

    /**
     * Set the bought ki points to the indicated value.
     *
     * @param kiPurchase amount of points to buy in this stat
     */
    open fun setBoughtKiPoints(kiPurchase: Int){
        boughtKiPoints.intValue = kiPurchase
        parent.updateBoughtPoints()
        updateTotalPoints()
    }

    /**
     * Updates the total ki points held in this stat as well as the overall total points.
     */
    fun updateTotalPoints(){
        totalKiPoints.intValue = baseKiPoints.intValue + boughtKiPoints.intValue
        parent.updateTotalPoints()
    }

    /**
     * Set the bought accumulation to the indicated value.
     *
     * @param accPurchase amount of accumulation to buy in this stat
     */
    open fun setBoughtAccumulation(accPurchase: Int){
        boughtAccumulation.intValue = accPurchase
        parent.updateBoughtAcc()
        updateAccumulation()
    }

    /**
     * Updates the total accumulation held in this stat as well as the overall total accumulation.
     */
    fun updateAccumulation(){
        totalAccumulation.intValue = baseAccumulation.intValue + boughtAccumulation.intValue
        parent.updateTotalAcc()
    }

    /**
     * Update function to run on a change in the primary stat's value.
     *
     * @param primeBase value the primary characteristic is set to
     */
    fun primaryUpdate(primeBase: Int){
        //set the base ki points
        baseKiPoints.intValue =
            if(primeBase <= 10) primeBase
            else 10 + ((primeBase - 10) * 2)

        //update the total ki points
        updateTotalPoints()

        //set the base accumulation value
        baseAccumulation.intValue =
            if(primeBase <= 9)
                1
            else if(primeBase in 10..12)
                2
            else if(primeBase in 13..15)
                3
            else
                4

        //update the total accumulation
        updateAccumulation()
    }
}