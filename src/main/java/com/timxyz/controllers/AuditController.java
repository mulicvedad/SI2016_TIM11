package com.timxyz.controllers;

import com.timxyz.models.Audit;
import com.timxyz.services.AuditService;
import com.timxyz.services.exceptions.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuditController extends BaseController<Audit, AuditService> {

    @ResponseBody
    public ResponseEntity filterByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(service.getByName(name));
    }

    @ResponseBody
    public ResponseEntity finalize(@PathVariable("id") Long id) {
        try {
            Audit audit = service.get(id);

            audit.setFinished(true);

            return ResponseEntity.ok(service.save(audit));
        } catch (ServiceException e) {
            return error(e);
        }
    }
}