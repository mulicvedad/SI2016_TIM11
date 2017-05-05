package com.timxyz.repositories;


import com.timxyz.models.Location;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
/**
 * Created by ramic on 04.05.2017..
 */
public interface LocationRepository extends PagingAndSortingRepository <Location, Long> {
    Location findFirstByName(String name);
    Location findFirstByNameOrType(String name, String type);
    Location findByNameContaining(String partOfName);
}
