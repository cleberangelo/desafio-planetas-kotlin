package com.desafio.planetas.advice

import com.desafio.planetas.exception.NotFoundException
import com.desafio.planetas.model.ErrorMessage
import com.fasterxml.jackson.core.JsonParseException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.Exception
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class ErrorHandler {
    @ExceptionHandler(NotFoundException::class)
    fun notFoundExceptionHandler(
        request: HttpServletRequest,
        response: HttpServletResponse,
        ex: Exception
    ): ResponseEntity<ErrorMessage> {
        return ResponseEntity(ErrorMessage("Not found", ex.localizedMessage ?: "not found"), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun httpRequestMethodNotSupportedExceptionHandler(
        request: HttpServletRequest,
        response: HttpServletResponse,
        ex: Exception
    ): ResponseEntity<ErrorMessage> {
        return ResponseEntity(
            ErrorMessage("Method not supported", ex.localizedMessage ?: "method not supported"),
            HttpStatus.METHOD_NOT_ALLOWED
        )
    }
}