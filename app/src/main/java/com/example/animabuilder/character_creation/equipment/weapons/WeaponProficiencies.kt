package com.example.animabuilder.character_creation.equipment.weapons

import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.attributes.class_objects.ClassName
import com.example.animabuilder.character_creation.equipment.weapons.weapon_classes.Weapon
import com.example.animabuilder.character_creation.equipment.weapons.weapon_instances.*
import java.io.BufferedReader
import java.io.Serializable

/**
 * Class that operates on the character's various proficiencies
 * Holds data on all of the available weapons
 * Holds data on all of the available martial arts
 */

class WeaponProficiencies(private val charInstance: BaseCharacter) : Serializable {

    val unarmed = Weapon(
        "Unarmed Combat",
        10,
        20,
        0, null,
        AttackType.Impact, null,
        WeaponType.Unarmed, null,
        null, null, null,
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
    val martials = MartialArts()

    //make list of every weapon
    val allWeapons = shortArms.shortArms + axes.axes + maces.maces + swords.swords + twoHanded.twoHanded +
            poles.poles + cords.cords + mixed.mixed + shields.shields + projectiles.projectiles + thrown.thrown

    /**
     * Finds the weapon from the given string input
     *
     * weaponName: Name of the weapon the caller is looking for
     */
    fun findWeapon(weaponName: String): Weapon {
        //search through all weapons until match is found
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

    //list of modules the character has taken
    val takenModules = mutableListOf<List<Weapon>>()

    /**
     * Changes the character's taken weapon modules as desired
     *
     * weaponCheck: module to add or remove
     * isAdded: whether to add weaponCheck or remove it
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

        //remove any individual weapon module costs from added archetype weapons
        individualModules -= fullModWeapons.toSet()
    }

    //initialize list of taken style modules
    val styleMods = mutableListOf<String>()

    //initialize taken martial arts and maximum martial art quantity
    var takenMartialList = mutableListOf<MartialArt>()
    var martialMax = 0

    /**
     * Attempts to add or remove a martial art from the character's taken martial arts
     *
     * changeItem: martial art to add or remove
     * isInput: whether to add or remove the inputted martial art
     */
    fun changeMartial(changeItem: MartialArt, isInput: Boolean): Boolean{
        //if user tries to add the item
        if(isInput){
            //check that character qualifies for this addition
            if(takenMartialList.size >= martialMax || !qualifies(changeItem))
                return false
            else
                takenMartialList += changeItem
        }
        //always allow martial art removal
        else
            takenMartialList -= changeItem

        //update martial knowledge accordingly
        charInstance.updateMK()

        //return successful process
        return true
    }

    /**
     * Checks whether the given martial art qualifies to be taken by the character
     *
     * input: the martial art to check
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
                    charInstance.attack >= 160 && (charInstance.block >= 160 || charInstance.dodge >= 160) &&
                    (primaryWeapon == unarmed || individualModules.contains(unarmed))

            martials.seraphite -> return (takenMartialList.contains(martials.shotokan) || takenMartialList.contains(martials.kempo)) &&
                    charInstance.ki.takenAbilities.contains(charInstance.ki.kiRecord.presenceExtrusion) &&
                    charInstance.attack >= 180 &&
                    (primaryWeapon == unarmed || individualModules.contains(unarmed))

            martials.dumah -> return (takenMartialList.contains(martials.kempo) || takenMartialList.contains(martials.capoeira)) &&
                    charInstance.ki.takenAbilities.contains(charInstance.ki.kiRecord.presenceExtrusion)

            martials.emp -> return (takenMartialList.contains(martials.kempo) || takenMartialList.contains(martials.taekwondo)) &&
                    charInstance.attack >= 200 &&
                    (primaryWeapon == unarmed || individualModules.contains(unarmed))

            martials.enuth -> return (takenMartialList.contains(martials.sambo) || takenMartialList.contains(martials.shotokan)) &&
                    charInstance.attack >= 160 && (charInstance.block >= 160 || charInstance.dodge >= 160) &&
                    (primaryWeapon == unarmed || individualModules.contains(unarmed))

            martials.shephon -> return takenMartialList.contains(martials.aikido) && takenMartialList.contains(martials.kungFu) &&
                    charInstance.ki.takenAbilities.contains(charInstance.ki.kiRecord.kiControl) &&
                    (charInstance.block >= 200 || charInstance.dodge >= 200) &&
                    (primaryWeapon == unarmed || individualModules.contains(unarmed))

            martials.asakusen -> return takenMartialList.contains(martials.kungFu) &&
                    charInstance.attack >= 160 && (charInstance.block >= 160 || charInstance.dodge >= 160) &&
                    (primaryWeapon == unarmed || individualModules.contains(unarmed))

            martials.velez -> return (takenMartialList.contains(martials.taiChi) || takenMartialList.contains(martials.kungFu)) &&
                    charInstance.ki.takenAbilities.contains(charInstance.ki.kiRecord.presenceExtrusion)

            martials.selene -> return takenMartialList.contains(martials.aikido) &&
                    (charInstance.block >= 200 || charInstance.dodge >= 200) &&
                    (primaryWeapon == unarmed || individualModules.contains(unarmed))

            martials.hakyoukuken -> return (takenMartialList.contains(martials.shotokan) || takenMartialList.contains(martials.muayThai)) &&
                    charInstance.ki.takenAbilities.contains(charInstance.ki.kiRecord.useOfNecessaryEnergy) &&
                    charInstance.attack >= 200 &&
                    (primaryWeapon == unarmed || individualModules.contains(unarmed))

            else -> return false
        }
    }

    /**
     * Check that all taken martial arts still qualify
     */
    fun doubleCheck() {
        takenMartialList.forEach{
            if(!qualifies(it))
                takenMartialList -= it
        }
    }

    /**
     * Updates the maximum number of martial arts the character can take
     */
    fun updateMartialMax() {
        //determine martial art number based on attack and highest defense abilities
        martialMax = charInstance.attack +
                if (charInstance.block > charInstance.dodge)
                    charInstance.block
                else
                    charInstance.dodge
        martialMax /= 40

        //remove martial arts that exceed the new found limit
        if(takenMartialList.size > martialMax)
            takenMartialList.dropLast(takenMartialList.size - martialMax)
    }

    /**
     * Determines the amount of martial knowledge gained from taken martial arts
     */
    fun mkFromArts(): Int{
        var total = 0

        takenMartialList.forEach{
            total += it.mkBonus
        }

        return total
    }

    /**
     * Determines development points spent in modules, martial arts, and weapon styles
     */
    fun calculateSpent(): Int{
        //initialize total as number of archetype modules taken
        var total = takenModules.size * 50

        //retrieve list of individual weapons
        val toCheck = individualModules.toMutableList() - fullModWeapons.toSet()

        toCheck.forEach{
            //if primary weapon is mixed
            if(primaryWeapon.type == WeaponType.Mixed){
                //apply same type for exactly matching weapons
                if(it.type == WeaponType.Mixed) {
                    if ((primaryWeapon.mixedType!! - it.mixedType).isEmpty())
                        total += 10

                    //apply mixed type for one matching type
                    else if (primaryWeapon.mixedType!!.contains(it.mixedType!![0]) ||
                        primaryWeapon.mixedType!!.contains(it.mixedType!![1])
                    )
                        total += 15
                }

                //apply mixed type for it belonging to one mixed type
                else if(primaryWeapon.mixedType!!.contains(it.type))
                    total += 15

                else
                    total += 20
            }

            //if primary weapon is not mixed
            //add mixed cost if secondary is mixed with one matching type
            else if(it.type == WeaponType.Mixed && it.mixedType!!.contains(primaryWeapon.type))
                total += 15
            //add identical cost for identical type
            else if(it.type == primaryWeapon.type)
                total += 10

            //add no matching type amount
            else
                total += 20
        }

        //add amount for different style modules
        styleMods.forEach{
            total += when(it){
                "Batto Jutsu/Iai Jutsu"-> 30
                "Area Attack",
                "Disarming Attack" -> 40
                "Precision Attack" -> 50
                else -> 0
            }
        }

        //if character has martial arts taken
        if(takenMartialList.isNotEmpty()){
            //determine cost of first art depending on
            total +=
                //if character is a Tao
                if(charInstance.ownClass.heldClass == ClassName.tao)
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
     * Retrieves save data to apply to the character for this category
     */
    fun loadProficiencies(fileReader: BufferedReader){
        primaryWeapon = findWeapon(fileReader.readLine())

        var loops = fileReader.readLine().toInt()

        for(loopNum in 1 .. loops)
            individualModules += findWeapon(fileReader.readLine())

        loops = fileReader.readLine().toInt()

        for(loopNum in 1 .. loops)
            loadModule(fileReader)

        loops = fileReader.readLine().toInt()

        for(loopNum in 1 .. loops)
            styleMods += fileReader.readLine()

        loops = fileReader.readLine().toInt()

        for(loopNum in 1 .. loops)
            takenMartialList += listOf(loadMartial(fileReader.readLine())!!)
    }

    /**
     * Saves the data found in this category
     */
    fun writeProficiencies(charInstance: BaseCharacter){
        charInstance.addNewData(primaryWeapon.name)

        charInstance.addNewData(individualModules.size.toString())
        individualModules.forEach{
            charInstance.addNewData(it.name)
        }

        charInstance.addNewData(takenModules.size)
        writeModules()

        charInstance.addNewData(styleMods.size)
        styleMods.forEach{
            charInstance.addNewData(it)
        }

        charInstance.addNewData(takenMartialList.size)
        takenMartialList.forEach{
            charInstance.addNewData(it.name)
        }
    }

    /**
     * Converts a loaded number into an archetype module
     */
    private fun loadModule(fileReader: BufferedReader){
        val addList = when(fileReader.readLine().toInt()){
            0 -> shortArms.shortArms
            1 -> axes.axes
            2 -> maces.maces
            3 -> swords.swords
            4 -> twoHanded.twoHanded
            5 -> poles.poles
            6 -> cords.cords
            7 -> shields.shields
            8 -> projectiles.projectiles
            9 -> thrown.thrown
            10 -> barbarianWeapons
            11 -> ninjaWeapons
            12 -> duelWeapons
            13 -> pirateWeapons
            14 -> nomadWeapons
            15 -> huntWeapons
            16 -> knightWeapons
            17 -> gladiatorWeapons
            18 -> assassinWeapons
            19 -> soldierWeapons
            20 -> indigenousWeapons
            21 -> banditWeapons
            22 -> improvised
            else -> listOf()
        }

        updateModulesTaken(addList, true)
    }

    /**
     * Converts a taken archetype module into a number to save
     */
    private fun writeModules() {
        takenModules.forEach{
            val listNum = when(it){
                shortArms.shortArms -> "0"
                axes.axes -> "1"
                maces.maces -> "2"
                swords.swords -> "3"
                twoHanded.twoHanded -> "4"
                poles.poles -> "5"
                cords.cords -> "6"
                shields.shields -> "7"
                projectiles.projectiles -> "8"
                thrown.thrown -> "9"
                barbarianWeapons -> "10"
                ninjaWeapons -> "11"
                duelWeapons -> "12"
                pirateWeapons -> "13"
                nomadWeapons -> "14"
                huntWeapons -> "15"
                knightWeapons -> "16"
                gladiatorWeapons -> "17"
                assassinWeapons -> "18"
                soldierWeapons -> "19"
                indigenousWeapons -> "20"
                banditWeapons -> "21"
                improvised -> "22"
                else -> "23"
            }

            charInstance.addNewData(listNum)
        }
    }

    /**
     * Finds a martial art of the given name
     *
     * name: martial art to find
     */
    fun loadMartial(name: String): MartialArt?{
        martials.allMartialArts.forEach{
            if(name == it.name)
                return it
        }

        return null
    }
}