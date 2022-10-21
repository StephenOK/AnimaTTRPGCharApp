package com.example.animabuilder.character_creation.attributes.ki_abilities

import androidx.compose.runtime.MutableState
import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.Element
import com.google.android.material.resources.TextAppearance
import java.io.BufferedReader
import java.io.Serializable

class KiList: Serializable {
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
        "Ki Healaing",
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

    val allKiAbilities = listOf(useOfKi, kiControl, kiDetection, erudition, weightElimination,
        levitation, objectMotion, flight, presenceExtrusion, energyArmor, auraExtension,
        destructionByKi, kiTransmission, kiHealing, useOfNecessaryEnergy, kiConcealment, falseDeath,
        eliminateNecessities, penaltyReduction, recovery, charAugmentation, inhumanity, zen)

    var takenAbilities: List<KiAbility> = listOf()

    fun attemptAbilityAdd(newIn: KiAbility): Boolean{
        if(martialKnowledgeRemaining - newIn.mkCost >= 0) {
            takenAbilities = takenAbilities + newIn
            updateMkSpent()
            return true
        }

        return false
    }

    fun removeAbility(item: KiAbility){
        takenAbilities = takenAbilities - item

        takenAbilities.forEach{
            if(it.prerequisites != null && !takenAbilities.contains(it.prerequisites))
                takenAbilities = takenAbilities - it
        }

        updateMkSpent()
    }

    val excisumAeris = Technique(
        "Excisum Aeris",
        "This technique allows the character to emit a full Ki explosion at a specific " +
                "moment, projecting a blow at such speed that it causes the air to warp while he " +
                "advances to attack. This technique uses the Base Damage of the user's weapon.",
        1,
        false,
        listOf(
            TechniqueEffect(
                "Long-Distance Attack", "+50m", 15, 3, 4,
                listOf(0, 4, 0, 0, 2, 0), listOf(Element.Air), 1),
            TechniqueEffect(
                "Initiative Augmentation", "+50", 10, 1, 2,
                listOf(0, 0, 4, 0, 0, 0), listOf(Element.Air, Element.Water, Element.Fire), 1)
        )
    )

    val velocitasVentas = Technique(
        "Velocitas Ventas",
        "By increasing his speed beyond human limits, a character can travel so fast " +
                "that his body seems to split. In this way, he can attack four times during the " +
                "Combat Turn while also adding a bonus to his Final Initiative.",
        1,
        false,
        listOf(
            TechniqueEffect("Limited Additional Attack", "+3", 15, 3, 1,
                listOf(0, 7, 3, 0, 4, 0), listOf(Element.Air, Element.Water, Element.Dark), 1),
            TechniqueEffect("Initiative Augmentation", "+50", 10, 1, 2,
                listOf(0, 0, 4, 0, 0, 0), listOf(Element.Air, Element.Water, Element.Fire), 1)
        )
    )

    val excisumMagister = Technique(
        "Excisum Magister",
        "The fighter concentrates all his energy in a single move - an attack of such " +
                "speed that it has the ability to split an opponent's body. This Technique " +
                "increases both Base Initiative and Attack Ability.",
        2,
        false,
        listOf(
            TechniqueEffect("Initiative Augmentation", "+125", 25, 4, 2,
                listOf(0, 0, 8, 0, 0, 0), listOf(Element.Air, Element.Water, Element.Fire), 2),
            TechniqueEffect("Attack Ability", "+75", 20, 6, 1,
                listOf(0, 8, 0, 0, 5, 0), listOf(Element.Air, Element.Fire, Element.Dark), 1)
        )
    )

    val magnusExacter = Technique(
        "Magnus Exacter",
        "The character moves at full speed shattering all obstacles in his way up to a " +
                "10 meter radius. The victims of the attack only manage to see someone suddenly " +
                "materialize and charge against them, only to disappear soon after. This Technique " +
                "uses the Base Damage of the user's weapon.",
        2,
        false,
        listOf(
            TechniqueEffect("Area Attack", "10m radius", 15, 2, 4,
                listOf(0, 0, 0, 0, 3, 0), listOf(Element.Dark, Element.Light, Element.Fire), 1),
            TechniqueEffect("Initiative Augmentation", "+150", 30, 5, 2,
                listOf(0, 7, 7, 0, 0, 0), listOf(Element.Air, Element.Water, Element.Fire), 2)
        )
    )

