package com.neighborhoods.domain.auth.handler

import com.neighborhoods.domain.auth.handler.dto.LoginDto
import com.neighborhoods.domain.auth.handler.dto.TokenResponse
import com.neighborhoods.domain.auth.service.AuthService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono

@Component
class AuthHandler(
    private val authService: AuthService
) {
    fun login(request: ServerRequest): Mono<ServerResponse> {
        val response: Mono<TokenResponse> = request.bodyToMono(LoginDto::class.java)
            .flatMap { dto -> authService.login(dto) }

        return ok().body(response, TokenResponse::class.java)
    }
}