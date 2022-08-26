package com.example.animabuilder.Serializables

import java.io.Serializable
import java.util.function.Consumer

interface SerialConsumer<T> : Consumer<T>, Serializable