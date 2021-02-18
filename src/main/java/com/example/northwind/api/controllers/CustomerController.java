package com.example.northwind.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.northwind.business.abstracts.ICustomerService;
import com.example.northwind.entities.concretes.Customer;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

	@Autowired
	ICustomerService customerService;

	@GetMapping("/customers")
	public Map<String, Boolean> getAll() throws Exception {
		List<Customer> customerGetAll = customerService.getAll();
		Map<String, Boolean> response = new HashMap<>();

		response.put(customerGetAll + "Müşteriler listelendi.", Boolean.TRUE);
		return response;
	}

	@PostMapping("/customers")
	public Map<String, Boolean> add(@RequestBody Customer customer) throws Exception {
		Customer customerAdd = customerService.add(customer);
		Map<String, Boolean> response = new HashMap<>();
		if (customerAdd == null) {
			response.put("Müşteri eklenemedi.", Boolean.FALSE);
			return response;
		} else {
			response.put(customerAdd + "Müşteri eklendi.", Boolean.TRUE);
			return response;
		}
	}

	@DeleteMapping("/customers")
	public Map<String, Boolean> delete(@RequestBody Customer customer) throws Exception {
		Customer customerDelete = customerService.findById(customer.getId())
				.orElseThrow(() -> new Exception("Bu kimliğe sahip müşteri yok : " + customer.getId()));

		customerService.delete(customerDelete);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Müşteri silindi", Boolean.TRUE);
		return response;
	}

	@GetMapping("/customers/{id}")
	public Map<String, Boolean> getById(@PathVariable(value = "id") String customerId) throws Exception {
		Customer customerGetById = customerService.findByCustomer(customerId);

		Map<String, Boolean> response = new HashMap<>();
		response.put(customerGetById + "  ID'si girilen müşterinin bilgileri getirildi.", Boolean.TRUE);
		return response;
	}
}
