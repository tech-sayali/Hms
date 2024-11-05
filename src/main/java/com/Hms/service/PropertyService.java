package com.Hms.service;

import com.Hms.entity.Property;
import com.Hms.repository.PropertyRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PropertyService {
    private PropertyRepository propertyRepository;

    public ResponseEntity<?> addProperty(Property property) {
        propertyRepository.save(property);
        return new ResponseEntity<>("Property Added", HttpStatus.OK);
    }
}
