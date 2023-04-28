package com.example.animabuilder.character_creation.attributes.ki_abilities

import androidx.compose.runtime.mutableStateOf

/**
 * Object that holds data in regards to a primary characteristics associated ki points and accumulation.
 *
 * @param parent ki object that manages this item
 */
class KiStat(
    val parent: Ki
){
    //initialize base ki points in this stat
    val baseKiPoints = mutableStateOf(0)

    //initialise ki points bought for this stat
    val boughtKiPoints = mutableStateOf(0)

    //initialize total ki points in this stat
    val totalKiPoints = mutableStateOf(0)

    //initialize base accumulation in this stat
    val baseAccumulation = mutableStateOf(0)

    //initialize accumulation bought for this stat
    val boughtAccumulation = mutableStateOf(0)

    //initialize total accumulation in this stat
    val totalAccumulation = mutableStateOf(0)

    /**
     * Set the bought ki points to the indicated value.
     *
     * @param input amount of points to buy in this stat
     */
    @JvmName("setBoughtKiPoints1")
    fun setBoughtKiPoints(input: Int){
        boughtKiPoints.value = input
        parent.updateBoughtPoints()
        updateTotalPoints()
    }

    /**
     * Updates the total ki points held in this stat as well as the overall total points.
     */
    fun updateTotalPoints(){
        totalKiPoints.value = baseKiPoints.value + boughtKiPoints.value
        parent.updateTotalPoints()
    }

    /**
     * Set the bought accumulation to the indicated value.
     *
     * @param input amount of accumulation to buy in this stat
     */
    @JvmName("setBoughtAccumulation1")
    fun setBoughtAccumulation(input: Int){
        boughtAccumulation.value = input
        parent.updateBoughtAcc()
        updateAccumulation()
    }

    /**
     * Updates the total accumulation held in this stat as well as the overall total accumulation.
     */
    fun updateAccumulation(){
        totalAccumulation.value = baseAccumulation.value + boughtAccumulation.value
        parent.updateTotalAcc()
    }

    /**
     * Update function to run on a change in the primary stat's value.
     *
     * @param input value the primary characteristic is set to
     */
    fun primaryUpdate(input: Int){
        //set the base ki points
        baseKiPoints.value =
            if(input <= 10) input
            else 10 + ((input - 10) * 2)

        //update the total ki points
        updateTotalPoints()

        //set the base accumulation value
        baseAccumulation.value =
            if(input <= 9)
                1
            else if(input in 10..12)
                2
            else if(input in 13..15)
                3
            else
                4

        //update the total accumulation
        updateAccumulation()
    }
}