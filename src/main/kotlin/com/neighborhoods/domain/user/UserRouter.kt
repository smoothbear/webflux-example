package com.neighborhoods.domain.user

import com.neighborhoods.domain.user.handler.UserHandler
import com.neighborhoods.domain.user.handler.dto.UserDto
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
class UserRouter(
    private val handler: UserHandler
) {
    @Bean
    @RouterOperation(
        path = "/user", beanClass = UserHandler::class, beanMethod = "register",
        operation = Operation(
            operationId = "register", summary = "Register by email and password, name", tags = ["user"],
            requestBody = RequestBody(
                required = true, description = "Enter Request body as Json Object",
                content = [Content(schema = Schema(implementation = UserDto::class))]
            ),
            responses = [
                ApiResponse(
                    responseCode = "200", description = "Successfully logined",
                    content = [Content()]
                ),
                ApiResponse(
                    responseCode = "409",
                    description = "User is already exists",
                    content = [Content()]
                )]
        )
    )
    fun userRouterFunc() = nest(
        path("/user"),
        router {
            listOf(
                POST("", handler::register),
            )
        }
    )
}