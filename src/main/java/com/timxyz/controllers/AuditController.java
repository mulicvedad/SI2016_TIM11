package com.timxyz.controllers;

import com.timxyz.controllers.forms.Audit.AuditCreateForm;
import com.timxyz.models.Audit;
import com.timxyz.models.AuditItem;
import com.timxyz.models.Item;
import com.timxyz.models.Location;
import com.timxyz.services.*;
import com.timxyz.services.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashSet;

@RestController
public class AuditController extends BaseController<Audit, AuditService> {

    @Autowired
    private LocationService locationService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private AuditItemService auditItemService;

    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    public void setAuditItemService(AuditItemService auditItemService) {
        this.auditItemService = auditItemService;
    }

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

    @Transactional
    @ResponseBody
    public ResponseEntity create(@RequestBody @Valid AuditCreateForm newAudit, @RequestHeader("Authorization") String token) {
        try {
            Audit audit = service.save(new Audit(
                    newAudit.getName(),
                    TokenAuthenticationService.findAccountByToken(token),
                    locationService.get(newAudit.getLocationId())
            ));

            Collection<Item> items = itemService.getByLocation(newAudit.getLocationId());

            for (Item i : items) {
                AuditItem auditItem = auditItemService.save(new AuditItem(i, audit));
            }

            return ResponseEntity.ok(service.save(audit));
        } catch (ServiceException e) {
            return error(e);
        }
    }

    @ResponseBody
    public ResponseEntity delete(@PathVariable("id") Long id, @RequestHeader("Authorization") String token) {
        try {
            Audit audit = service.get(id);

            if (audit.getFinished()) {
                // Finished audits cannot be deleted
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

            logForDelete(token, audit);

            service.delete(id);

            return ResponseEntity.ok().build();
        } catch (ServiceException e) {
            return error(e);
        }
    }
}