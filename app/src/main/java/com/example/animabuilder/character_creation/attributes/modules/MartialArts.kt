package com.example.animabuilder.character_creation.attributes.modules

import com.example.animabuilder.character_creation.BaseCharacter

/**
 * Record of martial arts the character may acquire.
 */
class MartialArts(private val charInstance: BaseCharacter) {
    val kempo = MartialArt(
        "Kempo",
        "This is a freewheeling style of combat that uses combinations of " +
                "strikes. The style uses rapid multiple attacks to try and find gaps in " +
                "an opponent's defenses. The rapid flurry of blows allows a Kempo master " +
                "to carry out additional attacks with a penalty of -10 to his ability " +
                "instead of the usual -25. It has a Base Damage of 20, plus the " +
                "character's Strength bonus. Kempo uses the Blunt Table.",
        "None",
        10
    ){true}

    val capoeira = MartialArt(
        "Capoeira",
        "Capoeira is a system of combat that appears chaotic and employs " +
                "broad acrobatic movements of the legs that resemble a dance. The " +
                "movements of Capoeira are so sweeping that when the user makes ana Area " +
                "Attack, he is considered to be using a large weapon, and he can affect " +
                "up to five opponents. Capoeira has a Base Damage of 20, plus the " +
                "character's Strength bonus. It uses the Blunt Table. Practitioners of Capoeira " +
                "also gain a class bonus of +10 to their Dodge ability.",
        "Dance 40",
        10
    ){charInstance.secondaryList.dance.total.value >= 40}

    val taiChi = MartialArt(
        "Tai Chi",
        "More than just a martial art, Tai Chi is a philosophy. It employs " +
                "every part of the body using movements that are so fluid and elegant " +
                "that it does not even seem to be a real way of fighting. Tai Chi " +
                "possesses a Base Damage of 20, plus double the Power bonus of the " +
                "character, representing the use of the character's internal energy. " +
                "Given that this energy is used only as a force multiplier, Tai Chi " +
                "attacks occur on the Blunt Table, and not the Energy Table.",
        "Use of Ki",
        30
    ){charInstance.ki.takenAbilities.contains(charInstance.ki.kiRecord.useOfKi)}

    val shotokan = MartialArt(
        "Shotokan",
        "Shotokan is a martial art that employs forceful strikes with either the fists " +
                "or the legs. The style consists of taking advantage of the weak points of an " +
                "opponent's defenses to defeat him with a single attack. It is an offensive " +
                "martial art capable of inflicting enormous damage. Shotokan has a Base Damage " +
                "of 30, plus the character's Strength bonus. It uses the Blunt Table.",
        "None",
        10
    ){true}

    val sambo = MartialArt(
        "Sambo",
        "The name Sambo comes from Samooborona Biez Orousia, which means \"unarmed " +
                "personal defense.\" It is a very precise and defensive combat style, developed " +
                "for the training of certain military organizations. Practitioners of Sambo " +
                "reduce the penalties for the following combat maneuvers in half: Trapping, " +
                "Area Attack, Take-Down, and Disarm. Sambo has a base damage of 20, plus the " +
                "character's Strength bonus. It uses the Blunt Table. Practitioners of Sambo also " +
                "gain a bonus of +10 to their Block ability when unarmed.",
        "None",
        10
    ){true}

    val kungFu = MartialArt(
        "Kung Fu",
        "Kung Fu is a broad style developed by many great oriental masters of martial " +
                "arts. It takes its inspiration from various animals and imitates their " +
                "movements while adapting them to human combat. Because its techniques are highly " +
                "varied, it is an art capable of adapting itself to situations as they arise. " +
                "Thus, its practitioners can modify their style with great ease. Every combat " +
                "turn, a master of Kung Fu can choose a bonus of +10 to his Attack, Block, " +
                "Dodge, Damage, or Initiative, as he finds convenient. He should declare the " +
                "ability to which his bonus is dedicated before Initiative is calculated for " +
                "the Combat Turn. If he uses it to improve his Attack, Block, or Dodge ability, " +
                "it is not considered an innate class bonus, and therefore can increase it by " +
                "more than +50. Kung Fu has a Base Damage of 20 plus the character's Strength " +
                "bonus, and attacks occur on the Blunt Table.",
        "Acrobatics 40, Sleight of Hand 40, Style 20",
        10
    ){
        charInstance.secondaryList.acrobatics.total .value>= 40 &&
            charInstance.secondaryList.sleightHand.total.value >= 40 &&
            charInstance.secondaryList.style.total.value >= 20
    }

