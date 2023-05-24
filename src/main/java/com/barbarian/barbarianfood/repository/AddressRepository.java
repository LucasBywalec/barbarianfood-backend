package com.barbarian.barbarianfood.repository;

import com.barbarian.barbarianfood.entity.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<CustomerAddress, String> {
}
