package com.paetus.animaCharCreator.character_creation.attributes.secondary_abilities

import com.paetus.animaCharCreator.character_creation.SblChar

/**
 * Subclass of SecondaryList for use in a SBL character.
 *
 * @param sblChar object that manages all of the character's stats
 */
class SblSecondaryList(
    val sblChar: SblChar
): SecondaryList(sblChar){
    override val acrobatics = SblSecondaryCharacteristic(parent = this, secondaryIndex = 0)
    override val athletics = SblSecondaryCharacteristic(parent = this, secondaryIndex = 1)
    override val climb = SblSecondaryCharacteristic(parent = this, secondaryIndex = 2)
    override val jump = SblSecondaryCharacteristic(parent = this, secondaryIndex = 3)
    override val ride = SblSecondaryCharacteristic(parent = this, secondaryIndex = 4)
    override val swim = SblSecondaryCharacteristic(parent = this, secondaryIndex = 5)

    override val intimidate = SblSecondaryCharacteristic(parent = this, secondaryIndex = 6)
    override val leadership = SblSecondaryCharacteristic(parent = this, secondaryIndex = 7)
    override val persuasion = SblSecondaryCharacteristic(parent = this, secondaryIndex = 8)
    override val style = SblSecondaryCharacteristic(parent = this, secondaryIndex = 9)

    override val notice = SblSecondaryCharacteristic(parent = this, secondaryIndex = 10)
    override val search = SblSecondaryCharacteristic(parent = this, secondaryIndex = 11)
    override val track = SblSecondaryCharacteristic(parent = this, secondaryIndex = 12)

    override val animals = SblSecondaryCharacteristic(parent = this, secondaryIndex = 13)
    override val appraise = SblSecondaryCharacteristic(parent = this, secondaryIndex = 14)
    override val herbalLore = SblSecondaryCharacteristic(parent = this, secondaryIndex = 15)
    override val history = SblSecondaryCharacteristic(parent = this, secondaryIndex = 16)
    override val memorize = SblSecondaryCharacteristic(parent = this, secondaryIndex = 17)
    override val magicAppraise = SblSecondaryCharacteristic(parent = this, secondaryIndex = 18)
    override val medic = SblSecondaryCharacteristic(parent = this, secondaryIndex = 19)
    override val navigate = SblSecondaryCharacteristic(parent = this, secondaryIndex = 20)
    override val occult = SblSecondaryCharacteristic(parent = this, secondaryIndex = 21)
    override val sciences = SblSecondaryCharacteristic(parent = this, secondaryIndex = 22)

    override val composure = SblSecondaryCharacteristic(parent = this, secondaryIndex = 23)
    override val strengthFeat = SblSecondaryCharacteristic(parent = this, secondaryIndex = 24)
    override val resistPain = SblSecondaryCharacteristic(parent = this, secondaryIndex = 25)

    override val disguise = SblSecondaryCharacteristic(parent = this, secondaryIndex = 26)
    override val hide = SblSecondaryCharacteristic(parent = this, secondaryIndex = 27)
    override val lockPick = SblSecondaryCharacteristic(parent = this, secondaryIndex = 28)
    override val poisons = SblSecondaryCharacteristic(parent = this, secondaryIndex = 29)
    override val theft = SblSecondaryCharacteristic(parent = this, secondaryIndex = 30)
    override val stealth = SblSecondaryCharacteristic(parent = this, secondaryIndex = 31)
    override val trapLore = SblSecondaryCharacteristic(parent = this, secondaryIndex = 32)

    override val art = SblSecondaryCharacteristic(parent = this, secondaryIndex = 33)
    override val dance = SblSecondaryCharacteristic(parent = this, secondaryIndex = 34)
    override val forging = SblSecondaryCharacteristic(parent = this, secondaryIndex = 35)
    override val music = SblSecondaryCharacteristic(parent = this, secondaryIndex = 36)
    override val sleightHand = SblSecondaryCharacteristic(parent = this, secondaryIndex = 37)

    /**
     * Attempts to toggle the natural bonus of the inputted characteristic.
     *
     * @param characteristic secondary characteristic to toggle the bonus of
     */
    override fun toggleNatBonus(characteristic: SecondaryCharacteristic) {
        //if natural bonus is currently off
        if(!characteristic.bonusApplied.value){
            //make true if characteristic is invested in and there are bonuses available
            if(countNatBonuses() < charInstance.lvl.intValue && characteristic.pointsApplied.intValue > 0)
                characteristic.setNatBonus(natBonus = true)
        }

        //if natural bonus is currently on
        else{
            //toggle off if not applied in a previous level
            if(sblChar.getCharAtLevel().secondaryList.getAllSecondaries()[(characteristic as SblSecondaryCharacteristic).secondaryIndex].bonusApplied.value)
                characteristic.setNatBonus(natBonus = false)
        }
    }

    /**
     * Updates the relevant values of secondary characteristics when the character changes level.
     */
    fun levelUpdate() {
        fullList().forEach{secondary ->
            (secondary as SblSecondaryCharacteristic).bonusApplied.value = secondary.natTaken()
            secondary.pointsAppliedUpdate()
            secondary.classTotalRefresh()
        }
    }
}