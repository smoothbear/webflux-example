package com.neighborhoods.global.security.jwt

import org.springframework.http.HttpHeaders
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

class BearerAuthConverter(
    private val jwtVerifier: JwtVerifier
) : ServerAuthenticationConverter {

    private val BEARER = "Bearer "

    private fun extract(exchange: ServerWebExchange): Mono<String> {
        return Mono.justOrEmpty(
            exchange.request
                .headers
                .getFirst(HttpHeaders.AUTHORIZATION)
        )
    }

    override fun convert(exchange: ServerWebExchange): Mono<Authentication> {
        return Mono.justOrEmpty(exchange)
            .flatMap { ex -> extract(ex) }
            .filter { authValue -> authValue.length > BEARER.length }
            .flatMap { authValue -> Mono.justOrEmpty(authValue.substring(BEARER.length)) }
            .flatMap { value -> jwtVerifier.check(value) }
            .flatMap { decoded ->
                Mono.just(
                    AuthenticationToken(
                        tokenType = decoded.type,
                        email = decoded.claims["email"].toString(),
                        decodedJwtToken = decoded.token
                    )
                )
            }
    }
}