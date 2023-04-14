package com.example.animabuilder.character_creation.attributes.ki_abilities.abilities

/**
 * Record of Ki Abilities the character can take.
 */
class KiRecord{
    val useOfKi = KiAbility(
        "Use of Ki",
        null,
        40,
        "This is the foundational Ki ability upon which all others are based. It allows " +
                "a character to awaken his inner energy and use it subconsciously."
    )

    val kiControl = KiAbility(
        "Ki Control",
        useOfKi,
        30,
        "This ability allows total control of inner energy. A character with this " +
                "ability is fully aware of his supernatural power and can accumulate Ki. Once " +
                "acquired, Ki Control allows characters to learn Dominion Techniques."
    )

    val kiDetection = KiAbility(
        "Ki Detection",
        kiControl,
        20,
        "This ability allows a character to detect a being's energy. Thus a character " +
                "with this ability is aware of any source of energy present in an area, but he " +
                "cannot determine its form, size, or intensity. Ki Detection effortlessly " +
                "overcomes obstacles such as solid objects and closed spaces, but it can not " +
                "penetrate sealed energy fields. The GM should treat this power as a special " +
                "Secondary Ability and can calculate its score by averaging the character's " +
                "total MK and his Notice score."
    )

    val erudition = KiAbility(
        "Erudition",
        kiDetection,
        10,
        "Erudition allows characters to determine the strength and form of any energy " +
                "perceived. The use of this ability makes it possible to tell if someone is " +
                "accumulating Ki or if an opponent is particularly strong. It can also provide " +
                "information as to a particular individual's energy type when specifically " +
                "sought. For instance, a character might find the exact cell in which his " +
                "friends are captive without the need of opening the door."
    )

    val weightElimination = KiAbility(
        "Weight Elimination",
        useOfKi,
        10,
        "Through control of energy, a character may affect his own body mass and " +
                "partially ignore the effects of gravity. By using this ability, a character " +
                "can temporarily perform actions otherwise virtually impossible - such as " +
                "running up walls or even running on water. A character using this ability can " +
                "run along any type of surface at his full Movement Value in a single turn. For " +
                "example, a character with a Movement Value of 70 feet per round who attempts " +
                "to cross to the other side of a river using this ability will successfully cover " +
                "the first 70 feet, but he will inevitably sink if the river stretches beyond " +
                "that point. The effects of this ability may be extended by investing 1 generic " +
                "Ki Point in every round."
    )

    val levitation = KiAbility(
        "Levitation",
        weightElimination,
        20,
        "This ability allows a character to glide through the air and move freely. " +
                "Each Flight Value achieved costs a character 1 generic Ki point. A character " +
                "using Levitation may only achieve a maximum Flight Value equal to one-quarter " +
                "of his Movement Value, rounded up. To maintain this ability and remain " +
                "levitating, the user must spend 1 extra Ki Point per minute."
    )

    val objectMotion = KiAbility(
        "Object Motion",
        levitation,
        10,
        "Armed with this ability, a character can project his energy, using it as " +
                "an extension of his own body, to touch and move objects over a distance. An " +
                "object so moved must be within sight of the character, or the character must " +
                "have a very definite notion of its location. The use of this ability costs 1 " +
                "Ki point per turn for every 10 pounds the object weighs."
    )

    val flight = KiAbility(
        "Flight",
        levitation,
        20,
        "This ability provides a character with complete control of his body mass, " +
                "enabling him to move in the air as freely as he would on the ground. This " +
                "ability releases the Movement Value restriction present in Levitation allowing " +
                "for a Flight Value equivalent to that of the character's Movement Value. A " +
                "character must spend 1 generic Ki Point for every Flight Value achieved. " +
                "Maintaining this ability costs 1 Ki Point per minute."
    )

    val presenceExtrusion = KiAbility(
        "Presence Extrusion",
        useOfKi,
        10,
        "This ability allows a character to create an invisible aura of Ki around" +
                " himself. In this way, he can physically touch pure energy and intangible " +
                "elements - such as fire, spectral beings, or even magic. In physical combat, " +
                "a character using Presence Extrusion may injure beings normally only vulnerable " +
                "to supernatural attacks, up to a value of twice his Presence. In other words, " +
                "someone  with a Presence of 50 could potentially damage a creature as if he " +
                "used a mystical weapon of Presence value 100. This ability allows characters " +
                "to repel supernatural effects with the Block Ability or even slash a fireball in half."
    )

