package com.timxyz.services;

import com.timxyz.models.Location;
import com.timxyz.repositories.LocationRepository;
import com.timxyz.services.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ramic on 04.05.2017..
 */
@Service
public class LocationService extends BaseService<Location, LocationRepository> {

    Location findFirstByName(String name) {
        return null;
    }
    Location findFirstByNameOrType(String name, String type){
        return repository.findFirstByNameOrType(name, type);
    }
    Location findByNameContaining(String partOfName){
        return repository.findByNameContaining(partOfName);
    }
}

