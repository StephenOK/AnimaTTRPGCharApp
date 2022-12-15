package com.example.animabuilder.character_creation.attributes.magic

import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.Element
import com.example.animabuilder.character_creation.attributes.magic.spells.Spell
import com.example.animabuilder.character_creation.attributes.magic.spells.spellbook.*
import java.io.Serializable
import kotlin.math.ceil

class Magic(private val charInstance: BaseCharacter) : Serializable {
    var baseZeon = 0
    var boughtZeon = 0
    var zeonMax = 0

    var baseZeonAcc = 0
    var zeonAccMult = 1
    var zeonAccTotal = 0

    var boughtMagProjection = 0
    var magProjTotal = 0
    var magProjImbalance = 0
    var imbalanceIsAttack = true

    var magicLevelMax = 0
    var magicLevelSpent = 0

    val lightBook = LightBook()
    val darkBook = DarkBook()
    val creationBook = CreationBook()
    val destructionBook = DestructionBook()
    val airBook = AirBook()
    val earthBook = EarthBook()
    val waterBook = WaterBook()
    val fireBook = FireBook()
    val essenceBook = EssenceBook()
    val illusionBook = IllusionBook()
    val necromancyBook = NecromancyBook()
    val freeBook = FreeBook()

    val primarySpellList = mutableListOf<Element>()

    var pointsInLightBook = 0
    var pointsInDarkBook = 0
    var pointsInCreateBook = 0
    var pointsInDestructBook = 0
    var pointsInAirBook = 0
    var pointsInEarthBook = 0
    var pointsInWaterBook = 0
    var pointsInFireBook = 0
    var pointsInEssenceBook = 0
    var pointsInIllusionBook = 0
    var pointsInNecroBook = 0

    var individualLightPoints = 0
    var individualDarkPoints = 0
    var individualCreatePoints = 0
    var individualDestructPoints = 0
    var individualAirPoints = 0
    var individualEarthPoints = 0
    var individualWaterPoints = 0
    var individualFirePoints = 0
    var individualEssencePoints = 0
    var individualIllusionPoints = 0
    var individualNecroPoints = 0
    var individualFreePoints = 0

    val individualSpells = mutableListOf<Spell?>()
    val spellList = mutableListOf<Spell?>()






    fun setBaseZeon() {
        baseZeon =
            if(charInstance.pow == 1)
                5
            else
                20 + (10 * charInstance.pow) + charInstance.modPOW

        calcMaxZeon()
    }

    fun buyZeon(toBuy: Int){
        boughtZeon = toBuy
        calcMaxZeon()
        charInstance.updateTotalSpent()
    }

    fun calcMaxZeon(){
        zeonMax = baseZeon + (boughtZeon * 5) + (charInstance.lvl * charInstance.ownClass.zeonPerLevel)
    }

    fun setBaseZeonAcc() {
        baseZeonAcc = when(charInstance.pow){
            in 5..7 -> 5
            in 8 .. 11 -> 10
            in 12..14 -> 15
            15 -> 20
            16, 17 -> 25
            18, 19, -> 30
            20 -> 35
            else -> 0
        }

        calcZeonAcc()
    }

    fun buyZeonAcc(toBuy: Int){
        zeonAccMult = toBuy
        calcZeonAcc()
        charInstance.updateTotalSpent()
    }

    fun calcZeonAcc(){
        zeonAccTotal = baseZeonAcc * zeonAccMult
    }

    fun buyMagProj(toBuy: Int){
        boughtMagProjection = toBuy
        calcMagProj()
    }

    fun calcMagProj(){
        magProjTotal = charInstance.modDEX + boughtMagProjection
    }

    fun setMagicLevelMax(){
        magicLevelMax = when(charInstance.int) {
            in 6..10 -> (charInstance.int - 5) * 10
            11 -> 75
            12 -> 100
            13 -> 150
            in 14..20 -> (charInstance.int - 12)* 100
            else -> 0
        }
    }