    val energyArmor = KiAbility(
        "Energy Armor",
        presenceExtrusion,
        10,
        "This ability allows a character to use his aura as a spiritual shield against " +
                "esoteric effects and pure energy based attacks. Energy Armor grants a natural " +
                "AT of against Energy. Even though this ability counts as armor, a character does " +
                "not suffer any penalties for using additional layers of protection."
    )

    val auraExtension = KiAbility(
        "Aura Extension",
        presenceExtrusion,
        10,
        "This ability allows a character to extend his aura and convey energy to any " +
                "hand-held device as if it were a natural extension of the individual. Aura " +
                "extension channels a character's essence through a weapon, for example making " +
                "it more powerful and difficult to break. This ability also allows a character " +
                "to extend the powers bestowed by Presence Extrusion to his weapon, granting it " +
                "the possibility of damaging Energy as if it were a mystical device. These aura " +
                "powered objects can also stop supernatural attacks and similar effects. " +
                "Consequently, Aura Extension increases a weapon's Base Damage by 10 points and " +
                "adds 10 points to its Fortitude and 5 points to its Breakage. The latter ability " +
                "may also be applied to armor. If two individuals engage in combat using weapons " +
                "powered by this ability, the clash of their sharp edges will send out sparks " +
                "visible to everyone."
    )

    val destructionByKi = KiAbility(
        "Destruction by Ki",
        presenceExtrusion,
        20,
        "Using this ability, an individual may project his energy to destroy targets " +
                "encountered in his path. Characters using this ability must touch or be in " +
                "physical contact with the object they wish to destroy. Using this ability " +
                "requires a character to spend 1 generic Ki Point, which forces a targeted body " +
                "to pass a Physical Resistance Check against the base Presence of the character " +
                "using this ability. When used against a living creature, Destruction by Ki " +
                "causes damage equal to the target's Resistance Failure Level. Unless inorganic " +
                "objects pass their Resistance Check by 40 or more, they are immediately destroyed " +
                "or decrease in quality by one degree. Each extra Ki Point that a character " +
                "spends on the ability adds 5 points to a character's Presence for purposes of " +
                "the Resistance Check, up to a maximum of twice his Presence. A fighter may use " +
                "his attack ability to attempt physical contact with the enemy. Since Destruction " +
                "by Ki is regarded as an attack, it can not be maintained. A character must spend " +
                "new Ki Points every turn if he wishes to make this kind of attack."
    )

    val kiTransmission = KiAbility(
        "Ki Transmission",
        useOfKi,
        10,
        "This ability allows a character to transmit or absorb Ki from another subject. " +
                "When two individuals with this ability meet, they can exchange their points " +
                "freely. Naturally, the Ki exchanged occurs between the same Characteristics from " +
                "which they stem. The transmission index per round is equal to a character's Accumulation."
    )

    val kiHealing = KiAbility(
        "Ki Healing",
        kiTransmission,
        10,
        "This ability allows a character to restore 2 Life Points to a wounded creature " +
                "for every 1 generic Ki Point spent. A character with this ability can heal " +
                "himself or any other individual with which he comes into contact. This ability " +
                "can not restore health completely; it can only repair up to half the damage."
    )

    val useOfNecessaryEnergy = KiAbility(
        "Use of Necessary Energy",
        useOfKi,
        10,
        "A character with this ability can control his energy in such a way that he " +
                "employs only the required amount for each one of his actions. This allows him " +
                "to run or carry out sustained efforts for days without suffering the effects " +
                "of exhaustion. Use of Necessary Energy multiplies the amount of time a " +
                "character spends on physical labor, running, or performing heavy tasks without " +
                "losing Fatigue points by a factor of 10. For example, a character using this " +
                "ability and running at his maximum Movement Value would lose 1 Fatigue Point " +
                "every 50 turns, not every 5 turns as the rules state for characters without " +
                "this power. This ability also makes it possible for characters to go beyond the " +
                "normal limits of exertion by increasing the maximum number of Fatigue Points " +
                "allowed per turn. Instead of 2 Fatigue points per round, this ability raises a " +
                "character's spending limit to 5, thus adding a +75 bonus to a single Action, " +
                "or several bonuses of +15 to several at a time."
    )

