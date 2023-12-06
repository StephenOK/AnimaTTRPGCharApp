package com.paetus.animaCharCreator.character_creation.attributes.modules

import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.BaseCharacter

/**
 * Record of martial arts the character may acquire.
 *
 * @param charInstance character object that holds this item
 */
class MartialArts(private val charInstance: BaseCharacter) {
    private val kempo = MartialArt(
        saveName = "kempo",
        name = R.string.kempo,
        description = R.string.kempoDesc,
        prereqList = R.string.noneLabel,
        mkBonus = 10,
        qualification = {true}
    )

    val capoeira = MartialArt(
        saveName = "capoeira",
        name = R.string.capoeira,
        description = R.string.capoeiraDesc,
        prereqList = R.string.capoeiraPrereqs,
        mkBonus = 10,
        qualification = {charInstance.secondaryList.dance.total.value >= 40}
    )

    private val taiChi = MartialArt(
        saveName = "taiChi",
        name = R.string.taiChi,
        description = R.string.taiChiDesc,
        prereqList = R.string.kiUse,
        mkBonus = 30,
        qualification = {charInstance.ki.takenAbilities.contains(charInstance.ki.kiRecord.useOfKi)}
    )

    private val shotokan = MartialArt(
        saveName = "shotokan",
        name = R.string.shotokan,
        description = R.string.shotokanDesc,
        prereqList = R.string.noneLabel,
        mkBonus = 10,
        qualification = {true}
    )

    private val sambo = MartialArt(
        saveName = "sambo",
        name = R.string.sambo,
        description = R.string.samboDesc,
        prereqList = R.string.noneLabel,
        mkBonus = 10,
        qualification = {true}
    )

    private val kungFu = MartialArt(
        saveName = "kungFu",
        name = R.string.kungFu,
        description = R.string.kungFuDesc,
        prereqList = R.string.kungFuPrereqs,
        mkBonus = 10,
        qualification = {
            charInstance.secondaryList.acrobatics.total .value>= 40 &&
                    charInstance.secondaryList.sleightHand.total.value >= 40 &&
                    charInstance.secondaryList.style.total.value >= 20
        }
    )

    private val taekwondo = MartialArt(
        saveName = "taekwondo",
        name = R.string.taekwondo,
        description = R.string.taekwondoDesc,
        prereqList = R.string.noneLabel,
        mkBonus = 10,
        qualification = {true}
    )

    val aikido = MartialArt(
        saveName = "aikido",
        name = R.string.aikido,
        description = R.string.aikidoDesc,
        prereqList = R.string.aikidoPrereqs,
        mkBonus = 10,
        qualification = {charInstance.secondaryList.sleightHand.total.value >= 40}
    )

    private val muayThai = MartialArt(
        saveName = "muayThai",
        name = R.string.muayThai,
        description = R.string.muayThaiDesc,
        prereqList = R.string.feats40Prereq,
        mkBonus = 10,
        qualification = {charInstance.secondaryList.strengthFeat.total.value >= 40}
    )

    private val grappling = MartialArt(
        saveName = "grappling",
        name = R.string.grappling,
        description = R.string.grapplingDesc,
        prereqList = R.string.feats40Prereq,
        mkBonus = 10,
        qualification = {charInstance.secondaryList.strengthFeat.total.value >= 40}
    )

    private val melkaiah = MartialArt(
        saveName = "melkaiah",
        name = R.string.melkaiah,
        description = R.string.melkaiahDesc,
        prereqList = R.string.melkaiahPrereqs,
        mkBonus = 10,
        qualification = {
            (charInstance.weaponProficiencies.takenMartialList.contains(grappling) || charInstance.weaponProficiencies.takenMartialList.contains(sambo)) &&
                    charInstance.ki.takenAbilities.contains(charInstance.ki.kiRecord.inhumanity) &&
                    charInstance.combat.attack.total.intValue >= 160 && (charInstance.combat.block.total.intValue >= 160 || charInstance.combat.dodge.total.intValue >= 160) &&
                    (charInstance.weaponProficiencies.primaryWeapon.value == charInstance.weaponProficiencies.unarmed || charInstance.weaponProficiencies.individualModules.contains(charInstance.weaponProficiencies.unarmed))
        }
    )

    private val seraphite = MartialArt(
        saveName = "seraphite",
        name = R.string.seraphite,
        description = R.string.seraphiteDesc,
        prereqList = R.string.seraphitePrereq,
        mkBonus = 10,
        qualification = {
            (charInstance.weaponProficiencies.takenMartialList.contains(shotokan) || charInstance.weaponProficiencies.takenMartialList.contains(kempo)) &&
                    charInstance.ki.takenAbilities.contains(charInstance.ki.kiRecord.presenceExtrusion) &&
                    charInstance.combat.attack.total.intValue >= 180 &&
                    (charInstance.weaponProficiencies.primaryWeapon.value == charInstance.weaponProficiencies.unarmed || charInstance.weaponProficiencies.individualModules.contains(charInstance.weaponProficiencies.unarmed))
        }
    )

    private val dumah = MartialArt(
        saveName = "dumah",
        name = R.string.dumah,
        description = R.string.dumahDesc,
        prereqList = R.string.dumahPrereq,
        mkBonus = 10,
        qualification = {
            (charInstance.weaponProficiencies.takenMartialList.contains(kempo) || charInstance.weaponProficiencies.takenMartialList.contains(capoeira)) &&
                    charInstance.ki.takenAbilities.contains(charInstance.ki.kiRecord.presenceExtrusion)
        }
    )

