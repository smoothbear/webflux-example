package com.neighborhoods.domain.user.service

import com.neighborhoods.domain.user.entity.User
import com.neighborhoods.domain.user.entity.UserRepository
import com.neighborhoods.domain.user.exception.UserAlreadyExistsException
import com.neighborhoods.domain.user.handler.dto.UserDto
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {
    override fun register(request: UserDto): Mono<Void> {
        return userRepository.existsById(request.email)
            .flatMap { exist ->
                if (exist) Mono.error(UserAlreadyExistsException())
                else {
                    userRepository.save(
                        User(
                            email = request.email,
                            password = request.password,
                            name = request.name
                        )
                    )
                        .then()
                }
            }
    }

    override fun userList(): Flux<User> {
        return userRepository.findAll()
    }
}