    fun buyBookLevels(spent: Int, book: Element){
        when(book){
            Element.Light -> pointsInLightBook = spent
            Element.Dark -> pointsInDarkBook = spent
            Element.Creation -> pointsInCreateBook = spent
            Element.Destruction -> pointsInDestructBook = spent
            Element.Air -> pointsInAirBook = spent
            Element.Earth -> pointsInEarthBook = spent
            Element.Water -> pointsInWaterBook = spent
            Element.Fire -> pointsInFireBook = spent
            Element.Essence -> pointsInEssenceBook = spent
            Element.Illusion -> pointsInIllusionBook = spent
            Element.Necromancy -> pointsInNecroBook = spent
            else -> {}
        }

        if(!primarySpellList.contains(book) && !oppositeElementFound(book) && spent != 0)
            primarySpellList.add(book)

        if (getElementInvestment(book) == 0 && primarySpellList.contains(book))
            setOppositeAsPrimary(book)

        updateSpellList()
    }

    fun changePrimaryBook(primeElement: Element, changeTo: Boolean){
        if(getElementInvestment(primeElement) != 0 && changeTo) {
            if(primarySpellList.contains(Element.Necromancy)) {
                primarySpellList.remove(Element.Necromancy)
                changeFromNecromancy(primeElement)
            }
            else {
                primarySpellList.removeAll(getOppositeElement(primeElement))
                primarySpellList.add(primeElement)
            }
        }
        else if(!changeTo && oppositeInvestedIn(primeElement))
            setOppositeAsPrimary(primeElement)

        updateSpellList()
    }

    fun updateSpellList(){
        magicLevelSpent = 0
        spellList.clear()

        addSpellsFromBook(pointsInLightBook, Element.Light, lightBook.fullBook)
        addSpellsFromBook(pointsInDarkBook, Element.Dark, darkBook.fullBook)
        addSpellsFromBook(pointsInCreateBook, Element.Creation, creationBook.fullBook)
        addSpellsFromBook(pointsInDestructBook, Element.Destruction, destructionBook.fullBook)
        addSpellsFromBook(pointsInAirBook, Element.Air, airBook.fullBook)
        addSpellsFromBook(pointsInEarthBook, Element.Earth, earthBook.fullBook)
        addSpellsFromBook(pointsInWaterBook, Element.Water, waterBook.fullBook)
        addSpellsFromBook(pointsInFireBook, Element.Fire, fireBook.fullBook)
        addSpellsFromBook(pointsInEssenceBook, Element.Essence, essenceBook.fullBook)
        addSpellsFromBook(pointsInIllusionBook, Element.Illusion, illusionBook.fullBook)
        addSpellsFromBook(pointsInNecroBook, Element.Necromancy, necromancyBook.fullBook)

        spellList.addAll(individualSpells)
        addIndividualCost()
    }

    fun addSpellsFromBook(
        pointValue: Int,
        inputElement: Element,
        spellBook: List<Spell?>
    ){
        magicLevelSpent += pointValue

        if(pointValue > 1) {
            val spellRange =
                if (!oppositeElementFound(inputElement))
                    pointValue / 2
                else
                    pointValue / 4

            if(pointValue < 50)
                spellList.addAll(spellBook.slice(0..spellRange))
            else
                spellList.addAll(spellBook)
        }
    }

    fun changeIndividualSpell(targetSpell: Spell, isBought: Boolean){
        if(isBought)
            individualSpells.add(targetSpell)
        else
            individualSpells.remove(targetSpell)
    }

    fun addIndividualCost(){
        individualSpells.forEach{
            val cost = (ceil((it!!.level/10).toDouble()).toInt() * 2)

            magicLevelSpent +=
                if(primarySpellList.contains(it.inBook))
                    cost
                else
                    cost * 2
        }
    }

    fun oppositeElementFound(element: Element): Boolean{
        getOppositeElement(element).forEach {
            if (primarySpellList.contains(it))
                return true
        }

        return false
    }

