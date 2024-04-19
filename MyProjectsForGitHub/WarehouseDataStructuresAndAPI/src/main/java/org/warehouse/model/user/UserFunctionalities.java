package org.warehouse.model.user;

import org.warehouse.exceptions.*;
import org.warehouse.management.WareHouse;
import org.warehouse.model.material.MaterialType;

import java.util.concurrent.TimeUnit;

/**
 * Provides implementations for user functionalities such as login, logout,
 * level upgrade, and currency management within an application context.
 */
public class UserFunctionalities implements UserFunctionalitiesInterface {

    // Reference to the User object this functionality class manipulates.
    private final User user;

    public UserFunctionalities(User user) {
        this.user = user;
    }

    /**
     * Simulates a login process with a delay.
     * @throws InterruptedException if the sleep is interrupted.
     */
    @Override
    public String logIn() throws InterruptedException {
        System.out.println("Logging in...");
        TimeUnit.SECONDS.sleep(5);
        System.out.println("Logged in");
        return "Logged in";
    }

    /**
     * Simulates a logout process with a delay.
     * @throws InterruptedException if the sleep is interrupted.
     */
    @Override
    public String logOut() throws InterruptedException {
        System.out.println("Logging out...");
        TimeUnit.SECONDS.sleep(5);
        System.out.println("Logged out");
        return "Logged out";
    }

    /**
     * Upgrades the user's level by incrementing it.
     */
    @Override
    public void upgradeLevel() {
        user.setLevel();
    }

    /**
     * Checks and displays the inventory quantity of a specified material type in a designated warehouse.
     *
     * @param assignedWarehouse The warehouse from which to check the inventory.
     *                          This warehouse object must contain the inventory data for various material types.
     * @param type The type of material for which the inventory needs to be checked.
     *             This specifies which material's stock level is being queried.
     */
    @Override
    public void checkInventory(WareHouse assignedWarehouse, MaterialType type) {
        try {
            int quantity = assignedWarehouse.getMaterialQuantity(assignedWarehouse, type);
            System.out.println("Inventory for " + type.getName() + ": " + quantity);
        } catch (MaterialNotFound e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Increases the user's money by a specified amount.
     *
     * @param quantity The amount of money to be added to the user's current total.
     *                 Must be greater than 0 to be considered valid.
     * @return The new total amount of money the user has after the addition.
     * @throws InvalidQuantityOfMoney If the specified quantity is less than or equal to zero,
     *                                indicating an attempt to add a non-positive amount.
     */
    @Override
    public int updateMoney(int quantity) throws InvalidQuantityOfMoney {
        if (quantity <= 0) {
            throw new InvalidQuantityOfMoney("The amount of money you want to get should be greater than 0");
        }
        int newQuantityOfMoney = user.getMoney() + quantity;
        user.setMoney(newQuantityOfMoney);
        return newQuantityOfMoney;
    }

    /**
     * Increases the user's gems by a specified amount.
     *
     * @param quantity The amount of gems to add to the user's current total.
     *                 The quantity must be greater than 0 to be valid.
     * @return The new total amount of gems the user has after the addition.
     * @throws InvalidQuantityOfGems If the specified quantity is less than or equal to zero,
     *                               indicating an attempt to add a non-positive amount of gems.
     */
    @Override
    public int updateGems(int quantity) throws InvalidQuantityOfGems {
        if (quantity <= 0) {
            throw new InvalidQuantityOfGems("The amount of gems you want to get should be greater than 0");
        }
        int newQuantityOfGems = user.getGems() + quantity;
        user.setGems(newQuantityOfGems);
        return newQuantityOfGems;
    }

    /**
     * Deducts a specified amount of money from the user's balance if valid.
     * @param quantity The amount of money to spend.
     * @return the remaining amount of money after the deduction.
     * @throws InvalidQuantityOfMoney if the specified quantity is zero or negative.
     * @throws ExceedingAmountOfMoney if the specified quantity exceeds the user's current balance.
     */
    @Override
    public int spendMoney(int quantity) throws InvalidQuantityOfMoney, ExceedingAmountOfMoney {
        if (quantity <= 0) {
            throw new InvalidQuantityOfMoney("The amount of money you want to spend should be greater than 0");
        }

        if (quantity > user.getMoney()) {
            throw new ExceedingAmountOfMoney("The amount of money you want to spend should not be larger than the money user have");
        }

        user.setMoney(user.getMoney() - quantity);
        return user.getMoney();
    }

    /**
     * Deducts a specified amount of gems from the user's balance if valid.
     * @param quantity The amount of gems to spend.
     * @return the remaining amount of gems after the deduction.
     * @throws InvalidQuantityOfGems if the specified quantity is zero or negative.
     * @throws ExceedingAmountOfGems if the specified quantity exceeds the user's current gem balance.
     */
    @Override
    public int spendGems(int quantity) throws ExceedingAmountOfGems, InvalidQuantityOfGems {
        if (quantity <= 0) {
            throw new InvalidQuantityOfGems("The amount of gems you want to spend should be greater than 0");
        }

        if (quantity > user.getGems()) {
            throw new ExceedingAmountOfGems("The amount of gems you want to spend should not be larger than the money user have");
        }

        user.setGems(user.getGems() - quantity);
        return user.getGems();
    }
}
