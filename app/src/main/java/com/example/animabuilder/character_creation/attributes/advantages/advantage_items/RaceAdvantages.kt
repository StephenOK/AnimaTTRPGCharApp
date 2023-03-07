package com.example.animabuilder.character_creation.attributes.advantages.advantage_items

import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.attributes.advantages.advantage_types.Advantage
import com.example.animabuilder.character_creation.attributes.advantages.advantage_types.RacialAdvantage
import java.io.Serializable

class RaceAdvantages(private val charInstance: BaseCharacter) : Serializable {
    private val exceptionalResistancesSylvain = RacialAdvantage(
        "Exceptional Resistances (Sylvain)",
        "As a reflection of their former existence, the Sylvain Nephilim are exceptionally " +
                "resistant to magic, psychic attacks, and disease. They apply a bonus of +10 to " +
                "their Magic Resistance (MR) and Psychic Resistance (PsR), a +20 to Disease " +
                "Resistance (DR), and a +5 to Physical Resistance (PhR) and Venom Resistance (VR). " +
                "In addition, a Nephilim cannot choose the following Disadvantages: Sickly, " +
                "Serious Illness, or Susceptible to Magic.",
        {_, _ ->
            charInstance.combat.physicalRes.setSpecial(5)
            charInstance.combat.diseaseRes.setSpecial(20)
            charInstance.combat.venomRes.setSpecial(5)
            charInstance.combat.magicRes.setSpecial(10)
            charInstance.combat.psychicRes.setSpecial(10)

            charInstance.advantageRecord.removeAdvantage(charInstance.advantageRecord.commonAdvantages.sickly)
            charInstance.advantageRecord.removeAdvantage(charInstance.advantageRecord.commonAdvantages.seriousIllness)
            charInstance.advantageRecord.removeAdvantage(charInstance.advantageRecord.commonAdvantages.magicSusceptibility)
        },
        {_, _ ->
            charInstance.combat.physicalRes.setSpecial(-5)
            charInstance.combat.diseaseRes.setSpecial(-20)
            charInstance.combat.venomRes.setSpecial(-5)
            charInstance.combat.magicRes.setSpecial(-10)
            charInstance.combat.psychicRes.setSpecial(-10)
        }
    )

    private val inclinationLight = RacialAdvantage(
        "Unbalanced Inclination to Light",
        "All Sylvain possess a natural inclination to Light. That natural inclination " +
                "gives them a special resistance of +10 against any effect based on The Light. " +
                "However, that inclination also impedes them from choosing the Elemental " +
                "Compatibility (Dark) Advantage.",
        {_, _ ->
            val reference = charInstance.advantageRecord.magicAdvantages.elementalCompatibility

            val dummyAdvantage = Advantage(
                reference.name,
                reference.description,
                reference.effect,
                reference.restriction,
                reference.special,
                null,
                1,
                reference.cost,
                reference.pickedCost,
                reference.onTake,
                reference.onRemove
            )

            charInstance.advantageRecord.removeAdvantage(dummyAdvantage)
        },
        null
    )

    private val quickHealingSylvain = RacialAdvantage(
        "Quick Healing (Sylvain)",
        "Sylvain possess an incredible capacity for recovering from physical injury. " +
                "They add one point to their natural Regeneration.",
        {_, _ ->
            charInstance.combat.specRegen += 1
            charInstance.combat.updateRegeneration()
        },
        {_, _ ->
            charInstance.combat.specRegen -= 1
            charInstance.combat.updateRegeneration()
        }
    )

    private val senseLightDarkSylvain = RacialAdvantage(
        "Sense Light and Dark (Sylvain)",
        "Sylvain perceive a Dark or Light essence emanating from certain reincarnated " +
                "souls. Thus, they can sense the presence of any other Sylvain or Duk\'zarist " +
                "Nephilim present - although that does not mean they can identify them as such. " +
                "This ability does not enable Sylvain to detect an individual hidden by spells or " +
                "Ki abilities.",
        null,
        null
    )

