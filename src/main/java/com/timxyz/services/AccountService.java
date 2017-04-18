package com.timxyz.services;

import com.timxyz.models.Account;
import com.timxyz.repositories.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService extends BaseService<Account, AccountRepository> {
}
