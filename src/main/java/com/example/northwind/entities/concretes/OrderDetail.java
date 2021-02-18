package com.example.northwind.entities.concretes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "order_details")
@NoArgsConstructor
@IdClass(OrderDetailIdUsingIdclass.class)
public class OrderDetail {
	@Id
	private int order_id;
	@Id
	private int product_id;
	
	private double unit_price;
	private int quantity;
	private double discount;

}
