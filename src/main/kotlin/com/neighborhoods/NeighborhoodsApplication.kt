package com.neighborhoods

import com.neighborhoods.global.properties.JwtProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.web.reactive.error.ErrorWebFluxAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties::class)
class NeighborhoodsApplication

fun main(args: Array<String>) {
    runApplication<NeighborhoodsApplication>(*args)
}
