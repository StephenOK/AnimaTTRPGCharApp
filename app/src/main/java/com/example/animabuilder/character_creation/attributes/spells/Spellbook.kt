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
        "Detects any life-form within 80 feet. The spell only detects the number of life-forms and their exact location. Resisting the spell requires beating a MR Check with a Difficulty of 140.",
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
        "Creates a small light of energy that moves as wished by the caster, with a Flight Value of 14, for a maximum distance of one mile. Through it, the caster can see and hear as though he were present, but doing so overwhelms his body's senses, and he can only perceive the world through the Spy of Light. Each combat turn, the caster decides if he will see through the Spy of Light or his own senses. The Spy of Light has an ability of 100 at Notice and Search. If attacked, it can defend itself with the Magic Projection of its caster. For purposes of Initiative, it acts when its controller does. It is only possible to attack it with supernatural attacks, although it is destroyed if it receives any damage.",
        "+5 to Notice and Search and +1 mile to range",
        20,
        "1 every 5 (Daily)",
        listOf(SpellType.Effect)
    )
}