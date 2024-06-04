package com.paetus.animaCharCreator.activities.fragments.dialogs

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.collectAsState
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
import com.paetus.animaCharCreator.character_creation.attributes.advantages.advantage_types.RacialAdvantage
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.abilities.KiAbility
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.base.PrebuiltTech
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.base.CustomTechnique
import com.paetus.animaCharCreator.character_creation.attributes.ki_abilities.techniques.base.TechniqueBase
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.FreeSpell
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.Spell
import com.paetus.animaCharCreator.enumerations.SpellType
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
import com.paetus.animaCharCreator.composables.DetailButton
import com.paetus.animaCharCreator.composables.OutlinedDropdown
import com.paetus.animaCharCreator.enumerations.Archetype
import com.paetus.animaCharCreator.theme.psyTableLightColors
import com.paetus.animaCharCreator.view_models.models.CharacterFragmentViewModel
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
        dialogTitle = title,
        mainContent = {
            LazyColumn {
                item {
                    //display the item's detail composable
                    when (item) {
                        //advantage or disadvantage
                        is Advantage -> AdvantageDetails(advantage = item)

                        //character page
                        is CharacterFragmentViewModel ->
                            if(item.classDetailOpen.collectAsState().value)
                                ClassDetails(charFragVM = item)
                            else if(item.raceDetailOpen.collectAsState().value)
                                RaceDetails(charFragVM = item)

                        //weapon
                        is Weapon -> WeaponDetails(weapon = item)

                        //archetype
                        is ModuleFragmentViewModel.ArchetypeData ->
                            ArchetypeDetails(weaponList = item.weapons)

                        //style module
                        is StyleModule ->
                            Text(text = stringResource(id = item.description))

                        //martial art
                        is MartialArt -> MartialDetails(martialArt = item)

                        //ki ability
                        is KiAbility -> KiDetails(ability = item)

                        //dominion technique
                        is TechniqueBase -> TechniqueDetails(technique = item)

                        //magical spell
                        is Spell -> SpellDetails(spell = item)

                        //psychic power
                        is PsychicPower -> PsyPowerDetails(power = item)

                        //inventory item
                        is GeneralEquipment -> EquipmentDetails(equipment = item)

                        //failed item input
                        else -> Text(text = stringResource(id = R.string.detailFailure))
                    }
                }
            }

            //run close function on back press
            BackHandler {closeFunc()}
        },
        buttonContent = {
            //button to close dialog
            TextButton(
                onClick = {closeFunc()}
            ){
                Text(
                    text = stringResource(id = R.string.closeLabel),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    )
}

/**
 * Shows details of the given advantage.
 *
 * @param advantage item to show the details of
 */
@Composable
private fun AdvantageDetails(
    advantage: Advantage
){
    //retrieve the costs of the item
    var costString = ""
    advantage.cost.forEach{cost ->
        costString += "$cost"
        if(advantage.cost.last() != cost) costString += ", "
    }

    //construct half attuned elements if necessary
    var halfTunedTo = ""
    if(advantage.multPicked != null) {
        halfTunedTo += "("
        advantage.multPicked.forEach {elementIndex ->
            halfTunedTo += stringArrayResource(R.array.elementList)[elementIndex]
            if(advantage.multPicked.last() != elementIndex) halfTunedTo += ", "
        }

        halfTunedTo += ")"
    }

    Column{
        //display half attuned elements if string was constructed
        if(halfTunedTo.isNotEmpty()){
            Row{Text(text = "\t$halfTunedTo")}
            Spacer(modifier = Modifier.height(10.dp))
        }

        //display advantage's description
        Row{Text(text = "\t${stringResource(id = advantage.description)}")}

        //display advantage's effect, if one given
        if(advantage.effect != null) {
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Text(
                    buildAnnotatedString{
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(stringResource(id = R.string.effectLabel))
                        }

                        append(" ${stringResource(id = advantage.effect)}")
                    }
                )
            }
        }

        //display advantage's restriction, if one given
        if(advantage.restriction != null) {
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(stringResource(id = R.string.restrictionLabel))
                        }

                        append(" ${stringResource(id = advantage.restriction)}")
                    }
                )
            }
        }

        //display advantage's special, if one given
        if(advantage.special != null) {
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                            append(stringResource(id = R.string.specialDetailLabel))
                        }

                        append(" ${stringResource(id = advantage.special)}")
                    }
                )
            }
        }

        //display available costs
        Spacer(modifier = Modifier.height(10.dp))
        if(advantage !is RacialAdvantage)
            Row{
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                            append(stringResource(id = R.string.costLabel))
                        }

                        append(" $costString")
                    }
                )
            }
        }
}

