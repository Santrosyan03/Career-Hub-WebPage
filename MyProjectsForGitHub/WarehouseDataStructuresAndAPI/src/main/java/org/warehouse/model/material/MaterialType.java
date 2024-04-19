package org.warehouse.model.material;

public class MaterialType {
    private String name;
    private String description;
    private String icon;
    private int maximumCapacity;

    public MaterialType(String name, String description, String icon, int maximumCapacity) {
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.maximumCapacity = maximumCapacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getMaximumCapacity() {
        return maximumCapacity;
    }

    public void setMaximumCapacity(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
    }
}
