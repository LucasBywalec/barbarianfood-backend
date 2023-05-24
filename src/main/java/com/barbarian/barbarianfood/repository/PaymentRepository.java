package com.barbarian.barbarianfood.repository;

import com.barbarian.barbarianfood.entity.CustomerPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<CustomerPayment, String> {
}
