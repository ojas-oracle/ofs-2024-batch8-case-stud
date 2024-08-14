package com.ofss.main.inb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ofss.main.inb.service.AdminService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    AdminService adminService;
    
    @PostMapping("login")
    public Map<String,Object> login(@RequestBody Map<String, String> body) {
        boolean res = adminService.login(body.get("id"), body.get("password"));
        HashMap<String, Object> map = new HashMap<>();
        map.put("result", res);
        map.put("message" , res? "Admin Login Success" : "Admin Login Failed");
        map.put("success" , res? true:false);        
        return map;
    }

    @GetMapping("unblock/{cust_id}")
    public Map<String,Object> unblockCustomer(@PathVariable int cust_id) {
        boolean res = adminService.unblockCustomer(cust_id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("result", res);
        map.put("message" , res? "Unblock Success" : "Unblock Failed");
        map.put("success" , res? true:false);
        return map;
    }
    
    

}
