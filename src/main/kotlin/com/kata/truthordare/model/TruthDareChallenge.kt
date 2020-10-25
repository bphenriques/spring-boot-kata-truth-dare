package com.kata.truthordare.model

data class TruthDareChallenge(
    val challenge: String,
    val type: ChallengeType
)

enum class ChallengeType {
    TRUTH, DARE;
}
