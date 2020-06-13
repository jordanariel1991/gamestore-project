package com.company.Invoiceservice.Dao;

import com.company.Invoiceservice.models.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvoiceItemDaoJdbcTemplateImple implements InvoiceItemDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    InvoiceItemDaoJdbcTemplateImple(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String INSERT_INVOICE_ITEM =
            "insert into invoice_item (invoice_id, inventory_id, quantity, unit_price) values (?, ?, ?, ?)";
    private static final String SELECT_INVOICE_ITEM_BY_ID =
            "select * from invoice_item where invoice_item_id = ?";
    private static final String SELECT_ALL_INVOICE_ITEMS =
            "select * from invoice_item";
    private static final String UPDATE_INVOICE_ITEM =
            "update invoice_item set invoice_id = ?, inventory_id = ?, quantity = ?, unit_price = ? where invoice_id = ?";
    private static final String DELETE_INVOICE_ITEM =
            "delete from invoice_item where invoice_item_id = ?";
    private static final String SELECT_INVOICE_ITEM_BY_INVOICE_ID =
            "select * from invoice_item where invoice_id = ?";

    @Override
    public InvoiceItem findInvoiceItemById(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_INVOICE_ITEM_BY_ID, this::mapRowToInvoiceItem, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Transactional
    @Override
    public InvoiceItem addInvoiceItem(InvoiceItem invoiceitem) {
        jdbcTemplate.update(INSERT_INVOICE_ITEM,
                invoiceitem.getInvoiceId(),
                invoiceitem.getInventoryId(),
                invoiceitem.getQuantity(),
                invoiceitem.getUnitPrice());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        invoiceitem.setInvoiceItemId(id);
        return invoiceitem;
    }

    @Override
    public List<InvoiceItem> getAllInvoiceItems() {
        return jdbcTemplate.query(SELECT_ALL_INVOICE_ITEMS, this::mapRowToInvoiceItem);
    }

    @Override
    public void deleteInvoiceItemsById(int id) {
        jdbcTemplate.update(DELETE_INVOICE_ITEM, id);
    }

    @Override
    public void updateInvoiceItem(InvoiceItem invoiceItem) {
        jdbcTemplate.update(UPDATE_INVOICE_ITEM,
                invoiceItem.getInvoiceId(),
                invoiceItem.getInventoryId(),
                invoiceItem.getQuantity(),
                invoiceItem.getUnitPrice(),
                invoiceItem.getInvoiceItemId());
    }

    @Override
    public List<InvoiceItem> getItemsByInvoiceId(int invoiceId) {
        return jdbcTemplate.query(SELECT_INVOICE_ITEM_BY_INVOICE_ID, this::mapRowToInvoiceItem, invoiceId);
    }

    private InvoiceItem mapRowToInvoiceItem(ResultSet rs, int rowNum) throws SQLException {
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceItemId(rs.getInt("invoice_item_id"));
        invoiceItem.setInvoiceId(rs.getInt("invoice_id"));
        invoiceItem.setInventoryId(rs.getInt("inventory_id"));
        invoiceItem.setQuantity(rs.getInt("quantity"));
        invoiceItem.setUnitPrice(rs.getBigDecimal("unit_price"));
        return invoiceItem;
    }
}
