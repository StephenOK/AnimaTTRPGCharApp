package com.example.animabuilder.character_creation.equipment.general_goods

import com.example.animabuilder.character_creation.equipment.CoinType
import java.io.Serializable

class GeneralEquipment(
    val name: String,
    val baseCost: Double,
    val coinType: CoinType,
    val weight: Double?,
    val availability: Availability
): Serializable