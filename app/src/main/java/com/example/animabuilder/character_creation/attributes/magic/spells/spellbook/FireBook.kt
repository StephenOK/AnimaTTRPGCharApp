package com.example.animabuilder.character_creation.attributes.magic.spells.spellbook

import com.example.animabuilder.character_creation.Element
import com.example.animabuilder.character_creation.attributes.magic.spells.Spell
import com.example.animabuilder.character_creation.attributes.magic.spells.SpellType
import java.io.Serializable

class FireBook: Serializable {
    val createFire = Spell(
        "Create Fire",
        Element.Fire,
        true,
        2,
        30,
        "This spell creates a fire with an Intensity of 1. The flame is magical and does " +
                "not require any fuel. If placed upon flammable material, the material will " +
                "continue to burn naturally even after the spell has ended.",
        "+1 fire Intensity",
        10,
        10,
        true,
        listOf(SpellType.Effect)
    )

    val putOutFire = Spell(
        "Put Out Fire",
        Element.Fire,
        true,
        6,
        30,
        "This spell reduces heat or fire by 1 Intensity. Bear in mind that some sources " +
                "of fire, such as volcanoes, reproduce themselves automatically when not " +
                "completely extinguished. When cast upon a fire-based being, the creature " +
                "suffers 5 points of damage for every level of diminished intensity - provided " +
                "he fails a MR Check with a Difficulty of 100. Accumulation creatures get 25 " +
                "damage points for each intensity.",
        "Fire reduced by an additional -1 Intensity and +5 to MR Difficulty",
        10,
        null,
        false,
        listOf(SpellType.Effect, SpellType.Spiritual)
    )

    val fireImmunity = Spell(
        "Fire Immunity",
        Element.Fire,
        true,
        10,
        50,
        "This spell grants the spellcaster, or whoever he designates, immunity to 5 levels " +
                "of heat intensity. When attacked from such an element, the Base Damage is " +
                "reduced by 5 points for every level of intensity to which the character is " +
                "immune. In addition, the immune character receives a +5 bonus to Resistances " +
                "against fire for each level of intensity.",
        "+1 Intensity immunity",
        20,
        20,
        true,
        listOf(SpellType.Effect)
    )

    val detectHeat = Spell(
        "Detect Heat",
        Element.Fire,
        true,
        12,
        60,
        "This spell enables the caster to locate any source of heat in a 25 meter radius. " +
                "The caster can feel Intensity level and size of the heat, and can even perceive " +
                "heat coming from warm-blooded creatures, but not from inanimate or immaterial " +
                "beings - such as golems or undead. Living creatures can resist this spell by " +
                "passing a MR Check with a Difficulty of 120.",
        "+10 meters to radius and +10 to the MR Difficulty",
        10,
        10,
        false,
        listOf(SpellType.Detection)
    )

    val fireBall = Spell(
        "Fire Ball",
        Element.Fire,
        true,
        16,
        50,
        "This spell projects a 50 point Base Damage fire attack which explodes in a 5 meter " +
                "radius. The caster can not select targets within the explosion area. The attack " +
                "qualifies as a Heat Attack Type.",
        "+5 meters to radius and +5 to Base Damage",
        20,
        null,
        false,
        listOf(SpellType.Attack)
    )

    val controlFire = Spell(
        "Control Fire",
        Element.Fire,
        true,
        20,
        50,
        "This spell grants the caster control of fire up to 5 levels of Intensity. He can " +
                "alter different aspects of the flames, such as color of flame and smoke they " +
                "produce. If faced with a fire-based creature, the magician can control it if " +
                "the creature fails a MR Check with a Difficulty of 100.",
        "+1 level of fire Intensity and +5 to the MR Difficulty",
        10,
        10,
        true,
        listOf(SpellType.Effect, SpellType.Spiritual)
    )

