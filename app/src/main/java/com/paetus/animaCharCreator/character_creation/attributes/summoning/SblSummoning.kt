package com.paetus.animaCharCreator.character_creation.attributes.summoning

import com.paetus.animaCharCreator.character_creation.SblChar

/**
 * Section containing stats involving a SBL Character's summoning abilities.
 *
 * @param charInstance object that holds all of the character's data
 */
class SblSummoning(val charInstance: SblChar): Summoning(charInstance){
    override val summon = SblSummonAbility(charInstance = charInstance, summoningIndex = 0)
    override val control = SblSummonAbility(charInstance = charInstance, summoningIndex = 1)
    override val bind = SblSummonAbility(charInstance = charInstance, summoningIndex = 2)
    override val banish = SblSummonAbility(charInstance = charInstance, summoningIndex = 3)
}