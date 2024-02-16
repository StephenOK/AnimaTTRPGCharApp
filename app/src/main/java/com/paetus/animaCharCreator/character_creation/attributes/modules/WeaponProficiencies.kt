package com.paetus.animaCharCreator.character_creation.attributes.modules

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.enumerations.AttackType
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponAbility
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.MixedWeapon
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.Weapon
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_instances.*
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

class WeaponProficiencies(private val charInstance: BaseCharacter){
    //get all weapon instances
    val shortArms = ShortArms()
    val axes = Axes()
    val maces = Maces()
    val swords = Swords()
    val twoHanded = TwoHanded()
    val poles = Poles()
    val cords = Cords()
    val mixed = Mixed()
    val shields = Shields()
    val projectiles = Projectiles()
    val thrown = Thrown()

    //create data on the unarmed weapon
    val unarmed = Weapon(
        saveName = "unarmed",
        name = R.string.unarmed,
        damage = 10,
        speed = 20,
        oneHandStr = 0, twoHandStr = null,
        primaryType = AttackType.Impact, secondaryType = null, type = WeaponType.Unarmed,
        fortitude = null, breakage = null, presence = null,
        ability = listOf(WeaponAbility.Precision), ownStrength = null,
        description = R.string.unarmedDesc
    )

    //make list of every weapon
    val allWeapons = shortArms.shortArms + axes.axes + maces.maces + swords.swords + twoHanded.twoHanded +
            poles.poles + cords.cords + mixed.mixed + shields.shields + projectiles.projectiles + thrown.thrown + unarmed

    //gather all archetype modules
    private val improvised = listOf(shortArms.brokenBottle, twoHanded.chair, shortArms.kitchenKnife,
        maces.hammer, axes.hoe, maces.metalBar, shortArms.pick, shortArms.sickle, maces.torch,
        maces.vase, maces.woodenPole, axes.woodAxe)
    private val barbarianWeapons = listOf(mixed.twoHandAxe, axes.battleAxe, twoHanded.twoHandSword,
        mixed.bastardSword, mixed.heavyBattleMace)
    private val ninjaWeapons = listOf(swords.katana, shortArms.tanto, shortArms.claws, shortArms.shuriken,
        mixed.kusariGama)
    private val duelWeapons = listOf(swords.rapier, mixed.foil, shortArms.parryDagger, shortArms.dagger,
        swords.saber, swords.longSword)
    private val pirateWeapons = listOf(poles.harpoon, cords.gladNet, shortArms.hook, swords.saber, axes.handAxe)
    private val nomadWeapons = listOf(shortArms.dagger, thrown.chakram, projectiles.longBow,
        swords.scimitar, poles.lance)
    private val huntWeapons = listOf(poles.javelin, projectiles.shortBow, shortArms.shortSword, poles.lance,
        thrown.bolas)
    private val knightWeapons = listOf(swords.longSword, poles.cavLance, maces.mace, mixed.bastardSword, shields.shield)
    private val gladiatorWeapons = listOf(shortArms.shortSword, cords.gladNet, shields.buckler,
        poles.trident, cords.whip)
    private val assassinWeapons = listOf(shortArms.shortSword, projectiles.miniCrossbow, maces.club,
        projectiles.blowgun, shortArms.stiletto)
    private val soldierWeapons = listOf(projectiles.crossbow, swords.longSword, mixed.halberd, poles.lance,
        shields.shield)
    private val indigenousWeapons = listOf(poles.javelin, poles.lance, shields.fullShield,
        projectiles.shortBow, projectiles.blowgun)
    private val banditWeapons = listOf(shortArms.dagger, projectiles.crossbow, shortArms.shortSword,
        maces.mace, maces.club)

