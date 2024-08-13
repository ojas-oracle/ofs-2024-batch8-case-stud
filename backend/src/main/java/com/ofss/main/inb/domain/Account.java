package com.ofss.main.inb.domain;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int accountNumber;

    @Column(name = "account_type")
    private String name;

    @Column(name = "account_balance")
    private double balance;

    @Column(name = "account_status")
    private String status;

    @Column(name = "account_minimum_balance")
    private double min_balance;
    
    @Column(name = "withdrawal_limit")
    private double limit;

    @Column(name = "account_rate")
    private double account_rate;

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    @JsonBackReference
    private Customer customer;

    // private List<Transaction> txns;
    // private List<Cheque> cheques;

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
