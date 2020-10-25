package com.kata.truthordare.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController {

    @GetMapping
    fun hello(): ResponseEntity<String> {
        return ResponseEntity.ok("hello world")
    }
}
