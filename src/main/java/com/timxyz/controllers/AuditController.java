package com.timxyz.controllers;

import com.timxyz.models.Audit;
import com.timxyz.services.AuditService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuditController extends BaseController<Audit, AuditService> {

    @ResponseBody
    public ResponseEntity filterByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(service.getByName(name));
    }
}