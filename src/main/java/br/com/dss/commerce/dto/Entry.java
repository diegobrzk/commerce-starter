package br.com.dss.commerce.dto;

import lombok.*;

import java.io.Serializable;

@Data
public class Entry implements Serializable {

    private Product product;
    private Integer quantity;

    public Entry() {
    }

    public Entry(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
