package com.timxyz.controllers;

import com.timxyz.controllers.forms.Category.CategoryCreateForm;
import com.timxyz.models.Category;
import com.timxyz.services.CategoryService;
import com.timxyz.services.exceptions.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CategoryController extends BaseController<Category, CategoryService> {
    @ResponseBody
    public ResponseEntity create(@RequestBody @Valid CategoryCreateForm newCategory,
                                 @RequestHeader("Authorization") String token) {
        try {
            Category model = service.save(new Category(newCategory.getName(), service.get(newCategory.getParentId())));
            logForCreate(token, model);
            return ResponseEntity.ok(model);
        } catch(ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

