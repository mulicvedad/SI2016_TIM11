package com.timxyz.services;

import com.timxyz.models.Item;
import com.timxyz.repositories.ItemRepository;
import com.timxyz.services.exceptions.ServiceException;
import org.springframework.stereotype.Service;
import java.util.Collection;


import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Dario on 5/9/2017.
 */
@Service
public class ItemService extends BaseService<Item, ItemRepository> {

    public Item save(Item model) throws ServiceException{

        List<String> codes = Arrays.asList("kom", "m", "kg");

        if(model.getId() == null && findFirstBySkuNumber(model.getSkuNumber()) != null)
            throw new ServiceException("A Item with this skuNumber already exists!");
        else if(model.getName() == null)
            throw new ServiceException("A Item does not have a name (min 4 letters)!");
        else if(!codes.contains(model.getUnitOfMeasurement().toLowerCase()))
            throw new ServiceException("A Item does not have proper unit of measurement!");
        else if(model.getPurchasedBy() == null)
            throw new ServiceException("Need to quote name of person who purchased item!");
        else if(model.getPersonResponsible() == null)
            throw new ServiceException("Need to quote name of person responsible for item!");
        else if(model.getDateOfPurchase() == null)
            throw new ServiceException("Item does not have date of purchase!");
        else if(model.getValue() == null)
            throw new ServiceException("Item does not have value!");
        else if(model.getCategory() == null)
            throw new ServiceException("Item does not have proper category (missing ID?)!");
        else if(model.getLocation() == null)
            throw new ServiceException("Item does not have proper location!");

        try {
            return super.save(model);
        } catch (ServiceException e) {
            throw new ServiceException("123");
        }
    }

    public Item findFirstBySkuNumber(String skuNumber) {
        return repository.findFirstBySkuNumber(skuNumber);
    }

    public List<Item> getAllByCategoryName( String name ) { return repository.getAllByCategoryName(name); }

    public List<Item> getAllByLocationName( String name ) { return repository.getAllByLocationName(name); }

    public List<Item> getAllByLocationTypeName( String name ) {return repository.getAllByLocationTypeName(name); }

    public List<Item> getAllByFilter(String name) {
        return repository.getAllByFilter(name);
    }

    public List<Item> getAllByDate( Date date ) { return  repository.getAllByDate(date); }

    public List<Item> getAllBetweenDates( Date date1, Date date2 ) throws ServiceException {
        int compare = date2.compareTo(date1);
        if(compare == 1) return  repository.getAllBetweenDates(date1, date2);
        else if(compare == 0) throw new ServiceException("Dates can not be same!");
        else throw new ServiceException("First date must be before second!");
    }

    public List<Item> getAllByItemName( String name ) { return repository.getAllByItemName(name); }

    public Collection<Item> filterByName(String name) {
        return repository.filterByName(name);
    }

    public Collection<Item> getByLocation(Long id) {
        return repository.getByLocation(id);
    }
}


