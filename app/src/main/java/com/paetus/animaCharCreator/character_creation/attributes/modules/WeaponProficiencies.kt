package com.paetus.animaCharCreator.character_creation.attributes.modules

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
        "unarmed",
        R.string.unarmed,
        10,
        20,
        0, null,
        AttackType.Impact, null, WeaponType.Unarmed,
        null, null, null,
        listOf(WeaponAbility.Precision), null,
        R.string.unarmedDesc
    )

    //make list of every weapon
    val allWeapons = shortArms.shortArms + axes.axes + maces.maces + swords.swords + twoHanded.twoHanded +
            poles.poles + cords.cords + mixed.mixed + shields.shields + projectiles.projectiles + thrown.thrown + unarmed

    //gather all archetype modules
    val improvised = listOf(shortArms.brokenBottle, twoHanded.chair, shortArms.kitchenKnife,
        maces.hammer, axes.hoe, maces.metalBar, shortArms.pick, shortArms.sickle, maces.torch,
        maces.vase, maces.woodenPole, axes.woodAxe)
    val barbarianWeapons = listOf(mixed.twoHandAxe, axes.battleAxe, twoHanded.twoHandSword,
        mixed.bastardSword, mixed.heavyBattleMace)
    val ninjaWeapons = listOf(swords.katana, shortArms.tanto, shortArms.claws, shortArms.shuriken,
        mixed.kusariGama)
    val duelWeapons = listOf(swords.rapier, mixed.foil, shortArms.parryDagger, shortArms.dagger,
        swords.saber, swords.longSword)
    val pirateWeapons = listOf(poles.harpoon, cords.gladNet, shortArms.hook, swords.saber, axes.handAxe)
    val nomadWeapons = listOf(shortArms.dagger, thrown.chakram, projectiles.longBow,
        swords.scimitar, poles.lance)
    val huntWeapons = listOf(poles.javelin, projectiles.shortBow, shortArms.shortSword, poles.lance,
        thrown.bolas)
    val knightWeapons = listOf(swords.longSword, poles.cavLance, maces.mace, mixed.bastardSword, shields.shield)
    val gladiatorWeapons = listOf(shortArms.shortSword, cords.gladNet, shields.buckler,
        poles.trident, cords.whip)
    val assassinWeapons = listOf(shortArms.shortSword, projectiles.miniCrossbow, maces.club,
        projectiles.blowgun, shortArms.stiletto)
    val soldierWeapons = listOf(projectiles.crossbow, swords.longSword, mixed.halberd, poles.lance,
        shields.shield)
    val indigenousWeapons = listOf(poles.javelin, poles.lance, shields.fullShield,
        projectiles.shortBow, projectiles.blowgun)
    val banditWeapons = listOf(shortArms.dagger, projectiles.crossbow, shortArms.shortSword,
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
    val martials = MartialArts(charInstance)
    val styles = StyleInstances()

    //initialize character's primary weapon
    val primaryWeapon = mutableStateOf(unarmed)

    //initialize weapons individually taken and given from group modules
    val individualModules = mutableListOf<Weapon>()

    //list of modules the character has taken
    val takenModules = mutableListOf<List<Weapon>>()
    val fullModWeapons = mutableListOf<Weapon>()

    //initialize taken martial arts and maximum martial art quantity
    val takenMartialList = mutableListOf<MartialArt>()
    val martialMax = mutableStateOf(0)

    //initialize list of taken style modules
    val styleMods = mutableListOf<StyleModule>()

    /**
     * Finds the weapon from the given string input.
     *
     * @param weaponName Name of the weapon the caller is looking for
     */
    fun findWeapon(weaponName: String): Weapon {
        //search through all weapons until a match is found
        allWeapons.forEach{
            if(it.saveName == weaponName)
                return it
        }

        //return unarmed if not found
        return unarmed
    }

    /**
     * Set the character's primary weapon to the inputted string.
     *
     * @param input item to set as the primary weapon
     */
    fun setPrimaryWeapon(input: String){
        primaryWeapon.value = findWeapon(input)
        individualModules -= primaryWeapon.value
        charInstance.updateTotalSpent()
    }

    /**
     * Give or remove an individual weapon module as indicated by the input.
     *
     * @param input weapon module to alter
     * @param toAdd whether to add or remove the module
     */
    fun changeIndividualModule(input: Weapon, toAdd: Boolean){
        //add module if requested and not taken by archetype
        if(toAdd && !fullModWeapons.contains(input))
            individualModules += input
        //remove module if requested
        else if(!toAdd)
            individualModules -= input

        //update the points spent by the character
        charInstance.updateTotalSpent()
    }

    /**
     * Changes the character's taken archetype modules as desired.
     *
     * @param weaponCheck archetype module to add or remove
     * @param isAdded whether to add weaponCheck or remove it
     */
    fun updateModulesTaken(weaponCheck: List<Weapon>, isAdded: Boolean){
        //empty individual weapons from modules list
        fullModWeapons.clear()

        //add or remove module list
        if(isAdded)
            takenModules += listOf(weaponCheck)
        else
            takenModules -= listOf(weaponCheck).toSet()

        //apply weapons to individual weapon list
        takenModules.forEach{list ->
            list.forEach{
                fullModWeapons += it
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
     * @param isInput whether to add or remove the inputted martial art
     */
    fun changeMartial(changeItem: MartialArt, isInput: Boolean): Boolean{
        //if user tries to add the item
        if(isInput){
            //check that character qualifies for this addition
            if(takenMartialList.size < martialMax.value && changeItem.qualification()){
                //add martial art to record
                takenMartialList += changeItem

                //apply capoeira's dodge bonus
                if(changeItem == martials.capoeira)
                    charInstance.combat.dodge.setClassBonus(10)

                charInstance.updateTotalSpent()
                return true
            }
        }
        //always allow martial art removal
        else {
            takenMartialList -= changeItem
            if(changeItem == martials.capoeira)
                charInstance.combat.dodge.setClassBonus(-10)
        }

        //update martial knowledge accordingly
        charInstance.ki.updateMK()
        charInstance.updateTotalSpent()

        return false
    }

    /**
     * Check that all taken martial arts still qualify.
     */
    fun doubleCheck() {
        takenMartialList.forEach{
            if(!it.qualification())
                takenMartialList -= it
        }
    }

    /**
     * Updates the maximum number of martial arts the character can take.
     */
    fun updateMartialMax() {
        //determine martial art number based on attack and highest defense abilities
        martialMax.value = charInstance.combat.attack.total.value +
                if (charInstance.combat.block.total.value > charInstance.combat.dodge.total.value)
                    charInstance.combat.block.total.value
                else
                    charInstance.combat.dodge.total.value
        martialMax.value /= 40

        //remove martial arts that exceed the new found limit
        if(takenMartialList.size > martialMax.value)
            takenMartialList.dropLast(takenMartialList.size - martialMax.value)
    }

    /**
     * Determines the amount of martial knowledge gained from taken martial arts.
     */
    fun mkFromArts(): Int{
        //initialize output
        var total = 0

        //add bonus for each taken martial art
        takenMartialList.forEach{
            total += it.mkBonus
        }

        //give output
        return total
    }

    /**
     * Acquire or remove the indicated style.
     *
     * @param input style to alter with this action
     * @param toAdd whether the style is being added or removed
     */
    fun changeStyle(input: StyleModule, toAdd: Boolean){
        //add or remove the style as indicated
        if(toAdd)
            styleMods += input
        else
            styleMods -= input

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
        styleMods.forEach{
            if(!styles.exceptions.contains(it))
                total += it.cost
        }

        //apply weaponmaster deductions if applicable
        if(charInstance.ownClass.value == charInstance.classes.weaponMaster)
            total /= 2

        //create dividend for individual weapon module addition
        val classDividend =
            if(charInstance.ownClass.value != charInstance.classes.weaponMaster)
                1
            else
                2

        //retrieve list of individual weapons
        val toCheck = individualModules.toMutableList() - fullModWeapons.toSet()

        //for each individual module taken
        toCheck.forEach{
            //if primary weapon is mixed
            if(primaryWeapon.value is MixedWeapon){
                val copyPrime = primaryWeapon.value as MixedWeapon

                //apply same type for exactly matching weapons
                if(it is MixedWeapon) {
                    if ((copyPrime.mixedType - it.mixedType.toSet()).isEmpty())
                        total += 10/classDividend

                    //apply mixed type for one matching type
                    else if (copyPrime.mixedType.contains(it.mixedType[0]) ||
                        copyPrime.mixedType.contains(it.mixedType[1])
                    )
                        total += 15/classDividend
                }

                //apply mixed type for it belonging to one mixed type
                else if(copyPrime.mixedType.contains(it.type))
                    total += 15/classDividend

                else
                    total += 20/classDividend
            }

            //if primary weapon is not mixed
            //add mixed cost if secondary is mixed with one matching type
            else if(it is MixedWeapon && it.mixedType.contains(primaryWeapon.value.type))
                total += 15/classDividend
            //add identical cost for identical type
            else if(it.type == primaryWeapon.value.type)
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
                if(charInstance.ownClass.value == charInstance.classes.tao)
                    10
                //if character is primarily unarmed
                else if(primaryWeapon.value == unarmed)
                    25
                //if neither are factors
                else
                    50

            //add 50 for every other martial art taken
            total +=
                if(charInstance.ownClass.value == charInstance.classes.tao) (takenMartialList.size - 1) * 20
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
        if(styleMods.contains(styles.magAsAttack)) output += 75
        if(styleMods.contains(styles.magAsDefense)) output += 75

        return output
    }

    /**
     * Determines the amount of psychic points spent in this section.
     *
     * @return value of psychic points in this section
     */
    fun calcPointsInPsy(): Int{
        return if (styleMods.contains(styles.psyProjection)) 100
            else 0
    }

    /**
     * Retrieves save data to apply to the character for this category.
     */
    fun loadProficiencies(fileReader: BufferedReader){
        //get primary weapon
        primaryWeapon.value = findWeapon(fileReader.readLine())

        //get each individual weapon module
        for(loopNum in 0 until fileReader.readLine().toInt())
            individualModules += findWeapon(fileReader.readLine())

        //get any archetype modules saved
        for(loopNum in 0 until fileReader.readLine().toInt())
            updateModulesTaken(allArchetypes[fileReader.readLine()]!!, true)

        //get any style modules saved
        for(loopNum in 0 until fileReader.readLine().toInt())
            styleMods += styles.getStyle(fileReader.readLine())!!

        //get any martial arts saved
        for(loopNum in 0 until fileReader.readLine().toInt())
            takenMartialList += listOf(loadMartial(fileReader.readLine())!!)
    }

    /**
     * Saves the data found in this category.
     */
    fun writeProficiencies(byteArray: ByteArrayOutputStream) {
        //save primary weapon data
        writeDataTo(byteArray, primaryWeapon.value.saveName)

        //record all individual weapon modules
        writeDataTo(byteArray, individualModules.size.toString())
        individualModules.forEach{
            writeDataTo(byteArray, it.saveName)
        }

        //record all archetype modules
        writeDataTo(byteArray, takenModules.size)
        allArchetypes.forEach{
            if(takenModules.contains(it.value)) writeDataTo(byteArray, it.key)
        }

        //record all style modules
        writeDataTo(byteArray, styleMods.size)
        styleMods.forEach{
            writeDataTo(byteArray, it.saveName)
        }

        //record all martial arts taken
        writeDataTo(byteArray, takenMartialList.size)
        takenMartialList.forEach{
            writeDataTo(byteArray, it.saveName)
        }
    }

    /**
     * Finds a martial art of the given name.
     *
     * @param name martial art to find
     * @return the desired martial art if found
     */
    fun loadMartial(name: String): MartialArt?{
        martials.allMartialArts.forEach{
            if(name == it.saveName)
                return it
        }

        return null
    }
}