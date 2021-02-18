package com.example.northwind.entities.concretes;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Embeddable
public class OrderDetailIDUsingEmbeddable implements Serializable {
	private static final long serialVersionUID = 1L;

	private int order_id;

	private int product_id;

}
