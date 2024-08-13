package com.ofss.main.inb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ofss.main.inb.domain.Transaction;
import com.ofss.main.inb.repo.TransactionRepo;


@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    TransactionRepo transactionRepo;

    @Override
    public Transaction createTxn(Transaction transaction) {
        return transactionRepo.save(transaction);
    }
    
}
