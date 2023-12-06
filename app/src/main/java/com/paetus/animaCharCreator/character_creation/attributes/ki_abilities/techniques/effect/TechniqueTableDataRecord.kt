package com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.effect

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.writeDataTo
import java.io.ByteArrayOutputStream

/**
 * Record of data for technique effects.
 * Utilized in custom technique dialog for selecting effects.
 */

class TechniqueTableDataRecord {
    val table1 = listOf(
        TechniqueTableData(
            name = 1,
            effectRef = R.string.addNumber,
            effectVal = 10,
            primaryCost = 2,
            secondaryCost = 4,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 1,
            effectRef = R.string.addNumber,
            effectVal = 25,
            primaryCost = 3,
            secondaryCost = 5,
            mkCost = 5,
            maintCost = 2,
            level = 1
        ),

        TechniqueTableData(
            name = 1,
            effectRef = R.string.addNumber,
            effectVal = 40,
            primaryCost = 4,
            secondaryCost = 6,
            mkCost = 10,
            maintCost = 3,
            level = 1
        ),

        TechniqueTableData(
            name = 1,
            effectRef = R.string.addNumber,
            effectVal = 50,
            primaryCost = 5,
            secondaryCost = 8,
            mkCost = 15,
            maintCost = 4,
            level = 1
        ),

        TechniqueTableData(
            name = 1,
            effectRef = R.string.addNumber,
            effectVal = 75,
            primaryCost = 8,
            secondaryCost = 11,
            mkCost = 20,
            maintCost = 6,
            level = 1
        ),

        TechniqueTableData(
            name = 1,
            effectRef = R.string.addNumber,
            effectVal = 90,
            primaryCost = 12,
            secondaryCost = 15,
            mkCost = 25,
            maintCost = 8,
            level = 1
        ),

        TechniqueTableData(
            name = 1,
            effectRef = R.string.addNumber,
            effectVal = 100,
            primaryCost = 14,
            secondaryCost = 18,
            mkCost = 30,
            maintCost = 10,
            level = 1
        ),

        TechniqueTableData(
            name = 1,
            effectRef = R.string.addNumber,
            effectVal = 125,
            primaryCost = 18,
            secondaryCost = 22,
            mkCost = 35,
            maintCost = 12,
            level = 2
        ),

        TechniqueTableData(
            name = 1,
            effectRef = R.string.addNumber,
            effectVal = 150,
            primaryCost = 22,
            secondaryCost = 26,
            mkCost = 40,
            maintCost = 14,
            level = 2
        ),

        TechniqueTableData(
            name = 1,
            effectRef = R.string.addNumber,
            effectVal = 175,
            primaryCost = 26,
            secondaryCost = 32,
            mkCost = 45,
            maintCost = 16,
            level = 3
        ),

        TechniqueTableData(
            name = 1,
            effectRef = R.string.addNumber,
            effectVal = 200,
            primaryCost = 30,
            secondaryCost = 36,
            mkCost = 50,
            maintCost = 18,
            level = 3
        )
    )

    val table2 = listOf(
        TechniqueTableData(
            name = 2,
            effectRef = R.string.addNumber,
            effectVal = 10,
            primaryCost = 1,
            secondaryCost = 2,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 2,
            effectRef = R.string.addNumber,
            effectVal = 25,
            primaryCost = 2,
            secondaryCost = 4,
            mkCost = 5,
            maintCost = 2,
            level = 1
        ),

        TechniqueTableData(
            name = 2,
            effectRef = R.string.addNumber,
            effectVal = 40,
            primaryCost = 3,
            secondaryCost = 5,
            mkCost = 10,
            maintCost = 3,
            level = 1
        ),

        TechniqueTableData(
            name = 2,
            effectRef = R.string.addNumber,
            effectVal = 50,
            primaryCost = 4,
            secondaryCost = 6,
            mkCost = 10,
            maintCost = 4,
            level = 1
        ),

        TechniqueTableData(
            name = 2,
            effectRef = R.string.addNumber,
            effectVal = 75,
            primaryCost = 6,
            secondaryCost = 9,
            mkCost = 15,
            maintCost = 6,
            level = 1
        ),

        TechniqueTableData(
            name = 2,
            effectRef = R.string.addNumber,
            effectVal = 90,
            primaryCost = 9,
            secondaryCost = 12,
            mkCost = 20,
            maintCost = 8,
            level = 1
        ),

        TechniqueTableData(
            name = 2,
            effectRef = R.string.addNumber,
            effectVal = 100,
            primaryCost = 12,
            secondaryCost = 15,
            mkCost = 25,
            maintCost = 10,
            level = 1
        ),

        TechniqueTableData(
            name = 2,
            effectRef = R.string.addNumber,
            effectVal = 125,
            primaryCost = 14,
            secondaryCost = 18,
            mkCost = 30,
            maintCost = 12,
            level = 2
        ),

        TechniqueTableData(
            name = 2,
            effectRef = R.string.addNumber,
            effectVal = 150,
            primaryCost = 18,
            secondaryCost = 22,
            mkCost = 35,
            maintCost = 14,
            level = 2
        ),

        TechniqueTableData(
            name = 2,
            effectRef = R.string.addNumber,
            effectVal = 175,
            primaryCost = 22,
            secondaryCost = 26,
            mkCost = 40,
            maintCost = 16,
            level = 3
        ),

        TechniqueTableData(
            name = 2,
            effectRef = R.string.addNumber,
            effectVal = 200,
            primaryCost = 26,
            secondaryCost = 32,
            mkCost = 45,
            maintCost = 18,
            level = 3
        )
    )

    val table3 = listOf(
        TechniqueTableData(
            name = 3,
            effectRef = R.string.addNumber,
            effectVal = 10,
            primaryCost = 2,
            secondaryCost = 4,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 3,
            effectRef = R.string.addNumber,
            effectVal = 25,
            primaryCost = 3,
            secondaryCost = 5,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 3,
            effectRef = R.string.addNumber,
            effectVal = 40,
            primaryCost = 4,
            secondaryCost = 6,
            mkCost = 10,
            maintCost = 2,
            level = 1
        ),

        TechniqueTableData(
            name = 3,
            effectRef = R.string.addNumber,
            effectVal = 50,
            primaryCost = 5,
            secondaryCost = 8,
            mkCost = 15,
            maintCost = 3,
            level = 1
        ),

        TechniqueTableData(
            name = 3,
            effectRef = R.string.addNumber,
            effectVal = 75,
            primaryCost = 8,
            secondaryCost = 11,
            mkCost = 20,
            maintCost = 4,
            level = 1
        ),

        TechniqueTableData(
            name = 3,
            effectRef = R.string.addNumber,
            effectVal = 90,
            primaryCost = 12,
            secondaryCost = 15,
            mkCost = 25,
            maintCost = 5,
            level = 1
        ),

        TechniqueTableData(
            name = 3,
            effectRef = R.string.addNumber,
            effectVal = 100,
            primaryCost = 14,
            secondaryCost = 18,
            mkCost = 30,
            maintCost = 8,
            level = 1
        ),

        TechniqueTableData(
            name = 3,
            effectRef = R.string.addNumber,
            effectVal = 125,
            primaryCost = 18,
            secondaryCost = 22,
            mkCost = 35,
            maintCost = 10,
            level = 2
        ),

        TechniqueTableData(
            name = 3,
            effectRef = R.string.addNumber,
            effectVal = 150,
            primaryCost = 22,
            secondaryCost = 26,
            mkCost = 40,
            maintCost = 12,
            level = 2
        ),

        TechniqueTableData(
            name = 3,
            effectRef = R.string.addNumber,
            effectVal = 175,
            primaryCost = 26,
            secondaryCost = 32,
            mkCost = 45,
            maintCost = 14,
            level = 3
        ),
        
        TechniqueTableData(
            name = 3,
            effectRef = R.string.addNumber,
            effectVal = 200,
            primaryCost = 30,
            secondaryCost = 36,
            mkCost = 50,
            maintCost = 16,
            level = 3
        )
    )

    val table4 = listOf(
        TechniqueTableData(
            name = 4,
            effectRef = R.string.addNumber,
            effectVal = 10,
            primaryCost = 1,
            secondaryCost = 2,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 4,
            effectRef = R.string.addNumber,
            effectVal = 25,
            primaryCost = 2,
            secondaryCost = 4,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 4,
            effectRef = R.string.addNumber,
            effectVal = 40,
            primaryCost = 3,
            secondaryCost = 5,
            mkCost = 10,
            maintCost = 1,
            level = 1
        ),
        TechniqueTableData(
            name = 4,
            effectRef = R.string.addNumber,
            effectVal = 50,
            primaryCost = 4,
            secondaryCost = 6,
            mkCost = 10,
            maintCost = 2,
            level = 1
        ),

        TechniqueTableData(
            name = 4,
            effectRef = R.string.addNumber,
            effectVal = 75,
            primaryCost = 6,
            secondaryCost = 9,
            mkCost = 15,
            maintCost = 3,
            level = 1
        ),

        TechniqueTableData(
            name = 4,
            effectRef = R.string.addNumber,
            effectVal = 90,
            primaryCost = 8,
            secondaryCost = 11,
            mkCost = 20,
            maintCost = 4,
            level = 1
        ),

        TechniqueTableData(
            name = 4,
            effectRef = R.string.addNumber,
            effectVal = 100,
            primaryCost = 10,
            secondaryCost = 13,
            mkCost = 25,
            maintCost = 6,
            level = 1
        ),

        TechniqueTableData(
            name = 4,
            effectRef = R.string.addNumber,
            effectVal = 125,
            primaryCost = 12,
            secondaryCost = 15,
            mkCost = 30,
            maintCost = 8,
            level = 2
        ),

        TechniqueTableData(
            name = 4,
            effectRef = R.string.addNumber,
            effectVal = 150,
            primaryCost = 16,
            secondaryCost = 20,
            mkCost = 35,
            maintCost = 10,
            level = 2
        ),

        TechniqueTableData(
            name = 4,
            effectRef = R.string.addNumber,
            effectVal = 175,
            primaryCost = 20,
            secondaryCost = 24,
            mkCost = 40,
            maintCost = 12,
            level = 3
        ),

        TechniqueTableData(
            name = 4,
            effectRef = R.string.addNumber,
            effectVal = 200,
            primaryCost = 24,
            secondaryCost = 29,
            mkCost = 45,
            maintCost = 14,
            level = 3
        )
    )

