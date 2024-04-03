package com.jdg.mypage.mapper;

import com.jdg.mypage.dto.AcDetailsDTO;
import com.jdg.mypage.entity.AcDetail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ACDetailMapper {
    ACDetailMapper INSTANCE = Mappers.getMapper(ACDetailMapper.class);
    Iterable<AcDetail> dtoToEntity(Iterable<AcDetailsDTO> acDetailsDTOs);
    Iterable<AcDetailsDTO> entityToDto(Iterable<AcDetail> acDetailsDTOs);
}
