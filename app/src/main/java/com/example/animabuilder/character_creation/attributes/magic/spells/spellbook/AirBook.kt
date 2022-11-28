package com.example.animabuilder.character_creation.attributes.magic.spells.spellbook

import android.media.effect.Effect
import com.example.animabuilder.character_creation.Element
import com.example.animabuilder.character_creation.attributes.magic.spells.Spell
import com.example.animabuilder.character_creation.attributes.magic.spells.SpellType
import java.io.Serializable

class AirBook: Serializable {
    val raiseWind = Spell(
        "Raise Wind",
        Element.Air,
        true,
        2,
        30,
        "Raises wind up to a maximum of 20 kilometers per hour. The spellcaster needs to " +
                "be in the open or in an area likely to experience wind drafts. Maximum wind " +
                "draft width is 25 meters.",
        "+10 kilometers per hour and +5 meters wide",
        10,
        10,
        false,
        listOf(SpellType.Effect)
    )

    val moveSpell = Spell(
        "Move",
        Element.Air,
        true,
        6,
        30,
        "This spell allows the spellcaster to move inanimate objects without physical " +
                "contact over a distance with a maximum speed equivalent to Flight Value 10. The " +
                "maximum weight he can affect is 30 kilograms.",
        "+10 kilograms",
        10,
        10,
        true,
        listOf(SpellType.Effect)
    )

    val reduceWeight = Spell(
        "Weight Reduction",
        Element.Air,
        true,
        10,
        40,
        "It reduces a material body weight by 30 kilograms.",
        "-10 kilograms",
        30,
        10,
        true,
        listOf(SpellType.Effect)
    )

    val stopBreathing = Spell(
        "Stop Breathing",
        Element.Air,
        false,
        12,
        40,
        "The target of this spell does not need to breathe and is no longer affected by " +
                "lack of air. A spellcaster may apply this spell to as many individuals as he " +
                "wishes, as long as the sum of their combined Presence is 80 or below.",
        "+10 to the maximum Presence affected",
        10,
        10,
        true,
        listOf(SpellType.Effect)
    )

    val freeMotion = Spell(
        "Free Motion",
        Element.Air,
        true,
        16,
        50,
        "The target of this spell is able to move freely along any type of solid surface, " +
                "completely untouched by gravity. Individuals under this spell are able to walk " +
                "on water or run on walls and ceilings. A spellcaster may apply this spell to as " +
                "many individuals as he wishes, as long as the sum of their combined Presence " +
                "is 80 or below.",
        "+10 to the maximum Presence affected",
        10,
        10,
        false,
        listOf(SpellType.Effect)
    )

    val airBlow = Spell(
        "Air Blow",
        Element.Air,
        true,
        20,
        40,
        "The caster unleashes a potent air blow that can either hit a single long-distance " +
                "target or hold a group of people together over a distance. The blast of air will " +
                "cover a maximum area of 5 meters wide and exert pressure equivalent to Strength " +
                "6. When directed at a single target, a +4 bonus to Strength is applied. Though " +
                "modest, a blow of air can cause real damage equivalent to twice the bonus of the " +
                "blow's Strength; such an attack uses the Impact Attack Type.\nSince this is an " +
                "air-produced blow, only those who pass an Absurd  level Notice Check, or who are " +
                "able to see magic, will perceive the attack. Once a spellcaster raises the " +
                "impact Strength over 12, he will have to use 2 added effects to increase the " +
                "spell's Strength by 1 point thereafter.",
        "+1 Strength and +5 meters wide",
        10,
        null,
        false,
        listOf(SpellType.Attack)
    )

    val airScreen = Spell(
        "Air Screen",
        Element.Air,
        false,
        22,
        50,
        "This spell forms an air barrier that offers protection against all kinds of attacks, " +
                "except those based on Electricity or Energy. In addition, the strong winds raised " +
                "will hinder any physical projectile fired or thrown by the enemy, causing a -50 " +
                "penalty to the projectile's Final Attack. The shield can take up to 300 damage " +
                "points before breaking.",
        "+100 Resistance Points",
        20,
        10,
        false,
        listOf(SpellType.Defense)
    )

