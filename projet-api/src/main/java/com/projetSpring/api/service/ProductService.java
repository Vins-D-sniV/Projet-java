package com.projetSpring.api.service;

import com.projetSpring.api.Dto.ProductCreateDTO;
import com.projetSpring.api.Dto.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO createProduct(ProductCreateDTO createDTO);
    ProductDTO getProductById(Long id);
    List<ProductDTO> getAllProducts();
    List<ProductDTO> searchProductsByName(String name);
    ProductDTO updateProduct(Long id, ProductCreateDTO updateDTO);
    ProductDTO partialUpdateProduct(Long id, ProductCreateDTO updateDTO);
    void deleteProduct(Long id);
    boolean existsById(Long id);
}
