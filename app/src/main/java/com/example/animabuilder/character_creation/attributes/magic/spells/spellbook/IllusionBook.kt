package com.example.animabuilder.character_creation.attributes.magic.spells.spellbook

import com.example.animabuilder.character_creation.Element
import com.example.animabuilder.character_creation.attributes.magic.spells.Spell
import com.example.animabuilder.character_creation.attributes.magic.spells.SpellType

/**
 * List of spells associated with the illusion element.
 */
class IllusionBook{
    private val illusorySound = Spell(
        "Illusory Sound",
        Element.Illusion,
        true,
        2,
        30,
        "This spell allows the caster to create any sound, including human voices, in a 20-" +
                "meter radius area. All subjects within the area of effect must pass a MR Check " +
                "with a Difficulty of 100 to disbelieve the effect, though the spellcaster may " +
                "choose which specific characters hear the illusion.",
        "+10 meters to radius and +5 to MR Difficulty",
        10,
        50,
        false,
        listOf(SpellType.Automatic)
    )

    private val illusorySmell = Spell(
        "Illusory Smell",
        Element.Illusion,
        true,
        6,
        30,
        "This spell creates an illusory smell. It affects those subjects in a 20-meter " +
                "radius who fail a MR Check with a Difficulty of 100. The caster can choose who " +
                "will smell the illusory scent and who will not.",
        "+10 meters to radius and +5 to MR Difficulty",
        10,
        50,
        false,
        listOf(SpellType.Automatic)
    )

    private val illusoryTouch = Spell(
        "Illusory Touch",
        Element.Illusion,
        true,
        10,
        30,
        "This spell can distort the touch or the taste of a specific element. The caster " +
                "decides the element\'s new taste or feel, which is noticed by all within a 20-" +
                "meter radius who fail a MR Check with a Difficulty of 100. The caster can choose " +
                "who notices the illusion and who does not.",
        "+10 meters to radius and +5 to MR Difficulty",
        10,
        50,
        false,
        listOf(SpellType.Automatic)
    )

    private val visualIllusion = Spell(
        "Visual Illusion",
        Element.Illusion,
        true,
        12,
        40,
        "This spell creates a false image that can deceive a viewer. The image must remain " +
                "static and have only a maximum radius of 1 square meter. The spell affects anyone " +
                "who sees the image and fails a MR Check with a Difficulty of 100. It is up to " +
                "the caster to decide who will see the image and who will not.",
        "+1 square meter to radius and +1 to MR Difficulty",
        10,
        50,
        false,
        listOf(SpellType.Automatic)
    )

    private val detectIllusion = Spell(
        "Detect Illusion",
        Element.Illusion,
        true,
        16,
        60,
        "This spell enables the spellcaster to sense the presence of all illusions with a " +
                "Zeonic Value of 80 or less in a 50-meter radius.",
        "+10 meters to radius and +10 to Zeonic Value",
        20,
        10,
        false,
        listOf(SpellType.Detection)
    )

