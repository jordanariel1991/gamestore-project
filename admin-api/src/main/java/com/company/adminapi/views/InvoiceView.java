package com.company.adminapi.views;

import com.company.adminapi.views.InvoiceItemView;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class InvoiceView {

        private int id;
        private CustomerView customer;
        @NotNull(message = "need purchaseDate")
        private LocalDate purchaseDate;
        private List<InvoiceItemView> invoiceItemList;
        private int levelUpPoints;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CustomerView getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerView customer) {
        this.customer = customer;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public List<InvoiceItemView> getInvoiceItemList() {
        return invoiceItemList;
    }

    public void setInvoiceItemList(List<InvoiceItemView> invoiceItemList) {
        this.invoiceItemList = invoiceItemList;
    }

    public int getLevelUpPoints() {
        return levelUpPoints;
    }

    public void setLevelUpPoints(int levelUpPoints) {
        this.levelUpPoints = levelUpPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceView that = (InvoiceView) o;
        return id == that.id &&
                levelUpPoints == that.levelUpPoints &&
                Objects.equals(customer, that.customer) &&
                Objects.equals(purchaseDate, that.purchaseDate) &&
                Objects.equals(invoiceItemList, that.invoiceItemList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, purchaseDate, invoiceItemList, levelUpPoints);
    }
}


