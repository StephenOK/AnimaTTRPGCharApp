package com.example.animabuilder.character_creation.attributes.magic.spells.spellbook

import com.example.animabuilder.character_creation.Element
import com.example.animabuilder.character_creation.attributes.magic.spells.Spell
import com.example.animabuilder.character_creation.attributes.magic.spells.SpellType
import java.io.Serializable

class DestructionBook: Serializable {
    val fragility = Spell(
        "Fragility",
        Element.Destruction,
        true,
        2,
        30,
        "This spell alters the solidity of an object, reducing its sturdiness and making " +
                "it easy to break. Anything affected automatically loses its Damage Barrier; if " +
                "the spell is cast on arms or armor, their Fortitude receives a reduction of -2. " +
                "This spell affects objects with a Presence of no more than 30.",
        "+5 to the maximum Presence that can be affected and -1 to Fortitude",
        10,
        10,
        false,
        listOf(SpellType.Effect)
    )

    val dismantle = Spell(
        "Dismantle",
        Element.Destruction,
        true,
        6,
        40,
        "Dismantles an object made of various pieces. It affects inanimate objects with a " +
                "Presence of no more than 20.",
        "+5 to the maximum Presence that can be affected",
        10,
        null,
        false,
        listOf(SpellType.Effect)
    )

    val destroyIntensities = Spell(
        "Destroy Intensities",
        Element.Destruction,
        true,
        8,
        40,
        "Destroys one intensity of one of the three types of existing energies (Cold, Fire, " +
                "or Electricity).",
        "-1 additional Intensity",
        10,
        null,
        false,
        listOf(SpellType.Effect)
    )

    val minorDestruction = Spell(
        "Minor Destruction",
        Element.Destruction,
        true,
        10,
        50,
        "Affects the essence of a lifeless material object, destroying it completely, " +
                "as long as its Presence is no greater than 20.",
        "+5 to the maximum Presence that can be affected",
        10,
        null,
        false,
        listOf(SpellType.Effect)
    )

    val destructionSphere = Spell(
        "Sphere of Destruction",
        Element.Destruction,
        true,
        12,
        30,
        "Projects a ball of magical energy. Sphere of Destruction is an Energy Attack Type " +
                "with a Base Damage of 30.",
        "+5 to Base Damage",
        10,
        null,
        false,
        listOf(SpellType.Attack)
    )

    val increaseWeakness = Spell(
        "Increase Weakness",
        Element.Destruction,
        true,
        16,
        50,
        "This spell finds a being's weak points, and makes them weaker supernaturally. " +
                "In game terms, it doubles the penalty the individual suffers for any " +
                "vulnerability. For example, an elemental who is especially vulnerable to light " +
                "would suffer quadruple damage, instead of double damage, from an attack using " +
                "light, while a character vulnerable to poisons would have his VR reduced to a " +
                "fourth of its usual value. To avoid being affected by Increase Weakness, its " +
                "target needs to pass a MR Check with a Difficulty of 120. If he fails, he can " +
                "only roll the Check again when he is affected by his weakness.",
        "+5 to the MR Difficulty",
        10,
        10,
        true,
        listOf(SpellType.Spiritual)
    )

    val magicDestruction = Spell(
        "Magic Destruction",
        Element.Destruction,
        false,
        18,
        60,
        "Destroys a spell whose Zeon value is no greater than 40. Since Magic Destruction " +
                "is passive, it can be employed to nullify any spell launched in the same combat turn.",
        "+5 to the Zeon value of the spell",
        30,
        null,
        false,
        listOf(SpellType.Effect)
    )

    val aggravateDamage = Spell(
        "Aggravate Damage",
        Element.Destruction,
        false,
        20,
        60,
        "Increases the Base Damage of any attack (physical or supernatural) by 30 points. " +
                "For example, if an attacker is wielding a weapon with a Base Damage of 60, using " +
                "the spell increases it to 90. In spite of its being a passive spell, it must be " +
                "cast before any dice are rolled, to make it possible to calculate the opponent's " +
                "respective attack and defense factors.",
        "+5 damage",
        10,
        null,
        false,
        listOf(SpellType.Effect)
    )

