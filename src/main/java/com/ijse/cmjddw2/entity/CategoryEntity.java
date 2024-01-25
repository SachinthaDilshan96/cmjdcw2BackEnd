package com.ijse.cmjddw2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true)
    private String name;
    private String addedBy;
    private long addedOn;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "category",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProductEntity> products;
}
