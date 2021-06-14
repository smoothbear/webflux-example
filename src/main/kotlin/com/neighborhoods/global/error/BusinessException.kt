package com.neighborhoods.global.error

import org.springframework.http.HttpStatus

open class BusinessException(val status: HttpStatus, message: String) : RuntimeException(message)