    val kiConcealment = KiAbility(
        "Ki Concealment",
        useOfNecessaryEnergy,
        10,
        "A character with this ability hides the traces of his energy, rendering it " +
                "invisible to Ki Detection and Erudition. Technically, it creates a spiritual " +
                "void that hinders tracking. Like Ki Detection, Ki Concealment is calculated as " +
                "a Special Secondary Ability. Simply find the average between a character's total " +
                "MK and his Hide score. When someone with Ki Detection tries to locate a " +
                "character using Ki Concealment, they must make an Opposed check - the hiding " +
                "character's Concealment score is deducted from the searching character's " +
                "Detection score. An individual accumulating Ki while using this ability must " +
                "subtract 10 from his Concealment score for every 1 Ki Point in use. Ki " +
                "Concealment also distorts energy information gathered with Erudition. By " +
                "succeeding at an Opposed Check, individuals may send out false information " +
                "to those using Erudition. Finally, this ability also grants certain advantages " +
                "against supernatural detection. If a character using Ki Concealment is being " +
                "tracked down by a spell or psychic ability, he can add half his Ki Concealment " +
                "score to the Resistance Check he needs to pass in order to avoid detection."
    )

    val falseDeath = KiAbility(
        "False Death",
        kiConcealment,
        10,
        "This ability enables a character to slip into a comatose state very much like " +
                "death. Someone in this state can not move but will be aware of any event taking " +
                "place around him. Anyone examining his body will conclude the subject is " +
                "actually dead. The \"corpse\" possesses no breath, heartbeat, or energy. The " +
                "only way to assess the character's true condition is by passing a Medicine Check " +
                "against an Impossible difficulty. Regaining control of the body after use of " +
                "False Death takes a whole turn. During this time, the recovering character still " +
                "can not move - though his body's functions are returning to normal."
    )

    val eliminateNecessities = KiAbility(
        "Elimination of Necessities",
        useOfNecessaryEnergy,
        10,
        "A character with this ability has virtually eliminated his physical needs, " +
                "requiring only one-tenth the food, water and sleep of a normal person."
    )

    val penaltyReduction = KiAbility(
        "Penalty Reduction",
        useOfNecessaryEnergy,
        20,
        "A character with this ability can reduce penalties applied to him from Fatigue " +
                "or Critical by half (rounding down). Penalty Reduction does not act upon " +
                "penalties applied due to amputation or similar damage, nor penalties caused by " +
                "magic or psychic powers."
    )

    val recovery = KiAbility(
        "Recovery",
        penaltyReduction,
        20,
        "This ability allows a character to use Ki to recover from physical exertion. " +
                "Recovery allows a character to recover 1 Fatigue point for every 5 generic Ki " +
                "points spent. Only 1 point may be recovered per turn."
    )

    val charAugmentation = KiAbility(
        "Characteristic Augmentation",
        useOfNecessaryEnergy,
        20,
        "By using internal energy, individuals with this ability can increase their " +
                "physical Characteristics up to three points higher than their original values. " +
                "The number of Ki Points a character must spend equals the target number they want " +
                "to reach in the particular Characteristic. Furthermore, the Ki Points spent must " +
                "come from the Characteristic the character wants to improve. In addition to the " +
                "initial Ki investment, the ability costs 1 Ki Point per turn to maintain."
    )

    val inhumanity = KiAbility(
        "Inhumanity",
        useOfKi,
        30,
        "This ability allows a character to perform physical tasks otherwise impossible " +
                "to human beings. Inhumanity allows its user to count any Inhuman-level results " +
                "they achieve on the Difficulty Table and get the most out of the capabilities " +
                "their Characteristics allow."
    )

    val zen = KiAbility(
        "Zen",
        inhumanity,
        50,
        "Zen is the state of ultimate perfection in body and soul. It works exactly " +
                "like Inhumanity, except for the fact that it enables characters to reach Zen " +
                "Difficulty in their Checks and Abilities."
    )

    //compile all ki abilities together
    val allKiAbilities = listOf(useOfKi, kiControl, kiDetection, erudition, weightElimination,
        levitation, objectMotion, flight, presenceExtrusion, energyArmor, auraExtension,
        destructionByKi, kiTransmission, kiHealing, useOfNecessaryEnergy, kiConcealment, falseDeath,
        eliminateNecessities, penaltyReduction, recovery, charAugmentation, inhumanity, zen)
}