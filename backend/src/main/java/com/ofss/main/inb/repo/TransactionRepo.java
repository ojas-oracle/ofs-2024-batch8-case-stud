package com.ofss.main.inb.repo;

import org.springframework.data.repository.CrudRepository;
import com.ofss.main.inb.domain.Account;
import com.ofss.main.inb.domain.Transaction;
import java.util.List;


public interface TransactionRepo extends CrudRepository<Transaction,Integer>{
    public List<Transaction> findByFromOrTo(Account fromAcc , Account toAcc);
}
