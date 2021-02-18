package com.example.northwind.dataaccess.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.northwind.entities.concretes.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

	public Optional<Cart> findById(Integer id);

	public Optional<Cart> findById(String customerId);

	public List<Cart> findByCustomerId(String customerId);

	public Cart findByCustomerIdAndProductId(String customerId, Integer productId);

	@Query("UPDATE Cart c SET c.quantity = ?1 WHERE c.productId = ?2 " + "AND c.customerId = ?3")
	@Modifying
	public void updateQuantity(Integer quantity, Integer productId, String customerId);

}
