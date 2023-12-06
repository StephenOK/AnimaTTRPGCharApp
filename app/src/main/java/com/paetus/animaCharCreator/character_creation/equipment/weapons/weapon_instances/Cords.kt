package com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.AttackType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.Weapon
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponAbility
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.ProjectileWeapon
import java.io.Serializable

/**
 * List of cord weapons the character may take.
 */
class Cords: Serializable {
    private val chain = Weapon(
        saveName = "chain",
        name = R.string.chain,
        damage = 25,
        speed = 0,
        oneHandStr = 6, twoHandStr = null,
        primaryType = AttackType.Impact, secondaryType = null, type = WeaponType.Cord,
        fortitude = 13, breakage = 2, presence = 15,
        ability = listOf(WeaponAbility.Complex, WeaponAbility.Trapping), ownStrength = 8,
        description = R.string.chainDesc
    )

    val gladNet = ProjectileWeapon(
        saveName = "gladNet",
        name = R.string.gladNet,
        damage = 5,
        speed = 0,
        oneHandStr = 4,
        twoHandStr = null,
        primaryType = AttackType.Impact,
        secondaryType = AttackType.Cut,
        type = WeaponType.Cord,
        fortitude = 13,
        breakage = -4,
        presence = 15,
        reloadOrRate = 100,
        range = 5,
        ability = listOf(WeaponAbility.Throwable, WeaponAbility.Trapping, WeaponAbility.Special),
        ownStrength = 10,
        description = R.string.gladNetDesc
    )

    private val lasso = Weapon(
        saveName = "lasso",
        name = R.string.lasso,
        damage = 5,
        speed = 10,
        oneHandStr = 4,
        twoHandStr = null,
        primaryType = AttackType.Impact,
        secondaryType = null,
        type = WeaponType.Cord,
        fortitude = 9,
        breakage = -4,
        presence = 20,
        ability = listOf(WeaponAbility.Complex, WeaponAbility.Trapping, WeaponAbility.Special),
        ownStrength = 9,
        description = R.string.lassoDesc
    )

    val whip = Weapon(
        saveName = "whip",
        name = R.string.whip,
        damage = 35,
        speed = -20,
        oneHandStr = 4, twoHandStr = null,
        primaryType = AttackType.Cut, secondaryType = AttackType.Impact, type = WeaponType.Cord,
        fortitude = 9, breakage = -3, presence = 20,
        ability = listOf(WeaponAbility.Complex, WeaponAbility.Trapping), ownStrength = 8,
        description = R.string.whipDesc
    )

    private val nunchakus = Weapon(
        saveName = "nunchakus",
        name = R.string.nunchakus,
        damage = 30,
        speed = 15,
        oneHandStr = 5, twoHandStr = null,
        primaryType = AttackType.Impact, secondaryType = null, type = WeaponType.Cord,
        fortitude = 11, breakage = 0, presence = 15,
        ability = null, ownStrength = null,
        description = R.string.nunchakusDesc
    )

    val cords = listOf(chain, gladNet, lasso, nunchakus, whip)
}