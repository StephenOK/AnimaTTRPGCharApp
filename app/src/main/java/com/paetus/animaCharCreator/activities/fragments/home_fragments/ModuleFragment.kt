package com.paetus.animaCharCreator.activities.fragments.home_fragments

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paetus.animaCharCreator.composables.DetailButton
import com.paetus.animaCharCreator.composables.GeneralCard
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.activities.fragments.dialogs.DetailAlert
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.character_creation.attributes.modules.MartialArt
import com.paetus.animaCharCreator.character_creation.attributes.modules.StyleModule
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.Weapon
import com.paetus.animaCharCreator.textScrollUp
import com.paetus.animaCharCreator.view_models.models.HomePageViewModel
import com.paetus.animaCharCreator.view_models.models.ModuleFragmentViewModel

/**
 * Fragment that displays attributes related to weapons and special attacks.
 * Weapon, archetype, and style modules are available to purchase.
 * Taking archetype modules affect other weapons the character can have.
 * Martial arts are available and account for their qualifications when taken.
 *
 * @param modFragVM viewModel to run with this fragment
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
fun ModuleFragment(
    modFragVM: ModuleFragmentViewModel,
    homePageVM: HomePageViewModel
){
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 30.dp,
                end = 30.dp
            )
    ){
        item{Spacer(modifier = Modifier.height(15.dp))}

        item{
            GeneralCard{
                //primary weapon label
                Text(
                    text = stringResource(id = R.string.primaryWeaponLabel),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )

                //display currently selected primary weapon
                AnimatedContent(
                    targetState = stringResource(id = modFragVM.primaryWeapon.collectAsState().value.name),
                    modifier = Modifier
                        .fillMaxWidth(),
                    transitionSpec = textScrollUp,
                    label = "primaryWeapon"
                ) {
                    Text(
                        text = it,
                        textAlign = TextAlign.Center,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        item{Spacer(modifier = Modifier.height(5.dp))}

        //display the unarmed weapon row
        item{
            GeneralCard{
                WeaponRow(
                    weapon = modFragVM.getUnarmed(),
                    modFragVM = modFragVM,
                    homePageVM = homePageVM
                )
            }
        }

        item{Spacer(modifier = Modifier.height(10.dp))}

        //display each weapon list button
        items(modFragVM.allWeapons){weaponButton ->
            WeaponListButton(
                weaponList = weaponButton,
                modFragVM = modFragVM,
                homePageVM = homePageVM
            )
        }

        item{Spacer(modifier = Modifier.height(25.dp))}

        //display archetype options
        item{ArchetypeButton(modFragVM = modFragVM, homePageVM = homePageVM)}
        item{Spacer(modifier = Modifier.height(25.dp))}

        //display martial art options
        item{MartialButton(modFragVM = modFragVM, homePageVM = homePageVM)}
        item{Spacer(modifier = Modifier.height(25.dp))}

        //display style options
        item{StyleButton(modFragVM = modFragVM, homePageVM = homePageVM)}
        item{Spacer(modifier = Modifier.height(15.dp))}
    }

    //show the requested item's details
    if(modFragVM.detailAlertOpen.collectAsState().value)
        DetailAlert(
            title = modFragVM.detailName.collectAsState().value,
            item = modFragVM.detailItem.collectAsState().value!!
        ){modFragVM.toggleDetailAlertOn()}
}

/**
 * Displays a list of available weapons that a character can take modules for.
 * Also displays whole weapon module if there is one available.
 *
 * @param weaponList information regarding this type of weapon
 * @param modFragVM viewModel that manages the data for this page
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
private fun WeaponListButton(
    weaponList: ModuleFragmentViewModel.WeaponListData,
    modFragVM: ModuleFragmentViewModel,
    homePageVM: HomePageViewModel
){
    //button for displaying the list
    Button(
        onClick = {weaponList.toggleListOpen()},
        modifier = Modifier
            .fillMaxWidth(0.8f)
    ){
        Text(text = stringResource(id = weaponList.nameRef))
    }

    //revealable list associated with the button
    AnimatedVisibility(
        visible = weaponList.listOpen.collectAsState().value,
    ){
        GeneralCard{
            //display whole class module if one is available
            if(weaponList.wholeClass) {
                ArchetypeRow(
                    archetype = weaponList.weaponArchetype!!,
                    modFragVM = modFragVM,
                    homePageVM = homePageVM
                )

                Divider()
            }

            //display all weapons from the given list
            weaponList.weaponList.forEach{ weapon ->
                WeaponRow(
                    weapon = weapon,
                    modFragVM = modFragVM,
                    homePageVM = homePageVM
                )
            }
        }
    }
}

/**
 * Row that displays the qualities of the given weapon.
 *
 * @param weapon weapon to display info on
 * @param modFragVM viewModel that manages the data for this page
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
private fun WeaponRow(
    weapon: Weapon,
    modFragVM: ModuleFragmentViewModel,
    homePageVM: HomePageViewModel
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        //primary checkbox item
        Checkbox(
            checked = weapon == modFragVM.primaryWeapon.collectAsState().value,
            onCheckedChange = {
                //update character's primary weapon
                modFragVM.setPrimaryWeapon(primeWeapon = weapon)

                //update character's spent points
                homePageVM.updateExpenditures()
            },
            modifier = Modifier
                .weight(0.1f)
        )

        //secondary checkbox item
        Checkbox(
            checked = modFragVM.allSecondaryWeapons[weapon]!!.value ||
                    modFragVM.archetypesHasWeapon(weapon = weapon),
            onCheckedChange = {
                //if primary check is not taken
                if(weapon != modFragVM.primaryWeapon.value)
                //perform appropriate action for input
                    modFragVM.changeIndividualModule(weapon = weapon, isTaking = it)

                homePageVM.updateExpenditures()
            },
            modifier = Modifier
                .weight(0.1f)
        )

        //display weapon name
        Text(
            text = stringResource(id = weapon.name),
            modifier = Modifier
                .weight(0.55f),
            textAlign = TextAlign.Center
        )

        //create a button to display the weapon's details
        DetailButton(
            onClick = {
                modFragVM.setDetailItem(weapon)
                modFragVM.toggleDetailAlertOn()
            },
            modifier = Modifier
                .weight(0.25f)
        )
    }
}

/**
 * Button that reveals a list of all archetype modules the character can take.
 *
 * @param modFragVM viewModel that manages the data for this page
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
private fun ArchetypeButton(
    modFragVM: ModuleFragmentViewModel,
    homePageVM: HomePageViewModel
){
    //button for displaying archetype list
    Button(
        onClick = {modFragVM.toggleArchetypeOpen()},
        modifier = Modifier
            .fillMaxWidth(0.8f)
    ){
        Text(text = stringResource(id = R.string.archetypeLabel))
    }

    //revealable list for the archetypes
    AnimatedVisibility(visible = modFragVM.archetypeOpen.collectAsState().value){
        //show all archetypes available
        GeneralCard{
            modFragVM.allArchetypeData.forEach{archetype ->
                ArchetypeRow(
                    archetype = archetype,
                    modFragVM = modFragVM,
                    homePageVM = homePageVM
                )
            }
        }
    }
}

/**
 * Creates a row that displays data on an archetype module.
 *
 * @param archetype module to display in this row
 * @param modFragVM viewModel that manages this fragment's data
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
private fun ArchetypeRow(
    archetype: ModuleFragmentViewModel.ArchetypeData,
    modFragVM: ModuleFragmentViewModel,
    homePageVM: HomePageViewModel
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        //initialize archetype name
        val itemString = stringResource(id = R.string.moduleSuffix, stringResource(id = archetype.name))

        Spacer(modifier = Modifier.weight(0.1f))

        //display archetype acquisition checkbox
        Checkbox(
            checked = archetype.takenCheck.collectAsState().value,
            onCheckedChange = {
                //add weapon list to character
                archetype.toggleCheck()

                homePageVM.updateExpenditures()
            },
            modifier = Modifier
                .weight(0.1f)
        )

        //display archetype name and cost
        Text(
            text = itemString,
            modifier = Modifier
                .weight(0.35f),
            textAlign = TextAlign.Center
        )

        Text(
            text = stringResource(id = R.string.dpLabel, 50),
            modifier = Modifier
                .weight(0.2f),
            textAlign = TextAlign.Center
        )

        //create a button to display the archetype's contents
        DetailButton(
            onClick = {
                modFragVM.setDetailItem(archName = itemString, archetype = archetype)
                modFragVM.toggleDetailAlertOn()
            },
            modifier = Modifier
                .weight(0.25f)
        )
    }
}

/**
 * Button that reveals the martial arts available to the player.
 *
 * @param modFragVM viewModel that manages this page's data
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
private fun MartialButton(
    modFragVM: ModuleFragmentViewModel,
    homePageVM: HomePageViewModel
){
    //button for displaying martial arts list
    Button(
        onClick = {modFragVM.toggleMartialOpen()},
        modifier = Modifier
            .fillMaxWidth(0.8f)
    ){
        Text(text = stringResource(id = R.string.martialArtLabel))
    }

    //revealable list of martial arts
    AnimatedVisibility(visible = modFragVM.martialOpen.collectAsState().value){
        GeneralCard{
            //display number of marital arts character can take
            Text(
                text = stringResource(
                    id = R.string.maxMartialLabel,
                    modFragVM.getMartialMax()
                ),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )

            //display a row for each martial art
            modFragVM.getAllMartials().forEach{martialArt ->
                MartialArtRow(
                    martialArt = martialArt,
                    modFragVM = modFragVM,
                    homePageVM = homePageVM
                )
            }
        }
    }
}

/**
 * Creates a row that displays information on a martial art.
 *
 * @param martialArt item to be displayed
 * @param modFragVM viewModel that manages the data for this page
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
private fun MartialArtRow(
    martialArt: MartialArt,
    modFragVM: ModuleFragmentViewModel,
    homePageVM: HomePageViewModel
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        //display martial art acquisition checkbox
        Checkbox(
            checked = modFragVM.allMartials[martialArt]!!.value,
            onCheckedChange = {
                //attempt to implement the user's desired change
                modFragVM.changeMartial(martialArt = martialArt, isTaking = it)
                homePageVM.updateExpenditures()
            },
            modifier = Modifier
                .weight(0.1f)
        )

        //display name and bonus martial knowledge of this martial art
        Text(
            text = stringResource(id = martialArt.name),
            modifier = Modifier
                .weight(0.45f),
            textAlign = TextAlign.Center
        )
        Text(
            text = "+" + stringResource(id = R.string.mkLabel, martialArt.mkBonus),
            modifier = Modifier
                .weight(0.2f),
            textAlign = TextAlign.Center
        )

        //display details button for the martial art
        DetailButton(
            onClick = {
                modFragVM.setDetailItem(martialArt = martialArt)
                modFragVM.toggleDetailAlertOn()
            },
            modifier = Modifier
                .weight(0.25f)
        )
    }
}

/**
 * Button that reveals a list of all weapon styles the character can take.
 *
 * @param modFragVM viewModel that manages the data for this page
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
private fun StyleButton(
    modFragVM: ModuleFragmentViewModel,
    homePageVM: HomePageViewModel
){
    //button for displaying weapon styles list
    Button(
        onClick = {modFragVM.toggleStyleOpen()},
        modifier = Modifier
            .fillMaxWidth(0.8f)
    ){
        Text(text = stringResource(id = R.string.styleModLabel))
    }

    //revealable list of weapon styles
    AnimatedVisibility(visible = modFragVM.styleOpen.collectAsState().value) {
        GeneralCard{
            modFragVM.getAllStyles().forEach{style ->
                StyleRow(
                    style = style,
                    modFragVM = modFragVM,
                    homePageVM = homePageVM
                )
            }
        }
    }
}

/**
 * Creates a row that displays the information for a weapon style module.
 *
 * @param style displayed item
 * @param modFragVM viewModel that manages the data for this page
 * @param homePageVM viewModel that manages the bottom bar display
 */
