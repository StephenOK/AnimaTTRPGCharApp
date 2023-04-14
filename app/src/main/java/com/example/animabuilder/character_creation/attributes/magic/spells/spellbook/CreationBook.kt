package com.example.animabuilder.character_creation.attributes.magic.spells.spellbook

import com.example.animabuilder.character_creation.Element
import com.example.animabuilder.character_creation.attributes.magic.spells.Spell
import com.example.animabuilder.character_creation.attributes.magic.spells.SpellType

/**
 * List of spells associated with the creation element.
 */
class CreationBook{
    private val minorCreation = Spell(
        "Minor Creation",
        Element.Creation,
        true,
        2,
        30,
        "Creates a simple object with a Presence of no more than 25.",
        "Creates one additional object",
        10,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val reconstruct = Spell(
        "Reconstruct",
        Element.Creation,
        true,
        6,
        40,
        "Restores a non-organic object to its original form from parts or pieces that " +
                "remain. Note that it is necessary to either have all the pieces, or enough of " +
                "the prime material to rebuild the complete object, otherwise it will be only " +
                "partly reconstructed. This spell affects objects with a Presence of no more than 20.",
        "+5 to the maximum Presence of the object",
        20,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val createEnergy = Spell(
        "Create Energy",
        Element.Creation,
        true,
        8,
        40,
        "Creates one Intensity of one of the three types of existing energies (Cold, Fire, or " +
                "Electricity).",
        "+1 additional Intensity",
        20,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val regeneration = Spell(
        "Regeneration",
        Element.Creation,
        true,
        10,
        60,
        "Increases the ability of a body to heal all types of wounds. This spell provides " +
                "a base Regeneration Level of 4 to anyone designated by the caster, substituting " +
                "it for the person\'s natural Regeneration Level. If it increases above 14, two " +
                "more Added Effects are necessary instead of one to continue increasing it more. " +
                "A Regeneration level of more than 18 cannot be achieved through this spell " +
                "unless the caster has sufficient Gnosis.",
        "+1 to the base Regeneration Level",
        10,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val inorganicModification = Spell(
        "Inorganic Modification",
        Element.Creation,
        true,
        12,
        60,
        "Through control of magic, the caster can alter the form and nature of an inorganic " +
                "object, transforming it into a completely different thing but of similar spiritual " +
                "power. This permits the caster to transform something with a Presence of 20 or " +
                "less into something else of equal or lesser Presence.",
        "+5 to the maximum Presence that can be affected",
        10,
        20,
        false,
        listOf(SpellType.Effect)
    )

    private val increaseResistances = Spell(
        "Increase Resistances",
        Element.Creation,
        true,
        16,
        80,
        "Supernaturally increases all resistances by +10 (Disease, Magic, Physical, Psychic," +
                " and Venom) of an individual. Multiple castings of this spell on a single target" +
                " are not cumulative.",
        "+5 to all Resistances",
        10,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val royalShield = Spell(
        "Royal Shield",
        Element.Creation,
        false,
        18,
        40,
        "Forms a barrier of Energy that protects the caster from any source of attack. " +
                "The shield will absorb 500 points of damage before breaking.",
        "+150 Resistance Points",
        30,
        20,
        false,
        listOf(SpellType.Defense)
    )

    private val heal = Spell(
        "Heal",
        Element.Creation,
        true,
        20,
        80,
        "Causes whomever the spell is directed at to recover 50 Life Points. The spell does " +
                "not permit recovery of permanently lost or destroyed limbs, but does eliminate " +
                "the temporary penalties caused by Criticals, up to a quantity equivalent to half " +
                "the Life Points returned.",
        "+5 Life Points",
        10,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val damageBarrier = Spell(
        "Damage Barrier",
        Element.Creation,
        true,
        22,
        60,
        "The supernatural energies governed by this spell adhere to the body of the " +
                "designated individual, and provide him with certain immunity to damage. In " +
                "game terms, he obtains a Damage Barrier of 30 points (see Chapter 14).",
        "+5 to the Damage Barrier",
        10,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val homunculus = Spell(
        "Create Homunculus",
        Element.Creation,
        true,
        26,
        60,
        "Homunculus are small magical elementals that casters use at times for various " +
                "purposes, from espionage to housework. The spell creates a zero-level mystical " +
                "being under complete control of the caster, as per the rules in Chapter 26. " +
                "It is a Being Between Worlds with a Gnosis of 5, but given its minor nature, " +
                "the following rules apply: " +
                "\n1 - First of all, its characteristics can never be higher than 5, nor can it " +
                "have any Primary Ability or Secondary Ability higher than 50." +
                "\n2 - It also suffers a -2 penalty to size, and it cannot choose Uncommon Size." +
                "\n3 - It can\'t contain any Zeon points" +
                "\nEach Homunculus created might be completely different.",
        "+1 additional Homunculus",
        20,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val minorChange = Spell(
        "Minor Change",
        Element.Creation,
        true,
        28,
        60,
        "This spell permits altering the appearance of an object or being by modifying its " +
                "form. The change is limited to varying its exterior appearance, and never alters " +
                "its original qualities or nature. In this way, a shining short sword might " +
                "become old and rusty, but could never become a walking stick. In the case of " +
                "living things, this spell can change things by no more than two points of size, " +
                "perhaps making an ugly and overweight man into a young good looking one. The MR " +
                "Difficulty to avoid the change is 80. Regarding objects, once affected they have " +
                "no further right to make Resistance Checks, but people can repeat the check once " +
                "a day if they wish to be free of the effects. It is possible to affect various " +
                "targets, as long as the sum of all their Presences is no higher than 60.",
        "+5 to the MR and +10 to the maximum Presence that can be affected",
        10,
        10,
        true,
        listOf(SpellType.Spiritual)
    )

    private val imitate = Spell(
        "Imitate",
        Element.Creation,
        true,
        30,
        100,
        "Creates an exact copy of an already existing inorganic object within reach of the " +
                "caster. It creates a perfect imitation of the original that retains all its " +
                "qualities, including the supernatural ones. The copied object can not have " +
                "Presence greater than 30. The spell has no effect on magically animated " +
                "creatures, but can affect mechanical constructions as long as they have no soul.",
        "+5 to the maximum Presence that can be duplicated",
        20,
        20,
        true,
        listOf(SpellType.Effect)
    )

    private val immunity = Spell(
        "Immunity",
        Element.Creation,
        true,
        32,
        80,
        "This spell permits the caster, or those people designated by him, to be immune " +
                "to 5 Intensities of a given type of Energy, whether it is Fire, Electricity, " +
                "or Cold. If an attack is made using the type of Energy to which they are " +
                "immune, each intensity of immunity reduces the Base Damage by 5 points and " +
                "provides a +5 to the Resistances against its effects.",
        "+1 to the Intensity immunity",
        20,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val reduceDamage = Spell(
        "Reduction of Damage",
        Element.Creation,
        false,
        36,
        80,
        "The magic of this spell affects the power of an attack, reducing the force of its " +
                "impact. Upon being cast, this spell reduces the Base Damage of an attack by 40 " +
                "points. If the damage is lowered to zero, the attack produces no effect. If, for " +
                "example, this spell is used against an attack with Base Damage 60, when it " +
                "actually hits, it does so as if it were an attack of only 20. Reduction of " +
                "Damage must be cast before rolling any dice.",
        "-5 to the Base Damage of the attack",
        10,
        null,
        false,
        listOf(SpellType.Automatic)
    )

    private val physicalControl = Spell(
        "Physical Control",
        Element.Creation,
        true,
        38,
        120,
        "Using this spell, the caster is able to control the body of a being as though it " +
                "were a simple puppet. Since the control is purely physical, not spiritual, the " +
                "subject being controlled is not obliged to use any Supernatural Ability (Magic, " +
                "Ki, and Psychic Powers) against his will, although any natural fighting abilities " +
                "are under the caster\'s control. Resisting the spell requires beating a MR Check " +
                "with a Difficulty of 80. The person being controlled has the right to one new " +
                "Resistance Check per day, and also any time he receives orders that are entirely " +
                "against his nature.",
        "+5 to the MR",
        20,
        5,
        true,
        listOf(SpellType.Spiritual)
    )

    private val raiseAbilities = Spell(
        "Raise Abilities",
        Element.Creation,
        true,
        40,
        80,
        "This spell temporarily increases the Secondary Abilities of an individual, " +
                "providing him with a +40 bonus to divide freely among them, except for those " +
                "Abilities requiring knowledge.",
        "+10 to bonus",
        20,
        20,
        false,
        listOf(SpellType.Effect)
    )

    private val fuse = Spell(
        "Fuse",
        Element.Creation,
        true,
        42,
        140,
        "Unites two beings in a single body, creating a new individual who possesses the " +
                "characteristics and abilities of both. The caster designates which capacities " +
                "prevail, selecting those that he is interested in from each. If the spell joins " +
                "a fighter and a psychic, for example, the resulting individual could have the " +
                "fighting ability of the warrior, the mental powers of the mentalist, and the " +
                "highest Secondary Abilities of each one. Control of the resulting united body " +
                "falls to whichever of the two wins an Opposed Check of Willpower, although some " +
                "characteristics typical of the other personality can be retained. The original " +
                "bodies remain in the state they were in before the fusion and so, when the spell " +
                "lapses, return to the state and condition they were in before it was cast. The " +
                "death of the joint entity causes the death of both original people. The total " +
                "Presence of both originals cannot be more than 80. It is also possible to Fuse " +
                "an individual with objects, in which case the GM can award the result the " +
                "advantages and abilities he considers appropriate.",
        "+5 to the MR and +5 to the maximum Presence affected",
        20,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val createMemories = Spell(
        "Create Memories",
        Element.Creation,
        true,
        46,
        140,
        "Permits creation of new memories in the target, without necessarily erasing the " +
                "pre-existing ones. Most of the time, unless very deeply-rooted memories are " +
                "modified, the person affected will feel slightly confused, but will be " +
                "convinced that his memories are real. The caster determines what information " +
                "he is trying to introduce, regardless of its complexity or duration. Resisting " +
                "the spell requires beating a MR or PsR Check with a Difficulty of 100. Although " +
                "this spell doesn\'t require maintenance, the individual affected has the right to " +
                "a new Resistance Check any time something makes him suspect his memory might be inaccurate.",
        "+5 to the MR or PsR Difficulty",
        20,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val recover = Spell(
        "Recover",
        Element.Creation,
        true,
        48,
        150,
        "This spell completely restores the physical condition of the subject, curing up " +
                "to 300 Life Points of damage and removing all physical penalties, unless caused " +
                "by amputations or similar losses. This spell does not restore Fatigue Points, " +
                "nor eliminate negative States caused by supernatural means.",
        "+20 additional Life Points",
        20,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val acquirePowers = Spell(
        "Acquire Powers",
        Element.Creation,
        true,
        50,
        100,
        "Gives the caster, or the person on whom the spell is cast, the ability to acquire " +
                "the abilities of supernatural beings. In game terms, it gives the character 100 " +
                "additional DP with which to acquire any of the Powers listed in Chapter 26, as " +
                "though they had a Gnosis of 25. These also temporarily affect their physical " +
                "form. If, for example, someone obtains Natural Flight, it would be logical for " +
                "him to have developed enormous wings. The effects of this spell are not " +
                "cumulative and a subject can only be affected by one Acquire Powers at a time.",
        "+10 DP with which to acquire powers",
        20,
        5,
        false,
        listOf(SpellType.Effect)
    )

    private val createMonstrosity = Spell(
        "Create Monstrosity",
        Element.Creation,
        true,
        52,
        80,
        "Creates a magical being with the appearance of life, but completely under the " +
                "control of the caster. The being is developed as a Being Between Worlds using the " +
                "rules described in Chapter 26. The creature is first-level with the equivalent of " +
                "Gnosis 20. The spell does not permit the creation of a creature of higher level " +
                "than the caster, since it is based on the caster\'s spiritual presence. The " +
                "Monstrosity is not able to receive a soul, so it is not possible to give it independent life.",
        "+1 level",
        10,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val protectiveAura = Spell(
        "Protective Aura",
        Element.Creation,
        false,
        56,
        120,
        "Supernaturally increases all the Resistances (VR, PhR, DR, MR, and PsR) of all " +
                "the designated individuals within 500 meters of the caster by +20. The effects " +
                "of this spell are not cumulative, and a subject can only be affected by one " +
                "Protective Aura at a time.",
        "+5 to the Resistances and +50 meters to radius",
        30,
        20,
        false,
        listOf(SpellType.Effect)
    )

    private val spiritualStandstill = Spell(
        "Spiritual Standstill",
        Element.Creation,
        true,
        58,
        150,
        "As the name indicates, this spell preserves the spirit of the individual on whom " +
                "it is cast in the same state as at the moment it is cast. From that moment on, " +
                "the affected person does not make any further MR or PsR Checks, and remains " +
                "inalterably in the same spiritual condition. The person cannot be affected by " +
                "States of any kind, either positive or negative. The subject can resist this " +
                "spell by passing a MR Check with a Difficulty of 100. Naturally, the person " +
                "affected by this spell cannot make any type of Check to free himself of it " +
                "while it is in effect.",
        "+5 to the MR Difficulty",
        20,
        10,
        false,
        listOf(SpellType.Spiritual)
    )

    private val perfectShield = Spell(
        "Perfect Shield",
        Element.Creation,
        true,
        60,
        150,
        "Forms a barrier of Energy that protects from any source of attack. The shield can " +
                "absorb 100 points of damage before breaking. If it is not destroyed by the end " +
                "of the combat turn, it automatically recovers all lost Resistance Points.",
        "+20 Resistance Points",
        20,
        10,
        true,
        listOf(SpellType.Defense)
    )

    private val vitality = Spell(
        "Vitality",
        Element.Creation,
        true,
        62,
        150,
        "This spell creates a state of supernatural vitality in an individual, increasing " +
                "his maximum Life Points by +50 while the spell is maintained. The effects of " +
                "this spell are not cumulative, and only one casting can affect a subject at a " +
                "time. Beings with Damage Resistance apply this quantity to their Damage " +
                "Resistance multiple.",
        "+5 to maximum Life Points",
        20,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val completeCreation = Spell(
        "Complete Creation",
        Element.Creation,
        true,
        66,
        150,
        "Creates an object with a Presence of no more than 50. The creation must appear in a " +
                "location that is logical for it, according to its nature. It would not be " +
                "possible, for instance, to conjure up a small mountain in mid-air, and then let " +
                "it fall on someone. As a limit, the Presence of the object cannot be more than " +
                "twice the Base Presence of the caster. For example, a fifth-level character with " +
                "a Presence of 50 could therefore create objects with a maximum Presence of 100 " +
                "(if he pays enough Added Effects, of course).",
        "+5 to the maximum Presence of the object created",
        20,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val reinforceMagic = Spell(
        "Reinforce Magic",
        Element.Creation,
        false,
        68,
        100,
        "Reinforce Magic adds strength to another spell launched either at the same time " +
                "or that is being maintained. In game terms, it adds an Added Effect to the " +
                "Zeon value of the Strengthened spell. Once the spell lapses, the added power disappears.",
        "+1 Added Effect",
        10,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val transmute = Spell(
        "Transmute",
        Element.Creation,
        true,
        70,
        250,
        "The caster transforms an inorganic object, converting it to a different one of " +
                "similar spiritual power. This permits the caster to modify something with a " +
                "Presence of 50 or less into something else of equal or lesser Presence. If the " +
                "object is supernatural, or especially powerful, it can reset by passing a MR " +
                "Check with a Difficulty of 120. If the transmuted Presence is over 100, two " +
                "Added Effects are necessary in place of one to increase the maximum value that " +
                "can be affected by 5 points.",
        "+5 to the MR Difficulty and +5 to the maximum Presence that can be affected",
        30,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val metamorphism = Spell(
        "Metamorphism",
        Element.Creation,
        true,
        72,
        150,
        "Using this spell, the caster can transform a person or object into anything he " +
                "wishes (a mouse, a statue, etc.) as long as the Presence of the new form is " +
                "not higher than the target\'s original Presence. Living things metamorphized " +
                "into non-living objects continue to be alive in their new forms even if it " +
                "happens they cannot move or breathe. When the spell lapses, the individual or " +
                "object returns to its original form. Metamorphism does not permit the " +
                "acquisition of essential powers or abilities, except those possessed by natural " +
                "living creatures with a Gnosis of zero. Resisting this spell requires passing a " +
                "MR Check with a Difficulty of 120. It is only possible to repeat the Resistance " +
                "check once per day.",
        "+5 to the MR",
        20,
        20,
        true,
        listOf(SpellType.Spiritual)
    )

    private val recreate = Spell(
        "Recreate",
        Element.Creation,
        true,
        76,
        300,
        "Manipulating the essence of both matter and soul, the caster is able to restore " +
                "any damage or loss an individual or object might have suffered. The caster can " +
                "decide which parts to recreate, and is not obligated to restore the target to " +
                "its original state. If he uses the spell on a one-eyed, one-armed person, the " +
                "caster can Recreate only the eye, only the arm, or both of them at once. Casting " +
                "this spell on a wounded individual or a damaged object makes any damage or " +
                "penalty disappear automatically (as long as the caster wishes it to, of course). " +
                "Recreate also eliminates the effects of any Supernatural Abilities that have " +
                "diminished the individual or object physically or spiritually; it restores " +
                "lost memories or DP.\nThis spell has only two limitations. The first is that " +
                "it affects only losses or damage suffered artificially, not due to natural or " +
                "positive change. It therefore cannot \"restore\" a person to an earlier state " +
                "that was damaged or inferior. Secondly, it has no jurisdiction over deceased " +
                "souls, or those that have passed into the Flow, so is unable to return the dead " +
                "to life. The maximum Presence that can be affected is 60.",
        "+5 to the maximum Presence that can be affected",
        40,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val createBeing = Spell(
        "Create Being",
        Element.Creation,
        true,
        78,
        250,
        "Create a creature with the appearance of life, but completely under the control " +
                "of the caster. The entity is developed as a Being Between Worlds, with 600 DP " +
                "and a Gnosis of 25. Given that their existence is directly tied to the soul of " +
                "the caster, the number of beings that are created by and that depend on him " +
                "(whether through this spell or the Level 82 spells of the minor Paths) will " +
                "limit the maximum level of the entity. The creature can therefore have at most " +
                "the level of its creator minus the number of entities he has magically created " +
                "and is maintaining. For example, if the caster creates only a single being, it " +
                "can have at most one level less than its creator, two levels less if there are " +
                "two beings, three less if he is maintaining three, etc. If one or more beings " +
                "were already created at the highest possible level for them, and the caster " +
                "thereafter creates another, the earlier ones automatically drop one level " +
                "immediately. The level of the entity is measured by the quantity of DP it " +
                "possesses; a creature with 850 DP, for instance, would be a level 4 creature.",
        "+50 DP",
        30,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val chimera = Spell(
        "Chimera",
        Element.Creation,
        true,
        80,
        250,
        "The caster, or whomever he designates, ceases to be a Natural being to become a " +
                "Being Between Worlds with a Gnosis of 25, enjoying all the advantages and " +
                "disadvantages that implies. He also obtains 150 additional DP to use to choose " +
                "between Essential Abilities and powers from the Creation of Beings, described " +
                "in Chapter 26. It also permits him to choose up to 50 DP in Disadvantages or " +
                "penalties to obtain additional points. Excess DP raises the Character Level. " +
                "In case of partial improvement, if for instance he only gets 170 DP, the " +
                "character still improves two levels, but when he goes up another level he will " +
                "receive 30 additional DP to use. This spell only works on Natural creatures, and " +
                "so cannot be cast on Beings Between Worlds or Spirits to increase their " +
                "abilities.\nAll the powers and gifts acquired through this spell depend directly " +
                "on the physical body of the individual, and so if by any means he returns to his " +
                "prior state, or his soul transmigrates to another body or form, he automatically " +
                "loses the advantages received.",
        "+10 DP and +5 DP in optional disadvantages",
        20,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val safetyZone = Spell(
        "Zone of Safety",
        Element.Creation,
        true,
        82,
        350,
        "Creates a magical zone inside which no person or object can be harmed in any way " +
                "by anyone or anything. A person within the zone could not, for instance, break " +
                "down a door or even harm a cockroach by stepping on it by mistake. Zone of " +
                "Safety automatically affects anyone within the area, including the caster. The " +
                "affected area has a radius of 100 meters and remains where it was cast without " +
                "being movable by the caster. To overcome the effect requires beating a MR Check " +
                "with a Difficulty of 120. It is only possible to repeat the Resistance check " +
                "once per day, even if someone has left the zone and then returned.",
        "+5 to the MR Difficulty and +50 meters in radius",
        30,
        10,
        true,
        listOf(SpellType.Automatic)
    )

    private val maintainMagic = Spell(
        "Maintain Magic",
        Element.Creation,
        true,
        86,
        250,
        "Affects an existing active spell, tying it to the world in a more lasting way. In " +
                "game terms, it adds 500 points of Zeon to keep up maintenance of the designated " +
                "spell. Remember these points do not add to the spell\'s power, only to its maintenance.",
        "+50 toward the Maintenance of the designated spell",
        40,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val provideSoul = Spell(
        "Provide Soul",
        Element.Creation,
        true,
        88,
        500,
        "Through this spell, the caster is able to create a complete soul, giving the " +
                "breath of life to an object or body able to contain one. If the soul is " +
                "provided to a magically engendered creature such as those developed with the " +
                "level 78 spell of this Path, or with the level 82 elemental spells, Provide " +
                "Soul makes it unnecessary to continue maintaining those spells to sustain the " +
                "creature. The creature thereby breaks any bond it has with its creator, " +
                "obtaining free will as just one more Being Between Worlds.\nNot all souls " +
                "created by this spell are capable of entering the body into which they are " +
                "introduced. It is possible to provide souls to bodies or objects with a Presence " +
                "of no more than 30. It cannot be used to provide a soul to something that " +
                "already has a living soul.",
        "+5 to the maximum Presence that can be affected",
        40,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val greaterCreation = Spell(
        "Greater Creation",
        Element.Creation,
        true,
        90,
        400,
        "Gives the caster the gift of creating at whim, awarding him 500 Presence points " +
                "to distribute freely between various objects or constrictions. He can create any " +
                "inanimate object he wishes, from castles to mountains, as long as the Presence " +
                "of each of his creations is not greater than 180.",
        "+50 additional Presence points to share out",
        40,
        20,
        true,
        listOf(SpellType.Effect)
    )

    private val eternalMagic = Spell(
        "Eternal Magic",
        Element.Creation,
        true,
        92,
        600,
        "As the name indicates, this spell affects another existing one, enormously " +
                "reducing (or even eliminating) its Maintenance Cost. Eternal Magic\'s effects vary " +
                "according to whether or not it affects a spell with Daily maintenance. If the " +
                "spell in question is Daily, Eternal Magic stabilizes it, making maintenance " +
                "unnecessary. The spell continues being controlled by its caster, but it will " +
                "continue in effect even after his death. If the spell\'s maintenance requirement " +
                "is \"per Combat turn,\" it becomes one requiring only Daily maintenance. " +
                "Eternal Magic only influences spells with a Zeonic value 100 or less and that " +
                "have a Path Level less than 80.",
        "+10 to its maximum Zeon value",
        50,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val barrierSpell = Spell(
        "The Barrier",
        Element.Creation,
        true,
        96,
        800,
        "This unusual spell forms a breach in reality that is capable of separating the " +
                "world into two parts. This separation cannot be perceived naturally, even by " +
                "those capable of seeing magic. It may adopt any appearance, from being " +
                "completely invisible to looking like a simple stone wall. Whatever form it " +
                "takes, it does not permit anyone so much as a glimpse of what hides beyond it. " +
                "Usually none are aware of its existence, even when attempting to go through it. " +
                "If someone attempts to cross it, they exit at another point of The Barrier " +
                "completely unaware of what is behind (it is even possible to turn directly " +
                "around without noting it). Transport is not automatic, rather little by " +
                "little, reality starts adapting to the exit location almost imperceptibly " +
                "(Inhuman Difficulty for Notice). As an example, The Barrier can be used over " +
                "an island, to separate it from the rest of the world. That location disappears " +
                "from view, and any ship sailing in the area will see only water, being " +
                "transported immediately to another place to innocently continue their voyage. " +
                "The Barrier need not be made absolutely perfect. The caster can choose to " +
                "deliberately leave passages or openings, in one or both directions, to use for " +
                "crossing. There are certain requirements for someone to be able to pass through " +
                "it. To start with, it must be someone with a Gnosis greater than 25 who knows of " +
                "The Barrier and its exact location. If both conditions are fulfilled, he must " +
                "pass a MR Check with a Difficulty of 120 to get through. If he fails, he can " +
                "only repeat the check once per day.\nThe spell can affect the territory of 100 " +
                "square kilometers, or be extended in a line of the same length. Once cast, it " +
                "remains in the same location.",
        "+5 to the MR Difficulty and +100 km",
        50,
        20,
        true,
        listOf(SpellType.Effect)
    )

    private val giftOfLife = Spell(
        "The Gift of Life",
        Element.Creation,
        true,
        98,
        800,
        "The caster is imbued with the ability to create a new form of life, the newborn of " +
                "a race that never existed before. The creation can be of any class (Natural, " +
                "Spiritual, or Between Worlds) and can be tied to a particular element. The " +
                "maximum level of the creature cannot be higher than that of his creator. The " +
                "creator should choose the Gnosis of his creation, awarding it no more than ten " +
                "points less than his own.\nIn the beginning it will be a first level creature, " +
                "although if it is a Natural Being with a Gnosis of 20 or less, it will be given " +
                "50 DP with which to select additional racial powers and abilities, employing " +
                "the rules described in Chapter 26.",
        "+1 to the level if the creation is a Between Worlds or Spiritual Being and " +
                "+10 DP if it is a Natural Being",
        50,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val createSpell = Spell(
        "Create",
        Element.Creation,
        true,
        100,
        1000,
        "Permits the caster to obtain absolute power over creation, giving him the ability " +
                "to create anything he wishes: Continents, oceans, and even entire worlds. In " +
                "game terms, it grants 5,000 Presence Points to create whatever the caster " +
                "wishes, as long as it doesn\'t exceed 350 points of presence.\nThis spell can " +
                "also impose new rules of reality: alter gravity, modify the rate at which time " +
                "passes, and so forth. Anyone with a Gnosis of less than half that of the caster " +
                "is affected by such changes.",
        "+1000 Presence Points",
        50,
        null,
        false,
        listOf(SpellType.Effect)
    )

    val fullBook = listOf(
        minorCreation,
        null,
        reconstruct,
        createEnergy,
        regeneration,
        inorganicModification,
        null,
        increaseResistances,
        royalShield,
        heal,
        damageBarrier,
        null,
        homunculus,
        minorChange,
        imitate,
        immunity,
        null,
        reduceDamage,
        physicalControl,
        raiseAbilities,
        fuse,
        null,
        createMemories,
        recover,
        acquirePowers,
        createMonstrosity,
        null,
        protectiveAura,
        spiritualStandstill,
        perfectShield,
        vitality,
        null,
        completeCreation,
        reinforceMagic,
        transmute,
        metamorphism,
        null,
        recreate,
        createBeing,
        chimera,
        safetyZone,
        null,
        maintainMagic,
        provideSoul,
        greaterCreation,
        eternalMagic,
        null,
        barrierSpell,
        giftOfLife,
        createSpell
    )
}