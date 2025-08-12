package com.hexaware.amazecare.entities;

import jakarta.persistence.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;

    private String name;
    private String email;
    private String password;
    private String contactNumber;

   
    public Admin() {}

    public Admin(String name, String email, String password, String contactNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.contactNumber = contactNumber;
    }

    
    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}

