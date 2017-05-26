package com.timxyz.services;

import com.timxyz.models.Location;
import com.timxyz.models.LocationType;
import com.timxyz.repositories.LocationTypeRepository;
import com.timxyz.services.exceptions.ServiceException;

import java.util.Collection;

import org.springframework.stereotype.Service;

@Service
public class LocationTypeService extends BaseService<LocationType, LocationTypeRepository> {
    public LocationType save(LocationType model) throws ServiceException {
        LocationType sameName = getByName(model.getName());

        if (sameName != null && model.getId() != sameName.getId()) {
            throw new ServiceException("A location type with this name already exists!");
        }

        return super.save(model);
    }

    public Collection<LocationType> filterByName(String name) {
        return repository.filterByName(name);
    }
    
    public LocationType getByName(String name) {
        return repository.findFirstByName(name);
    }
}