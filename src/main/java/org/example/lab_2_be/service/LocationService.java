package org.example.lab_2_be.service;

import org.example.lab_2_be.repository.CategoryRepository;
import org.example.lab_2_be.dto.LocationDto;
import org.example.lab_2_be.entities.CategoryEntity;
import org.example.lab_2_be.entities.LocationEntity;
import org.example.lab_2_be.repository.LocationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Coordinate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    private final LocationRepository locationRepository;
    private final CategoryRepository categoryRepository;
    private final GeometryFactory geometryFactory = new GeometryFactory();


    public LocationService(LocationRepository locationRepository, CategoryRepository categoryRepository) {
        this.locationRepository = locationRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<LocationDto> getPublicOrPersonalLocations() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return locationRepository.findAllByIsPublicOrUserId(userId)
                .stream()
                .map(LocationDto::fromLocation)
                .toList();
    }

    public Optional<LocationDto> getLocationById(Integer id) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return locationRepository.findAccessibleLocationById(id, userId).map(LocationDto::fromLocation);
    }

    public void addLocation(LocationDto locationDto) {
        int categoryId = locationDto.categoryId();
        double lat = locationDto.lat();
        double lng = locationDto.lng();
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid category ID"));
        Point coordinates = geometryFactory.createPoint(new Coordinate(lng, lat));
        coordinates.setSRID(4326);

        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setName(locationDto.name());
        locationEntity.setCategory(category);
        locationEntity.setCoordinates(coordinates);
        locationEntity.setDescription(locationDto.description());
        locationEntity.setUserId(userId);
        locationEntity.setIsPublic(locationDto.isPublic());

        locationRepository.save(locationEntity);
    }

    public List<LocationDto> getLocationByCategory(String category) {
        CategoryEntity categoryEntity = categoryRepository.findByName(category);
        return locationRepository.findByCategory(categoryEntity)
                .stream()
                .map(LocationDto::fromLocation)
                .toList();
    }

    public List<LocationDto> getPlacesWithinRadius(double lat, double lng, double radius) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        String pointWkt = geometryFactory.createPoint(new Coordinate(lat, lng)).toString();
        return locationRepository.findNearbyLocations(pointWkt, radius, userId)
                .stream()
                .map(LocationDto::fromLocation)
                .toList();
    }

    public void updateLocationById(int id, LocationDto locationDto) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<LocationEntity> optionalLocation = locationRepository.findByIdAndSameUserId(id, userId);
        Optional<CategoryEntity> optionalCategory = categoryRepository.findById(locationDto.categoryId());
        if (optionalCategory.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid category ID");
        }
        if (optionalLocation.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid location ID");
        }
        Point coordinates = geometryFactory.createPoint(new Coordinate(locationDto.lng(), locationDto.lat()));
        coordinates.setSRID(4326);
        LocationEntity locationEntity = optionalLocation.get();
        locationEntity.setName(locationDto.name());
        locationEntity.setDescription(locationDto.description());
        locationEntity.setIsPublic(locationDto.isPublic());
        locationEntity.setCoordinates(coordinates);
        locationRepository.save(locationEntity);
    }

    public void deleteLocation(int id) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<LocationEntity> optionalLocation = locationRepository.findByIdAndSameUserId(id, userId);
        if (optionalLocation.isPresent()) {
            LocationEntity locationEntity = optionalLocation.get();
            locationRepository.delete(locationEntity);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }
}
