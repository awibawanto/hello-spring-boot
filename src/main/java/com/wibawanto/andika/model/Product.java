package com.wibawanto.andika.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author andika
 */
@Entity
@Table(name = "product")
public class Product {

    private Long id;
    private String name;
    private Integer quantity;
    private BigDecimal price;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Column(name = "name", nullable = false, unique = true)
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "quantity", nullable = false)
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Column(name = "price", nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal unitPrice) {
        this.price = unitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("name='").append(name).append('\'');
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}

