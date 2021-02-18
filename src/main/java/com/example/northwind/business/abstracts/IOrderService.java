package com.example.northwind.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.example.northwind.api.controllers.dto.CheckoutCart;
import com.example.northwind.entities.concretes.Order;

public interface IOrderService {

	public List<Order> getAll();

	public Order add(Order order);

	public String delete(Order order);

	public Optional<Order> findById(int orderId);

	public Integer checkOutCart(int cartId, CheckoutCart request) throws Exception;

}
