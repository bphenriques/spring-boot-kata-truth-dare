package com.kata.truthordare.service

import com.kata.truthordare.gateway.TruthOrDareGateway
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.stereotype.Service

@Service
@ConditionalOnBean(TruthOrDareGateway::class)
class SeedService(
    private val challengesService: ChallengesService,
    private val truthOrDareGateway: TruthOrDareGateway
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun populate() {
        logger.info("Seeding application.")
        val challenges = truthOrDareGateway.getAllChallenges()
        logger.info("Obtained ${challenges.size} challenges.")
        for (challenge in challenges) {
            challengesService.add(challenge)
        }
    }
}
