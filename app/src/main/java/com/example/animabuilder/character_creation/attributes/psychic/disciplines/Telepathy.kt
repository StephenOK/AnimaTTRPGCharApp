package com.example.animabuilder.character_creation.attributes.psychic.disciplines

import com.example.animabuilder.character_creation.attributes.psychic.Discipline
import com.example.animabuilder.character_creation.attributes.psychic.PsychicPower
import java.io.Serializable

class Telepathy: Discipline(), Serializable {
    val areaScanning = PsychicPower(
        "Area Scanning",
        1,
        true,
        true,
        "This Power detects any active mind around the psychic. It may differentiate " +
                "between simple psyches - such as that of animals - or those of a much more complex " +
                "nature. However, it cannot locate a specific mind within the radius. Resisting " +
                "this Power requires a character to make a successful PsR Check against the " +
                "target Difficulty indicated by the Effects Table below. Characters failing this " +
                "check will not bee entitled to new Resistance Checks while they remain within the " +
                "scanned area. This ability does not call for Psychic Projection; it will " +
                "automatically affect everyone within the area.",
        listOf(
            "Fatigue 2",
            "Fatigue 1",
            "100 PsR/10-meter radius",
            "120 PsR/50-meter radius",
            "140 PsR/100-meter radius",
            "160 PsR/250-meter radius",
            "180 PsR/500-meter radius",
            "200 PsR/1 kilometer radius",
            "220 PsR/10 kilometer radius",
            "260 PsR/100 kilometer radius"
        )
    )

    val mentalRestraint = PsychicPower(
        "Mental Restraint",
        1,
        true,
        true,
        "The psychic is able to impose a very basic restraint upon his target, preventing " +
                "the victim from performing a specific action. The ability will only work on " +
                "Active actions - that is, those requiring a character's conscious will. It will " +
                "not affect Passive actions or those executed by mere reaction. The affected " +
                "character may resist the effect by making a successful PsR Check against the " +
                "target Difficulty indicated by the Effects Table below. He is also granted one " +
                "additional check every time he attempts to carry out the forbidden action. In the " +
                "case of a very generic restraint, or if the prohibition limits the subject's " +
                "freedom excessively, a +20 bonus may be applied to a victim's check.",
        listOf(
            "Fatigue 4",
            "Fatigue 2",
            "80 PsR",
            "100 PsR",
            "120 PsR",
            "140 PsR",
            "160 PsR",
            "180 PsR",
            "200 PsR",
            "220 PsR"
        )
    )

    val mindReading = PsychicPower(
        "Mind Reading",
        1,
        true,
        true,
        "This Power allows the psychic to read a subject's current thoughts - although " +
                "it does not permit him to delve into the victim's memories. Resisting this Power " +
                "requires the victim to make a successful PsR Check against a target Difficulty " +
                "listed along with the intensity of the effect below. A character can make a new " +
                "check every 5 turns, as long as he is somehow aware of the fact that he is being " +
                "targeted by this Power. So long as he is reading his opponent's intentions, the " +
                "psychic can apply a +30 bonus to any actions pitted against him.",
        listOf(
            "Fatigue 2",
            "Fatigue 1",
            "100 PsR",
            "120 PsR",
            "140 PsR",
            "160 PsR",
            "180 PsR",
            "200 PsR",
            "220 PsR",
            "240 PsR"
        )
    )

    val mentalCommunication = PsychicPower(
        "Mental Communication",
        1,
        true,
        true,
        "The psychic is able to engage in long distance conversation with another " +
                "character whose approximate location is known. Unlike with other Powers, no " +
                "Psychic Projection is required for setting the target. Maximum distance allowed " +
                "for conversation is indicated by the Power's effects",
        listOf(
            "Fatigue 2",
            "Fatigue 1",
            "100 meters",
            "500 meters",
            "1 kilometer",
            "10 kilometers",
            "100 kilometers",
            "1000 kilometers",
            "5000 kilometers",
            "Any distance"
        )
    )

    val psychicShield = PsychicPower(
        "Psychic Shield",
        1,
        false,
        true,
        "Psychic Shield enhances the psychic's PsR. It may be used to enhance another " +
                "person's PsR, but such an enhancement is reduced to half the bonus indicated.",
        listOf(
            "Fatigue 2",
            "Fatigue 1",
            "+10 PsR",
            "+30 PsR",
            "+50 PsR",
            "+80 PsR",
            "+120 PsR",
            "+160 PsR",
            "+200 PsR",
            "+240 PsR"
        )
    )

