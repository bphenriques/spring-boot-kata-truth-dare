package com.kata.truthordare.util

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Assertions

object TestUtils {

    val MAPPER = jacksonObjectMapper()

    fun assertEqualJson(s1: String, s2: String) {
        Assertions.assertEquals(MAPPER.readTree(s1), MAPPER.readTree(s2))
    }
}
