package com.paetus.animaCharCreator.character_creation.attributes.secondary_abilities

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.paetus.animaCharCreator.writeDataTo
import java.io.ByteArrayOutputStream

/**
 * Custom item the user creates for use as a secondary characteristic.
 * Extends the SblSecondaryCharacteristic class.
 *
 * @param parent secondary list that will hold this object
 * @param secondaryIndex index to find the level record version of this characteristic
 */
class SblCustomCharacteristic(
    val parent: SblSecondaryList,
    secondaryIndex: Int
): SblSecondaryCharacteristic(
    parent = parent,
    secondaryIndex = secondaryIndex
){
    //initialize inputted name
    val name = mutableStateOf(value = "")

    //initialize creating character's file name
    val filename = mutableStateOf(value = "")

    //initialize public state of the characteristic
    val isPublic = mutableStateOf(value = false)

    //initialize which field this characteristic belongs to
    val fieldIndex = mutableIntStateOf(value = 0)

    //initialize which primary characteristic applies to this characteristic
    val primaryCharIndex = mutableIntStateOf(value = 0)

    /**
     * Sets the name of the custom secondary ability.
     *
     * @param name new name to apply
     */
    fun setName(name: String){this.name.value = name}

    /**
     * Defines the creating character of this item.
     *
     * @param filename name of the creating character's file
     */
    fun setFileName(filename: String){this.filename.value = filename}

    /**
     * Defines the public availability of the characteristic to the given value.
     *
     * @param isPublic true if the characteristic is to be publicly available
     */
    fun setPublic(isPublic: Boolean){this.isPublic.value = isPublic}

    /**
     * Redefines the field this characteristic belongs to.
     *
     * @param fieldIndex index of the secondary field to set
     */
    fun setFieldIndex(fieldIndex: Int){this.fieldIndex.intValue = fieldIndex}

    /**
     * Redefines the primary characteristic this stat is affected by.
     *
     * @param primeIndex index of the primary characteristic to set
     */
    fun setPrimaryCharIndex(primeIndex: Int){primaryCharIndex.intValue = primeIndex}

    /**
     * Write the name of this item as well as its secondary characteristic data.
     *
     * @param byteArray output stream of this item's data
     */
    override fun write(byteArray: ByteArrayOutputStream){
        writeDataTo(writer = byteArray, input = name.value)
        super.write(byteArray = byteArray)
    }

    /**
     * Constructs a custom characteristic with the given data.
     *
     * @param parent secondary list that holds this object
     * @param secondaryIndex index to find the level record version of this characteristic
     * @param filename name of the creating character's file
     * @param isPublic public state of the characteristic
     * @param field which field this item belongs to
     * @param primary which primary characteristic affects this item
     */
    constructor(
        parent: SblSecondaryList,
        secondaryIndex: Int,
        name: String,
        filename: String,
        isPublic: Boolean,
        field: Int,
        primary: Int
    ): this(parent = parent, secondaryIndex = secondaryIndex){
        setName(name = name)
        setFileName(filename = filename)
        setPublic(isPublic = isPublic)
        setFieldIndex(fieldIndex = field)
        setPrimaryCharIndex(primeIndex = primary)
    }
}