package com.paetus.animaCharCreator.activities.fragments.dialogs

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.TextButton
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
import com.paetus.animaCharCreator.InfoRow
import com.paetus.animaCharCreator.R
import com.paetus.animaCharCreator.character_creation.BaseCharacter
import com.paetus.animaCharCreator.character_creation.Element
import com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_types.Advantage
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.abilities.KiAbility
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.base.PrebuiltTech
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.base.Technique
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.base.TechniqueBase
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.FreeSpell
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.Spell
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.SpellType
import com.paetus.animaCharCreator.character_creation.attributes.modules.MartialArt
import com.paetus.animaCharCreator.character_creation.attributes.modules.StyleModule
import com.paetus.animaCharCreator.character_creation.attributes.psychic.PsychicPower
import com.paetus.animaCharCreator.character_creation.equipment.CoinType
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.Availability
import com.paetus.animaCharCreator.character_creation.equipment.general_goods.GeneralEquipment
import com.paetus.animaCharCreator.character_creation.equipment.weapons.AttackType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.WeaponAbility
import com.paetus.animaCharCreator.character_creation.equipment.weapons.WeaponType
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.MixedWeapon
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.ProjectileWeapon
import com.paetus.animaCharCreator.character_creation.equipment.weapons.weapon_classes.Weapon
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
        title,
        {
            LazyColumn{
                item{
                    //display the item's detail composable
                    when(item){
                        //advantage or disadvantage
                        is Advantage -> AdvantageDetails(item)

                        //weapon
                        is Weapon -> WeaponContents(item)

                        //archetype
                        is ModuleFragmentViewModel.ArchetypeData -> ArchetypeContents(item.items)

                        //style module
                        is StyleModule -> {Text(text = stringResource(item.description))}

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
            BackHandler{closeFunc()}
        },
        {TextButton(onClick = {closeFunc()}){ Text(text = stringResource(R.string.closeLabel))}}
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

    Column{
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
            InfoRow(stringResource(R.string.damageLabel), input.damage.toString())
        else if (input.ownStrength != null)
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
            InfoRow(stringResource(R.string.damageTypeLabel), stringResource(AttackType.toAddress(input.primaryType)))

        //display secondary attack type
        if(input.secondaryType != null)
            InfoRow(stringResource(R.string.secondaryTypeLabel), stringResource(AttackType.toAddress(input.secondaryType)))

        //display weapon category
        if(input is MixedWeapon)
            InfoRow(
                stringResource(R.string.weaponTypeLabel),
                stringResource(WeaponType.toAddress(input.mixedType[0])) + "/" +
                        stringResource(WeaponType.toAddress(input.mixedType[1]))
            )
        else
            InfoRow(stringResource(R.string.weaponTypeLabel), stringResource(WeaponType.toAddress(input.type)))

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
            InfoRow(stringResource(R.string.abilityLabel), abilityString)
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
        InfoRow(stringResource(R.string.prereqLabel), stringResource(item.prereqList))
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
        InfoRow(stringResource(R.string.prereqLabel), preString)
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
        val fullList = stringArrayResource(R.array.techniqueAbilities) + stringArrayResource(R.array.techniqueDisadvantages)

        val desc =
            if(technique is PrebuiltTech) stringResource(technique.description)
            else (technique as Technique).description

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
        for(index in 0..5){
            if(kiBuilds[index] > 0)
                InfoRow(stringArrayResource(R.array.primaryCharArray)[index], kiBuilds[index].toString())
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
            for(index in 0..5){
                if(technique.maintArray[index] != 0)
                    Text(
                        text = technique.maintArray[index].toString() + " (" + stringArrayResource(R.array.primaryCharArray)[index] + ")",
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
            }
        }

        Spacer(Modifier.height(5.dp))

        //display total accumulation
        InfoRow(stringResource(R.string.totalAccumulation), technique.accTotal().toString())

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
        InfoRow(stringResource(R.string.actionLabel), action)
        InfoRow(stringResource(R.string.elementLabel), stringResource(Element.toAddress(spell.inBook)))
        InfoRow(stringResource(R.string.levelText), spell.level.toString())
        InfoRow(stringResource(R.string.zeonCostLabel), spell.zCost.toString())
        Spacer(Modifier.height(10.dp))

        Row{Text(text = "\t${stringResource(spell.effect)}")}
        Spacer(Modifier.height(10.dp))

        InfoRow(stringResource(R.string.addedEffectLabel), stringResource(spell.addedEffect))
        InfoRow(
            stringResource(R.string.maxZeonLabel),
            stringResource(
                R.string.intelligenceMultiplier,
                spell.zMax
            )
        )
        if(spell.maintenance != null)
            InfoRow(
                stringResource(R.string.maintenanceLabel),
                stringResource(
                    R.string.maintenanceAmount,
                    spell.maintenance
                ) + daily
            )
        else
            InfoRow(stringResource(R.string.maintenanceLabel), stringResource(R.string.noneLabel))

        InfoRow(stringResource(R.string.typeLabel), spellType)

        if(spell is FreeSpell)
            InfoRow(stringResource(R.string.forbiddenLabel), forbiddenList)
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
        InfoRow(stringResource(R.string.levelText), " $itemLevel")
        InfoRow(stringResource(R.string.actionLabel), " $isActive")
        InfoRow(stringResource(R.string.maintenanceLabel), " $isMaintained")
        Spacer(Modifier.height(10.dp))

        Row{Text(text = "\t${stringResource(power.description)}")}
        Spacer(Modifier.height(10.dp))

        //display power's Effects Table
        power.effects.forEach{
            InfoRow(
                stringArrayResource(R.array.difficultyTable)[power.effects.indexOf(it)],
                it
            )
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
        InfoRow(stringResource(R.string.basePriceLabel), priceString)

        //display item's weight, if any given
        if(item.weight != null)
            InfoRow(stringResource(R.string.weightLabel), item.weight.toString() + " kg")


        //display item's availability
        InfoRow(stringResource(R.string.availabilityLabel), stringResource(Availability.toAddress(item.availability)))
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
    val power = charInstance.psychic.cryokinesis.crystallize

    DetailAlert(stringResource(power.name), power){}
}

@Preview
@Composable
fun EquipmentDetailPreview(){
    val charInstance = BaseCharacter()
    val item = charInstance.inventory.miscellaneous.excellentLock

    DetailAlert(stringResource(item.name), item){}
}