package com.company.Invoiceservice.serviceLayer;

import com.company.Invoiceservice.Dao.InvoiceDao;
import com.company.Invoiceservice.Dao.InvoiceDaoJdbcTemplateImpl;
import com.company.Invoiceservice.Dao.InvoiceItemDao;
import com.company.Invoiceservice.Dao.InvoiceItemDaoJdbcTemplateImple;
import com.company.Invoiceservice.models.Invoice;
import com.company.Invoiceservice.models.InvoiceItem;
import com.company.Invoiceservice.viewmodels.InvoiceVM;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class InvoiceServiceLayerTest {

    InvoiceServiceLayer service;
    InvoiceDao invoiceDao;
    InvoiceItemDao invoiceItemDao;

    @Before
    public void setUp() throws Exception {
        // fire up bookDaoMock
        setInvoiceDaoMock();
        setInvoiceItemDaoMock();

        service = new InvoiceServiceLayer(invoiceDao, invoiceItemDao);
    }

    private void setInvoiceDaoMock() {
        invoiceDao = mock(InvoiceDaoJdbcTemplateImpl.class);
        // book to create
        Invoice invoiceToCreate = new Invoice();
        invoiceToCreate.setCustomerId(1);
        invoiceToCreate.setPurchaseDate(LocalDate.of(2012, 12, 12));

        // book from DB
        Invoice fromDB = new Invoice();
        fromDB.setInvoiceId(1);
        fromDB.setCustomerId(1);
        fromDB.setPurchaseDate(LocalDate.of(2012, 12, 12));

        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(fromDB);

        // asssert
        doReturn(fromDB).when(invoiceDao).addInvoice(invoiceToCreate);
        doReturn(fromDB).when(invoiceDao).findInvoiceById(1);
        doReturn(invoiceList).when(invoiceDao).getAllInvoice();
    }

    private void setInvoiceItemDaoMock() {
        invoiceItemDao = mock(InvoiceItemDaoJdbcTemplateImple.class);
        // book to create
        InvoiceItem invoiceToCreate = new InvoiceItem();
        invoiceToCreate.setInvoiceId(1);
        invoiceToCreate.setInventoryId(1);
        invoiceToCreate.setUnitPrice(new BigDecimal(19.99).setScale(2, BigDecimal.ROUND_CEILING));
        invoiceToCreate.setQuantity(1);

        // book from DB
        InvoiceItem fromDB = new InvoiceItem();
        fromDB.setInvoiceItemId(1);
        fromDB.setInvoiceId(1);
        fromDB.setInventoryId(1);
        fromDB.setUnitPrice(new BigDecimal(19.99).setScale(2, BigDecimal.ROUND_CEILING));
        fromDB.setQuantity(1);


        List<InvoiceItem> invoiceItemList = new ArrayList<>();
        invoiceItemList.add(fromDB);

        // asssert
        doReturn(fromDB).when(invoiceItemDao).addInvoiceItem(invoiceToCreate);
        doReturn(fromDB).when(invoiceItemDao).findInvoiceItemById(1);
        doReturn(invoiceItemList).when(invoiceItemDao).getAllInvoiceItems();
    }

    @Test
    public void shouldCreateInvoice() {
        InvoiceItem invItem = new InvoiceItem();
        invItem.setInventoryId(1);
        invItem.setUnitPrice(new BigDecimal(19.99).setScale(2, BigDecimal.ROUND_CEILING));
        invItem.setQuantity(1);

        List<InvoiceItem> invoiceItemList = new ArrayList<>();
        invoiceItemList.add(invItem);

        InvoiceVM invoiceVM = new InvoiceVM();
        invoiceVM.setCustomerId(1);
        invoiceVM.setPurchaseDate(LocalDate.of(2012, 12, 12));
        invoiceVM.setInvItemList(invoiceItemList);

        InvoiceVM fromSL = service.addInvoice(invoiceVM);

        assertEquals(invoiceVM, fromSL);
    }
    @Test
    public void shouldFindInvoice() {

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceItemId(1);
        invoiceItem.setInvoiceId(1);
        invoiceItem.setInventoryId(1);
        invoiceItem.setUnitPrice(new BigDecimal(19.99).setScale(2, RoundingMode.CEILING));
        invoiceItem.setQuantity(1);
        List<InvoiceItem> invoiceItemList = new ArrayList<>();
        invoiceItemList.add(invoiceItem);

        InvoiceVM invoiceVM = new InvoiceVM();
        invoiceVM.setInvoiceId(1);
        invoiceVM.setCustomerId(1);
        invoiceVM.setPurchaseDate(LocalDate.of(2012, 12, 12));
        invoiceVM.setInvItemList(invoiceItemList);

        InvoiceVM fromSL = service.getInv(1);

        assertEquals(invoiceVM, fromSL);
    }

    @Test
    public void shouldFindAllInvoices() {
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceItemId(1);
        invoiceItem.setInvoiceId(1);
        invoiceItem.setInventoryId(1);
        invoiceItem.setUnitPrice(new BigDecimal(19.99).setScale(2, RoundingMode.CEILING));
        invoiceItem.setQuantity(1);
        List<InvoiceItem> invoiceItemList = new ArrayList<>();
        invoiceItemList.add(invoiceItem);

        InvoiceVM invoiceVM = new InvoiceVM();
        invoiceVM.setInvoiceId(1);
        invoiceVM.setCustomerId(1);
        invoiceVM.setPurchaseDate(LocalDate.of(2012, 12, 12));
        invoiceVM.setInvItemList(invoiceItemList);


        assertEquals(1, service.getAllInv().size());

    }


}