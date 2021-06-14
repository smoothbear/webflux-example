package com.neighborhoods.domain.user.handler

import com.neighborhoods.domain.user.handler.dto.UserDto
import com.neighborhoods.domain.user.service.UserService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.created
import reactor.core.publisher.Mono
import java.net.URI

@Component
class UserHandler(
    private val userService: UserService
) {
    fun register(request: ServerRequest): Mono<ServerResponse> {
        val result: Mono<Void> = request.bodyToMono(UserDto::class.java)
            .flatMap { dto -> userService.register(dto) }

        return created(URI.create("/user")).body(result, Void::class.java)
    }
}