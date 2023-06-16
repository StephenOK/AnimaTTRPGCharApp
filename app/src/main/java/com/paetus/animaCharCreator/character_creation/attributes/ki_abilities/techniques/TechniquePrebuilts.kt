package com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques

import com.paetus.animaCharCreator.character_creation.Element

/**
 * Record of prebuilt techniques the character may have access to.
 */
class TechniquePrebuilts{
    val excisumAeris = Technique(
        "Excisum Aeris",
        "This technique allows the character to emit a full Ki explosion at a specific " +
                "moment, projecting a blow at such speed that it causes the air to warp while he " +
                "advances to attack. This technique uses the Base Damage of the user's weapon.",
        1,
        mutableListOf(0, 0, 0, 0, 0, 0),
        mutableListOf(
            TechniqueEffect(
                "Long-Distance Attack", "+50m", 15, 3, Pair(4, 6),
                mutableListOf(0, 4, 0, 0, 2, 0), mutableListOf(null, 2, 3, 4, 0, 1), mutableListOf(
                    Element.Air), 1),
            TechniqueEffect(
                "Initiative Augmentation", "+50", 10, 1, Pair(2, 4),
                mutableListOf(0, 0, 4, 0, 0, 0), mutableListOf(null, 1, 0, 2, 3, 3), mutableListOf(
                    Element.Air, Element.Water, Element.Fire), 1)
        )
    )

    val velocitasVentas = Technique(
        "Velocitas Ventas",
        "By increasing his speed beyond human limits, a character can travel so fast " +
                "that his body seems to split. In this way, he can attack four times during the " +
                "Combat Turn while also adding a bonus to his Final Initiative.",
        1,
        mutableListOf(0, 0, 0, 0, 0, 0),
        mutableListOf(
            TechniqueEffect("Limited Additional Attack", "+3", 15, 3, Pair(9, 12),
                mutableListOf(0, 7, 3, 0, 4, 0), mutableListOf(null, 0, 2, 1, 3, 3), mutableListOf(
                    Element.Air, Element.Water, Element.Dark), 1),
            TechniqueEffect("Initiative Augmentation", "+50", 10, 1, Pair(2, 4),
                mutableListOf(0, 0, 4, 0, 0, 0), mutableListOf(null, 1, 0, 2, 3, 3), mutableListOf(
                    Element.Air, Element.Water, Element.Fire), 1)
        )
    )

    val excisumMagister = Technique(
        "Excisum Magister",
        "The fighter concentrates all his energy in a single move - an attack of such " +
                "speed that it has the ability to split an opponent's body. This Technique " +
                "increases both Base Initiative and Attack Ability.",
        2,
        mutableListOf(0, 0, 0, 0, 0, 0),
        mutableListOf(
            TechniqueEffect("Initiative Augmentation", "+125", 25, 4, Pair(8, 11),
                mutableListOf(0, 0, 8, 0, 0, 0), mutableListOf(null, 1, 0, 2, 3, 3), mutableListOf(
                    Element.Air, Element.Water, Element.Fire), 2),
            TechniqueEffect("Attack Ability", "+75", 20, 6, Pair(8, 11),
                mutableListOf(0, 8, 0, 0, 5, 0), mutableListOf(2, 1, 2, null, 2, 3), mutableListOf(
                    Element.Air, Element.Fire, Element.Dark), 1)
        )
    )

    val magnusExacter = Technique(
        "Magnus Exacter",
        "The character moves at full speed shattering all obstacles in his way up to a " +
                "10 meter radius. The victims of the attack only manage to see someone suddenly " +
                "materialize and charge against them, only to disappear soon after. This Technique " +
                "uses the Base Damage of the user's weapon.",
        2,
        mutableListOf(0, 0, 0, 0, 0, 0),
        mutableListOf(
            TechniqueEffect("Area Attack", "10m radius", 15, 2, Pair(3, 5),
                mutableListOf(0, 0, 0, 0, 3, 0), mutableListOf(null, 2, 3, 3, 0, 1), mutableListOf(
                    Element.Dark, Element.Light, Element.Fire), 1),
            TechniqueEffect("Initiative Augmentation", "+150", 30, 5, Pair(10, 13),
                mutableListOf(0, 7, 7, 0, 0, 0), mutableListOf(null, 1, 0, 2, 3, 3), mutableListOf(
                    Element.Air, Element.Water, Element.Fire), 2)
        )
    )

