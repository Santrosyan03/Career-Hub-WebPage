import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.warehouse.exceptions.ExceedingAmountOfGems;
import org.warehouse.exceptions.ExceedingAmountOfMoney;
import org.warehouse.exceptions.InvalidQuantityOfGems;
import org.warehouse.exceptions.InvalidQuantityOfMoney;
import org.warehouse.management.WareHouse;
import org.warehouse.model.user.User;
import org.warehouse.model.user.UserFunctionalities;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestUserFunctionality {
    private UserFunctionalities functionalities;
    private User user;

    //in chatgpt continue from step 2;

    @BeforeEach
    void setUp() {
        WareHouse wareHouse = new WareHouse(new HashMap<>());
        user = new User("User1", wareHouse, 5, 100, 45);
        functionalities = new UserFunctionalities(user);
    }

    @Test
    void testLogIn() throws InterruptedException {
        assertEquals("Logged in", functionalities.logIn());
    }

    @Test
    void testLogOut() throws InterruptedException {
        assertEquals("Logged out", functionalities.logOut());
    }

    @Test
    void testUpgradeLevel() {
        assertEquals(45, user.getLevel());
        user.setLevel();
        assertEquals(46, user.getLevel());
    }

    @Test
    void testUpdateMoneyFailed() {
        assertThrows(InvalidQuantityOfMoney.class, () -> functionalities.updateMoney(0));
    }

    @Test
    void testUpdateMoneySuccess() throws InvalidQuantityOfMoney {
        assertEquals(1100, functionalities.updateMoney(1000));
    }

    @Test
    void testUpdateGemsFailed() {
        assertThrows(InvalidQuantityOfGems.class, () -> functionalities.updateGems(0));
    }

    @Test
    void testUpdateGemsSuccess() throws InvalidQuantityOfGems {
        assertEquals(40, functionalities.updateGems(35));
    }

    @Test
    void testSpendMoneyFailed() {
        Assertions.assertThrows(InvalidQuantityOfMoney.class, () -> functionalities.spendMoney(0));
        Assertions.assertThrows(ExceedingAmountOfMoney.class, () -> functionalities.spendMoney(150));
    }

    @Test
    void testSpendMoneySuccess() throws InvalidQuantityOfMoney, ExceedingAmountOfMoney {
        functionalities.updateMoney(500);
        int remaining = functionalities.spendMoney(10);
        assertEquals(590, remaining);
    }


    @Test
    void testSpendGemsFailed() {
        Assertions.assertThrows(InvalidQuantityOfGems.class, () -> functionalities.spendGems(0));
        Assertions.assertThrows(ExceedingAmountOfGems.class, () -> functionalities.spendGems(15000));
    }

    @Test
    void testSpendGemsSuccess() throws InvalidQuantityOfGems, ExceedingAmountOfGems {
        functionalities.updateGems(350);
        int remaining = functionalities.spendGems(150);
        assertEquals(205, remaining);
    }
}
