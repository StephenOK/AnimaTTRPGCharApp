package com.example.animabuilder.character_creation.attributes.advantages.advantage_items

import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.attributes.advantages.advantage_types.Advantage
import java.io.Serializable

class CommonAdvantages(private val charInstance: BaseCharacter): Serializable {
    val characteristicList = listOf("STR", "DEX", "AGI", "CON", "INT", "POW", "WP", "PER")
    val secondariesList = listOf(
        "Acrobatics",
        "Athleticism",
        "Climb",
        "Jump",
        "Ride",
        "Swim",
        "Art",
        "Dance",
        "Forging",
        "Music",
        "Sleight of Hand",
        "Notice",
        "Search",
        "Track",
        "Intimidate",
        "Leadership",
        "Persuasion",
        "Style",
        "Disguise",
        "Hide",
        "Lock Pick",
        "Poisons",
        "Theft",
        "Stealth",
        "Trap Lore",
        "Animals",
        "Appraisal",
        "Herbal Lore",
        "History",
        "Memorize",
        "Magic Appraisal",
        "Medic",
        "Navigate",
        "Occult",
        "Sciences",
        "Composure",
        "Feats of Strength",
        "Withstand Pain"
    )

    val fieldNames = listOf("Athletics", "Creative", "Perceptive", "Social", "Subterfuge",
        "Intellectual", "Vigor")

    val characteristicPoint = Advantage(
        "Add One Point to a Characteristic",
        "One of the character's attributes is greater than before.",
        "Add a point to the value of a single Characteristic.",
        "Restriction: Strength, Dexterity, Agility, and Constitution cannot be increased " +
                "to more than 11 through this Advantage. Intelligence, Power, Willpower, and " +
                "Perception cannot be increased to more than 13 through this Advantage.",
        "You may take this Advantage as many times as you wish.",
        characteristicList,
        0,
        listOf(1),
        0,
        { input, _ ->
            when(input){
                0 -> {charInstance.setStrBonus(1)}
                1 -> {charInstance.setDexBonus(1)}
                2 -> {charInstance.setAgiBonus(1)}
                3 -> {charInstance.setConBonus(1)}
                4 -> {charInstance.setIntBonus(1)}
                5 -> {charInstance.setPowBonus(1)}
                6 -> {charInstance.setWpBonus(1)}
                7 -> {charInstance.setPerBonus(1)}
            }
        },
        { input, _ ->
            when(input){
                0 -> {charInstance.setStrBonus(-1)}
                1 -> {charInstance.setDexBonus(-1)}
                2 -> {charInstance.setAgiBonus(-1)}
                3 -> {charInstance.setConBonus(-1)}
                4 -> {charInstance.setIntBonus(-1)}
                5 -> {charInstance.setPowBonus(-1)}
                6 -> {charInstance.setWpBonus(-1)}
                7 -> {charInstance.setPerBonus(-1)}
            }
        }
    )

    val acuteSenses = Advantage(
        "Acute Senses",
        "The character's senses are as developed as those of an animal.",
        "This Advantage adds 1 point to the character's Perception when making " +
                "Characteristic Checks. It also adds a special bonus of +30 to Notice and Search.",
        null,
        null,
        null,
        null,
        listOf(1),
        0,
        {_, _ ->
            charInstance.secondaryList.notice.setSpecial(30)
            charInstance.secondaryList.search.setSpecial(30)
        },
        {_, _ ->
            charInstance.secondaryList.notice.setSpecial(-30)
            charInstance.secondaryList.search.setSpecial(-30)
        }
    )

    val artifact = Advantage(
        "Artifact",
        "The character possesses a mystical device of enormous power.",
        "The player and the Game Master must agree on the abilities of the " +
                "device. Spending additional points increases the capabilities of the object.",
        null,
        null,
        null,
        null,
        listOf(1, 2, 3),
        0,
        null,
        null
    )

    val ambidextrous = Advantage(
        "Ambidextrous",
        "An ambidextrous person can use both hands equally well.",
        "An ambidextrous character can perform maneuvers with either hand. In combat, " +
                "he suffers only -10 to attacks with an additional weapon.",
        null,
        null,
        null,
        null,
        listOf(1),
        0,
        null,
        null
    )

    val characteristicToNine = Advantage(
        "Increase One Characteristic to Nine",
        "This advantage allows a player to increase the value of one of his character's " +
                "Primary Characteristics.",
        "Substitute one Characteristic's value for a 9, no matter what its original value was.",
        null,
        "You may take this Advantage as many times as you wish.",
        characteristicList,
        0,
        listOf(2),
        0,
        {input, _ ->
            when(input){
                0 -> charInstance.updateStrValues()
                1 -> charInstance.updateDexValues()
                2 -> charInstance.updateAgiValues()
                3 -> charInstance.updateConValues()
                4 -> charInstance.updateIntValues()
                5 -> charInstance.updatePowValues()
                6 -> charInstance.updateWpValues()
                7 -> charInstance.updatePerValues()
            }
        },
        {input, _ ->
            when(input){
                0 -> charInstance.updateStrValues()
                1 -> charInstance.updateDexValues()
                2 -> charInstance.updateAgiValues()
                3 -> charInstance.updateConValues()
                4 -> charInstance.updateIntValues()
                5 -> charInstance.updatePowValues()
                6 -> charInstance.updateWpValues()
                7 -> charInstance.updatePerValues()
            }
        }
    )

