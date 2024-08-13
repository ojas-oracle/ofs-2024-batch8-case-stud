package com.ofss.main.inb.repo;
import java.util.List;
import com.ofss.main.inb.domain.Account;

public interface AccountRepo {
    public boolean createAccount(String type , int customerId);
    //public List<Savings> getSavings(int customerId);
    //public List<Current> getCurrent(int customerId);
    public List<Account> getPendingAccounts();
    public int activateAccount(int id);
    Account geAccountByID(int id);
    boolean updateAccBalance(Account account);
}
