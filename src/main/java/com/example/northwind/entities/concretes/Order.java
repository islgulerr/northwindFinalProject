package com.example.northwind.entities.concretes;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.northwind.entities.abstracts.IEntity;

import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order implements IEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private int id;
	@Column(name = "customer_id")
	private String customerId;
	@Column(name = "employee_id")
	private int employeeId;
	@Column(name = "order_date")
	private Date orderDate;
	@Column(name = "required_date")
	private Date requiredDate;
	@Column(name = "shipped_date")
	private Date shippedDate;
	@Column(name = "ship_via")
	private int shipVia;
	@Column(name = "freight")
	private double freight;
	@Column(name = "ship_name")
	private String shipName;
	@Column(name = "ship_address")
	private String shipAddress;
	@Column(name = "ship_city")
	private String shipCity;
	@Column(name = "ship_region")
	private String shipRegion;
	@Column(name = "ship_postal_code")
	private String shipPostalCode;
	@Column(name = "ship_country")
	private String shipCountry;
	@Column(name = "discontinued")
	private Integer discontinued;
	@Column(name = "reorder_level")
	private Integer reorderLevel;
	@Column(name = "supplier_id")
	private Integer supplierId;
	@Column(name = "units_in_stock")
	private Integer unitsInStock;
	@Column(name = "units_on_order")
	private Integer unitsOnOrder;
}
