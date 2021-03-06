package com.kata.truthordare.util

import com.kata.truthordare.gateway.RandommerGateway
import com.kata.truthordare.model.ChallengeType
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.test.web.client.ExpectedCount
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.test.web.client.match.MockRestRequestMatchers.content
import org.springframework.test.web.client.match.MockRestRequestMatchers.method
import org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo
import org.springframework.test.web.client.response.MockRestResponseCreators.withStatus
import org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess
import org.springframework.web.client.RestTemplate

/**
 * Use like @Autowired private lateinit var randommerStubber2: RandommerStubber2
 *
 * See: https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html#spring-mvc-test-client
 */
@Component
class RandommerStubber(
    private val restTemplate: RestTemplate,
    @Value("\${randommer.url}") private val url: String
) : BeforeEachCallback, BeforeAllCallback {

    private val mockRestServiceServer = MockRestServiceServer
        .bindTo(restTemplate)
        .ignoreExpectOrder(true)
        .build()

    fun stubFetchOk(times: Int, type: ChallengeType, text: String) {
        val randommerChallengeType = when (type) {
            ChallengeType.DARE -> RandommerGateway.RandommerTruthDareChallengeResponse.Type.DARE
            ChallengeType.TRUTH -> RandommerGateway.RandommerTruthDareChallengeResponse.Type.TRUTH
        }

        mockRestServiceServer.expect(
            ExpectedCount.times(times),
            requestTo("$url/truth-dare-generator")
        )
            .andExpect(method(HttpMethod.POST))
            .andExpect(content().string("category=friendly&type=$randommerChallengeType"))
            .andRespond(
                withSuccess()
                    .headers(HttpHeaders().apply {
                        contentType = MediaType.APPLICATION_JSON
                    })
                    .body(
                        """
                            {
                                "id": 1,
                                "category": "friendly",
                                "type": "$randommerChallengeType",
                                "text": "$text"
                            }
                        """.trimIndent()
                    ))
    }

    fun stubFetchFailure(type: ChallengeType, status: HttpStatus) {
        val randommerChallengeType = when (type) {
            ChallengeType.DARE -> RandommerGateway.RandommerTruthDareChallengeResponse.Type.DARE
            ChallengeType.TRUTH -> RandommerGateway.RandommerTruthDareChallengeResponse.Type.TRUTH
        }

        mockRestServiceServer.expect(
            ExpectedCount.times(1),
            requestTo("$url/truth-dare-generator")
        )
            .andExpect(method(HttpMethod.POST))
            .andExpect(content().string("category=friendly&type=$randommerChallengeType"))
            .andRespond(
                withStatus(status)
            )
    }

    fun verifyStubs() {
        mockRestServiceServer.verify()
    }

    fun reset() {
        mockRestServiceServer.reset()
    }

    override fun beforeEach(context: ExtensionContext?) {
        reset()
    }

    override fun beforeAll(context: ExtensionContext?) {
        reset()
    }
}
