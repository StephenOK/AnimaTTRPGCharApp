package com.example.animabuilder.activities.fragments.home_fragments

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.animabuilder.R
import com.example.animabuilder.activities.*
import com.example.animabuilder.character_creation.equipment.weapons.MartialArt
import com.example.animabuilder.character_creation.equipment.weapons.Weapon
import com.example.animabuilder.character_creation.equipment.weapons.WeaponAbility
import com.example.animabuilder.character_creation.equipment.weapons.WeaponType


/**
 * Fragment that displays attributes related to weapons and special attacks
 * Weapon, archetype, and style modules are available to purchase
 * Taking archetype modules affect other weapons the character can have
 * Martial arts are available and account for their qualifications when taken
 */

@Composable
fun CombatFragment(
    updateFunc: () -> Unit
) {
    val primaryWeapon = remember{mutableStateOf(charInstance.weaponProficiencies.primaryWeapon)}
    val allSecondaries = mutableMapOf<Weapon, MutableState<Boolean>>()
    val allMartials = mutableMapOf<MartialArt, MutableState<Boolean>>()
    val allPrimaryBoxes = mutableListOf<MutableState<Boolean>>()

    //create list of weapon button data
    val weaponButtonList = mutableListOf<WeaponListData>()
    weaponButtonList.add(WeaponListData(
        "Short",
        charInstance.weaponProficiencies.shortArms,
        true
    ))
    weaponButtonList.add(WeaponListData(
        "Axes",
        charInstance.weaponProficiencies.axes,
        true
    ))
    weaponButtonList.add(WeaponListData(
        "Maces",
        charInstance.weaponProficiencies.maces,
        true
    ))
    weaponButtonList.add(WeaponListData(
        "Swords",
        charInstance.weaponProficiencies.swords,
        true
    ))
    weaponButtonList.add(WeaponListData(
        "Two-Handed",
        charInstance.weaponProficiencies.twoHanded,
        true
    ))
    weaponButtonList.add(WeaponListData(
        "Pole",
        charInstance.weaponProficiencies.poles,
        true
    ))
    weaponButtonList.add(WeaponListData(
        "Cords",
        charInstance.weaponProficiencies.cords,
        true
    ))
    weaponButtonList.add(WeaponListData(
        "Mixed",
        charInstance.weaponProficiencies.mixed,
        false
    ))
    weaponButtonList.add(WeaponListData(
        "Shields",
        charInstance.weaponProficiencies.shields,
        true
    ))
    weaponButtonList.add(WeaponListData(
        "Projectile",
        charInstance.weaponProficiencies.projectiles,
        true
    ))
    weaponButtonList.add(WeaponListData(
        "Thrown",
        charInstance.weaponProficiencies.thrown,
        true
    ))

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ){
        //display currently selected primary weapon
        item{Text(text = "Current Primary Weapon: " + primaryWeapon.value.name)}

        //display each weapon list button
        items(weaponButtonList){weaponButton ->
            WeaponListButton(
                weaponButton,
                primaryWeapon.value,
                allPrimaryBoxes,
                allSecondaries,
                allMartials,
                { input: Weapon -> primaryWeapon.value = input},
                updateFunc
            )
        }

        //display the unarmed weapon row
        item {
            WeaponRow(
                charInstance.weaponProficiencies.unarmed,
                primaryWeapon.value,
                allPrimaryBoxes,
                allSecondaries,
                allMartials,
                {input: Weapon -> primaryWeapon.value = input},
                updateFunc
            )
        }

        //display archetype, martial art, and style buttons
        item{ArchetypeButton(allSecondaries, updateFunc)}
        item{MartialButton(allMartials, updateFunc)}
        item{StyleButton(updateFunc)}
    }
}

/**
 * Displays a list of available weapons that a character can take modules for
 * Also displays whole weapon module if there is one available
 *

 * updateFunc: bottom bar update function
 */
