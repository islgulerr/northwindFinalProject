package com.example.northwind.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.northwind.business.abstracts.IOrderDetailService;
import com.example.northwind.entities.concretes.OrderDetail;

@RestController
@RequestMapping("/api/v1")
public class OrderDetailsController {

	@Autowired
	private IOrderDetailService orderDetailService;

	@GetMapping("/orders/orderdetails")
	public Map<String, Boolean> getAll() throws Exception {
		List<OrderDetail> orderDetailGetAll = orderDetailService.getAll();
		Map<String, Boolean> response = new HashMap<>();

		response.put(orderDetailGetAll + "Sipariş detayları listelendi.", Boolean.TRUE);
		return response;
	}

	@PostMapping("/orders/orderdetails")
	public Map<String, Boolean> add(@RequestBody OrderDetail orderDetail) throws Exception {
		OrderDetail orderDetailAdd = orderDetailService.add(orderDetail);
		Map<String, Boolean> response = new HashMap<>();

		response.put(orderDetailAdd + "Sipariş detayları eklendi.", Boolean.TRUE);
		return response;

	}

}
