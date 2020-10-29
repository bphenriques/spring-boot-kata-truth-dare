package com.kata.truthordare.gateway

import com.kata.truthordare.configuration.TruthOrDareApiRestTemplateConfiguration
import com.kata.truthordare.model.ChallengeType
import com.kata.truthordare.model.TruthDareChallenge
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
@ConditionalOnBean(TruthOrDareApiRestTemplateConfiguration::class)
class TruthOrDareGateway(
    @Qualifier("truth-or-dare") private val restTemplate: RestTemplate
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun getAllChallenges(): Set<TruthDareChallenge> =
        try {
            val response = restTemplate.exchange(
                "/truth-or-dare/list",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                Array<TruthDareGatewayResponse>::class.java
            )

            when (response.statusCode) {
                HttpStatus.OK -> {
                    val body = response.body ?: error("Expected body with HTTP Status Code 200")
                    body.map(TruthDareChallenge.Companion::fromTruthDareGatewayResponse).toSet()
                }
                else -> {
                    logger.error("Unexpected 2XX status code ${response.statusCode}. Returning empty set.")
                    emptySet()
                }
            }
        } catch (ex: Exception) {
            logger.error("Failed to fetch challenges: ${ex.message}", ex)
            emptySet()
        }
}

data class TruthDareGatewayResponse(
    val challenge: String,
    val type: Type
) {
    enum class Type {
        TRUTH, DARE;
    }
}

private fun TruthDareChallenge.Companion.fromTruthDareGatewayResponse(response: TruthDareGatewayResponse) =
    TruthDareChallenge(
        challenge = response.challenge,
        type = when (response.type) {
            TruthDareGatewayResponse.Type.TRUTH -> ChallengeType.TRUTH
            TruthDareGatewayResponse.Type.DARE -> ChallengeType.DARE
        }
    )