    val psyDisciplineAccess = Advantage(
        "Access to One Psychic Discipline",
        "The character is gifted with the ability to use the powers of a single psychic " +
                "discipline. Its abilities are limited to a single field and, no matter how much " +
                "it increases his potential, he will not have access to powers that are greater " +
                "than his natural capacities.",
        "This Advantage allows use of PP to acquire affinity to a single psychic discipline " +
                "and the matrix powers.",
        null,
        null,
        listOf(
            "Telepathy",
            "Psychokinesis",
            "Pyrokinesis",
            "Cryokinesis",
            "Physical Increase",
            "Energy",
            "Sentience",
            "Telemetry"
        ),
        0,
        listOf(1),
        0,
        {input, _ ->
            charInstance.psychic.legalDisciplines.add(charInstance.psychic.intToDiscipline(input!!))
            charInstance.psychic.legalDisciplines.add(charInstance.psychic.matrixPowers)
        },
        {input, _ ->
            charInstance.psychic.removeLegalDisciplineFromInt(input!!)
        }
    )

    val charm = Advantage(
        "Charm",
        "The character has a certain personal magnetism that makes others react " +
                "positively toward him. He always receives a positive reaction from people who do " +
                "not know him, and some individuals may even be slightly more permissive with him.",
        "The limits of this Advantage must be decided by the Game Master.",
        null,
        null,
        null,
        null,
        listOf(1),
        0,
        null,
        null
    )

    val disquieting = Advantage(
        "Disquieting",
        "A character with this Advantage can make people nervous whenever he wishes. He " +
                "can also discourage violence against himself or force intimidating people to " +
                "agree with him.",
        "The limits of this Advantage must be decided by the Game Master.",
        null,
        null,
        null,
        null,
        listOf(1),
        0,
        null,
        null
    )

    val animalAffinity = Advantage(
        "Animal Affinity",
        "A character with this Advantage has a special link with animals that allows him " +
                "to obtain a positive reaction. He is also able to communicate in a limited way " +
                "with them, understanding their general intentions, and vice versa.",
        "The limits of this Advantage must be decided by the GM. In any case, an animal " +
                "trained to attack will still do so in spite of this Advantage, but probably " +
                "after giving a warning and offering a character an opportunity to escape. When " +
                "combat against an animal is unavoidable, a character with this Advantage will " +
                "always be the last person attacked if he is in a group.",
        null,
        null,
        null,
        null,
        listOf(1),
        0,
        null,
        null
    )

    val dangerSense = Advantage(
        "Danger Sense",
        "Characters with this Advantage have a sixth sense, which allows them to detect " +
                "when something dangerous approaches or threatens them - though they will not be " +
                "able to detect the origin or nature of the danger until they see it.",
        "The character cannot be taken by surprise, unless his opponent's Initiative score " +
                "is 150 points or more higher than his.",
        null,
        null,
        null,
        null,
        listOf(2),
        0,
        null,
        null
    )

    val beenAround = Advantage(
        "Been Around",
        "The character has already had experiences in the real world, by which he has " +
                "learned great lessons.",
        "The character begins the game with 50, 100, or 150 additional Experience Points, " +
                "depending on the amount of Creation Points spent. For example, spending 3 CP " +
                "increases the character's Experience Points by 150. This increase permits a " +
                "conventional rise in level if the necessary point total is reached.",
        null,
        null,
        null,
        null,
        listOf(1, 2, 3),
        0,
        null,
        null
    )

    val subjectAptitude = Advantage(
        "Aptitude in a Subject",
        "A character with this Advantage has an enormous capacity to learn a single " +
                "Secondary Ability, which allows him to develop it with very little effort.",
        "This Advantage reduces the Development Cost of a single Secondary Ability for " +
                "each Creation Point spent. For example, a Wizard's player could reduce the cost " +
                "of the Composure ability from 3 to 2 if he spent 1 point on this Advantage, or " +
                "even to 1 if he spent 2 points.",
        "Development Costs cannot be reduced below 1. This Advantage works only for " +
                "Secondary Abilities.",
        null,
        secondariesList,
        0,
        listOf(1, 2),
        0,
        {input, cost ->
            charInstance.secondaryList.fullList[input!!].setDevelopmentDeduction(cost)
            charInstance.updateTotalSpent()
        },
        {input, cost ->
            charInstance.secondaryList.fullList[input!!].setDevelopmentDeduction(cost * -1)
            charInstance.updateTotalSpent()
        }
    )

    val naturalPsychicPower = Advantage(
        "Access to Natural Psychic Powers",
        "The character has a limited psychic capacity that allows him to use one Psychic " +
                "Ability unconsciously. He doesn't know the origin of the power or have complete " +
                "control over it, but he can use it whenever he needs to.",
        "The character can naturally use one specific Psychic Ability. He is not a true " +
                "psychic and cannot use Psychic Points to acquire new abilities or to harness " +
                "the power that he has. His psychic potential is not based on Willpower and does " +
                "not require rolling dice, but automatically is Difficult (DIF). The chosen power " +
                "can be of any level, but it cannot have a base requirement greater than Difficult. " +
                "It can be used once per minute without the character suffering a penalty, but " +
                "each additional use without the required rest inflicts 1 point of Fatigue. " +
                "Spending additional points increases the natural psychic potential to Very " +
                "Difficult (VDF) and Absurd (ABS), respectively.",
        null,
        null,
        charInstance.psychic.getAllPowerNames(),
        0,
        listOf(1, 2, 3),
        0,
        null,
        null
    )

