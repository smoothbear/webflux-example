package com.neighborhoods.domain.auth

import com.neighborhoods.domain.auth.handler.AuthHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates.path
import org.springframework.web.reactive.function.server.RouterFunctions.nest
import org.springframework.web.reactive.function.server.router

@Configuration
class AuthRouter(
    private val handler: AuthHandler
) {
    @Bean
    fun authRouterFunc() = nest(
        path("/auth"),
        router {
            listOf(
                POST("", handler::login)
            )
        }
    )
}