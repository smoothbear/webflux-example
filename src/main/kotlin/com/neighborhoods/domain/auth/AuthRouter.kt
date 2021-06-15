package com.neighborhoods.domain.auth

import com.neighborhoods.domain.auth.handler.AuthHandler
import com.neighborhoods.domain.auth.handler.dto.LoginDto
import com.neighborhoods.domain.auth.handler.dto.TokenResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springdoc.core.annotations.RouterOperation
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
    @RouterOperation(
        path = "/auth", beanClass = AuthHandler::class, beanMethod = "login",
        operation = Operation(
            operationId = "login", summary = "Login by email and password", tags = ["auth"],
            requestBody = RequestBody(
                required = true, description = "Enter Request body as Json Object",
                content = [Content(schema = Schema(implementation = LoginDto::class))]
            ),
            responses = [
                ApiResponse(
                    responseCode = "200", description = "Successfully logined",
                    content = [Content(mediaType = "APPLICATION_JSON", schema = Schema(implementation = TokenResponse::class))]
                ),
                ApiResponse(
                    responseCode = "401",
                    description = "Password is not matched",
                    content = [Content()]
                ),
                ApiResponse(
                    responseCode = "404",
                    description = "User is not found",
                    content = [Content()]
                )]
        )
    )
    fun authRouterFunc() = nest(
        path("/auth"),
        router {
            listOf(
                POST("", handler::login)
            )
        }
    )
}