@Composable
private fun WeaponListButton(
    button: WeaponListData,
    primaryWeapon: Weapon,
    allPrimaryBoxes: MutableList<MutableState<Boolean>>,
    allSecondaries: MutableMap<Weapon, MutableState<Boolean>>,
    allMartials: MutableMap<MartialArt, MutableState<Boolean>>,
    primaryUpdate: (Weapon) -> Unit,
    updateFunc: () -> Unit
){
    //initialize list's open state
    val open = remember{ mutableStateOf(false) }

    //button for displaying the list
    Button(
        onClick = {open.value = !open.value},
        modifier = Modifier.width(250.dp)
    ){
        Text(text = button.name)
    }

    //revealable list associated with the button
    AnimatedVisibility(
        visible = open.value,
    ){
        Column{
            //display whole class module if one is available
            if(button.wholeClass)
                ArchetypeRow(button.name + " Module", button.options, allSecondaries, updateFunc)

            //display all weapons from the given list
            button.options.forEach{
                WeaponRow(
                    it,
                    primaryWeapon,
                    allPrimaryBoxes,
                    allSecondaries,
                    allMartials,
                    primaryUpdate,
                    updateFunc
                )
            }
        }
    }
}

/**
 * Button that reveals a list of all archetype modules the character can take
 *
 * updateFunc: bottom bar update function
 */
@Composable
private fun ArchetypeButton(
    allSecondaries: MutableMap<Weapon, MutableState<Boolean>>,
    updateFunc: () -> Unit
){
    //initialize open state
    val open = remember{mutableStateOf(false)}

    //button for displaying archetype list
    Button(
        onClick = {open.value = !open.value},
        modifier = Modifier.width(250.dp)
    ){
        Text(text = "Archetype Modules")
    }

    //revealable list for the archetypes
    AnimatedVisibility(visible = open.value){
        //show all archetypes available
        Column {
            ArchetypeRow(
                "Barbarian",
                charInstance.weaponProficiencies.barbarianWeapons,
                allSecondaries,
                updateFunc
            )
            ArchetypeRow(
                "Ninja",
                charInstance.weaponProficiencies.ninjaWeapons,
                allSecondaries,
                updateFunc
            )
            ArchetypeRow(
                "Duel",
                charInstance.weaponProficiencies.duelWeapons,
                allSecondaries,
                updateFunc
            )
            ArchetypeRow(
                "Pirate",
                charInstance.weaponProficiencies.pirateWeapons,
                allSecondaries,
                updateFunc
            )
            ArchetypeRow(
                "Nomad",
                charInstance.weaponProficiencies.nomadWeapons,
                allSecondaries,
                updateFunc
            )
            ArchetypeRow(
                "Hunter",
                charInstance.weaponProficiencies.huntWeapons,
                allSecondaries,
                updateFunc
            )
            ArchetypeRow(
                "Knight",
                charInstance.weaponProficiencies.knightWeapons,
                allSecondaries,
                updateFunc
            )
            ArchetypeRow(
                "Gladiator",
                charInstance.weaponProficiencies.gladiatorWeapons,
                allSecondaries,
                updateFunc
            )
            ArchetypeRow(
                "Assassin",
                charInstance.weaponProficiencies.assassinWeapons,
                allSecondaries,
                updateFunc
            )
            ArchetypeRow(
                "Soldier",
                charInstance.weaponProficiencies.soldierWeapons,
                allSecondaries,
                updateFunc
            )
            ArchetypeRow(
                "Indigenous",
                charInstance.weaponProficiencies.indigenousWeapons,
                allSecondaries,
                updateFunc
            )
            ArchetypeRow(
                "Bandit",
                charInstance.weaponProficiencies.banditWeapons,
                allSecondaries,
                updateFunc
            )
            ArchetypeRow(
                "Improvised",
                charInstance.weaponProficiencies.improvised,
                allSecondaries,
                updateFunc
            )
        }
    }
}

/**
 * Button that reveals a list of all weapon styles the character can take
 *
 * updateFunc: bottom bar update function
 */
@Composable
private fun StyleButton(updateFunc: () -> Unit){
    //initialize open state
    val open = remember{mutableStateOf(false)}

    //button for displaying weapon styles list
    Button(
        onClick = {open.value = !open.value},
        modifier = Modifier.width(250.dp)
    ){
        Text(text = "Style Modules")
    }

    //revealable list of weapon styles
    AnimatedVisibility(visible = open.value) {
        Column {
            StyleRow(
                stringResource(R.string.battoIai),
                "This skill permits a character to unsheathe his weapon with perfect ease. " +
                        "The character can unsheathe his weapon without applying the -25 penalty to " +
                        "the Attack or Block abilities. It has no effect for two-handed weapons.",
                "30 DP",
                updateFunc
            )
            StyleRow(
                stringResource(R.string.area),
                "The character specializes in broad maneuvers that can take out various " +
                        "enemies with greater ease. This reduces the penalty for an Area Attack " +
                        "maneuver by half. Therefore a character applies a -25 to his attack ability " +
                        "when using this attack.",
                "40 DP",
                updateFunc
            )
            StyleRow(
                stringResource(R.string.precision),
                "The character has a marked ability to put his adversary in a Menace " +
                        "Position. This reduces the penalty for a Put at Weapon's Point maneuver by " +
                        "half. Therefore a character applies -50 to his attack ability when using this attack.",
                "50 DP",
                updateFunc
            )
            StyleRow(
                stringResource(R.string.disarming),
                "A character with this ability has specialized in disarming his opponents. " +
                        "This reduces the penalty for a Disarm maneuver to -20.",
                "40 DP",
                updateFunc
            )
        }
    }
}

