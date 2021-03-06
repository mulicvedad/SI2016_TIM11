package com.timxyz.controllers;


import com.timxyz.controllers.forms.Location.LocationCreateForm;
import com.timxyz.controllers.forms.Location.LocationUpdateForm;
import com.timxyz.models.Location;
import com.timxyz.models.LocationType;
import com.timxyz.services.LocationService;
import com.timxyz.services.LocationTypeService;
import com.timxyz.services.exceptions.ServiceException;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class LocationController extends BaseController<Location, LocationService> {
    private LocationTypeService locationTypeService;

    @Autowired
    public void setLocationTypeService(LocationTypeService locationTypeService) {
        this.locationTypeService = locationTypeService;
    }

    @Transactional
    @ResponseBody
    public ResponseEntity create(@RequestBody @Valid LocationCreateForm newLocation, @RequestHeader("Authorization") String token) throws ServiceException {
        Location location = service.save(new Location(
                newLocation.getName(),
                service.get(newLocation.getParentId()),
                locationTypeService.get(newLocation.getTypeId())
        ));

        logForCreate(token, location);

        return ResponseEntity.ok(location);
    }

    @Transactional
    @ResponseBody
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody @Valid LocationUpdateForm updatedLocation, @RequestHeader("Authorization") String token) throws ServiceException {
        Location location = service.get(id);

        location.setName(updatedLocation.getName());
        location.setParent(service.get(updatedLocation.getParentId()));
        location.setType(locationTypeService.get(updatedLocation.getTypeId()));

        location = service.save(location);

        logForUpdate(token, location);

        return ResponseEntity.ok(location);
    }
    
     public Collection<Location> filterByName(@PathVariable("name") String name) {
        return service.filterByName(name);
    }  
}