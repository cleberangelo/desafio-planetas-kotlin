package com.desafio.planetas.advice

import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes
import org.springframework.stereotype.Component
import org.springframework.web.context.request.WebRequest

@Component
class ErrorAttributes : DefaultErrorAttributes() {
    override fun getErrorAttributes(webRequest: WebRequest?, options: ErrorAttributeOptions?): MutableMap<String, Any> {
        val errorAttributes = super.getErrorAttributes(webRequest, options)
        errorAttributes.remove("timestamp")
        errorAttributes.remove("path")

        return errorAttributes
    }
}