    val allArchetypes = mapOf(
        "barbarian" to barbarianWeapons,
        "ninja" to ninjaWeapons,
        "duel" to duelWeapons,
        "pirate" to pirateWeapons,
        "nomad" to nomadWeapons,
        "hunter" to huntWeapons,
        "knight" to knightWeapons,
        "gladiator" to gladiatorWeapons,
        "assassin" to assassinWeapons,
        "soldier" to soldierWeapons,
        "indigenous" to indigenousWeapons,
        "bandit" to banditWeapons,
        "improvised" to improvised,

        "short" to shortArms.shortArms + mixed.shortAdditions,
        "axe" to axes.axes + mixed.axeAdditions,
        "mace" to maces.maces + mixed.maceAdditions,
        "sword" to swords.swords + mixed.swordAdditions,
        "twoHanded" to twoHanded.twoHanded + mixed.twoHandedAdditions,
        "pole" to poles.poles + mixed.poleAdditions,
        "cord" to cords.cords + mixed.cordAdditions,
        "shield" to shields.shields,
        "projectile" to projectiles.projectiles,
        "thrown" to thrown.thrown
    )

    //get martial arts and style modules
    val martials = MartialArts(charInstance = charInstance)
    val styles = StyleInstances()

    //initialize character's primary weapon
    val primaryWeapon = mutableStateOf(value = unarmed)

    //initialize weapons individually taken and given from group modules
    val individualModules = mutableListOf<Weapon>()

    //list of modules the character has taken
    val takenModules = mutableListOf<List<Weapon>>()
    val fullModWeapons = mutableListOf<Weapon>()

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
    private fun findWeapon(weaponName: String): Weapon {
        //search through all weapons until a match is found
        allWeapons.forEach{ weapon ->
            if(weapon.saveName == weaponName)
                return weapon
        }

        //return unarmed if not found
        return unarmed
    }

    /**
     * Set the character's primary weapon to the inputted string.
     *
     * @param weaponName item to set as the primary weapon
     */
    fun setPrimaryWeapon(weaponName: String){
        //retrieve the weapon item associated with the input
        primaryWeapon.value = findWeapon(weaponName = weaponName)

        //potentially remove the weapon from the purchase list
        individualModules -= primaryWeapon.value
        charInstance.updateTotalSpent()
    }

    /**
     * Give or remove an individual weapon module as indicated by the input.
     *
     * @param weapon weapon module to alter
     * @param toAdd whether to add or remove the module
     */
    fun changeIndividualModule(
        weapon: Weapon,
        toAdd: Boolean
    ){
        //add module if requested and not taken by archetype
        if(toAdd && !fullModWeapons.contains(element = weapon))
            individualModules += weapon
        //remove module if requested
        else if(!toAdd)
            individualModules -= weapon

        //update the points spent by the character
        charInstance.updateTotalSpent()
    }

    /**
     * Changes the character's taken archetype modules as desired.
     *
     * @param weaponCheck archetype module to add or remove
     * @param isAdded whether to add weaponCheck or remove it
     */
    fun updateModulesTaken(
        weaponCheck: List<Weapon>,
        isAdded: Boolean
    ){
        //empty individual weapons from modules list
        fullModWeapons.clear()

        //add or remove module list
        if(isAdded)
            takenModules += listOf(weaponCheck)
        else
            takenModules -= listOf(weaponCheck).toSet()

        //apply weapons to individual weapon list
        takenModules.forEach{list ->
            list.forEach{weapon ->
                fullModWeapons += weapon
            }
        }

        //remove any individual weapon modules from added archetype weapons
        individualModules -= fullModWeapons.toSet()

        charInstance.updateTotalSpent()
    }

