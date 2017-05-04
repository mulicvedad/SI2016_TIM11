package com.timxyz.services;

import com.timxyz.models.Account;
import com.timxyz.repositories.AccountRepository;
import com.timxyz.services.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService extends BaseService<Account, AccountRepository> {

    @Autowired
    private RoleService roleService;

    public Account save(Account model) throws ServiceException {
        // Is this a new account that is being created (id == null), and is the email or username already
        // in use?
        if(model.getId() == null && getByEmailOrUsername(model.getEmail(), model.getUsername()) != null)
            throw new ServiceException("An account with this email or username already exists!");
        else if(model.getId() != null) {
            // Account is being updated ... username can't be changed.
            // TO-DO: Finish proper partial update logic (shouldn't send the whole object during update)
        }

        try {
            // We first need to set the role, because currently our model
            // only has the role's ID which was sent in JSON, not it's actual
            // reference to the row and other info in DB
            model.setRole(roleService.get(model.getRole().getId()));
            return super.save(model);
        } catch (ServiceException e) {
            throw new ServiceException("Unknown role ID!");
        }
    }

    public Account getByEmail(String email) {
        return repository.findFirstByEmail(email);
    }

    public Account getByEmailOrUsername(String email, String username) {
        return repository.findFirstByEmailOrUsername(email, username);
    }

    public List<Account> getByPartOfEmail(String partOfEmail) {
        return repository.findByEmailContaining(partOfEmail);
    }
}
