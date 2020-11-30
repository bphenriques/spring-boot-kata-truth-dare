package com.kata.truthordare.controller

import com.kata.truthordare.model.ChallengeType
import com.kata.truthordare.model.TruthDareChallenge
import com.kata.truthordare.service.ChallengesService
import com.kata.truthordare.service.SeedService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class TruthOrDareController(
    private val challengesService: ChallengesService,
    private val seedService: SeedService
) {

    @PostMapping("/truth-or-dare")
    fun addChallenge(@RequestBody challenge: TruthDareChallenge): ResponseEntity<TruthDareChallenge> {
        val resultingChallenge = challengesService.add(challenge)
        return ResponseEntity.ok(resultingChallenge)
    }

    /**
     * Another alternative is to run this when the application launches. Not introducing new concepts for now.
     */
    @PostMapping("/truth-or-dare/populate")
    fun populate(@RequestParam type: ChallengeType, @RequestParam number: Int): ResponseEntity<Unit> {
        seedService.populate(type, number)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/truth-or-dare/list")
    fun list(): ResponseEntity<Set<TruthDareChallenge>> {
        val result = challengesService.getAll()
        return ResponseEntity.ok(result)
    }

    @GetMapping("/truth-or-dare")
    fun getChallenge(@RequestParam type: ChallengeType): ResponseEntity<TruthDareChallenge> {
        val result = challengesService.get(type)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.noContent().build()
        }
    }
}
