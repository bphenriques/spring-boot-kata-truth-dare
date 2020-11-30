package com.kata.truthordare.repository

import com.kata.truthordare.model.ChallengeType
import com.kata.truthordare.model.TruthDareChallenge
import org.springframework.stereotype.Repository

@Repository
class ChallengesRepository {

    private val existingChallenges = mutableSetOf<TruthDareChallenge>()

    fun save(challenge: TruthDareChallenge): TruthDareChallenge {
        existingChallenges.add(challenge)
        return challenge
    }

    fun findAll(): Set<TruthDareChallenge> = existingChallenges

    fun findOneByType(type: ChallengeType): TruthDareChallenge? = findAll().firstOrNull { it.type == type}
}
