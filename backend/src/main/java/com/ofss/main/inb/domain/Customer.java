package com.ofss.main.inb.domain;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int id;

    @Column(name = "customer_email")
    private String email;

    @Column(name = "customer_name")
    private String name;

    @Column(name = "customer_phone")
    private String phone;

    @Column(name = "customer_address")
    private String address;

    @Column(name = "customer_loginId")
    private String loginId;

    @Column(name = "customer_password")
    private String password;

    @Column(name = "login_attempts")
    private int login_attempts;

    @Column(name = "customer_country")
    private String country;

    @Column(name = "customer_state")
    private String state;

    @Column(name = "customer_status")
    private String status;

}
