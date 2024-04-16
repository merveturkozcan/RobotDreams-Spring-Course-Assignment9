package com.robotdreams.assignment9.service.mapper;

import com.robotdreams.assignment9.dto.ProductDetailRequestDto;
import com.robotdreams.assignment9.entity.ProductDetail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductDetailMapper {
    ProductDetailMapper INSTANCE = Mappers.getMapper(ProductDetailMapper.class);

    ProductDetail mapToProductDetail(ProductDetailRequestDto dto);
}
