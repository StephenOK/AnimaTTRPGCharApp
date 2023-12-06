package com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.AttackType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.Weapon
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponAbility
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponType
import java.io.Serializable

/**
 * List of two-handed weapons the character may take.
 */
class TwoHanded: Serializable {
    val twoHandSword = Weapon(
        saveName = "twoHandSword",
        name = R.string.twoHandSword,
        damage = 90,
        speed = -60,
        oneHandStr = 10,
        twoHandStr = 8,
        primaryType = AttackType.Cut,
        secondaryType = AttackType.Impact,
        type = WeaponType.TwoHanded,
        fortitude = 18,
        breakage = 6,
        presence = 30,
        ability = listOf(WeaponAbility.OneOrTwoHanded),
        ownStrength = null,
        description = R.string.twoHandSwordDesc
    )

    private val nodachi = Weapon(
        saveName = "nodachi",
        name = R.string.nodachi,
        damage = 80,
        speed = -35,
        oneHandStr = 10, twoHandStr = 8,
        primaryType = AttackType.Cut, secondaryType = null, type = WeaponType.TwoHanded,
        fortitude = 14, breakage = 4, presence = 40,
        ability = listOf(WeaponAbility.OneOrTwoHanded), ownStrength = null,
        description = R.string.nodachiDesc
    )

    private val swordBreaker = Weapon(
        saveName = "swordBreaker",
        name = R.string.swordBreaker,
        damage = 50,
        speed = -20,
        oneHandStr = 10,
        twoHandStr = 8,
        primaryType = AttackType.Impact,
        secondaryType = AttackType.Cut,
        type = WeaponType.TwoHanded,
        fortitude = 16,
        breakage = 8,
        presence = 25,
        ability = listOf(WeaponAbility.OneOrTwoHanded),
        ownStrength = null,
        description = R.string.swordBreakerDesc
    )

    val chair = Weapon(
        saveName = "chair",
        name = R.string.chair,
        damage = 25,
        speed = -20,
        oneHandStr = 5, twoHandStr = null,
        primaryType = AttackType.Impact, secondaryType = null, type = WeaponType.TwoHanded,
        fortitude = 9, breakage = 0, presence = 20,
        ability = listOf(WeaponAbility.TwoHanded), ownStrength = null,
        description = R.string.emptyItem
    )

    val twoHanded = listOf(chair, nodachi, swordBreaker, twoHandSword)
}