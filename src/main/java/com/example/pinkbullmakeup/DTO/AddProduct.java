package com.example.pinkbullmakeup.DTO;

import com.example.pinkbullmakeup.Entity.Shade;
import lombok.Data;


import java.util.List;

@Data
public class AddProduct {
    private String prodName;
    private String prodImage;
    private String prodCategory;
    private String prodBrand;
    private float prodPrice;
    private int quantityInStock;
    private List<Shade> shades;
}
