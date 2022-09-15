package com.example.animabuilder.character_creation.attributes.advantages

import com.example.animabuilder.character_creation.BaseCharacter

class AdvantageRecord(charInstance: BaseCharacter) {
    var advantTaken: List<Advantage> = mutableListOf()
    var advantForbidden: List<Advantage> = mutableListOf()

    fun addAdvantage(toAdd: Advantage){
        if(!advantForbidden.contains(toAdd)) {
            toAdd.onReceive()
            advantTaken += toAdd
        }
    }

    fun removeAdvantage(toTake: Advantage){
        if(advantTaken.contains(toTake)){
            toTake.onRemove()
            advantTaken -= toTake
        }
    }

    fun forbidAdvantage(toAdd: List<Advantage>){
        advantForbidden += toAdd
    }

    fun allowAdvantage(toTake: List<Advantage>){
        advantForbidden -= toTake
    }

    private val exceptionalResistances =
        Advantage(
            "Exceptional Resistances",
            "As a reflection of their former existence, the Sylvain Nephilim " +
                    "are exceptionally resistant to magic, psychic attacks, and disease. " +
                    "They apply a bonus of +10 to their Magic Resistance (MR) and Psychic " +
                    "Resistance (PsR), a +20 to Disease Resistance (DR), and a +5 to Physical " +
                    "Resistance (PhR) and Venom Resistance (VR). In addition, a Nephilim cannot " +
                    "choose the following Disadvantages: Sickly, Serious Illness, or Susceptible " +
                    "to Magic.",

            0,

            {
                charInstance.rmSpec += 10
                charInstance.rpsySpec += 10
                charInstance.rdSpec += 20
                charInstance.rphysSpec += 5
                charInstance.rvSpec += 5

                forbidAdvantage(
                    listOf(
                        seriousIllness,
                        sickly,
                        magicSusceptible
                    )
                )

                charInstance.updateResistances()
            },

            {
                charInstance.rmSpec -= 10
                charInstance.rpsySpec -= 10
                charInstance.rdSpec -= 20
                charInstance.rphysSpec -= 5
                charInstance.rvSpec -= 5

                allowAdvantage(
                    listOf(
                        seriousIllness,
                        sickly,
                        magicSusceptible
                    )
                )

                charInstance.updateResistances()
            }
        )

    //val lightInclination =
        //Advantage(
            //"Unbalanced Inclination to the Light",
            //"All Sylvain possess a natural inclination to Light. That natural " +
                    //"inclination gives them a special resistance of +10 against any effect " +
                    //"based on The Light. However, that inclination also impedes them from " +
                    //"choosing the Elemental Compatibility (Dark) Advantage."
        //)




    val sylvainAdvantages: List<Advantage> =
        listOf(
            exceptionalResistances
        )
    val jayanAdvantage: List<Advantage> = listOf()
    val danjayniAdvantage: List<Advantage> = listOf()
    val ebudanAdvantages: List<Advantage> = listOf()
    val daimahAdvantages: List<Advantage> = listOf()
    val dukzaristAdvantages: List<Advantage> = listOf()

    val commonAdvantages: List<Advantage> = listOf()
    val magicAdvantages: List<Advantage> = listOf()
    val psychicAdvantage: List<Advantage> = listOf()

    private val seriousIllness =
        Advantage(
            "Serious Illness",
            "A character with this Disadvantage suffers from some type of " +
                    "degenerative disease that will end up killing him. Usually, he " +
                    "has an average of little more than half a year of life remaining " +
                    "when beginning the game. However, the period can be greater or " +
                    "lesser if the GM needs it to fit within the time frame of his campaign. " +
                    "A character with this Disadvantage is not only very playable, but " +
                    "he can also have an additional objective in finding a cure for himself. " +
                    "The character applies a cumulative penalty of -10 to all actions for each " +
                    "month of game time that passes. The GM secretly determines the date when " +
                    "the character will die.",

            2,

            {},
            {}
        )

    private val sickly =
        Advantage(
            "Sickly",
            "A character with this Disadvantage suffers from bad health and sickens " +
                    "easily. Reduce the character's Disease Resistance (DR) by half.",

            1,

            {charInstance.rdMult /= 2; charInstance.updateResistances()},
            {charInstance.rdMult *= 2; charInstance.updateResistances()}
        )

    private val magicSusceptible =
        Advantage(
            "Susceptible to Magic",
            "A character with this Disadvantage is easily affected by magical " +
                    "energies. Reduce the character's MR by half",

            1,

            {charInstance.rmMult /= 2; charInstance.updateResistances()},
            {charInstance.rmMult *= 2; charInstance.updateResistances()}
        )

    val commonDisadvantages: List<Advantage> =
        listOf(
            seriousIllness,
            sickly,
            magicSusceptible
        )
    val magicDisadvantages: List<Advantage> = listOf()
    val psychicDisadvantages: List<Advantage> = listOf()
}