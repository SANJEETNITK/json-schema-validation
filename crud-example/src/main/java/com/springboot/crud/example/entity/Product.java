package com.springboot.crud.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @Column
    private int quantity;
    @Column
    private double price;

}
