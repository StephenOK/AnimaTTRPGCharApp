package com.paetus.animaCharCreator.activities.fragments.dialogs

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paetus.animaCharCreator.composables.InfoRow
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.enumerations.Element
import com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_types.Advantage
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.abilities.KiAbility
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.base.PrebuiltTech
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.base.CustomTechnique
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.base.TechniqueBase
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.FreeSpell
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.Spell
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.SpellType
import com.paetus.animaCharCreator.character_creation.attributes.modules.MartialArt
import com.paetus.animaCharCreator.character_creation.attributes.modules.StyleModule
import com.paetus.animaCharCreator.character_creation.attributes.psychic.PsychicPower
import com.paetus.animaCharCreator.enumerations.CoinType
import com.paetus.animaCharCreator.enumerations.Availability
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment
import com.paetus.animaCharCreator.enumerations.AttackType
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponAbility
import com.paetus.animaCharCreator.enumerations.weaponEnums.WeaponType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.MixedWeapon
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.ProjectileWeapon
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.Weapon
import com.paetus.animaCharCreator.theme.psyTableLightColors
import com.paetus.animaCharCreator.view_models.models.ModuleFragmentViewModel

/**
 * Shows an alert that gives details on the item in question.
 *
 * @param title displayed header item
 * @param item object whose details are being displayed
 * @param closeFunc function to run on alert closure
 */
@Composable
fun DetailAlert(
    title: String,
    item: Any,
    closeFunc: () -> Unit
) {
    DialogFrame(
        //display dialog header
        title,
        {
            LazyColumn {
                item {
                    //display the item's detail composable
                    when (item) {
                        //advantage or disadvantage
                        is Advantage -> AdvantageDetails(item)

                        //weapon
                        is Weapon -> WeaponContents(item)

                        //archetype
                        is ModuleFragmentViewModel.ArchetypeData -> ArchetypeContents(item.items)

                        //style module
                        is StyleModule -> {
                            Text(text = stringResource(item.description))
                        }

                        //martial art
                        is MartialArt -> MartialContents(item)

                        //ki ability
                        is KiAbility -> KiContents(item)

                        //dominion technique
                        is TechniqueBase -> TechContents(item)

                        //magical spell
                        is Spell -> SpellDetails(item)

                        //psychic power
                        is PsychicPower -> PowerDetails(item)

                        //inventory item
                        is GeneralEquipment -> EquipmentDetails(item)

                        //failed item input
                        else -> Text(text = stringResource(R.string.detailFailure))
                    }
                }
            }

            //run close function on back press
            BackHandler { closeFunc() }
        },
        {
            TextButton(
                onClick = {closeFunc()}
            ){
                Text(
                    text = stringResource(R.string.closeLabel),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    )
}

/**
 * Shows details of the given advantage.
 *
 * @param item advantage to show the details of
 */
@Composable
private fun AdvantageDetails(item: Advantage){
    //retrieve the costs of the item
    var costString = ""
    item.cost.forEach{
        costString += "$it"
        if(item.cost.last() != it) costString += ", "
    }

    //construct half attuned elements if necessary
    var halfTunedTo = ""
    if(item.multPicked != null) {
        halfTunedTo += "("
        item.multPicked.forEach {
            halfTunedTo += stringArrayResource(R.array.elementList)[it]
            if(item.multPicked.last() != it) halfTunedTo += ", "
        }

        halfTunedTo += ")"
    }

    Column{
        //display half attuned elements if string was constructed
        if(halfTunedTo.isNotEmpty()){
            Row{Text(text = "\t$halfTunedTo")}
            Spacer(Modifier.height(10.dp))
        }

        //display advantage's description
        Row{Text(text = "\t${stringResource(item.description)}")}

        //display advantage's effect, if one given
        if(item.effect != null) {
            Spacer(Modifier.height(10.dp))
            Row {
                Text(
                    buildAnnotatedString{
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(stringResource(R.string.effectLabel))
                        }

                        append(" ${stringResource(item.effect)}")
                    }
                )
            }
        }

        //display advantage's restriction, if one given
        if(item.restriction != null) {
            Spacer(Modifier.height(10.dp))
            Row {
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(stringResource(R.string.restrictionLabel))
                        }

                        append(" ${stringResource(item.restriction)}")
                    }
                )
            }
        }

        //display advantage's special, if one given
        if(item.special != null) {
            Spacer(Modifier.height(10.dp))
            Row {
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                            append(stringResource(R.string.specialDetailLabel))
                        }

                        append(" ${stringResource(item.special)}")
                    }
                )
            }
        }

        //display available costs
        Spacer(Modifier.height(10.dp))
        Row{
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                        append(stringResource(R.string.costLabel))
                    }

                    append(" $costString")
                }
            )
        }
    }
}

