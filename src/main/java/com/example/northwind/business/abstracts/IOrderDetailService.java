package com.example.northwind.business.abstracts;

import java.util.List;

import com.example.northwind.entities.concretes.OrderDetail;

public interface IOrderDetailService {

	List<OrderDetail> getAll();

	OrderDetail add(OrderDetail orderDetail);

}