    val table5 = listOf(
        TechniqueTableData(
            name = 5,
            effectRef = R.string.addNumber,
            effectVal = 10,
            primaryCost = 2,
            secondaryCost = 4,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 5,
            effectRef = R.string.addNumber,
            effectVal = 25,
            primaryCost = 3,
            secondaryCost = 5,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 5,
            effectRef = R.string.addNumber,
            effectVal = 40,
            primaryCost = 4,
            secondaryCost = 6,
            mkCost = 10,
            maintCost = 2,
            level = 1
        ),

        TechniqueTableData(
            name = 5,
            effectRef = R.string.addNumber,
            effectVal = 50,
            primaryCost = 5,
            secondaryCost = 8,
            mkCost = 15,
            maintCost = 3,
            level = 1
        ),

        TechniqueTableData(
            name = 5,
            effectRef = R.string.addNumber,
            effectVal = 75,
            primaryCost = 8,
            secondaryCost = 11,
            mkCost = 20,
            maintCost = 4,
            level = 1
        ),

        TechniqueTableData(
            name = 5,
            effectRef = R.string.addNumber,
            effectVal = 90,
            primaryCost = 12,
            secondaryCost = 15,
            mkCost = 25,
            maintCost = 5,
            level = 1
        ),

        TechniqueTableData(
            name = 5,
            effectRef = R.string.addNumber,
            effectVal = 100,
            primaryCost = 14,
            secondaryCost = 18,
            mkCost = 30,
            maintCost = 8,
            level = 1
        ),

        TechniqueTableData(
            name = 5,
            effectRef = R.string.addNumber,
            effectVal = 125,
            primaryCost = 18,
            secondaryCost = 22,
            mkCost = 35,
            maintCost = 10,
            level = 2
        ),

        TechniqueTableData(
            name = 5,
            effectRef = R.string.addNumber,
            effectVal = 150,
            primaryCost = 22,
            secondaryCost = 26,
            mkCost = 40,
            maintCost = 12,
            level = 2
        ),

        TechniqueTableData(
            name = 5,
            effectRef = R.string.addNumber,
            effectVal = 175,
            primaryCost = 26,
            secondaryCost = 32,
            mkCost = 45,
            maintCost = 14,
            level = 3
        ),

        TechniqueTableData(
            name = 5,
            effectRef = R.string.addNumber,
            effectVal = 200,
            primaryCost = 30,
            secondaryCost = 36,
            mkCost = 50,
            maintCost = 16,
            level = 3
        )
    )

    val table6 = listOf(
        TechniqueTableData(
            name = 6,
            effectRef = R.string.addNumber,
            effectVal = 10,
            primaryCost = 1,
            secondaryCost = 2,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 6,
            effectRef = R.string.addNumber,
            effectVal = 25,
            primaryCost = 2,
            secondaryCost = 4,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 6,
            effectRef = R.string.addNumber,
            effectVal = 40,
            primaryCost = 3,
            secondaryCost = 5,
            mkCost = 10,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 6,
            effectRef = R.string.addNumber,
            effectVal = 50,
            primaryCost = 4,
            secondaryCost = 6,
            mkCost = 10,
            maintCost = 2,
            level = 1
        ),

        TechniqueTableData(
            name = 6,
            effectRef = R.string.addNumber,
            effectVal = 75,
            primaryCost = 6,
            secondaryCost = 9,
            mkCost = 15,
            maintCost = 3,
            level = 1
        ),

        TechniqueTableData(
            name = 6,
            effectRef = R.string.addNumber,
            effectVal = 90,
            primaryCost = 8,
            secondaryCost = 11,
            mkCost = 20,
            maintCost = 4,
            level = 1
        ),

        TechniqueTableData(
            name = 6,
            effectRef = R.string.addNumber,
            effectVal = 100,
            primaryCost = 10,
            secondaryCost = 13,
            mkCost = 25,
            maintCost = 6,
            level = 1
        ),

        TechniqueTableData(
            name = 6,
            effectRef = R.string.addNumber,
            effectVal = 125,
            primaryCost = 12,
            secondaryCost = 15,
            mkCost = 30,
            maintCost = 8,
            level = 2
        ),

        TechniqueTableData(
            name = 6,
            effectRef = R.string.addNumber,
            effectVal = 150,
            primaryCost = 16,
            secondaryCost = 20,
            mkCost = 35,
            maintCost = 10,
            level = 2
        ),

        TechniqueTableData(
            name = 6,
            effectRef = R.string.addNumber,
            effectVal = 175,
            primaryCost = 20,
            secondaryCost = 24,
            mkCost = 40,
            maintCost = 12,
            level = 3
        ),

        TechniqueTableData(
            name = 6,
            effectRef = R.string.addNumber,
            effectVal = 200,
            primaryCost = 24,
            secondaryCost = 29,
            mkCost = 45,
            maintCost = 14,
            level = 3
        )
    )

    val table7 = listOf(
        TechniqueTableData(
            name = 7,
            effectRef = R.string.multNumber,
            effectVal = 2,
            primaryCost = 10,
            secondaryCost = 15,
            mkCost = 25,
            maintCost = 4,
            level = 1
        ),

        TechniqueTableData(
            name = 7,
            effectRef = R.string.multNumber,
            effectVal = 3,
            primaryCost = 15,
            secondaryCost = 20,
            mkCost = 40,
            maintCost = 8,
            level = 2
        ),

        TechniqueTableData(
            name = 7,
            effectRef = R.string.multNumber,
            effectVal = 4,
            primaryCost = 20,
            secondaryCost = 30,
            mkCost = 80,
            maintCost = 12,
            level = 3
        )
    )

    val table8 = listOf(
        TechniqueTableData(
            name = 8,
            effectRef = R.string.addNumber,
            effectVal = 10,
            primaryCost = 1,
            secondaryCost = 2,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 8,
            effectRef = R.string.addNumber,
            effectVal = 25,
            primaryCost = 2,
            secondaryCost = 4,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 8,
            effectRef = R.string.addNumber,
            effectVal = 40,
            primaryCost = 3,
            secondaryCost = 5,
            mkCost = 10,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 8,
            effectRef = R.string.addNumber,
            effectVal = 50,
            primaryCost = 4,
            secondaryCost = 6,
            mkCost = 15,
            maintCost = 2,
            level = 1
        ),

        TechniqueTableData(
            name = 8,
            effectRef = R.string.addNumber,
            effectVal = 75,
            primaryCost = 6,
            secondaryCost = 9,
            mkCost = 20,
            maintCost = 3,
            level = 1
        ),

        TechniqueTableData(
            name = 8,
            effectRef = R.string.addNumber,
            effectVal = 90,
            primaryCost = 8,
            secondaryCost = 11,
            mkCost = 25,
            maintCost = 4,
            level = 1
        ),

        TechniqueTableData(
            name = 8,
            effectRef = R.string.addNumber,
            effectVal = 100,
            primaryCost = 10,
            secondaryCost = 13,
            mkCost = 30,
            maintCost = 5,
            level = 1
        ),

        TechniqueTableData(
            name = 8,
            effectRef = R.string.addNumber,
            effectVal = 125,
            primaryCost = 14,
            secondaryCost = 18,
            mkCost = 35,
            maintCost = 6,
            level = 2
        ),

        TechniqueTableData(
            name = 8,
            effectRef = R.string.addNumber,
            effectVal = 150,
            primaryCost = 16,
            secondaryCost = 20,
            mkCost = 40,
            maintCost = 8,
            level = 2
        ),

        TechniqueTableData(
            name = 8,
            effectRef = R.string.addNumber,
            effectVal = 175,
            primaryCost = 18,
            secondaryCost = 22,
            mkCost = 45,
            maintCost = 10,
            level = 3
        ),

        TechniqueTableData(
            name = 8,
            effectRef = R.string.addNumber,
            effectVal = 200,
            primaryCost = 20,
            secondaryCost = 24,
            mkCost = 50,
            maintCost = 12,
            level = 3
        )
    )

    val table8a = listOf(
        TechniqueTableData(
            name = 8,
            effectRef = R.string.vitalSacData,
            effectVal = null,
            primaryCost = 4,
            secondaryCost = 4,
            mkCost = 15,
            maintCost = 3,
            level = 1
        ),

        TechniqueTableData(
            name = 8,
            effectRef = R.string.doubleVitalSac,
            effectVal = null,
            primaryCost = 10,
            secondaryCost = 10,
            mkCost = 50,
            maintCost = 4,
            level = 1
        ),

        TechniqueTableData(
            name = 8,
            effectRef = R.string.healthSac,
            effectVal = null,
            primaryCost = 2,
            secondaryCost = 2,
            mkCost = 10,
            maintCost = 2,
            level = 1
        ),

        TechniqueTableData(
            name = 8,
            effectRef = R.string.charSac,
            effectVal = null,
            primaryCost = 2,
            secondaryCost = 2,
            mkCost = 10,
            maintCost = 2,
            level = 1
        )
    )

