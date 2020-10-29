package com.kata.truthordare.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

/**
 * There are way too many ways to achieve this.
 */
@Service
class StartupService(
    private val seedService: SeedService?
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @PostConstruct
    fun startup() {
        logger.info("Starting up application.")
        seedService?.populate()
    }
}
