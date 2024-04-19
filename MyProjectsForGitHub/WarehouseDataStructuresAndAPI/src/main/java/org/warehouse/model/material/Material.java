package org.warehouse.model.material;

public class Material {
    private MaterialType materialType;
    private int quantity;

    public Material(MaterialType materialType, int quantity) {
        this.materialType = materialType;
        this.quantity = quantity;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