    val table9 = listOf(
        TechniqueTableData(
            name = 9,
            effectRef = R.string.addNumber,
            effectVal = 1,
            primaryCost = 6,
            secondaryCost = 9,
            mkCost = 20,
            maintCost = 3,
            level = 1
        ),

        TechniqueTableData(
            name = 9,
            effectRef = R.string.addNumber,
            effectVal = 2,
            primaryCost = 12,
            secondaryCost = 15,
            mkCost = 30,
            maintCost = 6,
            level = 1
        ),

        TechniqueTableData(
            name = 9,
            effectRef = R.string.addNumber,
            effectVal = 3,
            primaryCost = 18,
            secondaryCost = 22,
            mkCost = 40,
            maintCost = 9,
            level = 1
        ),

        TechniqueTableData(
            name = 9,
            effectRef = R.string.addNumber,
            effectVal = 4,
            primaryCost = 24,
            secondaryCost = 29,
            mkCost = 50,
            maintCost = 12,
            level = 2
        ),

        TechniqueTableData(
            name = 9,
            effectRef = R.string.addNumber,
            effectVal = 5,
            primaryCost = 30,
            secondaryCost = 36,
            mkCost = 60,
            maintCost = 15,
            level = 3
        )
    )

    val table9a = listOf(
        TechniqueTableData(
            name = 9,
            effectRef = R.string.continuousAttack,
            effectVal = null,
            primaryCost = 10,
            secondaryCost = 10,
            mkCost = 30,
            maintCost = 5,
            level = 1
        )
    )

    val table9b = listOf(
        TechniqueTableData(
            name = 9,
            effectRef = R.string.addFatigueBonus,
            effectVal = null,
            primaryCost = 8,
            secondaryCost = 8,
            mkCost = 30,
            maintCost = 2,
            level = 1
        )
    )

    val table10 = listOf(
        TechniqueTableData(
            name = 10,
            effectRef = R.string.addNumber,
            effectVal = 1,
            primaryCost = 3,
            secondaryCost = 5,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 10,
            effectRef = R.string.addNumber,
            effectVal = 2,
            primaryCost = 6,
            secondaryCost = 9,
            mkCost = 10,
            maintCost = 2,
            level = 1
        ),

        TechniqueTableData(
            name = 10,
            effectRef = R.string.addNumber,
            effectVal = 3,
            primaryCost = 9,
            secondaryCost = 12,
            mkCost = 15,
            maintCost = 3,
            level = 1
        ),

        TechniqueTableData(
            name = 10,
            effectRef = R.string.addNumber,
            effectVal = 4,
            primaryCost = 12,
            secondaryCost = 15,
            mkCost = 20,
            maintCost = 4,
            level = 1
        ),

        TechniqueTableData(
            name = 10,
            effectRef = R.string.addNumber,
            effectVal = 5,
            primaryCost = 15,
            secondaryCost = 19,
            mkCost = 30,
            maintCost = 6,
            level = 1
        ),

        TechniqueTableData(
            name = 10,
            effectRef = R.string.addNumber,
            effectVal = 6,
            primaryCost = 18,
            secondaryCost = 22,
            mkCost = 40,
            maintCost = 8,
            level = 2
        ),

        TechniqueTableData(
            name = 10,
            effectRef = R.string.addNumber,
            effectVal = 8,
            primaryCost = 22,
            secondaryCost = 26,
            mkCost = 50,
            maintCost = 10,
            level = 2
        ),

        TechniqueTableData(
            name = 10,
            effectRef = R.string.addNumber,
            effectVal = 10,
            primaryCost = 26,
            secondaryCost = 32,
            mkCost = 60,
            maintCost = 12,
            level = 3
        )
    )

    val table10a = listOf(
        TechniqueTableData(
            name = 10,
            effectRef = R.string.continuousAttack,
            effectVal = null,
            primaryCost = 10,
            secondaryCost = 10,
            mkCost = 30,
            maintCost = 5,
            level = 1
        )
    )

    val table11 = listOf(
        TechniqueTableData(
            name = 11,
            effectRef = R.string.addNumber,
            effectVal = 1,
            primaryCost = 1,
            secondaryCost = 2,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 11,
            effectRef = R.string.addNumber,
            effectVal = 2,
            primaryCost = 2,
            secondaryCost = 4,
            mkCost = 5,
            maintCost = 2,
            level = 1
        ),

        TechniqueTableData(
            name = 11,
            effectRef = R.string.addNumber,
            effectVal = 3,
            primaryCost = 3,
            secondaryCost = 5,
            mkCost = 10,
            maintCost = 3,
            level = 1
        ),

        TechniqueTableData(
            name = 11,
            effectRef = R.string.addNumber,
            effectVal = 4,
            primaryCost = 4,
            secondaryCost = 6,
            mkCost = 15,
            maintCost = 4,
            level = 1
        ),

        TechniqueTableData(
            name = 11,
            effectRef = R.string.addNumber,
            effectVal = 6,
            primaryCost = 5,
            secondaryCost = 8,
            mkCost = 20,
            maintCost = 6,
            level = 1
        ),

        TechniqueTableData(
            name = 11,
            effectRef = R.string.addNumber,
            effectVal = 8,
            primaryCost = 6,
            secondaryCost = 9,
            mkCost = 25,
            maintCost = 8,
            level = 1
        ),

        TechniqueTableData(
            name = 11,
            effectRef = R.string.addNumber,
            effectVal = 10,
            primaryCost = 7,
            secondaryCost = 10,
            mkCost = 30,
            maintCost = 10,
            level = 2
        ),

        TechniqueTableData(
            name = 11,
            effectRef = R.string.unlimited,
            effectVal = null,
            primaryCost = 8,
            secondaryCost = 11,
            mkCost = 35,
            maintCost = 12,
            level = 3
        )
    )

    val table11a = listOf(
        TechniqueTableData(
            name = 11,
            effectRef = R.string.addFatigueBonus,
            effectVal = null,
            primaryCost = 6,
            secondaryCost = 6,
            mkCost = 20,
            maintCost = 2,
            level = 1
        )
    )

    val table12 = listOf(
        TechniqueTableData(
            name = 12,
            effectRef = R.string.addNumber,
            effectVal = 1,
            primaryCost = 1,
            secondaryCost = 2,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 12,
            effectRef = R.string.addNumber,
            effectVal = 2,
            primaryCost = 2,
            secondaryCost = 4,
            mkCost = 5,
            maintCost = 2,
            level = 1
        ),

        TechniqueTableData(
            name = 12,
            effectRef = R.string.addNumber,
            effectVal = 3,
            primaryCost = 3,
            secondaryCost = 5,
            mkCost = 10,
            maintCost = 3,
            level = 1
        ),

        TechniqueTableData(
            name = 12,
            effectRef = R.string.addNumber,
            effectVal = 4,
            primaryCost = 4,
            secondaryCost = 6,
            mkCost = 15,
            maintCost = 4,
            level = 1
        ),

        TechniqueTableData(
            name = 12,
            effectRef = R.string.addNumber,
            effectVal = 5,
            primaryCost = 5,
            secondaryCost = 8,
            mkCost = 20,
            maintCost = 6,
            level = 1
        ),

        TechniqueTableData(
            name = 12,
            effectRef = R.string.addNumber,
            effectVal = 6,
            primaryCost = 6,
            secondaryCost = 9,
            mkCost = 25,
            maintCost = 8,
            level = 1
        ),

        TechniqueTableData(
            name = 12,
            effectRef = R.string.addNumber,
            effectVal = 8,
            primaryCost = 7,
            secondaryCost = 10,
            mkCost = 30,
            maintCost = 10,
            level = 2
        ),

        TechniqueTableData(
            name = 12,
            effectRef = R.string.addNumber,
            effectVal = 10,
            primaryCost = 8,
            secondaryCost = 11,
            mkCost = 35,
            maintCost = 12,
            level = 3
        )
    )

    val table12a = listOf(
        TechniqueTableData(
            name = 12,
            effectRef = R.string.addFatigueBonus,
            effectVal = null,
            primaryCost = 6,
            secondaryCost = 6,
            mkCost = 20,
            maintCost = 1,
            level = 1
        )
    )

    val table13 = listOf(
        TechniqueTableData(
            name = 13,
            effectRef = R.string.addNumber,
            effectVal = 25,
            primaryCost = 1,
            secondaryCost = 2,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 13,
            effectRef = R.string.addNumber,
            effectVal = 50,
            primaryCost = 2,
            secondaryCost = 4,
            mkCost = 10,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 13,
            effectRef = R.string.addNumber,
            effectVal = 75,
            primaryCost = 4,
            secondaryCost = 6,
            mkCost = 15,
            maintCost = 2,
            level = 1
        ),

        TechniqueTableData(
            name = 13,
            effectRef = R.string.addNumber,
            effectVal = 100,
            primaryCost = 6,
            secondaryCost = 9,
            mkCost = 20,
            maintCost = 3,
            level = 1
        ),

        TechniqueTableData(
            name = 13,
            effectRef = R.string.addNumber,
            effectVal = 125,
            primaryCost = 8,
            secondaryCost = 11,
            mkCost = 25,
            maintCost = 4,
            level = 2
        ),

        TechniqueTableData(
            name = 13,
            effectRef = R.string.addNumber,
            effectVal = 150,
            primaryCost = 10,
            secondaryCost = 13,
            mkCost = 30,
            maintCost = 5,
            level = 2
        ),

        TechniqueTableData(
            name = 13,
            effectRef = R.string.addNumber,
            effectVal = 175,
            primaryCost = 12,
            secondaryCost = 15,
            mkCost = 35,
            maintCost = 6,
            level = 3
        ),

        TechniqueTableData(
            name = 13,
            effectRef = R.string.addNumber,
            effectVal = 200,
            primaryCost = 14,
            secondaryCost = 18,
            mkCost = 40,
            maintCost = 7,
            level = 3
        )
    )

