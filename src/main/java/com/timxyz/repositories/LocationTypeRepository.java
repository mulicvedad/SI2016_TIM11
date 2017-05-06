package com.timxyz.repositories;

import com.timxyz.models.LocationType;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by amina on 04.05.2017..
 */
public interface LocationTypeRepository extends PagingAndSortingRepository<LocationType, Long> {
    LocationType findFirstByName(String name);
    LocationType findFirstByDescription(String description);
}
