package com.example.animabuilder.character_creation.attributes.magic.spells.spellbook

import com.example.animabuilder.character_creation.Element
import com.example.animabuilder.character_creation.attributes.magic.spells.FreeSpell
import com.example.animabuilder.character_creation.attributes.magic.spells.SpellType
import java.io.Serializable

/**
 * List of spells associated with no element.
 */
class FreeBook: Serializable {
    private val tieSpell = FreeSpell(
        "Tie",
        true,
        5,
        40,
        "This spell causes chains, ropes, or any type of string to be knotted with a 140 " +
                "Base Ability in Sleight of Hand. The caster may command strings to tie a subject " +
                "up, in which case the target will have to fight off an automatic attack of " +
                "Absurd Difficulty, following the rules of Trapping (this is to say, he would " +
                "receive a direct attack with a Final Ability of 180). The Strength of the " +
                "binding depends on the material used. Just as a reference, a very thick rope " +
                "would have Strength of 10, whereas metallic chains would have Strength 12. Note " +
                "that strings are not created by the spell.",
        "+10 to Sleight of Hand ability",
        10,
        null,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Destruction, Element.Illusion)
    )

    private val createFire = FreeSpell(
        "Create Fire",
        true,
        5,
        40,
        "This spell creates a single fire Intensity if cast upon a flammable substance. If " +
                "fire catches on, it will not be necessary to maintain the spell.",
        "+1 fire Intensity",
        10,
        10,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Water)
    )

    private val opening = FreeSpell(
        "Opening",
        true,
        5,
        30,
        "This spell allows the caster to open any closed door. If the door is locked, the " +
                "spell will try to force it by employing a Lock Picking ability of 80.",
        "+10 to Lock Picking",
        20,
        null,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Destruction, Element.Fire)
    )

    private val moveObjects = FreeSpell(
        "Move Objects",
        true,
        5,
        30,
        "This spell moves inorganic objects without the need of physical contact, by " +
                "granting them a Flight Value 10. Maximum weight that can be affected is 25 pounds.",
        "+10 kilograms",
        10,
        10,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Destruction, Element.Earth)
    )

    private val stopFall = FreeSpell(
        "Stop Fall",
        false,
        5,
        40,
        "This spell cancels the effects of falling from great heights. In game terms, it " +
                "completely eliminates the impact of falling from a 50-meter distance. It may " +
                "affect several individuals simultaneously, provided the total of their Presences " +
                "is not higher than 60.",
        "+10 extra meters of fall and +10 to the maximum Presence affected",
        20,
        10,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Earth)
    )

    private val cleanliness = FreeSpell(
        "Cleanliness",
        true,
        5,
        30,
        "This spell removes any minor physical impurity (such as dirt or bad smell) in the " +
                "target\'s body and clothes. It can also be used to clean places or objects. It " +
                "can affect up to a maximum Presence of 50.",
        "+10 to the maximum Presence affected",
        10,
        null,
        false,
        listOf(SpellType.Effect),
        listOf()
    )

    private val magicDetection = FreeSpell(
        "Magic Detection",
        true,
        5,
        40,
        "The caster automatically detects any source of magic in a 25-meter radius around " +
                "him. If the magic is hidden, this spell employs the equivalent of a Magic " +
                "Appraisal Ability of 140.",
        "+10 to Magic Appraisal and +25 meters to radius",
        10,
        20,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Dark)
    )

    private val createMusic = FreeSpell(
        "Create Music",
        true,
        5,
        40,
        "This spell creates music that is audible in a 10-meter radius. The piece is " +
                "performed with a Music Ability of 80. This spell can only play pieces the caster " +
                "is familiar with, even if only in a vague way.",
        "+25 meters to range and +5 to Music Ability",
        10,
        20,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Destruction)
    )

    private val undoWriting = FreeSpell(
        "Undo Writing",
        true,
        5,
        40,
        "The spell will erase a text up to 500 characters or letters. The material upon " +
                "which the text is imprinted in could be altered by this spell, but not in a " +
                "damaging way. For instance, the spell causes ink to fade away, or the space " +
                "created by engraving to be refilled. This spell can only affect objects with a " +
                "Presence less than 30.",
        "+5 to maximum Presence and +100 characters",
        10,
        null,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Creation)
    )

    private val staticMessage = FreeSpell(
        "Static Message",
        true,
        5,
        30,
        "This spell creates a written message in a particular place or on an object. The " +
                "spellcaster can make this message appear and disappear at will, even when not " +
                "physically present. However, if not present, he will not know if the message is " +
                "actually read by someone. The message has a 40 word limit.",
        "+10 words limit",
        20,
        20,
        true,
        listOf(SpellType.Effect),
        listOf(Element.Destruction)
    )

    private val jump = FreeSpell(
        "Jump",
        true,
        5,
        50,
        "The targeted individual can supernaturally enhance his Jump Ability. The spell adds " +
                "a +50 bonus to the base of a character\'s Jump Ability, and allows him to reach " +
                "Inhuman Difficulty Levels when making Jump Checks.",
        "+20 to Jump Ability",
        10,
        10,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Earth)
    )

    private val changeColor = FreeSpell(
        "Change Color",
        true,
        5,
        30,
        "This spell will change the color of objects, or even people, provided their " +
                "Presence does not exceed 40. The target can resist this spell by passing a MR " +
                "Check with a Difficulty of 100.",
        "+5 to MR Difficulty and +5 maximum Presence affected",
        10,
        20,
        true,
        listOf(SpellType.Spiritual),
        listOf(Element.Destruction)
    )

    val firstBook = listOf(
        tieSpell,
        createFire,
        opening,
        moveObjects,
        stopFall,
        cleanliness,
        magicDetection,
        createMusic,
        undoWriting,
        staticMessage,
        jump,
        changeColor
    )

    private val createSounds = FreeSpell(
        "Create Sounds",
        true,
        15,
        40,
        "This spell creates a sound in a specific place not more than 50 meters away from " +
                "the caster.",
        "+15 meters",
        10,
        10,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Destruction)
    )

    private val fog = FreeSpell(
        "Fog",
        true,
        15,
        60,
        "This spell creates fog in a 100-meter radius. Density may be determined by the " +
                "caster. The fog remains stationary unless affected by wind. Light breezes will " +
                "make the fog drift; stronger winds simply disperse the fog.",
        "+15 meters to radius",
        20,
        10,
        true,
        listOf(SpellType.Effect),
        listOf(Element.Fire)
    )

    private val breatheLiquids = FreeSpell(
        "Breathe Liquids",
        true,
        15,
        40,
        "This spell enables targets to breathe in a liquid environment just as easily as " +
                "they do in air. It may affect several individuals simultaneously provided the " +
                "total of their Presences is not higher than 80.",
        "+20 to the maximum Presence affected",
        10,
        10,
        true,
        listOf(SpellType.Effect),
        listOf(Element.Earth, Element.Fire)
    )

    private val enchantSpell = FreeSpell(
        "Enchant",
        true,
        15,
        50,
        "This spell turns an object or place with a Presence of 50 or below into a " +
                "supernatural element. The newly enchanted substance affects intangible beings and " +
                "spells. For instance, if cast upon a weapon, the weapon will be able to inflict " +
                "damage upon immaterial beings (employing the object\'s Presence). Likewise, " +
                "ethereal beings could not pass through an enchanted wall.",
        "+10 to the maximum Presence affected",
        10,
        20,
        true,
        listOf(SpellType.Effect),
        listOf()
    )

    private val recreateImage = FreeSpell(
        "Recreate Image",
        true,
        15,
        40,
        "This spell recreates an image previously seen by the spellcaster, with a maximum " +
                "surface of one square meter. This apparition is a transparent hologram with no " +
                "substance that remains motionless and stationary.",
        "+1 square meter",
        10,
        10,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Destruction)
    )

    private val repair = FreeSpell(
        "Repair",
        true,
        15,
        60,
        "This spell repairs an inorganic object entirely, but it does not recreate missing " +
                "pieces. Performing the repair requires that the fragments of the object are " +
                "preserved, or that the necessary raw materials are available. It can be cast " +
                "upon anything, from weapons to buildings, with a Presence less than 30.",
        "+5 to the maximum Presence affected",
        10,
        null,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Destruction, Element.Illusion)
    )

    private val climb = FreeSpell(
        "Climb",
        true,
        15,
        50,
        "The targeted individual has his Climb Ability supernaturally enhanced. The spell " +
                "adds a +50 bonus to the base of a character\'s Climb Ability and allows him to " +
                "reach Inhuman Difficulty Levels when making Climb Checks.",
        "+20 to the Climb Ability",
        10,
        10,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Air)
    )

    private val passWithoutTrace = FreeSpell(
        "Pass Without Leaving Trace",
        true,
        15,
        60,
        "This spell eliminates the physical traces people leave behind. It takes an " +
                "Inhuman-level Track Check to follow the traces of anyone using this spell. The " +
                "caster may affect as many individuals as he wishes, provided the total of their " +
                "Presences is not higher than 120.",
        "+20 to the maximum Presence affected",
        30,
        10,
        true,
        listOf(SpellType.Effect),
        listOf(Element.Light)
    )

    private val slipperyArea = FreeSpell(
        "Slippery Area",
        true,
        15,
        50,
        "This spell enchants a static 5-meter radius area, rendering it as slippery as wet " +
                "ice. Crossing this field without slipping requires an Acrobatics or Athleticism " +
                "Check of Difficult if walking, or of Absurd if running.",
        "+5 meters to area",
        20,
        20,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Fire)
    )

    private val attractMinorVermin = FreeSpell(
        "Attract Minor Vermin",
        true,
        15,
        30,
        "This spell summons a total mass of 30 kilograms of small vermin to a designated " +
                "spot. This spell does not create new creatures, it merely attracts existing " +
                "vermin in the area. This spell does not grant any control over these creatures.",
        "+10 kilograms of mass",
        10,
        20,
        false,
        listOf(SpellType.Effect),
        listOf()
    )

    private val infiniteBag = FreeSpell(
        "Infinite Bag",
        true,
        15,
        40,
        "This spell permits characters to fill a bag, sack, or chest with up to ten times " +
                "its normal storage capacity - without increasing its weight. The caster can " +
                "always draw the exact object he needs from the container, whereas the rest of " +
                "the characters will stumble upon random objects. Objects are still limited to a " +
                "size that would normally fit inside the container, so this spell would not, for " +
                "example, permit a wagon to be stored inside a typical backpack. The load " +
                "fully materializes upon termination of the spell; in this way, it is possible " +
                "that the container may burst.",
        "+5 additional times its storage capacity",
        10,
        10,
        true,
        listOf(SpellType.Effect),
        listOf()
    )

    private val inhumanity = FreeSpell(
        "Inhumanity",
        true,
        15,
        50,
        "Targets of this spell are able to achieve Inhuman-difficulty results in any field " +
                "or subject matter. Bear in mind that this does not mean actions performed by " +
                "these subjects are automatically Inhuman. Rather, it means that characters can " +
                "achieve them if their Characteristics or Ability Checks allow. This spell can " +
                "affect several individuals simultaneously, provided their total Presence scores " +
                "are not higher than 60.",
        "+10 to the maximum Presence affected",
        10,
        10,
        true,
        listOf(SpellType.Effect),
        listOf()
    )

    val secondBook = listOf(
        createSounds,
        fog,
        breatheLiquids,
        enchantSpell,
        recreateImage,
        repair,
        climb,
        passWithoutTrace,
        slipperyArea,
        attractMinorVermin,
        infiniteBag,
        inhumanity
    )

    private val closeWithMagic = FreeSpell(
        "Close with Magic",
        true,
        25,
        100,
        "This spell automatically closes any door or lock, increasing the necessary " +
                "Difficulty to open it by one degree - up to an Inhuman maximum. In other words, " +
                "a door requiring a Medium-Difficulty Lock Picking Check to open would now require " +
                "a Difficult Check. Although the lock itself is enchanted, the caster doesn\'t " +
                "need to pay for maintenance; the spell will be active until the door opens. " +
                "This spell will only affect one lock at a time.",
        "+1 to Difficulty Level for opening",
        10,
        null,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Destruction)
    )

    private val causeFear = FreeSpell(
        "Cause Fear",
        true,
        25,
        100,
        "All creatures within a 5-meter radius of the caster fall prey to a terrible " +
                "magical fear - unless they pass a MR Check with a Difficulty of 100. Those who " +
                "fail the check are subjected to the Fear State.",
        "+5 meters to radius and +5 to MR Difficulty",
        10,
        10,
        false,
        listOf(SpellType.Spiritual),
        listOf(Element.Light)
    )

    private val understandLanguage = FreeSpell(
        "Understand Languages",
        true,
        25,
        100,
        "This spell will allow the targeted individual to gain temporary proficiency in a " +
                "language previously not spoken by him. When dealing with an exceptionally " +
                "complex idiom, the GM is free to demand the investment of a larger amount of " +
                "Zeon to master it, as he sees fit. The spell may affect as many individuals as " +
                "designated, provided their total Presence scores do not exceed 80.",
        "+10 to maximum Presence affected",
        20,
        5,
        true,
        listOf(SpellType.Effect),
        listOf()
    )

    private val net = FreeSpell(
        "Net",
        true,
        25,
        60,
        "This spell will create high density magical fibers that form a very sticky net. " +
                "The caster can use this net for whatever he may desire, such as blocking a " +
                "passage or entrapping creatures. The net\'s maximum radius is 3 meters. It Traps " +
                "with a Strength of 10, and it can endure 500 points of damage before breaking " +
                "using an AT 4. Since its substance is of a mystical nature, only heat, cold, " +
                "electricity, or supernatural attacks will harm it.",
        "+1 meter to radius and +50 Resistance Points",
        10,
        20,
        false,
        listOf(SpellType.Effect),
        listOf()
    )

    private val serenity = FreeSpell(
        "Serenity",
        true,
        25,
        50,
        "This spell produces a feeling of tranquility and peace in the target, inducing " +
                "calm even when experiencing rage or terror. It cancels all fear, terror, or " +
                "rage States experienced by the target - except if they are of a supernatural " +
                "nature. A character can resist this spell by passing a MR Check with a " +
                "Difficulty of 120.",
        "+5 to MR Difficulty",
        10,
        10,
        false,
        listOf(SpellType.Spiritual),
        listOf(Element.Fire, Element.Dark)
    )

    private val magicShield = FreeSpell(
        "Magic Shield",
        false,
        25,
        60,
        "This spell creates a magical shield that will protect the caster from all attacks - " +
                "including those of a supernatural nature. It can withstand up to 300 points of " +
                "damage before breaking.",
        "+50 Resistance Points",
        20,
        10,
        false,
        listOf(SpellType.Defense),
        listOf()
    )

    private val magicalProtection = FreeSpell(
        "Magical Protection",
        true,
        25,
        60,
        "This spell grants armor protection with an AT 2 against all attacks except for " +
                "Energy-based attacks. It can be used in conjunction with any other form of " +
                "protection as an additional layer. However, it does not cause special penalties " +
                "to be applied to the Initiative.",
        "+1 AT",
        10,
        10,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Destruction)
    )

    private val clouds = FreeSpell(
        "Clouds",
        true,
        25,
        80,
        "This spell forms a thick layer of clouds with a maximum radius of 100 meters. The " +
                "caster is in complete control of them and may move them around or shape them in " +
                "any way he pleases.",
        "+20 meters to radius",
        20,
        10,
        true,
        listOf(SpellType.Effect),
        listOf(Element.Fire, Element.Earth)
    )

    private val sendMessage = FreeSpell(
        "Send Message",
        true,
        25,
        80,
        "This spell sends an oral message of 500 words or less to a familiar person not more " +
                "then 100 kilometers distant. The language of the message must be one that is " +
                "familiar to the caster.",
        "+100 kilometers to range and +100 words",
        10,
        null,
        false,
        listOf(SpellType.Effect),
        listOf()
    )

    private val speed = FreeSpell(
        "Speed",
        true,
        25,
        80,
        "This spell raises a subject\'s Movement Value by one degree and also applies a +20 " +
                "bonus to his Initiative. Increasing Movement Value beyond 12 requires two Added " +
                "Effects for each additional degree of increase.",
        "+10 to Initiative and +1 to Movement Value",
        10,
        10,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Earth)
    )

    private val levitation = FreeSpell(
        "Levitation",
        true,
        25,
        50,
        "Targets of this spell gain the ability to levitate their bodies vertically in the " +
                "air, with a Flight Value of 4. This spell may affect as many targets as " +
                "necessary - provided the sum of their Presence scores do not exceed 80.",
        "+10 to the maximum Presence affected",
        10,
        10,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Earth)
    )

    private val contraceptiveProtection = FreeSpell(
        "Contraceptive Protection",
        true,
        25,
        60,
        "This spell prevents sexually transmitted diseases and pregnancy. It may affect " +
                "several individuals simultaneously - provided the total of their Presence scores " +
                "do not exceed 80.",
        "+10 to the maximum Presence affected",
        10,
        10,
        true,
        listOf(SpellType.Effect),
        listOf()
    )

    val thirdBook = listOf(
        closeWithMagic,
        causeFear,
        understandLanguage,
        net,
        serenity,
        magicShield,
        magicalProtection,
        clouds,
        sendMessage,
        speed,
        levitation,
        contraceptiveProtection
    )

    private val painResistance = FreeSpell(
        "Resistance to Pain",
        true,
        35,
        60,
        "This spell increases a living being\'s endurance, applying a +60 bonus to its " +
                "Withstand Pain Checks.",
        "+20 to Withstand Pain",
        10,
        10,
        true,
        listOf(SpellType.Effect),
        listOf(Element.Essence)
    )

    private val trueClose = FreeSpell(
        "True Close",
        true,
        35,
        80,
        "The caster is automatically able to obstruct any type of door, shutter, or window " +
                "with a Presence of 20 or below. If cast upon an open door, this spell causes it " +
                "to close by itself, or if it has a latch, the spell will fasten it closed. For " +
                "as long as the spell is maintained, there will be no physical way to open the " +
                "door short of destruction or employing other magical means.",
        "+5 to the maximum Presence affected",
        20,
        20,
        true,
        listOf(SpellType.Effect),
        listOf()
    )

    private val purification = FreeSpell(
        "Purification",
        true,
        35,
        80,
        "This spell purifies a body by eliminating any harmful substances from its system, " +
                "such as poisons. It automatically eradicates any substance of Level 30 or below. " +
                "Purification only affects the harmful elements of an organism, and those alien " +
                "to it. In this way, a Purification spell cast upon a poisonous snake would not " +
                "remove the snake\'s own poison, but the same spell cast upon a person bitten by " +
                "the snake would be purified. The spell may affect as many individuals as " +
                "designated, provided the total of their Presence scores does not exceed 80.",
        "+10 to Poison Level affected and +10 to the maximum Presence affected",
        10,
        null,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Creation)
    )

    private val extendPresence = FreeSpell(
        "Extend Presence",
        true,
        35,
        100,
        "The caster is able to extend his Presence up to 5 meters away from his body, " +
                "allowing him to perform actions at a distance. As long as the Presence is " +
                "detached from the body, the caster is unable to move, but he can return to his " +
                "body without the need of ending the spell. The Presence is able to touch " +
                "physical objects and even attack, but it is invisible to the human eye. The only " +
                "way to notice its presence is via Ki detection, the ability to see spirits, or " +
                "some other means of seeing ethereal bodies. However, if the Presence is holding " +
                "an object, that object will be perfectly visible. The Presence is intangible " +
                "and can only be harmed by Energy-based attacks. Any Life Points lost will also " +
                "affect the caster\'s body.",
        "+5 meters to range",
        20,
        5,
        true,
        listOf(SpellType.Effect),
        listOf()
    )

    private val alterSize = FreeSpell(
        "Alter Size",
        true,
        35,
        80,
        "This spell increases or decreases the target\'s Size up to 2 points. No matter how " +
                "many added effects are employed, the lowest value for size is 1. In order to " +
                "resist the spell, characters must pass a MR with a Difficulty of 120.",
        "Increase of decrease 1 additional size point and +5 to MR Difficulty",
        10,
        10,
        false,
        listOf(SpellType.Spiritual),
        listOf(Element.Destruction)
    )

    private val invokeAggressiveness = FreeSpell(
        "Invoke Aggressiveness",
        true,
        35,
        80,
        "This spell increases aggressiveness in all living creatures and persons within 20 " +
                "meters of the caster. Each affected target can resist the spell with a MR or PsR " +
                "Check with a Difficulty of 80. The affected individuals will show violent " +
                "behavior around any person or object that may serve as as target of their rage.",
        "+10 meters to radius and +5 to MR or PsR Difficulty",
        10,
        10,
        false,
        listOf(SpellType.Automatic),
        listOf(Element.Light)
    )

    private val outlookChange = FreeSpell(
        "Change of Outlook",
        true,
        35,
        80,
        "This spell modifies the face and body of its target. It can alter skin color or " +
                "physiognomy, but it cannot change height or weight beyond the limits set by the " +
                "character\'s Size. In order to resist the makeover, a character must pass a MR " +
                "Check with a Difficulty of 100. If he fails the check, he receives a new one each day.",
        "+5 to MR Difficulty",
        10,
        10,
        true,
        listOf(SpellType.Effect, SpellType.Spiritual),
        listOf(Element.Destruction)
    )

    private val healDiseases = FreeSpell(
        "Heal Diseases",
        true,
        35,
        80,
        "This spell automatically eliminates any illness of Level 30 or below. It may be " +
                "cast upon several individuals, provided that the sum of their Presences does " +
                "not exceed 80.",
        "+10 to Disease Level and +10 to the maximum Presence affected",
        10,
        null,
        false,
        listOf(SpellType.Effect),
        listOf()
    )

    private val magicBeam = FreeSpell(
        "Magic Beam",
        true,
        35,
        60,
        "This spell projects a magical Energy discharge that uses the Energy AT and " +
                "possesses a Base Damage of 40.",
        "+5 to Base Damage",
        10,
        null,
        false,
        listOf(SpellType.Attack),
        listOf(Element.Creation)
    )

    private val eliminateDreams = FreeSpell(
        "Eliminate Dreams",
        true,
        35,
        80,
        "The spell suppresses an individual\'s ability to dream. This spell affects anyone " +
                "with a Presence of 60 or below. Those wishing to resist must pass a MR Check " +
                "with a Difficulty of 120. The check may be repeated every time the character " +
                "falls asleep.",
        "+5 to MR Difficulty and +5 to maximum Presence affected",
        20,
        20,
        true,
        listOf(SpellType.Spiritual),
        listOf(Element.Light, Element.Dark)
    )

    private val senseFeelings = FreeSpell(
        "Sense Feelings",
        true,
        35,
        60,
        "This spell detects a specific feeling in an individual less than 50 meters away " +
                "from the caster. It is up to him to decide what feeling he would like to perceive. " +
                "Affected subjects can avoid the effects of the spell by passing a MR or PsR " +
                "Check with a Difficulty of 120.",
        "+10 meters to radius and +5 to MR or PsR Difficulty",
        10,
        20,
        false,
        listOf(SpellType.Detection),
        listOf()
    )

    private val eliminateSpells = FreeSpell(
        "Eliminate Spells",
        true,
        35,
        150,
        "This spell destroys another active spell with a Zeonic value below 60.",
        "+5 to the Zeonic value affected",
        20,
        null,
        false,
        listOf(SpellType.Automatic),
        listOf(Element.Creation)
    )

    val fourthBook = listOf(
        painResistance,
        trueClose,
        purification,
        extendPresence,
        alterSize,
        invokeAggressiveness,
        outlookChange,
        healDiseases,
        magicBeam,
        eliminateDreams,
        senseFeelings,
        eliminateSpells
    )

    private val friendship = FreeSpell(
        "Friendship",
        true,
        45,
        80,
        "This spell creates a bond of friendship between two characters designated by the " +
                "caster. Keep in mind that the affected characters do not become stupid, meaning " +
                "they will not follow their new friend\'s advice blindly. At any rate, they will " +
                "act according to their ethics and personality. Characters wishing to resist this " +
                "spell need to pass a MR or PsR Check with a Difficulty of 120. The check will " +
                "only be repeated once a day, or in a case where something sheds doubt on the " +
                "true camaraderie between the characters.",
        "+5 to MR or PsR Difficulty",
        10,
        10,
        true,
        listOf(SpellType.Spiritual),
        listOf(Element.Dark)
    )

    private val quickTransport = FreeSpell(
        "Quick Transport",
        true,
        45,
        60,
        "This spell transports the target up to a distance of 25 meters. The spell allows " +
                "characters to pass through physical bodies, provided they are not based on " +
                "Energy. Maximum quantity of material mass to be transported cannot have a " +
                "Presence higher than 50.",
        "+25 meters to range and +5 to maximum Presence affected",
        10,
        null,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Earth)
    )

    private val sendDreams = FreeSpell(
        "Send Dreams",
        true,
        45,
        120,
        "This spell allows the caster to send images and words to a sleeping person\'s " +
                "subconscious. The spell does not constitute a means of communication, since the " +
                "caster will have no feedback on the actions or words of the target. The message " +
                "may be delivered to any subject designated by the caster, regardless of distance, " +
                "provided the target\'s Presence does not exceed 50. The target must beb asleep to " +
                "receive the message.",
        "+5 to maximum Presence affected",
        20,
        null,
        false,
        listOf(SpellType.Automatic),
        listOf()
    )

    private val readMinds = FreeSpell(
        "Read Minds",
        true,
        45,
        100,
        "This spell enables the caster to delve into a subject\'s memories or thoughts. It " +
                "is left to the GM\'s best judgement to decide the number of Combat Turns needed " +
                "to find the desired information, depending on how deep it is buried in the " +
                "character\'s memory. The target may resist the spell by passing a PsR or MR Check " +
                "with a Difficulty of 80. The caster will apply a +30 bonus to all Opposed " +
                "Checks against him for as long as he can read his opponent\'s intentions.",
        "5 to MR or PsR Difficulty",
        20,
        10,
        false,
        listOf(SpellType.Spiritual),
        listOf(Element.Dark)
    )

    private val cancelMagic = FreeSpell(
        "Cancel Magic",
        true,
        45,
        150,
        "This spell cancels the effects of all spells in a 10-meter radius that have a " +
                "Zeonic value below 60. Keep in mind that the spells are not destroyed; the " +
                "effects are merely neutralized within the area for the duration of the spell.",
        "+5 to Zeonic value affected and +5 meters to radius",
        20,
        20,
        false,
        listOf(SpellType.Automatic),
        listOf(Element.Creation)
    )

    private val curse = FreeSpell(
        "Curse",
        true,
        45,
        200,
        "This spell creates a number of negative conditions on the affected individuals " +
                "with the goal of bringing misfortune when the affected target attempts a " +
                "specific action. Every time the accursed character attempts the specified " +
                "action, it automatically fails. If the caster chooses to curse a Primary or " +
                "Secondary Ability, the victim suffers a -60 penalty to that ability every time " +
                "he uses it.\nIt is in the nature of this spell to influence not only the target, " +
                "but also those around him, if they enter the field of the curse. In this case, " +
                "each person is entitled to their own MR Check. For instance, a young girl " +
                "romantically interested in a boy who is affected by a curse that prevents anyone " +
                "from falling in love with him would feel discouraged in her endeavor, unless she " +
                "passes the MR Check. The basic MR Difficulty is 120. New checks can be made only " +
                "after their effects are re-activated.",
        "+5 to MR Difficulty",
        20,
        10,
        true,
        listOf(SpellType.Spiritual),
        listOf()
    )

    private val absorbInfo = FreeSpell(
        "Absorb Information",
        true,
        45,
        50,
        "The caster can use this spell to absorb knowledge in any written or graphic media " +
                "that is within sight or at hand. In this way, he can read a book in a few " +
                "seconds and remember the details as if he had studied it carefully. He may read " +
                "up to 500 words (or an equivalent amount of information) for every turn the " +
                "spell is maintained. Once the spell is finished, the caster\'s memory will be " +
                "responsible for storing the information correctly.",
        "+500 words per turn",
        20,
        5,
        false,
        listOf(SpellType.Effect),
        listOf()
    )

    private val showInvisible = FreeSpell(
        "Show the Invisible",
        true,
        45,
        60,
        "This spell exposes any force or invisible presence to public view. The spell reveals" +
                " not only supernatural effects, but also beings of a spiritual nature, or those" +
                " who are completely invisible. The spell covers a radius of 50 meters. Affected " +
                "creatures avoid manifesting themselves by passing a MR Check with a Difficulty " +
                "of 120. The only condition for being affected by the spell is being inside the area.",
        "+10 meters to radius and +5 to MR Difficulty",
        10,
        20,
        false,
        listOf(SpellType.Automatic),
        listOf(Element.Dark)
    )

    private val undo = FreeSpell(
        "Undo",
        true,
        45,
        100,
        "This spell destroys up to 50 kilograms of any inorganic material, provided it does " +
                "not pass a Resistance Check with a Difficulty of 80. It cannot affect objects " +
                "with a Presence higher than 50.",
        "+25 kilograms affected and +5 to the Resistance Difficulty",
        10,
        null,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Creation)
    )

    private val causeSickness = FreeSpell(
        "Cause Sickness",
        true,
        45,
        60,
        "This spell causes a Level 30 sickness in any individual who fails the required DR " +
                "Check.",
        "+5 to the Disease Level",
        10,
        null,
        false,
        listOf(SpellType.Spiritual),
        listOf(Element.Illusion, Element.Water)
    )

    private val slow = FreeSpell(
        "Slow",
        true,
        45,
        60,
        "This spell decreases the Movement Value of a target by -2 if it fails a MR Check " +
                "with a Difficulty of 120.",
        "-1 to Movement Value and +5 to MR Difficulty",
        10,
        10,
        false,
        listOf(SpellType.Spiritual),
        listOf(Element.Air)
    )

    private val alterEnergy = FreeSpell(
        "Alter Energy",
        true,
        45,
        100,
        "The caster can transform up to 10 Intensities of any kind of elemental energy, " +
                "such as fire, into another kind, such as electricity. If these Intensities are " +
                "magical or have a Presence of their own, they can resist the spell by passing a " +
                "MR Check with a Target Difficulty of 120.",
        "+2 Intensities and +5 to MR Difficulty",
        10,
        10,
        true,
        listOf(SpellType.Spiritual),
        listOf(Element.Destruction)
    )

    val fifthBook = listOf(
        friendship,
        quickTransport,
        sendDreams,
        readMinds,
        cancelMagic,
        curse,
        absorbInfo,
        showInvisible,
        undo,
        causeSickness,
        slow,
        alterEnergy
    )

    private val blindness = FreeSpell(
        "Blindness",
        true,
        55,
        80,
        "This spell will cause anyone within a 5-meter radius to go blind if they fail a MR " +
                "Check with a Difficulty of 100.",
        "+10 meters to radius and +5 to MR Difficulty",
        10,
        20,
        false,
        listOf(SpellType.Spiritual),
        listOf(Element.Creation, Element.Light)
    )

    private val deafness = FreeSpell(
        "Deafness",
        true,
        55,
        80,
        "The spell will cause anyone within a 5-meter radius to go deaf if they fail a MR " +
                "Check with a Difficulty of 120.",
        "+10 meters to radius and +5 to MR Difficulty",
        10,
        20,
        false,
        listOf(SpellType.Spiritual),
        listOf(Element.Creation)
    )

    private val muteness = FreeSpell(
        "Muteness",
        true,
        55,
        80,
        "The spell causes any creature within a 5-meter radius to become mute if they fail a " +
                "MR Check with a Difficulty of 120.",
        "+10 meters to radius and +5 to MR Difficulty",
        10,
        20,
        false,
        listOf(SpellType.Spiritual),
        listOf(Element.Creation)
    )

    private val healWounds = FreeSpell(
        "Heal Wounds",
        true,
        55,
        100,
        "This spell heals any kind of wound and restores 40 Life Points to the target. " +
                "This type of healing will not regenerate missing organs or similar damage, but it " +
                "will stop the effects of bleeding.",
        "+5 Life Points",
        10,
        null,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Destruction)
    )

    private val visualizeCartography = FreeSpell(
        "Visualize Cartography",
        true,
        55,
        60,
        "This spell enables the caster to visualize the surrounding landscape in a " +
                "25-kilometer radius. The spell is effective for locating cities, rivers, and " +
                "mountains; however, specific individuals or constructions cannot be placed.",
        "+25 kilometer to radius",
        10,
        null,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Dark)
    )

    private val sleep = FreeSpell(
        "Sleep",
        true,
        55,
        80,
        "This spell induces a deep sleep on all subjects within a 10-meter radius who do " +
                "not pass a MR Check with a Difficulty of 100. Affected characters fall fast " +
                "asleep in little more than a minute. If a character fails the check by more than " +
                "20 points, the effects of the spell will be instantaneous. The affected parties " +
                "remain asleep for the duration of the spell. Potential interruptions of sleep " +
                "during the spell will provide opportunities to reroll the check.",
        "+10 meters to radius and +5 to MR Difficulty",
        10,
        10,
        true,
        listOf(SpellType.Spiritual),
        listOf()
    )

    private val walkOnWalls = FreeSpell(
        "Walk on Walls",
        true,
        55,
        60,
        "Characters gain the ability to walk on walls or ceilings as if they were walking on " +
                "the ground. The maximum Presence affected by this spell is 80.",
        "+10 to the maximum Presence affected",
        10,
        20,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Water)
    )

    private val magicSaddle = FreeSpell(
        "Magic Saddle",
        true,
        55,
        100,
        "This spell creates a supernatural being whose sole purpose is that of transport. " +
                "The creature has no attack ability, and it defends according to the rules for " +
                "Damage Resistance creatures. This creature possesses a number of Life Points " +
                "equal to twice the Zeonic value invested in the spell and its AT is calculated " +
                "according to its Size. Its maximum Size is 20 and its maximum Movement Value is " +
                "10. Magic Saddle uses Ki Weight Elimination rules for moving.",
        "+1 to maximum Size and +1 to Movement Value",
        10,
        10,
        true,
        listOf(SpellType.Effect),
        listOf(Element.Illusion)
    )

    private val bodyMerge = FreeSpell(
        "Merge with Body",
        true,
        55,
        60,
        "This spell merges one or several objects, such as weapons, suits of armor, " +
                "lockpicks, with the designated character. The object remains hidden under some " +
                "visible form, such as a scar or a tattoo, until the time the owner decides to " +
                "disclose it. The maximum Presence of objects merged cannot exceed 100. As long " +
                "as they remain hidden, no weight or size penalties are applied to the bearer, " +
                "but neither will the bearer receive any bonuses or advantages from hidden " +
                "objects. No object exceeding ten times the bearer\'s size can be merged. It takes " +
                "a full turn for a merged object to materialize and detach from a character.",
        "+10 to the maximum Presence affected",
        10,
        5,
        true,
        listOf(SpellType.Effect),
        listOf(Element.Destruction)
    )

    private val eliminateFatigue = FreeSpell(
        "Eliminate Fatigue",
        true,
        55,
        80,
        "This spell induces recovery from physical exhaustion, allowing characters to " +
                "regain 1 lost Fatigue Point.",
        "+1 Fatigue Point recovered",
        10,
        null,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Dark)
    )

    private val acidCloud = FreeSpell(
        "Acid Cloud",
        true,
        55,
        100,
        "This spell forms a corrosive cloud that melts any substance by mere contact. All " +
                "bodies in contact with the cloud must pass a PhR Check with a Difficulty of 120 " +
                "or lose a number of Life Points equal to the Failure Level of the check. Those " +
                "objects that fail the resistance by 50 points lose 1 level of quality. For every " +
                "turn that bodies are exposed to the cloud and fail the check, they receive a " +
                "-10 cumulative penalty to their subsequent checks.\nThe cloud has a 5-meter " +
                "radius, and it moves at the caster\'s will at a Movement Value of 6. The caster " +
                "cannot select targets within the area of the cloud and may even be affected himself.",
        "+1 meter to radius, +1 to cloud Movement Value, and +5 to PhR Difficulty",
        10,
        10,
        false,
        listOf(SpellType.Automatic),
        listOf(Element.Earth)
    )

    private val leaveUnprotected = FreeSpell(
        "Leave Unprotected",
        true,
        55,
        80,
        "This spell lowers the victim\'s AT by 2 levels if he, or his armor, fails a MR " +
                "Check with a Difficulty of 140.",
        "-1 to AT and +10 to MR Difficulty",
        10,
        10,
        false,
        listOf(SpellType.Spiritual),
        listOf(Element.Creation)
    )

    val sixthBook = listOf(
        blindness,
        deafness,
        muteness,
        healWounds,
        visualizeCartography,
        sleep,
        walkOnWalls,
        magicSaddle,
        bodyMerge,
        eliminateFatigue,
        acidCloud,
        leaveUnprotected
    )

    private val increasePsyChar = FreeSpell(
        "Increase Psychic Characteristics",
        true,
        65,
        100,
        "This spell adds 1 point to one of the Psychic Characteristics: Intelligence, Power, " +
                "Willpower, or Perception. An Intelligence boost does not allow the caster to " +
                "increase the maximum potential of his spells. A single casting of this spell " +
                "can only affect one of the above characteristics at a time. Increasing a " +
                "characteristic beyond 12 requires two Added Effects per additional point.",
        "+1 to enhanced Characteristic",
        10,
        10,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Destruction)
    )

    private val magicalWeapon = FreeSpell(
        "Magical Weapon",
        true,
        65,
        140,
        "The caster uses magic to devise a supernatural weapon. His work is treated as a " +
                "Quality +10 Energy-damaging object. The Presence of the weapon cannot exceed 25. " +
                "The object\'s quality has no effect on its Presence at the time of creation.",
        "+5 maximum Presence of weapon",
        20,
        10,
        true,
        listOf(SpellType.Effect),
        listOf(Element.Destruction)
    )

    private val increasePhysChar = FreeSpell(
        "Increase Physical Characteristic",
        true,
        65,
        80,
        "This spell adds one point to one of the Physical Characteristics: Dexterity, " +
                "Agility, Strength, or Constitution. A single casting of this spell can only " +
                "affect one of the above characteristics at a time. Increasing a characteristic " +
                "beyond 12 requires two Added Effects per additional point.",
        "+1 to enhanced Characteristic",
        10,
        10,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Destruction)
    )

    private val minorAlteration = FreeSpell(
        "Minor Alteration",
        true,
        65,
        80,
        "This spell enables the caster to shape inorganic matter in such a way as to change " +
                "one object into another of similar characteristics and the same Presence. For " +
                "instance, a harpoon may be turned into a spear, since they are shaped similarly " +
                "and they have the same Presence, but a harpoon could not be turned into a " +
                "two-handed sword, since Presence and form differ greatly from one another. The " +
                "maximum Presence affected is 30.",
        "+5 to the maximum Presence affected",
        20,
        20,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Destruction)
    )

    private val bodyToMagic = FreeSpell(
        "Body to Magic",
        true,
        65,
        100,
        "The designated body transforms into magical energy, thus becoming intangible to all " +
                "matters and non-Energy-based attacks. The maximum Presence affected by this spell " +
                "is 80.",
        "+10 to the maximum Presence affected",
        10,
        10,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Earth)
    )

    private val paralyze = FreeSpell(
        "Paralyze",
        true,
        65,
        140,
        "Any subject in a 10-meter radius is exposed to the effects of Total Paralysis " +
                "unless they pass a MR with a Difficulty of 80.",
        "+5 meters to radius and +5 to MR Difficulty",
        20,
        10,
        false,
        listOf(SpellType.Spiritual),
        listOf(Element.Air)
    )

    private val createEmotion = FreeSpell(
        "Create Emotion",
        true,
        65,
        160,
        "This spell creates a specifically designated feeling or emotion within a particular " +
                "character. The Difficulty for the MR or PsR Check to avoid this spell is 120. A " +
                "character receives a new check each day and whenever he becomes suspicious of " +
                "being supernaturally influenced.",
        "+5 to MR or PsR Difficulty",
        20,
        10,
        true,
        listOf(SpellType.Spiritual),
        listOf(Element.Illusion)
    )

    private val forgetfulness = FreeSpell(
        "Forgetfulness",
        true,
        65,
        160,
        "This spell affects the target\'s memory, causing him to forget whatever the caster " +
                "desires if he fails a MR or PsR Check with a Difficulty of 120. If the memories " +
                "being targeted are deeply rooted in the character, a +40 may apply to his MR " +
                "Check. The spell has no maintenance, but the target is entitled to make another " +
                "check when faced with potential reminders. The spell does not affect a " +
                "character\'s abilities, only conscious memory.",
        "+5 to MR or PsR Difficulty",
        20,
        null,
        false,
        listOf(SpellType.Spiritual),
        listOf(Element.Essence)
    )

    private val weakness = FreeSpell(
        "Weakness",
        true,
        65,
        80,
        "The target of this spell is temporarily subjected to Weakness (as explained in " +
                "Chapter 14) unless he passes a MR Check with a Difficulty of 120.",
        "+5 to MR Difficulty",
        10,
        10,
        false,
        listOf(SpellType.Spiritual),
        listOf(Element.Creation)
    )

    private val resistSpell = FreeSpell(
        "Resist",
        false,
        65,
        80,
        "This spell enhances one type of Resistance, bestowing a +20 bonus for the duration " +
                "of the spell",
        "+10 to one Resistance",
        10,
        5,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Destruction)
    )

    private val plague = FreeSpell(
        "Plague",
        true,
        65,
        140,
        "This spell must be cast upon a subject or place infected with a disease. By doing " +
                "so, the infection is automatically spread to all individuals in a radius of one " +
                "kilometer. Affected beings are entitled to a DR Check to avoid the contagion. " +
                "The Plague can also be avoided by passing a MR Check with a Difficulty of 100. " +
                "The selected Disease Level cannot be higher than 20.",
        "+5 maximum Disease Level, +1 kilometer to radius, and +5 to MR Difficulty",
        20,
        null,
        false,
        listOf(SpellType.Automatic),
        listOf(Element.Illusion)
    )

    private val rejection = FreeSpell(
        "Rejection",
        true,
        65,
        80,
        "This spell imbues a body with magical energy that provokes a Strength 8 impact on " +
                "anyone coming into contact with it. When such an impact occurs, the two affected " +
                "beings make an Opposed Agility or Strength Check. The difference between that " +
                "check, along with possible additional effects of the surrounding environment, " +
                "determine the severity of damage, at the GM\'s discretion. Increasing the spell\'s " +
                "Strength beyond 14 requires two Added Effects per point of increase. The " +
                "designated body cannot have a Presence higher than 30.",
        "+5 to the maximum Presence affected and +1 to Strength",
        10,
        20,
        true,
        listOf(SpellType.Effect),
        listOf(Element.Essence, Element.Water)
    )

    val seventhBook = listOf(
        increasePsyChar,
        magicalWeapon,
        increasePhysChar,
        minorAlteration,
        bodyToMagic,
        paralyze,
        createEmotion,
        forgetfulness,
        weakness,
        resistSpell,
        plague,
        rejection
    )

    private val invisibility = FreeSpell(
        "Invisibility",
        true,
        75,
        140,
        "This spell renders one or several bodies designated by the caster invisible. It " +
                "affects as many people as desired, provided the sum of their Presences does not " +
                "exceed 80. Only by passing a Notice Check against an Inhuman Difficulty or a " +
                "Search Check against Almost Impossible may an individual perceive an invisible body.",
        "+5 to the maximum Presence affected",
        20,
        10,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Essence)
    )

    private val levitationSphere = FreeSpell(
        "Levitation Sphere",
        true,
        75,
        150,
        "This spell causes objects and creatures in a 25-meter radius to levitate. The " +
                "caster can decide what will levitate and what will not, regardless of weight or " +
                "size. Objects or people suspended in the air are moved by the caster with a " +
                "Flight Value 6. Those wishing to resist the effects must pass a MR or PsR with a " +
                "Difficulty of 80. Only animated or live beings are entitled to a Resistance " +
                "Check, as are constructions endowed with supernatural Characteristics.",
        "+25 meters to radius and +5 to MR or PsR Difficulty",
        20,
        10,
        true,
        listOf(SpellType.Spiritual),
        listOf(Element.Earth, Element.Water)
    )

    private val uselessness = FreeSpell(
        "Uselessness",
        true,
        75,
        120,
        "The target becomes clumsy and unable to perform physical maneuvers. He must roll a " +
                "MR Check with a Difficulty of 120 or suffer penalties to his actions equivalent " +
                "to the number by which he failed the check. The spell cannot affect magical, " +
                "psychic, or perceptive abilities.",
        "+5 to MR Difficulty",
        20,
        10,
        false,
        listOf(SpellType.Spiritual),
        listOf(Element.Water)
    )

    private val dominion = FreeSpell(
        "Dominion",
        true,
        75,
        160,
        "This spell bends the will of anyone who does not pass a MR or PsR with a " +
                "Difficulty of 100. The affected party is entitled to a new check only when " +
                "ordered to perform an action completely against its nature. A +20 may be " +
                "applied to his roll if the order is exceptionally adverse.",
        "+5 to MR or PsR Difficulty",
        20,
        10,
        false,
        listOf(SpellType.Spiritual),
        listOf()
    )

    private val deflectTrajectory = FreeSpell(
        "Deflect Trajectory",
        false,
        75,
        100,
        "The caster can alter a moving body\'s trajectory, redirecting its course as he " +
                "pleases. This spell can affect objects of any mass, from an arrow to a large " +
                "ship. However, this spell does not cause stationary objects to move; it " +
                "modifies the direction of those objects already in motion. Deflect Trajectory " +
                "can also be used defensively against weapon attacks. If the caster succeeds " +
                "in his Magic Projection Defense, he manages to redirect the attack to another " +
                "opponent. The spell may be resisted by passing a MR Check with a Difficulty of 140.",
        "+5 to MR",
        20,
        null,
        false,
        listOf(SpellType.Effect, SpellType.Defense),
        listOf(Element.Fire)
    )

    private val stallSpell = FreeSpell(
        "Stall Spell",
        true,
        75,
        150,
        "This spell allows for another spell to be stored inside an object to be used later, " +
                "possibly by a different spellcaster. Stall Spell must be cast at the same time as " +
                "the spell it will affect. The spell that is stalled may be stored in any " +
                "available object - easily transported items such as jewelry, staves, and pebbles " +
                "are common choices. The stalled spell can be activated later, by any caster who " +
                "pays the exact Zeonic cost with which the spell was cast. The spellcaster in " +
                "possession of the objects is in full control of the spell as if he were the " +
                "original caster, and he will even be allowed to pay for Maintenance, if " +
                "applicable. The stalled spell may be reused as long as the same Zeonic cost is " +
                "paid again. For example, an archmage could store a Zeonic-value 80 Fire Ball in a " +
                "staff and give it to his apprentice, who could then use the staff to cast a " +
                "Fire Ball every time he had 80 Zeonic points to spend. Stall Spell can affect " +
                "any other spell with a Zeonic value of 100 or below.",
        "+10 to maximum Zeonic value of stored spell",
        20,
        null,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Air)
    )

    private val detectionMark = FreeSpell(
        "Detection Mark",
        true,
        75,
        100,
        "This spell allows the caster to inscribe an arcane mark on a person, creature, or " +
                "object. The mark channels the caster\'s five senses, allowing him to experience " +
                "the sights, sounds, smells, tastes, and physical sensations surrounding the mark, " +
                "regardless of its location. The caster is also always aware of the exact " +
                "location of the mark. The target may not have a Presence higher than 50. Living " +
                "beings and supernatural objects can avoid this spell by passing a MR Check with " +
                "a Difficulty of 120.",
        "+5 to maximum Presence affected and +5 to MR Difficulty",
        10,
        10,
        true,
        listOf(SpellType.Effect, SpellType.Spiritual),
        listOf(Element.Dark)
    )

    private val flight = FreeSpell(
        "Flight",
        true,
        75,
        100,
        "The target is able to move with a Flight Value 8.",
        "+1 to Flight Value",
        10,
        20,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Earth)
    )

    private val offenseErudition = FreeSpell(
        "Offensive Erudition",
        true,
        75,
        80,
        "With the aid of this spell, the caster is able to increase his offensive Magic " +
                "Projection by +20. Only one Offensive Erudition spell at a time can be used on " +
                "a specific subject.",
        "+5 to offensive Magic Projection",
        10,
        10,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Creation)
    )

    private val defenseErudition = FreeSpell(
        "Defensive Erudition",
        true,
        75,
        80,
        "With the aid of the spell, the caster is able to increase his defensive Magic " +
                "Projection by +20. Only one Defensive Erudition spell at a time can be used on " +
                "a specific subject.",
        "+5 to defensive Magic Projection",
        10,
        10,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Creation)
    )

    private val containment = FreeSpell(
        "Containment",
        true,
        75,
        200,
        "This spell haunts a given place, making it impossible for anyone entering it or, " +
                "for those already within its boundaries, to leave the area unless they pass a MR " +
                "with a Difficulty of 120. Beings within the area of the Containment can move and " +
                "act normally within the spell\'s boundaries. Subjects are not allowed to repeat " +
                "the check for as long as the spell is being maintained - unless they increase " +
                "their resistances. The caster cannot select targets in the area and may even be " +
                "subject to the spell himself. Area of coverage cannot exceed a 10-meter radius, " +
                "and the condition to be affected by this spell is being inside its area in the " +
                "next round it is cast.",
        "+5 meters to radius and +5 to MR Difficulty",
        20,
        5,
        true,
        listOf(SpellType.Automatic),
        listOf()
    )

    private val perfectTarget = FreeSpell(
        "Perfect Target",
        true,
        75,
        80,
        "This spell enchants a conventional projectile, causing it to reach its target " +
                "inevitably. The projectile uses the ability of its thrower, and opponents " +
                "defend themselves using the conventional rules against projectiles. Nevertheless, " +
                "the supernatural nature of the spell grants the offender a +60 bonus to the " +
                "Attack allowing the thrower to bypass the shooting difficulties in Table 45. " +
                "Range is not a factor; as long as the caster has line of sight to the target, it " +
                "can be hit. The projectile\'s Presence cannot exceed 40. Perfect Target must be " +
                "cast in the same round as the projectile is fired.",
        "+10 to the maximum Presence of the projectile",
        10,
        null,
        false,
        listOf(SpellType.Effect),
        listOf()
    )

    val eighthBook = listOf(
        invisibility,
        levitationSphere,
        uselessness,
        dominion,
        deflectTrajectory,
        stallSpell,
        detectionMark,
        flight,
        offenseErudition,
        defenseErudition,
        containment,
        perfectTarget
    )

    private val spellReturn = FreeSpell(
        "Spell Return",
        false,
        85,
        150,
        "This spell turns an active spell against the original caster or any other target " +
                "selected. The returned spell uses the same Magic Projection as the person who " +
                "cast it. Return is an automatic effect. The spell in question must have a " +
                "Zeonic value of 100 or less.",
        "+5 to the spell\'s Zeonic value",
        20,
        null,
        false,
        listOf(SpellType.Automatic),
        listOf()
    )

    private val disenchantment = FreeSpell(
        "Disenchantment",
        true,
        85,
        200,
        "This spell automatically destroys magical objects with Presence scores lower than " +
                "80. The caster can choose whether to destroy the object completely or simply to " +
                "deprive it of its supernatural qualities.",
        "+5 to the maximum Presence affected",
        20,
        null,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Creation)
    )

    private val naturalSpell = FreeSpell(
        "Natural Spell",
        true,
        85,
        350,
        "Natural Spell allows the caster to tie his essence to one or more spells prepared " +
                "in advance, thus enabling him to cast them later as many times as he likes with " +
                "zero Zeonic cost. In this way, the spellcaster can perform one of these spells " +
                "innately per turn. At the time of using this natural spell, the caster can\'t " +
                "accumulate magic or prepare any other spells. The total Zeonic value of the " +
                "natural spell(s) cannot exceed 100 points. Only one Natural Spell at a time may " +
                "be kept active at a time.",
        "+5 to Zeonic value affected",
        30,
        5,
        true,
        listOf(SpellType.Effect),
        listOf()
    )

    private val stealSpell = FreeSpell(
        "Steal Spell",
        true,
        85,
        200,
        "This spell allows the caster to take control of another spell that does not belong " +
                "to him and treat it as if he had cast it himself. The stolen spell\'s Zeonic " +
                "value cannot exceed 80 points. Only maintenance spells can be stolen, not " +
                "automatic action spells. The original spellcaster can attempt to prevent this " +
                "theft by passing a MR Check with a Difficulty of 120.",
        "+5 to Zeonic value of the spell affected and +5 to MR Difficulty",
        20,
        null,
        false,
        listOf(SpellType.Automatic),
        listOf()
    )

    private val immortality = FreeSpell(
        "Immortality",
        true,
        85,
        300,
        "Targets of this spell do not age or in any way suffer the passage of time for as " +
                "long as the spell remains active. They also become invulnerable to poison and " +
                "natural illnesses. Despite its name, this spell does not protect the subject " +
                "from death due to injury. If this spell is discontinued or destroyed, the " +
                "affected individual will catch up with his real age at a much accelerated speed. " +
                "This spell can only affect subjects with a Presence under 60.",
        "+5 to the maximum Presence affected",
        30,
        20,
        true,
        listOf(SpellType.Effect),
        listOf(Element.Destruction)
    )

    private val magicPrism = FreeSpell(
        "Magic Prism",
        true,
        85,
        200,
        "This spell solidifies magic, creating a supernatural container for Zeon. In game " +
                "terms, it creates a magic container with a maximum capacity of 400 Zeon points. " +
                "The container appears empty. Only one Magic Prism spell at a time may be kept active.",
        "+25 to the the container\'s Zeon capacity",
        20,
        20,
        true,
        listOf(SpellType.Effect),
        listOf(Element.Destruction)
    )

    private val eliminateNeeds = FreeSpell(
        "Eliminate Needs",
        true,
        85,
        300,
        "This spell completely removes most physical needs from one or more individuals. As " +
                "long as the spell is kept active, the targets do not need to eat, drink, or sleep " +
                "and can easily cope with any weather conditions. This spell may be applied to " +
                "as many individuals as the caster wishes, as long as their total Presence " +
                "scores remain under 120.",
        "+5 to the maximum Presence affected",
        30,
        20,
        true,
        listOf(SpellType.Effect),
        listOf(Element.Essence)
    )

    private val prepareSpell = FreeSpell(
        "Prepare Spell",
        true,
        85,
        200,
        "This spell allows the caster to introduce a second spell inside an object or " +
                "person, so that it can automatically be cast later, without preparation or " +
                "accumulation. Prepare Spell must be cast at the same time as the spell it will " +
                "affect. The prepared spell may be activated by the caster, the subject hosting " +
                "it, or the bearer of the enchanted object. It uses the Magic Projection of the " +
                "original caster, unless the bearer decides to use his own. Once cast, the spell " +
                "vanishes. The maximum Zeonic value of the spell cannot exceed 100. If maintenance " +
                "payment of Prepare Spell is discontinued, the second spell will be lost as well. " +
                "Only one prepared spell may be cast per turn, regardless of how many spells there " +
                "are stored inside an object. An object or person may contain up to four times its " +
                "Presence in Zeonic value. This means an individual with a Presence of 50 could " +
                "have up to 200 Zeon points in prepared spells.",
        "+5 to the total stored Zeonic value",
        30,
        10,
        true,
        listOf(SpellType.Effect),
        listOf()
    )

    private val physicalImmunity = FreeSpell(
        "Physical Immunity",
        true,
        85,
        200,
        "The designated object or individual is made completely invulnerable to any damage " +
                "not Energy related. It may be applied to as many individuals as the caster " +
                "wishes, as long as their total Presence scores remain under 60.",
        "+5 to the maximum Presence affected",
        20,
        20,
        true,
        listOf(SpellType.Effect),
        listOf(Element.Essence)
    )

    private val gate = FreeSpell(
        "Gate",
        true,
        85,
        500,
        "Gate generates a magical door between two distant places. Anyone crossing it is " +
                "automatically transported to the other side. The spell remains linked to the " +
                "spot where it is cast and to the designated exit. Therefore, the spellcaster " +
                "will not be able to change its location at a later stage. He will, however, be " +
                "able to decide if it will work in only one or both directions at the time the " +
                "spell is cast. The gate has a maximum length of 5 meters, and the maximum " +
                "distance between the exit and the entrance is 1000 kilometers. Each day, the gate " +
                "can transport a total number of creatures equal to Presence score of 500.",
        "+50 to the maximum Presence transported per day, +1000 kilometers to distance " +
                "between both ends of the gate, and +1 extra meter to gate width",
        50,
        20,
        true,
        listOf(SpellType.Effect),
        listOf()
    )

    private val teletransportation = FreeSpell(
        "Teletransportation",
        true,
        85,
        300,
        "The caster, or whomever the caster designates, can instantly travel anywhere, up " +
                "to a maximum distance of 50 kilometers. The spell allows characters to pass " +
                "through physical bodies, provided they are not based on Energy. It may be " +
                "applied to as many individuals as the conjurer wishes, as long as their total " +
                "Presence scores do not exceed 80.",
        "+50 kilometers to range and +5 to maximum Presence affected",
        40,
        null,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Earth)
    )

    private val location = FreeSpell(
        "Location",
        true,
        85,
        300,
        "This spell can be used for finding the exact position of a person, place, or object at " +
                "a specified time, provided it is not more than 100 kilometers away from the " +
                "caster. The caster must be able to identify a specific target; general inquiries " +
                "do work with this spell. For example, he will not be able to find his brother\'s " +
                "murderer unless he knows exactly who it is. Characters wishing to avoid detection " +
                "must pass a MR Check with a Difficulty of 120. Very spacious areas impose a -40 " +
                "penalty to this check.",
        "+5 to MR Difficulty and +100 kilometers to range",
        30,
        null,
        false,
        listOf(SpellType.Detection),
        listOf(Element.Dark)
    )

    val ninthBook = listOf(
        spellReturn,
        disenchantment,
        naturalSpell,
        stealSpell,
        immortality,
        magicPrism,
        eliminateNeeds,
        prepareSpell,
        physicalImmunity,
        gate,
        teletransportation,
        location
    )

    private val eyeOfTime = FreeSpell(
        "Eye of Time",
        true,
        95,
        300,
        "This is a limited time travel spell that allows the caster to witness an event " +
                "that might have occurred within the last 100 years in his current location. " +
                "After the spell becomes active, the caster may go back and forth in time at will, " +
                "but he may not physically move more than a single step in any direction, or the " +
                "spell immediately ends.",
        "+100 years",
        50,
        20,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Dark)
    )

    private val possession = FreeSpell(
        "Possession",
        true,
        95,
        300,
        "This spell enables the caster to take control of another individual\'s body, " +
                "temporarily relocating part of his spirit in the host. This allows the caster to " +
                "use all the abilities of the invaded subject freely or his own. In this second " +
                "case, the spellcaster uses the physical Characteristics and bonuses of the host. " +
                "If the victim and the caster have very dissimilar bodies, the GM can apply a " +
                "penalty between -20 and -40 on account of the difficulty involved in the change. " +
                "The caster can decide to use either his own abilities or his host\'s every turn, " +
                "but he cannot mix them at the same time. The spellcaster\'s body remains in a coma " +
                "for as long as Possession is active. Non-Energy-damaging attacks will affect the " +
                "possessed body only, whereas supernatural attacks will subtract Life Points from " +
                "both. If the body in question is that of a creature with Damage Resistance, the " +
                "caster suffers only one-tenth (or less, in case the creature has a Damage " +
                "Resistance multiple higher than 10) of the damage received by the physical form. " +
                "If the possessed body should die while the caster still has Life Points left, his " +
                "spirit immediately returns to his original form. Avoiding the effects of the " +
                "spell requires the victim to pass a MR Check with a Difficulty of 120. The " +
                "affected party is entitled to a new roll each day or each time he is made to " +
                "carry out an action completely against his nature.",
        "+5 to MR Difficulty",
        30,
        10,
        true,
        listOf(SpellType.Spiritual),
        listOf()
    )

    private val powerShield = FreeSpell(
        "Shield Against Powers",
        true,
        95,
        300,
        "This spell affects a specific area within which no supernatural powers can take " +
                "effect. No spells with Zeonic values of 150 or below, no Psychic Powers with " +
                "Potentials lower than 150, and no Ki Technique costing less than a total of 13 " +
                "Ki Points will work within this area. In the same way, no Summoning activities " +
                "with a Difficulty under 200 are allowed. The maximum area covered with this spell " +
                "is a 50-meter radius, which remains stationary.",
        "+50 meters to radius, +5 to Zeonic value, +5 to Psychic Potential, +2 Ki Points, and +10 to Summoning Difficulty",
        30,
        10,
        true,
        listOf(SpellType.Automatic),
        listOf(Element.Creation)
    )

    private val seal = FreeSpell(
        "Seal",
        true,
        95,
        200,
        "Seal will stall a maintained Spiritual spell in someone, making it impossible for " +
                "the target to be freed from its effects. This enchantment is used on Spiritual " +
                "spells previously cast successfully on an individual who failed the Resistance " +
                "Check. In this way, the affected character loses all ability to make another MR " +
                "Check, regardless of general rules or what the spell itself dictates, until the " +
                "Spiritual spell itself is destroyed or maintenance is discontinued. Seal has no " +
                "effect upon non-maintenance spells or those restricted to a specific area. The " +
                "affected enchantments must have a Zeonic value of 100 or below.",
        "+5 to Zeonic value affected",
        30,
        null,
        false,
        listOf(SpellType.Effect),
        listOf()
    )

    private val imitateSpell = FreeSpell(
        "Imitate Spell",
        false,
        95,
        200,
        "This spell allows the caster to imitate a spell being cast before him. Any type of " +
                "spell, from automatic to maintained ones, can be imitated, provided they are " +
                "cast in the same round as Imitate Spell and have fewer than 100 Zeon. This " +
                "spell can even imitate High or Divine Magic without the need of the caster having " +
                "the required Gnosis level to cast them. The caster determines whether to use his " +
                "own Magic Projection or that of the original caster. The caster can maintain the " +
                "imitated spell using that spell\'s Maintenance Value.",
        "+5 to Zeonic value affected",
        30,
        null,
        false,
        listOf(SpellType.Effect),
        listOf(Element.Destruction)
    )

    private val conditioning = FreeSpell(
        "Conditioning",
        true,
        95,
        300,
        "This spell directly affects other spells cast by the spellcaster, stalling them " +
                "and delaying their activation until the advent of a particular circumstance or " +
                "moment previously determined by the caster. Conditioning must be cast at the " +
                "same time as the spells it will affect. Each restricted spell may be subject to " +
                "a different condition, but once set, a caster cannot make any changes at a later " +
                "date\nThe conditioned spell automatically activates at the exact designated " +
                "moment - even if the caster is unaware. For instance, a caster may condition " +
                "Stop Fall to activate when falling from great heights. In the same way, a " +
                "necromancer may condition Defeat Death to activate at the time of his death. " +
                "Conditioned spells that have not been triggered require no maintenance. If the " +
                "Conditioning spell itself disappears, all other spells linked to it also " +
                "disappear. Any number of spells may be Conditioned as long as the total number " +
                "of Zeon points is 100 or below. Only one Conditioning spell at a time may be " +
                "kept active. Spell action will be Active or Passive depending on its own nature.",
        "+5 to the spell\'s Zeonic value",
        40,
        5,
        true,
        listOf(SpellType.Effect),
        listOf()
    )

    private val linkMaintenance = FreeSpell(
        "Link Maintenance",
        true,
        95,
        200,
        "The spellcaster may assign maintenance payment of one of his spells to somebody " +
                "else, thus forcing the target to invest Zeon points unknowingly. Link " +
                "Maintenance con only be used in persons with the Gift, or magical beings or " +
                "objects. Characters wishing to avoid this spell need to pass a MR Check with a " +
                "Difficulty of 120. Those individuals unaware of the fact that they are being " +
                "targeted cannot roll again for Resistance. If they realize this and try to stop " +
                "it, they will be granted another check depending on the type of spell they are " +
                "being linked to. Daily maintenance spells grant daily checks, whereas the rest " +
                "will grant one check every five turns. Linking stops as soon as the target passes " +
                "his MR or runs out of Zeon points.",
        "+5 to MR Difficulty",
        20,
        null,
        false,
        listOf(SpellType.Spiritual),
        listOf()
    )

    private val magistrate = FreeSpell(
        "The Magistrate",
        true,
        95,
        450,
        "With this spell, the caster acts as a supreme judge of all events that transpire in " +
                "a 50-meter radius around him. Within this space, the caster has the power to " +
                "prohibit any Active Action taken by any person or creature who does not pass a " +
                "MR Check with a Difficulty of 140. This check must be repeated each round in " +
                "which an Active Action is attempted, unless the caster chooses to allow the " +
                "action to occur. Only Active Actions may be prohibited, so affected targets may " +
                "still perform Passive Actions, such as repelling attack. In order to prohibit " +
                "an action, the caster must be aware of it, so some individuals may be able to " +
                "overcome the effects of this spell through deception or subterfuge. Prohibiting " +
                "another\'s Active Action counts as a Passive Action for the caster. This spell\'s " +
                "area of effect remains stationary.",
        "+50 meters to radius and +5 to MR Difficulty",
        40,
        10,
        true,
        listOf(SpellType.Automatic),
        listOf()
    )

    private val giftOfKnowledge = FreeSpell(
        "The Gift of Knowledge",
        true,
        95,
        300,
        "This spell grants the target 100 bonus points to Secondary Abilities of the " +
                "Intellectual field, which he may distribute freely as he sees fit. Only one Gift " +
                "of Knowledge may be active in any given subject at a time.",
        "+10 to be allocated in Intellectual Secondary Abilities",
        50,
        10,
        true,
        listOf(SpellType.Effect),
        listOf(Element.Destruction)
    )

    private val strengthenMagic = FreeSpell(
        "Strengthen Magic",
        true,
        95,
        200,
        "This spell intensifies the power of the magic used by the caster, making his spells " +
                "harder to destroy. As long as this spell is maintained, all other spells the " +
                "caster performs are treated as having +50 Zeonic value. This increase is not " +
                "actual Zeon, so it cannot provide any Added Effect bonuses, but it does make " +
                "spells harder to destroy. For instance, a character casting Create Light with a " +
                "Zeonic value of 30 while simultaneously maintaining Strengthen Magic would not " +
                "see an increase in the Light spell, but if another character casts Destroy " +
                "against him, the spell would have, to all practical purposes, a Zeonic value of " +
                "80 instead of 30.",
        "+5 Zeonic value to spells",
        40,
        10,
        true,
        listOf(SpellType.Effect),
        listOf(Element.Destruction)
    )

    private val innateMagic = FreeSpell(
        "Innate Magic",
        true,
        95,
        500,
        "This spell increases the environmental magical potential of an area, thus increasing " +
                "the capability for spellcasters to perform magic innately. The spell enchants a " +
                "25-meter radius area, within which innate spells have a +30 Potential (in " +
                "addition to what the caster\'s MA indicates). The caster of Innate Magic can " +
                "choose who will benefit from this spell.",
        "+10 meters to radius",
        50,
        10,
        true,
        listOf(SpellType.Effect),
        listOf()
    )

    val predestination = FreeSpell(
        "Predestination",
        true,
        95,
        600,
        "This spell allows the caster to modify future events according to his plan, thus " +
                "predestining several circumstances in the future. The complexity of the events " +
                "is left to the caster\'s own design. He may preordain one event, leaving the " +
                "different small happenings that lead up to its realization to chance, or quite " +
                "on the contrary, he may try to determine each one of the aspects involved in " +
                "producing a final result. The spell is not meant to cause a completely impossible " +
                "outcome. However, the odds are limited only by the caster\'s imagination. " +
                "Preordained events may be set for a specific date or they may be scheduled to " +
                "repeat eternally through time - solstices and leap years, for example.\nGiven the " +
                "huge complexity of the spell, let\'s look at a few examples of what it could be " +
                "used for: An imprecise Predestination would be a caster\'s prediction of " +
                "misfortune befalling an entire family and their offspring, condemning all their " +
                "members to a horrible death before reaching a certain age. On the other hand, " +
                "should the caster wish to be more explicit, he could condemn their first born " +
                "children to be murdered by wild wolves on the very night of their birth. " +
                "Naturally, not all predestinations need to be negative; the caster might " +
                "predestine a child to become a king.\nHowever, the future created by the spell " +
                "is not absolute and inevitable; there is always the chance of preventing it " +
                "from coming true. Anyone outside the limits preordained by the caster, that is, " +
                "anyone passing the MR Check, can keep destiny from fulfilling itself. In order " +
                "to neutralize the actions of third parties, the caster may plan ahead for some " +
                "security measures inside the limits of the spell. A good example of this would " +
                "be the inclusion of a clause stating that anyone who tries to thwart destiny will " +
                "be murdered by wolves. Preventing this spell from working only requires that the " +
                "person, creature, or object with the highest resistance of those directly " +
                "affected by the spell passes a MR Check with a Difficulty of 140 at the time the " +
                "spell is being cast. Those influenced by its effects will not be able to do " +
                "anything to stop it from happening; chance will inevitably play against them in " +
                "the most unavoidable of ways. The MR can be repeated only when some of the " +
                "predestined events have come true. Therefore, the greater the detail, the " +
                "greater the chances of preventing the event. If any of the preordained events " +
                "should be stopped, the whole spell is cancelled.",
        "+5 to MR Difficulty",
        40,
        20,
        true,
        listOf(SpellType.Automatic),
        listOf()
    )

    val tenthBook = listOf(
        eyeOfTime,
        possession,
        powerShield,
        seal,
        imitateSpell,
        conditioning,
        linkMaintenance,
        magistrate,
        giftOfKnowledge,
        strengthenMagic,
        innateMagic,
        predestination
    )
}