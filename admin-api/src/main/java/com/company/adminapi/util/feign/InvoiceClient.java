package com.company.adminapi.util.feign;

import com.company.adminapi.DTO.Invoice;
import com.company.adminapi.views.InvoiceView;
import com.fasterxml.jackson.databind.util.ViewMatcher;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "invoice-service")
public interface InvoiceClient {

    @RequestMapping(value = "/invoices", method = RequestMethod.POST)
    InvoiceView addInvoice(@RequestBody Invoice invoice);

    @RequestMapping(value = "/invoices/{id}", method = RequestMethod.GET)
    InvoiceView getInvoiceById(@PathVariable int id);

    @RequestMapping(value = "/invoices", method = RequestMethod.GET)
    List<InvoiceView> getAllInvoices();

    @RequestMapping(value = "/invoices/customer/{id}", method = RequestMethod.GET)
    List<InvoiceView> getInvoicesByCustomerId(@PathVariable int id);

    @RequestMapping(value = "invoices/{id}", method = RequestMethod.DELETE)
    void deleteInvoice(@PathVariable int id);

//    @RequestMapping(value = "invoices/{id}", method = RequestMethod.PUT)
//    InvoiceView updateInvoice(@PathVariable int id);

    @RequestMapping(value = "invoices", method = RequestMethod.PUT)
    InvoiceView updateInvoice(InvoiceView invoice);
}