    val summum = Technique(
        "Summum",
        "The character reaches out his arms and vanishes momentarily. Right at that " +
                "moment, he begins to materialize around the victim, continually going back and " +
                "forth through his body, destroying him completely in the process. The attacker " +
                "appears to vanish, then materialize in a whirling blur of motion that completely " +
                "destroys his victim. This Technique allows a character to perform nine attacks " +
                "in the same Combat Turn, the first of which is usually accompanied by Surprise " +
                "due to its reaction speed.",
        3,
        mutableListOf(0, 0, 0, 0, 0, 0),
        mutableListOf(
            TechniqueEffect("Limited Additional Attack", "+8", 50, 10, Pair(22, 26),
                mutableListOf(0, 17, 0, 0, 8, 0), mutableListOf(null, 0, 2, 1, 3, 3), mutableListOf(
                    Element.Air, Element.Water, Element.Dark), 2),
            TechniqueEffect("Initiative Augmentation", "+175", 35, 6, Pair(12, 15),
                mutableListOf(0, 0, 15, 0, 0, 0), mutableListOf(null, 1, 0, 2, 3, 3), mutableListOf(
                    Element.Air, Element.Water, Element.Fire), 3,)
        )
    )

    val feuer = Technique(
        "Feuer",
        "By concentrating Ki in his weapon, a character can surround it with an aura " +
                "of fire, thus increasing its Base Damage by 25 points and gaining the possibility " +
                "of attacking in the Heat Table as a primary Critical. Feuer can be maintained " +
                "investing 1 Ki Point for Will and 1 for Strength in each round. This is also a key " +
                "Technique for performing subsequent attacks of greater power.",
        1,
        mutableListOf(1, 0, 0, 0, 0, 1),
        mutableListOf(
            TechniqueEffect("Damage Augmentation", "+25", 5, 1, Pair(2, 4),
                mutableListOf(2, 0, 0, 0, 0, 0), mutableListOf(0, 3, null, 1, 2, 1), mutableListOf(
                    Element.Fire, Element.Earth), 1),
            TechniqueEffect("Elemental Attack", "Heat", 5, 1, Pair(2, 4),
                mutableListOf(0, 0, 0, 0, 1, 4), mutableListOf(3, 3, null, 2, 0, 1), mutableListOf(
                    Element.Fire),1)
        )
    )

    val leFeu = Technique(
        "Le Feu",
        "This Technique unleashes a potent ball of fire which can strike a target up to " +
                "20 meters away. Le Feu does not use the weapon's damage, but twice the character's " +
                "Base Presence plus his Power Bonus.",
        1,
        mutableListOf(0, 0, 0, 0, 0, 0),
        mutableListOf(
            TechniqueEffect("Attack Ability", "+40", 10, 3, Pair(4, 6),
                mutableListOf(1, 3, 0, 0, 4, 0), mutableListOf(2, 0, 2, null, 2, 3), mutableListOf(
                    Element.Air, Element.Fire, Element.Dark), 1),
            TechniqueEffect("Long-Distance Attack", "20m", 10, 2, Pair(3, 5),
                mutableListOf(0, 0, 0, 0, 1, 5), mutableListOf(null, 2, 3, 4, 0, 1), mutableListOf(
                    Element.Air, Element.Water, Element.Fire), 1)
        )
    )

    val horecka = Technique(
        "Horecka",
        "This Technique unleashes an inferno that devours everything contained within " +
                "50 meter radius around the character in a huge explosion. This fire is so intense " +
                "it doubles the weapon's original Base Damage. Horecka requires a character to " +
                "maintain the first-level Technique, Feuer.",
        2,
        mutableListOf(0, 0, 0, 0, 0, 0),
        mutableListOf(
            TechniqueEffect("Damage Multiplier", "x2", 25, 4, Pair(10, 15),
                mutableListOf(2, 0, 0, 0, 2, 8), mutableListOf(0, 3, null, 2, 1, 1), mutableListOf(
                    Element.Light, Element.Water, Element.Earth), 1),
            TechniqueEffect("Area Attack", "50m radius", 30, 5, Pair(4, 6),
                mutableListOf(0, 6, 0, 0, 4, 2), mutableListOf(null, 2, 3, 3, 0, 1), mutableListOf(
                    Element.Dark, Element.Light, Element.Fire), 2),
            TechniqueEffect("Special Requirements", "Determined Condition", -15, 0, Pair(0, 0),
                mutableListOf(0, 0, 0, 0, 0, 0), mutableListOf(null, null, null, null, null, null), mutableListOf(
                    Element.Free), 1)
        )
    )