    val fieldAptitude = Advantage(
        "Aptitude in a Field",
        "As per Aptitude in a Subject, except that in this case, the character has a " +
                "great capacity to learn an entire area of Secondary Abilities.",
        "The Development Cost for a field of Secondary Abilities is reduced by 1 point. If " +
                "the character's class offers a Secondary Ability within this field at a reduced " +
                "cost, the benefits of this Advantage apply in addition to the class benefit. For " +
                "example, if a Ranger decides to reduce the cost of the abilities of the " +
                "Intellectual field, these will cost 2 instead of 3. The Medicine ability, which " +
                "normally costs 2, will now cost only 1.",
        "Development Costs cannot be reduced below 1. This Advantage works only for Secondary Abilities.",
        null,
        fieldNames,
        0,
        listOf(2),
        0,
        {input, _ ->
            val field = charInstance.secondaryList.intToField(input!!)
            field.forEach{it.setDevelopmentDeduction(1)}
            charInstance.updateTotalSpent()
        },
        {input, _ ->
            val field = charInstance.secondaryList.intToField(input!!)
            field.forEach{it.setDevelopmentDeduction(-1)}
            charInstance.updateTotalSpent()
        }
    )

    val characteristicReroll = Advantage(
        "Repeat a Characteristics Roll",
        "This Advantage allows a player to modify one of his character's Primary Characteristics.",
        "Players may roll one additional die once they have generated Characteristics and " +
                "use the result in place of one of the previous rolls. The new number cannot be " +
                "less than the character's lowest roll.",
        "This Advantage is not compatible with the fourth method of generating Characteristics.",
        "You may take this Advantage as many times as you wish.",
        characteristicList,
        0,
        listOf(1),
        0,
        null,
        null
    )

    val martialMastery = Advantage(
        "Martial Mastery",
        "The Martial Knowledge of a character with this Advantage is superior to others " +
                "of his level. Usually, the character has received special training that has " +
                "allowed him to explore his Ki abilities - although it is also possible that he " +
                "has simply been born with enormous natural ability.",
        "Adds 40 points to a character's Martial Knowledge (MK). Additional Creation Points " +
                "increase this amount to 80 and 120 points, respectively. For example, a " +
                "character whose player spends 2 Creation Points would receive 80 additional " +
                "points to his Martial Knowledge.",
        null,
        null,
        null,
        null,
        listOf(1, 2, 3),
        0,
        {_, cost ->
            when(cost){
                1 -> charInstance.ki.updateMKSpec(40)
                2 -> charInstance.ki.updateMKSpec(80)
                3 -> charInstance.ki.updateMKSpec(120)
            }
        },
        {_, cost ->
            when(cost){
                1 -> charInstance.ki.updateMKSpec(-40)
                2 -> charInstance.ki.updateMKSpec(-80)
                3 -> charInstance.ki.updateMKSpec(-120)
            }
        }
    )

    val goodLuck = Advantage(
        "Good Luck",
        "Characters with this Advantage are exceptionally lucky in everything they do " +
                "and very rarely commit a serious mistake.",
        "The required number to fumble is reduced by 1 point. In normal circumstances, " +
                "therefore, the character will only fumble on a roll of 2. If a character with " +
                "this Advantage attains mastery in any ability, he will fumble only on a 1 when " +
                "using it.",
        null,
        null,
        null,
        null,
        listOf(1),
        0,
        null,
        null
    )

    val kiRecovery = Advantage(
        "Ki Recovery",
        "This Advantage allows a character's spiritual energy to recover more quickly than normal.",
        "The character recovers 1 point of Ki every ten minutes, instead of every hour. " +
                "Spending additional Creation Points decreases the recovery time to five minutes " +
                "and one minute, respectively.",
        null,
        null,
        null,
        null,
        listOf(1, 2, 3),
        0,
        null,
        null
    )

    val jackOfAllTrades = Advantage(
        "Jack of All Trades",
        "Characters with this Advantage can adapt to any need that arises and develop " +
                "knowledge in all fields and subjects. No matter how rare or unusual the " +
                "Secondary Ability needed, they will always shave some knowledge or skill useful " +
                "in that situation.",
        "The character never applies the -30 penalty for not spending DP in a Secondary " +
                "Ability, and he also receives a special bonus of +10 in all his Secondary Abilities.",
        null,
        null,
        null,
        null,
        listOf(2),
        0,
        {_, _ ->
            charInstance.secondaryList.allTradesTaken = true
            charInstance.secondaryList.fullList.forEach{it.refreshTotal()}
        },
        {_, _ ->
            charInstance.secondaryList.allTradesTaken = false
            charInstance.secondaryList.fullList.forEach{it.refreshTotal()}
        }
    )

    val naturalArmor = Advantage(
        "Natural Armor",
        "The character has extremely resistant skin and very hard muscles - such that it " +
                "is very difficult to penetrate them.",
        "Grants natural armor of 2 against all classes of attacks except Energy-based ones. " +
                "Although it counts as armor, penalties are not applied for using additional armor layers.",
        null,
        null,
        null,
        null,
        listOf(1),
        0,
        null,
        null
    )

