package com.neighborhoods.global.security

import com.neighborhoods.global.properties.JwtProperties
import com.neighborhoods.global.security.jwt.BearerAuthConverter
import com.neighborhoods.global.security.jwt.JwtVerifier
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers

@EnableWebFluxSecurity
class SecurityConfig(
    val jwtProperties: JwtProperties
) {
    @Bean
    fun filterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http
            .httpBasic().disable()
            .formLogin().disable()
            .logout().disable()
            .csrf { csrf -> csrf.disable() }
            .cors { cors -> cors.disable() }
            .authorizeExchange { exchanges ->
                exchanges.pathMatchers(HttpMethod.POST, "/auth").permitAll()
                exchanges.pathMatchers(HttpMethod.POST, "/user").permitAll()
                exchanges.anyExchange().authenticated()
            }
            .addFilterAt(bearerAuthenticationFilter(), SecurityWebFiltersOrder.AUTHENTICATION)

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    private fun bearerAuthenticationFilter(): AuthenticationWebFilter {
        val authenticationFilter: AuthenticationWebFilter

        val bearerConverter: ServerAuthenticationConverter
        val authManager: ReactiveAuthenticationManager

        authManager = TokenAuthenticationManager()
        authenticationFilter = AuthenticationWebFilter(authManager)
        bearerConverter = BearerAuthConverter(JwtVerifier(jwtProperties))

        authenticationFilter.setServerAuthenticationConverter(bearerConverter)
        authenticationFilter.setRequiresAuthenticationMatcher(ServerWebExchangeMatchers.anyExchange())

        return authenticationFilter
    }
}