package com.kata.truthordare.model.jdbc

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("truth_dare_challenges")
data class TruthDareChallengeRow(
    @Id val id: Long = 0,
    val challenge: String,
    val type: Type
) {

    companion object;

    enum class Type {
        TRUTH, DARE;
    }
}
