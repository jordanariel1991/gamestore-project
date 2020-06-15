package com.company.adminapi.views;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceItemView {
    private int invoiceItemId;
    private int invoiceId;
    private int quantity;
    private InventoryView inventory;
    @NotNull(message = "must input unitprice")
    private BigDecimal unitPrice;

    public int getInvoiceItemId() {
        return invoiceItemId;
    }

    public void setInvoiceItemId(int invoiceItemId) {
        this.invoiceItemId = invoiceItemId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public InventoryView getInventory() {
        return inventory;
    }

    public void setInventory(InventoryView inventory) {
        this.inventory = inventory;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceItemView that = (InvoiceItemView) o;
        return invoiceItemId == that.invoiceItemId &&
                invoiceId == that.invoiceId &&
                quantity == that.quantity &&
                Objects.equals(inventory, that.inventory) &&
                Objects.equals(unitPrice, that.unitPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceItemId, invoiceId, quantity, inventory, unitPrice);
    }
}