    val psychicIllusion = PsychicPower(
        "Psychic Illusion",
        1,
        true,
        true,
        "This Power alters a subject's perception by introducing illusory images or " +
                "sounds into his mind. It enables psychics to become invisible to individuals, to " +
                "throw illusory rocks at them, or even make them think they are facing a dragon. " +
                "If a character decides to form illusory creatures, they will Attack and Defend " +
                "themselves using the character's own Psychic Projection, just as other illusions " +
                "will (arrows, spells, explosions, etc.). Resisting this Power requires a " +
                "character to make a successful PsR Check against the target Difficulty indicated " +
                "by the Effects Table below. Naturally, damage is unreal and the opponent will be " +
                "entitled to a new PsR Check upon being hit. If a character knows he is dealing " +
                "with an illusion, he can make a PsR Check every turn.",
        listOf(
            "Fatigue 2",
            "Fatigue 1",
            "80 PsR",
            "100 PsR",
            "120 PsR",
            "140 PsR",
            "160 PsR",
            "180 PsR",
            "200 PsR",
            "220 PsR"
        )
    )

    val mentalResearch = PsychicPower(
        "Mental Research",
        2,
        true,
        true,
        "Using this Power, the psychic delves into another person's thoughts and memories. " +
                "It is left to the GM's best judgment to decide the number of turns a psychic " +
                "needs to find the desired information, depending on how deep it is buried in the " +
                "character's memory. The psychic will have access to the victim's knowledge, but " +
                "not to supernaturally altered memories. The affected party may resist the effect " +
                "by making a successful PsR Check against the target Difficulty indicated by the " +
                "Effects Table below. He is also granted one additional check every 5 turns.",
        listOf(
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "100 PsR",
            "120 PsR",
            "140 PsR",
            "160 PsR",
            "180 PsR",
            "200 PsR",
            "240 PsR"
        )
    )

    val psychicAssault = PsychicPower(
        "Psychic Assault",
        2,
        true,
        false,
        "The psychic casts an attack upon a subject's mind, weakening his mental " +
                "resistance. The victim suffers a penalty to all PsR Checks equal to his Failure " +
                "against the check to resist this Power. Weakened Resistance is recovered at a " +
                "rate of 5 points per hour.",
        listOf(
            "Fatigue 8",
            "Fatigue 4",
            "Fatigue 2",
            "120 PsR",
            "140 PsR",
            "160 PsR",
            "180 PsR",
            "200 PsR",
            "220 PsR",
            "260 PsR"
        )
    )

    val psychicConnection = PsychicPower(
        "Psychic Connection",
        2,
        true,
        true,
        "This Power connects the psychic's mind to the mind of another willing person, " +
                "allowing them both to act upon each other's physical bodies. Characters " +
                "participating in the switch retain their knowledge and skills, but they are " +
                "subject to the Physical Characteristics of the host individual, which means their " +
                "Base Abilities must be recalculated to account for the different advantages and " +
                "disadvantages of the host's body. Since the soul does not transmigrate, " +
                "spellcasters cannot cast spells upon introducing their mind into another physical " +
                "form. This is a voluntary capability; characters cannot be forced to give up " +
                "control of their bodies or to control another body from a distance. Characters " +
                "in control of another body temporarily lose control of their own. If one " +
                "participant dies while the connection is active, the Power is cancelled and the " +
                "surviving participant returns to his normal state. The maximum distance between " +
                "the bodies is determined by the success level reached using the Effects Table below.",
        listOf(
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "100 meter radius",
            "500 meter radius",
            "1 kilometer radius",
            "10 kilometer radius",
            "100 kilometer radius",
            "1000 kilometer radius",
            "Any distance"
        )
    )

    val alterMemory = PsychicPower(
        "Alter Memory",
        2,
        true,
        false,
        "This Power permits a psychic to edit a subject's memories, eliminating them " +
                "completely or creating new ones. The exact element to be deleted or created " +
                "needs to be determined. Each point of difference between the victim's roll and " +
                "the required PsR Check represents one hour of memories that a psychic can modify. " +
                "Even though no maintenance is requires, the victim is entitled to a new PsR Check " +
                "against the original target Difficulty if he sees or does anything that can " +
                "prompt a deeply rooted memory to surface.",
        listOf(
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "100 PsR",
            "120 PsR",
            "140 PsR",
            "160 PsR",
            "180 PsR",
            "200 PsR"
        )
    )

