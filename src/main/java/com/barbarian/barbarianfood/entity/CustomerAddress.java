package com.barbarian.barbarianfood.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer_addresses")
public class CustomerAddress implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private String id;
    private String street;
    private int buildingNumber;
    private String postalCode;
    private String voivodeship;
    private String city;
    private String phoneNumber;

    @OneToOne
    @MapsId
    @JoinColumn(name = "customer_id")
    private CustomerBase customerBase;
}
