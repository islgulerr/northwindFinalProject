package com.example.northwind.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.example.northwind.entities.concretes.Cart;

public interface ICartService {

	List<Cart> getAll();

	public Cart add(Cart cart);

	public String delete(Cart cart);

	Optional<Cart> findById(Integer cartId);

	public Optional<Cart> findById(String customerId);

	Integer productQuantity(Integer productId, String customerId, int quantity);

	public double updateQuantity(Integer productId, Integer quantity, String customerId);

}
