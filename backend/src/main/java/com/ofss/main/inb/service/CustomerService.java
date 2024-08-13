package com.ofss.main.inb.service;

import java.util.List;

import com.ofss.main.inb.domain.Account;
import com.ofss.main.inb.domain.Customer;

public interface CustomerService {
    public Customer register(Customer c);
    public int login(String loginID , String pwd);
    public List<Account> getAll(int id);
}
