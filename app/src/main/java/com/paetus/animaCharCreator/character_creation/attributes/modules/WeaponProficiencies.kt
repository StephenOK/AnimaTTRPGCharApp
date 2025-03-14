package com.paetus.animaCharCreator.character_creation.attributes.modules

import androidx.compose.runtime.mutableIntStateOf
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.character_creation.equipment.Armory
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.MixedWeapon
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.Weapon
import com.paetus.animaCharCreator.writeDataTo
import java.io.BufferedReader
import java.io.ByteArrayOutputStream

/**
 * Class that operates on the character's various proficiencies.
 * Holds data on all of the available weapons and archetypes.
 * Holds data on all of the available martial arts.
 * Allows the user to take style modules.
 *
 * @param charInstance object that holds all data on the character
 */

open class WeaponProficiencies(private val charInstance: BaseCharacter){
    fun getArmory(): Armory {return charInstance.objectDB.armory}
    fun getMartials(): MartialArts {return charInstance.objectDB.martials}
    fun getStyles(): StyleInstances {return charInstance.objectDB.styles}

    //initialize character's primary weapon
    val primaryWeapon = mutableIntStateOf(value = 0)

    //initialize weapons individually taken and given from group modules
    val individualModules = mutableListOf<Int>()

    //list of modules the character has taken
    val takenModules = mutableListOf<List<Weapon>>()

    //initialize taken martial arts and maximum martial art quantity
    val takenMartialList = mutableListOf<MartialArt>()
    val martialMax = mutableIntStateOf(value = 0)

    //initialize list of taken style modules
    val styleMods = mutableListOf<StyleModule>()

    /**
     * Finds the weapon from the given string input.
     *
     * @param weaponName name of the weapon the caller is looking for
     * @return the weapon with the searched for name
     */
    fun findWeapon(weaponName: String): Int {
        //search through all weapons until a match is found
        getArmory().allWeapons.forEach{weapon ->
            if(weapon.saveName == weaponName)
                return getArmory().allWeapons.indexOf(weapon)
        }

        //return unarmed if not found
        return 0
    }

    /**
     * Set the character's primary weapon to the inputted string.
     *
     * @param weaponName reference to item to set as the primary weapon
     */
    open fun setPrimaryWeapon(weaponName: String){
        //retrieve the weapon item associated with the input and apply it as the primary weapon
        setPrimaryWeapon(findWeapon(weaponName = weaponName))
    }

    /**
     * Set the character's primary weapon to the inputted weapon.
     *
     * @param weaponIndex value to set as the primary weapon
     */
    fun setPrimaryWeapon(weaponIndex: Int){
        //set new primary weapon
        primaryWeapon.intValue = weaponIndex

        //potentially remove the weapon from the purchase list
        individualModules -= primaryWeapon.intValue
        charInstance.updateTotalSpent()
    }

    /**
     * Give or remove an individual weapon module as indicated by the input.
     *
     * @param weapon weapon module to alter
     * @param toAdd true if adding module; false if removing
     */
    open fun changeIndividualModule(
        weapon: Weapon,
        toAdd: Boolean
    ){
        //add module if requested and not taken by archetype
        if(toAdd && !weaponsFromArchetypes().contains(element = getArmory().allWeapons.indexOf(weapon)))
            individualModules += getArmory().allWeapons.indexOf(weapon)
        //remove module if requested
        else if(!toAdd)
            individualModules -= getArmory().allWeapons.indexOf(weapon)

        //update the points spent by the character
        charInstance.updateTotalSpent()
    }

    /**
     * Changes the character's taken archetype modules as desired.
     *
     * @param weaponCheck archetype module to add or remove
     * @param isAdded whether to add weaponCheck or remove it
     */
    open fun updateModulesTaken(
        weaponCheck: List<Weapon>,
        isAdded: Boolean
    ){
        //add or remove module list
        if(isAdded)
            takenModules += listOf(weaponCheck)
        else
            takenModules -= listOf(weaponCheck).toSet()

        //remove any individual weapon modules from added archetype weapons
        individualModules -= weaponsFromArchetypes().toSet()

        charInstance.updateTotalSpent()
    }

    /**
     * Get all weapons the character has proficiency in via archetype modules.
     *
     * @return list of weapons from taken archetypes
     */
    fun weaponsFromArchetypes(): List<Int>{
        //initialize the outputted list
        val output = mutableListOf<Int>()

        //add every weapon from the taken archetypes
        takenModules.forEach{list ->
            list.forEach{
                output += getArmory().allWeapons.indexOf(it)
            }
        }

        //output the final list
        return output.toList()
    }

