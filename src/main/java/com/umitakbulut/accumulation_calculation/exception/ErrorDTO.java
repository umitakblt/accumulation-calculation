package com.umitakbulut.accumulation_calculation.exception;

import lombok.*;
import org.springframework.http.HttpStatus;
import java.io.Serializable;
import java.time.Instant;

/**
 * Data Transfer Object for returning error details in API responses.
 * <p>
 * Contains information such as the error message, HTTP status, error code,
 * a unique key identifying the error type, and a timestamp indicating
 * when the error occurred.
 * </p>
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ErrorDTO implements Serializable {

    /**
     * Human-readable error message.
     */
    private String message;

    /**
     * HTTP status associated with the error.
     */
    private HttpStatus status;

    /**
     * Numeric HTTP status code.
     */
    private int code;

    /**
     * Unique key representing the error type.
     */
    private String key;

    /**
     * Timestamp when the error was created.
     */
    private final Instant timestamp = Instant.now();

    /**
     * Constructs a new ErrorDTO with the specified details.
     *
     * @param message    the error message
     * @param httpStatus the HTTP status
     * @param value      the numeric status code
     * @param key        the unique error key as Integer (will be converted to String)
     */
    public ErrorDTO(String message, HttpStatus httpStatus, int value, Integer key) {
        this.message = message;
        this.status = httpStatus;
        this.code = value;
        this.key = key != null ? key.toString() : null;
    }
}