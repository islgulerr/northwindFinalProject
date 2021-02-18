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

import com.example.northwind.api.controllers.dto.CheckoutCart;
import com.example.northwind.business.abstracts.ICartService;
import com.example.northwind.business.abstracts.IOrderService;
import com.example.northwind.entities.concretes.Cart;
import com.example.northwind.entities.concretes.Order;

@RestController
@RequestMapping("/api/v1")
public class OrdersController {

	@Autowired
	private ICartService cartService;

	@Autowired
	private IOrderService orderService;

	@GetMapping("/orders")
	public Map<String, Boolean> getAll() throws Exception {
		List<Order> orderGetAll = orderService.getAll();
		Map<String, Boolean> response = new HashMap<>();

		response.put(orderGetAll + "Siparişler listelendi.", Boolean.TRUE);
		return response;
	}

	@PostMapping("/orders")
	public Map<String, Boolean> add(@RequestBody Order order) throws Exception {
		Order orderAdd = orderService.add(order);
		Map<String, Boolean> response = new HashMap<>();

		response.put(orderAdd + "Sipariş eklendi.", Boolean.TRUE);
		return response;

	}

	@DeleteMapping("/orders")
	public Map<String, Boolean> delete(@RequestBody Order order) throws Exception {
		Order orderDelete = orderService.findById(order.getId())
				.orElseThrow(() -> new Exception("Bu kimliğe sahip sipariş yok : " + order.getId()));

		orderService.delete(orderDelete);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Sipariş silindi", Boolean.TRUE);
		return response;
	}

	@GetMapping("/orders/{id}")
	public Map<String, Boolean> getById(@PathVariable(value = "id") Integer orderId) throws Exception {
		Order orderGetById = orderService.findById(orderId)
				.orElseThrow(() -> new Exception("Bu kimliğe sahip sipariş bilgisi yok : " + orderId));

		Map<String, Boolean> response = new HashMap<>();
		response.put(orderGetById + "ID'si girilen sipariş bilgileri getirildi.", Boolean.TRUE);
		return response;
	}

	@PostMapping("/orders/{cid}")
	public Map<String, Boolean> finishPurchase(@PathVariable(value = "cid") Integer cartId,
			@RequestBody CheckoutCart request) throws Exception {
		Cart userCart = cartService.findById(cartId)
				.orElseThrow(() -> new Exception("Bu kimliğe sahip sepet yok : " + cartId));

		Integer orderId = orderService.checkOutCart(userCart.getId(), request);

		cartService.delete(userCart);

		Map<String, Boolean> response = new HashMap<>();
		response.put(orderId + "", Boolean.TRUE);
		return response;
	}

}
