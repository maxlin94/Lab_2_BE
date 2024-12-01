package org.example.lab_3_be.repository;

import org.example.lab_3_be.entities.CategoryEntity;
import org.example.lab_3_be.entities.LocationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends ListCrudRepository<LocationEntity, Integer> {
    @Query("SELECT l FROM LocationEntity l WHERE l.isPublic = true OR l.userId = :userId")
    List<LocationEntity> findAllByIsPublicOrUserId(@Param("userId") String userId);

    @Query("SELECT l FROM LocationEntity l WHERE l.isPublic = true AND l.category = :category")
    List<LocationEntity> findByCategory(CategoryEntity category);

    @Query("SELECT l FROM LocationEntity l WHERE l.id = :id AND (l.isPublic = true OR l.userId = :userId)")
    Optional<LocationEntity> findAccessibleLocationById(@Param("id") Integer id, @Param("userId") String userId);

    @Query("SELECT l FROM LocationEntity l WHERE ST_Distance_Sphere(l.coordinates, ST_GeomFromText(:pointWkt, 4326)) <= :radius AND (l.isPublic = true OR l.userId = :userId)")
    List<LocationEntity> findNearbyLocations(String pointWkt, double radius, String userId);

    @Query("SELECT l FROM LocationEntity l WHERE l.id = :id AND l.userId = :userId")
    Optional<LocationEntity> findByIdAndSameUserId(int id, String userId);
}
