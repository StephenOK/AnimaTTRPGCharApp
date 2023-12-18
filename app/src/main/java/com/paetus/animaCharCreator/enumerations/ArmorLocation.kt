package com.paetus.animaCharCreator.enumerations

import com.paetus.animaCharCreator.R

/**
 * Enumeration for a piece of armor's worn location.
 */
enum class ArmorLocation {
    Shirt,
    FullPlate,
    Breastplate,
    Complete,
    Head;

    companion object{
        /**
         * Converts an ArmorLocation enumeration to its string reference address.
         *
         * @param location armor location to convert
         * @return corresponding string reference for the inputted location
         */
        fun toAddress(location: ArmorLocation): Int{
            return when(location){
                Shirt -> R.string.shirtLocationName
                FullPlate -> R.string.fullPlateName
                Breastplate -> R.string.breastplateName
                Complete -> R.string.completeName
                Head -> R.string.headName
            }
        }
    }
}