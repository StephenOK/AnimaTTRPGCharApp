package com.example.animabuilder.activities.fragments.home_fragments

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.animabuilder.R
import com.example.animabuilder.character_creation.BaseCharacter
import com.example.animabuilder.character_creation.equipment.weapons.Weapon
import com.example.animabuilder.character_creation.equipment.weapons.WeaponAbility
import com.example.animabuilder.character_creation.equipment.weapons.WeaponType

var modWeapons: List<Weapon> = listOf()
var allSecondaries: List<Pair<Weapon, MutableState<Boolean>>> = listOf()

val archetypeAlert = mutableStateOf(false)
var detailListName = ""
var detailList: List<Weapon> = listOf()

val detailAlertOn = mutableStateOf(false)
val detailItem = mutableStateOf(Pair("name", "description"))

@Composable
fun CombatFragment(
    charInstance: BaseCharacter,
    updateFunc: () -> Unit
) {
    modWeapons = charInstance.weaponProficiencies.fullModWeapons

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
    ){
        val primaryWeapon = remember{mutableStateOf(charInstance.weaponProficiencies.primaryWeapon)}
        val allPrimaryBoxes: MutableState<List<MutableState<Boolean>>> = remember{mutableStateOf(listOf())}

        val weaponDetailAlertOn = remember{mutableStateOf(false)}
        val detailShow: MutableState<Weapon?> = remember{mutableStateOf(null)}

        Text(text = "Current Primary Weapon: " + primaryWeapon.value.name)

        WeaponListButton(
            "Short",
            charInstance.weaponProficiencies.shortArms,
            primaryWeapon,
            charInstance,
            allPrimaryBoxes,
            weaponDetailAlertOn,
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
            weaponDetailAlertOn,
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
            weaponDetailAlertOn,
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
            weaponDetailAlertOn,
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
            weaponDetailAlertOn,
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
            weaponDetailAlertOn,
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
            weaponDetailAlertOn,
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
            weaponDetailAlertOn,
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
            weaponDetailAlertOn,
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
            weaponDetailAlertOn,
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
            weaponDetailAlertOn,
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
            weaponDetailAlertOn,
            detailShow,
            updateFunc
        )

        ArchetypeButton(charInstance, updateFunc)

        StyleButton(charInstance, updateFunc)

        if(weaponDetailAlertOn.value)
            WeaponDetailsAlert(weaponDetailAlertOn, detailShow.value!!)
        if(detailAlertOn.value)
            DetailAlert()
        if(archetypeAlert.value)
            ArchetypeAlert()
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
                WeaponRow(
                    it,
                    chosen,
                    charInstance,
                    primaryBoxes,
                    alertToggle,
                    toDisplay,
                    updateFunc
                )
            }
        }
    }
}

@Composable
private fun ArchetypeButton(charInstance: BaseCharacter, updateFunc: () -> Unit){
    val open = remember{mutableStateOf(false)}

    Button(
        onClick = {open.value = !open.value},
        modifier = Modifier.width(250.dp)
    ){
        Text(text = "Archetype Modules")
    }

    AnimatedVisibility(visible = open.value){
        Column {
            ModuleRow(
                "Barbarian",
                charInstance,
                charInstance.weaponProficiencies.barbarianWeapons,
                updateFunc
            )
            ModuleRow(
                "Ninja",
                charInstance,
                charInstance.weaponProficiencies.ninjaWeapons,
                updateFunc
            )
            ModuleRow(
                "Duel",
                charInstance,
                charInstance.weaponProficiencies.duelWeapons,
                updateFunc
            )
            ModuleRow(
                "Pirate",
                charInstance,
                charInstance.weaponProficiencies.pirateWeapons,
                updateFunc
            )
            ModuleRow(
                "Nomad",
                charInstance,
                charInstance.weaponProficiencies.nomadWeapons,
                updateFunc
            )
            ModuleRow(
                "Hunter",
                charInstance,
                charInstance.weaponProficiencies.huntWeapons,
                updateFunc
            )
            ModuleRow(
                "Knight",
                charInstance,
                charInstance.weaponProficiencies.knightWeapons,
                updateFunc
            )
            ModuleRow(
                "Gladiator",
                charInstance,
                charInstance.weaponProficiencies.gladiatorWeapons,
                updateFunc
            )
            ModuleRow(
                "Assassin",
                charInstance,
                charInstance.weaponProficiencies.assassinWeapons,
                updateFunc
            )
            ModuleRow(
                "Soldier",
                charInstance,
                charInstance.weaponProficiencies.soldierWeapons,
                updateFunc
            )
            ModuleRow(
                "Indigenous",
                charInstance,
                charInstance.weaponProficiencies.indigenousWeapons,
                updateFunc
            )
            ModuleRow(
                "Bandit",
                charInstance,
                charInstance.weaponProficiencies.banditWeapons,
                updateFunc
            )
            ModuleRow(
                "Improvised",
                charInstance,
                charInstance.weaponProficiencies.improvised,
                updateFunc
            )
        }
    }
}