    val summum = Technique(
        "Summum",
        "The character reaches out his arms and vanishes momentarily. Right at that " +
                "moment, he begins to materialize around the victim, continually going back and " +
                "forth through his body, destroying him completely in the process. The attacker " +
                "appears to vanish, then materialize in a whirling blur of motion that completely " +
                "destroys his victim. This Technique allows a character to perform nine attacks " +
                "in the same Combat Turn, the first of which is usually accompanied by Surprise " +
                "due to its reaction speed.",
        3,
        false,
        listOf(
            TechniqueEffect("Limited Additional Attack", "+8", 50, 10, 1,
                listOf(0, 17, 0, 0, 8, 0), listOf(Element.Air, Element.Water, Element.Dark), 2),
            TechniqueEffect("Initiative Augmentation", "+175", 35, 6, 2,
                listOf(0, 0, 15, 0, 0, 0), listOf(Element.Air, Element.Water, Element.Fire), 3,)
        )
    )

    val feuer = Technique(
        "Feuer",
        "By concentrating Ki in his weapon, a character can surround it with an aura " +
                "of fire, thus increasing its Base Damage by 25 points and gaining the possibility " +
                "of attacking in the Heat Table as a primary Critical. Feuer can be maintained " +
                "investing 1 Ki Point for Will and 1 for Strength in each round. This is also a key " +
                "Technique for performing subsequent attacks of greater power.",
        1,
        true,
        listOf(
            TechniqueEffect("Damage Augmentation", "+25", 5, 1, 0,
                listOf(2, 0, 0, 0, 0, 0), listOf(Element.Fire, Element.Earth), 1),
            TechniqueEffect("Elemental Attack", "Heat", 5, 1, 5,
                listOf(0, 0, 0, 0, 1, 4), listOf(), 1)
        )
    )

    val leFeu = Technique(
        "Le Feu",
        "This Technique unleashes a potent ball of fire which can strike a target up to " +
                "20 meters away. Le Feu does not use the weapon's damage, but twice the character's " +
                "Base Presence plus his Power Bonus.",
        1,
        false,
        listOf(
            TechniqueEffect("Attack Ability", "+40", 10, 3, 1,
                listOf(1, 3, 0, 0, 4, 0), listOf(Element.Air, Element.Fire, Element.Dark), 1),
            TechniqueEffect("Long-Distance Attack", "20m", 10, 2, 4,
                listOf(0, 0, 0, 0, 1, 5), listOf(Element.Air, Element.Water, Element.Fire), 1)
        )
    )

    val horecka = Technique(
        "Horecka",
        "This Technique unleashes an inferno that devours everything contained within " +
                "50 meter radius around the character in a huge explosion. This fire is so intense " +
                "it doubles the weapon's original Base Damage. Horecka requires a character to " +
                "maintain the first-level Technique, Feuer.",
        2,
        false,
        listOf(
            TechniqueEffect("Damage Multiplier", "x2", 25, 4, 0,
                listOf(2, 0, 0, 0, 2, 8), listOf(Element.Light, Element.Water, Element.Earth), 1),
            TechniqueEffect("Area Attack", "50m radius", 30, 5, 4,
                listOf(0, 6, 0, 0, 4, 2), listOf(Element.Dark, Element.Light, Element.Fire), 2),
            TechniqueEffect("Special Requirements", "Determined Condition", -15, null, null,
                listOf(0, 0, 0, 0, 0, 0), listOf(), 1)
        )
    )

