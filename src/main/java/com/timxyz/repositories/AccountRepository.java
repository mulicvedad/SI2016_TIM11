package com.timxyz.repositories;

import com.timxyz.models.Account;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {
    Account findFirstByEmail(String email);
    Account findFirstByEmailOrUsername(String email, String username);
    List<Account> findByEmailContaining(String partOfEmail);
}