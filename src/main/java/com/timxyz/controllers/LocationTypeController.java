package com.timxyz.controllers;

import com.timxyz.controllers.forms.LocationType.LocationTypeCreateForm;
import com.timxyz.models.LocationType;
import com.timxyz.services.LocationTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.timxyz.services.exceptions.ServiceException;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by amina on 06.05.2017..
 */
@RestController
public class LocationTypeController extends BaseController<LocationType, LocationTypeService> {
    @ResponseBody
    public ResponseEntity create(@RequestBody @Valid LocationTypeCreateForm newLocType) {
        try {
            return ResponseEntity.ok(service.save(modelMapper.map(newLocType, LocationType.class)));
        } catch(ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}