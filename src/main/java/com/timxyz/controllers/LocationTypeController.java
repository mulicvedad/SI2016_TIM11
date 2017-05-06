package com.timxyz.controllers;

import com.timxyz.models.Location;
import com.timxyz.models.LocationType;
import com.timxyz.services.LocationTypeService;
import com.timxyz.services.exceptions.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * Created by amina on 04.05.2017..
 */
public class LocationTypeController extends BaseController<LocationType, LocationTypeService> {

    @ResponseBody
    public ResponseEntity create(@RequestBody @Valid LocationTypeCreateForm newLocationType) {
        try {
            //ništa ne kontam ovaj dio
            LocationType locType = modelMapper.map(newLocationType, LocationType.class);
            locType.setId(null); // modelMapper somehow seems to map the roleId field to Id...which shouldn't happen
            locType = service.save(locType);
            return ResponseEntity.ok(locType);
        } catch(ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @ResponseBody
    public ResponseEntity filterByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(service.getByName(name));
    }

}
