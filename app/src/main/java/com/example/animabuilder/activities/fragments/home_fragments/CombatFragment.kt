package com.example.animabuilder.activities.fragments.home_fragments

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.equipment.weapons.Weapon

@Composable
fun CombatFragment(
    charInstance: BaseCharacter,
    updateFunc: () -> Unit
) {
    Column(Modifier.verticalScroll(rememberScrollState())){
        val primaryWeapon = remember{mutableStateOf(charInstance.primaryWeapon)}
        val allPrimaryBoxes: MutableState<List<MutableState<Boolean>>> = remember{mutableStateOf(listOf())}

        val detailAlertOn = remember{mutableStateOf(false)}
        val detailShow: MutableState<Weapon?> = remember{mutableStateOf(null)}

        Column {
             WeaponListButton(
                 "Common",
                 charInstance.weaponOptions.commonWeapons,
                 primaryWeapon,
                 charInstance,
                 allPrimaryBoxes,
                 detailAlertOn,
                 detailShow
             )
            WeaponListButton(
                "Exotic",
                charInstance.weaponOptions.exoticWeapons,
                primaryWeapon,
                charInstance,
                allPrimaryBoxes,
                detailAlertOn,
                detailShow
            )
            WeaponListButton(
                "Improvised",
                charInstance.weaponOptions.improvisedWeapons,
                primaryWeapon,
                charInstance,
                allPrimaryBoxes,
                detailAlertOn,
                detailShow
            )
            WeaponListButton(
                "Shields",
                charInstance.weaponOptions.shields,
                primaryWeapon,
                charInstance,
                allPrimaryBoxes,
                detailAlertOn,
                detailShow
            )
            WeaponListButton(
                "Projectile",
                charInstance.weaponOptions.projectileWeapons,
                primaryWeapon,
                charInstance,
                allPrimaryBoxes,
                detailAlertOn,
                detailShow
            )
            WeaponListButton(
                "Siege",
                charInstance.weaponOptions.siegeWeapons,
                primaryWeapon,
                charInstance,
                allPrimaryBoxes,
                detailAlertOn,
                detailShow
            )
        }

        if(detailAlertOn.value)
            WeaponDetailsAlert(detailAlertOn, detailShow.value!!)
    }
}



@Composable
private fun WeaponListButton(
    name: String,
    options: List<Weapon>,
    chosen: MutableState<Weapon>,
    charInstance: BaseCharacter,
    primaryBoxes: MutableState<List<MutableState<Boolean>>>,
    alertToggle: MutableState<Boolean>,
    toDisplay: MutableState<Weapon?>
){
    val open = remember{ mutableStateOf(false) }

    Button(
        onClick = {open.value = !open.value},
    ){
        Text(text = name)
    }

    AnimatedVisibility(
        visible = open.value,
    ){
        Column{
            options.forEach{
                WeaponRow(it, chosen, charInstance, primaryBoxes, alertToggle, toDisplay)
            }
        }
    }
}

@Composable
private fun WeaponRow(
    input: Weapon,
    chosen: MutableState<Weapon>,
    charInstance: BaseCharacter,
    primaryBoxes: MutableState<List<MutableState<Boolean>>>,
    alertToggle: MutableState<Boolean>,
    toDisplay: MutableState<Weapon?>
){
    val primeCheck = remember{mutableStateOf(input == chosen.value)}
    val secondCheck = remember{mutableStateOf(charInstance.secondaryWeapon.contains(input))}
    primaryBoxes.value += primeCheck

    Row{
        Checkbox(
            checked = primeCheck.value,
            onCheckedChange = {
                primaryBoxes.value.forEach{
                    it.value = false
                }
                primeCheck.value = true
                chosen.value = input
                charInstance.setPrimaryWeapon(chosen.value)
            }
        )

        Checkbox(
            checked = secondCheck.value,
            onCheckedChange = {
                secondCheck.value = it
                if(secondCheck.value)
                    charInstance.addSecondaryWeapon(input)
                else
                    charInstance.removeSecondaryWeapon(input)
            }
        )

        Text(text = input.name, modifier = Modifier.weight(0.8f))

        TextButton(onClick = {toDisplay.value = input; alertToggle.value = true}) {
            Text(text = "Details")
        }
    }
}

@Composable
private fun WeaponDetailsAlert(
    isOpen: MutableState<Boolean>,
    input: Weapon
){
    AlertDialog(
        onDismissRequest = {isOpen.value = false},
        title = {Text(text = "Stats for " + input.name)},
        text = {
            Row{
                Text(text = input.damage.toString(), modifier = Modifier.weight(0.8f))
                Text(text = input.speed.toString(), modifier = Modifier.weight(0.8f))
                Text(text = input.oneHandStr.toString(), modifier = Modifier.weight(0.8f))
                Text(text = input.twoHandStr.toString(), modifier = Modifier.weight(0.8f))
                Text(text = input.primaryType.name, modifier = Modifier.weight(0.8f))

                if(input.secondaryType != null)
                    Text(text = input.secondaryType!!.name, modifier = Modifier.weight(0.8f))
                else
                    Spacer(modifier = Modifier.weight(0.8f))

                Text(text = input.type.name, modifier = Modifier.weight(0.8f))
                Text(text = input.fortitude.toString(), modifier = Modifier.weight(0.8f))
                Text(text = input.breakage.toString(), modifier = Modifier.weight(0.8f))
                Text(text = input.presence.toString(), modifier = Modifier.weight(0.8f))
            }
        },
        confirmButton = {
            TextButton(onClick = {isOpen.value = false}){
                Text(text = "Close")
            }
        }
    )
}