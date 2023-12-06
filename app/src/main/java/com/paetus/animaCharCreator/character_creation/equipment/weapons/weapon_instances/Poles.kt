package com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.AttackType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.Weapon
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponAbility
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.ProjectileWeapon
import java.io.Serializable

/**
 * List the pole weapons the character may take.
 */
class Poles: Serializable {
    val cavLance = Weapon(
        saveName = "cavLance",
        name = R.string.cavLance,
        damage = 80,
        speed = -30,
        oneHandStr = 8, twoHandStr = null,
        primaryType = AttackType.Thrust, secondaryType = null, type = WeaponType.Pole,
        fortitude = 12, breakage = 7, presence = 25,
        ability = listOf(WeaponAbility.Special), ownStrength = null,
        description = R.string.cavLanceDesc
    )

    val harpoon = ProjectileWeapon(
        saveName = "harpoon",
        name = R.string.harpoon,
        damage = 35,
        speed = -5,
        oneHandStr = 5, twoHandStr = null,
        primaryType = AttackType.Thrust, secondaryType = null, type = WeaponType.Pole,
        fortitude = 11, breakage = 0, presence = 15,
        reloadOrRate = 100, range = 20,
        ability = listOf(WeaponAbility.Throwable, WeaponAbility.OneOrTwoHanded), ownStrength = null,
        description = R.string.harpoonDesc
    )

    val javelin = ProjectileWeapon(
        saveName = "javelin",
        name = R.string.javelin,
        damage = 35,
        speed = 5,
        oneHandStr = 4, twoHandStr = null,
        primaryType = AttackType.Thrust, secondaryType = null, type = WeaponType.Pole,
        fortitude = 10, breakage = -2, presence = 20,
        reloadOrRate = 80, range = 30,
        ability = listOf(WeaponAbility.Throwable), ownStrength = null,
        description = R.string.javelinDesc
    )

    val lance = ProjectileWeapon(
        saveName = "lance",
        name = R.string.lance,
        damage = 40,
        speed = 5,
        oneHandStr = 6, twoHandStr = 4,
        primaryType = AttackType.Thrust, secondaryType = null, type = WeaponType.Pole,
        fortitude = 13, breakage = 2, presence = 25,
        reloadOrRate = 80, range = 30,
        ability = listOf(WeaponAbility.Throwable, WeaponAbility.OneOrTwoHanded), ownStrength = null,
        description = R.string.lanceDesc
    )

    private val quarterstaff = Weapon(
        saveName = "quarterstaff",
        name = R.string.quarterstaff,
        damage = 30,
        speed = 10,
        oneHandStr = 4, twoHandStr = null,
        primaryType = AttackType.Impact, secondaryType = null, type = WeaponType.Pole,
        fortitude = 11, breakage = 0, presence = 30,
        ability = listOf(WeaponAbility.TwoHanded), ownStrength = null,
        description = R.string.quarterstaffDesc
    )

    val trident = ProjectileWeapon(
        saveName = "trident",
        name = R.string.trident,
        damage = 40,
        speed = -10,
        oneHandStr = 7, twoHandStr = 6,
        primaryType = AttackType.Thrust, secondaryType = null, type = WeaponType.Pole,
        fortitude = 12, breakage = 3, presence = 15,
        reloadOrRate = 100, range = 15,
        ability = listOf(WeaponAbility.Throwable, WeaponAbility.OneOrTwoHanded), ownStrength = null,
        description = R.string.tridentDesc
    )

    private val haruNoOkina = Weapon(
        saveName = "haruNoOkina",
        name = R.string.haruNoOkina,
        damage = 35,
        speed = 15,
        oneHandStr = 4,
        twoHandStr = null,
        primaryType = AttackType.Cut,
        secondaryType = AttackType.Thrust,
        type = WeaponType.Pole,
        fortitude = 12,
        breakage = 2,
        presence = 25,
        ability = listOf(WeaponAbility.Complex, WeaponAbility.TwoHanded, WeaponAbility.Special),
        ownStrength = null,
        description = R.string.haruNoOkinaDesc
    )

    val poles = listOf(cavLance, harpoon, haruNoOkina, javelin, lance, quarterstaff, trident)
}