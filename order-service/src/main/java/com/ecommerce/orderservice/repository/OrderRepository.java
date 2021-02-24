package com.ecommerce.orderservice.repository;

import com.ecommerce.orderservice.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, String> {
    Page<Order> findAllByUserId(String userId, Pageable pageable);

    Optional<Order> findByIdAndUserId(String id, String userId);
}