    val autoTransport = Spell(
        "Automatic Transportation",
        Element.Air,
        true,
        26,
        50,
        "The recipient of this spell can be transported up to a maximum distance of 50 " +
                "meters. This spell allows individuals to pass through physical objects, such as " +
                "walls or doors - provided these are not based on energy. A spellcaster may apply " +
                "this spell to as many individuals as he wishes, as long as the sum of their " +
                "combined Presence is 60 or below.",
        "+10 to maximum Presence affected and +50 meters",
        10,
        null,
        false,
        listOf(SpellType.Effect)
    )

    val flight = Spell(
        "Flight",
        Element.Air,
        true,
        30,
        60,
        "This spell grants targets the ability to move with Flight Value 4. Once a " +
                "spellcaster empowers this spell to move beyond Flight 10, he will need to use " +
                "2 added effects to increase the Flight Value by 1 point.",
        "+1 to Flight Value",
        10,
        5,
        true,
        listOf(SpellType.Effect)
    )

    val reactionIncrease = Spell(
        "Reaction Increase",
        Element.Air,
        true,
        32,
        60,
        "This spell increases a subject's reaction time by adding a +30 bonus to his " +
                "Initiative. If Initiative is increased over 200, the caster must use two " +
                "added effects to increase it by 10.",
        "+10 bonus to initiative",
        10,
        20,
        false,
        listOf(SpellType.Effect)
    )

    val electrify = Spell(
        "Electrify",
        Element.Air,
        true,
        36,
        80,
        "This spell electrifies physical bodies, inflicting strong discharges on those who " +
                "touch them. Anyone who comes into physical contact with an electrified body, " +
                "including the caster himself, must pass a Physical Resistance Check (against a " +
                "Difficulty of 100) per Combat Turn to avoid losing a number of Life Points equal " +
                "to half his level of failure. This effect is considered an Electricity Attack " +
                "Type. The electrified body can't have a Presence greater than 40 and must be " +
                "less than 1 meter in size.",
        "+5 to PhR Check difficulty, +5 to maximum Presence affected, and +1 meter to size affected",
        10,
        10,
        true,
        listOf(SpellType.Effect)
    )

    val airCut = Spell(
        "Air Cut",
        Element.Air,
        true,
        40,
        60,
        "This spell produces a strong gust of wind capable of inflicting cuts on any " +
                "surface it comes into contact with. The cut will affect a 3 meter line where no " +
                "targets can be selected. The Base Damage of this spell is 80, and it uses the " +
                "Cut Attack Type, reducing enemy AT by 2.",
        "+3 meter",
        20,
        null,
        false,
        listOf(SpellType.Attack)
    )

    val speedSpell = Spell(
        "Speed",
        Element.Air,
        true,
        42,
        80,
        "This spell increases its target's speed. In game terms, it doubles the " +
                "meters-per-round ratio allowed to characters by their Movement Value. A " +
                "spellcaster may apply this spell to as many individuals as he wishes, as long " +
                "as the sum of their combined Presence is 50 or below.",
        "+10 to the maximum Presence affected",
        10,
        10,
        true,
        listOf(SpellType.Effect)
    )

    val lightning = Spell(
        "Lightning",
        Element.Air,
        true,
        46,
        80,
        "This spell sets off a 100 point Base Damage lightning strike. After hitting its " +
                "target, electricity bounces once to the nearest body in a 5 meter radius, causing " +
                "a new attack with identical characteristics. Although the caster cannot choose " +
                "the second target, he is exempt from the second attack. If the surrounding area " +
                "is empty, lightning will either vanish harmlessly or hit the ground.",
        "+1 additional bounce, +5 meters maximum bouncing distance",
        20,
        null,
        false,
        listOf(SpellType.Attack)
    )

