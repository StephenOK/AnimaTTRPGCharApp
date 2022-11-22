package com.example.animabuilder.character_creation.attributes.magic.spells

import com.example.animabuilder.character_creation.Element
import java.io.Serializable

class Spellbook(): Serializable {
    val createLight = Spell(
        "Create Light",
        Element.Light,
        true,
        2,
        20,
        "Create light in a radius of 5 meters.",
        "+5 meters radius",
        20,
        "1 every 10 (Daily)",
        listOf(SpellType.Effect)
    )

    val induceCalm = Spell(
        "Induce Calm",
        Element.Light,
        true,
        6,
        40,
        "Calms individuals feeling fear or hatred within 15 meters of the caster. Makes any " +
                "Fear, Anger, or Terror States disappear, even if of supernatural origin. It does " +
                "not prevent violent actions deliberately done in cold blood. The MR or PsR Check " +
                "to overcome this spell has a Difficulty of 80.",
        "+10 meters to radius and +5 to MR or PsR Difficulty",
        10,
        null,
        listOf(SpellType.Spiritual)
    )

    val blindingFlash = Spell(
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
        listOf(SpellType.Automatic)
    )

    val lightShield = Spell(
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
        "1 every 10",
        listOf(SpellType.Defense)
    )

    val perceive = Spell(
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
        "1 every 10",
        listOf(SpellType.Effect)
    )

    val lightArmor = Spell(
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
        "1 every 20",
        listOf(SpellType.Effect)
    )

    val banishShadows = Spell(
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
        "+20 meters to radius and 10 to the MR Difficulty.",
        10,
        "1 every 10",
        listOf(SpellType.Spiritual, SpellType.Effect)
    )

    val detectNegativeEmotions = Spell(
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
        "1 every 10",
        listOf(SpellType.Detection)
    )

    val lightBeam = Spell(
        "Light Beam",
        Element.Light,
        true,
        22,
        50,
        "Projects a beam of Light based magical energy. Light Beam is an Energy Attack Type " +
                "with a Base Damage of 60.",
        "+5 to Base Damage",
        10,
        null,
        listOf(SpellType.Attack)
    )

    val hologram = Spell(
        "Hologram",
        Element.Light,
        true,
        26,
        40,
        "Creates a immaterial luminous form with a maximum size of one square meter. The " +
                "caster can give the hologram the appearance he desires making it very difficult " +
                "to tell it from something real. If he creates a creature, it can perform any " +
                "inhuman action the caster wishes, but will mimic the physical abilities of the " +
                "caster. If, for example, the hologram is used to simulate an attack, it uses " +
                "the combat ability of the caster. The hologram can not tough anyone nor be " +
                "touched, but if it receives any damage, based on Energy, it disappears. To " +
                "detect that a hologram is not real, it is necessary to beat a Notice check " +
                "against a Difficulty of Almost Impossible, or Search against Very Difficult.",
        "+1 square meter to maximum size",
        20,
        "1 every 10",
        listOf(SpellType.Effect)
    )

    val lightBond = Spell(
        "Bonds of Light",
        Element.Light,
        true,
        28,
        60,
        "It casts bonds of light that hold the designated target immobile. An attack is made " +
                "using the rules for Trapping, although the caster suffers no penalty to his " +
                "Ability for performing this maneuver. The bonds use a Strength of 8 for any " +
                "Check. If anyone tries to help free the person Trapped, the Bonds of Light are " +
                "treated as an Energy weapon with a Fortitude of 20.",
        "+1 to Strength for all checks",
        10,
        "1 every 10",
        listOf(SpellType.Attack)
    )

    val controlLight = Spell(
        "Control Light",
        Element.Light,
        true,
        30,
        50,
        "Modifies and controls the form, color, or intensity of light in a 20 meter radius. " +
                "If cast at Light-based beings, they must pass a MR Check with a Difficulty of 80 " +
                "or they will fall under the control of the caster. A creature can only repeat " +
                "the check if it is ordered to do something against its nature.",
        "+10 meters to radius and +5 to MR Difficulty",
        20,
        "1 every 10",
        listOf(SpellType.Spiritual, SpellType.Effect)
    )

    val detectLife = Spell(
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
    "1 every 20",
        listOf(SpellType.Detection)
    )

    val lightSpy = Spell(
        "Spy of Light",
        Element.Light,
        true,
        36,
        100,
        "Creates a small light of energy that moves as wished by the caster, with a Flight " +
                "Value of 14, for a maximum distance of one kilometer. Through it, the caster can see " +
                "and hear as though he were present, but doing so overwhelms his body's senses, " +
                "and he can only perceive the world through the Spy of Light. Each combat turn, " +
                "the caster decides if he will see through the Spy of Light or his own senses. " +
                "The Spy of Light has an ability of 100 at Notice and Search. If attacked, it " +
                "can defend itself with the Magic Projection of its caster. For purposes of " +
                "Initiative, it acts when its controller does. It is only possible to attack it " +
                "with supernatural attacks, although it is destroyed if it receives any damage.",
        "+5 to Notice and Search and +1 kilometer to range",
        20,
        "1 every 5 (Daily)",
        listOf(SpellType.Effect)
    )

    val ecstasy = Spell(
        "Ecstasy",
        Element.Light,
        true,
        38,
        60,
        "This spell intoxicates anyone affected with a feeling of utter ecstasy. The " +
                "sensation of pleasure is so powerful that the victim's senses are completely " +
                "clouded, and he receives a -20 All Action Penalty while affected. However, the " +
                "spell's victims are also completely oblivious and immune to any pain or other " +
                "affliction based penalty, except those for actually being physically " +
                "incapacitated. The MR Check to resist this spell has a Difficulty of 80, and " +
                "affects a radius of 10 meters.",
        "+10 meters to radius and +5 to MR Difficulty",
        10,
        "1 every 10",
        listOf(SpellType.Spiritual)
    )

    val banishNegativeEmotions = Spell(
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
        listOf(SpellType.Spiritual)
    )

    val healingLight = Spell(
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
        listOf(SpellType.Effect)
    )

    val seekingSphere = Spell(
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
        "1 every 10",
        listOf(SpellType.Attack)
    )

    val detectionZone = Spell(
        "Zone of Detection",
        Element.Light,
        true,
        48,
        140,
        "This spell allows the caster to detect any being within the area of the spell who " +
                "does not beat a MR Check with a Difficulty of 180. The Zone of Detection only " +
                "tells the caster how many individuals are in the zone, and their exact " +
                "location. It also senses spells of Detection that attempt to enter into the " +
                "area, as long as the spellcaster using them does not beat the MR (regardless " +
                "of his actual location). The affected zone can be no larger than 20 meters in " +
                "radius, and is stationary in the place it was cast.",
        "+10 meters to radius and +10 to MR Difficulty",
        20,
        "1 every 20 (Daily)",
        listOf(SpellType.Detection)
    )

