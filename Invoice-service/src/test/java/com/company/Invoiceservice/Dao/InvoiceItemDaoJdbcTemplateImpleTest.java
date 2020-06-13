package com.company.Invoiceservice.Dao;

import com.company.Invoiceservice.models.Invoice;
import com.company.Invoiceservice.models.InvoiceItem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceItemDaoJdbcTemplateImpleTest {
    // dao impl
    @Autowired
    InvoiceItemDao dao;
    @Autowired
    InvoiceDao invoiceDao;

    InvoiceItem invoiceItem;
    List<InvoiceItem> invoiceItemList;
    List<Invoice> invoiceList;
    Invoice invoice;

    @Before
    public void setUp() throws Exception {
        // inst. test objects


        invoiceItemList = dao.getAllInvoiceItems();
        for (InvoiceItem i : invoiceItemList) {
            dao.deleteInvoiceItemsById(i.getInvoiceItemId());
        }

        invoiceList = invoiceDao.getAllInvoice();
        for (Invoice i : invoiceList) {
            invoiceDao.DeleteInvoiceById(i.getInvoiceId());

        }

        invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.of(2019, 12, 12));
        invoice = invoiceDao.addInvoice(invoice);

        invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(invoice.getInvoiceId());
        invoiceItem.setInventoryId(1);
        invoiceItem.setUnitPrice(new BigDecimal(19.99).setScale(2, BigDecimal.ROUND_CEILING));
        invoiceItem.setQuantity(1);
       // invoiceItem = dao.addInvoiceItem(invoiceItem);
    }

    @Test
    public void shouldAddGetDeleteInvoiceItems(){
        // arrange
        //act
        invoiceItem = dao.addInvoiceItem(invoiceItem);
        InvoiceItem invoiceItem1 = dao.findInvoiceItemById(invoiceItem.getInvoiceItemId());
        // assert
        assertEquals(invoiceItem1, invoiceItem);

        dao.deleteInvoiceItemsById(invoiceItem.getInvoiceItemId());
        invoiceItem1 = dao.findInvoiceItemById(invoiceItem.getInvoiceItemId());
        assertNull(invoiceItem1);
    }

    @Test
    public void shouldGetAllInvoiceItems() {
        // arrange
        //act
        invoiceItem = dao.addInvoiceItem(invoiceItem);

        // add a second book
        invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(invoice.getInvoiceId());
        invoiceItem.setInventoryId(1);
        invoiceItem.setUnitPrice(new BigDecimal(19.99).setScale(2, BigDecimal.ROUND_CEILING));
        invoiceItem.setQuantity(1);
        invoiceItem = dao.addInvoiceItem(invoiceItem);
        // list
        invoiceItemList = dao.getAllInvoiceItems();
        assertEquals(invoiceItemList.size(), 2);
    }

    @Test
    public void shouldUpdateInvoiceItem() {
        // arrange
        //act
//        invoiceItem = new InvoiceItem();
        invoiceItem = dao.addInvoiceItem(invoiceItem);
        invoiceItem.setInvoiceId(invoice.getInvoiceId());
        invoiceItem.setInventoryId(1);
        invoiceItem.setUnitPrice(new BigDecimal(19.99).setScale(2, BigDecimal.ROUND_CEILING));
        invoiceItem.setQuantity(22);
        invoiceItem = dao.addInvoiceItem(invoiceItem);
        InvoiceItem invoiceItem1 = dao.findInvoiceItemById(invoiceItem.getInvoiceItemId());
        assertEquals(invoiceItem1, invoiceItem);
    }

    @Test
    public void shouldGetAllInvoiceItemsByInvoiceId(){
        invoice = invoiceDao.addInvoice(invoice);
        invoiceItem = dao.addInvoiceItem(invoiceItem);

        assertEquals(dao.getItemsByInvoiceId(invoice.getInvoiceId()), 1);

    }

}