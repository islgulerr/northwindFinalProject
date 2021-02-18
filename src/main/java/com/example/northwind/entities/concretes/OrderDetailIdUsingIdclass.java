package com.example.northwind.entities.concretes;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class OrderDetailIdUsingIdclass implements Serializable {

	private int order_id;

	private int product_id;
}