    val whirlwind = Spell(
        "Whirlwind",
        Element.Air,
        true,
        50,
        140,
        "The caster raises a whirlwind with a maximum 3 meter radius that leaves nothing " +
                "but a trail of destruction behind it. Any subject within its area will " +
                "automatically receive an attack (with a 180 Final Attack score and a 40 point " +
                "Base Damage on the Impact Table). In addition, all those affected will have to " +
                "pass an Opposed Strength or Agility Check against the equivalent of Strength " +
                "12. Otherwise, the whirlwind sucks them into the air. Victims trapped within " +
                "the whirlwind suffer a -60 All Action Penalty for as long as they remain " +
                "inside. Once the spell ends, suspended bodies will fall from a distance of " +
                "between 30-40 meters. The only condition for being affected by the whirlwind is " +
                "being inside the area from the first round after the whirlwind has been " +
                "created. The caster may propel the spell with speed 8. It has no effect upon " +
                "immaterial bodies or those not affected by air.",
        "+3 meters to the spell's radius",
        20,
        5,
        false,
        listOf(SpellType.Automatic)
    )

    val etherealForm = Spell(
        "Ethereal Form",
        Element.Air,
        true,
        52,
        100,
        "This spell transforms a designated body into air, rendering it intangible and " +
                "therefore immune to all matter and non-energy based attacks. For as long as the " +
                "spell lasts, the subject can move through the air with a speed equivalent to " +
                "his natural Movement Value, and he will only beb visible to individuals who " +
                "pass an Almost Impossible Notice Check or a Very Difficult Search Check. Those " +
                "who can see magic can also see the ethereal individual. Although unable to pass " +
                "through physical matter, ethereal bodies may travel through any cracks or gaps " +
                "that allow the passage of air. The maximum Presence affected by this spell is 100.",
        "+10 to the maximum Presence affected",
        10,
        10,
        false,
        listOf(SpellType.Effect)
    )

    val airControl = Spell(
        "Air Control",
        Element.Air,
        true,
        56,
        80,
        "This spell endows the caster with dominion over air and any other gaseous " +
                "substance in a 50 meter radius. The wizard can manipulate wind currents and " +
                "gaseous elements, using them at will. For instance, he could deprive an entire " +
                "area of air or change the course of a tornado. If cast against a being made of " +
                "air, the caster can attempt to control it - provided the creature fails a MR " +
                "Check against a target Difficulty of 120.",
        "+25 meters and +5 to MR Difficulty",
        20,
        10,
        false,
        listOf(SpellType.Effect, SpellType.Spiritual)
    )

    val electricityControl = Spell(
        "Electricity Control",
        Element.Air,
        true,
        60,
        80,
        "This spell grants control over the form and direction of electrical sources not " +
                "exceeding 5 intensities. If faced with a being made of electricity, the " +
                "magician can control it - provided the creature fails an MR Check against a " +
                "Difficulty of 140.",
        "+1 electricity intensity and +5 to MR Difficulty",
        20,
        10,
        false,
        listOf(SpellType.Effect, SpellType.Spiritual)
    )

    val defensiveMovement = Spell(
        "Defensive Movement",
        Element.Air,
        false,
        62,
        120,
        "This spell allows the magician to move away from the reach of an attack. When " +
                "cast, this spell uses the caster's Magic Projection Ability in place of his " +
                "Dodge Ability. A mage can defend himself this way a maximum of three times " +
                "per Combat Turn. For the purpose of counting penalties against Area Attacks, " +
                "the spell grants the caster a speed equivalent to Movement Value 8.",
        "+1 Dodge per turn and +1 Movement Value to exit areas",
        20,
        10,
        false,
        listOf(SpellType.Defense)
    )

    val teletransportation = Spell(
        "Teletransportation",
        Element.Air,
        true,
        66,
        150,
        "The caster, or the character appointed by him, can be transported to a maximum " +
                "distance of 10 kilometers. This spell allows individuals to pass through physical " +
                "objects, provided these are not made of energy. The spell may affect as many " +
                "individuals as the caster wishes, as long as the total Presence of the affected " +
                "characters remain under 80.",
        "+10 to maximum Presence affected and twice the reach",
        30,
        null,
        false,
        listOf(SpellType.Effect)
    )

