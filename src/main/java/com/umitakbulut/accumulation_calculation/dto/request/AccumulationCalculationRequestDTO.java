package com.umitakbulut.accumulation_calculation.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * Data transfer object used for accumulation calculation requests.
 * <p>
 * This DTO contains input parameters such as the user's current age,
 * planned pension age, monthly saving amount, expected yearly increase rate,
 * and an optional initial saving amount.
 * </p>
 */
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccumulationCalculationRequestDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 4930353387863279633L;

    /**
     * The current age of the user. Must not be null.
     */
    @NotNull(message = "age can not be null")
    private Integer age;

    /**
     * The age at which the user plans to retire. Must not be null.
     */
    @NotNull(message = "pension age can not be null")
    private Integer pensionAge;

    /**
     * The amount of money the user saves each month. Must not be null.
     */
    @NotNull(message = "monthly saving amount can not be null")
    private Integer monthlySavingAmount;

    /**
     * The annual rate at which the user's savings increase (e.g., due to salary raise or inflation). Must not be null.
     */
    @NotNull(message = "yearly increase rate can not be null")
    private Integer yearlyIncreaseRate;

    /**
     * The initial amount saved by the user at the start of the plan. Optional.
     */
    private Integer initialSavingAmount;

}