    val fireBarrier = Spell(
        "Fire Barrier",
        Element.Fire,
        true,
        22,
        50,
        "This spell creates a strong wall of fire. Any character attempting to break through " +
                "the wall receives an automatic attack with a Final Attack Ability of 240 and an " +
                "80 point Base Damage using the Heat Attack Type. Suffering damage does not " +
                "prevent a creature from breaching the wall. The barrier has a maximum height and " +
                "length of 2 meters. In addition, the caster may use the wall as a magic shield " +
                "against attacks based on water, cold, or fire. In this case, the wall takes up " +
                "to 300 damage points before breaking.",
        "+5 to Base Damage, +1 meter to height and length, and +50 Resistance Points",
        10,
        10,
        false,
        listOf(SpellType.Automatic, SpellType.Defense)
    )

    val igneousWeapon = Spell(
        "Igneous Weapon",
        Element.Fire,
        true,
        26,
        50,
        "This spell infuses a weapon with fire, thus upgrading its attack into a Heat " +
                "Attack Type. This eldritch fire increases the weapon's Base Damage by 10 points " +
                "without altering the weapon's Fortitude.",
        "+5 to teh weapon's Base Damage",
        10,
        10,
        false,
        listOf(SpellType.Effect)
    )

    val heatWave = Spell(
        "Heat Wave",
        Element.Fire,
        true,
        30,
        60,
        "This spell projects a powerful high-temperature wave that has a 50 point Base " +
                "Damage using the Heat Attack Type. Since waves are invisible to the human eye, " +
                "the victim must be able to see magic, have thermal vision, or pass an Absurd " +
                "level Notice Check in order to avoid a Blinded penalty against this attack.",
        "+5 to Base Damage",
        10,
        null,
        false,
        listOf(SpellType.Attack)
    )

    val readAshes = Spell(
        "Read the Ashes",
        Element.Fire,
        true,
        32,
        60,
        "This spell transports the caster's senses to the past, endowing him with the " +
                "possibility of seeing the causes of a specific fire. The caster must be present " +
                "in the remains of the fire, but is not aware of reality around him while he is " +
                "performing the spell. The spell allows for a one-hour time regression.",
        "Doubles the regression time",
        20,
        null,
        false,
        listOf(SpellType.Effect)
    )

    val raiseTemperature = Spell(
        "Raise Weather Temperature",
        Element.Fire,
        true,
        36,
        60,
        "This spell causes temperature to rise 30 degrees Celsius in a one kilometer radius. " +
                "The caster may use Added Effects to increase the rise in temperature up to an " +
                "additional +30 degrees, but beyond that, it costs two Added Effects to raise " +
                "the temperature further by +1 degree.",
        "+1 degree and +1500 feet to radius",
        20,
        5,
        true,
        listOf(SpellType.Effect)
    )

    val fireMine = Spell(
        "Fire Mine",
        Element.Fire,
        true,
        40,
        80,
        "This spell creates an igneous mine ready to explode in a 10 meter radius at the " +
                "caster's command. The attack has an 80 point Base Damage using the Heat Attack " +
                "Type. The extent of the potential damage is determined by proximity. If the " +
                "distance between a character and the mine is more than half the explosion area, " +
                "the hapless victim must defend against an attack with a Final Attack Ability of " +
                "240, or against a Final Attack Ability of 280 if the distance is less. At a " +
                "distance of less than one meter from its source, the explosion has a Final " +
                "Attack Ability of 320. The caster may activate the fire mines at any time as " +
                "an active action. The mines affect everything in their radius, including the caster.",
        "+10 meters to radius and +5 to Base Damage",
        20,
        5,
        true,
        listOf(SpellType.Effect)
    )

    val increaseCritical = Spell(
        "Increases Critical",
        Element.Fire,
        false,
        42,
        60,
        "This spell adds a +20 bonus when calculating Critical Levels for specific attacks. " +
                "This spell must be cast before rolling any dice.",
        "+5 to Critical Level",
        10,
        null,
        false,
        listOf(SpellType.Effect)
    )

