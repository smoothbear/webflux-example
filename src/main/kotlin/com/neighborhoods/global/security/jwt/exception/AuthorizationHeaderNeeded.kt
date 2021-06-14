package com.neighborhoods.global.security.jwt.exception

import com.neighborhoods.global.error.BusinessException
import org.springframework.http.HttpStatus

class AuthorizationHeaderNeeded : BusinessException(HttpStatus.UNAUTHORIZED, "Authorization header is needed.")