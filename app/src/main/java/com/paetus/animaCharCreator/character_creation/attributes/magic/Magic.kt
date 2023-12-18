package com.paetus.animaCharCreator.character_creation.attributes.magic

import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.enumerations.Element
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.FreeSpell
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.MagicBook
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.Spell
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook.*
import com.paetus.animaCharCreator.writeDataTo
import java.io.BufferedReader
import java.io.ByteArrayOutputStream

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

    val allBooks = listOf(
        lightBook,
        darkBook,
        creationBook,
        destructionBook,
        airBook,
        earthBook,
        waterBook,
        fireBook,
        essenceBook,
        illusionBook,
        necromancyBook
    )

    init{
        //apply opposing books to the appropriate spell books
        lightBook.opposingBooks.addAll(elements = listOf(darkBook, necromancyBook))
        darkBook.opposingBooks.addAll(elements = listOf(lightBook, necromancyBook))
        creationBook.opposingBooks.addAll(elements = listOf(destructionBook, necromancyBook))
        destructionBook.opposingBooks.addAll(elements = listOf(creationBook, necromancyBook))
        airBook.opposingBooks.addAll(elements = listOf(earthBook, necromancyBook))
        earthBook.opposingBooks.addAll(elements = listOf(airBook, necromancyBook))
        waterBook.opposingBooks.addAll(elements = listOf(fireBook, necromancyBook))
        fireBook.opposingBooks.addAll(elements = listOf(waterBook, necromancyBook))
        essenceBook.opposingBooks.addAll(elements = listOf(illusionBook, necromancyBook))
        illusionBook.opposingBooks.addAll(elements = listOf(essenceBook, necromancyBook))
        necromancyBook.opposingBooks.addAll(elements = listOf(
            lightBook,
            darkBook,
            creationBook,
            destructionBook,
            airBook,
            earthBook,
            waterBook,
            fireBook,
            essenceBook,
            illusionBook
        ))
    }

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
     * Updates the magic levels spent by this character.
     */
    fun updateMagLevelSpent(){
        //re-initialize the spent value
        magicLevelSpent.intValue = 0

        //add each individual book's spent value to the total
        allBooks.forEach{book ->
            magicLevelSpent.intValue += book.getMagLevels()
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
        //search each held spell for a match
        getAllSpells().forEach{heldSpell ->
            if(heldSpell.name == check.name) return true
        }

        //notify of no match found
        return false
    }

    /**
     * Gets the associated element of the spell.
     *
     * @param spell item to find the element of
     * @return associated element of the spell
     */
    fun getSpellElement(spell: Spell): Element{
        //check if the spell is part of an elemental book
        allBooks.forEach{book ->
            if(spell in book.fullBook) return book.element
        }

        //return that spell has no element
        return Element.Free
    }

    /**
     * Retrieve the spellbook associated with the inputted spell.
     *
     * @param spell item to find the book of
     * @return associated book of the spell, if found
     */
    fun getSpellBook(spell: Spell): MagicBook?{
        //search each book for the spell
        allBooks.forEach{book ->
            if(spell in book.fullBook) return book
        }

        //no book associated with this spell
        return null
    }

    /**
     * Retrieves the magic book associated with the inputted element.
     *
     * @param element item to find the book of
     * @return associated book of the element, if found
     */
    fun getElementBook(element: Element): MagicBook?{
        //search each book for the associated element
        allBooks.forEach{book ->
            if(book.element == element) return book
        }

        //elemental book not found
        return null
    }

    /**
     * Retrieves the inputted free spell's associated spell book.
     *
     * @param freeSpell item to find the book of
     * @return book associated with this free spell
     */
    fun getFreeSpellBook(freeSpell: FreeSpell): MagicBook{
        return if(freeSpell.name == R.string.emptyItem)
            //get the empty item's associated book
            getElementBook(element = freeSpell.forbiddenElements[0])!!
        else
            //get the book this spell is in
            necromancyBook.charHasFreeSpell(freeSpell = freeSpell)!!
    }

    /**
     * Compiles all spells the character has access to.
     *
     * @return list of all accessible spells
     */
    fun getAllSpells(): List<Spell>{
        val output = mutableListOf<Spell>()

        allBooks.forEach{book ->
            output.addAll(elements = book.getSpells())
        }

        return output.toList()
    }

    /**
     * Set the taken state of the Magic Ties disadvantage.
     *
     * @param hasTies taken state of the Magic Ties disadvantage
     */
    fun setMagicTies(hasTies: Boolean){
        if(hasTies){
            //clear all individually purchased and free spells
            allBooks.forEach{book ->
                book.magicTiesClear()
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
        allBooks.forEach{book -> book.clear()}
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
     * @param writeVersion version of the app the file was written in
     */
    fun loadMagic(
        fileReader: BufferedReader,
        writeVersion: Int
    ){
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

        if(writeVersion <= 26){
            //get each saved primary element
            val tempPrimeList = mutableListOf<Element>()
            for(index in 0 until fileReader.readLine().toInt()){
                tempPrimeList.add(Element.fromString(elementName = fileReader.readLine()))
            }

            //get all points invested in each element book
            allBooks.forEach{book ->
                book.buyLevels(fileReader.readLine().toInt())
            }

            //get each individual spell saved
            for(index in 0 until fileReader.readLine().toInt()){
                //construct placeholder spell if indicated by spellName
                if(fileReader.readLine().toBoolean()){
                    val level = fileReader.readLine().toInt()

                    getElementBook(element = Element.fromString(elementName = fileReader.readLine()))!!
                        .changeIndividualSpell(spellLevel = level)
                }

                //find the element spell that is saved
                else{
                    getElementBook(element = Element.fromString(elementName = fileReader.readLine()))!!
                        .changeIndividualSpell(spellLevel = fileReader.readLine().toInt())
                }
            }

            //load free spells for each element
            allBooks.forEach{book ->
                for(count in 0 until fileReader.readLine().toInt()) {
                    val level = fileReader.readLine().toInt()
                    val freeBase = freeBook.findFreeSpell(fileReader.readLine())
                    book.addFreeSpell(
                        FreeSpell(
                            saveName = freeBase.saveName,
                            name = freeBase.name,
                            isActive = freeBase.isActive,
                            level = level,
                            zCost = freeBase.zCost,
                            effect = freeBase.effect,
                            addedEffect = freeBase.addedEffect,
                            zMax = freeBase.zMax,
                            maintenance = freeBase.maintenance,
                            isDaily = freeBase.isDaily,
                            type = freeBase.type,
                            forbiddenElements = freeBase.forbiddenElements
                        )
                    )
                }
            }

            //apply each primary element
            tempPrimeList.forEach{element ->
                getElementBook(element = element)!!.changePrimary(isTaking = true)
            }
        }
        else{
            allBooks.forEach{book -> book.load(fileReader = fileReader, freeBook = freeBook)}
        }
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

        //write all book data
        allBooks.forEach{book -> book.write(byteArray = byteArray)}
    }
}