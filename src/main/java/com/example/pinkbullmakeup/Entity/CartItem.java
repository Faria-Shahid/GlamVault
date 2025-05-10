package com.example.pinkbullmakeup.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
public class CartItem {
    @Id
    @UuidGenerator
    @GeneratedValue
    private UUID cartItemId;

    @ManyToOne
    private Product productInCart;

    @NotNull
    @Min(1)
    @Max(100)
    private int productQuantity;

    @NotNull
    private float priceAccordingToQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Cart cart;
    
    public CartItem() {}

    public CartItem(Product productInCart, int productQuantity) {
        this.productInCart = productInCart;
        this.productQuantity = productQuantity;
        calculateAllItemsPrice();
    }

    public UUID getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(UUID cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Product getProductInCart() {
        return productInCart;
    }

    public void setProductInCart(Product productInCart) {
        this.productInCart = productInCart;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
        calculateAllItemsPrice();
    }

    public float getPriceAccordingToQuantity() {
        return priceAccordingToQuantity;
    }

    public void setPriceAccordingToQuantity(float priceAccordingToQuantity) {
        this.priceAccordingToQuantity = priceAccordingToQuantity;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void calculateAllItemsPrice() {
        if (productInCart != null && productQuantity >= 1) {
            priceAccordingToQuantity = productQuantity * productInCart.getProductPrice();
        } else {
            priceAccordingToQuantity = 0;
        }
    }

}
