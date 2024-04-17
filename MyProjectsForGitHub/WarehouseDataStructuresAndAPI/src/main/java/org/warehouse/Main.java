package org.warehouse;

import org.warehouse.management.WareHouse;
import org.warehouse.model.MaterialType;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws Exception {
        WareHouse wareHouse = new WareHouse(new HashMap<>());
        MaterialType materialType = new MaterialType("type1", "des1", "icon1", 10000);
        MaterialType materialType2 = new MaterialType("type2", "des2", "icon2", 10000);
        wareHouse.addMaterial(materialType, 989);
        wareHouse.updateMaterialQuantity(materialType, 99);
        wareHouse.addMaterial(materialType2, 989);
        wareHouse.removeMaterial(materialType);
        WareHouse wareHouse2 = new WareHouse(new HashMap<>());
        wareHouse.transferFullMaterial(wareHouse2, materialType2);

    }
}