package org.example.lab_3_be.location;

import org.example.lab_3_be.entities.Location;
import org.springframework.data.repository.ListCrudRepository;

public interface LocationRepository extends ListCrudRepository<Location, Long> {
}
