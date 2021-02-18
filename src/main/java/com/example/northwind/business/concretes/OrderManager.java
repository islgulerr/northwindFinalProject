package com.example.northwind.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.northwind.api.controllers.dto.CheckoutCart;
import com.example.northwind.business.abstracts.IOrderService;
import com.example.northwind.dataaccess.concretes.CartRepository;
import com.example.northwind.dataaccess.concretes.OrderDetailRepository;
import com.example.northwind.dataaccess.concretes.OrderRepository;
import com.example.northwind.dataaccess.concretes.ProductRepository;
import com.example.northwind.entities.concretes.Cart;
import com.example.northwind.entities.concretes.Order;
import com.example.northwind.entities.concretes.OrderDetail;
import com.example.northwind.entities.concretes.Product;

@Service
public class OrderManager implements IOrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Override
	public List<Order> getAll() {
		return orderRepository.findAll();
	}

	@Override
	public Order add(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public Optional<Order> findById(int orderId) {

		return orderRepository.findById(orderId);
	}

	@Override
	public String delete(Order order) {

		orderRepository.delete(order);
		return order + "silindi";
	}

	public Integer checkOutCart(int cartId, CheckoutCart request) throws Exception {
		Cart userCart = cartRepository.findById(cartId)
				.orElseThrow(() -> new Exception("Bu kimliğe sahip kategori yok : " + cartId));

		Order order = new Order();

		order.setCustomerId(userCart.getCustomerId());
		order.setEmployeeId(request.getEmployeeId());
		order.setOrderDate(request.getOrderDate());
		order.setRequiredDate(request.getRequiredDate());
		order.setShippedDate(request.getShippedDate());
		order.setShipVia(request.getShipVia());
		order.setFreight(request.getFreight());
		order.setShipName(request.getShipName());
		order.setShipName(request.getShipName());
		order.setShipAddress(request.getShipAddress());
		order.setShipCity(request.getShipCity());
		order.setShipRegion(request.getShipRegion());
		order.setShipPostalCode(request.getShipPostalCode());
		order.setShipCountry(request.getShipCountry());
		order.setDiscontinued(request.getDiscontinued());
		order.setReorderLevel(request.getReorderLevel());
		order.setSupplierId(request.getSupplierId());
		order.setUnitsInStock(request.getUnitsInStock());
		order.setUnitsOnOrder(request.getUnitsOnOrder());

		orderRepository.save(order);

		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrder_id(order.getId());
		orderDetail.setProduct_id(userCart.getProductId());
		Product product = productRepository.getOne(orderDetail.getProduct_id());
		orderDetail.setUnit_price(product.getUnitPrice());
		orderDetail.setQuantity(userCart.getQuantity());
		orderDetail.setDiscount(0.15);

		orderDetailRepository.save(orderDetail);

		return order.getId();
	}

}
