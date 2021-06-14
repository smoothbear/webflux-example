package com.neighborhoods.domain.user.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class User(
    @Id
    val email: String,
    val password: String,
    val name: String
) {
    override fun toString(): String {
        return "User [email=$email, password=$password, name=$name]"
    }
}