    val table14 = listOf(
        TechniqueTableData(
            name = 14,
            effectRef = R.string.justNum,
            effectVal = 40,
            primaryCost = 1,
            secondaryCost = 2,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 14,
            effectRef = R.string.justNum,
            effectVal = 60,
            primaryCost = 2,
            secondaryCost = 4,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 14,
            effectRef = R.string.justNum,
            effectVal = 80,
            primaryCost = 3,
            secondaryCost = 5,
            mkCost = 10,
            maintCost = 2,
            level = 1
        ),

        TechniqueTableData(
            name = 14,
            effectRef = R.string.justNum,
            effectVal = 100,
            primaryCost = 5,
            secondaryCost = 8,
            mkCost = 15,
            maintCost = 3,
            level = 1
        ),

        TechniqueTableData(
            name = 14,
            effectRef = R.string.justNum,
            effectVal = 120,
            primaryCost = 6,
            secondaryCost = 9,
            mkCost = 20,
            maintCost = 4,
            level = 1
        ),

        TechniqueTableData(
            name = 14,
            effectRef = R.string.justNum,
            effectVal = 140,
            primaryCost = 8,
            secondaryCost = 11,
            mkCost = 20,
            maintCost = 5,
            level = 2
        ),

        TechniqueTableData(
            name = 14,
            effectRef = R.string.justNum,
            effectVal = 180,
            primaryCost = 10,
            secondaryCost = 13,
            mkCost = 30,
            maintCost = 6,
            level = 2
        ),

        TechniqueTableData(
            name = 14,
            effectRef = R.string.justNum,
            effectVal = 200,
            primaryCost = 14,
            secondaryCost = 18,
            mkCost = 50,
            maintCost = 8,
            level = 3
        ),

        TechniqueTableData(
            name = 14,
            effectRef = R.string.justNum,
            effectVal = 240,
            primaryCost = 18,
            secondaryCost = 22,
            mkCost = 80,
            maintCost = 10,
            level = 3
        )
    )

    val table14a = listOf(
        TechniqueTableData(
            name = 14,
            effectRef = R.string.physReduction,
            effectVal = null,
            primaryCost = 2,
            secondaryCost = 2,
            mkCost = 10,
            maintCost = 0,
            level = 1
        ),

        TechniqueTableData(
            name = 14,
            effectRef = R.string.blindnessData,
            effectVal = null,
            primaryCost = 5,
            secondaryCost = 5,
            mkCost = 15,
            maintCost = 0,
            level = 1
        ),

        TechniqueTableData(
            name = 14,
            effectRef = R.string.charReduction,
            effectVal = null,
            primaryCost = 2,
            secondaryCost = 2,
            mkCost = 10,
            maintCost = 0,
            level = 1
        ),

        TechniqueTableData(
            name = 14,
            effectRef = R.string.partParalyze,
            effectVal = null,
            primaryCost = 6,
            secondaryCost = 6,
            mkCost = 10,
            maintCost = 0,
            level = 1
        ),

        TechniqueTableData(
            name = 14,
            effectRef = R.string.damage,
            effectVal = null,
            primaryCost = 5,
            secondaryCost = 5,
            mkCost = 10,
            maintCost = 0,
            level = 1
        ),

        TechniqueTableData(
            name = 14,
            effectRef = R.string.unconscious,
            effectVal = null,
            primaryCost = 8,
            secondaryCost = 8,
            mkCost = 15,
            maintCost = 0,
            level = 1
        ),

        TechniqueTableData(
            name = 14,
            effectRef = R.string.coma,
            effectVal = null,
            primaryCost = 10,
            secondaryCost = 10,
            mkCost = 30,
            maintCost = 0,
            level = 2
        ),

        TechniqueTableData(
            name = 14,
            effectRef = R.string.totalParalyze,
            effectVal = null,
            primaryCost = 8,
            secondaryCost = 8,
            mkCost = 20,
            maintCost = 0,
            level = 2
        ),

        TechniqueTableData(
            name = 14,
            effectRef = R.string.lifeDrain,
            effectVal = null,
            primaryCost = 8,
            secondaryCost = 8,
            mkCost = 15,
            maintCost = 0,
            level = 2
        ),

        TechniqueTableData(
            name = 14,
            effectRef = R.string.controlData,
            effectVal = null,
            primaryCost = 10,
            secondaryCost = 10,
            mkCost = 40,
            maintCost = 0,
            level = 3
        ),

        TechniqueTableData(
            name = 14,
            effectRef = R.string.death,
            effectVal = null,
            primaryCost = 12,
            secondaryCost = 12,
            mkCost = 50,
            maintCost = 0,
            level = 3
        )
    )

    val table15 = listOf(
        TechniqueTableData(
            name = 15,
            effectRef = R.string.subtractNumber,
            effectVal = 10,
            primaryCost = 1,
            secondaryCost = 2,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 15,
            effectRef = R.string.subtractNumber,
            effectVal = 25,
            primaryCost = 2,
            secondaryCost = 4,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 15,
            effectRef = R.string.subtractNumber,
            effectVal = 50,
            primaryCost = 3,
            secondaryCost = 5,
            mkCost = 10,
            maintCost = 2,
            level = 1
        ),

        TechniqueTableData(
            name = 15,
            effectRef = R.string.subtractNumber,
            effectVal = 75,
            primaryCost = 4,
            secondaryCost = 6,
            mkCost = 10,
            maintCost = 2,
            level = 2
        ),

        TechniqueTableData(
            name = 15,
            effectRef = R.string.subtractNumber,
            effectVal = 100,
            primaryCost = 6,
            secondaryCost = 9,
            mkCost = 15,
            maintCost = 3,
            level = 2
        ),

        TechniqueTableData(
            name = 15,
            effectRef = R.string.subtractNumber,
            effectVal = 120,
            primaryCost = 8,
            secondaryCost = 11,
            mkCost = 20,
            maintCost = 3,
            level = 3
        )
    )

    val table16 = listOf(
        TechniqueTableData(
            name = 16,
            effectRef = R.string.justNum,
            effectVal = 1,
            primaryCost = 1,
            secondaryCost = 2,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 16,
            effectRef = R.string.justNum,
            effectVal = 2,
            primaryCost = 2,
            secondaryCost = 4,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 16,
            effectRef = R.string.justNum,
            effectVal = 3,
            primaryCost = 4,
            secondaryCost = 6,
            mkCost = 10,
            maintCost = 2,
            level = 1
        ),

        TechniqueTableData(
            name = 16,
            effectRef = R.string.justNum,
            effectVal = 4,
            primaryCost = 6,
            secondaryCost = 9,
            mkCost = 15,
            maintCost = 2,
            level = 1
        ),

        TechniqueTableData(
            name = 16,
            effectRef = R.string.justNum,
            effectVal = 5,
            primaryCost = 8,
            secondaryCost = 11,
            mkCost = 20,
            maintCost = 3,
            level = 2
        ),

        TechniqueTableData(
            name = 16,
            effectRef = R.string.justNum,
            effectVal = 6,
            primaryCost = 10,
            secondaryCost = 13,
            mkCost = 25,
            maintCost = 3,
            level = 2
        ),

        TechniqueTableData(
            name = 16,
            effectRef = R.string.justNum,
            effectVal = 7,
            primaryCost = 12,
            secondaryCost = 15,
            mkCost = 30,
            maintCost = 4,
            level = 2
        ),

        TechniqueTableData(
            name = 16,
            effectRef = R.string.justNum,
            effectVal = 8,
            primaryCost = 14,
            secondaryCost = 18,
            mkCost = 40,
            maintCost = 5,
            level = 3
        )
    )

    val table17 = listOf(
        TechniqueTableData(
            name = 17,
            effectRef = R.string.subtractAT,
            effectVal = 1,
            primaryCost = 1,
            secondaryCost = 2,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 17,
            effectRef = R.string.subtractAT,
            effectVal = 2,
            primaryCost = 2,
            secondaryCost = 4,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 17,
            effectRef = R.string.subtractAT,
            effectVal = 3,
            primaryCost = 3,
            secondaryCost = 5,
            mkCost = 10,
            maintCost = 2,
            level = 1
        ),

        TechniqueTableData(
            name = 17,
            effectRef = R.string.subtractAT,
            effectVal = 4,
            primaryCost = 4,
            secondaryCost = 6,
            mkCost = 10,
            maintCost = 2,
            level = 1
        ),

        TechniqueTableData(
            name = 17,
            effectRef = R.string.subtractAT,
            effectVal = 5,
            primaryCost = 5,
            secondaryCost = 8,
            mkCost = 15,
            maintCost = 3,
            level = 2
        ),

        TechniqueTableData(
            name = 17,
            effectRef = R.string.subtractAT,
            effectVal = 6,
            primaryCost = 6,
            secondaryCost = 9,
            mkCost = 20,
            maintCost = 3,
            level = 2
        ),

        TechniqueTableData(
            name = 17,
            effectRef = R.string.subtractAT,
            effectVal = 7,
            primaryCost = 8,
            secondaryCost = 11,
            mkCost = 25,
            maintCost = 4,
            level = 2
        ),

        TechniqueTableData(
            name = 17,
            effectRef = R.string.subtractAT,
            effectVal = 8,
            primaryCost = 10,
            secondaryCost = 13,
            mkCost = 30,
            maintCost = 5,
            level = 3
        )
    )

