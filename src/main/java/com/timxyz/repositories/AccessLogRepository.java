package com.timxyz.repositories;

import com.timxyz.models.AccessLog;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by muhamed on 5/5/17.
 */
public interface AccessLogRepository extends PagingAndSortingRepository<AccessLog, Long> {
}
