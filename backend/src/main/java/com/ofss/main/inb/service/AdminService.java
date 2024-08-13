package com.ofss.main.inb.service;

import java.util.List;

import com.ofss.main.inb.domain.Account;

public interface AdminService {
    public boolean login(String loginID , String pwd);
    public String unblockCustomer(int id);
    public List<Account> getInactiveAccounts();
    public int activateAccount(int id);
}
