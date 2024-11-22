package org.example.lab_3_be.location;

import org.example.lab_3_be.dto.LocationDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {
    LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public List<LocationDto> getAllLocations() {
        return locationService.getAllLocations();
    }

    @PostMapping
    public String addLocation(@RequestBody Location location) {
        return "Hello World";
    }

    @GetMapping("/{id}")
    public String getLocationById(@PathVariable int id) {
        return "Hello world" + id;
    }

    @PostMapping("/{id}")
    public String updateLocationById(@PathVariable int id) {
        return "Hello world" + id;
    }

    @GetMapping("/category/{category}")
    public String getLocationsByCategory(@PathVariable String category) {
        return "Hello world" + category;
    }

    @GetMapping("/area")
    public String getLocationsInArea() {
        return "Hello world";
    }

    @GetMapping("/my-locations")
    public String getMyLocations() {
        return "Hello world";
    }

}
