package com.paetus.animaCharCreator.character_creation.attributes.secondary_abilities

import androidx.compose.runtime.mutableStateOf

class CustomCharacteristic(
    private val parent: SecondaryList
): SecondaryCharacteristic(parent) {
    val name = mutableStateOf("")
    val isPublic = mutableStateOf(false)
    val fieldIndex = mutableStateOf(0)
    val primaryCharIndex = mutableStateOf(0)

    fun setName(input: String){name.value = input}

    fun setPublic(input: Boolean){isPublic.value = input}

    fun setFieldIndex(input: Int){fieldIndex.value = input}

    fun setPrimaryCharIndex(input: Int){primaryCharIndex.value = input}
}