    val taekwondo = MartialArt(
        "Taekwondo",
        "Taekwondo is a system of combat that is ideal for combining with the use of " +
                "weapons. It is a martial art that is based primarily on effective attacks with " +
                "the legs that are directed with power and expertise. Taekwondo possesses a Base " +
                "Damage of 20, plus the character's Strength bonus. It allows the character to " +
                "make an additional attack with his legs after all his other attacks have been " +
                "made. This extra attack suffers a penalty of only -20 and acts just as an " +
                "additional weapon would. It can be used even after making an attack with a " +
                "weapon. Taekwondo uses the Blunt Table.",
        "None",
        10
    ){true}

    val aikido = MartialArt(
        "Aikido",
        "Aikido is a martial art that enables a practitioner to defend himself against " +
                "attacks using the enemy's own strength against him. Its devotees easily trip or " +
                "break their attacker's arms or legs using minimal movement. According to the " +
                "philosophy behind this art, the adversary's own violence is the only thing that " +
                "will defeat them. The damage caused by Aikido is 10, plus the Strength Bonus of " +
                "the Aikido artist. However, when making a counter attack, twice the opponent's " +
                "Strength Bonus is also added (a minimum bonus of +5). The attack occurs on the " +
                "Blunt Table, and it allows one's opponent to easily be controlled, so that there " +
                "are no penalties to the Trapping maneuver during a counterattack. Practitioners " +
                "of Aikido also receive a +10 bonus to either their Block or Dodge ability while unarmed.",
        "Sleight of Hand 40",
        10
    ){charInstance.secondaryList.sleightHand.total.value >= 40}

    val muayThai = MartialArt(
        "Muay Thai",
        "Muay Thai maximizes utilization of the strength of those who practice it. The " +
                "fighter utilizes the harder parts of his body, like his elbows and knees. Its " +
                "masters seek out the opponent's weakest points, such as the joints or ribs. " +
                "Muay Thai has a Base Damage of 20, plus triple the Strength bonus of the user. " +
                "It uses the Blunt Table.",
        "Feats of Strength 40",
        10
    ){charInstance.secondaryList.strengthFeat.total.value >= 40}

    val grappling = MartialArt(
        "Grappling",
        "Grappling is an art that consists of holding and trapping one's opponent. " +
                "Training in grappling includes falls, punches, kicks, strangleholds, and throws. " +
                "The essence of the style is to close the distance between the fighter and his " +
                "opponent, take him down, and finish him off on the ground. Grappling permits a " +
                "character to use the Trapping and Take-down maneuvers without any penalty. It " +
                "has a Base Damage of 20, plus the character's Strength bonus. It utilizes the " +
                "Blunt Table.",
        "Feats of Strength 40",
        10
    ){charInstance.secondaryList.strengthFeat.total.value >= 40}

