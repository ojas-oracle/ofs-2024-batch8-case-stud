package com.ofss.main.inb.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ofss.main.inb.domain.Account;
import com.ofss.main.inb.domain.Admin;
import com.ofss.main.inb.domain.Customer;
import com.ofss.main.inb.repo.AdminRepo;
import com.ofss.main.inb.repo.CustomerRepo;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepo adminRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Override
    public boolean login(String loginID, String pwd) {
        List<Admin> admins = adminRepo.findByLoginId(loginID);
        if(admins.isEmpty()){
            // customer not found
            return false;
       }else{
            if(admins.get(0).getLoginPass().equals(pwd)){
                return true;
                // success login
            }
            return false;
       }
    }

    @Override
    public boolean unblockCustomer(int id) {
        Customer customer = customerRepo.findById(id).get();
        if(customer!=null){
            customer.setStatus("active");
            customer.setLogin_attempts(0);
            customerRepo.save(customer);
            return true;
        }
        return false;
        //return adminRepo.unblockCustomer(id);
    }

    @Override
    public List<Account> getInactiveAccounts() {
        return null;
        //return accountRepo.getPendingAccounts();
    }

    @Override
    public int activateAccount(int id) {
        return 0;
        //return accountRepo.activateAccount(id);
    }
    
}
