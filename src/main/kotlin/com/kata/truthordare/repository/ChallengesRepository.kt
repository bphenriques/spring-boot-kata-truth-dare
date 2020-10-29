package com.kata.truthordare.repository

import com.kata.truthordare.model.jdbc.TruthDareChallengeRow
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ChallengesRepository : CrudRepository<TruthDareChallengeRow, Long> {

    @Query(
        """
        SELECT * FROM "truth_dare_challenges" WHERE type=:type LIMIT 1
        """
    )
    fun findOneByType(@Param("type") type: TruthDareChallengeRow.Type): TruthDareChallengeRow?
}
