package com.timxyz.controllers;


import com.timxyz.controllers.forms.Location.LocationCreateForm;
import com.timxyz.models.Location;
import com.timxyz.models.LocationType;
import com.timxyz.services.LocationService;
import com.timxyz.services.LocationTypeService;
import com.timxyz.services.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LocationController extends BaseController<Location, LocationService> {
    private LocationTypeService locationTypeService;

    @Autowired
    public void setLocationTypeService(LocationTypeService locationTypeService) {
        this.locationTypeService = locationTypeService;
    }

    @ResponseBody
    public ResponseEntity create(@RequestBody @Valid LocationCreateForm newLocation) {
        try {
            Location parent = service.get(newLocation.getParentId());
            LocationType type = locationTypeService.get(newLocation.getTypeId());

            return ResponseEntity.ok(service.save(new Location(newLocation.getName(), parent, type)));
        } catch(ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}