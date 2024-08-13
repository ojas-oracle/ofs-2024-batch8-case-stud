package com.ofss.main.inb.service;

import com.ofss.main.inb.repo.AccountRepo;
import com.ofss.main.inb.repo.AccountRepoImpl;

public class AccountServiceImpl  implements AccountService{

    AccountRepo accountRepo = new AccountRepoImpl();

    @Override
    public boolean createAccount(String type , int customerId) {
        return accountRepo.createAccount(type, customerId);
    }


    
}