/**
 * Button that reveals the martial arts available to the player
 *
 * updateFunc: bottom bar update function
 */
@Composable
private fun MartialButton(
    allMartials: MutableMap<MartialArt, MutableState<Boolean>>,
    updateFunc: () -> Unit
){
    //initialize open state
    val open = remember{mutableStateOf(false)}

    //button for displaying martial arts list
    Button(
        onClick = {open.value = !open.value},
        modifier = Modifier.width(250.dp)
    ){
        Text(text = "Martial Arts")
    }

    //revealable list of martial arts
    AnimatedVisibility(visible = open.value){
        Column{
            //display number of marital arts character can take
            Text(text = "Max Martial Arts: " + charInstance.weaponProficiencies.martialMax)

            charInstance.weaponProficiencies.allMartialArts.forEach{
                MartialArtRow(
                    it,
                    allMartials,
                    updateFunc
                )
            }
        }
    }
}

/**
 * Row that displays the qualities of the given weapon
 *
 * input: weapon to display info on
 * updateFunc: bottom bar update function
 */
@Composable
private fun WeaponRow(
    input: Weapon,
    primaryWeapon: Weapon,
    allPrimaryBoxes: MutableList<MutableState<Boolean>>,
    allSecondaries: MutableMap<Weapon, MutableState<Boolean>>,
    allMartials: MutableMap<MartialArt, MutableState<Boolean>>,
    primaryUpdate: (Weapon) -> Unit,
    updateFunc: () -> Unit
){
    //checkboxes for when the weapon is taken as a primary, secondary, or archetype proficiency
    val primeCheck = remember{mutableStateOf(input == primaryWeapon)}
    val secondCheck = remember{mutableStateOf(
        charInstance.weaponProficiencies.individualModules.contains(input) ||
                charInstance.weaponProficiencies.fullModWeapons.contains(input))}

    //add these boxes to the appropriate collection
    allPrimaryBoxes += primeCheck
    allSecondaries += Pair(input, secondCheck)

    Row(verticalAlignment = Alignment.CenterVertically){
        //primary checkbox item
        Checkbox(
            checked = primeCheck.value,
            onCheckedChange = {
                //remove any other primary check
                allPrimaryBoxes.forEach{
                    it.value = false
                }
                //set this as the primary weapon
                primeCheck.value = true

                //if weapon was selected as a secondary weapon and isn't from an archetype module
                if(secondCheck.value && !charInstance.weaponProficiencies.fullModWeapons.contains(input)) {
                    //remove secondary weapon check
                    secondCheck.value = false
                    charInstance.weaponProficiencies.individualModules -= input
                }

                //update character's primary weapon
                primaryUpdate(input)
                charInstance.weaponProficiencies.primaryWeapon = primaryWeapon

                //update character's associated values
                charInstance.updateTotalSpent()
                martialUpdate(allMartials)
                updateFunc()
            },
            modifier = Modifier.weight(0.1f)
        )

        //secondary checkbox item
        Checkbox(
            checked = secondCheck.value,
            onCheckedChange = {
                //keep check if from an archetype
                if(charInstance.weaponProficiencies.fullModWeapons.contains(input)){
                    secondCheck.value = true
                }
                //if primary check is not taken
                else if(!primeCheck.value) {
                    //change secondary check
                    secondCheck.value = it

                    //perform appropriate action for input
                    if (secondCheck.value)
                        charInstance.weaponProficiencies.individualModules += input
                    else
                        charInstance.weaponProficiencies.individualModules -= input

                    //update totals
                    charInstance.updateTotalSpent()
                }

                //update other factors in character
                martialUpdate(allMartials)
                updateFunc()
            },
            modifier = Modifier.weight(0.1f)
        )

        //display weapon name
        Text(text = input.name, modifier = Modifier.weight(0.6f))

        //create a button to display the weapon's details
        TextButton(
            onClick = {
                detailAlertOn.value = true
                detailItem.value = input.name
                contents.value = @Composable{WeaponContents(input)}
            },
            modifier = Modifier.weight(0.2f)
        ) {
            Text(text = "Details")
        }
    }
}