    val enterDreams = Spell(
        "Enter Another's Dreams",
        Element.Light,
        true,
        50,
        120,
        "This allows the caster to physically enter a sleeper's dreams. The caster has no " +
                "control over the dream world of the dreamer, and anything that happens there " +
                "will be real to the caster. The person must have peaceful dreams to be affected " +
                "by this spell, and the moment the dream turns into a nightmare, or he awakens " +
                "or dies, the mage abandons the dream world and returns to reality. Any Spiritual " +
                "spell cast on the dreamer while the caster is present in his dreams will also " +
                "affect the caster. The MR or PsR Check has a Difficulty of 140. Once he is in " +
                "the target person's dreams, the caster can jump to the unconscious of yet another " +
                "dreamer who is physically no more than 10 meters from the original sleeper. " +
                "Naturally, this new dreamer will have the right to his own MR Check. If the " +
                "dreamer's consciousness happens to be in the world of The Wake, the caster is " +
                "trapped there even when the spell expires.",
        "+10 meters additional to jumping range and +5 to MR or PsR Difficulty",
        20,
        "1 every 20 (Daily)",
        listOf(SpellType.Spiritual)
    )

    val lightForm = Spell(
        "Light Form",
        Element.Light,
        true,
        52,
        100,
        "The body designated by the caster is transformed into pure luminous energy and " +
                "becomes intangible to matter and non-energy attacks. While in this state, the " +
                "transformed person gains a +50 bonus to his abilities of Notice and Search, and " +
                "a +30 to his Resistance against affects based on Light. In this state, the " +
                "damage caused by Darkness based attacks is doubled. The maximum presence that " +
                "can be affected is 100.",
        "+10 to the maximum Presence that can be affected",
        10,
        "1 every 10",
        listOf(SpellType.Effect)
    )

    val blessing = Spell(
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
        "1 every 20",
        listOf(SpellType.Effect)
    )

    val createGoodFeelings = Spell(
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
        "1 every 10 (Daily)",
        listOf(SpellType.Spiritual)
    )

    val seeTruth = Spell(
        "See Truth",
        Element.Light,
        true,
        60,
        100,
        "Permits the affected person to perceive supernatural forces that are invisible to " +
                "the human eye, including magic, psychic matrices, and invisible or spiritual " +
                "beings. Although this spell does not directly work against illusion spells " +
                "because they affect the mind, not the vision, anyone using See Truth against " +
                "visual illusions can apply a +50 bonus to the MR Check, since it would help " +
                "detect the falseness. The maximum Presence that can be affected is 80.",
        "+10 to the maximum Presence affected",
        10,
        "1 every 10 (Daily)",
        listOf(SpellType.Effect)
    )

    val shieldFromNegative = Spell(
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
        "1 every 10 (Daily)",
        listOf(SpellType.Automatic)
    )

    val find = Spell(
        "Find",
        Element.Light,
        true,
        66,
        160,
        "By means of Find, the finder can locate any person, place, or thing, and know its " +
                "exact location in that moment regardless of the distance separating them. " +
                "Anything can be found, whether an individual object, or a type of object, or " +
                "simply something that fulfills a specified condition. For example, the caster " +
                "can try to locate a city, the thief who stole his crosier (even if he doesn't " +
                "know who did it), or the closest eligible maiden of royal blood. Objects, " +
                "places, or people affected must make a dice roll against a MR Check with a " +
                "Difficulty of 140 to avoid being located. Large places apply a -40 penalty to " +
                "this check.",
        "+10 to the MR Difficulty",
        20,
        null,
        listOf(SpellType.Detection)
    )

    val restore = Spell(
        "Restore",
        Element.Light,
        true,
        70,
        140,
        "This spell allows the person affected to recover from any penalties including " +
                "those caused by fatigue, hunger, physical damage, and even spells, although not " +
                "from actual physical loss such as from the loss of a limb or other body part. " +
                "Restore automatically restores any number of Fatigue points lost due to fatigue. " +
                "The maximum Presence that can be affected iss 100.",
        "+10 to the maximum Presence affected",
        20,
        null,
        listOf(SpellType.Effect)
    )

    val hypnoticDisplay = Spell(
        "Hypnotic Display",
        Element.Light,
        true,
        70,
        140,
        "The spell creates a spectacle of lights in a specified place that has a fascinating " +
                "and dumbfounding affect. All the characters that see the display cannot help but " +
                "continue watching it. It is visible for a radius of half a kilometer, and anyone " +
                "seeing it must make a MR or PsR Check with a Difficulty of 120 to resist its " +
                "effects. Those affected can perform Passive Actions, but cannot move. They can " +
                "make a new Resistance Check every time they are attacked. The condition for being " +
                "affected is looking directly at the Hypnotic Display.",
        "+1 kilometer to radius and +5 to MR or PsR Difficulty",
        20,
        "1 every 50",
        listOf(SpellType.Automatic)
    )

    val catastrophicLight = Spell(
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
        listOf(SpellType.Attack)
    )

    val luminousMaterial = Spell(
        "Luminous Material Objects",
        Element.Light,
        true,
        76,
        150,
        "Forms a material object from luminous energy. Whether it is something as complex " +
                "as a clock, or as simple as a sword, the object created can not have a Presence " +
                "of more than 60, but for all purposes it is treated as having a quality of +10. " +
                "As an exceptional rule, the quality of the object does not affect its Presence.",
        "+10 to the object's presence",
        20,
        "1 every 10",
        listOf(SpellType.Effect)
    )

    val lightTravel = Spell(
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
        listOf(SpellType.Spiritual, SpellType.Effect)
    )

    val dreamLordship = Spell(
        "Lordship Over Dreams",
        Element.Light,
        true,
        80,
        300,
        "Permits the control of any type of dream. The caster has the ability to control the " +
                "sleeper's dream world, modifying it as if he had a Gnosis of 45 (although it is " +
                "not real). If the dream is fed by negative energy, in other words, if it turns " +
                "into a nightmare, his Gnosis becomes only 30. If the dreamer wishes to resist " +
                "the spell, it requires beating a MR Check with a Difficulty of 140. This spell " +
                "also has a second use, depending on whether or now the caster is in the Wake. " +
                "Within the Wake, the caster can control his surroundings and acquires the powers " +
                "of a creature with a Gnosis of 40, as long as he is in an area strongly " +
                "influenced by positive energies. If he is in an area of neutral energies, he " +
                "is treated as having a Gnosis of 30.",
        "+5 to the MR Difficulty",
        20,
        "1 every 5 (Daily)",
        listOf(SpellType.Spiritual, SpellType.Effect)
    )

