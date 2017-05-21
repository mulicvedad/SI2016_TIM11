package com.timxyz.services;

import com.timxyz.models.Category;
import com.timxyz.repositories.CategoryRepository;
import com.timxyz.services.exceptions.ServiceException;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends BaseService<Category, CategoryRepository> {
    public Category save(Category model) throws ServiceException {
        if(model.getId() == null && getByName(model.getName()) != null)
            throw new ServiceException("A category with this name already exists!");
        else if(model.getId() != null) {
            // TO-DO: Finish proper partial update logic (shouldn't send the whole object during update)
        }
        try {
            return super.save(model);
        } catch (ServiceException e) {
            throw new ServiceException("Unknown role ID!");
        }
    }

    public Category getByName(String name) {
        return repository.findFirstByName(name);
    }
}
