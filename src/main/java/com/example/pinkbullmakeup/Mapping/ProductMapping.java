package com.example.pinkbullmakeup.Mapping;

import com.example.pinkbullmakeup.DTO.AddProduct;
import com.example.pinkbullmakeup.DTO.ProductResponseForCustomer;
import com.example.pinkbullmakeup.Entity.Brand;
import com.example.pinkbullmakeup.Entity.Category;
import com.example.pinkbullmakeup.Entity.Product;
import com.example.pinkbullmakeup.Service.BrandService;
import com.example.pinkbullmakeup.Service.CategoryService;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapping {

    @Mapping(target = "productName", source = "prodName")
    @Mapping(target = "productImage", source = "prodImage")
    @Mapping(target = "productPrice", source = "prodPrice")
    @Mapping(target = "productQuantityInStock", source = "quantityInStock")
    @Mapping(target = "shades", source = "shades")
    @Mapping(target = "productCategory", expression = "java(mapCategory(dto.getProdCategory(), categoryService))")
    @Mapping(target = "productBrand", expression = "java(mapBrand(dto.getProdBrand(), brandService))")
    Product toProduct(AddProduct dto, @Context CategoryService categoryService, @Context BrandService brandService);

    @Mapping(target = "prodName", source = "productName")
    @Mapping(target = "prodImage", source = "productImage")
    @Mapping(target = "prodPrice", source = "productPrice")
    @Mapping(target = "prodCategory", expression = "java(product.getProductCategory().getCategoryName())")
    @Mapping(target = "prodBrand",    expression = "java(product.getProductBrand().getBrandName())")
    @Mapping(target = "shades",        source = "shades")
    ProductResponseForCustomer toCustomerResponse(Product product);

    List<ProductResponseForCustomer> toCustomerResponseList(List<Product> products);

    // Helper methods for mapping string to entity using services
    default Category mapCategory(String categoryName, @Context CategoryService categoryService) {
        return categoryService.findByName(categoryName);
    }

    default Brand mapBrand(String brandName, @Context BrandService brandService) {
        return brandService.findByName(brandName);
    }
}
