package com.example.animabuilder.character_creation.attributes.advantages.advantage_items

import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.Element
import com.example.animabuilder.character_creation.attributes.advantages.advantage_types.Advantage
import java.io.Serializable

class MagicAdvantages(private val charInstance: BaseCharacter): Serializable {
    val elementNames = listOf(
        "Light",
        "Dark",
        "Creation",
        "Destruction",
        "Air",
        "Earth",
        "Water",
        "Fire",
        "Essence",
        "Illusion"
    )

    val elementalCompatibility = Advantage(
        "Elemental Compatibility",
        "A character with this Advantage is naturally compatible with the powers of a " +
                "specific magical path, being simultaneously weaker in its opposite. His essence " +
                "is bound strongly to this element, and his magic is more powerful when he uses " +
                "those spells.",
        "The character has a special bonus of +20 to his MA and to his MR in the magical " +
                "path that he chooses. When he uses spells of the opposed path, he has a penalty " +
                "of -20 to his MA and to his MR. If the chosen path is necromancy, apply the " +
                "penalty to all other paths.",
        null,
        null,
        (elementNames.toSet() + "Necromancy").toList(),
        0,
        listOf(1),
        0,
        null,
        null
    )

    val naturalPath = Advantage(
        "Natural Knowledge of a Path",
        "A character with this Advantage possesses the capacity to cast certain spells " +
                "naturally without having to study them. He can unconsciously weave the powers " +
                "of the Soul Flow for one Path - as if the Path were simply responding to his " +
                "abilities. The character knows how to use the spells perfectly, but he does not " +
                "understand the theory behind them, nor can he explain it to others.",
        "This Advantage grants innate knowledge of a Path at level 40 without investing " +
                "Magic Levels points. As it is innate knowledge, the wizard can continue to " +
                "develop it beyond level 40 by spending new Magic Level points.",
        null,
        "This Advantage can be acquired again for different Paths.",
        elementNames,
        0,
        listOf(1),
        0,
        {input, _ ->
            when(input){
                0 -> charInstance.magic.naturalPaths.add(Element.Light)
                1 -> charInstance.magic.naturalPaths.add(Element.Dark)
                2 -> charInstance.magic.naturalPaths.add(Element.Creation)
                3 -> charInstance.magic.naturalPaths.add(Element.Destruction)
                4 -> charInstance.magic.naturalPaths.add(Element.Air)
                5-> charInstance.magic.naturalPaths.add(Element.Earth)
                6-> charInstance.magic.naturalPaths.add(Element.Water)
                7-> charInstance.magic.naturalPaths.add(Element.Fire)
                8-> charInstance.magic.naturalPaths.add(Element.Essence)
                9-> charInstance.magic.naturalPaths.add(Element.Illusion)
                10-> charInstance.magic.naturalPaths.add(Element.Necromancy)
            }

            charInstance.magic.updateSpellList()
        },
        {input, _ ->
            when(input){
                0 -> charInstance.magic.naturalPaths.remove(Element.Light)
                1 -> charInstance.magic.naturalPaths.remove(Element.Dark)
                2 -> charInstance.magic.naturalPaths.remove(Element.Creation)
                3 -> charInstance.magic.naturalPaths.remove(Element.Destruction)
                4 -> charInstance.magic.naturalPaths.remove(Element.Air)
                5-> charInstance.magic.naturalPaths.remove(Element.Earth)
                6-> charInstance.magic.naturalPaths.remove(Element.Water)
                7-> charInstance.magic.naturalPaths.remove(Element.Fire)
                8-> charInstance.magic.naturalPaths.remove(Element.Essence)
                9-> charInstance.magic.naturalPaths.remove(Element.Illusion)
                10-> charInstance.magic.naturalPaths.remove(Element.Necromancy)
            }

            charInstance.magic.updateSpellList()
        }
    )

