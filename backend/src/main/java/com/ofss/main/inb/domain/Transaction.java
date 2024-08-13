package com.ofss.main.inb.domain;
import java.time.LocalDate;

public class Transaction {

    private int id;
    private Account from_account;
    private Account to_account;
    private double amount;
    private String status;
    private String remarks;
    private LocalDate created_at;
    private LocalDate completed_at;
    private String transfer_type;

    public Account getFrom_account() {
        return from_account;
    }
    public void setFrom_account(Account from_account) {
        this.from_account = from_account;
    }
    public Account getTo_account() {
        return to_account;
    }
    public void setTo_account(Account to_account) {
        this.to_account = to_account;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public String getCreated_at() {
        return created_at.toString();
    }
    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }
    public String getCompleted_at() {
        return completed_at.toString();
    }
    public void setCompleted_at(LocalDate completed_at) {
        this.completed_at = completed_at;
    }
    public String getTransfer_type() {
        return transfer_type;
    }
    public void setTransfer_type(String transfer_type) {
        this.transfer_type = transfer_type;
    }
    
}
