package com.company.Invoiceservice.Dao;

import com.company.Invoiceservice.models.InvoiceItem;

import java.util.List;

public interface InvoiceItemDao {

    InvoiceItem findInvoiceItemById(int id);

    InvoiceItem addInvoiceItem(InvoiceItem invoiceitem);

    List<InvoiceItem> getAllInvoiceItems();

    void deleteInvoiceItemsById(int id);

    void updateInvoiceItem(InvoiceItem invoiceItem);

    List<InvoiceItem> getItemsByInvoiceId(int invoiceId);
}
