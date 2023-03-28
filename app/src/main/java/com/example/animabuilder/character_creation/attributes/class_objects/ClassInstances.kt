package com.example.animabuilder.character_creation.attributes.class_objects

import com.example.animabuilder.character_creation.BaseCharacter
import java.io.Serializable

/**
 * Record of classes available to the character.
 *
 * @param charInstance object that holds the character's dataa
 */
class ClassInstances(charInstance: BaseCharacter): Serializable {
    val warrior =
        CharClass(
            "Warrior", listOf(Archetype.Fighter),
            15, 15, 5, 25, 3,
            0.6, 2, 2, 2, 2, 2, 20,
            0.5, 3, 70, 3, 3, 3, 3, 3,
            0.5, 20, 3,
            2, 2, 2, 3, 2, 2, 2,
            {
                charInstance.combat.attack.setPointPerLevel(5)
                charInstance.combat.block.setPointPerLevel(5)
                charInstance.combat.wearArmor.setPointPerLevel(5)

                charInstance.secondaryList.strengthFeat.setClassPointsPerLevel(5)

                charInstance.secondaryList.strengthFeat.setDevelopmentDeduction(1)
            },
            {
                charInstance.combat.attack.setPointPerLevel(0)
                charInstance.combat.block.setPointPerLevel(0)
                charInstance.combat.wearArmor.setPointPerLevel(0)

                charInstance.secondaryList.strengthFeat.setClassPointsPerLevel(0)

                charInstance.secondaryList.strengthFeat.setDevelopmentDeduction(-1)
            }
        )

    val acroWarrior = CharClass(
        "Acrobatic Warrior", listOf(Archetype.Fighter),
        20, 10, 10, 25, 3,
        0.6, 2, 3, 2, 2, 2, 20,
        0.5, 3, 70, 3, 3, 3, 3, 3,
        0.5, 20, 3,
        2, 2, 2, 3, 2, 2, 2,
        {
            charInstance.combat.attack.setPointPerLevel(5)
            charInstance.combat.dodge.setPointPerLevel(5)

            charInstance.secondaryList.acrobatics.setClassPointsPerLevel(10)
            charInstance.secondaryList.jump.setClassPointsPerLevel(10)
            charInstance.secondaryList.athletics.setClassPointsPerLevel(10)
            charInstance.secondaryList.sleightHand.setClassPointsPerLevel(10)
            charInstance.secondaryList.style.setClassPointsPerLevel(10)
        },
        {
            charInstance.combat.attack.setPointPerLevel(0)
            charInstance.combat.dodge.setPointPerLevel(0)

            charInstance.secondaryList.acrobatics.setClassPointsPerLevel(0)
            charInstance.secondaryList.jump.setClassPointsPerLevel(0)
            charInstance.secondaryList.athletics.setClassPointsPerLevel(0)
            charInstance.secondaryList.sleightHand.setClassPointsPerLevel(0)
            charInstance.secondaryList.style.setClassPointsPerLevel(0)
        }
    )

    val paladin = CharClass(
        "Paladin", listOf(Archetype.Fighter),
        15, 15, 5, 20, 3,
        0.60, 2, 2, 2, 2, 2, 20,
        0.50, 2, 60, 3, 3, 3, 3, 1,
        0.50, 20, 3,
        2, 1, 2, 2, 2, 3, 2,
        {
            charInstance.combat.block.setPointPerLevel(5)
            charInstance.combat.wearArmor.setPointPerLevel(10)

            charInstance.secondaryList.leadership.setClassPointsPerLevel(10)
            charInstance.secondaryList.resistPain.setClassPointsPerLevel(10)
            charInstance.secondaryList.style.setClassPointsPerLevel(5)

            charInstance.secondaryList.resistPain.setDevelopmentDeduction(1)

            charInstance.magic.setZeonPerLevel(20)

            charInstance.summoning.banish.setPointsPerLevel(10)
        },
        {
            charInstance.combat.block.setPointPerLevel(0)
            charInstance.combat.wearArmor.setPointPerLevel(0)

            charInstance.secondaryList.leadership.setClassPointsPerLevel(0)
            charInstance.secondaryList.resistPain.setClassPointsPerLevel(0)
            charInstance.secondaryList.style.setClassPointsPerLevel(0)

            charInstance.secondaryList.resistPain.setDevelopmentDeduction(-1)

            charInstance.magic.setZeonPerLevel(0)

            charInstance.summoning.banish.setPointsPerLevel(0)
        }
    )

