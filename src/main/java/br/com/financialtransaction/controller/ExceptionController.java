package br.com.financialtransaction.controller;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {

  /**
   * Handles MethodArgumentNotValidException by returning a response entity with
   * validation errors.
   *
   * @param ex the MethodArgumentNotValidException to handle
   * @return a response entity with a map of field errors
   */
  @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    // Collect all field errors into a map
    Map<String, String> errors = ex.getAllErrors().stream()
        .map(error -> (FieldError) error)
        .collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage));

    // Return a response entity with the map of errors
    return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
        .body(errors);
  }
}