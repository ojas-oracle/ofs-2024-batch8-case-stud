package com.ofss.main.inb.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ofss.main.inb.domain.Customer;
import com.ofss.main.inb.domain.Transaction;
import com.ofss.main.inb.service.CustomerService;
import com.ofss.main.inb.service.TransactionService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("banking")
public class CustomerController{

    @Autowired
    CustomerService customerService;

    @Autowired
    TransactionService transactionService;
    
    @PostMapping("login")
    public Map<String,Object> login(@RequestBody Map<String, String> body) {
        int res = customerService.login(body.get("id"), body.get("password"));
        HashMap<String, Object> map = new HashMap<>();
        map.put("result", res);
        map.put("message" , res==1? "Login Success" : "Please check your userame and password");
        map.put("success" , res==1? true:false);        
        return map;
    }

    @PostMapping("register")
    public Map<String,Object> signup(@RequestBody Customer c) {
        HashMap<String, Object> map = new HashMap<>();
        try {

            Customer customer = customerService.register(c);
        
            map.put("result", customer);
            map.put("message" , customer!=null? "Register Success" : "Register Failed");
            map.put("success" , customer!=null? true:false);        
            return map;
            
        } catch (Exception e) {
            map.put("success" , false);
            map.put("message" , "Register Failed , " + e.getMessage());
            return map;
        }
      
    }

    @PostMapping("pay/{id}")
    public Map<String,Object> pay(@RequestBody Transaction transaction) {
        System.out.println(transaction);
        Transaction txn = transactionService.createTxn(transaction);
        HashMap<String, Object> map = new HashMap<>();
        map.put("result", txn);
        map.put("message" , txn!=null? "Payment Initiated" : "Payment Failed");
        map.put("success" , txn!=null? true:false);        
        return map;
    }

    @GetMapping("dashboard/{id}")
    public Map<String,Object> dashboard(@PathVariable int id) {
        List<Transaction> transactions = customerService.getTransactions(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("result", transactions);
        map.put("message" , transactions!=null? "success" : "failed");
        map.put("success" , transactions!=null? true:false);        
        return map;
    }
    
    

}
