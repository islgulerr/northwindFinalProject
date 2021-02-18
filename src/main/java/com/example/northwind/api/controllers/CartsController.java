package com.example.northwind.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.northwind.business.abstracts.ICartService;
import com.example.northwind.business.abstracts.ICustomerService;
import com.example.northwind.entities.concretes.Cart;
import com.example.northwind.entities.concretes.Customer;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/api/v1")
public class CartsController {

	@Autowired
	private ICartService cartService;

	@Autowired
	private ICustomerService customerService;

	@GetMapping("/carts")
	public Map<String, Boolean> getAll() throws Exception {
		List<Cart> cartGetAll = cartService.getAll();

		Map<String, Boolean> response = new HashMap<>();
		response.put(cartGetAll + "Sepetler listelendi.", Boolean.TRUE);
		return response;
	}

	@PostMapping("/carts")
	public Map<String, Boolean> add(@RequestBody Cart cart) throws Exception {
		Cart cartAdd = cartService.add(cart);
		Map<String, Boolean> response = new HashMap<>();
		if (cartAdd == null) {
			response.put("Sepet eklenemedi.", Boolean.FALSE);
			return response;
		} else {
			response.put(cartAdd + "Sepet eklendi.", Boolean.TRUE);
			return response;
		}
	}

	@DeleteMapping("/carts")
	public Map<String, Boolean> delete(@RequestBody Cart cart) throws Exception {

		Cart cartDelete = cartService.findById(cart.getId())
				.orElseThrow(() -> new Exception("Bu kimliğe sahip ürün yok : " + cart.getId()));

		cartService.delete(cartDelete);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Sepet silindi", Boolean.TRUE);
		return response;
	}

	@PostMapping("/carts/{id}/{pid}/{qty}")
	public Map<String, Boolean> addProductCart(@PathVariable("id") String customerId,
			@PathVariable("pid") Integer productId, @PathVariable("qty") int quantity) throws Exception {

		Integer addedQuantity = cartService.productQuantity(productId, customerId, quantity);

		Map<String, Boolean> response = new HashMap<>();
		response.put("Toplam ürün miktar sayısı: " + addedQuantity, Boolean.TRUE);
		return response;
	}

	@PostMapping("/carts/update/{id}/{pid}/{qty}")
	public Map<String, Boolean> updateQuantity(@PathVariable("id") String customerId,
			@PathVariable("pid") Integer productId, @PathVariable("qty") int quantity) throws Exception {

		Customer customerItem = customerService.findByCustomer(customerId);

		cartService.updateQuantity(productId, quantity, customerItem.getId());

		Map<String, Boolean> response = new HashMap<>();
		response.put("Ürün miktarı başarıyla güncellendi.", Boolean.TRUE);
		return response;
	}

	@GetMapping("/carts/{cid}")
	public Map<String, Boolean> getById(@PathVariable(value = "cid") Integer cartId) throws Exception {

		Cart cartGetById = cartService.findById(cartId)
				.orElseThrow(() -> new Exception("Bu kimliğe sahip sepet yok : " + cartId));

		Map<String, Boolean> response = new HashMap<>();
		response.put(cartGetById + "ID'si girilen sepet bilgileri getirildi.", Boolean.TRUE);
		return response;
	}

}
