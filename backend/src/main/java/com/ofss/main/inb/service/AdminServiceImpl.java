package com.ofss.main.inb.service;

import java.util.List;

import com.ofss.main.inb.domain.Account;
import com.ofss.main.inb.repo.AccountRepo;
import com.ofss.main.inb.repo.AccountRepoImpl;
import com.ofss.main.inb.repo.AdminRepo;
import com.ofss.main.inb.repo.AdminRepoImpl;

public class AdminServiceImpl implements AdminService {

    AdminRepo adminRepo = new AdminRepoImpl();
    AccountRepo accountRepo = new AccountRepoImpl();

    @Override
    public boolean login(String loginID, String pwd) {
        return adminRepo.login(loginID, pwd);
    }

    @Override
    public String unblockCustomer(int id) {
        return adminRepo.unblockCustomer(id);
    }

    @Override
    public List<Account> getInactiveAccounts() {
        return accountRepo.getPendingAccounts();
    }

    @Override
    public int activateAccount(int id) {
        return accountRepo.activateAccount(id);
    }
    
}
