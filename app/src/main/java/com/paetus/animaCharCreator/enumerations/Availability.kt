package com.paetus.animaCharCreator.enumerations

import com.paetus.animaCharCreator.R

/**
 * Chance of finding a good or service available to the player.
 */
enum class Availability {
    Common,
    Uncommon,
    Rare;

    companion object{
        /**
         * Retrieves the address of the availability's associated string.
         *
         * @param availability availability to translate to an address
         * @return associated string reference
         */
        fun toAddress(availability: Availability): Int{
            return when(availability){
                Common -> R.string.availCommon
                Uncommon -> R.string.availUncommon
                Rare -> R.string.availRare
            }
        }
    }
}