    val darkPaladin = CharClass(
        "Dark Paladin", listOf(Archetype.Fighter),
        15, 15, 5, 20, 3,
        0.60, 2, 2, 2, 2, 2, 20,
        0.50, 2, 60, 3, 3, 1, 3, 3,
        0.50, 20, 3,
        2, 1, 2, 2, 2, 2, 2,
        {
            charInstance.combat.attack.setPointPerLevel(5)
            charInstance.combat.wearArmor.setPointPerLevel(5)

            charInstance.secondaryList.intimidate.setClassPointsPerLevel(10)
            charInstance.secondaryList.composure.setClassPointsPerLevel(10)
            charInstance.secondaryList.style.setClassPointsPerLevel(5)
            charInstance.secondaryList.persuasion.setClassPointsPerLevel(5)

            charInstance.secondaryList.composure.setDevelopmentDeduction(1)

            charInstance.magic.setZeonPerLevel(20)

            charInstance.summoning.control.setPointsPerLevel(10)
        },
        {
            charInstance.combat.attack.setPointPerLevel(0)
            charInstance.combat.wearArmor.setPointPerLevel(0)

            charInstance.secondaryList.intimidate.setClassPointsPerLevel(0)
            charInstance.secondaryList.composure.setClassPointsPerLevel(0)
            charInstance.secondaryList.style.setClassPointsPerLevel(0)
            charInstance.secondaryList.persuasion.setClassPointsPerLevel(0)

            charInstance.secondaryList.composure.setDevelopmentDeduction(-1)

            charInstance.magic.setZeonPerLevel(0)

            charInstance.summoning.control.setPointsPerLevel(0)
        }
    )

    val weaponMaster = CharClass(
        "Weaponsmaster", listOf(Archetype.Fighter),
        10, 20, 5, 10, 3,
        0.60, 2, 2, 2, 1, 3, 30,
        0.50, 3, 70, 3, 3, 3, 3, 3,
        0.50, 20, 3,
        2, 2, 2, 3, 1, 3, 2,
        {
            charInstance.combat.attack.setPointPerLevel(5)
            charInstance.combat.block.setPointPerLevel(5)
            charInstance.combat.wearArmor.setPointPerLevel(10)

            charInstance.secondaryList.strengthFeat.setClassPointsPerLevel(5)
        },
        {
            charInstance.combat.attack.setPointPerLevel(0)
            charInstance.combat.block.setPointPerLevel(0)
            charInstance.combat.wearArmor.setPointPerLevel(0)

            charInstance.secondaryList.strengthFeat.setClassPointsPerLevel(0)
        }
    )

    val technician = CharClass(
        "Technician", listOf(Archetype.Domine),
        20, 5, 5, 50, 3,
        0.60, 2, 2, 2, 2, 1, 10,
        0.50, 3, 70, 3, 3, 3, 3, 3,
        0.50, 20, 3,
        2, 2, 2, 3, 2, 2, 2,
        {charInstance.combat.attack.setPointPerLevel(5)},
        {charInstance.combat.attack.setPointPerLevel(0)}
    )

    val tao = CharClass(
        "Tao", listOf(Archetype.Fighter, Archetype.Domine),
        20, 10, 5, 30, 3,
        0.60, 2, 2, 2, 2, 2, 15,
        0.50, 3, 70, 3, 3, 3, 3, 3,
        0.50, 20, 3,
        2, 2, 2, 3, 2, 2, 2,
        {charInstance.secondaryList.style.setClassPointsPerLevel(5)},
        {charInstance.secondaryList.style.setClassPointsPerLevel(0)}
    )

