package com.microsservice.productservice.service;

import com.microsservice.productservice.dto.ProductRequest;
import com.microsservice.productservice.dto.ProductResponse;
import com.microsservice.productservice.model.Product;
import com.microsservice.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        repository.save(product);

        log.info("Product {} is saved", product.getId());
    }
    public List<ProductResponse> getAllProducts(){
        List<Product> products = repository.findAll();
        return products.stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .toList();
    }
}

