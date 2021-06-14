package com.neighborhoods.global.security.jwt

import org.springframework.security.core.Authentication
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.util.function.Function

class BearerAuthConverter : Function<ServerWebExchange, Mono<Authentication>> {
    override fun apply(t: ServerWebExchange): Mono<Authentication> {
        TODO("Not yet implemented")
    }
}