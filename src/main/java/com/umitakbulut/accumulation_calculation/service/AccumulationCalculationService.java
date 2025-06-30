package com.umitakbulut.accumulation_calculation.service;

import com.umitakbulut.accumulation_calculation.dto.request.AccumulationCalculationRequestDTO;
import com.umitakbulut.accumulation_calculation.dto.response.AccumulationCalculationResponseDTO;

/**
 * Service interface defining accumulation calculation operations.
 * <p>
 * Provides a method to calculate the total accumulated savings based on user input parameters.
 * </p>
 */
public interface AccumulationCalculationService {

    /**
     * Performs the accumulation calculation using the given request data.
     *
     * @param accumulationCalculationRequestDTO the request DTO containing user input parameters
     * @return a response DTO containing the calculated total savings result
     */
    AccumulationCalculationResponseDTO calculation(AccumulationCalculationRequestDTO accumulationCalculationRequestDTO);
}