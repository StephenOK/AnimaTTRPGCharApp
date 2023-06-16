package com.paetus.animaCharCreator.character_creation.attributes.magic

import androidx.compose.runtime.mutableStateOf
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.character_creation.Element
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.FreeSpell
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.Spell
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook.*
import java.io.BufferedReader
import kotlin.math.ceil

/**
 * Component that holds the character's magical abilities.
 * Holds data for maximum Zeon, Zeon accumulation, and magic projection.
 * Holds data for the spells available to the character.
 *
 * @param charInstance object that holds all of the character's data
 */
class Magic(private val charInstance: BaseCharacter){
    //initialize base Zeon points
    val baseZeon = mutableStateOf(0)

    //initialize bought Zeon points
    val boughtZeon = mutableStateOf(0)

    //initialize Zeon points per level
    val zeonPerLevel = mutableStateOf(0)

    //initialize Zeon points from class levels
    val zeonFromClass = mutableStateOf(0)

    //initialize total Zeon points
    val zeonMax = mutableStateOf(0)

    //initialize base Zeon accumulation
    val baseZeonAcc = mutableStateOf(0)

    //initialize zeon accumulation multiple
    val zeonAccMult = mutableStateOf(1)

    //initialize zeon accumulation total
    val zeonAccTotal = mutableStateOf(0)

    //initialize zeon recovery multiplier
    val magicRecoveryMult = mutableStateOf(1.0)

    //initialize zeon recovery total
    val magicRecoveryTotal = mutableStateOf(0)

    //initialize character's innate magic level
    val innateMagic = mutableStateOf(0)

    //initialize bought magic projection
    val boughtMagProjection = mutableStateOf(0)

    //initialize magic projection total
    val magProjTotal = mutableStateOf(0)

    //initialize magic projection imbalance
    val magProjImbalance = mutableStateOf(0)

    //initialize whether imbalance is to offensive spells
    val imbalanceIsAttack = mutableStateOf(true)

    //initialize maximum magic level
    val magicLevelMax = mutableStateOf(0)

    //initialize magic levels spent
    val magicLevelSpent = mutableStateOf(0)

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

    //initialize list of natural paths taken
    val naturalPaths = mutableListOf<Element>()

    //initialize points invested in each book
    val pointsInLightBook = mutableStateOf(0)
    val pointsInDarkBook = mutableStateOf(0)
    val pointsInCreateBook = mutableStateOf(0)
    val pointsInDestructBook = mutableStateOf(0)
    val pointsInAirBook = mutableStateOf(0)
    val pointsInEarthBook = mutableStateOf(0)
    val pointsInWaterBook = mutableStateOf(0)
    val pointsInFireBook = mutableStateOf(0)
    val pointsInEssenceBook = mutableStateOf(0)
    val pointsInIllusionBook = mutableStateOf(0)
    val pointsInNecroBook = mutableStateOf(0)

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

    //initialize full spell list
    val spellList = mutableListOf<Spell>()

    //initialize if magic ties has been taken
    val magicTies = mutableStateOf(false)

    /**
     * Get the character's base Zeon based on the character's Power.
     */
    fun setBaseZeon() {
        //determine the base Zeon
        baseZeon.value =
            if(charInstance.primaryList.pow.total.value == 1)
                5
            else
                20 + (10 * charInstance.primaryList.pow.total.value) + charInstance.primaryList.pow.outputMod.value

        //recalculate the character's maximum Zeon
        calcMaxZeon()
    }

    /**
     * Set the number of Zeon points the character has bought.
     *
     * @param toBuy the number of Zeon points bought for the character
     */
    fun buyZeon(toBuy: Int){
        //change zeon bought and update appropriate figures
        boughtZeon.value = toBuy
        calcMaxZeon()
        charInstance.updateTotalSpent()
    }

