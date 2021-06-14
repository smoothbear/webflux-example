package com.neighborhoods.domain.auth.service

import com.neighborhoods.domain.auth.handler.dto.LoginDto
import com.neighborhoods.domain.auth.handler.dto.TokenResponse
import com.neighborhoods.domain.auth.exception.PasswordNotMatchedException
import com.neighborhoods.domain.auth.exception.UserNotFoundException
import com.neighborhoods.domain.user.entity.UserRepository
import com.neighborhoods.global.security.jwt.JwtProvider
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class AuthServiceImpl(
    private val userRepository: UserRepository,
    private val jwtProvider: JwtProvider,
    private val passwordEncoder: PasswordEncoder,
) : AuthService {
    override fun login(request: LoginDto): Mono<TokenResponse> {
        return userRepository.findById(request.email)
            .switchIfEmpty(Mono.error(UserNotFoundException()))
            .flatMap { user ->
                Mono.just(passwordEncoder.matches(request.password, user.password))
            }
            .flatMap { matches ->
                if (!matches) Mono.error(PasswordNotMatchedException())
                else Mono.just(
                    TokenResponse(
                        accessToken = jwtProvider.generateAccessToken(request.email)
                    )
                )
            }
    }
}