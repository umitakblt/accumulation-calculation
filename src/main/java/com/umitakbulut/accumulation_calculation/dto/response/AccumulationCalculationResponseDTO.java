package com.umitakbulut.accumulation_calculation.dto.response;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * Data transfer object used to return the result of the accumulation calculation.
 * <p>
 * This DTO contains the final calculated value representing the total savings
 * accumulated over the user's working years based on the provided request parameters.
 * </p>
 */
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccumulationCalculationResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -274776457910957318L;

    /**
     * The final result of the accumulation calculation (e.g., total savings).
     */
    private Double result;
}
