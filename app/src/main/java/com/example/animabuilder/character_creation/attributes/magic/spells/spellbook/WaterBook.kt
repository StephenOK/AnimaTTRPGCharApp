package com.example.animabuilder.character_creation.attributes.magic.spells.spellbook

import com.example.animabuilder.character_creation.Element
import com.example.animabuilder.character_creation.attributes.magic.spells.Spell
import com.example.animabuilder.character_creation.attributes.magic.spells.SpellType
import java.io.Serializable

/**
 * List of spells associated with the water element.
 */
class WaterBook: Serializable {
    private val spring = Spell(
        "Spring",
        Element.Water,
        true,
        2,
        30,
        "This spell draws forth any nearby underground current or stream, causing a spring " +
                "to flow from the spot designated by the caster. The spell affects natural liquids " +
                "within 100 meters of the character casting the spell, but it cannot overcome " +
                "Energy barriers.",
        "+30 meters to radius",
        20,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val createChill = Spell(
        "Create Chill",
        Element.Water,
        true,
        6,
        30,
        "This spell creates one ice or cold intensity. The temperature will remain stable " +
                "for as long as the mage maintains the spell.",
        "+1 cold or ice intensity",
        10,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val aquaticCapability = Spell(
        "Aquatic Capability",
        Element.Water,
        true,
        10,
        50,
        "This spell grants its recipient the ability to breath and move freely in an " +
                "aquatic environment. The target of the spell can use his maximum Movement " +
                "Value, breathe liquids, and resist any amount of pressure while underwater. " +
                "The caster may apply this spell to as many individuals as he wishes, as long as " +
                "their total Presence scores are not above 50.",
        "+10 to maximum Presence affected",
        20,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val coldImmunity = Spell(
        "Cold Immunity",
        Element.Water,
        true,
        12,
        50,
        "This spell grants the recipient immunity to 5 Intensities of cold. When suffering " +
                "an attack based on that element, every level of Intensity to which the character " +
                "is immune decreases the attack\'s Base Damage by 5 points and gives the immune " +
                "character +5 to Resistance Checks against cold effects.",
        "Immune to additional Intensity of cold",
        20,
        20,
        true,
        listOf(SpellType.Effect)
    )

    private val protectionBubble = Spell(
        "Protection Bubble",
        Element.Water,
        false,
        16,
        40,
        "This spell creates a bubble of magical energy around the caster that stops all " +
                "attacks with a Base Damage of less than 40. An attack with a Base Damage of 40 " +
                "or higher destroys the bubble.",
        "+10 to Base Damage",
        10,
        20,
        false,
        listOf(SpellType.Defense)
    )

    private val waterImpact = Spell(
        "Water Impact",
        Element.Water,
        true,
        20,
        50,
        "The spellcaster unleashes an offensive burst of water. Although this attack uses " +
                "the Impact Table, it can damage Energy. The spell\'s Base Damage is 40 points. " +
                "However, it also hits the target with an additional impact of Strength 8. A " +
                "character can raise the impact Strength by using an Added Effect. If the impact " +
                "Strength increases to 12 in this way, it will cost two Added Effects to increase " +
                "the impact Strength by 1 point thereafter.",
        "+5 to damage and +1 to impact Strength",
        10,
        null,
        false,
        listOf(SpellType.Attack)
    )

    private val liquidControl = Spell(
        "Liquid Control",
        Element.Water,
        true,
        22,
        60,
        "This spell grants the caster complete control of 5 liters of liquid mass. The " +
                "mage can alter the physical characteristics (such as color and clarity) of the " +
                "liquid, as well as modify its mass density. The liquid mass can even move on " +
                "its own. When this spell is cast against a water elemental, the magician can " +
                "control the creature if it does not pass a MR Check with a Difficulty of 100. " +
                "Elementals will receive an additional MR Check if their controller gives them " +
                "an order in direct opposition to their nature. They also receive a MR Check " +
                "each day.\nThis spell can also manipulate the bloodstream in living organisms. " +
                "However, victims receive a +40 to their MR or PhR Check in such cases. " +
                "Bloodstream manipulation allows the spellcaster to cause an All Action Penalty " +
                "and damage per round equivalent to half the failure level. Victims of " +
                "bloodstream manipulation can make an additional MR Check to free themselves " +
                "from the mage\'s influence every 5 Combat Turns.",
        "Doubles the amount of liquid that can be affected and +5 to MR Difficulty",
        10,
        10,
        true,
        listOf(SpellType.Spiritual)
    )

    private val freezeEmotions = Spell(
        "Freeze Emotions",
        Element.Water,
        true,
        26,
        60,
        "This spell freezes a person\'s feelings, granting him psychological immunity to " +
                "all emotional states. The caster may apply this to as many individuals as he " +
                "wishes, as long as their total Presence is not above 50.",
        "+10 to maximum Presence affected",
        10,
        20,
        false,
        listOf(SpellType.Effect)
    )

    private val controlCold = Spell(
        "Control Cold",
        Element.Water,
        true,
        30,
        50,
        "This spell grants the caster an ability to control the equivalent of 5 Intensities " +
                "of cold or ice. Control Cold empowers the caster to alter potency to half its " +
                "value, changing the shape or even sculpting it as he pleases. When this spell " +
                "is directed at a being made from cold or ice, the spellcaster can control it - " +
                "provided the creature fails a MR Check with a Difficulty of 100. A creature " +
                "controlled in this way can make a new MR Check each day or when receiving " +
                "commands completely contrary to its nature.",
        "+1 controllable Intensity of cold or ice and +5 to MR target Difficulty",
        10,
        10,
        true,
        listOf(SpellType.Spiritual)
    )

    private val freeze = Spell(
        "Freeze",
        Element.Water,
        true,
        32,
        60,
        "This spell inflicts intense Cold on one individual or several people at once, " +
                "freezing their bodies while enclosing them in ice prisons. Freeze has a 5-" +
                "meter radius and a MR Difficulty of 120 (140 when concentrated upon a single " +
                "opponent). The consequences of the spell will vary depending upon the victim\'s " +
                "failure level. A difference of less than 20 points will produce Minor Paralysis, " +
                "less than 80 points will produce Partial Paralysis, and a difference equal to " +
                "or greater than 80 points will produce Total Paralysis.",
        "+5 meters to radius and +5 to the MR target",
        10,
        10,
        true,
        listOf(SpellType.Spiritual)
    )

    private val iceScreen = Spell(
        "Ice Screen",
        Element.Water,
        false,
        36,
        60,
        "This spell forms an ice barrier that offers protection from any source of attack. " +
                "Whenever the shield successfully repels a Light- or Darkness-based Energy attack, " +
                "it can reflect that attack using the same Final Attack Ability as the original " +
                "strike. The ice shield can take up to 400 points of damage before breaking.",
        "+100 Damage Points",
        20,
        20,
        false,
        listOf(SpellType.Defense)
    )

    private val createLiquids = Spell(
        "Create Liquids",
        Element.Water,
        true,
        40,
        80,
        "This spell produces 10 liters of water or a similar liquid substance. In the " +
                "cases where a substance\'s Presence is higher than water, the GM may reduce the " +
                "quantity as he sees fit. Mystical liquids can not be created using this spell.",
        "Double the amount of liquid created",
        20,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val iceAttack = Spell(
        "Ice Attack",
        Element.Water,
        true,
        42,
        80,
        "This spell releases a strong ice attack that can damage Energy. The spellcaster " +
                "can decide whether to attack on the Cold or Thrust AT. Either way, the spell has " +
                "a 100-point Base Damage.",
        "+5 to Base Damage",
        20,
        null,
        false,
        listOf(SpellType.Attack)
    )

    private val crystallization = Spell(
        "Crystallization",
        Element.Water,
        true,
        46,
        80,
        "This spell turns a body into crystal, rendering it fragile and brittle The " +
                "targeted individual must pass a MR or PhR Check with a Difficulty of 140 to " +
                "stop his whole body from freezing and being subject to immediate Minor " +
                "Paralysis. In addition, all damage received will automatically be a Critical, " +
                "regardless of its value. Creatures with Damage Resistance won\'t suffer automatic " +
                "Criticals, but this spell does render their entire body a vulnerable spot.",
        "+5 to MR Difficulty",
        10,
        20,
        false,
        listOf(SpellType.Spiritual)
    )

    private val reflectedControl = Spell(
        "Reflected Control",
        Element.Water,
        true,
        50,
        80,
        "This spell mystically ties the victim\'s body to the caster\'s, forcing the target " +
                "to mimic the spellcaster\'s every move. In a way, the targeted individual behaves " +
                "as a mirror image, automatically duplicating his dominator. In order to " +
                "resist this spell, the target must pass a MR Check with a Difficulty of 80. " +
                "Victims receive subsequent Checks if the spellcaster forces them to engage in " +
                "an action entirely contrary to their nature.",
        "+5 to the MR Difficulty",
        20,
        20,
        false,
        listOf(SpellType.Spiritual)
    )

    private val liquidBody = Spell(
        "Liquid Body",
        Element.Water,
        true,
        52,
        100,
        "This spell transforms a targeted body into a liquid substance immune to many types " +
                "of attacks and susceptible to form shift at the spellcaster's will. This limited " +
                "metamorphism is very effective, because anyone who sees a character affected " +
                "with this spell needs to pass a Very Difficult level Notice Check to perceive " +
                "his body\'s liquid nature. This liquefied form can turn its limbs into various " +
                "physical weapons with a +5 quality bonus.\nAlthough not, strictly speaking, an " +
                "immaterial being, liquefied bodies can pass through any hole or crack that " +
                "water can. While in this state, characters are utterly invulnerable to non-Energy " +
                "damaging Thrust and Cut attacks. Furthermore, Impact attacks will have half " +
                "their regular effect upon them - unless they can damage Energy, in which case " +
                "they will still have full effect. Liquefied characters do, however, become " +
                "vulnerable to paralyzing Cold effects, suffering a -20 penalty to Resistance " +
                "Checks against them. This spell affects a maximum Presence of 100 points.",
        "+10 to the maximum Presence affected",
        10,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val reflectStates = Spell(
        "Reflect States",
        Element.Water,
        false,
        56,
        120,
        "The caster creates a mystical mirror, with the ability to reflect any " +
                "supernatural State of which the spellcaster, or a nearby individual, may be a " +
                "victim. The effect automatically reflects upon the originator, who must pass a " +
                "MR Check with a Difficulty of 120 in order to avoid falling victim to his own " +
                "powers. For example, if a caster falls prey to a terrible pain of a magical " +
                "nature, this spell would force the character who caused the pain to pass a " +
                "MR Check or undergo the same suffering. This spell requires no maintenance; " +
                "the reflected state will remain for as long as the original target continues " +
                "to suffer the ill effects. Each effect can only be reflected (or tried to be " +
                "reflected) once.",
        "+5 to the MR Difficulty",
        20,
        null,
        false,
        listOf(SpellType.Automatic)
    )

    private val iceStorm = Spell(
        "Ice Storm",
        Element.Water,
        true,
        60,
        120,
        "This spell creates a heavy ice storm that freezes anything in its path. Anyone " +
                "within its range must pass a PhR Check with a Difficulty of 140 every 5 Combat " +
                "Turns or automatically suffer 10 points of Cold-based damage, plus a cumulative " +
                "-5 All Action Penalty. Due to the intense blizzards called forth by this storm, " +
                "perceptive ability difficulties increase by two levels. The storm has a maximum " +
                "50-meter radius within which no targets can be selected. All creatures inside " +
                "the area of this spell\'s effect after the first round it appears must make " +
                "their Resistance Checks. The spell will remain stationary.",
        "+25 meters to radius",
        20,
        20,
        false,
        listOf(SpellType.Automatic)
    )

    private val tideControl = Spell(
        "Tide Control",
        Element.Water,
        true,
        62,
        150,
        "The caster gains limited control of river and sea currents, and he is able to " +
                "alter their course and strength. Through the use of the spell, a mage can even " +
                "create small underwater earthquakes, tidal waves, or reverse the course of " +
                "rivers. The maximum area of influence is a 500-meter radius which the spellcaster " +
                "can relocate as he wishes.",
        "+100 meters to radius",
        40,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val waterConfinement = Spell(
        "Water Confinement",
        Element.Water,
        true,
        66,
        140,
        "This spell produces a huge water mass that swallows all individuals it encounters. " +
                "Whoever is trapped inside will move as if diving and will soon drown unless he " +
                "is able to breathe in liquid environments. Potent currents push prisoners inside " +
                "the center of the prison, making it difficult for them to break free. An escape " +
                "attempt requires passing an Opposed Strength 14 Check. However, characters " +
                "receive a +1 bonus to their check for every Swim level above Easy that they " +
                "pass. The water mass appears as a cube 10 meters wide and 10 meters high. All " +
                "creatures inside the area of this spell\'s effect after the first round it " +
                "appears must make their Opposed Checks.",
        "+10 meters to length",
        20,
        20,
        false,
        listOf(SpellType.Automatic)
    )

    private val glacier = Spell(
        "Glacier",
        Element.Water,
        true,
        70,
        200,
        "As implied by its name, this spell creates an immense glacial zone around the " +
                "caster, totally independent of the prevailing weather conditions. The " +
                "surroundings will immediately be covered with ice and snow, while the " +
                "temperature will drop several degrees below freezing. Natural weather phenomena " +
                "will not affect the cold for as long as the caster maintains the spell. It covers " +
                "a one-kilometer radius.",
        "+500 meters to radius",
        20,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val tsunami = Spell(
        "Tsunami",
        Element.Water,
        true,
        72,
        250,
        "This spell raises a devastating kilometer-long Tsunami that ravages the coast. " +
                "The eldritch Tsunami destroys any construction with a Damage barrier less than " +
                "80, while constructions with Damage Barriers greater than 80 suffer great " +
                "damage. All individuals within the area of impact suffer the logical consequences.",
        "+1 kilometer to length",
        30,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val soulReflection = Spell(
        "Soul Reflection",
        Element.Water,
        true,
        76,
        200,
        "This spell produces the spiritual reflection of a person or creature. The result " +
                "is an animistic copy of the individual, featuring each and every quality present " +
                "in the original. This entity is identical to the original - except it is a " +
                "spiritual being. Although endowed with the capability of interacting with the " +
                "real world, and visible to everyone, it is immune to non-Energy-based attacks. " +
                "Powers of the original creature that depend on Gnosis higher than 25 cannot be " +
                "duplicated. The reflection will obey the spellcaster\'s orders, but it can only " +
                "materialize in the presence of the subject upon whose likeness it has been " +
                "created. This spell can create a copy of anyone\'s soul, provided he has a " +
                "Presence less than 40. Unwilling participants can attempt to resist the spell " +
                "by passing a MR Check with a Difficulty of 140. The spell will not work upon " +
                "the caster himself.\nIf a spellcaster uses Added Effects to increase the maximum " +
                "affected Presence to 60, it will take two Added Effects to increase the maximum " +
                "Presence by 5 thereafter. Only one reflection spell can be active upon the same " +
                "individual at a single time.",
        "+5 to maximum Presence affected and +5 to the MR Difficulty",
        20,
        10,
        false,
        listOf(SpellType.Automatic)
    )

    private val slowTime = Spell(
        "Slow Time",
        Element.Water,
        true,
        80,
        200,
        "The spell acts upon the space-time fibers of a specific section of reality, " +
                "slowing down its pace in such a way that time passes at a much slower rate for " +
                "everyone - including the caster. The affected area is momentarily separated from " +
                "reality. One minute inside the area equals one hour outside. The spell\'s final " +
                "radius is 100 meters around the caster, but it will extend 10 meters per round " +
                "from the time it is first cast.\nIndividuals entering the zone after the spell " +
                "has been cast will beb equally affected. Only entities with a Gnosis over 30 can " +
                "avoid the consequences of the spell by passing a MR Check with a Difficulty of 120.",
        "+50 meters to radius and +5 to the MR Difficulty",
        20,
        10,
        false,
        listOf(SpellType.Automatic)
    )

    private val createUndine = Spell(
        "Create Undine",
        Element.Water,
        true,
        82,
        250,
        "This spell creates a living water creature completely under the magician\'s " +
                "control. This entity is developed as a Being Between Worlds subject to the " +
                "elemental powers and limitations of Water elementals covered in Chapter 26. " +
                "The creature has 600 DP and its maximum level is calculated using the same rules " +
                "as in the spell Create Being from the Path of Creation.",
        "+50 DP",
        30,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val freezeMagic = Spell(
        "Freeze Magic",
        Element.Water,
        false,
        86,
        250,
        "This spell will freeze magic, temporarily stopping spells and cancelling their " +
                "effects. Spells do not vanish while they are stopped. However, they are " +
                "suspended and unable to affect anything. Suspended spells do not require " +
                "maintenance while in this state. Freeze Magic does affect both spellcaster-" +
                "controlled and third-party spells, provided their Zeonic value is not above 150. " +
                "The suspended spell will regain activity as soon as a caster ceases to maintain " +
                "Freeze Magic. Since this is a Passive spell, it can suspend other spells used " +
                "in the same round it is cast.",
        "+5 to total Zeonic value affected",
        30,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val inTheMirror = Spell(
        "Inside the Mirror",
        Element.Water,
        true,
        90,
        300,
        "The spellcaster can conjure up the reflection of a territory, thus creating a " +
                "parallel reality in the image and likeness of an actual setting. The reflection " +
                "will be identical, duplicating the exact condition of construction, flora, and " +
                "weather present in the original at the time this spell is cast. Only living " +
                "organisms with a Presence of 20 or higher and locations with exceptional " +
                "characteristics escape duplication.\nThe caster creates several doors connecting " +
                "the real world to his creation in whatever place or form he desires at the time " +
                "of casting the spell. There is no top limit to the number of passages that he " +
                "can create - provided there is at least one. This spell can reflect a maximum " +
                "area of 500 meters in radius.",
        "+100 meters ot maximum radius",
        40,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val lordOfIce = Spell(
        "Lord of Ice",
        Element.Water,
        true,
        92,
        300,
        "This spell grants the caster control of all cold or ice nuclei in a 100-kilometer " +
                "radius - regardless of their number of intensities. He can decrease weather " +
                "temperature within the area of action and crystallize ice wherever needed. Any " +
                "Cold-based creature inside the area must pass a MR Check with a Difficulty of " +
                "140 to resist domination by the caster. These beings receive another MR Check " +
                "anytime they alter their Base Resistance or when their master gives them a " +
                "command that goes against their very nature.",
        "+100 kilometers to radius and +5 to the MR Difficulty",
        30,
        10,
        true,
        listOf(SpellType.Automatic)
    )

    private val lordOfWater = Spell(
        "Lord of Water",
        Element.Water,
        true,
        96,
        300,
        "This spell grants absolute control over all liquid substances in a 100-kilometer " +
                "radius. The caster can manipulate oceans, rivers, and lakes of any size, molding " +
                "them at will as if they were extensions of himself. Any water-based creature " +
                "inside the area must pass a MR Check with a Difficulty of 140 to resist " +
                "domination by the caster. These beings receive another MR Check anytime they " +
                "alter their Base Resistance or when their master gives them a command that goes " +
                "against their very nature.",
        "+100 kilometers to radius and +5 to the MR Difficulty",
        30,
        10,
        true,
        listOf(SpellType.Automatic)
    )

    private val perfectWorld = Spell(
        "A Perfect World",
        Element.Water,
        true,
        100,
        450,
        "This spell freezes reality, grinding the passage of time to a complete halt. " +
                "Existence is frozen for the duration of the spell. Only the caster can move " +
                "and act freely upon his surroundings, but he is unable to regenerate Zeon. " +
                "Only entities with a 35 Gnosis or higher who pass a MR Check with a Difficulty " +
                "of 120 can avoid the consequences of this spell.",
        "+5 to the MR Difficulty",
        40,
        5,
        true,
        listOf(SpellType.Automatic)
    )

    val fullBook = listOf(
        spring,
        null,
        createChill,
        null,
        aquaticCapability,
        coldImmunity,
        null,
        protectionBubble,
        null,
        waterImpact,
        liquidControl,
        null,
        freezeEmotions,
        null,
        controlCold,
        freeze,
        null,
        iceScreen,
        null,
        createLiquids,
        iceAttack,
        null,
        crystallization,
        null,
        reflectedControl,
        liquidBody,
        null,
        reflectStates,
        null,
        iceStorm,
        tideControl,
        null,
        waterConfinement,
        null,
        glacier,
        tsunami,
        null,
        soulReflection,
        null,
        slowTime,
        createUndine,
        null,
        freezeMagic,
        null,
        inTheMirror,
        lordOfIce,
        null,
        lordOfWater,
        null,
        perfectWorld
    )
}