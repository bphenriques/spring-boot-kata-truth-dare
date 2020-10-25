package com.kata.truthordare.controller

import com.kata.truthordare.model.ChallengeType
import com.kata.truthordare.model.TruthDareChallenge
import com.kata.truthordare.service.ChallengesService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class TruthOrDareController(
    val challengesService: ChallengesService
) {

    @PostMapping("/truth-or-dare")
    fun getTruth(@RequestBody challenge: TruthDareChallenge): ResponseEntity<Unit> {
        challengesService.add(challenge)
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
