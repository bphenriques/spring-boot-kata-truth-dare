package com.kata.truthordare.service

import com.kata.truthordare.gateway.RandommerGateway
import com.kata.truthordare.model.ChallengeType
import com.kata.truthordare.model.TruthDareChallenge
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Test

class SeedServiceTest {

    private val challengesService = mock<ChallengesService>()
    private val randommerGateway = mock<RandommerGateway>()

    private val subject = SeedService(
        challengesService = challengesService,
        randommerGateway = randommerGateway
    )

    @Test
    fun `populate - It does nothing when the number is 0`() {
        subject.populate(ChallengeType.TRUTH, 0)

        verifyNoMoreInteractions(challengesService, randommerGateway)
    }

    @Test
    fun `populate - It fetches _number_ challenges from the randommer API and registers them`() {
        val challenge1 = TruthDareChallenge("hello-1", ChallengeType.TRUTH)
        val challenge2 = TruthDareChallenge("hello-2", ChallengeType.TRUTH)
        whenever(randommerGateway.getTruthDareChallenge(eq(challenge1.type))).thenReturn(
            challenge1,
            challenge2
        )

        subject.populate(ChallengeType.TRUTH, 2)

        verify(randommerGateway, times(2)).getTruthDareChallenge(eq(ChallengeType.TRUTH))
        verify(challengesService, times(1)).add(eq(challenge1))
        verify(challengesService, times(1)).add(eq(challenge2))
        verifyNoMoreInteractions(challengesService, randommerGateway)
    }
}
