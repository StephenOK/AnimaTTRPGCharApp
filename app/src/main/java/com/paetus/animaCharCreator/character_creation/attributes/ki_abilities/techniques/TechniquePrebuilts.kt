package com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.effect.TechniqueTableData
import com.paetus.animaCharCreator.enumerations.Element
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.base.PrebuiltTech
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.effect.TechniqueEffect

/**
 * Record of prebuilt techniques the character may have access to.
 */
class TechniquePrebuilts{
    val excisumAeris = PrebuiltTech(
        "Excisum Aeris",
        R.string.excisum,
        R.string.excisumDesc,
        1,
        mutableListOf(0, 0, 0, 0, 0, 0),
        mutableListOf(
            TechniqueEffect(
                TechniqueTableData(20, R.string.distanceLabelM, 50, 4, 6, 15, 3, 1),
                mutableListOf(0, 4, 0, 0, 2, 0),
                mutableListOf(null, 2, 3, 4, 0, 1),
                mutableListOf(Element.Air)
            ),
            TechniqueEffect(
                TechniqueTableData(13, R.string.addNumber, 50, 2, 4, 10, 1, 1),
                mutableListOf(0, 0, 4, 0, 0, 0),
                mutableListOf(null, 1, 0, 2, 3, 3),
                mutableListOf(Element.Air, Element.Water, Element.Fire)
            )
        )
    )

    val velocitasVentas = PrebuiltTech(
        "Velocitas Ventas",
        R.string.velocitasVentas,
        R.string.velocitasVentasDesc,
        1,
        mutableListOf(0, 0, 0, 0, 0, 0),
        mutableListOf(
            TechniqueEffect(
                TechniqueTableData(10, R.string.addNumber, 3, 9, 12, 15, 3, 1),
                mutableListOf(0, 7, 3, 0, 4, 0),
                mutableListOf(null, 0, 2, 1, 3, 3),
                mutableListOf(Element.Air, Element.Water, Element.Dark)
            ),
            TechniqueEffect(
                TechniqueTableData(13, R.string.addNumber, 50, 2, 4, 10, 1, 1),
                mutableListOf(0, 0, 4, 0, 0, 0),
                mutableListOf(null, 1, 0, 2, 3, 3),
                mutableListOf(Element.Air, Element.Water, Element.Fire)
            )
        )
    )

    val excisumMagister = PrebuiltTech(
        "Excisum Magister",
        R.string.excisumMagister,
        R.string.excisumMagisterDesc,
        2,
        mutableListOf(0, 0, 0, 0, 0, 0),
        mutableListOf(
            TechniqueEffect(
                TechniqueTableData(13, R.string.addNumber, 125, 8, 11, 25, 4, 2),
                mutableListOf(0, 0, 8, 0, 0, 0),
                mutableListOf(null, 1, 0, 2, 3, 3),
                mutableListOf(Element.Air, Element.Water, Element.Fire)
            ),
            TechniqueEffect(
                TechniqueTableData(1, R.string.addNumber, 75, 8, 11, 20, 6, 1),
                mutableListOf(0, 8, 0, 0, 5, 0),
                mutableListOf(2, 1, 2, null, 2, 3),
                mutableListOf(Element.Air, Element.Fire, Element.Dark)
            )
        )
    )

    val magnusExacter = PrebuiltTech(
        "Magnus Exacter",
        R.string.magnusExacter,
        R.string.magnusExacterDesc,
        2,
        mutableListOf(0, 0, 0, 0, 0, 0),
        mutableListOf(
            TechniqueEffect(
                TechniqueTableData(21, R.string.distanceLabelM, 10, 3, 5, 15, 2, 1),
                mutableListOf(0, 0, 0, 0, 3, 0),
                mutableListOf(null, 2, 3, 3, 0, 1),
                mutableListOf(Element.Dark, Element.Light, Element.Fire)
            ),
            TechniqueEffect(
                TechniqueTableData(13, R.string.addNumber, 150, 10, 13, 30, 5, 2),
                mutableListOf(0, 7, 7, 0, 0, 0),
                mutableListOf(null, 1, 0, 2, 3, 3),
                mutableListOf(Element.Air, Element.Water, Element.Fire)
            )
        )
    )

