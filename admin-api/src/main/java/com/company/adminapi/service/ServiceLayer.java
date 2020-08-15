package com.company.adminapi.service;

import com.company.adminapi.DTO.Invoice;
import com.company.adminapi.util.feign.*;
import com.company.adminapi.views.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceLayer {

    private InvoiceClient invoiceClient;
    private LvlClient levelClient;
    private InventoryClient inventoryClient;
    private ProdClient productClient;
    private CustClient customerClient;

    @Autowired
    public ServiceLayer(InvoiceClient invoiceClient, LvlClient levelClient, InventoryClient inventoryClient, ProdClient productClient, CustClient customerClient){
        this.invoiceClient = invoiceClient;
        this.levelClient = levelClient;
        this.inventoryClient = inventoryClient;
        this.productClient = productClient;
        this.customerClient = customerClient;

        //todo incorporate level up account creation here
    }

    public InventoryView newInventory(InventoryView inventoryView) {
        return inventoryClient.addInventory(inventoryView);
    }

    public InventoryView findInventoryById(int id) {
        return inventoryClient.getInventoryById(id);
    }

    public List<InventoryView> fetchAllInventory() {
        return inventoryClient.getAllInventory();
    }

    public void deleteInventory(int id) {
      inventoryClient.deleteInventory(id);
    }

    public void updateInventory(int id, InventoryView inventoryView) {
        inventoryClient.updateInventory(id, inventoryView);
    }


    public CustomerView newCustomer(CustomerView customerView) {
        return customerClient.createCustomer(customerView);
    }

    public CustomerView fetchCustomer(int id) {
        return customerClient.getById(id);
    }

    public List<CustomerView> fetchAllCustomers() {
        return customerClient.getAllCustomers();
    }

    public void updateCustomer(int id, CustomerView customerView) {
        customerClient.updateCustomer(id, customerView);
    }

    public void deleteCustomer(int id) {
        customerClient.deleteCustomer(id);
    }

    public ProductView newProduct(ProductView productView) {
       return productClient.createProduct(productView);
    }

    public ProductView fetchProduct(int id) {
        return productClient.getById(id);
    }

    public List<ProductView> fetchAllProducts() {
        return productClient.getAllProducts();
    }

    public void updateProduct(int id, ProductView productView) {
        productClient.updateProduct(id, productView);
    }

    public void deleteProduct(int id) {
        productClient.deleteProduct(id);
    }

    public LevelUpView addLevelUp(LevelUpView levelUpView) {
        return levelClient.addLevelUp(levelUpView);
    }

    public LevelUpView findLevelUpById(int id) {
        return levelClient.getLevelUpById(id);
    }

    public List<LevelUpView> getAllLevelUps() {
        return levelClient.getAllLevelUp();
    }

    public void deleteLevelUp(int id) {
        levelClient.deleteLevelUp(id);
    }

    public void updateLevelUp(int id, LevelUpView levelUpView) {
        levelClient.updateLevelUp(id, levelUpView);
    }

    public List<InvoiceView> getInvoiceByCustId(int custId) {
      return invoiceClient.getInvoicesByCustomerId(custId);
    }

    public void deleteInvoice(int id) {
        invoiceClient.deleteInvoice(id);
    }

    public InvoiceView addInvoice(Invoice invoice) {
       return invoiceClient.addInvoice(invoice);
    }

    public InvoiceView updateInvoice(InvoiceView invoiceView) {
        return invoiceClient.updateInvoice(invoiceView);
    }

    public List<InvoiceView> getAllInvoices() {
        return invoiceClient.getAllInvoices();
    }

    public InvoiceView getInvoice(int id) {
        return invoiceClient.getInvoiceById(id);
    }

    //todo need to incorporate methods for product/invoice endpoint. see the feign for productClient
}
