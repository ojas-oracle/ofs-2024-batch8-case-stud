package com.ofss.main.inb.domain;

import java.util.List;

public class Account {

    private int accountNumber;
    private String name;
    private double balance;
    private String status;
    private Customer customer;
    private List<Transaction> txns;
    private List<Cheque> cheques;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public List<Cheque> getCheques() {
        return cheques;
    }

    public void setCheques(List<Cheque> cheques) {
        this.cheques = cheques;
    }

    public List<Transaction> getTxns() {
        return txns;
    }

    public void setTxns(List<Transaction> txns) {
        this.txns = txns;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Account(){

    }
    
    public String toString(){
        return "Account Number : " + accountNumber; 
    }

    public Account(int no , String n , double bal){
        this.accountNumber = no;
        this.name=n;
        this.balance = bal;
    }
    
    public int getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean withdraw(double amount){
        if(amount > balance || amount<=0){
            return false;
        }
        this.balance-=amount;
        return true;
    }

    public boolean deposit(double amount){
        if(amount<0){
            return false;
        }
        this.balance+=amount;
        return true;
    }

}
