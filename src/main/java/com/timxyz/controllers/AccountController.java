package com.timxyz.controllers;

import com.timxyz.controllers.forms.Account.AccountCreateForm;
import com.timxyz.controllers.forms.Account.AccountUpdateForm;
import com.timxyz.models.Account;
import com.timxyz.services.AccountService;
import com.timxyz.services.RoleService;
import com.timxyz.services.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
public class AccountController extends BaseController<Account, AccountService> {

    @Autowired
    private RoleService roleService;

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @ResponseBody
    public ResponseEntity create(@RequestBody @Valid AccountCreateForm newAccount, @RequestHeader("Authorization") String token) {
        try {
            Account account = service.save(new Account(
                    newAccount.getFullName(),
                    newAccount.getEmail(),
                    newAccount.getUsername(),
                    newAccount.getPassword(),
                    roleService.get(newAccount.getRoleId())
            ));

            logForCreate(token, account);

            return ResponseEntity.ok(account);
        } catch(ServiceException e) {
            return error(e);
        }
    }

    @ResponseBody
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody @Valid AccountUpdateForm updatedAccount, @RequestHeader("Authorization") String token) {
        try {
            Account account = service.get(id);

            account.setFullName(updatedAccount.getFullName());
            account.setUsername(updatedAccount.getUsername());
            account.setEmail(updatedAccount.getEmail());
            account.setRole(roleService.get(updatedAccount.getRoleId()));

            if (updatedAccount.isPasswordUpdated()) {
                account.setRawPassword(updatedAccount.getPassword());
            }

            account = service.save(account);

            logForUpdate(token, account);

            return ResponseEntity.ok(account);
        } catch (ServiceException e) {
            return error(e);
        }
    }

    public Collection<Account> filterByEmail(@RequestParam("email") String email) {
        return service.filterByEmail(email);
    }
}