package com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook

import com.paetus.animaCharCreator.character_creation.Element
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.Spell
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.SpellType

/**
 * List of spells associated with the essence element.
 */
class EssenceBook{
    private val naturalAffinity = Spell(
        "Natural Affinity",
        Element.Essence,
        true,
        2,
        30,
        "This spell alters the essence of an individual, allowing him to bond with natural " +
                "beings so that such beings recognize the individual as one of their own. For " +
                "instance, this spell could be used to gain wolf affinity, so that wolves would " +
                "recognize the character as an actual wolf. This spell can affect up to a total " +
                "60 Presence.",
        "+10 to the maximum Presence affected",
        10,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val detectEssence = Spell(
        "Detect Essence",
        Element.Essence,
        true,
        6,
        30,
        "The spellcaster is able to detect any being\'s base essence in a 10-meter radius. " +
                "The caster does not obtain real information about the creatures but is able to " +
                "recognize its elemental bonding and is later able to identify beings with the " +
                "same type of essence or race. Avoiding the effect requires passing a MR Check " +
                "with a Difficulty of 100. The spellcaster needs some evidence of an entity\'s " +
                "presence nearby in order to feel its essence. This spell can affect spiritual " +
                "creatures invisible to the human eye.",
        "+5 meters to radius, +5 to MR Difficulty",
        10,
        10,
        false,
        listOf(SpellType.Automatic)
    )

    private val essenceCommunicate = Spell(
        "Communication Through Essence",
        Element.Essence,
        true,
        10,
        30,
        "The spell establishes a connection between the caster and another living being, " +
                "making it possible for them to communicate spiritually. Since this spell does " +
                "not rely on spoken language, it is even possible to communicate with plants or " +
                "animals. The spell only affects beings with a Presence lower than 40.",
        "+10 to the maximum Presence affected",
        10,
        5,
        true,
        listOf(SpellType.Automatic)
    )

    private val naturalKnowledge = Spell(
        "Natural Knowledge",
        Element.Essence,
        true,
        12,
        40,
        "This spell permits the analysis of all the information pertaining to a specific " +
                "kind of Natural being, such as plants or animals. A spellcaster using this " +
                "spell might examine a strange plant and determine its toxicity, or identify " +
                "any special properties. It can only affect plants and animals with a Presence " +
                "lower than 30.",
        "+5 to the maximum Presence affected",
        10,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val healing = Spell(
        "Healing",
        Element.Essence,
        true,
        16,
        80,
        "This spell instantly heals one target creature by restoring up to 30% of lost LP. " +
                "The spell cannot recover lost limbs, nor can it eliminate critical-based " +
                "penalties, but it does stop all types of bleeding. Damage Resistance beings only " +
                "recover half that percentage.",
        "+5% Life Points",
        10,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val soulBarrier = Spell(
        "Soul Barrier",
        Element.Essence,
        true,
        20,
        60,
        "This spell creates a barrier that stops all Spiritual attacks affecting " +
                "supernatural Resistances. In other words, the shield can be used to stop attacks " +
                "that would force the caster to roll for MR or PhR. It is not capable of " +
                "stopping attacks that cause damage with added effects, such as Rain of " +
                "Destruction. The shield itself does not have Life Points, since it does not " +
                "protect against damaging impacts. The barrier cannot stop attacks that require " +
                "a Resistance Check of 120 or higher.",
        "+5 to the value of Resistances that can be stopped",
        10,
        10,
        true,
        listOf(SpellType.Defense)
    )

    private val shareSenses = Spell(
        "Share Senses",
        Element.Essence,
        true,
        22,
        60,
        "This spell allows the caster and one or more other creatures to share sight and " +
                "hearing. The spellcaster may choose to deny access to his own senses. The " +
                "maximum distance allowed for the connection is one kilometer. Resisting the " +
                "spell requires passing a MR or a PsR Check with a Difficulty of 100. All " +
                "affected parties are entitled to a new Resistance Check each hour. The spell " +
                "may affect several individuals simultaneously provided the total of their " +
                "Presences is not higher than 100.",
        "+5 to the maximum Presence affected, +5 to MR or PsR Difficulty and +1 kilometer",
        20,
        10,
        true,
        listOf(SpellType.Spiritual)
    )

    private val modifyEssence = Spell(
        "Modify Essence",
        Element.Essence,
        true,
        26,
        50,
        "This spell allows the caster to modify his own, or another subject\'s, essence at " +
                "will. The spell does not grant special abilities as part of the change, but it " +
                "does temporarily alter the animistic base of the being. In this manner, the " +
                "spell could turn an elemental\'s Earth essence into Cold, causing it to become " +
                "particularly vulnerable to Heat-based effects and attacks. If the creature " +
                "should possess powers exclusive to the elemental being modified, it temporarily " +
                "loses those powers. To avoid this effect it is necessary to pass a MR Check " +
                "with a Difficulty of 140.",
        "+5 to MR Difficulty",
        10,
        10,
        false,
        listOf(SpellType.Spiritual)
    )

    private val soulPoison = Spell(
        "Soul Poison",
        Element.Essence,
        true,
        30,
        60,
        "Soul Poison is a supernatural substance inoculated in the soul of the targeted " +
                "individual which spreads through all of his body. The caster is free to decide " +
                "the effects of the poison, following the description on Chapter 14. The " +
                "substance\'s level cannot exceed 20. This spell can create antidotes if there " +
                "is enough information available about the original poison.",
        "+5 to poison level",
        10,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val analyzeSoul = Spell(
        "Analyze Soul",
        Element.Essence,
        true,
        32,
        60,
        "This spell allows the caster to spiritually scrutinize individuals or creatures, " +
                "obtaining detailed information about their natural capacities and powers. " +
                "Although the spell does not confer access to the subjects\' knowledge, or the level of " +
                "the abilities that depend on it, the caster can measure their spiritual potential and " +
                "its innate capabilities. For instance, when directed at a supernatural being, " +
                "the caster will discover his powers and their level, but he will not be able to " +
                "tell what the creature is able to do, or how good a fighter he is. The target " +
                "may resist this spell with a MR Check of 120 Difficulty.",
        "+5 to MR Difficulty",
        10,
        null,
        false,
        listOf(SpellType.Automatic)
    )

    private val acquireNaturalCapacities = Spell(
        "Acquire Natural Capacities",
        Element.Essence,
        true,
        36,
        120,
        "This spell grants the chance of acquiring the abilities and characteristics of " +
                "existing plants or animals. It gives the target 50 extra DP to acquire secondary " +
                "abilities, essential abilities, or powers described in Chapter 26. Only secondary " +
                "abilities from the physical fields of Agility, Perception, and Strength, and only " +
                "special powers with Gnosis 0 requirements can be chosen. DP are invested according " +
                "to the character\'s class. Primary Abilities may not be increased with this " +
                "spell, nor can supernatural abilities ba acquired. This spell can temporarily " +
                "affect the target\'s physical appearance. For instance, a character acquiring an " +
                "increased damage Natural Weapon might sprout horns. A character can only be " +
                "affected by one Acquire Natural Capacities spell at a time.",
        "+5 DP",
        20,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val revitalize = Spell(
        "Revitalize",
        Element.Essence,
        true,
        40,
        100,
        "This spell creates a 50-meter radius area around the caster, within which all " +
                "living organisms are treated as having Regeneration 16.",
        "+20 meters to radius",
        20,
        10,
        false,
        listOf(SpellType.Automatic)
    )

    private val lifeMind = Spell(
        "Life Mind",
        Element.Essence,
        true,
        42,
        120,
        "This spell connects the caster\'s essence with any living organism with Gnosis 10 " +
                "or lower, including plants and animals, within a 500-meter radius. The spell " +
                "enables the spellcaster to sense the events surrounding the affected parties " +
                "using their perception, but it will not enable him to exert any control upon " +
                "them. Since the caster is also transmitting part of his spirit, he can cast " +
                "spells through the connected target with half his usual MA. Any Spiritual " +
                "spell cast upon the targeted organism will also affect the caster of Life " +
                "Mind. The targeted organism can resist Life Mind by passing a MR with a " +
                "Difficulty of 80. Once established as a guest in a specific body, the " +
                "spellcaster can jump on to any subject in the radius of effect, provided the " +
                "new target fails the Resistance. An individual passing the Check cannot be " +
                "affected by the spell independently of the caster\'s attempts. Affected people do " +
                "not usually realize they are being used even when spells are being cast " +
                "through them. Anyone hosting the caster\'s essence who leaves the area of " +
                "effect of Life Mind is freed from the caster\'s influence.\nThe caster may " +
                "specifically target any appropriate organism within the spell\'s area of effect " +
                "that he is aware of, but Life Mind cannot be used to detect the presence of " +
                "living organisms. If the spellcaster has no obvious target, he can direct the " +
                "power of the spell to simply join with the nearest appropriate target, if there " +
                "is one. However, this method can be of very limited use, since it is quite " +
                "possible the nearest living organism would be nothing more than a blade of " +
                "grass.\nWhile this spell is in effect, the caster is not aware of events taking " +
                "place around his true being.",
        "+100 meters to radius and +5 to MR Difficulty",
        20,
        20,
        false,
        listOf(SpellType.Automatic)
    )

    private val alterGrowth = Spell(
        "Alter Growth",
        Element.Essence,
        true,
        46,
        100,
        "This spell modifies the natural development rate of any given organism; it either " +
                "doubles, or cuts in half, the aging speed of a living body with a Presence no " +
                "greater than 60. Resisting the effect requires passing a MR with a Difficulty " +
                "of 100. This spell is not capable of stopping the passage of time altogether, " +
                "no matter how many added effects are employed; however, it can delay its " +
                "effects considerably. For instance, in the case the spell is cast with five " +
                "added effects to quicken aging, each new day will count as 64 days for the " +
                "targeted individual.",
        "Doubles or cuts in half the growth rate again and +5 to the maximum Presence affected",
        10,
        10,
        true,
        listOf(SpellType.Spiritual)
    )

    private val naturalImitation = Spell(
        "Natural Imitation",
        Element.Essence,
        true,
        50,
        60,
        "This spell creates one or more natural animals under the spellcaster\'s absolute " +
                "control. The creature must be a Gnosis 0 animal, but may be from any species. A " +
                "single casting of this spell creates up to two levels of creatures, allowing " +
                "the caster to create a single level 2 creature, or two level 1 creatures. " +
                "Animals in Chapter 25 can be used as a reference.",
        "+1 level",
        20,
        20,
        false,
        listOf(SpellType.Effect)
    )

    private val spiritualForm = Spell(
        "Spiritual Form",
        Element.Essence,
        true,
        52,
        100,
        "This spell transforms an individual\'s physical form into spiritual matter, " +
                "rendering him invisible to those unable to see spirits and intangible to all " +
                "non-Energy-based matter. The target can also survive without physical " +
                "necessities including oxygen, water, and food, for as long as he remains in " +
                "this state. The affected character uses the rules for Spiritual Beings " +
                "described in Chapter 26. The maximum Presence that can be affected is 60.",
        "+10 to the maximum Presence affected",
        10,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val naturalControl = Spell(
        "Natural Control",
        Element.Essence,
        true,
        56,
        100,
        "By means of this spell, the spellcaster gains absolute dominion over a single " +
                "living thing with Gnosis 0. Valid targets include animals, plants, and even " +
                "regular human beings. The caster transmits his orders directly through a " +
                "mystical connection, without the need of language. Resisting the effects of the " +
                "spell requires passing a MR with a Difficulty of 80. Psychic Resistance cannot " +
                "be used for resisting the spell, since it\'s the subject\'s essence, and not its " +
                "mind, that is being controlled. The target is entitled to a new check every day, " +
                "and every time it receives an order completely against his nature. The target " +
                "may receive a +20 bonus to the MR Check if given an extreme order, such as " +
                "sacrificing its own life or slaying a loved one.",
        "+5 to MR Difficulty",
        20,
        5,
        true,
        listOf(SpellType.Spiritual)
    )

    private val stateInduction = Spell(
        "State Induction",
        Element.Essence,
        true,
        60,
        100,
        "Through soul manipulation, the subject can be induced into any of the generic " +
                "states described in Chapter 14, except death. The caster chooses the specific " +
                "state at the moment the spell is cast. The MR Difficulty for resisting the " +
                "spell is 80. Characters may add a +40 bonus to their roll if the induced " +
                "states are Coma or Complete Paralysis.",
        "+5 to MR Difficulty",
        20,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val flowReturn = Spell(
        "Return to the Flow",
        Element.Essence,
        true,
        62,
        100,
        "This spell sends a spirit back to the Flow of Souls. It can affect both dead souls " +
                "awaiting The Calling and creatures of a spiritual nature. The essence vanishes " +
                "once it arrives to the great beyond unless the entity has a Gnosis level high " +
                "enough to allow it to go back to the world. Undead are automatically destroyed " +
                "by this spell. The MR Difficulty for avoiding the effects of this spell is 120.",
        "+5 to MR Difficulty",
        20,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val shieldArea = Spell(
        "Shield Area",
        Element.Essence,
        true,
        66,
        120,
        "This spell haunts a specific area making it impossible to be trespassed by certain " +
                "beings. The spellcaster decides which beings\' access will be restricted, and " +
                "there is no limit to the number of creatures he can choose. For instance, he " +
                "could forbid the entrance of fire elementals, or only allow the entrance of " +
                "humans. The affected area has a maximum 20-meter radius and remains stationary. " +
                "Any forbidden creature that attempts to enter the shielded area must pass a MR " +
                "Check with a Difficulty of 120. Creatures that fail the Check are entitled to a " +
                "new roll each hour. Creatures already inside the area at the time the spell is " +
                "cast can circulate freely and may leave the shielded area without hindrance, but " +
                "once beyond the shielded area, they must pass a similar MR Check to re-enter the area.",
        "+5 to MR Difficulty",
        20,
        10,
        true,
        listOf(SpellType.Automatic)
    )

    private val supernaturalControl = Spell(
        "Supernatural Control",
        Element.Essence,
        true,
        70,
        120,
        "This spell grants total control of a spirit or a Being Between Worlds. The caster " +
                "transmits his orders through the mystical connection directly, without the need " +
                "of language or a mutual understanding. An entity wishing to resist the effects " +
                "of this spell must pass a MR Check with a Difficulty of 80, and since it is the " +
                "creature\'s essence that is under control, it cannot use its PsR. The victim is " +
                "entitled to a new check every day and every time it receives an order completely " +
                "against its nature.",
        "+5 to MR Difficulty",
        20,
        5,
        true,
        listOf(SpellType.Spiritual)
    )

    private val shareEssence = Spell(
        "Share Essence",
        Element.Essence,
        true,
        72,
        140,
        "The spellcaster ties the essence of two individuals together in an indivisible " +
                "unity. In this way, all damage and spiritual effects suffered by one will affect " +
                "the other. Both individuals use the highest MR of the two against Spiritual " +
                "spells. When the essence of a regular being is tied to that of a Damage " +
                "Resistance being, the former only suffers one-fifth of the damage received by " +
                "the creature. The total of the Presences that can be affected cannot exceed " +
                "100. Resisting this spell requires a MR Check with a Difficulty of 120.",
        "+5 to MR Difficulty and +10 to the maximum Presence affected",
        20,
        10,
        true,
        listOf(SpellType.Spiritual)
    )

    private val transmigrateSoul = Spell(
        "Transmigrate Soul",
        Element.Essence,
        true,
        76,
        180,
        "This spell confers upon the caster the ability to transport any soul in his " +
                "presence to a new host. The soul can be hosted in a living being, or an " +
                "inanimate object such as a stone or a sword. A dead body, in good condition, " +
                "can also serve as a host, in which case the body returns to life. The " +
                "transmigrated soul adopts all the physical capabilities and limitations of the " +
                "new host, retaining only its own intellectual, and possibly its animistic, " +
                "capabilities. For example, if the new host is a tree, the soul will be able " +
                "to think, but it will be unable to speak or move. Once the transmigration is " +
                "complete, the soul remains with its new host until the host is destroyed.\nIf " +
                "the soul transmigrates to a body, or object, already inhabited by another soul, " +
                "the intruding soul will naturally meet resistance. The two souls engage in a " +
                "struggle for dominion by each rolling 1d100 and adding their base Presence (no " +
                "open rolls allowed); the highest total wins the struggle. If the difference " +
                "between the two results is 100 or more, the winner consumes the other soul and " +
                "takes control of the host permanently. If the difference is 50-99, the weaker " +
                "soul is pacified and remains indefinitely asleep until something awakens it. If " +
                "the difference is less than 50, the winner gains temporary control over the host, " +
                "but the weaker soul remains awake and is entitles to a new check once per day.\n" +
                "This spell can affect souls with a maximum Presence of 60. Both the affected soul " +
                "and the targeted host can attempt to resist this spell with a MR check of " +
                "Difficulty 100. This spell can affect deceased spirits still awaiting the " +
                "Calling, but it cannot bring back spirits from the Flow. Whenever a supernatural " +
                "being is enclosed in a physical body, it may lose levels and animistic powers.",
        "+5 to MR Difficulty and +10 to the maximum Presence affected",
        20,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val spiritualExistence = Spell(
        "Spiritual Existence",
        Element.Essence,
        true,
        80,
        200,
        "The caster is able to transcend into a spiritual state, forsaking his physical body " +
                "forever. From that point on, he is no longer a Natural nor Being Between Worlds " +
                "creature, instead becoming a Gnosis 25 spiritual entity, with all the benefits " +
                "this implies. In addition, 150 extra Development Points are awarded, 100 of " +
                "which must be spent in acquiring the Spirit state, while the remaining 50 may be " +
                "spent choosing among Natural Abilities and Powers of Being Creation described in " +
                "Chapter 26. The spellcaster also has a choice of 50 Development Points in " +
                "disadvantages and penalties in order to get additional points. This spell will " +
                "only work on Natural and Beings Between Worlds, therefore it cannot be " +
                "cast upon spirits to increase their existing abilities. If a Spiritual being " +
                "should reincarnate in a physical form, it automatically loses the advantages " +
                "and benefits gained through this spell.",
        "+10 DP and +5 optional DP in disadvantages",
        20,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val spiritCreation = Spell(
        "Spirit Creation",
        Element.Essence,
        true,
        82,
        250,
        "The caster uses magic to create a spiritual entity under his complete dominion. " +
                "This entity is developed as a Spiritual Being, subject to the powers and limitations " +
                "described in Chapter 26 for such beings. The creature has 600 DP, and its " +
                "maximum level is to be calculated using the same rules as in the spell Create " +
                "Being of the path of Creation.",
        "+50 DP",
        30,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val tieVitalEssence = Spell(
        "Tie Vital Essence",
        Element.Essence,
        true,
        86,
        200,
        "This spell ties an individual\'s soul to a physical object without completely " +
                "disengaging it from the original body. An individual affected by this cannot die " +
                "from loss of Life Points. His body is still vulnerable to damage, including " +
                "criticals that could be completely incapacitating, but he remains alive even " +
                "after being reduced below the LP threshold that would normally kill him. " +
                "Assuming the individual has access to some form of supernatural healing (which " +
                "this spell does not provide), he can eventually recover from any amount of " +
                "damage. This spell also renders the individual immune to all magical effects " +
                "that directly affect his essence.\nIf the object tied to the soul is destroyed, " +
                "the individual dies instantly. Furthermore, the individual and the object must " +
                "forever remain within one kilometer of each other, or the bond breaks, killing " +
                "the subject. The object used as the container cannot have a Presence higher " +
                "than 30. Those wishing to resist the effects of this spell must pass a MR Check " +
                "with a Difficulty of 100.",
        "+5 to the maximum Presence affected, +5 to MR Difficulty and +1 kilometer apart",
        20,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val greenness = Spell(
        "Greenness",
        Element.Essence,
        true,
        90,
        250,
        "By merging his spirit with Nature itself, the spellcaster is able to create vegetal " +
                "and animal life as he pleases within a ten-kilometer radius. Such life emerges " +
                "instantly, regardless of local climate and terrain conditions. However, since " +
                "the magical energy of this spell has no duration, the extended survivability of " +
                "the newly created life is not guaranteed. Animals and plants created with this " +
                "spell must belong to existing species.",
        "+10 kilometer to radius",
        50,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val lifeDominion = Spell(
        "Life Dominion",
        Element.Essence,
        true,
        92,
        300,
        "By seizing control of a portion of the world\'s essence, the caster of this spell " +
                "gains absolute dominion over all Natural beings in a 100-kilometer radius. He " +
                "transmits his order through a mystical connection, without the need for physical " +
                "proximity. Any entity wishing to resist the effects of this spell must pass a " +
                "MR Check with a Difficulty of 100. Affected beings are entitled to a new check " +
                "every day and any time they receive an order completely against their nature.",
        "+100 kilometers to radius and +5 to MR Difficulty",
        30,
        5,
        true,
        listOf(SpellType.Automatic)
    )

    private val resurrection = Spell(
        "Resurrection",
        Element.Essence,
        true,
        96,
        400,
        "This spell snatches a soul from the Flow and returns it to life. Difficulty " +
                "depends on the amount of time elapsed since death. The longer the period, the " +
                "more the spirit has spread and attached to the Flow of Souls. The spell has the " +
                "power to bring spirits back, but not to tie them to their body or to heal it. " +
                "For a completely effective resurrection, the soul should be placed in a suitable " +
                "body using the spell Transmigrate Soul. However, it is possible that the time " +
                "elapsed may have produced irreparable damage on the soul, therefore it is up to " +
                "the GM to put some limit on the subject\'s memories and knowledge. Destroyed or " +
                "already reincarnated souls cannot be resurrected. Resurrected spirits cannot " +
                "have a base Presence higher than 30, and the elapsed time since death must be " +
                "less than one month.",
        "+5 to the maximum Presence affected and twice the time elapsed since the subject\'s death",
        40,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val soulLord = Spell(
        "Lord of the Souls",
        Element.Essence,
        true,
        100,
        600,
        "The spellcaster seizes partial control of the Flow of Souls, giving him absolute " +
                "dominion over all souls, Spiritual Beings, and similar entities in a 100-" +
                "kilometer radius. Among other uses, the caster can snatch a soul from a body and " +
                "reintroduce it into an unborn child, thus controlling Nephilim birth.\nThe Lord " +
                "of the Souls conveys his orders to those under his dominion via a mystical " +
                "connection that does not require physical proximity or mutual language. Those " +
                "wishing to resist the effects of this spell must pass a MR Check with a " +
                "Difficulty of 120. A being who fails is entitled to a new check each day control " +
                "is maintained, or any time it is given an order completely against its nature. " +
                "If the caster is attempting to separate a soul from its original body, the " +
                "target receives a +40 bonus to its MR Check. This spell does not affect undead.",
        "+5 MR Difficulty and +100 kilometers",
        50,
        5,
        true,
        listOf(SpellType.Automatic)
    )

    val fullBook = listOf(
        naturalAffinity,
        null,
        detectEssence,
        null,
        essenceCommunicate,
        naturalKnowledge,
        null,
        healing,
        null,
        soulBarrier,
        shareSenses,
        null,
        modifyEssence,
        null,
        soulPoison,
        analyzeSoul,
        null,
        acquireNaturalCapacities,
        null,
        revitalize,
        lifeMind,
        null,
        alterGrowth,
        null,
        naturalImitation,
        spiritualForm,
        null,
        naturalControl,
        null,
        stateInduction,
        flowReturn,
        null,
        shieldArea,
        null,
        supernaturalControl,
        shareEssence,
        null,
        transmigrateSoul,
        null,
        spiritualExistence,
        spiritCreation,
        null,
        tieVitalEssence,
        null,
        greenness,
        lifeDominion,
        null,
        resurrection,
        null,
        soulLord
    )
}