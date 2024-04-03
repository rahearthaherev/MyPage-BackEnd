package com.jdg.mypage.mapper;

import com.jdg.mypage.dto.AcHistoryDTO;
import com.jdg.mypage.entity.AcHistory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ACHistoryMapper {
    ACHistoryMapper INSTANCE = Mappers.getMapper(ACHistoryMapper.class);

    AcHistory dtoToEntity(AcHistoryDTO acHistoryDTO);
}