    val dry = Spell(
        "Dry",
        Element.Fire,
        true,
        46,
        80,
        "This spell immediately dries any wet body in a 5 meter radius. A mage may use " +
                "this spell to harm water elementals. Such creatures must pass a MR Check with " +
                "a Difficulty of 100 or suffer damage equal to twice their Failure Level. Living " +
                "creatures targeted by this spell must pass a MR Check with a Difficulty of 100 " +
                "or suffer damage equal to half their Failure Level.",
        "+5 meters to radius and +5 to MR Difficulty",
        10,
        null,
        false,
        listOf(SpellType.Effect, SpellType.Spiritual)
    )

    val melt = Spell(
        "Melt",
        Element.Fire,
        true,
        50,
        80,
        "This spell heats up any inorganic object the caster desires in a 50 meter radius, " +
                "causing it to reach extremely high temperatures. The intensity of the heat " +
                "causes any object that fails a 60 PhR Check to melt in a number of rounds equal " +
                "to its Fortitude. Anyone in contact with the object in question must get rid " +
                "of it, or they will suffer injury. Injury rolls occur on Table 73.",
        "+10 meters to radius and +5 to MR Difficulty",
        10,
        10,
        false,
        listOf(SpellType.Spiritual)
    )

    val fireBody = Spell(
        "Body of Fire",
        Element.Fire,
        true,
        52,
        100,
        "This spell transforms a designated body into fire. Once transformed, a fire body " +
                "becomes immune to all non-energy-, cold-, or water-based attacks. All elements " +
                "used to extinguish fire (sand, foam, etc) will injure the character, however. " +
                "The target receives a +30 Resistance bonus for heat-based effects as long as " +
                "he remains in this state. Anyone who comes into contact with the flames that " +
                "make up a fire body must pass a PhR Check equal to twice the transformed " +
                "character's Presence or suffer damage equal to half the failure level. If a " +
                "character has fire protection, he receives a +5 bonus for every AT point against " +
                "Heat on that roll. This spell can affect a maximum Presence of 60.",
        "+10 to the maximum Presence affected",
        10,
        10,
        false,
        listOf(SpellType.Effect)
    )

    val vitalSacrifice = Spell(
        "Vital Sacrifice",
        Element.Fire,
        true,
        56,
        120,
        "This spell allows a spellcaster to consume his own vital energy in order to " +
                "increase his physical capabilities whenever he requires. This spell grants the " +
                "Passive ability to temporarily sacrifice Life Points in order to receive a bonus " +
                "to a specific action. For every 5 Life Points thus spent, a character receives a " +
                "+5 to one of his rolls. Creatures with Damage Resistance shall multiply these " +
                "points by their DR multiple. This bonus applies only to physical actions, but it " +
                "can be added to Primary Abilities - such as Attack, Defense, or Magic Projection. " +
                "Even though this is a Passive action, the sacrifice must be declared prior to " +
                "rolling the dice. The spellcaster may also bestow this ability upon another " +
                "individual, in which case is the recipient decides how many points to spend. " +
                "Characters recover sacrificed Life Points at a rate of 10 per day, independently " +
                "of the character's natural Regeneration or any healing spells he might employ. " +
                "The maximum number of Life Points that a character can spend per round is 40. " +
                "Effects do not overlap, and only one spell of this class can be held active upon " +
                "a specific individual.",
        "+10 to the maximum Life Points spent per round",
        20,
        20,
        true,
        listOf(SpellType.Effect)
    )

    val incinerate = Spell(
        "Incinerate",
        Element.Fire,
        true,
        60,
        100,
        "This spell calls fire down on any target within a 50 meter radius area that the " +
                "caster designates. The spellcaster rolls the dice to attack every target he " +
                "intends to incinerate, adding a +100 bonus on Table 73: In Flames. Creatures can " +
                "resist this spell by passing a MR Check with a Difficulty of 140.",
        "+10 to Table 73 result, +5 to MR Difficulty and +25 meters to radius",
        10,
        10,
        false,
        listOf(SpellType.Automatic)
    )

