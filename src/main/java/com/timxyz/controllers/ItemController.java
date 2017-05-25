package com.timxyz.controllers;

import com.timxyz.controllers.forms.Item.ItemCreateForm;
import com.timxyz.controllers.forms.Item.ItemUpdateForm;
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
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;


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
    public ResponseEntity create(@RequestBody @Valid ItemCreateForm newItem, @RequestHeader("Authorization") String token) {
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
            Item model = service.save(new Item(skuNumber, name, unitOfMeasurement, purchasedBy,
                    personResponsible, dateOfPurchase, value, auditItems, category, location, pastAuditItems));
            
            logForCreate(token, model);
            return ResponseEntity.ok(model);
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
  
    @RequestMapping(value = "/items/search-by/filter", method = RequestMethod.GET)
    public List<Item> getAllByFilter(@RequestParam("name") String name) throws ServiceException {
        return service.getAllByFilter(name);
    }

    @RequestMapping(value = "/items/search-by/date", method = RequestMethod.GET)
    public List<Item> getAllByDate(@RequestParam("date") String paramDate) throws ServiceException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(paramDate);
        return service.getAllByDate(date);
    }

    @ResponseStatus( HttpStatus.OK )
    @RequestMapping(value = "/items/search-by/between-dates", method = RequestMethod.GET)
    public List<Item> getAllBetweenDates(@RequestParam("date1") String paramDate1, @RequestParam("date2") String paramDate2) throws ParseException {
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = format.parse(paramDate1);
                Date date2 = format.parse(paramDate2);
                return service.getAllBetweenDates(date1, date2);
            }
            catch (ServiceException e){
                return null;
            }
    }

    @RequestMapping(value = "/items/search-by/name", method = RequestMethod.GET)
    public List<Item> getAllByItemName(@RequestParam("name") String name){
        return service.getAllByItemName(name);
    }

    /*
    @RequestMapping(value = "/items/search-by/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllByItemName(@PathVariable("name") String name){
        List<Item> items = service.getAllByItemName(name);
        return  new ResponseEntity<List<Item>>(items, HttpStatus.OK);
    }
    */

     @ResponseBody
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody @Valid ItemUpdateForm updatedItem, @RequestHeader("Authorization") String token) {
        try {
            Item item = service.get(id);

            item.setName(updatedItem.getName());
            item.setUnitOfMeasurement(updatedItem.getUnitOfMeasurement());
            item.setValue(updatedItem.getValue()); 
            item.setPersonResponsible(updatedItem.getPersonResponsible());
            item.setDateOfPurchase(updatedItem.getDateOfPurchase());
            item.setSkuNumber(updatedItem.getSkuNumber());
            item.setPurchasedBy(updatedItem.getPurchasedBy());

            item.setCategory(categoryService.get(updatedItem.getCategoryID()));
            item.setLocation(locationService.get(updatedItem.getLocationID()));

            item = service.save(item);

            logForUpdate(token, item);

            return ResponseEntity.ok(item);
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Collection<Item> filterByName(@RequestParam("name") String name) {
        return service.filterByName(name);
    }
}