    val vatra = Technique(
        "Vatra",
        "Like a phoenix, the attacker wreaths himself in flames and charges against " +
                "his opponent with an attack that consumes him physically and spiritually. This " +
                "attack increases damage, Attack Ability, and the resulting Critical (if any). " +
                "Vatra requires a character to maintain the first-level Technique, Feuer.",
        2,
        false,
        listOf(
            TechniqueEffect("Attack Ability", "+75", 20, 6, 1,
                listOf(0, 6, 0, 0, 0, 5), listOf(Element.Air, Element.Fire, Element.Dark), 1),
            TechniqueEffect("Damage Augmentation", "+75", 20, 3, 0,
                listOf(1, 0, 0, 3, 0, 7), listOf(Element.Fire, Element.Earth), 1),
            TechniqueEffect("Critical Enhancement", "+40", 10, 3, 4,
                listOf(0, 0, 0, 0, 6, 0), listOf(Element.Fire, Element.Earth), 1),
            TechniqueEffect("Special Requirements", "Determined Condition", -10, null, null,
                listOf(0, 0, 0, 0, 0, 0), listOf(), 1)
        )
    )

    val eld = Technique(
        "Eld",
        "This frightening Technique has devastating effects, both on the victim and " +
                "the performer. In order to employ it, a character must sacrifice part of his " +
                "vital energy, suffering the resulting Damage in the process. In addition to the " +
                "increase in the character's Attack Ability, the Base Damage is increased by an " +
                "amount equivalent to twice the Life Points sacrificed. Eld requires a character " +
                "to maintain the first-level Technique, Feuer.",
        3,
        false,
        listOf(
            TechniqueEffect("Sacrifice", "Double Vital Sacrifice", 50, 4, 0,
                listOf(10, 0, 0, 0, 0, 0), listOf(Element.Fire, Element.Earth), 1),
            TechniqueEffect("Attack Ability", "+150", 40, 14, 1,
                listOf(0, 10, 0, 0, 0, 19), listOf(Element.Air, Element.Fire, Element.Dark), 2),
            TechniqueEffect("Special Requirements", "Determined Condition", -30, null, null,
                listOf(0, 0, 0, 0, 0, 0), listOf(), 1)
        )
    )

    val theScales = Technique(
        "The Scales",
        "Dragons are equipped to face a multitude of enemies. Thus, they can repel " +
                "several attacks without applying penalties. This Technique allows the user to " +
                "repel seven attacks without compromising his ability.",
        1,
        false,
        listOf(
            TechniqueEffect("Additional Defense", "+6", 20, 6, 2,
                listOf(0, 3, 1, 3, 0, 0), listOf(Element.Light), 1)
        )
    )

    val theClaws = Technique(
        "The Claws",
        "There are few things as lethal as a dragon's claws. Executing this Technique " +
                "permits the user to make two attacks, each with a 40-point bonus to Base Damage.",
        1,
        false,
        listOf(
            TechniqueEffect("Additional Attack", "+1", 20, 3, 1,
                listOf(0, 4, 4, 0, 0, 0), listOf(Element.Air, Element.Water), 1),
            TechniqueEffect("Damage Augmentation", "+40", 10, 1, 0,
                listOf(2, 0, 0, 4, 0, 0), listOf(Element.Fire, Element.Earth), 1)
        )
    )

    val theFang = Technique(
        "The Fang",
        "This attack lowers the victim's AT by 6 points while increasing offensive " +
                "ability and reducing penalties for performing aimed Attacks.",
        2,
        false,
        listOf(
            TechniqueEffect("Attack Ability", "+50", 15, 4, 1,
                listOf(0, 5, 0, 0, 0, 0), listOf(Element.Air, Element.Fire, Element.Dark), 1),
            TechniqueEffect("Armor Destruction", "-6", 20, 3, 0,
                listOf(3, 0, 0, 8, 0, 0), listOf(Element.Dark, Element.Fire), 2),
            TechniqueEffect("Combat Maneuvers and Aiming", "-50", 10, 2, 1,
                listOf(0, 0, 6, 0, 0, 0), listOf(Element.Air), 1)
        )
    )