    val createLightBeing = Spell(
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
        "1 every 5 (Daily)",
        listOf(SpellType.Effect)
    )

    val reflectingPrism = Spell(
        "Reflecting Prism",
        Element.Light,
        false,
        86,
        160,
        "Creates a body of light that works like a prismatic shield, reflecting any spell, psychic attack, or Ki technique back at the caster. For the charge to be reflected back, it must lose a Clashing Spells check against an equivalent of 100 Base Damage. It is also necessary to make a successful Block using the Prism. If defending against an Area Attack, the prism does not reflect the entire spell; it will still affect anyone in the area of the spell except the caster of the Reflecting Prism. This spell does not reflect Spiritual spells. The caster can use his Magic Projection to redirect the attack. It will absorb 800 points of damage before breaking.",
        "+5 to damage for clashing spells and +100 Resistance Points",
        20,
        "1 every 10 (Daily)",
        listOf(SpellType.Defense)
    )

    val omniscienceRadius = Spell(
        "Radius of Omniscience",
        Element.Light,
        true,
        88,
        200,
        "Permits the caster to be omniscient concerning any occurrence or thought within a " +
                "radius of 450 meters from his location. Affects only individuals whose Presence " +
                "is less than 60 and who do not have higher Gnosis than the caster. The caster " +
                "will automatically know everything that is happening and everything thought " +
                "within the radius. No Resistance is possible.",
        "+450 meters to radius and +5 to the affected Base Presence",
        20,
        "1 every 5",
        listOf(SpellType.Effect)
    )

    val predict = Spell(
        "Predict",
        Element.Light,
        true,
        90,
        200,
        "Permits the caster to forsee future events that will happen around a given person, " +
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
        listOf(SpellType.Spiritual)
    )

    val lightPrison = Spell(
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
        "1 every 5 (Daily)",
        listOf(SpellType.Spiritual)
    )

    val oneWithLight = Spell(
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
        listOf(SpellType.Effect)
    )

    val ascension = Spell(
        "Ascension",
        Element.Light,
        true,
        98,
        300,
        "This spell exchanges the material essence of a person for divine energy, modifying " +
                "his spirit through supernatural power. The effects of Ascension are different " +
                "depending on whether the caster is casting it on himself, or on another. When " +
                "increasing his own power, the spell increases his Gnosis by 10 points. If used " +
                "on another person, it can give the target as much Gnosis as desired, up to a " +
                "level 10 points below that of the caster. For instance, a caster with a Gnosis " +
                "of 45 can raise another individual to at most a Gnosis of 35. This spell can " +
                "affect as many targets as long as their accumulated Presence is no higher than 80.",
        "+10 to the maximum Presence affected",
        30,
        "1 every 10 (Daily)",
        listOf(SpellType.Effect)
    )

    val lightHolocaust = Spell(
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
        listOf(SpellType.Attack, SpellType.Spiritual)
    )

    val createDark = Spell(
        "Create Darkness",
        Element.Dark,
        true,
        2,
        20,
        "Completely darkens the area within a 5m radius. Everything within the area is " +
                "perceived as though on a dark and moonless night.",
        "+5m radius",
        20,
        "1 every 10 (Daily)",
        listOf(SpellType.Effect)
    )

    val induceFear = Spell(
        "Induce Fear",
        Element.Dark,
        true,
        6,
        40,
        "Temporarily causes a Fear State in all people within 15m of the caster. The " +
                "spellcaster decides what the victims are afraid of. The MR or PsR Check to " +
                "overcome this spell has a Difficulty of 80.",
        "+5 to MR or PsR Difficulty and +10 meters radius",
        10,
        null,
        listOf(SpellType.Spiritual)
    )

    val seeInDarkness = Spell(
        "See in Darkness",
        Element.Dark,
        true,
        8,
        40,
        "Permits the caster, or anyone he selects, to see perfectly in the dark. This spell " +
                "can affect as many targets as desired as long as their accumulated Presence is no " +
                "higher than 80.",
        "+5 to the maximum Presence that can be affected",
        10,
        "1 every 10 (Daily)",
        listOf(SpellType.Effect)
    )

    val darkShield = Spell(
        "Shield of Darkness",
        Element.Dark,
        false,
        10,
        50,
        "Forms a barrier of Energy that protects from any source of attack. The shield can " +
                "absorb up to 300 points before breaking, but is only damaged by supernatural " +
                "attacks. Impacts based on Light cause it double damage",
        "+100 Resistance Points",
        20,
        "1 every 10",
        listOf(SpellType.Defense)
    )

    val shadow = Spell(
        "Shadow",
        Element.Dark,
        false,
        12,
        50,
        "This spell increases the caster's ability to conceal, boosting his Secondary " +
                "Abilities of Stealth and Hide by +50. It also increases his Magic Appraisal by " +
                "+50, but only for the purpose of hiding the magical potency of something or " +
                "someone, not to detect it.",
        "+10 Stealth, Hide, Magic Appraisal",
        20,
        "1 every 10",
        listOf(SpellType.Effect)
    )

    val darkArmor = Spell(
        "Armor of Darkness",
        Element.Dark,
        true,
        16,
        60,
        "Forms a mystical armor with AT 2 against Energy-based Attacks, and an AT of 1 " +
                "against all others. Although it counts as armor, it does not count as an " +
                "additional layer of armor for purposes of penalties to Initiative.",
        "+1 to AT",
        10,
        "1 every 20",
        listOf(SpellType.Effect)
    )

    val banishLight = Spell(
        "Banish Light",
        Element.Dark,
        true,
        18,
        60,
        "Destroys ambient light within a radius of 10 meters. Any light-based creatures " +
                "within the radius must pass a MR Check with a Difficulty of 120, or lose double " +
                "their Failure level in Life Points (Damage Resistance creatures increase this " +
                "amount by its Damage Resistance multiple). As long as the spell is maintained " +
                "over the creatures, they must make a new MR Check each combat turn.",
        "+10 to the MR Difficulty and +20 meter radius",
        10,
        "1 every 10",
        listOf(SpellType.Spiritual, SpellType.Effect)
    )