    val destructMatrices = Spell(
        "Destruction of Matrices",
        Element.Destruction,
        false,
        22,
        80,
        "This spell unravels the energy matrices of a Psychic Power whose Potential is no " +
                "more than 80 (that is to say, of Medium Difficulty). Since it is a passive spell, " +
                "it can be employed to nullify any Psychic Power launched in the same combat turn.",
        "+10 to the Psychic Potential of the power that can be affected",
        20,
        null,
        false,
        listOf(SpellType.Effect)
    )

    val wound = Spell(
        "Wound",
        Element.Destruction,
        true,
        26,
        80,
        "Wound affects the physical condition of an individual, doing damage to his health. " +
                "It produces wounds and damage equal to 20% of his current Life Points, not of his " +
                "total. Resisting this spell requires beating a MR Check with a Difficulty of 120.",
        "-5% of Life Points and +5 to the MR Difficulty",
        10,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    val destroyKi = Spell(
        "Destroy Ki",
        Element.Destruction,
        true,
        28,
        80,
        "The magic unleashed by this spell penetrates the soul of its victim, dissolving " +
                "his reserve of Ki. The victim must beat a MR Check with a difficulty of 120, or " +
                "he loses as many points of Ki as the level of Failure. The caster chooses the " +
                "order and distribution of points lost among the different characteristics.",
        "+5 to the MR Difficulty",
        10,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    val produceDamage = Spell(
        "Produce Damage",
        Element.Destruction,
        true,
        30,
        80,
        "Through the use of this spell, the caster automatically causes a wound in an " +
                "individual's body. If the victim does not beat an MR Check with a Difficulty " +
                "of 120, he suffers 40 points of direct damage. Creatures with Damage Resistance " +
                "multiply the damage by their Resistance multiple.",
        "+5 to the MR Difficulty and +10 to the damage received",
        10,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    val senseDestroy = Spell(
        "Destruction of Senses",
        Element.Destruction,
        true,
        32,
        100,
        "The caster cuts off the senses of the individual who fails a MR Check with a " +
                "Difficulty of 100. The caster can choose which senses to take.",
        "+5 to the MR Difficulty",
        10,
        20,
        false,
        listOf(SpellType.Spiritual)
    )

    val mysticBolt = Spell(
        "Mystic Bolt",
        Element.Destruction,
        true,
        36,
        80,
        "Projects a mystical bolt that affects a single target. The attack is an Energy " +
                "Attack Type with a Base Damage of 100.",
        "+5 to Base Damage",
        20,
        null,
        false,
        listOf(SpellType.Attack)
    )

    val unravelTies = Spell(
        "Unravel Ties",
        Element.Destruction,
        true,
        38,
        100,
        "Using this spell, the caster undoes any type of tie that permits a summoner to " +
                "Control his creatures. It can be used with equal effect on either the controller " +
                "or the controlled creature, but it is always the creator of the Bound whose MR " +
                "is checked. If cast upon the creature, it affects only the ties binding the two " +
                "of them, but if it is cast directly against the summoner, it breaks an additional " +
                "binding for every 10 points by which the controller fails a MR with a Difficulty of 120.",
        "+5 to the MR Difficulty",
        10,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    val destroyResistances = Spell(
        "Destroy Resistances",
        Element.Destruction,
        true,
        40,
        80,
        "The person affected by this spell has all his Resistances reduced by an amount " +
                "equal to the margin by which he did not pass an MR Check with a Difficulty of 120.",
        "+5 to the MR Difficulty",
        20,
        10,
        false,
        listOf(SpellType.Spiritual)
    )

    val undoState = Spell(
        "Undo States",
        Element.Destruction,
        true,
        42,
        100,
        "Immediately undoes any of the States described in Chapter 14, or others that are " +
                "equivalent to them. This spell can affect as many targets as desired as long as " +
                "their accumulated Presence is no higher than 120. The spell cannot undo the " +
                "penalties to action caused by a Critical. Resisting the spell requires beating a " +
                "MR Check with a Difficulty of 100.",
        "+5 to the MR Difficulty and +10 to the maximum Presence that can be affected",
        20,
        null,
        false,
        listOf(SpellType.Effect)
    )

    val destructionDome = Spell(
        "Dome of Destruction",
        Element.Destruction,
        true,
        46,
        100,
        "Unleashes a dome of supernatural energy that bursts over an area of 10 meters in " +
                "radius. It is not possible to choose targets within the area. The attack is an " +
                "Energy Attack Type with a Base Damage of 80.",
        "+5 Base Damage and +5 meters to radius",
        20,
        null,
        false,
        listOf(SpellType.Attack)
    )

    val decayZone = Spell(
        "Zone of Decay",
        Element.Destruction,
        true,
        48,
        140,
        "This spell covers a fixed area of 10 meters in radius, within which all forms of " +
                "life begin to rot and decay at an accelerated pace. Anyone found within the Zone " +
                "of Decay loses 10% of their total Life Points unless they beat a MR Check with a " +
                "Difficulty of 100. Each turn they remain within the Zone, they must make a new " +
                "check, regardless of whether they passed a previous check or not. The condition " +
                "for being affected is simply being within the area.",
        "+5 meters to radius and +5 to the MR Difficulty",
        20,
        10,
        false,
        listOf(SpellType.Automatic)
    )

    val destructionAura = Spell(
        "Aura of Destruction",
        Element.Destruction,
        true,
        50,
        150,
        "Enchants a place or object, creating in the area an aura of magic that destroys " +
                "everything that comes into contact with it. Anyone who touches it must beat a MR " +
                "Check with a Difficulty of 80, or he loses as many points of Life Points " +
                "equivalent to the Failure level. The maximum Presence of the object or place " +
                "affected is 60. However, even if the object is very large, the aura cannot " +
                "extend more than 1 meter long. The spell can affect even its caster.",
        "+5 to Presence, +1 meter, and +5 to the MR Difficulty",
        20,
        10,
        true,
        listOf(SpellType.Effect)
    )

    val destroyMemories = Spell(
        "Destroy Memories",
        Element.Destruction,
        true,
        52,
        140,
        "Affects the memories of an individual, making him forget whatever the caster " +
                "wishes. The spell does not affect a person's abilities, only their conscious " +
                "memories. The MR or PsR to avoid the effects is 100.",
        "+5 to the MR or PsR Difficulty",
        20,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    val blockLearning = Spell(
        "Block Learning",
        Element.Destruction,
        true,
        56,
        120,
        "This spell impedes the ability of an individual to learn, completely preventing " +
                "him from developing or acquiring new knowledge. While a person is under the " +
                "effects of this spell, he cannot acquire new Experience Points or develop any " +
                "abilities or powers. To avoid the effects of the spell, he must beat a MR Check " +
                "with a Difficulty of 120. It is only possible to repeat the Resistance check " +
                "once per day.",
        "+5 to the MR Difficulty",
        20,
        10,
        true,
        listOf(SpellType.Effect)
    )

    val forbid = Spell(
        "Forbid",
        Element.Destruction,
        true,
        58,
        100,
        "The caster vetoes some specific action by the spell's victim, completely " +
                "prohibiting him from even attempting to do it. The spell Forbids only Active " +
                "Actions. Resisting the spell requires beating a MR Check with a Difficulty of " +
                "120. If the forbidden action is very general, like attack or move, the target " +
                "can add a special bonus to his MR between +10 and +30.",
        "+5 to the MR Difficulty",
        20,
        20,
        false,
        listOf(SpellType.Spiritual)
    )

    val destroyPowers = Spell(
        "Destroy Powers",
        Element.Destruction,
        true,
        60,
        140,
        "The person affected by this spell is completely unable to use any of his " +
                "supernatural powers. He is thereby prevented from using any magical, psychic, " +
                "or Ki powers he possesses. Mystical beings also lose all their powers (although " +
                "not their natural abilities). To avoid the effects of the spell, its target must " +
                "beat a MR Check with a Difficulty of 120.",
        "+5 to the MR Difficulty",
        20,
        10,
        false,
        listOf(SpellType.Spiritual)
    )

    val greatMysticBolt = Spell(
        "Greater Mystic Bolt",
        Element.Destruction,
        true,
        62,
        150,
        "Projects a powerful supernatural bolt. The attack is an Energy Attack Type with a " +
                "Base Damage of 150.",
        "+10 to Base Damage",
        30,
        null,
        false,
        listOf(SpellType.Attack)
    )

    val destroyWill = Spell(
        "Destroy Will",
        Element.Destruction,
        true,
        66,
        160,
        "This spell affects a 10 meter radius area, within which any individual who does " +
                "not beat a MR Check with a Difficulty of 120 automatically loses the ability to " +
                "make decisions. While a person is influenced by this spell, he cannot undertake " +
                "any Active Action, even moving, except in case of necessity.",
        "+5 meters to radius and +5 to the MR Difficulty",
        20,
        10,
        false,
        listOf(SpellType.Spiritual)
    )

    val weaknessZone = Spell(
        "Zone of Weakness",
        Element.Destruction,
        true,
        68,
        200,
        "With this spell, the caster weakens the fiber of reality itself within a given area, " +
                "making everything inside become fragile and vulnerable. Any damage produced " +
                "within the spell's area is automatically doubled, and characters suffer Criticals " +
                "as though their entire body was a vulnerable point. Structures and constructions " +
                "automatically lose their Damage Barrier, and objects with a Fortitude rating " +
                "such as swords and armor suffer a -5 penalty. The spell affects an area with a " +
                "radius of 20 meters, which remains stationary where it was cast. Any living " +
                "being can avoid the spell's effects by beating a MR Check with a Difficulty of " +
                "140. The condition for being affected by the Zone of Weakness is simply to be " +
                "within its area, and it is only possible to free oneself by leaving it, since " +
                "it is only permitted to repeat the Resistance Check once per day.",
        "+5 to the MR Difficulty and +10 meters",
        20,
        10,
        true,
        listOf(SpellType.Automatic)
    )

    val destructionEssence = Spell(
        "Essence of Destruction",
        Element.Destruction,
        true,
        70,
        150,
        "The caster alters the material of a body, transforming it to a form of purely " +
                "destructive energy capable of devouring everything it comes into contact with. " +
                "While in this state, the body can only be damaged by attacks capable of " +
                "affecting supernatural targets, and anyone coming in contact with it must beat " +
                "a MR Check with a Difficulty of 80, or lose as many Life Points as the Failure " +
                "Level. The maximum Presence that can be affected is 100.",
        "+5 to the maximum Presence affected",
        20,
        10,
        false,
        listOf(SpellType.Effect)
    )

    val death = Spell(
        "Death",
        Element.Destruction,
        true,
        72,
        200,
        "The caster is capable of producing the death of a living being by separating his " +
                "soul from his body. The power of this spell is so great that it can even kill " +
                "spiritual beings or necromantic creatures, converting them to simple dead souls " +
                "or even destroying them completely. To resist the effects of this terrible spell, " +
                "it is necessary to beat a MR or PhR Check with a Difficulty of 120.",
        "+5 to the MR or PhR Difficulty",
        20,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    val devouringZone = Spell(
        "Devouring Zone",
        Element.Destruction,
        true,
        76,
        250,
        "Like a deadly vortex, the Devouring Zone spreads, shredding the essence of " +
                "everything it finds in its path by slowly consuming it. Little by little, the " +
                "things this spell reaches weaken until they are unmade, whether they are material " +
                "objects or living beings. Every day the victims (either people or things) " +
                "remain within its area, they must beat a MR or PhR of 140, or temporarily have " +
                "their Base Presence reduced by five points. If their Presence falls to zero, " +
                "they decompose leaving little trace. Living beings also receive an additional " +
                "All Action Penalty equivalent to twice the Presence Points lost. The spiritual " +
                "damage is recouped at a rate of five points per day once the person leaves the " +
                "area of the spell. Devouring Zone affects an area with a radius of 500 meters, " +
                "which remains stationary where it was cast.",
        "+500 meters to radius and +5 to the MR or PhR Difficulty",
        30,
        10,
        true,
        listOf(SpellType.Automatic)
    )

    val destroyCapabilities = Spell(
        "Destroy Capabilities",
        Element.Destruction,
        true,
        78,
        150,
        "This spell destroys part of the abilities or powers of an individual, stripping him " +
                "of them forever. The caster freely removes 50 DP from the affected person, " +
                "choosing the capacities or fields affected. He could, for instance, cut the " +
                "person's Attack Ability by 30 DP and take the remaining DP from a Secondary " +
                "Ability he chooses. It is also possible to destroy the Supernatural Powers of " +
                "mystical creatures with this spell, using the value of DP listed in Chapter 26 " +
                "for reference. Destroy Capabilities also permits one to strip advantages chosen " +
                "with Creation Points, at a cost of 100 DP per CP. The MR Difficulty to avoid the " +
                "effects is 120.",
        "+5 to the MR Difficulty and -10 DP",
        20,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    val severExistence = Spell(
        "Sever Existence",
        Element.Destruction,
        true,
        80,
        340,
        "As though wielding a knife of nothingness, the caster can cut through reality " +
                "itself, annihilating anything in his path. Anything that comes in contact with " +
                "the spell is destroyed physically and spiritually, leaving no sign that it ever " +
                "existed. The spell permits either cutting a line 10 meters long or focusing all " +
                "its power in a single point, destroying everything it touches completely unless " +
                "the required Resistance Check is successful. In spite of being a Spiritual " +
                "spell, the cut in reality is perfectly visible even to those incapable of seeing " +
                "magic. The objects or individuals cut off by Sever Existence are destroyed " +
                "completely, and do not return to the Flow of Souls. The MR Difficulty to resist " +
                "the effects of the spell is 120, but if focused on a single point, becomes 140.",
        "+10 meters to the line and +5 to the MR Difficulty",
        30,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    val destructionRain = Spell(
        "Rain of Destruction",
        Element.Destruction,
        true,
        82,
        250,
        "This spell unleashes a storm of selective blasts that destroy only the targets " +
                "designated by the caster within a 50 meter radius. It has a Base Damage of 200, " +
                "and attacks on Energy AT. Additionally, if it impacts a target and causes damage " +
                "to him, the victim must beat a MR Check with a Difficulty of 140, or he loses a " +
                "quantity of Life Points equal to the Margin of Failure.",
        "+10 meter radius, +5 to Base Damage, and +5 to MR Difficulty",
        30,
        null,
        false,
        listOf(SpellType.Attack)
    )

    val zeonDestruction = Spell(
        "Destruction of Zeon",
        Element.Destruction,
        true,
        86,
        200,
        "By directly affecting the essence of magic, this spell is capable of dissipating " +
                "the power of another active spell, automatically diminishing its Zeon value by " +
                "60 points. If the diminished spell is thereby reduced below its base cost, it disappears.",
        "-5 to the Zeon of the spell",
        50,
        null,
        false,
        listOf(SpellType.Effect)
    )

    val heavenSweep = Spell(
        "Sweep from the Heavens",
        Element.Destruction,
        true,
        88,
        300,
        "By stealing away its very essence, this spell takes away the divine presence of " +
                "an entity, temporarily transforming it into an earthly creature. In terms of the " +
                "rules of the game, the creature affected by the spell has its Gnosis value " +
                "reduced by 10. That reduction affects the powers or abilities of the entity that " +
                "depend on Gnosis, and so it cannot use them while in the reduced state. " +
                "Resisting the spell requires beating a MR Check with a Difficulty of 120. It is " +
                "not possible to repeat the Resistance Check while it remains active.",
        "-5 to the Gnosis and +5 to the MR Difficulty",
        30,
        5,
        true,
        listOf(SpellType.Effect)
    )

    val void = Spell(
        "Void",
        Element.Destruction,
        true,
        90,
        250,
        "This spell forms a sphere of absolute void that absorbs into itself all physical and " +
                "spiritual matter around it, unmaking them completely. The dome has a 5 meter " +
                "radius and must be located in an open space. Once cast, it remains fixed in that " +
                "location until the spell ends. Once created, it begins to suck in anything within " +
                "a 50 meter radius. The power of attraction of the spell employs an equivalent " +
                "Strength of 14, and anyone failing in an Opposed Check of characteristics will " +
                "be drawn toward it at a rate of 10 meters for every point of difference. The " +
                "void is so powerful that anything coming into contact with the sphere weakens " +
                "until it disappears. Each combat turn a victim remains within the dome, he must " +
                "beat two distinct Resistance Checks with a Difficulty of 120; one is a MR Check " +
                "which if failed causes him to lose a number of Zeon equivalent to the margin of " +
                "failure, and the other is a PhR Check, which if failed causes him to lose as " +
                "many points of Life Points as the Margin of Failure. When a victim has lost all " +
                "Zeon, he begins to permanently lose one point of Power for every 100 points of " +
                "Zeon he would continue to lose. If his Power falls to zero, or he dies from loss " +
                "of Life Points, his being is totally swallowed by the nothingness. Although the " +
                "caster is not drawn by the attractive power of the dome, he will suffer the " +
                "effects of the Void if he comes in contact with its nucleus.",
        "+1 meter to radius, +50 meters to the area of influence, and +5 to the MR and " +
                "PhR Difficulty",
        30,
        10,
        false,
        listOf(SpellType.Effect)
    )

    val greaterDestruction = Spell(
        "Greater Destruction",
        Element.Destruction,
        true,
        92,
        350,
        "Provides the ability to destroy part of the material world on a grand scale, " +
                "disintegrating even large constructions such as entire cities or vast areas of " +
                "land. This spell can affect as many inorganic objects as desired as long as the " +
                "sum of their Presence is no higher than 100.",
        "+10 to the maximum Presence that can be affected",
        30,
        null,
        false,
        listOf(SpellType.Effect)
    )

    val destroySouls = Spell(
        "Destroy Souls",
        Element.Destruction,
        true,
        96,
        500,
        "The power unleashed by the destruction of souls sweeps away the spiritual matter " +
                "around the caster, ending the existence of anyone who does not resist its " +
                "effects. Any individual within an area of 5 kilometers from the point where the " +
                "spell is unleashed by the caster must make a MR Check against a Difficulty of " +
                "100 or have his soul completely disintegrated causing immediate death.",
        "+3 kilometer radius and +5 to MR Difficulty",
        40,
        null,
        false,
        listOf(SpellType.Automatic)
    )

    val chaos = Spell(
        "Chaos",
        Element.Destruction,
        true,
        98,
        700,
        "Chaos is a spell of almost insuperable power, since it is capable of shaking the " +
                "very pillars of reality. Upon being unleashed, the caster causes the order of " +
                "things to crumble, altering the balance of all things in a completely unexpected " +
                "way. Inside the zone of Chaos, nothing works as it should, and all events are " +
                "unnaturally twisted. For example, chaos can affect gravity, making some things " +
                "as light as feathers and others as heavy as lead. It can affect the weather, " +
                "causing icy winds only yards from tropical gales or heat waves. This spell does " +
                "not affect only the natural elements, though. It can influence the feelings and " +
                "instincts of living beings, making lovers hate each other with a passion, or " +
                "irreconcilable enemies form bonds of peace. The spell can even affect the " +
                "passage of time, making it flow more quickly or slowly (it cannot be used to " +
                "travel to the past, however). That is to say, Chaos is capable of affecting " +
                "practically any imaginable facet of reality.\nThe caster can designate a specific " +
                "aspect of reality he wishes to alter, or on the other hand, transform it " +
                "completely. In either case, this spell does not give the caster direct control " +
                "over what is changing, so the results and consequences of events can be, as the " +
                "name of the spell implies, completely chaotic.\nChaos covers a maximum radius of " +
                "100 kilometers, and is static in the place it was cast. It automatically affects " +
                "anyone with a Gnosis of 15 or less.",
        "+100 kilometers to radius and +1 to the Gnosis that can be affected",
        50,
        10,
        true,
        listOf(SpellType.Automatic)
    )

    val uncreation = Spell(
        "Uncreation",
        Element.Destruction,
        true,
        100,
        1000,
        "This spell permits the caster to designate an aspect of existence, one as " +
                "far-reaching as a city or race, or as precise as a single specific individual, " +
                "and simply make it so it ceases to exist. The unmade item or person is erased " +
                "from reality with all the consequences that implies, so that it never did exist. " +
                "No one who knew of what was unmade remembers it, and all events it may have " +
                "affected are modified as though what was unmade had no part in it; even " +
                "returning to life those whose death the unmade might have caused (as long as " +
                "their souls were not destroyed). Only entities with a Gnosis of more than 40 " +
                "will be conscious of what happened. It is only possible to make one MR Check " +
                "against a Difficulty of 140 to avoid the effects of this spell, even if it " +
                "affects a multitude of persons or objects. The MR Check is made using the " +
                "highest resistance that anyone or anything to be affected by Uncreation has at " +
                "that exact moment. It is not possible to unmake elements of the past, only " +
                "things that are currently present.",
        "+5 to the MR Difficulty",
        50,
        null,
        false,
        listOf(SpellType.Automatic)
    )
}