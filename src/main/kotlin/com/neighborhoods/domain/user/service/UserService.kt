package com.neighborhoods.domain.user.service

import com.neighborhoods.domain.user.entity.User
import com.neighborhoods.domain.user.handler.dto.UserDto
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface UserService {
    fun register(request: UserDto): Mono<Void>
    fun userList(): Flux<User>
}