    val table18 = listOf(
        TechniqueTableData(
            name = 18,
            effectRef = R.string.addNumber,
            effectVal = 5,
            primaryCost = 1,
            secondaryCost = 2,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 18,
            effectRef = R.string.addNumber,
            effectVal = 10,
            primaryCost = 2,
            secondaryCost = 4,
            mkCost = 10,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 18,
            effectRef = R.string.addNumber,
            effectVal = 15,
            primaryCost = 4,
            secondaryCost = 6,
            mkCost = 15,
            maintCost = 2,
            level = 1
        ),

        TechniqueTableData(
            name = 18,
            effectRef = R.string.addNumber,
            effectVal = 20,
            primaryCost = 6,
            secondaryCost = 9,
            mkCost = 20,
            maintCost = 3,
            level = 1
        ),

        TechniqueTableData(
            name = 18,
            effectRef = R.string.addNumber,
            effectVal = 25,
            primaryCost = 8,
            secondaryCost = 11,
            mkCost = 25,
            maintCost = 4,
            level = 2
        ),

        TechniqueTableData(
            name = 18,
            effectRef = R.string.addNumber,
            effectVal = 30,
            primaryCost = 12,
            secondaryCost = 15,
            mkCost = 30,
            maintCost = 5,
            level = 2
        ),

        TechniqueTableData(
            name = 18,
            effectRef = R.string.addNumber,
            effectVal = 35,
            primaryCost = 13,
            secondaryCost = 18,
            mkCost = 35,
            maintCost = 6,
            level = 2
        ),

        TechniqueTableData(
            name = 18,
            effectRef = R.string.addNumber,
            effectVal = 40,
            primaryCost = 18,
            secondaryCost = 22,
            mkCost = 40,
            maintCost = 8,
            level = 3
        )
    )

    val table19 = listOf(
        TechniqueTableData(
            name = 19,
            effectRef = R.string.addNumber,
            effectVal = 10,
            primaryCost = 1,
            secondaryCost = 2,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 19,
            effectRef = R.string.addNumber,
            effectVal = 15,
            primaryCost = 2,
            secondaryCost = 4,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 19,
            effectRef = R.string.addNumber,
            effectVal = 20,
            primaryCost = 3,
            secondaryCost = 5,
            mkCost = 10,
            maintCost = 2,
            level = 1
        ),

        TechniqueTableData(
            name = 19,
            effectRef = R.string.addNumber,
            effectVal = 25,
            primaryCost = 4,
            secondaryCost = 6,
            mkCost = 10,
            maintCost = 2,
            level = 1
        ),

        TechniqueTableData(
            name = 19,
            effectRef = R.string.addNumber,
            effectVal = 30,
            primaryCost = 5,
            secondaryCost = 8,
            mkCost = 15,
            maintCost = 3,
            level = 2
        ),

        TechniqueTableData(
            name = 19,
            effectRef = R.string.addNumber,
            effectVal = 35,
            primaryCost = 6,
            secondaryCost = 9,
            mkCost = 20,
            maintCost = 3,
            level = 2
        ),

        TechniqueTableData(
            name = 19,
            effectRef = R.string.addNumber,
            effectVal = 40,
            primaryCost = 7,
            secondaryCost = 10,
            mkCost = 25,
            maintCost = 4,
            level = 3
        )
    )

    val table20 = listOf(
        TechniqueTableData(
            name = 20,
            effectRef = R.string.distanceLabelM,
            effectVal = 5,
            primaryCost = 1,
            secondaryCost = 2,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 20,
            effectRef = R.string.distanceLabelM,
            effectVal = 10,
            primaryCost = 2,
            secondaryCost = 4,
            mkCost = 10,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 20,
            effectRef = R.string.distanceLabelM,
            effectVal = 20,
            primaryCost = 3,
            secondaryCost = 5,
            mkCost = 10,
            maintCost = 2,
            level = 1
        ),

        TechniqueTableData(
            name = 20,
            effectRef = R.string.distanceLabelM,
            effectVal = 50,
            primaryCost = 4,
            secondaryCost = 6,
            mkCost = 15,
            maintCost = 3,
            level = 1
        ),

        TechniqueTableData(
            name = 20,
            effectRef = R.string.distanceLabelM,
            effectVal = 100,
            primaryCost = 5,
            secondaryCost = 8,
            mkCost = 20,
            maintCost = 4,
            level = 1
        ),

        TechniqueTableData(
            name = 20,
            effectRef = R.string.distanceLabelM,
            effectVal = 250,
            primaryCost = 6,
            secondaryCost = 9,
            mkCost = 25,
            maintCost = 5,
            level = 2
        ),

        TechniqueTableData(
            name = 20,
            effectRef = R.string.distanceLabelM,
            effectVal = 500,
            primaryCost = 8,
            secondaryCost = 11,
            mkCost = 30,
            maintCost = 6,
            level = 2
        ),

        TechniqueTableData(
            name = 20,
            effectRef = R.string.distanceLabelKM,
            effectVal = 1,
            primaryCost = 10,
            secondaryCost = 13,
            mkCost = 35,
            maintCost = 8,
            level = 2
        ),

        TechniqueTableData(
            name = 20,
            effectRef = R.string.distanceLabelKM,
            effectVal = 5,
            primaryCost = 14,
            secondaryCost = 18,
            mkCost = 40,
            maintCost = 10,
            level = 3
        ),

        TechniqueTableData(
            name = 20,
            effectRef = R.string.distanceLabelKM,
            effectVal = 10,
            primaryCost = 18,
            secondaryCost = 22,
            mkCost = 45,
            maintCost = 12,
            level = 3
        ),

        TechniqueTableData(
            name = 20,
            effectRef = R.string.distanceLabelKM,
            effectVal = 100,
            primaryCost = 22,
            secondaryCost = 26,
            mkCost = 50,
            maintCost = 14,
            level = 3
        )
    )

    val table21 = listOf(
        TechniqueTableData(
            name = 21,
            effectRef = R.string.distanceLabelM,
            effectVal = 1,
            primaryCost = 1,
            secondaryCost = 2,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 21,
            effectRef = R.string.distanceLabelM,
            effectVal = 5,
            primaryCost = 2,
            secondaryCost = 4,
            mkCost = 10,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 21,
            effectRef = R.string.distanceLabelM,
            effectVal = 10,
            primaryCost = 3,
            secondaryCost = 5,
            mkCost = 15,
            maintCost = 2,
            level = 1
        ),

        TechniqueTableData(
            name = 21,
            effectRef = R.string.distanceLabelM,
            effectVal = 25,
            primaryCost = 4,
            secondaryCost = 6,
            mkCost = 20,
            maintCost = 3,
            level = 1
        ),

        TechniqueTableData(
            name = 21,
            effectRef = R.string.distanceLabelM,
            effectVal = 50,
            primaryCost = 6,
            secondaryCost = 9,
            mkCost = 25,
            maintCost = 4,
            level = 2
        ),

        TechniqueTableData(
            name = 21,
            effectRef = R.string.distanceLabelM,
            effectVal = 100,
            primaryCost = 8,
            secondaryCost = 11,
            mkCost = 30,
            maintCost = 5,
            level = 2
        ),

        TechniqueTableData(
            name = 21,
            effectRef = R.string.distanceLabelM,
            effectVal = 500,
            primaryCost = 10,
            secondaryCost = 13,
            mkCost = 40,
            maintCost = 6,
            level = 2
        ),

        TechniqueTableData(
            name = 21,
            effectRef = R.string.distanceLabelKM,
            effectVal = 1,
            primaryCost = 12,
            secondaryCost = 15,
            mkCost = 50,
            maintCost = 8,
            level = 3
        ),

        TechniqueTableData(
            name = 21,
            effectRef = R.string.distanceLabelKM,
            effectVal = 5,
            primaryCost = 16,
            secondaryCost = 20,
            mkCost = 60,
            maintCost = 10,
            level = 3
        )
    )

    val table21a = listOf(
        TechniqueTableData(
            name = 21,
            effectRef = R.string.targetChoice,
            effectVal = null,
            primaryCost = 2,
            secondaryCost = 2,
            mkCost = 10,
            maintCost = 1,
            level = 1
        )
    )

    val table22 = listOf(
        TechniqueTableData(
            name = 22,
            effectRef = R.string.distanceLabelM,
            effectVal = 10,
            primaryCost = 2,
            secondaryCost = 4,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 22,
            effectRef = R.string.distanceLabelM,
            effectVal = 20,
            primaryCost = 3,
            secondaryCost = 5,
            mkCost = 10,
            maintCost = 2,
            level = 1
        ),

        TechniqueTableData(
            name = 22,
            effectRef = R.string.distanceLabelM,
            effectVal = 50,
            primaryCost = 4,
            secondaryCost = 6,
            mkCost = 10,
            maintCost = 3,
            level = 1
        ),

        TechniqueTableData(
            name = 22,
            effectRef = R.string.distanceLabelM,
            effectVal = 100,
            primaryCost = 5,
            secondaryCost = 8,
            mkCost = 15,
            maintCost = 4,
            level = 1
        ),

        TechniqueTableData(
            name = 22,
            effectRef = R.string.distanceLabelM,
            effectVal = 250,
            primaryCost = 6,
            secondaryCost = 9,
            mkCost = 20,
            maintCost = 5,
            level = 1
        ),

        TechniqueTableData(
            name = 22,
            effectRef = R.string.distanceLabelM,
            effectVal = 500,
            primaryCost = 8,
            secondaryCost = 11,
            mkCost = 25,
            maintCost = 6,
            level = 2
        ),

        TechniqueTableData(
            name = 22,
            effectRef = R.string.distanceLabelKM,
            effectVal = 1,
            primaryCost = 10,
            secondaryCost = 13,
            mkCost = 30,
            maintCost = 7,
            level = 2
        ),

        TechniqueTableData(
            name = 22,
            effectRef = R.string.distanceLabelKM,
            effectVal = 5,
            primaryCost = 14,
            secondaryCost = 18,
            mkCost = 35,
            maintCost = 8,
            level = 2
        ),

        TechniqueTableData(
            name = 22,
            effectRef = R.string.distanceLabelKM,
            effectVal = 10,
            primaryCost = 18,
            secondaryCost = 22,
            mkCost = 40,
            maintCost = 10,
            level = 3
        ),

        TechniqueTableData(
            name = 22,
            effectRef = R.string.distanceLabelKM,
            effectVal = 100,
            primaryCost = 22,
            secondaryCost = 26,
            mkCost = 50,
            maintCost = 12,
            level = 3
        )
    )

