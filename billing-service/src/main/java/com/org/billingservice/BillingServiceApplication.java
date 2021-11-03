package com.org.billingservice;

import com.org.billingservice.dto.InvoiceRequestDTO;
import com.org.billingservice.service.InvoiceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(InvoiceService invoiceService){
        return args -> {
            invoiceService.save(new InvoiceRequestDTO( BigDecimal.valueOf(90000),"CO1"));
            invoiceService.save(new InvoiceRequestDTO( BigDecimal.valueOf(70000),"CO2"));
            invoiceService.save(new InvoiceRequestDTO( BigDecimal.valueOf(80000),"CO1"));
        };
    }
}
