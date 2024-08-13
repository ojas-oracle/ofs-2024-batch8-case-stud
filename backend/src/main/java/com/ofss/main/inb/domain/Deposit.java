package com.ofss.main.inb.domain;

public class Deposit {

    private int id;
    private int acc_id;
    private double amount;
    private double rate;
    private String created_at;
    private String expires_at;
    private String type;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getAcc_id() {
        return acc_id;
    }
    public void setAcc_id(int acc_id) {
        this.acc_id = acc_id;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public double getRate() {
        return rate;
    }
    public void setRate(double rate) {
        this.rate = rate;
    }
    public String getCreated_at() {
        return created_at;
    }
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
    public String getExpires_at() {
        return expires_at;
    }
    public void setExpires_at(String expires_at) {
        this.expires_at = expires_at;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    
}
