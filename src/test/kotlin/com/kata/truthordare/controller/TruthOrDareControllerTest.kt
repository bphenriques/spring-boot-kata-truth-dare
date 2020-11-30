package com.kata.truthordare.controller

import com.kata.truthordare.model.ChallengeType
import com.kata.truthordare.model.TruthDareChallenge
import com.kata.truthordare.service.ChallengesService
import com.kata.truthordare.util.TestUtils.assertEqualJson
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

//Contract Testing
@SpringBootTest
@AutoConfigureMockMvc
class TruthOrDareControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var challengesService: ChallengesService

    @Test
    fun `addChallenge - Adds challenge`() {
        // Arrange
        val challenge = TruthDareChallenge(
            challenge = "hello",
            type = ChallengeType.DARE
        )
        whenever(challengesService.add(eq(challenge))).thenReturn(challenge)
        val jsonBody = """
                {
                    "challenge": "${challenge.challenge}",
                    "type": "${challenge.type}"
                }
            """.trimIndent()
        val expectedResponseBody = """
                {
                    "challenge": "${challenge.challenge}",
                    "type": "${challenge.type}"
                }
            """.trimIndent()

        // Act
        val result = mvc.post("/truth-or-dare") {
            contentType = MediaType.APPLICATION_JSON
            content = jsonBody
        }

        // Assert
        val response = result.andExpect {
            status { isOk }
            content { contentType(MediaType.APPLICATION_JSON) }
        }.andReturn().response
        assertEqualJson(response.contentAsString, expectedResponseBody)
        verify(challengesService, times(1)).add(eq(challenge))
        verifyNoMoreInteractions(challengesService)
    }

    @Test
    fun `list - It returns the set of challenges`() {
        //TODO but not now
    }

    @Test
    fun `get - It returns the challenge of the provided type`() {
        //TODO but not now
    }

    @Test
    fun `populate - It dispatches the request to the service`() {
        //TODO but not now
    }
}
