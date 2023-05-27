package com.barbarian.barbarianfood.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "offers")
public class Offer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private String id;
    private String title;
    private OfferFor offerFor;
    private int kcalRangeTop;
    private int kcalRangeBottom;
    private OfferPeriod period;
    private String contraindications;
    private BigDecimal cost;

    @OneToMany(mappedBy = "offer")
    private List<CustomerBase> customers;
}