    private val immortalSoulSylvain = RacialAdvantage(
        "Immortal Soul (Sylvain)",
        "Like all Nephilim, the elvish soul of the Sylvain is conflicted by the contrast " +
                "between what he learns while living and his ancient memories. For that reason, a " +
                "Sylvain suffers a -4 penalty to all Experience Points awarded by the Game Master " +
                "at the end of each gaming session.",
        null,
        null
    )

    private val giant = RacialAdvantage(
        "Giant",
        "Jayan add 2 points to their Size Characteristic and may not choose to reduce it " +
                "using the Uncommon Size Advantage.",
        {_, _ ->
            val uncommonSizeHeld = charInstance.advantageRecord.getAdvantage("Uncommon Size")
            if(uncommonSizeHeld != null && uncommonSizeHeld.picked!! < 5)
                charInstance.advantageRecord.removeAdvantage(uncommonSizeHeld)

            charInstance.changeSize(6)
        },
        {_, _ ->
            charInstance.changeSize(3)
        }
    )

    private val withstandFatigue = RacialAdvantage(
        "Withstand Fatigue",
        "The Jayan tire less than other characters with the same Constitution, and " +
                "therefore they add 1 point to their maximum Fatigue number.",
        {_, _ ->
            charInstance.combat.specFatigue += 1
            charInstance.combat.updateFatigue()
        },
        {_, _ ->
            charInstance.combat.specFatigue -= 1
            charInstance.combat.updateFatigue()
        }
    )

    private val damageResistance = RacialAdvantage(
        "Resistance to Damage",
        "Jayan souls make their bodies much more resistant to the shock produced by " +
                "damage. As a result, they apply a +15 bonus to their Physical Resistance (PhR).",
        {_, _ -> charInstance.combat.physicalRes.setSpecial(15)},
        {_, _ -> charInstance.combat.physicalRes.setSpecial(-15)}
    )

    private val uncommonStrength = RacialAdvantage(
        "Uncommon Strength",
        "Because of their enhanced muscular development, Jayan add 1 point to their " +
                "Strength. Additionally, Jayan may not use the Deduct Two Points from a " +
                "Characteristic Disadvantage to lower their Strength.",
        {_, _ ->
            charInstance.primaryList.str.setBonus(1)
            val reference = charInstance.advantageRecord.commonAdvantages.deductCharacteristic

            val dummyAdvantage = Advantage(
                reference.name,
                reference.description,
                reference.effect,
                reference.restriction,
                reference.special,
                null,
                0,
                reference.cost,
                reference.pickedCost,
                reference.onTake,
                reference.onRemove
            )

            charInstance.advantageRecord.removeAdvantage(dummyAdvantage)
        },
        {_, _ -> charInstance.primaryList.str.setBonus(-1)}
    )

    private val spiritualVision = RacialAdvantage(
        "Spiritual Vision",
        "The residual power of the Jayan's Third Eye has left them with the ability to " +
                "see spirits under certain conditions. To do so, they must close their eyes and " +
                "allow their unconscious to look for spiritual beings. While doing so, Jayan " +
                "cannot see anything in the material world. This ability does not permit a " +
                "character to see spells, mystical effects, or psychic matrices - only souls " +
                "invisible to the human eye.",
        null,
        null
    )

    private val magicSusceptibility = RacialAdvantage(
        "Susceptibility to Magic",
        "Spells and mystical effects are especially effective against Jayan, who suffer " +
                "a -10 penalty to their Magic Resistance (MR).",
        {_, _ -> charInstance.combat.magicRes.setSpecial(-10)},
        {_, _ -> charInstance.combat.magicRes.setSpecial(10)}
    )

    private val immortalSoulJayan = RacialAdvantage(
        "Immortal Soul (Jayan)",
        "Like all Nephilim, the Jayan experiences conflict between what he learns while " +
                "living and his ancient memories. For that reason, a Jayan suffers a -3 penalty " +
                "to all Experience Points awarded by the Game Master ata the end of each gaming session.",
        null,
        null
    )

