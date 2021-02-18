package com.example.northwind.business.concretes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.northwind.business.abstracts.ICustomerService;
import com.example.northwind.dataaccess.concretes.CustomerRepository;
import com.example.northwind.entities.concretes.Customer;

import javassist.NotFoundException;
import lombok.SneakyThrows;

@Service
public class CustomerManager implements ICustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> getAll() {
		return customerRepository.findAll();
	}

	@Override
	public Customer add(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public String delete(Customer customer) {
		customerRepository.delete(customer);
		return customer + "silindi";
	}

	@Override
	public Optional<Customer> findById(String customerId) {
		return customerRepository.findById(customerId);
	}

	@Override
	public Map<String, Boolean> getById(String customerId) throws Exception {
		Customer customerGetById = customerRepository.findById(customerId)
				.orElseThrow(() -> new Exception("Bu kimliğe sahip müşteri yok : " + customerId));

		Map<String, Boolean> response = new HashMap<>();
		response.put(customerGetById + "  ID'si girilen müşterinin bilgileri getirildi.", Boolean.TRUE);
		return response;
	}

	@Override
	@SneakyThrows
	public Customer findByCustomer(String id) {
		return customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer not found"));
	}

}
