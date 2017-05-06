package com.timxyz.services;

import com.timxyz.models.Status;
import com.timxyz.repositories.StatusRepositoy;
import org.springframework.stereotype.Service;

/**
 * Created by Dario on 5/6/2017.
 */
@Service
public class StatusService extends BaseService<Status, StatusRepositoy> {
    public Status getByName(String name) {return repository.findFirstByName(name);}
}
