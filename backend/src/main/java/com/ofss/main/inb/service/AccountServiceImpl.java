package com.ofss.main.inb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofss.main.inb.domain.Account;
import com.ofss.main.inb.domain.Customer;
import com.ofss.main.inb.repo.AccountRepo;

@Service
public class AccountServiceImpl  implements AccountService{

    @Autowired
    AccountRepo accountRepo;

    @Override
    public Account createAccount(String type , Customer customer) {
        Account account = new Account();
        account.setCustomer(customer);
        account.setName(type);
        account.setStatus("active");
        return accountRepo.save(account);
    }


    
}
