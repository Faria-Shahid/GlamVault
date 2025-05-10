package com.example.pinkbullmakeup.Service;

import com.example.pinkbullmakeup.DTO.AddProduct;
import com.example.pinkbullmakeup.DTO.ProductResponseForCustomer;
import com.example.pinkbullmakeup.Entity.Brand;
import com.example.pinkbullmakeup.Entity.Category;
import com.example.pinkbullmakeup.Entity.Product;
import com.example.pinkbullmakeup.Exceptions.AlreadyExistsException;
import com.example.pinkbullmakeup.Exceptions.ResourceNotFoundException;
import com.example.pinkbullmakeup.Mapping.ProductMapping;
import com.example.pinkbullmakeup.Repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapping productMapping;
    private final CategoryService categoryService;

    private final BrandService brandService;

    public ProductService(ProductRepository productRepository, ProductMapping productMapping, CategoryService categoryService, BrandService brandService) {
        this.productRepository = productRepository;
        this.productMapping = productMapping;
        this.categoryService = categoryService;
        this.brandService = brandService;
    }

    public Product addProduct(AddProduct addProduct){
        Product product = productMapping.toProduct(addProduct,categoryService,brandService);

        if (productRepository.existsByProductNameAndProductBrandAndProductCategory(
                product.getProductName(),
                product.getProductBrand(),
                product.getProductCategory())) {
            throw new AlreadyExistsException(product.getProductName());
        }

        productRepository.save(product);
        return product;
    }

    @Transactional(readOnly = true)
    public List<ProductResponseForCustomer> getAllProducts(){
        List<Product> products = productRepository.findAll();

        return productMapping.toCustomerResponseList(products);
    }

    @Transactional(readOnly = true)
    public List<ProductResponseForCustomer> getAllProductsByBrand(String brandName){
        Brand brand = brandService.findByName(brandName);

        List<Product> products = productRepository.findAllByProductBrand(brand);

        return productMapping.toCustomerResponseList(products);
    }

    @Transactional(readOnly = true)
    public List<ProductResponseForCustomer> getAllProductsByCategory(String categoryName){
        Category category = categoryService.findByName(categoryName);

        List<Product> products = productRepository.findAllByProductCategory(category);

        return productMapping.toCustomerResponseList(products);
    }

    @Transactional(readOnly = true)
    public List<ProductResponseForCustomer> sortAllProductsByPriceLowToHigh(){
        List<Product> products = productRepository.findAllByOrderByProductPriceAsc();

        return productMapping.toCustomerResponseList(products);
    }

    @Transactional(readOnly = true)
    public List<ProductResponseForCustomer> sortAllProductsByPriceHighToLow(){
        List<Product> products = productRepository.findAllByOrderByProductPriceDesc();

        return productMapping.toCustomerResponseList(products);
    }

    public void deleteProduct(UUID productId) {
        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("Product with id: " + productId + " not found.");
        }
        productRepository.deleteById(productId);
    }

    public void updateProductPrice(float newPrice,UUID productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(()->new ResourceNotFoundException("Product with id: " + productId + " not found."));

        product.setProductPrice(newPrice);

        productRepository.save(product);
    }

    public void updateProductStock(int newQuantity, UUID productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(()->new ResourceNotFoundException("Product with id: " + productId + " not found."));

        product.setProductQuantityInStock(newQuantity);

        productRepository.save(product);
    }

    public void reduceStockAfterSale(int itemsSold, UUID productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(()->new ResourceNotFoundException("Product with id: " + productId + " not found."));

        product.updateProductStockAfterSale(itemsSold);

        productRepository.save(product);
    }

    public Product findById(UUID productId){
        return productRepository.findById(productId)
                .orElseThrow(()->new ResourceNotFoundException("Product with id: " + productId + " not found."));
    }
}
