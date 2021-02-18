package com.example.northwind.api.controllers.dto;

import java.sql.Date;
import java.util.Map;

import lombok.Data;

import com.sun.istack.NotNull;

@Data
public class CheckoutCart {

	@NotNull
	private Map<Integer, Integer> productIdProductCount;

	private int employeeId;

	private Date orderDate;

	private Date requiredDate;

	private Date shippedDate;

	private int shipVia;

	private double freight;

	private String shipName;

	private String shipAddress;

	private String shipCity;

	private String shipRegion;

	private String shipPostalCode;

	private String shipCountry;

	private Integer discontinued;

	private Integer reorderLevel;

	private Integer supplierId;

	private Integer unitsInStock;

	private Integer unitsOnOrder;

}