@Composable
private fun StyleRow(
    style: StyleModule,
    modFragVM: ModuleFragmentViewModel,
    homePageVM: HomePageViewModel
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        //display style acquisition checkbox
        Checkbox(
            checked = modFragVM.allStyles[style]!!.value,
            onCheckedChange = {
                //add or remove style as indicated
                modFragVM.changeStyle(style = style, isTaking = it)
                homePageVM.updateExpenditures()
            },
            modifier = Modifier
                .weight(0.1f)
        )

        //display style's name and cost
        Text(
            text = stringResource(id = style.name),
            modifier = Modifier
                .weight(0.4f),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.dpLabel, style.cost),
            modifier = Modifier
                .weight(0.2f),
            textAlign = TextAlign.Center
        )

        //make display button for style's details
        DetailButton(
            onClick = {
                modFragVM.setDetailItem(style)
                modFragVM.toggleDetailAlertOn()
            },
            modifier = Modifier
                .weight(0.2f)
        )
    }
}

@Preview
@Composable
fun ModulePreview(){
    val charInstance = BaseCharacter()

    val moduleFragVM = ModuleFragmentViewModel(charInstance.weaponProficiencies, LocalContext.current)
    moduleFragVM.allWeapons[5].toggleListOpen()
    moduleFragVM.toggleArchetypeOpen()

    val homePageVM = HomePageViewModel(charInstance)

    ModuleFragment(moduleFragVM, homePageVM)
}