    /**
     * Attempts to add or remove a martial art from the character's taken martial arts.
     *
     * @param changeItem martial art to add or remove
     * @param isAdded whether to add or remove the inputted martial art
     * @return true if martial art has been successfully added
     */
    open fun changeMartial(
        changeItem: MartialArt,
        isAdded: Boolean
    ): Boolean{
        //if user tries to add the item
        if(isAdded){
            //check that character qualifies for this addition
            if(takenMartialList.size < martialMax.intValue && changeItem.qualification(charInstance)){
                //add martial art to record
                takenMartialList += changeItem

                //apply capoeira's dodge bonus
                if(changeItem == getMartials().capoeira)
                    charInstance.combat.dodge.setClassBonus(10)

                //update spelt total
                charInstance.updateTotalSpent()

                //return successful addition flag
                return true
            }
        }
        //always allow martial art removal
        else {
            takenMartialList -= changeItem

            //remove capoeira's dodge bonus
            if(changeItem == getMartials().capoeira)
                charInstance.combat.dodge.setClassBonus(-10)
        }

        //update martial knowledge accordingly
        charInstance.ki.updateMK()
        charInstance.updateTotalSpent()

        //return removal or failed addition flag
        return false
    }

    /**
     * Check that all taken martial arts still qualify.
     */
    fun doubleCheck() {
        takenMartialList.forEach{martial ->
            if(!martial.qualification(charInstance))
                takenMartialList -= martial
        }
    }

    /**
     * Updates the maximum number of martial arts the character can take.
     */
    fun updateMartialMax() {
        //determine martial art number based on attack and highest defense abilities
        martialMax.intValue = charInstance.combat.attack.total.intValue +
                if (charInstance.combat.block.total.intValue > charInstance.combat.dodge.total.intValue)
                    charInstance.combat.block.total.intValue
                else
                    charInstance.combat.dodge.total.intValue
        martialMax.intValue /= 40

        //remove martial arts that exceed the new found limit
        if(takenMartialList.size > martialMax.intValue)
            takenMartialList.dropLast(takenMartialList.size - martialMax.intValue)
    }

    /**
     * Determines the amount of martial knowledge gained from taken martial arts.
     *
     * @return martial knowledge from martial arts
     */
    fun mkFromArts(): Int{
        //initialize output
        var total = 0

        //add bonus for each taken martial art
        takenMartialList.forEach{martial ->
            total += martial.mkBonus
        }

        //give output
        return total
    }

    /**
     * Acquire or remove the indicated style.
     *
     * @param style style to alter with this action
     * @param toAdd whether the style is being added or removed
     */
    open fun changeStyle(
        style: StyleModule,
        toAdd: Boolean
    ){
        //add or remove the style as indicated
        if(toAdd)
            styleMods += style
        else
            styleMods -= style

        //update development points spent
        charInstance.updateTotalSpent()
    }

    /**
     * Determines development points spent in modules, martial arts, and weapon styles.
     *
     * @return value of points spent
     */
    fun calculateSpent(): Int{
        //initialize total as number of archetype modules taken
        var total = takenModules.size * 50

        //add amount for different style modules
        styleMods.forEach{style ->
            if(!getStyles().exceptions.contains(element = style))
                total += style.cost
        }

        //create dividend for individual weapon module addition
        val classDividend =
            if(charInstance.classes.ownClass.intValue != 5)
                1
            else
                2

        //apply weaponmaster deductions if applicable
        total /= classDividend

        //retrieve list of individual weapons
        val toCheck = individualModules.toMutableList() - weaponsFromArchetypes().toSet()

        //for each individual module taken
        toCheck.forEach{
            val weapon = getArmory().allWeapons[it]

            //if primary weapon is mixed
            if(getArmory().allWeapons[primaryWeapon.intValue] is MixedWeapon){
                val copyPrime = getArmory().allWeapons[primaryWeapon.intValue] as MixedWeapon

                //apply same type for exactly matching weapons
                if(weapon is MixedWeapon) {
                    if ((copyPrime.mixedType - weapon.mixedType.toSet()).isEmpty())
                        total += 10/classDividend

                    //apply mixed type for one matching type
                    else if (copyPrime.mixedType.contains(weapon.mixedType[0]) ||
                        copyPrime.mixedType.contains(weapon.mixedType[1])
                    )
                        total += 15/classDividend
                }

                //apply mixed type for it belonging to one mixed type
                else if(copyPrime.mixedType.contains(weapon.type))
                    total += 15/classDividend

                else
                    total += 20/classDividend
            }

            //if primary weapon is not mixed
            //add mixed cost if secondary is mixed with one matching type
            else if(weapon is MixedWeapon && weapon.mixedType.contains(getArmory().allWeapons[primaryWeapon.intValue].type))
                total += 15/classDividend
            //add identical cost for identical type
            else if(weapon.type == getArmory().allWeapons[primaryWeapon.intValue].type)
                total += 10/classDividend

            //add no matching type amount
            else
                total += 20/classDividend
        }

        //if character has martial arts taken
        if(takenMartialList.isNotEmpty()){
            //determine cost of first art depending on
            total +=
                //if character is a Tao
                if(charInstance.classes.ownClass.intValue == 7)
                    10
                //if character is primarily unarmed
                else if(primaryWeapon.intValue == 0)
                    25
                //if neither are factors
                else
                    50

            //add 50 for every other martial art taken
            total +=
                if(charInstance.classes.ownClass.intValue == 7) (takenMartialList.size - 1) * 20
                else (takenMartialList.size - 1) * 50
        }

        return total
    }

