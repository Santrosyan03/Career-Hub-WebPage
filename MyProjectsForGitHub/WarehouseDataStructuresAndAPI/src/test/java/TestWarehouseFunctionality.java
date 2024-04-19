import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.warehouse.exceptions.ExceedingCapacity;
import org.warehouse.exceptions.InvalidQuantity;
import org.warehouse.exceptions.MaterialAlreadyExists;
import org.warehouse.exceptions.MaterialNotFound;
import org.warehouse.management.WareHouse;
import org.warehouse.model.material.Material;
import org.warehouse.model.material.MaterialType;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestWarehouseFunctionality {
    private WareHouse warehouse;
    private Material iron;
    private Material coal;


    @BeforeEach
    void setUp() {
        warehouse = new WareHouse(new HashMap<>());
        MaterialType ironType = new MaterialType("Iron", "Used for construction", "src/main/resources/materials/iron.png", 500);
        MaterialType coalType = new MaterialType("Coal", "Used for energy production", "src/main/resources/materials/coal.png", 200);
        iron = new Material(ironType, 0);
        coal = new Material(coalType, 0);
    }

    @Test
    void testAddMaterialFailed() {
        assertThrows(MaterialAlreadyExists.class, () -> {
            warehouse.addMaterial(iron.getMaterialType(), 100);
            warehouse.addMaterial(iron.getMaterialType(), 100);
        });
    }

    @Test
    void testAddMaterialSuccess() throws ExceedingCapacity, MaterialAlreadyExists, InvalidQuantity, MaterialNotFound {
        warehouse.addMaterial(iron.getMaterialType(), 100);
        assertEquals(100, warehouse.listAllMaterials().get(iron.getMaterialType()));
    }

    @Test
    void testUpdateMaterialQuantityFailed() {
        assertThrows(MaterialNotFound.class, () -> warehouse.updateMaterialQuantity(iron.getMaterialType(), 100));
    }

    @Test
    void testUpdateMaterialQuantitySuccess() throws ExceedingCapacity, MaterialAlreadyExists, InvalidQuantity, MaterialNotFound {
        warehouse.addMaterial(coal.getMaterialType(), 100);
        warehouse.updateMaterialQuantity(coal.getMaterialType(), 200);
        assertEquals(300, warehouse.listAllMaterials().get(coal.getMaterialType()));
    }

    @Test
    void testCheckInvalidQuantity() {
        assertThrows(InvalidQuantity.class, () -> warehouse.checkInvalidQuantity(iron.getMaterialType(), 0));
        assertThrows(ExceedingCapacity.class, () -> warehouse.checkInvalidQuantity(iron.getMaterialType(), 1000));
    }

    @Test
    void testRemoveMaterialFailed() {
        Assertions.assertThrows(MaterialNotFound.class, () -> warehouse.removeMaterial(iron.getMaterialType()));
    }

    @Test
    void testRemoveMaterialSuccess() throws ExceedingCapacity, MaterialAlreadyExists, InvalidQuantity, MaterialNotFound {
        warehouse.addMaterial(iron.getMaterialType(), 100);
        warehouse.removeMaterial(iron.getMaterialType());
    }

    @Test
    void testDropSomeQuantityFailed() {
        Assertions.assertThrows(MaterialNotFound.class, () -> warehouse.dropSomeQuantity(coal.getMaterialType(), 12));
    }

    @Test
    void testDropSomeQuantitySuccess() throws ExceedingCapacity, MaterialAlreadyExists, InvalidQuantity, MaterialNotFound {
        warehouse.addMaterial(iron.getMaterialType(), 100);
        warehouse.dropSomeQuantity(iron.getMaterialType(), 50);
        assertEquals(50, warehouse.listAllMaterials().get(iron.getMaterialType()));
    }

    @Test
    void testTransferFullQuantityMaterialV1() throws ExceedingCapacity, MaterialAlreadyExists, InvalidQuantity, MaterialNotFound {
        WareHouse otherWarehouse = new WareHouse(new HashMap<>());
        warehouse.addMaterial(coal.getMaterialType(), 100);
        warehouse.transferFullMaterial(otherWarehouse, coal.getMaterialType());
        assertEquals(100, otherWarehouse.listAllMaterials().get(coal.getMaterialType()));
    }

    @Test
    void testTransferFullQuantityMaterialV2() throws ExceedingCapacity, MaterialAlreadyExists, InvalidQuantity, MaterialNotFound {
        WareHouse otherWarehouse = new WareHouse(new HashMap<>());
        warehouse.addMaterial(coal.getMaterialType(), 99);
        otherWarehouse.addMaterial(coal.getMaterialType(), 75);
        warehouse.transferFullMaterial(otherWarehouse, coal.getMaterialType());
        assertEquals(174, otherWarehouse.listAllMaterials().get(coal.getMaterialType()));
    }

    @Test
    void testTransferSomeQuantityOfMaterial() throws ExceedingCapacity, MaterialAlreadyExists, InvalidQuantity, MaterialNotFound {
        WareHouse otherWarehouse = new WareHouse(new HashMap<>());
        warehouse.addMaterial(iron.getMaterialType(), 100);
        warehouse.transferSomeQuantityOfMaterial(otherWarehouse, iron.getMaterialType(), 50);
        assertEquals(50, warehouse.listAllMaterials().get(iron.getMaterialType()));
        assertEquals(50, otherWarehouse.listAllMaterials().get(iron.getMaterialType()));
    }

    @Test
    void testGetMaterialMaximumQuantityFailed() {
        Assertions.assertThrows(MaterialNotFound.class, () -> warehouse.getMaterialQuantity(warehouse, coal.getMaterialType()));
    }

    @Test
    void testGetMaterialMaximumQuantitySuccess() throws MaterialNotFound, ExceedingCapacity, MaterialAlreadyExists, InvalidQuantity {
        warehouse.addMaterial(iron.getMaterialType(), 345);
        assertEquals(345, warehouse.getMaterialQuantity(warehouse, iron.getMaterialType()));
    }

    @Test
    void testListAllMaterials() throws ExceedingCapacity, MaterialAlreadyExists, InvalidQuantity {
        Assertions.assertTrue(warehouse.listAllMaterials().isEmpty());
        warehouse.addMaterial(iron.getMaterialType(), 100);
        Assertions.assertFalse(warehouse.listAllMaterials().isEmpty());
    }

    @Test
    void upgradeMaterialQuantityFailed() {
        Assertions.assertThrows(MaterialNotFound.class, () -> warehouse.upgradeMaterialQuantity(iron.getMaterialType(), 100));
    }

    @Test
    void upgradeMaterialQuantitySuccess() throws MaterialNotFound, ExceedingCapacity, MaterialAlreadyExists, InvalidQuantity {
        warehouse.addMaterial(iron.getMaterialType(), 200);
        int updatedQuantity = warehouse.upgradeMaterialQuantity(iron.getMaterialType(), 100);
        assertEquals(300, updatedQuantity);
        assertEquals(300, warehouse.getMaterialQuantity(warehouse, iron.getMaterialType()));
    }
}