    val astralShape = PsychicPower(
        "Astral Shape",
        2,
        true,
        true,
        "The psychic can abandon his physical shape and project his mind in space. For " +
                "as long as he remains in this state, he is absolutely intangible toward anything " +
                "non-Energy based, and he is invisible to those without the ability to see " +
                "Psychic Matrices. He can only be hurt by attacks that affect immaterial beings " +
                "or damage their Resistances. If the psychic is damaged in this state, the " +
                "damage is transposed to his physical body and causes the Astral Shape to be " +
                "canceled. While in an Astral Shape, the psychic has a Flight Value equivalent to " +
                "his Willpower, but he can only use mental abilities. If his real body should face " +
                "death, the psychic is trapped in the Astral Shape until the time of its end, " +
                "which would also beb the time of his own utter destruction.",
        listOf(
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "10 kilometer radius",
            "100 kilometer radius",
            "500 kilometer radius",
            "1000 kilometer radius",
            "5000 kilometer radius",
            "Any distance"
        )
    )

    val psychicTracking = PsychicPower(
        "Psychic Tracking",
        2,
        true,
        true,
        "This Power allows the psychic to pinpoint the location of a specific subject's " +
                "mind within range - as determined by the psychic's success in activating this " +
                "Power. The psychic should know the matrix of the subject he is seeking, but he " +
                "may also be after only certain mental patterns. Once he has found his subject, " +
                "the psychic may maintain this Power so as to keep track of the subject's location " +
                "at all times. Resisting this Power requires the victim to make a successful PsR " +
                "Check against the target value indicated by the Effects Table below. The " +
                "affected character is allowed a new check every 5 turns if he is aware that he " +
                "is being targeted by this ability. No Psychic Projection is required. Psychic " +
                "Tracking works automatically whenever the subject is inside the Power's area of " +
                "action.",
        listOf(
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "10 kilometer radius/140 PsR",
            "100 kilometer radius/160 PsR",
            "500 kilometer radius/180 PsR",
            "1000 kilometer radius/200 PsR",
            "5000 kilometer radius/220 PsR",
            "Any distance/ 260 PsR"
        )
    )

    val mindControl = PsychicPower(
        "Mind Control",
        3,
        true,
        true,
        "The psychic obtains full control of the subject who fails the required PsR. The " +
                "victim is entitled to a new check every day, as well as every time he receives " +
                "an order completely against his normal behavior. He is able to apply a +20 to his " +
                "PsR if he receives a life-endangering command or any order that would subject him " +
                "to extreme actions.",
        listOf(
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "100 PsR",
            "120 PsR",
            "140 PsR",
            "160 PsR",
            "180 PsR",
            "220 PsR"
        )
    )

    val psychicDeath = PsychicPower(
        "Psychic Death",
        3,
        true,
        false,
        "This Power attacks a victim's mind and produces total devastation from within. " +
                "The character loses one point of Intelligence and Willpower for every 10 points " +
                "by which he fails the required Resistance. Characters recover lost points at a " +
                "rate of one per day. However, if either of the two Characteristics should reach " +
                "0, the character has suffered catastrophic damage to mind and becomes a hollow " +
                "shell, incapable of independent actions. Mindless bodies do not die. However, " +
                "they can be controlled through Psychic Connection.",
        listOf(
            "Fatigue 16",
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "140 PsR",
            "160 PsR",
            "180 PsR",
            "220 PsR",
            "240 PsR"
        )
    )

    val area = PsychicPower(
        "Area",
        3,
        true,
        true,
        "Maintaining Area enables the psychic to use any other Telepathic Power on all " +
                "subjects within the radius - as determined by the character's success in " +
                "activating the Power. Specific targets may be designated as long as the psychic " +
                "is aware of their presence within the radius. For instance, if a character " +
                "utilizes Psychic Assault while this Power is maintained on a Very Difficult " +
                "level, all individuals designated by the psychic within a 10 meter area will be attacked.",
        listOf(
            "Fatigue 16",
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 4",
            "10 meters",
            "100 meters",
            "1 kilometer",
            "10 kilometers",
            "100 kilometers",
            "500 kilometers"
        )
    )

    override var allPowers = listOf(areaScanning, mentalRestraint, mindReading, mentalCommunication,
        psychicShield, psychicIllusion, mentalResearch, psychicAssault, psychicConnection,
        alterMemory, astralShape, psychicTracking, mindControl, psychicDeath, area)
}