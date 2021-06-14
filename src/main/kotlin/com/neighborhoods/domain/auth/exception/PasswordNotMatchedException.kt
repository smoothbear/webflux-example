package com.neighborhoods.domain.auth.exception

import com.neighborhoods.global.error.BusinessException
import org.springframework.http.HttpStatus

class PasswordNotMatchedException : BusinessException(status = HttpStatus.UNAUTHORIZED, message = "Password is not matched.")