    val hideMagic = Spell(
        "Hide Magic",
        Element.Dark,
        false,
        20,
        50,
        "Hides a spell, or the mystical properties of an object, from any type of magical " +
                "detection. In game terms, it produces a -80 penalty to the Magic Appraisal spell " +
                "of anyone attempting to to detect or measure a spell or object that has been " +
                "hidden (it also hides the Hide Magic spell itself).",
        "-10 to Magic Appraisal",
        20,
        "1 every 10 (Daily)",
        listOf(SpellType.Effect)
    )

    val darkBeam = Spell(
        "Dark Beam",
        Element.Dark,
        true,
        22,
        50,
        "Projects a beam of Dark-based magical energy. Dark Beam is an Energy Attack Type " +
                "with a Base Damage of 60.",
        "+5 to Base Damage",
        10,
        null,
        listOf(SpellType.Attack)
    )

    val darkzone = Spell(
        "Darkzone",
        Element.Dark,
        true,
        26,
        60,
        "Creates a mystical environment that clouds the senses of anyone within it. The " +
                "Darkzone increases the difficulty of any Perceptive check within it by 2 levels. " +
                "This ability also affects Ki Detection. The spell has a radius of 20 meters, and " +
                "it is not possible to designate targets within it. No Resistance is possible.",
        "+20 meter radius",
        20,
        "1 every 20",
        listOf(SpellType.Effect)
    )

    val darkBond = Spell(
        "Bonds of Darkness",
        Element.Dark,
        true,
        28,
        60,
        "This spell casts bonds of darkness that hold the designated target immobile. An " +
                "attack is made using the rules for Trapping, although the caster suffers no " +
                "penalty to his Ability for performing this maneuver. The bonds use a Strength of " +
                "8 for any Check. If anyone tries to help free the person Trapped, the Bonds of " +
                "Darkness are treated as an Energy weapon with a Fortitude of 20.",
        "Strength +1",
        10,
        "1 every 10",
        listOf(SpellType.Attack)
    )

    val controlDark = Spell(
        "Control Darkness",
        Element.Dark,
        true,
        30,
        50,
        "This spell modifies and controls the intensity of darkness within a radius of 20 " +
                "meters. If cast at Darkness-based beings, they must pass a MR Check with a " +
                "Difficulty of 80 or they will fall under the control of the caster. A creature " +
                "can only repeat the check if it is ordered to do something against its nature.",
        "+10 meter radius and +5 to MR Difficulty",
        20,
        "1 every 10",
        listOf(SpellType.Spiritual, SpellType.Effect)
    )

    val concealment = Spell(
        "Concealment",
        Element.Dark,
        false,
        32,
        60,
        "Conceals the Presence of the caster or a target designated by him from any type of " +
                "detection. In game terms, it increases the resistance to supernatural (mystical " +
                "or psychic) detection by +40. This ability also increases the Ki Concealment of " +
                "the person by 40 points, permitting him to hide his energy even if he has not " +
                "developed that ability.",
        "+10 to Resistance against detections and +10 to Ki Concealment",
        20,
        "1 every 10 (Daily)",
        listOf(SpellType.Effect)
    )

    val obfuscate = Spell(
        "Obfuscate",
        Element.Dark,
        true,
        36,
        100,
        "Modifies the body of the individual, blending him in to the background and " +
                "permitting him to hide himself naturally. While the spell is active, the person " +
                "can exchange his own level of ability at Stealth and Hide for a base of 100. " +
                "It also offers the same level of ability at Ki Concealment, even if he has not " +
                "developed that ability. Remember that these numbers are not added to the person's " +
                "Secondary Ability, but are substituted for them if they are lower.",
        "+5 Stealth, Hide, and Ki Concealment",
        20,
        "1 every 5 (Daily)",
        listOf(SpellType.Effect)
    )

    val enrage = Spell(
        "Enrage",
        Element.Dark,
        true,
        38,
        60,
        "Provokes a Rage State in those affected, making them lose control and attack the " +
                "person closest to them. The spell's victim applies a bonus of +10 to their " +
                "offensive abilities and a -30 to all other checks. Enrage has a radius of 5 " +
                "meters and can be resisted by beating a MR Check with a Difficulty of 80.",
        "+5 meter radius and +5 to the MR Difficulty",
        10,
        "1 every 10",
        listOf(SpellType.Spiritual)
    )

    val banishPositiveEmotions = Spell(
        "Banish Positive Emotions",
        Element.Dark,
        true,
        40,
        80,
        "Temporarily banishes any positive sentiments such as inner peace, calm, or joy " +
                "within 100 meters of the caster. Resisting this spell requires beating a MR or " +
                "PsR Check with a Difficulty of 100.",
        "+45 meter radius and +5 to MR or PsR Difficulty",
        20,
        null,
        listOf(SpellType.Spiritual)
    )

    val night = Spell(
        "Night",
        Element.Dark,
        true,
        42,
        80,
        "Forms a dome of darkness with a maximum radius of 25 meters. Everyone in the " +
                "interior area, except the caster, is subject to the rules for Vision Totally " +
                "Obscured. To see through the darkness, whether from inside or outside the dome, " +
                "requires a difficulty check of Inhuman in Notice, or Almost Impossible in Search.",
        "+25 meter radius",
        20,
        "1 every 10",
        listOf(SpellType.Effect)
    )

    val darkSphere = Spell(
        "Dark Sphere",
        Element.Dark,
        true,
        46,
        120,
        "Unleashes a sphere of dark energy with Base Damage of 100. The caster can control " +
                "it using his Magic Projection until it hits the target. If the target " +
                "successfully dodges, the Dark Sphere can continue attacking the following turn, " +
                "since it has not been destroyed. When Dark Sphere causes damage, or is blocked, " +
                "the sphere explodes and vanishes. If the caster abandons control of it, it will " +
                "act independently, following its last designated target with a Magic Projection " +
                "of 150.",
        "+5 to Base Damage and +5 to the Magic Projection of the Dark Sphere",
        20,
        "1 every 10",
        listOf(SpellType.Attack)
    )

    val concealZone = Spell(
        "Zone of Concealment",
        Element.Dark,
        true,
        48,
        140,
        "By casting this spell over a particular zone, the spellcaster creates a mystical " +
                "area that impedes the detection of anything within it. Anything within the area " +
                "is concealed, with a MR Check against a value of 180 to avoid detections. " +
                "Additionally, any Ki Detection or Magic Appraisal used within the zone suffers " +
                "a -140 penalty to its Final Result. Similarly, any type of psychic detection is " +
                "subject to a -40 penalty to its Potential. The affected zone can be no larger " +
                "than 20 meters in radius, and is stationary in the place it was cast.",
        "+10 meters to radius, +10 to MR, and -10 to Ki Detection, -10 Magic Appraisal, " +
                "and -10 to the potential of any Psychic detection",
        20,
        "1 every 20 (Daily)",
        listOf(SpellType.Detection)
    )

