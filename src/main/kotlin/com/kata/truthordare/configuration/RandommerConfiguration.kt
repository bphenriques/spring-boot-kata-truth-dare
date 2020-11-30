package com.kata.truthordare.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate

@Configuration
class RandommerConfiguration(
    @Value("\${randommer.url}") private val url: String,
    private val restTemplateBuilder: RestTemplateBuilder
) {

    @Bean
    fun restTemplate(): RestTemplate = restTemplateBuilder
        .rootUri(url)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
        .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
        .build()
}
