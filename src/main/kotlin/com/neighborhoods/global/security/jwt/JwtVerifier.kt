package com.neighborhoods.global.security.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.neighborhoods.global.properties.JwtProperties
import com.neighborhoods.global.security.jwt.exception.InvalidTokenException
import reactor.core.publisher.Mono

class JwtVerifier(
    val properties: JwtProperties
) {
    fun check(accessToken: String): Mono<DecodedJWT> {
        return Mono.just(JWT.require(Algorithm.HMAC256(properties.secret)))
            .flatMap {
                verification -> Mono.just(verification.build().verify(accessToken))
            }.onErrorMap { InvalidTokenException() }
    }
}