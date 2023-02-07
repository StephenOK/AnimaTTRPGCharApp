package com.example.animabuilder.character_creation.attributes.class_objects

import com.example.animabuilder.character_creation.BaseCharacter
import java.io.Serializable

class ClassInstances(charInstance: BaseCharacter): Serializable {
    val warrior =
        CharClass(
            ClassName.warrior, listOf(Archetype.Fighter),
            15, 15, 5, 25, 3,
            0.6, 2, 2, 2, 2, 2, 20,
            0.5, 3, 70, 3, 3, 3, 3, 3,
            0.5, 20, 3,
            2, 2, 2, 3, 2, 2, 2,
            {
                charInstance.setAttackPerLevel(5)
                charInstance.setBlockPerLevel(5)
                charInstance.setWearPerLevel(5)

                charInstance.secondaryList.strengthFeat.setPointsFromClass(5)

                charInstance.secondaryList.strengthFeat.setDevelopmentDeduction(1)
            },
            {
                charInstance.setAttackPerLevel(0)
                charInstance.setBlockPerLevel(0)
                charInstance.setWearPerLevel(0)

                charInstance.secondaryList.strengthFeat.setPointsFromClass(0)

                charInstance.secondaryList.strengthFeat.setDevelopmentDeduction(-1)
            }
        )

    val acroWarrior = CharClass(
        ClassName.acroWarrior, listOf(Archetype.Fighter),
        20, 10, 10, 25, 3,
        0.6, 2, 3, 2, 2, 2, 20,
        0.5, 3, 70, 3, 3, 3, 3, 3,
        0.5, 20, 3,
        2, 2, 2, 3, 2, 2, 2,
        {
            charInstance.setAttackPerLevel(5)
            charInstance.setDodgePerLevel(5)

            charInstance.secondaryList.acrobatics.setPointsFromClass(10)
            charInstance.secondaryList.jump.setPointsFromClass(10)
            charInstance.secondaryList.athletics.setPointsFromClass(10)
            charInstance.secondaryList.sleightHand.setPointsFromClass(10)
            charInstance.secondaryList.style.setPointsFromClass(10)
        },
        {
            charInstance.setAttackPerLevel(0)
            charInstance.setDodgePerLevel(0)

            charInstance.secondaryList.acrobatics.setPointsFromClass(0)
            charInstance.secondaryList.jump.setPointsFromClass(0)
            charInstance.secondaryList.athletics.setPointsFromClass(0)
            charInstance.secondaryList.sleightHand.setPointsFromClass(0)
            charInstance.secondaryList.style.setPointsFromClass(0)
        }
    )

    val paladin = CharClass(
        ClassName.paladin, listOf(Archetype.Fighter),
        15, 15, 5, 20, 3,
        0.60, 2, 2, 2, 2, 2, 20,
        0.50, 2, 60, 3, 3, 3, 3, 1,
        0.50, 20, 3,
        2, 1, 2, 2, 2, 3, 2,
        {
            charInstance.setBlockPerLevel(5)
            charInstance.setWearPerLevel(10)

            charInstance.secondaryList.leadership.setPointsFromClass(10)
            charInstance.secondaryList.resistPain.setPointsFromClass(10)
            charInstance.secondaryList.style.setPointsFromClass(5)

            charInstance.secondaryList.resistPain.setDevelopmentDeduction(1)

            charInstance.magic.setZeonPerLevel(20)

            charInstance.summoning.setBanishPerLevel(10)
        },
        {
            charInstance.setBlockPerLevel(0)
            charInstance.setWearPerLevel(0)

            charInstance.secondaryList.leadership.setPointsFromClass(0)
            charInstance.secondaryList.resistPain.setPointsFromClass(0)
            charInstance.secondaryList.style.setPointsFromClass(0)

            charInstance.secondaryList.resistPain.setDevelopmentDeduction(-1)

            charInstance.magic.setZeonPerLevel(0)

            charInstance.summoning.setBanishPerLevel(0)
        }
    )

