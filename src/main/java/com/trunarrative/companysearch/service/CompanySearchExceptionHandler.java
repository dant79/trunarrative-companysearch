package com.trunarrative.companysearch.service;

import com.trunarrative.companysearch.exception.CompanyNotFoundException;
import com.trunarrative.companysearch.exception.InvalidRequestException;
import com.trunarrative.companysearch.exception.TruApiException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CompanySearchExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = InvalidRequestException.class)
    protected ResponseEntity<Object> handleInvalidRequest(
            InvalidRequestException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
    }
    @ExceptionHandler(value = CompanyNotFoundException.class)
    protected ResponseEntity<Object> handleCompanyNotFound(
            CompanyNotFoundException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    @ExceptionHandler(value = TruApiException.class)
    protected ResponseEntity<Object> handleTruApiError(
            TruApiException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE, request);
    }

}
