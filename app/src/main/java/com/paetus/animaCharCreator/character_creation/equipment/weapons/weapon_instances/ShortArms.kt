package com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.AttackType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.Weapon
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponAbility
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.ProjectileWeapon
import java.io.Serializable

/**
 * List of short weapons the character may take.
 */
class ShortArms: Serializable {
    private val cestus = Weapon(
        saveName = "cestus",
        name = R.string.cestus,
        damage = 25,
        speed = 10,
        oneHandStr = 3, twoHandStr = null,
        primaryType = AttackType.Thrust, secondaryType = AttackType.Cut, type = WeaponType.Short,
        fortitude = 11, breakage = -2, presence = 15,
        ability = null, ownStrength = null,
        description = R.string.cestusDesc
    )

    val dagger = ProjectileWeapon(
        saveName = "dagger",
        name = R.string.dagger,
        damage = 30,
        speed = 20,
        oneHandStr = 3, twoHandStr = null,
        primaryType = AttackType.Thrust, secondaryType = AttackType.Cut, type = WeaponType.Short,
        fortitude = 10, breakage = -2, presence = 15,
        reloadOrRate = 50, range = 20,
        ability = listOf(WeaponAbility.Throwable, WeaponAbility.Precision), ownStrength = null,
        description = R.string.daggerDesc
    )

    val hook = Weapon(
        saveName = "hook",
        name = R.string.hook,
        damage = 30,
        speed = 10,
        oneHandStr = 3, twoHandStr = null,
        primaryType = AttackType.Thrust, secondaryType = null, type = WeaponType.Short,
        fortitude = 11, breakage = -2, presence = 15,
        ability = null, ownStrength = null,
        description = R.string.hookDesc
    )

    val parryDagger = ProjectileWeapon(
        saveName = "parryDagger",
        name = R.string.parryDagger,
        damage = 30,
        speed = 15,
        oneHandStr = 3,
        twoHandStr = null,
        primaryType = AttackType.Thrust,
        secondaryType = AttackType.Cut,
        type = WeaponType.Short,
        fortitude = 12,
        breakage = 0,
        presence = 20,
        reloadOrRate = 50,
        range = 15,
        ability = listOf(WeaponAbility.WeaponTrap, WeaponAbility.Throwable, WeaponAbility.Precision),
        ownStrength = null,
        description = R.string.parryDaggerDesc
    )

    val shortSword = Weapon(
        saveName = "shortSword",
        name = R.string.shortSword,
        damage = 40,
        speed = 15,
        oneHandStr = 4, twoHandStr = null,
        primaryType = AttackType.Thrust, secondaryType = AttackType.Cut, type = WeaponType.Short,
        fortitude = 12, breakage = 1, presence = 20,
        ability = listOf(WeaponAbility.Precision), ownStrength = null,
        description = R.string.shortSwordDesc
    )

    val stiletto = ProjectileWeapon(
        saveName = "stiletto",
        name = R.string.stiletto,
        damage = 25,
        speed = 20,
        oneHandStr = 3, twoHandStr = null,
        primaryType = AttackType.Thrust, secondaryType = null, type = WeaponType.Short,
        fortitude = 8, breakage = -3, presence = 15,
        reloadOrRate = 30, range = 30,
        ability = listOf(WeaponAbility.Throwable, WeaponAbility.Precision), ownStrength = null,
        description = R.string.stilettoDesc
    )

    private val boomerang = ProjectileWeapon(
        saveName = "boomerang",
        name = R.string.boomerang,
        damage = 30,
        speed = 10,
        oneHandStr = 4, twoHandStr = null,
        primaryType = AttackType.Impact, secondaryType = AttackType.Cut, type = WeaponType.Short,
        fortitude = 10, breakage = 0, presence = 15,
        reloadOrRate = 60, range = 20,
        ability = listOf(WeaponAbility.Throwable, WeaponAbility.Special), ownStrength = null,
        description = R.string.boomerangDesc
    )

    val claws = Weapon(
        saveName = "claws",
        name = R.string.claws,
        damage = 30,
        speed = 15,
        oneHandStr = 4, twoHandStr = null,
        primaryType = AttackType.Cut, secondaryType = AttackType.Thrust, type = WeaponType.Short,
        fortitude = 12, breakage = 2, presence = 15,
        ability = null, ownStrength = null,
        description = R.string.clawsDesc
    )

    private val katar = Weapon(
        saveName = "katar",
        name = R.string.katar,
        damage = 40,
        speed = 10,
        oneHandStr = 8, twoHandStr = null,
        primaryType = AttackType.Thrust, secondaryType = AttackType.Cut, type = WeaponType.Short,
        fortitude = 13, breakage = 3, presence = 25,
        ability = listOf(WeaponAbility.Complex, WeaponAbility.Special), ownStrength = null,
        description = R.string.katarDesc
    )

    private val raven = Weapon(
        saveName = "raven",
        name = R.string.raven,
        damage = 35,
        speed = 10,
        oneHandStr = 4,
        twoHandStr = null,
        primaryType = AttackType.Impact,
        secondaryType = AttackType.Cut,
        type = WeaponType.Short,
        fortitude = 11,
        breakage = 2,
        presence = 25,
        ability = listOf(WeaponAbility.Complex, WeaponAbility.Precision, WeaponAbility.Special),
        ownStrength = null,
        description = R.string.ravenDesc
    )