    val enterNightmare = Spell(
        "Enter Another's Nightmares",
        Element.Dark,
        true,
        50,
        120,
        "This allows the caster to physically enter in a sleeper's nightmares. The " +
                "spellcaster has no control over the dream world of the dreamer, and anything " +
                "that happen there will be real to him. The person must have terrible nightmares " +
                "to be affected by this spell, and the moment the dream turns into a peaceful " +
                "dream, or he awakens or dies, the mage abandons the dream world and returns to " +
                "reality. Any Spiritual spell cast on the dreamer will also affect the caster. " +
                "The MR or PsR Check for the sleeper has a has a Difficulty of 140. Once he is in " +
                "the other person's nightmares, the caster can jump to the unconscious of yet " +
                "another dreamer who is physically no more than 10 meters from the original " +
                "sleeper. Naturally, this new sleeper will have the right to his own MR Check. " +
                "If the dreamer's consciousness happens to be in the world of the Wake, the " +
                "caster is trapped there even when the spell expires.",
        "+5 to MR or PsR Difficulty, +10 meters to jumping range",
        20,
        "1 every 50 (Daily)",
        listOf(SpellType.Spiritual)
    )

    val darkForm = Spell(
        "Dark Form",
        Element.Dark,
        true,
        52,
        100,
        "The body designated by the caster is transformed to pure dark energy and becomes " +
                "intangible to matter and non-energy attacks. While in this state, the transformed " +
                "person gains a +50 bonus to his abilities of Stealth and Hide, and a +30 to his " +
                "Resistance against effects based on Darkness. In this state, the damage caused " +
                "by any attacks based on Light are doubled. The maximum Presence that can be affected is 100.",
        "+10 to the maximum Presence that can be affected",
        10,
        "1 every 10",
        listOf(SpellType.Effect)
    )

    val perdition = Spell(
        "Perdition",
        Element.Dark,
        true,
        56,
        100,
        "Perdition causes a disturbed feeling among those affected, reducing their " +
                "abilities. Those within its area of influence must pass a MR Check of with a " +
                "Difficulty of 120, or else receive a -40 All Action Penalty. The area covered is " +
                "that within 5 meters of the caster, automatically affecting anyone within the " +
                "radius. The penalties for Perdition are not cumulative, and so no one ever " +
                "suffers a double penalty if in the area of influence of more than one such spell.",
        "+5 meters to radius and +5 to the MR Difficulty",
        20,
        "1 every 20",
        listOf(SpellType.Automatic)
    )

    val createNegativeFeelings = Spell(
        "Create Negative Feelings",
        Element.Dark,
        true,
        58,
        100,
        "This creates negative sentiments such as hatred, fear, or anger in the people " +
                "designated by the caster. The radius of the spell is 20 meters, and the MR or " +
                "PsR Check to resist the effects has a Difficulty of 120. Those affected can " +
                "repeat the Resistance Check once per day.",
        "+10 meters to radius and +5 to MR or PsR Difficulty",
        20,
        "1 every 10 (Daily)",
        listOf(SpellType.Spiritual)
    )

    val eliminateTraces = Spell(
        "Eliminate Traces",
        Element.Dark,
        true,
        60,
        100,
        "The caster can erase the evidence that he has been in a certain place. In fact, " +
                "any sign whatsoever of his passing, whether detectable naturally, or by " +
                "supernatural means, disappears completely. This spell even provides immunity " +
                "from spells that permit someone to see the past of a place or an object, hiding " +
                "the presence of the caster of Eliminate Traces from the recording of past " +
                "events. The only way to overcome the effects of this spell is by achieving a Zen " +
                "level at Track. The spell erases all such traces within a 45 meter radius.",
        "+45 meters to radius",
        20,
        "1 every 10 (Daily)",
        listOf(SpellType.Effect)
    )

    val shieldFromPositive = Spell(
        "Shield From Positive",
        Element.Dark,
        true,
        62,
        140,
        "This spell enchants a certain area, making it impenetrable for beings naturally " +
                "based in positive emotions or Light. Any such creature entering the zone must " +
                "pass a MR Check with a Difficulty of 120 or suffer the loss of a number of Life " +
                "Points equal to the margin of failure. Additionally, if the creature fails the " +
                "check, he receives an immediate -40 All Action Penalty. The affected zone can be " +
                "no larger than 20 meters in radius, and is stationary in the place it was cast.",
        "+20 meters to radius and +5 to the MR Difficulty",
        20,
        "1 every 10 (Daily)",
        listOf(SpellType.Automatic)
    )

    val dark = Spell(
        "Dark",
        Element.Dark,
        false,
        66,
        120,
        "Creates a shadow shield with the ability to swallow any attack spell received. For " +
                "the defense against an attacking spell to work, it is necessary to achieve a " +
                "Block against the attack. If the defender does achieve it, the attacking spell " +
                "must then lose a Clashing Spells Check against a damage of 80. The void absorbs " +
                "half the Zeon of the devoured spell, giving those points to the caster. It is " +
                "also possible for it to devour Area Attacks. The shield will resist 600 points " +
                "of damage before breaking.",
        "+5 to damage for Clashing Spells and +100 Resistance Points",
        20,
        "1 every 20",
        listOf(SpellType.Defense)
    )

    val devastate = Spell(
        "Devastate",
        Element.Dark,
        true,
        68,
        100,
        "Causes penalties through and accumulation of pain, suffering, misery, and other " +
                "negative mystical effects. The affected person must pass a MR Check with a " +
                "Difficulty of 120 or suffer an All Action Penalty and a penalty to his " +
                "Resistances equivalent to the margin of failure of the Check. If he does not " +
                "pass the Check, the target of Devastate does not have the right to a new check " +
                "for as long as the spell is maintained.",
        "+5 to the MR Difficulty",
        20,
        "1 every 10",
        listOf(SpellType.Spiritual)
    )

    val markOfFear = Spell(
        "Mark of Fear",
        Element.Dark,
        true,
        70,
        140,
        "Forms a display of shadows in a designated spot that terrorizes all who see it. " +
                "Anyone seeing the Mark of Fear suffers a Terror State unless they beat MR or PsR " +
                "Check with a Difficulty of 120. The spell is visible for a radius of one " +
                "kilometer. The condition for being affected is merely looking directly at the Mark of Fear.",
        "+1 km to radius and -5 to MR or PsR Difficulty",
        20,
        "1 every 50",
        listOf(SpellType.Automatic)
    )

