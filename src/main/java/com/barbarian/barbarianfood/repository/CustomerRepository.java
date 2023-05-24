package com.barbarian.barbarianfood.repository;

import com.barbarian.barbarianfood.entity.CustomerBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerBase, String> {

    Optional<CustomerBase> findByEmail(final String email);

    void updateCustomerBaseByEmail();
}