    val summum = PrebuiltTech(
        "Summum",
        R.string.summum,
        R.string.summumDesc,
        3,
        mutableListOf(0, 0, 0, 0, 0, 0),
        mutableListOf(
            TechniqueEffect(
                TechniqueTableData(10, R.string.addNumber, 8, 22, 26, 50, 10, 2),
                mutableListOf(0, 17, 0, 0, 8, 0),
                mutableListOf(null, 0, 2, 1, 3, 3),
                mutableListOf(Element.Air, Element.Water, Element.Dark)
            ),
            TechniqueEffect(
                TechniqueTableData(13, R.string.addNumber, 175, 12, 15, 35, 6, 3),
                mutableListOf(0, 0, 15, 0, 0, 0),
                mutableListOf(null, 1, 0, 2, 3, 3),
                mutableListOf(Element.Air, Element.Water, Element.Fire)
            )
        )
    )

    val feuer = PrebuiltTech(
        "Feuer",
        R.string.feuer,
        R.string.feuerDesc,
        1,
        mutableListOf(1, 0, 0, 0, 0, 1),
        mutableListOf(
            TechniqueEffect(
                TechniqueTableData(8, R.string.addNumber, 25, 2, 4, 5, 1, 1),
                mutableListOf(2, 0, 0, 0, 0, 0),
                mutableListOf(0, 3, null, 1, 2, 1),
                mutableListOf(Element.Fire, Element.Earth)
            ),
            TechniqueEffect(
                TechniqueTableData(32, R.string.elementalData, null, 2, 4, 5, 1, 1),
                mutableListOf(0, 0, 0, 0, 1, 4),
                mutableListOf(3, 3, null, 2, 0, 1),
                mutableListOf(Element.Fire)
            )
        )
    )

    val leFeu = PrebuiltTech(
        "Le Feu",
        R.string.leFeu,
        R.string.leFeuDesc,
        1,
        mutableListOf(0, 0, 0, 0, 0, 0),
        mutableListOf(
            TechniqueEffect(
                TechniqueTableData(1, R.string.addNumber, 40, 4, 6, 10, 3, 1),
                mutableListOf(1, 3, 0, 0, 4, 0),
                mutableListOf(2, 0, 2, null, 2, 3),
                mutableListOf(Element.Air, Element.Fire, Element.Dark)
            ),
            TechniqueEffect(
                TechniqueTableData(20, R.string.distanceLabelM, 20, 3, 5, 10, 2, 1),
                mutableListOf(0, 0, 0, 0, 1, 5),
                mutableListOf(null, 2, 3, 4, 0, 1),
                mutableListOf(Element.Air, Element.Water, Element.Fire)
            )
        )
    )

    val horecka = PrebuiltTech(
        "Horecka",
        R.string.horecka,
        R.string.horeckaDesc,
        2,
        mutableListOf(0, 0, 0, 0, 0, 0),
        mutableListOf(
            TechniqueEffect(
                TechniqueTableData(7, R.string.multNumber, 2, 10, 15, 25, 4, 1),
                mutableListOf(2, 0, 0, 0, 2, 8),
                mutableListOf(0, 3, null, 2, 1, 1),
                mutableListOf(Element.Light, Element.Water, Element.Earth)
            ),
            TechniqueEffect(
                TechniqueTableData(21, R.string.distanceLabelM, 50, 6, 9, 25, 4, 2),
                mutableListOf(0, 6, 0, 0, 4, 2),
                mutableListOf(null, 2, 3, 3, 0, 1),
                mutableListOf(Element.Dark, Element.Light, Element.Fire)
            ),
            TechniqueEffect(
                TechniqueTableData(37, R.string.determinedCondition, null, 0, 0, -15, 0, 1),
                mutableListOf(0, 0, 0, 0, 0, 0),
                mutableListOf(null, null, null, null, null, null),
                mutableListOf(Element.Free)
            )
        )
    )

