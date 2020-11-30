package com.kata.truthordare.gateway

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonValue
import com.kata.truthordare.model.ChallengeType
import com.kata.truthordare.model.TruthDareChallenge
import org.slf4j.LoggerFactory
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class RandommerGateway(
    private val restTemplate: RestTemplate
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun getTruthDareChallenge(type: ChallengeType): TruthDareChallenge {
        logger.info("Obtaining challenge (type=$type)")
        val category = "friendly"
        val randommerChallengeType = when (type) {
            ChallengeType.DARE -> "dare"
            ChallengeType.TRUTH -> "truth"
        }
        val response = restTemplate.exchange(
            "/truth-dare-generator",
            HttpMethod.POST,
            HttpEntity("category=$category&type=$randommerChallengeType"),
            RandommerTruthDareChallengeResponse::class.java
        )

        return response.body!!.toTruthDareChallenge()
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class RandommerTruthDareChallengeResponse(
        val text: String,
        val type: Type
    ) {
        enum class Type {
            TRUTH, DARE;

            @JsonValue
            override fun toString() = super.toString().toLowerCase()
        }

        fun toTruthDareChallenge() = TruthDareChallenge(
            challenge = text,
            type = when (type) {
                Type.TRUTH -> ChallengeType.TRUTH
                Type.DARE -> ChallengeType.DARE
            }
        )
    }
}

