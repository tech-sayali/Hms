package com.Hms.controller;

import com.Hms.entity.Country;
import com.Hms.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user/country")
public class CountryController {
    private CountryService countryService;

    //http://localhost:8080/api/v1/country
    @PostMapping
    public ResponseEntity<?> addCountry(
            @RequestBody Country country
    ){
        return countryService.addCountry(country);
    }
}
