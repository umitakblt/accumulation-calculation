package com.umitakbulut.accumulation_calculation.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/**
 * Global exception handler for the application.
 * <p>
 * This class handles exceptions thrown across the whole application,
 * providing centralized exception handling logic for specific exception types.
 * It extends {@link ResponseEntityExceptionHandler} to customize
 * the response for validation errors and resource not found exceptions.
 * </p>
 */
@ControllerAdvice
@Slf4j
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles {@link AccumulationCalculationException} exceptions.
     * Logs the error and returns a structured {@link ErrorDTO} response
     * with HTTP status 500 (Internal Server Error).
     *
     * @param accumulationCalculationException the custom exception thrown
     * @return a ResponseEntity containing the error details
     */
    @ExceptionHandler(AccumulationCalculationException.class)
    public ResponseEntity<Object> accumulationCalculationExceptionHandler(AccumulationCalculationException accumulationCalculationException) {
        log.error("GeneralExceptionHandler.accumulationCalculationExceptionHandler(): message: {}", accumulationCalculationException.getMessage(), accumulationCalculationException);
        return new ResponseEntity<>(
                new ErrorDTO(
                        accumulationCalculationException.getMessage(),
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        accumulationCalculationException.getKey()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Overrides the default handler for validation errors on method arguments annotated with @Valid.
     * Extracts the first validation error message and returns it in the response with HTTP status 400 (Bad Request).
     *
     * @param ex      the exception containing validation error details
     * @param headers HTTP headers
     * @param status  HTTP status code
     * @param request the current request
     * @return a ResponseEntity containing the validation error details
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        log.error("GeneralExceptionHandler.handleMethodArgumentNotValid(): message: {}", ex.getMessage(), ex);
        String message = "Validation failed";
        if (ex.getBindingResult().hasErrors() &&
                !ex.getBindingResult().getAllErrors().isEmpty() &&
                ex.getBindingResult().getFieldErrors().get(0) != null &&
                ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage() != null) {
            message = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        }
        return new ResponseEntity<>(
                new ErrorDTO(
                        message,
                        HttpStatus.BAD_REQUEST,
                        HttpStatus.BAD_REQUEST.value(),
                        "VALIDATION_ERROR"),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Overrides the default handler for requests to resources that do not exist.
     * Returns a structured {@link ErrorDTO} with HTTP status 404 (Not Found).
     *
     * @param ex      the exception indicating no resource found
     * @param headers HTTP headers
     * @param status  HTTP status code
     * @param request the current request
     * @return a ResponseEntity containing the error details
     */
    @Override
    protected ResponseEntity<Object> handleNoResourceFoundException(
            NoResourceFoundException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        log.error("GeneralExceptionHandler.handleNoResourceFoundException(): message: {}", ex.getMessage(), ex);
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(ex.getMessage());
        errorDTO.setCode(status.value());
        errorDTO.setKey("NO_RESOURCE_FOUND");
        errorDTO.setStatus(HttpStatus.NOT_FOUND);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorDTO);
    }
}