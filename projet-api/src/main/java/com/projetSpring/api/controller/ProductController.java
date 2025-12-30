package com.projetSpring.api.controller;

import com.projetSpring.api.Dto.ProductCreateDTO;
import com.projetSpring.api.Dto.ProductDTO;
import com.projetSpring.api.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductCreateDTO createDTO) {
        ProductDTO createdProduct = productService.createProduct(createDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(
            @RequestParam(required = false) String name) {
        List<ProductDTO> products;
        if (name != null && !name.isEmpty()) {
            products = productService.searchProductsByName(name);
        } else {
            products = productService.getAllProducts();
        }
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductCreateDTO updateDTO) {
        ProductDTO updatedProduct = productService.updateProduct(id, updateDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDTO> partialUpdateProduct(
            @PathVariable Long id,
            @RequestBody ProductCreateDTO updateDTO) {
        ProductDTO updatedProduct = productService.partialUpdateProduct(id, updateDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

 /*   @HeadMapping("/{id}")
    public ResponseEntity<Void> checkProductExists(@PathVariable Long id) {
        boolean exists = productService.existsById(id);
        return exists ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @OptionsMapping
    public ResponseEntity<Void> options() {
        return ResponseEntity.ok()
                .allow(
                        org.springframework.http.HttpMethod.GET,
                        org.springframework.http.HttpMethod.POST,
                        org.springframework.http.HttpMethod.PUT,
                        org.springframework.http.HttpMethod.PATCH,
                        org.springframework.http.HttpMethod.DELETE,
                        org.springframework.http.HttpMethod.HEAD,
                        org.springframework.http.HttpMethod.OPTIONS
                )
                .build();
    }*/
}