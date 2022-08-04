package com.example.animabuilder.CharacterCreation;

import java.io.Serializable;
import java.util.function.Supplier;

public interface SerialSupplier<T> extends Supplier<T>, Serializable {
}
