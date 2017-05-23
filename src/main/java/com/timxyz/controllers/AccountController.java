package com.timxyz.controllers;

import com.timxyz.controllers.forms.Account.AccountCreateForm;
import com.timxyz.models.Account;
import com.timxyz.services.AccountService;
import com.timxyz.services.exceptions.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.validation.Valid;

@RestController
public class AccountController extends BaseController<Account, AccountService> {

    @ResponseBody
    public ResponseEntity create(@RequestBody @Valid AccountCreateForm newAccount, @RequestHeader("Authorization") String token) {
        try {
            ///@Valid AccountCreateForm Maps our DTO (data transfer object) to the proper Account class after the
            // validations in our DTO (AccountCreateForm) have passed
            Account acc = modelMapper.map(newAccount, Account.class);
            acc.setId(null); // modelMapper somehow seems to map the roleId field to Id...which shouldn't happen
            acc = service.save(acc);
            logForCreate(token, acc);
            return ResponseEntity.ok(acc);
        } catch(ServiceException e) {
            // nacin na koji se vracao response nije omogucavao pristup
            // poruci o greski na frontendu
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder()
                    .add("message", e.getMessage());
            JsonObject responseObj = objectBuilder.build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObj);
        }
    }

    @ResponseBody
    public ResponseEntity filterByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(service.getByPartOfEmail(email));
    }
}