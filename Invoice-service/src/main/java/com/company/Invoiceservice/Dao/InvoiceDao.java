package com.company.Invoiceservice.Dao;

import com.company.Invoiceservice.models.Invoice;

import java.util.List;

public interface InvoiceDao {

    Invoice findInvoiceById(int id);

    Invoice addInvoice(Invoice invoice);

    List<Invoice>getAllInvoice();

    void DeleteInvoiceById(int id);

    void updateInvoice(Invoice invoice);

    List<Invoice> getByCustomerId(int customerId);


}
