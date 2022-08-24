package com.example.animabuilder.CharacterCreation

import java.io.Serializable
import java.util.function.Supplier

interface SerialSupplier<T> : Supplier<T>, Serializable