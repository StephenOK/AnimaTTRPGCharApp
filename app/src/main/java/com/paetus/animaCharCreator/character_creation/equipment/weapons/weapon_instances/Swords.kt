package com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.AttackType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.Weapon
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponAbility
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponType
import java.io.Serializable

/**
 * List of sword weapons the character may take.
 */
class Swords: Serializable {
    private val broadsword = Weapon(
        saveName = "broadsword",
        name = R.string.broadsword,
        damage = 55,
        speed = -5,
        oneHandStr = 5, twoHandStr = null,
        primaryType = AttackType.Cut, secondaryType = null, type = WeaponType.Sword,
        fortitude = 15, breakage = 3, presence = 25,
        ability = null, ownStrength = null,
        description = R.string.broadswordDesc
    )

    val longSword = Weapon(
        saveName = "longSword",
        name = R.string.longsword,
        damage = 50,
        speed = 0,
        oneHandStr = 6, twoHandStr = null,
        primaryType = AttackType.Cut, secondaryType = null, type = WeaponType.Sword,
        fortitude = 13, breakage = 3, presence = 25,
        ability = null, ownStrength = null,
        description = R.string.longswordDesc
    )

    val rapier = Weapon(
        saveName = "rapier",
        name = R.string.rapier,
        damage = 40,
        speed = 15,
        oneHandStr = 4, twoHandStr = null,
        primaryType = AttackType.Thrust, secondaryType = AttackType.Cut, type = WeaponType.Sword,
        fortitude = 11, breakage = 2, presence = 20,
        ability = listOf(WeaponAbility.Precision), ownStrength = null,
        description = R.string.rapierDesc
    )

    val saber = Weapon(
        saveName = "saber",
        name = R.string.saber,
        damage = 45,
        speed = 10,
        oneHandStr = 6, twoHandStr = null,
        primaryType = AttackType.Cut, secondaryType = AttackType.Thrust, type = WeaponType.Sword,
        fortitude = 12, breakage = 3, presence = 20,
        ability = null, ownStrength = null,
        description = R.string.saberDesc
    )

    val scimitar = Weapon(
        saveName = "scimitar",
        name = R.string.scimitar,
        damage = 50,
        speed = -5,
        oneHandStr = 5, twoHandStr = null,
        primaryType = AttackType.Cut, secondaryType = null, type = WeaponType.Sword,
        fortitude = 13, breakage = 4, presence = 20,
        ability = null, ownStrength = null,
        description = R.string.scimitarDesc
    )

    val katana = Weapon(
        saveName = "katana",
        name = R.string.katana,
        damage = 50,
        speed = 0,
        oneHandStr = 6, twoHandStr = 5,
        primaryType = AttackType.Cut, secondaryType = null, type = WeaponType.Sword,
        fortitude = 11, breakage = 3, presence = 40,
        ability = listOf(WeaponAbility.OneOrTwoHanded), ownStrength = null,
        description = R.string.katanaDesc
    )

    private val twoBladeKatana = Weapon(
        saveName = "twoBladeKatana",
        name = R.string.twoBladeKatana,
        damage = 55,
        speed = -5,
        oneHandStr = 8, twoHandStr = null,
        primaryType = AttackType.Cut, secondaryType = null, type = WeaponType.Sword,
        fortitude = 11, breakage = 3, presence = 40,
        ability = listOf(WeaponAbility.Special), ownStrength = null,
        description = R.string.twoBladeKatanaDesc
    )

    val swords = listOf(broadsword, katana, longSword, rapier, saber, scimitar, twoBladeKatana)
}