package com.umitakbulut.accumulation_calculation.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class used to handle errors related to accumulation calculation.
 * <p>
 * This exception is thrown when input parameters for accumulation calculation are missing,
 * invalid, or fail business rules. It carries an error message and a unique error key from
 * the {@link AccumulationCalculationExceptionEnum}.
 * </p>
 */
@Getter
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class AccumulationCalculationException extends RuntimeException {
    /**
     * A descriptive error message.
     */
    private final String message;

    /**
     * A numeric key representing the error type.
     */
    private final Integer key;

    /**
     * Constructs a new AccumulationCalculationException with details from the given enum.
     *
     * @param accumulationCalculationExceptionEnum the enum containing error details
     */
    public AccumulationCalculationException(AccumulationCalculationExceptionEnum accumulationCalculationExceptionEnum) {
        super();
        this.key = Integer.valueOf(accumulationCalculationExceptionEnum.getKey());
        this.message = accumulationCalculationExceptionEnum.getMessage();
    }
}

