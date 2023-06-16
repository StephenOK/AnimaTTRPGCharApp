package com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook

import com.paetus.animaCharCreator.character_creation.Element
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.Spell
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.SpellType

/**
 * List of spells associated with the dark element.
 */
class DarkBook{
    private val createDark = Spell(
        "Create Darkness",
        Element.Dark,
        true,
        2,
        20,
        "Completely darkens the area within a 5-meter radius. Everything within the area is " +
                "perceived as though on a dark and moonless night.",
        "+5 meters to radius",
        20,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val induceFear = Spell(
        "Induce Fear",
        Element.Dark,
        true,
        6,
        40,
        "Temporarily causes a Fear State in all people within 15-meters of the caster. The " +
                "spellcaster decides what the victims are afraid of. The MR or PsR Check to " +
                "overcome this spell has a Difficulty of 80.",
        "+5 to MR or PsR Difficulty and +10 meters radius",
        10,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val seeInDarkness = Spell(
        "See in Darkness",
        Element.Dark,
        true,
        8,
        40,
        "Permits the caster, or anyone he selects, to see perfectly in the dark. This spell " +
                "can affect as many targets as desired as long as their accumulated Presence is no " +
                "higher than 80.",
        "+5 to the maximum Presence that can be affected",
        10,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val darkShield = Spell(
        "Shield of Darkness",
        Element.Dark,
        false,
        10,
        50,
        "Forms a barrier of Energy that protects from any source of attack. The shield can " +
                "absorb up to 300 points before breaking, but is only damaged by supernatural " +
                "attacks. Impacts based on Light cause it double damage",
        "+100 Resistance Points",
        20,
        10,
        false,
        listOf(SpellType.Defense)
    )

    private val shadow = Spell(
        "Shadow",
        Element.Dark,
        false,
        12,
        50,
        "This spell increases the caster\'s ability to conceal, boosting his Secondary " +
                "Abilities of Stealth and Hide by +50. It also increases his Magic Appraisal by " +
                "+50, but only for the purpose of hiding the magical potency of something or " +
                "someone, not to detect it.",
        "+10 Stealth, Hide, Magic Appraisal",
        20,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val darkArmor = Spell(
        "Armor of Darkness",
        Element.Dark,
        true,
        16,
        60,
        "Forms a mystical armor with AT 2 against Energy-based Attacks, and an AT of 1 " +
                "against all others. Although it counts as armor, it does not count as an " +
                "additional layer of armor for purposes of penalties to Initiative.",
        "+1 to AT",
        10,
        20,
        false,
        listOf(SpellType.Effect)
    )

    private val banishLight = Spell(
        "Banish Light",
        Element.Dark,
        true,
        18,
        60,
        "Destroys ambient light within a radius of 10 meters. Any Light-based creatures " +
                "within the radius must pass a MR Check with a Difficulty of 120, or lose double " +
                "their Failure level in Life Points (Damage Resistance creatures increase this " +
                "amount by its Damage Resistance multiple). As long as the spell is maintained " +
                "over the creatures, they must make a new MR Check each combat turn.",
        "+10 to the MR Difficulty and +20 meters to radius",
        10,
        10,
        false,
        listOf(SpellType.Spiritual, SpellType.Effect)
    )

    private val hideMagic = Spell(
        "Hide Magic",
        Element.Dark,
        false,
        20,
        50,
        "Hides a spell, or the mystical properties of an object, from any type of magical " +
                "detection. In game terms, it produces a -80 penalty to the Magic Appraisal spell " +
                "of anyone attempting to to detect or measure a spell or object that has been " +
                "hidden (it also hides the Hide Magic spell itself).",
        "-10 to Magic Appraisal",
        20,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val darkBeam = Spell(
        "Dark Beam",
        Element.Dark,
        true,
        22,
        50,
        "Projects a beam of Dark-based magical energy. Dark Beam is an Energy Attack Type " +
                "with a Base Damage of 60.",
        "+5 to Base Damage",
        10,
        null,
        false,
        listOf(SpellType.Attack)
    )

    private val darkzone = Spell(
        "Darkzone",
        Element.Dark,
        true,
        26,
        60,
        "Creates a mystical environment that clouds the senses of anyone within it. The " +
                "Darkzone increases the difficulty of any Perceptive check within it by 2 levels. " +
                "This ability also affects Ki Detection. The spell has a radius of 20 meters, and " +
                "it is not possible to designate targets within it. No Resistance is possible.",
        "+20 meters to radius",
        20,
        20,
        false,
        listOf(SpellType.Effect)
    )

    private val darkBond = Spell(
        "Bonds of Darkness",
        Element.Dark,
        true,
        28,
        60,
        "This spell casts bonds of darkness that hold the designated target immobile. An " +
                "attack is made using the rules for Trapping, although the caster suffers no " +
                "penalty to his Ability for performing this maneuver. The bonds use a Strength of " +
                "8 for any Check. If anyone tries to help free the person Trapped, the Bonds of " +
                "Darkness are treated as an Energy weapon with a Fortitude of 20.",
        "Strength +1",
        10,
        10,
        false,
        listOf(SpellType.Attack)
    )

    private val controlDark = Spell(
        "Control Darkness",
        Element.Dark,
        true,
        30,
        50,
        "This spell modifies and controls the intensity of darkness within a radius of 20 " +
                "meters. If cast at Darkness-based beings, they must pass a MR Check with a " +
                "Difficulty of 80 or they will fall under the control of the caster. A creature " +
                "can only repeat the check if it is ordered to do something against its nature.",
        "+10 meters to radius and +5 to MR Difficulty",
        20,
        10,
        false,
        listOf(SpellType.Spiritual, SpellType.Effect)
    )

    private val concealment = Spell(
        "Concealment",
        Element.Dark,
        false,
        32,
        60,
        "Conceals the Presence of the caster or a target designated by him from any type of " +
                "detection. In game terms, it increases the resistance to supernatural (mystical " +
                "or psychic) detection by +40. This ability also increases the Ki Concealment of " +
                "the person by 40 points, permitting him to hide his energy even if he has not " +
                "developed that ability.",
        "+10 to Resistance against detections and +10 to Ki Concealment",
        20,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val obfuscate = Spell(
        "Obfuscate",
        Element.Dark,
        true,
        36,
        100,
        "The caster modifies the body of an individual, blending him in to the background and " +
                "permitting him to hide himself naturally. While the spell is active, the person " +
                "can exchange his own level of ability at Stealth and Hide for a base of 100. " +
                "It also offers the same level of ability at Ki Concealment, even if he has not " +
                "developed that ability. Remember that these numbers are not added to the person\'s " +
                "Secondary Ability, but are substituted for them if they are lower.",
        "+5 Stealth, Hide, and Ki Concealment",
        20,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val enrage = Spell(
        "Enrage",
        Element.Dark,
        true,
        38,
        60,
        "Provokes a Rage State in those affected, making them lose control and attack the " +
                "person closest to them. The spell\'s victim applies a bonus of +10 to their " +
                "offensive abilities and a -30 to all other checks. Enrage has a radius of 5 " +
                "meters and can be resisted by beating a MR Check with a Difficulty of 80.",
        "+5 meters to radius and +5 to the MR Difficulty",
        10,
        10,
        false,
        listOf(SpellType.Spiritual)
    )

    private val banishPositiveEmotions = Spell(
        "Banish Positive Emotions",
        Element.Dark,
        true,
        40,
        80,
        "Temporarily banishes any positive sentiments such as inner peace, calm, or joy " +
                "within 100 meters of the caster. Resisting this spell requires beating a MR or " +
                "PsR Check with a Difficulty of 100.",
        "+45 meters to radius and +5 to MR or PsR Difficulty",
        20,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val night = Spell(
        "Night",
        Element.Dark,
        true,
        42,
        80,
        "Forms a dome of darkness with a maximum radius of 25 meters. Everyone in the " +
                "interior area, except the caster, is subject to the rules for Vision Totally " +
                "Obscured. To see through the darkness, whether from inside or outside the dome, " +
                "requires a difficulty check of Inhuman in Notice, or Almost Impossible in Search.",
        "+25 meters to radius",
        20,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val darkSphere = Spell(
        "Dark Sphere",
        Element.Dark,
        true,
        46,
        120,
        "Unleashes a sphere of Dark energy with Base Damage of 100. The caster can control " +
                "it using his Magic Projection until it hits the target. If the target " +
                "successfully dodges, the Dark Sphere can continue attacking the following turn, " +
                "since it has not been destroyed. When Dark Sphere causes damage, or is blocked, " +
                "the sphere explodes and vanishes. If the caster abandons control of it, it will " +
                "act independently, following its last designated target with a Magic Projection " +
                "of 150.",
        "+5 to Base Damage and +5 to the Magic Projection of the Dark Sphere",
        20,
        10,
        false,
        listOf(SpellType.Attack)
    )

    private val concealZone = Spell(
        "Zone of Concealment",
        Element.Dark,
        true,
        48,
        140,
        "By casting this spell over a particular zone, the spellcaster creates a mystical " +
                "area that impedes the detection of anything within it. Anything within the area " +
                "is concealed, with a MR Check against a value of 180 to avoid detections. " +
                "Additionally, any Ki Detection or Magic Appraisal used within the zone suffers " +
                "a -140 penalty to its Final Result. Similarly, any type of psychic detection is " +
                "subject to a -40 penalty to its Potential. The affected zone can be no larger " +
                "than 20 meters in radius, and is stationary in the place it was cast.",
        "+10 meters to radius, +10 to MR, and -10 to Ki Detection, -10 Magic Appraisal, " +
                "and -10 to the potential of any Psychic detection",
        20,
        20,
        true,
        listOf(SpellType.Detection)
    )

    private val enterNightmare = Spell(
        "Enter Another\'s Nightmares",
        Element.Dark,
        true,
        50,
        120,
        "This allows the caster to physically enter a sleeper\'s nightmares. The " +
                "spellcaster has no control over the dream world of the dreamer, and anything " +
                "that happen there will be real to him. The person must have terrible nightmares " +
                "to be affected by this spell, and the moment the dream turns into a peaceful " +
                "dream, or he awakens or dies, the mage abandons the dream world and returns to " +
                "reality. Any Spiritual spell cast on the dreamer will also affect the caster. " +
                "The MR or PsR Check for the sleeper has a has a Difficulty of 140. Once he is in " +
                "the other person\'s nightmares, the caster can jump to the unconscious of yet " +
                "another dreamer who is physically no more than 10 meters from the original " +
                "sleeper. Naturally, this new sleeper will have the right to his own MR Check. " +
                "If the dreamer\'s consciousness happens to be in the world of the Wake, the " +
                "caster is trapped there even when the spell expires.",
        "+5 to MR or PsR Difficulty, +10 meters to jumping range",
        20,
        50,
        true,
        listOf(SpellType.Spiritual)
    )

    private val darkForm = Spell(
        "Dark Form",
        Element.Dark,
        true,
        52,
        100,
        "The body designated by the caster is transformed to pure Dark energy and becomes " +
                "intangible to matter and non-Energy attacks. While in this state, the transformed " +
                "person gains a +50 bonus to his abilities of Stealth and Hide, and a +30 to his " +
                "Resistance against effects based on Darkness. In this state, the damage caused " +
                "by any attacks based on Light are doubled. The maximum Presence that can be affected is 100.",
        "+10 to the maximum Presence that can be affected",
        10,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val perdition = Spell(
        "Perdition",
        Element.Dark,
        true,
        56,
        100,
        "Perdition causes a disturbed feeling among those affected, reducing their " +
                "abilities. Those within its area of influence must pass a MR Check of with a " +
                "Difficulty of 120, or else receive a -40 All Action Penalty. The area covered is " +
                "that within 5 meters of the caster, automatically affecting anyone within the " +
                "radius. The penalties for Perdition are not cumulative, and so no one ever " +
                "suffers a double penalty if in the area of influence of more than one such spell.",
        "+5 meters to radius and +5 to the MR Difficulty",
        20,
        20,
        false,
        listOf(SpellType.Automatic)
    )

    private val createNegativeFeelings = Spell(
        "Create Negative Feelings",
        Element.Dark,
        true,
        58,
        100,
        "This creates negative sentiments such as hatred, fear, or anger in the people " +
                "designated by the caster. The radius of the spell is 20 meters, and the MR or " +
                "PsR Check to resist the effects has a Difficulty of 120. Those affected can " +
                "repeat the Resistance Check once per day.",
        "+10 meters to radius and +5 to MR or PsR Difficulty",
        20,
        10,
        true,
        listOf(SpellType.Spiritual)
    )

    private val eliminateTraces = Spell(
        "Eliminate Traces",
        Element.Dark,
        true,
        60,
        100,
        "The caster can erase the evidence that he has been in a certain place. In fact, " +
                "any sign whatsoever of his passing, whether detectable naturally or by " +
                "supernatural means, disappears completely. This spell even provides immunity " +
                "from spells that permit someone to see the past of a place or an object, hiding " +
                "the presence of the caster of Eliminate Traces from the recording of past " +
                "events. The only way to overcome the effects of this spell is by achieving a Zen " +
                "level at Track. The spell erases all such traces within a 50-meter radius.",
        "+50 meters to radius",
        20,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val shieldFromPositive = Spell(
        "Shield From Positive",
        Element.Dark,
        true,
        62,
        140,
        "This spell enchants a certain area, making it impenetrable for beings naturally " +
                "based in positive emotions or Light. Any such creature entering the zone must " +
                "pass a MR Check with a Difficulty of 120 or suffer the loss of a number of Life " +
                "Points equal to the margin of failure. Additionally, if the creature fails the " +
                "check, he receives an immediate -40 All Action Penalty. The affected zone can be " +
                "no larger than 20 meters in radius, and is stationary in the place it was cast.",
        "+20 meters to radius and +5 to the MR Difficulty",
        20,
        10,
        true,
        listOf(SpellType.Automatic)
    )

    private val devouringDark = Spell(
        "Devouring Dark",
        Element.Dark,
        false,
        66,
        120,
        "Creates a shadow shield with the ability to swallow any attack spell received. For " +
                "the defense against an attacking spell to work, it is necessary to achieve a " +
                "Block against the attack. If the defender does achieve it, the attacking spell " +
                "must then lose a Clashing Spells Check against a damage of 80. The void absorbs " +
                "half the Zeon of the devoured spell, giving those points to the caster. It is " +
                "also possible for it to devour Area Attacks. The shield will resist 600 points " +
                "of damage before breaking.",
        "+5 to damage for Clashing Spells and +100 Resistance Points",
        20,
        20,
        false,
        listOf(SpellType.Defense)
    )

    private val devastate = Spell(
        "Devastate",
        Element.Dark,
        true,
        68,
        100,
        "Causes penalties through and accumulation of pain, suffering, misery, and other " +
                "negative mystical effects. The affected person must pass a MR Check with a " +
                "Difficulty of 120 or suffer an All Action Penalty and a penalty to his " +
                "Resistances equivalent to the margin of failure of the Check. If he does not " +
                "pass the Check, the target of Devastate does not have the right to a new check " +
                "for as long as the spell is maintained.",
        "+5 to the MR Difficulty",
        20,
        10,
        false,
        listOf(SpellType.Spiritual)
    )

    private val markOfFear = Spell(
        "Mark of Fear",
        Element.Dark,
        true,
        70,
        140,
        "Forms a display of shadows in a designated spot that terrorizes all who see it. " +
                "Anyone seeing the Mark of Fear suffers a Terror State unless they beat MR or PsR " +
                "Check with a Difficulty of 120. The spell is visible for a radius of one " +
                "kilometer. The condition for being affected is merely looking directly at the Mark of Fear.",
        "+1 km to radius and -5 to MR or PsR Difficulty",
        20,
        50,
        false,
        listOf(SpellType.Automatic)
    )

    private val catastrophicDarkness = Spell(
        "Catastrophic Darkness",
        Element.Dark,
        true,
        72,
        120,
        "Creates a deadly discharge of Darkness with a Bases Damage of 150. The Catastrophic " +
                "Darkness has a radius of 25 meters and is an Energy Attack Type spell.",
        "+5 Base Damage and +10 meters to radius",
        20,
        null,
        false,
        listOf(SpellType.Attack)
    )

    private val darkMaterialObjects = Spell(
        "Dark Material Objects",
        Element.Dark,
        true,
        76,
        150,
        "Forms a material object from Dark energy. Whether it is something as complex as a " +
                "clock, or as simple as a sword, the object created cannot have a Presence of more " +
                "than 60, but for all purposes it is treated as having a quality of +10. As an " +
                "exceptional rule, the quality of the object does not affect its Presence.",
        "+10 to the object\'s Presence",
        20,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val shadowTravel = Spell(
        "Travel by Shadow",
        Element.Dark,
        true,
        78,
        250,
        "Transports individuals or objects designated by the caster from one shadow to " +
                "another that must be less than 100 kilometers away. The quantity of Presence that can " +
                "be transported cannot be greater than 250. If someone wishes to resist, the MR " +
                "Check has a Difficulty of 120.",
        "+5 to MR Difficulty, +20 to transportable Presence, and +100 kilometers",
        30,
        null,
        false,
        listOf(SpellType.Spiritual, SpellType.Effect)
    )

    private val nightmareLord = Spell(
        "Lord of Nightmares",
        Element.Dark,
        true,
        80,
        300,
        "Permits the control of any type of nightmare. The caster has the ability to control " +
                "a sleeper\'s dream world, modifying it as if he had a Gnosis of 45 (although is " +
                "not real). If the dream is fed by positive energy, in other words, if it stops " +
                "being a nightmare, his Gnosis becomes only 30. If the dreamer wishes to resist " +
                "the spell, it requires beating a MR Check with a Difficulty of 140.\nThis spell " +
                "also has a second use, depending on whether or not the caster is in the Wake. " +
                "Within the Wake, the caster can control his surroundings and acquires the " +
                "powers of a creature with a Gnosis of 40, as long as he is in an area strongly " +
                "influenced by negative energies. If he is in an area of neutral energies, he " +
                "is treated as having a Gnosis of 30.",
        "+5 to the MR Difficulty",
        20,
        5,
        true,
        listOf(SpellType.Spiritual, SpellType.Effect)
    )

    private val createDarkBeing = Spell(
        "Create Being of Darkness",
        Element.Dark,
        true,
        82,
        250,
        "Creates a Dark being with the appearance of life, but completely under the control " +
                "of the caster. The entity is developed as a Being Between Worlds using the " +
                "powers and limitations of Dark Elementals explained in Chapter 26. The being " +
                "has 600 DP, and to calculate its maximum level the same rules are used as for " +
                "Create Being from the Path of Creation.",
        "+50 DP",
        30,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val concealmentFromMagic = Spell(
        "Concealment from Magic",
        Element.Dark,
        false,
        86,
        200,
        "Hides the presence of the individual from Automatic spells, causing him to be " +
                "directly unaffected by them. As an exception to the general rule, an opposing " +
                "caster maintaining any Automatic spell must succeed in fixing his Magic " +
                "Projection on the caster who is defending himself using Concealment from Magic, " +
                "as though the Automatic spell were a Spiritual one (even if the caster of " +
                "Concealment from Magic meets the condition for being subject to the Automatic " +
                "spell). The maximum Presence that can be affected is 80.",
        "+5 to the maximum Presence that can be affected",
        20,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val darknessKingdom = Spell(
        "Kingdom of Darkness",
        Element.Dark,
        true,
        88,
        200,
        "Creates a 50-meter radius zone around the caster in which total darkness reigns. " +
                "The body of the spellcaster blends into the shadows, increasing his resistance " +
                "to all kinds of detection by +40. To locate him using the senses requires " +
                "passing a check of Impossible Difficulty with Search, or Inhumane using Notice. " +
                "Even while using Ki Detection, it is still necessary to beat an Inhuman-level " +
                "Check. In addition, as long as he is one with the Darkness, the caster can " +
                "transport himself each Combat Turn anywhere he wishes within the Kingdom of " +
                "Darkness. The MA of the caster is also increased by 20 points when using spells " +
                "of Darkness, and any living being (except Dark Elementals) within the covered " +
                "area automatically loses 10 points of Zeon per combat turn (20 points per turn " +
                "if a Light Elemental). The spell is stationary in the place it was cast.",
        "+50 meters to radius",
        30,
        20,
        false,
        listOf(SpellType.Automatic)
    )

    private val undetectable = Spell(
        "Undetectable",
        Element.Dark,
        true,
        90,
        380,
        "The target of this spell becomes completely immune to supernatural detection, " +
                "whether mystical, psychic, or based on Ki. The only way to locate the person is " +
                "through the physical senses such as sight, hearing, or smell. The maximum " +
                "Presence that can be affected is 60.",
        "+5 to the maximum Presence that can be affected",
        30,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val prisonOfDarkness = Spell(
        "Prison of Darkness",
        Element.Dark,
        true,
        92,
        200,
        "Encloses the victim in an inescapable world of Darkness, a separate universe which " +
                "has no interaction with our own. While the victim is imprisoned, he will have " +
                "no knowledge of anything that happens outside the prison, nor does anyone " +
                "outside have any knowledge of the prisoner\'s status or activities. If the " +
                "prisoner tries to break out from within, the prison will resist damage points up " +
                "to 100 times the Zeon value of the spell, which defends itself according to the " +
                "rules of Damage Resistance with an AT of 10. From the outside, the prison can " +
                "resist only double the Zeon value spent. It can only be damaged by Energy-based " +
                "Attacks with a Presence greater than 180. The prison recovers from any damage " +
                "with a Regeneration of 19. The spell can be avoided by passing a MR Check with a " +
                "Difficulty of 140, but if that fails, the prisoner has no chance to check again " +
                "later; if he wants to get out, he better be able to break through the spell.",
        "+10 to the MR Difficulty",
        20,
        5,
        true,
        listOf(SpellType.Spiritual)
    )

    private val oneWithDarkness = Spell(
        "One With the Darkness",
        Element.Dark,
        true,
        96,
        100,
        "Permits the caster to enter a state of utter meditation in which his body becomes " +
                "one with the Dark. He abandons this world and ascends to the Flow of Souls to " +
                "nourish himself from their energies. Apart from, and without knowledge of " +
                "anything happening elsewhere, he multiplies his Zeon Regeneration rate by 10, " +
                "and cures his wounds with a with a Healing Regeneration of 16. Although there " +
                "is no maintenance cost for the spell, the caster can be in the Flow of Souls " +
                "for the equivalent of one day.",
        "One day additional stay in the Flow of Souls",
        20,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val darkAscension = Spell(
        "Dark Ascension",
        Element.Dark,
        true,
        98,
        300,
        "This spell exchanges the material essence of a person for divine energy, modifying " +
                "his spirit through pure supernatural power. The affects of Ascension are " +
                "different depending on whether the spell is cast on himself, or on another. When " +
                "increasing his own power, the spell increases his Gnosis by 10 points. If used " +
                "on another person, it can give him as much Gnosis as desired, up to a level 10 " +
                "points below that of the caster. For instance, a caster with a Gnosis of 45 can " +
                "raise another individual to at most a Gnosis of 35. This spell can affect as " +
                "many targets as desired as long as their accumulated Presence is no higher than 80.",
        "+10 to the maximum Presence that can be affected",
        30,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val darkHolocaust = Spell(
        "Holocaust of Darkness",
        Element.Dark,
        true,
        100,
        600,
        "This spell unleashes the power of Darkness in its purest state, sweeping away " +
                "all before it in the spiritual as well as the material world. The freed energy " +
                "sweeps up and devours everything, unifying its existence to the Darkness. The " +
                "Holocaust of Darkness creates a great dome of darkness within which everything " +
                "is dissolved. It has a radius of 100 meters, attacks the Energy AT, and causes " +
                "a Base Damage of 350 points. Anyone receiving damage, no matter how small, must " +
                "beat a MR Check with a Difficulty of 160 or be joined with the Darkness, and " +
                "automatically destroyed in body and soul. It is not possible to designate " +
                "specific targets within the Holocaust; all except the caster are equally affected.",
        "+10 to Base Damage and doubles the radius of the spell",
        50,
        null,
        false,
        listOf(SpellType.Attack, SpellType.Spiritual)
    )

    val fullBook = listOf(
        createDark,
        null,
        induceFear,
        seeInDarkness,
        darkShield,
        shadow,
        null,
        darkArmor,
        banishLight,
        hideMagic,
        darkBeam,
        null,
        darkzone,
        darkBond,
        controlDark,
        concealment,
        null,
        obfuscate,
        enrage,
        banishPositiveEmotions,
        night,
        null,
        darkSphere,
        concealZone,
        enterNightmare,
        darkForm,
        null,
        perdition,
        createNegativeFeelings,
        eliminateTraces,
        shieldFromPositive,
        null,
        devouringDark,
        devastate,
        markOfFear,
        catastrophicDarkness,
        null,
        darkMaterialObjects,
        shadowTravel,
        nightmareLord,
        createDarkBeing,
        null,
        concealmentFromMagic,
        darknessKingdom,
        undetectable,
        prisonOfDarkness,
        null,
        oneWithDarkness,
        darkAscension,
        darkHolocaust
    )
}