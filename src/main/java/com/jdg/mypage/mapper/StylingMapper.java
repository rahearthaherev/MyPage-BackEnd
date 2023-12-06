package com.jdg.mypage.mapper;

import com.jdg.mypage.dto.PersonalInfoDTO;
import com.jdg.mypage.dto.StylingDTO;
import com.jdg.mypage.entity.StylingData;
import com.jdg.mypage.entity.StylingPersonalInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StylingMapper {
    StylingMapper INSTANCE = Mappers.getMapper(StylingMapper.class);

    StylingDTO entityToDTO(StylingData stylingData);
    StylingData dtoToEntity(StylingDTO stylingDTO);


@Mapper
public interface PersonalInfoMapper {
    PersonalInfoMapper INSTANCE = Mappers.getMapper(PersonalInfoMapper.class);
    StylingPersonalInfo dtoToEntity(PersonalInfoDTO personalInfoDTO);
    PersonalInfoDTO entityToDTO(StylingPersonalInfo stylingPersonalInfo);
}
}