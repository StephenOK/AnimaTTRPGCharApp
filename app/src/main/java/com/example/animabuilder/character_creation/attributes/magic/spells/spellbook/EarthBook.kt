package com.example.animabuilder.character_creation.attributes.magic.spells.spellbook

import com.example.animabuilder.character_creation.Element
import com.example.animabuilder.character_creation.attributes.magic.spells.Spell
import com.example.animabuilder.character_creation.attributes.magic.spells.SpellType

/**
 * List of spells associated with the earth element.
 */
class EarthBook{
    private val detectMinerals = Spell(
        "Detect Minerals",
        Element.Earth,
        true,
        2,
        20,
        "The caster can detect a particular mineral\'s location in a 10-meter radius. The " +
                "spell also gives an approximation of the size and purity of the source. Energy-" +
                "based barriers can not be overcome.",
        "+5 meters to radius",
        10,
        null,
        false,
        listOf(SpellType.Detection)
    )

    private val mineralControl = Spell(
        "Mineral Control",
        Element.Earth,
        true,
        6,
        30,
        "This spell enables the caster to move, reshape, and control any mineral substance " +
                "with a Presence of 30 or lower. However, the caster cannot endow a mineral with " +
                "capacities it does not already possess. In other words, the caster may reshape a " +
                "pebble into an arrowhead, but he could not make it inflict Electrical damage. " +
                "Some golems and stone elementals can be controlled with this spell. Mineral-" +
                "based creatures may avoid the effect of this spell by passing a MR Check with " +
                "a Difficulty of 100. The control check must be repeated immediately if the " +
                "creature is given an order completely opposed to its nature.",
        "+10 to the maximum Presence affected and +5 to MR Difficulty",
        10,
        10,
        true,
        listOf(SpellType.Effect, SpellType.Spiritual)
    )

