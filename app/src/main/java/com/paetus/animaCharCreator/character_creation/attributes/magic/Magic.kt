package com.paetus.animaCharCreator.character_creation.attributes.magic

import android.os.Build
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.enumerations.Element
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.FreeSpell
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.Spell
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook.*
import com.paetus.animaCharCreator.writeDataTo
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
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
    val baseZeon = mutableIntStateOf(value = 0)

    //initialize bought Zeon points
    val boughtZeon = mutableIntStateOf(value = 0)

    //initialize Zeon points per level
    private val zeonPerLevel = mutableIntStateOf(value = 0)

    //initialize Zeon points from class levels
    val zeonFromClass = mutableIntStateOf(value = 0)

    //initialize total Zeon points
    val zeonMax = mutableIntStateOf(value = 0)

    //initialize base Zeon accumulation
    val baseZeonAcc = mutableIntStateOf(value = 0)

    //initialize zeon accumulation multiple
    val zeonAccMult = mutableIntStateOf(value = 1)

    //initialize zeon accumulation total
    val zeonAccTotal = mutableIntStateOf(value = 0)

    //initialize zeon recovery multiplier
    private val magicRecoveryMult = mutableDoubleStateOf(value = 1.0)

    //initialize zeon recovery total
    val magicRecoveryTotal = mutableIntStateOf(value = 0)

    //initialize character's innate magic level
    val innateMagic = mutableIntStateOf(value = 0)

    //initialize bought magic projection
    val boughtMagProjection = mutableIntStateOf(value = 0)

    //initialize magic projection total
    val magProjTotal = mutableIntStateOf(value = 0)

    //initialize magic projection imbalance
    val magProjImbalance = mutableIntStateOf(value = 0)

    //initialize whether imbalance is to offensive spells
    val imbalanceIsAttack = mutableStateOf(value = true)

    //initialize maximum magic level
    val magicLevelMax = mutableIntStateOf(value = 0)

    //initialize magic levels spent
    val magicLevelSpent = mutableIntStateOf(value = 0)

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
    val pointsInLightBook = mutableIntStateOf(value = 0)
    val pointsInDarkBook = mutableIntStateOf(value = 0)
    val pointsInCreateBook = mutableIntStateOf(value = 0)
    val pointsInDestructBook = mutableIntStateOf(value = 0)
    val pointsInAirBook = mutableIntStateOf(value = 0)
    val pointsInEarthBook = mutableIntStateOf(value = 0)
    val pointsInWaterBook = mutableIntStateOf(value = 0)
    val pointsInFireBook = mutableIntStateOf(value = 0)
    val pointsInEssenceBook = mutableIntStateOf(value = 0)
    val pointsInIllusionBook = mutableIntStateOf(value = 0)
    val pointsInNecroBook = mutableIntStateOf(value = 0)

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
    val magicTies = mutableStateOf(value = false)

    /**
     * Get the character's base Zeon based on the character's Power.
     */
    fun setBaseZeon() {
        //determine the base Zeon
        baseZeon.intValue =
            if(charInstance.primaryList.pow.total.intValue == 1)
                5
            else
                20 + (10 * charInstance.primaryList.pow.total.intValue) + charInstance.primaryList.pow.outputMod.intValue

        //recalculate the character's maximum Zeon
        calcMaxZeon()
    }

    /**
     * Set the number of Zeon points the character has bought.
     *
     * @param zeonBuy the number of Zeon points bought for the character
     */
    fun buyZeon(zeonBuy: Int){
        //change zeon bought and update appropriate figures
        boughtZeon.intValue = zeonBuy
        calcMaxZeon()
        charInstance.updateTotalSpent()
    }

    /**
     * Set the number of Zeon points the character gains per level.
     *
     * @param lvlBonus number to set the points per level value to
     */
    fun setZeonPerLevel(lvlBonus: Int){
        zeonPerLevel.intValue = lvlBonus
        updateZeonFromClass()
    }

    /**
     * Update the total number of Zeon points gained from levels.
     */
    fun updateZeonFromClass(){
        zeonFromClass.intValue =
            if(charInstance.lvl.intValue != 0) zeonPerLevel.intValue * charInstance.lvl.intValue
            else zeonPerLevel.intValue/2

        calcMaxZeon()
    }

    /**
     * Recalculate the character's maximum Zeon.
     */
    private fun calcMaxZeon(){
        zeonMax.intValue = baseZeon.intValue + (boughtZeon.intValue * 5) + zeonFromClass.intValue
    }

    /**
     * Get the character's base zeon accumulation based on the character's Power.
     */
    fun setBaseZeonAcc() {
        //determine the base accumulation
        baseZeonAcc.intValue = when(charInstance.primaryList.pow.total.intValue){
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
     * @param accBuy the accumulation amount bought for the character
     */
    fun buyZeonAcc(accBuy: Int){
        //change the accumulation bought and update the appropriate figures
        zeonAccMult.intValue = accBuy
        charInstance.updateTotalSpent()
        calcZeonAcc()
    }

    /**
     * Recalculate the character's Zeon accumulation.
     */
    private fun calcZeonAcc(){
        zeonAccTotal.intValue = baseZeonAcc.intValue * zeonAccMult.intValue
        calcZeonRecovery()
        setInnateMagic()
    }

    /**
     * Set the character's Zeon recovery multiplier to the indicated value.
     *
     * @param multValue multiplier value to set
     */
    fun changeRecoveryMult(multValue: Double){
        magicRecoveryMult.doubleValue = multValue
        calcZeonRecovery()
    }

    /**
     * Reevaluate the zeon recovery rate.
     */
    private fun calcZeonRecovery(){
        magicRecoveryTotal.intValue = (zeonAccTotal.intValue * magicRecoveryMult.doubleValue).toInt()
    }

    /**
     * Sets the value for the minimum zeon cost of spells cast innately.
     */
    private fun setInnateMagic(){
        innateMagic.intValue = when(zeonAccTotal.intValue){
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
     * @param projBuy the projection amount bought for the character
     */
    fun buyMagProj(projBuy: Int){
        boughtMagProjection.intValue = projBuy
        charInstance.updateTotalSpent()
        calcMagProj()
    }

    /**
     * Recalculate the character's Magic Projection stat.
     */
    fun calcMagProj(){
        magProjTotal.intValue = charInstance.primaryList.dex.outputMod.intValue + boughtMagProjection.intValue
    }

    /**
     * Determines if the bought amount of Magic Projection is a valid input.
     *
     * @return true if input is valid
     */
    fun getValidProjection(): Boolean{
        return boughtMagProjection.intValue * charInstance.ownClass.value.maProjGrowth <= charInstance.maxMagDP.intValue/2
    }

    /**
     * Determine the character's Magic Level based on their intelligence.
     */
    fun setMagicLevelMax(){
        magicLevelMax.intValue = when(charInstance.primaryList.int.total.intValue) {
            in 6..10 -> (charInstance.primaryList.int.total.intValue - 5) * 10
            11 -> 75
            12 -> 100
            13 -> 150
            in 14..20 -> (charInstance.primaryList.int.total.intValue - 12)* 100
            else -> 0
        }
    }

    /**
     * Attempts to alter the primary status of the inputted element's book.
     *
     * @param primeElement element to add or remove
     * @param isPrimary true if making element primary; false if removing it
     */
    fun changePrimaryBook(
        primeElement: Element,
        isPrimary: Boolean
    ){
        //if adding primary element and character has investment in it
        if(getElementInvestment(primeElement) != 0 && isPrimary) {
            //if primary list contains necromancy
            if(primaryElementList.contains(element = Element.Necromancy)) {
                //remove necromancy and perform change action from that
                primaryElementList.remove(element = Element.Necromancy)
                changeFromNecromancy(exception = primeElement)
            }
            else {
                //remove any present opposing element to the input
                primaryElementList.removeAll(elements = getOppositeElement(primeElement))

                //add element to the list
                primaryElementList.add(element = primeElement)
            }
        }

        //if removing, do so and add opposing element if doing so is required
        else if(!isPrimary && oppositeInvestedIn(element = primeElement))
            setOppositeAsPrimary(primary = primeElement)

        //update spells character has access to
        updateSpellList()
    }

    /**
     * Determines if any points are invested in any of the given element's opposites.
     *
     * @param element Element to check for opposite
     * @return true if opposing element has any magic levels in it
     */
    private fun oppositeInvestedIn(
        element: Element
    ): Boolean{
        //return true if any points found in opposite
        getOppositeElement(inputElement = element).forEach{opposingElement ->
            if(getElementInvestment(inputElement = opposingElement) > 0)
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
        primaryElementList.remove(element = primary)

        //perform necromancy specific action if that is what is being removed
        if(primary == Element.Necromancy)
            changeFromNecromancy(exception = null)

        else{
            //look through element's opposites
            getOppositeElement(inputElement = primary).forEach{opposingElement ->
                //add element to primary list if invested in and no opposite element of it found
                if(getElementInvestment(inputElement = opposingElement) > 0 &&
                    !oppositeElementFound(element = opposingElement)) {
                    if(!primaryElementList.contains(element = opposingElement))
                        primaryElementList.add(element = opposingElement)
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
            pickGreater(first = Element.Light, second = Element.Dark)
        if(exception != Element.Creation && exception != Element.Destruction)
            pickGreater(first = Element.Creation, second = Element.Destruction)
        if(exception != Element.Air && exception != Element.Earth)
            pickGreater(first = Element.Air, second = Element.Earth)
        if(exception != Element.Water && exception != Element.Fire)
            pickGreater(first = Element.Water, second = Element.Fire)
        if(exception != Element.Essence && exception != Element.Illusion)
            pickGreater(first = Element.Essence, second = Element.Illusion)

        //set the exception as a primary element
        if(exception != null)
            primaryElementList.add(element = exception)
    }

    /**
     * Sets the higher of the two given elements as a primary element.
     *
     * @param first one of the elements to compare
     * @param second the other compared element
     */
    private fun pickGreater(first: Element, second: Element){
        //retrieve elements' invested amounts
        val firstInvestment = getElementInvestment(inputElement = first)
        val secondInvestment = getElementInvestment(inputElement = second)

        //as long as one element has some points investment in it
        if(firstInvestment + secondInvestment != 0) {
            //add more invested in element as a primary element
            if (firstInvestment >= secondInvestment)
                primaryElementList.add(element = first)
            else
                primaryElementList.add(element = second)
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
        getOppositeElement(inputElement = element).forEach { opposingElement ->
            if (primaryElementList.contains(opposingElement))
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
    private fun getOppositeElement(
        inputElement: Element
    ): List<Element>{
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
            Element.Necromancy -> listOf(
                Element.Light, Element.Dark, Element.Creation,
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
            Element.Light -> pointsInLightBook.intValue = spent
            Element.Dark -> pointsInDarkBook.intValue = spent
            Element.Creation -> pointsInCreateBook.intValue = spent
            Element.Destruction -> pointsInDestructBook.intValue = spent
            Element.Air -> pointsInAirBook.intValue = spent
            Element.Earth -> pointsInEarthBook.intValue = spent
            Element.Water -> pointsInWaterBook.intValue = spent
            Element.Fire -> pointsInFireBook.intValue = spent
            Element.Essence -> pointsInEssenceBook.intValue = spent
            Element.Illusion -> pointsInIllusionBook.intValue = spent
            Element.Necromancy -> pointsInNecroBook.intValue = spent
            else -> {}
        }

        //add book to primary list if neither it nor its opposite are present and there is a spent value given
        if(!primaryElementList.contains(element = book) && !oppositeElementFound(element = book) && spent != 0)
            primaryElementList.add(element = book)

        //attempt to set opposite as primary if spent value is 0 and there previously was a value
        else if (getElementInvestment(inputElement = book) == 0 && primaryElementList.contains(element = book))
            setOppositeAsPrimary(primary = book)

        val cap = spent + if(naturalPaths.contains(element = book)) 40 else 0

        //remove individually bought spells obtained in this purchase
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            individualSpells.removeIf{spell -> spell.inBook == book && spell.level <= cap}
            individualSpells.removeIf{spell -> spell is FreeSpell && findFreeSpellElement(spell) == book && spell.level <= cap}
        }
        else{
            val removeSpells = mutableListOf<Spell>()

            individualSpells.forEach{spell ->
                if((spell.inBook == book && spell.level <= cap) ||
                    (spell is FreeSpell && findFreeSpellElement(find = spell) == book && spell.level <= cap))
                    removeSpells += spell
            }

            individualSpells.removeAll(elements = removeSpells)
        }
    }

    /**
     * Get total invested points in the given element.
     *
     * @param inputElement element to determine investment in
     * @return points invested in this element
     */
    private fun getElementInvestment(inputElement: Element): Int{
        //return sum of individual spells bought and book point investment
        return getIndividualPoints(inputElement = inputElement) + when(inputElement){
            Element.Light -> pointsInLightBook.intValue
            Element.Dark -> pointsInDarkBook.intValue
            Element.Creation -> pointsInCreateBook.intValue
            Element.Destruction -> pointsInDestructBook.intValue
            Element.Air -> pointsInAirBook.intValue
            Element.Earth -> pointsInEarthBook.intValue
            Element.Water -> pointsInWaterBook.intValue
            Element.Fire -> pointsInFireBook.intValue
            Element.Essence -> pointsInEssenceBook.intValue
            Element.Illusion -> pointsInIllusionBook.intValue
            Element.Necromancy -> pointsInNecroBook.intValue
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
                    spellList.add(element = spellBook[index]!!)
                else
                    spellList.add(element = getFreeSpell(
                        inputLevel = (index + 1) * 2,
                        inputElement = inputElement
                    ))
            }

            //set new starting index
            startIndex = 20
        }

        //add spent points value to cumulative value
        magicLevelSpent.intValue += pointValue

        //determine how many spells are bought depending on if opposite element is primary
        val spellRange =
            if (!oppositeElementFound(element = inputElement))
                (pointValue / 2)
            else
                (pointValue / 4)

        //if at least 1 spell gained from spent points
        if(spellRange > 0) {
            if(spellRange + startIndex <= 50) {
                //add indicated spells to the full list
                for (index in startIndex until spellRange + startIndex) {
                    if (spellBook[index] != null)
                        spellList.add(element = spellBook[index]!!)
                    else
                        spellList.add(element = getFreeSpell(
                            inputLevel = (index + 1) * 2,
                            inputElement = inputElement
                        ))
                }
            }

            //add all spells from list if list would be exceeded
            else{
                for(index in startIndex until 50)
                    if (spellBook[index] != null)
                        spellList.add(element = spellBook[index]!!)
                    else
                        spellList.add(element = getFreeSpell(
                            inputLevel = (index + 1) * 2,
                            inputElement = inputElement
                        ))
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
    fun getFreeSpell(
        inputLevel: Int,
        inputElement: Element
    ): FreeSpell{
        //check through element's free spells for appropriate spell
        getElementFreeSpells(inputElement).forEach {freeSpell ->
            if (freeSpell.level == inputLevel)
                return freeSpell
        }

        //return a placeholder for no valid spell found
        return FreeSpell(
            saveName = "PlaceHolder",
            name = R.string.emptyItem,
            isActive = false,
            level = inputLevel,
            zCost = 0,
            effect = R.string.emptyItem,
            addedEffect = R.string.emptyItem,
            zMax = 0,
            maintenance = null,
            isDaily = false,
            type = listOf(),
            forbiddenElements = listOf(inputElement)
        )
    }

    /**
     * Retrieve the associated element's free spells.
     *
     * @param inputElement element to find the free spells for
     * @return list of free spells with the associated element
     */
    private fun getElementFreeSpells(
        inputElement: Element
    ): MutableList<FreeSpell>{
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
    fun findFreeSpellElement(find: FreeSpell): Element {
        //search through each list if it contains this spell
        lightBookFreeSpells.forEach{freeSpell ->
            if(freeSpell == find) return Element.Light
        }
        darkBookFreeSpells.forEach{freeSpell ->
            if(freeSpell == find) return Element.Dark
        }
        creationBookFreeSpells.forEach{freeSpell ->
            if(freeSpell == find) return Element.Creation
        }
        destructionBookFreeSpells.forEach{freeSpell ->
            if(freeSpell == find) return Element.Destruction
        }
        airBookFreeSpells.forEach{freeSpell ->
            if(freeSpell == find) return Element.Air
        }
        earthBookFreeSpells.forEach{freeSpell ->
            if(freeSpell == find) return Element.Earth
        }
        waterBookFreeSpells.forEach{freeSpell ->
            if(freeSpell == find) return Element.Water
        }
        fireBookFreeSpells.forEach{freeSpell ->
            if(freeSpell == find) return Element.Fire
        }
        essenceBookFreeSpells.forEach{freeSpell ->
            if(freeSpell == find) return Element.Essence
        }
        illusionBookFreeSpells.forEach{freeSpell ->
            if(freeSpell == find) return Element.Illusion
        }
        necromancyBookFreeSpells.forEach{freeSpell ->
            if(freeSpell == find) return Element.Necromancy
        }

        //free spell is place holder and has associated element in its own data
        return find.forbiddenElements[0]
    }

    /**
     * Add a Free Spell to the character's indicated spell category.
     *
     * @param addSpell spell granted to the character
     * @param intoElement element the spell is bought under
     */
    fun addFreeSpell(
        addSpell: FreeSpell,
        intoElement: Element
    ){
        //get the element list to add the spell to
        val addToList = getElementFreeSpells(inputElement = intoElement)

        //look for a free spell of the same level in this element
        addToList.forEach{freeSpell ->
            //replace it if found
            if(freeSpell.level == addSpell.level) {
                addToList[addToList.indexOf(element = freeSpell)] = addSpell
                updateSpellList()
                return
            }
        }

        //add to list if none found
        addToList.add(element = addSpell)
        updateSpellList()
    }

    /**
     * Attempt to add or remove an individual spell.
     *
     * @param targetSpell spell to add or remove from character
     * @param isBought whether spell is being bought or removed
     */
    fun changeIndividualSpell(
        targetSpell: Spell,
        isBought: Boolean
    ){
        //cease action if character has Magic Ties disadvantage
        if(magicTies.value)
            return

        //if spell is being bought
        if(isBought){
            //check that spell isn't already owned from book investment
            if(!spellList.contains(element = targetSpell)){
                //add spell to individual list
                individualSpells.add(element = targetSpell)

                //add primary element if able to now
                if(!primaryElementList.contains(element = targetSpell.inBook) && !oppositeElementFound(element = targetSpell.inBook))
                    primaryElementList.add(element = targetSpell.inBook)
            }
        }

        //if spell is being removed
        else{
            //remove individual spell
            individualSpells.remove(element = targetSpell)

            //remove primary element if no more investment in it
            if(getElementInvestment(inputElement = targetSpell.inBook) == 0)
                setOppositeAsPrimary(primary = targetSpell.inBook)
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
    fun changeIndividualFreeSpell(
        levelInput: Int,
        elementInput: Element,
        isBought: Boolean
    ){
        //terminate process if Magic Ties disadvantage is taken
        if(magicTies.value)
            return

        //determine spell held by the character
        val spellItem = getFreeSpell(inputLevel = levelInput, inputElement = elementInput)

        //if acquiring the free spell
        if(isBought) {
            //check if character has an equivalent spell already
            if(!hasCopyOf(check = spellItem)) {
                //add individual spell to the list
                individualSpells.add(element = spellItem)

                //add element to primary list if able to
                if(!primaryElementList.contains(element = elementInput) && !oppositeElementFound(element = elementInput))
                    primaryElementList.add(element = elementInput)
            }
        }

        //if removing free spell
        else{
            //remove the appropriate spell from the list
            var removeSpell: Spell? = null

            individualSpells.forEach{spell ->
                if(spell is FreeSpell && spell.level == levelInput && findFreeSpellElement(find = spell) == elementInput){
                    removeSpell = spell
                }
            }

            if(removeSpell != null)
                individualSpells.remove(element = removeSpell)

            //remove the item from the element free spell list
            getElementFreeSpells(inputElement = elementInput).remove(element = spellItem)

            //remove primary element if required
            if(getElementInvestment(inputElement = elementInput) == 0)
                setOppositeAsPrimary(primary = elementInput)
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
        individualSpells.forEach{spell ->
            //determine if free spell is of this element and add its value if it matches
            if(spell is FreeSpell && findFreeSpellElement(find = spell) == inputElement)
                elementTotal += getIndividualCost(
                    inputLevel = spell.level,
                    inputElement = inputElement
                )

            //add spell costs for matching elements
            else if(spell.inBook == inputElement)
                elementTotal += getIndividualCost(
                    inputLevel = spell.level,
                    inputElement = inputElement
                )
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
    private fun getIndividualCost(
        inputLevel: Int,
        inputElement: Element
    ): Int{
        //initialize cost value
        var cost = (ceil(inputLevel.toDouble()/10.0).toInt() * 2)

        //double if opposing element is primary
        if(oppositeElementFound(element = inputElement))
            cost *= 2

        //return final cost value
        return cost
    }

    /**
     * Re-evaluates what spells the character has access to.
     */
    fun updateSpellList(){
        //clear magic level spent and list of usable spells
        magicLevelSpent.intValue = 0
        spellList.clear()

        //get spells acquired from investment in books
        addSpellsFromBook(pointsInLightBook.intValue, Element.Light, lightBook.fullBook)
        addSpellsFromBook(pointsInDarkBook.intValue, Element.Dark, darkBook.fullBook)
        addSpellsFromBook(pointsInCreateBook.intValue, Element.Creation, creationBook.fullBook)
        addSpellsFromBook(pointsInDestructBook.intValue, Element.Destruction, destructionBook.fullBook)
        addSpellsFromBook(pointsInAirBook.intValue, Element.Air, airBook.fullBook)
        addSpellsFromBook(pointsInEarthBook.intValue, Element.Earth, earthBook.fullBook)
        addSpellsFromBook(pointsInWaterBook.intValue, Element.Water, waterBook.fullBook)
        addSpellsFromBook(pointsInFireBook.intValue, Element.Fire, fireBook.fullBook)
        addSpellsFromBook(pointsInEssenceBook.intValue, Element.Essence, essenceBook.fullBook)
        addSpellsFromBook(pointsInIllusionBook.intValue, Element.Illusion, illusionBook.fullBook)
        addSpellsFromBook(pointsInNecroBook.intValue, Element.Necromancy, necromancyBook.fullBook)

        //get spells acquired from individual purchases
        individualSpells.forEach{spell ->
            if(spell is FreeSpell) {
                magicLevelSpent.intValue += getIndividualCost(
                    inputLevel = spell.level,
                    inputElement = findFreeSpellElement(find = spell)
                )
                spellList.add(
                    element = getFreeSpell(
                        inputLevel = spell.level,
                        inputElement = findFreeSpellElement(find = spell)
                    )
                )
            }
            else {
                magicLevelSpent.intValue += getIndividualCost(
                    inputLevel = spell.level,
                    inputElement = spell.inBook
                )
                spellList.add(element = spell)
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
        spellList.forEach{spell ->
            //return true if input is a placeholder of identical element and level
            if(check is FreeSpell && spell is FreeSpell && check.saveName == "PlaceHolder"){
                if(spell.level == check.level && findFreeSpellElement(spell) == findFreeSpellElement(check))
                    return true
            }

            //check for identical spell name
            else if(spell.name == check.name)
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
    fun hasIndividualFreeCopyOf(
        level: Int,
        type: Element
    ): Boolean{
        individualSpells.forEach{
            if(it is FreeSpell){
                if(it.level == level && findFreeSpellElement(find = it) == type)
                    return true
            }
        }

        return false
    }

    /**
     * Set the taken state of the Magic Ties disadvantage.
     *
     * @param hasTies taken state of the Magic Ties disadvantage
     */
    fun setMagicTies(hasTies: Boolean){
        if(hasTies){
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
                changeIndividualSpell(targetSpell = individualSpells[0], isBought = false)
            }
        }

        //set magic ties value
        magicTies.value = hasTies
    }

    /**
     * Function to run on confirmed removal of The Gift advantage.
     */
    fun loseMagic(){
        //reset original zeon values
        buyZeon(zeonBuy = 0)
        buyZeonAcc(accBuy = 1)
        buyMagProj(projBuy = 0)

        //clear spellbook
        individualSpells.clear()
        buyBookLevels(spent = 0, book = Element.Light)
        buyBookLevels(spent = 0, book = Element.Dark)
        buyBookLevels(spent = 0, book = Element.Creation)
        buyBookLevels(spent = 0, book = Element.Destruction)
        buyBookLevels(spent = 0, book = Element.Air)
        buyBookLevels(spent = 0, book = Element.Earth)
        buyBookLevels(spent = 0, book = Element.Water)
        buyBookLevels(spent = 0, book = Element.Fire)
        buyBookLevels(spent = 0, book = Element.Essence)
        buyBookLevels(spent = 0, book = Element.Illusion)
        buyBookLevels(spent = 0, book = Element.Necromancy)

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
     *
     * @return development points spent in this section
     */
    fun calculateSpent(): Int{
        return (boughtZeon.intValue * charInstance.ownClass.value.zeonGrowth) +
                ((zeonAccMult.intValue - 1) * charInstance.ownClass.value.maGrowth) +
                (boughtMagProjection.intValue * charInstance.ownClass.value.maProjGrowth)
    }

    /**
     * Load data for this section from the inputted file.
     *
     * @param fileReader file to retrieve the data from
     */
    fun loadMagic(fileReader: BufferedReader){
        //get character's purchased zeon
        boughtZeon.intValue = fileReader.readLine().toInt()

        //get zeon accumulation multiple
        zeonAccMult.intValue = fileReader.readLine().toInt()

        //get magic projection bought
        boughtMagProjection.intValue = fileReader.readLine().toInt()

        //get projection imbalance value
        magProjImbalance.intValue = fileReader.readLine().toInt()

        //get projection imbalance bias
        imbalanceIsAttack.value = fileReader.readLine().toBoolean()
        calcMagProj()

        //get each saved primary element
        for(index in 0 until fileReader.readLine().toInt()){
            primaryElementList.add(Element.fromString(fileReader.readLine()))
        }

        //get all points invested in each element book
        pointsInLightBook.intValue = fileReader.readLine().toInt()
        pointsInDarkBook.intValue = fileReader.readLine().toInt()
        pointsInCreateBook.intValue = fileReader.readLine().toInt()
        pointsInDestructBook.intValue = fileReader.readLine().toInt()
        pointsInAirBook.intValue = fileReader.readLine().toInt()
        pointsInEarthBook.intValue = fileReader.readLine().toInt()
        pointsInWaterBook.intValue = fileReader.readLine().toInt()
        pointsInFireBook.intValue = fileReader.readLine().toInt()
        pointsInEssenceBook.intValue = fileReader.readLine().toInt()
        pointsInIllusionBook.intValue = fileReader.readLine().toInt()
        pointsInNecroBook.intValue = fileReader.readLine().toInt()

        //get each individual spell saved
        for(index in 0 until fileReader.readLine().toInt()){
            //construct placeholder spell if indicated by spellName
            if(fileReader.readLine().toBoolean()){
                individualSpells.add(FreeSpell(
                    saveName = "PlaceHolder",
                    name = R.string.emptyItem,
                    isActive = false,
                    level = fileReader.readLine().toInt(),
                    zCost = 0,
                    effect = R.string.emptyItem,
                    addedEffect = R.string.emptyItem,
                    zMax = 0,
                    maintenance = null,
                    isDaily = false,
                    type = listOf(),
                    forbiddenElements = listOf(Element.fromString(elementName = fileReader.readLine()))
                ))
            }

            //find the element spell that is saved
            else{
                val searchList = when(Element.fromString(elementName = fileReader.readLine())){
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

                val searchLevel = fileReader.readLine().toInt()
                searchList.forEach search@{spell ->
                    if(spell != null && spell.level == searchLevel) {
                        individualSpells.add(spell)
                        return@search
                    }
                }
            }
        }

        //load free spells for each element
        loadElementFreeSpells(loadTo = lightBookFreeSpells, fileReader = fileReader)
        loadElementFreeSpells(loadTo = darkBookFreeSpells, fileReader = fileReader)
        loadElementFreeSpells(loadTo = creationBookFreeSpells, fileReader = fileReader)
        loadElementFreeSpells(loadTo = destructionBookFreeSpells, fileReader = fileReader)
        loadElementFreeSpells(loadTo = airBookFreeSpells, fileReader = fileReader)
        loadElementFreeSpells(loadTo = earthBookFreeSpells, fileReader = fileReader)
        loadElementFreeSpells(loadTo = waterBookFreeSpells, fileReader = fileReader)
        loadElementFreeSpells(loadTo = fireBookFreeSpells, fileReader = fileReader)
        loadElementFreeSpells(loadTo = essenceBookFreeSpells, fileReader = fileReader)
        loadElementFreeSpells(loadTo = illusionBookFreeSpells, fileReader = fileReader)
        loadElementFreeSpells(loadTo = necromancyBookFreeSpells, fileReader = fileReader)

        updateSpellList()
    }

    /**
     * Write the data to file.
     *
     * @param byteArray output stream for the data
     */
    fun writeMagic(byteArray: ByteArrayOutputStream) {
        //write bought zeon points
        writeDataTo(writer = byteArray, input = boughtZeon.intValue)

        //write zeon accumulation multiple
        writeDataTo(writer = byteArray, input = zeonAccMult.intValue)

        //write magic projection bought
        writeDataTo(writer = byteArray, input = boughtMagProjection.intValue)

        //write projection imbalance
        writeDataTo(writer = byteArray, input = magProjImbalance.intValue)

        //write imbalance bias
        writeDataTo(writer = byteArray, input = imbalanceIsAttack.value.toString())

        //save primary elements and list size to file
        writeDataTo(writer = byteArray, input = primaryElementList.size)
        primaryElementList.forEach{element ->
            writeDataTo(writer = byteArray, input = element.name)
        }

        //write all book levels to file
        writeDataTo(writer = byteArray, input = pointsInLightBook.intValue)
        writeDataTo(writer = byteArray, input = pointsInDarkBook.intValue)
        writeDataTo(writer = byteArray, input = pointsInCreateBook.intValue)
        writeDataTo(writer = byteArray, input = pointsInDestructBook.intValue)
        writeDataTo(writer = byteArray, input = pointsInAirBook.intValue)
        writeDataTo(writer = byteArray, input = pointsInEarthBook.intValue)
        writeDataTo(writer = byteArray, input = pointsInWaterBook.intValue)
        writeDataTo(writer = byteArray, input = pointsInFireBook.intValue)
        writeDataTo(writer = byteArray, input = pointsInEssenceBook.intValue)
        writeDataTo(writer = byteArray, input = pointsInIllusionBook.intValue)
        writeDataTo(writer = byteArray, input = pointsInNecroBook.intValue)

        //write all individually bought spells to file
        writeDataTo(writer = byteArray, input = individualSpells.size)
        individualSpells.forEach{spell ->
            if(spell is FreeSpell){
                writeDataTo(writer = byteArray, input = true)
                writeDataTo(writer = byteArray, input = spell.level)
                writeDataTo(writer = byteArray, input = findFreeSpellElement(find = spell).name)
            }
            else {
                writeDataTo(writer = byteArray, input = false)
                writeDataTo(writer = byteArray, input = spell.inBook.name)
                writeDataTo(writer = byteArray, input = spell.level)
            }
        }

        //save data on free spell investment
        saveElementFreeSpells(byteArray = byteArray, saveItem = lightBookFreeSpells)
        saveElementFreeSpells(byteArray = byteArray, saveItem = darkBookFreeSpells)
        saveElementFreeSpells(byteArray = byteArray, saveItem = creationBookFreeSpells)
        saveElementFreeSpells(byteArray = byteArray, saveItem = destructionBookFreeSpells)
        saveElementFreeSpells(byteArray = byteArray, saveItem = airBookFreeSpells)
        saveElementFreeSpells(byteArray = byteArray, saveItem = earthBookFreeSpells)
        saveElementFreeSpells(byteArray = byteArray, saveItem = waterBookFreeSpells)
        saveElementFreeSpells(byteArray = byteArray, saveItem = fireBookFreeSpells)
        saveElementFreeSpells(byteArray = byteArray, saveItem = essenceBookFreeSpells)
        saveElementFreeSpells(byteArray = byteArray, saveItem = illusionBookFreeSpells)
        saveElementFreeSpells(byteArray = byteArray, saveItem = necromancyBookFreeSpells)
    }

    /**
     * Function to run when loading free spells for an element.
     *
     * @param loadTo free spell list to push data to
     * @param fileReader file to load data from
     */
    private fun loadElementFreeSpells(
        loadTo: MutableList<FreeSpell>,
        fileReader: BufferedReader
    ){
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
            searchList.forEach{freeSpell ->
                if(freeSpell.saveName == searchName)
                    loadTo.add(
                        element = FreeSpell(
                            saveName = freeSpell.saveName,
                            name = freeSpell.name,
                            isActive = freeSpell.isActive,
                            level = searchLevel,
                            zCost = freeSpell.zCost,
                            effect = freeSpell.effect,
                            addedEffect = freeSpell.addedEffect,
                            zMax = freeSpell.zMax,
                            maintenance = freeSpell.maintenance,
                            isDaily = freeSpell.isDaily,
                            type = freeSpell.type,
                            forbiddenElements = freeSpell.forbiddenElements
                        )
                    )
            }
        }
    }

    /**
     * Write free spell data to file.
     *
     * @param byteArray output stream for the data
     * @param saveItem list of free spells to write to file
     */
    private fun saveElementFreeSpells(
        byteArray: ByteArrayOutputStream,
        saveItem: MutableList<FreeSpell>
    ){
        //save the number of spells in the list
        writeDataTo(writer = byteArray, input = saveItem.size)

        //write each spell's level and name
        saveItem.forEach{
            writeDataTo(writer = byteArray, input = it.level)
            writeDataTo(writer = byteArray, input = it.saveName)
        }
    }
}