    private val withoutTrace = RacialAdvantage(
        "Pass Without a Trace",
        "When a D\'Anjayni travels barefoot, his tracks erase themselves as he goes. " +
                "Anyone attempting to track a D\'Anjayni moving in this way suffers a -40 penalty " +
                "to their Ability Check.",
        null,
        null
    )

    private val forgetfulness = RacialAdvantage(
        "Forgetfulness",
        "When D\'Anjayni wish, they may pass unnoticed wherever they go; even those who " +
                "saw them will not remember much about them. They are capable of having a " +
                "conversation with one or more people while leaving those persons unable to " +
                "remember their description or the topic of conversation. In game terms, anyone " +
                "who sees or speaks with a D\'Anjayni character must pass a Magic Resistance (MR) " +
                "Check of 100. Failure indicates that the person in question has forgotten the " +
                "description of the D\'Anjayni and the conversation topic itself.\nThis ability is " +
                "considered the equivalent of an automatic mystical effect whose only conditions " +
                "are an encounter with a D\'Anjayni character who has not mentioned his true " +
                "name. It is almost impossible to detect the use of this ability, even for " +
                "someone who can see magic. The persons affected do not realize anything unnatural " +
                "has happened. A wizard must pass a magical Difficulty Check of Impossible to " +
                "detect the use of this ability. This special skill ceases to have effect if the " +
                "D\'Anjayni reveals his true name during the conversation, or if he runs into " +
                "individuals who know his true identity.",
        null,
        null
    )

    private val commonAppearance = RacialAdvantage(
        "Common Appearance",
        "Because of their average looks, D\'Anjayni never possess an Appearance of less " +
                "than 3 nor more than 7.",
        {_, _ -> if(charInstance.appearance !in 3..7) charInstance.setAppearance(5)},
        null
    )

    private val undetectability = RacialAdvantage(
        "Undetectability",
        "The D\'Anjayni have the mystical ability to resist detection by magical or " +
                "Ki-based means. For that reason, D\'Anjayni apply a bonus of +30 to their " +
                "Resistance to detection. In addition, they have a natural understanding of the " +
                "use of Ki Concealment, which they perform adding a bonus of +30.",
        null,
        null
    )

    private val silentWhisper = RacialAdvantage(
        "Silent Whisper",
        "It is quite difficult to hear the words of a D\'Anjayni if they are not " +
                "directed at you. Anyone but the intended listener trying to overhear a D\'Anjayni " +
                "applies a -60 penalty to the Ability Check.",
        null,
        null
    )

    private val immortalSoulDanjayni = RacialAdvantage(
        "Immortal Soul (D\'Anjayni)",
        "Like all Nephilim, the D\'Anjayni experience conflict between what they learn " +
                "while living and their ancient memories. For that reason, a D\'Anjayni suffers " +
                "a -3 penalty to all Experience Points awarded by the Game Master at the end of " +
                "each gaming session.",
        null,
        null
    )

    private val orinie = RacialAdvantage(
        "Or\'inie",
        "The mystical symbol with which the Ebudan are born is known as the Or\'inie. It " +
                "works as a shield against anything that might threaten to turn them from their " +
                "Sue\'Aman. As a consequence, Ebudan apply a bonus of +30 to their resistance " +
                "against Forgetfulness and Emotional Control effects that might impede the " +
                "fulfillment of their destiny. Once an Ebudan obtains his Sue\'Aman, this ability " +
                "disappears completely.",
        null,
        null
    )

    private val celestialEssence = RacialAdvantage(
        "Celestial Essence",
        "When an Ebudan has achieved his set purpose, his full spiritual essence " +
                "suffuses his body, preventing him from suffering natural physical injuries. In " +
                "game terms, the Ebudan becomes invulnerable to any natural attack that isn't " +
                "capable of damaging Energy.",
        null,
        null
    )

