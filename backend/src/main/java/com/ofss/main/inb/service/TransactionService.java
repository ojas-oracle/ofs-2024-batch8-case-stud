package com.ofss.main.inb.service;

import com.ofss.main.inb.domain.Transaction;
import java.util.List;

public interface TransactionService {
    public Transaction createTxn(Transaction transaction);
    public List<Transaction> getAllByAccount(int id);
}
