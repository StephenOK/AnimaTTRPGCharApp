package com.example.animabuilder.Serializables

import java.io.Serializable
import java.util.function.Supplier

interface SerialSupplier<T> : Supplier<T>, Serializable