    private val sai = Weapon(
        saveName = "sai",
        name = R.string.sai,
        damage = 35,
        speed = 15,
        oneHandStr = 4, twoHandStr = null,
        primaryType = AttackType.Thrust, secondaryType = AttackType.Cut, type = WeaponType.Short,
        fortitude = 12, breakage = 0, presence = 25,
        ability = listOf(WeaponAbility.WeaponTrap, WeaponAbility.Precision), ownStrength = null,
        description = R.string.saiDesc
    )

    private val shuko = Weapon(
        saveName = "shuko",
        name = R.string.shuko,
        damage = 20,
        speed = 10,
        oneHandStr = 4, twoHandStr = null,
        primaryType = AttackType.Thrust, secondaryType = null, type = WeaponType.Short,
        fortitude = 9, breakage = -2, presence = 25,
        ability = listOf(WeaponAbility.Special), ownStrength = null,
        description = R.string.shukoDesc
    )

    val shuriken = ProjectileWeapon(
        saveName = "shuriken",
        name = R.string.shuriken,
        damage = 25,
        speed = 20,
        oneHandStr = 4, twoHandStr = null,
        primaryType = AttackType.Cut, secondaryType = AttackType.Thrust, type = WeaponType.Short,
        fortitude = 10, breakage = 1, presence = 20,
        reloadOrRate = 30, range = 20,
        ability = listOf(WeaponAbility.Throwable), ownStrength = null,
        description = R.string.shurikenDesc
    )

    val tanto = Weapon(
        saveName = "tanto",
        name = R.string.tanto,
        damage = 40,
        speed = 20,
        oneHandStr = 3, twoHandStr = null,
        primaryType = AttackType.Cut, secondaryType = null, type = WeaponType.Short,
        fortitude = 9, breakage = 1, presence = 40,
        ability = listOf(WeaponAbility.Precision), ownStrength = null,
        description = R.string.tantoDesc
    )

    private val tessen = ProjectileWeapon(
        saveName = "tessen",
        name = R.string.tessen,
        damage = 30,
        speed = 20,
        oneHandStr = 4, twoHandStr = null,
        primaryType = AttackType.Cut, secondaryType = AttackType.Impact, type = WeaponType.Short,
        fortitude = 8, breakage = 0, presence = 25,
        reloadOrRate = 40, range = 20,
        ability = listOf(WeaponAbility.Precision, WeaponAbility.Throwable), ownStrength = null,
        description = R.string.tessenDesc
    )

    private val tonfa = Weapon(
        saveName = "tonfa",
        name = R.string.tonfa,
        damage = 30,
        speed = 20,
        oneHandStr = 4, twoHandStr = null,
        primaryType = AttackType.Impact, secondaryType = null, type = WeaponType.Short,
        fortitude = 13, breakage = 0, presence = 25,
        ability = listOf(WeaponAbility.Precision), ownStrength = null,
        description = R.string.tonfaDesc
    )

    val brokenBottle = Weapon(
        saveName = "brokenBottle",
        name = R.string.brokenBottle,
        damage = 15,
        speed = 10,
        oneHandStr = 3, twoHandStr = null,
        primaryType = AttackType.Cut, secondaryType = AttackType.Impact, type = WeaponType.Short,
        fortitude = 5, breakage = -3, presence = 15,
        ability = null, ownStrength = null,
        description = R.string.emptyItem
    )

    val kitchenKnife = Weapon(
        saveName = "kitchenKnife",
        name = R.string.kitchenKnife,
        damage = 25,
        speed = 10,
        oneHandStr = 4, twoHandStr = null,
        primaryType = AttackType.Cut, secondaryType = null, type = WeaponType.Short,
        fortitude = 9, breakage = -1, presence = 10,
        ability = null, ownStrength = null,
        description = R.string.emptyItem
    )

    val pick = Weapon(
        saveName = "pick",
        name = R.string.pick,
        damage = 40,
        speed = -20,
        oneHandStr = 5, twoHandStr = null,
        primaryType = AttackType.Thrust, secondaryType = null, type = WeaponType.Short,
        fortitude = 10, breakage = 3, presence = 15,
        ability = null, ownStrength = null,
        description = R.string.emptyItem
    )

    val sickle = Weapon(
        saveName = "sickle",
        name = R.string.sickle,
        damage = 35,
        speed = -10,
        oneHandStr = 4, twoHandStr = null,
        primaryType = AttackType.Cut, secondaryType = AttackType.Thrust, type = WeaponType.Short,
        fortitude = 8, breakage = 0, presence = 15,
        ability = null, ownStrength = null,
        description = R.string.emptyItem
    )

    val shortArms = listOf(boomerang, brokenBottle, cestus, claws, dagger, hook, katar, kitchenKnife,
        parryDagger, pick, raven, sai, shortSword, shuko, shuriken, sickle, stiletto, tanto, tessen, tonfa)
}