package com.timxyz.controllers;

import com.timxyz.models.Account;
import com.timxyz.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/accounts")
@RestController
public class AccountController {
    private AccountService accountService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Iterable<Account> all() {
        return accountService.all();
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public Account create(@RequestBody Account newAccount) {
        accountService.save(newAccount);
        return newAccount;
    }
}