    val catastrophicDarkness = Spell(
        "Catastrophic Darkness",
        Element.Dark,
        true,
        72,
        120,
        "Creates a deadly discharge of Darkness with a Bases Damage of 150. The Catastrophic " +
                "Darkness has a radius of 25 meters and is an Energy Attack Type spell.",
        "+5 Base Damage and 10 meters radius",
        20,
        null,
        listOf(SpellType.Attack)
    )

    val darkMaterialObjects = Spell(
        "Dark Material Objects",
        Element.Dark,
        true,
        76,
        150,
        "Forms a material object from dark energy. Whether it is something as complex as a " +
                "clock, or as simple as a sword, the object created cannot have a Presence of more " +
                "than 60, but for all purposes it is treated as having a quality of +10. As an " +
                "exceptional rule, the quality of the object does not affect its Presence.",
        "+10 to the object's Presence",
        20,
        "1 every 10",
        listOf(SpellType.Effect)
    )

    val shadowTravel = Spell(
        "Travel by Shadow",
        Element.Dark,
        true,
        78,
        250,
        "Transports individuals or objects designated by the caster from one shadow to " +
                "another that must be less than 100 km away. The quantity of Presence that can " +
                "be transported cannot be greater than 250. If someone wishes to resist, the MR " +
                "Check has a Difficulty of 120.",
        "+5 to MR Difficulty, +20 to transportable Presence, and +100 km",
        30,
        null,
        listOf(SpellType.Spiritual, SpellType.Effect)
    )

    val nightmareLord = Spell(
        "Lord of Nightmares",
        Element.Dark,
        true,
        80,
        300,
        "Permits the control of any type of nightmare. The caster has the ability to control " +
                "a sleeper's dream world, modifying it as if he had a Gnosis of 45 (although is " +
                "not real). If the dream is fed by positive energy, in other words, if it stops " +
                "being a nightmare, his Gnosis becomes only 30. If the dreamer wishes to resist " +
                "the spell, it requires beating a MR Check with a Difficulty of 140. This spell " +
                "also has a second use, depending on whether or not the caster is in the Wake. " +
                "Within the Wake, the caster can control his surroundings and acquires the " +
                "powers of a creature with a Gnosis of 40, as long as he is in an area strongly " +
                "influenced by negative energies. If he is in an area of neutral energies, he " +
                "is treated as having a Gnosis of 30.",
        "+5 to the MR Difficulty",
        20,
        "1 every 5 (Daily)",
        listOf(SpellType.Spiritual, SpellType.Effect)
    )

    val createDarkBeing = Spell(
        "Create Being of Darkness",
        Element.Dark,
        true,
        82,
        250,
        "Creates a dark being with the appearance of life, but completely under the control " +
                "of the caster. The entity is developed as a Being Between Worlds using the " +
                "powers and limitations of Dark Elementals explained in Chapter 26. The being " +
                "has 600 DP, and to calculate its maximum level the same rules are used as for " +
                "Create Being from the Path of Creation.",
        "+50 DP",
        30,
        "1 every 5 (Daily)",
        listOf(SpellType.Effect)
    )

    val concealmentFromMagic = Spell(
        "Concealment from Magic",
        Element.Dark,
        false,
        86,
        200,
        "Hides the presence of the individual from Automatic spells, causing him to be " +
                "directly unaffected by them. As an exception to the general rule, an opposing " +
                "caster maintaining any Automatic spell must succeed in fixing his Magic " +
                "Projection on the caster who is defending himself using Concealment from Magic, " +
                "as though the Automatic spell were a Spiritual one (even if the caster of " +
                "Concealment from Magic meets the condition for being subject to the Automatic " +
                "spell). The maximum Presence that can be affected is 80.",
        "+5 to the maximum Presence that can be affected",
        20,
        "1 every 10 (Daily)",
        listOf(SpellType.Effect)
    )

    val darknessKingdom = Spell(
        "Kingdom of Darkness",
        Element.Dark,
        true,
        88,
        200,
        "Creates a 45 meter radius zone around the caster in which total darkness reigns. " +
                "The body of the spellcaster blends into the shadows, increasing his resistance " +
                "to all kinds of detection by +40. To locate him using the senses requires " +
                "passing a check of Impossible difficulty with Search, or Inhumane using Notice. " +
                "Even while using Ki Detection, it is still necessary to beat an Inhuman level " +
                "check. In addition, as long as he is one with the Darkness, the caster can " +
                "transport himself each Combat Turn anywhere he wishes within the Kingdom of " +
                "Darkness. The MA of the caster is also increased by 20 points when using spells " +
                "of darkness, and any living being (except Dark Elementals) within the covered " +
                "area automatically loses 10 points of Zeon per combat turn (20 points per turn " +
                "if a Light Elemental). The spell is stationary in the place it was cast.",
        "+45 meters to radius",
        30,
        "1 every 20",
        listOf(SpellType.Automatic)
    )

    val undetectable = Spell(
        "Undetectable",
        Element.Dark,
        true,
        90,
        380,
        "The target of this spell becomes completely immune to supernatural detection, " +
                "whether mystical, psychic, or based on Ki. The only way to locate the person is " +
                "through the physical senses such as sight, hearing, or smell. The maximum " +
                "Presence that can be affected is 60.",
        "+5 to the maximum Presence that can be affected",
        30,
        "1 every 5 (Daily)",
        listOf(SpellType.Effect)
    )

    val prisonOfDarkness = Spell(
        "Prison of Darkness",
        Element.Dark,
        true,
        92,
        200,
        "Encloses the victim in an inescapable world of Darkness, a separate universe which " +
                "has no interaction with our own. While the victim is imprisoned, he will have " +
                "no knowledge of anything that happens outside the prison, nor does anyone " +
                "outside have any knowledge of the prisoner's status or activities. If the " +
                "prisoner tries to break out from within, the prison will resist damage points up " +
                "to 100 times the Zeon value of the spell, which defends itself according to the " +
                "rules of Damage Resistance with an AT of 10. From the outside, the prison can " +
                "resist only double the Zeon value spent. It can only be damaged by Energy-based " +
                "Attacks with a Presence greater than 180. The prison recovers from any damage " +
                "with a Regeneration of 19. The spell can be avoided by passing a MR Check with a " +
                "Difficulty of 140, but if that fails, the prisoner has no chance to check again " +
                "later, if he wants to get out, he better be able to break through the spell.",
        "+10 to the MR Difficulty",
        20,
        "1 every 5 (Daily)",
        listOf(SpellType.Spiritual)
    )