    val vatra = Technique(
        "Vatra",
        "Like a phoenix, the attacker wreaths himself in flames and charges against " +
                "his opponent with an attack that consumes him physically and spiritually. This " +
                "attack increases damage, Attack Ability, and the resulting Critical (if any). " +
                "Vatra requires a character to maintain the first-level Technique, Feuer.",
        2,
        mutableListOf(0, 0, 0, 0, 0, 0),
        mutableListOf(
            TechniqueEffect("Attack Ability", "+75", 20, 6, Pair(8, 11),
                mutableListOf(0, 6, 0, 0, 0, 5), mutableListOf(2, 0, 2, null, 2, 3), mutableListOf(
                    Element.Air, Element.Fire, Element.Dark), 1),
            TechniqueEffect("Damage Augmentation", "+75", 20, 3, Pair(6, 9),
                mutableListOf(1, 0, 0, 3, 0, 7), mutableListOf(0, 3, null, 1, 2, 1), mutableListOf(
                    Element.Fire, Element.Earth), 1),
            TechniqueEffect("Critical Enhancement", "+40", 10, 3, Pair(4, 6),
                mutableListOf(0, 0, 0, 0, 6, 0), mutableListOf(1, 2, null, 2, 0, 1), mutableListOf(
                    Element.Fire, Element.Earth), 1),
            TechniqueEffect("Special Requirements", "Determined Condition", -10, 0, Pair(0, 0),
                mutableListOf(0, 0, 0, 0, 0, 0), mutableListOf(null, null, null, null, null, null), mutableListOf(
                    Element.Free), 1)
        )
    )

    val eld = Technique(
        "Eld",
        "This frightening Technique has devastating effects, both on the victim and " +
                "the performer. In order to employ it, a character must sacrifice part of his " +
                "vital energy, suffering the resulting Damage in the process. In addition to the " +
                "increase in the character's Attack Ability, the Base Damage is increased by an " +
                "amount equivalent to twice the Life Points sacrificed. Eld requires a character " +
                "to maintain the first-level Technique, Feuer.",
        3,
        mutableListOf(0, 0, 0, 0, 0, 0),
        mutableListOf(
            TechniqueEffect("Sacrifice", "Double Vital Sacrifice", 50, 4, Pair(10, 10),
                mutableListOf(10, 0, 0, 0, 0, 0), mutableListOf(0, 3, null, 1, 2, 1), mutableListOf(
                    Element.Fire, Element.Earth), 1),
            TechniqueEffect("Attack Ability", "+150", 40, 14, Pair(22, 26),
                mutableListOf(0, 10, 0, 0, 0, 19), mutableListOf(2, 0, 2, null, 2, 3), mutableListOf(
                    Element.Air, Element.Fire, Element.Dark), 2),
            TechniqueEffect("Special Requirements", "Determined Condition", -30, 0, Pair(0, 0),
                mutableListOf(0, 0, 0, 0, 0, 0), mutableListOf(null, null, null, null, null, null), mutableListOf(
                    Element.Free), 1)
        )
    )

    val theScales = Technique(
        "The Scales",
        "Dragons are equipped to face a multitude of enemies. Thus, they can repel " +
                "several attacks without applying penalties. This Technique allows the user to " +
                "repel seven attacks without compromising his ability.",
        1,
        mutableListOf(0, 0, 0, 0, 0, 0),
        mutableListOf(
            TechniqueEffect("Additional Defense", "+6", 20, 6, Pair(5, 8),
                mutableListOf(0, 3, 1, 3, 0, 0), mutableListOf(null, 1, 0, 1, 3, 3), mutableListOf(
                    Element.Light), 1)
        )
    )

    val theClaws = Technique(
        "The Claws",
        "There are few things as lethal as a dragon's claws. Executing this Technique " +
                "permits the user to make two attacks, each with a 40-point bonus to Base Damage.",
        1,
        mutableListOf(0, 0, 0, 0, 0, 0),
        mutableListOf(
            TechniqueEffect("Additional Attack", "+1", 20, 3, Pair(6, 9),
                mutableListOf(0, 4, 4, 0, 0, 0), mutableListOf(null, 0, 2, 1, 3, 3), mutableListOf(
                    Element.Air, Element.Water), 1),
            TechniqueEffect("Damage Augmentation", "+40", 10, 1, Pair(3, 5),
                mutableListOf(2, 0, 0, 4, 0, 0), mutableListOf(0, 3, null, 1, 2, 1), mutableListOf(
                    Element.Fire, Element.Earth), 1)
        )
    )

