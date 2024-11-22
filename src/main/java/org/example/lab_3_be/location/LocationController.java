package org.example.lab_3_be.location;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @GetMapping
    public String getAllLocations() {
        return "Hello World";
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
