package com.paetus.animaCharCreator

import java.io.ByteArrayOutputStream
import java.nio.charset.StandardCharsets

/**
 * Function that writes the inputted data to the given output stream.
 *
 * @param writer output stream to write to
 * @param input item to write to the output stream
 * @param permitNegative flag for if the inputted item is allowed to be a negative integer
 */
fun writeDataTo(
    writer: ByteArrayOutputStream,
    input: Any?,
    permitNegative: Boolean = false
){
    //catch negative inputs and record them as 0s instead
    if(input is Int && input < 0 && !permitNegative){
        writer.write(
            """0""".toByteArray(StandardCharsets.UTF_8),
            0,
            """0""".toByteArray(StandardCharsets.UTF_8).size
        )
    }
    else {
        //write the data to the output stream
        writer.write(
            """$input""".toByteArray(StandardCharsets.UTF_8),
            0,
            """$input""".toByteArray(StandardCharsets.UTF_8).size
        )
    }

    //delineate data with a newline character
    writer.write(
        "\n".toByteArray(StandardCharsets.UTF_8),
        0,
        "\n".toByteArray(StandardCharsets.UTF_8).size
    )
}