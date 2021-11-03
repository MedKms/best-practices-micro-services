package com.org.billingservice.web;

import com.org.billingservice.dto.InvoiceRequestDTO;
import com.org.billingservice.dto.InvoiceRespenseDTO;
import com.org.billingservice.service.InvoiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/")
public class InvoiceRestApi {
    private InvoiceService invoiceService;

    public InvoiceRestApi(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }
    @GetMapping(path = "/invoices/{id}")
    public InvoiceRespenseDTO getInvoice(@PathVariable(name = "id") String invoiceId){
        return invoiceService.getInvoice(invoiceId);
    }
    @GetMapping(path = "/invoices/{customerId}")
    public List<InvoiceRespenseDTO> getInvoicesByCustomer(@PathVariable(name = "id") String customerId){
        return invoiceService.invoicesByCustomerId(customerId);
    }
    @PostMapping(path = "/invoices")
    public InvoiceRespenseDTO save(@RequestBody InvoiceRequestDTO invoiceRequestDTO){
        return invoiceService.save(invoiceRequestDTO);
    }
}
