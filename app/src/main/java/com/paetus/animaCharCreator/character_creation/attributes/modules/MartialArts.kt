package com.paetus.animaCharCreator.character_creation.attributes.modules

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.BaseCharacter

/**
 * Record of martial arts the character may acquire.
 */
class MartialArts(private val charInstance: BaseCharacter) {
    val kempo = MartialArt(
        "kempo",
        R.string.kempo,
        R.string.kempoDesc,
        R.string.noneLabel,
        10
    ){true}

    val capoeira = MartialArt(
        "capoeira",
        R.string.capoeira,
        R.string.capoeiraDesc,
        R.string.capoeiraPrereqs,
        10
    ){charInstance.secondaryList.dance.total.value >= 40}

    val taiChi = MartialArt(
        "taiChi",
        R.string.taiChi,
        R.string.taiChiDesc,
        R.string.kiUse,
        30
    ){charInstance.ki.takenAbilities.contains(charInstance.ki.kiRecord.useOfKi)}

    val shotokan = MartialArt(
        "shotokan",
        R.string.shotokan,
        R.string.shotokanDesc,
        R.string.noneLabel,
        10
    ){true}

    val sambo = MartialArt(
        "sambo",
        R.string.sambo,
        R.string.samboDesc,
        R.string.noneLabel,
        10
    ){true}

    val kungFu = MartialArt(
        "kungFu",
        R.string.kungFu,
        R.string.kungFuDesc,
        R.string.kungFuPrereqs,
        10
    ){
        charInstance.secondaryList.acrobatics.total .value>= 40 &&
            charInstance.secondaryList.sleightHand.total.value >= 40 &&
            charInstance.secondaryList.style.total.value >= 20
    }

    val taekwondo = MartialArt(
        "taekwondo",
        R.string.taekwondo,
        R.string.taekwondoDesc,
        R.string.noneLabel,
        10
    ){true}

    val aikido = MartialArt(
        "aikido",
        R.string.aikido,
        R.string.aikidoDesc,
        R.string.aikidoPrereqs,
        10
    ){charInstance.secondaryList.sleightHand.total.value >= 40}

    val muayThai = MartialArt(
        "muayThai",
        R.string.muayThai,
        R.string.muayThaiDesc,
        R.string.feats40Prereq,
        10
    ){charInstance.secondaryList.strengthFeat.total.value >= 40}

    val grappling = MartialArt(
        "grappling",
        R.string.grappling,
        R.string.grapplingDesc,
        R.string.feats40Prereq,
        10
    ){charInstance.secondaryList.strengthFeat.total.value >= 40}

    val melkaiah = MartialArt(
        "melkaiah",
        R.string.melkaiah,
        R.string.melkaiahDesc,
        R.string.melkaiahPrereqs,
        10
    ){
        (charInstance.weaponProficiencies.takenMartialList.contains(grappling) || charInstance.weaponProficiencies.takenMartialList.contains(sambo)) &&
                charInstance.ki.takenAbilities.contains(charInstance.ki.kiRecord.inhumanity) &&
                charInstance.combat.attack.total.value >= 160 && (charInstance.combat.block.total.value >= 160 || charInstance.combat.dodge.total.value >= 160) &&
                (charInstance.weaponProficiencies.primaryWeapon.value == charInstance.weaponProficiencies.unarmed || charInstance.weaponProficiencies.individualModules.contains(charInstance.weaponProficiencies.unarmed))
    }

    val seraphite = MartialArt(
        "seraphite",
        R.string.seraphite,
        R.string.seraphiteDesc,
        R.string.seraphitePrereq,
        10
    ){
        (charInstance.weaponProficiencies.takenMartialList.contains(shotokan) || charInstance.weaponProficiencies.takenMartialList.contains(kempo)) &&
                charInstance.ki.takenAbilities.contains(charInstance.ki.kiRecord.presenceExtrusion) &&
                charInstance.combat.attack.total.value >= 180 &&
                (charInstance.weaponProficiencies.primaryWeapon.value == charInstance.weaponProficiencies.unarmed || charInstance.weaponProficiencies.individualModules.contains(charInstance.weaponProficiencies.unarmed))
    }

    val dumah = MartialArt(
        "dumah",
        R.string.dumah,
        R.string.dumahDesc,
        R.string.dumahPrereq,
        10
    ){
        (charInstance.weaponProficiencies.takenMartialList.contains(kempo) || charInstance.weaponProficiencies.takenMartialList.contains(capoeira)) &&
                charInstance.ki.takenAbilities.contains(charInstance.ki.kiRecord.presenceExtrusion)
    }

