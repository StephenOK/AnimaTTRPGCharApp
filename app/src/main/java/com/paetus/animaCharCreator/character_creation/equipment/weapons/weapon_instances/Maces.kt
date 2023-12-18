package com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.AttackType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.Weapon
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponAbility
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.ProjectileWeapon
import java.io.Serializable

/**
 * List of mace weapons the character may take.
 */
class Maces: Serializable {
    val club = Weapon(
        saveName = "club",
        name = R.string.club,
        damage = 30,
        speed = 0,
        oneHandStr = 5, twoHandStr = null,
        primaryType = AttackType.Impact, secondaryType = null, type = WeaponType.Mace,
        fortitude = 11, breakage = -2, presence = 15,
        ability = null, ownStrength = null,
        description = R.string.clubDesc
    )

    private val greatHammer = Weapon(
        saveName = "greatWarhammer",
        name = R.string.greatWarhammer,
        damage = 70,
        speed = -35,
        oneHandStr = 10, twoHandStr = 7,
        primaryType = AttackType.Impact, secondaryType = null, type = WeaponType.Mace,
        fortitude = 16, breakage = 6, presence = 20,
        ability = listOf(WeaponAbility.OneOrTwoHanded), ownStrength = null,
        description = R.string.greatWarhammerDesc
    )

    val mace = Weapon(
        saveName = "mace",
        name = R.string.mace,
        damage = 40,
        speed = 0,
        oneHandStr = 6, twoHandStr = null,
        primaryType = AttackType.Impact, secondaryType = null, type = WeaponType.Mace,
        fortitude = 14, breakage = 4, presence = 15,
        ability = null, ownStrength = null,
        description = R.string.maceDesc
    )

    private val warhammer = Weapon(
        saveName = "warhammer",
        name = R.string.warhammer,
        damage = 50,
        speed = -5,
        oneHandStr = 6, twoHandStr = null,
        primaryType = AttackType.Impact, secondaryType = null, type = WeaponType.Mace,
        fortitude = 15, breakage = 4, presence = 15,
        ability = null, ownStrength = null,
        description = R.string.warhammerDesc
    )

    val hammer = Weapon(
        saveName = "hammer",
        name = R.string.hammer,
        damage = 30,
        speed = -20,
        oneHandStr = 4, twoHandStr = null,
        primaryType = AttackType.Impact, secondaryType = null, type = WeaponType.Mace,
        fortitude = 12, breakage = 2, presence = 10,
        ability = null, ownStrength = null,
        description = R.string.emptyItem
    )

    val metalBar = Weapon(
        saveName = "metalBar",
        name = R.string.metalBar,
        damage = 25,
        speed = -5,
        oneHandStr = 5, twoHandStr = null,
        primaryType = AttackType.Impact, secondaryType = null, type = WeaponType.Mace,
        fortitude = 12, breakage = 2, presence = 15,
        ability = null, ownStrength = null,
        description = R.string.emptyItem
    )

    val torch = Weapon(
        saveName = "torch",
        name = R.string.torch,
        damage = 20,
        speed = -10,
        oneHandStr = 4, twoHandStr = null,
        primaryType = AttackType.Impact, secondaryType = AttackType.Heat, type = WeaponType.Mace,
        fortitude = 10, breakage = -2, presence = 20,
        ability = null, ownStrength = null,
        description = R.string.emptyItem
    )

    val vase = ProjectileWeapon(
        saveName = "vase",
        name = R.string.vase,
        damage = 15,
        speed = -10,
        oneHandStr = 4, twoHandStr = null,
        primaryType = AttackType.Impact, secondaryType = null, type = WeaponType.Mace,
        fortitude = 6, breakage = -2, presence = 20,
        reloadOrRate = null, range = null,
        ability = listOf(WeaponAbility.Throwable), ownStrength = null,
        description = R.string.emptyItem
    )

    val woodenPole = Weapon(
        saveName = "woodPole",
        name = R.string.woodenPole,
        damage = 20,
        speed = 0,
        oneHandStr = 4, twoHandStr = null,
        primaryType = AttackType.Impact, secondaryType = null, type = WeaponType.Mace,
        fortitude = 8, breakage = -1, presence = 10,
        ability = null, ownStrength = null,
        description = R.string.emptyItem
    )

    val maces = listOf(club, greatHammer, hammer, mace, metalBar, torch, vase, warhammer, woodenPole)
}