    val table23 = listOf(
        TechniqueTableData(
            name = 23,
            effectRef = R.string.addNumber,
            effectVal = 10,
            primaryCost = 2,
            secondaryCost = 4,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 23,
            effectRef = R.string.addNumber,
            effectVal = 25,
            primaryCost = 3,
            secondaryCost = 5,
            mkCost = 5,
            maintCost = 2,
            level = 1
        ),

        TechniqueTableData(
            name = 23,
            effectRef = R.string.addNumber,
            effectVal = 40,
            primaryCost = 4,
            secondaryCost = 6,
            mkCost = 10,
            maintCost = 3,
            level = 1
        ),

        TechniqueTableData(
            name = 23,
            effectRef = R.string.addNumber,
            effectVal = 50,
            primaryCost = 5,
            secondaryCost = 8,
            mkCost = 15,
            maintCost = 4,
            level = 1
        ),

        TechniqueTableData(
            name = 23,
            effectRef = R.string.addNumber,
            effectVal = 75,
            primaryCost = 8,
            secondaryCost = 11,
            mkCost = 20,
            maintCost = 6,
            level = 1
        ),

        TechniqueTableData(
            name = 23,
            effectRef = R.string.addNumber,
            effectVal = 90,
            primaryCost = 12,
            secondaryCost = 15,
            mkCost = 25,
            maintCost = 8,
            level = 1
        ),

        TechniqueTableData(
            name = 23,
            effectRef = R.string.addNumber,
            effectVal = 100,
            primaryCost = 14,
            secondaryCost = 18,
            mkCost = 30,
            maintCost = 10,
            level = 1
        ),

        TechniqueTableData(
            name = 23,
            effectRef = R.string.addNumber,
            effectVal = 125,
            primaryCost = 18,
            secondaryCost = 22,
            mkCost = 35,
            maintCost = 12,
            level = 2
        ),

        TechniqueTableData(
            name = 23,
            effectRef = R.string.addNumber,
            effectVal = 150,
            primaryCost = 22,
            secondaryCost = 26,
            mkCost = 40,
            maintCost = 14,
            level = 2
        ),

        TechniqueTableData(
            name = 23,
            effectRef = R.string.addNumber,
            effectVal = 175,
            primaryCost = 26,
            secondaryCost = 32,
            mkCost = 45,
            maintCost = 16,
            level = 3
        ),

        TechniqueTableData(
            name = 23,
            effectRef = R.string.addNumber,
            effectVal = 200,
            primaryCost = 30,
            secondaryCost = 36,
            mkCost = 50,
            maintCost = 18,
            level = 3
        )
    )

    val table23a = listOf(
        TechniqueTableData(
            name = 23,
            effectRef = R.string.autoCrit,
            effectVal = null,
            primaryCost = 8,
            secondaryCost = 8,
            mkCost = 30,
            maintCost = 4,
            level = 1
        )
    )

    val table24 = listOf(
        TechniqueTableData(
            name = 24,
            effectRef = R.string.addNumber,
            effectVal = 0,
            primaryCost = 2,
            secondaryCost = 4,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 24,
            effectRef = R.string.addNumber,
            effectVal = 5,
            primaryCost = 4,
            secondaryCost = 6,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 24,
            effectRef = R.string.addNumber,
            effectVal = 10,
            primaryCost = 6,
            secondaryCost = 9,
            mkCost = 10,
            maintCost = 2,
            level = 1
        ),

        TechniqueTableData(
            name = 24,
            effectRef = R.string.addNumber,
            effectVal = 15,
            primaryCost = 8,
            secondaryCost = 11,
            mkCost = 15,
            maintCost = 3,
            level = 2
        ),

        TechniqueTableData(
            name = 24,
            effectRef = R.string.addNumber,
            effectVal = 20,
            primaryCost = 10,
            secondaryCost = 13,
            mkCost = 20,
            maintCost = 4,
            level = 3
        )
    )

    val table24a = listOf(
        TechniqueTableData(
            name = 24,
            effectRef = R.string.projectileWeapon,
            effectVal = null,
            primaryCost = 2,
            secondaryCost = 2,
            mkCost = 10,
            maintCost = 1,
            level = 1
        )
    )

    val table25 = listOf(
        TechniqueTableData(
            name = 25,
            effectRef = R.string.justNum,
            effectVal = 4,
            primaryCost = 2,
            secondaryCost = 4,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 25,
            effectRef = R.string.justNum,
            effectVal = 6,
            primaryCost = 3,
            secondaryCost = 5,
            mkCost = 10,
            maintCost = 2,
            level = 1
        ),

        TechniqueTableData(
            name = 25,
            effectRef = R.string.justNum,
            effectVal = 8,
            primaryCost = 4,
            secondaryCost = 6,
            mkCost = 10,
            maintCost = 3,
            level = 1
        ),

        TechniqueTableData(
            name = 25,
            effectRef = R.string.justNum,
            effectVal = 10,
            primaryCost = 5,
            secondaryCost = 8,
            mkCost = 15,
            maintCost = 4,
            level = 1
        ),

        TechniqueTableData(
            name = 25,
            effectRef = R.string.justNum,
            effectVal = 12,
            primaryCost = 6,
            secondaryCost = 9,
            mkCost = 20,
            maintCost = 5,
            level = 1
        ),

        TechniqueTableData(
            name = 25,
            effectRef = R.string.justNum,
            effectVal = 14,
            primaryCost = 8,
            secondaryCost = 11,
            mkCost = 25,
            maintCost = 6,
            level = 2
        ),

        TechniqueTableData(
            name = 25,
            effectRef = R.string.justNum,
            effectVal = 16,
            primaryCost = 10,
            secondaryCost = 13,
            mkCost = 30,
            maintCost = 7,
            level = 2
        ),

        TechniqueTableData(
            name = 25,
            effectRef = R.string.justNum,
            effectVal = 18,
            primaryCost = 14,
            secondaryCost = 18,
            mkCost = 35,
            maintCost = 8,
            level = 3
        ),

        TechniqueTableData(
            name = 25,
            effectRef = R.string.justNum,
            effectVal = 20,
            primaryCost = 18,
            secondaryCost = 22,
            mkCost = 40,
            maintCost = 10,
            level = 3
        )
    )

    val table26 = listOf(
        TechniqueTableData(
            name = 26,
            effectRef = R.string.justNum,
            effectVal = 4,
            primaryCost = 1,
            secondaryCost = 2,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 26,
            effectRef = R.string.justNum,
            effectVal = 6,
            primaryCost = 2,
            secondaryCost = 4,
            mkCost = 5,
            maintCost = 2,
            level = 1
        ),

        TechniqueTableData(
            name = 26,
            effectRef = R.string.justNum,
            effectVal = 8,
            primaryCost = 3,
            secondaryCost = 5,
            mkCost = 10,
            maintCost = 3,
            level = 1
        ),

        TechniqueTableData(
            name = 26,
            effectRef = R.string.justNum,
            effectVal = 10,
            primaryCost = 4,
            secondaryCost = 6,
            mkCost = 10,
            maintCost = 4,
            level = 1
        ),

        TechniqueTableData(
            name = 26,
            effectRef = R.string.justNum,
            effectVal = 12,
            primaryCost = 5,
            secondaryCost = 8,
            mkCost = 15,
            maintCost = 5,
            level = 1
        ),

        TechniqueTableData(
            name = 26,
            effectRef = R.string.justNum,
            effectVal = 14,
            primaryCost = 6,
            secondaryCost = 9,
            mkCost = 20,
            maintCost = 6,
            level = 2
        ),

        TechniqueTableData(
            name = 26,
            effectRef = R.string.justNum,
            effectVal = 16,
            primaryCost = 8,
            secondaryCost = 11,
            mkCost = 25,
            maintCost = 7,
            level = 2
        ),

        TechniqueTableData(
            name = 26,
            effectRef = R.string.justNum,
            effectVal = 18,
            primaryCost = 10,
            secondaryCost = 13,
            mkCost = 30,
            maintCost = 8,
            level = 3
        ),

        TechniqueTableData(
            name = 26,
            effectRef = R.string.justNum,
            effectVal = 20,
            primaryCost = 12,
            secondaryCost = 15,
            mkCost = 35,
            maintCost = 10,
            level = 3
        )
    )

