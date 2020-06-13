package com.company.Invoiceservice.serviceLayer;


import com.company.Invoiceservice.Dao.InvoiceDao;
import com.company.Invoiceservice.Dao.InvoiceItemDao;
import com.company.Invoiceservice.exceptions.NotFoundException;
import com.company.Invoiceservice.models.Invoice;
import com.company.Invoiceservice.models.InvoiceItem;
//import com.company.Invoiceservice.viewmodels.InvoiceVM;
import com.company.Invoiceservice.viewmodels.InvoiceVM;
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
    private Invoice VMtoModel(InvoiceVM InvoiceViewModel){
        Invoice inv =  new Invoice();
        inv.setInvoiceId(InvoiceViewModel.getInvoiceId());
        inv.setCustomerId(InvoiceViewModel.getCustomerId());
        inv.setPurchaseDate(InvoiceViewModel.getPurchaseDate());

        return inv;
    }

    private InvoiceVM buildInvoiceVM(Invoice inv){
        InvoiceVM InvoiceViewModel = new InvoiceVM();
        InvoiceViewModel.setInvoiceId(inv.getInvoiceId());
        InvoiceViewModel.setCustomerId(inv.getCustomerId());
        InvoiceViewModel.setPurchaseDate(inv.getPurchaseDate());
        InvoiceViewModel.setInvItemList(invItemDao.getItemsByInvoiceId(inv.getInvoiceId()));
        return InvoiceViewModel;
    }

    @Transactional
    public InvoiceVM addInvoice(InvoiceVM IVM) {
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
    public List<InvoiceVM>getInvoiceByCust(int custId){
        List<Invoice> invoiceList =invdao.getByCustomerId(custId);
        List<InvoiceVM> IVMList = new ArrayList<>();
        invoiceList.forEach(invoice -> IVMList.add(buildInvoiceVM(invoice)));
        return IVMList;

    }
    @Transactional
    public InvoiceVM getInv(int invId){
        Invoice inv = invdao.findInvoiceById(invId);
        if(inv==null){
            throw new NotFoundException("invoice not found");

        }else {
            return buildInvoiceVM(inv);
        }
    }
    @Transactional
    public InvoiceVM updateInv(InvoiceVM invoiceVM){
        //locate inv
        getInv(invoiceVM.getInvoiceId());
        //update inv
        invdao.updateInvoice(VMtoModel(invoiceVM));
        List<InvoiceItem>invoiceItemList = invItemDao.getItemsByInvoiceId(invoiceVM.getInvoiceId());
        invoiceItemList.forEach(invoiceItem -> invItemDao.deleteInvoiceItemsById(invoiceItem.getInvoiceItemId()));
        //add invitems
        invoiceVM.getInvItemList().forEach(invoiceItem -> {invoiceItem.setInvoiceId(invoiceVM.getInvoiceId());
        invItemDao.addInvoiceItem(invoiceItem);
        });
        return getInv(invoiceVM.getInvoiceId());

    }
    @Transactional
    public  List<InvoiceVM>getAllInv(){
        List<Invoice>invoiceList =invdao.getAllInvoice();
        List<InvoiceVM> invoiceVMList =new ArrayList<>();
        invoiceList.forEach(invoice -> invoiceVMList.add(buildInvoiceVM(invoice)));
        return invoiceVMList;
    }
    @Transactional
    public List<InvoiceVM> getInvByCustId(int custId){
        List<Invoice>invoiceList =invdao.getByCustomerId(custId);
        List<InvoiceVM>invoiceVMList = new ArrayList<>();
        invoiceList.forEach(invoice -> invoiceVMList.add(buildInvoiceVM(invoice)));
        return invoiceVMList;
    }
    @Transactional
    public void deleteInvoice (int invId){
        getInv(invId);
        List<InvoiceItem> invoiceItemList =invItemDao.getItemsByInvoiceId(invId);
        invoiceItemList.forEach(invoiceItem -> invItemDao.deleteInvoiceItemsById(invoiceItem.getInvoiceItemId()));
        invdao.DeleteInvoiceById(invId);
    }






}
