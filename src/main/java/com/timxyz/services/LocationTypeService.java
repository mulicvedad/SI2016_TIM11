package com.timxyz.services;

import com.timxyz.models.LocationType;
import com.timxyz.repositories.LocationTypeRepository;
import com.timxyz.services.exceptions.ServiceException;

/**
 * Created by amina on 04.05.2017..
 */
public class LocationTypeService extends BaseService<LocationType, LocationTypeRepository> {

    public LocationType save (LocationType model) throws ServiceException {
        if (model.getId() == null && getByName(model.getName()) != null)
            throw new ServiceException("A location type with this name already exists!");
        else if(model.getId()!=null){
      //ne znam
        }
        return super.save(model); //nije ovo ok, ne kontam
    }

    public LocationType getByName(String name) {
        return repository.findFirstByName(name);
    }

    public  LocationType getByDescription(String description){
        return repository.findFirstByDescription(description);
    }
}
