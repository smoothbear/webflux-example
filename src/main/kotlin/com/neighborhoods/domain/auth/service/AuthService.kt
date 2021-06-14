package com.neighborhoods.domain.auth.service

import com.neighborhoods.domain.auth.handler.dto.LoginDto
import com.neighborhoods.domain.auth.handler.dto.TokenResponse
import reactor.core.publisher.Mono

interface AuthService {
    fun login(request: LoginDto): Mono<TokenResponse>
}