package ru.iambelyaev.coincontrolserver.restapi.model;

import ru.iambelyaev.coincontrolserver.hibernate.models.User;

public class Wallet {
    private Integer id = 0;
    private Integer user = 0;
    private String name;
    private Integer money = 0;

    public Wallet(int id, int user, String name, int money) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.money = money;
    }

    public Wallet() {
    }

    public int getUser() {
        return user;
    }

    public  int getId() {
        return id;
    }

    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setUser(User user) {
        this.user = user.getId();
    }
}