    val theTail = Technique(
        "The Tail",
        "A dragon's tail flap can send entire armies flying. This Technique allows a " +
                "character to make a forceful attack within an 80-foot radius using his weapon's " +
                "Base Damage and increasing its offensive ability. Any individual hit by this " +
                "Technique must make and Opposed Check against Strength 16 or be knocked to the ground.",
        2,
        false,
        listOf(
            TechniqueEffect("Projection", "16", 25, 7, 0,
                listOf(2, 0, 0, 8, 0, 0), listOf(Element.Earth, Element.Fire), 2),
            TechniqueEffect("Attack Ability", "+40", 10, 3, 1,
                listOf(0, 6, 0, 0, 0, 0), listOf(Element.Air, Element.Fire, Element.Dark), 1),
            TechniqueEffect("Area Attack", "25m radius", 20, 3, 4,
                listOf(0, 0, 0, 0, 6, 0), listOf(Element.Dark, Element.Light, Element.Fire), 1)
        )
    )

    val dragonsBreath = Technique(
        "The Dragon's Breath",
        "This Technique fires a pulse of pure energy up to a maximum distance of 1 " +
                "kilometer. At the will of the user, the pulse bursts, causing utter destruction in a " +
                "100 meter radius. The damage Dragon's Breath deals is twice the character's Base " +
                "Presence, plus his Power Bonus, then multiplied by two. Dragon's Breath is a " +
                "Predetermined Technique and must be declared before it is used.",
        3,
        false,
        listOf(
            TechniqueEffect("Long-Distance Attack", "1km", 35, 8, 4,
                listOf(0, 0, 6, 10, 4, 0), listOf(Element.Air, Element.Water, Element.Fire), 2),
            TechniqueEffect("Area Attack", "100m radius", 30, 5, 4,
                listOf(0, 10, 4, 0, 2, 0), listOf(Element.Dark, Element.Light, Element.Fire), 2),
            TechniqueEffect("Energy Damaging Attack", "", 5, 1, 4,
                listOf(5, 0, 0, 0, 0, 0), listOf(Element.Fire, Element.Light, Element.Dark), 1),
            TechniqueEffect("Damage Multiplier", "x2", 25, 4, 0,
                listOf(10, 0, 0, 0, 0, 0), listOf(Element.Fire, Element.Earth), 1),
            TechniqueEffect("Predetermination", "", -20, null, null,
                listOf(0, 0, 0, 0, 0, 0), listOf(), 1)
        )
    )

    val allTechniques = listOf(excisumAeris, velocitasVentas, excisumMagister, magnusExacter,
        summum, feuer, leFeu, horecka, vatra, eld, theScales, theClaws, theFang, theTail, dragonsBreath)

    var takenFirstTechniques: List<Technique> = listOf()
    var takenSecondTechniques: List<Technique> = listOf()
    var takenThirdTechniques: List<Technique> = listOf()

    var customTechniques: List<Technique> = listOf()

    var takenTechniques: List<Technique> = takenFirstTechniques + takenSecondTechniques + takenThirdTechniques

    fun addTechnique(input: Technique): Boolean{
        when(input.level){
            1 ->
                if(martialKnowledgeRemaining - input.mkCost() >= 0){
                    takenFirstTechniques = takenFirstTechniques + input
                    updateFullList()
                    return true
                }
            2 ->
                if(martialKnowledgeRemaining - input.mkCost() >= 0 && takenFirstTechniques.size >= 2){
                    takenSecondTechniques = takenSecondTechniques + input
                    updateFullList()
                    return true
                }
            3 ->
                if(martialKnowledgeRemaining - input.mkCost() >= 0 && takenSecondTechniques.size >= 2){
                    takenThirdTechniques = takenThirdTechniques + input
                    updateFullList()
                    return true
                }
            else -> {}
        }

        return false
    }

    fun removeTechnique(input: Technique){
        when(input.level){
            1 -> takenFirstTechniques = takenFirstTechniques - input
            2 -> takenSecondTechniques = takenSecondTechniques - input
            3 -> takenThirdTechniques = takenThirdTechniques - input
            else -> {}
        }
        removeExtra()
        updateFullList()
    }

    fun removeExtra(){
        if(takenFirstTechniques.size < 2)
            takenSecondTechniques = listOf()
        if(takenSecondTechniques.size < 2)
            takenThirdTechniques = listOf()
    }

    fun updateFullList(){
        takenTechniques = takenFirstTechniques + takenSecondTechniques + takenThirdTechniques
        updateMkSpent()
    }

