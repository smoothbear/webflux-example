package com.neighborhoods.global.security.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.neighborhoods.global.security.jwt.exception.InvalidTokenException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class JwtVerifier {
    @Value("\${security.jwt.secret}")
    private lateinit var secretKey: String

    fun check(accessToken: String): Mono<DecodedJWT> {
        return Mono.just(JWT.require(Algorithm.HMAC256(secretKey)).build().verify(accessToken))
            .onErrorResume { e -> Mono.error(InvalidTokenException::class) }
    }
}