    /**
     * Set the number of Zeon points the character gains per level.
     *
     * @param amount number to set the points per level value to
     */
    @JvmName("setZeonPerLevel1")
    fun setZeonPerLevel(amount: Int){
        zeonPerLevel.value = amount
        updateZeonFromClass()
    }

    /**
     * Update the total number of Zeon points gained from levels.
     */
    fun updateZeonFromClass(){
        zeonFromClass.value =
            if(charInstance.lvl.value != 0) zeonPerLevel.value * charInstance.lvl.value
            else zeonPerLevel.value/2

        calcMaxZeon()
    }

    /**
     * Recalculate the character's maximum Zeon.
     */
    fun calcMaxZeon(){
        zeonMax.value = baseZeon.value + (boughtZeon.value * 5) + zeonFromClass.value
    }

    /**
     * Get the character's base zeon accumulation based on the character's Power
     */
    fun setBaseZeonAcc() {
        //determine the base accumulation
        baseZeonAcc.value = when(charInstance.primaryList.pow.total.value){
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
     * The user attempts to buy the given amount of accumulation.
     *
     * @param toBuy the accumulation amount bought for the character
     */
    fun buyZeonAcc(toBuy: Int){
        //change the accumulation bought and update the appropriate figures
        zeonAccMult.value = toBuy
        charInstance.updateTotalSpent()
        calcZeonAcc()
    }

    /**
     * Recalculate the character's Zeon accumulation.
     */
    private fun calcZeonAcc(){
        zeonAccTotal.value = baseZeonAcc.value * zeonAccMult.value
        calcZeonRecovery()
        setInnateMagic()
    }

    /**
     * Set the character's Zeon recovery multiplier to the indicated value.
     *
     * @param input multiplier value to set
     */
    fun changeRecoveryMult(input: Double){
        magicRecoveryMult.value = input
        calcZeonRecovery()
    }

    /**
     * Reevaluate the zeon recovery rate.
     */
    private fun calcZeonRecovery(){
        magicRecoveryTotal.value = (zeonAccTotal.value * magicRecoveryMult.value).toInt()
    }

    /**
     * Sets the value for the minimum zeon cost of spells cast innately.
     */
    @JvmName("setInnateMagic1")
    private fun setInnateMagic(){
        innateMagic.value = when(zeonAccTotal.value){
            in 0..9 -> 0
            in 10 .. 50 -> 10
            in 51..70 -> 20
            in 71..90 -> 30
            in 91..110 -> 40
            in 111..130 -> 50
            in 131..150 -> 60
            in 151..180 -> 70
            in 181..200 -> 80
            else -> 90
        }
    }

    /**
     * Set the amount of magic projection bought by the user.
     *
     * @param toBuy the projection amount bought for the character
     */
    fun buyMagProj(toBuy: Int){
        boughtMagProjection.value = toBuy
        charInstance.updateTotalSpent()
        calcMagProj()
    }

    /**
     * Recalculate the character's Magic Projection stat.
     */
    fun calcMagProj(){
        magProjTotal.value = charInstance.primaryList.dex.outputMod.value + boughtMagProjection.value
    }

    /**
     * Determines if the bought amount of Magic Projection is a valid input.
     *
     * @return true if input is valid
     */
    fun getValidProjection(): Boolean{
        return boughtMagProjection.value * charInstance.ownClass.value.maProjGrowth <= charInstance.maxMagDP.value/2
    }

    /**
     * Determine the character's Magic Level based on their intelligence.
     */
    fun setMagicLevelMax(){
        magicLevelMax.value = when(charInstance.primaryList.int.total.value) {
            in 6..10 -> (charInstance.primaryList.int.total.value - 5) * 10
            11 -> 75
            12 -> 100
            13 -> 150
            in 14..20 -> (charInstance.primaryList.int.total.value - 12)* 100
            else -> 0
        }
    }

    /**
     * Attempts to alter the primary status of the inputted element's book.
     *
     * @param primeElement element to add or remove
     * @param changeTo true if making element primary; false if removing it
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
     * Determines if any points are invested in any of the given element's opposites.
     *
     * @param element Element to check for opposite
     * @return true if opposing element has any magic levels in it
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
     * Removes the given element from the primary element list and changes it to its opposite if able to.
     *
     * @param primary element to remove from the primary list
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
     * Alters the primary elements list after removing the necromancy element.
     *
     * @param exception element to always set as primary if one is given
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
     * Sets the higher of the two given elements as a primary element.
     *
     * @param first one of the elements to compare
     * @param second the other compared element
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
     * Determines if any one of the given element's opposites are a primary element.
     *
     * @param element Element to check for opposite
     * @return true if an opposing element has been found
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
     * Retrieves all opposite elements to the given one.
     *
     * @param inputElement element to find the opposite of
     * @return set of opposing elements to the input
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
     * Spend magic levels in the given element.
     *
     * @param spent amount of magic levels to spend
     * @param book which book to buy points in
     */
    fun buyBookLevels(spent: Int, book: Element){
        //adjust points spent depending on the inputted element
        when(book){
            Element.Light -> pointsInLightBook.value = spent
            Element.Dark -> pointsInDarkBook.value = spent
            Element.Creation -> pointsInCreateBook.value = spent
            Element.Destruction -> pointsInDestructBook.value = spent
            Element.Air -> pointsInAirBook.value = spent
            Element.Earth -> pointsInEarthBook.value = spent
            Element.Water -> pointsInWaterBook.value = spent
            Element.Fire -> pointsInFireBook.value = spent
            Element.Essence -> pointsInEssenceBook.value = spent
            Element.Illusion -> pointsInIllusionBook.value = spent
            Element.Necromancy -> pointsInNecroBook.value = spent
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
    }

    /**
     * Get total invested points in the given element.
     *
     * @param inputElement element to determine investment in
     * @return points invested in this element
     */
    private fun getElementInvestment(inputElement: Element): Int{
        //return sum of individual spells bought and book point investment
        return getIndividualPoints(inputElement) + when(inputElement){
            Element.Light -> pointsInLightBook.value
            Element.Dark -> pointsInDarkBook.value
            Element.Creation -> pointsInCreateBook.value
            Element.Destruction -> pointsInDestructBook.value
            Element.Air -> pointsInAirBook.value
            Element.Earth -> pointsInEarthBook.value
            Element.Water -> pointsInWaterBook.value
            Element.Fire -> pointsInFireBook.value
            Element.Essence -> pointsInEssenceBook.value
            Element.Illusion -> pointsInIllusionBook.value
            Element.Necromancy -> pointsInNecroBook.value
            else -> 0
        }
    }

    /**
     * Adds spells to the character's available list from points invested in the element's book.
     *
     * @param pointValue points invested in the book
     * @param inputElement element of the book invested in
     * @param spellBook list of spells to reference
     */
    private fun addSpellsFromBook(
        pointValue: Int,
        inputElement: Element,
        spellBook: List<Spell?>
    ){
        //set the starting index
        var startIndex = 0

        //apply natural path levels if applicable
        if(naturalPaths.contains(inputElement)){
            //add first 40 levels of spells
            for(index in 0 until 20) {
                if (spellBook[index] != null)
                    spellList.add(spellBook[index]!!)
                else
                    spellList.add(getFreeSpell((index + 1) * 2, inputElement))
            }

            //set new starting index
            startIndex = 20
        }

        //add spent points value to cumulative value
        magicLevelSpent.value += pointValue

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

            //add all spells from list if list would be exceeded
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
     * Retrieves the character's free spell of the given element and level if a choice has been made.
     *
     * @param inputLevel level of the free spell
     * @param inputElement associated element of the free spell
     * @return free spell that meets the input conditions
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
     * Retrieve the associated element's free spells.
     *
     * @param inputElement element to find the free spells for
     * @return list of free spells with the associated element
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
     * Determines the element associated with the inputted free spell.
     *
     * @param find free spell to identify element of
     * @return element associated with the inputted free spell
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
     * Add a Free Spell to the character's indicated spell category.
     *
     * @param addItem spell granted to the character
     * @param intoElement element the spell is bought under
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
     * Attempt to add or remove an individual spell.
     *
     * @param targetSpell spell to add or remove from character
     * @param isBought whether spell is being bought or removed
     */
    fun changeIndividualSpell(targetSpell: Spell, isBought: Boolean){
        //cease action if character has Magic Ties disadvantage
        if(magicTies.value)
            return

        //if spell is being bought
        if(isBought){
            //check that spell isn't already owned from book investment
            if(!spellList.contains(targetSpell)){
                //add spell to individual list
                individualSpells.add(targetSpell)

                //add primary element if able to now
                if(!primaryElementList.contains(targetSpell.inBook) && !oppositeElementFound(targetSpell.inBook))
                    primaryElementList.add(targetSpell.inBook)
            }
        }

        //if spell is being removed
        else{
            //remove individual spell
            individualSpells.remove(targetSpell)

            //remove primary element if no more investment in it
            if(getElementInvestment(targetSpell.inBook) == 0)
                setOppositeAsPrimary(targetSpell.inBook)
        }

        updateSpellList()
    }

    /**
     * Attempt to add or remove the indicated free spell.
     *
     * @param levelInput level of the target free spell
     * @param elementInput associated element of the target free spell
     * @param isBought true if acquiring the spell; false if removing it
     */
    fun changeIndividualFreeSpell(levelInput: Int, elementInput: Element, isBought: Boolean){
        //terminate process if Magic Ties disadvantage is taken
        if(magicTies.value)
            return

        //determine spell held by the character
        val spellItem = getFreeSpell(levelInput, elementInput)

        //if acquiring the free spell
        if(isBought) {
            //check if character has an equivalent spell already
            if(!hasCopyOf(spellItem)) {
                //add individual spell to the list
                individualSpells.add(spellItem)

                //add element to primary list if able to
                if(!primaryElementList.contains(elementInput) && !oppositeElementFound(elementInput))
                    primaryElementList.add(elementInput)
            }
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
        }

        updateSpellList()
    }

    /**
     * Determine the points from individual spells of the indicated element.
     *
     * @param inputElement element to count for individual spells
     * @return points invested in individual spells
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
     * Retrieves the cost of a spell based on its level and element.
     *
     * @param inputLevel level of the spell
     * @param inputElement spell's associated element
     * @return the magic levels needed to acquire a spell with these values
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
     * Re-evaluates what spells the character has access to.
     */
    fun updateSpellList(){
        //clear magic level spent and list of usable spells
        magicLevelSpent.value = 0
        spellList.clear()

        //get spells acquired from investment in books
        addSpellsFromBook(pointsInLightBook.value, Element.Light, lightBook.fullBook)
        addSpellsFromBook(pointsInDarkBook.value, Element.Dark, darkBook.fullBook)
        addSpellsFromBook(pointsInCreateBook.value, Element.Creation, creationBook.fullBook)
        addSpellsFromBook(pointsInDestructBook.value, Element.Destruction, destructionBook.fullBook)
        addSpellsFromBook(pointsInAirBook.value, Element.Air, airBook.fullBook)
        addSpellsFromBook(pointsInEarthBook.value, Element.Earth, earthBook.fullBook)
        addSpellsFromBook(pointsInWaterBook.value, Element.Water, waterBook.fullBook)
        addSpellsFromBook(pointsInFireBook.value, Element.Fire, fireBook.fullBook)
        addSpellsFromBook(pointsInEssenceBook.value, Element.Essence, essenceBook.fullBook)
        addSpellsFromBook(pointsInIllusionBook.value, Element.Illusion, illusionBook.fullBook)
        addSpellsFromBook(pointsInNecroBook.value, Element.Necromancy, necromancyBook.fullBook)

        //get spells acquired from individual purchases
        individualSpells.forEach{
            if(it is FreeSpell) {
                magicLevelSpent.value += getIndividualCost(it.level, findFreeSpellElement(it))
                spellList.add(getFreeSpell(it.level, findFreeSpellElement(it)))
            }
            else {
                magicLevelSpent.value += getIndividualCost(it.level, it.inBook)
                spellList.add(it)
            }
        }
    }

    /**
     * Determines if the character possesses the given spell or a free spell of equivalent element
     * and level.
     *
     * @param check spell to identify in the spell list
     * @return true if inputted spell has been found
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
     * Determines if a free spell of identical level and element is taken by the character.
     *
     * @param level desired level of the search
     * @param type desired element of the search
     * @return true if matching items found in a spell
     */
    fun hasIndividualFreeCopyOf(level: Int, type: Element): Boolean{
        individualSpells.forEach{
            if(it is FreeSpell){
                if(it.level == level && findFreeSpellElement(it) == type)
                    return true
            }
        }

        return false
    }

    /**
     * Set the taken state of the Magic Ties disadvantage.
     *
     * @param input taken state of the Magic Ties disadvantage
     */
    @JvmName("setMagicTies1")
    fun setMagicTies(input: Boolean){
        if(input){
            //clear any existing free spells
            lightBookFreeSpells.clear()
            darkBookFreeSpells.clear()
            creationBookFreeSpells.clear()
            destructionBookFreeSpells.clear()
            airBookFreeSpells.clear()
            earthBookFreeSpells.clear()
            waterBookFreeSpells.clear()
            fireBookFreeSpells.clear()
            essenceBookFreeSpells.clear()
            illusionBookFreeSpells.clear()
            necromancyBookFreeSpells.clear()

            //clear individual spells obtained by the character
            while(individualSpells.size > 0){
                changeIndividualSpell(individualSpells[0], false)
            }
        }

        //set magic ties value
        magicTies.value = input
    }

    /**
     * Function to run on confirmed removal of The Gift advantage.
     */
    fun loseMagic(){
        //reset original zeon values
        buyZeon(0)
        buyZeonAcc(1)
        buyMagProj(0)

        //clear spellbook
        individualSpells.clear()
        buyBookLevels(0, Element.Light)
        buyBookLevels(0, Element.Dark)
        buyBookLevels(0, Element.Creation)
        buyBookLevels(0, Element.Destruction)
        buyBookLevels(0, Element.Air)
        buyBookLevels(0, Element.Earth)
        buyBookLevels(0, Element.Water)
        buyBookLevels(0, Element.Fire)
        buyBookLevels(0, Element.Essence)
        buyBookLevels(0, Element.Illusion)
        buyBookLevels(0, Element.Necromancy)

        lightBookFreeSpells.clear()
        darkBookFreeSpells.clear()
        creationBookFreeSpells.clear()
        destructionBookFreeSpells.clear()
        airBookFreeSpells.clear()
        earthBookFreeSpells.clear()
        waterBookFreeSpells.clear()
        fireBookFreeSpells.clear()
        essenceBookFreeSpells.clear()
        illusionBookFreeSpells.clear()
        necromancyBookFreeSpells.clear()

        updateSpellList()
    }


    /**
     * Determines development points spent in this section of the character build.
     */
    fun calculateSpent(): Int{
        return (boughtZeon.value * charInstance.ownClass.value.zeonGrowth) +
                ((zeonAccMult.value - 1) * charInstance.ownClass.value.maGrowth) +
                (boughtMagProjection.value * charInstance.ownClass.value.maProjGrowth)
    }

    /**
     * Load data for this section from the inputted file.
     *
     * @param fileReader file to retrieve the data from
     */
    fun loadMagic(fileReader: BufferedReader){
        //get character's purchased zeon
        boughtZeon.value = fileReader.readLine().toInt()

        //get zeon accumulation multiple
        zeonAccMult.value = fileReader.readLine().toInt()

        //get magic projection bought
        boughtMagProjection.value = fileReader.readLine().toInt()

        //get projection imbalance value
        magProjImbalance.value = fileReader.readLine().toInt()

        //get projection imbalance bias
        imbalanceIsAttack.value = fileReader.readLine().toBoolean()
        calcMagProj()

        //get each saved primary element
        for(index in 0 until fileReader.readLine().toInt()){
            primaryElementList.add(Element.fromString(fileReader.readLine()))
        }

        //get all points invested in each element book
        pointsInLightBook.value = fileReader.readLine().toInt()
        pointsInDarkBook.value = fileReader.readLine().toInt()
        pointsInCreateBook.value = fileReader.readLine().toInt()
        pointsInDestructBook.value = fileReader.readLine().toInt()
        pointsInAirBook.value = fileReader.readLine().toInt()
        pointsInEarthBook.value = fileReader.readLine().toInt()
        pointsInWaterBook.value = fileReader.readLine().toInt()
        pointsInFireBook.value = fileReader.readLine().toInt()
        pointsInEssenceBook.value = fileReader.readLine().toInt()
        pointsInIllusionBook.value = fileReader.readLine().toInt()
        pointsInNecroBook.value = fileReader.readLine().toInt()

        //get each individual spell saved
        for(index in 0 until fileReader.readLine().toInt()){
            //get its name
            val spellName = fileReader.readLine()

            //construct placeholder spell if indicated by spellName
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

            //find the element spell that is saved
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

        //load free spells for each element
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

    /**
     * Write the data to file.
     */
    fun writeMagic(){
        //write bought zeon points
        charInstance.addNewData(boughtZeon.value)

        //write zeon accumulation multiple
        charInstance.addNewData(zeonAccMult.value)

        //write magic projection bought
        charInstance.addNewData(boughtMagProjection.value)

        //write projection imbalance
        charInstance.addNewData(magProjImbalance.value)

        //write imbalance bias
        charInstance.addNewData(imbalanceIsAttack.value.toString())

        //save primary elements and list size to file
        charInstance.addNewData(primaryElementList.size)
        primaryElementList.forEach{
            charInstance.addNewData(it.name)
        }

        //write all book levels to file
        charInstance.addNewData(pointsInLightBook.value)
        charInstance.addNewData(pointsInDarkBook.value)
        charInstance.addNewData(pointsInCreateBook.value)
        charInstance.addNewData(pointsInDestructBook.value)
        charInstance.addNewData(pointsInAirBook.value)
        charInstance.addNewData(pointsInEarthBook.value)
        charInstance.addNewData(pointsInWaterBook.value)
        charInstance.addNewData(pointsInFireBook.value)
        charInstance.addNewData(pointsInEssenceBook.value)
        charInstance.addNewData(pointsInIllusionBook.value)
        charInstance.addNewData(pointsInNecroBook.value)

        //write all individually bought spells to file
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

        //save data on free spell investment
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

    /**
     * Function to run when loading free spells for an element.
     *
     * @param loadTo free spell list to push data to
     * @param fileReader file to load data from
     */
    private fun loadElementFreeSpells(loadTo: MutableList<FreeSpell>, fileReader: BufferedReader){
        //for the indicated number of spells
        for(index in 0 until fileReader.readLine().toInt()){
            //get the spell's level and name
            val searchLevel = fileReader.readLine().toInt()
            val searchName = fileReader.readLine()

            //get the associated free spell book
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

            //search for the spell in the book
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

    /**
     * Write free spell data to file.
     *
     * @param saveItem list of free spells to write to file
     */
    private fun saveElementFreeSpells(saveItem: MutableList<FreeSpell>){
        //save the number of spells in the list
        charInstance.addNewData(saveItem.size)

        //write each spell's level and name
        saveItem.forEach{
            charInstance.addNewData(it.level)
            charInstance.addNewData(it.name)
        }
    }
}