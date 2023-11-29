package com.jdg.mypage.mapper;

import com.jdg.mypage.dto.StylingDTO;
import com.jdg.mypage.entity.StylingData;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StylingMapper {
    StylingMapper INSTANCE = Mappers.getMapper(StylingMapper.class);


    StylingDTO entityToDTO(StylingData stylingData);


    StylingData dtoToEntity(StylingDTO stylingDTO);
}
