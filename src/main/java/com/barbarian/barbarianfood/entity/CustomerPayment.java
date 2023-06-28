package com.barbarian.barbarianfood.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer_payments")
public class CustomerPayment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private String id;
    private String creditCardNumber;
    private String creditCardOwner;
    private LocalDate creditCardExpDate;
    private String creditCardSecret;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private CustomerBase customerBase;
}
