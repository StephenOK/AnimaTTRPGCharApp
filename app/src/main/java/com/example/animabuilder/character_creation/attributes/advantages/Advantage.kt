package com.example.animabuilder.character_creation.attributes.advantages

class Advantage (
    var name: String?,
    var description: String?,
    var cost: Int,

    var onReceive: () -> Unit,
    var onRemove: () -> Unit,
)