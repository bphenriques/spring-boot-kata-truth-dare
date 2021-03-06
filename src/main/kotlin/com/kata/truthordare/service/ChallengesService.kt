package com.kata.truthordare.service

import com.kata.truthordare.model.ChallengeType
import com.kata.truthordare.model.TruthDareChallenge
import com.kata.truthordare.repository.ChallengesRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ChallengesService(
    private val challengesRepository: ChallengesRepository
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun add(challenge: TruthDareChallenge): TruthDareChallenge {
        logger.info("Registering challenge $challenge")
        val savedChallenge = challengesRepository.save(challenge)
        return savedChallenge
    }

    fun getAll(): Set<TruthDareChallenge> {
        logger.info("Listing challenges..")
        return challengesRepository.findAll()
    }

    fun get(type: ChallengeType): TruthDareChallenge? {
        logger.info("Obtaining challenge (type=$type)")
        return challengesRepository.findOneByType(type)
    }
}
