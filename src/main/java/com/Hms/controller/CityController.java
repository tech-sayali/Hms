package com.Hms.controller;

import com.Hms.entity.City;
import com.Hms.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/user/city")
public class CityController {

    private CityService cityService;

    @PostMapping
    public ResponseEntity<?> addCity(
            @RequestBody City city
    ){
        return cityService.addCity(city);
    }
}
