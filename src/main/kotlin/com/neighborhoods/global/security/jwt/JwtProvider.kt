package com.neighborhoods.global.security.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.neighborhoods.global.properties.JwtProperties
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class JwtProvider(
    val properties: JwtProperties
) {
    fun generateAccessToken(email: String): String {
        return JWT.create()
            .withClaim("email", email)
            .sign(Algorithm.HMAC256(properties.secret))
    }
}