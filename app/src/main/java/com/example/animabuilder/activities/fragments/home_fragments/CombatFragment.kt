package com.example.animabuilder.activities.fragments.home_fragments

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.equipment.weapons.Weapon
import com.example.animabuilder.character_creation.equipment.weapons.WeaponType

@Composable
fun CombatFragment(
    charInstance: BaseCharacter,
    updateFunc: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.verticalScroll(rememberScrollState())
            .fillMaxWidth()
    ){
        val primaryWeapon = remember{mutableStateOf(charInstance.primaryWeapon)}
        val allPrimaryBoxes: MutableState<List<MutableState<Boolean>>> = remember{mutableStateOf(listOf())}

        val detailAlertOn = remember{mutableStateOf(false)}
        val detailShow: MutableState<Weapon?> = remember{mutableStateOf(null)}

        WeaponListButton(
            "Short",
            charInstance.weaponOptions.shortArms,
            primaryWeapon,
            charInstance,
            allPrimaryBoxes,
            detailAlertOn,
            detailShow,
            updateFunc
        )
        WeaponListButton(
            "Axes",
            charInstance.weaponOptions.axes,
            primaryWeapon,
            charInstance,
            allPrimaryBoxes,
            detailAlertOn,
            detailShow,
            updateFunc
        )
        WeaponListButton(
            "Maces",
            charInstance.weaponOptions.maces,
            primaryWeapon,
            charInstance,
            allPrimaryBoxes,
            detailAlertOn,
            detailShow,
            updateFunc
        )
        WeaponListButton(
            "Swords",
            charInstance.weaponOptions.swords,
            primaryWeapon,
            charInstance,
            allPrimaryBoxes,
            detailAlertOn,
            detailShow,
            updateFunc
        )
        WeaponListButton(
            "Two-Handed",
            charInstance.weaponOptions.twoHanded,
            primaryWeapon,
            charInstance,
            allPrimaryBoxes,
            detailAlertOn,
            detailShow,
            updateFunc
        )
        WeaponListButton(
            "Pole",
            charInstance.weaponOptions.poles,
            primaryWeapon,
            charInstance,
            allPrimaryBoxes,
            detailAlertOn,
            detailShow,
            updateFunc
        )
        WeaponListButton(
            "Cords",
            charInstance.weaponOptions.cords,
            primaryWeapon,
            charInstance,
            allPrimaryBoxes,
            detailAlertOn,
            detailShow,
            updateFunc
        )
        WeaponListButton(
            "Mixed",
            charInstance.weaponOptions.mixed,
            primaryWeapon,
            charInstance,
            allPrimaryBoxes,
            detailAlertOn,
            detailShow,
            updateFunc
        )
        WeaponListButton(
            "Shields",
            charInstance.weaponOptions.shields,
            primaryWeapon,
            charInstance,
            allPrimaryBoxes,
            detailAlertOn,
            detailShow,
            updateFunc
        )
        WeaponListButton(
            "Projectile",
            charInstance.weaponOptions.projectileWeapons,
            primaryWeapon,
            charInstance,
            allPrimaryBoxes,
            detailAlertOn,
            detailShow,
            updateFunc
        )
        WeaponListButton(
            "Throwing",
            charInstance.weaponOptions.throwingWeapons,
            primaryWeapon,
            charInstance,
            allPrimaryBoxes,
            detailAlertOn,
            detailShow,
            updateFunc
        )
        WeaponListButton(
            "Siege",
            charInstance.weaponOptions.siegeWeapons,
            primaryWeapon,
            charInstance,
            allPrimaryBoxes,
            detailAlertOn,
            detailShow,
            updateFunc
        )
        WeaponRow(
            charInstance.weaponOptions.unarmed,
            primaryWeapon,
            charInstance,
            allPrimaryBoxes,
            detailAlertOn,
            detailShow,
            updateFunc
        )

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
    toDisplay: MutableState<Weapon?>,
    updateFunc: () -> Unit
){
    val open = remember{ mutableStateOf(false) }

    Button(
        onClick = {open.value = !open.value},
        modifier = Modifier.width(250.dp)
    ){
        Text(text = name)
    }

    AnimatedVisibility(
        visible = open.value,
    ){
        Column{
            options.forEach{
                WeaponRow(it, chosen, charInstance, primaryBoxes, alertToggle, toDisplay, updateFunc)
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
    toDisplay: MutableState<Weapon?>,
    updateFunc: () -> Unit
){
    val primeCheck = remember{mutableStateOf(input == chosen.value)}
    val secondCheck = remember{mutableStateOf(charInstance.secondaryWeapon.contains(input))}
    primaryBoxes.value += primeCheck

    Row(verticalAlignment = Alignment.CenterVertically){
        Checkbox(
            checked = primeCheck.value,
            onCheckedChange = {
                primaryBoxes.value.forEach{
                    it.value = false
                }
                primeCheck.value = true
                chosen.value = input
                charInstance.setPrimaryWeapon(chosen.value)
                updateFunc()
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

                updateFunc()
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
            Column {
                InfoRow("Damage:", input.damage.toString())
                InfoRow("Speed:", input.speed.toString())

                if(input.oneHandStr != null)
                    InfoRow("Min. Str:", input.oneHandStr.toString())

                if(input.twoHandStr != null)
                    InfoRow("Two-Handed Str:", input.twoHandStr.toString())

                InfoRow("Weapon Type:", input.primaryType!!.name)

                if(input.secondaryType != null)
                    InfoRow("Secondary Type:", input.secondaryType!!.name)

                if(input.type == WeaponType.Mixed)
                    InfoRow("Weapon Type:", input.mixedType!![0].name + "/" + input.mixedType!![1].name)
                else
                    InfoRow("WeaponType:", input.type.name)

                InfoRow("Fortitude:", input.fortitude.toString())
                InfoRow("Breakage:", input.breakage.toString())
                InfoRow("Presence:", input.presence.toString())
            }
        },
        confirmButton = {
            TextButton(onClick = {isOpen.value = false}){
                Text(text = "Close")
            }
        }
    )
}

@Composable
private fun InfoRow(
    label: String,
    info: String
){
    Row(verticalAlignment = Alignment.CenterVertically){
        Text(text = label, modifier = Modifier.weight(0.5f))
        Text(text = info, modifier = Modifier.weight(0.5f))
    }
}