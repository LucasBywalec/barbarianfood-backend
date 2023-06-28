package com.barbarian.barbarianfood.repository;

import com.barbarian.barbarianfood.entity.CustomerAddress;
import com.barbarian.barbarianfood.entity.CustomerBase;
import com.barbarian.barbarianfood.entity.CustomerPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<CustomerPayment, String> {
    Optional<CustomerPayment> findByCustomerBase(CustomerBase customerBase);
}
