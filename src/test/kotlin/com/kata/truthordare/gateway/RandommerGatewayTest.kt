package com.kata.truthordare.gateway

import com.kata.truthordare.model.ChallengeType
import com.kata.truthordare.model.TruthDareChallenge
import com.kata.truthordare.util.RandommerStubber
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.RegisterExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import java.lang.Exception

@SpringBootTest
class RandommerGatewayTest2 {

    @Autowired
    private lateinit var randommerStubber: RandommerStubber

    @Autowired
    private lateinit var subject: RandommerGateway

    @BeforeEach
    fun beforeEach() {
        randommerStubber.reset()
    }

    @Test
    fun `getTruthDareChallenge - It contacts the service correctly`() {
        // Arrange
        // Act
        // Assert
    }

    @Test
    fun `getTruthDareChallenge - It throws exception if there is an error when fetching`() {
        // TODO on another time
    }
}
