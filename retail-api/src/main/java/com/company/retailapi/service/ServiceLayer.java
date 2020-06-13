package com.company.retailapi.service;

import com.company.retailapi.model.Invoice;
import com.company.retailapi.model.LevelUp;
import com.company.retailapi.model.Product;
import com.company.retailapi.util.feign.*;
import com.company.retailapi.viewModel.CustomerView;
import com.company.retailapi.viewModel.InvoiceVM;
import com.company.retailapi.viewModel.LevelUpViewModel;
import com.company.retailapi.viewModel.ProductView;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceLayer {

    //private RabbitTemplate rabbitTemplate;
    private CustomerClient customerClient;
    private InventoryClient inventoryClient;
    private InvoiceClient invoiceClient;
    private LevelUpClient levelUpClient;
    private ProductClient productClient;


    //private static final String EXCHANGE = "whatever I call the exchange";
    // private static final String ROUTING_KEY = "routing";


    //could not autowire for some reason.
//    @Autowired
//    public ServiceLayer(CustomerClient customerClient, InventoryClient inventoryClient, InvoiceClient invoiceClient, LevelUpClient levelUpClient, ProductClient productClient) { //RabbitTemplate rabbitTemplate)
//        this.customerClient = customerClient;
//        this.inventoryClient = inventoryClient;
//        this.invoiceClient = invoiceClient;
//        this.levelUpClient = levelUpClient;
//        this.productClient = productClient;
//        // this.rabbitTemplate = rabbitTemplate;
//    }
    @Autowired
    public ServiceLayer(LevelUpClient levelUpClient) {
        this.levelUpClient = levelUpClient;
    }

    public LevelUpViewModel findLevelupByCustId(int id) {
        return LevelUpVMBuilder(levelUpClient.getLevelUpPointsByCustomerId(id));
    }



    //    public InvoiceVM findInvoice(int id){
//        return InvoiceVMBuilder
//    }
    //finding customer and product
    private CustomerView findCustomer(int id) {
        return customerClient.getCustomer(id);
    }

    private ProductView findProduct(int id) {
        return productClient.getProductById(id);
    }

    //viewmodel builders
    public LevelUpViewModel LevelUpVMBuilder(LevelUp level) {
        LevelUpViewModel levelUpViewModel = new LevelUpViewModel();
        levelUpViewModel.setLevelupId(level.getLevelupId());
        levelUpViewModel.setMemberDate(level.getMemberDate());
        levelUpViewModel.setPoint(level.getPoint());

        levelUpViewModel.setCustomer(findCustomer(level.getCustomerId()));
        return levelUpViewModel;

    }
//    public InvoiceVM InvoiceVMBuilder(Invoice inv){
//        InvoiceVM invoiceVM = new InvoiceVM();
//        List<Product> invoiceItemList = new ArrayList<>();
//
//        inv.get
//    }


}
