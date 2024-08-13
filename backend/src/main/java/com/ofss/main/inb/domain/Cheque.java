package com.ofss.main.inb.domain;

import java.time.LocalDate;

public class Cheque {

    private int id;
    private String status;
    private int payee_acc;
    private int payer_acc;
    private double amount;
    private LocalDate date;



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getPayee_acc() {
        return payee_acc;
    }
    public void setPayee_acc(int payee_acc) {
        this.payee_acc = payee_acc;
    }
    public int getPayer_acc() {
        return payer_acc;
    }
    public void setPayer_acc(int payer_acc) {
        this.payer_acc = payer_acc;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getDate() {
        return date.toString();
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    
}