    private val seraphimWings = RacialAdvantage(
        "Seraphim Wings",
        "The moment they achieve their purpose, Ebudan recover part of their lost " +
                "essence and gain two luminous wings. These energy wings represent the feathery " +
                "appendages the Ebudan used to possess in their former lives. They can summon " +
                "these wings of energy at will, but doing so requires an entire combat turn. An " +
                "Ebudan's energy wings grant him Flight Value 12. They can only use this ability " +
                "after they achieve their Sue\'Aman.",
        null,
        null
    )

    private val immortalSoulEbudan = RacialAdvantage(
        "Ebudan Soul",
        "Like all Nephilim, the Ebudan experience conflict between what they learn while " +
                "living and their ancient memories. For that reason, an Ebudan suffers a -3 " +
                "penalty to all Experience Points awarded by the Game Master at the end of each " +
                "gaming session.",
        null,
        null
    )

    private val seeEssence = RacialAdvantage(
        "See the Essence",
        "The eyes of the Daimah see the souls of the living immediately identifying the " +
                "elemental or spiritual ties of any type of being. This ability is treated as an " +
                "innate power of detection, but an individual can also resist it naturally with " +
                "a Magic Resistance Check of 140.",
        null,
        null
    )

    private val forestSense = RacialAdvantage(
        "Sense the Forest",
        "Though they cannot literally talk with plants, the Daimah can sense the " +
                "feelings of nature (both plants and animals), detecting such things as fear, " +
                "calm, or even just disquiet.",
        null,
        null
    )

    private val natureCure = RacialAdvantage(
        "Nature\'s Cure",
        "As long as they are within the forest, the soul of the Daimah provides their " +
                "bodies with the essence of life around them. Therefore, while in thick forest " +
                "or jungle, Daimah add three points to their Regeneration.",
        null,
        null
    )

    private val forestMovement = RacialAdvantage(
        "Movement in the Forest",
        "The Daimah recognize nature as their home, and nature accepts them. No matter " +
                "how thick the forest or how tangled the brush, Daimah suffer no penalties to movement.",
        null,
        null
    )

    private val smallSize = RacialAdvantage(
        "Small Size",
        "The Daimah are not usually tall or heavily built. They subtract 1 from their " +
                "Size Characteristic.",
        {_, _ ->
            charInstance.sizeSpecial -= 1
            charInstance.updateSize()
        },
        {_, _ ->
            charInstance.sizeSpecial += 1
            charInstance.updateSize()
        }
    )

    private val immortalSoulDaimah = RacialAdvantage(
        "Immortal Soul (Daimah)",
        "Like all Nephilim, the Daimah experience conflict between what they learn while " +
                "living and their ancient memories. For that reason, a Daimah suffers a -2 " +
                "penalty to all Experience Points awarded by the Game Master at the end of each " +
                "gaming session.",
        null,
        null
    )

    private val exceptionalResistancesDukzarist = RacialAdvantage(
        "Exceptional Resistances (Duk\\'zarist)",
        "The souls of the Duk\'zarist influence their human bodies at every stage of " +
                "development, thereby greatly increasing their resistances. This ability acts " +
                "differently in men and women. Male souls apply a bonus of +15 to all Resistance " +
                "rolls except Physical Resistance (PhR), for which they receive +20. Female " +
                "Duk\'zarist gain a +15 to all Resistance rolls except Magic Resistance (MR), for " +
                "which they receive +20.",
        {_, _ ->
            charInstance.combat.allResistances.forEach{it.setSpecial(15)}

            if(charInstance.isMale) charInstance.combat.physicalRes.setSpecial(5)
            else charInstance.combat.magicRes.setSpecial(5)
        },
        {_, _ ->
            charInstance.combat.allResistances.forEach{it.setSpecial(-15)}

            if(charInstance.isMale) charInstance.combat.physicalRes.setSpecial(-5)
            else charInstance.combat.magicRes.setSpecial(-5)
        }
    )

