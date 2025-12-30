package com.projetSpring.api.mapper;

import com.projetSpring.api.Dto.ProductCreateDTO;
import com.projetSpring.api.Dto.ProductDTO;
import com.projetSpring.api.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDTO toDTO(Product product) {
        if (product == null) {
            return null;
        }

        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    public Product toEntity(ProductCreateDTO createDTO) {
        if (createDTO == null) {
            return null;
        }

        return Product.builder()
                .name(createDTO.getName())
                .description(createDTO.getDescription())
                .price(createDTO.getPrice())
                .stock(createDTO.getStock())
                .build();
    }

    public void updateEntityFromDTO(ProductCreateDTO dto, Product product) {
        if (dto == null || product == null) {
            return;
        }

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
    }

    public void partialUpdateEntityFromDTO(ProductCreateDTO dto, Product product) {
        if (dto == null || product == null) {
            return;
        }

        if (dto.getName() != null) {
            product.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            product.setDescription(dto.getDescription());
        }
        if (dto.getPrice() != null) {
            product.setPrice(dto.getPrice());
        }
        if (dto.getStock() != null) {
            product.setStock(dto.getStock());
        }
    }
}