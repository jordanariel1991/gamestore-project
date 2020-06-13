package com.company.adminapi.util.feign;

import com.company.adminapi.DTO.Invoice;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "invoice-service")
public interface InvoiceClient {

    @RequestMapping(value = "/invoices", method = RequestMethod.POST)
    public Invoice submitInvoice(@RequestBody Invoice invoice);

    @RequestMapping(value = "/invoices/{id}", method = RequestMethod.GET)
    public Invoice getInvoiceById(@PathVariable int id);

    @RequestMapping(value = "/invoices", method = RequestMethod.GET)
    public List<Invoice> getAllInvoices();

    @RequestMapping(value = "/invoices/customer/{id}", method = RequestMethod.GET)
    public List<Invoice> getInvoicesByCustomerId(@PathVariable int id);
}
