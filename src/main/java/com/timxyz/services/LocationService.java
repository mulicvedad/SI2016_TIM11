package com.timxyz.services;

import com.timxyz.models.Location;
import com.timxyz.repositories.LocationRepository;
import com.timxyz.services.exceptions.ServiceException;
import java.util.Collection;
import org.springframework.stereotype.Service;

/**
 * Created by ramic on 04.05.2017..
 */
@Service
public class LocationService extends BaseService<Location, LocationRepository> {
    public Location save(Location model) throws ServiceException {
        if(model.getId() == null && getByName(model.getName()) != null)
            throw new ServiceException("A location with this name already exists!");
        else if(model.getId() != null) {
            // TO-DO: Finish proper partial update logic (shouldn't send the whole object during update)
        }
        else if(model.getType() == null) {
            throw new ServiceException("Unknown type ID!");
        }

        try {
            return super.save(model);
        } catch (ServiceException e) {
            throw new ServiceException("Couldn't save location!");
        }
    }
    public Collection<Location> filterByName(String name) {
        return repository.filterByName(name);
    }
    
    public Location getByName(String name) {
        return repository.findFirstByName(name);
    }
}

