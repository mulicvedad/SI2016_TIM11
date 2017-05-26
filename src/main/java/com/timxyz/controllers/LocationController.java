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

    @ResponseBody
    public ResponseEntity create(@RequestBody @Valid LocationCreateForm newLocation,
                                 @RequestHeader("Authorization") String token) {
        try {
            Location parent = service.get(newLocation.getParentId());
            LocationType type = locationTypeService.get(newLocation.getTypeId());
            Location model = service.save(new Location(newLocation.getName(), parent, type));
            logForCreate(token, model);
            return ResponseEntity.ok(model);
        } catch(ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @ResponseBody
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody @Valid LocationUpdateForm updatedCategory, @RequestHeader("Authorization") String token) {
        try {
            Location location = service.get(id);

            location.setName(updatedCategory.getName());
            location.setParent(service.get(updatedCategory.getParentId()));

            location = service.save(location);

            logForUpdate(token, location);

            return ResponseEntity.ok(location);
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
     public Collection<Location> filterByName(@RequestParam("name") String name) {
        return service.filterByName(name);
    }  
}