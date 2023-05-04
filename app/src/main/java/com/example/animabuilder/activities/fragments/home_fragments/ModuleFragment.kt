package com.example.animabuilder.activities.fragments.home_fragments

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animabuilder.DetailButton
import com.example.animabuilder.InfoRow
import com.example.animabuilder.R
import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.attributes.modules.MartialArt
import com.example.animabuilder.character_creation.attributes.modules.StyleModule
import com.example.animabuilder.character_creation.equipment.weapons.weapon_classes.Weapon
import com.example.animabuilder.character_creation.equipment.weapons.WeaponAbility
import com.example.animabuilder.character_creation.equipment.weapons.WeaponType
import com.example.animabuilder.character_creation.equipment.weapons.weapon_classes.MixedWeapon
import com.example.animabuilder.character_creation.equipment.weapons.weapon_classes.ProjectileWeapon
import com.example.animabuilder.view_models.models.HomePageViewModel
import com.example.animabuilder.view_models.models.ModuleFragmentViewModel

/**
 * Fragment that displays attributes related to weapons and special attacks.
 * Weapon, archetype, and style modules are available to purchase.
 * Taking archetype modules affect other weapons the character can have.
 * Martial arts are available and account for their qualifications when taken.
 *
 * @param modFragVM viewModel to run with this fragment
 * @param openDetailAlert function to run when looking at an item's details
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
fun ModuleFragment(
    modFragVM: ModuleFragmentViewModel,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    homePageVM: HomePageViewModel
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(
                top = 15.dp,
                bottom = 15.dp,
                start = 30.dp,
                end = 30.dp
            )
    ){
        //display currently selected primary weapon
        item{
            Text(
                text = stringResource(R.string.primaryWeaponLabel) + "\n" +
                        modFragVM.primaryWeapon.collectAsState().value.name,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }

        //display the unarmed weapon row
        item {
            WeaponRow(
                modFragVM,
                modFragVM.getUnarmed(),
                openDetailAlert,
                homePageVM
            )
        }

        //display each weapon list button
        items(modFragVM.allWeapons){weaponButton ->
            WeaponListButton(
                modFragVM,
                weaponButton,
                openDetailAlert,
                homePageVM
            )
        }

        item{Spacer(Modifier.height(25.dp))}

        //display archetype, martial art, and style buttons
        item{ArchetypeButton(
            modFragVM,
            openDetailAlert,
            homePageVM
        )}

        item{Spacer(Modifier.height(25.dp))}

        item{MartialButton(
            modFragVM,
            openDetailAlert,
            homePageVM
        )}

        item{Spacer(Modifier.height(25.dp))}

        item{StyleButton(modFragVM, openDetailAlert, homePageVM)}
    }
}

/**
 * Displays a list of available weapons that a character can take modules for.
 * Also displays whole weapon module if there is one available.
 *
 * @param modFragVM viewModel that manages the data for this page
 * @param weaponData information regarding this type of weapon
 * @param openDetailAlert function to run when opening a weapon's details
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
private fun WeaponListButton(
    modFragVM: ModuleFragmentViewModel,
    weaponData: ModuleFragmentViewModel.WeaponListData,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    homePageVM: HomePageViewModel
){
    //button for displaying the list
    Button(
        onClick = {weaponData.toggleListOpen()},
        modifier = Modifier
            .fillMaxWidth(0.8f)
    ){
        Text(text = stringResource(weaponData.nameRef))
    }

    //revealable list associated with the button
    AnimatedVisibility(
        visible = weaponData.listOpen.collectAsState().value,
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            //display whole class module if one is available
            if(weaponData.wholeClass)
                ArchetypeRow(
                    weaponData.weaponArchetype,
                    openDetailAlert,
                    homePageVM
                )

            //display all weapons from the given list
            weaponData.items.forEach{
                WeaponRow(
                    modFragVM,
                    it,
                    openDetailAlert,
                    homePageVM
                )
            }
        }
    }
}

/**
 * Button that reveals a list of all archetype modules the character can take.
 *
 * @param modFragVM viewModel that manages the data for this page
 * @param openDetailAlert function to run when looking at an archetype's details
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
private fun ArchetypeButton(
    modFragVM: ModuleFragmentViewModel,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    homePageVM: HomePageViewModel
){
    //button for displaying archetype list
    Button(
        onClick = {modFragVM.toggleArchetypeOpen() },
        modifier = Modifier
            .fillMaxWidth(0.8f)
    ){
        Text(text = stringResource(R.string.archetypeLabel))
    }

    //revealable list for the archetypes
    AnimatedVisibility(visible = modFragVM.archetypeOpen.collectAsState().value){
        //show all archetypes available
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            modFragVM.allArchetypeData.forEach{
                ArchetypeRow(
                    it,
                    openDetailAlert,
                    homePageVM
                )
            }
        }
    }
}

/**
 * Button that reveals a list of all weapon styles the character can take.
 *
 * @param modFragVM viewModel that manages the data for this page
 * @param openDetailAlert function to run when looking at the style's details
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
private fun StyleButton(
    modFragVM: ModuleFragmentViewModel,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    homePageVM: HomePageViewModel
){
    //button for displaying weapon styles list
    Button(
        onClick = {modFragVM.toggleStyleOpen() },
        modifier = Modifier
            .fillMaxWidth(0.8f)
    ){
        Text(text = stringResource(R.string.styleModLabel))
    }

    //revealable list of weapon styles
    AnimatedVisibility(visible = modFragVM.styleOpen.collectAsState().value) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            modFragVM.getAllStyles().forEach{
                StyleRow(
                    modFragVM,
                    it,
                    openDetailAlert,
                    homePageVM
                )
            }
        }
    }
}

/**
 * Button that reveals the martial arts available to the player.
 *
 * @param modFragVM viewModel that manages this page's data
 * @param openDetailAlert function to run when looking at a martial art's details
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
private fun MartialButton(
    modFragVM: ModuleFragmentViewModel,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    homePageVM: HomePageViewModel
){
    //button for displaying martial arts list
    Button(
        onClick = {modFragVM.toggleMartialOpen() },
        modifier = Modifier
            .fillMaxWidth(0.8f)
    ){
        Text(
            text = stringResource(R.string.martialArtLabel)
        )
    }

    //revealable list of martial arts
    AnimatedVisibility(visible = modFragVM.martialOpen.collectAsState().value){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            //display number of marital arts character can take
            Text(
                text = stringResource(R.string.maxMartialLabel) + " " +
                    modFragVM.getMartialMax().toString(),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )

            //display a row for each martial art
            modFragVM.getAllMartials().forEach{
                MartialArtRow(
                    modFragVM,
                    it,
                    openDetailAlert,
                    homePageVM
                )
            }
        }
    }
}

/**
 * Row that displays the qualities of the given weapon.
 *
 * @param modFragVM viewModel that manages the data for this page
 * @param input weapon to display info on
 * @param openDetailAlert function to run when looking at a weapon's details
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
private fun WeaponRow(
    modFragVM: ModuleFragmentViewModel,
    input: Weapon,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    homePageVM: HomePageViewModel
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        //primary checkbox item
        Checkbox(
            checked = input == modFragVM.primaryWeapon.collectAsState().value,
            onCheckedChange = {
                //update character's primary weapon
                modFragVM.setPrimaryWeapon(input)

                //update character's associated values
                homePageVM.updateExpenditures()
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

                homePageVM.updateExpenditures()
            },
            modifier = Modifier.weight(0.1f)
        )

        //display weapon name
        Text(
            text = input.name,
            modifier = Modifier
                .weight(0.55f),
            textAlign = TextAlign.Center
        )

        //create a button to display the weapon's details
        DetailButton(
            onClick = {openDetailAlert(input.name) @Composable{WeaponContents(input)}},
            modifier = Modifier.weight(0.25f)
        )
    }
}

/**
 * Creates a row that displays data on an archetype module.
 *
 * @param item data on the inputted archetype
 * @param openDetailAlert function to run when looking at an archetype's details
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
private fun ArchetypeRow(
    item: ModuleFragmentViewModel.ArchetypeData,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    homePageVM: HomePageViewModel
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        val itemString = LocalContext.current.getString(item.name) + " " +
                LocalContext.current.resources.getString(R.string.moduleSuffix)

        Spacer(modifier = Modifier.weight(0.1f))
        Checkbox(
            checked = item.takenCheck.collectAsState().value,
            onCheckedChange = {
                //add weapon list to character
                item.toggleCheck()

                homePageVM.updateExpenditures()
            },
            modifier = Modifier.weight(0.1f)
        )

        //display archetype name and cost
        Text(
            text = itemString,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(0.35f)
        )

        Text(
            text = stringResource(R.string.fiftyPointCost),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(0.2f)
        )

        //create a button to display the archetype's contents
        DetailButton(
            onClick = {openDetailAlert(itemString) @Composable {ArchetypeContents(item.items)}},
            modifier = Modifier.weight(0.25f)
        )
    }
}

/**
 * Creates a row that displays the information for a weapon style module.
 *
 * @param modFragVM viewModel that manages the data for this page
 * @param style displayed item
 * @param openDetailAlert function to run when looking at the style's details
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
private fun StyleRow(
    modFragVM: ModuleFragmentViewModel,
    style: StyleModule,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    homePageVM: HomePageViewModel
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Checkbox(
            checked = modFragVM.allStyles[style]!!.value,
            onCheckedChange = {
                //add or remove style as indicated
                modFragVM.changeStyle(style, it)

                homePageVM.updateExpenditures()
            },
            modifier = Modifier.weight(0.1f)
        )

        //display style's name and cost
        Text(
            text = style.name,
            modifier = Modifier
                .weight(0.4f),
            textAlign = TextAlign.Center
        )
        Text(
            text = style.cost.toString(),
            modifier = Modifier
                .weight(0.2f),
            textAlign = TextAlign.Center
        )

        //make display button for style's details
        DetailButton(
            onClick = {openDetailAlert(style.name) @Composable {Text(text = style.description)}},
            modifier = Modifier.weight(0.2f)
        )
    }
}

/**
 * Creates a row that displays information on a martial art.
 *
 * @param modFragVM viewModel that manages the data for this page
 * @param martialArt item to be displayed
 * @param openDetailAlert function to run when looking at the martial art's details
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
private fun MartialArtRow(
    modFragVM: ModuleFragmentViewModel,
    martialArt: MartialArt,
    openDetailAlert: (String, @Composable () -> Unit) -> Unit,
    homePageVM: HomePageViewModel
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Checkbox(
            checked = modFragVM.allMartials[martialArt]!!.value,
            onCheckedChange = {
                //attempt to implement the user's desired change
                modFragVM.changeMartial(martialArt, it)
                homePageVM.updateExpenditures()
            },
            modifier = Modifier.weight(0.1f)
        )

        //display name and bonus martial knowledge of this martial art
        Text(
            text = martialArt.name,
            modifier = Modifier
                .weight(0.45f),
            textAlign = TextAlign.Center
        )
        Text(
            text = "+" + martialArt.mkBonus.toString() + " " + stringResource(R.string.mkLabel),
            modifier = Modifier
                .weight(0.2f),
            textAlign = TextAlign.Center
        )

        //display details button for the martial art
        DetailButton(
            onClick = {openDetailAlert(martialArt.name) @Composable {MartialContents(martialArt.prereqList, martialArt.description)}},
            modifier = Modifier.weight(0.25f)
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

@Preview
@Composable
fun ModulePreview(){
    val charInstance = BaseCharacter()

    val moduleFragVM = ModuleFragmentViewModel(charInstance.weaponProficiencies)
    moduleFragVM.allWeapons[1].toggleListOpen()
    moduleFragVM.toggleArchetypeOpen()

    val homePageVM = HomePageViewModel(charInstance)

    ModuleFragment(moduleFragVM, {_, _ -> }, homePageVM)
}