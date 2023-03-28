package com.example.animabuilder.character_creation.attributes.psychic.disciplines

import com.example.animabuilder.character_creation.attributes.psychic.Discipline
import com.example.animabuilder.character_creation.attributes.psychic.PsychicPower
import java.io.Serializable

/**
 * Record of all of the available psychokinetic powers.
 */
class Psychokinesis: Discipline(), Serializable {
    val minorKinesis = PsychicPower(
        "Minor Psychokinesis",
        1,
        true,
        true,
        "This Power allows a psychic to move inorganic matter from a distance. The " +
                "weight and speed of the action depend upon the success a psychic has in using " +
                "this Power (as detailed on the Effects Table). When used for hurling objects, as " +
                "in long distance attacks, the character's Psychic Projection is reduced by half, " +
                "because the control this Power offers isn't meant for that use. A fighter with " +
                "this Power who uses the Psychic Projection Module can gain control of a weapon " +
                "and perform a long distance attack using his Psychic Projection rather than his " +
                "Attack score (provided of course he has developed his Attack Ability and Psychic " +
                "Projection on equal terms).",
        listOf(
            "Fatigue 1",
            "1 kg/Flight Value 4",
            "2 kg/Flight Value 6",
            "5 kg/Flight Value 8",
            "10 kg/Flight Value 10",
            "20 kg/Flight Value 12",
            "40 kg/Flight Value 14",
            "100 kg/Flight Value 16",
            "200 kg/Flight Value 18",
            "500 kg/Flight Value 20"
        )
    )

    val kineticImpact = PsychicPower(
        "Psychokinetic Impact",
        1,
        true,
        false,
        "This Power projects an invisible force that impacts its target with variable " +
                "potency. Even though its main function is to push the target, Psychokinetic " +
                "Impact can cause damage equal to twice the Strength bonus indicated on the Effects " +
                "Table, plus whatever the GM regards suitable considering the surroundings.",
        listOf(
            "Fatigue 2",
            "Fatigue 1",
            "Strength 8",
            "Strength 10",
            "Strength 12",
            "Strength 14",
            "Strength 15",
            "Strength 16",
            "Strength 18",
            "Strength 20"
        )
    )

    val kineticTrap = PsychicPower(
        "Psychokinetic Trap",
        1,
        true,
        true,
        "This Power enables the user to carry out Trapping maneuvers through Psychic " +
                "Projection - with no penalties. The success of the Power's activation determines " +
                "the intensity of the Trap (as detailed in the Effects Table). The Power could " +
                "become so strong as to trap several subjects within a given area. A -2 to the " +
                "Power's Strength score applies when trapping multiple targets.",
        listOf(
            "Fatigue 2",
            "Fatigue 1",
            "Strength 6",
            "Strength 8",
            "Strength 10",
            "Strength 12/5-meter radius",
            "Strength 14/10-meter radius",
            "Strength 15/50-meter radius",
            "Strength 16/100-meter radius",
            "Strength 18/500-meter radius"
        )
    )

    val kineticShield = PsychicPower(
        "Psychokinetic Shield",
        1,
        false,
        true,
        "This Power creates a psychokinetic shield that protects the user from physical " +
                "attacks, including most weapons - even those with magical enchantments. It does " +
                "not offer protection against spell or Energy-based damage. However, if a " +
                "character creates a barrier with a Power level higher than Impossible, he can " +
                "use it to stop ethereal effects and attacks. Upon reaching a certain level, the " +
                "shield gains a damage barrier (as detailed on the Effects Table).\nUnlike other " +
                "maintained Powers, which a character can only maintain at a strength equal to his " +
                "Base Psychic Potential, the Psychokinetic Shield keeps the Life Points that it " +
                "had when it was created. For example, someone with a Psychic Potential of 140 " +
                "can Maintain a Power at a Very Difficult level (giving the shield 700 LP), but if " +
                "he uses a Psychokinetic Shield and succeeds at activating the Power with a roll " +
                "of 240 (giving it 1500 LP), the shield would maintain using the 1,500 LP instead " +
                "of the 700 LP. However, in each subsequent turn, it loses 5 Life Points until it " +
                "has only 700 LP - its normal Base Potential.",
        listOf(
            "Fatigue 2",
            "Fatigue 1",
            "300 LP",
            "500 LP",
            "700 LP",
            "1000 LP",
            "1500 LP/Damage Barrier 60",
            "2000 LP/Damage Barrier 80/Stops Energy",
            "3000 LP/Damage Barrier 120/Stops Energy",
            "5000 LP/Damage Barrier 160/Stops Energy"
        )
    )

