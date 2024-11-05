package com.Hms.controller;

import com.Hms.entity.Property;
import com.Hms.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user/property")
public class PropertyController {
    private PropertyService propertyService;

    @PostMapping
    public ResponseEntity<?> addProperty(
            @RequestBody Property property
            ){
        return propertyService.addProperty(property);
    }
}
