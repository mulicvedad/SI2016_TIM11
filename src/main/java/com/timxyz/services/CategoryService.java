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

        if (sameName != null && model.getId() != sameName.getId()) {
            throw new ServiceException("A category with this name already exists!");
        }

        if (model.getId() != null) {
            // Check against cyclic parent categories
            Category parent = model.getParent();

            while (parent != null) {
                if (parent.getId() == model.getId()) {
                    throw new ServiceException("Cyclic categories detected!");
                }

                parent = parent.getParent();
            }
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
