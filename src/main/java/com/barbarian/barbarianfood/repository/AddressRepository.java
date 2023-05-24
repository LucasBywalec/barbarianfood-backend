package com.barbarian.barbarianfood.repository;

import com.barbarian.barbarianfood.entity.CustomerAddress;
import com.barbarian.barbarianfood.entity.CustomerBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<CustomerAddress, String> {

    Optional<CustomerAddress> findByCustomerBase(CustomerBase customerBase);
}