    val darkPaladin = CharClass(
        ClassName.darkPaladin, listOf(Archetype.Fighter),
        15, 15, 5, 20, 3,
        0.60, 2, 2, 2, 2, 2, 20,
        0.50, 2, 60, 3, 3, 1, 3, 3,
        0.50, 20, 3,
        2, 1, 2, 2, 2, 2, 2,
        {
            charInstance.setAttackPerLevel(5)
            charInstance.setWearPerLevel(5)

            charInstance.secondaryList.intimidate.setPointsFromClass(10)
            charInstance.secondaryList.composure.setPointsFromClass(10)
            charInstance.secondaryList.style.setPointsFromClass(5)
            charInstance.secondaryList.persuasion.setPointsFromClass(5)

            charInstance.secondaryList.composure.setDevelopmentDeduction(1)

            charInstance.magic.setZeonPerLevel(20)

            charInstance.summoning.setControlPerLevel(10)
        },
        {
            charInstance.setAttackPerLevel(0)
            charInstance.setWearPerLevel(0)

            charInstance.secondaryList.intimidate.setPointsFromClass(0)
            charInstance.secondaryList.composure.setPointsFromClass(0)
            charInstance.secondaryList.style.setPointsFromClass(0)
            charInstance.secondaryList.persuasion.setPointsFromClass(0)

            charInstance.secondaryList.composure.setDevelopmentDeduction(-1)

            charInstance.magic.setZeonPerLevel(0)

            charInstance.summoning.setControlPerLevel(0)
        }
    )

    val weaponMaster = CharClass(
        ClassName.weaponMaster, listOf(Archetype.Fighter),
        10, 20, 5, 10, 3,
        0.60, 2, 2, 2, 1, 3, 30,
        0.50, 3, 70, 3, 3, 3, 3, 3,
        0.50, 20, 3,
        2, 2, 2, 3, 1, 3, 2,
        {
            charInstance.setAttackPerLevel(5)
            charInstance.setBlockPerLevel(5)
            charInstance.setWearPerLevel(10)

            charInstance.secondaryList.strengthFeat.setPointsFromClass(5)
        },
        {
            charInstance.setAttackPerLevel(0)
            charInstance.setBlockPerLevel(0)
            charInstance.setWearPerLevel(0)

            charInstance.secondaryList.strengthFeat.setPointsFromClass(0)
        }
    )

    val technician = CharClass(
        ClassName.technician, listOf(Archetype.Domine),
        20, 5, 5, 50, 3,
        0.60, 2, 2, 2, 2, 1, 10,
        0.50, 3, 70, 3, 3, 3, 3, 3,
        0.50, 20, 3,
        2, 2, 2, 3, 2, 2, 2,
        {charInstance.setAttackPerLevel(5)},
        {charInstance.setAttackPerLevel(0)}
    )

    val tao = CharClass(
        ClassName.tao, listOf(Archetype.Fighter, Archetype.Domine),
        20, 10, 5, 30, 3,
        0.60, 2, 2, 2, 2, 2, 15,
        0.50, 3, 70, 3, 3, 3, 3, 3,
        0.50, 20, 3,
        2, 2, 2, 3, 2, 2, 2,
        {charInstance.secondaryList.style.setPointsFromClass(5)},
        {charInstance.secondaryList.style.setPointsFromClass(0)}
    )

