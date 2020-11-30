package com.kata.truthordare.acceptance

import com.kata.truthordare.model.ChallengeType
import com.kata.truthordare.model.TruthDareChallenge
import com.kata.truthordare.util.RandommerStubber
import com.kata.truthordare.util.TestUtils
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class PopulateAndFindChallenge {

    @Autowired
    private lateinit var mvc: MockMvc

    @Autowired
    private lateinit var randommerStubber: RandommerStubber

    @Test
    fun `populate and find - It returns the single challenge populated from Randommer`() {
        // Arrange
        val expectedChallenge = TruthDareChallenge(
            "some-challenge",
            ChallengeType.TRUTH
        )
        val expectedChallengeJson = """
                {
                    "challenge": "${expectedChallenge.challenge}",
                    "type": "${expectedChallenge.type}"
                }
                """.trimIndent()
        randommerStubber.stubFetchOk(1, expectedChallenge.type, expectedChallenge.challenge)

        // Act and assert the first part
        mvc.post("/truth-or-dare/populate?type=${expectedChallenge.type}&number=1")
            .andExpect {
                status { isOk }
            }
        randommerStubber.verifyStubs()

        // Act and assert the second part
        val responseBody = mvc.get("/truth-or-dare?type=${expectedChallenge.type}")
            .andExpect {
                status { isOk }
            }.andReturn().response.contentAsString
        TestUtils.assertEqualJson(responseBody, expectedChallengeJson)
    }
}