    val ranger = CharClass(
        "Ranger", listOf(Archetype.Fighter, Archetype.Prowler),
        20, 10, 5, 20, 3,
        0.60, 2, 2, 2, 2, 2, 25,
        0.50, 3, 70, 3, 3, 3, 3, 3,
        0.50, 20, 3,
        2, 2, 1, 3, 3, 2, 2,
        {
            charInstance.combat.attack.setPointPerLevel(5)

            charInstance.secondaryList.notice.setClassPointsPerLevel(10)
            charInstance.secondaryList.search.setClassPointsPerLevel(10)
            charInstance.secondaryList.track.setClassPointsPerLevel(10)
            charInstance.secondaryList.trapLore.setClassPointsPerLevel(5)
            charInstance.secondaryList.animals.setClassPointsPerLevel(5)
            charInstance.secondaryList.herbalLore.setClassPointsPerLevel(5)

            charInstance.secondaryList.trapLore.setDevelopmentDeduction(1)
            charInstance.secondaryList.herbalLore.setDevelopmentDeduction(1)
            charInstance.secondaryList.animals.setDevelopmentDeduction(2)
            charInstance.secondaryList.medic.setDevelopmentDeduction(1)
        },
        {
            charInstance.combat.attack.setPointPerLevel(5)

            charInstance.secondaryList.notice.setClassPointsPerLevel(0)
            charInstance.secondaryList.search.setClassPointsPerLevel(0)
            charInstance.secondaryList.track.setClassPointsPerLevel(0)
            charInstance.secondaryList.trapLore.setClassPointsPerLevel(0)
            charInstance.secondaryList.animals.setClassPointsPerLevel(0)
            charInstance.secondaryList.herbalLore.setClassPointsPerLevel(0)

            charInstance.secondaryList.trapLore.setDevelopmentDeduction(-1)
            charInstance.secondaryList.herbalLore.setDevelopmentDeduction(-1)
            charInstance.secondaryList.animals.setDevelopmentDeduction(-2)
            charInstance.secondaryList.medic.setDevelopmentDeduction(-1)
        }
    )

    val shadow = CharClass(
        "Shadow", listOf(Archetype.Fighter, Archetype.Prowler),
        20, 5, 10, 25, 3,
        0.60, 2, 3, 2, 2, 2, 20,
        0.50, 3, 70, 3, 3, 3, 3, 3,
        0.50, 20, 3,
        2, 2, 2, 3, 2, 2, 2,
        {
            charInstance.combat.attack.setPointPerLevel(5)
            charInstance.combat.dodge.setPointPerLevel(5)

            charInstance.secondaryList.notice.setClassPointsPerLevel(10)
            charInstance.secondaryList.search.setClassPointsPerLevel(10)
            charInstance.secondaryList.hide.setClassPointsPerLevel(10)
            charInstance.secondaryList.stealth.setClassPointsPerLevel(10)
        },
        {
            charInstance.combat.attack.setPointPerLevel(0)
            charInstance.combat.dodge.setPointPerLevel(0)

            charInstance.secondaryList.notice.setClassPointsPerLevel(0)
            charInstance.secondaryList.search.setClassPointsPerLevel(0)
            charInstance.secondaryList.hide.setClassPointsPerLevel(0)
            charInstance.secondaryList.stealth.setClassPointsPerLevel(0)
        }
    )

    val thief = CharClass(
        "Thief", listOf(Archetype.Prowler),
        20, 5, 10, 20, 3,
        0.50, 2, 3, 2, 3, 2, 25,
        0.50, 3, 70, 3, 3, 3, 3, 3,
        0.50, 20, 3,
        1, 2, 2, 3, 3, 1, 2,
        {
            charInstance.combat.dodge.setPointPerLevel(5)

            charInstance.secondaryList.notice.setClassPointsPerLevel(5)
            charInstance.secondaryList.search.setClassPointsPerLevel(5)
            charInstance.secondaryList.hide.setClassPointsPerLevel(5)
            charInstance.secondaryList.stealth.setClassPointsPerLevel(5)
            charInstance.secondaryList.trapLore.setClassPointsPerLevel(5)
            charInstance.secondaryList.sleightHand.setClassPointsPerLevel(5)
            charInstance.secondaryList.theft.setClassPointsPerLevel(10)

            charInstance.secondaryList.appraise.setDevelopmentDeduction(2)
        },
        {
            charInstance.combat.dodge.setPointPerLevel(0)

            charInstance.secondaryList.notice.setClassPointsPerLevel(0)
            charInstance.secondaryList.search.setClassPointsPerLevel(0)
            charInstance.secondaryList.hide.setClassPointsPerLevel(0)
            charInstance.secondaryList.stealth.setClassPointsPerLevel(0)
            charInstance.secondaryList.trapLore.setClassPointsPerLevel(0)
            charInstance.secondaryList.sleightHand.setClassPointsPerLevel(0)
            charInstance.secondaryList.theft.setClassPointsPerLevel(0)

            charInstance.secondaryList.appraise.setDevelopmentDeduction(-2)
        }
    )

