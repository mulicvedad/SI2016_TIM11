package com.timxyz.repositories;

import com.timxyz.models.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Dario on 5/6/2017.
 */
@Repository
public interface StatusRepositoy extends PagingAndSortingRepository<Status, Long> {
    Status findFirstByName(String name);
}

