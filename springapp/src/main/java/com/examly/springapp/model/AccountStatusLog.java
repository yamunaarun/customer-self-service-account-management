package com.examly.springapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AccountStatusLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String oldStatus;
    private String newStatus;

    public AccountStatusLog() {}

    public Long getId() {
        return id;
    }
  
    public void setId(Long id) {
        this.id = id;
    }
  
    public String getOldStatus() {
        return oldStatus;
    }
  
    public void setOldStatus(String oldStatus) {
        this.oldStatus = oldStatus;
    }
  
    public String getNewStatus() {
        return newStatus;
    }
  
    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }
}
