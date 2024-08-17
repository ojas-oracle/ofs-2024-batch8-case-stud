package com.ofss.main.inb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofss.main.inb.domain.Account;
import com.ofss.main.inb.domain.Transaction;
import com.ofss.main.inb.repo.AccountRepo;
import com.ofss.main.inb.repo.TransactionRepo;


@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    TransactionRepo transactionRepo;

    @Autowired
    AccountRepo accountRepo;

    @Override
    public Transaction createTxn(Transaction transaction) {
        Account fromAccount = accountRepo.findById(transaction.getFrom().getAccountNumber()).get();
        Account toAccount = accountRepo.findById(transaction.getTo().getAccountNumber()).get();
        if(fromAccount.getBalance() < transaction.getAmount()){
            System.out.println("Account balance not sufficient");
            return null;
        }
        fromAccount.setBalance(fromAccount.getBalance() - transaction.getAmount());
        toAccount.setBalance(toAccount.getBalance() + transaction.getAmount());
        accountRepo.save(fromAccount);
        accountRepo.save(toAccount);
        return transactionRepo.save(transaction);
    }

    @Override
    public List<Transaction> getAllByAccount(int id) {
        Account account = new Account();
        account.setAccountNumber(id);
        System.out.println(account.toString());
        return transactionRepo.findByFromOrTo(account , account);
    }

    
    
}