/**
 * Creates a row that displays data on an archetype module
 *
 * title: name of the archetype
 * modList: list of weapons in the associated mod
 * updateFunc: bottom bar update function
 */
@Composable
private fun ArchetypeRow(
    title: String,
    modList: List<Weapon>,
    allSecondaries: MutableMap<Weapon, MutableState<Boolean>>,
    updateFunc: () -> Unit
){
    //initialize checked boolean
    val isTaken = remember{mutableStateOf(charInstance.weaponProficiencies.takenModules.contains(modList))}

    Row(verticalAlignment = Alignment.CenterVertically){
        Spacer(modifier = Modifier.weight(0.1f))
        Checkbox(
            checked = isTaken.value,
            onCheckedChange = {
                //toggle checkbox
                isTaken.value = it

                //add weapon list to character
                charInstance.weaponProficiencies.updateModulesTaken(modList, isTaken.value)

                //for each secondary checkbox
                allSecondaries.forEach{ (input, display) ->
                    //check or uncheck depending on if it is in the archetype
                    if(charInstance.weaponProficiencies.fullModWeapons.contains(input))
                        display.value = true
                    else if(!charInstance.weaponProficiencies.individualModules.contains(input) && display.value)
                        display.value = false
                }

                //update spent values
                charInstance.updateTotalSpent()
                updateFunc()
            },
            modifier = Modifier.weight(0.1f)
        )

        //display archetype name and cost
        Text(text = title, modifier = Modifier.weight(0.4f))
        Text(text = "50 DP", textAlign = TextAlign.Center, modifier = Modifier.weight(0.2f))

        //create a button to display the archetype's contents
        TextButton(
            onClick = {
                detailAlertOn.value = true
                detailItem.value = title
                contents.value = @Composable{ArchetypeContents(modList)}
            },
            modifier = Modifier.weight(0.2f)
        ) {
            Text(text = "Details")
        }
    }
}

/**
 * Creates a row that displays the information for a weapon style module
 *
 * title: name of the style
 * description: details on the style's effect
 * cost: string of the development points needed for the style
 * updateFunc: bottom bar update function
 */
@Composable
private fun StyleRow(
    title: String,
    description: String,
    cost: String,
    updateFunc: () -> Unit
){
    //boolean for the checkbox of this style module
    val hasStyle = remember{ mutableStateOf(charInstance.weaponProficiencies.styleMods.contains(title)) }

    Row(verticalAlignment = Alignment.CenterVertically){
        Spacer(modifier = Modifier.weight(0.1f))
        Checkbox(
            checked = hasStyle.value,
            onCheckedChange = {
                //toggle style checkbox
                hasStyle.value = it

                //add or remove style as needed
                if(hasStyle.value)
                    charInstance.weaponProficiencies.styleMods += title
                else
                    charInstance.weaponProficiencies.styleMods -= title

                //update character's development points
                charInstance.updateTotalSpent()
                updateFunc()
            },
            modifier = Modifier.weight(0.1f)
        )

        //display style's name and cost
        Text(text = title, modifier = Modifier.weight(0.4f))
        Text(text = cost, modifier = Modifier.weight(0.2f))

        //make display button for style's details
        TextButton(
            onClick = {
                detailItem.value = title
                contents.value = @Composable{Text(text = description)}
                detailAlertOn.value = true
            },
            modifier = Modifier.weight(0.2f)
        ) {
            Text(text = "Details")
        }
    }
}

/**
 * Creates a row that displays information on a martial art
 *
 * martialArt: item to be displayed
 * updateFunc: bottom bar update function
 */
