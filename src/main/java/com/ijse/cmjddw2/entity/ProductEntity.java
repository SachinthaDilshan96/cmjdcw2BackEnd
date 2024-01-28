package com.ijse.cmjddw2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    @Column(unique = true)
    private String name;
    private long addedOn;
    private int initialQty;
    private int qty;
    private double unitPrice;
    @Column(name = "expire_date")
    private long expireDate;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
    @JsonIgnore
    @ManyToMany(mappedBy = "products")
    private Set<OrderEntity> orders = new HashSet<>();

}
