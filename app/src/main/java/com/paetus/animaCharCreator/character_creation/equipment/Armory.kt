package com.paetus.animaCharCreator.character_creation.equipment

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.equipment.armor.ArmorInstances
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.Weapon
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_instances.Axes
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_instances.Cords
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_instances.Maces
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_instances.Mixed
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_instances.Poles
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_instances.Projectiles
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_instances.Shields
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_instances.ShortArms
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_instances.Swords
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_instances.Thrown
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_instances.TwoHanded
import com.paetus.animaCharCreator.enumerations.AttackType
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponAbility
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponType

class Armory{
    //create data for each weapon
    val shortArms = ShortArms()
    val axes = Axes()
    val maces = Maces()
    val swords = Swords()
    val twoHanded = TwoHanded()
    val poles = Poles()
    val cords = Cords()
    val mixed = Mixed()
    val shields = Shields()
    val projectiles = Projectiles()
    val thrown = Thrown()

    //create data on the unarmed weapon
    val unarmed = Weapon(
        saveName = "unarmed",
        name = R.string.unarmed,
        damage = 10,
        speed = 20,
        oneHandStr = 0, twoHandStr = null,
        primaryType = AttackType.Impact, secondaryType = null, type = WeaponType.Unarmed,
        fortitude = null, breakage = null, presence = null,
        ability = listOf(WeaponAbility.Precision), ownStrength = null,
        description = R.string.unarmedDesc
    )

    //collect all weapon data
    val allWeapons = listOf(unarmed) + shortArms.shortArms + axes.axes + maces.maces + swords.swords + twoHanded.twoHanded +
            poles.poles + cords.cords + mixed.mixed + shields.shields + projectiles.projectiles + thrown.thrown

    //create all archetype modules
    val improvised = listOf(
        shortArms.brokenBottle,
        twoHanded.chair,
        shortArms.kitchenKnife,
        maces.hammer,
        axes.hoe,
        maces.metalBar,
        shortArms.pick,
        shortArms.sickle,
        maces.torch
    )

    val barbarianWeapons = listOf(
        mixed.twoHandAxe,
        axes.battleAxe,
        twoHanded.twoHandSword,
        mixed.bastardSword,
        mixed.heavyBattleMace
    )

    val ninjaWeapons = listOf(
        swords.katana,
        shortArms.tanto,
        shortArms.claws,
        shortArms.shuriken,
        mixed.kusariGama
    )

    val duelWeapons = listOf(
        swords.rapier,
        mixed.foil,
        shortArms.parryDagger,
        shortArms.dagger,
        swords.saber,
        swords.longSword
    )

    val pirateWeapons = listOf(
        poles.harpoon,
        cords.gladNet,
        shortArms.hook,
        swords.saber,
        axes.handAxe
    )

    val nomadWeapons = listOf(
        shortArms.dagger,
        thrown.chakram,
        projectiles.longBow,
        swords.scimitar,
        poles.lance
    )

    val huntWeapons = listOf(
        poles.javelin,
        projectiles.shortBow,
        shortArms.shortSword,
        poles.lance,
        thrown.bolas
    )

    val knightWeapons = listOf(
        swords.longSword,
        poles.cavLance,
        maces.mace,
        mixed.bastardSword,
        shields.shield
    )

    val gladiatorWeapons = listOf(
        shortArms.shortSword,
        cords.gladNet,
        shields.buckler,
        poles.trident,
        cords.whip
    )

    val assassinWeapons = listOf(
        shortArms.shortSword,
        projectiles.miniCrossbow,
        maces.club,
        projectiles.blowgun,
        shortArms.stiletto
    )

    val soldierWeapons = listOf(
        projectiles.crossbow,
        swords.longSword,
        mixed.halberd,
        poles.lance,
        shields.shield
    )

    val indigenousWeapons = listOf(
        poles.javelin,
        poles.lance,
        shields.fullShield,
        projectiles.shortBow,
        projectiles.blowgun
    )

    val banditWeapons = listOf(
        shortArms.dagger,
        projectiles.crossbow,
        shortArms.shortSword,
        maces.mace,
        maces.club
    )

    //create map of all available archetypes
    val allArchetypes = mapOf(
        "barbarian" to barbarianWeapons,
        "ninja" to ninjaWeapons,
        "duel" to duelWeapons,
        "pirate" to pirateWeapons,
        "nomad" to nomadWeapons,
        "hunter" to huntWeapons,
        "knight" to knightWeapons,
        "gladiator" to gladiatorWeapons,
        "assassin" to assassinWeapons,
        "soldier" to soldierWeapons,
        "indigenous" to indigenousWeapons,
        "bandit" to banditWeapons,
        "improvised" to improvised,

        "short" to shortArms.shortArms + mixed.shortAdditions,
        "axe" to axes.axes + mixed.axeAdditions,
        "mace" to maces.maces + mixed.maceAdditions,
        "sword" to swords.swords + mixed.swordAdditions,
        "twoHanded" to twoHanded.twoHanded + mixed.twoHandedAdditions,
        "pole" to poles.poles + mixed.poleAdditions,
        "cord" to cords.cords + mixed.cordAdditions,
        "shield" to shields.shields,
        "projectile" to projectiles.projectiles,
        "thrown" to thrown.thrown
    )

    val armor = ArmorInstances()
}