    val mysticalArmor = Advantage(
        "Mystical Armor",
        "The character's aura forms a layer of mystical energy that protect him against " +
                "supernatural attacks.",
        "Grants a character a natural armor of 4 against Energy-based attacks. Although it " +
                "counts as armor, penalties are not applied for using additional armor layers.",
        null,
        null,
        null,
        null,
        listOf(1),
        0,
        null,
        null
    )

    val untiring = Advantage(
        "Untiring",
        "A character with this Advantage possesses a superior endurance relative to what " +
                "his Constitution would indicate.",
        "A player can add 3 points to his character's Fatigue. Spending additional " +
                "Creation Points adds 6 and 9 points respectively. Thus a character whose player " +
                "spends 2 Creation Points on this Advantage would add 6 to his Fatigue score.",
        null,
        null,
        null,
        null,
        listOf(1, 2, 3),
        0,
        {_, cost ->
            when(cost){
                1 -> charInstance.specFatigue += 3
                2 -> charInstance.specFatigue += 6
                3 -> charInstance.specFatigue += 9
            }

            charInstance.updateFatigue()
        },
        {_, cost ->
            when(cost){
                1 -> charInstance.specFatigue -= 3
                2 -> charInstance.specFatigue -= 6
                3 -> charInstance.specFatigue -= 9
            }

            charInstance.updateFatigue()
        }
    )

    val uncommonSize = Advantage(
        "Uncommon Size",
        "A character with this Advantage possesses an unusual Size relative to his " +
                "Strength and Constitution. This allows someone who should be a colossal mass of " +
                "muscles to be a small person, or vice versa.",
        "The player can increase or decrease his character's Size up to 5 points during " +
                "character creation.",
        null,
        null,
        listOf("-5", "-4", "-3", "-2", "-1", "1", "2", "3", "4", "5"),
        0,
        listOf(1),
        0,
        {input, _ ->
            charInstance.changeSize(input!!)
        },
        {input, _ ->
            charInstance.changeSize(9 - input!!)
        }
    )

    val startingWealth = Advantage(
        "Starting Wealth",
        "The character has a great fortune in materials and equipment.",
        "This Advantage provides starting money or equipment valued at 2,000 gold crowns " +
                "(GC). Further points spent increase this amount to 5,000 and 10,000 respectively.",
        "The Game Master may prefer to give a different amount of money, tailored to his " +
                "game. In this case, both the GM and the player should decide the amount.",
        null,
        null,
        null,
        listOf(1, 2, 3),
        0,
        null,
        null
    )

    val increasedRegeneration = Advantage(
        "Regeneration: Basic, Advanced, and Greater",
        "Wounds suffered by the character heal easily.",
        "This Advantage increases the character's Regeneration by two levels. Spending " +
                "additional points increases Regeneration by four and six levels, respectively.",
        null,
        null,
        null,
        null,
        listOf(1, 2, 3),
        0,
        {_, cost ->
            when(cost){
                1 -> charInstance.specRegen += 2
                2 -> charInstance.specRegen += 4
                3 -> charInstance.specRegen += 6
            }

            charInstance.updateRegeneration()
        },
        {_, cost ->
            when(cost){
                1 -> charInstance.specRegen -= 2
                2 -> charInstance.specRegen -= 4
                3 -> charInstance.specRegen -= 6
            }

            charInstance.updateRegeneration()
        }
    )

    val elan = Advantage(
        "Elan",
        "A character with this Advantage has attracted the attention of a Shajad or a " +
                "Beryl. Generally, this means that an ancestor of the character was bound to the " +
                "being and it still maintains some type of bond with all the members of the " +
                "family. It is also possible that this attention is due to some actions the " +
                "character has taken. At the moment, he enjoys its favor - although after the " +
                "player begins playing his character, the relationship will depend on how he behaves.",
        "The character has Elan 25 for the entity he chooses. Spending additional points " +
                "increases the level to 45 and 60, respectively. Thus, a character whose player " +
                "spent 2 Creation Points on this Advantage would have Elan of 45 with the " +
                "particular Shajad or Beryl whose favor he enjoys.",
        null,
        null,
        null,
        null,
        listOf(1, 2, 3),
        0,
        null,
        null
    )

    val painImmunity = Advantage(
        "Immunity to Pain and Fatigue",
        "A character with this Advantage is especially resistant to the effects of pain " +
                "and fatigue.",
        "Penalties caused by pain and Fatigue are reduced by half.",
        null,
        null,
        null,
        null,
        listOf(1),
        0,
        null,
        null
    )

    val gift = Advantage(
        "The Gift",
        "The character can feel and control supernatural energies inherent within his own " +
                "soul. Magic flows through his very spirit, and with the appropriate knowledge, " +
                "the character with this Advantage will be able to cast spells.",
        "The character can see and use magic. He also adds a special bonus of +10 to his " +
                "MR, since his supernatural nature better resists mystical effects.",
        null,
        null,
        null,
        null,
        listOf(2),
        0,
        null,
        null
    )

    val seeSupernatural = Advantage(
        "See Supernatural",
        "A character with this Advantage can perceive the Soul Flow and at the same " +
                "time perceive the energy of psychic matrices.",
        "The character sees supernatural things - including magic and psychic matrices - as " +
                "spiritual creatures. He does not apply the blinded penalty in any of these situations.",
        null,
        null,
        null,
        null,
        listOf(1),
        0,
        null,
        null
    )

