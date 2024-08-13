package com.ofss.main.inb.repo;

public interface TransactionRepo {
    boolean createTransaction(double amount , int fromAcc , int toAcc);   
}