    val immateriality = Spell(
        "Immateriality",
        Element.Air,
        true,
        70,
        120,
        "The designated body becomes completely immaterial, thus turning intangible to all " +
                "non-energy based creatures, objects, and attacks. While remaining in this state, " +
                "the subject in question will not be able to touch or be touched, and he can pass " +
                "through any material not of a supernatural origin. If a mage casts this spell on " +
                "an unwilling target, the subject in question will have to pass a MR against 100 " +
                "in order to avoid being affected. Should the target fail the Check and become " +
                "immaterial, he will be entitled to a new Check each day. The caster can affect " +
                "creatures up to a maximum Presence of 80.",
        "+10 to maximum Presence affected and +5 MR Difficulty",
        20,
        10,
        true,
        listOf(SpellType.Spiritual)
    )

    val hurricane = Spell(
        "Hurricant",
        Element.Air,
        true,
        72,
        200,
        "This spell creates a tremendous gale that sweeps away everything in a one kilometer " +
                "radius area. All characters inside the area must pass a Opposed Strength Check " +
                "against Strength 12 or be taken by the wind. All constructions with a Damage " +
                "Barrier lower than 60 shatters automatically, while those with a Barrier under " +
                "120 suffer 10 points of damage per round until they are utterly destroyed. " +
                "Constructions with a Damage Barrier higher than 120 remains unaffected by the " +
                "eldritch hurricane.\nBodies dragged by the wind remain in the air until " +
                "the spell wears off, at which time they plunge to the ground. Distance to the " +
                "ground may vary according to the environment and the magician's will - although " +
                "under no circumstance can it exceed 100 meters. Characters who pass the Opposed " +
                "Strength Check are safe and will not have to make further Checks as long as they " +
                "remain immobile. It is up to the GM to apply whatever bonus he deems fit to the " +
                "Strength Check of those affected.",
        "+50 meters to radius",
        30,
        20,
        false,
        listOf(SpellType.Automatic)
    )

    val solidAir = Spell(
        "Solid Air",
        Element.Air,
        true,
        76,
        140,
        "This spell solidifies the very air, producing resistant, compact matter. The " +
                "magician may choose its form and location, but the area of the solidified air " +
                "is limited to an 25 meter radius. Possible uses of this spell are obstructing " +
                "an entrance with a solid block of air or building an invisible bridge to cover " +
                "a gap, for example. Only energy damaging weapons can harm solid air, and it " +
                "will resist up to 150 points of damage every 5 meters before breaking. The only " +
                "way this substance can be seen is by the viewer passing an Inhuman level Notice " +
                "Check or an Absurd level Search Check.\nThe spell could also be used to fence " +
                "people in, preventing them from moving about freely. When used with this " +
                "purpose, attacks may be performed following the Trapping rules; no penalties " +
                "apply to the magician's Projection Ability for performing this maneuver. Solid " +
                "Air affects all individuals inside the spell area as if possessed Strength 14.",
        "+25 meters to radius",
        20,
        20,
        false,
        listOf(SpellType.Effect, SpellType.Attack)
    )

    val weatherControl = Spell(
        "Weather Control",
        Element.Air,
        true,
        80,
        220,
        "With this spell, a magician can fully control the weather within a 5 kilometer " +
                "radius. He has the power to modify any meteorological element at will, thus " +
                "gradually creating the desired climatic situation.",
        "+5 kilometers to radius",
        30,
        5,
        true,
        listOf(SpellType.Effect)
    )

    val createSylph = Spell(
        "Create Sylph",
        Element.Air,
        true,
        82,
        250,
        "This spell creates a seemingly live creature completely under the magician's " +
                "control. This entity shall be developed as a Being Between Worlds, subject to " +
                "the elemental powers and limitations of Air elementals established in Chapter " +
                "26. The creature shall have 600 DP and its maximum level will be calculated " +
                "using the same rules as in the spell Create Being from the Path of Creation.",
        "+50 DP",
        30,
        5,
        true,
        listOf(SpellType.Effect)
    )