    val nightVision = Advantage(
        "Night Vision",
        "This Advantage allows a character to see in the dark and to adapt quickly to " +
                "any change in light intensity.",
        "The character may ignore any penalty caused by the dark - except for magically " +
                "induced dark or absolute lack of light, in which case the penalty is reduced " +
                "by half.",
        null,
        null,
        null,
        null,
        listOf(1),
        0,
        null,
        null
    )

    val fortunate = Advantage(
        "Fortunate",
        "A Fortunate character enjoys true luck. He can often escape difficult " +
                "situations due to his lucky star.",
        "The limits of this Advantage must be decided by the Game Master. In any case, the " +
                "character will never suffer the negative effects of a trap or an attack " +
                "determined solely by chance.",
        null,
        null,
        null,
        null,
        listOf(1),
        0,
        null,
        null
    )

    val freePsychicDiscipline = Advantage(
        "Free Access to Any Psychic Discipline",
        "Characters with this Advantage may use any type of psychic discipline and its abilities.",
        "This Advantage enables the character to use as many Psychic Disciplines as he " +
                "wishes using his Psychic Points.",
        null,
        null,
        null,
        null,
        listOf(2),
        0,
        {_, _ ->
            val removable = charInstance.advantageRecord.getAdvantage("Access to One Psychic Discipline")
            if(removable != null)
                charInstance.advantageRecord.removeAdvantage(removable)

            charInstance.psychic.legalDisciplines.addAll(charInstance.psychic.allDisciplines)
        },
        {_, _ ->
            charInstance.psychic.allDisciplines.forEach{
                charInstance.psychic.removeLegalDiscipline(it)
            }
        }
    )

    val quickReflexes = Advantage(
        "Quick Reflexes",
        "The character has exceptional reflexes that allow him to respond quickly to " +
                "any situation.",
        "Grants a special bonus of +25 to a character's initiative score. Spending " +
                "additional Creation Points will increase this bonus to +45 and +60, respectively. " +
                "Thus, a character whose player spends 2 Creation Points on this Advantage " +
                "receives a +45 bonus to his Initiative score.",
        null,
        null,
        null,
        null,
        listOf(1, 2, 3),
        0,
        {_, cost ->
            when(cost){
                1 -> charInstance.changeSpecInitiative(25)
                2 -> charInstance.changeSpecInitiative(45)
                3 -> charInstance.changeSpecInitiative(60)
            }
        },
        {_, cost ->
            when(cost){
                1 -> charInstance.changeSpecInitiative(-25)
                2 -> charInstance.changeSpecInitiative(-45)
                3 -> charInstance.changeSpecInitiative(-60)
            }
        }
    )

    val learning = Advantage(
        "Learning",
        "Characters with this Advantage possess an enormous capacity to learn and " +
                "develop their potential, always gaining the maximum benefit from whatever they " +
                "have seen or done.",
        "Characters gain an additional 3 Experience Points when the Game Master grants " +
                "points at the end of each game session. Spending additional Creation Points " +
                "increases the benefit to 6 and 9 points respectively.",
        null,
        null,
        null,
        null,
        listOf(1, 2, 3),
        0,
        null,
        null
    )

    val naturalLearner = Advantage(
        "Natural Learner",
        "Characters with this Advantage naturally improve in a specific Secondary Ability.",
        "Grants a character an innate special modifier of +10 per level in a single " +
                "Secondary Ability. Add this modifier to any other innate class-based bonus the " +
                "character receives. Spending additional points on this Advantage increases the " +
                "bonus to +20 and +30, respectively. Thus, a character whose player spends 2 " +
                "Creation Points on this Advantage would receive a modifier of +20 per level to " +
                "a single Secondary Ability.",
        null,
        null,
        secondariesList,
        0,
        listOf(1, 2, 3),
        0,
        {input, cost ->
            val characteristic = charInstance.secondaryList.fullList[input!!]

            when(cost){
                1 -> characteristic.setSpecialPerLevel(10)
                2 -> characteristic.setSpecialPerLevel(20)
                3 -> characteristic.setSpecialPerLevel(30)
            }
        },
        {input, cost ->
            val characteristic = charInstance.secondaryList.fullList[input!!]

            when(cost){
                1 -> characteristic.setSpecialPerLevel(-10)
                2 -> characteristic.setSpecialPerLevel(-20)
                3 -> characteristic.setSpecialPerLevel(-30)
            }
        }
    )

    val fieldLearner = Advantage(
        "Natural Learner (Field)",
        "As Natural Learner, but in this case the character improves in all the " +
                "Secondary Abilities that belong to a certain field.",
        "Grants a special +5 per level bonus to all the Secondary Abilities in a field. Add " +
                "this bonus to any other innate class-based bonus the character receives. " +
                "Spending an additional point on this Advantage increases the bonus to +10. Thus " +
                "a character whose player spends 3 Creation Points on this Advantage would receive " +
                "a modifier of +10 per level to all Secondary Abilities in Athletics, for example.",
        null,
        null,
        fieldNames,
        0,
        listOf(2, 3),
        0,
        {input, cost ->
            val charList = charInstance.secondaryList.intToField(input!!)
            charList.forEach{
                when(cost){
                    2 -> it.setSpecialPerLevel(5)
                    3 -> it.setSpecialPerLevel(10)
                }
            }
        },
        {input, cost ->
            val charList = charInstance.secondaryList.intToField(input!!)
            charList.forEach{
                when(cost){
                    2 -> it.setSpecialPerLevel(-5)
                    3 -> it.setSpecialPerLevel(-10)
                }
            }
        }
    )