    val vatra = PrebuiltTech(
        "Vatra",
        R.string.vatra,
        R.string.vatraDesc,
        2,
        mutableListOf(0, 0, 0, 0, 0, 0),
        mutableListOf(
            TechniqueEffect(
                TechniqueTableData(1, R.string.addNumber, 75, 8, 11, 20, 6, 1),
                mutableListOf(0, 6, 0, 0, 0, 5),
                mutableListOf(2, 0, 2, null, 2, 3),
                mutableListOf(Element.Air, Element.Fire, Element.Dark)
            ),
            TechniqueEffect(
                TechniqueTableData(8, R.string.addNumber, 75, 6, 9, 20, 3, 1),
                mutableListOf(1, 0, 0, 3, 0, 7),
                mutableListOf(0, 3, null, 1, 2, 1),
                mutableListOf(Element.Fire, Element.Earth)
            ),
            TechniqueEffect(
                TechniqueTableData(23, R.string.addNumber, 40, 4, 6, 10, 3, 1),
                mutableListOf(0, 0, 0, 0, 6, 0),
                mutableListOf(1, 2, null, 2, 0, 1),
                mutableListOf(Element.Fire, Element.Earth)
            ),
            TechniqueEffect(
                TechniqueTableData(37, R.string.determinedCondition, null, 0, 0, -10, 0, 1),
                mutableListOf(0, 0, 0, 0, 0, 0),
                mutableListOf(null, null, null, null, null, null),
                mutableListOf(Element.Free)
            )
        )
    )

    val eld = PrebuiltTech(
        "Eld",
        R.string.eld,
        R.string.eldDesc,
        3,
        mutableListOf(0, 0, 0, 0, 0, 0),
        mutableListOf(
            TechniqueEffect(
                TechniqueTableData(8, R.string.doubleVitalSac, null, 10, 10, 50, 4, 1),
                mutableListOf(10, 0, 0, 0, 0, 0),
                mutableListOf(0, 3, null, 1, 2, 1),
                mutableListOf(Element.Fire, Element.Earth)
            ),
            TechniqueEffect(
                TechniqueTableData(1, R.string.addNumber, 150, 22, 26, 40, 14, 2),
                mutableListOf(0, 10, 0, 0, 0, 19),
                mutableListOf(2, 0, 2, null, 2, 3),
                mutableListOf(Element.Air, Element.Fire, Element.Dark)
            ),
            TechniqueEffect(
                TechniqueTableData(37, R.string.determinedCondition, null, 0, 0, -30, 0, 1),
                mutableListOf(0, 0, 0, 0, 0, 0),
                mutableListOf(null, null, null, null, null, null),
                mutableListOf(Element.Free)
            )
        )
    )

    val theScales = PrebuiltTech(
        "The Scales",
        R.string.theScales,
        R.string.theScalesDesc,
        1,
        mutableListOf(0, 0, 0, 0, 0, 0),
        mutableListOf(
            TechniqueEffect(
                TechniqueTableData(11, R.string.addNumber, 6, 5, 8, 20, 6, 1),
                mutableListOf(0, 3, 1, 3, 0, 0),
                mutableListOf(null, 1, 0, 1, 3, 3),
                mutableListOf(Element.Light)
            )
        )
    )

    val theClaws = PrebuiltTech(
        "The Claws",
        R.string.theClaws,
        R.string.theClawsDesc,
        1,
        mutableListOf(0, 0, 0, 0, 0, 0),
        mutableListOf(
            TechniqueEffect(
                TechniqueTableData(9, R.string.addNumber, 1, 6, 9, 20, 3, 1),
                mutableListOf(0, 4, 4, 0, 0, 0),
                mutableListOf(null, 0, 2, 1, 3, 3),
                mutableListOf(Element.Air, Element.Water)
            ),
            TechniqueEffect(
                TechniqueTableData(8, R.string.addNumber, 40, 3, 5, 10, 1, 1),
                mutableListOf(2, 0, 0, 4, 0, 0),
                mutableListOf(0, 3, null, 1, 2, 1),
                mutableListOf(Element.Fire, Element.Earth)
            )
        )
    )

