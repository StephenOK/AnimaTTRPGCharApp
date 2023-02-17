package com.example.animabuilder.character_creation.attributes.modules

import java.io.Serializable

class MartialArt (
    var name: String,
    var description: String,
    var prereqList: String,
    var mkBonus: Int
): Serializable