    val exceptionalMagicResistance = Advantage(
        "Exceptional Magic Resistance",
        "Characters with this Advantage possess heightened resistance to magical attacks and effects.",
        "Add a special bonus of +25 to Magic Resistance (MR). Spending a second Creation " +
                "Point increases the bonus to +50.",
        null,
        null,
        null,
        null,
        listOf(1, 2),
        0,
        {_, cost ->
            when(cost){
                1 -> charInstance.rmSpec += 25
                2 -> charInstance.rmSpec += 50
            }

            charInstance.updateResistances()
        },
        {_, cost ->
            when(cost){
                1 -> charInstance.rmSpec -= 25
                2 -> charInstance.rmSpec -= 50
            }

            charInstance.updateResistances()
        }
    )

    val exceptionalPhysicalResistance = Advantage(
        "Exceptional Physical Resistance",
        "Characters with this Advantage possess heightened resistance to physical " +
                "attacks and effects. Damage, poisons, and disease do not affect them as they do " +
                "other people.",
        "Add a special bonus of +25 to Physical Resistance (PhR), Venom Resistance (VR), " +
                "and Disease Resistance (DR). Spending a second Creation Point increases the " +
                "bonus to +50.",
        null,
        null,
        null,
        null,
        listOf(1, 2),
        0,
        {_, cost ->
            when(cost){
                1 -> {charInstance.rphysSpec += 25; charInstance.rvSpec += 25; charInstance.rdSpec += 25}
                2 -> {charInstance.rphysSpec += 50; charInstance.rvSpec += 50; charInstance.rdSpec += 50}
            }

            charInstance.updateResistances()
        },
        {_, cost ->
            when(cost){
                1 -> {charInstance.rphysSpec -= 25; charInstance.rvSpec -= 25; charInstance.rdSpec -= 25}
                2 -> {charInstance.rphysSpec -= 50; charInstance.rvSpec -= 50; charInstance.rdSpec -= 50}
            }

            charInstance.updateResistances()
        }
    )

    val exceptionalPsychicResistance = Advantage(
        "Exceptional Psychic Resistance",
        "The mind fo a character with this Advantage possesses very strong mental " +
                "barriers that protect him from psychic attacks and other negative psychic effects.",
        "This Advantage adds a special bonus of +25 to a character's Psychic Resistance " +
                "(PsR). Spending a second Creation Point increases the bonus to +50.",
        null,
        null,
        null,
        null,
        listOf(1, 2),
        0,
        {_, cost ->
            when(cost){
                1 -> charInstance.rpsySpec += 25
                2 -> charInstance.rpsySpec += 50
            }

            charInstance.updateResistances()
        },
        {_, cost ->
            when(cost){
                1 -> charInstance.rpsySpec -= 25
                2 -> charInstance.rpsySpec -= 50
            }

            charInstance.updateResistances()
        }
    )

    val lightSleeper = Advantage(
        "Light Sleeper",
        "A character with this Advantage remains partially conscious while sleeping and " +
                "is able to wake at the slightest noise or movement.",
        "The character applies a penalty of only -20 to his Notice ability while sleeping.",
        null,
        null,
        null,
        null,
        listOf(1),
        0,
        null,
        null
    )

    val advantages = listOf(characteristicPoint, acuteSenses, artifact, ambidextrous, characteristicToNine,
        psyDisciplineAccess, charm, disquieting, animalAffinity, dangerSense, beenAround,
        subjectAptitude, naturalPsychicPower, fieldAptitude, characteristicReroll, martialMastery,
        goodLuck, kiRecovery, jackOfAllTrades, naturalArmor, mysticalArmor, untiring, uncommonSize,
        startingWealth, increasedRegeneration, elan, painImmunity, gift, seeSupernatural, nightVision,
        fortunate, freePsychicDiscipline, quickReflexes, learning, naturalLearner, fieldLearner,
        exceptionalMagicResistance, exceptionalPhysicalResistance, exceptionalPsychicResistance,
        lightSleeper)





