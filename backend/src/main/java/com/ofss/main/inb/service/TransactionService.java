package com.ofss.main.inb.service;

public interface TransactionService {
    public boolean createTxn(double amount , int from , int to);
}
