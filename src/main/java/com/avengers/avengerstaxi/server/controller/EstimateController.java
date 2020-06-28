package com.avengers.avengerstaxi.server.controller;

import com.avengers.avengerstaxi.server.dto.EstimateResultDto;
import com.avengers.avengerstaxi.server.service.TaxiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/estimate")
public class EstimateController {
    private TaxiService taxiService;

    public EstimateController(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    @GetMapping()
    public EstimateResultDto getEstimate(@RequestParam("startLatitude") double startLatitude,
                                         @RequestParam("startLongitude") double startLongitude,
                                         @RequestParam("endLatitude") double endLatitude,
                                         @RequestParam("endLongitude") double endLongitude) {
        EstimateResultDto dto = new EstimateResultDto();
        dto.estimateCost = this.taxiService.calculateEstimateCost(startLatitude,startLongitude,endLatitude,endLongitude);
        dto.estimateTime = this.taxiService.calculateEstimateTime(startLatitude,startLongitude,endLatitude,endLongitude);
        dto.estimateDistance = this.taxiService.calculateDistance(startLatitude,startLongitude,endLatitude,endLongitude);
        return dto;
    }
}