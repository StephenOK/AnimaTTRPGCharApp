package com.paetus.animaCharCreator.character_creation.attributes.psychic

import com.paetus.animaCharCreator.character_creation.SblChar

/**
 * Holds the data for the SBL Character's psychic abilities.
 *
 * @param charInstance object that holds all of a character's data
 */
class SblPsychic(
    val charInstance: SblChar
): Psychic(charInstance = charInstance) {
    /**
     * Set the amount of base Psychic Points for the character.
     */
    override fun setInnatePsy() {
        //get class total for the character
        innatePsyPoints.intValue =
            //no points for a level 0 character
            if(charInstance.lvl.intValue == 0) 0
            //sum up each level's bonus for the character
            else {
                var output = 1.0

                charInstance.levelLoop(startLevel = 1){
                    output += 1.0/it.classes.getClass().psyPerTurn.toDouble()
                }

                output.toInt()
            }

        updatePsyPointTotal()
    }
}