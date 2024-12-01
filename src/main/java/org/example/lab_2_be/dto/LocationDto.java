package org.example.lab_2_be.dto;

import jakarta.validation.constraints.*;
import org.example.lab_2_be.entities.LocationEntity;

public record LocationDto(
        @NotBlank(message = "Name must not be blank")
        String name,

        @NotNull(message = "Category ID is required")
        Integer categoryId,

        @NotBlank
        @Size(max = 500, message = "Description cannot exceed 500 characters")
        String description,

        @NotNull
        @Min(value = -180, message = "Longitude must be greater than or equal to -180")
        @Max(value = 180, message = "Longitude must be less than or equal to 180")
        Double lng,

        @NotNull
        @Min(value = -90, message = "Latitude must be greater than or equal to -90")
        @Max(value = 90, message = "Latitude must be less than or equal to 90")
        Double lat,

        Boolean isPublic) {
    public static LocationDto fromLocation(LocationEntity locationEntity) {
        double lng = locationEntity.getCoordinates().getCoordinate().getX();
        double lat = locationEntity.getCoordinates().getCoordinate().getY();
        return new LocationDto(locationEntity.getName(), locationEntity.getCategory().getId(), locationEntity.getDescription(), lng, lat, locationEntity.getIsPublic());
    }
}
