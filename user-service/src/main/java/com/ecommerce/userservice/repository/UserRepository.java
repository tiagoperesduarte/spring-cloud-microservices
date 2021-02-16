package com.ecommerce.userservice.repository;

import com.ecommerce.userservice.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {
    boolean existsById(String id);

    boolean existsByEmail(String email);

    boolean existsByIdNotAndEmail(String id, String email);
}