    val kineticArmor = PsychicPower(
        "Psychokinetic Armor",
        1,
        false,
        true,
        "This Power creates a force armor around the psychic, or anyone he designates. " +
                "The armor's AT offers protection against all attacks save those based upon " +
                "Energy. It can be used in conjunction with any other protection as an additional " +
                "layer, but it will not cause any special penalties.",
        listOf(
            "Fatigue 2",
            "Fatigue 1",
            "AT 1",
            "AT 2",
            "AT 4",
            "AT 6",
            "AT 8",
            "AT 10",
            "AT 12",
            "AT 14"
        )
    )

    val motionDetection = PsychicPower(
        "Motion Detection",
        2,
        true,
        true,
        "All bodies in motion within the radius of this Power who fail the required PhR " +
                "Check will be detected by the psychic. He will perceive the object's speed, size, " +
                "and direction, but he will not be able to distinguish its shape. The ability will " +
                "only work on physical material forms; things without substance will remain " +
                "undetected. This ability does not call for Psychic Projection; it will " +
                "automatically affect all parties within its area of effect. Ki Concealment works " +
                "against this Power by providing a bonus to the PhR Check (as described in Chapter 10).",
        listOf(
            "Fatigue 4",
            "Fatigue 2",
            "Fatigue 1",
            "120 PhR/10-meter radius",
            "160 PhR/50-meter radius",
            "200 PhR/100-meter radius",
            "240 PhR/500-meter radius",
            "280 PhR/1-kilometer radius",
            "320 PhR/10-kilometer radius",
            "400 PhR/100-kilometer radius"
        )
    )

    val repulsion = PsychicPower(
        "Repulsion",
        2,
        true,
        true,
        "Repulsion creates a barrier that violently repels any physical body that comes " +
                "into contact with it - unless it wins an Opposed Strength or Agility Check. No " +
                "Psychic Projection is necessary for focusing this Power. Repulsion affects all " +
                "objects or individuals in touch with the barrier. At the time a character " +
                "creates the barrier, he cannot use it against specific targets. The barrier's " +
                "length is determined by the character's success in activating the Power (as " +
                "detailed in the Effects Table). Its shape, however, is left to the psychic to " +
                "determine - which means that he could even decide to wrap the barrier around his body.",
        listOf(
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "Strength 6/2-meter line",
            "Strength 8/5-meter line",
            "Strength 10/10-meter line",
            "Strength 12/20-meter line",
            "Strength 14/50-meter line",
            "Strength 18/100-meter line"
        )
    )

    val ballistics = PsychicPower(
        "Ballistics",
        2,
        true,
        false,
        "The Power enables the psychic to throw objects with extreme precision using his " +
                "Psychic Projection. The higher the character's potential, the higher the " +
                "precision and quantity of elements he will be able to throw simultaneously, " +
                "ranging from a simple dagger to thousands of huge rocks. Depending on the PC the " +
                "psychic has obtained, he can increase either the number of objects, so as to " +
                "cover a much larger area, or the precision of his power. In other words, he must " +
                "make a choice between a bonus to his projection or to throwing a multitude of " +
                "objects over an area of effect. For example, if he reached Difficulty Level " +
                "Absurd, he would have to choose between receiving a +20 projection bonus to " +
                "throwing an element, or to launching a shower of attacks that would cover a " +
                "15-meter radius. Damage will vary according to the elements being projected and " +
                "to whether area of effect is being used or not. If only one object is being " +
                "thrown, damage depends exclusively on the element being projected with Ballistics; " +
                "if it is a weapon, it uses its Base Damage and adds the character's bonus for " +
                "Willpower instead of Strength. For objects in area attacks, damage depends on the " +
                "kind of elements being used: scenery elements (tables, chairs, rocks) or weapons. " +
                "In the case of scenery elements, damage is determined by the GM, who will decide " +
                "the value on a 30 to 150 scale, depending on how dangerous the elements of the " +
                "scenery available to the psychic are. The use of a multitude of weapons increases " +
                "their Base Damage by 50%.\nProjectiles launched by this ability fall beyond the " +
                "psychic's control; they need to be recovered before they can be used a second time.",
        listOf(
            "Fatigue 4",
            "Fatigue 2",
            "Fatigue 1",
            "+0 Projection/5 meters",
            "+10 Projection/10 meters",
            "+20 Projection/15 meters",
            "+30 Projection/25 meters",
            "+40 Projection/40 meters",
            "+50 Projection/80 meters",
            "+60 Projection/150 meters"
        )
    )