/**
 * Shows details of the given weapon.
 *
 * @param input weapon to display the details of
 */
@Composable
private fun WeaponContents(input: Weapon) {
    Column {
        //display either damage or own strength value
        if(input.damage != null)
            InfoRow(
                label = stringResource(R.string.damageLabel)
            ){it, _ ->
                Text(
                    text = "${input.damage}",
                    modifier = it
                )
            }
        else if (input.ownStrength != null)
            InfoRow(
                label = stringResource(R.string.strengthLabel)
            ){it, _ ->
                Text(
                    text = "${input.ownStrength}",
                    modifier = it
                )
            }

        //display weapon's speed
        InfoRow(
            label = stringResource(R.string.speedLabel)
        ){it, _ ->
            Text(
                text = "${input.speed}",
                modifier = it
            )
        }

        //display one-handed strength if available
        if(input.oneHandStr != null)
            InfoRow(
                label = stringResource(R.string.oneHandedLabel)
            ){it, _ ->
                Text(
                    text = "${input.oneHandStr}",
                    modifier = it
                )
            }

        //display two-handed strength if available
        if(input.twoHandStr != null)
            InfoRow(
                label = stringResource(R.string.twoHandedLabel)
            ){it, _ ->
                Text(
                    text = "${input.twoHandStr}",
                    modifier = it
                )
            }

        //display primary attack type
        if(input.primaryType != null)
            InfoRow(
                label = stringResource(R.string.damageTypeLabel)
            ){it, _ ->
                Text(
                    text = stringResource(AttackType.toAddress(input.primaryType)),
                    modifier = it
                )
            }

        //display secondary attack type
        if(input.secondaryType != null)
            InfoRow(
                label = stringResource(R.string.secondaryTypeLabel)
            ){it, _ ->
                Text(
                    text = stringResource(AttackType.toAddress(input.secondaryType)),
                    modifier = it
                )
            }

        //display weapon category
        if(input is MixedWeapon)
            InfoRow(
                label = stringResource(R.string.weaponTypeLabel)
            ){it, _ ->
                Text(
                    text = "${stringResource(WeaponType.toAddress(input.mixedType[0]))}/${stringResource(WeaponType.toAddress(input.mixedType[1]))}",
                    modifier = it
                )
            }
        else
            InfoRow(
                label = stringResource(R.string.weaponTypeLabel)
            ){it, _ ->
                Text(
                    text = stringResource(WeaponType.toAddress(input.type)),
                    modifier = it
                )
            }

        //display weapon's fortitude, breakage, and presence
        InfoRow(
            label = stringResource(R.string.fortitudeLabel)
        ){it, _ ->
            Text(
                text = "${input.fortitude}",
                modifier = it
            )
        }

        if(input.breakage != null)
            InfoRow(
                label = stringResource(R.string.breakageLabel)
            ){it, _ ->
                Text(
                    text = "${input.breakage}",
                    modifier = it
                )
            }

        InfoRow(
            label = stringResource(R.string.presenceLabel) + ": "
        ){it, _ ->
            Text(
                text = "${input.presence}",
                modifier = it
            )
        }

        //display weapon's rate of fire, reload rate, and range
        if(input is ProjectileWeapon) {
                InfoRow(
                    label =
                        if(input.type == WeaponType.Throwing) stringResource(R.string.fireRateLabel)
                        else stringResource(R.string.reloadLabel)

                ){it, _ ->
                    Text(
                        text = "${input.reloadOrRate}",
                        modifier = it
                    )
                }

            if (input.range != null)
                InfoRow(
                    label = stringResource(R.string.rangeLabel)
                ){it, _ ->
                    Text(
                        text = stringResource(R.string.distanceLabelM, input.range),
                        modifier = it
                    )
                }
        }

        //initialize ability output and loop counter
        var abilityString = ""
        var counter = 0

        //if there are abilities given
        if(input.ability != null){
            input.ability.forEach{
                //add ability to the output string
                abilityString += stringResource(WeaponAbility.getAddress(it))

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
            InfoRow(
                label = stringResource(R.string.abilityLabel)
            ){it, _ ->
                Text(
                    text = abilityString,
                    modifier = it
                )
            }
        }

        Spacer(Modifier.height(10.dp))

        //show description
        Text(text = "\t${stringResource(input.description)}")
    }
}

/**
 * Shows details of the given weapon archetype.
 *
 * @param detailList weapon list to display the details of
 */
@Composable
private fun ArchetypeContents(detailList: List<Weapon>){
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //display each weapon's name
        detailList.forEach {
            Text(text = stringResource(it.name))
        }
    }
}