    private val sweetTalk = Spell(
        "Sweet Talk",
        Element.Illusion,
        true,
        20,
        50,
        "This spell enhances the targeted individual\'s charisma and personal charm. The " +
                "character receives a +5 bonus to the Leadership and Persuasion Secondary Abilities.",
        "+10 to Leadership and Persuasion",
        10,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val alterAppearance = Spell(
        "Alter Appearance",
        Element.Illusion,
        true,
        22,
        60,
        "The spellcaster can change the appearance of an individual or object into that of " +
                "another of his choosing. This spell will only increase or decrease the targeted " +
                "individual\'s Size and Appearance by two degrees. All subjects in contact with " +
                "the image can see through the illusion if they pass a MR Check with a Difficulty " +
                "of 120. Once a viewer is affected by an illusion, he only receives a new " +
                "Resistance Check when he has reason to doubt the identity or appearance of the subject.",
        "+10 to MR Difficulty",
        10,
        10,
        true,
        listOf(SpellType.Automatic)
    )

    private val illusoryInvisibility = Spell(
        "Illusory Invisibility",
        Element.Illusion,
        true,
        26,
        60,
        "This spell allows the caster to make any being or object vanish from sight. He " +
                "can affect any number of people, as long as the sum of their Presence does not " +
                "exceed 140. Only individuals who pass a MR Check with a Difficulty of 120 can " +
                "detect the presence of the invisible character through their sense of vision.",
        "+5 to MR and +10 to the maximum Presence affected",
        10,
        20,
        false,
        listOf(SpellType.Automatic)
    )

    private val mirrorImage = Spell(
        "Mirror Image",
        Element.Illusion,
        true,
        30,
        80,
        "This spell creates eight illusory copies of any given target. The caster cannot " +
                "place these mirror images more than 5 meters apart from one another. Acting as " +
                "mirrors, they perform the same actions as the targeted individual. Any images " +
                "hit by an Energy-damaging attack are immediately destroyed. Seeing through this " +
                "illusion requires a MR Check with a Difficulty of 120.",
        "+5 to MR and +2 images",
        10,
        10,
        false,
        listOf(SpellType.Automatic)
    )

    private val totalIllusion = Spell(
        "Total Illusion",
        Element.Illusion,
        true,
        32,
        80,
        "This spell creates a complete illusion that deceives all five of a victim\'s senses. " +
                "The caster can create any inanimate object with a volume not exceeding 1 square " +
                "meter. The illusion can be destroyed by Energy-damaging attacks. This spell " +
                "affects anyone able to see, hear, smell, or feel the illusion who fails a MR " +
                "Check with a Difficulty of 120.",
        "+1 square meter to volume and +5 to MR",
        20,
        50,
        false,
        listOf(SpellType.Automatic)
    )

    private val confusion = Spell(
        "Confusion",
        Element.Illusion,
        true,
        36,
        50,
        "This spell confounds all five senses of a single target. The target must pass a " +
                "MR Check with a Difficulty of 120 to resist the spell. If he fails, he suffers " +
                "a penalty equal to his Failure Level to all of his perception-based Secondary " +
                "Abilities. Furthermore, if he fails by more than 40, the target also suffers a " +
                "-20 All Action Penalty due to dizziness. The target cannot make a new check " +
                "unless he increases his Resistances.",
        "+5 to MR Difficulty",
        10,
        20,
        false,
        listOf(SpellType.Spiritual)
    )

    private val createIllusoryBeing = Spell(
        "Create Illusory Being",
        Element.Illusion,
        true,
        40,
        60,
        "This spell creates a first-level illusory being. The entity is fashioned according " +
                "to the caster\'s desires, using the rules set forth in Chapter 26  for Beings " +
                "Between Worlds. However, the illusory being\'s nature automatically grants it " +
                "the Physical Exemption ability.\nBecause the creature is not real, it can not " +
                "inflict damage or affect physical reality whatsoever. All non-Energy-based " +
                "attacks pass right through it without damaging it in any way. This spell must be " +
                "cast upon a specific area not exceeding a radius of 20 meters. Those characters " +
                "entering the spell\'s area of effect must pass a MR Check with a Difficulty of " +
                "120 to avoid it. Even though the spell is circumscribed to a specific zone, " +
                "the illusory creature can leave that area while chasing after a subject " +
                "affected by the spell. However, it remains unseen by anyone not previously " +
                "inside the spell\'s area. Keep in mind that those subjects who pass the MR Check " +
                "do not exist to the illusory creature, and it will ignore them. Illusions may " +
                "have a maximum of two levels more than the caster.",
        "+5 to MR, +1 to the created being\'s level and +1 meter to radius",
        10,
        20,
        true,
        listOf(SpellType.Automatic)
    )

    private val illusionResistance = Spell(
        "Resistance to Illusions",
        Element.Illusion,
        true,
        42,
        80,
        "This spell increases a subject\'s MR against illusory effects. It grants a +30 " +
                "bonus to every MR Check made against an Illusion spell. The effects of this " +
                "spell do not overlap, and subjects may be affected by it only once.",
        "+10 to MR against illusions",
        10,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val detectLie = Spell(
        "Detect Lie",
        Element.Illusion,
        true,
        46,
        80,
        "This spell automatically detects any lie told in the caster\'s presence. Every time " +
                "a lie is deliberately told before him, the liar must make a MR or PsR Check with " +
                "a Difficulty of 120 to prevent the caster from finding out. Lies unknowingly " +
                "told are not detected by the spell.",
        "+5 to MR or PsR Difficulty",
        10,
        10,
        true,
        listOf(SpellType.Automatic)
    )

    private val ghostlyIllusion = Spell(
        "Ghostly Illusion",
        Element.Illusion,
        true,
        50,
        120,
        "This spell creates one or several objects governed by the Ghostly Spell rules. Any " +
                "inanimate object the caster desires can be produced, from a sword to a wall, " +
                "provided that its theoretical Presence does not exceed 60. A character or " +
                "creature can avoid the spell and ignore the illusion if he or it passes a MR " +
                "Check with a Difficulty of 120. Anyone can make another check if he has reason " +
                "to doubt the reality of the object.",
        "+5 to the maximum Presence of the object and +5 to the MR Difficulty",
        20,
        20,
        false,
        listOf(SpellType.Automatic)
    )

    private val distortDetection = Spell(
        "Distort Detection",
        Element.Illusion,
        true,
        52,
        120,
        "Casting this spell, the caster distorts the result of any supernatural detection " +
                "methods in a 50-meter radius. The distortion may be applied in any possible " +
                "way: increasing or decreasing the potential, abilities, or situation of a " +
                "creature or object. The character using the supernatural detection will have to " +
                "pass a MR Check with a Difficulty of 120 to avoid deception.\nThis spell works " +
                "on the supernatural detection itself and not on the individuals inside an area. " +
                "Therefore, characters attempting to detect something inside the area must pass " +
                "the MR even if they are physically outside the spell\'s radius. An example of " +
                "this could be a spellcaster trying to locate an illusionist kilometers away " +
                "through magic.",
        "+50 meters to radius and 5 to the MR Difficulty",
        20,
        10,
        true,
        listOf(SpellType.Automatic)
    )

    private val lie = Spell(
        "Lie",
        Element.Illusion,
        true,
        56,
        100,
        "This spell allows the caster to convince his audience to believe anything he says, " +
                "no matter how ridiculous or absurd. The affected parties will not necessarily " +
                "obey orders from him, but they will believe what he says to be true. Any given " +
                "subject can avoid the result of the spell by passing a MR Check with a Difficulty " +
                "of 120. Characters are permitted a new Resistance roll each hour. In the case of " +
                "an exceptionally unbelievable lie, or if the subjects have been warned of this " +
                "ability, they may apply a +40 bonus to their MR Check.",
        "+5 to MR Difficulty",
        10,
        10,
        true,
        listOf(SpellType.Spiritual)
    )

    private val destroyIllusion = Spell(
        "Destroy Illusion",
        Element.Illusion,
        true,
        60,
        80,
        "This spell destroys any other Illusion spell with a Zeonic Value not greater than 60.",
        "+5 to Zeonic Value affected by the spell",
        40,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val ghostlyBeing = Spell(
        "Ghostly Being",
        Element.Illusion,
        true,
        62,
        80,
        "This spell creates a 4th-level ghostly being fashioned according to the caster\'s " +
                "desires using the Being Between Worlds rules in Chapter 26. Ghostly Being " +
                "functions exactly as the spell Create Illusory Being (Illusion level 40), except " +
                "that it has no area limitation, and those who fail the MR Check are subjected to " +
                "the rules of Ghostly Spells. A character might receive a new check if he had " +
                "reason to doubt the authenticity of the entity. The illusion may be a maximum of " +
                "three levels higher than the caster.",
        "+5 to the MR Difficulty and +1 to the being\'s level",
        10,
        20,
        false,
        listOf(SpellType.Automatic)
    )

    private val gullibility = Spell(
        "Gullibility",
        Element.Illusion,
        true,
        66,
        80,
        "This spell weakens a target\'s Resistance against illusion spells. The affected " +
                "party must pass a MR with a Difficulty of 120 or suffer a penalty to future MR " +
                "and PsR Checks against Illusion spells equal to his Failure Level. This penalty " +
                "will only work against spells of this Path.",
        "+10 to MR Difficulty",
        10,
        10,
        false,
        listOf(SpellType.Spiritual)
    )

    private val ghostlyAttack = Spell(
        "Ghostly Attack",
        Element.Illusion,
        true,
        70,
        100,
        "This spell projects a 100 point Base Damage discharge of Energy that uses the " +
                "Ghostly Spell rules. Character\'s can avoid the damage of this spell by passing " +
                "a MR Check with a Difficulty of 120. Given the attack\'s unreal status, it " +
                "cannot clash against other discharges.",
        "+5 to MR Difficulty and +5 to Base Damage",
        20,
        null,
        false,
        listOf(SpellType.Attack, SpellType.Spiritual)
    )

    private val lyingGift = Spell(
        "The Gift of Lying",
        Element.Illusion,
        true,
        72,
        140,
        "This spell can force an individual to lie. The illusionist can compel the target " +
                "to lie in absolutely everything he says, or about a specific subject matter. " +
                "The affected party is not be able to indicate the deceptive nature of his words " +
                "or convey any truthful information. A character can avoid the spell\'s effects " +
                "by passing a MR or PsR Check with a Difficulty of 120. Characters who fail this " +
                "Check receive a new check each day.",
        "+5 to MR or PsR Difficulty",
        20,
        10,
        true,
        listOf(SpellType.Spiritual)
    )

    private val illusoryLie = Spell(
        "Illusory Lie",
        Element.Illusion,
        true,
        76,
        140,
        "This spell introduces fake memories into an individual\'s mind. The illusionist is " +
                "able to alter memories and include as much new information as he desires. Once " +
                "under the effects of this spell, characters can not distinguish illusory " +
                "memories from their own real experiences. The MR or PsR Check for resisting " +
                "this spell has a Difficulty of 120. Characters receive a new roll only upon " +
                "finding a reason to doubt the authenticity of their recollections.",
        "+5 to MR or PsR Difficulty",
        20,
        10,
        true,
        listOf(SpellType.Spiritual)
    )

    private val majorIllusion = Spell(
        "Major Illusion",
        Element.Illusion,
        true,
        80,
        250,
        "This spell creates an enormous illusion affecting all five human senses. The spell " +
                "develops within a one-kilometer radius within which people see, hear, feel, " +
                "smell, and taste whatever the spellcaster desires. For instance, he may trick " +
                "someone into thinking a whole town is deserted, when it is actually a thriving " +
                "city. Any character entering the area of this spell is automatically affected by " +
                "the illusion unless he passes a MR Check with a Difficulty of 120. Affected " +
                "characters only receive a new check upon encountering a reason to doubt the " +
                "illusory world around them.",
        "+500 meters to radius and +5 to MR Difficulty",
        30,
        10,
        true,
        listOf(SpellType.Automatic)
    )

    private val fixIllusion = Spell(
        "Fix Illusion",
        Element.Illusion,
        true,
        82,
        250,
        "This spell creates a permanent illusion. In game terms, it adds 1000 Zeon " +
                "Maintenance Points to any spell from the Path of Illusion. Bear in mind that " +
                "this amount will not serve to increase the power of the spell. It only provides " +
                "the caster with the points to maintain the illusion constantly. It cannot be " +
                "used in connection with Free Access spells - even if these spells are from " +
                "Illusion slots.",
        "+50 to the Zeon of the Maintained spell",
        30,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val illusionSense = Spell(
        "Illusion of the Senses",
        Element.Illusion,
        true,
        86,
        200,
        "The caster has the ability to lead a subject to believe whatever he wishes. Though " +
                "the caster does not actually modify reality, his spell convinces an affected " +
                "character that an illusion is so real, that he will react as if it were true " +
                "in every aspect. For example, if an illusionist wants to trick somebody into " +
                "thinking he has no legs or arms, the subject will lose all sense of his limbs " +
                "and fall to the ground in the belief that he has, indeed, lost them. Another " +
                "example of this could be a fighter suffering from wounds who is led to believe, " +
                "not only that he is in perfect shape, but also that he possesses a +50 All " +
                "Action Bonus. A character convinced that he is dead automatically lapses into " +
                "unconsciousness. The MR of PsR Difficulty for resisting the spell is 120. A " +
                "character receives a new Resistance roll only upon becoming convinced he is " +
                "being deceived.",
        "+5 to MR or PsR Difficulty",
        20,
        10,
        false,
        listOf(SpellType.Spiritual)
    )

    private val nonExistence = Spell(
        "Non-Existence",
        Element.Illusion,
        true,
        90,
        220,
        "The target of this spell cannot be perceived by any of the natural senses - " +
                "although he still has a material existence. Others cannot see, smell, hear, " +
                "touch, or taste his presence in any way. In addition to this, the target does " +
                "not leave any visible trace or marks behind while the spell is maintained. The " +
                "only ways to realize a non-existent character\'s presence is via supernatural " +
                "(magical or Ki related) detection. A character can resist the effects of this " +
                "spell by passing a MR Check with a Difficulty of 120. Those subjects failing the " +
                "check are entitled to repeat it every time they have reason to believe there is " +
                "someone around.",
        "+5 to MR Difficulty",
        20,
        10,
        true,
        listOf(SpellType.Automatic)
    )

    private val deceiveDeath = Spell(
        "Deceive Death",
        Element.Illusion,
        true,
        92,
        500,
        "A person affected by this spell cannot die; the Flow of Souls will ignore his " +
                "presence and thus be unable to claim him. However, his physical form is " +
                "vulnerable to destruction, and even though his spirit will not abandon his " +
                "body, he will still suffer any adverse effects produced by damage. This spell " +
                "does not offer protection from soul-destroying effects. The maximum Presence " +
                "this spell affects is 80.",
        "+5 to the maximum Presence affected",
        40,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val worldOfLies = Spell(
        "World of Lies",
        Element.Illusion,
        true,
        96,
        500,
        "This spell creates an artificial reality that the caster can modify as he pleases. " +
                "He might create a city out of nowhere and turn it into a fantasy land in the " +
                "next turn, for example. Imagination is the limit. This spell covers a one-" +
                "kilometer radius within which all illusions have Ghostly Spell status. Everyone " +
                "entering the spell\'s radius must pass a MR Check with a Difficulty of 140 or fall " +
                "beneath the power of this spell. World of Lies also allows the caster to create " +
                "unreal creatures to populate it. These creatures exist as illusions of a Ghostly " +
                "nature. The caster has 100 levels at his disposal to distribute among these " +
                "entities - although none of them can have more than half of their level rounded " +
                "up. In other words, the caster may create one hundred 1st level Ghostly Beings, " +
                "ten 5th level beings, and five 10th level beings, or any other possible " +
                "combination. The entities are fashioned according to the caster\'s desires using " +
                "the Beings Between Worlds rules in Chapter 26. These creatures have illusory " +
                "intelligence and life; they are able to act independently following the orders " +
                "given by their master. Those characters entering the area of influence and " +
                "failing the Resistance Check can only repeat it when encountering doubts on the " +
                "reality of their surroundings.",
        "+5 to MR Difficulty, +50 levels distributed among Beings, and double the area of coverage",
        40,
        5,
        true,
        listOf(SpellType.Automatic)
    )

    private val falseReality = Spell(
        "False Reality",
        Element.Illusion,
        true,
        100,
        600,
        "This spell creates a distorted reality and makes something fake real. In this way, " +
                "events that can have never taken place can be invented and made to produce " +
                "effects upon the present. History is not really altered, since the spell only " +
                "manages to change the status of things at the time it is cast. However, few " +
                "people are able to tell the difference. The spell cannot accomplish the " +
                "impossible; it can only alter feasible events which are likely to happen if " +
                "the appropriate situation is created by the caster.\nFor example, the caster " +
                "might say a certain mountain pass has been blocked due to a landslide caused by " +
                "thieves making their escape. According to this, the False Reality spell would " +
                "affect a random bunch of burglars and all individuals in the mountain pass at " +
                "the time. Another example may be that the caster could claim that a non-existent " +
                "entity, called Ethon, as about to destroy a city. He only needs to be able to " +
                "justify the birth of the creature by saying, for instance, that it was created " +
                "by a powerful sorcerer in the past. It is all up to the creativity he puts into " +
                "the lie.\nUtilizing MR Checks for this spell is hard to do. It is necessary to " +
                "find the being or object with the highest Resistance introduced \"within the " +
                "lie\" or affected by it. In the first of the examples above, the burglars would " +
                "make the MR Check. However, if a high-level individual crossed the pass at the " +
                "time, he would use his Magic Resistance instead of the thieves. In the second " +
                "example, a sorcerer powerful enough to create Ethon would be the one who do the " +
                "check. Whoever he is, the chosen individual may resist (disbelieve) this spell " +
                "with a MR Check with a base Difficulty of 120. The GM can adjust this Difficulty " +
                "depending on the nature of the False Reality created. Extremely absurd events or " +
                "circumstances should be easier to disbelieve.",
        "+5 to MR Difficulty",
        50,
        null,
        false,
        listOf(SpellType.Automatic)
    )

    val fullBook = listOf(
        illusorySound,
        null,
        illusorySmell,
        null,
        illusoryTouch,
        visualIllusion,
        null,
        detectIllusion,
        null,
        sweetTalk,
        alterAppearance,
        null,
        illusoryInvisibility,
        null,
        mirrorImage,
        totalIllusion,
        null,
        confusion,
        null,
        createIllusoryBeing,
        illusionResistance,
        null,
        detectLie,
        null,
        ghostlyIllusion,
        distortDetection,
        null,
        lie,
        null,
        destroyIllusion,
        ghostlyBeing,
        null,
        gullibility,
        null,
        ghostlyAttack,
        lyingGift,
        null,
        illusoryLie,
        null,
        majorIllusion,
        fixIllusion,
        null,
        illusionSense,
        null,
        nonExistence,
        deceiveDeath,
        null,
        worldOfLies,
        null,
        falseReality
    )
}