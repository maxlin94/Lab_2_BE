package org.example.lab_3_be.location;

import org.example.lab_3_be.dto.LocationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<LocationDto> getAllLocations() {
        return locationRepository.findAll()
                .stream()
                .map(LocationDto::fromLocation)
                .toList();
    }

}