    val assassin = CharClass(
        "Assassin", listOf(Archetype.Prowler),
        20, 5, 10, 20, 3,
        0.50, 2, 3, 2, 3, 2, 25,
        0.50, 3, 70, 3, 3, 3, 3, 3,
        0.50, 20, 3,
        2, 2, 1, 3, 3, 2, 2,
        {
            charInstance.combat.attack.setPointPerLevel(5)

            charInstance.secondaryList.notice.setClassPointsPerLevel(10)
            charInstance.secondaryList.search.setClassPointsPerLevel(10)
            charInstance.secondaryList.hide.setClassPointsPerLevel(10)
            charInstance.secondaryList.stealth.setClassPointsPerLevel(10)
            charInstance.secondaryList.poisons.setClassPointsPerLevel(10)
            charInstance.secondaryList.composure.setClassPointsPerLevel(10)
            charInstance.secondaryList.trapLore.setClassPointsPerLevel(10)

            charInstance.secondaryList.stealth.setDevelopmentDeduction(1)
            charInstance.secondaryList.composure.setDevelopmentDeduction(1)
            charInstance.secondaryList.memorize.setDevelopmentDeduction(1)
        },
        {
            charInstance.combat.attack.setPointPerLevel(0)

            charInstance.secondaryList.notice.setClassPointsPerLevel(0)
            charInstance.secondaryList.search.setClassPointsPerLevel(0)
            charInstance.secondaryList.hide.setClassPointsPerLevel(0)
            charInstance.secondaryList.stealth.setClassPointsPerLevel(0)
            charInstance.secondaryList.poisons.setClassPointsPerLevel(0)
            charInstance.secondaryList.composure.setClassPointsPerLevel(0)
            charInstance.secondaryList.trapLore.setClassPointsPerLevel(0)

            charInstance.secondaryList.stealth.setDevelopmentDeduction(-1)
            charInstance.secondaryList.composure.setDevelopmentDeduction(-1)
            charInstance.secondaryList.memorize.setDevelopmentDeduction(-1)
        }
    )

    val wizard = CharClass(
        "Wizard", listOf(Archetype.Mystic),
        20, 5, 5, 10, 3,
        0.50, 3, 3, 2, 3, 3, 30,
        0.60, 1, 50, 2, 2, 2, 2, 2,
        0.50, 20, 3,
        2, 2, 2, 2, 3, 2, 2,
        {
            charInstance.secondaryList.magicAppraise.setClassPointsPerLevel(10)
            charInstance.secondaryList.occult.setClassPointsPerLevel(5)

            charInstance.secondaryList.magicAppraise.setDevelopmentDeduction(1)

            charInstance.magic.setZeonPerLevel(100)
        },
        {
            charInstance.secondaryList.magicAppraise.setClassPointsPerLevel(0)
            charInstance.secondaryList.occult.setClassPointsPerLevel(0)

            charInstance.secondaryList.magicAppraise.setDevelopmentDeduction(-1)

            charInstance.magic.setZeonPerLevel(0)
        }
    )

