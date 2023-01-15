package com.example.animabuilder.character_creation.attributes.psychic.disciplines

import com.example.animabuilder.character_creation.attributes.psychic.Discipline
import com.example.animabuilder.character_creation.attributes.psychic.PsychicPower
import java.io.Serializable

class Sentience: Discipline(), Serializable {
    val senseFeelings = PsychicPower(
        "Sense Feelings",
        1,
        true,
        true,
        "The psychic can sense an individual's feelings at a particular moment. Resisting " +
                "this Power requires the target to pass a PsR Check with a variable target that " +
                "depends upon the psychic's success at activating the Power (as detailed in the " +
                "Effects Table). The victim is entitled to a new check every 5 turns only if he " +
                "suspects that he is a victim of this Power.",
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

    val intensifyFeelings = PsychicPower(
        "Intensify Feelings",
        1,
        true,
        true,
        "This Power intensifies an individual's dominant feeling or mood at a specific " +
                "time. Bear in mind that the Power does not accentuate previously non-existent " +
                "feelings. One might, for instance, drive an angry person mad with wrath, or have " +
                "a sad person sink fast into depression. Characters wishing to resist this Power " +
                "must pass a PsR Check (as detailed in the Effects Table). They are entitled to " +
                "a new check every 5 turns only if they suspect that they have been subject to " +
                "Intensify Feelings.",
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

    val detectFeelings = PsychicPower(
        "Detect Feelings",
        1,
        true,
        true,
        "This Power detects a specific feeling in any subject within its area of effect. " +
                "For instance, if the psychic is seeking to detect anger, the search would turn " +
                "up angry individuals within the area of influence. The affected party may resist " +
                "by passing a PsR Check against a target difficulty that varies with the psychic's " +
                "success in activating the Power (as detailed in the Effects Table). All " +
                "individuals within the area will be affected equally without the need of " +
                "Psychic Projection.",
        listOf(
            "Fatigue 2",
            "Fatigue 1",
            "80 PsR/10-meter radius",
            "100 PsR/50-meter radius",
            "120 PsR/100-meter radius",
            "140 PsR/250-meter radius",
            "160 PsR/500-meter radius",
            "180 PsR/1-kilometer radius",
            "200 PsR/10-kilometer radius",
            "220 PsR/100-kilometer radius"
        )
    )

    val connectSenses = PsychicPower(
        "Connect Senses",
        1,
        true,
        true,
        "This Power links the psychic's senses with those of another individual, and " +
                "vice versa, granting them both access to what each of them hears and sees. It " +
                "is the exclusive prerogative of the psychic to deny access to his own senses. " +
                "Characters who pass a PsR Check with a variable target difficulty (as detailed " +
                "in the Effects Table) successfully resists this ability and deny the psychic " +
                "access to their senses. If a character affected by Control Senses suspects that " +
                "he is under the influence of the Power, he can make a new PsR Check every 5 " +
                "turns. Maximum distance between the two connected bodies is determined by the " +
                "psychic's success in activating the Power.",
        listOf(
            "Fatigue 2",
            "Fatigue 1",
            "60 PsR/ 10-meter radius",
            "80 PsR/100-meter radius",
            "100 PsR/500-meter radius",
            "120 PsR/1-kilometer radius",
            "140 PsR/10-kilometer radius",
            "160 PsR/100-kilometer radius",
            "180 PsR/1000-kilometer radius",
            "200 PsR/Any distance"
        )
    )

    val projectSenses = PsychicPower(
        "Project Senses",
        2,
        true,
        true,
        "Psychics are capable of projecting their senses at a distance. Once they do, " +
                "they can use their Secondary Perceptive Abilities as if they were really " +
                "standing in that place. The psychic's presence will only be detected by those " +
                "individuals able to sense Psychic Matrices. The Power cannot get through against " +
                "energy barriers and magically protected areas.",
        listOf(
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "1-kilometer radius",
            "10-kilometer radius",
            "50-kilometer radius",
            "100-kilometer radius",
            "1000-kilometer radius",
            "Any distance"
        )
    )

    val eliminateSenses = PsychicPower(
        "Eliminate Senses",
        2,
        true,
        true,
        "The psychic is able to temporarily eliminate one of his victim's five senses. " +
                "One extra sense may be eliminated for every 20 points by which the victim fails " +
                "his PsR Check. Victims will be allowed a new check every 5 turns, but they will " +
                "only recover one sense at a time.",
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
            "220 PsR"
        )
    )

    val createFeelings = PsychicPower(
        "Create Feelings",
        2,
        true,
        true,
        "This Power instills new feelings in an individual. For instance, it may cause " +
                "two people who despise each other to fall in love, or two lovers to be repelled " +
                "by one another. This Power enables psychics to subject their opponents to any " +
                "psychological state they desire - such as fear, sorrow, etc. Those wishing to " +
                "resist these effects need to pass a PsR with a variable target that depends upon " +
                "the psychic's success at activating this Power (as detailed in the Effects " +
                "Table). If the nature of the feelings created is radically contrary to the " +
                "victim's current emotional state, the target receives a +20 bonus to his PsR " +
                "Check. Victims suspicious of the unnatural origin of their feelings can make new " +
                "checks every 5 turns.",
        listOf(
            "Fatigue 8",
            "Fatigue 4",
            "Fatigue 2",
            "80 PsR",
            "100 PsR",
            "120 PsR",
            "140 PsR",
            "160 PsR",
            "180 PsR",
            "200 PsR"
        )
    )

    val infuseFeelings = PsychicPower(
        "Infuse Feelings",
        2,
        true,
        true,
        "This Power will infuse an object or place with a strong feeling that " +
                "automatically affects all individuals in contact with it. For instance an " +
                "anger-infused sword would incite immense rage in anyone who touched it. If a " +
                "place is infused, the total area that a character can affect with Infuse Feelings " +
                "depends upon his success in activating this Power (as detailed in the Effects " +
                "Table). Characters wishing to resist will need to pass a PsR Check with the " +
                "appropriate target difficulty. Victims who become suspicious that their feelings " +
                "are being manipulated by unnatural means can make new rolls every 5 turns. The " +
                "psychic does not need to use his Psychic Projection. All it takes is his presence " +
                "in the specific place or physical contact with the object. Effects vanish " +
                "immediately outside the area or when contact with the object is interrupted.",
        listOf(
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "Fatigue 2",
            "100 PsR/5-meter radius",
            "120 PsR/10-meter radius",
            "140 PsR/25-meter radius",
            "160 PsR/50-meter radius",
            "180 PsR/100-meter radius",
            "220 PsR/500-meter radius"
        )
    )

    val destroyFeelings = PsychicPower(
        "Destroy Feelings",
        3,
        true,
        false,
        "The psychic is able to eliminate unwanted feelings in any given individual. In " +
                "order to use this Power, it is necessary to specify the exact feeling and " +
                "identify whether it is of a general nature or associated with a particular " +
                "element. Characters wishing to resist this Power need to pass a PsR Check at the " +
                "required Difficulty. Deeply rooted emotions may give the resisting character a " +
                "+20 bonus to his check. The psychic can erase one extra feeling for every 20 " +
                "points by which the victim fails the PsR. If the victim fails the check by more " +
                "than 80 points, the psychic could erase all feelings altogether from the victim's " +
                "mind, turning him into a vegetable.",
        listOf(
            "Fatigue 16",
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 6",
            "Fatigue 4",
            "120 PsR",
            "140 PsR",
            "160 PsR",
            "180 PsR",
            "200 PsR"
        )
    )

    val area = PsychicPower(
        "Area",
        3,
        true,
        true,
        "This Power works like the Area Power in the Telepathy Discipline, except that " +
                "it affects only Sentience Discipline Powers. In this way, all Sentience Powers " +
                "used while Area is active will affect any individual within the indicated radius.",
        listOf(
            "Fatigue 16",
            "Fatigue 12",
            "Fatigue 8",
            "Fatigue 4",
            "10-meter radius",
            "100-meter radius",
            "1-kilometer radius",
            "10-kilometer radius",
            "100-kilometer radius",
            "500-kilometer radius"
        )
    )

    override var allPowers = listOf(senseFeelings, intensifyFeelings, detectFeelings, connectSenses,
        projectSenses, eliminateSenses, createFeelings, infuseFeelings, destroyFeelings, area)
}