    fun updateMkSpent(){
        martialKnowledgeRemaining = martialKnowledgeMax

        takenAbilities.forEach{
            martialKnowledgeRemaining -= it.mkCost
        }

        takenTechniques.forEach{
            martialKnowledgeRemaining -= it.mkCost()
        }
    }

    var martialKnowledgeMax = 0
    var martialKnowledgeRemaining = martialKnowledgeMax

    var kiSTR = 0
    var kiDEX = 0
    var kiAGI = 0
    var kiCON = 0
    var kiPOW = 0
    var kiWP = 0

    var totalKi = 0

    var accSTR = 0
    var accDEX = 0
    var accAGI = 0
    var accCON = 0
    var accPOW = 0
    var accWP = 0

    var totalAcc = 0

    var boughtStrPoint = 0
    var boughtDexPoint = 0
    var boughtAgiPoint = 0
    var boughtConPoint = 0
    var boughtPowPoint = 0
    var boughtWpPoint = 0

    val setBoughtStr = {
            input: Int,
            charIn: BaseCharacter,
            statFinal: MutableState<String>,
            totalDisplay: MutableState<String>->
        boughtStrPoint = input
        updateStr(charIn)
        updateTotals(charIn)
        statFinal.value = kiSTR.toString()
        totalDisplay.value = totalKi.toString()
    }

    val setBoughtDex = {
            input: Int,
            charIn: BaseCharacter,
            statFinal: MutableState<String>,
            totalDisplay: MutableState<String> ->
        boughtDexPoint = input
        updateDex(charIn)
        updateTotals(charIn)
        statFinal.value = kiDEX.toString()
        totalDisplay.value = totalKi.toString()
    }

    val setBoughtAgi = {
            input: Int,
            charIn: BaseCharacter,
            statFinal: MutableState<String>,
            totalDisplay: MutableState<String> ->
        boughtAgiPoint = input
        updateAgi(charIn)
        updateTotals(charIn)
        statFinal.value = kiAGI.toString()
        totalDisplay.value = totalKi.toString()
    }

    val setBoughtCon = {
            input: Int,
            charIn: BaseCharacter,
            statFinal: MutableState<String>,
            totalDisplay: MutableState<String> ->
        boughtConPoint = input
        updateCon(charIn)
        updateTotals(charIn)
        statFinal.value = kiCON.toString()
        totalDisplay.value = totalKi.toString()
    }

    val setBoughtPow = {
            input: Int,
            charIn: BaseCharacter,
            statFinal: MutableState<String>,
            totalDisplay: MutableState<String> ->
        boughtPowPoint = input
        updatePow(charIn)
        updateTotals(charIn)
        statFinal.value = kiPOW.toString()
        totalDisplay.value = totalKi.toString()
    }

    val setBoughtWp = {
            input: Int,
            charIn: BaseCharacter,
            statFinal: MutableState<String>,
            totalDisplay: MutableState<String> ->
        boughtWpPoint = input
        updateWp(charIn)
        updateTotals(charIn)
        statFinal.value = kiWP.toString()
        totalDisplay.value = totalKi.toString()
    }

    var totalPointBuy = 0

    var boughtStrAcc = 0
    var boughtDexAcc = 0
    var boughtAgiAcc = 0
    var boughtConAcc = 0
    var boughtPowAcc = 0
    var boughtWpAcc = 0

    val setStrAcc = {
            input: Int,
            charIn: BaseCharacter,
            statFinal: MutableState<String>,
            totalDisplay: MutableState<String> ->
        boughtStrAcc = input
        updateStr(charIn)
        updateTotals(charIn)
        statFinal.value = accSTR.toString()
        totalDisplay.value = totalAcc.toString()
    }

    val setDexAcc = {
            input: Int,
            charIn: BaseCharacter,
            statFinal: MutableState<String>,
            totalDisplay: MutableState<String> ->
        boughtDexAcc = input
        updateDex(charIn)
        updateTotals(charIn)
        statFinal.value = accDEX.toString()
        totalDisplay.value = totalAcc.toString()
    }

    val setAgiAcc = {
            input: Int,
            charIn: BaseCharacter,
            statFinal: MutableState<String>,
            totalDisplay: MutableState<String> ->
        boughtAgiAcc = input
        updateAgi(charIn)
        updateTotals(charIn)
        statFinal.value = accAGI.toString()
        totalDisplay.value = totalAcc.toString()
    }

