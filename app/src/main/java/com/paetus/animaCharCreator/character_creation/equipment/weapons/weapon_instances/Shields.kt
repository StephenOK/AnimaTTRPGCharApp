package com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.AttackType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.Weapon
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponAbility
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponType
import java.io.Serializable

/**
 * List of shields the character may take.
 */
class Shields: Serializable {
    val buckler = Weapon(
        saveName = "buckler",
        name = R.string.buckler,
        damage = 15,
        speed = -15,
        oneHandStr = 5, twoHandStr = null,
        primaryType = AttackType.Impact, secondaryType = null, type = WeaponType.Shield,
        fortitude = 14, breakage = 0, presence = 20,
        ability = listOf(WeaponAbility.Special), ownStrength = null,
        description = R.string.bucklerDesc
    )

    val shield = Weapon(
        saveName = "shield",
        name = R.string.shield,
        damage = 20,
        speed = -25,
        oneHandStr = 7, twoHandStr = null,
        primaryType = AttackType.Impact, secondaryType = null, type = WeaponType.Shield,
        fortitude = 16, breakage = 0, presence = 25,
        ability = listOf(WeaponAbility.Special), ownStrength = null,
        description = R.string.shieldDesc,
    )

    val fullShield = Weapon(
        saveName = "fullShield",
        name = R.string.fullShield,
        damage = 25,
        speed = -40,
        oneHandStr = 10, twoHandStr = null,
        primaryType = AttackType.Impact, secondaryType = null, type = WeaponType.Shield,
        fortitude = 18, breakage = 1, presence = 25,
        ability = listOf(WeaponAbility.Special), ownStrength = null,
        description = R.string.fullShieldDesc
    )

    val shields = listOf(buckler, shield, fullShield)
}