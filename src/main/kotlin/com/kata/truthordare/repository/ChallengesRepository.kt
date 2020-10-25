package com.kata.truthordare.repository

import com.kata.truthordare.model.TruthDareChallenge
import org.springframework.stereotype.Repository

@Repository
class ChallengesRepository {

    private val existingChallenges = mutableSetOf<TruthDareChallenge>()

    fun save(challenge: TruthDareChallenge) {
        existingChallenges.add(challenge)
    }

    fun findAll(): Set<TruthDareChallenge> = existingChallenges
}
