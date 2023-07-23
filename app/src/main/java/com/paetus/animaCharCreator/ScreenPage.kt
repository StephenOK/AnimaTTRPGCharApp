package com.paetus.animaCharCreator

/**
 * Enumeration that denotes the page of the character aspect that is being editted.
 */
enum class ScreenPage {
    Character,
    Combat,
    SecondaryCharacteristics,
    Advantages,
    Modules,
    Ki,
    Magic,
    Summoning,
    Psychic,
    Equipment;

    companion object {
        /**
         * Convert a ScreenPage enumeration to a string reference.
         *
         * @param input enumeration to convert
         * @return corresponding string resource for the inputted item
         */
        fun toAddress(input: ScreenPage): Int {
            return when (input) {
                Character -> R.string.charLabel
                Combat -> R.string.combatLabel
                SecondaryCharacteristics -> R.string.secondaryLabel
                Advantages -> R.string.advantageLabel
                Modules -> R.string.modulesLabel
                Ki -> R.string.kiLabel
                Magic -> R.string.magicLabel
                Summoning -> R.string.summonLabel
                Psychic -> R.string.psychicLabel
                Equipment -> R.string.equipmentLabel
            }
        }
    }
}