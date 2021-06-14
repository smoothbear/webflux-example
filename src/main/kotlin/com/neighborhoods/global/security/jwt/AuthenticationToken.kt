package com.neighborhoods.global.security.jwt

import org.springframework.security.authentication.AbstractAuthenticationToken

class AuthenticationToken (
    val tokenType: String,
    val email: String,
    val decodedJwtToken: String
) : AbstractAuthenticationToken(null) {

    init {
        super.setAuthenticated(true)
    }

    override fun getCredentials(): Any {
        return this.decodedJwtToken
    }

    override fun getPrincipal(): Any {
        return this.email
    }
}