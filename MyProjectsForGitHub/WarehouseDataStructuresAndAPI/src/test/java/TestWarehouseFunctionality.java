import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.warehouse.exceptions.ExceedingCapacity;
import org.warehouse.exceptions.InvalidQuantity;
import org.warehouse.exceptions.MaterialAlreadyExists;
import org.warehouse.exceptions.MaterialNotFound;
import org.warehouse.management.WareHouse;
import org.warehouse.model.MaterialType;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestWarehouseFunctionality {
    public WareHouse warehouse;
    public MaterialType iron;
    public MaterialType coal;

    @BeforeEach
    void setUp() {
        warehouse = new WareHouse(new HashMap<>());
        iron = new MaterialType("Iron", "Used for construction", "src/main/resources/materials/iron.png", 500);
        coal = new MaterialType("Coal", "Used for energy production", "src/main/resources/materials/coal.png", 200);
    }

    @Test
    void testAddMaterialFailed() {
        assertThrows(MaterialAlreadyExists.class, () -> {
            warehouse.addMaterial(iron, 100);
            warehouse.addMaterial(iron, 100);
        });
    }

    @Test
    void testAddMaterialSuccess() throws ExceedingCapacity, MaterialAlreadyExists, InvalidQuantity, MaterialNotFound {
        warehouse.addMaterial(iron, 100);
        assertEquals(100, warehouse.listAllMaterials().get(iron));
    }

    @Test
    void testUpdateMaterialQuantityFailed() {
        assertThrows(MaterialNotFound.class, () -> warehouse.updateMaterialQuantity(iron, 100));
    }

    @Test
    void testUpdateMaterialQuantitySuccess() throws ExceedingCapacity, MaterialAlreadyExists, InvalidQuantity, MaterialNotFound {
        warehouse.addMaterial(iron, 100);
        warehouse.updateMaterialQuantity(iron, 200);
        assertEquals(300, warehouse.listAllMaterials().get(iron));
    }

    @Test
    void testCheckInvalidQuantity() {
        assertThrows(InvalidQuantity.class, () -> warehouse.checkInvalidQuantity(iron, 0));
        assertThrows(ExceedingCapacity.class, () -> warehouse.checkInvalidQuantity(iron, 1000));
    }

    @Test
    void testRemoveMaterialFailed() {
        Assertions.assertThrows(MaterialNotFound.class, () -> warehouse.removeMaterial(iron));
    }

    @Test
    void testRemoveMaterialSuccess() throws ExceedingCapacity, MaterialAlreadyExists, InvalidQuantity, MaterialNotFound {
        warehouse.addMaterial(iron, 100);
        warehouse.removeMaterial(iron);
    }

    @Test
    void testDropSomeQuantityFailed() {
        Assertions.assertThrows(MaterialNotFound.class, () -> warehouse.dropSomeQuantity(iron, 12));
    }

    @Test
    void testDropSomeQuantitySuccess() throws ExceedingCapacity, MaterialAlreadyExists, InvalidQuantity, MaterialNotFound {
        warehouse.addMaterial(iron, 100);
        warehouse.dropSomeQuantity(iron, 50);
        assertEquals(50, warehouse.listAllMaterials().get(iron));
    }

    @Test
    void testTransferFullQuantityMaterialV1() throws ExceedingCapacity, MaterialAlreadyExists, InvalidQuantity, MaterialNotFound {
        WareHouse otherWarehouse = new WareHouse(new HashMap<>());
        warehouse.addMaterial(iron, 100);
        warehouse.transferFullMaterial(otherWarehouse, iron);
        assertEquals(100, otherWarehouse.listAllMaterials().get(iron));
    }

    @Test
    void testTransferFullQuantityMaterialV2() throws ExceedingCapacity, MaterialAlreadyExists, InvalidQuantity, MaterialNotFound {
        WareHouse otherWarehouse = new WareHouse(new HashMap<>());
        warehouse.addMaterial(coal, 99);
        otherWarehouse.addMaterial(coal, 75);
        warehouse.transferFullMaterial(otherWarehouse, coal);
        assertEquals(174, otherWarehouse.listAllMaterials().get(coal));
    }

    @Test
    void testTransferSomeQuantityOfMaterial() throws ExceedingCapacity, MaterialAlreadyExists, InvalidQuantity, MaterialNotFound {
        WareHouse otherWarehouse = new WareHouse(new HashMap<>());
        warehouse.addMaterial(iron, 100);
        warehouse.transferSomeQuantityOfMaterial(otherWarehouse, iron, 50);
        assertEquals(50, warehouse.listAllMaterials().get(iron));
        assertEquals(50, otherWarehouse.listAllMaterials().get(iron));
    }

    @Test
    void testGetMaterialMaximumQuantityFailed() {
        Assertions.assertThrows(MaterialNotFound.class, () -> warehouse.getMaterialQuantity(warehouse, iron));
    }

    @Test
    void testGetMaterialMaximumQuantitySuccess() throws MaterialNotFound, ExceedingCapacity, MaterialAlreadyExists, InvalidQuantity {
        warehouse.addMaterial(iron, 345);
        assertEquals(345, warehouse.getMaterialQuantity(warehouse, iron));
    }

    @Test
    void testListAllMaterials() throws ExceedingCapacity, MaterialAlreadyExists, InvalidQuantity, MaterialNotFound {
        Assertions.assertTrue(warehouse.listAllMaterials().isEmpty());
        warehouse.addMaterial(iron, 100);
        Assertions.assertFalse(warehouse.listAllMaterials().isEmpty());
    }
}