    val table27 = listOf(
        TechniqueTableData(
            name = 27,
            effectRef = R.string.justNum,
            effectVal = 100,
            primaryCost = 2,
            secondaryCost = 4,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 27,
            effectRef = R.string.justNum,
            effectVal = 200,
            primaryCost = 3,
            secondaryCost = 5,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 27,
            effectRef = R.string.justNum,
            effectVal = 300,
            primaryCost = 4,
            secondaryCost = 6,
            mkCost = 10,
            maintCost = 2,
            level = 1
        ),

        TechniqueTableData(
            name = 27,
            effectRef = R.string.justNum,
            effectVal = 400,
            primaryCost = 5,
            secondaryCost = 8,
            mkCost = 15,
            maintCost = 3,
            level = 1
        ),

        TechniqueTableData(
            name = 27,
            effectRef = R.string.justNum,
            effectVal = 500,
            primaryCost = 8,
            secondaryCost = 11,
            mkCost = 20,
            maintCost = 4,
            level = 1
        ),

        TechniqueTableData(
            name = 27,
            effectRef = R.string.justNum,
            effectVal = 800,
            primaryCost = 12,
            secondaryCost = 15,
            mkCost = 25,
            maintCost = 5,
            level = 2
        ),

        TechniqueTableData(
            name = 27,
            effectRef = R.string.justNum,
            effectVal = 1000,
            primaryCost = 14,
            secondaryCost = 18,
            mkCost = 30,
            maintCost = 8,
            level = 2
        ),

        TechniqueTableData(
            name = 27,
            effectRef = R.string.justNum,
            effectVal = 1250,
            primaryCost = 18,
            secondaryCost = 22,
            mkCost = 35,
            maintCost = 10,
            level = 2
        ),

        TechniqueTableData(
            name = 27,
            effectRef = R.string.justNum,
            effectVal = 1500,
            primaryCost = 22,
            secondaryCost = 26,
            mkCost = 40,
            maintCost = 12,
            level = 3
        ),

        TechniqueTableData(
            name = 27,
            effectRef = R.string.justNum,
            effectVal = 2000,
            primaryCost = 26,
            secondaryCost = 32,
            mkCost = 45,
            maintCost = 14,
            level = 3
        )
    )

    val table28 = listOf(
        TechniqueTableData(
            name = 28,
            effectRef = R.string.intangibility,
            effectVal = null,
            primaryCost = 3,
            secondaryCost = 5,
            mkCost = 10,
            maintCost = 2,
            level = 1
        )
    )

    val table29 = listOf(
        TechniqueTableData(
            name = 29,
            effectRef = R.string.justNum,
            effectVal = 1,
            primaryCost = 1,
            secondaryCost = 2,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 29,
            effectRef = R.string.justNum,
            effectVal = 2,
            primaryCost = 2,
            secondaryCost = 4,
            mkCost = 5,
            maintCost = 2,
            level = 1
        ),

        TechniqueTableData(
            name = 29,
            effectRef = R.string.justNum,
            effectVal = 4,
            primaryCost = 4,
            secondaryCost = 6,
            mkCost = 10,
            maintCost = 3,
            level = 1
        ),

        TechniqueTableData(
            name = 29,
            effectRef = R.string.justNum,
            effectVal = 6,
            primaryCost = 6,
            secondaryCost = 9,
            mkCost = 10,
            maintCost = 4,
            level = 1
        ),

        TechniqueTableData(
            name = 29,
            effectRef = R.string.justNum,
            effectVal = 10,
            primaryCost = 8,
            secondaryCost = 11,
            mkCost = 15,
            maintCost = 6,
            level = 2
        ),

        TechniqueTableData(
            name = 29,
            effectRef = R.string.justNum,
            effectVal = 15,
            primaryCost = 10,
            secondaryCost = 13,
            mkCost = 20,
            maintCost = 8,
            level = 2
        ),

        TechniqueTableData(
            name = 29,
            effectRef = R.string.justNum,
            effectVal = 20,
            primaryCost = 12,
            secondaryCost = 15,
            mkCost = 25,
            maintCost = 10,
            level = 2
        ),

        TechniqueTableData(
            name = 29,
            effectRef = R.string.justNum,
            effectVal = 25,
            primaryCost = 14,
            secondaryCost = 18,
            mkCost = 30,
            maintCost = 12,
            level = 3
        )
    )

    val table29a = listOf(
        TechniqueTableData(
            name = 29,
            effectRef = R.string.moderate,
            effectVal = null,
            primaryCost = 1,
            secondaryCost = 1,
            mkCost = 5,
            maintCost = 0,
            level = 1
        ),

        TechniqueTableData(
            name = 29,
            effectRef = R.string.difficult,
            effectVal = null,
            primaryCost = 2,
            secondaryCost = 2,
            mkCost = 10,
            maintCost = 0,
            level = 1
        ),

        TechniqueTableData(
            name = 29,
            effectRef = R.string.veryDifficult,
            effectVal = null,
            primaryCost = 3,
            secondaryCost = 3,
            mkCost = 10,
            maintCost = 0,
            level = 1
        ),

        TechniqueTableData(
            name = 29,
            effectRef = R.string.absurd,
            effectVal = null,
            primaryCost = 4,
            secondaryCost = 4,
            mkCost = 15,
            maintCost = 0,
            level = 1
        ),

        TechniqueTableData(
            name = 29,
            effectRef = R.string.almostImpossible,
            effectVal = null,
            primaryCost = 5,
            secondaryCost = 5,
            mkCost = 15,
            maintCost = 0,
            level = 1
        ),

        TechniqueTableData(
            name = 29,
            effectRef = R.string.impossible,
            effectVal = null,
            primaryCost = 6,
            secondaryCost = 6,
            mkCost = 20,
            maintCost = 0,
            level = 2
        ),

        TechniqueTableData(
            name = 29,
            effectRef = R.string.inhuman,
            effectVal = null,
            primaryCost = 7,
            secondaryCost = 7,
            mkCost = 25,
            maintCost = 0,
            level = 2
        ),

        TechniqueTableData(
            name = 29,
            effectRef = R.string.zenData,
            effectVal = null,
            primaryCost = 8,
            secondaryCost = 8,
            mkCost = 30,
            maintCost = 0,
            level = 3
        )
    )

    val table30 = listOf(
        TechniqueTableData(
            name = 30,
            effectRef = R.string.attackMirror,
            effectVal = null,
            primaryCost = 12,
            secondaryCost = 15,
            mkCost = 30,
            maintCost = 8,
            level = 2
        )
    )

    val table30a = listOf(
        TechniqueTableData(
            name = 30,
            effectRef = R.string.targetChoice,
            effectVal = null,
            primaryCost = 2,
            secondaryCost = 2,
            mkCost = 10,
            maintCost = 2,
            level = 1
        )
    )

    val table30b = listOf(
        TechniqueTableData(
            name = 30,
            effectRef = R.string.mirrorEsoteric,
            effectVal = null,
            primaryCost = 4,
            secondaryCost = 4,
            mkCost = 20,
            maintCost = 1,
            level = 1
        )
    )

    val table31 = listOf(
        TechniqueTableData(
            name = 31,
            effectRef = R.string.energyData,
            effectVal = null,
            primaryCost = 1,
            secondaryCost = 2,
            mkCost = 5,
            maintCost = 1,
            level = 1
        )
    )

    val table32 = listOf(
        TechniqueTableData(
            name = 32,
            effectRef = R.string.elementalData,
            effectVal = null,
            primaryCost = 2,
            secondaryCost = 4,
            mkCost = 5,
            maintCost = 1,
            level = 1
        )
    )

    val table33 = listOf(
        TechniqueTableData(
            name = 33,
            effectRef = R.string.energyData,
            effectVal = null,
            primaryCost = 5,
            secondaryCost = 8,
            mkCost = 10,
            maintCost = 1,
            level = 1
        )
    )

    val table34 = listOf(
        TechniqueTableData(
            name = 34,
            effectRef = R.string.justNum,
            effectVal = 100,
            primaryCost = 2,
            secondaryCost = 4,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 34,
            effectRef = R.string.justNum,
            effectVal = 200,
            primaryCost = 3,
            secondaryCost = 5,
            mkCost = 5,
            maintCost = 1,
            level = 1
        ),

        TechniqueTableData(
            name = 34,
            effectRef = R.string.justNum,
            effectVal = 300,
            primaryCost = 4,
            secondaryCost = 6,
            mkCost = 10,
            maintCost = 2,
            level = 1
        ),

        TechniqueTableData(
            name = 34,
            effectRef = R.string.justNum,
            effectVal = 400,
            primaryCost = 5,
            secondaryCost = 8,
            mkCost = 15,
            maintCost = 3,
            level = 1
        ),

        TechniqueTableData(
            name = 34,
            effectRef = R.string.justNum,
            effectVal = 600,
            primaryCost = 8,
            secondaryCost = 11,
            mkCost = 20,
            maintCost = 4,
            level = 1
        ),

        TechniqueTableData(
            name = 34,
            effectRef = R.string.justNum,
            effectVal = 800,
            primaryCost = 12,
            secondaryCost = 15,
            mkCost = 25,
            maintCost = 5,
            level = 1
        ),

        TechniqueTableData(
            name = 34,
            effectRef = R.string.justNum,
            effectVal = 1000,
            primaryCost = 14,
            secondaryCost = 18,
            mkCost = 30,
            maintCost = 8,
            level = 2
        ),

        TechniqueTableData(
            name = 34,
            effectRef = R.string.justNum,
            effectVal = 1200,
            primaryCost = 18,
            secondaryCost = 22,
            mkCost = 35,
            maintCost = 10,
            level = 2
        ),

        TechniqueTableData(
            name = 34,
            effectRef = R.string.justNum,
            effectVal = 1400,
            primaryCost = 22,
            secondaryCost = 26,
            mkCost = 40,
            maintCost = 12,
            level = 3
        )
    )

    val table35 = listOf(
        TechniqueTableData(
            name = 35,
            effectRef = R.string.singleElement,
            effectVal = null,
            primaryCost = 0,
            secondaryCost = 0,
            mkCost = -15,
            maintCost = 0,
            level = 1
        ),

        TechniqueTableData(
            name = 35,
            effectRef = R.string.twoElement,
            effectVal = null,
            primaryCost = 0,
            secondaryCost = 0,
            mkCost = -10,
            maintCost = 0,
            level = 1
        )
    )

