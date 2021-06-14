package com.neighborhoods.domain.auth.exception

import com.neighborhoods.global.error.BusinessException
import org.springframework.http.HttpStatus

class UserNotFoundException : BusinessException(status = HttpStatus.NOT_FOUND, "User is not exist.") {
}