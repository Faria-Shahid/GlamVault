package com.example.pinkbullmakeup.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Category {

    @Id
    @UuidGenerator
    private UUID categoryId;

    @NotNull
    @Column(unique = true)
    private String categoryName;

    @OneToMany(mappedBy = "productCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();


    public Category() {}

    public Category(UUID categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