    val table36 = listOf(
        TechniqueTableData(
            name = 36,
            effectRef = R.string.noDamage,
            effectVal = null,
            primaryCost = 0,
            secondaryCost = 0,
            mkCost = -20,
            maintCost = 0,
            level = 1
        ),

        TechniqueTableData(
            name = 36,
            effectRef = R.string.halfDamage,
            effectVal = null,
            primaryCost = 0,
            secondaryCost = 0,
            mkCost = -10,
            maintCost = 0,
            level = 1
        )
    )

    val table37 = listOf(
        TechniqueTableData(
            name = 37,
            effectRef = R.string.singleIntensity,
            effectVal = null,
            primaryCost = 0,
            secondaryCost = 0,
            mkCost = -15,
            maintCost = 0,
            level = 1
        ),

        TechniqueTableData(
            name = 37,
            effectRef = R.string.majorIntensity,
            effectVal = null,
            primaryCost = 0,
            secondaryCost = 0,
            mkCost = -10,
            maintCost = 0,
            level = 1
        ),

        TechniqueTableData(
            name = 37,
            effectRef = R.string.determinedCondition,
            effectVal = null,
            primaryCost = 0,
            secondaryCost = 0,
            mkCost = -5,
            maintCost = 0,
            level = 1
        )
    )

    val table38 = listOf(
        TechniqueTableData(
            name = 38,
            effectRef = R.string.predetermination,
            effectVal = null,
            primaryCost = 0,
            secondaryCost = 0,
            mkCost = -20,
            maintCost = 0,
            level = 2
        )
    )

    /**
     * Finds the technique table based on the inputted parameters.
     *
     * @param name name reference for the data
     * @param primary primary cost of the data item
     * @param secondary secondary cost of the data item
     * @param mkCost martial knowledge of the data item
     * @return the desired table data with the given parameters
     */
    fun findData(
        name: Int,
        primary: Int,
        secondary: Int,
        mkCost: Int
    ): TechniqueTableData{
        //retrieve the desired table based on the name and two costs
        val searchTable = when(name){
            1 -> table1
            2 -> table2
            3 -> table3
            4 -> table4
            5 -> table5
            6 -> table6
            7 -> table7
            8 -> if(primary != secondary) table8 else table8a
            9 -> {
                if(primary != secondary) table9
                else if (primary == 10) table9a
                else table9b
            }
            10 -> if(primary != secondary) table10 else table10a
            11 -> if(primary != secondary) table11 else table11a
            12 -> if(primary != secondary) table12 else table12a
            13 -> table13
            15 -> table15
            16 -> table16
            17 -> table17
            18 -> table18
            19 -> table19
            20 -> table20
            21 -> if(primary != secondary) table21 else table21a
            22 -> table22
            23 -> if(primary != secondary) table23 else table23a
            24 -> if(primary != secondary) table24 else table24a
            25 -> table25
            26 -> table26
            27 -> table27
            28 -> table28
            29 -> if(primary != secondary) table29 else table29a
            30 -> {
                if(primary != secondary) table30
                else if (primary == 2) table30a
                else table30b
            }
            31 -> table31
            32 -> table32
            33 -> table33
            34 -> table34
            35 -> table35
            36 -> table36
            37 -> table37
            38 -> table38
            else -> null
        }

        //initialize the output as single intensity requirement
        var output: TechniqueTableData = table37[0]

        //search the desired table for the desired item
        searchTable!!.forEach{tableData ->
            //get data when match found
            if(tableData.primaryCost == primary &&
                    tableData.secondaryCost == secondary &&
                    tableData.mkCost == mkCost) {
                output = tableData
                return@forEach
            }
        }

        //construct determined condition cost if required
        if(name == 37)
            output = TechniqueTableData(
                name,
                output.effectRef,
                output.effectVal,
                output.primaryCost,
                output.secondaryCost,
                mkCost,
                output.maintCost,
                output.level
            )

        //return the desired data table
        return output
    }

    /**
     * Construct a data table for a state technique effect.
     *
     * @param stateRef reference to the effect type of this technique
     * @param costRemain
     * @param primary
     * @return the table data for the state effect
     */
    fun stateEffect(
        stateRef: Int,
        costRemain: Int,
        primary: Int
    ): TechniqueTableData?{
        //retrieve the state data for this effect
        val base = table14a[stateRef]

        //for each available resistance data
        table14.forEach{resTableData ->
            //find a match for the martial knowledge and primary cost
            if(resTableData.mkCost == costRemain && resTableData.primaryCost == primary){
                //return the merged effect data
                return TechniqueTableData(
                    name = 14,
                    effectRef = base.effectRef,
                    effectVal = resTableData.effectVal,
                    primaryCost = base.primaryCost + resTableData.primaryCost,
                    secondaryCost = base.secondaryCost + resTableData.secondaryCost,
                    mkCost = base.mkCost + resTableData.mkCost,
                    maintCost = resTableData.maintCost,
                    level = if(base.level >= resTableData.level) base.level else resTableData.level
                )
            }
        }

        //return no effect found
        return null
    }
}

/**
 * Format of how technique effect data is stored.
 *
 * @param effectRef name to display for the data
 * @param primaryCost ki cost of technique effect if selected first
 * @param secondaryCost ki cost of technique effect if not selected first
 * @param mkCost martial knowledge required to use effect
 * @param maintCost ki cost to maintain the effect
 * @param level minimum technique level needed to acquire the effect
 */
data class TechniqueTableData(
    val name: Int,
    val effectRef: Int,
    val effectVal: Int?,
    val primaryCost: Int,
    val secondaryCost: Int,
    val mkCost: Int,
    val maintCost: Int,
    val level: Int
){
    /**
     * Determines if the inputted data table matches this one.
     *
     * @param dataTable the data table to compare to this one
     * @return true if contents match exactly
     */
    fun isEquivalent(
        dataTable: TechniqueTableData
    ): Boolean{
        return dataTable.name == name &&
                dataTable.effectRef == effectRef &&
                dataTable.effectVal == effectVal &&
                dataTable.primaryCost == primaryCost &&
                dataTable.secondaryCost == secondaryCost &&
                dataTable.mkCost == mkCost &&
                dataTable.maintCost == maintCost &&
                dataTable.level == level
    }

    /**
     * Writes data about this table to file.
     *
     * @param byteArray output stream to write to
     */
    fun write(byteArray: ByteArrayOutputStream){
        //write name reference for this table
        writeDataTo(writer = byteArray, input = name)

        //if state table
        if(name == 14){
            //write specific data for each state effect
            when(effectRef){
                R.string.physReduction -> {
                    writeDataTo(writer = byteArray, input = 0)
                    writeDataTo(writer = byteArray, input = mkCost - 10)
                    writeDataTo(writer = byteArray, input = primaryCost - 2)
                }
                R.string.blindnessData ->{
                    writeDataTo(writer = byteArray, input = 1)
                    writeDataTo(writer = byteArray, input = mkCost - 15)
                    writeDataTo(writer = byteArray, input = primaryCost - 5)
                }
                R.string.charReduction ->{
                    writeDataTo(writer = byteArray, input = 2)
                    writeDataTo(writer = byteArray, input = mkCost - 10)
                    writeDataTo(writer = byteArray, input = primaryCost - 2)
                }
                R.string.partParalyze ->{
                    writeDataTo(writer = byteArray, input = 3)
                    writeDataTo(writer = byteArray, input = mkCost - 10)
                    writeDataTo(writer = byteArray, input = primaryCost - 6)
                }
                R.string.damage ->{
                    writeDataTo(writer = byteArray, input = 4)
                    writeDataTo(writer = byteArray, input = mkCost - 10)
                    writeDataTo(writer = byteArray, input = primaryCost - 5)
                }
                R.string.unconscious ->{
                    writeDataTo(writer = byteArray, input = 5)
                    writeDataTo(writer = byteArray, input = mkCost - 15)
                    writeDataTo(writer = byteArray, input = primaryCost - 8)
                }
                R.string.coma ->{
                    writeDataTo(writer = byteArray, input = 6)
                    writeDataTo(writer = byteArray, input = mkCost - 30)
                    writeDataTo(writer = byteArray, input = primaryCost - 10)
                }
                R.string.totalParalyze ->{
                    writeDataTo(writer = byteArray, input = 7)
                    writeDataTo(writer = byteArray, input = mkCost - 20)
                    writeDataTo(writer = byteArray, input = primaryCost - 8)
                }
                R.string.lifeDrain ->{
                    writeDataTo(writer = byteArray, input = 8)
                    writeDataTo(writer = byteArray, input = mkCost - 15)
                    writeDataTo(writer = byteArray, input = primaryCost - 8)
                }
                R.string.controlData ->{
                    writeDataTo(writer = byteArray, input = 9)
                    writeDataTo(writer = byteArray, input = mkCost - 40)
                    writeDataTo(writer = byteArray, input = primaryCost - 10)
                }
                R.string.death ->{
                    writeDataTo(writer = byteArray, input = 10)
                    writeDataTo(writer = byteArray, input = mkCost - 50)
                    writeDataTo(writer = byteArray, input = primaryCost - 12)
                }
                else -> {}
            }
        }

        //otherwise, write primary, secondary, and martial knowledge costs
        else {
            writeDataTo(writer = byteArray, input = primaryCost)
            writeDataTo(writer = byteArray, input = secondaryCost)
            writeDataTo(writer = byteArray, input = mkCost)
        }
    }
}