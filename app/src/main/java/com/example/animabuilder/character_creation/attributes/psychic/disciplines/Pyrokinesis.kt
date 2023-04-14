package com.example.animabuilder.character_creation.attributes.psychic.disciplines

import com.example.animabuilder.character_creation.attributes.psychic.Discipline
import com.example.animabuilder.character_creation.attributes.psychic.PsychicPower

/**
 * Record of all of the available pyrokinetic powers.
 */
class Pyrokinesis: Discipline(){
    val createFire = PsychicPower(
        "Create Fire",
        1,
        true,
        true,
        "This Power creates Fire Intensities or arouses an existing flame to equal " +
                "proportions. If used upon a body capable of catching fire, Create Fire does not " +
                "require maintenance. If not, maintenance allows the flame to burn without " +
                "consuming anything - although nothing prevents it from being extinguished.",
        listOf(
            "Fatigue 1",
            "1 Intensity",
            "3 Intensities",
            "5 Intensities",
            "7 Intensities",
            "10 Intensities",
            "13 Intensities",
            "16 Intensities",
            "20 Intensities",
            "25 Intensities"
        )
    )

    val extinguishFire = PsychicPower(
        "Extinguish Fire",
        1,
        true,
        false,
        "This Power lowers the Intensity of an existing fire. When cast upon a heat-based " +
                "being, the creature will suffer 5 Life Points of damage for every diminished " +
                "Intensity - if it does not make a successful PhR Check. Damage Resistance " +
                "creatures suffer 25 points of damage per Intensity. Bear in mind that a fire " +
                "not completely extinguished may regain strength in the following turn.",
        listOf(
            "Fatigue 1",
            "-1 Intensity/80 PhR",
            "-3 intensities/100 PhR",
            "-5 Intensities/120 PhR",
            "-7 Intensities/140 PhR",
            "-10 Intensities/160 PhR",
            "-15 Intensities/180 PhR",
            "-20 Intensities/200 PhR",
            "-30 Intensities/220 PhR",
            "-40 Intensities/260 PhR"
        )
    )

    val controlFire = PsychicPower(
        "Control Fire",
        1,
        true,
        true,
        "This Power controls the spread and size of a fire within the Intensity range " +
                "specified by the psychic's success in activating this ability (as detailed in " +
                "the Effects Table). For example, a character may direct the course of a fire to " +
                "a settlement, ignoring the buildings he wishes to leave unharmed. Control Fire " +
                "also allows the psychic to choose the shape and color of the flames. When used " +
                "against fire with a Presence of its own, or an elemental creature, this effect " +
                "can be avoided by passing a PhR Check against the Difficulty indicated in the " +
                "Effects Table below.",
        listOf(
            "Fatigue 2",
            "Fatigue 1",
            "4 Intensities/80 PhR",
            "6 Intensities/100 PhR",
            "8 Intensities/120 PhR",
            "12 Intensities/140 PhR",
            "16 Intensities/160 PhR",
            "20 Intensities/180 PhR",
            "25 Intensities/200 PhR",
            "30 Intensities/240 PhR"
        )
    )

    val immolate = PsychicPower(
        "Immolate",
        1,
        true,
        false,
        "The psychic creates an explosion of a variable Base Damage (using the Heat " +
                "Attack Type) over a wide area. He cannot select targets inside the area, and he " +
                "might even find himself affected unless he is careful. Since it usually takes " +
                "the form of a fire ball, the attack is perfectly visible - even to those who " +
                "cannot see Psychic Matrices.",
        listOf(
            "Fatigue 4",
            "Fatigue 2",
            "Fatigue 1",
            "Base Damage 60/5-meter radius",
            "Base Damage 80/10-meter radius",
            "Base Damage 100/20-meter radius",
            "Base Damage 120/30-meter radius",
            "Base Damage 150/50-meter radius",
            "Base Damage 200/100-meter radius",
            "Base Damage 250/200-meter radius"
        )
    )

    val igneousMaintenance = PsychicPower(
        "Igneous Maintenance",
        2,
        true,
        true,
        "This Power keeps several fire Intensities burning and prevents them from " +
                "extinguishing. There are no natural means for extinguishing fire maintained by " +
                "this method - including by sand or water. In fact, fire sustained by Igneous " +
                "Maintenance does not rely on any sort of fuel to burn.",
        listOf(
            "Fatigue 4",
            "Fatigue 2",
            "Fatigue 1",
            "5 Intensities",
            "10 Intensities",
            "15 Intensities",
            "20 Intensities",
            "30 Intensities",
            "40 Intensities",
            "50 Intensities"
        )
    )