    val oneWithDarkness = Spell(
        "One With the Darkness",
        Element.Dark,
        true,
        96,
        100,
        "Permits the caster to enter a state of utter meditation in which his body becomes " +
                "one with the Dark. He abandons this world and ascends to the Flow of Souls to " +
                "nourish himself from their energies. Apart from, and without knowledge of " +
                "anything happening elsewhere, he multiplies his Zeon Regeneration rate by 10, " +
                "and cures his wounds with a with a Healing Regeneration of 16. Although there " +
                "is no maintenance cost for the spell, the caster can be in the Flow of Souls " +
                "for the equivalent of one day.",
        "One day additional stay in the Flow of Souls",
        20,
        null,
        listOf(SpellType.Effect)
    )

    val darkAscension = Spell(
        "Dark Ascension",
        Element.Dark,
        true,
        98,
        300,
        "This spell exchanges the material essence of a person for divine energy, modifying " +
                "his spirit through pure supernatural power. The affects of Ascension are " +
                "different depending on whether the spell is cast on himself, or on another. When " +
                "increasing his own power, the spell increases his Gnosis by 10 points. If used " +
                "on another person, it can give him as much Gnosis as desired, up to a level 10 " +
                "points below that of the caster. For instance, a caster with a Gnosis of 45 can " +
                "raise another individual to at most a Gnosis of 35. This spell can affect as " +
                "many targets as desired as long as their accumulated Presence is no higher than 80.",
        "+10 to the maximum Presence that can be affected",
        30,
        "1 every 10 (Daily)",
        listOf(SpellType.Effect)
    )

    val darkHolocaust = Spell(
        "Holocaust of Darkness",
        Element.Dark,
        true,
        100,
        600,
        "This spell unleashes the power of Darkness in its purest state, sweeping away " +
                "all before it in the spiritual as well as the material world. The freed energy " +
                "sweeps up and devours everything, unifying its existence to the Darkness. The " +
                "Holocaust of Darkness creates a great dome of darkness within which everything " +
                "is dissolved. It has a radius of 100 meters, attacks the Energy AT, and causes " +
                "a Base Damage of 350 points. Anyone receiving damage, no matter how small, must " +
                "beat a MR Check with a Difficulty of 160 or be joined with the Darkness, and " +
                "automatically destroyed in body and soul. It is not possible to designate " +
                "specific targets within the Holocaust; all except the caster are equally affected.",
        "+10 to Base Damage and doubles the radius of the spell",
        50,
        null,
        listOf(SpellType.Attack, SpellType.Spiritual)
    )

    val minorCreation = Spell(
        "Minor Creation",
        Element.Creation,
        true,
        2,
        30,
        "Creates a simple object with a Presence of no more than 25.",
        "Creates one additional object",
        10,
        "1 every 10",
        listOf(SpellType.Effect)
    )

    val reconstruct = Spell(
        "Reconstruct",
        Element.Creation,
        true,
        6,
        40,
        "Restores a non-organic object to its original form from parts or pieces that " +
                "remain. Note that it is necessary to either have all the pieces, or enough of " +
                "the prime material to rebuild the complete object, otherwise it will be only " +
                "partly reconstructed. This spell affects objects with a Presence of no more than 20.",
        "+5 to the maximum Presence of the object",
        20,
        null,
        listOf(SpellType.Effect)
    )

    val createEnergy = Spell(
        "Create Energy",
        Element.Creation,
        true,
        8,
        40,
        "Creates one Intensity of one of the three types of existing energies (Cold, Fire, or Electricity).",
        "+1 additional Intensity",
        20,
        "1 every 10",
        listOf(SpellType.Effect)
    )

    val regeneration = Spell(
        "Regeneration",
        Element.Creation,
        true,
        10,
        60,
        "Increases the ability of a body to heal all types of wounds. This spell provides " +
                "a base Regeneration Level of 4 to anyone designated by the caster, substituting " +
                "it for the person's natural Regeneration Level. If it increases above 14, two " +
                "more Added Effects are necessary instead of one to continue increasing it more. " +
                "A Regeneration level of more than 18 cannot be achieved through this spell " +
                "unless the caster has sufficient Gnosis.",
        "+1 to the base Regeneration Level",
        10,
        "1 every 10 (Daily)",
        listOf(SpellType.Effect)
    )

    val inorganicModification = Spell(
        "Inorganic Modification",
        Element.Creation,
        true,
        12,
        60,
        "Through control of magic, the caster can alter the form and nature of an inorganic " +
                "object, transforming it into a completely different thing but of similar spiritual " +
                "power. This permits the caster to transform something with a Presence of 20 or " +
                "less into something else of equal or lesser Presence.",
        "+5 to the maximum Presence that can be affected",
        10,
        "1 every 20",
        listOf(SpellType.Effect)
    )

    val increaseResistances = Spell(
        "Increase Resistances",
        Element.Creation,
        true,
        16,
        80,
        "Supernaturally increases all resistances by +10 (Disease, Magic, Physical, Psychic," +
                " and Venom) of an individual. Multiple castings of this spell on a single target" +
                " are not cumulative.",
        "+5 to all Resistances",
        10,
        "1 every 5 (Daily)",
        listOf(SpellType.Effect)
    )

    val royalShield = Spell(
        "Royal Shield",
        Element.Creation,
        false,
        18,
        40,
        "Forms a barrier of Energy that protects the caster from any source of attack. " +
                "The shield will absorb 500 points of damage before breaking.",
        "+150 Resistance Points",
        30,
        "1 every 20",
        listOf(SpellType.Defense)
    )

    val heal = Spell(
        "Heal",
        Element.Creation,
        true,
        20,
        80,
        "Causes whomever the spell is directed at to recover 50 Life Points. The spell does " +
                "not permit recovery of permanently lost or destroyed limbs, but does eliminate " +
                "the temporary penalties caused by Criticals, up to a quantity equivalent to half " +
                "the Life Points returned.",
        "+5 Life Points",
        10,
        null,
        listOf(SpellType.Effect)
    )

    val damageBarrier = Spell(
        "Damage Barrier",
        Element.Creation,
        true,
        22,
        60,
        "The supernatural energies governed by this spell adhere to the body of the " +
                "designated individual, and provide him with certain immunity to damage. In " +
                "game terms, he obtains a Damage Barrier of 30 points (see Chapter 14).",
        "+5 to the Damage Barrier",
        10,
        "1 every 10 (Daily)",
        listOf(SpellType.Effect)
    )