    val ranger = CharClass(
        ClassName.ranger, listOf(Archetype.Fighter, Archetype.Prowler),
        20, 10, 5, 20, 3,
        0.60, 2, 2, 2, 2, 2, 25,
        0.50, 3, 70, 3, 3, 3, 3, 3,
        0.50, 20, 3,
        2, 2, 1, 3, 3, 2, 2,
        {
            charInstance.setAttackPerLevel(5)

            charInstance.secondaryList.notice.setPointsFromClass(10)
            charInstance.secondaryList.search.setPointsFromClass(10)
            charInstance.secondaryList.track.setPointsFromClass(10)
            charInstance.secondaryList.trapLore.setPointsFromClass(5)
            charInstance.secondaryList.animals.setPointsFromClass(5)
            charInstance.secondaryList.herbalLore.setPointsFromClass(5)

            charInstance.secondaryList.trapLore.setDevelopmentDeduction(1)
            charInstance.secondaryList.herbalLore.setDevelopmentDeduction(1)
            charInstance.secondaryList.animals.setDevelopmentDeduction(2)
            charInstance.secondaryList.medic.setDevelopmentDeduction(1)
        },
        {
            charInstance.setAttackPerLevel(5)

            charInstance.secondaryList.notice.setPointsFromClass(0)
            charInstance.secondaryList.search.setPointsFromClass(0)
            charInstance.secondaryList.track.setPointsFromClass(0)
            charInstance.secondaryList.trapLore.setPointsFromClass(0)
            charInstance.secondaryList.animals.setPointsFromClass(0)
            charInstance.secondaryList.herbalLore.setPointsFromClass(0)

            charInstance.secondaryList.trapLore.setDevelopmentDeduction(-1)
            charInstance.secondaryList.herbalLore.setDevelopmentDeduction(-1)
            charInstance.secondaryList.animals.setDevelopmentDeduction(-2)
            charInstance.secondaryList.medic.setDevelopmentDeduction(-1)
        }
    )

    val shadow = CharClass(
        ClassName.shadow, listOf(Archetype.Fighter, Archetype.Prowler),
        20, 5, 10, 25, 3,
        0.60, 2, 3, 2, 2, 2, 20,
        0.50, 3, 70, 3, 3, 3, 3, 3,
        0.50, 20, 3,
        2, 2, 2, 3, 2, 2, 2,
        {
            charInstance.setAttackPerLevel(5)
            charInstance.setDodgePerLevel(5)

            charInstance.secondaryList.notice.setPointsFromClass(10)
            charInstance.secondaryList.search.setPointsFromClass(10)
            charInstance.secondaryList.hide.setPointsFromClass(10)
            charInstance.secondaryList.stealth.setPointsFromClass(10)
        },
        {
            charInstance.setAttackPerLevel(0)
            charInstance.setDodgePerLevel(0)

            charInstance.secondaryList.notice.setPointsFromClass(0)
            charInstance.secondaryList.search.setPointsFromClass(0)
            charInstance.secondaryList.hide.setPointsFromClass(0)
            charInstance.secondaryList.stealth.setPointsFromClass(0)
        }
    )

    val thief = CharClass(
        ClassName.thief, listOf(Archetype.Prowler),
        20, 5, 10, 20, 3,
        0.50, 2, 3, 2, 3, 2, 25,
        0.50, 3, 70, 3, 3, 3, 3, 3,
        0.50, 20, 3,
        1, 2, 2, 3, 3, 1, 2,
        {
            charInstance.setDodgePerLevel(5)

            charInstance.secondaryList.notice.setPointsFromClass(5)
            charInstance.secondaryList.search.setPointsFromClass(5)
            charInstance.secondaryList.hide.setPointsFromClass(5)
            charInstance.secondaryList.stealth.setPointsFromClass(5)
            charInstance.secondaryList.trapLore.setPointsFromClass(5)
            charInstance.secondaryList.sleightHand.setPointsFromClass(5)
            charInstance.secondaryList.theft.setPointsFromClass(10)

            charInstance.secondaryList.appraise.setDevelopmentDeduction(2)
        },
        {
            charInstance.setDodgePerLevel(0)

            charInstance.secondaryList.notice.setPointsFromClass(0)
            charInstance.secondaryList.search.setPointsFromClass(0)
            charInstance.secondaryList.hide.setPointsFromClass(0)
            charInstance.secondaryList.stealth.setPointsFromClass(0)
            charInstance.secondaryList.trapLore.setPointsFromClass(0)
            charInstance.secondaryList.sleightHand.setPointsFromClass(0)
            charInstance.secondaryList.theft.setPointsFromClass(0)

            charInstance.secondaryList.appraise.setDevelopmentDeduction(-2)
        }
    )