    val fireImmunity = PsychicPower(
        "Fire Immunity",
        2,
        false,
        true,
        "The psychic, or the character designated by him, gains immunity to several Heat " +
                "Intensities - including those of a Supernatural nature. When receiving a " +
                "fire-based attack, every Intensity level to which the character is immune " +
                "decreases the attack's Base Damage by 5 points and raises his Resistances by +5 " +
                "against effects.",
        listOf(
            "Fatigue 4",
            "Fatigue 2",
            "Fatigue 1",
            "5 Intensities",
            "10 Intensities",
            "15 Intensities",
            "20 Intensities",
            "30 Intensities",
            "40 Intensities",
            "50 Intensities"
        )
    )

    val igneousBarrier = PsychicPower(
        "Igneous Barrier",
        2,
        true,
        true,
        "This ability will create a fire barrier wherever the psychic needs it. " +
                "Trespassers will automatically receive a Psychic Projection attack from its " +
                "maker. It uses the Heat Attack Type and has a variable Base Damage. The maximum " +
                "length of the barrier is determined by the character's success in activating the " +
                "Power (as detailed in the Effects Table), but its shape is up to the psychic. In " +
                "the same manner as Repulsion, Igneous Barrier cannot be cast directly upon targets.",
        listOf(
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "Fatigue 1",
            "Base Damage 60/5 meters long",
            "Base Damage 80/10 meters long",
            "Base Damage 120/20 meters long",
            "Base Damage 160/30 meters long",
            "Base Damage 200/40 meters long",
            "Base Damage 240/50 meters long"
        )
    )

    val raiseTemperature = PsychicPower(
        "Raise Temperature",
        2,
        true,
        true,
        "The psychic can control the weather temperature and is able to increase it considerably in a wide radius.",
        listOf(
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "Fatigue 1",
            "+5℃/1-kilometer radius",
            "+10℃/5-kilometer radius",
            "+15℃/10-kilometer radius",
            "+20℃/25-kilometer radius",
            "+30℃/50-kilometer radius",
            "+40℃/100-kilometer radius"
        )
    )

    val consume = PsychicPower(
        "Consume",
        3,
        true,
        false,
        "This Power causes objects to burn internally, consuming their substance and " +
                "reducing them to ashes. Whether it is inorganic objects (such as swords) or " +
                "living beings, no material substance can avoid destruction by Consume. Characters " +
                "affected by this Power need to pass a PhR Check to avoid its effects. Those who " +
                "fail automatically suffer damage as indicated by the Effects Table below. " +
                "Creatures with Damage Resistance multiply this amount by 5. No AT offers " +
                "protection against this ability, not even Heat, since it operates directly from " +
                "within the victim's bodies. Objects failing their Resistance Check will be " +
                "automatically destroyed - except for those of exceptional quality, which will " +
                "only lose one level of quality.",
        listOf(
            "Fatigue 16",
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "120 PhR/Automatic Damage 80",
            "140 PhR/Automatic Damage 120",
            "160 PhR/Automatic Damage 160",
            "180 PhR/Automatic Damage 200",
            "220 PhR/Automatic Damage 250"
        )
    )

    val nova = PsychicPower(
        "Nova",
        3,
        true,
        true,
        "This Power allows a character to consume his own vital energy in order to " +
                "increase his psychic capabilities. In gaming terms, he is allowed to trade Life " +
                "Points in exchange for a bonus to Psychic Potential. Each point consumed allows " +
                "him to increase his Psychic Potential by 2 during the current turn. Note that " +
                "beings with Damage Resistance multiply lost LP times 5. The maximum amount of " +
                "Life Points a character can sacrifice per round is determined by his success " +
                "in activating this Power (as detailed in the Effects Table). However, a character " +
                "has the choice of investing fewer points than the maximum. Damage is fire based " +
                "and heals half as fast as conventional wounds.",
        listOf(
            "Fatigue 16",
            "Fatigue 12",
            "Fatigue 8",
            "10 Life Points",
            "20 Life Points",
            "30 Life Points",
            "40 Life Points",
            "60 Life Points",
            "80 Life Points",
            "120 Life Points"
        )
    )

    val majorFire = PsychicPower(
        "Major Fire",
        3,
        true,
        true,
        "An amplified version of Create Fire. A character can create flames and " +
                "temperatures of much greater strength, according to the Effects Table below.",
        listOf(
            "Fatigue 20",
            "Fatigue 16",
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "30 Intensities",
            "40 Intensities",
            "50 Intensities",
            "60 Intensities"
        )
    )

    override var allPowers = listOf(createFire, extinguishFire, controlFire, immolate, igneousMaintenance,
        fireImmunity, igneousBarrier, raiseTemperature, consume, nova, majorFire)
}