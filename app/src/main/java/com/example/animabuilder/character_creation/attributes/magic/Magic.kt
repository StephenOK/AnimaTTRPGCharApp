package com.example.animabuilder.character_creation.attributes.magic

import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.Element
import com.example.animabuilder.character_creation.attributes.magic.spells.FreeSpell
import com.example.animabuilder.character_creation.attributes.magic.spells.Spell
import com.example.animabuilder.character_creation.attributes.magic.spells.spellbook.*
import java.io.BufferedReader
import java.io.Serializable
import kotlin.math.ceil

/**
 * Component that holds the character's magical abilities
 * Holds data for maximum Zeon, Zeon accumulation, and magic projection
 * Holds data for the spells available to the character
 */
class Magic(private val charInstance: BaseCharacter) : Serializable {
    //initialize Zeon total stats
    var baseZeon = 0
    var boughtZeon = 0
    var zeonMax = 0

    //initialize Zeon Accumulation stats
    var baseZeonAcc = 0
    var zeonAccMult = 1
    var zeonAccTotal = 0

    var magicRecoveryMult = 1.0
    var magicRecoveryTotal = 0

    //initialize Magic Projection stats
    var boughtMagProjection = 0
    var magProjTotal = 0
    var magProjImbalance = 0
    var imbalanceIsAttack = true

    //initialize Magic Level stats
    var magicLevelMax = 0
    var magicLevelSpent = 0

    //retrieve all available spells
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
    val primaryElementList = mutableListOf<Element>()

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
    private val lightBookFreeSpells = mutableListOf<FreeSpell>()
    private val darkBookFreeSpells = mutableListOf<FreeSpell>()
    private val creationBookFreeSpells = mutableListOf<FreeSpell>()
    private val destructionBookFreeSpells = mutableListOf<FreeSpell>()
    private val airBookFreeSpells = mutableListOf<FreeSpell>()
    private val earthBookFreeSpells = mutableListOf<FreeSpell>()
    private val waterBookFreeSpells = mutableListOf<FreeSpell>()
    private val fireBookFreeSpells = mutableListOf<FreeSpell>()
    private val essenceBookFreeSpells = mutableListOf<FreeSpell>()
    private val illusionBookFreeSpells = mutableListOf<FreeSpell>()
    private val necromancyBookFreeSpells = mutableListOf<FreeSpell>()

    //initialize list of individually acquired spells
    val individualSpells = mutableListOf<Spell>()
    val spellList = mutableListOf<Spell>()

    val naturalPaths = mutableListOf<Element>()
    var magicTies = false

    /**
     * Get the character's base Zeon based on the character's Power
     */
    fun setBaseZeon() {
        //determine the base Zeon
        baseZeon =
            if(charInstance.totalPOW == 1)
                5
            else
                20 + (10 * charInstance.totalPOW) + charInstance.modPOW

        //recalculate the character's maximum Zeon
        calcMaxZeon()
    }

    /**
     * The user attempts to buy the given amount of Zeon
     *
     * toBuy: the number of Zeon points bought for the character
     */
    fun buyZeon(toBuy: Int){
        //change zeon bought and update appropriate figures
        boughtZeon = toBuy
        calcMaxZeon()
        charInstance.updateTotalSpent()
    }

    /**
     * Recalculate the character's maximum Zeon
     */
    fun calcMaxZeon(){
        zeonMax = baseZeon + (boughtZeon * 5) + (charInstance.lvl * charInstance.ownClass.zeonPerLevel)
    }

    /**
     * Get the character's base zeon accumulation based on the character's Power
     */
    fun setBaseZeonAcc() {
        //determine the base accumulation
        baseZeonAcc = when(charInstance.totalPOW){
            in 5..7 -> 5
            in 8 .. 11 -> 10
            in 12..14 -> 15
            15 -> 20
            16, 17 -> 25
            18, 19 -> 30
            20 -> 35
            else -> 0
        }

        //recalculate accumulation total
        calcZeonAcc()
    }

    /**
     * The user attempts to buy the given amount of accumulation
     *
     * toBuy: the accumulation amount bought for the character
     */
    fun buyZeonAcc(toBuy: Int){
        //change the accumulation bought and update the appropriate figures
        zeonAccMult = toBuy
        calcZeonAcc()
        charInstance.updateTotalSpent()
    }

