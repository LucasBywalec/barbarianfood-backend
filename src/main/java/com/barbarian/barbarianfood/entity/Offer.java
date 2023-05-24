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
@Table(name = "OFFERS")
public class Offer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private String id;
    private String title;
    private OfferFor offerFor;
    private int kcalRangeTop;
    private int kcalRangeBottom;
    private OfferPeriod period;
    private String contraindications;
    private float cost;
}
