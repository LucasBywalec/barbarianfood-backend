package com.barbarian.barbarianfood.config;

import com.barbarian.barbarianfood.entity.CustomerAddress;
import com.barbarian.barbarianfood.entity.CustomerBase;
import com.barbarian.barbarianfood.entity.CustomerPayment;
import com.barbarian.barbarianfood.entity.Offer;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class HibernateConfig {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @PostConstruct
    public void init() {
        final List<Class<?>> entityClasses = Arrays.asList(
                CustomerBase.class,
                CustomerAddress.class,
                CustomerPayment.class,
                Offer.class
        );
    }
}


