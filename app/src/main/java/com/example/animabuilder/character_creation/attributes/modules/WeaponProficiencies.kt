package com.example.animabuilder.character_creation.attributes.modules

import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.equipment.weapons.AttackType
import com.example.animabuilder.character_creation.equipment.weapons.WeaponAbility
import com.example.animabuilder.character_creation.equipment.weapons.WeaponType
import com.example.animabuilder.character_creation.equipment.weapons.weapon_classes.MixedWeapon
import com.example.animabuilder.character_creation.equipment.weapons.weapon_classes.Weapon
import com.example.animabuilder.character_creation.equipment.weapons.weapon_instances.*
import java.io.BufferedReader
import java.io.Serializable

/**
 * Class that operates on the character's various proficiencies.
 * Holds data on all of the available weapons and archetypes.
 * Holds data on all of the available martial arts.
 * Allows the user to take style modules.
 *
 * @param charInstance object that holds all data on the character
 */

class WeaponProficiencies(private val charInstance: BaseCharacter) : Serializable {
    //create data on the unarmed weapon
    val unarmed = Weapon(
        "Unarmed Combat",
        10,
        20,
        0, null,
        AttackType.Impact, null, WeaponType.Unarmed,
        null, null, null,
        listOf(WeaponAbility.Precision), null,
        "This is not a weapon, of course. Rather, these are the numbers used for a " +
                "character fighting without weapons. The attacks made are made by punching, " +
                "kicking, head-butting, and biting. Fighting unarmed requires the use of the " +
                "whole body, so a character fighting this way cannot apply the rules for attacks " +
                "with other weapons"
    )

    //initialize character's primary weapon
    var primaryWeapon = unarmed

    //initialize weapons individually taken and given from group modules
    val individualModules = mutableListOf<Weapon>()
    val fullModWeapons = mutableListOf<Weapon>()

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

    //get martial arts and style modules
    val martials = MartialArts()
    val styles = StyleInstances()

    //make list of every weapon
    val allWeapons = shortArms.shortArms + axes.axes + maces.maces + swords.swords + twoHanded.twoHanded +
            poles.poles + cords.cords + mixed.mixed + shields.shields + projectiles.projectiles + thrown.thrown + unarmed

    /**
     * Set the character's primary weapon to the inputted string.
     *
     * @param input item to set as the primary weapon
     */
    fun setPrimaryWeapon(input: String){
        primaryWeapon = findWeapon(input)
        individualModules -= primaryWeapon
        charInstance.updateTotalSpent()
    }

    /**
     * Finds the weapon from the given string input.
     *
     * @param weaponName Name of the weapon the caller is looking for
     */
    fun findWeapon(weaponName: String): Weapon {
        //search through all weapons until a match is found
        allWeapons.forEach{
            if(it.name == weaponName)
                return it
        }

        //return unarmed if not found
        return unarmed
    }

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

    val allArchetypes = listOf(
        barbarianWeapons,
        ninjaWeapons,
        duelWeapons,
        pirateWeapons,
        nomadWeapons,
        huntWeapons,
        knightWeapons,
        gladiatorWeapons,
        assassinWeapons,
        soldierWeapons,
        indigenousWeapons,
        banditWeapons,
        improvised,

        shortArms.shortArms,
        axes.axes,
        maces.maces,
        swords.swords,
        twoHanded.twoHanded,
        poles.poles,
        cords.cords,
        shields.shields,
        projectiles.projectiles,
        thrown.thrown
    )

    //list of modules the character has taken
    val takenModules = mutableListOf<List<Weapon>>()

