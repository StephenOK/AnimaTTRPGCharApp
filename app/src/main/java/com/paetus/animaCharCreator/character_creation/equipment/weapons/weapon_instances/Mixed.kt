package com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_instances

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.enumerations.AttackType
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponAbility
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.MixedWeapon
import java.io.Serializable

/**
 * List of mixed class weapons the character may take.
 */
class Mixed: Serializable {
    val bastardSword = MixedWeapon(
        saveName = "bastardSword",
        name = R.string.bastardSword,
        damage = 70,
        speed = -30,
        oneHandStr = 9,
        twoHandStr = 7,
        primaryType = AttackType.Cut,
        secondaryType = AttackType.Impact,
        mixedType = listOf(WeaponType.Sword, WeaponType.TwoHanded),
        fortitude = 15,
        breakage = 5,
        presence = 25,
        ability = listOf(WeaponAbility.OneOrTwoHanded),
        ownStrength = null,
        description = R.string.bastardSwordDesc
    )

    private val flail = MixedWeapon(
        saveName = "flail",
        name = R.string.flail,
        damage = 40,
        speed = 0,
        oneHandStr = 6,
        twoHandStr = null,
        primaryType = AttackType.Impact,
        secondaryType = null,
        mixedType = listOf(WeaponType.Mace, WeaponType.Cord),
        fortitude = 13,
        breakage = 4,
        presence = 15,
        ability = listOf(WeaponAbility.Complex),
        ownStrength = null,
        description = R.string.flailDesc
    )

    val foil = MixedWeapon(
        saveName = "foil",
        name = R.string.foil,
        damage = 35,
        speed = 15,
        oneHandStr = 3,
        twoHandStr = null,
        primaryType = AttackType.Thrust,
        secondaryType = null,
        mixedType = listOf(WeaponType.Sword, WeaponType.Short),
        fortitude = 9,
        breakage = -2,
        presence = 20,
        ability = listOf(WeaponAbility.Precision),
        ownStrength = null,
        description = R.string.foilDesc
    )

    val halberd = MixedWeapon(
        saveName = "halberd",
        name = R.string.halberd,
        damage = 60,
        speed = -15,
        oneHandStr = 11,
        twoHandStr = 6,
        primaryType = AttackType.Cut,
        secondaryType = AttackType.Impact,
        mixedType = listOf(WeaponType.Pole, WeaponType.TwoHanded),
        fortitude = 15,
        breakage = 4,
        presence = 20,
        ability = listOf(WeaponAbility.OneOrTwoHanded),
        ownStrength = null,
        description = R.string.halberdDesc
    )

    val heavyBattleMace = MixedWeapon(
        saveName = "heavyBattleMace",
        name = R.string.heavyBattleMace,
        damage = 60,
        speed = -15,
        oneHandStr = 10,
        twoHandStr = 6,
        primaryType = AttackType.Impact,
        secondaryType = null,
        mixedType = listOf(WeaponType.Mace, WeaponType.TwoHanded),
        fortitude = 16,
        breakage = 5,
        presence = 15,
        ability = listOf(WeaponAbility.OneOrTwoHanded),
        ownStrength = null,
        description = R.string.heavyBattleMaceDesc
    )

    private val largeMultiFlail = MixedWeapon(
        saveName = "largeMultiFlail",
        name = R.string.largeMultiFlail,
        damage = 80,
        speed = -50,
        oneHandStr = 10,
        twoHandStr = 8,
        primaryType = AttackType.Impact,
        secondaryType = null,
        mixedType = listOf(WeaponType.Mace, WeaponType.TwoHanded),
        fortitude = 14,
        breakage = 6,
        presence = 20,
        ability = listOf(WeaponAbility.Complex),
        ownStrength = null,
        description = R.string.largeMultiFlailDesc
    )

    private val scythe = MixedWeapon(
        saveName = "scythe",
        name = R.string.scythe,
        damage = 35,
        speed = 0,
        oneHandStr = 9,
        twoHandStr = 5,
        primaryType = AttackType.Cut,
        secondaryType = AttackType.Impact,
        mixedType = listOf(WeaponType.Pole, WeaponType.TwoHanded),
        fortitude = 12,
        breakage = 2,
        presence = 25,
        ability = listOf(WeaponAbility.OneOrTwoHanded),
        ownStrength = null,
        description = R.string.scytheDesc
    )

    val twoHandAxe = MixedWeapon(
        saveName = "twoHandAxe",
        name = R.string.twoHandAxe,
        damage = 100,
        speed = -70,
        oneHandStr = 11,
        twoHandStr = 9,
        primaryType = AttackType.Cut,
        secondaryType = AttackType.Impact,
        mixedType = listOf(WeaponType.Axe, WeaponType.TwoHanded),
        fortitude = 17,
        breakage = 7,
        presence = 30,
        ability = listOf(WeaponAbility.OneOrTwoHanded),
        ownStrength = null,
        description = R.string.twoHandAxeDesc
    )

    val kusariGama = MixedWeapon(
        saveName = "kusariGama",
        name = R.string.kusari,
        damage = 40,
        speed = 5,
        oneHandStr = 5,
        twoHandStr = null,
        primaryType = AttackType.Cut,
        secondaryType = AttackType.Impact,
        mixedType = listOf(WeaponType.Short, WeaponType.Cord),
        fortitude = 12,
        breakage = 4,
        presence = 25,
        ability = listOf(WeaponAbility.TwoHanded, WeaponAbility.Trapping, WeaponAbility.Special),
        ownStrength = 8,
        description = R.string.kusariDesc
    )

    val mixed = listOf(bastardSword, flail, foil, halberd, heavyBattleMace, kusariGama,
        largeMultiFlail, scythe, twoHandAxe)

    val shortAdditions = listOf(foil, kusariGama)
    val axeAdditions = listOf(twoHandAxe)
    val maceAdditions = listOf(flail, heavyBattleMace, largeMultiFlail)
    val swordAdditions = listOf(bastardSword, foil)
    val twoHandedAdditions = listOf(bastardSword, halberd, heavyBattleMace, largeMultiFlail,
        scythe, twoHandAxe)
    val poleAdditions = listOf(halberd, scythe)
    val cordAdditions = listOf(flail, kusariGama)
}