package com.example.pinkbullmakeup.Controller;

import com.example.pinkbullmakeup.Entity.Brand;
import com.example.pinkbullmakeup.Service.BrandService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/brands")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Brand> createBrand(@Valid @RequestParam String brandName) {
        Brand brand = brandService.addBrand(brandName);
        return ResponseEntity.created(URI.create("/api/brands/" + brand.getBrandId())).body(brand);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Brand> updateBrand(@Valid @RequestParam String newBrandName, @PathVariable UUID id) {
        Brand updatedBrand = brandService.updateBrand(newBrandName, id);
        return ResponseEntity.ok(updatedBrand);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable UUID id) {
        brandService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Brand>> getAllBrands() {
        List<Brand> brands = brandService.getAllBrands();
        return ResponseEntity.ok(brands);
    }

    @GetMapping("/search")
    public ResponseEntity<Brand> getBrandByName(@RequestParam String brandName) {
        Brand brand = brandService.findByName(brandName);
        return ResponseEntity.ok(brand);
    }
}

