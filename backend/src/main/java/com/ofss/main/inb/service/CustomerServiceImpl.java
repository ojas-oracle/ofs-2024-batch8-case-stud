package com.ofss.main.inb.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofss.main.inb.domain.Account;
import com.ofss.main.inb.domain.Customer;
import com.ofss.main.inb.domain.Transaction;
import com.ofss.main.inb.repo.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService transactionService;

    @Override
    public Customer register(Customer c) {
        System.err.println(c);
        Customer customer = customerRepo.save(c);
        Account account = accountService.createAccount("savings", customer);
        System.out.println(account);
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
                return customers.get(0).getId();
                // success login
            }else{
                Customer customer = customers.get(0);
                customer.setLogin_attempts(customer.getLogin_attempts() + 1);

                if(customer.getLogin_attempts()==3){
                    customer.setStatus("inactive");
                }
                customerRepo.save(customer);
                // wrong pwd
                return -1;
            }
       }

    }

    @Override
    public List<Account> getAll(int id) {
        Customer customer = customerRepo.findById(id).get();
        if(customer!=null){
            return customer.getAccounts();
        }
        return null;
    }

    @Override
    public List<Transaction> getTransactions(int customerID) {
        List<Account> accounts = getAll(customerID);
        if(accounts.isEmpty()!=true){
            List<Transaction> transactions =  transactionService.getAllByAccount(accounts.get(0).getAccountNumber());
            return transactions;
        }
        return null;
    }
    
    
}
