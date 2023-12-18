package com.paetus.animaCharCreator

import java.io.ByteArrayOutputStream
import java.nio.charset.StandardCharsets

/**
 * Function that writes the inputted data to the given output stream.
 *
 * @param writer output stream to write to
 * @param input item to write to the output stream
 */
fun writeDataTo(
    writer: ByteArrayOutputStream,
    input: Any?
){
    //write the data to the output stream
    writer.write(
        """$input""".toByteArray(StandardCharsets.UTF_8),
        0,
        """$input""".toByteArray(StandardCharsets.UTF_8).size
    )

    //delineate data with a newline character
    writer.write(
        "\n".toByteArray(StandardCharsets.UTF_8),
        0,
        "\n".toByteArray(StandardCharsets.UTF_8).size
    )
}