package com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.AttackType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.Weapon
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponAbility
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.ProjectileWeapon
import java.io.Serializable

/**
 * List of axe weapons the character may take.
 */
class Axes: Serializable {
    val battleAxe = ProjectileWeapon(
        saveName = "battleAxe",
        name = R.string.battleAxe,
        damage = 70,
        speed = -30,
        oneHandStr = 7, twoHandStr = null,
        primaryType = AttackType.Cut, secondaryType = AttackType.Impact, type = WeaponType.Axe,
        fortitude = 15, breakage = 5, presence = 25,
        reloadOrRate = 100, range = 5,
        ability = listOf(WeaponAbility.Throwable), ownStrength = null,
        description = R.string.battleAxeDesc
    )

    val handAxe = ProjectileWeapon(
        saveName = "handAxe",
        name = R.string.handAxe,
        damage = 45,
        speed = 0,
        oneHandStr = 5, twoHandStr = null,
        primaryType = AttackType.Cut, secondaryType = null, type = WeaponType.Axe,
        fortitude = 13, breakage = 4, presence = 15,
        reloadOrRate = 80, range = 10,
        ability = listOf(WeaponAbility.Throwable), ownStrength = null,
        description = R.string.handAxeDesc
    )

    val hoe = Weapon(
        saveName = "hoe",
        name = R.string.hoe,
        damage = 30,
        speed = -20,
        oneHandStr = 4, twoHandStr = null,
        primaryType = AttackType.Cut, secondaryType = AttackType.Impact, type = WeaponType.Axe,
        fortitude = 10, breakage = 1, presence = 15,
        ability = null, ownStrength = null,
        description = R.string.emptyItem
    )

    val woodAxe = Weapon(
        saveName = "woodAxe",
        name = R.string.woodsmanAxe,
        damage = 40,
        speed = -10,
        oneHandStr = 7, twoHandStr = 5,
        primaryType = AttackType.Cut, secondaryType = null, type = WeaponType.Axe,
        fortitude = 12, breakage = 3, presence = 15,
        ability = listOf(WeaponAbility.OneOrTwoHanded), ownStrength = null,
        description = R.string.emptyItem
    )

    val axes = listOf(battleAxe, handAxe, hoe, woodAxe)
}