/**
 * Shows details of the given martial art.
 *
 * @param item martial art to display the details of
 */
@Composable
private fun MartialContents(item: MartialArt){
    Column{
        //show art's prerequisites and description
        InfoRow(
            label = stringResource(R.string.prereqLabel)
        ){it, _ ->
            Text(
                text = stringResource(item.prereqList),
                modifier = it
            )
        }
        Spacer(Modifier.height(10.dp))
        Text(text = "\t${stringResource(item.description)}")
    }
}

/**
 * Shows details of the given ki ability.
 *
 * @param ability ki ability to display the details of
 */
@Composable
private fun KiContents(ability: KiAbility) {
    Column{
        //construct prerequisites needed
        val preString =
            if(ability.prerequisites != null)
                stringResource(ability.prerequisites.name)
            else
                ""

        //display ability's prerequisites
        InfoRow(
            label = stringResource(R.string.prereqLabel)
        ){it, _ ->
            Text(
                text = preString,
                modifier = it
            )
        }
        Spacer(Modifier.height(10.dp))

        //display ability's effect
        Text(text = "\t${stringResource(ability.description)}")
    }
}

/**
 * Shows details of the given dominion technique.
 *
 * @param technique dominion technique to display the details of
 */
@Composable
fun TechContents(technique: TechniqueBase) {
    Column{
        //compose all technique name options
        val fullList = stringArrayResource(R.array.techniqueAbilities) + stringArrayResource(R.array.techniqueDisadvantages)

        //retrieve technique's description
        val desc =
            if(technique is PrebuiltTech) stringResource(technique.description)
            else (technique as CustomTechnique).description

        //display all of the technique's abilities
        technique.givenAbilities.forEach {
            val namePrefix = fullList[it.data.name]

            Row {
                Text(
                    text = "$namePrefix " +
                        if(it.data.effectVal != null) stringResource(it.data.effectRef, it.data.effectVal)
                        else stringResource(it.data.effectRef),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(Modifier.height(10.dp))

        //initialize list of accumulation needed
        val kiBuilds = technique.statSpent()

        //display each required build needed
        listOf(Pair(0, 0), Pair(1, 1), Pair(2, 2), Pair(3, 3), Pair(5, 4), Pair(6, 5)).forEach{(primary, ki) ->
            if(kiBuilds[ki] > 0)
                InfoRow(
                    label = stringArrayResource(R.array.primaryCharArray)[primary]
                ){it, _ ->
                    Text(
                        text = "${kiBuilds[ki]}",
                        modifier = it
                    )
                }
        }

        //if this technique is maintainable
        if (technique.isMaintained()){
            Spacer(Modifier.height(5.dp))

            //display maintenance total
            Text(
                text = stringResource(R.string.maintenanceLabel),
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            //display maintenance by stat
            listOf(Pair(0, 0), Pair(1, 1), Pair(2, 2), Pair(3, 3), Pair(5, 4), Pair(6, 5)).forEach{(primary, ki) ->
                if(technique.maintArray[ki] != 0)
                    Text(
                        text = technique.maintArray[ki].toString() + " (" + stringArrayResource(R.array.primaryCharArray)[primary] + ")",
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
            }
        }

        Spacer(Modifier.height(5.dp))

        //display total accumulation
        InfoRow(
            label = stringResource(R.string.totalAccumulation)
        ){it, _ ->
            Text(
                text = "${technique.accTotal()}",
                modifier = it
            )
        }

        Spacer(Modifier.height(10.dp))

        //display technique description
        Text(text = "\t$desc")
    }
}

/**
 * Shows details of the given spell.
 *
 * @param spell magic spell to display the details of
 */
@Composable
fun SpellDetails(spell: Spell){
    //get active or passive text
    val action =
        if(spell.isActive)
            stringResource(R.string.activeLabel)
        else
            stringResource(R.string.passiveLabel)

    //get daily text if able
    val daily =
        if(spell.isDaily)
            " ${stringResource(R.string.dailyLabel)}"
        else
            ""

    //create string of the spell's categories
    var spellType = ""
    spell.type.forEach{
        spellType += stringResource(SpellType.toAddress(it)) + " "
    }

    //create string of forbidden elements if this is a free spell
    var forbiddenList = ""
    if(spell is FreeSpell){
        spell.forbiddenElements.forEach {
            forbiddenList += stringResource(Element.toAddress(it)) + " "
        }
    }

    if(forbiddenList.isEmpty())
        forbiddenList = stringResource(R.string.noneLabel)

    Column{
        InfoRow(
            label = stringResource(R.string.actionLabel)
        ){it, _ ->
            Text(
                text = action,
                modifier = it
            )
        }

        InfoRow(
            label = stringResource(R.string.elementLabel)
        ){it, _ ->
            Text(
                text = stringResource(Element.toAddress(spell.inBook)),
                modifier = it
            )
        }

        InfoRow(
            label = stringResource(R.string.levelText)
        ){it, _ ->
            Text(
                text = spell.level.toString(),
                modifier = it
            )
        }

        InfoRow(
            label = stringResource(R.string.zeonCostLabel)
        ){it, _ ->
            Text(
                text = spell.zCost.toString(),
                modifier = it
            )
        }

        Spacer(Modifier.height(10.dp))

        Row{Text(text = "\t${stringResource(spell.effect)}")}
        Spacer(Modifier.height(10.dp))

        InfoRow(
            label = stringResource(R.string.addedEffectLabel)
        ){it, _ ->
            Text(
                text = stringResource(spell.addedEffect),
                modifier = it
            )
        }

        InfoRow(
            label = stringResource(R.string.maxZeonLabel),
        ){it, _ ->
            Text(
                text = stringResource(R.string.intelligenceMultiplier, spell.zMax),
                modifier = it
            )
        }

        if(spell.maintenance != null)
            InfoRow(
                label = stringResource(R.string.maintenanceLabel)
            ){it, _ ->
                Text(
                    text = stringResource(R.string.maintenanceAmount, spell.maintenance) + daily,
                    modifier = it
                )
            }
        else
            InfoRow(
                label = stringResource(R.string.maintenanceLabel)
            ){it, _ ->
                Text(
                    text = stringResource(R.string.noneLabel),
                    modifier = it
                )
            }

        InfoRow(
            label = stringResource(R.string.typeLabel)
        ){it, _ ->
            Text(
                text = spellType,
                modifier = it
            )
        }

        if(spell is FreeSpell)
            InfoRow(
                label = stringResource(R.string.forbiddenLabel)
            ){it, _ ->
                Text(
                    text = forbiddenList,
                    modifier = it
                )
            }
    }
}

/**
 * Shows details of the given psychic power.
 *
 * @param power psychic power to display the details of
 */
@Composable
fun PowerDetails(power: PsychicPower){
    //retrieve level, if power is active, and if power is maintained
    val itemLevel = if(power.level > 0) power.level.toString() else stringResource(R.string.notApplicable)
    val isActive = if(power.active) stringResource(R.string.activeLabel) else stringResource(R.string.passiveLabel)
    val isMaintained = if(power.maintained) stringResource(R.string.yesLabel) else stringResource(R.string.noLabel)

    Column{
        //display power values
        InfoRow(
            label = stringResource(R.string.levelText)
        ){it, _ ->
            Text(
                text = itemLevel,
                modifier = it
            )
        }

        InfoRow(
            label = stringResource(R.string.actionLabel)
        ){it, _ ->
            Text(
                text = isActive,
                modifier = it
            )
        }

        InfoRow(
            label = stringResource(R.string.maintenanceLabel)
        ){it, _ ->
            Text(
                text = isMaintained,
                modifier = it
            )
        }

        Spacer(Modifier.height(10.dp))

        Row{Text(text = "\t${stringResource(power.description)}")}
        Spacer(Modifier.height(10.dp))

        //display power's Effects Table
        MaterialTheme(colorScheme = psyTableLightColors){
            MakePowerTable(power)
        }
    }
}

/**
 * Composes the psychic power's effect table.
 *
 * @param power psychic power to create the table of
 */
@Composable
private fun MakePowerTable(power: PsychicPower){
    //initialize index referenced
    var index = 0

    //for each indicated fatigued level
    while(index < power.stringBaseCount[0]){
        val backColor =
            if(index%2 == 0) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.secondary

        //display the difficulty level with the fatigue gained from it
        InfoRow(
            label = stringArrayResource(R.array.difficultyTable)[index],
            backColor = backColor
        ){it, _ ->
            Text(
                text = stringResource(R.string.fatigueBase, power.stringInput[index] as Int),
                modifier = it
            )
        }

        //increment to next index
        index++
    }

    //for each remaining string display
    power.stringBaseList.forEach{
        while(index < power.stringBaseCount[power.stringBaseList.indexOf(it) + 1]){
            //get the effect input
            val newIn = power.stringInput[index]

            //get the string indicated by the input
            val display = when(newIn) {
                //simply input integer
                is Int -> stringResource(it, newIn)

                //two inputs needed
                is Pair<*, *> -> {
                    if(newIn.second is Int)
                        stringResource(it, newIn.first!!, newIn.second!!)
                    else
                        stringResource(it, newIn.first!!, stringResource((newIn.second as () -> Int)()))
                }

                //three inputs needed
                is List<*> ->
                    stringResource(it, newIn[0] as Int, newIn[1] as Int, newIn[2] as Int)

                //no input given
                null -> stringResource(it)

                //run a function to get a string reference
                else -> stringResource(it, stringResource((newIn as () -> Int)()))
            }

            val backColor =
                if(index%2 == 0) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.secondary

            //display table effect
            InfoRow(
                label = stringArrayResource(R.array.difficultyTable)[index],
                backColor = backColor,
                textColor = MaterialTheme.colorScheme.onPrimary
            ){modifier, _ ->
                Text(
                    text = display,
                    modifier = modifier
                )
            }

            //increment index
            index++
        }
    }
}

/**
 * Shows details of the given equipment.
 *
 * @param item equipment to display the details of
 */
@Composable
fun EquipmentDetails(item: GeneralEquipment){
    //create price of the object
    val priceString =
            when(item.coinType){
                CoinType.Gold -> stringResource(R.string.goldLabel, item.baseCost)
                CoinType.Silver -> stringResource(R.string.silverLabel, item.baseCost)
                CoinType.Copper -> stringResource(R.string.copperLabel, item.baseCost)
            }

    Column{
        //display item's cost
        InfoRow(
            label = stringResource(R.string.basePriceLabel)
        ){it, _ ->
            Text(
                text = priceString,
                modifier = it
            )
        }

        //display item's weight, if any given
        if(item.weight != null)
            InfoRow(
                label = stringResource(R.string.weightLabel)
            ){it, _ ->
                Text(
                    text = stringResource(R.string.weightDoubleKG, item.weight),
                    modifier = it
                )
            }


        //display item's availability
        InfoRow(
            label = stringResource(R.string.availabilityLabel)
        ){it, _ ->
            Text(
                text = stringResource(Availability.toAddress(item.availability)),
                modifier = it
            )
        }
    }
}

@Preview
@Composable
fun AdvantageDetailPreview(){
    val charInstance = BaseCharacter()
    val advantage = charInstance.advantageRecord.commonAdvantages.characteristicPoint
    DetailAlert(stringResource(advantage.name), advantage){}
}

@Preview
@Composable
fun WeaponDetailPreview(){
    val charInstance = BaseCharacter()
    val weapon = charInstance.weaponProficiencies.projectiles.crossbow

    DetailAlert(stringResource(weapon.name), weapon){}
}

@Preview
@Composable
fun ModuleDetailPreview(){
    val charInstance = BaseCharacter()
    val modFragVM = ModuleFragmentViewModel(charInstance.weaponProficiencies, LocalContext.current)
    val module = modFragVM.allArchetypeData[12]

    DetailAlert(stringResource(module.name), module){}
}

@Preview
@Composable
fun MartialDetailPreview(){
    val charInstance = BaseCharacter()
    val martialArt = charInstance.weaponProficiencies.martials.aikido

    DetailAlert(stringResource(martialArt.name), martialArt){}
}

@Preview
@Composable
fun KiDetailPreview(){
    val charInstance = BaseCharacter()
    val kiAbility = charInstance.ki.kiRecord.kiControl

    DetailAlert(stringResource(kiAbility.name), kiAbility){}
}

@Preview
@Composable
fun TechniqueDetailPreview(){
    val charInstance = BaseCharacter()
    val technique = charInstance.ki.allPrebuilts[0]
    technique.maintArray[4] = 2

    DetailAlert(stringResource(technique.name), technique){}
}

@Preview
@Composable
fun SpellDetailPreview(){
    val charInstance = BaseCharacter()
    val spell = charInstance.magic.freeBook.fifthBook[3]

    DetailAlert(stringResource(spell.name), spell){}
}

@Preview
@Composable
fun PowerDetailPreview(){
    val charInstance = BaseCharacter()
    val power = charInstance.psychic.matrixPowers.linkMatrices

    DetailAlert(stringArrayResource(R.array.powerNames)[power.name], power){}
}

@Preview
@Composable
fun EquipmentDetailPreview(){
    val charInstance = BaseCharacter()
    val item = charInstance.inventory.miscellaneous.excellentLock

    DetailAlert(stringResource(item.name), item){}
}