    private val emp = MartialArt(
        saveName = "emp",
        name = R.string.emp,
        description = R.string.empDesc,
        prereqList = R.string.empPrereqs,
        mkBonus = 10,
        qualification = {
            (charInstance.weaponProficiencies.takenMartialList.contains(kempo) || charInstance.weaponProficiencies.takenMartialList.contains(taekwondo)) &&
                    charInstance.combat.attack.total.intValue >= 200 &&
                    (charInstance.weaponProficiencies.primaryWeapon.value == charInstance.weaponProficiencies.unarmed || charInstance.weaponProficiencies.individualModules.contains(charInstance.weaponProficiencies.unarmed))
        }
    )

    private val enuth = MartialArt(
        saveName = "enuth",
        name = R.string.enuth,
        description = R.string.enuthDesc,
        prereqList = R.string.enuthPrereqs,
        mkBonus = 10,
        qualification = {
            (charInstance.weaponProficiencies.takenMartialList.contains(sambo) || charInstance.weaponProficiencies.takenMartialList.contains(shotokan)) &&
                    charInstance.combat.attack.total.intValue >= 160 && (charInstance.combat.block.total.intValue >= 160 || charInstance.combat.dodge.total.intValue >= 160) &&
                    (charInstance.weaponProficiencies.primaryWeapon.value == charInstance.weaponProficiencies.unarmed || charInstance.weaponProficiencies.individualModules.contains(charInstance.weaponProficiencies.unarmed))
        }
    )

    private val shephon = MartialArt(
        saveName = "shephon",
        name = R.string.shephon,
        description = R.string.shephonDesc,
        prereqList = R.string.shephonPrereqs,
        mkBonus = 10,
        qualification = {
            charInstance.weaponProficiencies.takenMartialList.contains(aikido) && charInstance.weaponProficiencies.takenMartialList.contains(kungFu) &&
                    charInstance.ki.takenAbilities.contains(charInstance.ki.kiRecord.kiControl) &&
                    (charInstance.combat.block.total.intValue >= 200 || charInstance.combat.dodge.total.intValue >= 200) &&
                    (charInstance.weaponProficiencies.primaryWeapon.value == charInstance.weaponProficiencies.unarmed || charInstance.weaponProficiencies.individualModules.contains(charInstance.weaponProficiencies.unarmed))
        }
    )

    private val asakusen = MartialArt(
        saveName = "asakusen",
        name = R.string.asakusen,
        description = R.string.asakusenDesc,
        prereqList = R.string.asakusenPrereqs,
        mkBonus = 10,
        qualification = {
            charInstance.weaponProficiencies.takenMartialList.contains(kungFu) &&
                    charInstance.combat.attack.total.intValue >= 160 && (charInstance.combat.block.total.intValue >= 160 || charInstance.combat.dodge.total.intValue >= 160) &&
                    (charInstance.weaponProficiencies.primaryWeapon.value == charInstance.weaponProficiencies.unarmed || charInstance.weaponProficiencies.individualModules.contains(charInstance.weaponProficiencies.unarmed))
        }
    )

    private val velez = MartialArt(
        saveName = "velez",
        name = R.string.velez,
        description = R.string.velezDesc,
        prereqList = R.string.velezPrereqs,
        mkBonus = 20,
        qualification = {
            (charInstance.weaponProficiencies.takenMartialList.contains(taiChi) || charInstance.weaponProficiencies.takenMartialList.contains(kungFu)) &&
                    charInstance.ki.takenAbilities.contains(charInstance.ki.kiRecord.presenceExtrusion)
        }
    )

    private val selene = MartialArt(
        saveName = "selene",
        name = R.string.selene,
        description = R.string.seleneDesc,
        prereqList = R.string.selenePrereqs,
        mkBonus = 10,
        qualification = {
            charInstance.weaponProficiencies.takenMartialList.contains(aikido) &&
                    (charInstance.combat.block.total.intValue >= 200 || charInstance.combat.dodge.total.intValue >= 200) &&
                    (charInstance.weaponProficiencies.primaryWeapon.value == charInstance.weaponProficiencies.unarmed || charInstance.weaponProficiencies.individualModules.contains(charInstance.weaponProficiencies.unarmed))
        }
    )

    private val hakyoukuken = MartialArt(
        saveName = "hakyoukuken",
        name = R.string.hakyoukuken,
        description = R.string.hakyoukukenDesc,
        prereqList = R.string.hakyoukukenPrereqs,
        mkBonus = 10,
        qualification = {
            (charInstance.weaponProficiencies.takenMartialList.contains(shotokan) || charInstance.weaponProficiencies.takenMartialList.contains(muayThai)) &&
                    charInstance.ki.takenAbilities.contains(charInstance.ki.kiRecord.useOfNecessaryEnergy) &&
                    charInstance.combat.attack.total.intValue >= 200 &&
                    (charInstance.weaponProficiencies.primaryWeapon.value == charInstance.weaponProficiencies.unarmed || charInstance.weaponProficiencies.individualModules.contains(charInstance.weaponProficiencies.unarmed))
        }
    )

    val allMartialArts = listOf(kempo, capoeira, taiChi, shotokan, sambo, kungFu, taekwondo,
        aikido, muayThai, grappling, melkaiah, seraphite, dumah, emp, enuth, shephon, asakusen,
        velez, selene, hakyoukuken)
}