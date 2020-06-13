package com.company.Invoiceservice.viewmodels;

import com.company.Invoiceservice.models.InvoiceItem;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class InvoiceVM {

        private int invoiceId;
        private int customerId;
        @NotNull(message = "need purchaseDate")
        private LocalDate purchaseDate;
        private List<InvoiceItem> invItemList;

        public int getInvoiceId() {
            return invoiceId;
        }

        public void setInvoiceId(int invoiceId) {
            this.invoiceId = invoiceId;
        }

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public LocalDate getPurchaseDate() {
            return purchaseDate;
        }

        public void setPurchaseDate(LocalDate purchaseDate) {
            this.purchaseDate = purchaseDate;
        }

        public List<InvoiceItem> getInvItemList() {
            return invItemList;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            com.company.Invoiceservice.viewmodels.InvoiceVM invoiceVM = (com.company.Invoiceservice.viewmodels.InvoiceVM) o;
            return getInvoiceId() == invoiceVM.getInvoiceId() &&
                    getCustomerId() == invoiceVM.getCustomerId() &&
                    Objects.equals(getPurchaseDate(), invoiceVM.getPurchaseDate()) &&
                    Objects.equals(getInvItemList(), invoiceVM.getInvItemList());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getInvoiceId(), getCustomerId(), getPurchaseDate(), getInvItemList());
        }

        public void setInvItemList(List<InvoiceItem> invItemList) {
            this.invItemList = invItemList;
        }

        @Override
        public String toString() {
            return "InvoiceVM{" +
                    "invoiceId=" + invoiceId +
                    ", customerId=" + customerId +
                    ", purchaseDate=" + purchaseDate +
                    ", invItemList=" + invItemList +
                    '}';
        }
    }


