package com.example.pinkbullmakeup.Service;

import com.example.pinkbullmakeup.Entity.Brand;
import com.example.pinkbullmakeup.Exceptions.AlreadyExistsException;
import com.example.pinkbullmakeup.Exceptions.ResourceNotFoundException;
import com.example.pinkbullmakeup.Repository.BrandRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class BrandService {
    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public Brand addBrand(String brandName) {
        String name = brandName.trim();

        if (brandRepository.existsByBrandName(name)){
            throw new AlreadyExistsException(name);
        }

        Brand brand = new Brand();
        brand.setBrandName(name);

        return brandRepository.save(brand);
    }

    public Brand updateBrand(String newBrandName, UUID brandId) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new ResourceNotFoundException("Brand with id " + brandId + " not found."));

        brand.setBrandName(newBrandName);

        return brandRepository.save(brand);
    }

    public void deleteBrand(UUID brandId){
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new ResourceNotFoundException("Brand with id " + brandId + " not found."));

        brandRepository.delete(brand);
    }

    @Transactional(readOnly = true)
    public List<Brand> getAllBrands(){
        return brandRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Brand findByName(String brandName) {
        return brandRepository.findBrandByBrandName(brandName.trim())
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found."));
    }

}
