package com.timxyz.controllers;

import com.timxyz.controllers.forms.Status.StatusCreateForm;
import com.timxyz.models.Status;
import com.timxyz.services.StatusService;
import com.timxyz.services.exceptions.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by Dario on 5/6/2017.
 */
@RestController
public class StatusController extends BaseController<Status, StatusService> {

    @ResponseBody
    public ResponseEntity create(@RequestBody @Valid StatusCreateForm newStatus) {
        try {
            return ResponseEntity.ok(service.save(new Status(newStatus.getName())));
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
