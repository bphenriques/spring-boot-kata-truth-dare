package com.kata.truthordare.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate

/**
 * https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-external-config
 */
@Configuration
@ConditionalOnProperty("seed.enabled", havingValue = "true")
class TruthOrDareApiRestTemplateConfiguration(
    private val restTemplateBuilder: RestTemplateBuilder,
    @Value("\${truthdare.gateway.url}") val url: String
) {

    @Bean("truth-or-dare")
    fun restTemplate(): RestTemplate = restTemplateBuilder
        .rootUri(url)
        .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build()
}
