package com.timxyz.repositories;

import com.timxyz.models.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
    Category findFirstByName(String name);
}