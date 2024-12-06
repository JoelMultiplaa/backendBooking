package com.example.backendbooking2.Service;

import com.example.backendbooking2.Entity.Customer;
import com.example.backendbooking2.Repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    public Optional<Customer> getAllCustomerById(Long id){
        return customerRepository.findById(id);
    }

    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer updateCustomer){
        Customer existCustomer = customerRepository.findById(id)
                .orElseThrow( () -> new RuntimeException(""));

        existCustomer.setName(updateCustomer.getName());
        existCustomer.setCity(updateCustomer.getCity());
        existCustomer.setEmail(updateCustomer.getEmail());
        existCustomer.setLicensePlate(updateCustomer.getLicensePlate());
        existCustomer.setPhoneNumber(updateCustomer.getPhoneNumber());

        return customerRepository.save(existCustomer);
    }

    public void deleteCustomer(Long ID){
        customerRepository.deleteById(ID);
    }


}