    /**
     * Give or remove an individual weapon module as indicated by the input.
     *
     * @param input weapon module to alter
     * @param toAdd whether to add or remove the module
     */
    fun changeIndividualModule(input: Weapon, toAdd: Boolean){
        //add or remove the module as indicated
        if(toAdd)
            individualModules += input
        else
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

    //initialize list of taken style modules
    val styleMods = mutableListOf<StyleModule>()

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

    //initialize taken martial arts and maximum martial art quantity
    var takenMartialList = mutableListOf<MartialArt>()
    var martialMax = 0

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
            if(takenMartialList.size < martialMax && qualifies(changeItem)){
                takenMartialList += changeItem
                if(changeItem == martials.capoeira)
                    charInstance.combat.dodge.setClassBonus(10)
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
     * Checks whether the given martial art qualifies to be taken by the character.
     *
     * @param input the martial art to check
     * @return true if character qualifies for the martial art
     */
    fun qualifies(input: MartialArt): Boolean{
        when(input){
            martials.kempo, martials.shotokan, martials.sambo, martials.taekwondo -> return true
            martials.capoeira -> return charInstance.secondaryList.dance.total >= 40
            martials.taiChi -> return charInstance.ki.takenAbilities.contains(charInstance.ki.kiRecord.useOfKi)
            martials.kungFu -> return charInstance.secondaryList.acrobatics.total >= 40 &&
                    charInstance.secondaryList.sleightHand.total >= 40 &&
                    charInstance.secondaryList.style.total >= 20
            martials.aikido -> return charInstance.secondaryList.sleightHand.total >= 40
            martials.muayThai, martials.grappling -> return charInstance.secondaryList.strengthFeat.total >= 40

            martials.melkaiah -> return (takenMartialList.contains(martials.grappling) || takenMartialList.contains(martials.sambo)) &&
                    charInstance.ki.takenAbilities.contains(charInstance.ki.kiRecord.inhumanity) &&
                    charInstance.combat.attack.total >= 160 && (charInstance.combat.block.total >= 160 || charInstance.combat.dodge.total >= 160) &&
                    (primaryWeapon == unarmed || individualModules.contains(unarmed))

            martials.seraphite -> return (takenMartialList.contains(martials.shotokan) || takenMartialList.contains(martials.kempo)) &&
                    charInstance.ki.takenAbilities.contains(charInstance.ki.kiRecord.presenceExtrusion) &&
                    charInstance.combat.attack.total >= 180 &&
                    (primaryWeapon == unarmed || individualModules.contains(unarmed))

            martials.dumah -> return (takenMartialList.contains(martials.kempo) || takenMartialList.contains(martials.capoeira)) &&
                    charInstance.ki.takenAbilities.contains(charInstance.ki.kiRecord.presenceExtrusion)

            martials.emp -> return (takenMartialList.contains(martials.kempo) || takenMartialList.contains(martials.taekwondo)) &&
                    charInstance.combat.attack.total >= 200 &&
                    (primaryWeapon == unarmed || individualModules.contains(unarmed))

            martials.enuth -> return (takenMartialList.contains(martials.sambo) || takenMartialList.contains(martials.shotokan)) &&
                    charInstance.combat.attack.total >= 160 && (charInstance.combat.block.total >= 160 || charInstance.combat.dodge.total >= 160) &&
                    (primaryWeapon == unarmed || individualModules.contains(unarmed))

            martials.shephon -> return takenMartialList.contains(martials.aikido) && takenMartialList.contains(martials.kungFu) &&
                    charInstance.ki.takenAbilities.contains(charInstance.ki.kiRecord.kiControl) &&
                    (charInstance.combat.block.total >= 200 || charInstance.combat.dodge.total >= 200) &&
                    (primaryWeapon == unarmed || individualModules.contains(unarmed))

            martials.asakusen -> return takenMartialList.contains(martials.kungFu) &&
                    charInstance.combat.attack.total >= 160 && (charInstance.combat.block.total >= 160 || charInstance.combat.dodge.total >= 160) &&
                    (primaryWeapon == unarmed || individualModules.contains(unarmed))

            martials.velez -> return (takenMartialList.contains(martials.taiChi) || takenMartialList.contains(martials.kungFu)) &&
                    charInstance.ki.takenAbilities.contains(charInstance.ki.kiRecord.presenceExtrusion)

            martials.selene -> return takenMartialList.contains(martials.aikido) &&
                    (charInstance.combat.block.total >= 200 || charInstance.combat.dodge.total >= 200) &&
                    (primaryWeapon == unarmed || individualModules.contains(unarmed))

            martials.hakyoukuken -> return (takenMartialList.contains(martials.shotokan) || takenMartialList.contains(martials.muayThai)) &&
                    charInstance.ki.takenAbilities.contains(charInstance.ki.kiRecord.useOfNecessaryEnergy) &&
                    charInstance.combat.attack.total >= 200 &&
                    (primaryWeapon == unarmed || individualModules.contains(unarmed))

            else -> return false
        }
    }

    /**
     * Check that all taken martial arts still qualify.
     */
    fun doubleCheck() {
        takenMartialList.forEach{
            if(!qualifies(it))
                takenMartialList -= it
        }
    }

    /**
     * Updates the maximum number of martial arts the character can take.
     */
    fun updateMartialMax() {
        //determine martial art number based on attack and highest defense abilities
        martialMax = charInstance.combat.attack.total +
                if (charInstance.combat.block.total > charInstance.combat.dodge.total)
                    charInstance.combat.block.total
                else
                    charInstance.combat.dodge.total
        martialMax /= 40

        //remove martial arts that exceed the new found limit
        if(takenMartialList.size > martialMax)
            takenMartialList.dropLast(takenMartialList.size - martialMax)
    }

    /**
     * Determines the amount of martial knowledge gained from taken martial arts.
     */
    fun mkFromArts(): Int{
        var total = 0

        takenMartialList.forEach{
            total += it.mkBonus
        }

        return total
    }

    /**
     * Determines development points spent in modules, martial arts, and weapon styles.
     */
    fun calculateSpent(): Int{
        //initialize total as number of archetype modules taken
        var total = takenModules.size * 50

        //retrieve list of individual weapons
        val toCheck = individualModules.toMutableList() - fullModWeapons.toSet()

        toCheck.forEach{
            //if primary weapon is mixed
            if(primaryWeapon is MixedWeapon){
                val copyPrime = primaryWeapon as MixedWeapon

                //apply same type for exactly matching weapons
                if(it is MixedWeapon) {
                    if ((copyPrime.mixedType - it.mixedType.toSet()).isEmpty())
                        total += 10

                    //apply mixed type for one matching type
                    else if (copyPrime.mixedType.contains(it.mixedType[0]) ||
                        copyPrime.mixedType.contains(it.mixedType[1])
                    )
                        total += 15
                }

                //apply mixed type for it belonging to one mixed type
                else if(copyPrime.mixedType.contains(it.type))
                    total += 15

                else
                    total += 20
            }

            //if primary weapon is not mixed
            //add mixed cost if secondary is mixed with one matching type
            else if(it is MixedWeapon && it.mixedType.contains(primaryWeapon.type))
                total += 15
            //add identical cost for identical type
            else if(it.type == primaryWeapon.type)
                total += 10

            //add no matching type amount
            else
                total += 20
        }

        //add amount for different style modules
        styleMods.forEach{total += it.cost}

        //if character has martial arts taken
        if(takenMartialList.isNotEmpty()){
            //determine cost of first art depending on
            total +=
                //if character is a Tao
                if(charInstance.ownClass == charInstance.classes.tao)
                    10
                //if character is primarily unarmed
                else if(primaryWeapon == unarmed)
                    25
                //if neither are factors
                else
                    50

            //add 50 for every other martial art taken
            total += (takenMartialList.size - 1) * 50
        }

        return total
    }


    /**
     * Retrieves save data to apply to the character for this category.
     */
    fun loadProficiencies(fileReader: BufferedReader){
        //get primary weapon
        primaryWeapon = findWeapon(fileReader.readLine())

        //get each individual weapon module
        for(loopNum in 0 until fileReader.readLine().toInt())
            individualModules += findWeapon(fileReader.readLine())

        //get any archetype modules saved
        for(loopNum in 0 until fileReader.readLine().toInt())
            updateModulesTaken(allArchetypes[fileReader.readLine().toInt()], true)

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
    fun writeProficiencies(){
        //save primary weapon data
        charInstance.addNewData(primaryWeapon.name)

        //record all individual weapon modules
        charInstance.addNewData(individualModules.size.toString())
        individualModules.forEach{
            charInstance.addNewData(it.name)
        }

        //record all archetype modules
        charInstance.addNewData(takenModules.size)
        takenModules.forEach {
            charInstance.addNewData(allArchetypes.indexOf(it))
        }

        //record all style modules
        charInstance.addNewData(styleMods.size)
        styleMods.forEach{
            charInstance.addNewData(it.name)
        }

        //record all martial arts taken
        charInstance.addNewData(takenMartialList.size)
        takenMartialList.forEach{
            charInstance.addNewData(it.name)
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
            if(name == it.name)
                return it
        }

        return null
    }
}