    val shatter = PsychicPower(
        "Shatter",
        2,
        true,
        false,
        "This Psychic Power shatters a body, causing it to burst into pieces from the " +
                "inside. If an object is targeted, it needs to pass a PhR Check to avoid being " +
                "shattered. Note that Special Quality objects will not break automatically; they " +
                "will lose one level for every 50 points by which they fail the check. If the " +
                "target is a naturally live being, he needs to use his PhR against the required " +
                "Difficulty or lose twice the number of LP by which he failed the check. If the " +
                "target is an object with Structural Resistance or a creature with Damage " +
                "Resistance, damage equals five times the check's Failure Level. Naturally, only " +
                "material beings are susceptible to this Power.",
        listOf(
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "100 PhR",
            "120 PhR",
            "140 PhR",
            "160 PhR",
            "180 PhR",
            "220 PhR"
        )
    )

    val kineticFlight = PsychicPower(
        "Psychokinetic Flight",
        2,
        true,
        true,
        "The psychic gets to move freely through the air, with the Flight Value indicated by his success in activating the Power, as detailed on the Effects Table.",
        listOf(
            "Fatigue 4",
            "Fatigue 2",
            "Fatigue 1",
            "Flight Value 6",
            "Flight Value 8",
            "Flight Value 10",
            "Flight Value 12",
            "Flight Value 14",
            "Flight Value 16",
            "Flight Value 18"
        )
    )

    val organicKinesis = PsychicPower(
        "Organic Psychokinesis",
        2,
        true,
        true,
        "This Power allows the psychic to move material objects of an organic nature - " +
                "provided that the target fails his PhR Check. Speed is determined by the " +
                "psychic's success in activating the Power (as detailed in the Effects Table).",
        listOf(
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "100 kg/Flight Value 4/100 PhR",
            "250 kg/Flight Value 6/120 PhR",
            "500 kg/Flight Value 8/140 PhR",
            "1000 kg/Flight Value 10/160 PhR",
            "2500 kg/Flight Value 12/180 PhR",
            "5000 kg/Flight Value 14/200 PhR",
            "10000 kg/Flight Value 16/220 PhR"
        )
    )

    val groundControl = PsychicPower(
        "Ground Control",
        3,
        true,
        false,
        "Ground Control grants the psychic complete control of the area or terrain in " +
                "which he stands. His dominion is absolute; he can create a small earthquake or " +
                "build a huge stone wall - as long as he stays within the area of effect. If a " +
                "psychic wishes to affect something that has been built, its possible destruction " +
                "is subject ot its Damage Barrier.",
        listOf(
            "Fatigue 16",
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "10-meter radius/Damage Barrier 40",
            "100-meter radius/Damage Barrier 60",
            "250-meter radius/Damage Barrier 80",
            "500-meter radius/Damage Barrier 100",
            "1-kilometer radius/Damage Barrier 140"
        )
    )

    val atomicRestructuring = PsychicPower(
        "Atomic Restructuring",
        3,
        true,
        false,
        "The psychic is capable of restructuring the atoms of any organic or inorganic " +
                "material, transforming its substance and form. A psychic could turn a living " +
                "being into a stone statue, or a pile of sand into coins of gold, for example. " +
                "Nevertheless, a character's ability to mold and forge is limited by his knowledge " +
                "of Art and Forging. However, the Difficulty for checks using these abilities are " +
                "two levels lower than normal as the character can act freely upon the object to " +
                "be changed. In terms of material remodeling, a character can alter the quality of " +
                "an object five grades up or down. However, a psychic cannot make materials of a " +
                "mystical nature - such as malebolgia, illuminatum, or stellar metal. The degree " +
                "to which a character successfully activates this Power determines the maximum " +
                "amount of mass affected, as well as the Resistance target Difficulty for those " +
                "who do not wish to be affected by Atomic Restructuring (as detailed in the " +
                "Effects Table below).",
        listOf(
            "Fatigue 24",
            "Fatigue 20",
            "Fatigue 16",
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "140 PhR/100 kg",
            "160 PhR/10 tons",
            "200 PhR/100 tons"
        )
    )

    val majorKinesis = PsychicPower(
        "Major Psychokinesis",
        3,
        true,
        true,
        "This is an amplified version of Minor Psychokinesis that allows characters to " +
                "move much heavier masses.",
        listOf(
            "Fatigue 24",
            "Fatigue 20",
            "Fatigue 16",
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 4",
            "500 tons/Flight Value 4",
            "10000 tons/Flight Value 6",
            "100000 tons/Flight Value 8",
            "1000000 tons/Flight Value 10"
        )
    )

    override var allPowers = listOf(minorKinesis, kineticImpact, kineticTrap, kineticShield, kineticArmor,
        motionDetection, repulsion, ballistics, shatter, kineticFlight, organicKinesis,
        groundControl, atomicRestructuring, majorKinesis)
}