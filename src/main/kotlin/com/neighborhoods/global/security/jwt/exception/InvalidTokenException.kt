package com.neighborhoods.global.security.jwt.exception

import com.neighborhoods.global.error.BusinessException
import org.springframework.http.HttpStatus

class InvalidTokenException : BusinessException(
    status = HttpStatus.UNAUTHORIZED,
    message = "Invalid token."
)