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
        if(model.getId() == null && getByName(model.getName()) != null)
            throw new ServiceException("A location type with this name already exists!");
        else if(model.getId() != null) {
            // TO-DO: Finish proper partial update logic (shouldn't send the whole object during update)
        }

        try {
            return super.save(model);
        } catch (ServiceException e) {
            throw new ServiceException("Unknown role ID!");
        }
    }

    public Collection<LocationType> filterByName(String name) {
        return repository.filterByName(name);
    }
    
    public LocationType getByName(String name) {
        return repository.findFirstByName(name);
    }
}