    fun oppositeInvestedIn(element: Element): Boolean{
        getOppositeElement(element).forEach{
            if(getElementInvestment(it) > 0)
                return true
        }

        return false
    }

    fun getElementInvestment(inputElement: Element): Int{
        return when(inputElement){
            Element.Light -> pointsInLightBook + individualLightPoints
            Element.Dark -> pointsInDarkBook + individualDarkPoints
            Element.Creation -> pointsInCreateBook + individualCreatePoints
            Element.Destruction -> pointsInDestructBook + individualDestructPoints
            Element.Air -> pointsInAirBook + individualAirPoints
            Element.Earth -> pointsInEarthBook + individualEarthPoints
            Element.Water -> pointsInWaterBook + individualWaterPoints
            Element.Fire -> pointsInFireBook + individualFirePoints
            Element.Essence -> pointsInEssenceBook + individualEssencePoints
            Element.Illusion -> pointsInIllusionBook + individualIllusionPoints
            Element.Necromancy -> pointsInNecroBook + individualNecroPoints
            else -> 0
        }
    }

    fun setOppositeAsPrimary(primary: Element){
        primarySpellList.remove(primary)

        if(primary == Element.Necromancy)
            changeFromNecromancy(null)
        else{
           getOppositeElement(primary).forEach{
                if(getElementInvestment(it) > 0 && !oppositeElementFound(it)) {
                    if(!primarySpellList.contains(it))
                        primarySpellList.add(it)
                    return
                }
            }
        }
    }

    fun changeFromNecromancy(exception: Element?){
        if(exception != Element.Light && exception != Element.Dark)
            pickGreater(Element.Light, Element.Dark)
        if(exception != Element.Creation && exception != Element.Destruction)
            pickGreater(Element.Creation, Element.Destruction)
        if(exception != Element.Air && exception != Element.Earth)
            pickGreater(Element.Air, Element.Earth)
        if(exception != Element.Water && exception != Element.Fire)
            pickGreater(Element.Water, Element.Fire)
        if(exception != Element.Essence && exception != Element.Illusion)
            pickGreater(Element.Essence, Element.Illusion)
        if(exception != null)
            primarySpellList.add(exception)
    }

    fun pickGreater(first: Element, second: Element){
        if(getElementInvestment(first) + getElementInvestment(second) != 0) {
            if (getElementInvestment(first) >= getElementInvestment(second))
                primarySpellList.add(first)
            else
                primarySpellList.add(second)
        }
    }

    fun getOppositeElement(inputElement: Element): List<Element>{
        return when(inputElement){
            Element.Light -> listOf(Element.Dark, Element.Necromancy)
            Element.Dark -> listOf(Element.Light, Element.Necromancy)
            Element.Creation -> listOf(Element.Destruction, Element.Necromancy)
            Element.Destruction -> listOf(Element.Creation, Element.Necromancy)
            Element.Air -> listOf(Element.Earth, Element.Necromancy)
            Element.Earth -> listOf(Element.Air, Element.Necromancy)
            Element.Water -> listOf(Element.Fire, Element.Necromancy)
            Element.Fire -> listOf(Element.Water, Element.Necromancy)
            Element.Essence -> listOf(Element.Illusion, Element.Necromancy)
            Element.Illusion -> listOf(Element.Essence, Element.Necromancy)
            Element.Necromancy -> listOf(Element.Light, Element.Dark, Element.Creation,
                Element.Destruction, Element.Air, Element.Earth, Element.Water, Element.Fire,
                Element.Essence, Element.Illusion)
            else -> listOf()
        }
    }






    fun calculateSpent(): Int{
         return boughtZeon * charInstance.ownClass.zeonGrowth +
                (zeonAccMult - 1) * charInstance.ownClass.maGrowth +
                magProjTotal * charInstance.ownClass.maProjGrowth
    }
}