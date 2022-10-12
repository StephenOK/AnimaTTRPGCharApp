package com.example.animabuilder.character_creation.attributes.spells

import android.hardware.lights.Light
import java.io.Serializable

class Spellbook(): Serializable {
    val createLight = Spell(
        "Create Light",
        SpellCategory.Light,
        true,
        2,
        20,
        "Create light in a radius of 15 feet.",
        "+15 feet radius",
        20,
        "1 every 10 (Daily)",
        listOf(SpellType.Effect)
    )

    val induceCalm = Spell(
        "Induce Calm",
        SpellCategory.Light,
        true,
        6,
        40,
        "Calms individuals feeling fear or hatred within 50 feet of the caster. Makes any " +
                "Fear, Anger, or Terror States disappear, even if of supernatural origin. It does " +
                "not prevent violent actions deliberately done in cold blood. The MR or PsR Check " +
                "to overcome this spell has a Difficulty of 80.",
        "+30 feet to radius and +5 to MR or PsR Difficulty",
        10,
        null,
        listOf(SpellType.Spiritual)
    )

    val blindingFlash = Spell(
        "Blinding Flash",
        SpellCategory.Light,
        true,
        8,
        50,
        "Causes a sudden flash of light for a radius of 50 feet. It blinds anyone looking " +
                "at it when it goes off for as many combat turns as he fails the MR check by, " +
                "divided by 10. It is not possible to designate specific targets within the " +
                "flash, and everyone except the caster is equally effected. Characters can resist " +
                "this spell by passing a PhR Check with a Difficulty Check of 140. If someone is " +
                "actively avoiding looking at the flash, the PhR Check Difficulty is 80.",
        "+15 feet to radius",
        10,
        null,
        listOf(SpellType.Automatic)
    )

    val lightShield = Spell(
        "Shield of Light",
        SpellCategory.Light,
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
        SpellCategory.Light,
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
        SpellCategory.Light,
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
        SpellCategory.Light,
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
        SpellCategory.Light,
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
        SpellCategory.Light,
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
        SpellCategory.Light,
        true,
        26,
        40,
        "Creates a immaterial luminous form with a maximum size of three square feet. The " +
                "caster can give the hologram the appearance he desires making it very difficult " +
                "to tell it from something real. If he creates a creature, it can perform any " +
                "inhuman action the caster wishes, but will mimic the physical abilities of the " +
                "caster. If, for example, the hologram is used to simulate an attack, it uses " +
                "the combat ability of the caster. The hologram can not tough anyone nor be " +
                "touched, but if it receives any damage, based on Energy, it disappears. To " +
                "detect that a hologram is not real, it is necessary to beat a Notice check " +
                "against a Difficulty of Almost Impossible, or Search against Very Difficult.",
        "+3 square feet to maximum size",
        20,
        "1 every 10",
        listOf(SpellType.Effect)
    )

    val lightBond = Spell(
        "Bonds of Light",
        SpellCategory.Light,
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
        SpellCategory.Light,
        true,
        30,
        50,
        "Modifies and controls the form, color, or intensity of light in a 60-foot radius. " +
                "If cast at Light-based beings, they must pass a MR Check with a Difficulty of 80 " +
                "or they will fall under the control of the caster. A creature can only repeat " +
                "the check if it is ordered to do something against its nature.",
        "+30 feet to radius and +5 to MR Difficulty",
        20,
        "1 every 10",
        listOf(SpellType.Spiritual, SpellType.Effect)
    )

    val detectLife = Spell(
        "Detect Life",
        SpellCategory.Light,
        true,
        32,
        60,
        "Detects any life-form within 80 feet. The spell only detects the number of " +
                "life-forms and their exact location. Resisting the spell requires beating a " +
                "MR Check with a Difficulty of 140.",
        "+30 feet to radius and +10 to MR Difficulty",
        10,
    "1 every 20",
        listOf(SpellType.Detection)
    )

    val lightSpy = Spell(
        "Spy of Light",
        SpellCategory.Light,
        true,
        36,
        100,
        "Creates a small light of energy that moves as wished by the caster, with a Flight " +
                "Value of 14, for a maximum distance of one mile. Through it, the caster can see " +
                "and hear as though he were present, but doing so overwhelms his body's senses, " +
                "and he can only perceive the world through the Spy of Light. Each combat turn, " +
                "the caster decides if he will see through the Spy of Light or his own senses. " +
                "The Spy of Light has an ability of 100 at Notice and Search. If attacked, it " +
                "can defend itself with the Magic Projection of its caster. For purposes of " +
                "Initiative, it acts when its controller does. It is only possible to attack it " +
                "with supernatural attacks, although it is destroyed if it receives any damage.",
        "+5 to Notice and Search and +1 mile to range",
        20,
        "1 every 5 (Daily)",
        listOf(SpellType.Effect)
    )

    val ecstasy = Spell(
        "Ecstasy",
        SpellCategory.Light,
        true,
        38,
        60,
        "This spell intoxicates anyone affected with a feeling of utter ecstasy. The " +
                "sensation of pleasure is so powerful that the victim's senses are completely " +
                "clouded, and he receives a -20 All Action Penalty while affected. However, the " +
                "spell's victims are also completely oblivious and immune to any pain or other " +
                "affliction based penalty, except those for actually being physically " +
                "incapacitated. The MR Check to resist this spell has a Difficulty of 80, and " +
                "affects a radius of 30 feet.",
        "+30 feet to radius and +5 to MR Difficulty",
        10,
        "1 every 10",
        listOf(SpellType.Spiritual)
    )