    private val inclinationDark = RacialAdvantage(
        "Unbalanced Inclination Toward the Dark",
        "The Duk\'zarist souls have an unbalanced inclination toward the Dark. That " +
                "natural inclination gives them a special resistance of +10 against any effect " +
                "based on The Dark. However, that inclination also impedes them from choosing " +
                "the Elemental Compatibility (Light) Advantage.",
        {_, _ ->
            val reference = charInstance.advantageRecord.magicAdvantages.elementalCompatibility

            val dummyAdvantage = Advantage(
                reference.name,
                reference.description,
                reference.effect,
                reference.restriction,
                reference.special,
                null,
                0,
                reference.cost,
                reference.pickedCost,
                reference.onTake,
                reference.onRemove
            )

            charInstance.advantageRecord.removeAdvantage(dummyAdvantage)
        },
        null
    )

    private val withstandDeath = RacialAdvantage(
        "Withstand Death",
        "When they are in a state between life and death, the Duk\'zarist do not need " +
                "to pass a Physical Resistance (PhR) Check to survive; since their souls are so " +
                "firmly wedded to their bodies, they automatically pass such checks.",
        null,
        null
    )

    private val quickHealingDukzarist = RacialAdvantage(
        "Quick Healing (Duk\'zarist)",
        "Duk\'zarist possess an incredible capacity for recovering from any physical " +
                "injury. They add one point to their natural Regeneration.",
        {_, _ ->
            charInstance.combat.specRegen += 1
            charInstance.combat.updateRegeneration()
        },
        {_, _ ->
            charInstance.combat.specRegen -= 1
            charInstance.combat.updateRegeneration()
        }
    )

    private val limitedNeeds = RacialAdvantage(
        "Limited Needs",
        "The Duk\'zarist need much less rest and nourishment than any other race; they " +
                "can survive on one-third the sleep and food required by humans.",
        null,
        null
    )

    private val senseLightDarkDukzarist = RacialAdvantage(
        "Sense Light and Dark (Duk\'zarist)",
        "Duk\'zarist perceive the Dark or Light essence emanating from certain " +
                "reincarnated souls. Thus, they can sense the presence of any other Sylvain or " +
                "Duk\'zarist Nephilim present - although that does not mean they can identify " +
                "them as such. This ability does not enable Duk\'zarist to detect an individual " +
                "hidden by spells or Ki abilities.",
        null,
        null
    )

    private val nightVision = RacialAdvantage(
        "Night Vision",
        "The eyes of the Duk\'zarist are more adapted to darkness than any other human " +
                "being. In game terms, this ability is not as developed as that acquired through " +
                "spending a Creation Point, but it permits the Duk\'zarist to reduce any penalty " +
                "due to natural darkness by one-half.",
        null,
        null
    )

    private val fireDevotion = RacialAdvantage(
        "Devotion to Fire",
        "The psychic powers of the Duk\'zarist are naturally tied to fire. If they " +
                "develop their mental abilities, the first one they must acquire is the " +
                "discipline of Pyrokinesis.",
        {_, _ ->
            if(charInstance.psychic.totalPsychicPoints > 0) {
                if (charInstance.psychic.getFreePsyPoints() == 0) {
                    val powerRemoved =
                        if (charInstance.psychic.masteredPowers.size > 0) charInstance.psychic.masteredPowers.last()
                        else null
                    if(powerRemoved != null)
                        charInstance.psychic.masterPower(
                            powerRemoved,
                            charInstance.psychic.getPowerDiscipline(powerRemoved)!!,
                            false
                        )
                    else{
                        val disciplineRemoved =
                            if (charInstance.psychic.disciplineInvestment.size > 0) charInstance.psychic.disciplineInvestment.last()
                            else null
                        if(disciplineRemoved != null)
                            charInstance.psychic.updateInvestment(disciplineRemoved, false)
                    }
                }

                charInstance.psychic.updateInvestment(charInstance.psychic.pyrokinesis, true)
            }
        },
        null
    )

