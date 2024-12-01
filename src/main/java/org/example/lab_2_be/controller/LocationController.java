package org.example.lab_2_be.controller;

import jakarta.validation.Valid;
import org.example.lab_2_be.dto.LocationDto;
import org.example.lab_2_be.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public List<LocationDto> getPublicOrPersonalLocations() {
        return locationService.getPublicOrPersonalLocations();
    }

    @PostMapping
    public ResponseEntity<?> addLocation(@Valid @RequestBody LocationDto locationDto) {
        locationService.addLocation(locationDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationDto> getLocationById(@PathVariable Integer id) {
        return locationService.getLocationById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLocationById(@PathVariable int id, @Valid @RequestBody LocationDto locationDto) {
        locationService.updateLocationById(id, locationDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/category/{category}")
    public List<LocationDto> getLocationsByCategory(@PathVariable String category) {
        List<LocationDto> locations = locationService.getLocationByCategory(category);
        if (locations.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return locations;
    }

    @GetMapping("/radius")
    public List<LocationDto> getPlacesWithinRadius(@Valid @RequestParam double lat, @RequestParam double lng, @RequestParam double radius) {
        return locationService.getPlacesWithinRadius(lat, lng, radius);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLocation(@PathVariable int id) {
        locationService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }
}
