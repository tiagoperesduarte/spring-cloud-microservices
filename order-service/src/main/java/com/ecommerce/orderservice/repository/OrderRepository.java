package com.ecommerce.orderservice.repository;

import com.ecommerce.orderservice.model.Order;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, String> {
}
