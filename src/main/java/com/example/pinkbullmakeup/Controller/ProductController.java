package com.example.pinkbullmakeup.Controller;

import com.example.pinkbullmakeup.DTO.AddProduct;
import com.example.pinkbullmakeup.DTO.ProductResponseForCustomer;
import com.example.pinkbullmakeup.Entity.Product;
import com.example.pinkbullmakeup.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@Valid @RequestBody AddProduct addProduct) {
        Product product = productService.addProduct(addProduct);
        return ResponseEntity.created(URI.create("/api/products/" + product.getProductId())).body(product);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseForCustomer>> getAllProducts() {
        List<ProductResponseForCustomer> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/brand")
    public ResponseEntity<List<ProductResponseForCustomer>> getProductsByBrand(@RequestParam String brandName) {
        List<ProductResponseForCustomer> products = productService.getAllProductsByBrand(brandName);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/category")
    public ResponseEntity<List<ProductResponseForCustomer>> getProductsByCategory(@RequestParam String categoryName) {
        List<ProductResponseForCustomer> products = productService.getAllProductsByCategory(categoryName);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/sort/price-low-to-high")
    public ResponseEntity<List<ProductResponseForCustomer>> sortProductsByPriceLowToHigh() {
        List<ProductResponseForCustomer> products = productService.sortAllProductsByPriceLowToHigh();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/sort/price-high-to-low")
    public ResponseEntity<List<ProductResponseForCustomer>> sortProductsByPriceHighToLow() {
        List<ProductResponseForCustomer> products = productService.sortAllProductsByPriceHighToLow();
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/price")
    public ResponseEntity<Void> updateProductPrice(@RequestParam float newPrice, @PathVariable UUID id) {
        productService.updateProductPrice(newPrice, id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<Void> updateProductStock(@RequestParam int newQuantity, @PathVariable UUID id) {
        productService.updateProductStock(newQuantity, id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/sales")
    public ResponseEntity<Void> updateProductSales(@RequestParam int itemsSold, @PathVariable UUID id) {
        productService.reduceStockAfterSale(itemsSold, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable UUID id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok(product);
    }
}

