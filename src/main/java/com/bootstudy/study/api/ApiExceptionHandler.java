package com.bootstudy.study.api;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex
            , HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = createApiError(ex, ex.getMessage());
        ex.getBindingResult().getGlobalErrors().stream()
                .forEach(e -> apiError.addDetail(e.getObjectName(), getMessage(e, request)));
        ex.getBindingResult().getFieldErrors().stream()
                .forEach(e -> apiError.addDetail(e.getField(), getMessage(e, request)));

        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }

    private String getMessage(MessageSourceResolvable resolvable, WebRequest request) {
        return messageSource.getMessage(resolvable, request.getLocale());
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        ApiError apiError = createApiError(ex, "System error is occurred.");
        return super.handleExceptionInternal(ex, apiError, headers, status, request);
    }

    private final Map<Class<? extends Exception>, String> messageMappings =
            Collections.unmodifiableMap(new LinkedHashMap() {
                {
                    put(HttpMessageNotReadableException.class, "Request body is invalid");

                    put(MethodArgumentNotValidException.class, "Request value is invalid");
                }
            });

    private String resolveMessage(Exception ex, String defaultMessage) {
        return messageMappings.entrySet().stream().filter(entry -> entry.getKey().isAssignableFrom(ex.getClass()))
                .findFirst().map(Map.Entry::getValue).orElse(defaultMessage);
    }

    private ApiError createApiError(Exception ex, String defaultMessage) {
        ApiError apiError = new ApiError();
        apiError.setMessage(resolveMessage(ex, defaultMessage));
        apiError.setDocumentationUrl("http://study.com/api/errors");

        return apiError;
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleBookNotFoundException(BookNotFoundException ex, WebRequest request) {
        return handleExceptionInternal(ex, null, null, HttpStatus.NOT_FOUND, request);
    }
}