    /**
     * Determines the amount of magic points spent in this section.
     *
     * @return value of magic points in this section
     */
    fun calcPointsInMag(): Int{
        var output = 0

        //add style costs for each one taken
        if(styleMods.contains(element = getStyles().magAsAttack)) output += 75
        if(styleMods.contains(element = getStyles().magAsDefense)) output += 75

        return output
    }

    /**
     * Determines the amount of psychic points spent in this section.
     *
     * @return value of psychic points in this section
     */
    fun calcPointsInPsy(): Int{
        return if (styleMods.contains(element = getStyles().psyProjection)) 100
            else 0
    }

    /**
     * Retrieves save data to apply to the character for this category.
     *
     * @param fileReader reader of the input for this item
     * @param writeVersion version of the last save for the loaded character
     */
    fun loadProficiencies(
        fileReader: BufferedReader,
        writeVersion: Int
    ){
        //get primary weapon
        if(writeVersion < 44){
            //convert save name to index pointer
            val findWeapon = fileReader.readLine()
            getArmory().allWeapons.forEach{
                if(it.saveName == findWeapon) primaryWeapon.intValue = getArmory().allWeapons.indexOf(it)
                return@forEach
            }
        }
        //retrieve index pointer
        else
            primaryWeapon.intValue = fileReader.readLine().toInt()

        //get each individual weapon module
        (0 until fileReader.readLine().toInt()).forEach{
            if(writeVersion < 44){
                val findWeapon = fileReader.readLine()
                getArmory().allWeapons.forEach{
                    if(it.saveName == findWeapon) individualModules += getArmory().allWeapons.indexOf(it)
                    return@forEach
                }
            }
            else
                individualModules += fileReader.readLine().toInt()
        }

        //get any archetype modules saved
        (0 until fileReader.readLine().toInt()).forEach{
            updateModulesTaken(weaponCheck = getArmory().allArchetypes[fileReader.readLine()]!!, isAdded = true)
        }

        //get any style modules saved
        (0 until fileReader.readLine().toInt()).forEach{
            styleMods += getStyles().getStyle(styleName = fileReader.readLine())!!
        }

        //get any martial arts saved
        (0 until fileReader.readLine().toInt()).forEach {
            takenMartialList += listOf(loadMartial(martialName = fileReader.readLine())!!)
        }
    }

    /**
     * Saves the data found in this category.
     *
     * @param byteArray output stream for this item's data
     */
    fun writeProficiencies(byteArray: ByteArrayOutputStream) {
        //save primary weapon data
        writeDataTo(writer = byteArray, input = primaryWeapon.intValue)

        //record all individual weapon modules
        writeDataTo(writer = byteArray, input = individualModules.size.toString())
        individualModules.forEach{
            writeDataTo(writer = byteArray, input = it)
        }

        //record all archetype modules
        writeDataTo(writer = byteArray, input = takenModules.size)
        getArmory().allArchetypes.forEach{(archName, list) ->
            if(takenModules.contains(element = list)) writeDataTo(writer = byteArray, input = archName)
        }

        //record all style modules
        writeDataTo(writer = byteArray, input = styleMods.size)
        styleMods.forEach{style ->
            writeDataTo(writer = byteArray, input = style.saveName)
        }

        //record all martial arts taken
        writeDataTo(writer = byteArray, input = takenMartialList.size)
        takenMartialList.forEach{martial ->
            writeDataTo(writer = byteArray, input = martial.saveName)
        }
    }

    /**
     * Finds a martial art of the given name.
     *
     * @param martialName martial art to find
     * @return the desired martial art if found
     */
    private fun loadMartial(martialName: String): MartialArt?{
        getMartials().allMartialArts.forEach{martial ->
            if(martialName == martial.saveName)
                return martial
        }

        return null
    }
}