    val consumeEssence = Spell(
        "Consume Essence",
        Element.Fire,
        true,
        62,
        120,
        "This spell creates a terrifying supernatural aura around the spellcaster that can " +
                "destroy the vital essence of people, consuming their energy and preventing them " +
                "from healing by natural means. Anyone in a 10 meter radius must pass a MR Check " +
                "with a Difficulty of 120 or suffer damage to Life Points and Zeon equal to their " +
                "Failure. Life Points lost through this spell can not be recovered naturally; " +
                "healing magic is required restore the health. Spellcasters can only recover lost " +
                "Zeon by absorbing it from other spellcasters or from magic containers.",
        "+5 meters to radius and +5 to MR Difficulty",
        20,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    val powerSacrifice = Spell(
        "Power Sacrifice",
        Element.Fire,
        true,
        66,
        120,
        "This spell allows the spellcaster to consume his own magical energy in order to " +
                "increase his Magic Accumulation. It grants the caster the Passive ability of " +
                "spending Zeon points in order to receive a temporary bonus to MA. For every " +
                "10 points spent with this purpose, the character receives a +5 bonus on his MA " +
                "until the end of the turn. The maximum number of Zeon points a character can " +
                "sacrifice per round is 20. Effects do not overlap, and only one spell of this " +
                "class can be held active upon a specific individual.",
        "+5 to the maximum Zeon points sacrificed per round",
        20,
        20,
        true,
        listOf(SpellType.Effect)
    )

    val directCritical = Spell(
        "Direct Critical",
        Element.Fire,
        true,
        70,
        100,
        "This spell causes an internal explosion in an individual, resulting in an " +
                "automatic Level 120 Critical - although the target can counteract it with his " +
                "PhR as the general rules dictate. A target can resist this spell by passing a " +
                "MR with a Difficulty of 140.",
        "+10 to Critical Level and +5 to MR Difficulty",
        10,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    val magicCapacities = Spell(
        "Magic for Capacities",
        Element.Fire,
        true,
        72,
        120,
        "This spell allows a spellcaster to consume his own Zeon in order to temporarily " +
                "increase his Characteristics and dependant abilities. This spell grants the " +
                "caster the Passive ability to gain a +1 to one of his Characteristics (until " +
                "the end of the turn) for every 20 Zeon points he sacrifices. The maximum " +
                "number of Zeon points a character can sacrifice per round is 40. Effects do " +
                "not overlap, and only one spell of this class can be held active upon a specific " +
                "individual.",
        "+10 to the maximum Zeon points sacrificed per turn",
        20,
        20,
        true,
        listOf(SpellType.Effect)
    )

    val fireStorm = Spell(
        "Fire Storm",
        Element.Fire,
        true,
        76,
        150,
        "The caster selects an area within which a heavy fire storm appears, burning " +
                "everything inside it to the ground. Every turn, individuals encompassed within " +
                "the area will suffer an automatic attack with a Final Attack Ability of 240 " +
                "using the Heat Attack Type. This attack possesses a Base Damage of 100. The " +
                "storm area has a maximum 25 meter radius and affects all within, including " +
                "the caster. This spell remains stationary.",
        "+25 meters to radius",
        20,
        10,
        false,
        listOf(SpellType.Automatic)
    )

    val lifeForMagic = Spell(
        "Consume Life for Magic",
        Element.Fire,
        true,
        80,
        120,
        "This spell allows the spellcaster to consume his own vital energy in order to " +
                "increase his magic reserve. This spell grants the Passive Ability to temporarily " +
                "sacrifice Life Points to recover Zeon. For every 5 LP sacrificed, the caster " +
                "receives 50 Zeon points to spend. Damage Resistance creatures will increase this " +
                "number according to their DR Multiple. Life Points lost through this spell " +
                "recovers at a rate of 10 points per day, regardless of the character's natural " +
                "Regeneration or any healing spells he might employ. The maximum number of Life " +
                "Points a character may sacrifice per round is 20. Effects do not overlap, and " +
                "only one spell of this class can be held active upon a specific individual.",
        "+10 to the maximum number of Life Points sacrificed per round",
        20,
        20,
        true,
        listOf(SpellType.Effect)
    )

    val createIfreet = Spell(
        "Create Ifreet",
        Element.Fire,
        true,
        82,
        250,
        "This spell creates a live fire creature completely under the magician's control. " +
                "This entity is developed as a Being Between Worlds, subject to the powers and " +
                "limitations of Fire elementals in Chapter 26. The creature has 600 DP, and its " +
                "maximum level is calculated using the same rules as in the spell Create Being " +
                "from the Path of Creation.",
        "+50 DP",
        30,
        5,
        true,
        listOf(SpellType.Effect)
    )

    val pyre = Spell(
        "Pyre",
        Element.Fire,
        true,
        86,
        250,
        "This spell produces 10 levels of fire intensity. The created fire by this spell " +
                "continues to burn without consuming anything for the duration of the spell. If " +
                "set on flammable material, it burns naturally after the spell has ended.",
        "+5 levels of fire intensity",
        20,
        10,
        true,
        listOf(SpellType.Effect)
    )

    val devastation = Spell(
        "Devastation",
        Element.Fire,
        true,
        90,
        200,
        "This spell creates a 200 point Base Damage fire explosion in a 500 meter radius. " +
                "Even though this spell can damage energy, it uses the Heat Attack Type. The " +
                "caster can not select targets within the explosion area.",
        "+500 meters to radius",
        30,
        null,
        false,
        listOf(SpellType.Attack)
    )

    val sacrificingOthers = Spell(
        "Sacrificing Others",
        Element.Fire,
        true,
        92,
        250,
        "This spell binds other individuals' essence and vital force to the caster, " +
                "enabling him to consume them for his own advantage. In gaming terms, it allows " +
                "the entity to use the Life Points and Zeon of those affected by the spell, for " +
                "sacrifice spells: Vital Sacrifice, Magic for Capacities, Power Sacrifice, and " +
                "Consume Life for Magic. This spell has a one kilometer permanent area within " +
                "which the caster is free to select as many targets as he wishes. MR to resist " +
                "the spell is 120. The affected parties will be entitled to a new roll every day, " +
                "or at the time their energy is being consumed.",
        "+500 meters to radius and +5 to MR Difficulty",
        20,
        5,
        true,
        listOf(SpellType.Effect, SpellType.Spiritual)
    )

    val lordOfFire = Spell(
        "Lord of Fire",
        Element.Fire,
        true,
        96,
        300,
        "The caster controls all heat nuclei in a 300 kilometer radius. This dominion " +
                "extends control over planetary magma, thus allowing the spellcaster to produce " +
                "volcanic eruptions. All fire based creatures within the spell area are subject " +
                "to the spellcaster's control unless they pass a MR with a Difficulty of 140. " +
                "Affected creatures are entitled to a new roll only if they alter their base " +
                "Resistance. A creature that passes the check need not reroll unless its base " +
                "Resistance decreases.",
        "+60 miles to radius and +5 to MR Difficulty",
        30,
        10,
        true,
        listOf(SpellType.Automatic)
    )

    val armageddon = Spell(
        "Armageddon",
        Element.Fire,
        true,
        100,
        450,
        "The definitive fire spell allows the caster to consume a portion of the world's " +
                "very essence, incinerating everything, including souls. Armageddon inhibits any " +
                "form of existence in a 10 kilometer radius. Any and all organic or inorganic " +
                "entities inside the area must pass a MR Check with a Difficulty of 140 every " +
                "minute to avoid being physically and spiritually incinerated. No life form can " +
                "be born or created in the spell's zone of influence for as long as the spell " +
                "is maintained.",
        "+2 miles to radius and +5 to MR Difficulty",
        30,
        5,
        true,
        listOf(SpellType.Automatic)
    )
}