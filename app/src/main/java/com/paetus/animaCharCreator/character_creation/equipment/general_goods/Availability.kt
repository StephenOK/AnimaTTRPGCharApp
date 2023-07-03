package com.paetus.animaCharCreator.character_creation.equipment.general_goods

import com.paetus.animaCharCreator.R

/**
 * Chance of finding a good or service available to the player.
 */
enum class Availability {
    Common,
    Uncommon,
    Rare;

    companion object{
        fun toAddress(input: Availability): Int{
            return when(input){
                Common -> R.string.availCommon
                Uncommon -> R.string.availUncommon
                Rare -> R.string.availRare
            }
        }
    }
}