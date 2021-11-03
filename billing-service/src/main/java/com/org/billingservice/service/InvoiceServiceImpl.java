package com.org.billingservice.service;

import com.org.billingservice.dto.InvoiceRequestDTO;
import com.org.billingservice.dto.InvoiceRespenseDTO;
import com.org.billingservice.entities.Customer;
import com.org.billingservice.entities.Invoice;
import com.org.billingservice.mappers.InvoiceMapper;
import com.org.billingservice.openfeign.CustomerRestClient;
import com.org.billingservice.repository.InvoiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {
    private InvoiceRepository invoiceRepository;
    private InvoiceMapper invoiceMapper;
    private CustomerRestClient customerRestClient;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper, CustomerRestClient customerRestClient) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.customerRestClient = customerRestClient;
    }

    @Override
    public InvoiceRespenseDTO save(InvoiceRequestDTO invoiceRequestDTO) {
        Invoice invoice=invoiceMapper.frominvoiceRequesDTO(invoiceRequestDTO);
        invoice.setId(UUID.randomUUID().toString());
        invoice.setDate(new Date());
        /*
        Verification de l'intergrite referentiel de Invoice/Customer
        */
        Invoice savedInvoice=invoiceRepository.save(invoice);
        return invoiceMapper.fromInvoice(savedInvoice);
    }

    @Override
    public InvoiceRespenseDTO getInvoice(String invoiceId) {
        Invoice invoice=invoiceRepository.findById(invoiceId).get();
        Customer customer=customerRestClient.getCustomer(invoice.getCustomerId());
        invoice.setCustomer(customer);
        return invoiceMapper.fromInvoice(invoice);
    }

    @Override
    public List<InvoiceRespenseDTO> invoicesByCustomerId(String customerId) {
        List<Invoice> invoices=invoiceRepository.findByCustomerId(customerId);
        return invoices
                .stream()
                    .map(invoice -> invoiceMapper.fromInvoice(invoice))
                        .collect(Collectors.toList());
    }
}