    /**
     * Recalculate the character's Zeon accumulation
     */
    private fun calcZeonAcc(){
        zeonAccTotal = baseZeonAcc * zeonAccMult
        calcZeonRecovery()
    }

    fun changeRecoveryMult(input: Double){
        magicRecoveryMult = input
        calcZeonRecovery()
    }

    private fun calcZeonRecovery(){
        magicRecoveryTotal = (zeonAccTotal * magicRecoveryMult).toInt()
    }

    /**
     * The user attempts to buy the given amount of Magic Projection
     *
     * toBuy: the projection amount bought for the character
     */
    fun buyMagProj(toBuy: Int){
        boughtMagProjection = toBuy
        calcMagProj()
    }

    /**
     * Recalculate the character's Magic Projection stat
     */
    fun calcMagProj(){
        magProjTotal = charInstance.modDEX + boughtMagProjection
    }

    /**
     * Determine the character's Magic Level based on their intelligence
     */
    fun setMagicLevelMax(){
        magicLevelMax = when(charInstance.totalINT) {
            in 6..10 -> (charInstance.totalINT - 5) * 10
            11 -> 75
            12 -> 100
            13 -> 150
            in 14..20 -> (charInstance.totalINT - 12)* 100
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
        if(!primaryElementList.contains(book) && !oppositeElementFound(book) && spent != 0)
            primaryElementList.add(book)

        //attempt to set opposite as primary if spent value is 0 and there previously was a value
        else if (getElementInvestment(book) == 0 && primaryElementList.contains(book))
            setOppositeAsPrimary(book)

        val cap = spent + if(naturalPaths.contains(book)) 40 else 0

        //remove individually bought spells obtained in this purchase
        individualSpells.removeIf{it.inBook == book && it.level <= cap}
        individualSpells.removeIf{it is FreeSpell && findFreeSpellElement(it) == book && it.level <= cap}

        //update full spell list
        updateSpellList()
    }

    /**
     * Add a Free Spell to the character's indicated spell category
     *
     * addItem: spell granted to the character
     * intoElement: element the spell is bought under
     */
    fun addFreeSpell(addItem: FreeSpell, intoElement: Element){
        //get the element list to add the spell to
        val addToList = getElementFreeSpells(intoElement)

        //look for a free spell of the same level in this element
        addToList.forEach{
            //replace it if found
            if(it.level == addItem.level) {
                addToList[addToList.indexOf(it)] = addItem
                updateSpellList()
                return
            }
        }

        //add to list if none found
        addToList.add(addItem)
        updateSpellList()
    }

    /**
     * Attempts to alter the primary status of the inputted element's book
     *
     * primeElement: element to add or remove
     * changeTo: true if making element primary; false if removing it
     */
    fun changePrimaryBook(primeElement: Element, changeTo: Boolean){
        //if adding primary element and character has investment in it
        if(getElementInvestment(primeElement) != 0 && changeTo) {
            //if primary list contains necromancy
            if(primaryElementList.contains(Element.Necromancy)) {
                //remove necromancy and perform change action from that
                primaryElementList.remove(Element.Necromancy)
                changeFromNecromancy(primeElement)
            }
            else {
                //remove any present opposing element to the input
                primaryElementList.removeAll(getOppositeElement(primeElement))

                //add element to the list
                primaryElementList.add(primeElement)
            }
        }

        //if removing, do so and add opposing element if doing to is required
        else if(!changeTo && oppositeInvestedIn(primeElement))
            setOppositeAsPrimary(primeElement)

        //update spells character has access to
        updateSpellList()
    }

    /**
     * Re-evaluates what spells the character has access to
     */
    fun updateSpellList(){
        //clear magic level spent and list of usable spells
        magicLevelSpent = 0
        spellList.clear()

        //get spells acquired from investment in books
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

        //get spells acquired from individual purchases
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

    /**
     * Adds spells to the character's available list from points invested in the element's book
     *
     * pointValue: points invested in the book
     * inputElement: element of the book invested in
     * spellBook: list of spells to reference
     */
    private fun addSpellsFromBook(
        pointValue: Int,
        inputElement: Element,
        spellBook: List<Spell?>
    ){
        var startIndex = 0

        if(naturalPaths.contains(inputElement)){
            for(index in 0 until 20) {
                if (spellBook[index] != null)
                    spellList.add(spellBook[index]!!)
                else
                    spellList.add(getFreeSpell((index + 1) * 2, inputElement))
            }

            startIndex = 20
        }

        //add spent points value to cumulative value
        magicLevelSpent += pointValue

        //determine how many spells are bought depending on if opposite element is primary
        val spellRange =
            if (!oppositeElementFound(inputElement))
                (pointValue / 2)
            else
                (pointValue / 4)

        //if at least 1 spell gained from spent points
        if(spellRange > 0) {
            if(spellRange + startIndex <= 50) {
                //add indicated spells to the full list
                for (index in startIndex until spellRange + startIndex) {
                    if (spellBook[index] != null)
                        spellList.add(spellBook[index]!!)
                    else
                        spellList.add(getFreeSpell((index + 1) * 2, inputElement))
                }
            }
            else{
                for(index in startIndex until 50)
                    if (spellBook[index] != null)
                        spellList.add(spellBook[index]!!)
                    else
                        spellList.add(getFreeSpell((index + 1) * 2, inputElement))
            }
        }
    }

    /**
     * Retrieves the character's free spell of the given element and level if a choice has been made
     *
     * inputLevel: level of the free spell
     * inputElement: associated element of the free spell
     */
    fun getFreeSpell(inputLevel: Int, inputElement: Element): FreeSpell{
        //check through element's free spells for appropriate spell
        getElementFreeSpells(inputElement).forEach {
            if (it.level == inputLevel)
                return it
        }

        //return a placeholder for no valid spell found
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

    /**
     * Attempt to add or remove an individual spell
     *
     * targetSpell: spell to add or remove from character
     * isBought: whether spell is being bought or removed
     */
    fun changeIndividualSpell(targetSpell: Spell, isBought: Boolean): Boolean{
        if(magicTies)
            return false

        //if spell is being bought
        return if(isBought){
            //check that spell isn't already owned from book investment
            if(!spellList.contains(targetSpell)){
                //add spell to individual list
                individualSpells.add(targetSpell)

                //add primary element if able to now
                if(!primaryElementList.contains(targetSpell.inBook) && !oppositeElementFound(targetSpell.inBook))
                    primaryElementList.add(targetSpell.inBook)

                //update spell list
                updateSpellList()

                //indicate that spell has been acquired
                true
            }

            //do nothing if spell already owned
            else
                false
        }

        //if spell is being removed
        else{
            //remove individual spell
            individualSpells.remove(targetSpell)

            //remove primary element if no more investment in it
            if(getElementInvestment(targetSpell.inBook) == 0)
                setOppositeAsPrimary(targetSpell.inBook)

            //update full spell list
            updateSpellList()

            //indicate spell's removal
            false
        }
    }

    /**
     * Attempt to add or remove the indicated free spell
     *
     * levelInput: level of the target free spell
     * elementInput: associated element of the target free spell
     * isBought: true if acquiring the spell; false if removing it
     */
    fun changeIndividualFreeSpell(levelInput: Int, elementInput: Element, isBought: Boolean): Boolean{
        if(magicTies)
            return false

        //determine spell held by the character
        val spellItem = getFreeSpell(levelInput, elementInput)

        //if acquiring the free spell
        return if(isBought) {
            //check if character has an equivalent spell already
            if(!hasCopyOf(spellItem)) {
                //add individual spell to the list
                individualSpells.add(spellItem)

                //add element to primary list if able to
                if(!primaryElementList.contains(elementInput) && !oppositeElementFound(elementInput))
                    primaryElementList.add(elementInput)

                //update character's spells
                updateSpellList()

                //indicate spells acquisition
                true
            }

            //do nothing if equivalent spell is present
            else
                false
        }

        //if removing free spell
        else{
            //remove the appropriate spell from the list
            individualSpells.forEach{
                if(it.name == "PlaceHolder" && it.level == levelInput && findFreeSpellElement(it as FreeSpell) == elementInput){
                    individualSpells.remove(it)
                }
            }

            //remove the item from the element free spell list
            getElementFreeSpells(elementInput).remove(spellItem)

            //remove primary element if required
            if(getElementInvestment(elementInput) == 0)
                    setOppositeAsPrimary(elementInput)

            //update character's spell list
            updateSpellList()

            //indicate removal of spell
            false
        }
    }

    /**
     * Retrieves the cost of a spell based on its level and element
     *
     * inputLevel: level of the spell
     * inputElement: spell's associated element
     */
    private fun getIndividualCost(inputLevel: Int, inputElement: Element): Int{
        //initialize cost value
        var cost = (ceil(inputLevel.toDouble()/10.0).toInt() * 2)

        //double if opposing element is primary
        if(oppositeElementFound(inputElement))
            cost *= 2

        //return final cost value
        return cost
    }

    /**
     * Determines if any one of the given element's opposites are a primary element
     *
     * element: Element to check for opposite
     */
    private fun oppositeElementFound(element: Element): Boolean{
        //return true if one of the opposite elements is found
        getOppositeElement(element).forEach {
            if (primaryElementList.contains(it))
                return true
        }

        //notify of no opposing element found
        return false
    }

    /**
     * Determines if any points are invested in any of the given element's opposites
     *
     * element: Element to check for opposite
     */
    private fun oppositeInvestedIn(element: Element): Boolean{
        //return true if any points found in opposite
        getOppositeElement(element).forEach{
            if(getElementInvestment(it) > 0)
                return true
        }

        //notify of no opposing element found
        return false
    }

    /**
     * Determine the points from individual spells of the indicated element
     *
     * inputElement: element to count for individual spells
     */
    private fun getIndividualPoints(inputElement: Element): Int{
        //initialize sum
        var elementTotal = 0

        //for each individual spell
        individualSpells.forEach{
            //determine if free spell is of this element and add its value if it matches
            if(it is FreeSpell && findFreeSpellElement(it) == inputElement)
                elementTotal += getIndividualCost(it.level, inputElement)

            //add spell costs for matching elements
            else if(it.inBook == inputElement)
                elementTotal += getIndividualCost(it.level, inputElement)
        }

        //return final total
        return elementTotal
    }

    /**
     * Get total invested points in the given element
     *
     * inputElement: element to determine investment in
     */
    private fun getElementInvestment(inputElement: Element): Int{
        //return sum of individual spells bought and book point investment
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

    /**
     * Retrieve the associated element's free spells
     *
     * inputElement: element to find the free spells for
     */
    private fun getElementFreeSpells(inputElement: Element): MutableList<FreeSpell>{
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

    /**
     * Removes the given element from the primary element list and changes it to its opposite if able to
     *
     * primary: element to remove from the primary list
     */
    private fun setOppositeAsPrimary(primary: Element){
        //remove the element from the primary element list
        primaryElementList.remove(primary)

        //perform necromancy specific action if that is what is being removed
        if(primary == Element.Necromancy)
            changeFromNecromancy(null)

        else{
            //look through element's opposites
            getOppositeElement(primary).forEach{
                //add element to primary list if invested in and no opposite element of it found
                if(getElementInvestment(it) > 0 && !oppositeElementFound(it)) {
                    if(!primaryElementList.contains(it))
                        primaryElementList.add(it)
                    return
                }
            }
        }
    }

    /**
     * Alters the primary elements list after removing the necromancy element
     *
     * exception: element to always set as primary if one is given
     */
    private fun changeFromNecromancy(exception: Element?){

        //set higher of element pairs unless they are the exception
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

        //set the exception as a primary element
        if(exception != null)
            primaryElementList.add(exception)
    }

    /**
     * Sets the higher of the two given elements as a primary element
     *
     * first: one of the elements to compare
     * second: the other compared element
     */
    private fun pickGreater(first: Element, second: Element){
        //retrieve elements' invested amounts
        val firstInvestment = getElementInvestment(first)
        val secondInvestment = getElementInvestment(second)

        //as long as one element has some points investment in it
        if(firstInvestment + secondInvestment != 0) {
            //add more invested in element as a primary element
            if (firstInvestment >= secondInvestment)
                primaryElementList.add(first)
            else
                primaryElementList.add(second)
        }
    }

    /**
     * Retrieves all opposite elements to the given one
     *
     * inputElement: element to find the opposite of
     */
    private fun getOppositeElement(inputElement: Element): List<Element>{
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

    /**
     * Determines the element associated with the inputted free spell
     *
     * find: free spell to identify element of
     */
    fun findFreeSpellElement(find: FreeSpell): Element{
        //search through each list if it contains this spell
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

        //free spell is place holder and has associated element in its own data
        return find.forbiddenElements[0]
    }

    /**
     * Determines if the character possesses the given spell or a free spell of equivalent element
     * and level
     *
     * check: spell to identify in the spell list
     */
    fun hasCopyOf(check: Spell): Boolean{
        spellList.forEach{
            //return true if input is a placeholder of identical element and level
            if(check is FreeSpell && it is FreeSpell && check.name == "PlaceHolder"){
                if(it.level == check.level && findFreeSpellElement(it) == findFreeSpellElement(check))
                    return true
            }

            //check for identical spell name
            else if(it.name == check.name)
                return true
        }

        //no matching spell found
        return false
    }


    /**
     * Determines development points spent in this section of the character build
     */
    fun calculateSpent(): Int{
        return (boughtZeon * charInstance.ownClass.zeonGrowth) +
                ((zeonAccMult - 1) * charInstance.ownClass.maGrowth) +
                (boughtMagProjection * charInstance.ownClass.maProjGrowth)
    }

    fun loadMagic(fileReader: BufferedReader){
        boughtZeon = fileReader.readLine().toInt()
        zeonAccMult = fileReader.readLine().toInt()
        boughtMagProjection = fileReader.readLine().toInt()
        magProjImbalance = fileReader.readLine().toInt()

        imbalanceIsAttack = fileReader.readLine().toBoolean()
        calcMagProj()

        for(index in 0 until fileReader.readLine().toInt()){
            primaryElementList.add(Element.fromString(fileReader.readLine()))
        }

        pointsInLightBook = fileReader.readLine().toInt()
        pointsInDarkBook = fileReader.readLine().toInt()
        pointsInCreateBook = fileReader.readLine().toInt()
        pointsInDestructBook = fileReader.readLine().toInt()
        pointsInAirBook = fileReader.readLine().toInt()
        pointsInEarthBook = fileReader.readLine().toInt()
        pointsInWaterBook = fileReader.readLine().toInt()
        pointsInFireBook = fileReader.readLine().toInt()
        pointsInEssenceBook = fileReader.readLine().toInt()
        pointsInIllusionBook = fileReader.readLine().toInt()
        pointsInNecroBook = fileReader.readLine().toInt()

        for(index in 0 until fileReader.readLine().toInt()){
            val spellName = fileReader.readLine()

            if(spellName == "PlaceHolder"){
                individualSpells.add(FreeSpell(
                    "PlaceHolder",
                    false,
                    fileReader.readLine().toInt(),
                    0,
                    "This is not a real spell. This is a placeholder object.",
                    "This is not a spell. You can't add more Zeon to it.",
                    0,
                    null,
                    false,
                    listOf(),
                    listOf(Element.fromString(fileReader.readLine()))
                ))
            }
            else{
                val searchList = when(Element.fromString(fileReader.readLine())){
                    Element.Light -> lightBook.fullBook
                    Element.Dark -> darkBook.fullBook
                    Element.Creation -> creationBook.fullBook
                    Element.Destruction -> destructionBook.fullBook
                    Element.Air -> airBook.fullBook
                    Element.Earth -> earthBook.fullBook
                    Element.Water -> waterBook.fullBook
                    Element.Fire -> fireBook.fullBook
                    Element.Essence -> essenceBook.fullBook
                    Element.Illusion -> illusionBook.fullBook
                    Element.Necromancy -> necromancyBook.fullBook
                    else -> listOf()
                }

                searchList.forEach{
                    if(it != null && it.name == spellName)
                        individualSpells.add(it)
                }
            }
        }

        loadElementFreeSpells(lightBookFreeSpells, fileReader)
        loadElementFreeSpells(darkBookFreeSpells, fileReader)
        loadElementFreeSpells(creationBookFreeSpells, fileReader)
        loadElementFreeSpells(destructionBookFreeSpells, fileReader)
        loadElementFreeSpells(airBookFreeSpells, fileReader)
        loadElementFreeSpells(earthBookFreeSpells, fileReader)
        loadElementFreeSpells(waterBookFreeSpells, fileReader)
        loadElementFreeSpells(fireBookFreeSpells, fileReader)
        loadElementFreeSpells(essenceBookFreeSpells, fileReader)
        loadElementFreeSpells(illusionBookFreeSpells, fileReader)
        loadElementFreeSpells(necromancyBookFreeSpells, fileReader)

        updateSpellList()
    }

    fun writeMagic(){
        charInstance.addNewData(boughtZeon)
        charInstance.addNewData(zeonAccMult)
        charInstance.addNewData(boughtMagProjection)
        charInstance.addNewData(magProjImbalance)
        charInstance.addNewData(imbalanceIsAttack.toString())

        charInstance.addNewData(primaryElementList.size)
        primaryElementList.forEach{
            charInstance.addNewData(it.name)
        }

        charInstance.addNewData(pointsInLightBook)
        charInstance.addNewData(pointsInDarkBook)
        charInstance.addNewData(pointsInCreateBook)
        charInstance.addNewData(pointsInDestructBook)
        charInstance.addNewData(pointsInAirBook)
        charInstance.addNewData(pointsInEarthBook)
        charInstance.addNewData(pointsInWaterBook)
        charInstance.addNewData(pointsInFireBook)
        charInstance.addNewData(pointsInEssenceBook)
        charInstance.addNewData(pointsInIllusionBook)
        charInstance.addNewData(pointsInNecroBook)

        charInstance.addNewData(individualSpells.size)
        individualSpells.forEach{
            charInstance.addNewData(it.name)
            if(it is FreeSpell){
                charInstance.addNewData(it.level)
                charInstance.addNewData(findFreeSpellElement(it).name)
            }
            else
                charInstance.addNewData(it.inBook.name)
        }

        saveElementFreeSpells(lightBookFreeSpells)
        saveElementFreeSpells(darkBookFreeSpells)
        saveElementFreeSpells(creationBookFreeSpells)
        saveElementFreeSpells(destructionBookFreeSpells)
        saveElementFreeSpells(airBookFreeSpells)
        saveElementFreeSpells(earthBookFreeSpells)
        saveElementFreeSpells(waterBookFreeSpells)
        saveElementFreeSpells(fireBookFreeSpells)
        saveElementFreeSpells(essenceBookFreeSpells)
        saveElementFreeSpells(illusionBookFreeSpells)
        saveElementFreeSpells(necromancyBookFreeSpells)
    }

    private fun loadElementFreeSpells(loadTo: MutableList<FreeSpell>, fileReader: BufferedReader){
        for(index in 0 until fileReader.readLine().toInt()){
            val searchLevel = fileReader.readLine().toInt()
            val searchName = fileReader.readLine()

            val searchList = when(searchLevel){
                4, 8 -> freeBook.firstBook
                14, 18 -> freeBook.secondBook
                24, 28 -> freeBook.thirdBook
                34, 38 -> freeBook.fourthBook
                44, 48 -> freeBook.fifthBook
                54, 58 -> freeBook.sixthBook
                64, 68 -> freeBook.seventhBook
                74, 78 -> freeBook.eighthBook
                84, 88 -> freeBook.ninthBook
                94, 98 -> freeBook.tenthBook
                else -> listOf()
            }

            searchList.forEach{
                if(it.name == searchName)
                    loadTo.add(FreeSpell(
                        it.name,
                        it.isActive,
                        searchLevel,
                        it.zCost,
                        it.effect,
                        it.addedEffect,
                        it.zMax,
                        it.maintenance,
                        it.isDaily,
                        it.type,
                        it.forbiddenElements
                    ))
            }
        }
    }

    private fun saveElementFreeSpells(saveItem: MutableList<FreeSpell>){
        charInstance.addNewData(saveItem.size)

        saveItem.forEach{
            charInstance.addNewData(it.level)
            charInstance.addNewData(it.name)
        }
    }
}