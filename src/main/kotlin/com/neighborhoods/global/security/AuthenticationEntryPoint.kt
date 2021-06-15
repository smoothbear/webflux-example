package com.neighborhoods.global.security

import com.neighborhoods.global.log.Logger
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.server.ServerAuthenticationEntryPoint
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class AuthenticationEntryPoint : ServerAuthenticationEntryPoint {

    companion object: Logger

    override fun commence(exchange: ServerWebExchange, ex: AuthenticationException): Mono<Void> {
        exchange.response.statusCode = HttpStatus.UNAUTHORIZED
        exchange.response.headers.contentType = MediaType.APPLICATION_JSON

        log.error("Error at ${exchange.request.path}, status 401, cause: Not authenticated.")

        val str = "{\n" +
                "  \"status\": 401,\n" +
                "  \"message\": \"Not authenticated.\"\n" +
                "}"
        val buffer = exchange.response.bufferFactory().wrap(str.toByteArray())

        return exchange.response.writeWith(Flux.just(buffer))
    }
}