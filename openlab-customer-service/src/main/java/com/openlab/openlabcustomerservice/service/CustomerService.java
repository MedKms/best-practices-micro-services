package com.openlab.openlabcustomerservice.service;

import com.openlab.openlabcustomerservice.dto.CustomerRequestDTO;
import com.openlab.openlabcustomerservice.dto.CustomerResponseDTO;

import java.util.List;

public interface CustomerService {
    CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO);
    CustomerResponseDTO getCustomer(String id);
    CustomerResponseDTO update(CustomerRequestDTO customerRequestDTO);
    CustomerResponseDTO delete(CustomerRequestDTO customerRequestDTO);
    List<CustomerResponseDTO> listCustomers();
}
