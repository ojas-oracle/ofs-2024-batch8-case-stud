package com.ofss.main.inb.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ofss.main.inb.domain.Customer;
import com.ofss.main.inb.repo.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepo customerRepo;

    @Override
    public int register(Customer c) {
        return 0;
    }

    @Override
    public int login(String loginID, String pwd) {
       List<Customer> customer = customerRepo.findByLoginId(loginID);
       System.out.println(customer);
       return 0;
    }
    
}
