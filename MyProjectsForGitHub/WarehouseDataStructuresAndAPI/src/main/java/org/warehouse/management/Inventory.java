package org.warehouse.management;

import org.warehouse.exceptions.*;
import org.warehouse.model.MaterialType;

import java.util.List;
import java.util.Map;

public interface Inventory {
    MaterialType addMaterial(MaterialType type, int quantity) throws ExceedingCapacity, InvalidQuantity, MaterialAlreadyExists;
    void updateMaterialQuantity(MaterialType type, int quantity) throws ExceedingCapacity, InvalidQuantity, MaterialNotFound;
    MaterialType removeMaterial(MaterialType type) throws MaterialNotFound;
    int dropSomeQuantity(MaterialType type, int quantity) throws ExceedingCapacity, InvalidQuantity, MaterialNotFound;
    void transferFullMaterial(Inventory toWarehouse, MaterialType type) throws ExceedingCapacity, MaterialAlreadyExists, InvalidQuantity, MaterialNotFound;
    int transferSomeQuantityOfMaterial(Inventory toWarehouse, MaterialType type, int quantity) throws ExceedingCapacity, InvalidQuantity, MaterialAlreadyExists, MaterialNotFound;
    int getMaterialQuantity(MaterialType type) throws MaterialNotFound;
    Map<MaterialType, Integer> listAllMaterials() throws WarehouseIsEmpty;
}
