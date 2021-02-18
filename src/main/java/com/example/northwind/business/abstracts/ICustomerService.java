package com.example.northwind.business.abstracts;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.northwind.entities.concretes.Customer;

public interface ICustomerService {

	List<Customer> getAll();

	Customer add(Customer customer);

	Customer findByCustomer(String id);

	public String delete(Customer customer);

	Optional<Customer> findById(String customerId);

	Map<String, Boolean> getById(String customerId) throws Exception;

}
