package com.ofss.main.inb.domain;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
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

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Customer(){}

    public Customer(String name, String email, String phone,String status ,  String address,String state,  String country, String loginId, String password
) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.loginId = loginId;
        this.password = password;
        this.country = country;
        this.state = state;
        this.status = status;
    }
    //List<Savings> savingAcc;
    //List<Current> currAcc;

        
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getloginId() {
        return loginId;
    }
    public void setloginId(String loginId) {
        this.loginId = loginId;
    }
    public int getLogin_attempts() {
        return login_attempts;
    }
    public void setLogin_attempts(int login_attempts) {
        this.login_attempts = login_attempts;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }


}
