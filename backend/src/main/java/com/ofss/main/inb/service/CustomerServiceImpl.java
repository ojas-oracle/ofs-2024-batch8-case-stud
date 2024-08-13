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
    public Customer register(Customer c) {
        System.err.println(c);
        Customer customer = customerRepo.save(c);
        return customer;
    }

    @Override
    public int login(String loginID, String pwd) {
       List<Customer> customers = customerRepo.findByLoginId(loginID);
       if(customers.isEmpty()){
            // customer not found
            return -1;
       }else{
            if(customers.get(0).getPassword().equals(pwd)){
                return 1;
                // success login
            }else{
                Customer customer = customers.get(0);
                customer.setLogin_attempts(customer.getLogin_attempts() + 1);

                if(customer.getLogin_attempts()==3){
                    customer.setStatus("inactive");
                }
                customerRepo.save(customer);
                // wrong pwd
                return 0;
            }
       }

    }
    
}
