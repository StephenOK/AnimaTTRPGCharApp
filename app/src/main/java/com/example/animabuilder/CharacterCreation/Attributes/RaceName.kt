package com.example.animabuilder.CharacterCreation.Attributes

import com.example.animabuilder.CharacterCreation.Attributes.RaceName
import java.io.Serializable

enum class RaceName : Serializable {
    human, sylvain, jayan, danjayni, ebudan, daimah, dukzarist;

    companion object {
        @JvmStatic
        fun fromString(input: String?): RaceName {
            return when (input) {
                "sylvain", "Sylvain" -> sylvain
                "jayan", "Jayan" -> jayan
                "danjayni", "D\'Anjayni" -> danjayni
                "ebudan", "Ebudan" -> ebudan
                "daimah", "Daimah" -> daimah
                "dukzarist", "Duk\'zarist" -> dukzarist
                "human", "Human" -> human
                else -> human
            }
        }
    }
}