package com.kata.truthordare.controller

import com.kata.truthordare.model.ChallengeType
import com.kata.truthordare.model.TruthDareChallenge
import com.kata.truthordare.util.TestUtils.assertEqualJson
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

//Contract Testing
@SpringBootTest
@AutoConfigureMockMvc
class TruthOrDareControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    fun `addChallenge - Adds challenge`() {
        //TODO
    }

    @Test
    fun `list - It returns the set of challenges`() {
        //TODO
    }

    @Test
    fun `get - It returns the challenge of the provided type`() {
        //TODO
    }

    @Test
    fun `populate - It dispatches the request to the service`() {
        //TODO
    }
}
