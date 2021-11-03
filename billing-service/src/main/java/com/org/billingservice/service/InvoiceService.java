package com.org.billingservice.service;

import com.org.billingservice.dto.InvoiceRequestDTO;
import com.org.billingservice.dto.InvoiceRespenseDTO;

import java.util.List;

public interface InvoiceService {
    InvoiceRespenseDTO save(InvoiceRequestDTO invoiceRequestDTO);
    InvoiceRespenseDTO getInvoice(String id);
    List<InvoiceRespenseDTO> invoicesByCustomerId(String id);
}