    private val weightIncrement = Spell(
        "Weight Increment",
        Element.Earth,
        true,
        10,
        40,
        "This spell increases a physical body\'s weight by 20 kg.",
        "+10 kg",
        30,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val transformMineral = Spell(
        "Transform Mineral",
        Element.Earth,
        false,
        12,
        40,
        "This spell changes one type of material into another, modifying its natural " +
                "composition. It can affect rocks and metals with a base Presence not higher than " +
                "30. For instance, it can turn a piece of limestone into a gold nugget. It can " +
                "transform up to 10 kilograms of material.",
        "+5 to the maximum Presence affected and +10 kilograms",
        10,
        20,
        false,
        listOf(SpellType.Effect)
    )

    private val firmness = Spell(
        "Firmness",
        Element.Earth,
        true,
        16,
        50,
        "This spell enhances the endurance of individuals or objects, making them more " +
                "resistant to damage. When cast upon a living organism, it grants a +20 bonus to " +
                "any PhR Check to avoid the effects of a Critical. On the other hand, when " +
                "applied to an object with Fortitude, it increases it by +2. Each Firmness spell " +
                "can affect only one body or object at a time.",
        "+5 to PhR or +1 to Fortitude",
        10,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val stoneBarrier = Spell(
        "Stone Barrier",
        Element.Earth,
        false,
        20,
        60,
        "This spell raises a material barrier allowing the spellcaster to repel any damaging " +
                "attacks including those based on Energy. However, this shield is unable to " +
                "stop Spiritual effects that only call for MR or PsR. The spell can take up to " +
                "600 points before being broken, but it has a Damage Barrier of 60 against " +
                "physical attacks.",
        "+100 Resistance Points and +5 to Damage Barrier",
        20,
        20,
        false,
        listOf(SpellType.Defense)
    )

    private val slowness = Spell(
        "Slowness",
        Element.Earth,
        true,
        22,
        60,
        "This spell decreases motion and reaction speed of the selected individual. If the " +
                "affected character does not pass a MR Check with a Difficulty of 120, his " +
                "Initiative decreases by 50 points and a -2 penalty is applied to his Movement Value.",
        "+5 to MR Difficulty, -5 to Initiative and -1 to Movement Value",
        10,
        10,
        false,
        listOf(SpellType.Spiritual)
    )

    private val shell = Spell(
        "Shell",
        Element.Earth,
        true,
        26,
        80,
        "This spell creates a physical shell with an AT 2 against all kinds of attacks, " +
                "except Energy-based attacks. Even though it is considered armor, no penalties " +
                "are applied to the turn Initiative for using extra protection layers.",
        "+1 to AT",
        10,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val magneticShield = Spell(
        "Magnetic Shield",
        Element.Earth,
        false,
        30,
        50,
        "By controlling magnetic fields around him, the spellcaster raises a shield with the " +
                "ability of repelling any attack of a metallic nature made against him, including " +
                "bullets, arrows, and darts with metal tips. The shield\'s magnetism causes a " +
                "distortion that results in a -50 penalty to the attacker\'s offensive ability. " +
                "The shield will take up to 300 damage points before being broken, but it will only " +
                "be affected by Energy-damaging attacks. This barrier is virtually useless against " +
                "immaterial or Spiritual attacks.",
        "+100 Resistance points",
        10,
        10,
        false,
        listOf(SpellType.Defense)
    )

    private val passThroughMatter = Spell(
        "Pass Through Solid Matter",
        Element.Earth,
        true,
        32,
        80,
        "This spell enables one or more subjects, as appointed by the spellcaster, to pass " +
                "through solid objects. The recipients of the spell do not exactly become " +
                "immaterial, as they are still affected by heat or cold, but they can completely " +
                "ignore all things non-Energy based. In this way, characters can decide to pass " +
                "through anything from walls to sword blades as if they did not exist. It is " +
                "possible for the caster to determine what materials can be passed through and " +
                "which cannot. The maximum total Presences that can be affected may not exceed 100.",
        "+10 to the maximum Presence",
        10,
        20,
        false,
        listOf(SpellType.Effect)
    )

    private val earthSpike = Spell(
        "Earth Spike",
        Element.Earth,
        true,
        36,
        80,
        "Giant stone spikes erupt from the ground and impale targets on the surface. The " +
                "spell allows for a maximum of two spikes, each with a 60 point Base Damage in " +
                "the Thrust Attack Type; they cannot affect immaterial beings, or those only " +
                "damaged by Energy, unless Earth Spike is combined with an Enchant spell. Each " +
                "spike may be used to attack the same, or different targets. This spell cannot be " +
                "employed to perform a spell clash.",
        "One additional spike",
        10,
        null,
        false,
        listOf(SpellType.Attack)
    )

    private val breakage = Spell(
        "Breakage",
        Element.Earth,
        true,
        40,
        60,
        "This magically increases a targeted object\'s or weapon\'s breakage by four points.",
        "+1 to the object\'s Breakage",
        10,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val telemetry = Spell(
        "Telemetry",
        Element.Earth,
        true,
        42,
        120,
        "This spell allows the caster to read the story of an object or person he comes " +
                "into contact with, including the most important events in which it has been " +
                "involved during the past year. Individuals have the chance of repelling the " +
                "spell if they pass a MR with a Difficulty of 80.",
        "+5 to MR Difficulty and an additional year",
        20,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val magneticControl = Spell(
        "Magnetic Control",
        Element.Earth,
        true,
        46,
        100,
        "This spell grants control over the surrounding magnetic fields in a 25-meter " +
                "radius, allowing the caster to freely move any metallic body with a force " +
                "equivalent to Strength 13. Magnetism control is such that actions are executed " +
                "as an automatic effect on metal. For instance, a character using this spell " +
                "could paralyze someone on a full armor, or snatch the sword out of his hands. " +
                "In these cases, it is not possible to avoid such effects by passing an Opposed " +
                "Strength or Agility Check. This control is an active action, therefore the " +
                "caster must have the action in order to perform it, and consequently this " +
                "cannot be used as a defense. Against objects or creatures only partially " +
                "composed of metals, or those that are Energy shielded, the control is " +
                "reduced to Strength 8.",
        "+25 meters to radius",
        20,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val forge = Spell(
        "Forge",
        Element.Earth,
        true,
        50,
        160,
        "This spell employs magic with the purpose of forging objects, using the " +
                "equivalent of a Forging ability of 100. Since the action is based on " +
                "supernatural powers, none of the time modifiers in Table 17 apply, and no " +
                "forging equipment is required. This spell does not produce the material, such as " +
                "steel, needed for creating specific items; the spellcaster must provide them.",
        "+5 to Forging ability",
        30,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val solidBody = Spell(
        "Solid Body",
        Element.Earth,
        true,
        52,
        100,
        "The targeted body becomes immensely solid with stone-like resistance. When cast " +
                "upon individuals, they receive a natural AT of 6 against non-Energy Attacks and " +
                "a Damage Barrier of 100. The character\'s muscles strengthen for as long as he " +
                "remains in this state, increasing his Strength characteristic by 2 and " +
                "decreasing his Movement Value by 2. The transformed body resembles a different " +
                "material depending on the additions employed in the spell; iron, granite, " +
                "steel, diamonds, etc. The maximum Presence that can be affected is 100.",
        "+10 to the maximum Presence affected, +1 to natural AT and +10 to Damage Barrier",
        10,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val resistanceSpell = Spell(
        "Resistance",
        Element.Earth,
        true,
        56,
        100,
        "This spell confers the temporary ability to absorb almost any damage inflicted on " +
                "an individual. It provides 500 additional Life Points, which allow the targeted " +
                "individual to use the defense rules of Damage Resistance beings. These extra LP " +
                "are subtracted before any of the target\'s actual LP. Its Armor Type depends on " +
                "the character\'s size, as it is described in Chapter 26. The target of this " +
                "spell cannot use any defense abilities for as long as the spell is maintained. " +
                "Resistance spells affect only one individual at a time.",
        "+50 extra LP",
        20,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val petrify = Spell(
        "Petrify",
        Element.Earth,
        true,
        60,
        140,
        "This spell transforms a physical being into a stone statue. The targeted " +
                "individual cannot move and has no awareness of the events going on around him " +
                "for the duration of the spell. This spell can be maintained for years, but the " +
                "affected being does not age. As soon as the spell ends, the individual returns " +
                "to his original condition. Any damage or breakage inflicted upon the statue may " +
                "result in damage, or even death, of the actual character. The target of this " +
                "spell can resist its affects by passing a MR Check of 120 Difficulty. A subject " +
                "affected by a Petrify spell is entitled to a reroll after the first day he has " +
                "been affected, and later, once a week.",
        "+5 to MR Difficulty",
        20,
        20,
        true,
        listOf(SpellType.Spiritual)
    )

    private val fissure = Spell(
        "Fissure",
        Element.Earth,
        true,
        62,
        150,
        "This spell causes a violent, but highly concentrated tremor that splits open the " +
                "earth, creating a fissure 3 meters wide, 10 meters long, and 20 - 50 meters deep. " +
                "Individuals in the area of the fissure must pass an Agility Check to avoid " +
                "falling inside and suffering the appropriate impact damage. Constructions in " +
                "the area of the fissure can also e severely damage, but any structure with a " +
                "Damage Barrier higher than 40 is not be affected by this spell, since their " +
                "structure is too dense.",
        "+3 meters long, +1 meter wide, and +5 to Damage Barrier of constructions affected",
        20,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val reverseGravity = Spell(
        "Reverse Gravity",
        Element.Earth,
        true,
        66,
        200,
        "This spell completely alters gravity in a particular area of the planet, reversing " +
                "its force. In a way, it turns the world upside down. Everything encompassed " +
                "within a 25-meter radius immediately starts to \"fall\" into the sky up to a " +
                "maximum distance of 50 meters. The caster may set limits on this spell, such as " +
                "only affecting the interior of a designated structure. Naturally, objects rooted " +
                "or otherwise fixed to the ground will not fall. Individuals may avoid the " +
                "effects of the spell by passing a MR Check with a Difficulty of 120. The area of " +
                "effect remains stationary.",
        "+10 meters to radius and height",
        20,
        5,
        true,
        listOf(SpellType.Automatic)
    )

    private val mineralCreation = Spell(
        "Mineral Creation",
        Element.Earth,
        true,
        70,
        120,
        "The spellcaster may use this spell to create anything he wishes, provided it is " +
                "composed of minerals or metal. The created object cannot have a Presence higher " +
                "than 40, and it must appear in a logical location according to its nature.",
        "+5 to the maximum Presence of the created object",
        20,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val terrainErudition = Spell(
        "Terrain Erudition",
        Element.Earth,
        true,
        72,
        120,
        "The caster gains immediate and total knowledge of everything in contact with the " +
                "ground for several kilometers around him. Both constructions and living " +
                "creatures can be detected straight away, provided they are not immaterial. This " +
                "spell does not affect energy-sealed places. Terrain Erudition covers a 400-meter " +
                "radius around the caster.",
        "+200 meters to radius",
        30,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val earthquake = Spell(
        "Earthquake",
        Element.Earth,
        true,
        76,
        150,
        "This spell causes a devastating tremor in a 500-meter radius with enough potential " +
                "force to destroy a city. Any construction with a Damage Barrier lower than 40 is " +
                "to be immediately destroyed, while the rest suffer 5 points of damage in the " +
                "first round; this damage is doubled every subsequent round (10 in the second, " +
                "20 in the third, etc). Constructions with a Damage Barrier higher than 150 are " +
                "not affected by the earthquake at all. All individuals inside the spell zone " +
                "suffer the appropriate effects from falling debris, depending on their surroundings.",
        "+250 meters to radius",
        20,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val gravityDestruction = Spell(
        "Gravity Destruction",
        Element.Earth,
        true,
        80,
        180,
        "This spell creates a high pressure gravity bubble that can trap any physical entity " +
                "and damage it to the point it bursts. Trapped creatures must pass a PhR with a " +
                "Difficulty of 180 every round, or be subject ot damage equal to half the failure " +
                "level. The victim receives a +5 bonus to the PhR roll for every AT point against " +
                "Impact. The extreme pressure prevents the target from escaping the area of effect " +
                "unless it passes an Opposed Check against a Strength of 16. The power of gravity " +
                "is so strong that even immaterial beings are partially affected by it, although " +
                "they can apply a +40 bonus to their Resistance Checks and +6 to Strength. The " +
                "spell affects everything in a 20-meter radius except for the caster. The area " +
                "of effect remains stationary.",
        "+5 meters to radius",
        20,
        10,
        false,
        listOf(SpellType.Automatic)
    )

    private val createGolem = Spell(
        "Create Golem",
        Element.Earth,
        true,
        82,
        250,
        "This spell creates a living earthly creature completely under the caster\'s control. " +
                "This entity is treated as a Being Between Worlds and is subject to the elemental " +
                "powers and limitations of Earth elementals covered in Chapter 26. The creature " +
                "has 600 DP, and its maximum level is calculated using the same rules as in the " +
                "spell Create Being from the Path of Creation.",
        "+50 DP",
        30,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val gravityIncrement = Spell(
        "Gravity Increment",
        Element.Earth,
        true,
        86,
        200,
        "The spell increases the atmosphere\'s mass in a particular area, thus augmenting " +
                "the pull of gravity and with it, the weight of everything contained in the " +
                "zone. In gaming terms, it automatically doubles the weight of everything in a " +
                "500-meter radius.",
        "Increase the multiplier by one",
        20,
        10,
        true,
        listOf(SpellType.Automatic)
    )

    private val meteor = Spell(
        "Meteor",
        Element.Earth,
        true,
        90,
        200,
        "This spell allows the caster to call down a small meteor from the heavens that " +
                "smashes into the earth with devastating force. Everything within 10 meters of the " +
                "meteor\'s point of impact suffer a Base Damage of 100 points against the Impact " +
                "AT, plus an additional 100 points of damage against the Heat AT. Everything " +
                "10 - 50 meters from the point of impact suffers the effects of the expansion " +
                "wave, which immediately hits with the force of a Strength 14 melee attack and " +
                "inflicts a Base Damage of 60 points. Any point in space the caster can see is " +
                "considered a valid target for this spell. The meteor takes 1d10+4 rounds to " +
                "impact after the spell has been cast. Each additional meteor, made possible " +
                "through the spell\'s Added Effect, strikes 1d10 rounds after the first. The " +
                "caster can direct each meteor at a different target, but all targets must be " +
                "designated at the time the spell is cast and cannot later be changed, even if " +
                "the meteors have not yet impacted.\nThis spell does not take full effect when " +
                "cast indoors, or in subterranean spaces, so its consequences may be altered " +
                "depending on the environment.",
        "One additional meteor",
        20,
        null,
        false,
        listOf(SpellType.Attack)
    )

    private val gravityControl = Spell(
        "Gravity Control",
        Element.Earth,
        true,
        92,
        350,
        "This spell grants complete control of all gravitational forces in a particular area of " +
                "the planet within a 100-kilometer radius of the caster. Inside this area, the " +
                "spellcaster has absolute dominion of gravity, allowing him to increase or " +
                "decrease its affects up to ten times its original value, or reverse it. " +
                "Everything in the area is automatically affected.",
        "+500 meters to radius",
        40,
        5,
        true,
        listOf(SpellType.Automatic)
    )

    private val oneWithEarth = Spell(
        "One with the Earth",
        Element.Earth,
        true,
        96,
        300,
        "The spellcaster merges his essence with the world, obtaining complete control of " +
                "all mineral and metallic elements in a 100-kilometer radius. This allows him to " +
                "alter the face of the earth by creating or removing mountains and valleys, " +
                "redirecting rivers, and so forth. Every stone-based creature within the spell\'s " +
                "area is required to pass a MR with a Difficulty of 140 in order to avoid being " +
                "subject to the spellcaster\'s will. Once passed, no rerolls are necessary. The " +
                "affected parties are entitled to a second roll only when they have altered " +
                "their base Resistance.",
        "+100 kilometers to radius and +5 MR Difficulty",
        30,
        5,
        true,
        listOf(SpellType.Automatic)
    )

    private val atomicControl = Spell(
        "Atomic Control",
        Element.Earth,
        true,
        100,
        450,
        "The caster is able to mold atomic matter in his surroundings like clay. To him, " +
                "everything that moves is nothing but a set of atoms to manipulate as he wishes. " +
                "He gains full dominion of all organic and inorganic matter within a 100-meter " +
                "radius that does not pass a PhR or MR with a Difficulty of 140. There is no " +
                "limit to what the caster can do with a body or object under his dominion; he may " +
                "alter its shape and appearance, transform into anything else, or simply scatter " +
                "its atoms, thereby causing its complete destruction. Since this spell only " +
                "affects physical matter, it has no effect upon souls or completely immaterial bodies.",
        "+5 meters to radius, +5 to MR or PhR Difficulty",
        40,
        10,
        false,
        listOf(SpellType.Automatic)
    )

    val fullBook = listOf(
        detectMinerals,
        null,
        mineralControl,
        null,
        weightIncrement,
        transformMineral,
        null,
        firmness,
        null,
        stoneBarrier,
        slowness,
        null,
        shell,
        null,
        magneticShield,
        passThroughMatter,
        null,
        earthSpike,
        null,
        breakage,
        telemetry,
        null,
        magneticControl,
        null,
        forge,
        solidBody,
        null,
        resistanceSpell,
        null,
        petrify,
        fissure,
        null,
        reverseGravity,
        null,
        mineralCreation,
        terrainErudition,
        null,
        earthquake,
        null,
        gravityDestruction,
        createGolem,
        null,
        gravityIncrement,
        null,
        meteor,
        gravityControl,
        null,
        oneWithEarth,
        null,
        atomicControl
    )
}