package com.robotdreams.assignment9.service.mapper;

import com.robotdreams.assignment9.dto.ProductDetailRequestDto;
import com.robotdreams.assignment9.dto.ProductRequestDto;
import com.robotdreams.assignment9.dto.ProductResponseDto;
import com.robotdreams.assignment9.entity.Product;
import com.robotdreams.assignment9.entity.ProductDetail;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductMapper {

    @Mapping(target = "productDetail", source = "productDetail", qualifiedByName = "mapToProductDetail")
    Product mapToProduct(ProductRequestDto dto);

    ProductResponseDto productToProductResponseDto(Product product);

    @Named("mapToProductDetail")
    default ProductDetail mapToProductDetail(ProductDetailRequestDto dto) {
        return ProductDetailMapper.INSTANCE.mapToProductDetail(dto);
    }

    @AfterMapping
    default void afterMapping(@MappingTarget Product product) {
        var detail = product.getProductDetail();
        detail.setProduct(product);
    }

}