    val melkaiah = MartialArt(
        "Melkaiah",
        "This is a strong system of fighting that maximizes the strength and ability of " +
                "its practitioners to inhuman levels. The style is based on holds, throws, and " +
                "take-downs of incredible effectiveness. It is said that as long as a " +
                "practitioner of Melkaiah keeps his feet on the ground, he cannot be defeated " +
                "by conventional means. This martial art awards a bonus of +3 to Strength or " +
                "Dexterity Checks when performing Take-Down or Trapping maneuvers. Practitioners " +
                "of Melkaiah also gain a +10 bonus to their Attack ability while unarmed.",
        "Grappling or Sambo, Inhumanity, more than 160 in both Attack and Defense (Unarmed)",
        10
    ){
        (charInstance.weaponProficiencies.takenMartialList.contains(grappling) || charInstance.weaponProficiencies.takenMartialList.contains(sambo)) &&
                charInstance.ki.takenAbilities.contains(charInstance.ki.kiRecord.inhumanity) &&
                charInstance.combat.attack.total.value >= 160 && (charInstance.combat.block.total.value >= 160 || charInstance.combat.dodge.total.value >= 160) &&
                (charInstance.weaponProficiencies.primaryWeapon.value == charInstance.weaponProficiencies.unarmed || charInstance.weaponProficiencies.individualModules.contains(charInstance.weaponProficiencies.unarmed))
    }

    val seraphite = MartialArt(
        "Seraphite",
        "According to tradition, Seraphite is a style that was developed for the purpose " +
                "of hunting demons. It is an art that uses unusually perilous movements that put " +
                "the practitioner at risk, but that also multiply the effectiveness of the " +
                "attack. In current times, this art is known by certain sectors of the Church, " +
                "especially members of the Inquisition. A character who employs Seraphite adds a " +
                "+10 bonus to the Final Damage of the Basic Martial Art style being used. If " +
                "desired, he can also temporarily add +20 to his Attack ability in exchange for " +
                "a -30 to his Defense ability. This must be declared before calculation of Initiative.",
        "Shotokan or Kempo, Precision Extrusion, more than 180 Attack (Unarmed)",
        10
    ){
        (charInstance.weaponProficiencies.takenMartialList.contains(shotokan) || charInstance.weaponProficiencies.takenMartialList.contains(kempo)) &&
                charInstance.ki.takenAbilities.contains(charInstance.ki.kiRecord.presenceExtrusion) &&
                charInstance.combat.attack.total.value >= 180 &&
                (charInstance.weaponProficiencies.primaryWeapon.value == charInstance.weaponProficiencies.unarmed || charInstance.weaponProficiencies.individualModules.contains(charInstance.weaponProficiencies.unarmed))
    }

    val dumah = MartialArt(
        "Dumah",
        "Dumah is known as \"the art of the wind,\" a name it received because it " +
                "teaches its practitioners to use their hands and legs as though they were " +
                "cutting or thrusting weapons. This is a tribal practice usually transmitted " +
                "from parents to children within a family. A character who employs Dumah adds " +
                "a +10 bonus to the Final Damage of the Basic Martial Art style being used, " +
                "and he can also choose to use the Thrust or Cut Tables when attacking. The " +
                "forcefulness of these cuts or thrusts is such that it reduces the opponent's " +
                "Armor Type by two points. It also adds +10 to the Breakage of its attacks. " +
                "Practitioners of Dumah also gain a +20 bonus to their Attack ability while unarmed",
        "Kempo or Capoeira, Presence Extrusion",
        10
    ){
        (charInstance.weaponProficiencies.takenMartialList.contains(kempo) || charInstance.weaponProficiencies.takenMartialList.contains(capoeira)) &&
                charInstance.ki.takenAbilities.contains(charInstance.ki.kiRecord.presenceExtrusion)
    }

    val emp = MartialArt(
        "Emp",
        "Emp is a refined technique of fighting that prepares its users to fight " +
                "against armed enemies. Using rapid and dizzying spiral movements, a master of " +
                "Emp is capable of advancing on and rendering an opponent helpless in a couple " +
                "of short moves. This style permits its user to perform the Disarm maneuver with " +
                "no penalty to his ability, and it adds a bonus of +3 to his Characteristic in " +
                "Contested Checks. Practitioners of Emp also gain a +20 bonus to their Attack " +
                "ability while unarmed and a +10 to their Initiative when using martial arts.",
        "Kempo or Taekwondo, Mastery of Attack (Unarmed)",
        10
    ){
        (charInstance.weaponProficiencies.takenMartialList.contains(kempo) || charInstance.weaponProficiencies.takenMartialList.contains(taekwondo)) &&
                charInstance.combat.attack.total.value >= 200 &&
                (charInstance.weaponProficiencies.primaryWeapon.value == charInstance.weaponProficiencies.unarmed || charInstance.weaponProficiencies.individualModules.contains(charInstance.weaponProficiencies.unarmed))
    }

