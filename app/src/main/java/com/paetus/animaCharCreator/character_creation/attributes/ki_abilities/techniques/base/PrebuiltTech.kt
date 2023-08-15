package com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.base

import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.effect.TechniqueEffect
import com.paetus.animaCharCreator.writeDataTo
import java.io.ByteArrayOutputStream

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
    override fun write(byteArray: ByteArrayOutputStream){
        writeDataTo(byteArray, saveName)
    }
}