    val badLuck = Advantage(
        "Bad Luck",
        "Characters with this Disadvantage have very bad luck in doing what they set " +
                "out to do; they fail much more than they would like.",
        "The required number for a fumble increases by 2 points. Normal abilities, " +
                "therefore, fumble on a result of 5 (4 if the character possesses mastery in that " +
                "ability).",
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val blind = Advantage(
        "Blind",
        "A character with this Disadvantage is completely blind.",
        "The character cannot use any ability that requires sight. He applies the blinded " +
                "penalty at all times.",
        null,
        null,
        null,
        null,
        listOf(-2),
        0,
        null,
        null
    )

    val deafness = Advantage(
        "Deafness",
        "A character with this Disadvantage cannot hear anything.",
        "The character cannot use any ability that requires hearing.",
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val mute = Advantage(
        "Mute",
        "A character with this Disadvantage is incapable of speaking.",
        "The character cannot speak.",
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val nearsighted = Advantage(
        "Nearsighted",
        "A character with this Disadvantage cannot see well. Many things appear blurry, " +
                "and he has difficulty even reading.",
        "Apply a -50 penalty to any Notice and Search rolls using vision, and a -3 to any " +
                "Perception Checks that require it. This penalty also applies to aiming. A " +
                "character can reduce this penalty somewhat (as determined by the GM) by obtaining " +
                "glasses.",
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val exclusiveWeapon = Advantage(
        "Exclusive Weapon",
        "A character with this Disadvantage is accustomed to fighting exclusively with " +
                "a specific weapon and therefore is less able to fight with other weapons.",
        "The character applies a penalty of -30 to his combat ability with any weapon other " +
                "than his preferred one.",
        "Only classes in the Domine, Fighter, Prowler, and Novel Archetypes can acquire " +
                "this Disadvantage.",
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val severeAllergy = Advantage(
        "Severe Allergy",
        "A character with this Disadvantage suffers from some type of allergy which is " +
                "so serious that, by mere contact or inhalation, he will have a terrible allergic " +
                "reaction lasting hours. Some examples of typical allergies are to metal, pollen, " +
                "or even sunlight.",
        "On making contact with the allergen, a character suffers penalties between -40 to " +
                "-80 on all actions, depending on the severity or the length of time in contact " +
                "with the allergen.",
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val seriousVice = Advantage(
        "Addiction or Serious Vice",
        "A character with this Disadvantage has an urgent need to take some type of " +
                "action or to consume a specific substance daily and will do anything necessary " +
                "to satisfy his vice - otherwise he will begin to feel very nervous and go into " +
                "withdrawal.",
        "The character applies a cumulative penalty of -10 for every day that passes " +
                "without satisfying his addiction (up to -100).",
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val atrophiedLimb = Advantage(
        "Atrophied Limb",
        "A character with this Disadvantage has a severe problem with one of his limbs. " +
                "This limb may shake all the time, or it may not respond when most needed.",
        "The character applies a penalty of -80 to all physical actions that require the " +
                "use of the atrophied limb.",
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val seriousIllness = Advantage(
        "Serious Illness",
        "A character with this Disadvantage suffers from some type of degenerative " +
                "disease that will end up killing him. Usually, he has an average of little more " +
                "than half a year of life remaining when beginning the game. However, the period " +
                "can be greater or lesser if the GM needs it to fit within the time frame of his " +
                "campaign. A character with this Disadvantage is not only very playable, but he " +
                "can also have an additional objective in finding a cure for himself.",
        "The character applies a cumulative penalty of -10 to all actions for each month of " +
                "game time that passes. The GM secretly determines the date when the character " +
                "will die.",
        null,
        null,
        null,
        null,
        listOf(-2),
        0,
        null,
        null
    )

    val physicalWeakness = Advantage(
        "Physical Weakness",
        "A character with this Disadvantage is exceptionally weak physically; whenever " +
                "he receives a critical wound, he has a strong possibility of dying or of " +
                "suffering irreversible damage.",
        "Reduce the Physical Resistance (PhR) of a character by half.",
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        {_, _ ->
            charInstance.rphysMult = 0.5
            charInstance.updateResistances()
        },
        {_, _ ->
            charInstance.rphysMult = 1.0
            charInstance.updateResistances()
        }
    )

    val deepSleeper = Advantage(
        "Deep Sleeper",
        "A character with this Disadvantage sleeps very deeply and has difficulty " +
                "awakening. He will remain asleep even with light physical contact, and when he " +
                "finally does awaken, he will be stunned for several minutes.",
        "The character applies a penalty of -200 to any Perceptive roll while sleeping. For " +
                "the first ten turns after waking, he has a penalty of -40 to all actions.",
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val deductCharacteristic = Advantage(
        "Deduct Two Points from a Characteristic",
        "One of the Characteristics of a character with this Disadvantage is less " +
                "developed than it should be.",
        "Deduct 2 points from one of the character's Primary Characteristics.",
        "Characters can only acquire this Disadvantage once. You cannot reduce a " +
                "Characteristic below 3.",
        null,
        characteristicList,
        0,
        listOf(-1),
        0,
        {input, _ ->
            when(input){
                0 -> charInstance.setStrBonus(-2)
                1 -> charInstance.setDexBonus(-2)
                2 -> charInstance.setAgiBonus(-2)
                3 -> charInstance.setConBonus(-2)
                4 -> charInstance.setIntBonus(-2)
                5 -> charInstance.setPowBonus(-2)
                6 -> charInstance.setWpBonus(-2)
                7 -> charInstance.setPerBonus(-2)
            }
        },
        {input, _ ->
            when(input){
                0 -> charInstance.setStrBonus(2)
                1 -> charInstance.setDexBonus(2)
                2 -> charInstance.setAgiBonus(2)
                3 -> charInstance.setConBonus(2)
                4 -> charInstance.setIntBonus(2)
                5 -> charInstance.setPowBonus(2)
                6 -> charInstance.setWpBonus(2)
                7 -> charInstance.setPerBonus(2)
            }
        }
    )

    val unfortunate = Advantage(
        "Unfortunate",
        "Misfortune follows the character wherever he goes. Terrible things happen to " +
                "him no matter how much he tries to avoid them.",
        "The GM will have to interpret the limits of this Disadvantage. In a group, an " +
                "Unfortunate character will always be the one who \"randomly\" falls in the " +
                "trap, and he will be the first one attacked when chance decides who is hit first.",
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val easilyPossessed = Advantage(
        "Easily Possessed",
        "A character with this Disadvantage is easily controlled by any being with the " +
                "ability to affect his mind or alter his personality - even if the character's " +
                "will is strong.",
        "The character receives -50 to any Physical Resistance or Magic Resistance against " +
                "any type of domination or possession capable of modifying his conduct.",
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val exhausted = Advantage(
        "Exhausted",
        "A character with this Disadvantage is vulnerable to Fatigue. Not only will he " +
                "tire more easily than others, but he particularly suffers the effects of Fatigue.",
        "Doubles Fatigue penalties to actions and reduces the base Fatigue of the character " +
                "by 1 point.",
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        {_, _ ->
            charInstance.specFatigue -= 1
            charInstance.updateFatigue()
        },
        {_, _ ->
            charInstance.specFatigue += 1
            charInstance.updateFatigue()
        }
    )

    val severePhobia = Advantage(
        "Severe Phobia",
        "A character with this Disadvantage experiences a terrible fear of something, " +
                "which forces him to behave irrationally in its presence. The exact nature of " +
                "the phobia is at the discretion of the GM.",
        "The character suffers the Fear State whenever he encounters the object of his phobia.",
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val painVulnerability = Advantage(
        "Vulnerability to Pain",
        "The character has no resistance to physical pain, which terrifies him.",
        "Doubles any penalty caused by pain, including those produced by criticals or " +
                "mystical effects.",
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        null,
        null
    )

    val sickly = Advantage(
        "Sickly",
        "A character with this Disadvantage suffers from bad health and sickens easily.",
        "Reduce the character's Disease Resistance (DR) by half.",
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        {_, _ ->
            charInstance.rdMult = 0.5
            charInstance.updateResistances()
        },
        {_, _ ->
            charInstance.rdMult = 1.0
            charInstance.updateResistances()
        }
    )

    val slowHealer = Advantage(
        "Slow Healer",
        "A character with this Disadvantage possesses a very low recuperative capacity " +
                "and recovers from wounds with great difficulty - even with supernatural aid.",
        "The character recovers only half the Life Points he should by whatever means, " +
                "whether through normal or magical recuperation.",
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        {_, _ ->
            charInstance.specRegen -= 1
            charInstance.updateRegeneration()
        },
        {_, _ ->
            charInstance.specRegen += 1
            charInstance.updateRegeneration()
        }
    )

    val slowLearner = Advantage(
        "Slow Learner",
        "A character with this Disadvantage cannot learn as quickly as a normal individual.",
        "The character suffers a penalty of -4 Experience Points to those granted by the GM " +
                "at the end of a session. An additional point in this Disadvantage increases the " +
                "penalty to -8.",
        null,
        null,
        null,
        null,
        listOf(-1, -2),
        0,
        null,
        null
    )

    val slowReactions = Advantage(
        "Slow Reactions",
        "The character's reflexes leave him ill-prepared to respond quickly to events.",
        "The character applies a special penalty of -30 to his Initiative. An additional " +
                "point in this Disadvantage increases the penalty to -60.",
        null,
        null,
        null,
        null,
        listOf(-1, -2),
        0,
        {_, cost ->
            when(cost){
                -1 -> charInstance.specInitiative -= 30
                -2 -> charInstance.specInitiative -= 60
            }

            charInstance.updateInitiative()
        },
        {_, cost ->
            when(cost){
                -1 -> charInstance.specInitiative += 30
                -2 -> charInstance.specInitiative += 60
            }

            charInstance.updateInitiative()
        }
    )

    val magicSusceptibility = Advantage(
        "Magic Susceptibility",
        "A character with this Disadvantage is easily affected by magical energies",
        "Reduce the character's MR by half.",
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        {_, _ ->
            charInstance.rmMult = 0.5
            charInstance.updateResistances()
        },
        {_, _ ->
            charInstance.rmMult = 1.0
            charInstance.updateResistances()
        }
    )

    val poisonSusceptibility = Advantage(
        "Susceptible to Poisons",
        "A character with this Disadvantage cannot combat the negative effects of any " +
                "type of harmful substance.",
        "Reduce the character's Venom Resistance (VR) by half.",
        null,
        null,
        null,
        null,
        listOf(-1),
        0,
        {_, _ ->
            charInstance.rvMult = 0.5
            charInstance.updateResistances()
        },
        {_, _ ->
            charInstance.rvMult = 1.0
            charInstance.updateResistances()
        }
    )

    val unattractive = Advantage(
        "Unattractive",
        "A character with this Disadvantage suffers from terrible deformities that make " +
                "him very distasteful to look upon.",
        "This Disadvantage reduces a character's Appearance to 2.",
        "The character must have a minimum of 7 in Appearance and it must have been " +
                "generated by means of a die roll, not chosen by the player.",
        null,
        null,
        null,
        listOf(-1),
        0,
        {_, _ ->
            charInstance.setAppearance(2)
        },
        null
    )

    val temperatureVulnerability = Advantage(
        "Vulnerable to Heat/Cold",
        "A character with this Disadvantage is particularly vulnerable to heat or " +
                "cold (player's choice).",
        "The character suffers a penalty of -80 to his Resistance against the chosen " +
                "element and -30 to all actions in extreme climates.",
        null,
        null,
        listOf("Heat", "Cold"),
        0,
        listOf(-1),
        0,
        null,
        null
    )

    val disadvantages = listOf(badLuck, blind, deafness, mute, nearsighted, exclusiveWeapon, severeAllergy,
        seriousVice, atrophiedLimb, seriousIllness, physicalWeakness, deepSleeper, deductCharacteristic,
        unfortunate, easilyPossessed, exhausted, severePhobia, painVulnerability, sickly, slowHealer,
        slowLearner, slowReactions, magicSusceptibility, poisonSusceptibility, unattractive,
        temperatureVulnerability)
}