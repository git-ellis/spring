package com.example.aspect;

public class CreditCard {
    private String uuid;
    private String userName;
    private String company;
    private String password;
    private int money;

    public CreditCard() {
    }

    public CreditCard(String uuid, String userName, String company, String password, int money) {
        this.uuid = uuid;
        this.userName = userName;
        this.company = company;
        this.password = password;
        this.money = money;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
