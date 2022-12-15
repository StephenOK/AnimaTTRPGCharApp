package com.example.animabuilder.character_creation.attributes.magic.spells.spellbook

import com.example.animabuilder.character_creation.Element
import com.example.animabuilder.character_creation.attributes.magic.spells.Spell
import com.example.animabuilder.character_creation.attributes.magic.spells.SpellType
import java.io.Serializable

class NecromancyBook: Serializable {
    private val feelDeath = Spell(
        "Feel Death",
        Element.Necromancy,
        true,
        2,
        30,
        "The necromancer automatically detects any death occurring 100 meters around him. " +
                "This spell also reveals undead creatures if they fail a MR Check with a " +
                "Difficulty of 120.",
        "+25 meters to radius and +5 to MR Difficulty",
        20,
        10,
        false,
        listOf(SpellType.Detection)
    )

    private val seeBeyond = Spell(
        "See the Great Beyond",
        Element.Necromancy,
        true,
        6,
        30,
        "This spell allows the caster to see specters and other spiritual creatures " +
                "invisible to the human eye. The caster may bestow this gift upon as many " +
                "individuals as he wishes, provided the total of their Presences do not exceed 80.",
        "+10 to the maximum Presence affected",
        10,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val controlScavengers = Spell(
        "Control Scavengers",
        Element.Necromancy,
        true,
        8,
        40,
        "This spell grants the caster control over creatures that feed mostly on the dead. " +
                "The affected creatures must all be within a 10-meter radius and have a " +
                "combined Presence less than 20. Some possible examples include vultures, crows, " +
                "and maggots.",
        "+10 meters to radius",
        30,
        20,
        false,
        listOf(SpellType.Effect)
    )

    private val spectralShield = Spell(
        "Spectral Shield",
        Element.Necromancy,
        false,
        10,
        40,
        "This spell creates a shield of necromantic Energy capable of stopping Spiritual " +
                "assaults that affect the character\'s supernatural Resistances. The spell does " +
                "not stop physical attacks. Spectral Shield offers protection against mystical " +
                "effects, as long as they do not force the character to make a Resistance Check " +
                "with a Difficulty greater than 140.",
        "+5 to the value of Resistances that can be stopped",
        20,
        20,
        false,
        listOf(SpellType.Defense)
    )

    private val drainLife = Spell(
        "Drain Life",
        Element.Necromancy,
        true,
        12,
        50,
        "This spell allows a necromancer to absorb the life force of another living creature, " +
                "severely injuring the target in the process, while healing himself in turn. The " +
                "caster must be able to physically touch the target. Any individual affected by " +
                "this spell is forced to pass a MR Check with a Difficulty of 80 or lose a number " +
                "of Life Points equal to his Failure Level. The caster then adds this number to " +
                "his own Life Points, however he cannot exceed his normal maximum. Creatures with " +
                "Damage Resistance lose five times the amount of their Failure Level, but the " +
                "caster only receives one-fifth of that number to heal his own wounds. For " +
                "example, a creature with Damage Resistance that fails its MR Check by 20 points " +
                "would lose 100 LP, but the caster would only add 20 to his LP total.",
        "+5 to MR Difficulty",
        20,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val necromanticDetection = Spell(
        "Necromantic Detection",
        Element.Necromancy,
        true,
        16,
        50,
        "This spell allows the necromancer to detect any living or undead creature within " +
                "20 meters of him. Characters who pass a MR Check with a Difficulty of 120 " +
                "remain undetected by this spell.",
        "+10 meters to radius and +10 to MR Difficulty",
        10,
        10,
        false,
        listOf(SpellType.Detection)
    )

    private val talkToDead = Spell(
        "Talk to the Dead",
        Element.Necromancy,
        true,
        18,
        60,
        "This spell allows the necromancer to communicate with dead souls and spirits around " +
                "him. The spell automatically affects all spirits within 25 meters of the caster " +
                "that have a Presence less than 40. The caster doesn\'t need to be aware of a " +
                "spirit\'s exact location.",
        "+10 to the maximum Presence affected",
        10,
        20,
        false,
        listOf(SpellType.Automatic)
    )

    private val necromanticParalysis = Spell(
        "Necromantic Paralysis",
        Element.Necromancy,
        true,
        20,
        60,
        "This spell freezes the supernatural essence of undead creatures, rendering them " +
                "unable to move. Any undead being in a 20-meter radius form the caster is " +
                "automatically subject to Total Paralysis if unable to pass a MR Check with a " +
                "Difficulty of 120.",
        "+10 meters to radius and +5 to MR Difficulty",
        10,
        10,
        false,
        listOf(SpellType.Spiritual)
    )

    private val necromitude = Spell(
        "Necromitude",
        Element.Necromancy,
        true,
        22,
        80,
        "This spell allows an undead creature to recover 50 Life Points. It has no effect " +
                "upon living beings.",
        "+5 Life Points",
        10,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val deathBeam = Spell(
        "Death Beam",
        Element.Necromancy,
        true,
        26,
        60,
        "This spell projects a strong necromantic discharge. The attack hits on the Energy " +
                "AT and has a Base Damage of 80.",
        "+5 to Base Damage",
        10,
        null,
        false,
        listOf(SpellType.Attack)
    )

    private val raiseCorpses = Spell(
        "Raise Corpses",
        Element.Necromancy,
        true,
        28,
        80,
        "Through necromantic Energy manipulation, the caster can animate corpses and turn " +
                "them into zombies or skeletons under his control. The dead do not keep the " +
                "Special Abilities or knowledge they had while alive. But, they maintain their " +
                "basic abilities - such as those associated with natural weapons and some of " +
                "their physical Characteristics. The creature\'s Power and Resistances vary " +
                "according to its body. For instance, a bear\'s cadaver would be considerably more " +
                "dangerous than that of a human. Basic information on zombies is included in the " +
                "Bestiary as reference for the GM. A caster can raise a number of corpses equal " +
                "to 100 Presence points, as long as none of them are individually higher than 20.",
        "+20 to total number of affected Presences and +5 to their maximum value",
        20,
        20,
        true,
        listOf(SpellType.Effect)
    )

    private val deadBody = Spell(
        "Dead Body",
        Element.Necromancy,
        true,
        30,
        80,
        "This spell temporarily stops an individual\'s bodily functions without causing his " +
                "death. The affected target can still move and act normally and becomes extremely " +
                "resistant to damage effects and consequences for as long as the spell stays " +
                "active. Any physical penalty he incurs is reduced by half, and he remains " +
                "conscious while in the state between life and death. In addition, anyone " +
                "inspecting the body will be unable to determine its true condition. The maximum " +
                "total of Presences affected by this spell cannot exceed 40.",
        "+5 to the maximum Presence affected",
        10,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val drainMagic = Spell(
        "Drain Magic",
        Element.Necromancy,
        true,
        32,
        60,
        "This spell drains the magical energy out of an individual or an object and " +
                "transfers it to the necromancer. Any individual affected by this spell is " +
                "forced to pass a MR Check with a Difficulty of 100 or lose a number of Zeon " +
                "points equal to twice the number by which he failed the check. The caster " +
                "immediately absorbs this Zeon.",
        "+5 to MR Difficulty",
        20,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val destroyUndead = Spell(
        "Destroy the Undead",
        Element.Necromancy,
        true,
        36,
        80,
        "This spell completely destroys the essence of the undead. Any necromantic entity " +
                "targeted by this spell is forced to pass a MR Check with a Difficulty of 120 or " +
                "suffer damage equivalent to twice the number by which he failed the check. " +
                "Creatures with Damage Resistance increase this amount by their Damage Resistance " +
                "multiple.",
        "+5 to MR Difficulty",
        20,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val drainCharacteristic = Spell(
        "Drain Characteristic",
        Element.Necromancy,
        true,
        38,
        80,
        "By touching a living creature, the necromancer can absorb one of the individual\'s " +
                "Characteristics, increasing his own in the process. The necromancer must decide " +
                "the Characteristic that he will drain before executing the spell. The designated " +
                "target loses 1 point from that Characteristic for every 10 points by which he " +
                "fails the 120 Difficulty MR Check. If the natural value of the victim\'s drained " +
                "Characteristic is higher than that of the necromancer\'s, the necromancer adds " +
                "that drained point to his own Characteristic. If the target\'s Characteristic is " +
                "lower, the necromancer needs to absorb 3 Characteristic points to increase his " +
                "own by 1. The increased attributes remain for as long as the spell is maintained. " +
                "Lost Characteristics are recovered at a rate of 1 per hour once the caster " +
                "cancels the spell.",
        "+5 to MR Difficulty",
        20,
        20,
        false,
        listOf(SpellType.Spiritual)
    )

    private val controlDead = Spell(
        "Control the Dead",
        Element.Necromancy,
        true,
        40,
        100,
        "The necromancer obtains absolute control of any undead creature in a 20-meter " +
                "radius. Dominion over the undead stays active for as long as the spell is " +
                "maintained, but it does not affect other necromantic creatures that might " +
                "approach the area later. The undead can attempt to avoid being controlled by " +
                "passing a MR Check with a Difficulty of 120. They will only be allowed to reroll " +
                "upon receiving an order completely contradictory to their nature; therefore " +
                "creatures without a will, such as animated corpses, can never be freed.",
        "+10 meters to radius and +5 to MR Difficulty",
        10,
        10,
        true,
        listOf(SpellType.Spiritual)
    )

    private val witherLife = Spell(
        "Wither Life",
        Element.Necromancy,
        true,
        42,
        80,
        "This spell creates a necromantic Energy area that immediately kills any inferior " +
                "life form - such as small animals and plants - around the caster. Any living " +
                "being with a Presence less than 20 that is within 10 meters from the caster " +
                "automatically rots away.",
        "+5 meters to radius",
        10,
        10,
        true,
        listOf(SpellType.Automatic)
    )

    private val necromanticShield = Spell(
        "Necromantic Shield",
        Element.Necromancy,
        false,
        46,
        80,
        "This spell creates an Energy shield from the essence of dead souls that protects " +
                "against all kinds of attacks. The shield can absorb up to 1000 points of damage " +
                "before breaking.",
        "+100 Resistance Points",
        20,
        20,
        false,
        listOf(SpellType.Defense)
    )

    private val dominateLife = Spell(
        "Dominate Life",
        Element.Necromancy,
        false,
        48,
        140,
        "This spell allows the caster to enslave a living being\'s soul. The targeted victim " +
                "can resist the spell if he passes a MR Check with a Difficulty of 100. The " +
                "controlled individual receives a new check each day, and he also receives the " +
                "chance to make another MR Check every time he receives an order completely " +
                "against his behavior.",
        "+5 to MR Target Number Difficulty",
        20,
        5,
        true,
        listOf(SpellType.Spiritual)
    )

    private val vampireStigma = Spell(
        "Vampire Stigma",
        Element.Necromancy,
        true,
        50,
        140,
        "The target of this spell automatically absorbs 10% of the damage he deals to any " +
                "opponent. Vampire Stigma will work both on physical attacks and direct damage " +
                "spells, as well as similar supernatural powers. Attacks against creatures with " +
                "Damage Resistance give the caster only 2% of the dealt damage.",
        "+5% additional damage absorption (+1% against creatures with Damage Resistance)",
        20,
        20,
        false,
        listOf(SpellType.Effect)
    )

    private val spectralForm = Spell(
        "Spectral Form",
        Element.Necromancy,
        true,
        52,
        100,
        "The necromancer\'s physical body becomes a burning, spectral mass that damages the " +
                "essence of all living beings that comes into contact with it. Anyone touching " +
                "its body feels the chill of death and is forced to make a PhR or MR Check with " +
                "a Difficulty equal to twice the necromancer\'s Presence. If the affected character " +
                "fails the check, he suffers an All Action Penalty and loses a number of Life " +
                "Points equal to half the number by which he failed the check. The necromancer " +
                "can only be hit by Energy damaging attacks for as long as he remains in this " +
                "condition. The maximum Presence affected by this spell is 100.",
        "+5 to the maximum Presence affected",
        20,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val necromanticModification = Spell(
        "Necromantic Modification",
        Element.Necromancy,
        true,
        56,
        100,
        "This spell can target either a living or undead entity, temporarily granting it " +
                "new Powers and Abilities. If the target is an undead creature, it receives " +
                "100 additional DP to obtain any of the Special Powers detailed in Chapter 26, " +
                "as a creature with a Gnosis of 25 would. When directed at a living being, the " +
                "DP granted by the spell are reduced by half. The effects of this spell do not " +
                "overlap, and subjects may be affected by it only once.",
        "+10 DP",
        20,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val summonDead = Spell(
        "Summon the Dead",
        Element.Necromancy,
        true,
        58,
        100,
        "This spell calls the souls of dead people or specters to the necromancer. The " +
                "caster is able to summon spirits selectively, although some other soul may " +
                "present itself if the specific one he is seeking to contact is unavailable. " +
                "Summon the Dead only works on spirits still tied to the world, or on spectral " +
                "creatures, but not upon souls already transmigrated to the Flow of Souls. The " +
                "maximum total of Presence affected cannot exceed 50.",
        "+5 to the maximum Presence affected",
        10,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val raiseSpecters = Spell(
        "Raise Specters",
        Element.Necromancy,
        true,
        60,
        200,
        "By dominating the souls of the dead, the necromancer can pervert their essences and " +
                "turn them into specters at his service. These ghosts keep a small part of the " +
                "capacities they used to have in life, but they lose the majority of their old " +
                "powers and attributes in the process. A specter raised by this spell reduces to " +
                "half (and round up) the Level achieved in life. However, it abides by the general " +
                "rules of undead spirits and receives 100 extra DP which the caster employs to " +
                "provide it with the powers he sees fit (as a Gnosis 20 being). The spell will " +
                "only work on recently deceased souls still waiting for the Calling. It can raise " +
                "up to 100 Presence points among several spirits, provided none of them is higher " +
                "than 30.",
        "+20 to the number of Presences and +5 to their maximum value",
        20,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val drainLifeForce = Spell(
        "Drain Life Force",
        Element.Necromancy,
        true,
        62,
        180,
        "This spell absorbs an individual\'s vital force and transmits it to whoever the " +
                "necromancer designates. The target must pass a MR Check with a Difficulty of " +
                "100 or lose 1 Constitution and 1 Power Point for every 10 points by which he " +
                "fails the check. The affected individual also ages gradually, according to his " +
                "Failure Level. Absorbed points allow the necromancer (or the subject absorbing " +
                "them) to rejuvenate and recover part of his lost vitality, up to starting " +
                "levels. Characters deprived of their vital force can never recover it, except " +
                "through supernatural abilities that make it possible.",
        "+5 to MR Difficulty",
        20,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val kill = Spell(
        "Kill",
        Element.Necromancy,
        true,
        66,
        100,
        "This spell stops a subject\'s bodily functions causing immediate death. It only " +
                "works on living beings and is useless against spirits and some inanimate Beings " +
                "Between Worlds. The affected character must pass a MR or PhR Check with a " +
                "Difficulty of 80 to survive.",
        "+5 to MR or PhR Difficulty",
        10,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val soulBeam = Spell(
        "Soul Beam",
        Element.Necromancy,
        true,
        68,
        140,
        "This spell projects a magical Energy discharge composed of withered souls. The " +
                "spell hits on the Energy AT and has a 100 point Base Damage. It only affects " +
                "beings with a soul; lifeless physical bodies, such as walls or golems, are " +
                "ignored. Given the nature of the discharge, only those with the capacity of " +
                "seeing spirits are able to sense it.",
        "+5 to Base Damage",
        30,
        null,
        false,
        listOf(SpellType.Attack)
    )

    private val necromanticChimera = Spell(
        "Necromantic Chimera",
        Element.Necromancy,
        true,
        70,
        250,
        "This spell creates an undead creature subject to the caster\'s control. This entity " +
                "is developed as a Being Between Worlds with 600 DP and a Gnosis of 25. However, " +
                "the necromancer cannot create the entity from scratch; in order to develop it, " +
                "he must gather pieces from several corpses. If looking to endow any power or " +
                "essential ability, he must first find the bodies of beings that possessed those " +
                "powers. For instance, if wanting to confer Natural Flight, he must find the " +
                "corpse of a creature who had that specific ability in life. The existence of " +
                "the chimera is directly linked to the necromancer\'s soul. Therefore, its " +
                "maximum level is calculated using the same rules as in the spell Create Being " +
                "of the path of Creation.",
        "+50 DP",
        30,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val lifePerversion = Spell(
        "Life Perversion",
        Element.Necromancy,
        true,
        72,
        180,
        "This spell corrupts the essence of a living being and stops its bodily functions, " +
                "immediately transforming it into an undead entity. The affected character " +
                "maintains the same Characteristics and abilities he had in life - although his " +
                "animated corpse status will naturally grant him the advantage of Physical " +
                "Exemption. Individuals may avoid transformation by passing a MR Check with a " +
                "Difficulty of 100.",
        "+5 to MR or PhR Difficulty",
        20,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val vassalage = Spell(
        "Vassalage",
        Element.Necromancy,
        true,
        76,
        250,
        "This spell permanently ties an undead creature\'s soul to that of the necromancer. " +
                "Thus, the spellcaster automatically becomes its hierarchical superior, and the " +
                "monster\'s existence depends upon him. Although this spell does not endow the " +
                "necromancer with real control upon the creature, their lives are tied together " +
                "in a manner such that if the master is destroyed, the creature will perish too. " +
                "The affected undead may resist Vassalage by passing a MR or PhR Check with a " +
                "Difficulty of 80.",
        "+5 to MR or PhR Difficulty",
        30,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val drainSouls = Spell(
        "Drain Souls",
        Element.Necromancy,
        true,
        78,
        200,
        "This spell allows the necromancer to snatch another individual\'s soul so he may " +
                "feed on its essence and power. The stronger the absorbed spirit, the greater the " +
                "energy received. The targeted individual must pass a MR Check with a Difficulty " +
                "of 120 or lose an amount of Presence equal to half the number by which he failed " +
                "the check. For every 5 Presence points lost, the target loses 1 level and all " +
                "the capacities or powers dependent upon it (with the exception of knowledge-based " +
                "abilities). If the target\'s Presence ever reaches zero, its soul is considered " +
                "to be completely extinguished and he dies. For every 10 points of Presence " +
                "absorbed, the necromancer temporarily increases one of his Characteristics by 1, " +
                "or he can choose to receive 10 DP to acquire any Power or Essential creature " +
                "ability. These increased Characteristic values will vanish at the rate of one " +
                "per day, and DP will decrease at a rate of 20 per day.",
        "+5 to MR Difficulty",
        20,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val surpassDeath = Spell(
        "Surpass Death",
        Element.Necromancy,
        true,
        80,
        300,
        "This spell allows a living person to defeat death. The target becomes an undead " +
                "entity without ties and can therefore exist in the world on his own power. The " +
                "spell must be cast at the exact moment of the person\'s death - or a few seconds " +
                "before (it will not cause death by itself). The caster is free to decide if the " +
                "target in question becomes a Being Between Worlds or a Spirit, but in any case " +
                "it will have a Gnosis of 25. The spell confers 150 extra DP to use for any of " +
                "the essential abilities or powers described in Chapter 26. If the caster chooses " +
                "for the target to become a Spirit, the target must spend 100 DP to that end. " +
                "He can also choose up to 50 DP in Disadvantages and Penalties, which will give " +
                "him extra points. Excess DP increases the level as described in the spell Chimera. " +
                "Surpass Death will only work on living creatures at the moment of death; " +
                "therefore, it will have no effect upon creatures of a necromantic nature.",
        "+10 DP and +5 optional DP in Disadvantages",
        30,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val trueRise = Spell(
        "True Rise",
        Element.Necromancy,
        true,
        82,
        320,
        "This spell raises a corpse allowing it to maintain its knowledge and every one of " +
                "the powers and abilities it had in life. Unfortunately, the raised creature " +
                "becomes an undead being lacking true soul. The spell affects deceased characters " +
                "or creatures with a Presence score less than 30 whose bodies are at the reach of " +
                "the caster. If death occurred a long time before the spell is cast, the raised " +
                "creature\'s physical abilities might be damaged. Characters resurrected once " +
                "through this spell cannot be resurrected again. Keep in mind that, even though " +
                "the character is an undead entity, it is not under the control of the necromancer.",
        "+5 to the maximum Presence affected",
        30,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val wellOfLife = Spell(
        "Well of Life",
        Element.Necromancy,
        true,
        86,
        300,
        "This spell creates a field of dark Energy centered on the necromancer that allows " +
                "him to absorb half the Life Points lost by all living beings within 50 meters of " +
                "him. These Life Points go towards healing any sort of wounds he may have " +
                "suffered. Well of Life itself does not inflict damage, but necromancers usually " +
                "combine its effects with other damaging magics at their disposal.",
        "+50 meters to radius",
        30,
        10,
        false,
        listOf(SpellType.Automatic)
    )

    private val cursedLand = Spell(
        "Cursed Land",
        Element.Necromancy,
        true,
        88,
        350,
        "The caster corrupts a small fragment of the world, haunting a section of land. " +
                "Inside this area, all deceased individuals are instantaneously resurrected as " +
                "undead creatures subject to his control. Deceased characters automatically " +
                "become animated corpses, but one out of a hundred are resurrected as a specter. " +
                "These undead creatures only act inside the cursed land (see Level 28 and Level " +
                "60 spells of this Path as a reference). The magic sustaining them will vanish " +
                "if they leave the area. The spell haunts a one-kilometer radius, stationary to " +
                "the place where it was cast.",
        "+1 kilometer to radius",
        40,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val sustenance = Spell(
        "Sustenance",
        Element.Necromancy,
        true,
        90,
        200,
        "This spell affects the essence of one or several undead creatures maintained by the " +
                "spell, allowing for their existence even after the necromancer stops paying for " +
                "their maintenance. For instance, if the spellcaster uses Sustenance upon a " +
                "zombie animated by the spell Raise Corpses, the creature will continue to stay " +
                "active once that spell has ended. The caster may affect as many individuals as " +
                "he may wish, provided the total of their Presences is not higher than 60.",
        "+10 to the maximum Presence affected",
        20,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val rawMaterial = Spell(
        "Raw Material",
        Element.Necromancy,
        true,
        92,
        350,
        "This spell generates the corrupt energy used by necromantic magic to resurrect " +
                "corpses and spirits, enabling the caster to create the raw material necessary " +
                "to resurrect corpses at a later stage. The spell forms necromantic material, " +
                "equivalent to a thousand human bodies, or a smaller amount of other more " +
                "powerful creatures instead.",
        "+1000 additional bodies",
        50,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val lordOfTheDead = Spell(
        "Lord of the Dead",
        Element.Necromancy,
        true,
        96,
        300,
        "The Lord of the Dead extends its presence in the world, subduing all undead " +
                "creatures in a 100-kilometer radius. All creatures within the spell area are " +
                "subjected to the spellcaster\'s control unless they pass a MR Check with a " +
                "Difficulty of 140. Once they do, no rerolls are necessary. Affected individuals " +
                "are not entitles to a new check unless they alter their Resistances.",
        "+100 kilometers to radius and +5 to MR Difficulty",
        30,
        5,
        true,
        listOf(SpellType.Automatic)
    )

    private val comeBackFromDead = Spell(
        "Come Back from the Dead",
        Element.Necromancy,
        true,
        98,
        400,
        "This spell returns a deceased creature\'s spirit to the world, even after it has " +
                "gone back to the Flow of Souls or has been scattered. The character returns as " +
                "an undead being, with his soul and essence untouched. If the body of the " +
                "deceased character still exists, it will come back to it. If it does not, he " +
                "may reincarnate in a new one or become an immaterial specter. As is the case in " +
                "similar spells, the time elapsed between death and resurrection is a restraining " +
                "factor to the efficacy of the spell. Resuscitated souls cannot have a base " +
                "Presence higher than 30, and the elapsed time since death cannot exceed one " +
                "month. Destroyed or resurrected souls cannot be affected by this spell.",
        "+5 to the maximum Presence affected and twice the time elapsed since the " +
                "subject\'s death",
        40,
        null,
        false,
        listOf(SpellType.Automatic)
    )

    private val awakening = Spell(
        "The Awakening",
        Element.Necromancy,
        true,
        100,
        900,
        "The Awakening is the most powerful of necromantic spells. By corrupting a part of " +
                "the world, the caster extends his shadow over all existence and brings back the " +
                "dead, subjugating them to his full control. The Awakening is not limited to a " +
                "normal area of effect; all deceased persons in the world with a Presence lower " +
                "than 50 are affected. In case spirits are still awaiting the calling they are " +
                "raised either as specters or animated corpses. A small percentage of the " +
                "deceased - one out of every ten thousand - come back with full faculties.",
        "+5 to the maximum Presence affected",
        50,
        null,
        false,
        listOf(SpellType.Automatic)
    )

    val fullBook = listOf(
        feelDeath,
        null,
        seeBeyond,
        controlScavengers,
        spectralShield,
        drainLife,
        null,
        necromanticDetection,
        talkToDead,
        necromanticParalysis,
        necromitude,
        null,
        deathBeam,
        raiseCorpses,
        deadBody,
        drainMagic,
        null,
        destroyUndead,
        drainCharacteristic,
        controlDead,
        witherLife,
        null,
        necromanticShield,
        dominateLife,
        vampireStigma,
        spectralForm,
        null,
        necromanticModification,
        summonDead,
        raiseSpecters,
        drainLifeForce,
        null,
        kill,
        soulBeam,
        necromanticChimera,
        lifePerversion,
        null,
        vassalage,
        drainSouls,
        surpassDeath,
        trueRise,
        null,
        wellOfLife,
        cursedLand,
        sustenance,
        rawMaterial,
        null,
        lordOfTheDead,
        comeBackFromDead,
        awakening
    )
}