package com.ofss.main.inb.domain;
import java.time.LocalDate;

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
@Table(name="transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private int id;

    @OneToOne
    @JoinColumn(name = "payer_account_id", referencedColumnName = "account_id")
    private Account from;

    @OneToOne
    @JoinColumn(name = "payee_account_id", referencedColumnName = "account_id")
    private Account to;

    @Column(name = "transaction_amount")
    private double amount;

    @Column(name = "transaction_status")
    private String status;

    @Column(name = "transaction_remarks")
    private String remarks;

    @Column(name = "transaction_created_at")
    private LocalDate created_at;

    @Column(name = "transaction_completed_at")
    private LocalDate completed_at;

    @Column(name = "transfer_type")
    private String transfer_type;
    
}
