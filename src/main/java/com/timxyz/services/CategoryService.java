package com.timxyz.services;

import com.timxyz.models.Category;
import com.timxyz.repositories.CategoryRepository;
import com.timxyz.services.exceptions.ServiceException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CategoryService extends BaseService<Category, CategoryRepository> {

    public Category save(Category model) throws ServiceException {
        Category sameName = getByName(model.getName());
        Category parent = model.getParent();

        if (sameName != null && model.getId() != sameName.getId()) {
            throw new ServiceException("A category with this name already exists!");
        } else if (model.getId() != null && parent != null && model.equals(parent)) {
            throw new ServiceException("Parent can't be the same category!");
        }

        return super.save(model);
    }

    public Collection<Category> filterByName(String name) {
        return repository.filterByName(name);
    }

    public Category getByName(String name) {
        return repository.findFirstByName(name);
    }
}
