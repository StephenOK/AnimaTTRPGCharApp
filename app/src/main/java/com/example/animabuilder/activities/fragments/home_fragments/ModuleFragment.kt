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
import com.example.animabuilder.DetailButton
import com.example.animabuilder.InfoRow
import com.example.animabuilder.R
import com.example.animabuilder.character_creation.attributes.modules.MartialArt
import com.example.animabuilder.character_creation.attributes.modules.StyleModule
import com.example.animabuilder.character_creation.equipment.weapons.weapon_classes.Weapon
import com.example.animabuilder.character_creation.equipment.weapons.WeaponAbility
import com.example.animabuilder.character_creation.equipment.weapons.WeaponType
import com.example.animabuilder.character_creation.equipment.weapons.weapon_classes.MixedWeapon
import com.example.animabuilder.character_creation.equipment.weapons.weapon_classes.ProjectileWeapon
import com.example.animabuilder.view_models.ModuleFragmentViewModel

/**
 * Fragment that displays attributes related to weapons and special attacks
 * Weapon, archetype, and style modules are available to purchase
 * Taking archetype modules affect other weapons the character can have
 * Martial arts are available and account for their qualifications when taken
 */

@Composable
fun ModuleFragment(
    modFragVM: ModuleFragmentViewModel,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    updateFunc: () -> Unit
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ){
        //display currently selected primary weapon
        item{Text(text = stringResource(R.string.primaryWeaponLabel) + modFragVM.primaryWeapon.collectAsState().value.name)}

        //display each weapon list button
        items(modFragVM.allWeapons){weaponButton ->
            WeaponListButton(
                modFragVM,
                weaponButton,
                openDetailAlert,
                updateFunc
            )
        }

        //display the unarmed weapon row
        item {
            WeaponRow(
                modFragVM,
                modFragVM.getUnarmed(),
                openDetailAlert,
                updateFunc
            )
        }

        //display archetype, martial art, and style buttons
        item{ArchetypeButton(
            modFragVM,
            openDetailAlert,
            updateFunc
        )}
        item{MartialButton(
            modFragVM,
            openDetailAlert,
            updateFunc
        )}
        item{StyleButton(modFragVM, openDetailAlert, updateFunc)}
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
    modFragVM: ModuleFragmentViewModel,
    button: ModuleFragmentViewModel.WeaponListData,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    updateFunc: () -> Unit
){
    //button for displaying the list
    Button(
        onClick = {button.setListOpen(!button.listOpen.value)},
        modifier = Modifier.width(250.dp)
    ){
        Text(text = stringResource(button.nameRef))
    }

    //revealable list associated with the button
    AnimatedVisibility(
        visible = button.listOpen.collectAsState().value,
    ){
        Column{
            //display whole class module if one is available
            if(button.wholeClass)
                ArchetypeRow(
                    modFragVM,
                    stringResource(button.nameRef) + stringResource(R.string.moduleSuffix),
                    button.items,
                    openDetailAlert,
                    updateFunc
                )

            //display all weapons from the given list
            button.items.forEach{
                WeaponRow(
                    modFragVM,
                    it,
                    openDetailAlert,
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
    modFragVM: ModuleFragmentViewModel,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    updateFunc: () -> Unit
){
    //button for displaying archetype list
    Button(
        onClick = {modFragVM.setArchetypeOpen(!modFragVM.archetypeOpen.value)},
        modifier = Modifier.width(250.dp)
    ){
        Text(text = stringResource(R.string.archetypeLabel))
    }

    //revealable list for the archetypes
    AnimatedVisibility(visible = modFragVM.archetypeOpen.collectAsState().value){
        //show all archetypes available
        Column {
            ArchetypeRow(
                modFragVM,
                stringResource(R.string.barbarianLabel),
                modFragVM.getArchetypes()[0],
                openDetailAlert,
                updateFunc
            )
            ArchetypeRow(
                modFragVM,
                stringResource(R.string.ninjaLabel),
                modFragVM.getArchetypes()[1],
                openDetailAlert,
                updateFunc
            )
            ArchetypeRow(
                modFragVM,
                stringResource(R.string.duelLabel),
                modFragVM.getArchetypes()[2],
                openDetailAlert,
                updateFunc
            )
            ArchetypeRow(
                modFragVM,
                stringResource(R.string.pirateLabel),
                modFragVM.getArchetypes()[3],
                openDetailAlert,
                updateFunc
            )
            ArchetypeRow(
                modFragVM,
                stringResource(R.string.nomadLabel),
                modFragVM.getArchetypes()[4],
                openDetailAlert,
                updateFunc
            )
            ArchetypeRow(
                modFragVM,
                stringResource(R.string.hunterLabel),
                modFragVM.getArchetypes()[5],
                openDetailAlert,
                updateFunc
            )
            ArchetypeRow(
                modFragVM,
                stringResource(R.string.knightLabel),
                modFragVM.getArchetypes()[6],
                openDetailAlert,
                updateFunc
            )
            ArchetypeRow(
                modFragVM,
                stringResource(R.string.gladiatorLabel),
                modFragVM.getArchetypes()[7],
                openDetailAlert,
                updateFunc
            )
            ArchetypeRow(
                modFragVM,
                stringResource(R.string.assassinLabel),
                modFragVM.getArchetypes()[8],
                openDetailAlert,
                updateFunc
            )
            ArchetypeRow(
                modFragVM,
                stringResource(R.string.soldierLabel),
                modFragVM.getArchetypes()[9],
                openDetailAlert,
                updateFunc
            )
            ArchetypeRow(
                modFragVM,
                stringResource(R.string.indigenousLabel),
                modFragVM.getArchetypes()[10],
                openDetailAlert,
                updateFunc
            )
            ArchetypeRow(
                modFragVM,
                stringResource(R.string.banditLabel),
                modFragVM.getArchetypes()[11],
                openDetailAlert,
                updateFunc
            )
            ArchetypeRow(
                modFragVM,
                stringResource(R.string.improvisedLabel),
                modFragVM.getArchetypes()[12],
                openDetailAlert,
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
private fun StyleButton(
    modFragVM: ModuleFragmentViewModel,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    updateFunc: () -> Unit
){
    //button for displaying weapon styles list
    Button(
        onClick = {modFragVM.setStyleOpen(!modFragVM.styleOpen.value)},
        modifier = Modifier.width(250.dp)
    ){
        Text(text = stringResource(R.string.styleModLabel))
    }

    //revealable list of weapon styles
    AnimatedVisibility(visible = modFragVM.styleOpen.collectAsState().value) {
        Column {
            StyleRow(
                modFragVM,
                modFragVM.getAllStyles()[0],
                openDetailAlert,
                updateFunc
            )
            StyleRow(
                modFragVM,
                modFragVM.getAllStyles()[1],
                openDetailAlert,
                updateFunc
            )
            StyleRow(
                modFragVM,
                modFragVM.getAllStyles()[2],
                openDetailAlert,
                updateFunc
            )
            StyleRow(
                modFragVM,
                modFragVM.getAllStyles()[3],
                openDetailAlert,
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
    modFragVM: ModuleFragmentViewModel,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    updateFunc: () -> Unit
){
    //button for displaying martial arts list
    Button(
        onClick = {modFragVM.setMartialOpen(!modFragVM.martialOpen.value)},
        modifier = Modifier.width(250.dp)
    ){
        Text(text = stringResource(R.string.martialArtLabel))
    }

    //revealable list of martial arts
    AnimatedVisibility(visible = modFragVM.martialOpen.collectAsState().value){
        Column{
            //display number of marital arts character can take
            Text(text = stringResource(R.string.maxMartialLabel) + modFragVM.getMartialMax().toString())

            modFragVM.getAllMartials().forEach{
                MartialArtRow(
                    modFragVM,
                    it,
                    openDetailAlert,
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
    modFragVM: ModuleFragmentViewModel,
    input: Weapon,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    updateFunc: () -> Unit
){
    Row(verticalAlignment = Alignment.CenterVertically){
        //primary checkbox item
        Checkbox(
            checked = input == modFragVM.primaryWeapon.collectAsState().value,
            onCheckedChange = {
                //update character's primary weapon
                modFragVM.setPrimaryWeapon(input)

                //update character's associated values
                updateFunc()
            },
            modifier = Modifier.weight(0.1f)
        )

        //secondary checkbox item
        Checkbox(
            checked = modFragVM.allSecondaryWeapons[input]!!.value ||
                        modFragVM.archetypesHasWeapon(input),
            onCheckedChange = {
                //if primary check is not taken
                if(input != modFragVM.primaryWeapon.value)
                    //perform appropriate action for input
                    modFragVM.changeIndividualModule(input, it)

                updateFunc()
            },
            modifier = Modifier.weight(0.1f)
        )

        //display weapon name
        Text(text = input.name, modifier = Modifier.weight(0.6f))

        //create a button to display the weapon's details
        DetailButton(
            onClick = {openDetailAlert(input.name) @Composable{WeaponContents(input)}},
            modifier = Modifier.weight(0.2f)
        )
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
    modFragVM: ModuleFragmentViewModel,
    title: String,
    modList: List<Weapon>,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    updateFunc: () -> Unit
){
    Row(verticalAlignment = Alignment.CenterVertically){
        Spacer(modifier = Modifier.weight(0.1f))
        Checkbox(
            checked = modFragVM.allArchetypes[modList]!!.value,
            onCheckedChange = {
                //add weapon list to character
                modFragVM.setModuleTaken(modList, it)

                updateFunc()
            },
            modifier = Modifier.weight(0.1f)
        )

        //display archetype name and cost
        Text(text = title, modifier = Modifier.weight(0.4f))
        Text(text = stringResource(R.string.fiftyPointCost), textAlign = TextAlign.Center, modifier = Modifier.weight(0.2f))

        //create a button to display the archetype's contents
        DetailButton(
            onClick = {openDetailAlert(title) @Composable {ArchetypeContents(modList)}},
            modifier = Modifier.weight(0.2f)
        )
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
    modFragVM: ModuleFragmentViewModel,
    style: StyleModule,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    updateFunc: () -> Unit
){
    Row(verticalAlignment = Alignment.CenterVertically){
        Spacer(modifier = Modifier.weight(0.1f))
        Checkbox(
            checked = modFragVM.allStyles[style]!!.value,
            onCheckedChange = {
                //add or remove style as needed
                modFragVM.changeStyle(style, it)

                updateFunc()
            },
            modifier = Modifier.weight(0.1f)
        )

        //display style's name and cost
        Text(text = style.name, modifier = Modifier.weight(0.4f))
        Text(text = style.cost.toString(), modifier = Modifier.weight(0.2f))

        //make display button for style's details
        DetailButton(
            onClick = {openDetailAlert(style.name) @Composable {Text(text = style.description)}},
            modifier = Modifier.weight(0.2f)
        )
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
    modFragVM: ModuleFragmentViewModel,
    martialArt: MartialArt,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    updateFunc: () -> Unit
){
    Row(verticalAlignment = Alignment.CenterVertically){
        Checkbox(
            checked = modFragVM.allMartials[martialArt]!!.value,
            onCheckedChange = {
                //check that martial art can be taken and grant if able
                modFragVM.changeMartial(martialArt, it)
                updateFunc()
            },
            modifier = Modifier.weight(0.1f)
        )

        //display name and bonus martial knowledge
        Text(text = martialArt.name, modifier = Modifier.weight(0.5f))
        Text(text = "+" + martialArt.mkBonus.toString() + " " + stringResource(R.string.mkLabel), modifier = Modifier.weight(0.2f))

        //display details button for the martial art
        DetailButton(
            onClick = {openDetailAlert(martialArt.name) @Composable {MartialContents(martialArt.prereqList, martialArt.description)}},
            modifier = Modifier.weight(0.2f)
        )
    }
}

//contents for the weapon's details
val WeaponContents = @Composable
{input: Weapon ->
    Column {
        //display either damage or own strength value
        if(input.damage != null)
            InfoRow(stringResource(R.string.damageLabel), input.damage.toString())
        else
            InfoRow(stringResource(R.string.strengthLabel), input.ownStrength.toString())

        //display weapon's speed
        InfoRow(stringResource(R.string.speedLabel), input.speed.toString())

        //display one-handed strength if available
        if(input.oneHandStr != null)
            InfoRow(stringResource(R.string.oneHandedLabel), input.oneHandStr.toString())

        //display two-handed strength if available
        if(input.twoHandStr != null)
            InfoRow(stringResource(R.string.twoHandedLabel), input.twoHandStr.toString())

        //display primary attack type
        if(input.primaryType != null)
            InfoRow(stringResource(R.string.damageTypeLabel), input.primaryType.name)

        //display secondary attack type
        if(input.secondaryType != null)
            InfoRow(stringResource(R.string.secondaryTypeLabel), input.secondaryType.name)

        //display weapon category
        if(input is MixedWeapon)
            InfoRow(stringResource(R.string.weaponTypeLabel), input.mixedType[0].name + "/" + input.mixedType[1].name)
        else
            InfoRow(stringResource(R.string.weaponTypeLabel), input.type.name)

        //display weapon's fortitude, breakage, and presence
        InfoRow(stringResource(R.string.fortitudeLabel), input.fortitude.toString())
        if(input.breakage != null)
            InfoRow(stringResource(R.string.breakageLabel), input.breakage.toString())
        InfoRow(stringResource(R.string.presenceLabel) + ": ", input.presence.toString())

        //display weapon's rate of fire, reload rate, and range
        if(input is ProjectileWeapon) {
            if (input.type == WeaponType.Throwing)
                InfoRow(stringResource(R.string.fireRateLabel), input.reloadOrRate.toString())
            else
                InfoRow(stringResource(R.string.reloadLabel), input.reloadOrRate.toString())
            if (input.range != null)
                InfoRow(stringResource(R.string.rangeLabel), input.range.toString() + "m")
        }

        //initialize ability output and loop counter
        var abilityString = ""
        var counter = 0

        //if there are abilities given
        if(input.ability != null){
            input.ability.forEach{
                //add ability to the output string
                abilityString += it.name

                //display strength of trapping ability
                if(it == WeaponAbility.Trapping)
                    abilityString += "(" + input.ownStrength + ")"

                //delimit the separation between abilities
                if(counter < input.ability.count() - 1){
                    abilityString += "/"
                    counter++
                }
            }

            //show ability string
            InfoRow(stringResource(R.string.abilityLabel), abilityString)
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
        InfoRow(stringResource(R.string.prereqLabel), preReqList)
        Text(text = description)
    }
}