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

    public Item save(Item model) throws ServiceException {

        List<String> codes = Arrays.asList("kom", "m", "kg");

        if (model.getId() == null && findFirstBySkuNumber(model.getSkuNumber()) != null) {
            throw new ServiceException("Inventurna stavka s navedenim inventurnim brojem već postoji.");
        } else if (!codes.contains(model.getUnitOfMeasurement().toLowerCase())) {
            throw new ServiceException("Neispravna mjerna jedinica.");
        }

        return super.save(model);
    }

    public Item findFirstBySkuNumber(String skuNumber) {
        return repository.findFirstBySkuNumber(skuNumber);
    }

    public Collection<Item> filterByName(String name) {
        return repository.filterByName(name);
    }

    public Collection<Item> getByLocation(Long id) {
        return repository.getByLocation(id);
    }
}


