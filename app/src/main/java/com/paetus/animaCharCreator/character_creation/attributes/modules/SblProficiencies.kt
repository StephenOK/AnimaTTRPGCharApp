package com.paetus.animaCharCreator.character_creation.attributes.modules

import com.paetus.animaCharCreator.character_creation.SblChar
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.Weapon

/**
 * Class that operates on the SBL character's various modules.
 * Holds data on all of the available weapons and archetypes.
 * Holds data on all of the available martial arts.
 * Allows the user to take style modules.
 *
 * @param charInstance object that holds all data on the character
 */
class SblProficiencies(
    val charInstance: SblChar
): WeaponProficiencies(charInstance = charInstance){
    /**
     * Set the character's primary weapon to the inputted string.
     *
     * @param weaponName item to set as the primary weapon
     */
    override fun setPrimaryWeapon(weaponName: String) {
        //only run if character's level is 0
        if(charInstance.lvl.intValue == 0) {
            //set primary weapon as normal
            super.setPrimaryWeapon(weaponName = weaponName)

            //apply change to all available character level records
            charInstance.levelLoop(endLevel = 20) {
                it.weaponProficiencies.setPrimaryWeapon(weaponName = weaponName)
            }
        }

        //update DP spent
        charInstance.updateTotalSpent()
    }

    /**
     * Give or remove an individual weapon module as indicated bby the input.
     *
     * @param weapon weapon module to alter
     * @param toAdd true if adding module; false if removing
     */
    override fun changeIndividualModule(
        weapon: Weapon,
        toAdd: Boolean
    ){
        //add module if requested and not taken by archetype
        if(toAdd && !individualModules.contains(weapon)) {
            //update this level record's individual module
            charInstance.getCharAtLevel().weaponProficiencies.changeIndividualModule(weapon, true)

            //remove module added in any future levels
            charInstance.levelLoop(startLevel = charInstance.lvl.intValue + 1, endLevel = 20){
                it.weaponProficiencies.changeIndividualModule(weapon = weapon, toAdd = false)
            }
        }
        //remove module if requested
        else if (!toAdd && individualModules.contains(weapon))
            charInstance.getCharAtLevel().weaponProficiencies.changeIndividualModule(weapon, false)

        //update character's held weapons
        weaponUpdate()
    }

    /**
     * Changes the character's taken archetype modules as desired.
     *
     * @param weaponCheck archetype module to add or remove
     * @param isAdded whether to add weaponCheck or remove it
     */
    override fun updateModulesTaken(
        weaponCheck: List<Weapon>,
        isAdded: Boolean
    ) {
        //update the archetype's taken state in the level record
        charInstance.getCharAtLevel().weaponProficiencies.updateModulesTaken(weaponCheck, isAdded)

        //update the archetypes taken
        archetypeUpdate()
    }

    /**
     * Updates this item's weapon list.
     */
    private fun weaponUpdate(){
        //clear current individual modules taken
        individualModules.clear()

        //get individual modules taken from each level
        charInstance.levelLoop{character ->
            character.weaponProficiencies.individualModules.forEach{
                if(!individualModules.contains(it)) individualModules.add(it)
            }
        }

        //update total DP spent
        charInstance.updateTotalSpent()
    }

    /**
     * Updates this item's archetype list.
     */
    private fun archetypeUpdate(){
        //clear current archetype modules taken
        takenModules.clear()

        //get archetype modules taken from each level
        charInstance.levelLoop{character ->
            character.weaponProficiencies.takenModules.forEach{
                if(!takenModules.contains(it)) takenModules.add(it)
            }
        }

        //update individual weapons taken
        weaponUpdate()

        //update total DP spent
        charInstance.updateTotalSpent()
    }

    /**
     * Update this item on a character's level change.
     */
    fun levelUpdate(){
        archetypeUpdate()
    }
}