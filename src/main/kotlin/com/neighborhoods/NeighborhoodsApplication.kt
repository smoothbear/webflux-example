package com.neighborhoods

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.web.reactive.error.ErrorWebFluxAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication
class NeighborhoodsApplication

fun main(args: Array<String>) {
    runApplication<NeighborhoodsApplication>(*args)
}
