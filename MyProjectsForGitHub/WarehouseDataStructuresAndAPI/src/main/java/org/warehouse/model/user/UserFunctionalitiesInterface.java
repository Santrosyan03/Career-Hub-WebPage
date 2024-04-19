package org.warehouse.model.user;

import org.warehouse.exceptions.ExceedingAmountOfMoney;
import org.warehouse.exceptions.InvalidQuantityOfGems;
import org.warehouse.exceptions.ExceedingAmountOfGems;
import org.warehouse.exceptions.InvalidQuantityOfMoney;
import org.warehouse.management.WareHouse;
import org.warehouse.model.material.MaterialType;

public interface UserFunctionalitiesInterface {
    String logIn() throws InterruptedException;
    String logOut() throws InterruptedException;
    void upgradeLevel();
    void checkInventory(WareHouse assignedWarehouse, MaterialType type);
    int updateMoney(int quantity) throws InvalidQuantityOfMoney;
    int updateGems(int quantity) throws InvalidQuantityOfGems;
    int spendMoney(int quantity) throws InvalidQuantityOfMoney, ExceedingAmountOfMoney;
    int spendGems(int quantity) throws ExceedingAmountOfGems, InvalidQuantityOfGems;
}