    val banishNegativeEmotions = Spell(
        "Banish Negative Emotions",
        SpellCategory.Light,
        true,
        40,
        80,
        "Temporarily banishes any negative sentiments such as hatred, fear, or anger within " +
                "300 feet of the caster. Resisting the spell requires beating a MR or PsR Check " +
                "with a Difficulty of 100.",
        "+150 feet to radius and +5 to MR or PsR Difficulty",
        20,
        null,
        listOf(SpellType.Spiritual)
    )

    val healingLight = Spell(
        "Healing Light",
        SpellCategory.Light,
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
        SpellCategory.Light,
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
        SpellCategory.Light,
        true,
        48,
        140,
        "This spell allows the caster to detect any being within the area of the spell who " +
                "does not beat a MR Check with a Difficulty of 180. The Zone of Detection only " +
                "tells the caster how many individuals are in the zone, and their exact " +
                "location. It also senses spells of Detection that attempt to enter into the " +
                "area, as long as the spellcaster using them does not beat the MR (regardless " +
                "of his actual location). The affected zone can be no larger than 60 feet in " +
                "radius, and is stationary in the place it was cast.",
        "+30 feet to radius and +10 to MR Difficulty",
        20,
        "1 every 20 (Daily)",
        listOf(SpellType.Detection)
    )

    val enterDreams = Spell(
        "Enter Another's Dreams",
        SpellCategory.Light,
        true,
        50,
        120,
        "This allows the caster to physically enter a sleeper's dreams. The caster has no " +
                "control over the dream world of the dreamer, and anything that happens there " +
                "will be real to the caster. The person must have peaceful dreams to be affected " +
                "\by this spell, and the moment the dream turns into a nightmare, or he awakens " +
                "or dies, the mage abandons the dream world and returns to reality. Any Spiritual " +
                "spell cast on the dreamer while the caster is present in his dreams will also " +
                "affect the caster. The MR or PsR Check has a Difficulty of 140. Once he is in " +
                "the target person's dreams, the caster can jump to the unconscious of yet another " +
                "dreamer who is physically no more than 30 feet from the original sleeper. " +
                "Naturally, this new dreamer will have the right to his own MR Check. If the " +
                "dreamer's consciousness happens to be in the world of The Wake, the caster is " +
                "trapped there even when the spell expires.",
        "+30 feet additional to jumping range and +5 to MR or PsR Difficulty",
        20,
        "1 every 20 (Daily)",
        listOf(SpellType.Spiritual)
    )

    val lightForm = Spell(
        "Light Form",
        SpellCategory.Light,
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
        SpellCategory.Light,
        true,
        56,
        100,
        "Blessing endows the affected party with incredible energy. Those affected receive " +
                "a bonus of +20 to all their actions and Resistances. All allies within 15 feet " +
                "of the caster are affected. No one can be affected by more than one Blessing at a time.",
        "+15 feet to radius",
        20,
        "1 every 20",
        listOf(SpellType.Effect)
    )

    val createGoodFeelings = Spell(
        "Create Good Feelings",
        SpellCategory.Light,
        true,
        58,
        100,
        "This creates positive sentiments such as love, pleasure, or friendship in the " +
                "people designated by the caster. The radius of the spell is 60 feet, and the MR " +
                "or PsR Check has a Difficulty of 120. Those affected can repeat the Resistance " +
                "Check once per day.",
        "+5 to MR or PsR Difficulty and +30 foot radius",
        20,
        "1 every 10 (Daily)",
        listOf(SpellType.Spiritual)
    )

    val seeTruth = Spell(
        "See Truth",
        SpellCategory.Light,
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
        SpellCategory.Light,
        true,
        62,
        140,
        "This spell enchants a certain area, making it impenetrable for beings naturally " +
                "based in negative emotions or Darkness. Any such creature entering the zone must " +
                "pass a MR Check with a Difficulty of 120 or suffer the loss of a number of Life " +
                "Points equal to the margin of failure. Additionally, if it fails the check, it " +
                "receives an immediate -40 All Action Penalty. The affected zone can be no larger " +
                "than 60 feet in radius, and is stationary in the place it was cast.",
        "+60 feet to radius and +5 to the MR Difficulty",
        20,
        "1 every 10 (Daily)",
        listOf(SpellType.Automatic)
    )

    val find = Spell(
        "Find",
        SpellCategory.Light,
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
        SpellCategory.Light,
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
        SpellCategory.Light,
        true,
        70,
        150,
        "The spell creates a spectacle of lights in a specified place that has a fascinating " +
                "and dumbfounding affect. All the characters that see the display cannot help but " +
                "continue watching it. It is visible for a radius of half a mile, and anyone " +
                "seeing it must make a MR or PsR Check with a Difficulty of 120 to resist its " +
                "effects. Those affected can perform Passive Actions, but cannot move. They can " +
                "make a new Resistance Check every time they are attacked. The condition for being " +
                "affected is looking directly at the Hypnotic Display.",
        "+1 mile to radius and +5 to MR or PsR Difficulty",
        20,
        "1 every 50",
        listOf(SpellType.Automatic)
    )

    val catastrophicLight = Spell(
        "Catastrophic Light",
        SpellCategory.Light,
        true,
        72,
        120,
        "Creates a deadly discharge of Light with a Base Damage of 150. Catastrophic Light " +
                "has a radius of 80 feet and is an Energy Attack Type.",
        "+30 feet to radius and +5 damage",
        20,
        null,
        listOf(SpellType.Attack)
    )

    val luminousMaterial = Spell(
        "Luminous Material Objects",
        SpellCategory.Light,
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
}