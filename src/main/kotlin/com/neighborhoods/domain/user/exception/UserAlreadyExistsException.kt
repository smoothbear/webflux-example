package com.neighborhoods.domain.user.exception

import com.neighborhoods.global.error.BusinessException
import org.springframework.http.HttpStatus

class UserAlreadyExistsException : BusinessException(status = HttpStatus.CONFLICT, message = "User already exists!!")