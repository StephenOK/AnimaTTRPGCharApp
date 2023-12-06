package com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.base

import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.effect.TechniqueEffect
import com.paetus.animaCharCreator.writeDataTo
import java.io.ByteArrayOutputStream

/**
 * Provides a technique for the character to use.
 * All properties have already been provided for the user.
 *
 * @param saveName name to be written to file on saving
 * @param name reference to the displayed name of the technique
 * @param description reference to the description of the technique
 * @param level strength value of the technique
 * @param maintArray maintenance values for this technique
 * @param givenAbilities effects this technique possesses
 */
class PrebuiltTech(
    val saveName: String,
    val name: Int,
    val description: Int,
    level: Int,
    maintArray: MutableList<Int>,
    givenAbilities: MutableList<TechniqueEffect>
): TechniqueBase(
    level,
    maintArray,
    givenAbilities
){
    /**
     * Writes the data for this technique to file.
     *
     * @param byteArray output stream for the data
     */
    override fun write(byteArray: ByteArrayOutputStream){
        writeDataTo(writer = byteArray, input = saveName)
    }
}