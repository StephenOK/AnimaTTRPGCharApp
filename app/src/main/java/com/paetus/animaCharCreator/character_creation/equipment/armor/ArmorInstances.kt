package com.paetus.animaCharCreator.character_creation.equipment.armor

/**
 * Record of all armors available for the character to acquire.
 */
class ArmorInstances{
    val paddedArmor = Armor(
        "Padded",

        0,
        -5,
        0,
        10,
        25,
        ArmorLocation.Shirt,
        false,

        1,
        1,
        1,
        1,
        2,
        2,
        0,

        "This is a simple armor made of various layers of quilted cloth to absorb the " +
                "force of blows."
    )

    val leatherArmor = Armor(
        "Leather",

        0,
        0,
        0,
        12,
        25,
        ArmorLocation.Shirt,
        false,

        1,
        0,
        2,
        1,
        2,
        1,
        0,

        "This is armor made of very thick leather treated with oils to give it better " +
                "resistance."
    )

    val armoredLongcoat = Armor(
        "Armored Longcoat",

        0,
        -5,
        0,
        10,
        25,
        ArmorLocation.FullPlate,
        false,

        1,
        0,
        2,
        1,
        2,
        2,
        0,

        "This is armor made of treated leather but with quilted layers to give it better " +
                "consistency."
    )

    val furArmor = Armor(
        "Fur",

        10,
        -10,
        0,
        10,
        25,
        ArmorLocation.Shirt,
        false,

        2,
        1,
        2,
        1,
        2,
        2,
        0,

        "Protection made of layers of animal skins (fur). It is heavy and difficult to use."
    )

    val completeLeather = Armor(
        "Complete Leather",

        10,
        0,
        1,
        12,
        25,
        ArmorLocation.FullPlate,
        false,

        1,
        0,
        2,
        1,
        2,
        1,
        0,

        "A version of leather armor that also protects the legs. The joints are made " +
                "of cloth."
    )

    val hardenedLeather = Armor(
        "Hardened Leather",

        20,
        -10,
        0,
        13,
        25,
        ArmorLocation.Breastplate,
        true,

        2,
        2,
        2,
        2,
        2,
        2,
        0,

        "This is an armor made of leather treated with oils and unguents until it " +
                "becomes completely solid."
    )

    val studdedLeather = Armor(
        "Studded Leather",

        25,
        -10,
        1,
        14,
        25,
        ArmorLocation.Breastplate,
        true,

        3,
        1,
        2,
        2,
        1,
        2,
        0,

        "This is a soft leather armor studded with small metal fragments. It is very " +
                "useful, but difficult to wear."
    )

    val chainmail = Armor(
        "Chainmail",

        30,
        -15,
        1,
        15,
        30,
        ArmorLocation.FullPlate,
        false,

        4,
        2,
        1,
        2,
        0,
        1,
        0,

        "This is possibly the most advanced armor of its era. It is composed of fine " +
                "metal rings interwoven or sewn to each other; it offers enormous protection as " +
                "well as great mobility."
    )

    val breastplate = Armor(
        "Breastplate",

        40,
        -15,
        1,
        16,
        30,
        ArmorLocation.Breastplate,
        true,

        4,
        5,
        4,
        1,
        0,
        1,
        0,

        "This metal armor covers the chest and back."
    )

    val partialPlate = Armor(
        "Partial Plate",

        50,
        -20,
        2,
        15,
        30,
        ArmorLocation.Complete,
        true,

        4,
        3,
        2,
        3,
        2,
        2,
        0,

        "Armor made of various pieces of metal that cover the most vital parts of the " +
                "body. Normally they are sewn onto a suit of leather or fur, though sometimes " +
                "this is done with joints."
    )

    val byrnie = Armor(
        "Byrnie",

        60,
        -20,
        2,
        15,
        30,
        ArmorLocation.Shirt,
        false,

        4,
        3,
        1,
        2,
        0,
        1,
        0,

        "This is a jacket made of metal rings interlaced with each other and sewn onto a " +
                "layer of stiffened leather or cloth to hold it together."
    )

    val halfPlate = Armor(
        "Half Plate",

        70,
        -20,
        3,
        16,
        35,
        ArmorLocation.Complete,
        true,

        4,
        4,
        4,
        2,
        0,
        1,
        1,

        "This is a complete metal armor made of dozens of pieces perfectly forged and " +
                "prepared to fit into each other. It is lighter than Full Plate, since it " +
                "sacrifices protection for mobility."
    )

