package com.ecommerce.authservice.repository;

import com.ecommerce.authservice.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {
    Optional<User> findByEmail(String email);

    boolean existsById(String id);

    boolean existsByEmail(String email);

    boolean existsByIdNotAndEmail(String id, String email);
}
