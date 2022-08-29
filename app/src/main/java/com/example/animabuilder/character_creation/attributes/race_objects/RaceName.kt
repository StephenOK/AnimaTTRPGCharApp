package com.example.animabuilder.character_creation.attributes.race_objects

import java.io.Serializable

/**
 * Names of races given in the system
 */

enum class RaceName : Serializable {
    human, sylvain, jayan, danjayni, ebudan, daimah, dukzarist;

    companion object {
        /**
         * Converts a string to its corresponding race
         */
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