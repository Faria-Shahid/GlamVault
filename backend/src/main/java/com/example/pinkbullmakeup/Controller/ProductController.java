package com.example.pinkbullmakeup.Controller;

import com.example.pinkbullmakeup.DTO.AddProduct;
import com.example.pinkbullmakeup.DTO.ProductResponseForCustomer;
import com.example.pinkbullmakeup.Entity.Product;
import com.example.pinkbullmakeup.Service.ImageStorageService;
import com.example.pinkbullmakeup.Service.ProductService;
import com.example.pinkbullmakeup.Service.ProductUploadService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final ProductUploadService productUploadService;

    public ProductController(ProductService productService, ProductUploadService productUploadService) {
        this.productService = productService;
        this.productUploadService = productUploadService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductResponseForCustomer> addProduct(
            @RequestPart("data") @Valid AddProduct addProduct,
            @RequestPart("image") MultipartFile imageFile,
            @RequestPart("shadeImages") List<MultipartFile> shadeImageFiles) {

        // Debugging logs:
        System.out.println("ImageFile: " + imageFile);  // This should not be null
        System.out.println("ShadeImages: " + shadeImageFiles.size()); // Should match your files

        AddProduct populatedProduct = productUploadService.handleUpload(addProduct, imageFile, shadeImageFiles);
        ProductResponseForCustomer savedProduct = productService.addProduct(populatedProduct);

        return ResponseEntity
                .created(URI.create("/api/products/" + savedProduct.getProductId()))
                .body(savedProduct);
    }

    @PreAuthorize("permitAll()")
    @GetMapping
    public ResponseEntity<List<ProductResponseForCustomer>> getAllProducts() {
        List<ProductResponseForCustomer> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/brand")
    public ResponseEntity<List<ProductResponseForCustomer>> getProductsByBrand(@RequestParam String brandName) {
        List<ProductResponseForCustomer> products = productService.getAllProductsByBrand(brandName);
        return ResponseEntity.ok(products);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/category")
    public ResponseEntity<List<ProductResponseForCustomer>> getProductsByCategory(@RequestParam String categoryName) {
        List<ProductResponseForCustomer> products = productService.getAllProductsByCategory(categoryName);
        return ResponseEntity.ok(products);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/sort/price-low-to-high")
    public ResponseEntity<List<ProductResponseForCustomer>> sortProductsByPriceLowToHigh() {
        List<ProductResponseForCustomer> products = productService.sortAllProductsByPriceLowToHigh();
        return ResponseEntity.ok(products);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/sort/price-high-to-low")
    public ResponseEntity<List<ProductResponseForCustomer>> sortProductsByPriceHighToLow() {
        List<ProductResponseForCustomer> products = productService.sortAllProductsByPriceHighToLow();
        return ResponseEntity.ok(products);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/price")
    public ResponseEntity<Void> updateProductPrice(@RequestParam float newPrice, @PathVariable UUID id) {
        productService.updateProductPrice(newPrice, id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/stock")
    public ResponseEntity<Void> updateProductStock(@RequestParam int newQuantity, @PathVariable UUID id) {
        productService.updateProductStock(newQuantity, id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable UUID id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok(product);
    }
}

