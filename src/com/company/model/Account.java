package com.company.model;

public class Account {
    private String AccountNo;
    private String Name;
    private Double Amount;

    public Account() {
    }

    public Account(String AccountNo, String Name, Double Amount) {
        this.AccountNo = AccountNo;
        this.Name = Name;
        this.Amount = Amount;
    }

    public String getAccountNo() {
        return AccountNo;
    }

    public void setAccountNo(String accountNo) {
        AccountNo = accountNo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }
}
