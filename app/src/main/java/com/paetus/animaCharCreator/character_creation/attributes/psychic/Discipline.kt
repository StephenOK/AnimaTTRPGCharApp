package com.paetus.animaCharCreator.character_creation.attributes.psychic

/**
 * Object that holds the list of Psychic Powers in a single discipline
 */
open class Discipline(val saveName: String){
    val allPowers = mutableListOf<PsychicPower>()
}