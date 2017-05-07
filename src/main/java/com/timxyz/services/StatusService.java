package com.timxyz.services;

import com.timxyz.models.Status;
import com.timxyz.repositories.StatusRepositoy;
import com.timxyz.services.exceptions.ServiceException;
import org.springframework.stereotype.Service;

/**
 * Created by Dario on 5/6/2017.
 */
@Service
public class StatusService extends BaseService<Status, StatusRepositoy> {

    public Status save(Status model) throws ServiceException {
        if(getByName(model.getName()) != null)
            throw new ServiceException("A status with this name already exists!");

        try {
            return super.save(model);
        } catch (ServiceException e) {
            throw new ServiceException(" ");
        }
    }

    public Status getByName(String name) {return repository.findFirstByName(name);}
}