@Composable
fun ClassDetails(charFragVM: CharacterFragmentViewModel){
    val charClass = charFragVM.getDisplayedClass()

    //retrieve all archetype strings
    var archetypeList = ""
    charClass.archetype.forEach{type ->
        archetypeList += stringResource(id = Archetype.toAddress(archetype = type))

        if(type != charClass.archetype.last()) archetypeList += ", "
    }

    //convert primary maximums to integer strings
    val combatMax = (charClass.combatMax * 100).toInt()
    val magMax = (charClass.magMax * 100).toInt()
    val psyMax = (charClass.psyMax * 100).toInt()

    Column{
        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedDropdown(
                data = charFragVM.classDetailDropdown
            )
        }

        Spacer(modifier =  Modifier.height(20.dp))

        when(charFragVM.classDetailDropdown.output.collectAsState().value) {
            0 -> {
                //display class' archetype
                InfoRow(
                    label = stringResource(id = R.string.archetypeHead)
                ) { modifier, _ ->
                    Text(
                        text = archetypeList,
                        modifier = modifier
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                //display life point multiple
                InfoRow(
                    label = stringResource(id = R.string.lifePointMultHead)
                ){modifier, _ ->
                    Text(
                        text = charClass.lifePointMultiple.toString(),
                        modifier = modifier
                    )
                }

                //display life points gained per level
                InfoRow(
                    label = stringResource(R.string.lifePointLevelHead)
                ) { modifier, _ ->
                    Text(
                        text = stringResource(id = R.string.perLevel, charClass.lifePointsPerLevel),
                        modifier = modifier
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                //display initiative gained per level
                InfoRow(
                    label = stringResource(R.string.initiativeHead)
                ) { modifier, _ ->
                    Text(
                        text = stringResource(id = R.string.perLevel, charClass.initiativePerLevel),
                        modifier = modifier
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                //display martial knowledge gained per level
                InfoRow(
                    label = stringResource(R.string.martialKnowledgeHead)
                ) { modifier, _ ->
                    Text(
                        text = stringResource(id = R.string.perLevel, charClass.mkPerLevel),
                        modifier = modifier
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                //display innate psychic points gained per level
                InfoRow(
                    label = stringResource(id = R.string.innatePsyHead)
                ) { modifier, _ ->
                    Text(
                        text = stringResource(
                            id = R.string.innatePsyPerLevel,
                            charClass.psyPerTurn
                        ),
                        modifier = modifier
                    )
                }
            }

            1 -> {
                //display combat ability percentages
                InfoRow(
                    label = stringResource(id = R.string.combatAbilityHead)
                ) { modifier, _ ->
                    Text(
                        text = stringResource(id = R.string.primaryLimit, combatMax),
                        modifier = modifier
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                //display cost of attack points
                Text(
                    text = stringResource(
                        id = R.string.plusItemCost,
                        1,
                        stringResource(id = R.string.attackLabel),
                        charClass.atkGrowth
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                //display cost of block points
                Text(
                    text = stringResource(
                        id = R.string.plusItemCost,
                        1,
                        stringResource(id = R.string.blockLabel),
                        charClass.blockGrowth
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                //display cost of dodge points
                Text(
                    text = stringResource(
                        id = R.string.plusItemCost,
                        1,
                        stringResource(id = R.string.dodgeLabel),
                        charClass.dodgeGrowth
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                //display cost of wear armor points
                Text(
                    text = stringResource(
                        id = R.string.plusItemCost,
                        1,
                        stringResource(id = R.string.wearLabel),
                        charClass.armorGrowth
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(10.dp))

                //display cost of ki points
                Text(
                    text = stringResource(
                        id = R.string.plusItemCost,
                        1,
                        stringResource(id = R.string.kiLabel),
                        charClass.kiGrowth
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                //display cost of ki accumulation multiple
                InfoRow(
                    label = stringResource(id = R.string.accumulationHead)
                ) { modifier, _ ->
                    Text(
                        text = charClass.kiAccumMult.toString(),
                        modifier = modifier
                    )
                }
            }

            2 -> {
                //display magic ability percentages
                InfoRow(
                    label = stringResource(id = R.string.magicAbilityHead)
                ) { modifier, _ ->
                    Text(
                        text = stringResource(id = R.string.primaryLimit, magMax),
                        modifier = modifier
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                //display cost of adding zeon points
                Text(
                    text = stringResource(
                        id = R.string.plusItemCost,
                        5,
                        stringResource(R.string.zeonLabel),
                        charClass.zeonGrowth
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                //display cost of magic accumulation multiples
                InfoRow(
                    label = stringResource(id = R.string.maMultHead)
                ) { modifier, _ ->
                    Text(
                        text = charClass.maGrowth.toString(),
                        modifier = modifier
                    )
                }

                //display cost of magic projection addition
                Text(
                    text = stringResource(
                        id = R.string.plusItemCost,
                        1,
                        stringResource(R.string.magProjectionLabel),
                        charClass.maProjGrowth
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(10.dp))

                //display cost of summon points
                Text(
                    text = stringResource(
                        id = R.string.plusItemCost,
                        1,
                        stringResource(R.string.summonLabel),
                        charClass.summonGrowth
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                //display cost of control points
                Text(
                    text = stringResource(
                        id = R.string.plusItemCost,
                        1,
                        stringResource(id = R.string.controlTitle),
                        charClass.controlGrowth
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                //display cost of bind points
                Text(
                    text = stringResource(
                        id = R.string.plusItemCost,
                        1,
                        stringResource(id = R.string.bindTitle),
                        charClass.bindGrowth
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                //display cost of banish points
                Text(
                    text = stringResource(
                        id = R.string.plusItemCost,
                        1,
                        stringResource(id = R.string.banishTitle),
                        charClass.banishGrowth
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

            3 -> {
                //display psychic ability percentages
                InfoRow(
                    label = stringResource(R.string.psychicAbilityHead)
                ) { modifier, _ ->
                    Text(
                        text = stringResource(id = R.string.primaryLimit, psyMax),
                        modifier = modifier
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                //display cost of psychic points
                InfoRow(
                    label = stringResource(R.string.psyPointHead)
                ) { modifier, _ ->
                    Text(
                        text = charClass.psyPointGrowth.toString(),
                        modifier = modifier
                    )
                }

                //display cost of psychic projection
                Text(
                    text = stringResource(
                        id = R.string.plusItemCost,
                        1,
                        stringResource(R.string.psyProjectionLabel),
                        charClass.psyProjGrowth
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

            4 -> {
                //display cost of athletic secondaries
                Text(
                    text = stringResource(
                        id = R.string.plusItemCost,
                        1,
                        stringArrayResource(id = R.array.secondaryFields)[0],
                        charClass.athGrowth
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                //display cost of social secondaries
                Text(
                    text = stringResource(
                        id = R.string.plusItemCost,
                        1,
                        stringArrayResource(id = R.array.secondaryFields)[1],
                        charClass.socGrowth
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                //display cost of perceptive secondaries
                Text(
                    text = stringResource(
                        id = R.string.plusItemCost,
                        1,
                        stringArrayResource(id = R.array.secondaryFields)[2],
                        charClass.percGrowth
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                //display cost of intellectual secondaries
                Text(
                    text = stringResource(
                        id = R.string.plusItemCost,
                        1,
                        stringArrayResource(id = R.array.secondaryFields)[3],
                        charClass.intellGrowth
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                //display cost of vigor secondaries
                Text(
                    text = stringResource(
                        id = R.string.plusItemCost,
                        1,
                        stringArrayResource(id = R.array.secondaryFields)[4],
                        charClass.vigGrowth
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                //display cost of subterfuge secondaries
                Text(
                    text = stringResource(
                        id = R.string.plusItemCost,
                        1,
                        stringArrayResource(id = R.array.secondaryFields)[5],
                        charClass.subterGrowth
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                //display cost of creative secondaries
                Text(
                    text = stringResource(
                        id = R.string.plusItemCost,
                        1,
                        stringArrayResource(id = R.array.secondaryFields)[6],
                        charClass.createGrowth
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(10.dp))

                //display all cost reductions
                Text(
                    text = stringResource(id = R.string.reducedCostHead),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                charClass.reducedCosts.forEach { (secondaryIndex, cost) ->
                    Text(
                        text = stringResource(
                            id = R.string.plusItemCost,
                            1,
                            stringArrayResource(id = R.array.secondaryCharacteristics)[secondaryIndex],
                            cost
                        ),
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }

            else -> {
                //display primary bonuses
                charClass.primaryBonus.forEach { (name, amount) ->
                    Text(
                        text = stringResource(
                            id = R.string.itemPerLevel,
                            amount,
                            stringResource(id = name)
                        ),
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                //display secondary bonuses
                charClass.secondaryBonus.forEach {(name, amount) ->
                    Text(
                        text = stringResource(
                            id = R.string.itemPerLevel,
                            amount,
                            stringArrayResource(id = R.array.secondaryCharacteristics)[name]
                        ),
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }

                if(charClass == charFragVM.getFreelancer()){
                    Spacer(modifier = Modifier.height(10.dp))

                    //display user's selected bonuses for their freelancer
                    Text(
                        text = stringResource(id = R.string.freelancerBonusHead),
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )

                    charFragVM.getFreelancerBonuses().forEach{index ->
                        Text(
                            text =
                                if(index > 0)
                                    stringResource(
                                        id = R.string.itemPerLevel,
                                        10,
                                        stringArrayResource(id = R.array.secondaryCharacteristics)[index - 1]
                                    )
                                else
                                    stringResource(id = R.string.freelancerEmptySelectionDetail),
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                //display special ability of class, if available
                if (charClass.specialText != null)
                    Text(
                        text = stringResource(id = charClass.specialText),
                        modifier = Modifier
                            .fillMaxWidth()
                    )
            }
        }
    }
}

@Composable
private fun RaceDetails(
    charFragVM: CharacterFragmentViewModel
){
    charFragVM.getRace().forEach{racial ->
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = stringResource(racial.name),
                modifier = Modifier
                    .weight(0.75f),
                textAlign = TextAlign.Center
            )

            DetailButton(
                onClick = {
                    charFragVM.setRacialAdvantage(racial = racial)
                    charFragVM.toggleRacialAdvantageOpen()
                },
                modifier = Modifier
                    .weight(0.75f)
            )
        }
    }
}

/**
 * Shows details of the given weapon.
 *
 * @param weapon item to display the details of
 */
@Composable
private fun WeaponDetails(
    weapon: Weapon
) {
    Column {
        //display either damage or own strength value
        if(weapon.damage != null)
            InfoRow(
                label = stringResource(id = R.string.damageLabel)
            ){modifier, _ ->
                Text(
                    text = "${weapon.damage}",
                    modifier = modifier
                )
            }
        else if (weapon.ownStrength != null)
            InfoRow(
                label = stringResource(id = R.string.strengthLabel)
            ){modifier, _ ->
                Text(
                    text = "${weapon.ownStrength}",
                    modifier = modifier
                )
            }

        //display weapon's speed
        InfoRow(
            label = stringResource(id = R.string.speedLabel)
        ){modifier, _ ->
            Text(
                text = "${weapon.speed}",
                modifier = modifier
            )
        }

        //display one-handed strength if available
        if(weapon.oneHandStr != null)
            InfoRow(
                label = stringResource(id = R.string.oneHandedLabel)
            ){modifier, _ ->
                Text(
                    text = "${weapon.oneHandStr}",
                    modifier = modifier
                )
            }

        //display two-handed strength if available
        if(weapon.twoHandStr != null)
            InfoRow(
                label = stringResource(id = R.string.twoHandedLabel)
            ){modifier, _ ->
                Text(
                    text = "${weapon.twoHandStr}",
                    modifier = modifier
                )
            }

        //display primary attack type
        if(weapon.primaryType != null)
            InfoRow(
                label = stringResource(id = R.string.damageTypeLabel)
            ){modifier, _ ->
                Text(
                    text = stringResource(id = AttackType.toAddress(weapon.primaryType)),
                    modifier = modifier
                )
            }

        //display secondary attack type
        if(weapon.secondaryType != null)
            InfoRow(
                label = stringResource(id = R.string.secondaryTypeLabel)
            ){modifier, _ ->
                Text(
                    text = stringResource(id = AttackType.toAddress(weapon.secondaryType)),
                    modifier = modifier
                )
            }

        //display weapon category
        if(weapon is MixedWeapon)
            InfoRow(
                label = stringResource(id = R.string.weaponTypeLabel)
            ){modifier, _ ->
                Text(
                    text = "${stringResource(id = WeaponType.toAddress(weapon.mixedType[0]))}/${stringResource(id = WeaponType.toAddress(weapon.mixedType[1]))}",
                    modifier = modifier
                )
            }
        else
            InfoRow(
                label = stringResource(id = R.string.weaponTypeLabel)
            ){modifier, _ ->
                Text(
                    text = stringResource(id = WeaponType.toAddress(weapon.type)),
                    modifier = modifier
                )
            }

        //display weapon's fortitude, breakage, and presence
        InfoRow(
            label = stringResource(id = R.string.fortitudeLabel)
        ){modifier, _ ->
            Text(
                text = "${weapon.fortitude}",
                modifier = modifier
            )
        }

        if(weapon.breakage != null)
            InfoRow(
                label = stringResource(id = R.string.breakageLabel)
            ){modifier, _ ->
                Text(
                    text = "${weapon.breakage}",
                    modifier = modifier
                )
            }

        InfoRow(
            label = stringResource(id = R.string.presenceLabel) + ": "
        ){modifier, _ ->
            Text(
                text = "${weapon.presence}",
                modifier = modifier
            )
        }

        //display weapon's rate of fire, reload rate, and range
        if(weapon is ProjectileWeapon) {
            InfoRow(
                label =
                    if(weapon.type == WeaponType.Throwing) stringResource(id = R.string.fireRateLabel)
                    else stringResource(id = R.string.reloadLabel)
            ){modifier, _ ->
                Text(
                    text = "${weapon.reloadOrRate}",
                    modifier = modifier
                )
            }

            if (weapon.range != null)
                InfoRow(
                    label = stringResource(id = R.string.rangeLabel)
                ){modifier, _ ->
                    Text(
                        text = stringResource(id = R.string.distanceLabelM, weapon.range),
                        modifier = modifier
                    )
                }
        }

        //initialize ability output and loop counter
        var abilityString = ""
        var counter = 0

        //if there are abilities given
        if(weapon.ability != null){
            weapon.ability.forEach{ability ->
                //add ability to the output string
                abilityString += stringResource(id = WeaponAbility.getAddress(input = ability))

                //display strength of trapping ability
                if(ability == WeaponAbility.Trapping)
                    abilityString += "(" + weapon.ownStrength + ")"

                //delimit the separation between abilities
                if(counter < weapon.ability.count() - 1){
                    abilityString += "/"
                    counter++
                }
            }

            //show ability string
            InfoRow(
                label = stringResource(id = R.string.abilityLabel)
            ){it, _ ->
                Text(
                    text = abilityString,
                    modifier = it
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        //show description
        Text(text = "\t${stringResource(id = weapon.description)}")
    }
}

/**
 * Shows details of the given weapon archetype.
 *
 * @param weaponList items to display the details of
 */
@Composable
private fun ArchetypeDetails(
    weaponList: List<Weapon>
){
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //display each weapon's name
        weaponList.forEach {weapon ->
            Text(text = stringResource(id = weapon.name))
        }
    }
}

/**
 * Shows details of the given martial art.
 *
 * @param martialArt item to display the details of
 */
@Composable
private fun MartialDetails(
    martialArt: MartialArt
){
    Column{
        //show art's prerequisites and description
        InfoRow(
            label = stringResource(id = R.string.prereqLabel)
        ){modifier, _ ->
            Text(
                text = stringResource(id = martialArt.prereqList),
                modifier = modifier
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "\t${stringResource(id = martialArt.description)}")
    }
}

/**
 * Shows details of the given ki ability.
 *
 * @param ability ki ability to display the details of
 */
@Composable
private fun KiDetails(
    ability: KiAbility
){
    Column{
        //construct prerequisites needed
        val preString =
            if(ability.prerequisite != null)
                stringResource(id = ability.prerequisite.name)
            else
                ""

        //display ability's prerequisites
        InfoRow(
            label = stringResource(id = R.string.prereqLabel)
        ){it, _ ->
            Text(
                text = preString,
                modifier = it
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        //display ability's effect
        Text(text = "\t${stringResource(id = ability.description)}")
    }
}

/**
 * Shows details of the given dominion technique.
 *
 * @param technique dominion technique to display the details of
 */
@Composable
fun TechniqueDetails(
    technique: TechniqueBase
){
    Column{
        //compose all technique name options
        val fullList = stringArrayResource(R.array.techniqueAbilities) + stringArrayResource(R.array.techniqueDisadvantages)

        //retrieve technique's description
        val desc =
            if(technique is PrebuiltTech) stringResource(id = technique.description)
            else (technique as CustomTechnique).description.value

        //display all of the technique's abilities
        technique.givenAbilities.forEach {effect ->
            val namePrefix = fullList[effect.data.name]

            Row {
                Text(
                    text = "$namePrefix " +
                        if(effect.data.effectVal != null) stringResource(id = effect.data.effectRef, effect.data.effectVal)
                        else stringResource(id = effect.data.effectRef),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

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

        //if this technique is maintained
        if (technique.isMaintained()){
            Spacer(modifier = Modifier.height(5.dp))

            //display maintenance total
            Text(
                text = stringResource(id = R.string.maintenanceLabel),
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

        Spacer(modifier = Modifier.height(5.dp))

        //display total accumulation
        InfoRow(
            label = stringResource(id = R.string.totalAccumulation)
        ){modifier, _ ->
            Text(
                text = "${technique.accTotal()}",
                modifier = modifier
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

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
fun SpellDetails(
    spell: Spell
){
    //get active or passive text
    val action =
        if(spell.isActive)
            stringResource(id = R.string.activeLabel)
        else
            stringResource(id = R.string.passiveLabel)

    //get daily text if able
    val daily =
        if(spell.isDaily)
            " ${stringResource(id = R.string.dailyLabel)}"
        else
            ""

    //create string of the spell's categories
    var spellType = ""
    spell.type.forEach{type ->
        spellType += stringResource(id = SpellType.toAddress(type)) + " "
    }

    //create string of forbidden elements if this is a free spell
    var forbiddenList = ""
    if(spell is FreeSpell){
        spell.forbiddenElements.forEach {element ->
            forbiddenList += stringResource(id = Element.toAddress(element)) + " "
        }
    }

    if(forbiddenList.isEmpty())
        forbiddenList = stringResource(id = R.string.noneLabel)

    Column{
        //display active state of spell
        InfoRow(
            label = stringResource(id = R.string.actionLabel)
        ){modifier, _ ->
            Text(
                text = action,
                modifier = modifier
            )
        }

        //display spell's level
        InfoRow(
            label = stringResource(id = R.string.levelText)
        ){modifier, _ ->
            Text(
                text = spell.level.toString(),
                modifier = modifier
            )
        }

        //display spell's zeon cost
        InfoRow(
            label = stringResource(id = R.string.zeonCostLabel)
        ){modifier, _ ->
            Text(
                text = spell.zCost.toString(),
                modifier = modifier
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        //display spell's effect
        Row{Text(text = "\t${stringResource(id = spell.effect)}")}
        Spacer(modifier = Modifier.height(10.dp))

        //display spell's added effect
        InfoRow(
            label = stringResource(id = R.string.addedEffectLabel)
        ){modifier, _ ->
            Text(
                text = stringResource(id = spell.addedEffect),
                modifier = modifier
            )
        }

        //display spell's maximum zeon modifier
        InfoRow(
            label = stringResource(id = R.string.maxZeonLabel),
        ){modifier, _ ->
            Text(
                text = stringResource(id = R.string.intelligenceMultiplier, spell.zMax),
                modifier = modifier
            )
        }

        //display spell's maintenance cost, if available
        if(spell.maintenance != null)
            InfoRow(
                label = stringResource(id = R.string.maintenanceLabel)
            ){modifier, _ ->
                Text(
                    text = stringResource(id = R.string.maintenanceAmount, spell.maintenance) + daily,
                    modifier = modifier
                )
            }

        //otherwise tell of no maintenance cost
        else
            InfoRow(
                label = stringResource(id = R.string.maintenanceLabel)
            ){modifier, _ ->
                Text(
                    text = stringResource(id = R.string.noneLabel),
                    modifier = modifier
                )
            }

        //display spell's type
        InfoRow(
            label = stringResource(id = R.string.typeLabel)
        ){modifier, _ ->
            Text(
                text = spellType,
                modifier = modifier
            )
        }

        //display a free spell's restricted elements
        if(spell is FreeSpell)
            InfoRow(
                label = stringResource(id = R.string.forbiddenLabel)
            ){modifier, _ ->
                Text(
                    text = forbiddenList,
                    modifier = modifier
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
fun PsyPowerDetails(
    power: PsychicPower
){
    //retrieve level, if power is active, and if power is maintained
    val itemLevel = if(power.level > 0) power.level.toString() else stringResource(id = R.string.notApplicable)
    val isActive = if(power.isActive) stringResource(id = R.string.activeLabel) else stringResource(id = R.string.passiveLabel)
    val isMaintained = if(power.maintained) stringResource(id = R.string.yesLabel) else stringResource(id = R.string.noLabel)

    Column{
        //display power's level
        InfoRow(
            label = stringResource(id = R.string.levelText)
        ){modifier, _ ->
            Text(
                text = itemLevel,
                modifier = modifier
            )
        }

        //display power's active state
        InfoRow(
            label = stringResource(id = R.string.actionLabel)
        ){modifier, _ ->
            Text(
                text = isActive,
                modifier = modifier
            )
        }

        //display power's maintenance
        InfoRow(
            label = stringResource(id = R.string.maintenanceLabel)
        ){modifier, _ ->
            Text(
                text = isMaintained,
                modifier = modifier
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        //display power's description
        Row{Text(text = "\t${stringResource(id = power.description)}")}
        Spacer(modifier = Modifier.height(10.dp))

        //display power's Effects Table
        MaterialTheme(colorScheme = psyTableLightColors){
            MakePowerTable(power = power)
        }
    }
}

/**
 * Composes the psychic power's effect table.
 *
 * @param power psychic power to create the table of
 */
@Composable
private fun MakePowerTable(
    power: PsychicPower
){
    //initialize index referenced
    var index = 0

    //for each indicated fatigued level
    while(index < power.stringBaseCount[0]){
        //get background color for this row
        val backColor =
            if(index%2 == 0) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.secondary

        //display the difficulty level with the fatigue gained from it
        InfoRow(
            label = stringArrayResource(R.array.difficultyTable)[index],
            backColor = backColor
        ){modifier, _ ->
            Text(
                text = stringResource(id = R.string.fatigueBase, power.stringInput[index] as Int),
                modifier = modifier
            )
        }

        //increment to next index
        index++
    }

    //for each remaining string display
    power.stringBaseList.forEach{baseString ->
        while(index < power.stringBaseCount[power.stringBaseList.indexOf(baseString) + 1]){
            //get the string indicated by the input
            val display = when(val newIn = power.stringInput[index]) {
                //simply input integer
                is Int -> stringResource(id = baseString, newIn)

                //two inputs needed
                is Pair<*, *> -> {
                    if(newIn.second is Int)
                        stringResource(id = baseString, newIn.first!!, newIn.second!!)
                    else
                        stringResource(id = baseString, newIn.first!!, stringResource(id = (newIn.second as () -> Int)()))
                }

                //three inputs needed
                is List<*> ->
                    stringResource(id = baseString, newIn[0] as Int, newIn[1] as Int, newIn[2] as Int)

                //no input given
                null -> stringResource(id = baseString)

                //run a function to get a string reference
                else -> stringResource(id = baseString, stringResource(id = (newIn as () -> Int)()))
            }

            //apply background color
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
 * @param equipment item to display the details of
 */
@Composable
fun EquipmentDetails(
    equipment: GeneralEquipment
){
    //create price string of the object
    val priceString =
            when(equipment.coinType){
                CoinType.Gold -> stringResource(id = R.string.goldLabel, equipment.baseCost)
                CoinType.Silver -> stringResource(id = R.string.silverLabel, equipment.baseCost)
                CoinType.Copper -> stringResource(id = R.string.copperLabel, equipment.baseCost)
            }

    Column{
        //display item's cost
        InfoRow(
            label = stringResource(id = R.string.basePriceLabel)
        ){modifier, _ ->
            Text(
                text = priceString,
                modifier = modifier
            )
        }

        //display item's weight, if any given
        if(equipment.weight != null)
            InfoRow(
                label = stringResource(id = R.string.weightLabel)
            ){modifier, _ ->
                Text(
                    text = stringResource(id = R.string.weightDoubleKG, equipment.weight),
                    modifier = modifier
                )
            }


        //display item's availability
        InfoRow(
            label = stringResource(id = R.string.availabilityLabel)
        ){modifier, _ ->
            Text(
                text = stringResource(id = Availability.toAddress(equipment.availability)),
                modifier = modifier
            )
        }
    }
}

@Preview
@Composable
fun ClassDetailPreview(){
    val charInstance = BaseCharacter()
    charInstance.classes.setOwnClass(1)

    val charFragVM = CharacterFragmentViewModel(charInstance)
    charFragVM.classDetailDropdown.setOutput(2)

    DetailAlert(
        stringArrayResource(id = R.array.classArray)[charFragVM.classDropdown.data.output.collectAsState().value],
        charFragVM
    ){}
}

@Preview
@Composable
fun AdvantageDetailPreview(){
    val charInstance = BaseCharacter()
    val advantage = charInstance.advantageRecord.commonAdvantages.characteristicPoint
    DetailAlert(stringResource(id = advantage.name), advantage){}
}

@Preview
@Composable
fun WeaponDetailPreview(){
    val charInstance = BaseCharacter()
    val weapon = charInstance.weaponProficiencies.projectiles.crossbow

    DetailAlert(stringResource(id = weapon.name), weapon){}
}

@Preview
@Composable
fun ModuleDetailPreview(){
    val charInstance = BaseCharacter()
    val modFragVM = ModuleFragmentViewModel(charInstance.weaponProficiencies, LocalContext.current)
    val module = modFragVM.allArchetypeData[12]

    DetailAlert(stringResource(id = module.name), module){}
}

@Preview
@Composable
fun MartialDetailPreview(){
    val charInstance = BaseCharacter()
    val martialArt = charInstance.weaponProficiencies.martials.aikido

    DetailAlert(stringResource(id = martialArt.name), martialArt){}
}

@Preview
@Composable
fun KiDetailPreview(){
    val charInstance = BaseCharacter()
    val kiAbility = charInstance.ki.kiRecord.kiControl

    DetailAlert(stringResource(id = kiAbility.name), kiAbility){}
}

@Preview
@Composable
fun TechniqueDetailPreview(){
    val charInstance = BaseCharacter()
    val technique = charInstance.ki.allPrebuilts.keys.first()
    technique.maintArray[4] = 2

    DetailAlert(stringResource(id = technique.name), technique){}
}

@Preview
@Composable
fun SpellDetailPreview(){
    val charInstance = BaseCharacter()
    val spell = charInstance.magic.freeBook.fifthBook[3]

    DetailAlert(stringResource(id = spell.name), spell){}
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

    DetailAlert(stringResource(id = item.name), item){}
}