package com.timxyz.repositories;

import com.timxyz.models.Category;
import com.timxyz.models.Item;
import com.timxyz.models.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dario on 5/9/2017.
 */
public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {
    Item findFirstBySkuNumber(String skuNumber);

    @Query("Select i from Item i where i.category.name like  %:name%")
    List<Item> getAllByCategoryName(@Param("name") String name );

    @Query("select i from Item i where i.location.name like  %:name%")
    List<Item> getAllByLocationName(@Param("name") String name );

    @Query("select i from Item i where i.location.type.name like  %:name%")
    List<Item> getAllByLocationTypeName(@Param("name") String name );

    //@Query("select i from Item where i.dateOfPurchase = :date")
    //List<Item> getAllByDate(@Param("date") Timestamp date );
}
