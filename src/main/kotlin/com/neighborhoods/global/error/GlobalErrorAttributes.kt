package com.neighborhoods.global.error

import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.server.ResponseStatusException

@Component
class GlobalErrorAttributes : DefaultErrorAttributes() {
    override fun getErrorAttributes(request: ServerRequest, options: ErrorAttributeOptions): Map<String, Any> {
        val map: MutableMap<String, Any> = super.getErrorAttributes(request, options)

        if (getError(request) is BusinessException) {
            val ex = getError(request) as BusinessException

            map["exception"] = ex.javaClass.simpleName
            map["message"] = ex.message ?: ""
            map["status"] = ex.status.value()
            map["error"] = ex.status.reasonPhrase

            return map
        } else if (getError(request) is ResponseStatusException) {
            val ex = getError(request) as ResponseStatusException

            map["exception"] = ex.javaClass.simpleName
            map["message"] = ex.message
            map["status"] = ex.status.value()
            map["error"] = ex.status.reasonPhrase

            return map
        }

        map["exception"] = "SystemException"
        map["message"] = "System Error , Check logs!"
        map["status"] = 500
        map["error"] = " System Error "

        return map
    }
}