package com.chakkiapp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class DeliveryAgent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
}