    val theFang = PrebuiltTech(
        "The Fang",
        R.string.theFang,
        R.string.theFangDesc,
        2,
        mutableListOf(0, 0, 0, 0, 0, 0),
        mutableListOf(
            TechniqueEffect(
                TechniqueTableData(1, R.string.addNumber, 50, 5, 8, 15, 4, 1),
                mutableListOf(0, 5, 0, 0, 0, 0),
                mutableListOf(2, 0, 2, null, 2, 3),
                mutableListOf(Element.Air, Element.Fire, Element.Dark)
            ),
            TechniqueEffect(
                TechniqueTableData(17, R.string.subtractAT, 6, 6, 9, 20, 3, 2),
                mutableListOf(3, 0, 0, 8, 0, 0),
                mutableListOf(0, 2, null, 2, 1, 2),
                mutableListOf(Element.Dark, Element.Fire)
            ),
            TechniqueEffect(
                TechniqueTableData(15, R.string.subtractNumber, 50, 3, 5, 10, 2, 1),
                mutableListOf(0, 0, 6, 0, 0, 0),
                mutableListOf(null, 0, 1, 2, 2, 2),
                mutableListOf(Element.Air)
            )
        )
    )

    val theTail = PrebuiltTech(
        "The Tail",
        R.string.theTail,
        R.string.theTailDesc,
        2,
        mutableListOf(0, 0, 0, 0, 0, 0),
        mutableListOf(
            TechniqueEffect(
                TechniqueTableData(26, R.string.justNum, 16, 8, 11, 25, 7, 2),
                mutableListOf(2, 0, 0, 8, 0, 0),
                mutableListOf(0, 3, null, 2, 1, 1),
                mutableListOf(Element.Earth, Element.Fire)
            ),
            TechniqueEffect(
                TechniqueTableData(1, R.string.addNumber, 40, 4, 6, 10, 3, 1),
                mutableListOf(0, 6, 0, 0, 0, 0),
                mutableListOf(2, 0, 2, null, 2, 3),
                mutableListOf(Element.Air, Element.Fire, Element.Dark)
            ),
            TechniqueEffect(
                TechniqueTableData(21, R.string.distanceLabelM, 25, 4, 6, 20, 3, 1),
                mutableListOf(0, 0, 0, 0, 6, 0),
                mutableListOf(null, 2, 3, 3, 0, 1),
                mutableListOf(Element.Dark, Element.Light, Element.Fire)
            )
        )
    )

    val dragonsBreath = PrebuiltTech(
        "The Dragon's Breath",
        R.string.dragonBreath,
        R.string.dragonBreathDesc,
        3,
        mutableListOf(0, 0, 0, 0, 0, 0),
        mutableListOf(
            TechniqueEffect(
                TechniqueTableData(20, R.string.distanceLabelKM, 1, 10, 13, 35, 8, 2),
                mutableListOf(0, 0, 6, 10, 4, 0),
                mutableListOf(null, 2, 3, 4, 0, 1),
                mutableListOf(Element.Air, Element.Water, Element.Fire)
            ),
            TechniqueEffect(
                TechniqueTableData(21, R.string.distanceLabelM, 100, 8, 11, 30, 5, 2),
                mutableListOf(0, 10, 4, 0, 2, 0),
                mutableListOf(null, 2, 3, 3, 0, 1),
                mutableListOf(Element.Dark, Element.Light, Element.Fire)
            ),
            TechniqueEffect(
                TechniqueTableData(31, R.string.energyData, null, 1, 2, 5, 1, 1),
                mutableListOf(5, 0, 0, 0, 0, 0),
                mutableListOf(3, 3, null, 2, 0, 1),
                mutableListOf(Element.Fire, Element.Light, Element.Dark)
            ),
            TechniqueEffect(
                TechniqueTableData(7, R.string.multNumber, 2, 10, 15, 25, 4, 1),
                mutableListOf(10, 0, 0, 0, 0, 0),
                mutableListOf(0, 3, null, 2, 1, 1),
                mutableListOf(Element.Fire, Element.Earth)
            ),
            TechniqueEffect(
                TechniqueTableData(38, R.string.predetermination, null, 0, 0, -20, 0, 2),
                mutableListOf(0, 0, 0, 0, 0, 0),
                mutableListOf(null, null, null, null, null, null),
                mutableListOf(Element.Free)
            )
        )
    )

    //compile prebuilt techniques into a single list
    val allTechniques = listOf(excisumAeris, velocitasVentas, excisumMagister, magnusExacter,
        summum, feuer, leFeu, horecka, vatra, eld, theScales, theClaws, theFang, theTail, dragonsBreath)
}