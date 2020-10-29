package com.kata.truthordare.service

import com.kata.truthordare.model.ChallengeType
import com.kata.truthordare.model.TruthDareChallenge
import com.kata.truthordare.model.jdbc.TruthDareChallengeRow
import com.kata.truthordare.repository.ChallengesRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ChallengesService(
    private val challengesRepository: ChallengesRepository
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun add(challenge: TruthDareChallenge) {
        logger.info("Registering challenge $challenge")
        challengesRepository.save(TruthDareChallengeRow.from(challenge))
    }

    fun getAll(): Set<TruthDareChallenge> {
        logger.info("Listing challenges..")
        return challengesRepository.findAll().map { it.toTruthDareChallenge() }.toSet()
    }

    fun get(type: ChallengeType): TruthDareChallenge? {
        logger.info("Obtaining challenge (type=$type)")
        // Ideally the repository should be the one translating Service Entities to DB entities.
        return challengesRepository.findOneByType(type.toRowType())?.toTruthDareChallenge()
    }

    private fun TruthDareChallengeRow.toTruthDareChallenge() = TruthDareChallenge(
        challenge = challenge,
        type = type.toChallengeType()
    )

    private fun ChallengeType.toRowType() = when(this) {
        ChallengeType.TRUTH -> TruthDareChallengeRow.Type.TRUTH
        ChallengeType.DARE -> TruthDareChallengeRow.Type.DARE
    }

    private fun TruthDareChallengeRow.Type.toChallengeType() = when(this) {
        TruthDareChallengeRow.Type.TRUTH -> ChallengeType.TRUTH
        TruthDareChallengeRow.Type.DARE -> ChallengeType.DARE
    }

    private fun TruthDareChallengeRow.Companion.from(challenge: TruthDareChallenge) = TruthDareChallengeRow(
        challenge = challenge.challenge,
        type = challenge.type.toRowType()
    )
}
