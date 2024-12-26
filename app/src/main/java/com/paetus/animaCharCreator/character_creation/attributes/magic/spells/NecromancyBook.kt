package com.paetus.animaCharCreator.character_creation.attributes.magic.spells

import com.paetus.animaCharCreator.character_creation.attributes.magic.Magic
import com.paetus.animaCharCreator.character_creation.attributes.magic.spells.spellbook.NecromancySpells

/**
 * List of spells associated with necromancy.
 *
 * @param necroSpells list of spells in necromancy
 * @param mag magic section of the character profile
 */
open class NecromancyBook(
    necroSpells: NecromancySpells,
    mag: Magic
): MagicBook(necroSpells, mag, -1){
    /**
     * Sets the primary status of this book in ways unique to the necromancy book.
     *
     * @param isTaking value to set the primary status to
     */
    override fun changePrimary(isTaking: Boolean) {
        //if making necromancy book primary element
        if(hasInvestment() && isTaking){
            //remove all other elements' primary statuses
            for(index in 0..9)
                magic.retrieveBooks()[index].isPrimary.value = false

            //apply primary status to this book
            isPrimary.value = true
        }
        //if removing primary element status
        else if (!isTaking && isPrimary.value){
            isPrimary.value = false

            //initialize opposing book pointer
            var index = 0

            while(index < 10){
                //retrieve the two opposing books
                val book1 = magic.retrieveBooks()[index++]
                val book2 = magic.retrieveBooks()[index++]

                //if only first book invested in, set first book as primary element
                if(book1.hasInvestment() && !book2.hasInvestment())
                    book1.changePrimary(true)

                //if only second book invested in, set second book as primary element
                else if(book2.hasInvestment() && !book1.hasInvestment())
                    book2.changePrimary(true)
                //if both books invested in
                else if(book1.hasInvestment() && book2.hasInvestment()){
                    //set second book as primary if it has more points, more individual spells, or if the only one with a natural bonus
                    if(book2.pointsIn.intValue > book1.pointsIn.intValue ||
                        book2.individualSpells.size > book1.individualSpells.size ||
                        book2.isNatural.value && !book1.isNatural.value)
                        book2.changePrimary(true)
                    //otherwise, set first book as primary
                    else
                        book1.changePrimary(true)
                }
            }
        }
    }

    /**
     * Check if the character has the indicated free spell.
     *
     * @param freeSpell spell to search for in the books
     * @return magic book the free spell belongs to
     */
    override fun charHasFreeSpell(freeSpell: FreeSpell): MagicBook?{
        //check all other books for the queried item
        magic.retrieveBooks().forEach{opposing ->
            opposing.freeSpells.forEach{spell ->
                if(spell.saveName == freeSpell.saveName) return opposing
            }
        }

        //check this book for the spell
        freeSpells.forEach{spell ->
            if(spell.saveName == freeSpell.saveName) return this
        }

        return null
    }

    /**
     * Gets if the opposite element has any points invested in it.
     *
     * @return true if points in opposite element's book
     */
    override fun getOpposedInvestment(): Boolean {
        for(index in 0..9)
            if(magic.retrieveBooks()[index].isPrimary.value)
                return true

        return false
    }
}