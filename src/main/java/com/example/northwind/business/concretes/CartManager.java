package com.example.northwind.business.concretes;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.northwind.business.abstracts.ICartService;
import com.example.northwind.dataaccess.concretes.CartRepository;
import com.example.northwind.dataaccess.concretes.ProductRepository;
import com.example.northwind.entities.concretes.Cart;
import com.example.northwind.entities.concretes.Product;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class CartManager implements ICartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Cart> getAll() {

		return cartRepository.findAll();
	}

	@Override
	public Optional<Cart> findById(Integer id) {
		return cartRepository.findById(id);
	}

	@Override
	public Cart add(Cart cart) {
		return cartRepository.save(cart);
	}

	@Override
	public String delete(Cart cart) {
		cartRepository.delete(cart);
		return cart + "silindi";
	}

	@Override
	public Optional<Cart> findById(String customerId) {
		return cartRepository.findById(customerId);
	}

	@Override
	public Integer productQuantity(Integer productId, String customerId, int quantity) {
		Integer addedQuantity = quantity;
		Cart userCart = cartRepository.findByCustomerIdAndProductId(customerId, productId);
		Product product = productRepository.getOne(productId);

		double totalPrice = product.getUnitPrice();

		if (userCart != null) {
			addedQuantity = userCart.getQuantity() + quantity;
			totalPrice = totalPrice * addedQuantity;
			userCart.setQuantity(addedQuantity);
			userCart.setTotalPrice(totalPrice);
		} else {
			userCart = new Cart();
			userCart.setQuantity(quantity);
			userCart.setCustomerId(customerId);
			userCart.setProductId(productId);
			userCart.setTotalPrice(totalPrice * quantity);
		}

		cartRepository.save(userCart);
		return addedQuantity;
	}

	@Override
	public double updateQuantity(Integer productId, Integer quantity, String customerId) {
		cartRepository.updateQuantity(quantity, productId, customerId);
		Product product = productRepository.getOne(productId);
		Cart userCart = cartRepository.findByCustomerIdAndProductId(customerId, productId);
		double totalPrice = product.getUnitPrice() * quantity;
		userCart.setTotalPrice(totalPrice);
		return totalPrice;

	}

}
