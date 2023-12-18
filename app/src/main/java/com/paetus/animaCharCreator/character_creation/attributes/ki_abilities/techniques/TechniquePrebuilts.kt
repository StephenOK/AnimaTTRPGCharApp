package com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.effect.TechniqueTableData
import com.paetus.animaCharCreator.enumerations.Element
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.base.PrebuiltTech
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.effect.TechniqueEffect
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.effect.TechniqueTableDataRecord

/**
 * Record of prebuilt techniques the character may have access to.
 */
class TechniquePrebuilts(
    techniqueDataRecord: TechniqueTableDataRecord
){
    private val excisumAeris = PrebuiltTech(
        saveName = "Excisum Aeris",
        name = R.string.excisum,
        description = R.string.excisumDesc,
        level = 1,
        maintArray = mutableListOf(0, 0, 0, 0, 0, 0),
        givenAbilities = mutableListOf(
            TechniqueEffect(
                data = techniqueDataRecord.table20[3],
                kiBuild = mutableListOf(0, 4, 0, 0, 2, 0),
                buildAdditions = mutableListOf(null, 2, 3, 4, 0, 1),
                elements = mutableListOf(Element.Air)
            ),
            TechniqueEffect(
                data = techniqueDataRecord.table13[1],
                kiBuild = mutableListOf(0, 0, 4, 0, 0, 0),
                buildAdditions = mutableListOf(null, 1, 0, 2, 3, 3),
                elements = mutableListOf(Element.Air, Element.Water, Element.Fire)
            )
        )
    )

    private val velocitasVentas = PrebuiltTech(
        saveName = "Velocitas Ventas",
        name = R.string.velocitasVentas,
        description = R.string.velocitasVentasDesc,
        level = 1,
        maintArray = mutableListOf(0, 0, 0, 0, 0, 0),
        givenAbilities = mutableListOf(
            TechniqueEffect(
                data = techniqueDataRecord.table10[2],
                kiBuild = mutableListOf(0, 7, 3, 0, 4, 0),
                buildAdditions = mutableListOf(null, 0, 2, 1, 3, 3),
                elements = mutableListOf(Element.Air, Element.Water, Element.Dark)
            ),
            TechniqueEffect(
                data = techniqueDataRecord.table13[1],
                kiBuild = mutableListOf(0, 0, 4, 0, 0, 0),
                buildAdditions = mutableListOf(null, 1, 0, 2, 3, 3),
                elements = mutableListOf(Element.Air, Element.Water, Element.Fire)
            )
        )
    )

    private val excisumMagister = PrebuiltTech(
        "Excisum Magister",
        R.string.excisumMagister,
        R.string.excisumMagisterDesc,
        2,
        mutableListOf(0, 0, 0, 0, 0, 0),
        mutableListOf(
            TechniqueEffect(
                data = techniqueDataRecord.table13[4],
                kiBuild = mutableListOf(0, 0, 8, 0, 0, 0),
                buildAdditions = mutableListOf(null, 1, 0, 2, 3, 3),
                elements = mutableListOf(Element.Air, Element.Water, Element.Fire)
            ),
            TechniqueEffect(
                data = techniqueDataRecord.table1[4],
                kiBuild = mutableListOf(0, 8, 0, 0, 5, 0),
                buildAdditions = mutableListOf(2, 1, 2, null, 2, 3),
                elements = mutableListOf(Element.Air, Element.Fire, Element.Dark)
            )
        )
    )

    private val magnusExacter = PrebuiltTech(
        saveName = "Magnus Exacter",
        name = R.string.magnusExacter,
        description = R.string.magnusExacterDesc,
        level = 2,
        maintArray = mutableListOf(0, 0, 0, 0, 0, 0),
        givenAbilities = mutableListOf(
            TechniqueEffect(
                data = techniqueDataRecord.table21[2],
                kiBuild = mutableListOf(0, 0, 0, 0, 3, 0),
                buildAdditions = mutableListOf(null, 2, 3, 3, 0, 1),
                elements = mutableListOf(Element.Dark, Element.Light, Element.Fire)
            ),
            TechniqueEffect(
                data = techniqueDataRecord.table13[5],
                kiBuild = mutableListOf(0, 7, 7, 0, 0, 0),
                buildAdditions = mutableListOf(null, 1, 0, 2, 3, 3),
                elements = mutableListOf(Element.Air, Element.Water, Element.Fire)
            )
        )
    )

    private val summum = PrebuiltTech(
        saveName = "Summum",
        name = R.string.summum,
        description = R.string.summumDesc,
        level = 3,
        maintArray = mutableListOf(0, 0, 0, 0, 0, 0),
        givenAbilities = mutableListOf(
            TechniqueEffect(
                data = techniqueDataRecord.table10[6],
                kiBuild = mutableListOf(0, 17, 0, 0, 8, 0),
                buildAdditions = mutableListOf(null, 0, 2, 1, 3, 3),
                elements = mutableListOf(Element.Air, Element.Water, Element.Dark)
            ),
            TechniqueEffect(
                data = techniqueDataRecord.table13[6],
                kiBuild = mutableListOf(0, 0, 15, 0, 0, 0),
                buildAdditions = mutableListOf(null, 1, 0, 2, 3, 3),
                elements = mutableListOf(Element.Air, Element.Water, Element.Fire)
            )
        )
    )

    private val feuer = PrebuiltTech(
        saveName = "Feuer",
        name = R.string.feuer,
        description = R.string.feuerDesc,
        level = 1,
        maintArray = mutableListOf(1, 0, 0, 0, 0, 1),
        givenAbilities = mutableListOf(
            TechniqueEffect(
                data = techniqueDataRecord.table8[1],
                kiBuild = mutableListOf(2, 0, 0, 0, 0, 0),
                buildAdditions = mutableListOf(0, 3, null, 1, 2, 1),
                elements = mutableListOf(Element.Fire, Element.Earth)
            ),
            TechniqueEffect(
                data = techniqueDataRecord.table32[0],
                kiBuild = mutableListOf(0, 0, 0, 0, 1, 4),
                buildAdditions = mutableListOf(3, 3, null, 2, 0, 1),
                elements = mutableListOf(Element.Fire)
            )
        )
    )

    private val leFeu = PrebuiltTech(
        saveName = "Le Feu",
        name = R.string.leFeu,
        description = R.string.leFeuDesc,
        level = 1,
        maintArray = mutableListOf(0, 0, 0, 0, 0, 0),
        givenAbilities = mutableListOf(
            TechniqueEffect(
                data = techniqueDataRecord.table1[2],
                kiBuild = mutableListOf(1, 3, 0, 0, 4, 0),
                buildAdditions = mutableListOf(2, 0, 2, null, 2, 3),
                elements = mutableListOf(Element.Air, Element.Fire, Element.Dark)
            ),
            TechniqueEffect(
                data = techniqueDataRecord.table20[2],
                kiBuild = mutableListOf(0, 0, 0, 0, 1, 5),
                buildAdditions = mutableListOf(null, 2, 3, 4, 0, 1),
                elements = mutableListOf(Element.Air, Element.Water, Element.Fire)
            )
        )
    )

    private val horecka = PrebuiltTech(
        saveName = "Horecka",
        name = R.string.horecka,
        description = R.string.horeckaDesc,
        level = 2,
        maintArray = mutableListOf(0, 0, 0, 0, 0, 0),
        givenAbilities = mutableListOf(
            TechniqueEffect(
                data = techniqueDataRecord.table7[0],
                kiBuild = mutableListOf(2, 0, 0, 0, 2, 8),
                buildAdditions = mutableListOf(0, 3, null, 2, 1, 1),
                elements = mutableListOf(Element.Light, Element.Water, Element.Earth)
            ),
            TechniqueEffect(
                data = techniqueDataRecord.table21[4],
                kiBuild = mutableListOf(0, 6, 0, 0, 4, 2),
                buildAdditions = mutableListOf(null, 2, 3, 3, 0, 1),
                elements = mutableListOf(Element.Dark, Element.Light, Element.Fire)
            ),
            TechniqueEffect(
                data = TechniqueTableData(
                    name = 37,
                    effectRef = R.string.determinedCondition,
                    effectVal = null,
                    primaryCost = 0,
                    secondaryCost = 0,
                    mkCost = -15,
                    maintCost = 0,
                    level = 1
                ),
                kiBuild = mutableListOf(0, 0, 0, 0, 0, 0),
                buildAdditions = mutableListOf(null, null, null, null, null, null),
                elements = mutableListOf(Element.Free)
            )
        )
    )

    private val vatra = PrebuiltTech(
        saveName = "Vatra",
        name = R.string.vatra,
        description = R.string.vatraDesc,
        level = 2,
        maintArray = mutableListOf(0, 0, 0, 0, 0, 0),
        givenAbilities = mutableListOf(
            TechniqueEffect(
                data = techniqueDataRecord.table1[4],
                kiBuild = mutableListOf(0, 6, 0, 0, 0, 5),
                buildAdditions = mutableListOf(2, 0, 2, null, 2, 3),
                elements = mutableListOf(Element.Air, Element.Fire, Element.Dark)
            ),
            TechniqueEffect(
                data = techniqueDataRecord.table8[4],
                kiBuild = mutableListOf(1, 0, 0, 3, 0, 7),
                buildAdditions = mutableListOf(0, 3, null, 1, 2, 1),
                elements = mutableListOf(Element.Fire, Element.Earth)
            ),
            TechniqueEffect(
                data = techniqueDataRecord.table23[2],
                kiBuild = mutableListOf(0, 0, 0, 0, 6, 0),
                buildAdditions = mutableListOf(1, 2, null, 2, 0, 1),
                elements = mutableListOf(Element.Fire, Element.Earth)
            ),
            TechniqueEffect(
                data = TechniqueTableData(
                    name = 37,
                    effectRef = R.string.determinedCondition,
                    effectVal = null,
                    primaryCost = 0,
                    secondaryCost = 0,
                    mkCost = -10,
                    maintCost = 0,
                    level = 1
                ),
                kiBuild = mutableListOf(0, 0, 0, 0, 0, 0),
                buildAdditions = mutableListOf(null, null, null, null, null, null),
                elements = mutableListOf(Element.Free)
            )
        )
    )

    private val eld = PrebuiltTech(
        saveName = "Eld",
        name = R.string.eld,
        description = R.string.eldDesc,
        level = 3,
        maintArray = mutableListOf(0, 0, 0, 0, 0, 0),
        givenAbilities = mutableListOf(
            TechniqueEffect(
                data = techniqueDataRecord.table8a[1],
                kiBuild = mutableListOf(10, 0, 0, 0, 0, 0),
                buildAdditions = mutableListOf(0, 3, null, 1, 2, 1),
                elements = mutableListOf(Element.Fire, Element.Earth)
            ),
            TechniqueEffect(
                data = techniqueDataRecord.table1[8],
                kiBuild = mutableListOf(0, 10, 0, 0, 0, 19),
                buildAdditions = mutableListOf(2, 0, 2, null, 2, 3),
                elements = mutableListOf(Element.Air, Element.Fire, Element.Dark)
            ),
            TechniqueEffect(
                data = TechniqueTableData(
                    name = 37,
                    effectRef = R.string.determinedCondition,
                    effectVal = null,
                    primaryCost = 0,
                    secondaryCost = 0,
                    mkCost = -30,
                    maintCost = 0,
                    level = 1
                ),
                kiBuild = mutableListOf(0, 0, 0, 0, 0, 0),
                buildAdditions = mutableListOf(null, null, null, null, null, null),
                elements = mutableListOf(Element.Free)
            )
        )
    )

    private val theScales = PrebuiltTech(
        saveName = "The Scales",
        name = R.string.theScales,
        description = R.string.theScalesDesc,
        level = 1,
        maintArray = mutableListOf(0, 0, 0, 0, 0, 0),
        givenAbilities = mutableListOf(
            TechniqueEffect(
                data = techniqueDataRecord.table11[4],
                kiBuild = mutableListOf(0, 3, 1, 3, 0, 0),
                buildAdditions = mutableListOf(null, 1, 0, 1, 3, 3),
                elements = mutableListOf(Element.Light)
            )
        )
    )

    private val theClaws = PrebuiltTech(
        saveName = "The Claws",
        name = R.string.theClaws,
        description = R.string.theClawsDesc,
        level = 1,
        maintArray = mutableListOf(0, 0, 0, 0, 0, 0),
        givenAbilities = mutableListOf(
            TechniqueEffect(
                data = techniqueDataRecord.table9[0],
                kiBuild = mutableListOf(0, 4, 4, 0, 0, 0),
                buildAdditions = mutableListOf(null, 0, 2, 1, 3, 3),
                elements = mutableListOf(Element.Air, Element.Water)
            ),
            TechniqueEffect(
                data = techniqueDataRecord.table8[2],
                kiBuild = mutableListOf(2, 0, 0, 4, 0, 0),
                buildAdditions = mutableListOf(0, 3, null, 1, 2, 1),
                elements = mutableListOf(Element.Fire, Element.Earth)
            )
        )
    )

    private val theFang = PrebuiltTech(
        saveName = "The Fang",
        name = R.string.theFang,
        description = R.string.theFangDesc,
        level = 2,
        maintArray = mutableListOf(0, 0, 0, 0, 0, 0),
        givenAbilities = mutableListOf(
            TechniqueEffect(
                data = techniqueDataRecord.table1[3],
                kiBuild = mutableListOf(0, 5, 0, 0, 0, 0),
                buildAdditions = mutableListOf(2, 0, 2, null, 2, 3),
                elements = mutableListOf(Element.Air, Element.Fire, Element.Dark)
            ),
            TechniqueEffect(
                data = techniqueDataRecord.table17[5],
                kiBuild = mutableListOf(3, 0, 0, 8, 0, 0),
                buildAdditions = mutableListOf(0, 2, null, 2, 1, 2),
                elements = mutableListOf(Element.Dark, Element.Fire)
            ),
            TechniqueEffect(
                data = techniqueDataRecord.table15[2],
                kiBuild = mutableListOf(0, 0, 6, 0, 0, 0),
                buildAdditions = mutableListOf(null, 0, 1, 2, 2, 2),
                elements = mutableListOf(Element.Air)
            )
        )
    )

    private val theTail = PrebuiltTech(
        saveName = "The Tail",
        name = R.string.theTail,
        description = R.string.theTailDesc,
        level = 2,
        maintArray = mutableListOf(0, 0, 0, 0, 0, 0),
        givenAbilities = mutableListOf(
            TechniqueEffect(
                data = techniqueDataRecord.table26[6],
                kiBuild = mutableListOf(2, 0, 0, 8, 0, 0),
                buildAdditions = mutableListOf(0, 3, null, 2, 1, 1),
                elements = mutableListOf(Element.Earth, Element.Fire)
            ),
            TechniqueEffect(
                data = techniqueDataRecord.table1[2],
                kiBuild = mutableListOf(0, 6, 0, 0, 0, 0),
                buildAdditions = mutableListOf(2, 0, 2, null, 2, 3),
                elements = mutableListOf(Element.Air, Element.Fire, Element.Dark)
            ),
            TechniqueEffect(
                data = techniqueDataRecord.table21[3],
                kiBuild = mutableListOf(0, 0, 0, 0, 6, 0),
                buildAdditions = mutableListOf(null, 2, 3, 3, 0, 1),
                elements = mutableListOf(Element.Dark, Element.Light, Element.Fire)
            )
        )
    )

    private val dragonsBreath = PrebuiltTech(
        saveName = "The Dragon's Breath",
        name = R.string.dragonBreath,
        description = R.string.dragonBreathDesc,
        level = 3,
        maintArray = mutableListOf(0, 0, 0, 0, 0, 0),
        givenAbilities = mutableListOf(
            TechniqueEffect(
                data = techniqueDataRecord.table20[7],
                kiBuild = mutableListOf(0, 0, 6, 10, 4, 0),
                buildAdditions = mutableListOf(null, 2, 3, 4, 0, 1),
                elements = mutableListOf(Element.Air, Element.Water, Element.Fire)
            ),
            TechniqueEffect(
                data = techniqueDataRecord.table21[5],
                kiBuild = mutableListOf(0, 10, 4, 0, 2, 0),
                buildAdditions = mutableListOf(null, 2, 3, 3, 0, 1),
                elements = mutableListOf(Element.Dark, Element.Light, Element.Fire)
            ),
            TechniqueEffect(
                data = techniqueDataRecord.table31[0],
                kiBuild = mutableListOf(5, 0, 0, 0, 0, 0),
                buildAdditions = mutableListOf(3, 3, null, 2, 0, 1),
                elements = mutableListOf(Element.Fire, Element.Light, Element.Dark)
            ),
            TechniqueEffect(
                data = techniqueDataRecord.table7[0],
                kiBuild = mutableListOf(10, 0, 0, 0, 0, 0),
                buildAdditions = mutableListOf(0, 3, null, 2, 1, 1),
                elements = mutableListOf(Element.Fire, Element.Earth)
            ),
            TechniqueEffect(
                data = techniqueDataRecord.table38[0],
                kiBuild = mutableListOf(0, 0, 0, 0, 0, 0),
                buildAdditions = mutableListOf(null, null, null, null, null, null),
                elements = mutableListOf(Element.Free)
            )
        )
    )

    //compile prebuilt techniques into a single list
    val allTechniques = listOf(excisumAeris, velocitasVentas, excisumMagister, magnusExacter,
        summum, feuer, leFeu, horecka, vatra, eld, theScales, theClaws, theFang, theTail, dragonsBreath)
}