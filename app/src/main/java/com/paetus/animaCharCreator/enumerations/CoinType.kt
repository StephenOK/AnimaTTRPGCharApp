package com.paetus.animaCharCreator.enumerations

import com.paetus.animaCharCreator.R

/**
 * Enumeration for the types of coin a piece of equipment may cost.
 */
enum class CoinType {
    Gold,
    Silver,
    Copper;

    companion object{
        /**
         * Retrieves the address of the coin type's associated string.
         *
         * @param coin coing type to translate to an address
         * @return associated string reference
         */
        fun toAddress(coin: CoinType): Int{
            return when(coin){
                Gold -> R.string.goldCoinName
                Silver -> R.string.silverCoinName
                Copper -> R.string.copperCoinName
            }
        }
    }
}