    val setConAcc = {
            input: Int,
            charIn: BaseCharacter,
            statFinal: MutableState<String>,
            totalDisplay: MutableState<String> ->
        boughtConAcc = input
        updateCon(charIn)
        updateTotals(charIn)
        statFinal.value = accCON.toString()
        totalDisplay.value = totalAcc.toString()
    }

    val setPowAcc = {
            input: Int,
            charIn: BaseCharacter,
            statFinal: MutableState<String>,
            totalDisplay: MutableState<String> ->
        boughtPowAcc = input
        updatePow(charIn)
        updateTotals(charIn)
        statFinal.value = accPOW.toString()
        totalDisplay.value = totalAcc.toString()
    }

    val setWpAcc = {
            input: Int,
            charIn: BaseCharacter,
            statFinal: MutableState<String>,
            totalDisplay: MutableState<String> ->
        boughtWpAcc = input
        updateWp(charIn)
        updateTotals(charIn)
        statFinal.value = accWP.toString()
        totalDisplay.value = totalAcc.toString()
    }

    var totalAccBuy = 0

    fun updateKiStats(charInstance: BaseCharacter){
        updateStr(charInstance)
        updateDex(charInstance)
        updateAgi(charInstance)
        updateCon(charInstance)
        updatePow(charInstance)
        updateWp(charInstance)

        updateTotals(charInstance)
    }

    fun updateStr(charInstance: BaseCharacter){
        kiSTR = getStatKi(charInstance.str) + boughtStrPoint
        accSTR = getStatKiAcc(charInstance.str) + boughtStrAcc
    }

    fun updateDex(charInstance: BaseCharacter){
        kiDEX = getStatKi(charInstance.dex) + boughtDexPoint
        accDEX = getStatKiAcc(charInstance.dex) + boughtDexAcc
    }

    fun updateAgi(charInstance: BaseCharacter){
        kiAGI = getStatKi(charInstance.agi) + boughtAgiPoint
        accAGI = getStatKiAcc(charInstance.agi) + boughtAgiAcc
    }

    fun updateCon(charInstance: BaseCharacter){
        kiCON = getStatKi(charInstance.con) + boughtConPoint
        accCON =  getStatKiAcc(charInstance.con) + boughtConAcc
    }

    fun updatePow(charInstance: BaseCharacter){
        kiPOW = getStatKi(charInstance.pow) + boughtPowPoint
        accPOW = getStatKiAcc(charInstance.pow) + boughtPowAcc
    }

    fun updateWp(charInstance: BaseCharacter){
        kiWP = getStatKi(charInstance.wp) + boughtWpPoint
        accWP = getStatKiAcc(charInstance.wp) + boughtWpAcc
    }

    fun updateTotals(charInstance: BaseCharacter){
        totalKi = kiSTR + kiDEX + kiAGI + kiCON + kiPOW + kiWP
        totalAcc = accSTR + accDEX + accAGI + accCON + accPOW + accWP

        totalPointBuy = boughtStrPoint + boughtDexPoint + boughtAgiPoint + boughtConPoint + boughtPowPoint + boughtWpPoint
        totalAccBuy = boughtStrAcc + boughtDexAcc + boughtAgiAcc + boughtConAcc + boughtPowAcc + boughtWpAcc
        charInstance.updateTotalSpent()
    }

    fun getStatKi(input: Int): Int {
        return if (input <= 10)
                input
            else
                10 + ((input - 10) * 2)
    }

    fun getStatKiAcc(input: Int): Int{
        return if(input <= 9)
                1
            else if (input in 10 .. 12)
                2
            else if(input in 13 .. 15)
                3
            else 4
    }

    fun calculateSpent(charInstance: BaseCharacter): Int{
        var total = 0

        total += totalPointBuy * charInstance.ownClass.kiGrowth
        total += totalAccBuy * charInstance.ownClass.kiAccumMult

        return total
    }