    val theFang = Technique(
        "The Fang",
        "This attack lowers the victim's AT by 6 points while increasing offensive " +
                "ability and reducing penalties for performing aimed Attacks.",
        2,
        mutableListOf(0, 0, 0, 0, 0, 0),
        mutableListOf(
            TechniqueEffect("Attack Ability", "+50", 15, 4, Pair(5, 8),
                mutableListOf(0, 5, 0, 0, 0, 0), mutableListOf(2, 0, 2, null, 2, 3), mutableListOf(
                    Element.Air, Element.Fire, Element.Dark), 1),
            TechniqueEffect("Armor Destruction", "-6", 20, 3, Pair(6, 9),
                mutableListOf(3, 0, 0, 8, 0, 0), mutableListOf(0, 2, null, 2, 1, 2), mutableListOf(
                    Element.Dark, Element.Fire), 2),
            TechniqueEffect("Combat Maneuvers and Aiming", "-50", 10, 2, Pair(3, 5),
                mutableListOf(0, 0, 6, 0, 0, 0), mutableListOf(null, 0, 1, 2, 2, 2), mutableListOf(
                    Element.Air), 1)
        )
    )

    val theTail = Technique(
        "The Tail",
        "A dragon's tail flap can send entire armies flying. This Technique allows a " +
                "character to make a forceful attack within an 80-foot radius using his weapon's " +
                "Base Damage and increasing its offensive ability. Any individual hit by this " +
                "Technique must make and Opposed Check against Strength 16 or be knocked to the ground.",
        2,
        mutableListOf(0, 0, 0, 0, 0, 0),
        mutableListOf(
            TechniqueEffect("Projection", "16", 25, 7, Pair(8, 11),
                mutableListOf(2, 0, 0, 8, 0, 0), mutableListOf(0, 3, null, 2, 1, 1), mutableListOf(
                    Element.Earth, Element.Fire), 2),
            TechniqueEffect("Attack Ability", "+40", 10, 3, Pair(4, 6),
                mutableListOf(0, 6, 0, 0, 0, 0), mutableListOf(2, 0, 2, null, 2, 3), mutableListOf(
                    Element.Air, Element.Fire, Element.Dark), 1),
            TechniqueEffect("Area Attack", "25m radius", 20, 3, Pair(4, 6),
                mutableListOf(0, 0, 0, 0, 6, 0), mutableListOf(null, 2, 3, 3, 0, 1), mutableListOf(
                    Element.Dark, Element.Light, Element.Fire), 1)
        )
    )

    val dragonsBreath = Technique(
        "The Dragon's Breath",
        "This Technique fires a pulse of pure energy up to a maximum distance of 1 " +
                "kilometer. At the will of the user, the pulse bursts, causing utter destruction in a " +
                "100 meter radius. The damage Dragon's Breath deals is twice the character's Base " +
                "Presence, plus his Power Bonus, then multiplied by two. Dragon's Breath is a " +
                "Predetermined Technique and must be declared before it is used.",
        3,
        mutableListOf(0, 0, 0, 0, 0, 0),
        mutableListOf(
            TechniqueEffect("Long-Distance Attack", "1km", 35, 8, Pair(10, 13),
                mutableListOf(0, 0, 6, 10, 4, 0), mutableListOf(null, 2, 3, 4, 0, 1), mutableListOf(
                    Element.Air, Element.Water, Element.Fire), 2),
            TechniqueEffect("Area Attack", "100m radius", 30, 5, Pair(8, 11),
                mutableListOf(0, 10, 4, 0, 2, 0), mutableListOf(null, 2, 3, 3, 0, 1), mutableListOf(
                    Element.Dark, Element.Light, Element.Fire), 2),
            TechniqueEffect("Energy Damaging Attack", "", 5, 1, Pair(1, 2),
                mutableListOf(5, 0, 0, 0, 0, 0), mutableListOf(3, 3, null, 2, 0, 1), mutableListOf(
                    Element.Fire, Element.Light, Element.Dark), 1),
            TechniqueEffect("Damage Multiplier", "x2", 25, 4, Pair(10, 15),
                mutableListOf(10, 0, 0, 0, 0, 0), mutableListOf(0, 3, null, 2, 1, 1), mutableListOf(
                    Element.Fire, Element.Earth), 1),
            TechniqueEffect("Predetermination", "", -20, 0, Pair(0, 0),
                mutableListOf(0, 0, 0, 0, 0, 0), mutableListOf(null, null, null, null, null, null), mutableListOf(
                    Element.Free), 1)
        )
    )

    //compile prebuilt techniques into a single list
    val allTechniques = listOf(excisumAeris, velocitasVentas, excisumMagister, magnusExacter,
        summum, feuer, leFeu, horecka, vatra, eld, theScales, theClaws, theFang, theTail, dragonsBreath)
}