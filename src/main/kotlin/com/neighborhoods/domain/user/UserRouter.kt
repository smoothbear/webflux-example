package com.neighborhoods.domain.user

import com.neighborhoods.domain.user.handler.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates.path
import org.springframework.web.reactive.function.server.RouterFunctions.nest
import org.springframework.web.reactive.function.server.router

@Configuration
class UserRouter(
    private val handler: UserHandler
) {
    @Bean
    fun userRouterFunc() = nest(
        path("/user"),
        router {
            listOf(
                POST("", handler::register),
                GET("", handler::getUser)
            )
        }
    )
}