    /**
     * Attempts to add or remove a martial art from the character's taken martial arts.
     *
     * @param changeItem martial art to add or remove
     * @param isAdded whether to add or remove the inputted martial art
     * @return true if martial art has been successfully added
     */
    fun changeMartial(
        changeItem: MartialArt,
        isAdded: Boolean
    ): Boolean{
        //if user tries to add the item
        if(isAdded){
            //check that character qualifies for this addition
            if(takenMartialList.size < martialMax.intValue && changeItem.qualification()){
                //add martial art to record
                takenMartialList += changeItem

                //apply capoeira's dodge bonus
                if(changeItem == martials.capoeira)
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
            if(changeItem == martials.capoeira)
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
            if(!martial.qualification())
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
    fun changeStyle(
        style: StyleModule,
        toAdd: Boolean)
    {
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
            if(!styles.exceptions.contains(element = style))
                total += style.cost
        }

        //create dividend for individual weapon module addition
        val classDividend =
            if(charInstance.classes.ownClass.value != charInstance.classes.weaponMaster)
                1
            else
                2

        //apply weaponmaster deductions if applicable
        total /= classDividend

        //retrieve list of individual weapons
        val toCheck = individualModules.toMutableList() - fullModWeapons.toSet()

        //for each individual module taken
        toCheck.forEach{weapon ->
            //if primary weapon is mixed
            if(primaryWeapon.value is MixedWeapon){
                val copyPrime = primaryWeapon.value as MixedWeapon

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
            else if(weapon is MixedWeapon && weapon.mixedType.contains(primaryWeapon.value.type))
                total += 15/classDividend
            //add identical cost for identical type
            else if(weapon.type == primaryWeapon.value.type)
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
                if(charInstance.classes.ownClass.value == charInstance.classes.tao)
                    10
                //if character is primarily unarmed
                else if(primaryWeapon.value == unarmed)
                    25
                //if neither are factors
                else
                    50

            //add 50 for every other martial art taken
            total +=
                if(charInstance.classes.ownClass.value == charInstance.classes.tao) (takenMartialList.size - 1) * 20
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
        if(styleMods.contains(element = styles.magAsAttack)) output += 75
        if(styleMods.contains(element = styles.magAsDefense)) output += 75

        return output
    }

    /**
     * Determines the amount of psychic points spent in this section.
     *
     * @return value of psychic points in this section
     */
    fun calcPointsInPsy(): Int{
        return if (styleMods.contains(element = styles.psyProjection)) 100
            else 0
    }

    /**
     * Retrieves save data to apply to the character for this category.
     *
     * @param fileReader reader of the input for this item
     */
    fun loadProficiencies(fileReader: BufferedReader){
        //get primary weapon
        primaryWeapon.value = findWeapon(weaponName = fileReader.readLine())

        //get each individual weapon module
        for(loopNum in 0 until fileReader.readLine().toInt())
            individualModules += findWeapon(weaponName = fileReader.readLine())

        //get any archetype modules saved
        for(loopNum in 0 until fileReader.readLine().toInt())
            updateModulesTaken(weaponCheck = allArchetypes[fileReader.readLine()]!!, isAdded = true)

        //get any style modules saved
        for(loopNum in 0 until fileReader.readLine().toInt())
            styleMods += styles.getStyle(styleName = fileReader.readLine())!!

        //get any martial arts saved
        for(loopNum in 0 until fileReader.readLine().toInt())
            takenMartialList += listOf(loadMartial(martialName = fileReader.readLine())!!)
    }

    /**
     * Saves the data found in this category.
     *
     * @param byteArray output stream for this item's data
     */
    fun writeProficiencies(byteArray: ByteArrayOutputStream) {
        //save primary weapon data
        writeDataTo(writer = byteArray, input = primaryWeapon.value.saveName)

        //record all individual weapon modules
        writeDataTo(writer = byteArray, input = individualModules.size.toString())
        individualModules.forEach{
            writeDataTo(writer = byteArray, input = it.saveName)
        }

        //record all archetype modules
        writeDataTo(writer = byteArray, input = takenModules.size)
        allArchetypes.forEach{(archName, list) ->
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
        martials.allMartialArts.forEach{martial ->
            if(martialName == martial.saveName)
                return martial
        }

        return null
    }
}