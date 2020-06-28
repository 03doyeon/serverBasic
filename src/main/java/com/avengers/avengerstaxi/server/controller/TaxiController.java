package com.avengers.avengerstaxi.server.controller;

import com.avengers.avengerstaxi.server.dto.TaxiDto;
import com.avengers.avengerstaxi.server.dto.TaxiListDto;
import com.avengers.avengerstaxi.server.model.TaxiEntity;
import com.avengers.avengerstaxi.server.service.SchoolService;
import com.avengers.avengerstaxi.server.service.TaxiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController()
@RequestMapping(path = "/taxis")
public class TaxiController {
    private TaxiService taxiService;

    @Autowired
    public TaxiController(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    @GetMapping // schoolController이랑 비슷한 내용의 코드(거의 동일)
    public TaxiListDto getTaxilist() {
        ArrayList<TaxiEntity> taxis = this.taxiService.findTaxiList();
        ArrayList<TaxiDto> list = new ArrayList();
        for (TaxiEntity taxi : taxis) {
            TaxiDto dto = new TaxiDto();
            dto.driver = taxi.driver;
            dto.taxiNumber = taxi.taxiNumber;
            dto.latitude = taxi.latitude;
            dto.longitude = taxi.longitude;
            list.add(dto);
        }
        TaxiListDto result = new TaxiListDto();
        result.taxis = list;
        return result;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void addTaxi(@RequestBody() TaxiDto dto) {
        TaxiEntity taxi = new TaxiEntity();
        taxi.driver = dto.driver;
        taxi.taxiNumber = dto.taxiNumber;
        taxi.latitude = dto.latitude;
        taxi.longitude = dto.longitude;
        this.taxiService.addTaxiPosition(taxi);
    }
}
