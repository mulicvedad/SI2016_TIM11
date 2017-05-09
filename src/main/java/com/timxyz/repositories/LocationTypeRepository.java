package com.timxyz.repositories;

import com.timxyz.models.LocationType;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LocationTypeRepository extends PagingAndSortingRepository<LocationType, Long> {
    LocationType findFirstByName(String name);
    LocationType findFirstByDescription(String description);
}