    val assassin = CharClass(
        ClassName.assassin, listOf(Archetype.Prowler),
        20, 5, 10, 20, 3,
        0.50, 2, 3, 2, 3, 2, 25,
        0.50, 3, 70, 3, 3, 3, 3, 3,
        0.50, 20, 3,
        2, 2, 1, 3, 3, 2, 2,
        {
            charInstance.setAttackPerLevel(5)

            charInstance.secondaryList.notice.setPointsFromClass(10)
            charInstance.secondaryList.search.setPointsFromClass(10)
            charInstance.secondaryList.hide.setPointsFromClass(10)
            charInstance.secondaryList.stealth.setPointsFromClass(10)
            charInstance.secondaryList.poisons.setPointsFromClass(10)
            charInstance.secondaryList.composure.setPointsFromClass(10)
            charInstance.secondaryList.trapLore.setPointsFromClass(10)

            charInstance.secondaryList.stealth.setDevelopmentDeduction(1)
            charInstance.secondaryList.composure.setDevelopmentDeduction(1)
            charInstance.secondaryList.memorize.setDevelopmentDeduction(1)
        },
        {
            charInstance.setAttackPerLevel(0)

            charInstance.secondaryList.notice.setPointsFromClass(0)
            charInstance.secondaryList.search.setPointsFromClass(0)
            charInstance.secondaryList.hide.setPointsFromClass(0)
            charInstance.secondaryList.stealth.setPointsFromClass(0)
            charInstance.secondaryList.poisons.setPointsFromClass(0)
            charInstance.secondaryList.composure.setPointsFromClass(0)
            charInstance.secondaryList.trapLore.setPointsFromClass(0)

            charInstance.secondaryList.stealth.setDevelopmentDeduction(-1)
            charInstance.secondaryList.composure.setDevelopmentDeduction(-1)
            charInstance.secondaryList.memorize.setDevelopmentDeduction(-1)
        }
    )

    val wizard = CharClass(
        ClassName.wizard, listOf(Archetype.Mystic),
        20, 5, 5, 10, 3,
        0.50, 3, 3, 2, 3, 3, 30,
        0.60, 1, 50, 2, 2, 2, 2, 2,
        0.50, 20, 3,
        2, 2, 2, 2, 3, 2, 2,
        {
            charInstance.secondaryList.magicAppraise.setPointsFromClass(10)
            charInstance.secondaryList.occult.setPointsFromClass(5)

            charInstance.secondaryList.magicAppraise.setDevelopmentDeduction(1)

            charInstance.magic.setZeonPerLevel(100)
        },
        {
            charInstance.secondaryList.magicAppraise.setPointsFromClass(0)
            charInstance.secondaryList.occult.setPointsFromClass(0)

            charInstance.secondaryList.magicAppraise.setDevelopmentDeduction(-1)

            charInstance.magic.setZeonPerLevel(0)
        }
    )

    val warlock = CharClass(
        ClassName.warlock, listOf(Archetype.Fighter, Archetype.Mystic),
        20, 10, 5, 20, 3,
        0.50, 2, 2, 2, 2, 2, 25,
        0.50, 1, 50, 2, 2, 2, 2, 2,
        0.50, 20, 3,
        2, 2, 2, 2, 2, 2, 2,
        {
            charInstance.setAttackPerLevel(5)
            charInstance.setBlockPerLevel(5)
            charInstance.setDodgePerLevel(5)

            charInstance.secondaryList.magicAppraise.setPointsFromClass(5)

            charInstance.magic.setZeonPerLevel(20)
        },
        {
            charInstance.setAttackPerLevel(0)
            charInstance.setBlockPerLevel(0)
            charInstance.setDodgePerLevel(0)

            charInstance.secondaryList.magicAppraise.setPointsFromClass(0)

            charInstance.magic.setZeonPerLevel(0)
        }
    )

