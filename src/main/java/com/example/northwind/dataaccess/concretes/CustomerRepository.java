package com.example.northwind.dataaccess.concretes;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.northwind.entities.concretes.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

	Customer findByCompanyName(String companyName);

	Optional<Customer> findById(String customerId);
}
