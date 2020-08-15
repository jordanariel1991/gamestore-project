package com.company.Invoiceservice.serviceLayer;


import com.company.Invoiceservice.Dao.InvoiceDao;
import com.company.Invoiceservice.Dao.InvoiceItemDao;
import com.company.Invoiceservice.exceptions.NotFoundException;
import com.company.Invoiceservice.models.Invoice;
import com.company.Invoiceservice.models.InvoiceItem;
//import com.company.Invoiceservice.viewmodels.InvoiceVM;
import com.company.Invoiceservice.viewmodels.InvoiceView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class InvoiceServiceLayer {

    InvoiceDao invdao;
    InvoiceItemDao invItemDao;

    @Autowired
    public InvoiceServiceLayer(InvoiceDao invdao, InvoiceItemDao invItemDao) {
        this.invdao =invdao;
        this.invItemDao =invItemDao;
    }


    //helper method
    private Invoice VMtoModel(InvoiceView InvoiceView){
        Invoice inv =  new Invoice();
        inv.setInvoiceId(InvoiceView.getInvoiceId());
        inv.setCustomerId(InvoiceView.getCustomerId());
        inv.setPurchaseDate(InvoiceView.getPurchaseDate());

        return inv;
    }

    private InvoiceView buildInvoiceVM(Invoice inv){
        InvoiceView InvoiceView = new InvoiceView();
        InvoiceView.setInvoiceId(inv.getInvoiceId());
        InvoiceView.setCustomerId(inv.getCustomerId());
        InvoiceView.setPurchaseDate(inv.getPurchaseDate());
        InvoiceView.setInvItemList(invItemDao.getItemsByInvoiceId(inv.getInvoiceId()));
        return InvoiceView;
    }

    @Transactional
    public InvoiceView addInvoice(InvoiceView IVM) {
        Invoice inv = VMtoModel(IVM);
        inv = invdao.addInvoice(inv);
        IVM.setInvoiceId(inv.getInvoiceId());
        IVM.getInvItemList().forEach(invoiceItem -> {
            invoiceItem.setInvoiceId(IVM.getInvoiceId());
            invItemDao.addInvoiceItem(invoiceItem);
        });
        return IVM;
    }
    @Transactional
    public List<InvoiceView>getInvoiceByCust(int custId){
        List<Invoice> invoiceList =invdao.getByCustomerId(custId);
        List<InvoiceView> IVMList = new ArrayList<>();
        invoiceList.forEach(invoice -> IVMList.add(buildInvoiceVM(invoice)));
        return IVMList;

    }
    @Transactional
    public InvoiceView getInv(int invId){
        Invoice inv = invdao.findInvoiceById(invId);
        if(inv==null){
            throw new NotFoundException("invoice not found");

        }else {
            return buildInvoiceVM(inv);
        }
    }
    @Transactional
    public InvoiceView updateInv(InvoiceView invoiceView){
        //locate inv
        getInv(invoiceView.getInvoiceId());
        //update inv
        invdao.updateInvoice(VMtoModel(invoiceView));
        List<InvoiceItem>invoiceItemList = invItemDao.getItemsByInvoiceId(invoiceView.getInvoiceId());
        invoiceItemList.forEach(invoiceItem -> invItemDao.deleteInvoiceItemsById(invoiceItem.getInvoiceItemId()));
        //add invitems
        invoiceView.getInvItemList().forEach(invoiceItem -> {invoiceItem.setInvoiceId(invoiceView.getInvoiceId());
        invItemDao.addInvoiceItem(invoiceItem);
        });
        return getInv(invoiceView.getInvoiceId());

    }
    @Transactional
    public  List<InvoiceView>getAllInv(){
        List<Invoice>invoiceList =invdao.getAllInvoice();
        List<InvoiceView> invoiceViewList =new ArrayList<>();
        invoiceList.forEach(invoice -> invoiceViewList.add(buildInvoiceVM(invoice)));
        return invoiceViewList;
    }
    @Transactional
    public List<InvoiceView> getInvByCustId(int custId){
        List<Invoice>invoiceList =invdao.getByCustomerId(custId);
        List<InvoiceView> invoiceViewList = new ArrayList<>();
        invoiceList.forEach(invoice -> invoiceViewList.add(buildInvoiceVM(invoice)));
        return invoiceViewList;
    }
    @Transactional
    public void deleteInvoice (int invId){
        getInv(invId);
        List<InvoiceItem> invoiceItemList =invItemDao.getItemsByInvoiceId(invId);
        invoiceItemList.forEach(invoiceItem -> invItemDao.deleteInvoiceItemsById(invoiceItem.getInvoiceItemId()));
        invdao.DeleteInvoiceById(invId);
    }






}
