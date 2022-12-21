package com.example.animabuilder.character_creation.attributes.magic

import android.hardware.lights.Light
import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.Element
import com.example.animabuilder.character_creation.attributes.magic.spells.FreeSpell
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

    //retreive all available spells
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

    //initialize character's known spells
    val primarySpellList = mutableListOf<Element>()

    //initialize points invested in each book
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

    //initialize lists of free spells taken for each spell category
    val lightBookFreeSpells = mutableListOf<FreeSpell>()
    val darkBookFreeSpells = mutableListOf<FreeSpell>()
    val creationBookFreeSpells = mutableListOf<FreeSpell>()
    val destructionBookFreeSpells = mutableListOf<FreeSpell>()
    val airBookFreeSpells = mutableListOf<FreeSpell>()
    val earthBookFreeSpells = mutableListOf<FreeSpell>()
    val waterBookFreeSpells = mutableListOf<FreeSpell>()
    val fireBookFreeSpells = mutableListOf<FreeSpell>()
    val essenceBookFreeSpells = mutableListOf<FreeSpell>()
    val illusionBookFreeSpells = mutableListOf<FreeSpell>()
    val necromancyBookFreeSpells = mutableListOf<FreeSpell>()
    val individualFreeSpells = mutableListOf<FreeSpell>()

    //initialize list of individually acquired spells
    val individualSpells = mutableListOf<Spell>()
    val spellList = mutableListOf<Spell>()






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

    /**
     * Spend magic levels in the given element
     *
     * spent: Amount of magic levels to spend
     * book: Which book to buy points in
     */
    fun buyBookLevels(spent: Int, book: Element){
        //adjust points spent depending on the inputted element
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

        //add book to primary list if neither it nor its opposite are present and there is a spent value given
        if(!primarySpellList.contains(book) && !oppositeElementFound(book) && spent != 0)
            primarySpellList.add(book)

        //attempt to set opposite as primary if spent value is 0 and there previously was a value
        else if (getElementInvestment(book) == 0 && primarySpellList.contains(book))
            setOppositeAsPrimary(book)

        //remove individually bought spells obtained in this purchase
        individualSpells.removeIf{it.inBook == book && it.level >= spent}

        //update full spell list
        updateSpellList()
    }

    fun addFreeSpell(addItem: FreeSpell, intoElement: Element){
        val addToList = getElementFreeSpells(intoElement)
        addToList.forEach{
            if(it.level == addItem.level) {
                addToList[addToList.indexOf(it)] = addItem
                updateSpellList()
                return
            }
        }

        addToList.add(addItem)

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

        individualSpells.forEach{
            if(it is FreeSpell) {
                magicLevelSpent += getIndividualCost(it.level, findFreeSpellElement(it))
                spellList.add(getFreeSpell(it.level, findFreeSpellElement(it)))
            }
            else {
                magicLevelSpent += getIndividualCost(it.level, it.inBook)
                spellList.add(it)
            }
        }
    }

    fun addSpellsFromBook(
        pointValue: Int,
        inputElement: Element,
        spellBook: List<Spell?>
    ){
        //add spent points value to cumulative value
        magicLevelSpent += pointValue

        //if at least 2 points spent in the book
        if(pointValue > 1) {
            //determine how many spells are bought depending on if opposite element is primary
            val spellRange =
                if (!oppositeElementFound(inputElement))
                    (pointValue / 2) - 1
                else
                    (pointValue / 4) - 1

            for(index in 0 .. spellRange) {
                if(spellBook[index] != null)
                    spellList.add(spellBook[index]!!)
                else
                    spellList.add(getFreeSpell((index + 1) * 2, inputElement))
            }
        }
    }

    fun getFreeSpell(inputLevel: Int, inputElement: Element): FreeSpell{
        getElementFreeSpells(inputElement).forEach {
            if (it.level == inputLevel)
                return it
        }


        return FreeSpell(
            "PlaceHolder",
            false,
            inputLevel,
            0,
            "This is not a real spell. This is a placeholder object.",
            "This is not a spell. You can't add more Zeon to it.",
            0,
            null,
            false,
            listOf(),
            listOf(inputElement)
        )
    }

    fun changeIndividualSpell(targetSpell: Spell, isBought: Boolean): Boolean{
        return if(isBought){
            if(!spellList.contains(targetSpell)){
                individualSpells.add(targetSpell)

                if(!primarySpellList.contains(targetSpell.inBook) && !oppositeElementFound(targetSpell.inBook))
                    primarySpellList.add(targetSpell.inBook)

                updateSpellList()
                true
            } else
                false
        } else{
            individualSpells.remove(targetSpell)

            if(getElementInvestment(targetSpell.inBook) == 0)
                primarySpellList.remove(targetSpell.inBook)

            updateSpellList()
            false
        }
    }

    fun changeIndividualFreeSpell(levelInput: Int, elementInput: Element, isBought: Boolean): Boolean{
        val placeHolderSpell = FreeSpell(
            "PlaceHolder",
            false,
            levelInput,
            0,
            "This is not a real spell. This is a placeholder object.",
            "This is not a spell. You can't add more Zeon to it.",
            0,
            null,
            false,
            listOf(),
            listOf(elementInput)
        )

        return if(isBought) {
            if(!individualSpells.contains(getFreeSpell(levelInput, elementInput))) {
                individualSpells.add(placeHolderSpell)

                if(!primarySpellList.contains(elementInput) && !oppositeElementFound(elementInput))
                    primarySpellList.add(elementInput)

                updateSpellList()
                true
            } else
                false
        } else{
            individualSpells.remove(placeHolderSpell)
            getElementFreeSpells(elementInput).remove(getFreeSpell(levelInput, elementInput))

            if(getElementInvestment(elementInput) == 0)
                primarySpellList.remove(elementInput)

            updateSpellList()
            false
        }
    }

    fun getIndividualCost(inputLevel: Int, inputElement: Element): Int{
        var cost = (ceil(inputLevel.toDouble()/10.0).toInt() * 2)

        if(oppositeElementFound(inputElement))
            cost *= 2

        return cost
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

    fun getIndividualPoints(inputElement: Element): Int{
        var elementTotal = 0
        individualSpells.forEach{
            if(it is FreeSpell && findFreeSpellElement(it) == inputElement)
                elementTotal += getIndividualCost(it.level, inputElement)
            else if(it.inBook == inputElement)
                elementTotal += getIndividualCost(it.level, inputElement)
        }

        return elementTotal
    }

    fun getElementInvestment(inputElement: Element): Int{
        return getIndividualPoints(inputElement) + when(inputElement){
            Element.Light -> pointsInLightBook
            Element.Dark -> pointsInDarkBook
            Element.Creation -> pointsInCreateBook
            Element.Destruction -> pointsInDestructBook
            Element.Air -> pointsInAirBook
            Element.Earth -> pointsInEarthBook
            Element.Water -> pointsInWaterBook
            Element.Fire -> pointsInFireBook
            Element.Essence -> pointsInEssenceBook
            Element.Illusion -> pointsInIllusionBook
            Element.Necromancy -> pointsInNecroBook
            else -> 0
        }
    }

    fun getElementFreeSpells(inputElement: Element): MutableList<FreeSpell>{
        return when(inputElement){
            Element.Light -> lightBookFreeSpells
            Element.Dark -> darkBookFreeSpells
            Element.Creation -> creationBookFreeSpells
            Element.Destruction -> destructionBookFreeSpells
            Element.Air -> airBookFreeSpells
            Element.Earth -> earthBookFreeSpells
            Element.Water -> waterBookFreeSpells
            Element.Fire -> fireBookFreeSpells
            Element.Essence -> essenceBookFreeSpells
            Element.Illusion -> illusionBookFreeSpells
            Element.Necromancy -> necromancyBookFreeSpells
            else -> mutableListOf()
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

    fun findFreeSpellElement(find: FreeSpell): Element{
        lightBookFreeSpells.forEach{
            if(it == find) return Element.Light
        }
        darkBookFreeSpells.forEach{
            if(it == find) return Element.Dark
        }
        creationBookFreeSpells.forEach{
            if(it == find) return Element.Creation
        }
        destructionBookFreeSpells.forEach{
            if(it == find) return Element.Destruction
        }
        airBookFreeSpells.forEach{
            if(it == find) return Element.Air
        }
        earthBookFreeSpells.forEach{
            if(it == find) return Element.Earth
        }
        waterBookFreeSpells.forEach{
            if(it == find) return Element.Water
        }
        fireBookFreeSpells.forEach{
            if(it == find) return Element.Fire
        }
        essenceBookFreeSpells.forEach{
            if(it == find) return Element.Essence
        }
        illusionBookFreeSpells.forEach{
            if(it == find) return Element.Illusion
        }
        necromancyBookFreeSpells.forEach{
            if(it == find) return Element.Necromancy
        }
        return find.forbiddenElements[0]
    }






    fun calculateSpent(): Int{
         return boughtZeon * charInstance.ownClass.zeonGrowth +
                (zeonAccMult - 1) * charInstance.ownClass.maGrowth +
                magProjTotal * charInstance.ownClass.maProjGrowth
    }
}