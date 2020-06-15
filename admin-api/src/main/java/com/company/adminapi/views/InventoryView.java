package com.company.adminapi.views;

import java.util.Objects;

public class InventoryView {
    // should there be JSR303 on these? what about on the view model in the inventory backing service?
    private  int inventoryId;
    private ProductView product;
    private int quantity;

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public ProductView getProduct() {
        return product;
    }

    public void setProduct(ProductView product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryView that = (InventoryView) o;
        return inventoryId == that.inventoryId &&
                quantity == that.quantity &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventoryId, product, quantity);
    }
}
