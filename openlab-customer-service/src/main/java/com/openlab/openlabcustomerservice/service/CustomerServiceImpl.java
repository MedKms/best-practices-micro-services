package com.openlab.openlabcustomerservice.service;

import com.openlab.openlabcustomerservice.dto.CustomerRequestDTO;
import com.openlab.openlabcustomerservice.dto.CustomerResponseDTO;
import com.openlab.openlabcustomerservice.entities.Customer;
import com.openlab.openlabcustomerservice.mappers.CustomerMapper;
import com.openlab.openlabcustomerservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO) {
        Customer customer=customerMapper.customerResponseDTOToCustomer(customerRequestDTO);
        Customer savedCustomer=customerRepository.save(customer);
        CustomerResponseDTO customerResponseDTO=customerMapper.customerToCustomerResponseDTO(savedCustomer);
        return customerResponseDTO;
    }

    @Override
    public CustomerResponseDTO getCustomer(String id) {
        Customer customer=customerRepository.findById(id).get();
        return customerMapper.customerToCustomerResponseDTO(customer);
    }

    @Override
    public CustomerResponseDTO update(CustomerRequestDTO customerRequestDTO) {
        Customer customer=customerMapper.customerResponseDTOToCustomer(customerRequestDTO);
        Customer editedCustomer=customerRepository.save(customer);
        return customerMapper.customerToCustomerResponseDTO(editedCustomer);
    }

    @Override
    public CustomerResponseDTO delete(CustomerRequestDTO customerRequestDTO) {
        return null;
    }

    @Override
    public List<CustomerResponseDTO> listCustomers() {
        List<Customer> customerList=customerRepository.findAll();
        List<CustomerResponseDTO> customerResponseDTOList=customerList
                .stream()
                .map(customer -> customerMapper.customerToCustomerResponseDTO(customer))
                .collect(Collectors.toList());
        return customerResponseDTOList;
    }
}
