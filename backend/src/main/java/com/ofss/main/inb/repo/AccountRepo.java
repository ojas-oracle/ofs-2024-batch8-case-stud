package com.ofss.main.inb.repo;

import org.springframework.data.repository.CrudRepository;

import com.ofss.main.inb.domain.Account;

public interface AccountRepo extends CrudRepository<Account,Integer>{
    // public boolean createAccount(String type , int customerId);
    // //public List<Savings> getSavings(int customerId);
    // //public List<Current> getCurrent(int customerId);
    // public List<Account> getPendingAccounts();
    // public int activateAccount(int id);
    // Account geAccountByID(int id);
    // boolean updateAccBalance(Account account);
}
