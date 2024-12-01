package org.example.lab_3_be.dto;

import jakarta.validation.constraints.*;
import org.example.lab_3_be.entities.LocationEntity;

public record LocationDto(
        @NotBlank(message = "Name must not be blank")
        String name,

        @NotNull(message = "Category ID is required")
        Integer categoryId,

        @Size(max = 500, message = "Description cannot exceed 500 characters")
        String description,

        @Min(value = -180, message = "Longitude must be greater than or equal to -180")
        @Max(value = 180, message = "Longitude must be less than or equal to 180")
        double lng,

        @Min(value = -90, message = "Latitude must be greater than or equal to -90")
        @Max(value = 90, message = "Latitude must be less than or equal to 90")
        double lat,

        Boolean isPublic) {
    public static LocationDto fromLocation(LocationEntity locationEntity) {
        double lng = locationEntity.getCoordinates().getCoordinate().getOrdinate(0);
        double lat = locationEntity.getCoordinates().getCoordinate().getOrdinate(1);
        return new LocationDto(locationEntity.getName(), locationEntity.getCategory().getId(), locationEntity.getDescription(), lng, lat, locationEntity.getIsPublic());
    }
}