    val homunculus = Spell(
        "Create Homunculus",
        Element.Creation,
        true,
        26,
        60,
        "Homunculus are small magical elementals that casters use at times for various " +
                "purposes, from espionage to housework. The spell creates a zero-level mystical " +
                "being under complete control of the caster, as per the rules in Chapter 26. " +
                "It is a Being Between Worlds with a Gnosis of 5, but given its minor nature, " +
                "the following rules apply: " +
                "\n1 - First of all, its characteristics can never be higher than 5, nor can it " +
                "have any Primary Ability or Secondary Ability higher than 50." +
                "\n2 - It also suffers a -2 penalty to size, and it cannot choose Uncommon Size." +
                "\n3 - It can't contain any Zeon points" +
                "\nEach Homunculus created might be completely different",
        "+1 additional Homunculus",
        20,
        "1 every 10 (Daily)",
        listOf(SpellType.Effect)
    )

    val minorChange = Spell(
        "Minor Change",
        Element.Creation,
        true,
        28,
        60,
        "This spell permits altering the appearance of an object or being by modifying its " +
                "form. The change is limited to varying its exterior appearance, and never alters " +
                "its original qualities or nature. In this way, a shining short sword might " +
                "become old and rusty, but could never become a walking stick. In the case of " +
                "living things, this spell can change things by no more than two points of size, " +
                "perhaps making an ugly and overweight man into a young good looking one. The MR " +
                "Difficulty to avoid the change is 80. Regarding objects, once affected they have " +
                "no further right to make Resistance Checks, but people can repeat the Check once " +
                "a day if they wish to be free of the effects. It is possible to affect various " +
                "targets, as long as the sum of all their Presences is no higher than 60.",
        "+5 to the MR and +10 to the maximum Presence that can be affected",
        10,
        "1 every 10 (Daily)",
        listOf(SpellType.Spiritual)
    )

    val imitate = Spell(
        "Imitate",
        Element.Creation,
        true,
        30,
        100,
        "Creates an exact copy of an already existing inorganic object within reach of the " +
                "caster. It creates a perfect imitation of the original that retains all its " +
                "qualities, including the supernatural ones. The copied object can not have " +
                "Presence greater than 30. The spell has no effect on magically animated " +
                "creatures, but can affect mechanical constructions as long as they have no soul.",
        "+5 to the maximum Presence that can be duplicated",
        20,
        "1 every 20 (Daily)",
        listOf(SpellType.Effect)
    )

    val immunity = Spell(
        "Immunity",
        Element.Creation,
        true,
        32,
        80,
        "This spell permits the caster, or those people designated by him, to be immune " +
                "to 5 Intensities of a given type of Energy, whether it is Fire, Electricity, " +
                "or Cold. If an attack is made using the type of Energy to which they are " +
                "immune, each intensity of immunity reduces the Base Damage by 5 points and " +
                "provides a +5 to the Resistances against its effects.",
        "+1 to the Intensity immunity",
        20,
        "1 every 10 (Daily)",
        listOf(SpellType.Effect)
    )

    val reduceDamage = Spell(
        "Reduction of Damage",
        Element.Creation,
        false,
        36,
        80,
        "The magic of this spell affects the power of an attack, reducing the force of its " +
                "impact. Upon being cast, this spell reduces the Base Damage of an attack by 40 " +
                "points. If the damage is lowered to zero, the attack produces no effect. If, for " +
                "example, this spell is used against an attack with Base Damage 60, when it " +
                "actually hits, it does so as if it were an attack of only 20. Reduction of " +
                "Damage must be cast before rolling any dice.",
        "-5 to the Base Damage of the attack",
        10,
        null,
        listOf(SpellType.Automatic)
    )

    val physicalControl = Spell(
        "Physical Control",
        Element.Creation,
        true,
        38,
        120,
        "Using this spell, the caster is able to control the body of a being as though it " +
                "were a simple puppet. Since the control is purely physical, not spiritual, the " +
                "subject being controlled is not obliged to use any Supernatural Ability (Magic, " +
                "Ki, and Psychic Powers) against his will, although any natural fighting abilities " +
                "are under the caster's control. Resisting the spell requires beating a MR Check " +
                "with a Difficulty of 80. The person being controlled has the right to one new " +
                "Resistance Check per day, and also any time he receives orders that are entirely " +
                "against his nature.",
        "+5 to the MR",
        20,
        "1 every 5 (Daily)",
        listOf(SpellType.Spiritual)
    )

    val raiseAbilities = Spell(
        "Raise Abilities",
        Element.Creation,
        true,
        40,
        80,
        "This spell temporarily increases the Secondary Abilities of an individual, " +
                "providing him with a +40 bonus to divide freely among them, except for those " +
                "Abilities requiring knowledge.",
        "+10 to bonus",
        20,
        "1 every 20",
        listOf(SpellType.Effect)
    )

    val fuse = Spell(
        "Fuse",
        Element.Creation,
        true,
        42,
        140,
        "Unites two beings in a single body, creating a new individual who possesses the " +
                "characteristics and abilities of both. The caster designates which capacities " +
                "prevail, selecting those that he is interested in from each. If the spell joins " +
                "a fighter and a psychic, for example, the resulting individual could have the " +
                "fighting ability of the warrior, the mental powers of the mentalist, and the " +
                "highest Secondary Abilities of each one. Control of the resulting united body " +
                "falls to whichever of the two wins an Opposed Check of Willpower, although some " +
                "characteristics typical of the other personality can be retained. The original " +
                "bodies remain in the state they were in before the fusion and so, when the spell " +
                "lapses, return to the state and condition they were in before it was cast. The " +
                "death of the joint entity causes the death of both original people. The total " +
                "Presence of both originals cannot be more than 80. It is also possible to Fuse " +
                "an individual with objects, in which case the GM can award the result the " +
                "advantages and abilities he considers appropriate.",
        "+5 to the MR and +5 to the maximum Presence affected",
        20,
        "1 every 10",
        listOf(SpellType.Effect)
    )

    val createMemories = Spell(
        "Create Memories",
        Element.Creation,
        true,
        46,
        140,
        "Permits creation of new memories in the target, without necessarily erasing the " +
                "pre-existing ones. Most of the time, unless very deeply-rooted memories are " +
                "modified, the person affected will feel slightly confused, but will be " +
                "convinced that his memories are real. The caster determines what information " +
                "he is trying to introduce, regardless of its complexity or duration. Resisting " +
                "the spell requires beating a MR or PsR Check with a Difficulty of 100. Although " +
                "this spell doesn't require maintenance, the individual affected has the right to " +
                "a new Resistance Check any time something makes him suspect his memory might be inaccurate.",
        "+5 to the MR or PsR Difficulty",
        20,
        null,
        listOf(SpellType.Spiritual)
    )
}