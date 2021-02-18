package com.example.northwind.dataaccess.concretes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.northwind.entities.concretes.OrderDetail;
import com.example.northwind.entities.concretes.OrderDetailIDUsingEmbeddable;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailIDUsingEmbeddable> {

}
