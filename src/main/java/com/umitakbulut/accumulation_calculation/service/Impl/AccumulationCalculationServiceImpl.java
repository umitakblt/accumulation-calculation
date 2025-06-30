package com.umitakbulut.accumulation_calculation.service.Impl;

import com.umitakbulut.accumulation_calculation.dto.request.AccumulationCalculationRequestDTO;
import com.umitakbulut.accumulation_calculation.dto.response.AccumulationCalculationResponseDTO;
import com.umitakbulut.accumulation_calculation.exception.AccumulationCalculationException;
import com.umitakbulut.accumulation_calculation.exception.AccumulationCalculationExceptionEnum;
import com.umitakbulut.accumulation_calculation.service.AccumulationCalculationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link AccumulationCalculationService} that performs
 * the calculation of accumulated savings for retirement based on user inputs.
 * <p>
 * The calculation accounts for initial savings, monthly contributions,
 * yearly increase rate, state support, and inflation adjustments.
 * </p>
 */
@Service
@Slf4j
public class AccumulationCalculationServiceImpl implements AccumulationCalculationService {

    /**
     * Calculates the total accumulated savings from the current age until pension age.
     * <p>
     * The calculation considers:
     * <ul>
     *     <li>Initial saving amount (if any)</li>
     *     <li>Monthly saving amounts with yearly increases</li>
     *     <li>Annual state support contribution (30%)</li>
     *     <li>Annual inflation rate adjustment (5%)</li>
     * </ul>
     *
     * @param accumulationCalculationRequestDTO the request DTO containing user input parameters
     * @return a response DTO containing the total accumulated savings as result
     */
    @Override
    public AccumulationCalculationResponseDTO calculation(AccumulationCalculationRequestDTO accumulationCalculationRequestDTO) {
        log.info("AccumulationController.calculation(): accumulationCalculationRequestDTO: {}", accumulationCalculationRequestDTO);

        this.accumulationRequestDTOCheck(accumulationCalculationRequestDTO);

        int age = accumulationCalculationRequestDTO.getAge();
        int pensionAge = accumulationCalculationRequestDTO.getPensionAge();
        double monthlySaving = accumulationCalculationRequestDTO.getMonthlySavingAmount();
        double initialSaving = accumulationCalculationRequestDTO.getInitialSavingAmount() != null ? accumulationCalculationRequestDTO.getInitialSavingAmount() : 0.0;

        double yearlyIncreaseRate = 0.10;
        double stateSupportRate = 0.30;
        double inflationRate = 0.05;

        double Result = 0.0;

        for (int year = age; year < pensionAge; year++) {
            double yearlyUserPayment;

            if (year == age && initialSaving > 0) {
                yearlyUserPayment = initialSaving;
            } else {
                yearlyUserPayment = monthlySaving * 12;
            }

            double yearlyStateSupport = (yearlyUserPayment + Result) * stateSupportRate;
            Result += yearlyUserPayment + yearlyStateSupport;
            Result *= (1 - inflationRate);

            log.debug("AccumulationServiceImpl.calculation(): year: {}, yearlyUserPayment: {}, yearlyStateSupport: {}, Result(after inflation): {}",
                    year, yearlyUserPayment, yearlyStateSupport, Result);

            monthlySaving *= (1 + yearlyIncreaseRate);
        }

        AccumulationCalculationResponseDTO responseDTO = new AccumulationCalculationResponseDTO();
        responseDTO.setResult(Result);
        return responseDTO;
    }

    /**
     * Validates that all required fields in the request DTO are not null.
     * <p>
     * Throws {@link AccumulationCalculationException} if any required field is missing.
     * </p>
     *
     * @param accumulationCalculationRequestDTO the request DTO to validate
     * @throws AccumulationCalculationException if validation fails
     */
    private void accumulationRequestDTOCheck(AccumulationCalculationRequestDTO accumulationCalculationRequestDTO) {
        log.info("AccumulationCalculationController.accumulationRequestDTOCheck(): requestDTO: {}", accumulationCalculationRequestDTO);

        if (accumulationCalculationRequestDTO.getAge() == null) {
            log.error("AccumulationCalculationServiceImpl.accumulationRequestDTOCheck(): age is null, accumulationCalculationRequestDTO: {}", accumulationCalculationRequestDTO);
            throw new AccumulationCalculationException(AccumulationCalculationExceptionEnum.ACCUMULATION_CALCULATION_AGE_NULL_ERROR);
        }

        if (accumulationCalculationRequestDTO.getPensionAge() == null) {
            log.error("AccumulationCalculationServiceImpl.accumulationRequestDTOCheck(): pensionAge is null, accumulationCalculationRequestDTO: {}", accumulationCalculationRequestDTO);
            throw new AccumulationCalculationException(AccumulationCalculationExceptionEnum.ACCUMULATION_CALCULATION_PENSION_AGE_NULL_ERROR);
        }

        if (accumulationCalculationRequestDTO.getMonthlySavingAmount() == null) {
            log.error("AccumulationCalculationServiceImpl.accumulationRequestDTOCheck(): monthlySavingAmount is null, accumulationCalculationRequestDTO: {}", accumulationCalculationRequestDTO);
            throw new AccumulationCalculationException(AccumulationCalculationExceptionEnum.ACCUMULATION_CALCULATION_MONTHLY_SAVING_AMOUNT_NULL_ERROR);
        }

        if (accumulationCalculationRequestDTO.getYearlyIncreaseRate() == null) {
            log.error("AccumulationCalculationServiceImpl.accumulationRequestDTOCheck(): yearlySavingAmount is null, accumulationCalculationRequestDTO: {}", accumulationCalculationRequestDTO);
            throw new AccumulationCalculationException(AccumulationCalculationExceptionEnum.ACCUMULATION_CALCULATION_YEARLY_INCREASE_RATE_NULL_ERROR);
        }
    }
}