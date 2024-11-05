package com.Hms.service;

import com.Hms.entity.Country;
import com.Hms.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CountryService {
    private CountryRepository countryRepository;

    public ResponseEntity<?> addCountry(Country country) {
        countryRepository.save(country);
        return new ResponseEntity<>("Country Added", HttpStatus.OK);
    }
}
