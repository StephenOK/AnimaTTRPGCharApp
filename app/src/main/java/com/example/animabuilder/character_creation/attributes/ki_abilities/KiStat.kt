package com.example.animabuilder.character_creation.attributes.ki_abilities

import java.io.Serializable

class KiStat(
    val parent: Ki
): Serializable {
    var baseKiPoints = 0
    var boughtKiPoints = 0
    var totalKiPoints = 0

    var baseAccumulation = 0
    var boughtAccumulation = 0
    var totalAccumulation = 0

    @JvmName("setBoughtKiPoints1")
    fun setBoughtKiPoints(input: Int){
        boughtKiPoints = input
        parent.updateBoughtPoints()
        updateTotalPoints()
    }

    fun updateTotalPoints(){
        totalKiPoints = baseKiPoints + boughtKiPoints
        parent.updateTotalPoints()
    }

    @JvmName("setBoughtAccumulation1")
    fun setBoughtAccumulation(input: Int){
        boughtAccumulation = input
        parent.updateBoughtAcc()
        updateAccumulation()
    }

    fun updateAccumulation(){
        totalAccumulation = baseAccumulation + boughtAccumulation
        parent.updateTotalAcc()
    }

    fun primaryUpdate(input: Int){
        baseKiPoints =
            if(input <= 10) input
            else 10 + ((input - 10) * 2)

        updateTotalPoints()

        baseAccumulation =
            if(input <= 9)
                1
            else if(input in 10..12)
                2
            else if(input in 13..15)
                3
            else
                4

        updateAccumulation()
    }
}