    val warlock = CharClass(
        "Warlock", listOf(Archetype.Fighter, Archetype.Mystic),
        20, 10, 5, 20, 3,
        0.50, 2, 2, 2, 2, 2, 25,
        0.50, 1, 50, 2, 2, 2, 2, 2,
        0.50, 20, 3,
        2, 2, 2, 2, 2, 2, 2,
        {
            charInstance.combat.attack.setPointPerLevel(5)
            charInstance.combat.block.setPointPerLevel(5)
            charInstance.combat.dodge.setPointPerLevel(5)

            charInstance.secondaryList.magicAppraise.setClassPointsPerLevel(5)

            charInstance.magic.setZeonPerLevel(20)
        },
        {
            charInstance.combat.attack.setPointPerLevel(0)
            charInstance.combat.block.setPointPerLevel(0)
            charInstance.combat.dodge.setPointPerLevel(0)

            charInstance.secondaryList.magicAppraise.setClassPointsPerLevel(0)

            charInstance.magic.setZeonPerLevel(0)
        }
    )

    val illusionist = CharClass(
        "Illusionist", listOf(Archetype.Mystic, Archetype.Prowler),
        20, 5, 5, 20, 3,
        0.50, 3, 3, 2, 3, 2, 25,
        0.60, 1, 60, 2, 3, 3, 3, 3,
        0.50, 20, 3,
        2, 2, 2, 2, 3, 2, 2,
        {
            charInstance.secondaryList.magicAppraise.setClassPointsPerLevel(5)
            charInstance.secondaryList.stealth.setClassPointsPerLevel(10)
            charInstance.secondaryList.hide.setClassPointsPerLevel(10)
            charInstance.secondaryList.sleightHand.setClassPointsPerLevel(10)
            charInstance.secondaryList.disguise.setClassPointsPerLevel(5)
            charInstance.secondaryList.theft.setClassPointsPerLevel(5)
            charInstance.secondaryList.persuasion.setClassPointsPerLevel(5)

            charInstance.secondaryList.sleightHand.setDevelopmentDeduction(1)
            charInstance.secondaryList.persuasion.setDevelopmentDeduction(1)

            charInstance.magic.setZeonPerLevel(75)
        },
        {
            charInstance.secondaryList.magicAppraise.setClassPointsPerLevel(0)
            charInstance.secondaryList.stealth.setClassPointsPerLevel(0)
            charInstance.secondaryList.hide.setClassPointsPerLevel(0)
            charInstance.secondaryList.sleightHand.setClassPointsPerLevel(0)
            charInstance.secondaryList.disguise.setClassPointsPerLevel(0)
            charInstance.secondaryList.theft.setClassPointsPerLevel(0)
            charInstance.secondaryList.persuasion.setClassPointsPerLevel(0)

            charInstance.secondaryList.sleightHand.setDevelopmentDeduction(-1)
            charInstance.secondaryList.persuasion.setDevelopmentDeduction(-1)

            charInstance.magic.setZeonPerLevel(0)
        }
    )

    val wizMentalist = CharClass(
        "Wizard Mentalist", listOf(Archetype.Mystic, Archetype.Psychic),
        20, 5, 5, 10, 1,
        0.50, 3, 3, 2, 3, 3, 30,
        0.50, 1, 50, 2, 2, 2, 2, 2,
        0.50, 10, 2,
        2, 2, 2, 2, 3, 2, 2,
        {
            charInstance.secondaryList.magicAppraise.setClassPointsPerLevel(10)
            charInstance.secondaryList.occult.setClassPointsPerLevel(5)

            charInstance.magic.setZeonPerLevel(100)
        },
        {
            charInstance.secondaryList.magicAppraise.setClassPointsPerLevel(0)
            charInstance.secondaryList.occult.setClassPointsPerLevel(0)

            charInstance.magic.setZeonPerLevel(0)
        }
    )