    val scaleMail = Armor(
        "Scale Mail",

        80,
        -25,
        3,
        17,
        35,
        ArmorLocation.Complete,
        true,

        4,
        4,
        4,
        3,
        0,
        3,
        1,

        "This is a leather armor covered in metal scales like those of a fish."
    )

    val lightPlate = Armor(
        "Light Plate",

        90,
        -35,
        4,
        17,
        40,
        ArmorLocation.Complete,
        true,

        5,
        4,
        5,
        3,
        0,
        3,
        1,

        "This is heavy armor composed of a combination of metal plates over articulated " +
                "joints. This has fewer pieces than Full Heavy Plate, but it also covers the " +
                "entire body."
    )

    val fullPlate = Armor(
        "Full Plate",

        100,
        -50,
        4,
        18,
        45,
        ArmorLocation.Complete,
        true,

        5,
        5,
        5,
        4,
        0,
        4,
        2,

        "These are true works of metallic art that are usually made to order. The " +
                "pieces making it up cover the entire body utilizing joints of leather which are " +
                "in turn covered by metal plates. Unfortunately, its weight is excessive and " +
                "permits little mobility."
    )

    val fullHeavyPlate = Armor(
        "Full Heavy Plate",

        120,
        -60,
        5,
        19,
        45,
        ArmorLocation.Complete,
        true,

        6,
        6,
        6,
        4,
        0,
        4,
        2,

        "A version of Full Plate in which the metal plates have two or three times the " +
                "thickness. Although it requires greater strength and skill to use, the " +
                "protection it offers is much better."
    )

    val fullFieldPlate = Armor(
        "Full Field Plate",

        150,
        -70,
        6,
        20,
        50,
        ArmorLocation.Complete,
        true,

        7,
        7,
        7,
        4,
        0,
        4,
        2,

        "This is the heaviest and most complete of all the different types of armor. " +
                "Even the joints are covered over with thick layers of metal that, although they " +
                "make movement difficult, protect every bit of the anatomy."
    )

    val armors = listOf(paddedArmor, leatherArmor, armoredLongcoat, furArmor, completeLeather,
        hardenedLeather, studdedLeather, chainmail, breastplate, partialPlate, byrnie, halfPlate,
        scaleMail, lightPlate, fullPlate, fullHeavyPlate, fullFieldPlate)

    val circlet = Armor(
        "Circlet",

        0,
        0,
        0,
        8,
        15,
        ArmorLocation.Head,
        true,

        2,
        2,
        1,
        1,
        1,
        1,
        0,

        "This is a metal ring that goes around the head of the character, protecting it " +
                "from lateral blows or those attacks aimed at the forehead."
    )

    val foreheadPlate = Armor(
        "Forehead Plate",

        0,
        0,
        0,
        12,
        15,
        ArmorLocation.Head,
        true,

        3,
        3,
        3,
        1,
        1,
        2,
        0,

        "This is a metal plate to block blows at the forehead."
    )

    val casque = Armor(
        "Casque",

        0,
        0,
        0,
        12,
        15,
        ArmorLocation.Head,
        true,

        4,
        4,
        3,
        2,
        0,
        3,
        0,

        "This is a metallic helmet that protects the upper part of the head."
    )

    val leatherHood = Armor(
        "Leather Hood",

        0,
        0,
        0,
        10,
        15,
        ArmorLocation.Head,
        false,

        1,
        0,
        2,
        1,
        3,
        1,
        0,

        "This is a hood of oil-treated leather that covers the whole head except the face."
    )

    val mailCoif = Armor(
        "Mail Coif",

        0,
        -10,
        0,
        13,
        20,
        ArmorLocation.Head,
        false,

        4,
        2,
        1,
        2,
        0,
        1,
        0,

        "This helmet is identical to the leather hood, but it is made of rings or woven " +
                "metal strands. The inside is lined with cloth to avoid damage to the wearer's skin."
    )

    val openHelm = Armor(
        "Open Helm",

        5,
        -20,
        0,
        16,
        25,
        ArmorLocation.Head,
        true,

        5,
        4,
        5,
        3,
        0,
        3,
        1,

        "This is a full helm covering the entire head, except for the face."
    )

    val greatHelm = Armor(
        "Great Helm",

        10,
        -30,
        0,
        16,
        25,
        ArmorLocation.Head,
        true,

        5,
        5,
        5,
        4,
        0,
        4,
        2,

        "This is a helm that covers the entire head. Usually, it has a visor that can " +
                "be raised for better vision or lowered to protect the face."
    )

    val helmets = listOf(circlet, foreheadPlate, casque, leatherHood, mailCoif, openHelm, greatHelm)
}