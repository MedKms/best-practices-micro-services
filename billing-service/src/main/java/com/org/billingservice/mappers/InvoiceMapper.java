package com.org.billingservice.mappers;

import com.org.billingservice.dto.InvoiceRequestDTO;
import com.org.billingservice.dto.InvoiceRespenseDTO;
import com.org.billingservice.entities.Invoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    Invoice frominvoiceRequesDTO (InvoiceRequestDTO invoiceRequestDTO);
    InvoiceRespenseDTO fromInvoice(Invoice invoice);
}