    fun loadKiAttributes(fileReader: BufferedReader){
        boughtStrPoint = fileReader.readLine().toInt()
        boughtStrAcc = fileReader.readLine().toInt()
        boughtDexPoint = fileReader.readLine().toInt()
        boughtDexAcc = fileReader.readLine().toInt()
        boughtAgiPoint = fileReader.readLine().toInt()
        boughtAgiAcc = fileReader.readLine().toInt()
        boughtConPoint = fileReader.readLine().toInt()
        boughtConAcc = fileReader.readLine().toInt()
        boughtPowPoint = fileReader.readLine().toInt()
        boughtPowAcc = fileReader.readLine().toInt()
        boughtWpPoint = fileReader.readLine().toInt()
        boughtWpAcc = fileReader.readLine().toInt()

        var loops = fileReader.readLine().toInt()

        while(loops > 0){
            takenAbilities = takenAbilities + listOf(getAbility(fileReader.readLine())!!)
            loops--
        }

        loops = fileReader.readLine().toInt()

        while(loops > 0){
            val techName = fileReader.readLine()
            val techDesc = fileReader.readLine()
            val techLvl = fileReader.readLine().toInt()
            val techMaint = fileReader.readLine() == "true"
            var techEffects: List<TechniqueEffect> = listOf()

            var techLoops = fileReader.readLine().toInt()

            while(techLoops > 0){
                val teName = fileReader.readLine()
                val teEffect = fileReader.readLine()
                val teCost = fileReader.readLine().toInt()

                val teMaint: Int? =
                    try{
                        fileReader.readLine().toInt()
                    }catch(_: NumberFormatException){
                        null
                    }

                val teIndex: Int? =
                    try{
                        fileReader.readLine().toInt()
                    }catch(_: NumberFormatException){
                        null
                    }

                val teBuild = mutableListOf(
                    fileReader.readLine().toInt(),
                    fileReader.readLine().toInt(),
                    fileReader.readLine().toInt(),
                    fileReader.readLine().toInt(),
                    fileReader.readLine().toInt(),
                    fileReader.readLine().toInt()
                )

                var teElements: List<Element> = listOf()

                var effectLoops = fileReader.readLine().toInt()
                while(effectLoops > 0){
                    teElements = teElements + Element.fromString(fileReader.readLine())
                    effectLoops--
                }

                techEffects = techEffects +
                        TechniqueEffect(
                            teName,
                            teEffect,
                            teCost,
                            teMaint,
                            teIndex,
                            teBuild,
                            teElements,
                            fileReader.readLine().toInt()
                        )

                techLoops--
            }

            val newTech = Technique(techName, techDesc, techLvl, techMaint, techEffects)

            if(techEquivalent(newTech).first)
                addTechnique(techEquivalent(newTech).second!!)
            else
                addTechnique(newTech)

            loops--
        }

        updateMkSpent()
    }

    fun writeKiAttributes(charInstance: BaseCharacter){
        charInstance.addNewData(boughtStrPoint)
        charInstance.addNewData(boughtStrAcc)
        charInstance.addNewData(boughtDexPoint)
        charInstance.addNewData(boughtDexAcc)
        charInstance.addNewData(boughtAgiPoint)
        charInstance.addNewData(boughtAgiAcc)
        charInstance.addNewData(boughtConPoint)
        charInstance.addNewData(boughtConAcc)
        charInstance.addNewData(boughtPowPoint)
        charInstance.addNewData(boughtPowAcc)
        charInstance.addNewData(boughtWpPoint)
        charInstance.addNewData(boughtWpAcc)

        charInstance.addNewData(takenAbilities.size)
        takenAbilities.forEach{
            charInstance.addNewData(it.name)
        }

        charInstance.addNewData(takenTechniques.size)
        takenTechniques.forEach{
            it.write(charInstance)
        }
    }

    fun getAbility(toFind: String): KiAbility?{
        allKiAbilities.forEach{
            if(it.name == toFind)
                return it
        }

        return null
    }

    fun techEquivalent(compareTo: Technique): Pair<Boolean, Technique?>{
        allTechniques.forEach{
            if(it.equivalentTo(compareTo))
                return Pair(true, it)
        }

        return Pair(false, null)
    }
}