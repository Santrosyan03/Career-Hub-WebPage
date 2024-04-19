package org.warehouse.model.user;

import org.warehouse.management.WareHouse;

public class User {
    private String userName;
    private WareHouse assignedWarehouse;
    private int gems;
    private int money;
    private int level;

    public User(String userName, WareHouse warehouse, int gems, int money, int level) {
        this.userName = userName;
        this.assignedWarehouse = warehouse;
        this.gems = gems;
        this.money = money;
        this.level = level;
    }

    public String getUsername() {
        return this.userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public int getGems() {
        return gems;
    }

    public void setGems(int gems) {
        this.gems = gems;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel() {
        this.level += 1;
    }
}
