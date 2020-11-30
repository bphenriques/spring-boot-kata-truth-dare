package com.kata.truthordare.service

import com.kata.truthordare.gateway.RandommerGateway
import com.kata.truthordare.model.ChallengeType
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class SeedService(
    private val challengesService: ChallengesService,
    private val randommerGateway: RandommerGateway
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun populate(type: ChallengeType, number: Int) {
        logger.info("Fetching challenges (type=$type number=$number)")
        repeat(number) {
            val challenge = randommerGateway.getTruthDareChallenge(type)
            challengesService.add(challenge)
        }
    }
}
