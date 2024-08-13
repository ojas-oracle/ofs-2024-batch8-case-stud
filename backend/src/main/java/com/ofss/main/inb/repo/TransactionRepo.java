package com.ofss.main.inb.repo;

import org.springframework.data.repository.CrudRepository;

import com.ofss.main.inb.domain.Transaction;

public interface TransactionRepo extends CrudRepository<Transaction,Integer>{
 
}