    private val perfectBodies = RacialAdvantage(
        "Perfect Bodies",
        "The essence of the Duk\'zarist prevents their bodies from developing any " +
                "natural malformation. A Duk\'zarist cannot choose the following Disadvantages: " +
                "Atrophied Limb, Blind, Deafness, Mute, Nearsighted, Physical Weakness, Serious " +
                "Illness, Sickly, and Susceptible to Poisons.",
        {_, _ ->
            charInstance.advantageRecord.removeAdvantage(charInstance.advantageRecord.commonAdvantages.atrophiedLimb)
            charInstance.advantageRecord.removeAdvantage(charInstance.advantageRecord.commonAdvantages.blind)
            charInstance.advantageRecord.removeAdvantage(charInstance.advantageRecord.commonAdvantages.deafness)
            charInstance.advantageRecord.removeAdvantage(charInstance.advantageRecord.commonAdvantages.mute)
            charInstance.advantageRecord.removeAdvantage(charInstance.advantageRecord.commonAdvantages.nearsighted)
            charInstance.advantageRecord.removeAdvantage(charInstance.advantageRecord.commonAdvantages.physicalWeakness)
            charInstance.advantageRecord.removeAdvantage(charInstance.advantageRecord.commonAdvantages.seriousIllness)
            charInstance.advantageRecord.removeAdvantage(charInstance.advantageRecord.commonAdvantages.sickly)
            charInstance.advantageRecord.removeAdvantage(charInstance.advantageRecord.commonAdvantages.poisonSusceptibility)
        },
        null
    )

    private val metalAllergy = RacialAdvantage(
        "Allergic to Metal",
        "The only weakness of the Duk\'zarist in their past lives was their vulnerability " +
                "to metals, especially iron and iron-alloys. The original Duk\'zarist could be " +
                "killed even by simple contact with metal; swords harmed them more because they " +
                "were made of metal than because they were sharp. Although in a limited way, the " +
                "reborn Duk\'zarist have inherited part of this disadvantage. If their skin makes " +
                "contact with any metal containing iron, the Duk\'zarist must pass a check of " +
                "their base Presence against a Difficulty of 60. If they fail, they suffer an " +
                "adverse reaction causing an All Action Penalty equivalent to the margin by which " +
                "they failed the check. If the metal is pure iron, the check is against a " +
                "Difficulty of 80. These negative effects disappear at a rate of 10 points per " +
                "minute. The Duk\'zarist Nephilim can use clothing and gloves to avoid these effects.",
        null,
        null
    )

    private val immortalSoulDukzarist = RacialAdvantage(
        "Immortal Soul (Duk\'zarist)",
        "Like all Nephilim, the Duk\'zarist experience conflict between what they learn " +
                "while living and their ancient memories. For that reason, a Duk\'zarist suffers " +
                "a -5 penalty to all Experience Points awarded by the Game Master at the end of " +
                "each gaming session.",
        null,
        null
    )

    val sylvainAdvantages = listOf(exceptionalResistancesSylvain, inclinationLight, quickHealingSylvain,
        senseLightDarkSylvain, immortalSoulSylvain)

    val jayanAdvantages = listOf(giant, withstandFatigue, damageResistance, uncommonStrength,
        spiritualVision, magicSusceptibility, immortalSoulJayan)

    val danjayniAdvantages = listOf(withoutTrace, forgetfulness, commonAppearance, undetectability,
        silentWhisper, immortalSoulDanjayni)

    val ebudanAdvantages = listOf(orinie, celestialEssence, seraphimWings, immortalSoulEbudan)

    val daimahAdvantages = listOf(seeEssence, forestSense, natureCure, forestMovement, smallSize,
        immortalSoulDaimah)

    val dukzaristAdvantages = listOf(exceptionalResistancesDukzarist, inclinationDark, withstandDeath,
        quickHealingDukzarist, limitedNeeds, senseLightDarkDukzarist, nightVision, fireDevotion,
        perfectBodies, metalAllergy, immortalSoulDukzarist)
}