@Composable
private fun StyleButton(charInstance: BaseCharacter, updateFunc: () -> Unit){
    val open = remember{mutableStateOf(false)}

    Button(
        onClick = {open.value = !open.value},
        modifier = Modifier.width(250.dp)
    ){
        Text(text = "Style Modules")
    }

    AnimatedVisibility(visible = open.value) {
        Column {
            StyleRow(
                charInstance,
                stringResource(R.string.battoIai),
                "This skill permits a character to unsheathe his weapon with perfect ease. " +
                        "The character can unsheathe his weapon without applying the -25 penalty to " +
                        "the Attack or Block abilities. It has no effect for two-handed weapons.",
                "30 DP",
                updateFunc
            )
            StyleRow(
                charInstance,
                stringResource(R.string.area),
                "The character specializes in broad maneuvers that can take out various " +
                        "enemies with greater ease. This reduces the penalty for an Area Attack " +
                        "maneuver by half. Therefore a character applies a -25 to his attack ability " +
                        "when using this attack.",
                "40 DP",
                updateFunc
            )
            StyleRow(
                charInstance,
                stringResource(R.string.precision),
                "The character has a marked ability to put his adversary in a Menace " +
                        "Position. This reduces the penalty for a Put at Weapon's Point maneuver by " +
                        "half. Therefore a character applies -50 to his attack ability when using this attack.",
                "50 DP",
                updateFunc
            )
            StyleRow(
                charInstance,
                stringResource(R.string.disarming),
                "A character with this ability has specialized in disarming his opponents. " +
                        "This reduces the penalty for a Disarm maneuver to -20.",
                "40 DP",
                updateFunc
            )
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
    val secondCheck = remember{mutableStateOf(
        charInstance.weaponProficiencies.individualModules.contains(input) ||
        modWeapons.contains(input))}

    allSecondaries = allSecondaries + Pair(input, secondCheck)

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
                    charInstance.weaponProficiencies.individualModules -= input
                }

                chosen.value = input
                charInstance.weaponProficiencies.primaryWeapon = chosen.value
                charInstance.updateTotalSpent()
                updateFunc()
            },
            modifier = Modifier.weight(0.1f)
        )

        Checkbox(
            checked = secondCheck.value,
            onCheckedChange = {
                if(modWeapons.contains(input)){
                    secondCheck.value = true
                }
                else if(!primeCheck.value) {
                    secondCheck.value = it

                    if (secondCheck.value)
                        charInstance.weaponProficiencies.individualModules += input
                    else
                        charInstance.weaponProficiencies.individualModules -= input

                    charInstance.updateTotalSpent()
                }

                updateFunc()
            },
            modifier = Modifier.weight(0.1f)
        )

        Text(text = input.name, modifier = Modifier.weight(0.6f))

        TextButton(
            onClick = {toDisplay.value = input; alertToggle.value = true},
            modifier = Modifier.weight(0.2f)
        ) {
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

    Row(verticalAlignment = Alignment.CenterVertically){
        Spacer(modifier = Modifier.weight(0.1f))
        Checkbox(
            checked = isTaken.value,
            onCheckedChange = {
                isTaken.value = it

                modWeapons = charInstance.weaponProficiencies.updateModulesTaken(modList, isTaken.value)
                modUpdate(charInstance)

                charInstance.updateTotalSpent()
                updateFunc()
            },
            modifier = Modifier.weight(0.1f)
        )

        Text(text = title, modifier = Modifier.weight(0.4f))
        Text(text = "50 DP", textAlign = TextAlign.Center, modifier = Modifier.weight(0.2f))
        TextButton(
            onClick = {
                archetypeAlert.value = true
                detailListName = title
                detailList = modList
            },
            modifier = Modifier.weight(0.2f)
        ) {
            Text(text = "Details")
        }
    }
}

fun modUpdate(charInstance: BaseCharacter){
    allSecondaries.forEach{
        it.let{(input, display) ->
            if(modWeapons.contains(input))
                display.value = true
            else if(!charInstance.weaponProficiencies.individualModules.contains(input) && display.value)
                display.value = false
        }
    }
}

@Composable
private fun StyleRow(
    charInstance: BaseCharacter,
    title: String,
    description: String,
    cost: String,
    updateFunc: () -> Unit
){
    val hasStyle = remember{ mutableStateOf(charInstance.weaponProficiencies.styleMods.contains(title)) }

    Row(verticalAlignment = Alignment.CenterVertically){
        Spacer(modifier = Modifier.weight(0.1f))
        Checkbox(
            checked = hasStyle.value,
            onCheckedChange = {
                hasStyle.value = it
                if(hasStyle.value)
                    charInstance.weaponProficiencies.styleMods += title
                else
                    charInstance.weaponProficiencies.styleMods -= title

                charInstance.updateTotalSpent()
                updateFunc()
            },
            modifier = Modifier.weight(0.1f)
        )
        Text(text = title, modifier = Modifier.weight(0.4f))
        Text(text = cost, modifier = Modifier.weight(0.2f))
        TextButton(
            onClick = {
                detailItem.value = Pair(title, description)
                detailAlertOn.value = true
            },
            modifier = Modifier.weight(0.2f)
        ) {
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

@Composable
private fun ArchetypeAlert(){
    AlertDialog(
        onDismissRequest = {archetypeAlert.value = false},
        title = {Text(text = "Weapons from $detailListName Archetype")},
        text = {
            Column{
                detailList.forEach{
                    Text(text = it.name)
                }
            }
        },
        confirmButton = {TextButton(onClick = {archetypeAlert.value = false}){Text(text = "Close")} }
    )
}

@Composable
private fun DetailAlert(){
    AlertDialog(
        onDismissRequest = {detailAlertOn.value = false},
        title = {Text(text = "Description of " + detailItem.value.first)},
        text = {Column{Text(text = detailItem.value.second)}},
        confirmButton = {TextButton(onClick = {detailAlertOn.value = false}){Text(text = "Close")} }
    )
}