    val contestedSpellMastery = Advantage(
        "Contested Spell Mastery",
        "The attack spells of a character with this Advantage are greater when it " +
                "clashes against another Supernatural Beam.",
        "The character applies a bonus of +50 to his roll to calculate the result of a " +
                "Collision against another beam.",
        null,
        null,
        null,
        null,
        listOf(1),
        0,
        null,
        null
    )

    val magicDevelopmentAptitude = Advantage(
        "Aptitude for Magic Development",
        "A character with this Advantage has the capacity to understand and achieve " +
                "levels of power with his spells far greater than his Intelligence would normally allow.",
        "A player can add 3 points to his character's Intelligence to determine the maximum " +
                "potential of the spell. This bonus is not applied to any other ability - not " +
                "even to calculate the character's level of magic.",
        null,
        null,
        null,
        null,
        listOf(1),
        0,
        null,
        null
    )

    val halfTreeAttuned = Advantage(
        "Half-Attuned to the Tree",
        "As in Elemental Compatibility, but in this case the character is naturally " +
                "compatible with the magic of half of the Mystical Tree.",
        "The wizard has a special bonus of +20 to his MA and his MR in the five magical " +
                "Paths of a segment of the Tree. In the rest, he has a penalty of -20 to his MA " +
                "and his MR.",
        "Necromancy is not included in this Advantage.",
        null,
        elementNames,
        0,
        listOf(2),
        0,
        null,
        null
    )

    val improvedInnateMagic = Advantage(
        "Improved Innate Magic",
        "A character with this Advantage cna execute his innate spells with a greater " +
                "potential than normal.",
        "The innate spells of the wizard add +10 to their potential as indicated by their " +
                "MA. Additional Creation Points increase the value to +20 and +30, respectively. " +
                "Thus a wizard with a MA of 100 whose player spent 2 points in this Advantage " +
                "could cast his innate spells with a value of up to 60 (40 by his MA, plus 20 " +
                "due to the Advantage).",
        null,
        null,
        null,
        null,
        listOf(1, 2, 3),
        0,
        null,
        null
    )

    val unspokenCasting = Advantage(
        "Unspoken Casting",
        "A character with this Advantage does not need to speak to control the powers " +
                "of the Soul Flow of souls.",
        "The character can cast spells in complete silence without reducing his MA.",
        null,
        null,
        null,
        null,
        listOf(1),
        0,
        null,
        null
    )

    val gesturelessCasting = Advantage(
        "Gestureless Casting",
        "A character with this Advantage does not need to make physical gestures to use " +
                "his magic; he can take any type of physical action without affecting his powers.",
        "The character does not reduce his MA if unable to gesture.",
        null,
        null,
        null,
        null,
        listOf(1),
        0,
        null,
        null
    )

    val superiorMagicRecovery = Advantage(
        "Superior Magic Recovery",
        "The essence of a character with this Advantage acts like a magnet for magic, " +
                "and he is able to regenerate his power at a faster rate than that of other " +
                "mystical individuals.",
        "The character recovers his Zeon at twice his normal Zeonic regeneration rate. " +
                "Spending additional Creation Points will triple or quadruple the normal rate.",
        null,
        null,
        null,
        null,
        listOf(1, 2, 3),
        0,
        {_, cost ->
            when(cost){
                1 -> charInstance.magic.changeRecoveryMult(2.0)
                2 -> charInstance.magic.changeRecoveryMult(3.0)
                3 -> charInstance.magic.changeRecoveryMult(4.0)
            }
        },
        {_, _ ->
            charInstance.magic.changeRecoveryMult(1.0)
        }
    )

    val advantages = listOf(elementalCompatibility, naturalPath, contestedSpellMastery, magicDevelopmentAptitude,
        halfTreeAttuned, improvedInnateMagic, unspokenCasting, gesturelessCasting, superiorMagicRecovery)





