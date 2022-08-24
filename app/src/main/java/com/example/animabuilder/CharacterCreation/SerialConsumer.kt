package com.example.animabuilder.CharacterCreation

import java.io.Serializable
import java.util.function.Consumer

interface SerialConsumer<T> : Consumer<T>, Serializable