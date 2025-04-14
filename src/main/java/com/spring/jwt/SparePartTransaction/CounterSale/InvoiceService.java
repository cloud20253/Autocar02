package com.spring.jwt.SparePartTransaction.CounterSale;

import java.util.List;

public interface InvoiceService {
    Invoice saveInvoice(Invoice invoice);
    List<Invoice> getAllInvoices();

    Invoice getInvoiceById(Long id);

    List<Invoice> getInvoicesByDateRange(String fromDate, String toDate);
}

