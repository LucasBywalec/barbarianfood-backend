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
@Table(name = "customers")
public class CustomerBase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private String id;
    private String name;
    private String surname;
    @Column(unique = true)
    private String email;
    private String password;
    private UserRole role;

    @OneToOne(mappedBy = "customerBase", cascade = CascadeType.ALL)
    private CustomerAddress address;

    @OneToOne(mappedBy = "customerBase", cascade = CascadeType.ALL)
    private CustomerPayment payments;

    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;

}