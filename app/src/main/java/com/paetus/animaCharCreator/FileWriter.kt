package com.paetus.animaCharCreator

import java.io.ByteArrayOutputStream
import java.nio.charset.StandardCharsets

fun writeDataTo(
    writer: ByteArrayOutputStream,
    input: Any?
){
    writer.write(
        """$input""".toByteArray(StandardCharsets.UTF_8),
        0,
        """$input""".toByteArray(StandardCharsets.UTF_8).size
    )

    writer.write(
        "\n".toByteArray(StandardCharsets.UTF_8),
        0,
        "\n".toByteArray(StandardCharsets.UTF_8).size
    )
}