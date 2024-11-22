package org.example.lab_3_be.dto;

import org.example.lab_3_be.entities.Location;

public record LocationDto(String name, String category, String description) {
    public static LocationDto fromLocation(Location location) {
        return new LocationDto(location.getName(), location.getCategory().getName(), location.getDescription());
    }
}
