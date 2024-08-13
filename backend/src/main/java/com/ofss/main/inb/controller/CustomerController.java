package com.ofss.main.inb.controller;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ofss.main.inb.domain.Customer;
import com.ofss.main.inb.service.CustomerService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("banking")
public class CustomerController{

    @Autowired
    CustomerService customerService;
    
    @PostMapping("login")
    public Map<String,Object> login(@RequestBody Map<String, String> body) {
        int res = customerService.login(body.get("id"), body.get("password"));
        HashMap<String, Object> map = new HashMap<>();
        map.put("result", res);
        map.put("message" , res==1? "Login Success" : "Login Failed");
        map.put("success" , res==1? true:false);        
        return map;
    }

    @PostMapping("register")
    public Map<String,Object> signup(@RequestBody Customer c) {
        Customer customer = customerService.register(c);
        HashMap<String, Object> map = new HashMap<>();
        map.put("result", customer);
        map.put("message" , customer!=null? "Register Success" : "Register Failed");
        map.put("success" , customer!=null? true:false);        
        return map;
    }
    
    

}
