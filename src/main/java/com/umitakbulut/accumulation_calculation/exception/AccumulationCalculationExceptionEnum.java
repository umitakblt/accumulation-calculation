package com.umitakbulut.accumulation_calculation.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum representing error codes and messages for accumulation calculation exceptions.
 * <p>
 * Each constant defines a specific validation error with a descriptive message and a unique key.
 * </p>
 */
@AllArgsConstructor
@Getter
public enum AccumulationCalculationExceptionEnum {
    /**
     * Error indicating that the age value is null.
     */
    ACCUMULATION_CALCULATION_AGE_NULL_ERROR(
            "Age cannot be null",
            "ACCUMULATION_CALCULATION_AGE_NULL_ERROR"
    ),

    /**
     * Error indicating that the pension age value is null.
     */
    ACCUMULATION_CALCULATION_PENSION_AGE_NULL_ERROR(
            "Pension age cannot be null",
            "ACCUMULATION_CALCULATION_PENSION_AGE_NULL_ERROR"
    ),

    /**
     * Error indicating that the monthly saving amount is null.
     */
    ACCUMULATION_CALCULATION_MONTHLY_SAVING_AMOUNT_NULL_ERROR(
            "Monthly saving amount cannot be null",
            "ACCUMULATION_CALCULATION_MONTHLY_SAVING_AMOUNT_NULL_ERROR"
    ),

    /**
     * Error indicating that the yearly increase rate is null.
     */
    ACCUMULATION_CALCULATION_YEARLY_INCREASE_RATE_NULL_ERROR(
            "Yearly increase rate cannot be null",
            "ACCUMULATION_CALCULATION_YEARLY_INCREASE_RATE_NULL_ERROR"
    );

    /**
     * The error message to be displayed or logged.
     */
    private final String message;

    /**
     * A unique key identifying the error type.
     */
    private final String key;
}