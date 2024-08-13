package com.ofss.main.inb.repo;

public interface AdminRepo {
    public boolean login(String loginID , String pwd);
    public String unblockCustomer(int id);
}