    val oralRequirement = Advantage(
        "Oral Requirement",
        "A character with this Disadvantage cna only cast spells if he can speak",
        "The character must be able to speak to accumulate magic and to cast his spells.",
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val requireGestures = Advantage(
        "Require Gestures",
        "A character with this Disadvantage must have complete freedom of movement in " +
                "order to control or use his powers.",
        "The character must move freely to accumulate magic and cast spells.",
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val magicalExhaustion = Advantage(
        "Magical Exhaustion",
        "A character with this Disadvantage suffers intense fatigue whenever he uses " +
                "magic. The mage will exhaust himself if he casts spells of great power, " +
                "weakening if he uses his abilities too much.",
        "The mage loses 1 point of Fatigue when casting a spell with a potential greater " +
                "than 100, 2 if it is greater than 200, and 3 if it is greater than 300.",
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val shamanism = Advantage(
        "Shamanism",
        "The magic practiced by this character has a tribal and shamanic character. " +
                "His supernatural powers are tied to the material world, and he needs certain " +
                "components to channel them and cast spells.",
        "The character requires material components to cast spells. Each spell requires a " +
                "different component as determined by the GM - according to its origin and the " +
                "knowledge of the character.",
        null,
        null,
        null,
        null,
        listOf(-2),
        0,
        null,
        null
    )

    val magicalTies = Advantage(
        "Magical Ties",
        "The powers of a character with this Disadvantage are tied to the same roots as " +
                "the magical paths, so his capacity to develop or specify his own spells is " +
                "practically impossible. The character obtains knowledge of his paths only and " +
                "cannot research his own spells.",
        "The magician cannot choose free spells of his magical paths or freely access " +
                "chosen spells.",
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        {_, _ ->
            charInstance.magic.setMagicTies(true)
        },
        {_, _ ->
            charInstance.magic.setMagicTies(false)
        }
    )

    val slowMagicRecovery = Advantage(
        "Slow Recovery of Magic",
        "Magic has problems passing through the wizard\'s essence; he experiences " +
                "difficulties recovering power he has used.",
        "Reduce the Zeonic regeneration of the character by half.",
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        {_, _ ->
            charInstance.magic.changeRecoveryMult(0.5)
        },
        {_, _ ->
            charInstance.magic.changeRecoveryMult(1.0)
        }
    )

    val magicBlockage = Advantage(
        "Magic Blockage",
        "Magic does not flow naturally through the body and soul of a character with " +
                "this Disadvantage. Some type of blockage prevents him from channeling the powers " +
                "of the environment, and his own soul does not regenerate magic either. When he " +
                "uses his power, the character never recovers it by himself. The only way he can " +
                "do so is to feed on the mystical energies of other magical beings or objects.",
        "The character lacks Zeonic regeneration and does not naturally recover the " +
                "points of Zeon he consumes. He regains magic only by draining objects that " +
                "allow it or living beings with the Gift.",
        "This Disadvantage cannot be combined with Slow Recovery of Magic",
        null,
        null,
        null,
        listOf(-2),
        0,
        {_, _ ->
            charInstance.magic.changeRecoveryMult(0.0)
        },
        {_, _ ->
            charInstance.magic.changeRecoveryMult(1.0)
        }
    )

    val actionRequirement = Advantage(
        "Action Requirement",
        "The control that a character has over the powers of the Soul Flow is fickle, " +
                "and they respond for him only while he performs a certain action. If, for " +
                "example, his magic is tied to dance, his spells will work only while he dances. " +
                "It is possible that the magical powers work solely in specific situations or " +
                "conditions, such as during the day or with one's feet on the ground.",
        "The character may use magic only if the specific action is performed or the " +
                "specific conditions of the Disadvantage are met. If the action is a Secondary " +
                "Ability, the character must roll a check against Difficult (DIF) to cast his spells.",
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val disadvantages = listOf(oralRequirement, requireGestures, magicalExhaustion, shamanism, magicalTies,
        slowMagicRecovery, magicBlockage, actionRequirement)
}