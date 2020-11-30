package com.kata.truthordare.gateway

import com.kata.truthordare.model.ChallengeType
import com.kata.truthordare.model.TruthDareChallenge
import com.kata.truthordare.util.RandommerStubber
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RandommerGatewayTest {

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
        randommerStubber.stubFetchOk(1, ChallengeType.TRUTH, "some-challenge")
        val expectedChallenge = TruthDareChallenge(
            "some-challenge",
            ChallengeType.TRUTH
        )

        //Act
        val result = subject.getTruthDareChallenge(ChallengeType.TRUTH)

        //Assert
        Assertions.assertEquals(expectedChallenge, result)
        randommerStubber.verifyStubs()
    }

    @Test
    fun `getTruthDareChallenge - It throws exception if there is an error when fetching`() {
        // TODO on another time
    }
}
