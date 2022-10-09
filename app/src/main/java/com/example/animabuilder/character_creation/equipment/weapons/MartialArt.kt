package com.example.animabuilder.character_creation.equipment.weapons

import java.io.Serializable

class MartialArt (
    var name: String,
    var description: String,
    var prereqList: String,
    var mkBonus: Int
): Serializable