    val enuth = MartialArt(
        "Enuth",
        "Known as \"the art of dreams,\" Enuth was created during the period of war " +
                "between the Sylvain and the Duk\'zarist. One faction of the elves, followers of " +
                "the philosophy of C\'iel, which forbids killing, created Enuth in order to " +
                "battle the Duk\'zarist without having to take their lives. Due to the incredible " +
                "resistance of the Duk\'zarist, the style reached a level of perfection of " +
                "incredible extremes in order to equal the fighting power of their antagonists. " +
                "Enuth permits the application of a +20 bonus to the die roll to calculate the " +
                "Critical Level when the character strikes intending to knock his opponent " +
                "unconscious. It can also permit the character to voluntarily reduce the amount " +
                "of damage inflicted by his blow - even after the dice have been thrown for both " +
                "antagonists. Practitioners of Enuth also gain a +20 bonus to their Block and " +
                "Dodge abilities while unarmed.",
        "Sambo or Shotokan, more than 160 in both Attack and Defense (Unarmed)",
        10
    ){
        (charInstance.weaponProficiencies.takenMartialList.contains(sambo) || charInstance.weaponProficiencies.takenMartialList.contains(shotokan)) &&
                charInstance.combat.attack.total.value >= 160 && (charInstance.combat.block.total.value >= 160 || charInstance.combat.dodge.total.value >= 160) &&
                (charInstance.weaponProficiencies.primaryWeapon.value == charInstance.weaponProficiencies.unarmed || charInstance.weaponProficiencies.individualModules.contains(charInstance.weaponProficiencies.unarmed))
    }

    val shephon = MartialArt(
        "Shephon",
        "Shephon is probably the most perfect system of defense that exists. It is " +
                "inspired by the flow of water, and with its free-flowing movements, a master " +
                "of this style is capable of avoiding almost any attack by changing its " +
                "trajectory. When a character declares that he has entered into Total Defense " +
                "mode, it increases the bonus for that maneuver to +60. A practitioner of Shephon " +
                "also gains a +20 to their Block and Dodge abilities while unarmed.",
        "Aikido and Kung Fu, Ki Control, Mastery of Defense (Unarmed)",
        10
    ){
        charInstance.weaponProficiencies.takenMartialList.contains(aikido) && charInstance.weaponProficiencies.takenMartialList.contains(kungFu) &&
                charInstance.ki.takenAbilities.contains(charInstance.ki.kiRecord.kiControl) &&
                (charInstance.combat.block.total.value >= 200 || charInstance.combat.dodge.total.value >= 200) &&
                (charInstance.weaponProficiencies.primaryWeapon.value == charInstance.weaponProficiencies.unarmed || charInstance.weaponProficiencies.individualModules.contains(charInstance.weaponProficiencies.unarmed))
    }

    val asakusen = MartialArt(
        "Asakusen",
        "Although the word Asakusen is used today to designate any fighting style used " +
                "to kill, it is actually one of the most complex martial arts in the world. It " +
                "has been prohibited for centuries due to its lethal nature. With the passage " +
                "of time, it was diluted into various styles created by schools of Kung Fu that " +
                "are no more than pale reflections of true Asakusen. Asakusen makes the variable " +
                "+10 bonus of Kung Fu apply to Dodge, Attack, Block, Initiative, and Damage all " +
                "at the same time - as long as martial arts are being used. However, the " +
                "character still adds another +10 to any one of those abilities he chooses in " +
                "the same way as is done for Kung Fu.",
        "Kung Fu, more than 16- in both Attack and Defense (Unarmed)",
        10
    ){
        charInstance.weaponProficiencies.takenMartialList.contains(kungFu) &&
                charInstance.combat.attack.total.value >= 160 && (charInstance.combat.block.total.value >= 160 || charInstance.combat.dodge.total.value >= 160) &&
                (charInstance.weaponProficiencies.primaryWeapon.value == charInstance.weaponProficiencies.unarmed || charInstance.weaponProficiencies.individualModules.contains(charInstance.weaponProficiencies.unarmed))
    }

