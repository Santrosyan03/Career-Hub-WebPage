package org.warehouse;

import org.warehouse.exceptions.*;
import org.warehouse.management.WareHouse;
import org.warehouse.model.MaterialType;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        WareHouse warehouse = new WareHouse(new HashMap<>());
        WareHouse otherWarehouse = new WareHouse(new HashMap<>());
        MaterialType iron = new MaterialType("Iron", "Used for construction", "iron.png", 500);
        MaterialType copper = new MaterialType("Copper", "Used in electrical cables", "copper.png", 300);

        try {
            // Add materials
            warehouse.addMaterial(iron, 100);
            System.out.println("Added 100 units of Iron");
            warehouse.addMaterial(copper, 150);
            System.out.println("Added 150 units of Copper");

            // Update material quantity
            warehouse.updateMaterialQuantity(iron, 50);
            System.out.println("Updated Iron quantity by 50 units");
            warehouse.updateMaterialQuantity(copper, 100);
            System.out.println("Updated Copper quantity by 100 units");

            // Transfer some quantity of Iron to another warehouse
            warehouse.transferSomeQuantityOfMaterial(otherWarehouse, iron, 5);
            System.out.println("Transferred 50 units of Iron to another warehouse");

            // Transfer full Copper to another warehouse
            warehouse.transferFullMaterial(otherWarehouse, copper);
            System.out.println("Transferred all Copper to another warehouse");

            // Drop some quantity from the remaining Iron in the other warehouse
            warehouse.dropSomeQuantity(iron, 60);
            System.out.println("Dropped 25 units of Iron from the other warehouse");

            // Remove Iron completely from the first warehouse
            warehouse.removeMaterial(iron);
            System.out.println("Removed all Iron from the first warehouse");

            // List all materials in both warehouses
            System.out.println("Materials in the first warehouse:");
            warehouse.listAllMaterials().forEach((type, qty) -> System.out.println(type.getName() + ": " + qty));
            System.out.println("Materials in the other warehouse:");
            otherWarehouse.listAllMaterials().forEach((type, qty) -> System.out.println(type.getName() + ": " + qty));

        } catch (MaterialAlreadyExists | MaterialNotFound | InvalidQuantity | ExceedingCapacity e) {
            e.printStackTrace();
        }
    }
}
