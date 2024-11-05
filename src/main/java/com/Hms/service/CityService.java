package com.Hms.service;

import com.Hms.entity.City;
import com.Hms.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CityService {
    private CityRepository cityRepository;

    public ResponseEntity<String> addCity(City city) {
        cityRepository.save(city);
        return new ResponseEntity<>("City added", HttpStatus.OK);
    }
}
