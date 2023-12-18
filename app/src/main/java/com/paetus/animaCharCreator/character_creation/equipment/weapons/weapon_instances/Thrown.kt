package com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.AttackType
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponAbility
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.ProjectileWeapon
import java.io.Serializable

/**
 * List of thrown weapons the character may take.
 */
class Thrown: Serializable {
    val bolas = ProjectileWeapon(
        saveName = "bolas",
        name = R.string.bolas,
        damage = 30,
        speed = -10,
        oneHandStr = 5,
        twoHandStr = null,
        primaryType = AttackType.Impact,
        secondaryType = null,
        type = WeaponType.Throwing,
        fortitude = 6,
        breakage = 2,
        presence = 15,
        reloadOrRate = 80,
        range = 20,
        ability = listOf(WeaponAbility.Trapping, WeaponAbility.Complex, WeaponAbility.Special),
        ownStrength = 10,
        description = R.string.bolasDesc
    )

    val chakram = ProjectileWeapon(
        saveName = "chakram",
        name = R.string.chakram,
        damage = 40,
        speed = 0,
        oneHandStr = 6, twoHandStr = null,
        primaryType = AttackType.Cut, secondaryType = null, type = WeaponType.Throwing,
        fortitude = 9, breakage = 2, presence = 20,
        reloadOrRate = 80, range = 30,
        ability = listOf(WeaponAbility.Special), ownStrength = null,
        description = R.string.chakramDesc
    )

    private val darts = ProjectileWeapon(
        saveName = "darts",
        name = R.string.darts,
        damage = 20,
        speed = 20,
        oneHandStr = 3, twoHandStr = null,
        primaryType = AttackType.Thrust, secondaryType = null, type = WeaponType.Throwing,
        fortitude = 3, breakage = -4, presence = 15,
        reloadOrRate = 40, range = 20,
        ability = null, ownStrength = null,
        description = R.string.dartsDesc
    )

    private val spikedBall = ProjectileWeapon(
        saveName = "spikedBall",
        name = R.string.spikeBall,
        damage = 20,
        speed = 0,
        oneHandStr = 5, twoHandStr = null,
        primaryType = AttackType.Impact, secondaryType = null, type = WeaponType.Throwing,
        fortitude = 10, breakage = 2, presence = 15,
        reloadOrRate = 50, range = 20,
        ability = null, ownStrength = null,
        description = R.string.spikeBallDesc
    )

    val thrown = listOf(bolas, chakram, darts, spikedBall)
}