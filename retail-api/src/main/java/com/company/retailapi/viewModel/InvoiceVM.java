package com.company.retailapi.viewModel;

//import com.company.Invoiceservice.models.InvoiceItem;
import com.company.retailapi.model.InvoiceItem;
import com.company.retailapi.model.Product;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class InvoiceVM {

        private int invoiceId;
        private CustomerView customer;
        @NotNull(message = "need purchaseDate")
        private LocalDate purchaseDate;
//        private List<Product> invItemList;
//        private String points;

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
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

//    public List<Product> getInvItemList() {
//        return invItemList;
//    }

//    public String getPoints() {
//        return points;
//    }
//
//    public void setPoints(String points) {
//        this.points = points;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceVM invoiceVM = (InvoiceVM) o;
        return invoiceId == invoiceVM.invoiceId &&
                Objects.equals(customer, invoiceVM.customer) &&
                Objects.equals(purchaseDate, invoiceVM.purchaseDate) ;
//                &&
//                Objects.equals(invItemList, invoiceVM.invItemList) &&
//                Objects.equals(points, invoiceVM.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId, customer, purchaseDate
//                , invItemList, points
              );
    }

}