    val illusionist = CharClass(
        ClassName.illusionist, listOf(Archetype.Mystic, Archetype.Prowler),
        20, 5, 5, 20, 3,
        0.50, 3, 3, 2, 3, 2, 25,
        0.60, 1, 60, 2, 3, 3, 3, 3,
        0.50, 20, 3,
        2, 2, 2, 2, 3, 2, 2,
        {
            charInstance.secondaryList.magicAppraise.setPointsFromClass(5)
            charInstance.secondaryList.stealth.setPointsFromClass(10)
            charInstance.secondaryList.hide.setPointsFromClass(10)
            charInstance.secondaryList.sleightHand.setPointsFromClass(10)
            charInstance.secondaryList.disguise.setPointsFromClass(5)
            charInstance.secondaryList.theft.setPointsFromClass(5)
            charInstance.secondaryList.persuasion.setPointsFromClass(5)

            charInstance.secondaryList.sleightHand.setDevelopmentDeduction(1)
            charInstance.secondaryList.persuasion.setDevelopmentDeduction(1)

            charInstance.magic.setZeonPerLevel(75)
        },
        {
            charInstance.secondaryList.magicAppraise.setPointsFromClass(0)
            charInstance.secondaryList.stealth.setPointsFromClass(0)
            charInstance.secondaryList.hide.setPointsFromClass(0)
            charInstance.secondaryList.sleightHand.setPointsFromClass(0)
            charInstance.secondaryList.disguise.setPointsFromClass(0)
            charInstance.secondaryList.theft.setPointsFromClass(0)
            charInstance.secondaryList.persuasion.setPointsFromClass(0)

            charInstance.secondaryList.sleightHand.setDevelopmentDeduction(-1)
            charInstance.secondaryList.persuasion.setDevelopmentDeduction(-1)

            charInstance.magic.setZeonPerLevel(0)
        }
    )

    val wizMentalist = CharClass(
        ClassName.wizMentalist, listOf(Archetype.Mystic, Archetype.Psychic),
        20, 5, 5, 10, 1,
        0.50, 3, 3, 2, 3, 3, 30,
        0.50, 1, 50, 2, 2, 2, 2, 2,
        0.50, 10, 2,
        2, 2, 2, 2, 3, 2, 2,
        {
            charInstance.secondaryList.magicAppraise.setPointsFromClass(10)
            charInstance.secondaryList.occult.setPointsFromClass(5)

            charInstance.magic.setZeonPerLevel(100)
        },
        {
            charInstance.secondaryList.magicAppraise.setPointsFromClass(0)
            charInstance.secondaryList.occult.setPointsFromClass(0)

            charInstance.magic.setZeonPerLevel(0)
        }
    )

    val summoner = CharClass(
        ClassName.summoner, listOf(Archetype.Mystic),
        20, 5, 5, 10, 3,
        0.50, 3, 3, 2, 3, 3, 30,
        0.60, 1, 60, 3, 1, 1, 1, 1,
        0.50, 20, 3,
        2, 2, 2, 2, 3, 2, 2,
        {
            charInstance.secondaryList.magicAppraise.setPointsFromClass(5)
            charInstance.secondaryList.occult.setPointsFromClass(10)

            charInstance.secondaryList.occult.setDevelopmentDeduction(1)

            charInstance.magic.setZeonPerLevel(50)

            charInstance.summoning.setSummonPerLevel(10)
            charInstance.summoning.setControlPerLevel(10)
            charInstance.summoning.setBindPerLevel(10)
            charInstance.summoning.setBanishPerLevel(10)
        },
        {
            charInstance.secondaryList.magicAppraise.setPointsFromClass(0)
            charInstance.secondaryList.occult.setPointsFromClass(0)

            charInstance.secondaryList.occult.setDevelopmentDeduction(-1)

            charInstance.magic.setZeonPerLevel(0)

            charInstance.summoning.setSummonPerLevel(0)
            charInstance.summoning.setControlPerLevel(0)
            charInstance.summoning.setBindPerLevel(0)
            charInstance.summoning.setBanishPerLevel(0)
        }
    )

