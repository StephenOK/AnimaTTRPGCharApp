package com.example.animabuilder.character_creation.attributes.magic.spells.spellbook

import com.example.animabuilder.character_creation.Element
import com.example.animabuilder.character_creation.attributes.magic.spells.Spell
import com.example.animabuilder.character_creation.attributes.magic.spells.SpellType
import java.io.Serializable

class LightBook: Serializable {
    private val createLight = Spell(
        "Create Light",
        Element.Light,
        true,
        2,
        20,
        "Create light in a radius of 5 meters.",
        "+5 meters radius",
        20,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val induceCalm = Spell(
        "Induce Calm",
        Element.Light,
        true,
        6,
        40,
        "Calms individuals feeling fear or hatred within 15 meters of the caster. Makes any " +
                "Fear, Terror, or Anger States disappear, even if of supernatural origin. It does " +
                "not prevent violent actions deliberately done in cold blood. The MR or PsR Check " +
                "to overcome this spell has a Difficulty of 80.",
        "+10 meters to radius and +5 to MR or PsR Difficulty",
        10,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val blindingFlash = Spell(
        "Blinding Flash",
        Element.Light,
        true,
        8,
        50,
        "Causes a sudden flash of light for a radius of 15 meters. It blinds anyone looking " +
                "at it when it goes off for as many combat turns as he fails the MR check by, " +
                "divided by 10. It is not possible to designate specific targets within the " +
                "flash, and everyone except the caster is equally effected. Characters can resist " +
                "this spell by passing a PhR Check with a Difficulty Check of 140. If someone is " +
                "actively avoiding looking at the flash, the PhR Check Difficulty is 80.",
        "+5 meters to radius",
        10,
        null,
        false,
        listOf(SpellType.Automatic)
    )

    private val lightShield = Spell(
        "Shield of Light",
        Element.Light,
        false,
        10,
        50,
        "Forms a barrier of Energy that protects the caster from any source of attack. " +
                "The shield can absorb up to 300 points before breaking, but is only damaged by " +
                "supernatural attacks. Impacts based on Darkness cause double damage.",
        "+100 Resistance Points",
        20,
        10,
        false,
        listOf(SpellType.Defense)
    )

    private val perceiveSpell = Spell(
        "Perceive",
        Element.Light,
        true,
        12,
        50,
        "This spell improves the perception of the caster, increasing his Secondary " +
                "Abilities of Notice and Search by +50. It also increases his Magical Appraisal " +
                "by the same amount, but only for the purpose of detecting or measuring the " +
                "magic potency of something or someone, not to hide it.",
        "+10 to Notice, Search, and Magic Appraisal",
        10,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val lightArmor = Spell(
        "Armor of Light",
        Element.Light,
        true,
        16,
        60,
        "Forms a mystical armor with AT 2 against Energy-based Attacks, and an AT of 1 " +
                "against all others. Although it counts as armor, it does not count as an " +
                "additional layer of armor for purposes of penalties to Initiative.",
        "+1 to the AT",
        10,
        20,
        false,
        listOf(SpellType.Effect)
    )

    private val banishShadows = Spell(
        "Banish Shadows",
        Element.Light,
        true,
        18,
        60,
        "Destroys shadows within a radius of 10 meters. Any darkness based creatures within " +
                "the radius must pass a MR Check with a Difficulty of 120, or lose double their " +
                "Failure level in Life Points (Damage Resistance creatures increases this amount " +
                "by its Damage Resistance multiple). As long as the spell is maintained over " +
                "the creatures, they must do a new MR Check each combat turn.",
        "+20 meters to radius and 10 to the MR Difficulty",
        10,
        10,
        false,
        listOf(SpellType.Spiritual, SpellType.Effect)
    )

    private val detectNegativeEmotions = Spell(
        "Detect Negative Emotions",
        Element.Light,
        true,
        20,
        50,
        "Detects any negative sentiments such as hatred, fear, or anger within 10 meters " +
                "of the caster. It also senses creatures based on such emotions. Resisting the " +
                "spell requires beating a MR Check of with a Difficulty fo 80.",
        "+20 meters to radius and +10 to the MR Difficulty",
        10,
        10,
        false,
        listOf(SpellType.Detection)
    )

    private val lightBeam = Spell(
        "Light Beam",
        Element.Light,
        true,
        22,
        50,
        "Projects a beam of Light-based magical energy. Light Beam is an Energy Attack Type " +
                "with a Base Damage of 60.",
        "+5 to Base Damage",
        10,
        null,
        false,
        listOf(SpellType.Attack)
    )

    private val hologram = Spell(
        "Hologram",
        Element.Light,
        true,
        26,
        40,
        "Creates an immaterial luminous form with a maximum size of one square meter. The " +
                "caster can give the hologram the appearance he desires, making it very difficult " +
                "to tell it from something real. If he creates a creature, it can perform any " +
                "inhuman action the caster wishes, but will mimic the physical abilities of the " +
                "caster. If, for example, the hologram is used to simulate an attack, it uses " +
                "the combat ability of the caster. The hologram can not touch anyone nor be " +
                "touched, but if it receives any damage, based on Energy, it disappears. To " +
                "detect that a hologram is not real, it is necessary to beat a Notice check " +
                "against a Difficulty of Almost Impossible, or Search against Very Difficult.",
        "+1 square meter to maximum size",
        20,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val lightBond = Spell(
        "Bonds of Light",
        Element.Light,
        true,
        28,
        60,
        "It casts bonds of light that hold the designated target immobile. An attack is made " +
                "using the rules for Trapping, although the caster suffers no penalty to his " +
                "Ability for performing this maneuver. The bonds use a Strength of 8 for any " +
                "check. If anyone tries to help free the person Trapped, the Bonds of Light are " +
                "treated as an Energy weapon with a Fortitude of 20.",
        "+1 to Strength for all checks",
        10,
        10,
        false,
        listOf(SpellType.Attack)
    )

    private val controlLight = Spell(
        "Control Light",
        Element.Light,
        true,
        30,
        50,
        "Modifies and controls the form, color, and intensity of light in a 20 meter radius. " +
                "If cast at Light-based beings, they must pass a MR Check with a Difficulty of 80 " +
                "or they will fall under the control of the caster. A creature can only repeat " +
                "the check if it is ordered to do something against its nature.",
        "+10 meters to radius and +5 to MR Difficulty",
        20,
        10,
        false,
        listOf(SpellType.Spiritual, SpellType.Effect)
    )

    private val detectLife = Spell(
        "Detect Life",
        Element.Light,
        true,
        32,
        60,
        "Detects any life-form within 25 meters. The spell only detects the number of " +
                "life-forms and their exact location. Resisting the spell requires beating a " +
                "MR Check with a Difficulty of 140.",
        "+10 meters to radius and +10 to MR Difficulty",
        10,
        20,
        false,
        listOf(SpellType.Detection)
    )

    private val lightSpy = Spell(
        "Spy of Light",
        Element.Light,
        true,
        36,
        100,
        "Creates a small light of energy that moves as wished by the caster, with a Flight " +
                "Value of 14, for a maximum distance of one kilometer. Through it, the caster can see " +
                "and hear as though he were present, but doing so overwhelms his body\'s senses, " +
                "and he can only perceive the world through the Spy of Light. Each combat turn, " +
                "the caster decides if he will see through the Spy of Light or his own senses. " +
                "The Spy of Light has an ability of 100 at Notice and Search. If attacked, it " +
                "can defend itself with the Magic Projection of its caster. For purposes of " +
                "Initiative, it acts when its controller does. It is only possible to attack it " +
                "with supernatural attacks, although it is destroyed if it receives any damage.",
        "+5 to Notice and Search and +1 kilometer to range",
        20,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val ecstasy = Spell(
        "Ecstasy",
        Element.Light,
        true,
        38,
        60,
        "This spell intoxicates anyone affected with a feeling of utter ecstasy. The " +
                "sensation of pleasure is so powerful that the victim\'s senses are completely " +
                "clouded, and he receives a -20 All Action Penalty while affected. However, the " +
                "spell\'s victims are also completely oblivious and immune to any pain or other " +
                "affliction based penalty, except those for actually being physically " +
                "incapacitated. The MR Check to resist this spell has a Difficulty of 80, and " +
                "affects a radius of 10 meters.",
        "+10 meters to radius and +5 to MR Difficulty",
        10,
        10,
        false,
        listOf(SpellType.Spiritual)
    )

    private val banishNegativeEmotions = Spell(
        "Banish Negative Emotions",
        Element.Light,
        true,
        40,
        80,
        "Temporarily banishes any negative sentiments such as hatred, fear, or anger within " +
                "100 meters of the caster. Resisting the spell requires beating a MR or PsR Check " +
                "with a Difficulty of 100.",
        "+50 meters to radius and +5 to MR or PsR Difficulty",
        20,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val healingLight = Spell(
        "Healing Light",
        Element.Light,
        true,
        42,
        80,
        "Causes whomever the spell is directed at to recover 40 Life Points. This spell " +
                "does not restore permanently lost or destroyed limbs, nor eliminate penalties " +
                "caused by Critical attacks.",
        "+5 Life Points",
        10,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val seekingSphere = Spell(
        "Seeking Sphere",
        Element.Light,
        true,
        46,
        120,
        "Unleashes a sphere of luminous energy with Base Damage of 100. The caster can " +
                "control it using his Magic Projection until it hits its target. If the target " +
                "successfully dodges, the Seeking Sphere can continue attacking the following " +
                "turn, since it has not been destroyed. When seeking sphere causes damage, or " +
                "is blocked, the sphere explodes and vanishes. If the caster abandons control of " +
                "it, it will act independently, following its last designated target with a " +
                "Magic Projection of 150.",
        "+5 to Base Damage and +5 to the Magic Projection of the Seeking Sphere",
        20,
        10,
        false,
        listOf(SpellType.Attack)
    )

    private val detectionZone = Spell(
        "Zone of Detection",
        Element.Light,
        true,
        48,
        140,
        "This spell allows the caster to detect any being within the area of the spell who " +
                "does not beat a MR Check with a Difficulty of 180. The Zone of Detection only " +
                "tells the caster how many individuals are in the zone and their exact " +
                "location. It also senses spells of Detection that attempt to enter into the " +
                "area, as long as the spellcaster using them does not beat the MR (regardless " +
                "of his actual location). The affected zone can be no larger than 20 meters in " +
                "radius, and is stationary in the place it was cast.",
        "+10 meters to radius and +10 to MR Difficulty",
        20,
        20,
        true,
        listOf(SpellType.Detection)
    )

    private val enterDreams = Spell(
        "Enter Another\'s Dreams",
        Element.Light,
        true,
        50,
        120,
        "This allows the caster to physically enter a sleeper\'s dreams. The caster has no " +
                "control over the dream world of the dreamer, and anything that happens there " +
                "will be real to the caster. The person must have peaceful dreams to be affected " +
                "by this spell, and the moment the dream turns into a nightmare, or he awakens " +
                "or dies, the mage abandons the dream world and returns to reality. Any Spiritual " +
                "spell cast on the dreamer while the caster is present in his dreams will also " +
                "affect the caster. The MR or PsR Check has a Difficulty of 140. Once he is in " +
                "the target person\'s dreams, the caster can jump to the unconscious of yet another " +
                "dreamer who is physically no more than 10 meters from the original sleeper. " +
                "Naturally, this new dreamer will have the right to his own MR Check. If the " +
                "dreamer\'s consciousness happens to be in the world of The Wake, the caster is " +
                "trapped there even when the spell expires.",
        "+10 meters additional to jumping range and +5 to MR or PsR Difficulty",
        20,
        20,
        true,
        listOf(SpellType.Spiritual)
    )

    private val lightForm = Spell(
        "Light Form",
        Element.Light,
        true,
        52,
        100,
        "The body designated by the caster is transformed into pure luminous energy and " +
                "becomes intangible to matter and non-Energy attacks. While in this state, the " +
                "transformed person gains a +50 bonus to his abilities of Notice and Search, and " +
                "a +30 to his Resistance against affects based on Light. In this state, the " +
                "damage caused by Darkness-based attacks is doubled. The maximum presence that " +
                "can be affected is 100.",
        "+10 to the maximum Presence that can be affected",
        10,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val blessingSpell = Spell(
        "Blessing",
        Element.Light,
        true,
        56,
        100,
        "Blessing endows the affected party with incredible energy. Those affected receive " +
                "a bonus of +20 to all their actions and Resistances. All allies within 5 meters " +
                "of the caster are affected. No one can be affected by more than one Blessing at a time.",
        "+5 meters to radius",
        20,
        20,
        false,
        listOf(SpellType.Effect)
    )

    private val createGoodFeelings = Spell(
        "Create Good Feelings",
        Element.Light,
        true,
        58,
        100,
        "This creates positive sentiments such as love, pleasure, or friendship in the " +
                "people designated by the caster. The radius of the spell is 20 meters, and the MR " +
                "or PsR Check has a Difficulty of 120. Those affected can repeat the Resistance " +
                "Check once per day.",
        "+5 to MR or PsR Difficulty and +10 meters radius",
        20,
        10,
        true,
        listOf(SpellType.Spiritual)
    )

    private val seeTruth = Spell(
        "See Truth",
        Element.Light,
        true,
        60,
        100,
        "Permits the affected person to perceive supernatural forces that are invisible to " +
                "the human eye, including magic, psychic matrices, and invisible or spiritual " +
                "beings. Although this spell does not directly work against Illusion spells, " +
                "because they affect the mind, not the vision, anyone using See Truth against " +
                "visual illusions can apply a +50 bonus to the MR Check, since it would help " +
                "detect the falseness. The maximum Presence that can be affected is 80.",
        "+10 to the maximum Presence affected",
        10,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val shieldFromNegative = Spell(
        "Shield From Negative",
        Element.Light,
        true,
        62,
        140,
        "This spell enchants a certain area, making it impenetrable for beings naturally " +
                "based in negative emotions or Darkness. Any such creature entering the zone must " +
                "pass a MR Check with a Difficulty of 120 or suffer the loss of a number of Life " +
                "Points equal to the margin of failure. Additionally, if it fails the check, it " +
                "receives an immediate -40 All Action Penalty. The affected zone can be no larger " +
                "than 20 meters in radius, and is stationary in the place it was cast.",
        "+20 meters to radius and +5 to the MR Difficulty",
        20,
        10,
        true,
        listOf(SpellType.Automatic)
    )

    private val findSpell = Spell(
        "Find",
        Element.Light,
        true,
        66,
        160,
        "By means of Find, the caster can locate any person, place, or thing, and know its " +
                "exact location in that moment regardless of the distance separating them. " +
                "Anything can be found, whether an individual object, a type of object, or " +
                "simply something that fulfills a specified condition. For example, the caster " +
                "can try to locate a city, the thief who stole his crosier (even if he doesn\'t " +
                "know who did it), or the closest eligible maiden of royal blood. Objects, " +
                "places, or people affected must make a dice roll against a MR Check with a " +
                "Difficulty of 140 to avoid being located. Large places apply a -40 penalty to " +
                "this check.",
        "+10 to the MR Difficulty",
        20,
        null,
        false,
        listOf(SpellType.Detection)
    )

    private val restoreSpell = Spell(
        "Restore",
        Element.Light,
        true,
        68,
        160,
        "This spell allows the person affected to recover from any penalties including " +
                "those caused by fatigue, hunger, physical damage, and even spells, although not " +
                "from actual physical loss such as from the loss of a limb or other body part. " +
                "Restore automatically restores any number of Fatigue points lost due to fatigue. " +
                "The maximum Presence that can be affected is 100.",
        "+10 to the maximum Presence affected",
        20,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val hypnoticDisplay = Spell(
        "Hypnotic Display",
        Element.Light,
        true,
        70,
        140,
        "This spell creates a spectacle of lights in a specified place that has a fascinating " +
                "and dumbfounding affect. All the characters that see the display cannot help but " +
                "continue watching it. It is visible for a radius of one kilometer, and anyone " +
                "seeing it must make a MR or PsR Check with a Difficulty of 120 to resist its " +
                "effects. Those affected can perform Passive Actions, but cannot move. They can " +
                "make a new Resistance Check every time they are attacked. The condition for being " +
                "affected is looking directly at the Hypnotic Display.",
        "+1 kilometer to radius and +5 to MR or PsR Difficulty",
        20,
        50,
        false,
        listOf(SpellType.Automatic)
    )

    private val catastrophicLight = Spell(
        "Catastrophic Light",
        Element.Light,
        true,
        72,
        120,
        "Creates a deadly discharge of Light with a Base Damage of 150. Catastrophic Light " +
                "has a radius of 25 meters and is an Energy Attack Type.",
        "+10 meters to radius and +5 damage",
        20,
        null,
        false,
        listOf(SpellType.Attack)
    )

    private val luminousMaterial = Spell(
        "Luminous Material Objects",
        Element.Light,
        true,
        76,
        150,
        "Forms a material object from luminous energy. Whether it is something as complex " +
                "as a clock or as simple as a sword, the object created can not have a Presence " +
                "of more than 60, but for all purposes it is treated as having a quality of +10. " +
                "As an exceptional rule, the quality of the object does not affect its Presence.",
        "+10 to the object\'s presence",
        20,
        10,
        false,
        listOf(SpellType.Effect)
    )

    private val lightTravel = Spell(
        "Travel by Light",
        Element.Light,
        true,
        78,
        250,
        "Transports individuals or objects designated by the caster from one light source " +
                "to another that must be less than 100 kilometers away. The quantity of Presence that " +
                "can be transported cannot be greater than 250. If someone wishes to resist, the " +
                "MR Check has a Difficulty of 120.",
        "+5 to MR Difficulty, +20 to transportable Presence, and +100 kilometers",
        30,
        null,
        false,
        listOf(SpellType.Spiritual, SpellType.Effect)
    )

    private val dreamLordship = Spell(
        "Lordship Over Dreams",
        Element.Light,
        true,
        80,
        300,
        "Permits the control of any type of dream. The caster has the ability to control the " +
                "sleeper\'s dream world, modifying it as if he had a Gnosis of 45 (although it is " +
                "not real). If the dream is fed by negative energy, in other words, if it turns " +
                "into a nightmare, his Gnosis becomes only 30. If the dreamer wishes to resist " +
                "the spell, it requires beating a MR Check with a Difficulty of 140. This spell " +
                "also has a second use, depending on whether or not the caster is in the Wake. " +
                "Within the Wake, the caster can control his surroundings and acquires the powers " +
                "of a creature with a Gnosis of 40, as long as he is in an area strongly " +
                "influenced by positive energies. If he is in an area of neutral energies, he " +
                "is treated as having a Gnosis of 30.",
        "+5 to the MR Difficulty",
        20,
        5,
        true,
        listOf(SpellType.Spiritual, SpellType.Effect)
    )

    private val createLightBeing = Spell(
        "Create Being of Light",
        Element.Light,
        true,
        82,
        250,
        "Creates a luminous being with the appearance of life, but completely under the " +
                "control of the caster. The entity is developed as a Being Between Worlds, using " +
                "the powers and limitations of Light Elementals explained in Chapter 26. The " +
                "creature has 600 DP and its maximum level is calculated using the same rules as " +
                "in the spell Create Being from the Path of Creation.",
        "+50 DP",
        30,
        5,
        true,
        listOf(SpellType.Effect)
    )

    private val reflectingPrism = Spell(
        "Reflecting Prism",
        Element.Light,
        false,
        86,
        160,
        "Creates a body of light that works like a prismatic shield, reflecting any spell, " +
                "psychic attack, or Ki technique back at the caster. For the charge to be reflected " +
                "back, it must lose a Clashing Spells Check against an equivalent of 100 Base " +
                "Damage. It is also necessary to make a successful Block using the Prism. If " +
                "defending against an Area Attack, the prism does not reflect the entire spell; " +
                "it will still affect anyone in the area of the spell except the caster of the " +
                "Reflecting Prism. This spell does not reflect Spiritual spells. The caster can " +
                "use his Magic Projection to redirect the attack. It will absorb 800 points of " +
                "damage before breaking.",
        "+5 to damage for clashing spells and +100 Resistance Points",
        20,
        10,
        true,
        listOf(SpellType.Defense)
    )

    private val omniscienceRadius = Spell(
        "Radius of Omniscience",
        Element.Light,
        true,
        88,
        200,
        "Permits the caster to be omniscient concerning any occurrence or thought within a " +
                "radius of 500 meters from his location. This spell only affects individuals " +
                "whose Presence is less than 60 and who do not have higher Gnosis than the caster. " +
                "The caster will automatically know everything that is happening and everything " +
                "thought within the radius. No Resistance is possible.",
        "+500 meters to radius and +5 to the affected Base Presence",
        20,
        5,
        false,
        listOf(SpellType.Effect)
    )

    private val predict = Spell(
        "Predict",
        Element.Light,
        true,
        90,
        200,
        "Permits the caster to foresee future events that will happen around a given person, " +
                "place, or thing. Predict shows the caster the most probable destiny awaiting " +
                "someone or something, providing him with detailed information about future " +
                "events. The period of time covered can never be more than a year from the moment " +
                "of the prediction. The Game Master can truthfully tell the caster what events " +
                "will occur in the future. Of course, these predictions are not infallible; what " +
                "is seen is only the most probable destiny, which may be changed through the " +
                "intervention of higher powers or the actions of persons with elevated Gnosis.",
        "Doubles the period of time covered",
        20,
        null,
        false,
        listOf(SpellType.Spiritual)
    )

    private val lightPrison = Spell(
        "Prison of Light",
        Element.Light,
        true,
        92,
        200,
        "Enclose the victim in an inescapable world of Light, a separate universe which " +
                "has no interaction with our own. While the victim is imprisoned, he will have " +
                "no knowledge of anything that happens outside the prison, nor does anyone " +
                "outside have any knowledge of the prisoner. To try to break out from within, " +
                "the prison will resist damage points up to 100 times the Zeon value of the " +
                "spell, which defends itself according to the rules of Damage Resistance with " +
                "an AT of 10. From the outside, the prison can resist only double the Zeon " +
                "value spent. It can only be damaged by Energy-based Attacks with a Presence " +
                "greater than 180. The prison recovers from any damage with a Regeneration of " +
                "19. The spell can be avoided by passing a MR Check with a Difficulty of 140, " +
                "but if that fails, the prisoner has no chance to check again later; if he wants " +
                "to get out, he better be able to break through the spell.",
        "+10 to the MR Difficulty",
        20,
        5,
        true,
        listOf(SpellType.Spiritual)
    )

    private val oneWithLight = Spell(
        "One with the Light",
        Element.Light,
        true,
        96,
        100,
        "Permits the caster to enter a state of utter meditation in which his body becomes " +
                "one with the Light. He abandons this world and ascends to the Flow of Souls to " +
                "nourish himself from their energies. Apart from, and without knowledge of " +
                "anything happening elsewhere, he multiplies his Zeon Regeneration rate by 10, " +
                "and cures his wounds with a Healing Regeneration of 16. Although there is no " +
                "maintenance cost for the spell, the caster can be in the Flow of Souls for the " +
                "equivalent of one day.",
        "One day additional stay in the Flow of Souls",
        20,
        null,
        false,
        listOf(SpellType.Effect)
    )

    private val ascension = Spell(
        "Ascension",
        Element.Light,
        true,
        98,
        300,
        "This spell exchanges the material essence of a person for divine energy, modifying " +
                "his spirit through supernatural power. The effects of Ascension are different " +
                "depending on whether the caster is casting it on himself or on another. When " +
                "increasing his own power, the spell increases his Gnosis by 10 points. If used " +
                "on another person, it can give the target as much Gnosis as desired, up to a " +
                "level 10 points below that of the caster. For instance, a caster with a Gnosis " +
                "of 45 can raise another individual to at most a Gnosis of 35. This spell can " +
                "affect as many targets as long as their accumulated Presence is no higher than 80.",
        "+10 to the maximum Presence affected",
        30,
        10,
        true,
        listOf(SpellType.Effect)
    )

    private val lightHolocaust = Spell(
        "Holocaust of Light",
        Element.Light,
        true,
        100,
        600,
        "This spell unleashes the power of Light in its purest state, sweeping away " +
                "everything in the spiritual as well as the material world. The freed energy " +
                "sweeps up and devours anything, unifying its existence to the Light. Even " +
                "elemental creatures of Light are assimilated by its power. The Holocaust of " +
                "Light creates a great luminous dome within which everything is dissolved. It " +
                "has a radius of 100 meters, attacks the Energy AT, and causes a Base Damage of 350. " +
                "Anyone receiving damage, no matter how small, must beat a MR Check with a " +
                "Difficulty of 160 or be joined with the Light, and automatically destroyed in " +
                "body and soul. It is not possible to designate specific targets within the " +
                "Holocaust; all except the caster are equally affected.",
        "+10 Base Damage and doubles the radius of the spell",
        50,
        null,
        false,
        listOf(SpellType.Attack, SpellType.Spiritual)
    )

    val fullBook = listOf(
        createLight,
        null,
        induceCalm,
        blindingFlash,
        lightShield,
        perceiveSpell,
        null,
        lightArmor,
        banishShadows,
        detectNegativeEmotions,
        lightBeam,
        null,
        hologram,
        lightBond,
        controlLight,
        detectLife,
        null,
        lightSpy,
        ecstasy,
        banishNegativeEmotions,
        healingLight,
        null,
        seekingSphere,
        detectionZone,
        enterDreams,
        lightForm,
        null,
        blessingSpell,
        createGoodFeelings,
        seeTruth,
        shieldFromNegative,
        null,
        findSpell,
        restoreSpell,
        hypnoticDisplay,
        catastrophicLight,
        null,
        luminousMaterial,
        lightTravel,
        dreamLordship,
        createLightBeing,
        null,
        reflectingPrism,
        omniscienceRadius,
        predict,
        lightPrison,
        null,
        oneWithLight,
        ascension,
        lightHolocaust
    )
}