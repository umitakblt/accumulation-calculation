package com.umitakbulut.accumulation_calculation.controller;

import com.umitakbulut.accumulation_calculation.dto.request.AccumulationCalculationRequestDTO;
import com.umitakbulut.accumulation_calculation.dto.response.AccumulationCalculationResponseDTO;
import com.umitakbulut.accumulation_calculation.service.AccumulationCalculationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for handling accumulation calculation requests.
 * <p>
 * This controller exposes an endpoint to perform retirement savings accumulation
 * calculations based on age, pension age, monthly savings, yearly increase rate,
 * and an optional initial saving amount.
 * </p>
 */
@RestController
@RequestMapping(value = "api/v1/accumulation")
@RequiredArgsConstructor
@Slf4j
public class AccumulationCalculationController {
    private final AccumulationCalculationService accumulationCalculationService;

    /**
     * Endpoint to calculate accumulated retirement savings.
     *
     * <p>
     * Accepts a request body containing parameters such as age, pension age, monthly saving amount,
     * yearly increase rate, and an optional initial saving amount. Returns the calculated accumulated
     * savings at the time of retirement.
     * </p>
     *
     * @param accumulationCalculationRequestDTO the request containing calculation parameters
     * @return a response entity with the accumulated savings result
     */
    @GetMapping(value = "/calculation")
    public ResponseEntity<AccumulationCalculationResponseDTO> calculation (@Valid @RequestBody AccumulationCalculationRequestDTO accumulationCalculationRequestDTO) {
        log.info("AccumulationCalculationController.calculation(): accumulationCalculationRequestDTO: {}", accumulationCalculationRequestDTO);
        return ResponseEntity.ok(this.accumulationCalculationService.calculation(accumulationCalculationRequestDTO));
    }

}
