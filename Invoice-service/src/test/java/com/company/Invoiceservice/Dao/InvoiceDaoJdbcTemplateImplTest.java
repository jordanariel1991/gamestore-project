package com.company.Invoiceservice.Dao;

import com.company.Invoiceservice.models.Invoice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest

public class InvoiceDaoJdbcTemplateImplTest {

    @Autowired
    InvoiceDao dao;

    @Autowired
    InvoiceItemDao itemDao;

    Invoice invoice;
    List<Invoice> invoiceList;

    @Before
    public void setUp() throws Exception {
        invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.of(2019, 12, 12));

        itemDao.getAllInvoiceItems().forEach(item -> itemDao.deleteInvoiceItemsById(item.getInvoiceItemId()));

        invoiceList = dao.getAllInvoice();
        for (Invoice i : invoiceList) {
            dao.DeleteInvoiceById(i.getInvoiceId());
        }
    }


    @Test
    public void shouldAddGetDeleteInvoice() {
        invoice = dao.addInvoice(invoice);
        Invoice invoice1 = dao.findInvoiceById(invoice.getInvoiceId());

        assertEquals(invoice1, invoice);

        dao.DeleteInvoiceById(invoice.getInvoiceId());
        invoice1 = dao.findInvoiceById(invoice.getInvoiceId());
        assertNull(invoice1);
    }

    @Test
    public void shouldGetAllInvoice() {
        invoice = dao.addInvoice(invoice);
        invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.of(2012, 12, 31));
        invoice = dao.addInvoice(invoice);

        invoiceList = dao.getAllInvoice();
        assertEquals(invoiceList.size(), 2);
    }


    @Test
    public void updateInvoice() {
        invoice = dao.addInvoice(invoice);
        invoice.setCustomerId(2);
        invoice.setPurchaseDate(LocalDate.of(2009, 10, 26));
        invoice = dao.addInvoice(invoice);
        Invoice invoice1 = dao.findInvoiceById(invoice.getInvoiceId());
        assertEquals(invoice1, invoice);
    }

    @Test
    public void shouldGetByCustomerId() {
        invoice = dao.addInvoice(invoice);
        invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.of(2012, 12, 31));
        invoice = dao.addInvoice(invoice);

        invoiceList = dao.getByCustomerId(1);
        assertEquals(invoiceList.size(), 2);

    }
}