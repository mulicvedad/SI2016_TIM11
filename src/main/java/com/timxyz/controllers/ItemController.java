package com.timxyz.controllers;

import com.timxyz.controllers.forms.Item.ItemCreateForm;
import com.timxyz.models.*;
import com.timxyz.services.CategoryService;
import com.timxyz.services.ItemService;
import com.timxyz.services.LocationService;
import com.timxyz.services.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Dario on 5/9/2017.
 */
@RestController
public class ItemController extends BaseController<Item, ItemService> {
    private CategoryService categoryService;
    private LocationService locationService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    @ResponseBody
    public ResponseEntity create(@RequestBody @Valid ItemCreateForm newItem) {
        try {
            Location location = locationService.get(newItem.getLocationID());
            Category category = categoryService.get(newItem.getCategoryID());

            String skuNumber = newItem.getSkuNumber();
            String name = newItem.getName();
            String unitOfMeasurement = newItem.getUnitOfMeasurement();
            String purchasedBy = newItem.getPurchasedBy();
            String personResponsible = newItem.getPersonResponsible();
            Timestamp dateOfPurchase = newItem.getDateOfPurchase();
            BigDecimal value = newItem.getValue();
            Collection<AuditItem> auditItems = null;
            Collection<PastAuditItem> pastAuditItems = null;

            return ResponseEntity.ok(service.save(new Item(skuNumber, name, unitOfMeasurement, purchasedBy, personResponsible, dateOfPurchase, value, auditItems, category, location, pastAuditItems)));
        } catch(ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/items/search-by/category", method = RequestMethod.GET)
    public List<Item> getAllByCategoryName(@RequestParam("name") String name) throws ServiceException {
        return service.getAllByCategoryName(name);
    }

    @RequestMapping(value = "/items/search-by/location", method = RequestMethod.GET)
    public List<Item> getAllByLocationName(@RequestParam("name") String name) throws ServiceException {
        return service.getAllByLocationName(name);
    }

    @RequestMapping(value = "/items/search-by/location-type", method = RequestMethod.GET)
    public List<Item> getAllByLocationTypeName(@RequestParam("name") String name) throws ServiceException {
        return service.getAllByLocationTypeName(name);
    }

    /*@RequestMapping(value = "/items/search-by/date", method = RequestMethod.GET)
    public List<Item> getAllByDate(@RequestParam("date") Timestamp date) throws ServiceException {
        return service.getAllByDate(date);
    }*/
}