    val warSummoner = CharClass(
        ClassName.warSummoner, listOf(Archetype.Fighter, Archetype.Mystic),
        20, 10, 5, 20, 3,
        0.50, 2, 2, 2, 2, 2, 20,
        0.50, 1, 60, 3, 1, 1, 1, 1,
        0.50, 20, 3,
        2, 2, 2, 2, 2, 2, 2,
        {
            charInstance.setAttackPerLevel(5)
            charInstance.setBlockPerLevel(5)
            charInstance.setDodgePerLevel(5)

            charInstance.secondaryList.occult.setPointsFromClass(5)

            charInstance.magic.setZeonPerLevel(20)

            charInstance.summoning.setSummonPerLevel(5)
            charInstance.summoning.setControlPerLevel(5)
            charInstance.summoning.setBindPerLevel(5)
            charInstance.summoning.setBanishPerLevel(5)
        },
        {
            charInstance.setAttackPerLevel(0)
            charInstance.setBlockPerLevel(0)
            charInstance.setDodgePerLevel(0)

            charInstance.secondaryList.occult.setPointsFromClass(0)

            charInstance.magic.setZeonPerLevel(0)

            charInstance.summoning.setSummonPerLevel(0)
            charInstance.summoning.setControlPerLevel(0)
            charInstance.summoning.setBindPerLevel(0)
            charInstance.summoning.setBanishPerLevel(0)
        }
    )

    val mentalist = CharClass(
        ClassName.mentalist, listOf(Archetype.Psychic),
        20, 5, 5, 10, 1,
        0.50, 3, 3, 2, 3, 3, 30,
        0.50, 3, 70, 3, 3, 3, 3, 3,
        0.60, 10, 2,
        2, 2, 2, 2, 3, 2, 2,
        {}, {}
    )

    val warMentalist = CharClass(
        ClassName.warMentalist, listOf(Archetype.Fighter, Archetype.Psychic),
        20, 10, 5, 20, 1,
        0.50, 2, 2, 2, 2, 2, 25,
        0.50, 3, 70, 3, 3, 3, 3, 3,
        0.50, 15, 2,
        2, 2, 2, 3, 2, 2, 2,
        {
            charInstance.setAttackPerLevel(5)
            charInstance.setBlockPerLevel(5)
            charInstance.setDodgePerLevel(5)
        },
        {
            charInstance.setAttackPerLevel(0)
            charInstance.setBlockPerLevel(0)
            charInstance.setDodgePerLevel(0)
        }
    )

    val freelancer = CharClass(
        ClassName.freelancer, listOf(Archetype.Novel),
        20, 5, 5, 20, 2,
        0.60, 2, 2, 2, 2, 2, 20,
        0.60, 2, 60, 2, 2, 2, 2, 2,
        0.60, 20, 2,
        2, 2, 2, 2, 2, 2, 2,
        {charInstance.magic.setZeonPerLevel(10)},
        {charInstance.magic.setZeonPerLevel(0)}
    )

    val allClasses = listOf(freelancer, warrior, acroWarrior, paladin, darkPaladin, weaponMaster, technician, tao,
    ranger, shadow, thief, assassin, wizard, warlock, illusionist, wizMentalist, summoner, warSummoner,
    mentalist, warMentalist)

    fun findClass(input: ClassName): CharClass?{
        allClasses.forEach{
            if(it.heldClass == input) return it
        }

        return null
    }
}