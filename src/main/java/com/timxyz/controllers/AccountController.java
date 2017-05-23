package com.timxyz.controllers;

import com.timxyz.controllers.forms.Account.AccountCreateForm;
import com.timxyz.models.Account;
import com.timxyz.services.AccountService;
import com.timxyz.services.exceptions.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AccountController extends BaseController<Account, AccountService> {

    @ResponseBody
    public ResponseEntity create(@RequestBody @Valid AccountCreateForm newAccount) {
        try {
            ///@Valid AccountCreateForm Maps our DTO (data transfer object) to the proper Account class after the
            // validations in our DTO (AccountCreateForm) have passed
            Account acc = modelMapper.map(newAccount, Account.class);
            acc.setId(null); // modelMapper somehow seems to map the roleId field to Id...which shouldn't happen
            acc = service.save(acc);
            return ResponseEntity.ok(acc);
        } catch(ServiceException e) {
            return error(e);
        }
    }

    @ResponseBody
    public ResponseEntity filterByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(service.getByPartOfEmail(email));
    }
}