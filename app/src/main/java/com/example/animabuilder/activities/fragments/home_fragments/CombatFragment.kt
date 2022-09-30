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
import com.example.animabuilder.character_creation.equipment.weapons.WeaponAbility
import com.example.animabuilder.character_creation.equipment.weapons.WeaponType

@Composable
fun CombatFragment(
    charInstance: BaseCharacter,
    updateFunc: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
    ){
        val primaryWeapon = remember{mutableStateOf(charInstance.weaponProficiencies.primaryWeapon)}
        val allPrimaryBoxes: MutableState<List<MutableState<Boolean>>> = remember{mutableStateOf(listOf())}

        val detailAlertOn = remember{mutableStateOf(false)}
        val detailShow: MutableState<Weapon?> = remember{mutableStateOf(null)}

        Text(text = "Current Primary Weapon: " + primaryWeapon.value.name)

        WeaponListButton(
            "Short",
            charInstance.weaponProficiencies.shortArms,
            primaryWeapon,
            charInstance,
            allPrimaryBoxes,
            detailAlertOn,
            detailShow,
            charInstance.weaponProficiencies.shortArms,
            true,
            updateFunc
        )
        WeaponListButton(
            "Axes",
            charInstance.weaponProficiencies.axes,
            primaryWeapon,
            charInstance,
            allPrimaryBoxes,
            detailAlertOn,
            detailShow,
            charInstance.weaponProficiencies.axes,
            true,
            updateFunc
        )
        WeaponListButton(
            "Maces",
            charInstance.weaponProficiencies.maces,
            primaryWeapon,
            charInstance,
            allPrimaryBoxes,
            detailAlertOn,
            detailShow,
            charInstance.weaponProficiencies.maces,
            true,
            updateFunc
        )
        WeaponListButton(
            "Swords",
            charInstance.weaponProficiencies.swords,
            primaryWeapon,
            charInstance,
            allPrimaryBoxes,
            detailAlertOn,
            detailShow,
            charInstance.weaponProficiencies.swords,
            true,
            updateFunc
        )
        WeaponListButton(
            "Two-Handed",
            charInstance.weaponProficiencies.twoHanded,
            primaryWeapon,
            charInstance,
            allPrimaryBoxes,
            detailAlertOn,
            detailShow,
            charInstance.weaponProficiencies.twoHanded,
            true,
            updateFunc
        )
        WeaponListButton(
            "Pole",
            charInstance.weaponProficiencies.poles,
            primaryWeapon,
            charInstance,
            allPrimaryBoxes,
            detailAlertOn,
            detailShow,
            charInstance.weaponProficiencies.poles,
            true,
            updateFunc
        )
        WeaponListButton(
            "Cords",
            charInstance.weaponProficiencies.cords,
            primaryWeapon,
            charInstance,
            allPrimaryBoxes,
            detailAlertOn,
            detailShow,
            charInstance.weaponProficiencies.cords,
            true,
            updateFunc
        )
        WeaponListButton(
            "Mixed",
            charInstance.weaponProficiencies.mixed,
            primaryWeapon,
            charInstance,
            allPrimaryBoxes,
            detailAlertOn,
            detailShow,
            null,
            false,
            updateFunc
        )
        WeaponListButton(
            "Shields",
            charInstance.weaponProficiencies.shields,
            primaryWeapon,
            charInstance,
            allPrimaryBoxes,
            detailAlertOn,
            detailShow,
            charInstance.weaponProficiencies.shields,
            true,
            updateFunc
        )
        WeaponListButton(
            "Projectile",
            charInstance.weaponProficiencies.projectiles,
            primaryWeapon,
            charInstance,
            allPrimaryBoxes,
            detailAlertOn,
            detailShow,
            charInstance.weaponProficiencies.projectiles,
            true,
            updateFunc
        )
        WeaponListButton(
            "Thrown",
            charInstance.weaponProficiencies.thrown,
            primaryWeapon,
            charInstance,
            allPrimaryBoxes,
            detailAlertOn,
            detailShow,
            charInstance.weaponProficiencies.thrown,
            true,
            updateFunc,
        )
        WeaponRow(
            charInstance.weaponProficiencies.unarmed,
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
    wholeList: List<Weapon>?,
    wholeClass: Boolean,
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
            if(wholeClass)
                ModuleRow("$name Module", charInstance, wholeList!!, updateFunc)

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
    updateFunc: () -> Unit,
){
    val primeCheck = remember{mutableStateOf(input == chosen.value)}
    val secondCheck = remember{mutableStateOf(charInstance.weaponProficiencies.secondaryWeapon.contains(input))}

    primaryBoxes.value += primeCheck

    Row(verticalAlignment = Alignment.CenterVertically){
        Checkbox(
            checked = primeCheck.value,
            onCheckedChange = {
                primaryBoxes.value.forEach{
                    it.value = false
                }
                primeCheck.value = true

                if(secondCheck.value) {
                    secondCheck.value = false
                    charInstance.weaponProficiencies.secondaryWeapon -= input
                }

                chosen.value = input
                charInstance.weaponProficiencies.primaryWeapon = chosen.value
                charInstance.updateTotalSpent()
                updateFunc()
            }
        )

        Checkbox(
            checked = secondCheck.value,
            onCheckedChange = {
                if(!primeCheck.value) {
                    secondCheck.value = it

                    if (secondCheck.value)
                        charInstance.weaponProficiencies.secondaryWeapon += input
                    else
                        charInstance.weaponProficiencies.secondaryWeapon -= input

                    charInstance.updateTotalSpent()
                }

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
private fun ModuleRow(
    title: String,
    charInstance: BaseCharacter,
    modList: List<Weapon>,
    updateFunc: () -> Unit
){
    val isTaken = remember{mutableStateOf(charInstance.weaponProficiencies.takenModules.contains(modList))}

    Row(){
        Checkbox(
            checked = isTaken.value,
            onCheckedChange = {
                isTaken.value = it
                charInstance.weaponProficiencies.updateModulesTaken(modList, isTaken.value)
                charInstance.updateTotalSpent()
                updateFunc()
            }
        )

        Text(text = title)
        Text(text = "50 DP")
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
                if(input.damage != null)
                    InfoRow("Damage:", input.damage.toString())
                else
                    InfoRow("Strength:", input.ownStrength.toString())

                InfoRow("Speed:", input.speed.toString())

                if(input.oneHandStr != null)
                    InfoRow("Min. Str:", input.oneHandStr.toString())

                if(input.twoHandStr != null)
                    InfoRow("Two-Handed Str:", input.twoHandStr.toString())

                if(input.primaryType != null)
                    InfoRow("Weapon Type:", input.primaryType!!.name)

                if(input.secondaryType != null)
                    InfoRow("Secondary Type:", input.secondaryType!!.name)

                if(input.type == WeaponType.Mixed)
                    InfoRow("Weapon Type:", input.mixedType!![0].name + "/" + input.mixedType!![1].name)
                else
                    InfoRow("WeaponType:", input.type.name)

                if(input.fortitude != null)
                    InfoRow("Fortitude:", input.fortitude.toString())
                if(input.breakage != null)
                    InfoRow("Breakage:", input.breakage.toString())
                if(input.presence != null)
                    InfoRow("Presence:", input.presence.toString())

                if(input.rateOfFire != null)
                    InfoRow("Fire Rate:", input.rateOfFire.toString())
                if(input.reload != null)
                    InfoRow("Reload:", input.reload.toString())
                if(input.range != null)
                    InfoRow("Range", input.range.toString() + "m")

                var abilityString = ""
                var counter = 0

                if(input.ability != null){
                    input.ability!!.forEach{
                        abilityString += it.name

                        if(it == WeaponAbility.Trapping)
                            abilityString += "(" + input.ownStrength + ")"

                        if(counter < input.ability!!.count() - 1){
                            abilityString += "/"
                            counter++
                        }
                    }

                    InfoRow("Abilities:", abilityString)
                }

                Text(text = input.description)
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