@Composable
private fun MartialArtRow(
    martialArt: MartialArt,
    allMartials: MutableMap<MartialArt, MutableState<Boolean>>,
    updateFunc: () -> Unit
){
    //initialize checkbox value and add it to the collection
    val martialTaken = remember{mutableStateOf(charInstance.weaponProficiencies.takenMartialList.contains(martialArt))}
    allMartials += Pair(martialArt, martialTaken)

    Row(verticalAlignment = Alignment.CenterVertically){
        Checkbox(
            checked = martialTaken.value,
            onCheckedChange = {
                //check that martial art can be taken and grant if able
                if(charInstance.weaponProficiencies.changeMartial(martialArt, it))
                    martialTaken.value = it

                //update development point expenditure
                charInstance.updateTotalSpent()
                martialUpdate(allMartials)
                updateFunc()
            },
            modifier = Modifier.weight(0.1f)
        )

        //display name and bonus martial knowledge
        Text(text = martialArt.name, modifier = Modifier.weight(0.5f))
        Text(text = "+" + martialArt.mkBonus.toString() + " MK", modifier = Modifier.weight(0.2f))

        //display details button for the martial art
        TextButton(onClick = {
            detailAlertOn.value = true
            detailItem.value = martialArt.name
            contents.value = @Composable{MartialContents(martialArt.prereqList, martialArt.description)}
            },
            modifier = Modifier.weight(0.2f)
        ) {
            Text(text = "Details")
        }
    }
}

/**
 * Updates martial list in case any of its prerequisites have been removed
 */
fun martialUpdate(allMartials: MutableMap<MartialArt, MutableState<Boolean>>) {
    //for each martial art checkbox
    allMartials.forEach{ (input, display) ->
        //check that the character still has this martial art
        display.value = charInstance.weaponProficiencies.takenMartialList.contains(input)
    }
}

//contents for the weapon's details
val WeaponContents = @Composable
{input: Weapon ->
    Column {
        //display either damage or own strength value
        if(input.damage != null)
            InfoRow("Damage:", input.damage.toString())
        else
            InfoRow("Strength:", input.ownStrength.toString())

        //display weapon's speed
        InfoRow("Speed:", input.speed.toString())

        //display one-handed strength if available
        if(input.oneHandStr != null)
            InfoRow("One-Handed Str:", input.oneHandStr.toString())

        //display two-handed strength if available
        if(input.twoHandStr != null)
            InfoRow("Two-Handed Str:", input.twoHandStr.toString())

        //display primary attack type
        if(input.primaryType != null)
            InfoRow("Weapon Type:", input.primaryType!!.name)

        //display secondary attack type
        if(input.secondaryType != null)
            InfoRow("Secondary Type:", input.secondaryType!!.name)

        //display weapon category
        if(input.type == WeaponType.Mixed)
            InfoRow("Weapon Type:", input.mixedType!![0].name + "/" + input.mixedType!![1].name)
        else
            InfoRow("WeaponType:", input.type.name)

        //display weapon's fortitude, breakage, and presence
        if(input.fortitude != null)
            InfoRow("Fortitude:", input.fortitude.toString())
        if(input.breakage != null)
            InfoRow("Breakage:", input.breakage.toString())
        if(input.presence != null)
            InfoRow("Presence:", input.presence.toString())

        //display weapon's rate of fire, reload rate, and range
        if(input.rateOfFire != null)
            InfoRow("Fire Rate:", input.rateOfFire.toString())
        if(input.reload != null)
            InfoRow("Reload:", input.reload.toString())
        if(input.range != null)
            InfoRow("Range", input.range.toString() + "m")

        //initialize ability output and loop counter
        var abilityString = ""
        var counter = 0

        //if there are abilities given
        if(input.ability != null){
            input.ability!!.forEach{
                //add ability to the output string
                abilityString += it.name

                //display strength of trapping ability
                if(it == WeaponAbility.Trapping)
                    abilityString += "(" + input.ownStrength + ")"

                //delimit the separation between abilities
                if(counter < input.ability!!.count() - 1){
                    abilityString += "/"
                    counter++
                }
            }

            //show ability string
            InfoRow("Abilities:", abilityString)
        }

        //show description
        Text(text = input.description)
    }
}

//contents for the archetype's description
val ArchetypeContents = @Composable
{detailList: List<Weapon> ->
    Column {
        //display each weapon's name
        detailList.forEach {
            Text(text = it.name)
        }
    }
}

//contents for the martial art's description
val MartialContents = @Composable
{preReqList: String, description: String ->
    Column{
        //show art's prerequisites and description
        InfoRow("PreRequisites", preReqList)
        Text(text = description)
    }
}

/**
 * Holds the data for a single weapon list button
 *
 * name: the type of weapon displayed after the button is clicked
 * options: the list of weapons to be displayed
 * wholeClass: whether the given class has a whole class module or not
 */
private data class WeaponListData(
    val name: String,
    val options: List<Weapon>,
    val wholeClass: Boolean
)