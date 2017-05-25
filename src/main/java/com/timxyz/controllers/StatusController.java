package com.timxyz.controllers;

import com.timxyz.controllers.forms.Status.StatusCreateForm;
import com.timxyz.controllers.forms.Status.StatusUpdateForm;
import com.timxyz.models.Category;
import com.timxyz.models.Status;
import com.timxyz.services.StatusService;
import com.timxyz.services.exceptions.ServiceException;
import java.util.Collection;
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
public class StatusController extends BaseController<Status, StatusService> {

    @ResponseBody
    public ResponseEntity create(@RequestBody @Valid StatusCreateForm newStatus,
                                 @RequestHeader("Authorization") String token) {
        try {
            Status model = service.save(new Status(newStatus.getName()));
            logForCreate(token, model);
            return ResponseEntity.ok(model);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
      @ResponseBody
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody @Valid StatusUpdateForm updatedStatus, @RequestHeader("Authorization") String token) {
        try {
            Status status = service.get(id);

            status.setName(updatedStatus.getName());
         
            status = service.save(status);

            logForUpdate(token, status);

            return ResponseEntity.ok(status);
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
      public Collection<Status> filterByName(@RequestParam("name") String name) {
        return service.filterByName(name);
    }

   
}
