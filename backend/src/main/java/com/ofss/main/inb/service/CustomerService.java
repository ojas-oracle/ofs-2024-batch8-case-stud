package com.ofss.main.inb.service;

import com.ofss.main.inb.domain.Customer;

public interface CustomerService {
    public Customer register(Customer c);
    public int login(String loginID , String pwd);
}
