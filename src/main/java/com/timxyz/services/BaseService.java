package com.timxyz.services;

import com.timxyz.models.BaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;

public class BaseService<M extends BaseModel, R extends PagingAndSortingRepository<M, Long> > {
    private R repository;

    @Autowired
    public void setRepository(R repository) {
        this.repository = repository;
    }

    public Iterable<M> all() {
        return repository.findAll();
    }

    public M get(Long id) {
        return repository.findOne(id);
    }

    public M save(M model) {
        return repository.save(model);
    }

    public void delete(Long id) { repository.delete(id); }

    public Boolean exists(Long id) { return repository.exists(id); }

    public Long count() { return repository.count(); }
}
