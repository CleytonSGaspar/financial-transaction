package br.com.financialtransaction.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.util.Map;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;

class ExceptionControllerTest {

    /**
     * Method under test: {@link ExceptionController#handleValidationExceptions(MethodArgumentNotValidException)}
     */
    @Test
    void testHandleValidationExceptions2() {
        ExceptionController exceptionController = new ExceptionController();
        ResponseEntity<Map<String, String>> actualHandleValidationExceptionsResult = exceptionController
                .handleValidationExceptions(
                        new MethodArgumentNotValidException(null, new BindException("Target", "Object Name")));
        assertTrue(actualHandleValidationExceptionsResult.hasBody());
        assertEquals(HttpStatus.PRECONDITION_FAILED, actualHandleValidationExceptionsResult.getStatusCode());
        assertTrue(actualHandleValidationExceptionsResult.getHeaders().isEmpty());
    }
}

