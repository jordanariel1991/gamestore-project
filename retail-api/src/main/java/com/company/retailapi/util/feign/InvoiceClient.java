package com.company.retailapi.util.feign;

import com.company.retailapi.model.Invoice;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "invoice-client")
public interface InvoiceClient {

    @RequestMapping(value = "/invoices/{id}", method = RequestMethod.GET)
    Invoice getInvoiceById(@PathVariable int id);

    @RequestMapping(value = "/invoices", method = RequestMethod.GET)
    List<Invoice> getAllInvoices();

    @RequestMapping(value = "/invoices/customer/{id}", method = RequestMethod.GET)
    List<Invoice> getInvoicesByCustomerId(@PathVariable int id);
}
