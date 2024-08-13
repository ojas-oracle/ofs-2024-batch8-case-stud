package com.ofss.main.inb.service;

import com.ofss.main.inb.repo.TransactionRepo;
import com.ofss.main.inb.repo.TransactionRepoImpl;

public class TransactionServiceImpl implements TransactionService{

    TransactionRepo transactionRepo = new TransactionRepoImpl();

    @Override
    public boolean createTxn(double amount, int from, int to) {
        return transactionRepo.createTransaction(amount, from, to);
    }
    
}