    val emp = MartialArt(
        "emp",
        R.string.emp,
        R.string.empDesc,
        R.string.empPrereqs,
        10
    ){
        (charInstance.weaponProficiencies.takenMartialList.contains(kempo) || charInstance.weaponProficiencies.takenMartialList.contains(taekwondo)) &&
                charInstance.combat.attack.total.value >= 200 &&
                (charInstance.weaponProficiencies.primaryWeapon.value == charInstance.weaponProficiencies.unarmed || charInstance.weaponProficiencies.individualModules.contains(charInstance.weaponProficiencies.unarmed))
    }

    val enuth = MartialArt(
        "enuth",
        R.string.enuth,
        R.string.enuthDesc,
        R.string.enuthPrereqs,
        10
    ){
        (charInstance.weaponProficiencies.takenMartialList.contains(sambo) || charInstance.weaponProficiencies.takenMartialList.contains(shotokan)) &&
                charInstance.combat.attack.total.value >= 160 && (charInstance.combat.block.total.value >= 160 || charInstance.combat.dodge.total.value >= 160) &&
                (charInstance.weaponProficiencies.primaryWeapon.value == charInstance.weaponProficiencies.unarmed || charInstance.weaponProficiencies.individualModules.contains(charInstance.weaponProficiencies.unarmed))
    }

    val shephon = MartialArt(
        "shephon",
        R.string.shephon,
        R.string.shephonDesc,
        R.string.shephonPrereqs,
        10
    ){
        charInstance.weaponProficiencies.takenMartialList.contains(aikido) && charInstance.weaponProficiencies.takenMartialList.contains(kungFu) &&
                charInstance.ki.takenAbilities.contains(charInstance.ki.kiRecord.kiControl) &&
                (charInstance.combat.block.total.value >= 200 || charInstance.combat.dodge.total.value >= 200) &&
                (charInstance.weaponProficiencies.primaryWeapon.value == charInstance.weaponProficiencies.unarmed || charInstance.weaponProficiencies.individualModules.contains(charInstance.weaponProficiencies.unarmed))
    }

    val asakusen = MartialArt(
        "asakusen",
        R.string.asakusen,
        R.string.asakusenDesc,
        R.string.asakusenPrereqs,
        10
    ){
        charInstance.weaponProficiencies.takenMartialList.contains(kungFu) &&
                charInstance.combat.attack.total.value >= 160 && (charInstance.combat.block.total.value >= 160 || charInstance.combat.dodge.total.value >= 160) &&
                (charInstance.weaponProficiencies.primaryWeapon.value == charInstance.weaponProficiencies.unarmed || charInstance.weaponProficiencies.individualModules.contains(charInstance.weaponProficiencies.unarmed))
    }

    val velez = MartialArt(
        "velez",
        R.string.velez,
        R.string.velezDesc,
        R.string.velezPrereqs,
        20
    ){
        (charInstance.weaponProficiencies.takenMartialList.contains(taiChi) || charInstance.weaponProficiencies.takenMartialList.contains(kungFu)) &&
                charInstance.ki.takenAbilities.contains(charInstance.ki.kiRecord.presenceExtrusion)
    }

    val selene = MartialArt(
        "selene",
        R.string.selene,
        R.string.seleneDesc,
        R.string.selenePrereqs,
        10
    ){
        charInstance.weaponProficiencies.takenMartialList.contains(aikido) &&
                (charInstance.combat.block.total.value >= 200 || charInstance.combat.dodge.total.value >= 200) &&
                (charInstance.weaponProficiencies.primaryWeapon.value == charInstance.weaponProficiencies.unarmed || charInstance.weaponProficiencies.individualModules.contains(charInstance.weaponProficiencies.unarmed))
    }

    val hakyoukuken = MartialArt(
        "hakyoukuken",
        R.string.hakyoukuken,
        R.string.hakyoukukenDesc,
        R.string.hakyoukukenPrereqs,
        10
    ){
        (charInstance.weaponProficiencies.takenMartialList.contains(shotokan) || charInstance.weaponProficiencies.takenMartialList.contains(muayThai)) &&
                charInstance.ki.takenAbilities.contains(charInstance.ki.kiRecord.useOfNecessaryEnergy) &&
                charInstance.combat.attack.total.value >= 200 &&
                (charInstance.weaponProficiencies.primaryWeapon.value == charInstance.weaponProficiencies.unarmed || charInstance.weaponProficiencies.individualModules.contains(charInstance.weaponProficiencies.unarmed))
    }

    val allMartialArts = listOf(kempo, capoeira, taiChi, shotokan, sambo, kungFu, taekwondo,
        aikido, muayThai, grappling, melkaiah, seraphite, dumah, emp, enuth, shephon, asakusen,
        velez, selene, hakyoukuken)
}