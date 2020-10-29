package com.kata.truthordare.model

data class TruthDareChallenge(
    val challenge: String,
    val type: ChallengeType
) {
    companion object
}

enum class ChallengeType {
    TRUTH, DARE;
}