    val superiorPsychokinesis = Spell(
        "Superior Psychokinesis",
        Element.Air,
        true,
        86,
        160,
        "This spell allows a spellcaster to move organic and inorganic bodies at a " +
                "distance, sparing the need for physical contact. Objects moved by this spell " +
                "can travel up to a speed equivalent to Flight Value 10. Superior Psychokinesis " +
                "can affect a maximum weight of 100 tons. Living creatures may resist this spell " +
                "by passing a MR Check with a Difficulty of 100.",
        "Twice the maximum weight and +5 to the MR Difficulty",
        20,
        5,
        true,
        listOf(SpellType.Effect)
    )

    val relocateMagic = Spell(
        "Relocate Magic",
        Element.Air,
        true,
        90,
        180,
        "The magician is able to transport the magical sources supporting an active spell " +
                "from one place to another. In this way, the mage can relocate spells at will. A " +
                "character casting this spell can relocate any maintained spell with a Zeonic " +
                "value lower than 100 points - including those not under his control. Maximum " +
                "relocation distance is determined by the general rules of Magic Projection. " +
                "This spell can be used against place-oriented, individual-oriented, and " +
                "object-oriented spells. When performing the relocation of a Spiritual spell, " +
                "all effects are treated exactly as if the spell had just been cast, allowing " +
                "the new target to defend himself with a normal Resistance Check.",
        "+5 to the Zeonic value the spell can affect",
        30,
        10,
        true,
        listOf(SpellType.Effect)
    )

    val passiveMagic = Spell(
        "Passive Magic",
        Element.Air,
        true,
        92,
        300,
        "This spell envelops the magician's essence, allowing magic to flow within him in " +
                "an instinctive manner. The caster is at one with magic, and they respond to " +
                "events as a single entity. As long as he maintains this spell, all other spells " +
                "that he performs occur as a Passive Actions - including Attack and Spiritual " +
                "spells. This spell affects a maximum Presence of 80 points.",
        "+5 to maximum Presence affected",
        30,
        10,
        false,
        listOf(SpellType.Effect)
    )

    val airLord = Spell(
        "Lord of the Air",
        Element.Air,
        true,
        96,
        300,
        "This spell grants a character control of all air or electricity nuclei, regardless " +
                "of the number of intensities they are composed of, within a 100 kilometer radius. " +
                "The dominion also allows for weather manipulation and the creation of gales, " +
                "storms, or gusts of wind of all sizes. All air-based creatures within the spell " +
                "area will be immediately controlled by the caster unless they pass a MR Check " +
                "with a target Difficulty of 140. Once passed, they will not be required to make " +
                "another Check. The affected parties are entitled to a new roll only if they " +
                "alter their base Resistance.",
        "+100 kilometers to radius and +5 to MR Difficulty",
        30,
        10,
        true,
        listOf(SpellType.Automatic)
    )

    val placeInTheWorld = Spell(
        "A Place in the World",
        Element.Air,
        true,
        100,
        450,
        "By performing this spell, the character modifies the very order of the soul network, " +
                "thus altering reality itself. Even when not able to change the essence of things, " +
                "or their shape, the spell grants the caster the ability to move them around and " +
                "place them anywhere he wishes - with no other limit than his own will. Any " +
                "object or being, both physical and spiritual, can be teletransported anywhere. " +
                "There is no per round limit ot the number or condition of things that can be " +
                "moved and relocated. The only way a character or creature can avoid the effects " +
                "of the spell is to succeed on a MR Check with a Difficulty of 140. This spell's " +
                "area of influence is 50 kilometers. All beings or creatures within the area are " +
                "automatically affected.",
        "+10 kilometers to radius and +5 to the MR Difficulty",
        40,
        10,
        false,
        listOf(SpellType.Automatic)
    )
}