    val summoner = CharClass(
        "Summoner", listOf(Archetype.Mystic),
        20, 5, 5, 10, 3,
        0.50, 3, 3, 2, 3, 3, 30,
        0.60, 1, 60, 3, 1, 1, 1, 1,
        0.50, 20, 3,
        2, 2, 2, 2, 3, 2, 2,
        {
            charInstance.secondaryList.magicAppraise.setClassPointsPerLevel(5)
            charInstance.secondaryList.occult.setClassPointsPerLevel(10)

            charInstance.secondaryList.occult.setDevelopmentDeduction(1)

            charInstance.magic.setZeonPerLevel(50)

            charInstance.summoning.summon.setPointsPerLevel(10)
            charInstance.summoning.control.setPointsPerLevel(10)
            charInstance.summoning.bind.setPointsPerLevel(10)
            charInstance.summoning.banish.setPointsPerLevel(10)
        },
        {
            charInstance.secondaryList.magicAppraise.setClassPointsPerLevel(0)
            charInstance.secondaryList.occult.setClassPointsPerLevel(0)

            charInstance.secondaryList.occult.setDevelopmentDeduction(-1)

            charInstance.magic.setZeonPerLevel(0)

            charInstance.summoning.summon.setPointsPerLevel(0)
            charInstance.summoning.control.setPointsPerLevel(0)
            charInstance.summoning.bind.setPointsPerLevel(0)
            charInstance.summoning.banish.setPointsPerLevel(0)
        }
    )

    val warSummoner = CharClass(
        "Warrior Summoner", listOf(Archetype.Fighter, Archetype.Mystic),
        20, 10, 5, 20, 3,
        0.50, 2, 2, 2, 2, 2, 20,
        0.50, 1, 60, 3, 1, 1, 1, 1,
        0.50, 20, 3,
        2, 2, 2, 2, 2, 2, 2,
        {
            charInstance.combat.attack.setPointPerLevel(5)
            charInstance.combat.block.setPointPerLevel(5)
            charInstance.combat.dodge.setPointPerLevel(5)

            charInstance.secondaryList.occult.setClassPointsPerLevel(5)

            charInstance.magic.setZeonPerLevel(20)

            charInstance.summoning.summon.setPointsPerLevel(5)
            charInstance.summoning.control.setPointsPerLevel(5)
            charInstance.summoning.bind.setPointsPerLevel(5)
            charInstance.summoning.banish.setPointsPerLevel(5)
        },
        {
            charInstance.combat.attack.setPointPerLevel(0)
            charInstance.combat.block.setPointPerLevel(0)
            charInstance.combat.dodge.setPointPerLevel(0)

            charInstance.secondaryList.occult.setClassPointsPerLevel(0)

            charInstance.magic.setZeonPerLevel(0)

            charInstance.summoning.summon.setPointsPerLevel(0)
            charInstance.summoning.control.setPointsPerLevel(0)
            charInstance.summoning.bind.setPointsPerLevel(0)
            charInstance.summoning.banish.setPointsPerLevel(0)
        }
    )

    val mentalist = CharClass(
        "Mentalist", listOf(Archetype.Psychic),
        20, 5, 5, 10, 1,
        0.50, 3, 3, 2, 3, 3, 30,
        0.50, 3, 70, 3, 3, 3, 3, 3,
        0.60, 10, 2,
        2, 2, 2, 2, 3, 2, 2,
        {}, {}
    )

    val warMentalist = CharClass(
        "Warrior Mentalist", listOf(Archetype.Fighter, Archetype.Psychic),
        20, 10, 5, 20, 1,
        0.50, 2, 2, 2, 2, 2, 25,
        0.50, 3, 70, 3, 3, 3, 3, 3,
        0.50, 15, 2,
        2, 2, 2, 3, 2, 2, 2,
        {
            charInstance.combat.attack.setPointPerLevel(5)
            charInstance.combat.block.setPointPerLevel(5)
            charInstance.combat.dodge.setPointPerLevel(5)
        },
        {
            charInstance.combat.attack.setPointPerLevel(0)
            charInstance.combat.block.setPointPerLevel(0)
            charInstance.combat.dodge.setPointPerLevel(0)
        }
    )

    val freelancer = CharClass(
        "Freelancer", listOf(Archetype.Novel),
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

    /**
     * Finds the class based on the inputted class name
     *
     * @param input desired class to find
     * @return character class to find or null indicator
     */
    fun findClass(input: String): CharClass?{
        allClasses.forEach{
            if(it.heldClass == input) return it
        }

        return null
    }
}