    val velez = MartialArt(
        "Velez",
        "To learn Velez it is necessary for the practitioner to control his internal " +
                "energy and know how to channel it. This style concentrates all the spiritual " +
                "power of the martial artist when he strikes, permitting him to create a flow " +
                "of power capable of penetrating even physical matter. It is without doubt one " +
                "of the rarest and most spectacular martial arts in the world - although the " +
                "few who know its secrets are reluctant to share them. Velez permits the " +
                "character to strike using the Energy Table. Nonetheless, attacks made with this " +
                "style can be blocked normally, since they are not intangible. Practitioners of " +
                "Velez also gain a +20 bonus to either their Block or Dodge ability while unarmed.",
        "Tai Chi or Kung Fu, Presence Extrusion",
        20
    ){
        (charInstance.weaponProficiencies.takenMartialList.contains(taiChi) || charInstance.weaponProficiencies.takenMartialList.contains(kungFu)) &&
                charInstance.ki.takenAbilities.contains(charInstance.ki.kiRecord.presenceExtrusion)
    }

    val selene = MartialArt(
        "Selene",
        "According to mythology, Selene was the first martial art to be recognized as " +
                "such. It was practiced exclusively by women, and, traditionally, no man was " +
                "permitted to discover its secrets. Selene turns the attacker's own force " +
                "against him, tossing him around like a rag doll. In spite of its great " +
                "complexity, the movements of Selene are so subtle that it often seems the " +
                "artist hasn't even moved. Due to his ability to use his Defense ability, a " +
                "master of Selene doubles his bonus for counterattacking if he uses his Response " +
                "Action to attack his opponent with this martial art. Practitioners of Selene " +
                "also gain a +20 bonus to Block and Dodge when unarmed.",
        "Aikido, Mastery of Block or Dodge (Unarmed)",
        10
    ){
        charInstance.weaponProficiencies.takenMartialList.contains(aikido) &&
                (charInstance.combat.block.total.value >= 200 || charInstance.combat.dodge.total.value >= 200) &&
                (charInstance.weaponProficiencies.primaryWeapon.value == charInstance.weaponProficiencies.unarmed || charInstance.weaponProficiencies.individualModules.contains(charInstance.weaponProficiencies.unarmed))
    }

    val hakyoukuken = MartialArt(
        "Hakyoukuken",
        "Many consider Hakyoukuken to be the most perfect martial art that has ever " +
                "existed. In fact, its origin isn't even human, though its true source is a " +
                "mystery. The practitioner of Hakyoukuken controls the tension of every muscle " +
                "in his body and makes his attacks with devastating power, literally destroying " +
                "his adversaries from the inside. This style also teaches one to get in the " +
                "first strike: If there is no attacker, there is no need for defense. Hakyoukuken " +
                "adds a bonus of +20 to the Final Damage of whatever martial art is being used. " +
                "Most armor offers no protection its attacks and so subtracts a -2 from the AT " +
                "if they are soft. As it destroys an enemy's internal organs, add a +20 to the " +
                "die roll calculating the Critical Level due to attacks made using this martial " +
                "art. This last advantage only applies to organic beings. Practitioners of " +
                "Hakyoukukun also gain a +20 bonus to Initiative when using martial arts and " +
                "+10 to Attack when unarmed.",
        "Shotokan or Muay Thai, Use of Necessary Energy, Mastery in Attack (Unarmed)",
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