package com.example.pinkbullmakeup.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
public class Brand {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID brandId;

    @NotNull
    @Size(max = 25)
    @Column(unique = true)
    private String brandName;

    public Brand() {}

    public Brand(UUID brandId, String brandName) {
        this.brandId = brandId;
        this.brandName = brandName;
    }

    public UUID getBrandId() {
        return brandId;
    }

    public void setBrandId(UUID brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
